package functions.setAssociative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import functions.FileManager;

public class LfuSA extends SetAssociativeExecution {
	
	// LFU -> least frequently used
	
	Map<String, Integer> freqCounter = new HashMap<String, Integer>();
	
	public void runLfuSA() {
        readingDataSetAssociative();
        readMemoryFileLfuSA();
    }

	public void readMemoryFileLfuSA() {
    	ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/teste_1.txt");
        for (String mainMemory : mainMemoryData) {
            setAssociativeMethodLfuSA(getPart(Long.parseLong(mainMemory)));
        }
        System.out.println("Hits: " + getHits());
        System.out.println("Miss: " + getMisses());
        System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
    }
	
	public void setAssociativeMethodLfuSA(String[] part) {
		String keyToSearch = part[0];
		if(!(getCacheMemory().get(part[1]) != null && getCacheMemory().get(part[1]).contains(keyToSearch))) {
			setMisses(getMisses() + 1);
			if(getCacheMemory().get(part[1]) == null) {
				getCacheMemory().put(part[1], new ArrayList<String>());
			}
			
			if((getCacheMemory().get(part[1]).size() >= getRows())) {
				 Map.Entry<String, Integer> min = Collections.min(freqCounter.entrySet(),
	             	new Comparator<Map.Entry<String, Integer>>() {
	                 	public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
	                     	return entry1.getValue().compareTo(entry2.getValue());
	                 	}
				 	});
				 getCacheMemory().get(part[1]).remove(min.getKey());
	             freqCounter.remove(min.getKey());
			}
			getCacheMemory().get(part[1]).add(part[0]);
            freqCounter.put(part[0], 1);
		} else {
			setHits(getHits() + 1);
			freqCounter.replace(part[0], freqCounter.get(part[0]) + 1);
		}
	}
	
}
