package vocab.com.bae.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String icelandic;
	private String english;
	private String pos;
	private int score;

//	***************** Constructors *********************
	public Word(String icelandic, String english, String pos, int score) {
		super();
		this.icelandic = icelandic;
		this.english = english;
		this.pos = pos;
		this.score = score;
	}

	public Word(int id, String icelandic, String english, String pos, int score) {
		super();
		this.id = id;
		this.icelandic = icelandic;
		this.english = english;
		this.pos = pos;
		this.score = score;
	}

	public Word() {
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Word: " + icelandic + ", English: " + english + ", Part of speech: " + pos + ", Score: "
				+ score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(english, icelandic, id, pos, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(english, other.english) && Objects.equals(icelandic, other.icelandic) && id == other.id
				&& Objects.equals(pos, other.pos) && score == other.score;
	}

//	*************** Getters and Setters **************

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcelandic() {
		return icelandic;
	}

	public void setIcelandic(String icelandic) {
		this.icelandic = icelandic;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
