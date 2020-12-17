package com.myoldschool.manager;

import com.myoldschool.manager.api.ApiController;
import com.myoldschool.manager.api.Student;
import com.netflix.ribbon.proxy.annotation.Http;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManagerApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ApiController controller;

    @BeforeAll
    public void mockResponseForApis(){

        // Mock GetUsers API response
        Student student = new Student(
                MockConstants.id, MockConstants.name, MockConstants.rollNo, MockConstants.marks);
        ArrayList<Student> mockList = new ArrayList<>();
        mockList.add(student);
        given(controller.getAllRecords()).willReturn(mockList);

        // Mock PostUser API response
        HashMap<String, String> mockResponse = new HashMap<>();
        mockResponse.put(MockConstants.STATUS, MockConstants.RECORDS_INSERTED);
        given(controller.postUser(any())).willReturn(mockResponse);
    }

    @Test
    void testGetAllRecords() {
        ResponseEntity<ArrayList<Student>> responseEntity = testRestTemplate.exchange(MockConstants.GET_RECORDS,
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Student>>() {
                });
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ArrayList<Student> list = responseEntity.getBody();
        assertThat(list.get(0).getId()).isEqualTo(MockConstants.id);
        assertThat(list.get(0).getName()).isEqualTo(MockConstants.name);
        assertThat(list.get(0).getRollno()).isEqualTo(MockConstants.rollNo);
        assertThat(list.get(0).getMarks()).isEqualTo(MockConstants.marks);
    }

    @Test
    void testPostUser(){

        Student student = new Student(
                MockConstants.id, MockConstants.name, MockConstants.rollNo, MockConstants.marks);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> request = new HttpEntity<>(student, headers);

        ResponseEntity<Map> result = testRestTemplate.postForEntity(MockConstants.SAVE_RECORDS, request, Map.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        HashMap<String, String> map = (HashMap<String, String>) result.getBody();

        assertThat(map.size()).isEqualTo(1);
        assertThat(map.get(MockConstants.STATUS)).isEqualTo(MockConstants.RECORDS_INSERTED);

    }

}
