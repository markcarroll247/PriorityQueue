import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSpinner;

public class myGUI extends JFrame {


	// launches the GUI
	public static void main(String[] args) { 
		myGUI frame = new myGUI();
		frame.setVisible(true);
	}
	
	
	IDatabase idb = new Database(); // Dependency Inversion here, can swap out the database now for another database
	
	IList iList = new DoubleLinkedList(); // Dependency Inversion here, can swap out the dll now for another type of list

	
	// temporary value to store patients who have NOT been assigned a priority (processed by admin).
	int tempPatientCount = 0;  
	

	
	// all the labels and text fields
	private JLabel firstname_Nurse;
	private JPanel contentPane;
	private JTextField firstnametextField;
	private JTextField lastnameTextField;
	private JTextField vitalSignstextField;
	private JLabel Index_Nurse;
	private JLabel lastname_Nurse;
	private JButton Next_Nurse;
	private JButton Save_Nurse;
	private JLabel firstname_Doctor;
	private JLabel lastname_Doctor;
	private JLabel Condition_Docotor;
	private JButton Next_Doctor;
	private JButton Save_Doctor;
	private JTextField addressTextField;
	private JTextField ppsTextField;
	private JTextField conditionTextField;
	private JLabel pps_Nurse;
	private JLabel address_Nurse;
	private JLabel conditionDescription_Nurse;
	private JLabel vitalSignsLabel;
	private JLabel lblTreatmentDescription;
	private JTextField treatmentDescription_Doctor;
	private JLabel saved_Doctor;
	private JLabel lblPps;
	private JLabel lblFirstname_1;
	private JLabel lblLastname_1;
	private JLabel lblAddress;
	private JLabel lblConditionDescription_1;
	private JLabel lblIndexInList;
	private JLabel lblPressnextTo;
	private JLabel lblRegisterPatientDetails;
	private JLabel lblPressnextTo_1;
	private JLabel lblFirstname_2;
	private JLabel lblLastname_2;
	private JLabel lblCondition;
	private JLabel saved_Nurse;
	private JSpinner spinner;
	

