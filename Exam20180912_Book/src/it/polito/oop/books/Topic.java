package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Topic {
	
	private String keyword;
	private Set<Topic> subTopics = new HashSet<Topic>();
	
	Topic(String keyword) throws BookException {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	
	@Override
	public String toString() {
		return keyword;
	}

	public boolean addSubTopic(Topic topic) {
		if (subTopics.contains(topic)) {
			return false;
		} else {
			subTopics.add(topic);
			return true;
		}
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
		return subTopics.stream()
				.sorted(Comparator.comparing(Topic::getKeyword))
				.collect(Collectors.toList());
	}
}
