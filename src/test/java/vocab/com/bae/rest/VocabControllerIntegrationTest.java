package vocab.com.bae.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@Sql(scripts = { "classpath:vocab-schema.sql",
		"classpath:vocab-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@AutoConfigureMockMvc

@ActiveProfiles("test")

public class VocabControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

//	test for create new word
	@Test
	void testCreate() throws Exception {

	}

//	test for getall
	@Test
	void testGetall() throws Exception {

	}

}
