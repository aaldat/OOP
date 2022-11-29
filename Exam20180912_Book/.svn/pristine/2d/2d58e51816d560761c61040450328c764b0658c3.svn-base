package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class TheoryChapter extends Chapter {
	
	private String text;
	private Set<Topic> topicSet = new HashSet<>();

    public TheoryChapter(String title, int numPages, String text) {
    	super(title, numPages);
		this.text = text;
	}

	public String getText() {
		return text;
	}

    public void setText(String newText) {
    	this.text = newText;
    }

    public void addTopic(Topic topic) {
    	if(!topicSet.contains(topic)) {
			topicSet.add(topic);
			for(Topic t: topic.getSubTopics()) {
				addTopic(t);
			}
		}
    }

	public List<Topic> getTopics() {
		System.out.println(topicSet.stream()
        		.sorted(Comparator.comparing(Topic::getKeyword))
        		.collect(Collectors.toList()));
        return topicSet.stream()
        		.sorted(Comparator.comparing(Topic::getKeyword))
        		.collect(Collectors.toList());
	}
    
}
