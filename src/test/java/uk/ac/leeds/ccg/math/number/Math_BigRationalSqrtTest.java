/*
 * Copyright 2020 Centre for Computational Geography, University of Leeds.
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
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.1
 */
public class Math_BigRationalSqrtTest {

    public Math_BigRationalSqrtTest() {
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

    /**
     * Test of initSqrt method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetSqrt_0args() {
        System.out.println("getSqrt");
        BigRational x;
        int oomi = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational result;
        BigRational expResult;
        // Test 1
        x = BigRational.valueOf(2);
        result = new Math_BigRationalSqrt(x, oomi, rm).getSqrt();
        assertNull(result);
        // Test 2
        x = BigRational.valueOf(4);
        expResult = BigRational.valueOf(2);
        result = new Math_BigRationalSqrt(x, oomi, rm).getSqrt();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMultiply_BigRational() {
        System.out.println("multiply");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt x = new Math_BigRationalSqrt(
                BigRational.valueOf(2).divide(
                        BigRational.valueOf(1)), oom, rm);
        BigRational y = BigRational.valueOf(2).divide(
                BigRational.valueOf(1));
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(
                BigRational.valueOf(8), oom, rm);
        Math_BigRationalSqrt result = x.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 3
        x = new Math_BigRationalSqrt(
                BigRational.valueOf(8).divide(BigRational.valueOf(1)),
                oom, rm);
        y = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(32), oom,
                rm);
        result = x.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 5
        x = new Math_BigRationalSqrt(
                BigRational.valueOf(10).divide(
                        BigRational.valueOf(3)), oom, rm);
        y = BigRational.valueOf(15).divide(BigRational.valueOf(4));
        expResult = new Math_BigRationalSqrt(y.pow(2).multiply(x.x), oom, rm);
        result = x.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 6
        x = new Math_BigRationalSqrt(
                BigRational.valueOf(10).divide(
                        BigRational.valueOf(3)), oom, rm);
        y = BigRational.valueOf(-15).divide(BigRational.valueOf(4));
        expResult = new Math_BigRationalSqrt(y.pow(2).multiply(x.x), oom, rm,
                true);
        result = x.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getNumeratorAndDenominator method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetNumeratorAndDenominator() {
        System.out.println("getNumeratorAndDenominator");
        BigRational x = BigRational.valueOf(4);
        int oomi = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigInteger[] expResult = new BigInteger[2];
        expResult[0] = BigInteger.valueOf(4);
        expResult[1] = BigInteger.valueOf(1);
        BigInteger[] result = new Math_BigRationalSqrt(x, oomi, rm).getNumeratorAndDenominator();
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i].compareTo(expResult[i]) == 0);
        }
    }

    /**
     * Test of toString method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        int oomi = -2;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt instance = new Math_BigRationalSqrt(2L, oomi, rm);
        String expResult = "Math_BigRationalSqrt(x=2, sqrtxapprox=1.41, oom=-2)";
        String result = instance.toString();
        assertTrue(expResult.equalsIgnoreCase(result));
        // Test 2
        instance.toBigDecimal(-3, rm);
        expResult = "Math_BigRationalSqrt(x=2, sqrtxapprox=1.414, oom=-3)";
        result = instance.toString();
        assertTrue(expResult.equalsIgnoreCase(result));
    }

    /**
     * Test of toBigDecimal method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testToBigDecimal() {
        System.out.println("toBigDecimal");
        int oomi = -1;
        int oom = -2;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt instance = new Math_BigRationalSqrt(2, oomi, rm);
        BigDecimal expResult = new BigDecimal("1.41");
        BigDecimal result = instance.toBigDecimal(oom, rm);
        assertEquals(expResult, result);
        // Test 2
        oom = -2;
        instance = new Math_BigRationalSqrt(16L, oomi, rm);
        expResult = new BigDecimal("4");
        result = instance.toBigDecimal(oom, rm);
        assertEquals(expResult, result);
        // Test 3
        oom = -2;
        instance = new Math_BigRationalSqrt(256L, oomi, rm);
        expResult = new BigDecimal("16");
        result = instance.toBigDecimal(oom, rm);
        assertEquals(expResult, result);
        // Test 4
        oom = 0;
        instance = new Math_BigRationalSqrt(257L, oomi, rm);
        expResult = new BigDecimal("16");
        result = instance.toBigDecimal(oom, rm);
        assertEquals(expResult, result);
        // Test 5
        oom = 1;
        instance = new Math_BigRationalSqrt(257L, oomi, rm);
        expResult = new BigDecimal("20");
        result = instance.toBigDecimal(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -1;
        instance = new Math_BigRationalSqrt(257L, oomi, rm);
        expResult = new BigDecimal("16.0");
        result = instance.toBigDecimal(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -20;
        instance = new Math_BigRationalSqrt(257L, oomi, rm);
        expResult = new BigDecimal("16.03121954188139736487");
        result = instance.toBigDecimal(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -20;
        instance = new Math_BigRationalSqrt(256L * 256L + 1L, oomi, rm);
        expResult = new BigDecimal("256.00195311754947624595");
        result = instance.toBigDecimal(oom, rm);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -20;
        instance = new Math_BigRationalSqrt(new BigInteger("4294967297"), oomi,
                rm);
        expResult = new BigDecimal("65536.00000762939453080591");
        result = instance.toBigDecimal(oom, rm);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -20;
        instance = new Math_BigRationalSqrt(
                new BigInteger("18446744073709551617"), oomi, rm);
        expResult = new BigDecimal("4294967296.00000000011641532183");
        result = instance.toBigDecimal(oom, rm);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = -11;
        instance = new Math_BigRationalSqrt(
                new BigInteger("18446744073709551617"), oomi, rm);
        expResult = new BigDecimal("4294967296.00000000012");
        result = instance.toBigDecimal(oom, rm);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAdd_Math_BigRationalSqrt() {
        System.out.println("add");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt x = new Math_BigRationalSqrt(8L, oom, rm);
        Math_BigRationalSqrt y = new Math_BigRationalSqrt(2L, oom, rm);
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(18L, oom, rm);
        Math_BigRationalSqrt result = x.add(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 2
        x = new Math_BigRationalSqrt(16L, oom, rm);
        y = new Math_BigRationalSqrt(4L, oom, rm);
        expResult = new Math_BigRationalSqrt(36L, oom, rm);
        result = x.add(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 3
        x = new Math_BigRationalSqrt(3L, oom, rm);
        y = new Math_BigRationalSqrt(4L, oom, rm);
        result = x.add(y, oom, rm);
        assertNull(result);
        // Test 4
        x = new Math_BigRationalSqrt(4L, oom, rm);
        y = new Math_BigRationalSqrt(16L, oom, rm);
        expResult = new Math_BigRationalSqrt(36L, oom, rm);
        result = x.add(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 5
        x = new Math_BigRationalSqrt(2L, oom, rm);
        y = new Math_BigRationalSqrt(8L, oom, rm);
        expResult = new Math_BigRationalSqrt(18, oom, rm);
        result = x.add(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 6
        x = new Math_BigRationalSqrt(3L, oom, rm);
        y = new Math_BigRationalSqrt(8L, oom, rm);
        assertNull(x.add(y, oom, rm));
    }

    /**
     * Test of equals method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object o = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        assertTrue(instance.equals(o));
        // Test 2
        instance = Math_BigRationalSqrt.ZERO;
        assertFalse(instance.equals(o));
    }

    /**
     * Test of hashCode method, of class Math_BigRationalSqrt.
     */
    @Test
    @Disabled
    public void testHashCode() {
        System.out.println("hashCode");
        // No test.
    }

    /**
     * Test of equals method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testEquals_Math_BigRationalSqrt() {
        System.out.println("equals");
        Math_BigRationalSqrt x = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        assertTrue(instance.equals(x));
        // Test 2
        instance = Math_BigRationalSqrt.ZERO;
        assertFalse(instance.equals(x));
    }

    /**
     * Test of compareTo method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Math_BigRationalSqrt o = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        assertTrue(instance.compareTo(o) == 0);
        // Test 2
        instance = Math_BigRationalSqrt.ZERO;
        assertTrue(instance.compareTo(o) == -1);
        // Test 3
        instance = Math_BigRationalSqrt.ZERO;
        assertTrue(o.compareTo(instance) == 1);
    }

    /**
     * Test of getX method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetX() {
        // No Test
        System.out.println("getX");
        Math_BigRationalSqrt instance = new Math_BigRationalSqrt(1, 0);
        BigRational expResult = BigRational.ONE;
        BigRational result = instance.getX();
        assertEquals(expResult, result);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAdd_BigRational() {
        System.out.println("add");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational y = BigRational.valueOf(BigDecimal.ONE);
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(4L, oom, rm);
        Math_BigRationalSqrt result = instance.add(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = new Math_BigRationalSqrt(4L, oom, rm);
        expResult = new Math_BigRationalSqrt(9L, oom, rm);
        result = instance.add(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        y = BigRational.valueOf(BigDecimal.TEN);
        instance = new Math_BigRationalSqrt(4L, oom, rm);
        expResult = new Math_BigRationalSqrt(144L, oom, rm);
        result = instance.add(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = BigRational.valueOf(BigDecimal.TEN.negate());
        instance = new Math_BigRationalSqrt(4L, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(64),
                BigRational.valueOf(-8));
        result = instance.add(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMultiply_Math_BigRationalSqrt() {
        System.out.println("multiply");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt y = Math_BigRationalSqrt.ZERO;
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ZERO;
        Math_BigRationalSqrt result = instance.multiply(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRationalSqrt.ONE;
        expResult = Math_BigRationalSqrt.ZERO;
        result = instance.multiply(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        BigRational aob = BigRational.valueOf(2).divide(
                BigRational.valueOf(1));
        BigRational cod = BigRational.valueOf(2).divide(
                BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(4), oom, rm);
        result = instance.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 4
        aob = BigRational.valueOf(8).divide(BigRational.valueOf(1));
        cod = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(16), oom, rm);
        result = instance.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 5
        aob = BigRational.valueOf(12).divide(BigRational.valueOf(1));
        cod = BigRational.valueOf(3).divide(BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(36), oom, rm);
        result = instance.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 6
        aob = BigRational.valueOf(10).divide(BigRational.valueOf(3));
        cod = BigRational.valueOf(15).divide(BigRational.valueOf(4));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(25).divide(2), oom, rm);
        result = instance.multiply(y, oom, rm);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of divide method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testDivide_BigRational() {
        System.out.println("divide");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational y = BigRational.ONE;
        Math_BigRationalSqrt x = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt result = x.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_BigRationalSqrt.ONE.divide(BigRational.ZERO, oom, rm);
        });
        // Test 3
        y = BigRational.ONE;
        x = Math_BigRationalSqrt.ZERO;
        expResult = Math_BigRationalSqrt.ZERO;
        result = x.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = BigRational.valueOf(2);
        x = new Math_BigRationalSqrt(2L, oom, rm);
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom, rm);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        y = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom, rm);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 6
        y = BigRational.valueOf(8).divide(BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom,
                rm);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 7
        y = BigRational.valueOf(-8).divide(BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom, rm,
                true);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 8
        y = BigRational.valueOf(1).divide(BigRational.valueOf(3));
        x = new Math_BigRationalSqrt(
                BigRational.valueOf(3).divide(BigRational.valueOf(1)),
                oom, rm);
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom, rm);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 9
        y = BigRational.valueOf(-1).divide(BigRational.valueOf(3));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oom, rm,
                true);
        result = x.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of divide method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testDivide_Math_BigRationalSqrt() {
        System.out.println("divide");
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt y = new Math_BigRationalSqrt(BigRational.ONE,
                oom, rm);
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt result = instance.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_BigRationalSqrt.ONE.divide(BigRational.ZERO, oom, rm);
        });
        // Test 3
        y = new Math_BigRationalSqrt(BigRational.ONE, oom, rm);
        instance = Math_BigRationalSqrt.ZERO;
        expResult = Math_BigRationalSqrt.ZERO;
        result = instance.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = new Math_BigRationalSqrt(2L, oom);
        instance = new Math_BigRationalSqrt(2L, oom);
        expResult = Math_BigRationalSqrt.ONE;
        result = instance.divide(y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        BigRational aob = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        BigRational cod = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = Math_BigRationalSqrt.ONE;
        result = instance.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 6
        aob = BigRational.valueOf(8).divide(BigRational.valueOf(1));
        cod = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf("0.25"), oom, rm);
        result = instance.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 7
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(4), oom, rm);
        result = y.divide(instance, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 8
        cod = BigRational.valueOf(2).divide(BigRational.valueOf(1));
        assertThrows(Exception.class, () -> {
            new Math_BigRationalSqrt(BigRational.valueOf(-8).divide(
                    BigRational.valueOf(1)), -1, rm);
        });
        // Test 9
        aob = BigRational.valueOf(1).divide(BigRational.valueOf(3));
        cod = BigRational.valueOf(3).divide(BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oom, rm);
        instance = new Math_BigRationalSqrt(cod, oom, rm);
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(9), oom, rm);
        result = instance.divide(y, oom, rm);
        assertTrue(expResult.equals(result));
        // Test 10
        expResult = new Math_BigRationalSqrt(BigRational.valueOf(1).divide(9), oom, rm);
        result = y.divide(instance, oom, rm);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getOOM method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetOOM() {
        System.out.println("getOOM");
        BigRational v = BigRational.valueOf(BigInteger.ONE, BigInteger.valueOf(3));
        int oom = -10;
        int expResult = -18;
        int result = Math_BigRationalSqrt.getOOM(v, oom);
        assertTrue(expResult == result);
        //BigDecimal sqrt0 = new Math_BigRationalSqrt(v).toBigDecimal(result);
        // Test 2
        oom = -5;
        expResult = -8;
        result = Math_BigRationalSqrt.getOOM(v, oom);
        assertTrue(expResult == result);
        //BigDecimal sqrt1 = new Math_BigRationalSqrt(v).toBigDecimal(result);
        // Test 3
        v = BigRational.valueOf(BigInteger.ONE, BigInteger.valueOf(3)).divide(100);
        oom = -5;
        expResult = -8;
        result = Math_BigRationalSqrt.getOOM(v, oom);
        assertTrue(expResult == result);
        //BigDecimal sqrt2 = new Math_BigRationalSqrt(v).toBigDecimal(result);
        // Test 3
        v = BigRational.valueOf(BigInteger.valueOf(33333), BigInteger.valueOf(5));
        oom = -5;
        expResult = -8;
        result = Math_BigRationalSqrt.getOOM(v, oom);
        assertTrue(expResult == result);
        //BigDecimal sqrt3 = new Math_BigRationalSqrt(v).toBigDecimal(result);
    }

    /**
     * Test of min method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMin_Math_BigRationalSqrtArr() {
        System.out.println("min");
        int oomi;
        Math_BigRationalSqrt[] x;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        oomi = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        x = new Math_BigRationalSqrt[2];
        x[0] = new Math_BigRationalSqrt(1, oomi, rm);
        x[1] = new Math_BigRationalSqrt(2, oomi, rm);
        expResult = x[0];
        result = Math_BigRationalSqrt.min(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x[1] = new Math_BigRationalSqrt(2, oomi, rm, true);
        expResult = x[1];
        result = Math_BigRationalSqrt.min(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of min method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMin_Collection() {
        System.out.println("min");
        Collection<Math_BigRationalSqrt> c;
        int oomi;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        oomi = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        c = new HashSet<>();
        c.add(new Math_BigRationalSqrt(1, oomi, rm));
        c.add(new Math_BigRationalSqrt(2, oomi, rm));
        expResult = new Math_BigRationalSqrt(1, oomi, rm);
        result = Math_BigRationalSqrt.min(c);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        c.add(new Math_BigRationalSqrt(2, oomi, rm, true));
        expResult = new Math_BigRationalSqrt(2, oomi, rm, true);
        result = Math_BigRationalSqrt.min(c);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of max method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMax_Math_BigRationalSqrtArr() {
        System.out.println("max");
        int oomi;
        Math_BigRationalSqrt[] x;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        oomi = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        x = new Math_BigRationalSqrt[2];
        x[0] = new Math_BigRationalSqrt(1, oomi, rm);
        x[1] = new Math_BigRationalSqrt(2, oomi, rm);
        expResult = x[1];
        result = Math_BigRationalSqrt.max(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x[1] = new Math_BigRationalSqrt(2, oomi, rm, true);
        expResult = x[0];
        result = Math_BigRationalSqrt.max(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of max method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMax_Collection() {
        System.out.println("max");
        Collection<Math_BigRationalSqrt> c;
        int oomi;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        oomi = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        c = new HashSet<>();
        c.add(new Math_BigRationalSqrt(1, oomi, rm));
        c.add(new Math_BigRationalSqrt(2, oomi, rm));
        expResult = new Math_BigRationalSqrt(2, oomi, rm);
        result = Math_BigRationalSqrt.max(c);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        c.add(new Math_BigRationalSqrt(2, oomi, rm, true));
        expResult = new Math_BigRationalSqrt(2, oomi, rm);
        result = Math_BigRationalSqrt.max(c);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getSqrt method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetSqrt_int() {
        System.out.println("getSqrt");
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt instance;
        BigRational expResult;
        BigRational result;
        // Test 1
        instance = new Math_BigRationalSqrt(1L, oom, rm);
        expResult = BigRational.ONE;
        result = instance.getSqrt(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -1;
        instance = new Math_BigRationalSqrt(2L, oom, rm);
        expResult = BigRational.valueOf("1.4");
        result = instance.getSqrt(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAdd_Math_BigRational() {
        System.out.println("add");
        BigRational y;
        Math_BigRationalSqrt instance;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        oom = -1;
        y = BigRational.ONE;
        instance = new Math_BigRationalSqrt(1L, oom, rm);
        result = instance.add(y, oom, rm);
        expResult = new Math_BigRationalSqrt(4L, 2L);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of negate method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigRationalSqrt instance;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        oom = -1;
        instance = new Math_BigRationalSqrt(1L, oom, rm);
        expResult = new Math_BigRationalSqrt(1L, -1L);
        result = instance.negate();
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -1;
        instance = new Math_BigRationalSqrt(1L, oom, rm, true);
        expResult = new Math_BigRationalSqrt(1L, 1L);
        result = instance.negate();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMultiply_Math_BigRational() {
        System.out.println("multiply");
        BigRational y;
        Math_BigRationalSqrt instance;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        y = BigRational.ONE;
        instance = new Math_BigRationalSqrt(1, oom);
        result = instance.multiply(y, oom, rm);
        expResult = new Math_BigRationalSqrt(1, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divide method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testDivide_Math_BigRational() {
        System.out.println("divide");
        BigRational y;
        Math_BigRationalSqrt instance;
        Math_BigRationalSqrt expResult;
        Math_BigRationalSqrt result;
        // Test 1
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        y = BigRational.ONE;
        instance = new Math_BigRationalSqrt(1, oom);
        result = instance.divide(y, oom, rm);
        expResult = new Math_BigRationalSqrt(1, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of isNegative method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testIsNegative() {
        System.out.println("isNegative");
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        assertFalse(instance.isNegative());
        instance = instance.negate();
        assertTrue(instance.isNegative());
        instance = Math_BigRationalSqrt.ZERO;
        assertFalse(instance.isNegative());
        instance = instance.negate();
        assertFalse(instance.isNegative());
    }

    /**
     * Test of isZero method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testIsZero() {
        System.out.println("isZero");
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        assertFalse(instance.isZero());
        instance = Math_BigRationalSqrt.ZERO;
        assertTrue(instance.isZero());
    }
    
    /**
     * Test of abs method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAbs() {
        System.out.println("isAbs");
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt result = instance.abs();
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ONE;
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRationalSqrt.ONE.negate();
        result = instance.abs();
        expResult = Math_BigRationalSqrt.ONE;
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        instance = new Math_BigRationalSqrt(4096L, 64L).negate();
        assertTrue(instance.isNegative());
        result = instance.abs();
        assertFalse(result.isNegative());
        expResult = new Math_BigRationalSqrt(4096L, 64L);
        assertTrue(expResult.compareTo(result) == 0);
        
        
    }

}
