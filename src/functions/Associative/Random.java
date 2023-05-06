package functions.Associative;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import functions.FileManager;
import graphicInterface.MemoryCacheSimulator;

public class Random extends AssociativeExecution {
	
	//Random
	
	public int getRandomIntInclusive(Integer min, Integer max) {
		  min = (int) Math.ceil(min);
		  max = (int) Math.floor(max);
		  return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
	
	public void runRandom() {
		readingDataAssociative();
		readMemoryFileRandom();
	}
		
	public void readMemoryFileRandom() {
		ArrayList<String> mainMemoryData  = FileManager.stringReader(MemoryCacheSimulator.getFile());
//		ArrayList<String> mainMemoryData  = FileManager.stringReader("./src/data/testeproprio.txt");
		for(String mainMemory : mainMemoryData) {
			associativeMethodRandom(getPart(Long.parseLong(mainMemory)));
		}
		JOptionPane.showMessageDialog(null, "<html>Utilizando algoritmo Random<br>Hits: " + getHits() 
											+ "<br>Miss: " + getErrors() + "<br>Percentage of hits: " 
											+ (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) 
											+ "%" + "</html>",
											"Resultado do Método Associativo",
											JOptionPane.INFORMATION_MESSAGE);
//		System.out.println("Hits: " + getHits());
//		System.out.println("Errors: " + getErrors());
//		System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
	}
		
	public void associativeMethodRandom(String[] part) {
		String keyToSearch = part[0];
		String[] randomKey;
		
		if(cacheMemory.containsKey(keyToSearch)) {
			setHits(getHits() + 1);
		} else {
			setErrors(getErrors() + 1);
			if(getCacheSizeRightNow() < getLimitSize()) {
				cacheMemory.put(part[0], part[1]);
				setCacheSizeRightNow(getCacheSizeRightNow() + 1);
			} else {
				do {
					Long randomNum = (long) getRandomIntInclusive(0, 25596);
					randomKey = getPart(randomNum);
				}
				while (!(cacheMemory.containsKey(randomKey[0])));
				cacheMemory.remove(randomKey[0]);
				cacheMemory.put(part[0], part[1]);
			}
		}
	}

}
