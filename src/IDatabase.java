
public interface IDatabase {

	public void createPatientsDB();
	public void savePatient(Patient p);
	public void updatePatientNurse(Patient p);
	public void updatePatientDoctor(Patient p);
}