	/**
	 * Create the frame.
	 */
	public myGUI() {    
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel receptionTab = new JPanel();
		tabbedPane.addTab("Reception", null, receptionTab, null);
		receptionTab.setLayout(null);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(145, 107, 73, 16);
		receptionTab.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setBounds(145, 142, 63, 16);
		receptionTab.add(lblLastname);
		
		firstnametextField = new JTextField();
		firstnametextField.setBounds(236, 104, 116, 22);
		receptionTab.add(firstnametextField);
		firstnametextField.setColumns(10);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(236, 139, 116, 22);
		receptionTab.add(lastnameTextField);
		lastnameTextField.setColumns(10);
		
		JLabel Address = new JLabel("Address:");
		Address.setBounds(151, 177, 73, 16);
		receptionTab.add(Address);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(236, 174, 116, 22);
		receptionTab.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblpps = new JLabel("PPS:");
		lblpps.setBounds(180, 72, 34, 16);
		receptionTab.add(lblpps);
		
		ppsTextField = new JTextField();
		ppsTextField.setBounds(236, 69, 116, 22);
		receptionTab.add(ppsTextField);
		ppsTextField.setColumns(10);
		
		JLabel lblConditionDescription = new JLabel("Condition description: ");
		lblConditionDescription.setBounds(82, 212, 136, 16);
		receptionTab.add(lblConditionDescription);
		
		conditionTextField = new JTextField();
		conditionTextField.setBounds(236, 209, 116, 22);
		receptionTab.add(conditionTextField);
		conditionTextField.setColumns(10);
        
		JButton btnSubmit = new JButton("Submit"); // admin presses the submit button
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        Patient patient = new Patient(ppsTextField.getText().toString(),  // pps is the primary key
		        							 	firstnametextField.getText().toString(), 
		        							 	lastnameTextField.getText().toString(),
		        								addressTextField.getText().toString(),
		        								conditionTextField.getText().toString(),
		        								null, // vital signs will be added by the nurse
		        								null ); // treatment description will be added by the doctor
		        

		        iList.add(patient, 0); // saved to the list, Nurse will update the priority later
		        
		        
		        idb.createPatientsDB(); //establish the connection with the database and creates a Patients table if one doesn't exist already
		        idb.savePatient(patient); // saves patient to the database
		        
		        // adds one to the tempPatientCount, a patient has been processed by admin but not assigned a priority yet
		        tempPatientCount++;
		        
		        // resets all the text fields to blank
		        ppsTextField.setText("");
		        firstnametextField.setText("");  
		        lastnameTextField.setText(""); 
		        addressTextField.setText("");
		        conditionTextField.setText("");
		        
			}
		});
		

		btnSubmit.setBounds(180, 292, 97, 25);
		receptionTab.add(btnSubmit);
		
		lblRegisterPatientDetails = new JLabel("Register patient details");
		lblRegisterPatientDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegisterPatientDetails.setBounds(124, 13, 249, 30);
		receptionTab.add(lblRegisterPatientDetails);
		
		
		
		JPanel nurseTab = new JPanel();
		tabbedPane.addTab("Nurse", null, nurseTab, null);
		nurseTab.setLayout(null);
		
		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setBounds(132, 214, 50, 16);
		nurseTab.add(lblPriority);
		
		vitalSignstextField = new JTextField();
		vitalSignstextField.setBounds(194, 246, 116, 22);
		nurseTab.add(vitalSignstextField);
		vitalSignstextField.setColumns(10);
		
		Save_Nurse = new JButton("Save"); // nurse's save button
		Save_Nurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// sets the priority of each patient as assigned by the nurse
				// Index_Nurse is used as the position in the double linked list

				// sets the priority of the Node from the spinner
				iList.getNode(Integer.valueOf(Index_Nurse.getText().toString())).setPriority(Integer.valueOf((Integer) spinner.getValue()));

				// finds the patient associated with the Node
				Patient patient=(Patient) iList.getNode(Integer.valueOf(Index_Nurse.getText().toString())).getData(); 
				
				// sets the vital signs of the patient
				patient.setVitalSigns(vitalSignstextField.getText().toString());
				idb.updatePatientNurse(patient);  // update the patient's vital sign in the database
				
				// Nurse has assigned a priority and so reduce tempPatientCount.
				tempPatientCount--;
				
				// resets the text fields				
				pps_Nurse.setText("");				             
	            firstname_Nurse.setText("");
	            lastname_Nurse.setText("");
	            address_Nurse.setText("");
	            conditionDescription_Nurse.setText("");
	            Index_Nurse.setText("");
				spinner.setValue(1); // 1 is the minimum priority value
				vitalSignstextField.setText("");
				
				// displays a message to the user to assure them that action has been taken
				saved_Nurse.setText(patient.getFirstName() + " " +  patient.getLastName() + "'s file updated.");

				
				// can't press button without entering data about the current patient
	            Next_Nurse.setEnabled(true); 
	            Save_Nurse.setEnabled(false);
		
				
			}
		});
		Save_Nurse.setBounds(249, 305, 97, 25);
		nurseTab.add(Save_Nurse);
		
		Save_Nurse.setEnabled(false); 		// set as false to make it clear to the user which button to press
		
		firstname_Nurse = new JLabel("");
		firstname_Nurse.setBounds(194, 67, 260, 16);
		nurseTab.add(firstname_Nurse);
		
		lastname_Nurse = new JLabel("");
		lastname_Nurse.setBounds(194, 96, 260, 16);
		nurseTab.add(lastname_Nurse);
		
		Index_Nurse = new JLabel("");
		Index_Nurse.setBounds(194, 185, 56, 16);
		nurseTab.add(Index_Nurse);
		
		
		
		Next_Nurse = new JButton("Next");  // nurse's next button
		Next_Nurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saved_Nurse.setText(""); // sets the "saved message" the user sees to blank
				

		        for (int i = 1; i <= iList.size(); i++) { // iterates through each node 
		            if (iList.getNode(i).getPriority() == 0) {
		            	Patient patient=(Patient) iList.getNode(i).getData(); 
			            pps_Nurse.setText(patient.getPps());				             
			            firstname_Nurse.setText(patient.getFirstName());
			            lastname_Nurse.setText(patient.getLastName());
			            address_Nurse.setText(patient.getAddress());
			            conditionDescription_Nurse.setText(patient.getConditionDescription());
			            Index_Nurse.setText(String.valueOf(i)); // shows the index of patient in the double linked list - used to find associated Node/patient
	
						 // can't press button without entering data about the current patient
			            Next_Nurse.setEnabled(false); 
			            Save_Nurse.setEnabled(true);
			            break;
		            	}
				       
				}
			}
		});
		Next_Nurse.setBounds(103, 305, 97, 25);
		nurseTab.add(Next_Nurse);
		
		pps_Nurse = new JLabel("");
		pps_Nurse.setBounds(194, 40, 260, 16);
		nurseTab.add(pps_Nurse);
		
		address_Nurse = new JLabel("");
		address_Nurse.setBounds(194, 127, 260, 16);
		nurseTab.add(address_Nurse);
		
		conditionDescription_Nurse = new JLabel("");
		conditionDescription_Nurse.setBounds(194, 156, 260, 16);
		nurseTab.add(conditionDescription_Nurse);
		
		vitalSignsLabel = new JLabel("Vital Signs:");
		vitalSignsLabel.setBounds(113, 249, 69, 16);
		nurseTab.add(vitalSignsLabel);
		
		lblPps = new JLabel("PPS:");
		lblPps.setBounds(149, 40, 33, 16);
		nurseTab.add(lblPps);
		
		lblFirstname_1 = new JLabel("Firstname:");
		lblFirstname_1.setBounds(113, 67, 69, 16);
		nurseTab.add(lblFirstname_1);
		
		lblLastname_1 = new JLabel("Lastname:");
		lblLastname_1.setBounds(115, 96, 67, 16);
		nurseTab.add(lblLastname_1);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(126, 127, 56, 16);
		nurseTab.add(lblAddress);
		
		lblConditionDescription_1 = new JLabel("Condition Description:");
		lblConditionDescription_1.setBounds(49, 156, 133, 16);
		nurseTab.add(lblConditionDescription_1);
		
		lblIndexInList = new JLabel("Index in list:");
		lblIndexInList.setBounds(106, 185, 76, 16);
		nurseTab.add(lblIndexInList);
		
		lblPressnextTo = new JLabel("Press \"Next\" to see the next patient");
		lblPressnextTo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPressnextTo.setBounds(82, 13, 357, 22);
		nurseTab.add(lblPressnextTo);
		
		saved_Nurse = new JLabel("");
		saved_Nurse.setBounds(108, 281, 262, 16);
		nurseTab.add(saved_Nurse);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1); //default value,lower bound,upper bound,increment by
		spinner = new JSpinner(sm);
		spinner.setToolTipText("");
		spinner.setBounds(194, 211, 48, 22);
		nurseTab.add(spinner);
		
		
		JPanel doctorTab = new JPanel();
		tabbedPane.addTab("Doctor", null, doctorTab, null);
		doctorTab.setLayout(null);
		
		firstname_Doctor = new JLabel("firstname");
		firstname_Doctor.setBounds(222, 70, 265, 16);
		doctorTab.add(firstname_Doctor);
		
		lastname_Doctor = new JLabel("lastname");
		lastname_Doctor.setBounds(221, 99, 266, 16);
		doctorTab.add(lastname_Doctor);
		
		Condition_Docotor = new JLabel("Condition");
		Condition_Docotor.setBounds(222, 128, 267, 16);
		doctorTab.add(Condition_Docotor);
		

		
		Next_Doctor = new JButton("Next");
		Next_Doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saved_Doctor.setText("");  // sets the "saved message" the user sees to blank
				// if there are no more patients in the list, set all fields to blank and display "no patients" message
				if(iList.size() <= 0){ 
					firstname_Doctor.setText("no patients in queue, press Next after a while");
					lastname_Doctor.setText("");
					Condition_Docotor.setText("");
					
					// checks if there is a patient in the double linked list (added by admin) but not yet assigned a priority by the Nurse. Otherwise, an error is thrown by dll.getHighestPriorityPosition() below as there is a patient but it doesn't have a priority yet. 
				}else if(tempPatientCount > 0){   
					firstname_Doctor.setText("no patients in queue, press Next after a while");
					lastname_Doctor.setText("");
					Condition_Docotor.setText("");
				}else{
					// gets the next highest priority patient
					Patient patient = (Patient) iList.getNode(iList.getHighestPriorityPosition()).getData();
					// displays the information about the highest priority patient
					firstname_Doctor.setText(patient.getFirstName());
					lastname_Doctor.setText(patient.getLastName());
					Condition_Docotor.setText(patient.getConditionDescription());					
					
					// can't press button without entering data about the current patient
					Next_Doctor.setEnabled(false);	
					Save_Doctor.setEnabled(true);
				}
			}
		});
		Next_Doctor.setBounds(95, 258, 97, 25);
		doctorTab.add(Next_Doctor);
		
		
		Save_Doctor = new JButton("Save");  // doctor save button
		Save_Doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// gets the next highest priority patient
				Patient patient = (Patient) iList.getNode(iList.getHighestPriorityPosition()).getData();
				patient.setTreatmentDescription(treatmentDescription_Doctor.getText().toString());
				idb.updatePatientDoctor(patient);

				// removes the highest priority patient from the double linked list as they are finished with the doctor
				iList.remove(iList.getHighestPriorityPosition()); 
					 
				// reset fields to blank
				firstname_Doctor.setText("");
				lastname_Doctor.setText("");
				Condition_Docotor.setText("");	
				treatmentDescription_Doctor.setText("");

				// displays a message to the user to assure them that action has been taken
				saved_Doctor.setText(patient.getFirstName() + " " +  patient.getLastName() + "'s file updated.");
					 
				// can't press button without entering data about the current patient
				Next_Doctor.setEnabled(true);	
				Save_Doctor.setEnabled(false);	
			}
		});
		Save_Doctor.setBounds(222, 258, 97, 25);
		doctorTab.add(Save_Doctor);
		
		Save_Doctor.setEnabled(false); // set as false to make it clear to the user which button to press
		
		lblTreatmentDescription = new JLabel("Treatment Description");
		lblTreatmentDescription.setBounds(76, 160, 134, 16);
		doctorTab.add(lblTreatmentDescription);
		
		treatmentDescription_Doctor = new JTextField();
		treatmentDescription_Doctor.setBounds(222, 157, 116, 22);
		doctorTab.add(treatmentDescription_Doctor);
		treatmentDescription_Doctor.setColumns(10);
		
		saved_Doctor = new JLabel("");
		saved_Doctor.setBounds(130, 230, 269, 16);
		doctorTab.add(saved_Doctor);
		
		lblPressnextTo_1 = new JLabel("Press \"Next\" to see the patient with highest priority");
		lblPressnextTo_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPressnextTo_1.setBounds(12, 13, 475, 25);
		doctorTab.add(lblPressnextTo_1);
		
		lblFirstname_2 = new JLabel("Firstname:");
		lblFirstname_2.setBounds(130, 70, 62, 16);
		doctorTab.add(lblFirstname_2);
		
		lblLastname_2 = new JLabel("Lastname:");
		lblLastname_2.setBounds(130, 99, 62, 16);
		doctorTab.add(lblLastname_2);
		
		lblCondition = new JLabel("Condition:");
		lblCondition.setBounds(130, 128, 62, 16);
		doctorTab.add(lblCondition);
	}
}
