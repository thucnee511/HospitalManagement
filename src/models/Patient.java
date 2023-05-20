/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public String formatPrintDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
        String ret = sdf.format(date) ;
        return ret;
    }
    
    public static Date toDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;
        try{
            Date ret = sdf.parse(date) ;
            return ret ;
        }catch(Exception e){}
        return null ;
    }
    
    @Override
    public void show() {
        String str = String.format("%10s|%14s|%16s|%-12s|%-15s", 
                id, formatPrintDate(addmissionDate), name, phone, diagnosis);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s,%s",
                id, name, age, gender, address, phone, diagnosis, formatPrintDate(addmissionDate), formatPrintDate(dischargeDate),
                nurseAssigned.getId());
        return str;
    }
}
