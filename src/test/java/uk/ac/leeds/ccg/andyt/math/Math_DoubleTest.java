/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.math;

import java.math.BigInteger;
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
public class Math_DoubleTest {

    public Math_DoubleTest() {
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
     * Test of isDouble method, of class Math_Double.
     */
    @Test
    public void testIsDouble_String() {
        System.out.println("isDouble");
        String s;
        s = "0.1";
        assertTrue(Math_Double.isDouble(s));
        s = "";
        assertTrue(Math_Double.isDouble(s));
        s = "NaN";
        assertTrue(Math_Double.isDouble(s));
        s = "+Infinity";
        assertTrue(Math_Double.isDouble(s));
        s = "+Inf";
        assertFalse(Math_Double.isDouble(s));
        s = "-Infinity";
        assertTrue(Math_Double.isDouble(s));
        s = "-Inf";
        assertFalse(Math_Double.isDouble(s));
    }
    
    /**
     * Test of isDouble method, of class Math_Double.
     */
    @Test
    public void testIsDouble_String_int() {
        System.out.println("isDouble(String,int)");
        String s;
        int dp;
        // Test 1
        s = "0.1";
        dp = 3;
        assertTrue(Math_Double.isDouble(s,dp));
        // Test 2
        s = "0.1";
        dp = 17;
        assertFalse(Math_Double.isDouble(s,dp));
        // Test 3
        s = "NAN";
        assertTrue(Math_Double.isDouble(s,dp));
        // Test 3
        s = "+Infinity";
        assertTrue(Math_Double.isDouble(s,dp));
        // Test 4
        s = "-Infinity";
        assertTrue(Math_Double.isDouble(s,dp));
    }
    /**
     * Test of isDoubleExact method, of class Math_Double.
     */
    @Test
    public void testIsDoubleExact() {
        System.out.println("isDoubleExact");
        String s;
        // Test 1
        s = "0.1";
        assertFalse(Math_Double.isDoubleExact(s));
        // Test 2
        s = "NAN";
        assertTrue(Math_Double.isDoubleExact(s));
        // Test 3
        s = "+Infinity";
        assertTrue(Math_Double.isDoubleExact(s));
        // Test 4
        s = "-Infinity";
        assertTrue(Math_Double.isDoubleExact(s));
    }

    /**
     * Test of toPlainString method, of class Math_Double.
     */
    @Test
    public void testToPlainString() {
        System.out.println("toPlainString");
        double d;
        String expResult;
        String result;
        // Test 1
        d = 0.0d;
        expResult = "0";
        result = Math_Double.toPlainString(d);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 2
        d = 0.1d;
        expResult = "0.1000000000000000055511151231257827021181583404541015625";
        result = Math_Double.toPlainString(d);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfDoublesInRange method, of class Math_Double.
     */
    @Test
    public void testGetNumberOfDoublesInRange() {
        System.out.println("getNumberOfDoublesInRange");
        double l;
        double u;
        BigInteger expResult;
        // 7205759404 double values between 0.0999999 and 0.1
//        l = 0.0999999;
//        u = 0.1;
//        expResult = new BigInteger("7205759404");
        // 3602879702 double values between 0.1999999 and 0.2
        // 1801439851 double values between 0.2999999 and 0.3
        // 900719925 double values between 0.5999999 and 0.6
        // 900719926 double values between 0.9999998 and 0.9999999
        // 900719925 double values between 0.9999999 and 1.0
        // 9 double values between 0.999999999999999 and 1.0
        l = 0.999999999999999;
        u = 1.0;
        expResult = new BigInteger("9");
        // 858993460 double values between 1000000.2 and 1000000.3
        BigInteger result = Math_Double.getNumberOfDoublesInRange(l, u);
        assertEquals(expResult, result);
    }

    /**
     * Test of roundUpToNearestInt method, of class Math_Double.
     */
    @Test
    public void testRoundUpToNearestInt() {
        System.out.println("roundUpToNearestInt");
        double v = 0.1;
        int expResult = 1;
        int result = Math_Double.roundUpToNearestInt(v);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseDouble method, of class Math_Double.
     */
    @Test
    public void testParseDouble() {
        System.out.println("parseDouble");
        String s;
        double expResult;
        double result;
        // Test 1
        s = "";
        expResult = Double.NaN;
        result = Math_Double.parseDouble(s);
        assertEquals(expResult, result, 0.0d);
        // Test 2
        s = "-Infinity";
        expResult = Double.NEGATIVE_INFINITY;
        result = Math_Double.parseDouble(s);
        assertEquals(expResult, result, 0.0d);
        // Test 3
        s = "+Infinity";
        expResult = Double.POSITIVE_INFINITY;
        result = Math_Double.parseDouble(s);
        assertEquals(expResult, result, 0.0d);
    }

}
