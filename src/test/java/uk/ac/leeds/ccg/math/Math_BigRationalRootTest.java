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

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for class Math_BigRationalRoot.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_BigRationalRootTest {

    public Math_BigRationalRootTest() {
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
     * Test of toString method, of class Math_BigRationalRoot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Math_BigRationalRoot instance = new Math_BigRationalRoot(27, 3);
        String expResult = "Math_BigRationalRoot(x=27, n=3, rootx=3,"
                + " rootxapprox=null, oom=0)";
        String result = instance.toString();
        assertTrue(expResult.equalsIgnoreCase(result));
        // Test 2
    }

    /**
     * Test of valueOf method, of class Math_BigRationalRoot.
     */
    @Test
    public void testValueOf_BigInteger() {
        System.out.println("valueOf");
        BigInteger v = BigInteger.ZERO;
        Math_BigRationalRoot expResult = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot result = Math_BigRationalRoot.valueOf(v);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
    }

    /**
     * Test of valueOf method, of class Math_BigRationalRoot.
     */
    @Test
    public void testValueOf_BigDecimal() {
        System.out.println("valueOf");
        BigDecimal v = BigDecimal.ZERO;
        Math_BigRationalRoot expResult = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot result = Math_BigRationalRoot.valueOf(v);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
    }

    /**
     * Test of valueOf method, of class Math_BigRationalRoot.
     */
    @Test
    public void testValueOf_BigRational() {
        System.out.println("valueOf");
        BigRational v = BigRational.ZERO;
        Math_BigRationalRoot expResult = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot result = Math_BigRationalRoot.valueOf(v);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
    }

    /**
     * Test of getRoot method, of class Math_BigRationalRoot.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        BigRational x = BigRational.ZERO;
        int n = 0;
        BigRational expResult = BigRational.ZERO;
        BigRational result = Math_BigRationalRoot.getRoot(x, n);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigRational.ONE;
        n = 3;
        expResult = BigRational.ONE;
        result = Math_BigRationalRoot.getRoot(x, n);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = BigRational.valueOf(27);
        n = 3;
        expResult = BigRational.valueOf(3);
        result = Math_BigRationalRoot.getRoot(x, n);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of toBigDecimal method, of class Math_BigRationalRoot.
     */
    @Test
    public void testToBigDecimal() {
        System.out.println("toBigDecimal");
        int oom = 0;
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        BigDecimal expResult = BigDecimal.ZERO;
        BigDecimal result = instance.toBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -1;
        instance = new Math_BigRationalRoot(2, 2);
        expResult = new BigDecimal("1.4");
        result = instance.toBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getNumeratorAndDenominator method, of class Math_BigRationalRoot.
     */
    @Test
    public void testGetNumeratorAndDenominator() {
        System.out.println("getNumeratorAndDenominator");
        BigRational x = BigRational.valueOf(0);
        BigInteger[] expResult = new BigInteger[2];
        expResult[0] = BigInteger.valueOf(0);
        expResult[1] = BigInteger.valueOf(1);
        BigInteger[] result = Math_BigRationalRoot.getNumeratorAndDenominator(x);
        assertTrue(result.length == expResult.length);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i].compareTo(expResult[i]) == 0);
        }
        // Test 2
        x = BigRational.valueOf(1, 3);
        expResult = new BigInteger[2];
        expResult[0] = BigInteger.valueOf(1);
        expResult[1] = BigInteger.valueOf(3);
        result = Math_BigRationalRoot.getNumeratorAndDenominator(x);
        assertTrue(result.length == expResult.length);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i].compareTo(expResult[i]) == 0);
        }
        // Test 2
        x = BigRational.valueOf(3, 9);
        expResult = new BigInteger[2];
        expResult[0] = BigInteger.valueOf(1);
        expResult[1] = BigInteger.valueOf(3);
        result = Math_BigRationalRoot.getNumeratorAndDenominator(x);
        assertTrue(result.length == expResult.length);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i].compareTo(expResult[i]) == 0);
        }

    }

    /**
     * Test of multiply method, of class Math_BigRationalRoot.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        Math_BigRationalRoot y = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot expResult = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot result = instance.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        y = new Math_BigRationalRoot(27, 3);
        instance = new Math_BigRationalRoot(27, 3);
        expResult = new Math_BigRationalRoot(9, 1);
        result = instance.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divide method, of class Math_BigRationalRoot.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Math_BigRationalRoot y = new Math_BigRationalRoot(1, 2);
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot expResult = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot result = instance.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        y = new Math_BigRationalRoot(27, 3);
        instance = new Math_BigRationalRoot(27, 3);
        expResult = new Math_BigRationalRoot(1, 1);
        result = instance.divide(y);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of equals method, of class Math_BigRationalRoot.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object o = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        assertTrue(instance.equals(o));
        // Test 2
        o = new Math_BigRationalRoot(27, 3);
        instance = new Math_BigRationalRoot(27, 3);
        assertTrue(instance.equals(o));
    }

    /**
     * Test of hashCode method, of class Math_BigRationalRoot.
     */
    @Test
    @Disabled
    public void testHashCode() {
        // No Test
        System.out.println("hashCode");
        Math_BigRationalRoot instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Math_BigRationalRoot.
     */
    @Test
    public void testEquals_Math_BigRationalRoot() {
        System.out.println("equals");
        Math_BigRationalRoot x = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        assertTrue(instance.equals(x));
        // Test 2
        x = new Math_BigRationalRoot(27, 3);
        instance = new Math_BigRationalRoot(27, 3);
        assertTrue(instance.equals(x));
    }

    /**
     * Test of compareTo method, of class Math_BigRationalRoot.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Math_BigRationalRoot o = new Math_BigRationalRoot(0, 0);
        Math_BigRationalRoot instance = new Math_BigRationalRoot(0, 0);
        assertTrue(0 == instance.compareTo(o));
        // Test 2
        o = new Math_BigRationalRoot(0, 0);
        instance = new Math_BigRationalRoot(1, 1);
        assertTrue(1 == instance.compareTo(o));
    }

}
