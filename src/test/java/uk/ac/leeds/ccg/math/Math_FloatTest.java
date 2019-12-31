/*
 * Copyright 2019 Centre for Computational Geography, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.math;

import uk.ac.leeds.ccg.math.Math_Double;
import uk.ac.leeds.ccg.math.Math_Float;
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
public class Math_FloatTest  extends Math_Test {
    
    public Math_FloatTest() {
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
    public void testIsFloat_String() {
        String funcName = "isFloat";
        System.out.println("Test " + funcName);
        String s;
        boolean result;
        // Test 1
        int test = 1;
        s = "0.1";
        result = Math_Float.isFloat(s);
        printFunctionTest(funcName, test, s, result);
        assertTrue(result);
    }
    
    @Test
    public void testIsFloat_String_int() {
        String funcName = "IsFloat_String_int";
        System.out.println("Test " + funcName);
        String s;
        int dp;
        boolean result;
        // Test 1
        int test = 1;
        s = "0.1";
        dp = 3;
        result = Math_Float.isFloat(s,dp);
        printFunctionTest(funcName, test, s, result);
        assertTrue(result);
        // Test 2
        test ++;
        s = "0.1";
        dp = 9;
        result = Math_Float.isFloat(s,dp);
        printFunctionTest(funcName, test, s, result);
        assertFalse(result);
    }
    
    @Test
    public void testIsFloatExact() {
        String funcName = "isFloatExact";
        System.out.println("Test " + funcName);
        String s;
         boolean result;
        // Test 1
        int test = 1;
        s = "0.1";
        result = Math_Float.isFloatExact(s);
        printFunctionTest(funcName, test, s, result);
        assertFalse(result);
    }

    @Test
    public void testToPlainString() {
        String funcName = "toPlainString";
        System.out.println("Test " + funcName);
        float f;
        String expResult;
        String result;
        // Test 1
        int test = 1;
        f = 0.0f;
        expResult = "0.0";
        result = Math_Float.toPlainString(f);
        printFunctionTest(funcName, test, result, true);
        assertEquals(expResult, result);
        // Test 2
        test ++;
        f = 0.1f;
        expResult = "0.1";
        result = Math_Float.toPlainString(f);
        printFunctionTest(funcName, test, result, true);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfFloatsInRange method, of class Math_Float.
     */
    @Test
    public void testGetNumberOfFloatsInRange() {
        String funcName = "getNumberOfFloatsInRange";
        System.out.println("Test " + funcName);
        float l;
        float u;
        BigInteger expResult;
        // Test 1: 17 float values between 0.999999f and 1.0f
        int test = 1;
        l = 0.999999f;
        u = 1.0f;
        expResult = new BigInteger("17");
        BigInteger result = Math_Float.getNumberOfFloatsInRange(l, u);
        printFunctionTest(funcName, test, l, u, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of roundUpToNearestInt method, of class Math_Float.
     */
    @Test
    public void testRoundUpToNearestInt() {
        String funcName = "roundUpToNearestInt";
        System.out.println("Test " + funcName);
        float f;
        int expResult;
        int result;
        // Test 1
        int test = 1;
        f = 0.1F;
        expResult = 1;
        result = Math_Float.roundUpToNearestInt(f);
        printFunctionTest(funcName, test, f, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseFloat method, of class Math_Float.
     */
    @Test
    public void testParseDouble() {
        String funcName = "parseDouble";
        System.out.println("Test " + funcName);
        String s;
        double expResult;
        double result;
        // Test 1
        int test = 1;
        s = "";
        expResult = Double.NaN;
        result = Math_Double.parseDouble(s);
        printFunctionTest(funcName, test, s, result);
        assertEquals(expResult, result, 0.0d);
        // Test 2
        test ++;
        s = "-Infinity";
        expResult = Double.NEGATIVE_INFINITY;
        result = Math_Double.parseDouble(s);
        printFunctionTest(funcName, test, s, result);
        assertEquals(expResult, result, 0.0d);
        // Test 3
        test ++;
        s = "+Infinity";
        expResult = Double.POSITIVE_INFINITY;
        result = Math_Double.parseDouble(s);
        printFunctionTest(funcName, test, s, result);
        assertEquals(expResult, result, 0.0d);
    }

}
