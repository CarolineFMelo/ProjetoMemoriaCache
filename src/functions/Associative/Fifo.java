package functions.Associative;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import functions.FileManager;
import graphicInterface.MemoryCacheSimulator;

public class Fifo extends AssociativeExecution {
	
	//FIFO -> first in first out
	
	ArrayList<String> entryOrder = new ArrayList<String>();
	
	public void runFifo() {
		readingDataAssociative();
		readMemoryFileFifo();
	}
	
	public void readMemoryFileFifo() {
		ArrayList<String> mainMemoryData  = FileManager.stringReader(MemoryCacheSimulator.getFile());
//		ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/testeproprio.txt");
		for(String mainMemory : mainMemoryData) {
			associativeMethodFifo(getPart(Long.parseLong(mainMemory)));
		}
		JOptionPane.showMessageDialog(null, "<html>Utilizando algoritmo FIFO<br>Hits: " + getHits() 
											+ "<br>Miss: " + getErrors() + "<br>Percentage of hits: " 
											+ (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) 
											+ "%" + "</html>",
											"Resultado do Método Associativo",
											JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Hits: " + getHits());
//		System.out.println("Errors: " + getErrors());
//		System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
	}
	
	
	public void associativeMethodFifo(String[] part) {
		String keyToSearch = part[0];
		
		if(cacheMemory.containsKey(keyToSearch)) {
			setHits(getHits() + 1);
		} else {
			setErrors(getErrors() + 1);
			if(getCacheSizeRightNow() < getLimitSize()) {
				cacheMemory.put(part[0], part[1]);
				entryOrder.add(part[0]);
				setCacheSizeRightNow(getCacheSizeRightNow() + 1);
			} else {
				cacheMemory.remove(entryOrder.get(0));
				entryOrder.remove(0);
				cacheMemory.put(part[0], part[1]);
				entryOrder.add(part[0]);
			}
		}
	}
	
}
