package business;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import models.Nurse;
import models.Patient;
import tools.InputHandle;

public class PatientList extends HashMap<String, Patient>{
    public void addPatient(NurseList nList) {
        String id = "";
        while (true) {
            id = InputHandle.getString("Enter id: ", "[P****], * as a number", "P\\d{4}");
            if (this.containsKey(id)) {
                System.out.println("This staff already exist!");
                continue;
            } else
                break;
        }
        String name = InputHandle.getString("Enter patient's name: ", "This field cannot be empty!");
        int age = InputHandle.getPositiveInt("Enter patient's age: ", "Must be a integer number!");
        String gender = InputHandle.getString("Enter patient's gender: ", "Male or Female", "[(Male)|(Female)]");
        String address = InputHandle.getString("Enter patient's address: ", "This field cannot be empty!");
        String phone = InputHandle.getString("Enter patient's phone: ", "[0|(+84)]*********", "[9|(+84)]\\d{9}");
        String diagnosis = InputHandle.getString("Enter patient's diagnosis: ", "This field cannot be empty!");
        Date admissionDate = InputHandle.getDate("Enter admission date (yyyy/MM/dd): ");
        Date dischargDate = InputHandle.getDate("Enter discharge date (yyyy/MM/dd): ");
        Nurse nurse = null;
        while(true){
            String nId = InputHandle.getString("Enter nurse assigned's id: ", "[N****], * as a number", "N\\d{4}");
            if ((nurse = nList.find(nId)) != null) {
                break ;
            } else{
                System.out.println("This nurse does not exist");
            }
        }
        this.put(id, new Patient(id, name, age, gender, address, phone, diagnosis, admissionDate, dischargDate , nurse));
    }

    public void displayPatient(){

    }

    public void sortPatient(){

    }
}
