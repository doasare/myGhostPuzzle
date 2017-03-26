package com.javaapp.myGhostPuzzle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.TreeSet;
import java.util.Comparator;
// import java.util.Collections;
import java.util.Random;


@RestController
public class AppController {
	@Autowired
    private WordRepository wordRepository;

	private String sessionString = "";
	private TreeSet<String> possibleResults;




	//game state methods
 	public boolean getGameStatus(){
 		System.out.println("---Status---");
 		System.out.println(sessionString.isEmpty());
 		// System.out.println("---Status---" + sessionString == "");
 		return sessionString.isEmpty();
 	}
 	public void reset(){
 		sessionString = "";
 	}
 	public void setSessionString(String s){
 		sessionString = s; 
 	}

 	//functional methods
 	public boolean makeUsersMove(char c){ //user entry
 		sessionString = sessionString + c; // new session string
 		possibleResults = new TreeSet<String>();
 		System.out.println("new string -> " +sessionString);
 		possibleResults.addAll(wordRepository.find(sessionString)); //get sub category
 		System.out.println(sessionString);
 		System.out.println(!(getSub().size() < 2 || getSub().contains(sessionString)));

 		return !(getSub().size() < 2 || getSub().contains(sessionString)); // checks if
 	} 

 	//functional methods
 	public boolean makeComputerMove(){
 		String bestWord = getBestOutcome();
 		if(sessionString.length() < bestWord.length()){
 			char c = bestWord.charAt(sessionString.length());
	  		sessionString = sessionString + c;

	 		possibleResults = new TreeSet<String>();
	 		possibleResults.addAll(wordRepository.find(sessionString));
 		}

 		return !(getSub().size() < 2 || getSub().contains(sessionString));
 	}

 	//best word
 	public String getBestOutcome(){
 		System.out.println("query -> " +sessionString);
 		String bestOutcome = "";
 		String randWord = "";
 		Random rand = new Random();
 		System.out.println(getSub().size());
 		int  n = (getSub().size() == 0)? 0: rand.nextInt(getSub().size()); 
 		int i= 0;

 		// iterate winnable outcome for computer and get best
 		for (String word : getSub()){
 			System.out.println(word);
 			if(bestOutcome.length() < word.length() && word.length() % 2 != 0){
 				bestOutcome = word;
 			}
 			if(i==n){
 				randWord = word;
 			}
 			i++;
 		}
 		
 		System.out.println(bestOutcome);
 		System.out.println(randWord);
 		return (bestOutcome == "")? randWord : bestOutcome;
 	}


 	public TreeSet<String> getSub(){
 		return (TreeSet<String>) possibleResults.subSet(sessionString, sessionString + "zzz");
 	}

}



