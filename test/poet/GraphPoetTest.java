/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import java.io.File;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    /** Testing strategy
    -Test assertions enabled
	-Test when corpus doesn't exist
	-Test when corpus is empty
	-Test when input is empty
	-Test that spaces between words are one space long and words contain letters only
	-Test more than one space, words contain letters and numbers
    */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
 // corpus doesn't exist
    @Test(expected=IOException.class)
    public void testNonexistentCorpus() throws IOException {
        File givenCorpus = new File("test/poet/doesntExist.txt");
        GraphPoet graph = new GraphPoet(givenCorpus);
    }
    
    // corpus is empty
    @Test
    public void testPoemEmptyCorpus() throws IOException {
        File givenCorpus = new File("test/poet/emptyFile.txt");
        GraphPoet graph = new GraphPoet(givenCorpus);
        String input = "test the empty one";
        String outputPoem = graph.poem(input);
        assertEquals(input, outputPoem);
    }
    
    // input is empty
    @Test
    public void testPoemEmptyInput() throws IOException {
        File givenCorpus = new File("test/poet/testFile.txt");
        GraphPoet graph = new GraphPoet(givenCorpus);
        String input = "";
        String outputPoem = graph.poem(input);
        assertEquals(input, outputPoem);
    }
    
    // all spaces between words are one space long and words contain letters only
    @Test
    public void testPoem() throws IOException {
        File givenCorpus = new File("test/poet/testFile.txt");
        GraphPoet graph = new GraphPoet(givenCorpus);
        //System.out.println(graph.toString());
        String input = "to new worlds.";
        String outputPoem = graph.poem(input);
        String correctPoem = "to explore new worlds.";
        assertEquals(correctPoem, outputPoem);
    }
    
    // more than one space, words contain letters and numbers
    @Test
    public void testPoemLettersOrNumbers() throws IOException {
        File givenCorpus = new File("test/poet/testFile.txt");
        GraphPoet graph = new GraphPoet(givenCorpus);
        //System.out.println(graph.toString());
        String input = "to seek new 1 civilizations";
        String outputPoem = graph.poem(input);
        String correctPoem = "to seek out new 1 civilizations";
        assertEquals(correctPoem, outputPoem);
    }
}