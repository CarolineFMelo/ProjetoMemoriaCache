package functions.Associative;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import functions.FileManager;
import graphicInterface.MemoryCacheSimulator;

public class Lru extends AssociativeExecution {

	//LRU -> least recently used
	
	ArrayList<String> recentUse = new ArrayList<String>();
	
	public void runLru() {
		readingDataAssociative();
		readMemoryFileLru();
	}
		
	public void readMemoryFileLru() {
		ArrayList<String> mainMemoryData  = FileManager.stringReader(MemoryCacheSimulator.getFile());
//		ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/testeproprio.txt");
		for(String mainMemory : mainMemoryData) {
			associativeMethodLru(getPart(Long.parseLong(mainMemory)));
		}
		JOptionPane.showMessageDialog(null, "<html>Utilizando algoritmo LRU<br>Hits: " + getHits() 
											+ "<br>Miss: " + getErrors() + "<br>Percentage of hits: " 
											+ (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) 
											+ "%" + "</html>",
											"Resultado do Método Associativo",
											JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Hits: " + getHits());
//		System.out.println("Errors: " + getErrors());
//		System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
	}
		
	public void associativeMethodLru(String[] part) {
		String keyToSearch = part[0];
		
		if (cacheMemory.containsKey(keyToSearch)) {
	        setHits(getHits() + 1);
	        recentUse.remove(part[0]);
	        recentUse.add(part[0]);
	    } else {
	        setErrors(getErrors() + 1);
	        if (!(cacheMemory.size() < getLimitSize())) {
	        	cacheMemory.remove(recentUse.get(0));
	        	recentUse.remove(0);
	        }
	        cacheMemory.put(part[0], part[1]);
	        recentUse.add(part[0]);
	    }
	}
	
}
