package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Nurse;
import models.Patient;
import tools.InputHandle;
import view.Menu;

public class PatientList extends HashMap<String, Patient> {

    public void addPatient(NurseList nList) {
        String id = "";
        while (true) {
            id = InputHandle.getString("Enter id: ", "[P****], * as a number", "P\\d{4}");
            if (this.containsKey(id)) {
                System.out.println("This staff already exist!");
            } else {
                break;
            }
        }
        String name = InputHandle.getString("Enter patient's name: ", "This field cannot be empty!");
        int age = InputHandle.getPositiveInt("Enter patient's age: ", "Must be a integer number!");
        String gender = InputHandle.getString("Enter patient's gender: ", "Male or Female", "(Male)|(Female)");
        String address = InputHandle.getString("Enter patient's address: ", "This field cannot be empty!");
        String phone = InputHandle.getString("Enter patient's phone: ", "0|(+84)*********", "^((0)|(\\+84))\\d{9}");
        String diagnosis = InputHandle.getString("Enter patient's diagnosis: ", "This field cannot be empty!");
        Date admissionDate = InputHandle.getDate("Enter admission date (dd/MM/yyyy): ");
        Date dischargDate = InputHandle.getDate("Enter discharge date (dd/MM/yyyy): ");
        HashMap<String, Nurse> nl = new HashMap<>();
        int count = 0;
        while (true) {
            Nurse nurse = null;
            String nId = InputHandle.getString("Enter nurse assigned's id: ", "[N****], * as a number", "N\\d{4}");
            if ((nurse = nList.find(nId)) != null) 
                if (nurse.getNumPatientAssign() < 2) {
                    nl.put(nId, nurse);
                    nurse.incNumPatientAssign(); 
                    count++;
                }else{
                    System.out.println("This nurse already assigned 2 patients!");
                }
            } else {
                System.out.println("This nurse does not exist");
            }
            if (count == 2 ) {
                break;
            }
        }
        this.put(id, new Patient(id, name, age, gender, address, phone, diagnosis, admissionDate, dischargDate, nl));
    }
    public void displayPatient() {
        System.out.println("LIST OF PATIENT");
        Date startDate = InputHandle.getDate("Start date: ");
        Date endDate = InputHandle.getDate("End date: ");
        String title = "No.|Patient Id|Admission Date|Full Name       |       Phone|Diagnosis";
        System.out.println(title);
        List<Patient> displayingList = new ArrayList<>();
        List<Map.Entry<String, Patient>> _pList = new ArrayList<>(this.entrySet());
        Collections.sort(_pList, (Map.Entry<String, Patient> o1, Map.Entry<String, Patient> o2) -> (o1.getValue().getAddmissionDate().compareTo(o2.getValue().getAddmissionDate())));
        for (Map.Entry<String, Patient> item : _pList) {
            Date eleDate = item.getValue().getAddmissionDate();
            if (isInDateRange(startDate, endDate, eleDate)) 
                displayingList.add(item.getValue());
            }
        }
        print(displayingList);
    }
    public boolean isInDateRange(Date sDate , Date eDate , Date pDate){
        boolean isBeforeEnd = pDate.equals(eDate) || pDate.before(eDate);
        boolean isAfterStart = pDate.equals(sDate) || pDate.after(sDate);
        return isBeforeEnd && isAfterStart ;
    }
    public void sortPatient() {
        System.out.println("LIST OF PATIENT");
        Menu sortingBy = new Menu("Sort by:");
        sortingBy.addOption("Patient Id");
        sortingBy.addOption("Name");
        Menu sortingOrder = new Menu("Sort order:");
        sortingOrder.addOption("ASC");
        sortingOrder.addOption("DESC");
        sortingBy.printMenu();
        int sBy = sortingBy.getChoice();
        sortingOrder.printMenu();
        boolean sOr = sortingOrder.getChoice() == 1;
        System.out.println("Sort by: " + sortingBy.getChoice(sBy - 1));
        System.out.println("Sort order: " + sortingOrder.getChoice(sOr ? 0 : 1));
        List<Patient> displayList = new ArrayList<>(this.values());
        switch (sBy) {
            case 1: {
                Comparator<Patient> cmp = (o1, o2) -> o1.getId().compareTo(o2.getId()) ;
                displayList = sort(displayList, cmp, sOr);
                print(displayList);
                break ;
            }
            case 2: {
                Comparator<Patient> cmp =(o1, o2) -> o1.getName().compareTo(o2.getName()) ;
                displayList = sort(displayList, cmp, sOr);
                print(displayList);
                break ;
            }
        }
    }
    
    private List<Patient> sort(List<Patient> pList , Comparator<Patient> cmp , boolean asc){
        for(int i = 0 ; i < pList.size()-1 ; i++){
            for(int j = i+1 ; j < pList.size() ; j++){
                boolean swap;
                if(asc){
                    swap = cmp.compare(pList.get(i), pList.get(j)) > 0 ;
                }else{
                    swap = cmp.compare(pList.get(i), pList.get(j)) < 0 ;
                }
                if(swap){
                    Collections.swap(pList , i , j) ;
                }
            }
        }
        return pList ;
    }
    
    private void print(List<Patient> displayList){
        String title = "No.|Patient Id|Admission Date|Full Name       |       Phone|Diagnosis";
        System.out.println(title);
        int count = 1;
        for (Patient item : displayList) {
            String msg = String.format("%-3d|", count);
            count++;
            System.out.print(msg);
            item.show();
        }
    }
}
