package com.javaapp.myGhostPuzzle;

import org.springframework.stereotype.Repository;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.Collection;
import com.google.common.collect.HashMultimap;


@Repository
public class WordRepository{
	private Multimap<String, String> collections = HashMultimap.create();

	public void add(String word){
		collections.put(word.substring(0,4), word);
	}
	
	// return subcollection
	public Collection find(String entry){
		if (entry.length() < 4){
			return collections.keySet();
		} else {
			return collections.get(entry.substring(0,4));
		}
	}



}