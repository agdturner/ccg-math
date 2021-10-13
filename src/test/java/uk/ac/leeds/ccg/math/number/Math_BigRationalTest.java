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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
}
