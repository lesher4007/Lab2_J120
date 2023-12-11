package exercise1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        FrequencyDictionary frequencyDictionary= new FrequencyDictionary();

        File file = new File("C:\\Users\\Professional\\IdeaProjects\\Lab2_J120\\j120-lab2.txt");
        if(file.exists() && file.canRead()){
            StringBuilder sb = new StringBuilder();
            try{
                FileReader reader = new FileReader(file);
                char[] buff = new char[1024];
                int size;
                while ((size = reader.read(buff))>-1){
                    sb.append(new String(buff,0,size));
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
            frequencyDictionary.setText(sb.toString());
        }

        frequencyDictionary.text();
        frequencyDictionary.reportByAlph();
        frequencyDictionary.reportByAlphRev();
        frequencyDictionary.reportByFreq();

        System.out.println(Arrays.toString(frequencyDictionary.dictionary()));


    }
}
