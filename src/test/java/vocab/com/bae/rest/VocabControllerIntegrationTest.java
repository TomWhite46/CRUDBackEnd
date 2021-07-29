package vocab.com.bae.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import vocab.com.bae.data.Word;

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
		// define word to test-create
		Word testWord = new Word("svartur", "black", "adjective", 0);

		// convert to JSON
		String testWordAsJSON = this.mapper.writeValueAsString(testWord);

		// build request using JSON string
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testWordAsJSON);

		// *** build response checkers**

		// for status code
		ResultMatcher checkStatus = status().isCreated();

		// body
		Word testCreatedWord = new Word(4, "svartur", "black", "adjective", 0); // create test word
		String testCreatedWordAsJSON = this.mapper.writeValueAsString(testCreatedWord); // convert to JSON
		ResultMatcher checkBody = content().json(testCreatedWordAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

//	test for getall
	@Test
	void testGetall() throws Exception {
		// request builder
		RequestBuilder request = get("/getAll");

		// *** response ***
		// status code
		ResultMatcher checkStatus = status().isOk();

		// body
		List<Word> testCreatedWords = new ArrayList<>(List.of(new Word(1, "kona", "woman", "noun", 0),
				new Word(2, "barn", "child", "noun", 1), new Word(3, "koma", "to come", "verb", 3)));
		String testCreatedWordsAsJSON = this.mapper.writeValueAsString(testCreatedWords);
		ResultMatcher checkBody = content().json(testCreatedWordsAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

//	test for delete by id
	@Test
	void testDeleteById() throws Exception {
		// request builder
		int testId = 2;
		RequestBuilder request = delete("/delete/" + testId);

		// *** response ***
		// status code
		ResultMatcher checkStatus = status().isAccepted();

		// body
		ResultMatcher checkBody = content().string("ID " + testId + " deleted.");
		;

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

//	test for put by id
	@Test
	void testPutById() throws Exception {
		// request builder
		int testId = 2;
		Word testWord = new Word("svartur", "black", "adjective", 5);
		String testWordAsJSON = this.mapper.writeValueAsString(testWord);
		RequestBuilder request = put("/update/" + testId).contentType(MediaType.APPLICATION_JSON)
				.content(testWordAsJSON);

		// *** response ***
		// status code
		ResultMatcher checkStatus = status().isOk();

		// body
		Word testReplacedWord = new Word(2, "svartur", "black", "adjective", 5);
		String testReplacedWordAsJSON = this.mapper.writeValueAsString(testReplacedWord);
		ResultMatcher checkBody = content().json(testReplacedWordAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

//	test get random lowest-scoring word
	@Test
	void testGetRandom() throws Exception {
		// build request
		RequestBuilder request = get("/getRandom");

		// *** response ***
		// status code
		ResultMatcher checkStatus = status().isOk();

		// body - because SQL data has kona as lowest scoring, this should be returned
		Word randomWord = new Word(1, "kona", "woman", "noun", 0);
		String randomWordAsJSON = this.mapper.writeValueAsString(randomWord);
		ResultMatcher checkBody = content().json(randomWordAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

//	test increment score
	@Test
	void testAddScore() throws Exception {
		// build request
		RequestBuilder request = patch("/addScore/2");

		// *** response ***
		// status code
		ResultMatcher checkStatus = status().isAccepted();

		// body
		Word testWord = new Word(2, "barn", "child", "noun", 2); // ie score 1 higher than in SQL
		String testWordAsJSON = this.mapper.writeValueAsString(testWord);
		ResultMatcher checkBody = content().json(testWordAsJSON);

		// run test
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
}
