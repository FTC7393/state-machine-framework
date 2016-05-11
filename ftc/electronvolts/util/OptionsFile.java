package ftc.electronvolts.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vandejd1 on 2/6/16.
 * FTC Team EV 7393
 */
public class OptionsFile {
    Map<String, String> values;
    public OptionsFile(){
        values = new HashMap<>();
    }
    public OptionsFile(Map<String, String> values) {
        this.values = values;
    }

    public static OptionsFile fromFile(String filename){
        try {
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Map<String, String> newvalues = new HashMap<>();
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    //name:type:value
                    //types: Double, Integer, Boolean, String
//                    String[] elements = line.split(":");
//                    String key = elements[0].trim();
//                    String type = elements[1].trim();
//                    String value = elements[2].trim();

                    try {
                        String[] elements = line.split("=");
                        String key = elements[0].trim();
                        String value = elements[1].trim();
                        newvalues.put(key, value);
                    } catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new OptionsFile(newvalues);
        } catch (FileNotFoundException e1) {
            return new OptionsFile();
        }
    }

    public static OptionsFile fromFile(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Map<String, String> newvalues = new HashMap<>();
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    //name:type:value
                    //types: Double, Integer, Boolean, String
//                    String[] elements = line.split(":");
//                    String key = elements[0].trim();
//                    String type = elements[1].trim();
//                    String value = elements[2].trim();

                    try {
                        String[] elements = line.split("=");
                        String key = elements[0].trim();
                        String value = elements[1].trim();
                        newvalues.put(key, value);
                    } catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new OptionsFile(newvalues);
        } catch (FileNotFoundException e1) {
            return new OptionsFile();
        }
    }

    public Map<String, String> getValues(){
        return values;
    }

    public void add(String tag, String value){
        values.put(tag, value);
    }

    public String getAsString(String tag){
        return values.get(tag);
    }

    public Integer getAsInteger(String tag, Integer fallback){
        try {
            return Integer.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public Double getAsDouble(String tag, Double fallback){
        try {
            return Double.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }


    public Boolean getAsBoolean(String tag, Boolean fallback){
        try {
            return Boolean.valueOf(values.get(tag));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public boolean writeToFile(String filename){
        try {
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(values.toString().replaceAll("\\{|\\}", "").replaceAll(", ", "\n"));
//            System.out.println(values.toString().replaceAll("\\{|\\}", "").replaceAll(", ", "\n"));

//            Iterator it = values.entrySet().iterator();
//            while (it.hasNext()){
//                Map.Entry pair = (Map.Entry) it.next();
//                pw.println(pair.getKey() + "=" + pair.getValue());
//                it.remove();
//            }
            pw.close();
            return true;
        } catch (FileNotFoundException e1) {
            //throw new RuntimeException("Error opening file: " + filename, e1);
            e1.printStackTrace();
            return false;
        }
    }
}
