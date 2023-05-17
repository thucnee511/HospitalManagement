package business;

import java.util.HashMap;
import models.Nurse;
import tools.InputHandle;

public class NurseList extends HashMap<String , Nurse>{
    public void addNewNurse(){
        String id = "" ;
        while(true){
            id = InputHandle.getString("Enter staff id: ", "[N****], * as a number", "N\\d{4}");
            if (this.containsKey(id)){
                System.out.println("This staff already exist!");
                continue ;
            }
            else break ;
        }
        String name = InputHandle.getString("Enter nurse's name: ", "This field cannot be empty!") ;
        int age = InputHandle.getPositiveInt("Enter nurse's age: ", "Must be a integer number!") ;
        String gender = InputHandle.getString("Enter nurse's gender: ", "Male or Female") ;
        String address = InputHandle.getString("Enter nurse's address: ", "This field cannot be empty!") ;
        String phone = InputHandle.getString("Enter nurse's phone: ", "[0|(+84)]*********" , "[9|(+84)]\\d{9}") ;
        String deparment = InputHandle.getString("Enter nusre's deparment: ", "Length of deparment must be in [3,50]", 3, 50);
        String shift = InputHandle.getString("Enter nurse's shift: ","This field cannot be empty!") ;
        double salary = InputHandle.getPositiveReal("Enter nurse's salary: ","Must be a real number!") ;
        this.put(id , new Nurse(id , name , age , gender , address , phone , deparment , shift , salary)) ;
    }

    public void findNurse(){
        String srch = InputHandle.getString("Enter nurse's name or part of the name: " , "Cannot be empty!") ;
        Nurse finded = null ;
        for(Entry<String , Nurse> item : this.entrySet()){
            String name = item.getValue().getName() ;
            if (srch.equals(name) || name.contains(srch)){
                finded = item.getValue() ;
                break ;
            }
        }
        if (finded != null){
            finded.show();
        }else{
            System.out.println("The nurse does not exist!");
        }
    }
}
