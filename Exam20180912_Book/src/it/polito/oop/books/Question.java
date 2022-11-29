package it.polito.oop.books;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Question {
	
	String question;
	Topic mainTopic;
	private Map<String, Boolean> answers = new HashMap<>();
	
	public Question(String question, Topic mainTopic) {
		this.question = question;
		this.mainTopic = mainTopic;
	}

	public String getQuestion() {
		return question;
	}
	
	public Topic getMainTopic() {
		return mainTopic;
	}
	
	@Override
    public String toString() {
    	return question + " (" + mainTopic + ")";
    }

	public void addAnswer(String answer, boolean correct) {
		answers.put(answer, correct);
	}
	
	public long numAnswers() {
	    return answers.size();
	}

	public Set<String> getCorrectAnswers() {
		return answers.entrySet().stream()
				.filter(t -> t.getValue().equals(true))
				.map(t -> t.getKey())
				.collect(Collectors.toSet());
	}

	public Set<String> getIncorrectAnswers() {
		return answers.entrySet().stream()
				.filter(t -> t.getValue().equals(false))
				.map(t -> t.getKey())
				.collect(Collectors.toSet());
	}
}
