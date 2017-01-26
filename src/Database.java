import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database implements IDatabase{
	Connection c = null;
	Statement stmt = null;
	
	public void createPatientsDB(){
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hospital3.sqlite");
			System.out.println("We are connected!");
			
			// creates a Patients table if one does not already exist
			stmt = c.createStatement();
			String createTable = "CREATE TABLE IF NOT EXISTS Patients" + 
					"(pps VARCHAR (80) PRIMARY KEY," + 
					"firstname VARCHAR (80) NOT NULL," +
					"lastname VARCHAR (80) NOT NULL, " + 
					"address VARCHAR (100) NOT NULL," +
					"conditionDescription VARCHAR (80) NOT NULL," +
					"vitalSigns VARCHAR (50)," +
					"treatmentDescription VARCHAR (80)" +
					")";
			stmt.executeUpdate(createTable);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void savePatient(Patient p){
		try{
			stmt = c.createStatement();
			String insertPatient = String.format("INSERT INTO Patients (pps, firstname, lastname, address, conditionDescription)"
					+ "VALUES('%s','%s','%s','%s','%s')",p.getPps(), p.getFirstName(), p.getLastName(), p.getAddress(), p.getConditionDescription());
			stmt.executeUpdate(insertPatient);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}	
	}
	
	public void updatePatientNurse(Patient p){
		try{
			stmt = c.createStatement();
			String insertPatient = String.format("UPDATE Patients SET vitalSigns = '%s' WHERE pps = '%s'", p.getVitalSigns(),p.getPps());
			stmt.executeUpdate(insertPatient);
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}	
	}
	
	public void updatePatientDoctor(Patient p){
		try{
			stmt = c.createStatement();
			String insertPatient = String.format("UPDATE Patients SET treatmentDescription = '%s' WHERE pps = '%s'",
					p.getTreatmentDescription(),p.getPps());
			stmt.executeUpdate(insertPatient);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
}
