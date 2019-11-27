/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.agdt.math;

import uk.ac.leeds.ccg.agdt.math.Math_Byte;
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
public class Math_ByteTest extends Math_Test {
    
    public Math_ByteTest() {
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
    public void testIsByte() {
        String funcName = "isByte";
        System.out.println("Test " + funcName);
        boolean result;
        // Test 1
        int test = 1;
        String s;
        byte x = Byte.MIN_VALUE;
        s = Byte.toString(x);
        result = Math_Byte.isByte(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
        // Test 2
        test ++;
        x += 1;
        s = Byte.toString(x);
        result = Math_Byte.isByte(s);
        printFunctionTest(funcName, test, x, result);
        assertTrue(result);
    }
    
}
