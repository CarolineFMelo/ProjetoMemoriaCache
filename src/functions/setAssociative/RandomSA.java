package functions.setAssociative;

import java.util.ArrayList;

import functions.FileManager;

public class RandomSA extends SetAssociativeExecution {
	
	//Random
	
	public int getRandomIntInclusive(Integer min, Integer max) {
		  min = (int) Math.ceil(min);
		  max = (int) Math.floor(max);
		  return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
	
	public void runRandomSA() {
        readingDataSetAssociative();
        readMemoryFileRandomSA();
    }

	public void readMemoryFileRandomSA() {
    	ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/teste_1.txt");
        for (String mainMemory : mainMemoryData) {
        	setAssociativeMethodRandomSA(getPart(Long.parseLong(mainMemory)));
        }
        System.out.println("Hits: " + getHits());
        System.out.println("Errors: " + getMisses());
        System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
    }
	
	public void setAssociativeMethodRandomSA(String[] part) {
		String keyToSearch = part[0];
		String[] randomKey;
		
		if(!(getCacheMemory().get(part[1]) != null && getCacheMemory().get(part[1]).contains(keyToSearch))) {
			setMisses(getMisses() + 1);
			if(getCacheMemory().get(part[1]) == null) {
				getCacheMemory().put(part[1], new ArrayList<String>());
			}
			
			if((getCacheMemory().get(part[1]).size() >= getRows())) {
				do {
					Long randomNum = (long) getRandomIntInclusive(0, 25596);
					randomKey = getPart(randomNum);
				}
				while (!(getCacheMemory().get(part[1]).contains(randomKey[0])));
				getCacheMemory().get(part[1]).remove(randomKey[0]);
			}
			getCacheMemory().get(part[1]).add(part[0]);
		} else {
			setHits(getHits() + 1);
		}
	}

}
