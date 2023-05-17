package business;

import tools.FileHandle;

public class HospitalManagement {
    NurseList nList = new NurseList() ;
    PatientList pList = new PatientList() ;
    private final String nursePath = "files/nurses.dat" ;
    private final String patientPath = "files/patients.dat" ;

    public void addNewNurse(){nList.addNewNurse();}
    public void findNurse(){nList.findNurse();}
    public void updateNurse(){nList.updateNurse();}
    public void deleteNurse(){nList.deleteNurse(pList);}
    public void addPatient(){pList.addPatient(nList);}
    public void displayPatient(){pList.displayPatient();}
    public void sortPatient(){pList.sortPatient();}

    public void saveData(){
       
    }

    public void loadData(){
        
    }
}
