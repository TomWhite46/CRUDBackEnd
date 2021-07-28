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
		return this.repo.save(word);
	}

//	delete by id
	@Override
	public String deleteWord(int id) {
		this.repo.deleteById(id);
		return "ID " + id + " deleted.";
	}

//	replace by id with new word
	@Override
	public Word replaceWord(int id, Word newWord) {
		Word found = this.repo.findById(id).get();

		found.setIcelandic(newWord.getIcelandic());
		found.setEnglish(newWord.getEnglish());
		found.setPos(newWord.getPos());
		found.setScore(newWord.getScore());
		Word updated = this.repo.save(found);
		return found;
	}

//	get random
	@Override
	public Word getRandom() {
		List<Word> lowestList = this.repo.getLowestScores();
		int i = (int) (Math.random() * lowestList.size());
		Word randomWord = lowestList.get(i);
		return randomWord;
	}

//	increment score
	@Override
	public Word addScore(int id) {
		Word found = this.repo.findById(id).get();
		found.setScore(found.getScore() + 1);
		return this.repo.save(found);
	}

}
