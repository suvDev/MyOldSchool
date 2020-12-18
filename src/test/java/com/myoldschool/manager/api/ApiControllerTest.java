package com.myoldschool.manager.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myoldschool.manager.BussLayer;
import com.myoldschool.manager.BussLayerHibernate;
import com.myoldschool.manager.MockConstants;
import com.myoldschool.manager.api.ApiController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BussLayer bs;

    @MockBean
    BussLayerHibernate bsh;

    @MockBean
    private ApiController controller;

    @Test
    void testUpdateUserAPI() throws Exception{

        HashMap<String, String> mockResponse = new HashMap<>();
        mockResponse.put(MockConstants.STATUS, MockConstants.RECORDS_UPDATED);
        given(controller.updateUser(any())).willReturn(mockResponse);

        Student student = new Student(
                MockConstants.id, MockConstants.name, MockConstants.rollNo, MockConstants.marks);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(student);
        mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.UPDATE_RECORD)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MockConstants.STATUS).value(MockConstants.RECORDS_UPDATED));
    }
}
