package vocab.com.bae.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vocab.com.bae.data.Word;

public interface VocabRepo extends JpaRepository<Word, Integer> {

	@Query(value = "SELECT * FROM word WHERE score = (select MIN(score) FROM word)", nativeQuery = true)
	List<Word> getLowestScores();

}
