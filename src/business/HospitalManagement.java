package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import models.Nurse;
import models.Patient;

public class HospitalManagement {

    NurseList nList = new NurseList();
    PatientList pList = new PatientList();
    private final String nursePath = new File("").getAbsolutePath() + "\\src\\files\\nurses.dat";
    private final String patientPath = new File("").getAbsolutePath() + "\\src\\files\\patients.dat";

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
        System.out.println(nursePath + "\n" + patientPath + "\n");
        try {
            FileOutputStream nFos = new FileOutputStream(nursePath);
            FileOutputStream pFos = new FileOutputStream(patientPath);
            ObjectOutputStream nOos = new ObjectOutputStream(nFos);
            ObjectOutputStream pOos = new ObjectOutputStream(pFos);
//            for(Entry<String,Nurse> item : nList.entrySet()){
//                nOos.writeObject(item.getValue());
//            }
//            for(Entry<String,Patient> item : pList.entrySet()){
//                pOos.writeObject(item.getValue());
//            }
            nOos.writeObject(nList);
            pOos.writeObject(pList);
            nOos.close();
            pOos.close();
            nFos.close();
            pFos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        nList.clear();
        pList.clear();
        try {
            FileInputStream nFis = new FileInputStream(nursePath);
            FileInputStream pFis = new FileInputStream(patientPath);
            ObjectInputStream nOis = new ObjectInputStream(nFis);
            ObjectInputStream pOis = new ObjectInputStream(pFis);
//            Patient p;
//            Nurse n;
//            while ((p = (Patient) pOis.readObject()) != null) {
//                pList.put(p.getId(), p);
//            }
//            while ((n = (Nurse) pOis.readObject()) != null) {
//                nList.put(n.getId(), n);
//            }
            nList = (NurseList) nOis.readObject();
            pList = (PatientList) pOis.readObject() ;
            nOis.close();
            pOis.close();
            nFis.close();
            pFis.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
