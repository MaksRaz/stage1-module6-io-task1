package com.epam.mjc.io;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile result = new Profile();
        try(FileInputStream inputStream = new FileInputStream(file.getAbsolutePath())){
            int ch = 0;
            List<String> lines = new ArrayList<>();
            StringBuilder line = new StringBuilder();
               while((ch = inputStream.read()) != -1){
                   if ((char)ch == '\r' && (char)inputStream.read() == '\n'){
                        lines.add(line.toString());
                        line = new StringBuilder();
                    }
                   else{
                       line.append((char)ch);
                   }
            }
           result.setName(getFromLine(lines.get(0)));
            result.setAge(Integer.parseInt(getFromLine(lines.get(1))));
            result.setEmail(getFromLine(lines.get(2)));
            result.setPhone(Long.parseLong(getFromLine(lines.get(3))));
        }
        catch (Exception ex){
            return result;
        }
        return result;
    }

    public static String getFromLine(String line){
        StringBuilder result = new StringBuilder();
        int i = 1;
            while(line.charAt(i-1) != ' '){
                i++;
            }
        for (; i<line.length(); i++){
            result.append(line.charAt(i));
        }
        return result.toString();
    }
}
