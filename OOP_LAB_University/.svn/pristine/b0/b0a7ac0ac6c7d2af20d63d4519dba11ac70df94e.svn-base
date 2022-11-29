package university;

public class Course {
	//private final static int MIN_COURSE = 10;
	private final static int MAX_STUD = 100;
	
	private String title, teacher;
	private int code = 0, nextStud = 0;
	private Student[] enrolled = new Student[MAX_STUD];

	
	private Exam[] exams = new Exam[MAX_STUD];
	private int nextExamIndex = 0;
			
	public Course(int code, String title, String teacher) {
		this.title = title;
		this.teacher = teacher;
		this.code = code;
		//this.voto = voto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int setEnrolled(Student s) {
		enrolled[nextStud] = s;
		return nextStud++;
	}
	
	public String getEnrolled() {
		String result = "";
		
		for(int i = 0; i < nextStud; i++) {
			Student s = enrolled[i];
			result += s.toString() + "\n";
		}
		return result.trim();
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

	@Override
	public String toString() {
		return code + "," + title + "," + teacher;
	}
	
	
	
	
	

}
