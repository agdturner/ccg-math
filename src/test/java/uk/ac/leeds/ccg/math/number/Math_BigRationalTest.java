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
        BigRational x = BigRational.valueOf("8.80446261998075791112518174946277"
                + "2084351754080848495898653087767533866267556634");
//        System.out.println(x.toRationalString());
//        System.out.println(x.toFloat());
//        System.out.println(Float.valueOf(x.toBigDecimal().toString()));
//        System.out.println(new BigDecimal("1000000000000000000000000000000000"
//              "000000000000000000000000000000000000000000000").floatValue());
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
        BigRational x = BigRational.valueOf(BigInteger.valueOf(8),
                BigInteger.valueOf(3));
        BigRational y = BigRational.valueOf(BigInteger.valueOf(14),
                BigInteger.valueOf(3));
        BigRational expResult = BigRational.valueOf(BigInteger.valueOf(2),
                BigInteger.valueOf(3));
        BigRational result = Math_BigRational.getCommonFactor(x, y);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of toFloat method, of class Math_BigRational.
     */
    @Test
    public void testToFloat() {
        System.out.println("toFloat");
        BigRational x = BigRational.valueOf("8.80446261998075791112518174946277"
                + "2084351754080848495898653087767533866267556634");
        float expResult = 8.804463F;
        float result = Math_BigRational.toFloat(x, -6);
        assertTrue(expResult == result);
        //System.out.println(Float.valueOf(x.toBigDecimal().toString()));
        System.out.println(new BigDecimal("100000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000").floatValue());
    }

    /**
     * Test of floor method, of class Math_BigRational.
     */
    @Test
    public void testFloor() {
        System.out.println("floor");
        BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = BigRational.valueOf("0.1");
        expResult = BigInteger.ZERO;
        result = Math_BigRational.floor(instance);
        assertEquals(expResult, result);
        // Test 2
        instance = BigRational.valueOf("-0.1");
        expResult = BigInteger.valueOf(-1);
        result = Math_BigRational.floor(instance);
        assertEquals(expResult, result);
        // Test 3
        instance = BigRational.valueOf("-1.1");
        expResult = BigInteger.valueOf(-2);
        result = Math_BigRational.floor(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of floor method, of class Math_BigRational.
     */
    @Test
    public void testCeil() {
        System.out.println("ceil");
        BigRational instance;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        instance = BigRational.valueOf("0.1");
        expResult = BigInteger.ONE;
        result = Math_BigRational.ceil(instance);
        assertEquals(expResult, result);
        // Test 2
        instance = BigRational.valueOf("-0.1");
        expResult = BigInteger.valueOf(0);
        result = Math_BigRational.ceil(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of withPrecision method, of class Math_BigRational.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        int precision = -3;
        BigRational instance = BigRational.valueOf(1, 3);
        BigRational expResult = BigRational.valueOf("0.333");
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational result = Math_BigRational.round(instance, precision, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        expResult = BigRational.valueOf("0.334");
        rm = RoundingMode.UP;
        result = Math_BigRational.round(instance, precision, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of cos method, of class Math_BigRational.
     */
    @Test
    public void testCos() {
        System.out.println("cos");
        Math_BigInteger bi = new Math_BigInteger();
        int oom = -3;
        BigRational x = BigRational.ZERO;
        BigRational expResult = BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational result = Math_BigRational.cos(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigRational.ONE;
        expResult = Math_BigRational.round(BigRational.valueOf(Math.cos(1.0d)), oom, rm);
        result = Math_BigRational.cos(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = BigRational.ONE.negate();
        expResult = Math_BigRational.round(BigRational.valueOf(Math.cos(1.0d)), oom, rm);
        result = Math_BigRational.cos(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = BigRational.valueOf(1, 2);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.cos(0.5d)), oom, rm);
        result = Math_BigRational.cos(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = BigRational.valueOf(1, 2).negate();
        expResult = Math_BigRational.round(BigRational.valueOf(Math.cos(-0.5d)), oom, rm);
        result = Math_BigRational.cos(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = BigRational.valueOf(1, 4).negate();
        expResult = Math_BigRational.round(BigRational.valueOf(Math.cos(-0.25d)), oom, rm);
        result = Math_BigRational.cos(x, bi, oom, rm);
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
        BigRational x = BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(1.0d)), oom, rm);
        BigRational result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigRational.ZERO;
        expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(0.0d)), oom, rm);
        result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = BigRational.ONE.negate();
        expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(-1.0d)), oom, rm);
        result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = BigRational.valueOf(1, 2);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(0.5d)), oom, rm);
        result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = BigRational.valueOf(-1, 2);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(-0.5d)), oom, rm);
        result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = BigRational.valueOf(1, 4);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.sin(0.25d)), oom, rm);
        result = Math_BigRational.sin(x, bi, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }
    
    /**
     * Test of cos method, of class Math_BigRational.
     */
    @Test
    public void testAsin() {
        System.out.println("asin");
        int oom = -3;
        BigRational x = BigRational.ONE;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational expResult = Math_BigRational.round(BigRational.valueOf(Math.asin(1.0d)), oom, rm);
        System.out.println(expResult.toString());
        BigRational result = Math_BigRational.asin(x, oom, rm);
        System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigRational.ZERO;
        expResult = Math_BigRational.round(BigRational.valueOf(Math.asin(0.0d)), oom, rm);
        result = Math_BigRational.asin(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = BigRational.ONE.negate();
        expResult = Math_BigRational.round(BigRational.valueOf(Math.asin(-1.0d)), oom, rm);
        result = Math_BigRational.asin(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = BigRational.valueOf(1, 2);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.asin(0.5d)), oom, rm);
        //System.out.println(expResult.toString());
        result = Math_BigRational.asin(x, oom, rm);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = BigRational.valueOf(-1, 2);
        expResult = Math_BigRational.round(BigRational.valueOf(Math.asin(-0.5d)), oom, rm);
        //System.out.println(expResult.toString());
        result = Math_BigRational.asin(x, oom, rm);
        //System.out.println(result.toString());
        assertTrue(expResult.compareTo(result) == 0);
    }
}
