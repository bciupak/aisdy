/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.edu.pw.ee.aisd_ex_0.searcher.binary_search;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.edu.pw.ee.aisd_ex_0.searcher.linear_search.LinearSearch;
import pl.edu.pw.ee.aisd_ex_0.searcher.service.Searching;

/**
 *
 * @author userl
 */
public class BinarySearchTest {

    private Searching searcher;
    
    
    @BeforeEach
    public void setUp(){
        searcher = new LinearSearch();
    }

    
        @Test
    public void shouldThrowException_WhenInputNumsAreNull(){
        
        // given
        
        int[] nums = null;
        int toFind = 0;
        
        
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> {
            searcher.search(nums, toFind);
            
            
        });
        
        // when
        String actualMessage = ex.getMessage();
        String expectedMessage = "Input nums array shound not be null!";
        
        
        // then
        
        assertTrue(actualMessage.equals(expectedMessage));
        
        
  }
    
            @Test
    public void shouldPassWhen_toFindIsInNumsEven(){

        //given 
        int[] nums = {1, 2, 3, 4};
      
        int toFind = 3;
        
       
        
        //when
        
        int actualResult = searcher.search(nums, toFind);
        int excpectedResult = 2;
         //then
         
        assertTrue(actualResult == excpectedResult);
        
        
        
    }
    
    
                @Test
    public void shouldPassWhen_toFindIsInNumsUneven(){

        //given 
        int[] nums = {1, 2, 3, 4, 5};
      
        int toFind = 3;
        
       
        
        //when
        
        int actualResult = searcher.search(nums, toFind);
        int excpectedResult = 2;
         //then
         
        assertTrue(actualResult == excpectedResult);
        
        
        
    }
    
    @Test
    public void shouldPassWhen_toFindIsNotInNums(){

        //given 
        int[] nums = {1, 2, 3, 4};
      
        int toFind = 6;
        
       
        
        //when
        
        int actualResult = searcher.search(nums, toFind);
        int excpectedResult = -1;
         //then
         
        assertTrue(actualResult == excpectedResult);
        
        
        
    }
}
