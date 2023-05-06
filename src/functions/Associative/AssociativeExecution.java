package functions.Associative;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import functions.Conversion;
import functions.FileManager;
import graphicInterface.MemoryCacheSimulator;

public class AssociativeExecution {
	
	private Long adressSize;
	private Long wordSize;
	private Long tagSize;
	private Long lineSize;
	private Integer hits = 0;
	private Integer errors = 0;
	private Long limitSize = (long) 0;
	private Long cacheSizeRightNow = (long) 0;
	
	Map<String,String> cacheMemory = new HashMap<String,String>();

	public void readingDataAssociative() {
		ArrayList<String> dadosConfig = FileManager.stringReader(MemoryCacheSimulator.getConfigFile());
//		ArrayList<String> dadosConfig = FileManager.stringReader("./src/data/teste_config.txt");
		String[] mem = dadosConfig.get(0).split("[ #@_\\/.*;]");
		String[] word = dadosConfig.get(1).split("[ #@_\\/.*;]");
		String[] cache = dadosConfig.get(2).split("[ #@_\\/.*;]");
		
		Long memoryBytes = convertValue(Long.parseLong(mem[2]), mem[3]);
		Long wordBytes = convertValue(Long.parseLong(word[2]), word[3]);
		Long cacheBytes = convertValue(Long.parseLong(cache[2]), cache[3]);
		
		setAdressSize(calculateAdress(memoryBytes, wordBytes));
		setWordSize(calculateWord(wordBytes));
		setTagSize(calculateTag(adressSize, wordSize));
		setLimitSize(hashMapLimitSize(cacheBytes, wordBytes));
	}
	
	public Long hashMapLimitSize(Long cacheBytes, Long wordBytes) {
		Long limit = (long) (cacheBytes / wordBytes) / wordBytes;
		return limit;
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
	
	public Long calculateAdress(Long memoryBytes, Long wordBytes) {
		Long adressBytes = memoryBytes/wordBytes;
		Long adressSize = Math.round(Math.log(adressBytes) / Math.log(2));
		return adressSize;
	}
	
	public Long calculateWord(Long wordBytes) {
		Long wordSize = (Long) (Math.round(Math.log(wordBytes) / Math.log(2)));
		return wordSize;
	}
	
	public Long calculateTag(Long adressSize2, Long wordSize2) {
		Long tagSize = adressSize2 - wordSize2;
		return tagSize;
	}
	
	public String[] getPart(Long number) {
		String[] part = new String[2];
		String adress = Conversion.intToBinaryString(number, adressSize);
		part[0] = adress.substring(0, tagSize.intValue());
		part[1] = adress.substring(tagSize.intValue(), adressSize.intValue());
		return part;
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

	public Long getTagSize() {
		return tagSize;
	}

	public void setTagSize(Long tagSize) {
		this.tagSize = tagSize;
	}

	public Long getLineSize() {
		return lineSize;
	}

	public void setLineSize(Long lineSize) {
		this.lineSize = lineSize;
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

	public Long getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(Long limitSize) {
		this.limitSize = limitSize;
	}
	
	public Long getCacheSizeRightNow() {
		return cacheSizeRightNow;
	}

	public void setCacheSizeRightNow(Long cacheSize) {
		this.cacheSizeRightNow = cacheSize;
	}
	
}
