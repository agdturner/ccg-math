/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author geoagdt
 */
public class Math_shortTest {
    
    public Math_shortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isByte method, of class Math_short.
     */
    @Test
    public void testIsShort() {
        System.out.println("isShort");
        String s;
        // Test 1
        short sh = Short.MIN_VALUE;
        s = Short.toString(sh);
        assertFalse(Math_short.isShort(s));
        // Test 2
        sh += 1;
        s = Short.toString(sh);
        assertTrue(Math_short.isShort(s));
    }
    
}
