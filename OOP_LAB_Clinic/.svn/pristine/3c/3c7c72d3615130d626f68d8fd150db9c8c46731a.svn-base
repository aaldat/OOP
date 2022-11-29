package clinic;

import java.util.LinkedList;
import java.util.List;

public class Doctor extends Patient{

	private int docID;
	private String specialization;
	private List<Patient> patients = new LinkedList<>();
	
	
	public Doctor(String first, String last, String ssn, int docID, String specialization) {
		super(first, last, ssn);
		this.docID = docID;
		this.specialization = specialization;
	}
	
	public int getDocID() {
		return docID;
	}

	public String getSpecialization() {
		return specialization;
	}
	
	public void addPatient(Patient p) {
		patients.add(p);
	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + docID + "]: " + specialization;
	}

}
