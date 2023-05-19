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
package uk.ac.leeds.ccg.math.arithmetic;

import uk.ac.leeds.ccg.math.arithmetic.Math_Double;
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
public class Math_DoubleTest {

    public Math_DoubleTest() {
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
    public void testIsDouble_String() {
        String funcName = "isDouble";
        System.out.println("Test " + funcName);
        String s;
        boolean result;
        // Test 1
        s = "0.1";
        result = Math_Double.isDouble(s);
        assertTrue(result);
        // Test 2
        s = "";
        result = Math_Double.isDouble(s);
        assertTrue(result);
        // Test 3
        s = "NaN";
        result = Math_Double.isDouble(s);
        assertTrue(result);
        // Test 4
        s = "+Infinity";
        result = Math_Double.isDouble(s);
        assertTrue(result);
        // Test 5
        s = "+Inf";
        result = Math_Double.isDouble(s);
        assertFalse(result);
        // Test 6
        s = "-Infinity";
        result = Math_Double.isDouble(s);
        assertTrue(result);
        // Test 7
        s = "-Inf";
        result = Math_Double.isDouble(s);
        assertFalse(result);

    }

    @Test
    public void testIsDouble_String_int() {
        String funcName = "isDouble(String,int)";
        System.out.println("Test " + funcName);
        String s;
        int oom;
        boolean result;
        // Test 1
        s = "0.1";
        oom = -3;
        result = Math_Double.isDouble(s, oom);
        assertTrue(result);
        // Test 2
        s = "0.1";
        oom = -17;
        result = Math_Double.isDouble(s, oom);
        assertFalse(result);
        // Test 3
        s = "NAN";
        result = Math_Double.isDouble(s, oom);
        assertTrue(result);
        // Test 4
        s = "+Infinity";
        result = Math_Double.isDouble(s, oom);
        assertTrue(result);
        // Test 5
        s = "-Infinity";
        result = Math_Double.isDouble(s, oom);
        assertTrue(result);
    }

    @Test
    public void testIsDoubleExact() {
        String funcName = "isDoubleExact";
        System.out.println("Test " + funcName);
        String s;
        boolean result;
        // Test 1
        s = "0.1";
        result = Math_Double.isDoubleExact(s);
        assertFalse(result);
        // Test 2
        s = "NAN";
        result = Math_Double.isDoubleExact(s);
        assertTrue(result);
        // Test 3
        s = "+Infinity";
        result = Math_Double.isDoubleExact(s);
        assertTrue(result);
        // Test 4
        s = "-Infinity";
        result = Math_Double.isDoubleExact(s);
        assertTrue(result);
    }

    @Test
    public void testToPlainString() {
        String funcName = "toPlainString";
        System.out.println("Test " + funcName);
        double d;
        String expResult;
        String result;
        // Test 1
        int test = 1;
        d = 0.0d;
        expResult = "0";
        result = Math_Double.toPlainString(d);
        assertEquals(expResult, result);
        // Test 2
        test++;
        d = 0.1d;
        expResult = "0.1000000000000000055511151231257827021181583404541015625";
        result = Math_Double.toPlainString(d);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNumberOfDoublesInRange() {
        String funcName = "getNumberOfDoublesInRange";
        System.out.println("Test " + funcName);
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
        // Test 1
        l = 0.999999999999999;
        u = 1.0;
        expResult = new BigInteger("9");
        // 858993460 double values between 1000000.2 and 1000000.3
        BigInteger result = Math_Double.getNumberOfDoublesInRange(l, u);
        assertEquals(expResult, result);
    }

    @Test
    public void testRoundUpToNearestInt() {
        String funcName = "roundUpToNearestInt";
        System.out.println("Test " + funcName);
        double x;
        int expResult;
        int result;
        // Test 1
        x = 0.1;
        expResult = 1;
        result = Math_Double.roundUpToNearestInt(x);
        assertEquals(expResult, result);
    }

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

    @Test
    public void testEquals() {
        String funcName = "testEquals";
        System.out.println("Test " + funcName);
        double d0 = 0d;
        double d1 = 0d;
        double epsilon = 0d;
        // Test 1
        assertTrue(Math_Double.equals(d0, d1, epsilon));
        // Test 2        
        epsilon = 1d/10000000d;
        double ediv2 = epsilon / 2d;
        d0 = 0d;
        d1 = ediv2;
        assertTrue(Math_Double.equals(d0, d1, epsilon));
        // Test 3
        d1 = -ediv2;
        assertTrue(Math_Double.equals(d0, d1, epsilon));
        // Test 4
        d1 = epsilon;
        assertTrue(Math_Double.equals(d0, d1, epsilon));
        // Test 5
        d1 = epsilon * 2d;
        assertFalse(Math_Double.equals(d0, d1, epsilon));
    }
}
