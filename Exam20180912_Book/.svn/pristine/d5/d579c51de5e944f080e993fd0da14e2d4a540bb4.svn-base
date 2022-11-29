package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ExerciseChapter extends Chapter{
	
	protected List<Question> questions = new LinkedList<>();

    public ExerciseChapter(String title, int numPages) {
    	super(title, numPages);
	}

	public List<Topic> getTopics() {
		Set<Topic> topics = new HashSet<>();
		for(Question q: questions) {
			topics.add(q.getMainTopic()); 
		}
		
		return topics.stream()
				.sorted(Comparator.comparing(Topic::getKeyword))
				.collect(Collectors.toList());
	};
    

	public void addQuestion(Question question) {
		questions.add(question);
	}	
}
