package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Book {
	
	private Map<String, Topic> topicsDictionary = new HashMap<>();
	private List<Question> questions = new LinkedList<>();
	private List<Chapter> chapters = new LinkedList<>();

    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
		if (keyword == null || keyword == "") throw new BookException();
		if (!topicsDictionary.containsKey(keyword)) {
			topicsDictionary.put(keyword, new Topic(keyword));
		}
		return topicsDictionary.get(keyword);
	}

	public Question createQuestion(String question, Topic mainTopic) {
		Question q = new Question(question, mainTopic);
	    questions.add(q);
	    return q;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		TheoryChapter c = new TheoryChapter(title, numPages, text);
		chapters.add(c);
		return c;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter c = new ExerciseChapter(title, numPages);
		chapters.add(c);
		return c;
	}

	public List<Topic> getAllTopics() {
		return chapters.stream()									//Prendo la lista di capitoli
		        .flatMap(c -> c.getTopics().stream())				//Riprendo una lista di Topics
		        .distinct()											//senza ripetizioni
		        .sorted(Comparator.comparing(Topic::getKeyword))	//ordinati alfabeticamente
		        .collect(Collectors.toList());						//in una lista
	}

	public boolean checkTopics() {
		Set<Topic> t = chapters.stream()					//Prendo la lista di capitoli
				.filter(c -> c instanceof TheoryChapter)	//prendo solo i capitoli di teoria
				.flatMap(c -> c.getTopics().stream())		//Riprendo una lista di Topics
				.collect(Collectors.toSet());				//in un Set (senza ripetizioni)
		Set<Topic> e = chapters.stream()					//Prendo la lista di capitoli
				.filter(c -> c instanceof ExerciseChapter)	//prendo solo i capitoli di esercizi
				.flatMap(c -> c.getTopics().stream())		//Riprendo una lista di Topics
				.collect(Collectors.toSet());				//in un Set (senza ripetizioni)
		return t.containsAll(e);		//Vedo se la lista dei capitoli di teoria e' uguale a quella di esercizi
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		return new Assignment(ID, chapter);
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
    	return chapters.stream()
		        .filter(c -> c instanceof ExerciseChapter)
				.flatMap(c -> ((ExerciseChapter) c).questions.stream())
				.collect(Collectors.groupingBy(q -> q.numAnswers(), Collectors.toList()));
    }
}
