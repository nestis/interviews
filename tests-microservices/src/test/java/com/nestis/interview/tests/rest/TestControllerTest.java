package com.nestis.interview.tests.rest;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import com.nestis.interview.tests.TestsApplication;
import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.exception.TestsException;
import com.nestis.interview.tests.service.TestService;
import com.nestis.interview.tests.service.TokenService;
import com.nestis.interview.tests.service.model.FinishTestDto;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"config.endpoints.tests=tests", "security.endpoint=security", "security.tokenHeader=token"})
public class TestControllerTest {

	private final String securityToken = "SEC";
	
	@Value("${security.tokenHeader}")
	private String tokenHeader;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private TestService testService;
	
	@MockBean
	private TokenService tokenService;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@Test
	public void shouldGetTestFromAToken() throws Exception {

		Token token = new Token();
		token.setTestId(1);
		given(tokenService.getToken(eq("1"))).willReturn(token);
		
		com.nestis.interview.tests.entity.Test test = new com.nestis.interview.tests.entity.Test();
		test.setLeader("Test Leader");
		test.setName("Test candidate");
		test.setTestId(1);	
		given(testService.getTestById(eq(1))).willReturn(test);
		
		given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Void.class)))
			.willReturn(new ResponseEntity<Void>(HttpStatus.OK));
		
		webTestClient.get()
			.uri("/tests/{token}", "1")
			.header(tokenHeader, securityToken)
			.exchange()
			.expectStatus().isOk()
			.expectBody(com.nestis.interview.tests.entity.Test.class)
			.consumeWith(testResponse -> {
				assertThat(testResponse.getResponseBody().getName(), comparesEqualTo("Test candidate"));
			});
	}
	
	@Test
	public void shouldACreateNewTest() throws Exception {
		FinishTestDto mark = new FinishTestDto();
		mark.setTestId(1);
		mark.setAnswers(Collections.EMPTY_MAP);
		
		given(testService.finishTest(eq(mark))).willReturn(true);
		
		given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Void.class)))
			.willReturn(new ResponseEntity<Void>(HttpStatus.OK));
		
		webTestClient.post().uri("/tests/mark")
			.body(Mono.just(mark), FinishTestDto.class)
			.exchange()
			.expectStatus().is2xxSuccessful();
	}
	
	@Test
	public void shouldMarkATest() throws Exception {
		assert(false);
	}
	
	@Test
	public void shouldReturnATestsException() throws Exception {
		given(tokenService.getToken(anyString())).willThrow(RuntimeException.class);

		given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Void.class)))
			.willReturn(new ResponseEntity<Void>(HttpStatus.OK));
		
		webTestClient.get().uri("/tests/{token}", "1")
			.exchange()
			.expectStatus().is5xxServerError()
			.expectBody(TestsException.class);
	}
	
	@Test
	public void shouldReturn403Error() throws Exception {
		webTestClient.get().uri("/tests/{token}", "1")
			.exchange()
			.expectStatus().is4xxClientError();
	}
}
