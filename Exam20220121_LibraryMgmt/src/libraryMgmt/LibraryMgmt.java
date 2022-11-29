package libraryMgmt;
import java.util.*;
import java.util.stream.Collectors;
import java.time.*;

public class LibraryMgmt {

	private int code = 0, loanIn = 1;
	LocalDate date;
	private SortedMap<String, Book> books = new TreeMap<>();
	private Map<String, User> users = new HashMap<>();
	private Map<Integer, Loan> loans = new HashMap<>();
	
	//R0
	/**
	 * Defines the current date
	 * @param date current date
	 */
	public void setCurrentDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * retrieves current library system date
	 * @return current date
	 */
	public LocalDate getCurrentDate () {
		return date;
	}

	/**
	 * Moves current date forward
	 * @param nOfDays number of days forward
	 */
	public void addDays (long nOfDays) {
		date = date.plusDays(nOfDays);
	}
	
	public LocalDate getAddDays (long nOfDays) {
		return date.plusDays(nOfDays);
	}


	//R1
	/**
	 * Add a new book with corresponding volumes
	 * 
	 * @param title    title of the book
	 * @param nVolumes number of volumes available
	 * @param authors  list of authors
	 * @return volume index range
	 * @throws LMException
	 */
	public String addBook(String title, int nVolumes, String... authors) throws LMException {
		if(books.containsKey(title)) throw new LMException("E' gia' presente un libro con questo titolo.");
		Book b = new Book(code+1, code+nVolumes, title, nVolumes, authors);
		books.put(title, b);
		code = code + nVolumes;
		return b.getMinCode() + ":" + b.getMaxCode();
	}

	/**
	 * Adds a new user with relative parameters
	 * 
	 * @param name
	 * @param maxNofBooks
	 * @param duration
	 * @return
	 * @throws LMException
	 */
	public String addUser (String name, int maxNofBooks, int duration) throws LMException {
		if(users.containsKey(name)) throw new LMException("E' gia' presente un utente con questo nome.");
		User u = new User(name, maxNofBooks, duration);
		users.put(name, u);
		return name + ":" + maxNofBooks + ":" + duration;
	}

	//R2
	/**
	 * Adds a new volume loan in the system.
	 * 
	 * @param user : user name
	 * @param title: book title
	 * @return loan index
	 * @throws LMException
	 */
	public int addLoan (String user, String title) throws LMException {
		User u = users.get(user);
		Book b = books.get(title);
		boolean noRest = false;
		for(Loan l : u.getLoans().values()) {
			if(l.getDateRest().isBefore(date)) {
				noRest = true;
			}
		}
		if(u.getMaxNofBooks() == 0) throw new LMException("L'utente " + user + " non puo' prendere in prestito altri libri.");
		if(noRest == true) throw new LMException("L'utente " + user + " non ha ancora restituito dei libri.");
		if(b.getnVolumes() == 0) throw new LMException("Non sono presenti volumi disponibili per il seguente libro: " + title);
		Loan l = new Loan(loanIn, b.getMinCode(), u, b, getAddDays(u.getDuration()));
		b.rmVolume();
		u.rmBooks();
		//addDays(u.getDuration());
		u.addLoan(loanIn, l);
		loans.put(loanIn, l);
		return loanIn++;
	}

	/**
	 * Retrieves loan information
	 * 
	 * @param loanIndex
	 * @return information as string
	 */
	public String getLoanInfo (int loanIndex) {
		Loan l = loans.get(loanIndex);
		return l.getUser().getName() + ":" + loanIndex + ":" + l.getCode() + ":" + l.getDateRest().toString() + ":" + l.getState(date).toString();
	}

	/**
	 * Closes a loan
	 * 
	 * @param loanIndex loan index
	 * @return loan return date
	 */
	public LocalDate closeLoan (int loanIndex)  { //throws LMException
		Loan l = loans.get(loanIndex);
		l.getBook().addVolume();;
		l.getUser().addBooks();;
		l.setDateRestEff(date);
		return l.getDateRestEff();
	}


	/**
	 * Retrieves number of volumes currently on loan to user
	 * @param user
	 * @return number of volumes
	 */
	public int numberOfBooks (String user) {
		return users.get(user).getLoanBooks();
	}

	//R3  statistics

	/**
	 * Returns map of authors grouped by title
	 * 
	 * @return map title -> author list
	 */
	public TreeMap<String, ArrayList<String>> authorsByTitle() {
		Map<String, ArrayList<String>> map = books.values().stream()
												.sorted(Comparator.comparing(Book::getTitle))
												.collect(Collectors.groupingBy(Book::getTitle, TreeMap::new, Collectors.mapping(Book::autoriList, Collectors.toSet())));
		TreeMap<String, ArrayList<String>> result = new TreeMap<>();
		result.putAll(map);
		return result;
	}


	/**
	 * Retrieves total loans for users (including closed ones)
	 * 
	 * @return map user -> loan number
	 */
	public TreeMap<String, Integer> numberOfTotalLoansByUser() {
		Map<String, Long> map = loans.values().stream()
												.sorted(Comparator.comparing((Loan l) -> l.getUser().getName()))
												.collect(Collectors.groupingBy((Loan l) -> l.getUser().getName(), Collectors.counting()));
		Map<String, Integer> newMap = map.entrySet().stream()
										.collect(Collectors.toMap(Map.Entry::getKey, e -> (Integer)e.getValue().intValue()));
		TreeMap<String, Integer> result = new TreeMap<>();
		result.putAll(newMap);
		return result;
	}

	//R4  queries

	/**
	 * returns the list of loans whose due date is equal to the current date.
	 * 
	 * @return list of loan indexes
	 */
	public List<Integer> dailyOverdue(){
		return null;
	}

	/**
	 * returns the average delay of loan returns for given user
	 * @param userName
	 * @return
	 */
	public double averageDelay(String userName) {
		return -1.0;
	}

	/**
	 * returns the number of volumes available for the book with the given title
	 * @param title
	 * @return number of available volumes
	 */
	public long availableVolumes(String title) {
		return -1;
	}


}
