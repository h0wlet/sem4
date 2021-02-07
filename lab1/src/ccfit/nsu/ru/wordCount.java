package ccfit.nsu.ru;

import java.io.IOException;
import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class wordCount {
    int count = 0;
    Map<String, Integer> wordMap = new HashMap<String, Integer>();

    public void readAndCount(String inputFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String str;
            StringBuilder newWord = new StringBuilder();
            boolean beginningOfWord = true;
            while ((str = reader.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    char variable = str.charAt(i);
                    if (Character.isLetterOrDigit(variable)) {
                        newWord.append(variable);
                        beginningOfWord = false;
                    }
                    else {
                        if (!beginningOfWord) {
                            putInMap(newWord);
                            newWord.delete(0, newWord.length());
                        }
                        beginningOfWord = true;
                    }
                    if ((Character.isLetterOrDigit(variable)) && (i == str.length() - 1)) {
                        putInMap(newWord);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != reader) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void putInMap(StringBuilder newWord){
        wordMap.merge(newWord.toString(), 1, Integer::sum);
        count++;
    }

    public void sortAndWrite(String outputFile) throws IOException {
        List<Map.Entry<String, Integer>> wordList = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        writeToCSV(wordList, outputFile);
    }

    public void writeToCSV(List<Map.Entry<String, Integer>> wordList, String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        for (Map.Entry<String, Integer> str : wordList) {
            writer.write(str.getKey() + ";" + str.getValue() + ";" + (double)str.getValue()/(double)count*100 + "\n");
        }
        writer.close();
    }

}
