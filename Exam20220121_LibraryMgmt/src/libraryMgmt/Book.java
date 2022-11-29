package libraryMgmt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Book {
	private int minCode;
	private int maxCode;
	private String title;
	private String[] authors;
	private int nVolumes;
	
	
	public Book(Integer minCode, Integer maxCode, String title, int nVolumes, String[] authors) {
		this.minCode = minCode;
		this.maxCode = maxCode;
		this.title = title;
		this.authors = authors;
		this.nVolumes = nVolumes;
	}

	public String getTitle() {
		return title;
	}

	public int getMinCode() {
		return minCode;
	}

	public int getMaxCode() {
		return maxCode;
	}

	public String[] getAuthors() {
		return authors;
	}

	public int getnVolumes() {
		return nVolumes;
	}
	
	public void addVolume() {
		nVolumes++;
		minCode--;;
	}
	
	public void rmVolume() {
		nVolumes--;
		minCode++;
	}
	
	public  ArrayList<String> autoriList() { 
	  ArrayList<String> nomiAutoriList= new ArrayList<>(); 
	  for(String s: authors) {
		  nomiAutoriList.add(s);
	  }
	  List<String> list = nomiAutoriList.stream().sorted().collect(Collectors.toList());
	  nomiAutoriList.addAll(list);
	  return nomiAutoriList; 
	}
	
	
}
