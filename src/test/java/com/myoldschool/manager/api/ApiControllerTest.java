package com.myoldschool.manager.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myoldschool.manager.BussLayer;
import com.myoldschool.manager.BussLayerHibernate;
import com.myoldschool.manager.MockConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

        // Given: Test updateUser API
        // When: a request to updateUser API is made
        HashMap<String, String> mockResponse = new HashMap<>();
        mockResponse.put(MockConstants.STATUS, MockConstants.RECORDS_UPDATED);
        given(controller.updateUser(any())).willReturn(mockResponse);

        // object mapper to convert pojo class to string while creating a post request
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = new Student(
                MockConstants.id, MockConstants.name, MockConstants.rollNo, MockConstants.marks);

        // Then: it should match the following:
        mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.UPDATE_RECORD)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MockConstants.STATUS).value(MockConstants.RECORDS_UPDATED));
    }

    @Test
    void testDeleteUserAPI() throws Exception{
        HashMap<String, String> map = new HashMap<>();
        map.put(MockConstants.STATUS, MockConstants.RECORD_DELETED);
        given(controller.deleteUser(any())).willReturn(map);

        ObjectMapper objectMapper = new ObjectMapper();
        Student student = new Student(
                MockConstants.id, MockConstants.name, MockConstants.rollNo, MockConstants.marks);

        mockMvc.perform(MockMvcRequestBuilders.post(MockConstants.DELETE_RECORD)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MockConstants.STATUS).value(MockConstants.RECORD_DELETED));
    }
}
