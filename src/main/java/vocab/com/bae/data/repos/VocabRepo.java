package vocab.com.bae.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import vocab.com.bae.data.Word;

public interface VocabRepo extends JpaRepository<Word, Integer> {

}
