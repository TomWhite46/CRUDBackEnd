package vocab.com.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vocab.com.bae.data.Word;
import vocab.com.bae.data.repos.VocabRepo;

@Service

public class VocabServiceDB implements VocabService {

	private VocabRepo repo;

//	constructor
	public VocabServiceDB(VocabRepo repo) {
		super();
		this.repo = repo;
	}

//	get all
	@Override
	public List<Word> getAll() {
		return this.repo.findAll();
	}

//	create new
	@Override
	public Word createWord(Word word) {
		this.repo.save(word);
		return word;
	}

//	delete by id
	@Override
	public String deleteWord(int id) {
		this.repo.deleteById(id);
		return "ID " + id + " deleted.";
	}

//	replace by id with new word
	@Override
	public Word replaceWord(int id, Word word) {
		Word foundWord = this.repo.findById(id).get();
		foundWord.setIcelandic(word.getIcelandic());
		foundWord.setEnglish(word.getEnglish());
		foundWord.setPos(word.getPos());
		foundWord.setScore(word.getScore());
		this.repo.save(foundWord);
		return foundWord;
	}

}
