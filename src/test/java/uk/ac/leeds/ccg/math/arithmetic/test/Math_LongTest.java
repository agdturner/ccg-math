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
package uk.ac.leeds.ccg.math.arithmetic.test;

import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;
import uk.ac.leeds.ccg.math.arithmetic.Math_Long;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;
import uk.ac.leeds.ccg.math.arithmetic.Math_Long;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.1
 */
public class Math_LongTest {

    public Math_LongTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testIsLong() {
        String funcName = "isLong";
        System.out.println("Test " + funcName);
        long x;
        String s;
        boolean result;
        // Test 1
        x = Long.MIN_VALUE;
        s = Long.toString(x);
        result = Math_Long.isLong(s);
        assertFalse(result);
        // Test 2
        x += 1;
        s = Long.toString(x);
        result = Math_Long.isLong(s);
        assertTrue(result);
        // Test 3
        BigInteger bi = Math_BigInteger.LONG_MAX_VALUE.add(BigInteger.ONE);
        s = bi.toString();
        result = Math_Long.isLong(s);
        assertFalse(result);
    }

    @Test
    public void testIsEven() {
        String funcName = "isEven";
        System.out.println("Test " + funcName);
        long x;
        boolean expResult;
        boolean result;
        // Test 1
        x = 1L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 2
        x = 2L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 3
        x = 3L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 4
        x = 4L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 5
        x = 5L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 6
        x = 6L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 7
        x = 7L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 8
        x = 8L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 9
        x = 9L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 10
        x = 0L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 11
        x = -1L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 12
        x = -2L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 13
        x = -3L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 14
        x = -4L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 15
        x = -5L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 16
        x = -6L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 17
        x = -7L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 18
        x = -8L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 19
        x = -9L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 20
        x = 123456789L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 21
        x = 12345678L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 22
        x = -123456789L;
        expResult = false;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
        // Test 23
        x = -12345678L;
        expResult = true;
        result = Math_Long.isEven(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseLong method, of class Math_Long.
     */
    @Test
    public void testParseLong() {
        System.out.println("parseLong");
        String s = "123456789";
        long expResult = 123456789L;
        long result = Math_Long.parseLong(s);
        assertTrue(expResult == result);
        // Test2
        assertThrows(NumberFormatException.class, () -> {
            BigInteger v = Math_BigInteger.LONG_MAX_VALUE.add(BigInteger.ONE);
            Math_Long.parseLong(v.toString());
        });
    }

    /**
     * Test of add method, of class Math_Long.
     */
    @Test
    public void testAdd_long_long() {
        System.out.println("add");
        long x = 123456789L;
        long y = 1L;
        long expResult = 123456790L;
        long result = Math_Long.add(x, y);
        assertTrue(expResult == result);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.add(Long.MAX_VALUE, 1L);
        });
    }

    /**
     * Test of add method, of class Math_Long.
     */
    @Test
    public void testAdd_long_int() {
        System.out.println("add");
        long x = 123456789L;
        int y = 1;
        long expResult = 123456790L;
        long result = Math_Long.add(x, y);
        assertTrue(expResult == result);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.add(Long.MAX_VALUE, 1);
        });
    }

    /**
     * Test of subtract method, of class Math_Long.
     */
    @Test
    public void testSubtract_long_long() {
        System.out.println("subtract");
        long x = 123456789L;
        long y = 1L;
        long expResult = 123456788L;
        long result = Math_Long.subtract(x, y);
        assertTrue(expResult == result);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.subtract(-Long.MAX_VALUE, 2L);
        });
    }

    /**
     * Test of subtract method, of class Math_Long.
     */
    @Test
    public void testSubtract_long_int() {
        System.out.println("subtract");
        long x = 123456789L;
        int y = 1;
        long expResult = 123456788L;
        long result = Math_Long.subtract(x, y);
        assertTrue(expResult == result);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.subtract(-Long.MAX_VALUE, 2);
        });
    }

    /**
     * Test of multiply method, of class Math_Long.
     */
    @Test
    public void testMultiply_long_long() {
        System.out.println("multiply");
        // Test
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.multiply(Long.MAX_VALUE, 2L);
        });
    }

    /**
     * Test of multiply method, of class Math_Long.
     */
    @Test
    public void testMultiply_long_int() {
        System.out.println("multiply");
        // Test
        assertThrows(ArithmeticException.class, () -> {
            Math_Long.multiply(Long.MAX_VALUE, 2);
        });
    }

    /**
     * Test of factors method, of class Math_Long.
     */
    @Test
    public void testGetPrimeFactors() {
        System.out.println("getPrimeFactors");
        long n = 123456789L;
        TreeMap<Long, Long> expResult = new TreeMap<>();
        expResult.put(3L, 2L);
        expResult.put(3607L, 1L);
        expResult.put(3803L, 1L);
        TreeMap<Long, Long> result = Math_Long.getPrimeFactors(n);
        assertTrue(result.size() == expResult.size());
        for (Long v : result.keySet()) {
            assertTrue(expResult.get(v).compareTo(result.get(v)) == 0);
        }
    }
}
