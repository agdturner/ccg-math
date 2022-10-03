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
package uk.ac.leeds.ccg.math.number;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;

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
    public void testToFloat2() {
        BigRational x = BigRational.valueOf("8.804462619980757911125181749462772084351754080848495898653087767533866267556634");
//        System.out.println(x.toRationalString());
//        System.out.println(x.toFloat());
//        System.out.println(Float.valueOf(x.toBigDecimal().toString()));
//        System.out.println(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000").floatValue());
        x = BigRational.valueOf("8.8044626199807579111251817494627720843");
        System.out.println(x.toRationalString());
        System.out.println(x.toFloat());
        System.out.println(Float.valueOf(x.toBigDecimal().toString()));
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
    public void testGetX() {
        // No test.
    }

    /**
     * Test of compareTo method, of class Math_BigRational.
     */
    @Test
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
        float expResult = 8.804463F;
        float result = x.toFloat(-6);
        assertTrue(expResult == result);
        //System.out.println(Float.valueOf(x.toBigDecimal().toString()));
        System.out.println(new BigDecimal("1000000000000000000000000000000000000000000000000000000000000000000000000000000").floatValue());
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

    /**
     * Test of getNumeratorBigInteger method, of class Math_BigRational.
     */
    @Test
    public void testGetNumeratorBigInteger() {
        System.out.println("getNumeratorBigInteger");
        Math_BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = BigInteger.TWO;
        result = instance.getNumeratorBigInteger();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getNumerator method, of class Math_BigRational.
     */
    @Test
    public void testGetNumerator() {
        System.out.println("getNumerator");
        Math_BigRational instance;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = BigDecimal.valueOf(2);
        result = instance.getNumerator();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getDenominatorBigInteger method, of class Math_BigRational.
     */
    @Test
    public void testGetDenominatorBigInteger() {
        System.out.println("getDenominatorBigInteger");
        Math_BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = BigInteger.valueOf(4);
        result = instance.getDenominatorBigInteger();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getDenominator method, of class Math_BigRational.
     */
    @Test
    public void testGetDenominator() {
        System.out.println("getDenominator");
        Math_BigRational instance;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = BigDecimal.valueOf(4);
        result = instance.getDenominator();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reduce method, of class Math_BigRational.
     */
    @Test
    public void testReduce() {
        System.out.println("reduce");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = Math_BigRational.valueOf(1, 2);
        result = instance.reduce();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of integerPart method, of class Math_BigRational.
     */
    @Test
    public void testIntegerPart() {
        System.out.println("integerPart");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2, 4);
        expResult = Math_BigRational.ZERO;
        result = instance.integerPart();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(9, 2);
        expResult = Math_BigRational.valueOf(BigDecimal.valueOf(4));
        result = instance.integerPart();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of fractionPart method, of class Math_BigRational.
     */
    @Test
    public void testFractionPart() {
        System.out.println("fractionPart");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(9, 2);
        expResult = Math_BigRational.valueOf(1, 2);
        result = instance.fractionPart();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of negate method, of class Math_BigRational.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = Math_BigRational.valueOf(-2);
        result = instance.negate();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        expResult = Math_BigRational.valueOf(2);
        result = instance.negate();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reciprocal method, of class Math_BigRational.
     */
    @Test
    public void testReciprocal() {
        System.out.println("reciprocal");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = Math_BigRational.valueOf(1, 2);
        result = instance.reciprocal();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(1, 2);
        expResult = Math_BigRational.valueOf(2);
        result = instance.reciprocal();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of abs method, of class Math_BigRational.
     */
    @Test
    public void testAbs() {
        System.out.println("abs");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = Math_BigRational.valueOf(2);
        result = instance.abs();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        expResult = Math_BigRational.valueOf(2);
        result = instance.abs();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of signum method, of class Math_BigRational.
     */
    @Test
    public void testSignum() {
        System.out.println("signum");
        Math_BigRational instance;
        int expResult;
        int result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = 1;
        result = instance.signum();
        assertEquals(expResult, result);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        expResult = -1;
        result = instance.signum();
        assertEquals(expResult, result);
        // Test 3
        instance = Math_BigRational.valueOf(0);
        expResult = 0;
        result = instance.signum();
        assertEquals(expResult, result);
    }

    /**
     * Test of increment method, of class Math_BigRational.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = Math_BigRational.valueOf(3);
        result = instance.increment();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        expResult = Math_BigRational.valueOf(-1);
        result = instance.increment();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of decrement method, of class Math_BigRational.
     */
    @Test
    public void testDecrement() {
        System.out.println("decrement");
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        expResult = Math_BigRational.valueOf(1);
        result = instance.decrement();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        expResult = Math_BigRational.valueOf(-3);
        result = instance.decrement();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRational.
     */
    @Test
    public void testAdd_Math_BigRational() {
        System.out.println("add");
        Math_BigRational value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(5);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(1);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRational.
     */
    @Test
    public void testAdd_BigInteger() {
        System.out.println("add");
        BigInteger value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(5);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(1);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRational.
     */
    @Test
    public void testAdd_int() {
        System.out.println("add");
        int value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = 3;
        expResult = Math_BigRational.valueOf(5);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = 3;
        expResult = Math_BigRational.valueOf(1);
        result = instance.add(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of subtract method, of class Math_BigRational.
     */
    @Test
    public void testSubtract_Math_BigRational() {
        System.out.println("subtract");
        Math_BigRational value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(-1);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(-5);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of subtract method, of class Math_BigRational.
     */
    @Test
    public void testSubtract_BigInteger() {
        System.out.println("subtract");
        BigInteger value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(-1);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(-5);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of subtract method, of class Math_BigRational.
     */
    @Test
    public void testSubtract_int() {
        System.out.println("subtract");
        int value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = 3;
        expResult = Math_BigRational.valueOf(-1);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = 3;
        expResult = Math_BigRational.valueOf(-5);
        result = instance.subtract(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRational.
     */
    @Test
    public void testMultiply_Math_BigRational() {
        System.out.println("multiply");
        Math_BigRational value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = Math_BigRational.valueOf(3);
        expResult = Math_BigRational.valueOf(-6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRational.
     */
    @Test
    public void testMultiply_BigInteger() {
        System.out.println("multiply");
        BigInteger value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = BigInteger.valueOf(3);
        expResult = Math_BigRational.valueOf(-6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRational.
     */
    @Test
    public void testMultiply_int() {
        System.out.println("multiply");
        int value;
        Math_BigRational instance;
        Math_BigRational expResult;
        Math_BigRational result;
        // Test 1
        instance = Math_BigRational.valueOf(2);
        value = 3;
        expResult = Math_BigRational.valueOf(6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRational.valueOf(-2);
        value = 3;
        expResult = Math_BigRational.valueOf(-6);
        result = instance.multiply(value);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of floor method, of class Math_BigRational.
     */
    @Test
    public void testFloor() {
        System.out.println("floor");
        Math_BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = Math_BigRational.valueOf("0.1");
        expResult = BigInteger.ZERO;
        result = instance.floor();
        assertEquals(expResult, result);
        // Test 2
        instance = Math_BigRational.valueOf("-0.1");
        expResult = BigInteger.valueOf(-1);
        result = instance.floor();
        assertEquals(expResult, result);
    }

    /**
     * Test of floor method, of class Math_BigRational.
     */
    @Test
    public void testCiel() {
        System.out.println("ceil");
        Math_BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = Math_BigRational.valueOf("0.1");
        expResult = BigInteger.ONE;
        result = instance.ceil();
        assertEquals(expResult, result);
        // Test 2
        instance = Math_BigRational.valueOf("-0.1");
        expResult = BigInteger.valueOf(0);
        result = instance.ceil();
        assertEquals(expResult, result);
    }

    /**
     * Test of divide method, of class Math_BigRational.
     */
    @Test
    public void testDivide_BigInteger() {
        System.out.println("divide");
        BigInteger value = BigInteger.ONE;
        Math_BigRational instance = Math_BigRational.ONE;
        Math_BigRational expResult = Math_BigRational.ONE;
        Math_BigRational result = instance.divide(value);
        assertTrue(expResult.equals(result));
        // Test 2
        value = BigInteger.TWO;
        instance = Math_BigRational.ONE;
        expResult = Math_BigRational.valueOf(1, 2);
        result = instance.divide(value);
        assertTrue(expResult.equals(result));
        // Test 3
        value = BigInteger.TWO.negate();
        instance = Math_BigRational.ONE;
        expResult = Math_BigRational.valueOf(-1, 2);
        result = instance.divide(value);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of isZero method, of class Math_BigRational.
     */
    @Test
    public void testIsZero() {
        System.out.println("isZero");
        assertTrue(Math_BigRational.ZERO.isZero());
        assertTrue(Math_BigRational.ZERO.negate().isZero());
        assertFalse(Math_BigRational.ONE.isZero());
        assertFalse(Math_BigRational.ONE.negate().isZero());
    }

    /**
     * Test of isInteger method, of class Math_BigRational.
     */
    @Test
    public void testIsInteger() {
        System.out.println("isInteger");
        assertTrue(Math_BigRational.ZERO.isInteger());
        assertTrue(Math_BigRational.ZERO.negate().isInteger());
        assertTrue(Math_BigRational.ONE.isInteger());
        assertTrue(Math_BigRational.ONE.negate().isInteger());
        assertTrue(Math_BigRational.TWO.isInteger());
        assertTrue(Math_BigRational.TWO.negate().isInteger());
        assertTrue(Math_BigRational.valueOf(4, 2).isInteger());
        assertFalse(Math_BigRational.valueOf(4, 3).isInteger());
    }

    /**
     * Test of pow method, of class Math_BigRational.
     */
    @Test
    public void testPow() {
        System.out.println("pow");
        int exponent = 3;
        Math_BigRational instance = Math_BigRational.TWO;
        Math_BigRational expResult = Math_BigRational.valueOf(8);
        Math_BigRational result = instance.pow(exponent);
        assertTrue(expResult.equals(result));
        // Test 2
        instance = instance.negate();
        result = instance.pow(exponent);
        expResult = Math_BigRational.valueOf(-8);
        assertTrue(expResult.equals(result));
        // Test 3
        exponent = 3;
        instance = Math_BigRational.valueOf(10);
        result = instance.pow(exponent);
        expResult = Math_BigRational.valueOf(1000);
        assertTrue(expResult.equals(result));
        // Test 3
        exponent = -3;
        instance = Math_BigRational.valueOf(10);
        result = instance.pow(exponent);
        expResult = Math_BigRational.valueOf("0.001");
        assertTrue(expResult.equals(result));
        // Test 3
        exponent = 3;
        instance = Math_BigRational.valueOf("0.02");
        result = instance.pow(exponent);
        expResult = instance.multiply(instance).multiply(instance);
        assertTrue(expResult.equals(result));
        // Test 4
        exponent = 3;
        instance = Math_BigRational.valueOf("-0.02");
        result = instance.pow(exponent);
        expResult = instance.multiply(instance).multiply(instance);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of withPrecision method, of class Math_BigRational.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        int precision = -3;
        Math_BigRational instance = Math_BigRational.valueOf(1, 3);
        Math_BigRational expResult = Math_BigRational.valueOf("0.333");
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRational result = instance.round(precision, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        expResult = Math_BigRational.valueOf("0.334");
        rm = RoundingMode.UP;
        result = instance.round(precision, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of toDouble method, of class Math_BigRational.
     */
    @Test
    public void testToDouble() {
        System.out.println("toDouble");
        Math_BigRational instance = Math_BigRational.valueOf(1, 3);
        double expResult = 1.0d / 3.0d;
        double result = instance.toDouble();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toBigDecimal method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testToBigDecimal_int() {
        System.out.println("toBigDecimal");
        int oom = 0;
        Math_BigRational instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.toBigDecimal(oom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toBigDecimal method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testToBigDecimal_MathContext() {
        System.out.println("toBigDecimal");
        MathContext mc = null;
        Math_BigRational instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.toBigDecimal(mc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testHashCode() {
        System.out.println("hashCode");
        Math_BigRational instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Math_BigRational instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testToString() {
        System.out.println("toString");
        Math_BigRational instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPlainString method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testToPlainString() {
        System.out.println("toPlainString");
        Math_BigRational instance = null;
        String expResult = "";
        String result = instance.toPlainString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toIntegerRationalString method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testToIntegerRationalString() {
        System.out.println("toIntegerRationalString");
        Math_BigRational instance = null;
        String expResult = "";
        String result = instance.toIntegerRationalString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testGetStringValue_0args() {
        System.out.println("getStringValue");
        Math_BigRational instance = null;
        String expResult = "";
        String result = instance.getStringValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testGetStringValue_int() {
        System.out.println("getStringValue");
        int n = 0;
        Math_BigRational instance = null;
        String expResult = "";
        String result = instance.getStringValue(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_int() {
        System.out.println("valueOf");
        int value = 0;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_int_int() {
        System.out.println("valueOf");
        int numerator = 0;
        int denominator = 0;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_3args() {
        System.out.println("valueOf");
        int integer = 0;
        int fractionNumerator = 0;
        int fractionDenominator = 0;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(integer, fractionNumerator, fractionDenominator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_BigInteger_BigInteger() {
        System.out.println("valueOf");
        BigInteger numerator = null;
        BigInteger denominator = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_BigInteger() {
        System.out.println("valueOf");
        BigInteger value = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_double() {
        System.out.println("valueOf");
        double value = 0.0;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_BigDecimal() {
        System.out.println("valueOf");
        BigDecimal value = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_String() {
        System.out.println("valueOf");
        String string = "";
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_5args() {
        System.out.println("valueOf");
        boolean positive = false;
        String integerPart = "";
        String fractionPart = "";
        String fractionRepeatPart = "";
        String exponentPart = "";
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(positive, integerPart, fractionPart, fractionRepeatPart, exponentPart);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testValueOf_BigDecimal_BigDecimal() {
        System.out.println("valueOf");
        BigDecimal numerator = null;
        BigDecimal denominator = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.valueOf(numerator, denominator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of min method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testMin_Math_BigRationalArr() {
        System.out.println("min");
        Math_BigRational[] values = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.min(values);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testMax_Math_BigRationalArr() {
        System.out.println("max");
        Math_BigRational[] values = null;
        Math_BigRational expResult = null;
        Math_BigRational result = Math_BigRational.max(values);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testIntValue() {
        System.out.println("intValue");
        Math_BigRational instance = null;
        int expResult = 0;
        int result = instance.intValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testLongValue() {
        System.out.println("longValue");
        Math_BigRational instance = null;
        long expResult = 0L;
        long result = instance.longValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of floatValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testFloatValue() {
        System.out.println("floatValue");
        Math_BigRational instance = null;
        float expResult = 0.0F;
        float result = instance.floatValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doubleValue method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testDoubleValue() {
        System.out.println("doubleValue");
        Math_BigRational instance = null;
        double expResult = 0.0;
        double result = instance.doubleValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ceil method, of class Math_BigRational.
     */
    @Test
    @Disabled
    public void testCeil() {
        System.out.println("ceil");
        Math_BigRational instance = null;
        BigInteger expResult = null;
        BigInteger result = instance.ceil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cos method, of class Math_BigRational.
     */
    @Test
    public void testCos() {
        System.out.println("cos");
        Math_BigInteger bi = new Math_BigInteger();
        int oom = -3;
        Math_BigRational x = Math_BigRational.ZERO;
        Math_BigRational expResult = Math_BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRational result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = Math_BigRational.ONE;
        expResult = Math_BigRational.valueOf(Math.cos(1.0d)).round(oom, rm);
        result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = Math_BigRational.ONE.negate();
        expResult = Math_BigRational.valueOf(Math.cos(1.0d)).round(oom, rm);
        result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = Math_BigRational.valueOf(1, 2);
        expResult = Math_BigRational.valueOf(Math.cos(0.5d)).round(oom, rm);
        result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = Math_BigRational.valueOf(1, 2).negate();
        expResult = Math_BigRational.valueOf(Math.cos(-0.5d)).round(oom, rm);
        result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = Math_BigRational.valueOf(1, 4).negate();
        expResult = Math_BigRational.valueOf(Math.cos(-0.25d)).round(oom, rm);
        result = x.cos(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of cos method, of class Math_BigRational.
     */
    @Test
    public void testSin() {
        System.out.println("sin");
        Math_BigInteger bi = new Math_BigInteger();
        int oom = -3;
        Math_BigRational x = Math_BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRational expResult = Math_BigRational.valueOf(Math.sin(1.0d)).round(oom, rm);
        Math_BigRational result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = Math_BigRational.ZERO;
        expResult = Math_BigRational.valueOf(Math.sin(0.0d)).round(oom, rm);
        result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = Math_BigRational.ONE.negate();
        expResult = Math_BigRational.valueOf(Math.sin(-1.0d)).round(oom, rm);
        result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = Math_BigRational.valueOf(1, 2);
        expResult = Math_BigRational.valueOf(Math.sin(0.5d)).round(oom, rm);
        result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = Math_BigRational.valueOf(-1, 2);
        expResult = Math_BigRational.valueOf(Math.sin(-0.5d)).round(oom, rm);
        result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = Math_BigRational.valueOf(1, 4);
        expResult = Math_BigRational.valueOf(Math.sin(0.25d)).round(oom, rm);
        result = x.sin(bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }
    
    /**
     * Test of cos method, of class Math_BigRational.
     */
    @Test
    public void testAsin() {
        System.out.println("asin");
        int oom = -3;
        Math_BigRational x = Math_BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRational expResult = Math_BigRational.valueOf(Math.asin(1.0d)).round(oom, rm);
        System.out.println(expResult.toString());
        Math_BigRational result = x.asin(oom, rm);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = Math_BigRational.ZERO;
        expResult = Math_BigRational.valueOf(Math.asin(0.0d)).round(oom, rm);
        result = x.asin(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = Math_BigRational.ONE.negate();
        expResult = Math_BigRational.valueOf(Math.asin(-1.0d)).round(oom, rm);
        result = x.asin(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = Math_BigRational.valueOf(1, 2);
        expResult = Math_BigRational.valueOf(Math.asin(0.5d)).round(oom, rm);
        //System.out.println(expResult.toString());
        result = x.asin(oom, rm);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = Math_BigRational.valueOf(-1, 2);
        expResult = Math_BigRational.valueOf(Math.asin(-0.5d)).round(oom, rm);
        //System.out.println(expResult.toString());
        result = x.asin(oom, rm);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
    }
}
