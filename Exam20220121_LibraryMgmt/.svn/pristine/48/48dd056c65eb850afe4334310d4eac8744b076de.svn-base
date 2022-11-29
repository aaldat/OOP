package libraryMgmt;

import java.time.LocalDate;

public class Loan {
	
	private int loanIn;
	private int code;
	private Book book;
	private User user;
	LocalDate dateRest, dateRestEff;
	private String state;
	
	public Loan(int loanIn, int code, User user, Book book, LocalDate dateRest) {
		this.loanIn = loanIn;
		this.code = code;
		this.book = book;
		this.user = user;
		this.dateRest = dateRest;
	}
	
	public int getLoanIn() {
		return loanIn;
	}
	
	public int getCode() {
		return code;
	}

	public LocalDate getDateRestEff() {
		return dateRestEff;
	}

	public void setDateRestEff(LocalDate dateRestEff) {
		this.dateRestEff = dateRestEff;
	}

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}

	public LocalDate getDateRest() {
		return dateRest;
	}
	
	public String getState(LocalDate date) {
		if(this.dateRestEff == null && date.isBefore(this.dateRest)) {
			state = "ongoing";
		} else if(this.dateRestEff == null && date.isAfter(this.dateRest)) {
			state = "overdue";
		} else {
			state = "closed";
		}
		return state;
	}
	
//	@Override
//	public String toString() {
//		return user + ":" + code + ":" + ;
//	}

}
