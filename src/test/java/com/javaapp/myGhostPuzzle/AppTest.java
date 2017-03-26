package com.javaapp.myGhostPuzzle;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.util.Assert;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest{

    @Autowired
    private AppController appController;   

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void testInitiated(){
        //instance exist
        assertThat(appController).isNotNull();
        assertThat(wordRepository).isNotNull();
    }
    @Test
    public void testRepository(){

        //find valid entry
        assertThat(wordRepository.find("charqui").size() == 157).isTrue();
        //find valid entry
        assertThat(wordRepository.find("char").size() == 157).isTrue();
        //find valid entry
        assertThat(wordRepository.find("char").size() == 157).isTrue();
        // find valid lessThanFour keys
        assertThat(wordRepository.find("cha").size() == 13135).isTrue();

        // find invalid entry keys
        assertThat(wordRepository.find("chaa").size() == 0).isTrue();
    }
    @Test
    public void testSessions(){
        appController.reset();

        // ready to play? status = true
        assertThat(appController.getGameStatus()).isTrue();

        appController.setSessionString("test");

        // game in session status = false
        assertThat(appController.getGameStatus()).isFalse();
       
        // reset session for new game status = true
        appController.reset();
        assertThat(appController.getGameStatus()).isTrue();

    } 

    //Calculated by getting the largest Odd length word if available
    @Test   
    public void testBestOutcomeGenerator(){
        appController.reset();
        //expected outcome is charmingest
        appController.setSessionString("charm");
        //user always goes first :)
        appController.makeUsersMove('i');
        //odd word length is potential win
        assertThat(appController.getBestOutcome()).isEqualTo("charmingest");


        appController.reset();
        //expected outcome random
        appController.setSessionString("charlad");
        //user always goes first :)
        appController.makeUsersMove('d');
        //even word length with other words available
        assertThat(appController.getBestOutcome()).isNotEqualTo("charladies");

        appController.reset();
        //expected outcome random
        appController.setSessionString("charladi");
        //user always goes first :)
        appController.makeUsersMove('e');
        //even word length with no other words available
        assertThat(appController.getBestOutcome()).isEqualTo("charladies");

        


    }

    @Test   
    public void testGame(){
        appController.reset();

         //user makes a successful move
        appController.setSessionString("charm");
        assertThat(appController.makeUsersMove('i')).isTrue();

        //user makes a unsuccessful move
        appController.setSessionString("charm");
        assertThat(appController.makeUsersMove('c')).isFalse();

        //computer makes a successful move
        appController.setSessionString("charm");
        assertThat(appController.makeComputerMove()).isTrue();

        //computer makes a unsuccessful move
        appController.setSessionString("charladies");
        assertThat(appController.makeComputerMove()).isFalse();
    }

}
