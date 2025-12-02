package com.dsqd.nacf.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void indexEndpointReturnsHelloVscode() throws Exception {
		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string("hello vscode"));
	}

	@Test
	void postGreetWithNameReturnsHelloName() throws Exception {
		mvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "Alice"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello Alice."));
	}

	@Test
	void postGreetWithoutNameReturnsHelloWorld() throws Exception {
		mvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello world."));
	}

	@Test
	void userGetWithNameReturnsVscodeMessage() throws Exception {
		mvc.perform(get("/user").param("name", "KKK"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello~ KKK.\n" + //
                                "Source: GET parameter\n" + //
                                "I'm vscode. Nice meet you."));
	}
}
