package exercise2;


import java.util.HashMap;
import java.util.Map;

public class ScriptingLanguage {

    private String text;
    private String[] line;

    public void setText(String text) {
        this.text = text;
    }

    public void read() {
        Map<String, Integer> variable = new HashMap<>();

        text = text.replaceAll("\r", " ");
        line = text.split("\n");

        for (int i = 0; i < line.length; i++) {
            String[]arrayString = line[i].split(" ");

            if(arrayString[0].equals("#")){continue;}
            if(arrayString[0].equals("print")){
                StringBuilder sb = new StringBuilder();
                char[]arrayChar=line[i].toCharArray();
                for (int j = 6; j < arrayChar.length; j++) {
                    switch (arrayChar[j]){
                        case '"':
                            j++;
                            for (int k =j; k < arrayChar.length; k++) {
                                if (arrayChar[k]!='"'){
                                    j++;
                                    sb.append(arrayChar[k]);
                                }else {break;}
                            }
                            break;
                        case ',', ' ':
                            break;
                        case '$':
                            StringBuilder sb$ = new StringBuilder('S');
                            for (int k =j; k < arrayChar.length; k++) {

                                if (arrayChar[k]!=','&& arrayChar[k]!=' '){
                                    j++;
                                    sb$.append(arrayChar[k]);
                                }else {break;}
                            }
                            if (variable.containsKey(sb$.toString())){
                                sb.append(variable.get(sb$.toString()));
                            }else {
                                System.out.println("Переменная "+ sb$ +" не найдена.");
                                throw new IllegalArgumentException();
                            }
                            break;
                    }
                }
                System.out.println(sb);
            }
            if(arrayString[0].equals("set")){
                for (int j = 1; j < arrayString.length; j++) {

                    switch (arrayString[j].charAt(0)) {
                        case '$':
                            variable.put(arrayString[j], 0);
                            break;
                        case '=':
                            if (arrayString[j+1].charAt(0)=='$'){
                                j++;
                                if (variable.containsKey(arrayString[j])){
                                    variable.put(arrayString[1], variable.get(arrayString[j]));
                                }else {
                                    System.out.println("Переменная "+arrayString[j]+" не найдена");
                                    throw new IllegalArgumentException();
                                }
                            }else {
                                j++;
                                variable.put(arrayString[1], Integer.valueOf(arrayString[j]));
                            }
                            break;
                        case '+':
                            if (arrayString[j+1].charAt(0)=='$'){
                                j++;
                                if (variable.containsKey(arrayString[j])){
                                    variable.put(arrayString[1], variable.get(arrayString[1])+ variable.get(arrayString[j]));
                                }else {
                                    System.out.println("Переменная "+arrayString[j]+" не найдена");
                                    throw new IllegalArgumentException();
                                }
                            }else {
                                j++;
                                variable.put(arrayString[1], Integer.parseInt(arrayString[j]) + variable.get(arrayString[1]));
                            }
                            break;
                        case '-':
                            if (arrayString[j+1].charAt(0)=='$'){
                                j++;
                                if (variable.containsKey(arrayString[j])){
                                    variable.put(arrayString[1], variable.get(arrayString[1])- variable.get(arrayString[j]));
                                }else {
                                    System.out.println("Переменная "+arrayString[j]+" не найдена");
                                    throw new IllegalArgumentException();
                                }
                            }else {
                                j++;
                                variable.put(arrayString[1],variable.get(arrayString[1])-Integer.parseInt(arrayString[j]));
                            }
                            break;
                    }
                }
            }
        }
    }
}
