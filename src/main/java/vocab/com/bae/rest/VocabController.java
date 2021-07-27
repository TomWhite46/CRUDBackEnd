package vocab.com.bae.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vocab.com.bae.data.Word;
import vocab.com.bae.service.VocabService;

@RestController
public class VocabController {

	public VocabService service;

//	constructor (grab VocabServiceDB as service)
	public VocabController(VocabService service) {
		super();
		this.service = service;
	}

//	GetAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Word>> getAll() {
		List<Word> allWords = this.service.getAll();
		return new ResponseEntity<>(allWords, HttpStatus.OK);
	}

	// Create new
	@PostMapping("/create")
	public ResponseEntity<Word> createWord(@RequestBody Word newWord) {
		Word createdWord = this.service.createWord(newWord);
		return new ResponseEntity<>(createdWord, HttpStatus.CREATED);

	}

//	Delete selected
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteWord(@PathVariable int id) {
		String returnString = this.service.deleteWord(id);
		return new ResponseEntity<>(returnString, HttpStatus.ACCEPTED);
	}

//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Word> replaceWord(@PathVariable int id, @RequestBody Word newWord) {
		Word returnWord = this.service.replaceWord(id, newWord);
		return new ResponseEntity<>(returnWord, HttpStatus.OK);
	}

}
