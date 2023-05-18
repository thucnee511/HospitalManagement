/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runtime;

import business.HospitalManagement;
import view.Menu;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        HospitalManagement hm = new HospitalManagement();
        hm.loadData();
        Menu hmm = new Menu("Hospital Managemnt");
        hmm.addOption("Nurse Maangement");
        hmm.addOption("Patient Management");
        hmm.addOption("Quit");
        Menu pm = new Menu("Patient Management");
        pm.addOption("Add patient");
        pm.addOption("Display patient");
        pm.addOption("Sort patient");
        pm.addOption("Save data");
        pm.addOption("Load data");
        pm.addOption("Back to main menu");
        Menu nm = new Menu("Nurese Management");
        nm.addOption("Add nurse");
        nm.addOption("Find nurse");
        nm.addOption("Update nurse");
        nm.addOption("Delete nurse");
        nm.addOption("Back to main menu");
        boolean saved = true;
        while (true) {
            hmm.printMenu();
            int choice = hmm.getChoice();
            clear();
            switch (choice) {
                case 1: {
                    nm.printMenu();
                    int _choice = nm.getChoice();
                    clear();
                    switch (_choice) {
                        case 1: {
                            while(true){
                                hm.addNewNurse();
                                if (!Menu.getYesOrNo("Countinue to add nurse?")) break ;
                            }
                            saved = false ;
                            break ;
                        }
                        case 2: {
                            hm.findNurse();
                            break ;
                        }
                        case 3: {
                            hm.updateNurse();
                            saved = false ;
                            break ;
                        }
                        case 4: {
                            hm.deleteNurse();
                            saved = false ;
                            break ;
                        }
                        case 5: {
                            break;
                        }
                    }
                    break ;
                }
                case 2: {
                    pm.printMenu();
                    int _choice = pm.getChoice();
                    clear();
                    switch (_choice) {
                        case 1: {
                            while(true){
                                hm.addPatient();
                                if (!Menu.getYesOrNo("Countinue to add patient?")) break ;
                            }
                            saved = false ;
                            break ;
                        }
                        case 2: {
                            hm.displayPatient();
                            break ;
                        }
                        case 3: {
                            hm.sortPatient();
                            break ;
                        }
                        case 4: {
                            hm.saveData();
                            saved = true ;                           
                            break ;
                        }
                        case 5: {
                            hm.loadData();
                            break;
                        }
                        case 6:{
                            break ;
                        }
                    }
                    break ;
                }
                case 3: {
                    if (!saved) {
                        if (Menu.getYesOrNo("Do you want to save before quit?")) {
                            hm.saveData();
                        }
                    }
                    return;
                }
            }
        }
    }

    private static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Handle any exceptions
        }
    }
}
