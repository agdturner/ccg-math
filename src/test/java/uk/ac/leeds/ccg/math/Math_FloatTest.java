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

import java.math.BigInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_FloatTest {
    
    public Math_FloatTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testIsFloat_String() {
        String funcName = "isFloat";
        System.out.println("Test " + funcName);
        String s;
        boolean result;
        // Test 1
        s = "0.1";
        result = Math_Float.isFloat(s);
        assertTrue(result);
    }
    
    @Test
    public void testIsFloat_String_int() {
        String funcName = "IsFloat_String_int";
        System.out.println("Test " + funcName);
        String s;
        int oom;
        boolean result;
        // Test 1
        s = "0.1";
        oom = -3;
        result = Math_Float.isFloat(s,oom);
        assertTrue(result);
        // Test 2
        s = "0.1";
        oom = -9;
        result = Math_Float.isFloat(s,oom);
        assertFalse(result);
    }
    
    @Test
    public void testIsFloatExact() {
        String funcName = "isFloatExact";
        System.out.println("Test " + funcName);
        String s;
         boolean result;
        // Test 1
        s = "0.1";
        result = Math_Float.isFloatExact(s);
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
        f = 0.0f;
        expResult = "0.0";
        result = Math_Float.toPlainString(f);
        assertEquals(expResult, result);
        // Test 2
        f = 0.1f;
        expResult = "0.1";
        result = Math_Float.toPlainString(f);
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
        l = 0.999999f;
        u = 1.0f;
        expResult = new BigInteger("17");
        BigInteger result = Math_Float.getNumberOfFloatsInRange(l, u);
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
        f = 0.1F;
        expResult = 1;
        result = Math_Float.roundUpToNearestInt(f);
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
