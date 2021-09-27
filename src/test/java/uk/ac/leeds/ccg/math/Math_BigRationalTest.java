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
        BigRational x;
        x = BigRational.valueOf("123.456789");
        BigRational result;
        BigRational expResult;
        int oom;
        // Test 1
        oom = 0;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        oom = -1;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3
        oom = 1;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("120");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4
        oom = 2;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("100");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5
        oom = -4;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4567");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6
        oom = 20;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("0");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 7
        oom = -7;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4567890");
        assertTrue(result.compareTo(expResult) == 0);
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
     * Test of roundToBD method, of class Math_BigRational.
     */
    @Test
    public void testRoundToBD() {
        System.out.println("roundToBD");
        BigRational x;
        int oom;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(3));
        oom = -3;
        expResult = new BigDecimal("0.333");
        result = Math_BigRational.roundToBD(x, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of min method, of class Math_BigRational.
     */
    @Test
    public void testMin() {
        System.out.println("min");
        BigRational x;
        BigRational y;
        x = BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000000));
        y = BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000001));
        BigRational expResult = null;
        BigRational result = Math_BigRational.min(x, y);
        assertTrue(result.compareTo(y) == 0);
    }

    /**
     * Test of max method, of class Math_BigRational.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        BigRational x;
        BigRational y;
        x = BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000000));
        y = BigRational.valueOf(BigDecimal.ONE, BigDecimal.valueOf(1000000001));
        BigRational expResult = null;
        BigRational result = Math_BigRational.max(x, y);
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
}
