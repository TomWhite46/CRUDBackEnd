package vocab.com.bae.service;

import java.util.List;

import vocab.com.bae.data.Word;

public interface VocabService {

//	GetAll
	public List<Word> getAll();

//	Create new
	public Word createWord(Word word);

//	Delete selected
	public String deleteWord(int id);

//	Update
	public Word replaceWord(int id, Word word);

//	GetRandom
	public Word getRandom();

//	increment score
	public Word addScore(int id);

}
