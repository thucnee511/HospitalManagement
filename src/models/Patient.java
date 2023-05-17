/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Patient extends Person implements Serializable {

    private String diagnosis;
    private Date addmissionDate;
    private Date dischargeDate;
    private Nurse nurseAssigned;

    public Patient() {
    }

    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis,
            Date addmissionDate, Date dischargeDate, Nurse nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.addmissionDate = addmissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAddmissionDate() {
        return addmissionDate;
    }

    public void setAddmissionDate(Date addmissionDate) {
        this.addmissionDate = addmissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Nurse getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(Nurse nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }

    @Override
    public void show() {
    }

    @Override
    public String toString() {
        String str = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s,%s",
                id, name, age, gender, address, phone, diagnosis, addmissionDate.toString(), dischargeDate.toString(),
                nurseAssigned.getId());
        return str;
    }
}
