package com.myoldschool.manager;

import com.myoldschool.manager.api.ApiController;
import com.myoldschool.manager.api.Student;
import com.netflix.ribbon.proxy.annotation.Http;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManagerApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	private ApiController controller;

	@Test
	void testGetAllRecords(){
		Student student = new Student(1,"Jack",101,"99");
		ArrayList<Student> mocklist = new ArrayList<>();
		mocklist.add(student);
		given(controller.getAllRecords()).willReturn(mocklist);

		ResponseEntity<ArrayList<Student>> responseEntity = testRestTemplate.exchange("/getRecords",
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Student>>() {});
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

		ArrayList<Student> list = responseEntity.getBody();
		assertThat(list.get(0).getId()).isEqualTo(1);
	}

}
