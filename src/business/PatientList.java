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

public class PatientList extends HashMap<String, Patient>{
    public void addPatient(NurseList nList) {
        String id = "";
        while (true) {
            id = InputHandle.getString("Enter id: ", "[P****], * as a number", "P\\d{4}");
            if (this.containsKey(id)) {
                System.out.println("This staff already exist!");
            } else
                break;
        }
        String name = InputHandle.getString("Enter patient's name: ", "This field cannot be empty!");
        int age = InputHandle.getPositiveInt("Enter patient's age: ", "Must be a integer number!");
        String gender = InputHandle.getString("Enter patient's gender: ", "Male or Female", "(Male)|(Female)");
        String address = InputHandle.getString("Enter patient's address: ", "This field cannot be empty!");
        String phone = InputHandle.getString("Enter patient's phone: ", "0|(+84)*********", "^((0)|(\\+84))\\d{9}");
        String diagnosis = InputHandle.getString("Enter patient's diagnosis: ", "This field cannot be empty!");
        Date admissionDate = InputHandle.getDate("Enter admission date (dd/MM/yyyy): ");
        Date dischargDate = InputHandle.getDate("Enter discharge date (dd/MM/yyyy): ");
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
        System.out.println("LIST OF PATIENT");
        Date startDate = InputHandle.getDate("Start date: ");
        Date endDate = InputHandle.getDate("End date: ");
        String title = "No.|Patient Id|Admission Date|Full Name       |       Phone|Diagnosis" ;
        System.out.println(title);
        HashMap<Integer,Patient> displayingList = new HashMap<>() ;
        List<Map.Entry<String,Patient>> _pList = new ArrayList<>(this.entrySet()) ;
        Collections.sort(_pList, (Map.Entry<String,Patient> o1,Map.Entry<String,Patient> o2) -> (o1.getValue().getAddmissionDate().compareTo(o2.getValue().getAddmissionDate())));
        int count = 1 ;
        for(Map.Entry<String,Patient> item : _pList){
            Date eleDate = item.getValue().getAddmissionDate() ;
            if (startDate.before(eleDate) && eleDate.before(endDate)){
                displayingList.put(count , item.getValue()) ;
                count++ ;
            }
        }
        for(Map.Entry<Integer,Patient> item : displayingList.entrySet()){
            String msg = String.format("%-3d|",item.getKey()) ;
            System.out.print(msg);
            item.getValue().show();
        }
    }

    public void sortPatient(){
        System.out.println("LIST OF PATIENT");
        Menu sortingBy = new Menu("Sort by:") ;
        sortingBy.addOption("Patient Id");
        sortingBy.addOption("Admission date");
        sortingBy.addOption("Name");
        sortingBy.addOption("Phone");
        sortingBy.addOption("Diagnosis");
        Menu sortingOrder = new Menu("Sort order:");
        sortingOrder.addOption("ASC");
        sortingOrder.addOption("DESC");
        sortingBy.printMenu();
        int sBy = sortingBy.getChoice() ;
        boolean sOr = sortingOrder.getChoice() == 1 ;
        List<Entry<String,Patient>> displayList = new ArrayList<>(this.entrySet()) ;
        switch(sBy){
            case 1:{
                if (sOr){
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> (o1.getValue().getId().compareTo(o2.getValue().getId()))) ;
                }else{
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> -(o1.getValue().getId().compareTo(o2.getValue().getId()))) ;
                }
            }
            case 2:{
                if (sOr){
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> (o1.getValue().getAddmissionDate().compareTo(o2.getValue().getAddmissionDate()))) ;
                }else{
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> -(o1.getValue().getAddmissionDate().compareTo(o2.getValue().getAddmissionDate()))) ;
                }
            }
            case 3:{
                if (sOr){
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> (o1.getValue().getName().compareTo(o2.getValue().getName()))) ;
                }else{
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> -(o1.getValue().getName().compareTo(o2.getValue().getName()))) ;
                }
            }
            case 4:{
                if (sOr){
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> (o1.getValue().getPhone().compareTo(o2.getValue().getPhone()))) ;
                }else{
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> -(o1.getValue().getPhone().compareTo(o2.getValue().getPhone()))) ;
                }
            }
            case 5:{
                if (sOr){
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> (o1.getValue().getDiagnosis().compareTo(o2.getValue().getDiagnosis()))) ;
                }else{
                    Collections.sort(displayList,
                            (Entry<String,Patient> o1 , Entry<String,Patient> o2) -> -(o1.getValue().getDiagnosis().compareTo(o2.getValue().getDiagnosis()))) ;
                }
            }
        }
        String title = "No.|Patient Id|Admission Date|Full Name       |       Phone|Diagnosis" ;
        System.out.println(title);
        int count = 1 ;
        for(Entry<String,Patient> item : displayList){
            String msg = String.format("%-3d|",count) ;
            count++ ;
            System.out.print(msg);
            item.getValue().show();
        }
    }
}
