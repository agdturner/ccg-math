/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.agdt.math;

import uk.ac.leeds.ccg.agdt.math.Math_Short;
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
public class Math_ShortTest  extends Math_Test {
    
    public Math_ShortTest() {
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

    @Test
    public void testIsShort() {
        String funcName = "isShort";
        System.out.println("Test " + funcName);
        short x;
        String s;
        boolean result;
        // Test 1
        int test = 1;
        x = Short.MIN_VALUE;
        s = Short.toString(x);
        result = Math_Short.isShort(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
        // Test 2
        test ++;
        x += 1;
        s = Short.toString(x);
        result = Math_Short.isShort(s);
        printFunctionTest(funcName, test, x, result);
        assertTrue(Math_Short.isShort(s));
    }
    
}
