/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class FileHandle {

    private final static String SYSPATH = new File("").getAbsolutePath();

    private static String initPath(String path) {
        return SYSPATH + path;
    }

    public static ArrayList<String> readFromFile(String path) {
        String _path = initPath(path);
        File file = new File(_path);
        ArrayList<String> dta = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                dta.add(line.trim());
            }
            input.close();
        } catch (IOException e) {
        }
        return dta;
    }

    public static boolean writeToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        File file = new File(_path);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (String line : dta) {
                output.write(line);
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean writeBinaryStringToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        File file = new File(_path);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (String line : dta) {
                output.write(toBinaryString(line));
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> readBinaryStringFromFile(String filePath) {
        String _path = initPath(filePath);
        File file = new File(_path);
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                data.add(toOriginString(line.trim()));
            }
            input.close();
        } catch (IOException e) {
        }
        return data;
    }

    private static String toBinaryString(String str) {
        byte arr[] = str.getBytes();
        StringBuilder ret = new StringBuilder();
        for (byte b : arr) {
            String bStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            ret.append(bStr);
        }
        return ret.toString();
    }

    private static String toOriginString(String bStr) {
        ArrayList<Byte> arr = new ArrayList<>();
        for (int i = 0; i < bStr.length(); i += 8) {
            String chr = bStr.substring(i, i + 8);
            byte b = Byte.parseByte(chr, 2);
            arr.add(b);
        }
        byte[] ret = new byte[arr.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr.get(i);
        }
        return new String(ret);
    }

    public static <T> void writeObjectToFile(String path, ArrayList<T> dta) {
        String _path = initPath(path);
        try {
            FileOutputStream fos = new FileOutputStream(_path);
            ObjectOutputStream oos = new ObjectOutputStream(fos) ;
            oos.writeObject(dta);
            oos.close() ;
            fos.close() ;
        }catch(IOException e){
        }
    }
    
    public static <T> ArrayList<T> readObjectFromFile(String path , T any){
        String _path = initPath(path) ;
        ArrayList<T> ret = null;
        try{
            FileInputStream fis = new FileInputStream(_path) ;
            ObjectInputStream ois = new ObjectInputStream(fis) ;
            ret = (ArrayList<T>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){           
        }
        return ret ;
    }
}
