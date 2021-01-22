/**
process the .log files under this file's path,
filter and keep the line that starts with "[<color=white>Info</color>][Network]".
(line 32, 33 : replace it with your own demands!)
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class LogFilter {
    public static void main(String[] args) {
        String filepath = LogFilter.class.getResource("").getPath().replace('/', '\\');
        filepath = filepath.substring(1, filepath.length());    //get this file's path

        File folder = new File(filepath);
        File[] logfiles = folder.listFiles();
        for (File logfile : logfiles) {
            String filename = logfile.getName();        //get the filenames under the path
            if (filename.substring(filename.length() - 4, filename.length()).equals(".log")) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));   //read
                    String line = null;
                    ArrayList<String> keeplines = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        if (line.length() > 36 && line.substring(0, 36).equals("[<color=white>Info</color>][Network]")) {   //replace it with your own demands!
                            keeplines.add(line.substring(36, line.length()));
                        }
                    }
                    br.close();
                    
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("new_" + filename),"UTF-8"));   //write
                    for (int i = 0; i < keeplines.size(); ++i) {
                        bw.write(keeplines.get(i));
                        bw.newLine();
                        bw.newLine();
                    }
                    bw.flush();
                    bw.close();
                } 
                catch (FileNotFoundException e) {
                    System.out.println("FileNotFoundException!");
                } 
                catch (IOException e) {
                    System.out.println("IOException!");
                }                
            }
        }
    }
}