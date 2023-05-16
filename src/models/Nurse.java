/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Nurse extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private String deparmnet ;
    private String shift ;
    private double salary ;

    public Nurse() {
    }

    public Nurse(String id, String name, int age, String gender, String address, String phone,String deparmnet, String shift, double salary) {
        super(id, name, age, gender, address, phone);
        this.deparmnet = deparmnet;
        this.shift = shift;
        this.salary = salary;
    }

    public String getDeparmnet() {
        return deparmnet;
    }

    public void setDeparmnet(String deparmnet) {
        this.deparmnet = deparmnet;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public void show() {
    }    
}
