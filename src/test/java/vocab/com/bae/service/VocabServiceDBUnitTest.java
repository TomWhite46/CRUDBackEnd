package vocab.com.bae.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Test
	void testCreate() {
		int id = 1;
		Word newWord = new Word("land", "country", "noun", 2);
		Word testWord = new Word(1, "land", "country", "noun", 2);
//		on test save, return testWord
		Mockito.when(this.repo.save(newWord)).thenReturn(testWord);

//		input test arguments
		Word actual = this.service.createWord(newWord);

//		check that this has returned the same as testDinosaur
		assertThat(actual).isEqualTo(testWord);

		Mockito.verify(this.repo, Mockito.times(1)).save(newWord);

	}

	@Test
	void testDelete() {
		int id = 2;
		String testString = "ID 2 deleted.";
		String actual = this.service.deleteWord(id);
		assertThat(actual).isEqualTo(testString);

		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);

	}

	@Test
	void testReplace() {

		int id = 2;
		Word origWord = new Word(id, "land", "country", "noun", 1);
		Word replacementWord = new Word("svartur", "black", "adjective", 2);
		Word finalWord = new Word(id, "svartur", "black", "adjective", 2);

//		on findbyId for 2, return land
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(origWord));

//		on saving replacementWord, return it with id2
		Mockito.when(this.repo.save(finalWord)).thenReturn(finalWord);

//		input test arguments
		Word actual = this.service.replaceWord(id, replacementWord);

//		check results
		assertThat(actual).isEqualTo(finalWord);

//		check numbers
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(finalWord);

	}

	@Test
	void testGetRandom() {
		Word randomWord = new Word(1, "land", "country", "noun", 2);

		List<Word> testList = new ArrayList<>(List.of(new Word(1, "land", "country", "noun", 2)));

		// on getLowestScores, return testList
		Mockito.when(this.repo.getLowestScores()).thenReturn(testList);

		// cannot mock Math.random (apparently) so test list only has item - so this is
		// the only possible one

		Word actual = this.service.getRandom();

		assertThat(actual).isEqualTo(randomWord);

		Mockito.verify(this.repo, Mockito.times(1)).getLowestScores();

	}

	@Test
	void testaddScore() {
		int id = 1;
		Word inputWord = new Word(id, "land", "country", "noun", 2);
		Word outputWord = new Word(id, "land", "country", "noun", 3);

		// on findById, produce inputWord
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(inputWord));

		// on save, return final item
		Mockito.when(this.repo.save(outputWord)).thenReturn(outputWord);

		// create actual result
		Word actual = this.service.addScore(id);

		// run test
		assertThat(actual).isEqualTo(outputWord);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(outputWord);
	}

}
