package functions.setAssociative;
import java.util.ArrayList;
import functions.FileManager;

public class FifoSA extends SetAssociativeExecution{
	
	//FIFO -> first in first out
	
	ArrayList<String> entryOrder = new ArrayList<String>();

	public void runFifoSA() {
		readingDataSetAssociative();
		readMemoryFileFifoSA();
	}
	
	public void readMemoryFileFifoSA() {
		ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/testeproprio.txt");
		for(String mainMemory : mainMemoryData) {
			setAssociativeMethodFifoSA(getPart(Long.parseLong(mainMemory)));
		}
		System.out.println("Hits: " + getHits());
		System.out.println("Errors: " + getMisses());
		System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
	}
	
	
	public void setAssociativeMethodFifoSA(String[] part) {
		String keyToSearch = part[0];
		
		if(!(getCacheMemory().get(part[1]) != null && getCacheMemory().get(part[1]).contains(keyToSearch))) {
			setMisses(getMisses() + 1);
			if(getCacheMemory().get(part[1]) == null) {
				getCacheMemory().put(part[1], new ArrayList<String>());
			}
			
			if((getCacheMemory().get(part[1]).size() >= getRows())) {
				getCacheMemory().get(part[1]).remove(entryOrder.get(0));
				entryOrder.remove(0);
			}
			getCacheMemory().get(part[1]).add(part[0]);
			entryOrder.add(part[0]);
		} else {
			setHits(getHits() + 1);
		}
	}
	
}
