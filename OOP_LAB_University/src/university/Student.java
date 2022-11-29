package university;

public class Student {
	//private final static int MIN_MATR = 10000;
	private final static int MAX_COURSE = 25;
	
	private String first, last;
	private int matr = 0, nextCourse = 0;
	
	private Course[] studyPlan = new Course[MAX_COURSE];
	
	private Exam[] exams = new Exam[MAX_COURSE];
	private int nextExamIndex = 0;

	public Student(int matr, String first, String last) {
		this.first = first;
		this.last = last;
		this.matr = matr;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public int getMatr() {
		return matr;
	}

	public void setMatr(int matr) {
		this.matr = matr;
	}

	public int addStudyPlan(Course c) {
		studyPlan[nextCourse] = c;
		return nextCourse++;
	}
	
	public int getNextCourse() {
		return nextCourse;
	}
	
	public String getStudyPlan() {
		StringBuffer bf = new StringBuffer();
		
		for(Course c : studyPlan) {
			if(c != null) {
				bf.append(c.toString()).append("\n");
			}
		}
		return bf.toString().trim();
	}
	
	public int addExam(Exam e) {
		exams[nextExamIndex] = e;
		return nextExamIndex++;
	}
	
	public double average() {
		if(nextExamIndex == 0) {
			return Double.NaN;
		}
		double sum = 0.0;
		for(Exam e : exams) {
			if(e == null) {
				break;
			}
			sum += e.getGrade();
		}
		return sum/nextExamIndex;
	}
	
	public double getScore() {
		double avg = average();
		if(Double.isNaN(avg)) return Double.NEGATIVE_INFINITY;
		int taken = nextExamIndex;
		int enrolled = 0;
		while(studyPlan[enrolled]!=null) {enrolled++;}
		
		return avg + 10*taken/(double)enrolled;
	}

	@Override
	public String toString() {
		return matr + " " + first + " " + last;
	}
	
	
	
	
	
	

	
	
	
	
}
