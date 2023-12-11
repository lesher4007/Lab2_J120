package exercise1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FrequencyDictionary {
    private String text;
    private String[] words;
    private Set<String> wordsInArray;
    private Map<String,Integer> absoluteResult;

    public void setText(String text) {
        this.text = text;
    }

    public void text() {

        text = text.replaceAll("\n", " ");
        text = text.replaceAll("\r", " ");
        text = text.replaceAll("\t", " ");
        text = text.replaceAll("\b", " ");
        text = text.replaceAll("\f", " ");
        text = text.replaceAll(" \\*\\*\\*", "");
        text = text.replaceAll(",", "");
        text = text.replaceAll("\\.", "");
        text = text.replaceAll("\"", "");
        text = text.replaceAll("'", "");
        text = text.replaceAll(":", "");
        text = text.replaceAll(";", "");
        text = text.replaceAll("!", "");
        text = text.replaceAll("«", "");
        text = text.replaceAll("»", "");
        text = text.replaceAll("…", "");
        text = text.replaceAll("\\?", "");
        text = text.replaceAll("\\*", "");
        text = text.replaceAll("— ", "");
        text = text.replaceAll("\\(", "");
        text = text.replaceAll("\\)", "");
        text = text.replaceAll("    ", " ");
        text = text.replaceAll("   ", " ");
        text = text.replaceAll("  ", " ");
        words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i]=words[i].toLowerCase();
        }

        Arrays.sort(words);

        Set<String> wordsInArray = new LinkedHashSet<>();
        for (String word : words) {
            wordsInArray.add(word);
        }

        this.wordsInArray = wordsInArray;

        Map<String, Integer> collectionWorlds = new HashMap<>();
        for (String word : words) {
            if (collectionWorlds.containsKey(word)) {
                int count = collectionWorlds.get(word);
                collectionWorlds.put(word, count + 1);
            } else {
                collectionWorlds.put(word, 1);
            }
        }
        absoluteResult = collectionWorlds;
    }

    public void reportByAlph(){
        StringBuilder report = new StringBuilder("Слово = абсолютная частота = относительная частота:\n\n");

        for (String word:wordsInArray) {
            report.append(word+" = "+absoluteResult.get(word)+" = "+((double)(absoluteResult.get(word))/wordsInArray.size()));
            report.append("\n");
        }

        File dir = new File("C:\\Users\\Professional\\IdeaProjects\\Lab2_J120\\files");
        dir.mkdirs();
        File newFile = new File(dir,"report-by-alph.txt");
        String text = new String(report);
        if(dir.canWrite()){
            try {newFile.createNewFile();
                FileWriter writer = new FileWriter(newFile);
                writer.write(text);
                writer.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void  reportByAlphRev(){
        String[] wordsRev = words;
        Set<String> wordsInArrayRev = new LinkedHashSet<>();

        for (int i = 0; i < wordsRev.length; i++) {
            StringBuilder sb = new StringBuilder(wordsRev[i]);
            sb. reverse();
            wordsRev[i] = sb.toString().toLowerCase();
        }

        Arrays.sort(wordsRev);

        Collections.addAll(wordsInArrayRev, wordsRev);

        StringBuilder report = new StringBuilder("Слово = абсолютная частота = относительная частота:\n\n");
        for (String word:wordsInArrayRev) {
            StringBuilder sb = new StringBuilder(word);
            sb. reverse();
            report.append(sb+" = "+absoluteResult.get(sb.toString())+" = "+((double)(absoluteResult.get(sb.toString()))/wordsInArrayRev.size()));
            report.append("\n");
        }

        File dir = new File("C:\\Users\\Professional\\IdeaProjects\\Lab2_J120\\files");
        dir.mkdirs();
        File newFile = new File(dir,"report-by-alph-rev.txt");
        String text = new String(report);
        if(dir.canWrite()){
            try {newFile.createNewFile();
                FileWriter writer = new FileWriter(newFile);
                writer.write(text);
                writer.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void   reportByFreq(){
        List<String> arrayForReport = new LinkedList<>();
        Set<String> setForReport = new LinkedHashSet<>();
        setForReport.addAll(wordsInArray);


        for (int i = 0; i < setForReport.size(); i++) {
            String var = null;
            int count = 0;
            for (String word : setForReport) {
                if (count < absoluteResult.get(word)) {
                    count = absoluteResult.get(word);
                    var = word;
                }
            }
            arrayForReport.add(var);
            setForReport.remove(var);
        }

        StringBuilder report = new StringBuilder("Слово = абсолютная частота = относительная частота:\n\n");

        for (String word:arrayForReport) {
            report.append(word+" = "+absoluteResult.get(word)+" = "+((double)(absoluteResult.get(word))/absoluteResult.size()));
            report.append("\n");
        }
        File dir = new File("C:\\Users\\Professional\\IdeaProjects\\Lab2_J120\\files");
        dir.mkdirs();
        File newFile = new File(dir,"report-by-freq.txt");
        String text = new String(report);
        if(dir.canWrite()){
            try {newFile.createNewFile();
                FileWriter writer = new FileWriter(newFile);
                writer.write(text);
                writer.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String[] dictionary(){
        String[] array = wordsInArray.toArray(new String[0]);
        String[] result = new String[wordsInArray.size()];
        System.arraycopy(array,0,result,0,array.length);
        return result;
    }


}
