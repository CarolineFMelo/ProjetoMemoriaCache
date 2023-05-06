package functions.Direct;

import java.util.ArrayList;
import graphicInterface.MemoryCacheSimulator;
import javax.swing.JOptionPane;

import functions.Conversion;
import functions.FileManager;

public class DirectExecution {
	
	private String[] cacheMemory;
	private Long adressSize;
	private Long wordSize;
	private Long lineSize;
	private Long tagSize;
	private Integer hits = 0;
	private Integer errors = 0;

	public void readingDataDirect() {
		ArrayList<String> dadosConfig = FileManager.stringReader(MemoryCacheSimulator.getConfigFile());
		String[] mem = dadosConfig.get(0).split("[ #@_\\/.*;]");
		String[] word = dadosConfig.get(1).split("[ #@_\\/.*;]");
		String[] cache = dadosConfig.get(2).split("[ #@_\\/.*;]");
		String[] line = dadosConfig.get(3).split("[ #@_\\/.*;]");
		
		Long memoryBytes = convertValue(Long.parseLong(mem[2]), mem[3]); 
		Long wordBytes = convertValue(Long.parseLong(word[2]), word[3]);
		Long cacheBytes = convertValue(Long.parseLong(cache[2]), cache[3]);
		Long lineBytes = Long.parseLong(line[2]);
		
		adressSize = calculateAdress(memoryBytes, wordBytes);
		wordSize = calculateWord(wordBytes);
		lineSize = calculateLine(cacheBytes, wordBytes);
		tagSize = calculateTag(adressSize, wordSize, lineSize);
		
		//System.out.println("Size in bytes: " + "\nmem:" + memoryBytes + "\nword:" + wordBytes + "\ncache:" + cacheBytes + "\nline:" + lineBytes + " word");
		//System.out.println("\nAdress division: " + "\nadress:" + adressSize + "\nword:" + wordSize + "\nline:" + lineSize + "\ntag:" + tagSize + "\n");

		Long memoryCalculate = (cacheBytes / wordBytes) / wordBytes;
		
		setCacheMemory(new String[memoryCalculate.intValue()]);
		
		readMemoryFile();
		
	}
	

	public Long convertValue(Long bytes, String multiplier) {
		Long totalBytes = bytes;
		switch(multiplier) {
		case "KB":
			totalBytes = bytes * 1024;
			break;
		case "MB":
			totalBytes = bytes * 1024 * 1024;
			break;
		case "GB":
			totalBytes = bytes * 1024 * 1024 * 1024;
			break;
		}
		return totalBytes;
	}
	
	public Long calculateAdress(Long mem, Long word) {
		Long adressBytes = mem/word;
		Long adressSize = (Long)(Math.round(Math.log(adressBytes) / Math.log(2)));
		return adressSize;
	}
	
	public Long calculateWord(Long wordBytes) {
		Long wordSize = (Long) (Math.round(Math.log(wordBytes) / Math.log(2)));
		return wordSize;
	}
	
	public Long calculateLine(Long cacheBytes, Long wordBytes) {
		Long lineBytes = (cacheBytes/wordBytes) / wordBytes;
		Long lineSize = (Long)(Math.round(Math.log(lineBytes) / Math.log(2)));
		return lineSize;
	}
	
	public Long calculateTag(Long adressSize, Long wordSize, Long lineSize) {
		Long tagSize = adressSize - (wordSize + lineSize);
		return tagSize;
	}
	
	public void directMethod(String[] part) {
		Integer index = Integer.parseInt(part[1], 2);
		if(getCacheMemory()[index] != null && getCacheMemory()[index].equals(part[0])) {
			setHits(getHits() + 1);
		} else {
			getCacheMemory()[index] = part[0];
			setErrors(getErrors() + 1);
		}
	}
	
	public void readMemoryFile() {
		ArrayList<String> mainMemoryData = FileManager.stringReader(MemoryCacheSimulator.getFile());
		for(String mainMemory : mainMemoryData) {
			directMethod(getPart(Long.parseLong(mainMemory)));
		}
		 JOptionPane.showMessageDialog(null, "<html>Hits: " + getHits() 
		 									+ "<br>Miss: " + getErrors() + "<br>Percentage of hits: " 
		 									+ (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) 
		 									+ "%" + "</html>",
		 									"Resultado do Método Direto",
		 									JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("Hits: " + getHits());
		//System.out.println("Errors: " + getErrors());
		//System.out.println("Percentage of hits: " + (Double.valueOf(getHits() / Double.valueOf(mainMemoryData.size())) * 100) + "%");
	}
	
	public String[] getPart(Long number) {
		String[] part = new String[3];
		String adress = Conversion.intToBinaryString(number, adressSize);
		part[0] = adress.substring(0, tagSize.intValue());
		part[1] = adress.substring(tagSize.intValue(), lineSize.intValue() + tagSize.intValue());
		part[2] = adress.substring(lineSize.intValue() + tagSize.intValue(), adressSize.intValue());
		return part;
	}

	public String[] getCacheMemory() {
		return cacheMemory;
	}

	public void setCacheMemory(String[] cacheMemory) {
		this.cacheMemory = cacheMemory;
	}
	
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}
	
	public Long getAdressSize() {
		return adressSize;
	}

	public void setAdressSize(Long adressSize) {
		this.adressSize = adressSize;
	}

	public Long getWordSize() {
		return wordSize;
	}

	public void setWordSize(Long wordSize) {
		this.wordSize = wordSize;
	}

	public Long getLineSize() {
		return lineSize;
	}

	public void setLineSize(Long lineSize) {
		this.lineSize = lineSize;
	}

	public Long getTagSize() {
		return tagSize;
	}

	public void setTagSize(Long tagSize) {
		this.tagSize = tagSize;
	}

}
