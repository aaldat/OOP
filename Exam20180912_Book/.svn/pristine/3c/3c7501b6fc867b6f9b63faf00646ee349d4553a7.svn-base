package it.polito.oop.books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Assignment {
	
	private String ID;
	private ExerciseChapter chapter;
	private Map<Question, Double> scores = new HashMap<>();
	
	Assignment(String iD, ExerciseChapter chapter) {
		this.ID = iD;
		this.chapter = chapter;
	}

    public String getID() {
        return ID;
    }

    public ExerciseChapter getChapter() {
        return chapter;
    }

    public double addResponse(Question q,List<String> answers) {
    	Set<String> correct = q.getCorrectAnswers();
		Set<String> incorrect = q.getIncorrectAnswers();

		double n = correct.size() + incorrect.size();

		double fp = answers.stream().filter(a -> !correct.contains(a)).count();
		double fn = correct.stream().filter(a -> !answers.contains(a)).count();

		double score = (n - fp - fn) / n;

		scores.put(q, score);

		return score;
    }
    
    public double totalScore() {
    	return scores.values().stream()
    			.mapToDouble(s -> s)
    			.sum();
    }

}
