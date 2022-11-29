package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {

	private Map<String, Patient> patients = new HashMap<>();
	private Map<Integer, Doctor> doctors = new HashMap<>();
	
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		Patient p = new Patient(first, last, ssn);
		patients.put(ssn, p);
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		return getPatientObject(ssn).toString();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Doctor d = new Doctor(first, last, ssn, docID, specialization);
		doctors.put(docID, d);
		patients.put(ssn, d);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		return getDoctorObject(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		Patient p = getPatientObject(ssn);
		Doctor d = getDoctorObject(docID);
		p.setDoctor(d);
		d.addPatient(p);
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		Patient p = getPatientObject(ssn);
		if(p.getDoctor() == null) throw new NoSuchDoctor();
		return p.getDoctor().getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		Doctor d = getDoctorObject(id);
		return d.getPatients().stream()
				.map(Patient::getSsn)
				.collect(Collectors.toList());
	}
	
	private Doctor getDoctorObject(int docID) throws NoSuchDoctor {
		Doctor d = doctors.get(docID);
		if(d == null) throw new NoSuchDoctor();
		return d;
	}
	
	private Patient getPatientObject(String ssn) throws NoSuchPatient {
		Patient p = patients.get(ssn);
		if(p == null) throw new NoSuchPatient ("Non e' stato trovato alcun paziente con il seguente codice fiscale: " + ssn);
		return p;
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		return loadData(reader, null);		
	}

	private final static Pattern re = Pattern.compile("(?:P|M *; *(?<id>[0-9]+)) *; *(?<first>[^;]+) *; *(?<last>[^;]+) *; *(?<ssn>[0-9a-zA-Z]+)(?: *; *(?<spec>[^;]+))?");

	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		try (BufferedReader r = new BufferedReader(reader)) {
			String line;
			int n = 0;
			while((line = r.readLine()) != null) {
				Matcher m = re.matcher(line);
				if(m.find()) {
					try {
						if(m.group("id") == null) {
							addPatient(m.group("first"), m.group("last"), m.group("ssn"));
						} else {
							addDoctor(m.group("first"), m.group("last"), m.group("ssn"), Integer.parseInt(m.group("id")), m.group("spec"));
						}
						n++;
					} catch (NumberFormatException e) {
						if(listener != null) listener.offending(line);
					}
				} else {
					if(listener != null) listener.offending(line);
				}
			}
			return n;
		}
	}

		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		return doctors.values().stream()
				.filter(d -> d.getPatients().isEmpty())
				.sorted(Comparator.comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
				.map(Doctor::getDocID)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		double avg = doctors.values().stream()
						.mapToInt(d -> d.getPatients().size())
						.average()
						.orElse(0.0);
		return doctors.values().stream()
				.filter(d -> d.getPatients().size() > avg)
				.map(Doctor::getDocID)
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		return doctors.values().stream()
				.sorted(Comparator.comparing((Doctor d) -> d.getPatients().size()).reversed())
				.map(d -> String.format("%3d: %d %s %s", d.getPatients().size(), d.getDocID(), d.getLast(), d.getFirst()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return patients.values().stream()
				.map(Patient::getDoctor)
				.filter(d -> d != null)
				.collect(Collectors.groupingBy(Doctor::getSpecialization, Collectors.counting()))
				.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry<String, Long>::getValue, Comparator.reverseOrder()).thenComparing(Map.Entry<String, Long>::getKey))
				.map(e -> String.format("%3d - %s", e.getValue(), e.getKey()))
				.collect(Collectors.toList());
	}
	
}
