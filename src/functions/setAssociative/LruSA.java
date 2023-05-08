package functions.setAssociative;

import java.util.ArrayList;

import functions.FileManager;

public class LruSA extends SetAssociativeExecution {

	//LRU -> least recently used
	
	ArrayList<String> recentUse = new ArrayList<String>();
	
	public void runLruSA() {
        readingDataSetAssociative();
        readMemoryFileLruSA();
    }

	public void readMemoryFileLruSA() {
    	ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/teste_1.txt");
        for (String mainMemory : mainMemoryData) {
            setAssociativeMethodLruSA(getPart(Long.parseLong(mainMemory)));
        }
        System.out.println("Hits: " + getHits());
        System.out.println("Errors: " + getMisses());
        System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
    }
	
	public void setAssociativeMethodLruSA(String[] part) {
		String keyToSearch = part[0];
		
		if(!(getCacheMemory().get(part[1]) != null && getCacheMemory().get(part[1]).contains(keyToSearch))) {
			setMisses(getMisses() + 1);
			if(getCacheMemory().get(part[1]) == null) {
				getCacheMemory().put(part[1], new ArrayList<String>());
			}
			
			if(getCacheMemory().get(part[1]).size() >= getRows()) {
				getCacheMemory().get(part[1]).remove(recentUse.get(0));
	        	recentUse.remove(0);
			}
			getCacheMemory().get(part[1]).add(part[0]);
			recentUse.add(part[0]);
		} else {
			setHits(getHits() + 1);
			recentUse.remove(part[0]);
	        recentUse.add(part[0]);
		}
	}
	
}
