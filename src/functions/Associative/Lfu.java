package functions.Associative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import functions.FileManager;
import graphicInterface.MemoryCacheSimulator;

public class Lfu extends AssociativeExecution {

    // LFU -> least frequently used

    Map<String, Integer> freqCounter = new HashMap<String, Integer>();

    public void runLfu() {
        readingDataAssociative();
        readMemoryFileLfu();
    }

    public void readMemoryFileLfu() {
        ArrayList<String> mainMemoryData = FileManager.stringReader(MemoryCacheSimulator.getFile());
//    	ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/testeproprio.txt");
        for (String mainMemory : mainMemoryData) {
            associativeMethodLfu(getPart(Long.parseLong(mainMemory)));
        }
        JOptionPane.showMessageDialog(null, "<html>Utilizando algoritmo LFU<br>Hits: " + getHits() 
											+ "<br>Miss: " + getErrors() + "<br>Percentage of hits: " 
											+ (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) 
											+ "%" + "</html>",
											"Resultado do Método Associativo",
											JOptionPane.INFORMATION_MESSAGE);
//        System.out.println("Hits: " + getHits());
//        System.out.println("Errors: " + getErrors());
//        System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
    }

    public void associativeMethodLfu(String[] part) {
        String keyToSearch = part[0];

        if (cacheMemory.containsKey(keyToSearch)) {
            setHits(getHits() + 1);
            freqCounter.replace(part[0], freqCounter.get(part[0]) + 1);
        } else {
            setErrors(getErrors() + 1);
            if (!(cacheMemory.size() < getLimitSize())) {
                Map.Entry<String, Integer> min = Collections.min(freqCounter.entrySet(),
                        new Comparator<Map.Entry<String, Integer>>() {
                            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                                return entry1.getValue().compareTo(entry2.getValue());
                            }
                        });
                cacheMemory.remove(min.getKey());
                freqCounter.remove(min.getKey());
            }
            cacheMemory.put(part[0], part[1]);
            freqCounter.put(part[0], 1);
        }
    }

}