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
package uk.ac.leeds.ccg.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_BigIntegerTest {

    public Math_BigIntegerTest() {
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
    public void testCeiling() {
        String funcName = "ceiling";
        System.out.println("Test " + funcName);
        BigDecimal x;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        int test = 1;
        x = new BigDecimal("1.0000000000000000000000000000000000000000000001");
        expResult = new BigInteger("2");
        result = Math_BigInteger.ceiling(x);
        assertEquals(expResult, result);
        // Test 2
        test++;
        x = new BigDecimal("-1.0000000000000000000000000000000000000000000001");
        expResult = new BigInteger("-1");
        result = Math_BigInteger.ceiling(x);
        assertEquals(expResult, result);
    }

    @Test
    public void testFloor() {
        String funcName = "floor";
        System.out.println("Test " + funcName);
        BigDecimal x;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        int test = 1;
        x = new BigDecimal("1");
        result = Math_BigInteger.floor(x);
        expResult = new BigInteger("1");
        assertEquals(expResult, result);
        // Test 2
        test++;
        x = new BigDecimal("-1");
        result = Math_BigInteger.floor(x);
        expResult = new BigInteger("-1");
        assertEquals(expResult, result);
        // Test 3
        test++;
        x = new BigDecimal("1.00000000000000000000000000000000000000000000001");
        result = Math_BigInteger.floor(x);
        expResult = new BigInteger("1");
        assertEquals(expResult, result);
        // Test 4
        test++;
        x = new BigDecimal("-1.0000000000000000000000000000000000000000000001");
        result = Math_BigInteger.floor(x);
        expResult = new BigInteger("-2");
        assertEquals(expResult, result);
    }

    @Test
    public void testFactorial() {
        String funcName = "factorial";
        System.out.println("Test " + funcName);
        Integer x;
        Math_BigInteger a_Generic_BigInteger = new Math_BigInteger();
        BigInteger expResult;
        // Test 1
        int test = 1;
        x = 123;
        BigInteger result = a_Generic_BigInteger.factorial(x);
        expResult = new BigInteger(
                "12146304367025329675766243241881295855454217088483382315328918"
                + "161829235892362167668831156960612640202170735835221294047782"
                + "591091570411651472186029519906261646730733907419814952960000"
                + "000000000000000000000000");
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRandom() {
        String funcName = "getRandom";
        System.out.println("Test " + funcName);
        BigInteger upperLimit;
        BigInteger result;
        BigInteger expResult;
        Math_BigInteger bi = new Math_BigInteger();
        int length;
        long seed;
        int seedIncrement;
        // Test 1
        int test = 1;
        length = 100;
        seed = 0L;
        seedIncrement = 1;
        bi.initRandoms(length, seed, seedIncrement);
        upperLimit = new BigInteger("10000");
        expResult = new BigInteger("4402");
        result = bi.getRandom(upperLimit);
        assertEquals(expResult, result);
        // Test 2
        test++;
        length = 100;
        seed = 0L;
        bi.initRandoms(length, seed, seedIncrement);
        upperLimit = new BigInteger("1000000000000000000000000000000000000000");
        expResult = new BigInteger("628570378078456855601488631590048551226");
        result = bi.getRandom(upperLimit);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 3
        test++;
        length = 100;
        seed = 1234567L;
        bi.initRandoms(length, seed, seedIncrement);
        upperLimit = new BigInteger("10000");
        expResult = new BigInteger("8804");
        result = bi.getRandom(upperLimit);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 4
        test++;
        length = 100;
        seed = 1234567L;
        bi.initRandoms(length, seed, seedIncrement);
        upperLimit = new BigInteger("1000000000000000000000000000000000000000");
        expResult = new BigInteger("772480814235536969920545354438528182674");
        //System.out.println(result);
        result = bi.getRandom(upperLimit);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPowersOfTwoDecomposition_1args() {
        String funcName = "getPowersOfTwoDecomposition_1args";
        System.out.println("Test " + funcName);
        Math_BigInteger bi = new Math_BigInteger();
        BigInteger x;
        TreeMap<Integer, Integer> powersOfTwoDecomposition;
        BigInteger xR;
        // Test 1
        int test = 1;
        x = new BigInteger("67");
        powersOfTwoDecomposition = bi.getPowersOfTwoDecomposition(x);
        xR = getXRecomposed(bi, powersOfTwoDecomposition);
        assertEquals(x, xR);
        // Test 2
        test++;
        x = new BigInteger("6734517348951454718534151347888542719004873129054");
        powersOfTwoDecomposition = bi.getPowersOfTwoDecomposition(x);
        xR = getXRecomposed(bi, powersOfTwoDecomposition);
        assertEquals(x, xR);
        // Test 3
        test++;
        x = new BigInteger("0");
        powersOfTwoDecomposition = bi.getPowersOfTwoDecomposition(x);
        xR = getXRecomposed(bi, powersOfTwoDecomposition);
        assertEquals(x, xR);
    }

    public BigInteger getXRecomposed(Math_BigInteger bi,
            TreeMap<Integer, Integer> powersOfTwoDecomposition) {
        BigInteger r = BigInteger.ZERO;
        if (powersOfTwoDecomposition != null) {
            if (powersOfTwoDecomposition.size() > 0) {
                for (Entry<Integer, Integer> entry : powersOfTwoDecomposition.entrySet()) {
                    r = r.add(bi.powersOfTwo.get(entry.getKey()).multiply(
                            new BigInteger(entry.getValue().toString())));
                }
            }
        }
        return r;
    }

    /**
     * Test of initFactorials method, of class Math_BigInteger.
     */
    @Test
    public void testInitFactorials() {
        System.out.println("initFactorials");
        // No test.
    }

    /**
     * Test of initPowersOfTwo method, of class Math_BigInteger.
     */
    @Test
    public void testInitPowersOfTwo() {
        System.out.println("initPowersOfTwo");
        // No test.
    }

    /**
     * Test of powerOfTwo method, of class Math_BigInteger.
     */
    @Test
    public void testPowerOfTwo() {
        System.out.println("powerOfTwo");
        // No test.
    }

    /**
     * Test of addPowerOfTwo method, of class Math_BigInteger.
     */
    @Test
    public void testAddPowerOfTwo() {
        System.out.println("addPowerOfTwo");
        // No test.
    }

    /**
     * Test of getPowersOfTwo method, of class Math_BigInteger.
     */
    @Test
    public void testGetPowersOfTwo_0args() {
        System.out.println("getPowersOfTwo");
        // No test.
    }

    /**
     * Test of getPowersOfTwo method, of class Math_BigInteger.
     */
    @Test
    public void testGetPowersOfTwo_BigInteger() {
        System.out.println("getPowersOfTwo");
        // No test.
    }

    /**
     * Test of getPowersOfTwoDecomposition method, of class Math_BigInteger.
     */
    @Test
    public void testGetPowersOfTwoDecomposition() {
        System.out.println("getPowersOfTwoDecomposition");
        // No test.
    }

    /**
     * Test of log10 method, of class Math_BigInteger.
     */
    @Test
    public void testLog10() {
        System.out.println("log10");
        BigInteger x = new BigInteger("10");
        int expResult = 1;
        int result = Math_BigInteger.log10(x);
        assertEquals(expResult, result);
        // Test 2
        x = new BigInteger("1000000000000000000000000000000000000000000000000");
        expResult = 48;
        result = Math_BigInteger.log10(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigInteger("9999999999999999999999999999999999999999999999999");
        expResult = 48;
        result = Math_BigInteger.log10(x);
        assertEquals(expResult, result);
        // Test 4
        Throwable exception = assertThrows(ArithmeticException.class, () -> {
            Math_BigInteger.log10(new BigInteger("-10"));
        });
        String m = exception.getMessage();
        assertTrue(m.equalsIgnoreCase("!(x > 0)"));
    }

    /**
     * Test of isEven method, of class Math_BigInteger.
     */
    @Test
    public void testIsEven() {
        System.out.println("isEven");
        BigInteger x = new BigInteger("12121212124568924316823543574329574388");
        assertTrue(Math_BigInteger.isEven(x));
        // Test 2
        x = new BigInteger("12121212124568924316823543574329574389");
        assertFalse(Math_BigInteger.isEven(x));
    }

    /**
     * Test of isBigInteger method, of class Math_BigInteger.
     */
    @Test
    public void testIsBigInteger() {
        System.out.println("isBigInteger");
        String s = "1";
        assertTrue(Math_BigInteger.isBigInteger(s));
        // Test 2
        s = "15824578392891237489237489327489327489327489327849327849372849732";
        assertTrue(Math_BigInteger.isBigInteger(s));
        // Test 3
        s = "-5824578392891237489237489327489327489327489327849327849372849732";
        assertTrue(Math_BigInteger.isBigInteger(s));
        // Test 4
        s = "158245783928912374892374893274893274893274893278493278493728497.0";
        assertFalse(Math_BigInteger.isBigInteger(s));
        // Test 4
        s = "158245783928912374892374893274893274893274893278493278493728497.1";
        assertFalse(Math_BigInteger.isBigInteger(s));
    }

    /**
     * Test of sqrt method, of class Math_BigInteger.
     */
    @Test
    public void testSqrt() {
        System.out.println("sqrt");
        BigInteger x = new BigInteger("25");
        BigInteger expResult = new BigInteger("5");
        BigInteger result = Math_BigInteger.sqrt(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigInteger("26");
        expResult = new BigInteger("-5");
        result = Math_BigInteger.sqrt(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigInteger("35");
        expResult = new BigInteger("-5");
        result = Math_BigInteger.sqrt(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigInteger("36");
        expResult = new BigInteger("6");
        result = Math_BigInteger.sqrt(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigInteger("-2");
        result = Math_BigInteger.sqrt(x);
        assertNull(result);
    }

    /**
     * Test of isDivisibleBy method, of class Math_BigInteger.
     */
    @Test
    public void testIsDivisibleBy() {
        System.out.println("isDivisibleBy");
        BigInteger x = new BigInteger("120");
        BigInteger y = new BigInteger("6");
        assertTrue(Math_BigInteger.isDivisibleBy(x, y));
        // Test 2
        x = new BigInteger("1201");
        y = new BigInteger("6");
        assertFalse(Math_BigInteger.isDivisibleBy(x, y));
    }

    /**
     * Test of getOrderOfMagnitudeOfMostSignificantDigit method, of class Math_BigInteger.
     */
    @Test
    public void testGetOrderOfMagnitudeOfMostSignificantDigit() {
        System.out.println("getOrderOfMagnitudeOfMostSignificantDigit");
        BigInteger x = BigInteger.valueOf(123456789);
        int expResult = 8;
        int result = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 2
        x = BigInteger.valueOf(-123456789);
        expResult = 8;
        result = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigInteger("1234567890000000000000000000000");
        expResult = 30;
        result = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 4
        x = BigInteger.ZERO;
        expResult = 0;
        result = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class Math_BigInteger.
     */
    @Test
    public void testMultiply_3args() {
        System.out.println("multiply");
        BigInteger x = null;
        BigInteger y = null;
        int oom = 10;
        BigInteger expResult = null;
        BigInteger result = null;
        // Test 1
        x = new BigInteger("123456789000000000000000000000");
        y = new BigInteger("12345678900000000");
        System.out.println(x.multiply(y));
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 30;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = 40;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigInteger("9999999");
        y = new BigInteger("999999");
        oom = 4;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigInteger("99999999");
        y = new BigInteger("9999999");
        oom = 4;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigInteger("999999999");
        y = new BigInteger("99999999");
        oom = 5;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigInteger("999999999");
        y = new BigInteger("99999999");
        oom = 6;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigInteger("999999999");
        y = new BigInteger("99999999");
        oom = 7;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigInteger("999999999");
        y = new BigInteger("99999999");
        oom = 8;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigInteger("999999999");
        y = new BigInteger("99999999");
        oom = 9;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigInteger("99999999999");
        y = new BigInteger("999999");
        oom = 4;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigInteger("999999");
        y = new BigInteger("99999999999");
        oom = 4;
        expResult = Math_BigInteger.round(x.multiply(y), oom);
        result = Math_BigInteger.multiply(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of round method, of class Math_BigInteger.
     */
    @Test
    public void testRound_3args() {
        System.out.println("round");
        RoundingMode rm = RoundingMode.HALF_UP;
        BigInteger x = BigInteger.valueOf(123456789);
        int oom = 0;
        BigInteger expResult = BigInteger.valueOf(123456789);
        BigInteger result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 1
        oom = 1;
        expResult = BigInteger.valueOf(123456790);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 2;
        expResult = BigInteger.valueOf(123456800);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = 3;
        expResult = BigInteger.valueOf(123457000);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = 4;
        expResult = BigInteger.valueOf(123460000);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = 5;
        expResult = BigInteger.valueOf(123500000);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = 6;
        expResult = BigInteger.valueOf(123000000);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = 7;
        expResult = BigInteger.valueOf(120000000);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -1;
        expResult = BigInteger.valueOf(123456789);
        result = Math_BigInteger.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);

    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigInteger.
     */
    @Test
    public void testMultiplyPriorRound_3args() {
        System.out.println("multiplyPriorRound");
        System.out.println("multiply");
        BigInteger x = null;
        BigInteger y = null;
        int oom = 40;
        BigInteger expResult;
        // Test 1
        x = new BigInteger("123456789000000000000000000000");
        y = new BigInteger("12345678900000000");
        //System.out.println(x.multiply(y));
        expResult = Math_BigInteger.multiply(x, y, oom);
        BigInteger result = Math_BigInteger.multiplyPriorRound(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigInteger("123456789123456789123456789");
        y = new BigInteger("123456789123456789");
        oom = 40;
        //System.out.println(x.multiply(y));
        expResult = Math_BigInteger.multiply(x, y, oom);
        result = Math_BigInteger.multiplyPriorRound(x, y, oom);
        //System.out.println(result.toString());
        //System.out.println(expResult.toString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigInteger("123456789123456789123456789");
        y = new BigInteger("123456789123456789");
        oom = 30;
        //System.out.println(x.multiply(y));
        expResult = Math_BigInteger.multiply(x, y, oom);
        result = Math_BigInteger.multiplyPriorRound(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigInteger(
                "9999999999" + "9999999999" + "9999999999" + "9999999999"
                + "9999999999" + "9999999999" + "9999999999" + "9999999999"
                + "9999999999" + "9999999999");
        y = new BigInteger(
                "9999999999" + "9999999999" + "9999999999" + "9999999999"
                + "9999999999" + "9999999999");
        oom = 64;
        //System.out.println(x.multiply(y));
        expResult = Math_BigInteger.multiply(x, y, oom);
        // 9999999999999999999999999999999999999999999999999999999999989999999999999999999999999999999999999999000000000000000000000000000000000000000000000000000000000001
        // =
        // 999
        // 9999999999 9999999999 999999999 9999999999 9999999999 9999999899 
        // 9999999999 9999999999 999999999 9999999990 0000000000 0000000000
        // 0000000000 0000000000 000000000 0000000001
        // prior rounded x.y
        // 9999999999999999999999999999999999999999999999999999999999989999999999999999999999999999999900000000000000000000000000000000000000000000000000000000000100000000
        // 99999999999999999999999999999999999999999999999999999999999899999999999999999999999999999999999990000000000000000000000000000000000000000000000000000000000000000
        expResult = new BigInteger("9999999999" + "9999999999" + "9999999999"
                + "9999999999" + "9999999999" + "9999999998" + "9999999999"
                + "9999999999" + "9999999999" + "9900000000" + "0000000000"
                + "0000000000" + "0000000000" + "0000000000" + "0000000000"
                + "0000000000");
        result = Math_BigInteger.multiplyPriorRound(x, y, oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getOrderOfMagnitudeOfSmallestNonZeroDigit method, of class Math_BigInteger.
     */
    @Test
    public void testGetOrderOfMagnitudeOfSmallestNonZeroDigit_BigInteger() {
        System.out.println("getOrderOfMagnitudeOfSmallestNonZeroDigit");
        BigInteger x = BigInteger.valueOf(123456789);
        int expResult = 0;
        int result = Math_BigInteger.getOrderOfMagnitudeOfSmallestNonZeroDigit(x);
        assertEquals(expResult, result);
        // Test 2
        x = BigInteger.valueOf(-123456789);
        expResult = 0;
        result = Math_BigInteger.getOrderOfMagnitudeOfSmallestNonZeroDigit(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigInteger("123456789000000000000000000000");
        expResult = 21;
        result = Math_BigInteger.getOrderOfMagnitudeOfSmallestNonZeroDigit(x);
        assertEquals(expResult, result);
        // Test 4
        x = new BigInteger("-123456789000000000000000000000");
        expResult = 21;
        result = Math_BigInteger.getOrderOfMagnitudeOfSmallestNonZeroDigit(x);
        assertEquals(expResult, result);
        // Test 5
        x = BigInteger.ZERO;
        expResult = 0;
        result = Math_BigInteger.getOrderOfMagnitudeOfSmallestNonZeroDigit(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of round method, of class Math_BigInteger.
     * Test covered by {@link #testRound_3args()}.
     */
    @Test
    public void testRound_BigInteger_int() {
        System.out.println("round");
        // No test.
    }

    /**
     * Test of getOrderOfMagnitudeOfSmallestNonZeroDigit method, of class Math_BigInteger.
     * Test covered by {@link #testGetOrderOfMagnitudeOfSmallestNonZeroDigit_BigInteger()}
     */
    @Test
    public void testGetOrderOfMagnitudeOfSmallestNonZeroDigit_BigInteger_int() {
        System.out.println("getOrderOfMagnitudeOfSmallestNonZeroDigit");
        // No test.
    }

    /**
     * Test of multiply method, of class Math_BigInteger.
     * Test covered by {@link #testMultiply_3args()}.
     */
    @Test
    public void testMultiply_4args() {
        System.out.println("multiply");
        // No test.
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigInteger.
     * Test covered by {@link #testMultiplyPriorRound_3args()}.
     */
    @Test
    public void testMultiplyPriorRound_4args() {
        System.out.println("multiplyPriorRound");
        // Not test.
    }


}
