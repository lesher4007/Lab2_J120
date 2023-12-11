package exercise2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ScriptingLanguage scriptingLanguage = new ScriptingLanguage();

        File file = new File("C:\\Users\\Professional\\IdeaProjects\\Lab2_J120\\text.txt");
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
            scriptingLanguage.setText(sb.toString());
        }
        scriptingLanguage.read();
    }
}
