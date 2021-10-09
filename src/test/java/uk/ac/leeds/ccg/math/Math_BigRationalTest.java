/*
 * Copyright 2021 Centre for Computational Geography, University of Leeds.
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

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for class Math_BigRational
 *
 * @author Andy Turner
 * @verson 1.0
 */
public class Math_BigRationalTest {

    public Math_BigRationalTest() {
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
    public void testRound() {
        Math_BigRational x;
        x = Math_BigRational.valueOf("123.456789");
        Math_BigRational result;
        Math_BigRational expResult;
        int oom;
        // Test 1
        oom = 0;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("123");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        oom = -1;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("123.4");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3
        oom = 1;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("120");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4
        oom = 2;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("100");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5
        oom = -4;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("123.4567");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6
        oom = 20;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("0");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 7
        oom = -7;
        result = Math_BigRational.round(x, oom);
        expResult = Math_BigRational.valueOf("123.4567890");
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of getCommonFactor method, of class Math_BigRational.
     */
    @Test
    public void testGetCommonFactor() {
        System.out.println("getCommonFactor");
        Math_BigRational x = Math_BigRational.valueOf(BigInteger.valueOf(8),
                BigInteger.valueOf(3));
        Math_BigRational y = Math_BigRational.valueOf(BigInteger.valueOf(14),
                BigInteger.valueOf(3));
        Math_BigRational expResult = Math_BigRational.valueOf(BigInteger.valueOf(2),
                BigInteger.valueOf(3));
        Math_BigRational result = Math_BigRational.getCommonFactor(x, y);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of roundToBD method, of class Math_BigRational.
     */
    @Test
    public void testRoundToBD() {
        System.out.println("roundToBD");
        Math_BigRational x;
        int oom;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(3));
        oom = -3;
        expResult = new BigDecimal("0.333");
        result = Math_BigRational.roundToBD(x, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of min method, of class Math_BigRational.
     */
    @Test
    public void testMin_Math_BigRational_Math_BigRational() {
        System.out.println("min");
        Math_BigRational x;
        Math_BigRational y;
        x = Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000000));
        y = Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000001));
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.min(x, y);
        assertTrue(result.compareTo(y) == 0);
    }

    /**
     * Test of max method, of class Math_BigRational.
     */
    @Test
    public void testMax_Math_BigRational() {
        System.out.println("max");
        Math_BigRational x;
        Math_BigRational y;
        x = Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000000));
        y = Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000001));
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.max(x, y);
        assertTrue(result.compareTo(x) == 0);
    }

    /**
     * Test of getX method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testGetX() {
        // No test.
    }

    /**
     * Test of compareTo method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testCompareTo() {
        // No test.
    }

    /**
     * Test of min method, of class Math_BigRational.
     */
    @Test
    public void testMin_Math_BigRational() {
        System.out.println("min");
        Math_BigRational x;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        x = Math_BigRational.valueOf(BigDecimal.ONE);
        instance = Math_BigRational.valueOf(BigDecimal.ZERO);
        expResult = instance;
        result = Math_BigRational.min(x, instance);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of max method, of class Math_BigRational.
     */
    @Test
    public void testMax_Math_BigRational_Math_BigRational() {
        System.out.println("max");
        Math_BigRational x;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        x = Math_BigRational.valueOf(BigDecimal.ONE);
        instance = Math_BigRational.valueOf(BigDecimal.ZERO);
        expResult = x;
        result = instance.max(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of toString method, of class Math_BigRational.
     */
    @Test
    public void testToRationalString() {
        System.out.println("toRationalString");
        Math_BigRational instance = Math_BigRational.valueOf(10, 3);
        String expResult = "3 1/3";
        String result = instance.toIntegerRationalString();
        assertTrue(expResult.equalsIgnoreCase(result));
    }

    /**
     * Test of toFloat method, of class Math_BigRational.
     */
    @Test
    public void testToFloat() {
        System.out.println("toFloat");
        Math_BigRational x = Math_BigRational.valueOf("8.80446261998075791112518174946277"
                + "2084351754080848495898653087767533866267556634");
        float expResult = 8.804462F;
        float result = x.toFloat();
        assertTrue(expResult == result);
    }
    
    /**
     * Test of subtract method, of class Math_BigRational.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Math_BigRational x;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        x = Math_BigRational.ONE;
        instance = Math_BigRational.valueOf(2);
        result = instance.subtract(x);
        expResult = Math_BigRational.ONE;
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_BigDecimal() {
        // No test.
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_BigInteger() {
        // No test.
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_String() {
        System.out.println("of");
        String x;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        x = "1";
        expResult = Math_BigRational.ONE;
        result = Math_BigRational.valueOf(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(Exception.class, () -> {
            Math_BigRational.valueOf("One");
        });
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_double() {
        System.out.println("of");
        double x;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1.
        x = 0.0;
        expResult = Math_BigRational.ZERO;
        result = Math_BigRational.valueOf(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2.
        assertThrows(Exception.class, () -> {
            Math_BigRational.valueOf(Double.NaN);
        });
        assertThrows(Exception.class, () -> {
            Math_BigRational.valueOf(Double.POSITIVE_INFINITY);
        });
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_int() {
        System.out.println("of");
        int x = 0;
        Math_BigRational expResult = Math_BigRational.ZERO;
        Math_BigRational result = Math_BigRational.valueOf(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_long() {
        System.out.println("of");
        long x = 0L;
        Math_BigRational expResult = Math_BigRational.ZERO;
        Math_BigRational result = Math_BigRational.valueOf(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_BigDecimal_BigDecimal() {
        System.out.println("of");
        BigDecimal numerator = BigDecimal.ONE;
        BigDecimal denominator = BigDecimal.ONE;
        Math_BigRational expResult = Math_BigRational.ONE;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(Exception.class, () -> {
            Math_BigRational.valueOf(BigDecimal.ONE, BigDecimal.ZERO);
        });
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_BigInteger_BigInteger() {
        System.out.println("of");
        BigInteger numerator = BigInteger.ONE;
        BigInteger denominator = BigInteger.ONE;
        Math_BigRational expResult = Math_BigRational.ONE;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(Exception.class, () -> {
            Math_BigRational.valueOf(BigInteger.ONE, BigInteger.ZERO);
        });
    }

    /**
     * Test of of method, of class Math_BigRational.
     */
    @Test
    public void testOf_int_int() {
        System.out.println("of");
        int numerator = 0;
        int denominator = 1;
        Math_BigRational expResult = Math_BigRational.ZERO;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRational.
     */
    @Test
    public void testAdd() {
        // No test.
    }

    /**
     * Test of multiply method, of class Math_BigRational.
     */
    @Test
    public void testMultiply() {
        // No test.
    }

    /**
     * Test of divide method, of class Math_BigRational.
     */
    @Test
    public void testDivide_Math_BigRational() {
        // No test.
    }

    /**
     * Test of divide method, of class Math_BigRational.
     */
    @Test
    public void testDivide_int() {
        // No test.
    }

    /**
     * Test of divide method, of class Math_BigRational.
     */
    @Test
    public void testDivide_long() {
        // No test.
    }
}
