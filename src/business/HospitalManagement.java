package business;

import java.util.ArrayList;
import java.util.Map.Entry;
import models.Nurse;
import models.Patient;
import tools.FileHandle;

public class HospitalManagement {

    NurseList nList = new NurseList();
    PatientList pList = new PatientList();
    private final String nursePath = "\\src\\files\\nurses.dat";
    private final String patientPath = "\\src\\files\\patients.dat";

    public void addNewNurse() {
        nList.addNewNurse();
    }

    public void findNurse() {
        nList.findNurse();
    }

    public void updateNurse() {
        nList.updateNurse();
    }

    public void deleteNurse() {
        nList.deleteNurse(pList);
    }

    public void addPatient() {
        pList.addPatient(nList);
    }

    public void displayPatient() {
        pList.displayPatient();
    }

    public void sortPatient() {
        pList.sortPatient();
    }

    public void saveData() {
        ArrayList<String> dta = new ArrayList<>();
        for (Entry<String, Nurse> item : nList.entrySet()) {
            dta.add(item.getValue().toString());
        }
        FileHandle.writeToFile(nursePath, dta);
        dta.clear();
        for (Entry<String, Patient> item : pList.entrySet()) {
            dta.add(item.getValue().toString());
        }
        FileHandle.writeToFile(patientPath, dta);
        System.out.println("Data saved successfully!");
    }

    public void loadData() {
        nList.clear();
        pList.clear();
        ArrayList<String> dta = new ArrayList<>();
        dta.addAll(FileHandle.readFromFile(nursePath));
        dta.addAll(FileHandle.readFromFile(patientPath));
        for (String item : dta) {
            String lineSplit[] = item.trim().split(",");
            if (lineSplit[0].matches("N\\d{4}")) {
                nList.put(lineSplit[0],
                        new Nurse(lineSplit[0],
                                lineSplit[1],
                                Integer.parseInt(lineSplit[2]),
                                lineSplit[3],
                                lineSplit[4],
                                lineSplit[5],
                                lineSplit[6],
                                lineSplit[7],
                                Double.parseDouble(lineSplit[8])));
            } else if (lineSplit[0].matches("P\\d{4}")) {
                pList.put(lineSplit[0],
                        new Patient(lineSplit[0],
                                lineSplit[1],
                                Integer.parseInt(lineSplit[2]),
                                lineSplit[3],
                                lineSplit[4],
                                lineSplit[5],
                                lineSplit[6],
                                Patient.toDate(lineSplit[7]),
                                Patient.toDate(lineSplit[8]),
                                nList.get(lineSplit[9])));
            }
        }
    }
}
