package business;

import java.util.HashMap;
import models.Nurse;
import models.Patient;
import tools.InputHandle;
import view.Menu;

public class NurseList extends HashMap<String, Nurse> {
    public void addNewNurse() {
        String id = "";
        while (true) {
            id = InputHandle.getString("Enter staff id: ", "[N****], * as a number", "N\\d{4}");
            if (this.containsKey(id)) {
                System.out.println("This staff already exist!");
                continue;
            } else
                break;
        }
        String name = InputHandle.getString("Enter nurse's name: ", "This field cannot be empty!");
        int age = InputHandle.getPositiveInt("Enter nurse's age: ", "Must be a integer number!");
        String gender = InputHandle.getString("Enter nurse's gender: ", "Male or Female", "[(Male)|(Female)]");
        String address = InputHandle.getString("Enter nurse's address: ", "This field cannot be empty!");
        String phone = InputHandle.getString("Enter nurse's phone: ", "[0|(+84)]*********", "[9|(+84)]\\d{9}");
        String deparment = InputHandle.getString("Enter nusre's deparment: ", "Length of deparment must be in [3,50]",
                3, 50);
        String shift = InputHandle.getString("Enter nurse's shift: ", "This field cannot be empty!");
        double salary = InputHandle.getPositiveReal("Enter nurse's salary: ", "Must be a real number!");
        this.put(id, new Nurse(id, name, age, gender, address, phone, deparment, shift, salary));
    }

    public void findNurse() {
        String srch = InputHandle.getString("Enter nurse's name or part of the name: ", "Cannot be empty!");
        Nurse finded = null;
        for (Entry<String, Nurse> item : this.entrySet()) {
            String name = item.getValue().getName();
            if (srch.equals(name) || name.contains(srch)) {
                finded = item.getValue();
                break;
            }
        }
        if (finded != null) {
            finded.show();
        } else {
            System.out.println("The nurse does not exist!");
        }
    }

    public Nurse find(String id) {
        if (this.containsKey(id))
            return this.get(id);
        else
            return null;
    }

    public void updateNurse() {
        String id = InputHandle.getString("Enter staff id: ", "[N****], * as a number", "N\\d{4}");
        Nurse n = find(id);
        if (n != null) {
            System.out.println("Nurse found:\n" + n.toString()
                    + "\n Updating nurse [Enter blank for any field does not need to be updated]:");
            String name = InputHandle.getString("Enter nurse's name: ");
            String age = InputHandle.getString("Enter nurse's age: ");
            String gender = InputHandle.getString("Enter nurse's gender: ");
            String address = InputHandle.getString("Enter nurse's address: ");
            String phone = InputHandle.getString("Enter nurse's phone: ");
            String deparment = InputHandle.getString("Enter nusre's deparment: ");
            String shift = InputHandle.getString("Enter nurse's shift: ");
            String salary = InputHandle.getString("Enter nurse's salary: ");

            // check valid data
            try {
                if (Integer.parseInt(age) <= 0)
                    throw new Exception("Age must be a positive integer number");
                if (!gender.isEmpty() && !gender.matches("[(Male)|(Female)]"))
                    throw new Exception("Gender must be Male or Female");
                if (!phone.isEmpty() && !phone.matches("[9|(+84)]\\d{9}"))
                    throw new Exception("Invalid phone number");
                if (!deparment.isEmpty() && (deparment.length() < 3 || deparment.length() > 50))
                    throw new Exception("Deparment length must be in [3,50]");
                if (Double.parseDouble(salary) < 0)
                    throw new Exception("Age must be a positive real number");
            } catch (Exception e) {
                System.out.println("Update failed! " + e.getMessage());
                return;
            } finally {
                if (!age.isEmpty())
                    n.setAge(Integer.parseInt(age));
                if (!name.isEmpty())
                    n.setName(name);
                if (!gender.isEmpty())
                    n.setGender(gender);
                if (!address.isEmpty())
                    n.setAddress(address);
                if (!phone.isEmpty())
                    n.setPhone(phone);
                if (!deparment.isEmpty())
                    n.setDeparment(deparment);
                if (!shift.isEmpty())
                    n.setShift(shift);
                if (!salary.isEmpty())
                    n.setSalary(Double.parseDouble(salary));
                System.out.println("Update successfully");
            }
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    public void deleteNurse(PatientList pList) {
        String id = InputHandle.getString("Enter staff id: ", "[N****], * as a number", "N\\d{4}");
        Nurse n = find(id);
        if (n != null) {
            System.out.println("Nurse found:\n" + n.toString());
            if (Menu.getYesOrNo("Do you want to delete this nurse?")) {
                try {
                    for (Entry<String, Patient> item : pList.entrySet()) {
                        if (item.getValue().getNurseAssigned() == n) throw new Exception("This nurse is having patient(s)");
                    }
                } catch (Exception e) {
                    System.out.println("Delete failed! "+e.getMessage());
                    return ;
                }finally{
                    this.remove(n.getId(), n) ;
                }
            }
        } else {
            System.out.println("The nurse does not exist");
        }
    }
}
