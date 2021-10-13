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

import uk.ac.leeds.ccg.math.number.Math_BigRationalSqrt;
import uk.ac.leeds.ccg.math.number.Math_BigRational;
import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
    public void testGetSqrt() {
        System.out.println("getSqrt");
        Math_BigRational x;
        int oomi = -1;
        Math_BigRational result;
        Math_BigRational expResult;
        // Test 1
        x = Math_BigRational.valueOf(2);
        result = new Math_BigRationalSqrt(x, oomi).getSqrt();
        assertNull(result);
        // Test 2
        x = Math_BigRational.valueOf(4);
        expResult = Math_BigRational.valueOf(2);
        result = new Math_BigRationalSqrt(x, oomi).getSqrt();
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMultiply_BigRational() {
        System.out.println("multiply");
        int oomi = -1;
        Math_BigRationalSqrt x = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(2).divide(
                        Math_BigRational.valueOf(1)), oomi);
        Math_BigRational y = Math_BigRational.valueOf(2).divide(
                Math_BigRational.valueOf(1));
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(8), oomi);
        Math_BigRationalSqrt result = x.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 3
        x = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(8).divide(Math_BigRational.valueOf(1)), oomi);
        y = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(32), oomi);
        result = x.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 5
        x = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(10).divide(Math_BigRational.valueOf(3)), oomi);
        y = Math_BigRational.valueOf(15).divide(Math_BigRational.valueOf(4));
        expResult = new Math_BigRationalSqrt(y.pow(2).multiply(x.x), oomi);
        result = x.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 6
        x = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(10).divide(Math_BigRational.valueOf(3)), oomi);
        y = Math_BigRational.valueOf(-15).divide(Math_BigRational.valueOf(4));
        expResult = new Math_BigRationalSqrt(y.pow(2).multiply(x.x).negate(), oomi);
        result = x.multiply(y);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getNumeratorAndDenominator method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetNumeratorAndDenominator() {
        System.out.println("getNumeratorAndDenominator");
        Math_BigRational x = Math_BigRational.valueOf(4);
        int oomi = -1;
        BigInteger[] expResult = new BigInteger[2];
        expResult[0] = BigInteger.valueOf(4);
        expResult[1] = BigInteger.valueOf(1);
        BigInteger[] result = new Math_BigRationalSqrt(x, oomi).getNumeratorAndDenominator();
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
        int oomi = -1;
        Math_BigRationalSqrt instance = new Math_BigRationalSqrt(2, oomi);
        String expResult = "Math_BigRationalSqrt(x=2, negative=false, "
                + "sqrtx=null, oomi=-1, sqrtxapprox=1.4, oom=-1)";
        String result = instance.toString();
        assertEquals(expResult, result);
        // Test 2
        instance.toBigDecimal(-2);
        expResult = "Math_BigRationalSqrt(x=2, negative=false, "
                + "sqrtx=null, oomi=-2, sqrtxapprox=1.41, oom=-2)";
        result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toBigDecimal method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testToBigDecimal() {
        System.out.println("toBigDecimal");
        int oomi = -1;
        int oom = -2;
        Math_BigRationalSqrt instance = new Math_BigRationalSqrt(2, oomi);
        BigDecimal expResult = new BigDecimal("1.41");
        BigDecimal result = instance.toBigDecimal(oom);
        assertEquals(expResult, result);
        // Test 2
        oom = -2;
        instance = new Math_BigRationalSqrt(16, oomi);
        expResult = new BigDecimal("4");
        result = instance.toBigDecimal(oom);
        assertEquals(expResult, result);
        // Test 3
        oom = -2;
        instance = new Math_BigRationalSqrt(256, oomi);
        expResult = new BigDecimal("16");
        result = instance.toBigDecimal(oom);
        assertEquals(expResult, result);
        // Test 4
        oom = 0;
        instance = new Math_BigRationalSqrt(257, oomi);
        expResult = new BigDecimal("16");
        result = instance.toBigDecimal(oom);
        assertEquals(expResult, result);
        // Test 5
        oom = 1;
        instance = new Math_BigRationalSqrt(257, oomi);
        expResult = new BigDecimal("20");
        result = instance.toBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -1;
        instance = new Math_BigRationalSqrt(257, oomi);
        expResult = new BigDecimal("16.0");
        result = instance.toBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -20;
        instance = new Math_BigRationalSqrt(257, oomi);
        expResult = new BigDecimal("16.03121954188139736487");
        result = instance.toBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -20;
        instance = new Math_BigRationalSqrt(256 * 256 + 1, oomi);
        expResult = new BigDecimal("256.00195311754947624595");
        result = instance.toBigDecimal(oom);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -20;
        instance = new Math_BigRationalSqrt(new BigInteger("4294967297"), oomi);
        expResult = new BigDecimal("65536.00000762939453080591");
        result = instance.toBigDecimal(oom);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -20;
        instance = new Math_BigRationalSqrt(new BigInteger("18446744073709551617"), oomi);
        expResult = new BigDecimal("4294967296.00000000011641532183");
        result = instance.toBigDecimal(oom);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = -11;
        instance = new Math_BigRationalSqrt(new BigInteger("18446744073709551617"), oomi);
        expResult = new BigDecimal("4294967296.00000000012");
        result = instance.toBigDecimal(oom);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of add method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAdd_Math_BigRationalSqrt() {
        System.out.println("add");
        int oomi = -1;
        Math_BigRationalSqrt x = new Math_BigRationalSqrt(8, oomi);
        Math_BigRationalSqrt y = new Math_BigRationalSqrt(2, oomi);
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(18, oomi);
        Math_BigRationalSqrt result = x.add(y);
        assertTrue(expResult.equals(result));
        // Test 2
        x = new Math_BigRationalSqrt(16, oomi);
        y = new Math_BigRationalSqrt(4, oomi);
        expResult = new Math_BigRationalSqrt(36, oomi);
        result = x.add(y);
        assertTrue(expResult.equals(result));
        // Test 3
        x = new Math_BigRationalSqrt(3, oomi);
        y = new Math_BigRationalSqrt(4, oomi);
        result = x.add(y);
        assertNull(result);
        // Test 4
        x = new Math_BigRationalSqrt(4, oomi);
        y = new Math_BigRationalSqrt(16, oomi);
        expResult = new Math_BigRationalSqrt(36, oomi);
        result = x.add(y);
        assertTrue(expResult.equals(result));
        // Test 5
        x = new Math_BigRationalSqrt(2, oomi);
        y = new Math_BigRationalSqrt(8, oomi);
        expResult = new Math_BigRationalSqrt(18, oomi);
        result = x.add(y);
        assertTrue(expResult.equals(result));
        // Test 6
        x = new Math_BigRationalSqrt(3, oomi);
        y = new Math_BigRationalSqrt(8, oomi);
        assertNull(x.add(y));
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
    @Disabled
    public void testGetX() {
        // No Test
        System.out.println("getX");
        Math_BigRationalSqrt instance = null;
        Math_BigRational expResult = null;
        Math_BigRational result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testAdd_BigRational() {
        System.out.println("add");
        Math_BigRational y = Math_BigRational.valueOf(BigDecimal.ONE);
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = new Math_BigRationalSqrt(4, -1);
        Math_BigRationalSqrt result = instance.add(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = new Math_BigRationalSqrt(4, -1);
        expResult = new Math_BigRationalSqrt(9, -1);
        result = instance.add(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        y = Math_BigRational.valueOf(BigDecimal.TEN);
        instance = new Math_BigRationalSqrt(4, -1);
        expResult = new Math_BigRationalSqrt(144, -1);
        result = instance.add(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = Math_BigRational.valueOf(BigDecimal.TEN.negate());
        instance = new Math_BigRationalSqrt(4, -1);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(64),
                Math_BigRational.valueOf(-8), -1);
        result = instance.add(y);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testMultiply_Math_BigRationalSqrt() {
        System.out.println("multiply");
        Math_BigRationalSqrt y = Math_BigRationalSqrt.ZERO;
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ZERO;
        Math_BigRationalSqrt result = instance.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        instance = Math_BigRationalSqrt.ONE;
        expResult = Math_BigRationalSqrt.ZERO;
        result = instance.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        Math_BigRational aob = Math_BigRational.valueOf(2).divide(
                Math_BigRational.valueOf(1));
        Math_BigRational cod = Math_BigRational.valueOf(2).divide(
                Math_BigRational.valueOf(1));
        int oomi = -1;
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(4), oomi);
        result = instance.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 4
        aob = Math_BigRational.valueOf(8).divide(Math_BigRational.valueOf(1));
        cod = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(16), oomi);
        result = instance.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 5
        aob = Math_BigRational.valueOf(12).divide(Math_BigRational.valueOf(1));
        cod = Math_BigRational.valueOf(3).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(36), oomi);
        result = instance.multiply(y);
        assertTrue(expResult.equals(result));
        // Test 6
        aob = Math_BigRational.valueOf(10).divide(Math_BigRational.valueOf(3));
        cod = Math_BigRational.valueOf(15).divide(Math_BigRational.valueOf(4));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(25).divide(2), oomi);
        result = instance.multiply(y);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of divide method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testDivide_BigRational() {
        System.out.println("divide");
        int oomi = -1;
        Math_BigRational y = Math_BigRational.ONE;
        Math_BigRationalSqrt x = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt result = x.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_BigRationalSqrt.ONE.divide(Math_BigRational.ZERO);
        });
        // Test 3
        y = Math_BigRational.ONE;
        x = Math_BigRationalSqrt.ZERO;
        expResult = Math_BigRationalSqrt.ZERO;
        result = x.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = Math_BigRational.valueOf(2);
        x = Math_BigRationalSqrt.TWO;
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oomi);
        result = x.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        y = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oomi);
        result = x.divide(y);
        assertTrue(expResult.equals(result));
        // Test 6
        y = Math_BigRational.valueOf(8).divide(Math_BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oomi);
        result = x.divide(y);
        assertTrue(expResult.equals(result));
        // Test 7
        y = Math_BigRational.valueOf(-8).divide(Math_BigRational.valueOf(1));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)).negate(), oomi);
        result = x.divide(y);
        assertTrue(expResult.equals(result));
        // Test 8
        y = Math_BigRational.valueOf(1).divide(Math_BigRational.valueOf(3));
        x = new Math_BigRationalSqrt(
                Math_BigRational.valueOf(3).divide(Math_BigRational.valueOf(1)), oomi);
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)), oomi);
        result = x.divide(y);
        assertTrue(expResult.equals(result));
        // Test 9
        y = Math_BigRational.valueOf(-1).divide(Math_BigRational.valueOf(3));
        expResult = new Math_BigRationalSqrt(x.getX().divide(y.pow(2)).negate(), oomi);
        result = x.divide(y);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of divide method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testDivide_Math_BigRationalSqrt() {
        System.out.println("divide");
        int oomi = -1;
        Math_BigRationalSqrt y = new Math_BigRationalSqrt(Math_BigRational.ONE, oomi);
        Math_BigRationalSqrt instance = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt expResult = Math_BigRationalSqrt.ONE;
        Math_BigRationalSqrt result = instance.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_BigRationalSqrt.ONE.divide(Math_BigRational.ZERO);
        });
        // Test 3
        y = new Math_BigRationalSqrt(Math_BigRational.ONE, oomi);
        instance = Math_BigRationalSqrt.ZERO;
        expResult = Math_BigRationalSqrt.ZERO;
        result = instance.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = Math_BigRationalSqrt.TWO;
        instance = Math_BigRationalSqrt.TWO;
        expResult = Math_BigRationalSqrt.ONE;
        result = instance.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        Math_BigRational aob = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        Math_BigRational cod = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = Math_BigRationalSqrt.ONE;
        result = instance.divide(y);
        assertTrue(expResult.equals(result));
        // Test 6
        aob = Math_BigRational.valueOf(8).divide(Math_BigRational.valueOf(1));
        cod = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf("0.25"), oomi);
        result = instance.divide(y);
        assertTrue(expResult.equals(result));
        // Test 7
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(4), oomi);
        result = y.divide(instance);
        assertTrue(expResult.equals(result));
        // Test 8
        aob = Math_BigRational.valueOf(-8).divide(Math_BigRational.valueOf(1));
        cod = Math_BigRational.valueOf(2).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf("-0.25"), oomi);
        result = instance.divide(y);
        assertTrue(expResult.equals(result));
        // Test 9
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(-4), oomi);
        result = y.divide(instance);
        assertTrue(expResult.equals(result));
        // Test 10
        aob = Math_BigRational.valueOf(1).divide(Math_BigRational.valueOf(3));
        cod = Math_BigRational.valueOf(3).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(9), oomi);
        result = instance.divide(y);
        assertTrue(expResult.equals(result));
        // Test 11
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(1).divide(9), oomi);
        result = y.divide(instance);
        assertTrue(expResult.equals(result));
        // Test 12
        aob = Math_BigRational.valueOf(-1).divide(Math_BigRational.valueOf(3));
        cod = Math_BigRational.valueOf(3).divide(Math_BigRational.valueOf(1));
        y = new Math_BigRationalSqrt(aob, oomi);
        instance = new Math_BigRationalSqrt(cod, oomi);
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(-9), oomi);
        result = instance.divide(y);
        assertTrue(expResult.equals(result));
        // Test 13
        expResult = new Math_BigRationalSqrt(Math_BigRational.valueOf(1).divide(9).negate(), oomi);
        result = y.divide(instance);
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getOOM method, of class Math_BigRationalSqrt.
     */
    @Test
    public void testGetOOM() {
        System.out.println("getOOM");
        Math_BigRational v = Math_BigRational.valueOf(BigInteger.ONE, BigInteger.valueOf(3));
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
        v = Math_BigRational.valueOf(BigInteger.ONE, BigInteger.valueOf(3)).divide(100);
        oom = -5;
        expResult = -8;
        result = Math_BigRationalSqrt.getOOM(v, oom);
        assertTrue(expResult == result);
        //BigDecimal sqrt2 = new Math_BigRationalSqrt(v).toBigDecimal(result);
        // Test 3
        v = Math_BigRational.valueOf(BigInteger.valueOf(33333), BigInteger.valueOf(5));
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
    @Disabled
    public void testMin_Math_BigRationalSqrtArr() {
        System.out.println("min");
        Math_BigRationalSqrt[] x = null;
        Math_BigRationalSqrt expResult = null;
        Math_BigRationalSqrt result = Math_BigRationalSqrt.min(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of min method, of class Math_BigRationalSqrt.
     */
    @Test
    @Disabled
    public void testMin_Collection() {
        System.out.println("min");
        Collection<Math_BigRationalSqrt> c = null;
        Math_BigRationalSqrt expResult = null;
        Math_BigRationalSqrt result = Math_BigRationalSqrt.min(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class Math_BigRationalSqrt.
     */
    @Test
    @Disabled
    public void testMax_Math_BigRationalSqrtArr() {
        System.out.println("max");
        Math_BigRationalSqrt[] x = null;
        Math_BigRationalSqrt expResult = null;
        Math_BigRationalSqrt result = Math_BigRationalSqrt.max(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class Math_BigRationalSqrt.
     */
    @Test
    @Disabled
    public void testMax_Collection() {
        System.out.println("max");
        Collection<Math_BigRationalSqrt> c = null;
        Math_BigRationalSqrt expResult = null;
        Math_BigRationalSqrt result = Math_BigRationalSqrt.max(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
