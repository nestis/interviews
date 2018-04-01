package com.nestis.interview.tests.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nestis.interview.tests.TestsApplication;
import com.nestis.interview.tests.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "endpoints.tests=tests")
public class TestControllerTest {

	@Autowired
	private WebTestClient webtestClient;
	
	@MockBean
	private TestService testService;
	
	@Test
	public void shouldGetTest() throws Exception {
		assert(false);
	}
	
	@Test
	public void shouldCreateNewTest() throws Exception {
		assert(false);
	}
	
	@Test
	public void shouldMarkTest() throws Exception {
		assert(false);
	}
}
