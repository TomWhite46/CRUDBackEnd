package vocab.com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import vocab.com.bae.data.Word;
import vocab.com.bae.data.repos.VocabRepo;

@SpringBootTest
@ActiveProfiles("test")
public class VocabServiceDBUnitTest {

	@Autowired
	private VocabServiceDB service;

	@MockBean
	private VocabRepo repo;

	@Test
	void testGetAll() {
		// expected outcome (arraylist)
		List<Word> testList = new ArrayList<>(
				List.of(new Word(1, "land", "country", "noun", 2), new Word(2, "svartur", "black", "adjective", 1)));

		// on running findAll, return the testlist
		Mockito.when(this.repo.findAll()).thenReturn(testList);

		// get actual results
		List<Word> actualList = this.service.getAll();

		// check if equal
		assertThat(actualList).isEqualTo(testList);

		// check no of runs
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
}
