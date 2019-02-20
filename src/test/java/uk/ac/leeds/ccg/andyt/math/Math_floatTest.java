/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.math;

import java.math.BigInteger;
import java.math.RoundingMode;
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
public class Math_floatTest {
    
    public Math_floatTest() {
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
     * Test of isFloat method, of class Math_float.
     */
    @Test
    public void testIsFloat_String() {
        System.out.println("isFloat");
        String s;
        s = "0.1";
        assertTrue(Math_float.isFloat(s));
    }
    
    /**
     * Test of isFloat method, of class Math_float.
     */
    @Test
    public void testIsFloat_3args() {
        System.out.println("isFloat(String,int,RoundingMode)");
        String s;
        int dp;
        RoundingMode rm;
        // Test 1
        s = "0.1";
        dp = 3;
        rm = RoundingMode.HALF_UP;
        assertTrue(Math_float.isFloat(s,dp, rm));
        // Test 2
        s = "0.1";
        dp = 9;
        rm = RoundingMode.HALF_UP;
        assertFalse(Math_float.isFloat(s,dp, rm));
    }
    
    /**
     * Test of isFloatExact method, of class Math_float.
     */
    @Test
    public void testIsFloatExact() {
        System.out.println("isFloatExact");
        String s;
        s = "0.1";
        assertFalse(Math_float.isFloatExact(s));
    }

    /**
     * Test of toPlainString method, of class Math_float.
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
        result = Math_float.toPlainString(f);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 2
        f = 0.1f;
        expResult = "0.1";
        result = Math_float.toPlainString(f);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfFloatsInRange method, of class Math_float.
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
        BigInteger result = Math_float.getNumberOfFloatsInRange(l, u);
        assertEquals(expResult, result);
    }

    /**
     * Test of roundUpToNearestInt method, of class Math_float.
     */
    @Test
    public void testRoundUpToNearestInt() {
        System.out.println("roundUpToNearestInt");
        float f = 0.1F;
        int expResult = 1;
        int result = Math_float.roundUpToNearestInt(f);
        assertEquals(expResult, result);
    }
}
