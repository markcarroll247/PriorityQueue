
public class Patient {
	private String pps;
	private String firstName;
	private String lastName;
	private String address;
	private String conditionDescription;
	private String vitalSigns;
	private String treatmentDescription;

    
    public Patient(String pps, String firstName, String lastName, String address, String conditionDescription, String vitalSigns, String treatmentDescription ){
    	this.pps = pps;
    	this.firstName = firstName;
    	this.lastName = lastName;  
    	this.address = address;
    	this.conditionDescription = conditionDescription;
    	this.vitalSigns = vitalSigns;
    	this.treatmentDescription = treatmentDescription;
    }


	public String getPps() {
		return pps;
	}


	public void setPps(String pps) {
		this.pps = pps;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getConditionDescription() {
		return conditionDescription;
	}


	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}


	public String getVitalSigns() {
		return vitalSigns;
	}


	public void setVitalSigns(String vitalSigns) {
		this.vitalSigns = vitalSigns;
	}


	public String getTreatmentDescription() {
		return treatmentDescription;
	}


	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}   

}
