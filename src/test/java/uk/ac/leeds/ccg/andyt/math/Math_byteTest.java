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
public class Math_byteTest {
    
    public Math_byteTest() {
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
     * Test of isByte method, of class Math_byte.
     */
    @Test
    public void testIsByte() {
        System.out.println("isByte");
        String s;
        // Test 1
        byte b = Byte.MIN_VALUE;
        s = Byte.toString(b);
        assertFalse(Math_byte.isByte(s));
        // Test 2
        b += 1;
        s = Byte.toString(b);
        assertTrue(Math_byte.isByte(s));
    }
    
}
