/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.math;

import java.math.BigInteger;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static uk.ac.leeds.ccg.andyt.math.Generic_float.getNumberOfFloatsInRange;

/**
 *
 * @author geoagdt
 */
public class Generic_floatTest {
    
    public Generic_floatTest() {
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
     * Test of isFloat method, of class Generic_float.
     */
    @Test
    public void testIsFloat() {
        System.out.println("isFloat");
        String s;
        s = "0.1";
        assertTrue(Generic_float.isFloat(s));
    }
    
    /**
     * Test of isFloatExact method, of class Generic_float.
     */
    @Test
    public void testIsFloatExact() {
        System.out.println("isFloatExact");
        String s;
        s = "0.1";
        assertFalse(Generic_float.isFloatExact(s));
    }

    /**
     * Test of toPlainString method, of class Generic_float.
     */
    @Test
    public void testToPlainString() {
        System.out.println("toPlainString");
        float f;
        String expResult;
        String result;
        // Test 1
        f = 0.0f;
        expResult = "0.0";
        result = Generic_float.toPlainString(f);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 2
        f = 0.1f;
        expResult = "0.1";
        result = Generic_float.toPlainString(f);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfFloatsInRange method, of class Generic_float.
     */
    @Test
    public void testGetNumberOfFloatsInRange() {
        System.out.println("getNumberOfFloatsInRange");
        float l;
        float u;
        BigInteger expResult;
        // 17 float values between 0.999999f and 1.0f
        l = 0.999999f;
        u = 1.0f;
        expResult = new BigInteger("17");
        BigInteger result = Generic_float.getNumberOfFloatsInRange(l, u);
        assertEquals(expResult, result);
    }
}
