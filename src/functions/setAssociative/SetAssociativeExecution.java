package functions.setAssociative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import functions.Conversion;
import functions.FileManager;

public class SetAssociativeExecution {
	
	private Long adressSize;
	private Long wordSize;
	private Long tagSize;
	private Long lineSize;
	private Integer hits = 0;
	private Integer misses = 0;
	private Long limitSize = (long) 0;
	private Integer rows = 1;
	private Long sets = (long) 0;
	private Long cacheSizeRightNow = (long) 0;
	
	Map<String, ArrayList<String>> cacheMemory = new HashMap<String, ArrayList<String>>();

	public void readingDataSetAssociative() {
		ArrayList<String> dadosConfig = FileManager.stringReader("./src/data/config.txt");
		String[] mem = dadosConfig.get(0).split("[ #@_\\/.*;]");
		String[] word = dadosConfig.get(1).split("[ #@_\\/.*;]");
		String[] cache = dadosConfig.get(2).split("[ #@_\\/.*;]");
		String[] line = dadosConfig.get(3).split("[ #@_\\/.*;]");
		
		Long memoryBytes = convertValue(Long.parseLong(mem[2]), mem[3]);
		Long wordBytes = convertValue(Long.parseLong(word[2]), word[3]);
		Long cacheBytes = convertValue(Long.parseLong(cache[2]), cache[3]);
		Long lineBytes = Long.parseLong(line[2]);
		
		setAdressSize(calculateAdress(memoryBytes, wordBytes));
		setWordSize(calculateWord(wordBytes));
		setSets(calculateSets(getRows(), cacheBytes, wordBytes));
		setLineSize(calculateLine(getSets()));
		setTagSize(calculateTag(adressSize, wordSize, lineSize));
		setLimitSize(hashMapLimitSize(cacheBytes, wordBytes));
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
	
	public Long calculateLine(Long sets) {
		Long lineSize = (long) (Math.log(sets) / Math.log(2));
		return lineSize;
	}
	
	public Long calculateTag(Long adressSize, Long wordSize, Long lineSize) {
		Long tagSize = adressSize - (wordSize + lineSize);
		return tagSize;
	}
	
	public Long calculateSets(Integer rows, Long cacheBytes, Long wordBytes) {
		Long aux = (cacheBytes/wordBytes) / wordBytes;
		Long sets = (Long) (aux/rows);
		return sets;
	}
	
	public Long hashMapLimitSize(Long cacheBytes, Long wordBytes) {
		Long limit = (long) (cacheBytes / wordBytes) / wordBytes;
		return limit;
	}
	
	public String[] getPart(Long number) {
		String[] part = new String[3];
		String adress = Conversion.intToBinaryString(number, adressSize);
		part[0] = adress.substring(0, tagSize.intValue());
		part[1] = adress.substring(tagSize.intValue(), lineSize.intValue() + tagSize.intValue());
		part[2] = adress.substring(lineSize.intValue() + tagSize.intValue(), adressSize.intValue());
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

	public Integer getMisses() {
		return misses;
	}

	public void setMisses(Integer misses) {
		this.misses = misses;
	}

	public Long getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(Long limitSize) {
		this.limitSize = limitSize;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Long getSets() {
		return sets;
	}

	public void setSets(Long sets) {
		this.sets = sets;
	}

	public Map<String, ArrayList<String>> getCacheMemory() {
		return cacheMemory;
	}

	public void setCacheMemory(Map<String, ArrayList<String>> cacheMemory) {
		this.cacheMemory = cacheMemory;
	}

	public Long getCacheSizeRightNow() {
		return cacheSizeRightNow;
	}

	public void setCacheSizeRightNow(Long cacheSizeRightNow) {
		this.cacheSizeRightNow = cacheSizeRightNow;
	}
	
	
}
