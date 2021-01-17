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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Andy Turner
 */
public class Math_BigDecimalTest {
    
    public Math_BigDecimalTest() {
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
     * Test of getRoundingMode method, of class Math_BigDecimal.
     */
    @Test
    public void testGetRoundingMode() {
        System.out.println("getRoundingMode");
        Math_BigDecimal instance = new Math_BigDecimal();
        RoundingMode expResult = null;
        RoundingMode result = instance.getRoundingMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundingMode method, of class Math_BigDecimal.
     */
    @Test
    public void testSetRoundingMode() {
        System.out.println("setRoundingMode");
        RoundingMode r = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        instance.setRoundingMode(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitudeOfMostSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testGetMagnitudeOfMostSignificantDigit() {
        System.out.println("getMagnitudeOfMostSignificantDigit");
        BigDecimal x = null;
        int expResult = 0;
        int result = Math_BigDecimal.getMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitudeOfLeastSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testGetMagnitudeOfLeastSignificantDigit() {
        System.out.println("getMagnitudeOfLeastSignificantDigit");
        BigDecimal x = null;
        int expResult = 0;
        int result = Math_BigDecimal.getMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiply_BigDecimal_BigInteger() {
        System.out.println("multiply");
        BigDecimal x = null;
        BigInteger y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.multiply(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiply_4args_1() {
        System.out.println("multiply");
        BigDecimal x = null;
        BigInteger y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiply_4args_2() {
        System.out.println("multiply");
        BigDecimal x = null;
        BigDecimal y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiplyPriorRound() {
        System.out.println("multiplyPriorRound");
        BigDecimal x = null;
        BigDecimal y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_4args_1() {
        System.out.println("divideRoundIfNecessary");
        BigDecimal x = null;
        BigDecimal y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundIfNecessary(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_3args() {
        System.out.println("divideRoundIfNecessary");
        BigDecimal x = null;
        BigDecimal y = null;
        MathContext mc = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundIfNecessary(x, y, mc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundToFixedDecimalPlaces method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundToFixedDecimalPlaces_4args_1() {
        System.out.println("divideRoundToFixedDecimalPlaces");
        BigDecimal x = null;
        BigDecimal y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundToFixedDecimalPlaces(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideNoRounding_BigDecimal_BigDecimal() {
        System.out.println("divideNoRounding");
        BigDecimal x = null;
        BigDecimal y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_4args_2() {
        System.out.println("divideRoundIfNecessary");
        ArrayList<BigDecimal> list = null;
        BigDecimal divisor = null;
        int dp = 0;
        RoundingMode rm = null;
        ArrayList<BigDecimal> expResult = null;
        ArrayList<BigDecimal> result = Math_BigDecimal.divideRoundIfNecessary(list, divisor, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_4args_3() {
        System.out.println("divideRoundIfNecessary");
        BigDecimal x = null;
        BigInteger y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundIfNecessary(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundToFixedDecimalPlaces method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundToFixedDecimalPlaces_4args_2() {
        System.out.println("divideRoundToFixedDecimalPlaces");
        BigDecimal x = null;
        BigInteger y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundToFixedDecimalPlaces(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideNoRounding_BigDecimal_BigInteger() {
        System.out.println("divideNoRounding");
        BigDecimal x = null;
        BigInteger y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_4args_4() {
        System.out.println("divideRoundIfNecessary");
        BigInteger x = null;
        BigDecimal y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundIfNecessary(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundToFixedDecimalPlaces method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundToFixedDecimalPlaces_4args_3() {
        System.out.println("divideRoundToFixedDecimalPlaces");
        BigInteger x = null;
        BigDecimal y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundToFixedDecimalPlaces(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideNoRounding_BigInteger_BigDecimal() {
        System.out.println("divideNoRounding");
        BigInteger x = null;
        BigDecimal y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundIfNecessary_4args_5() {
        System.out.println("divideRoundIfNecessary");
        BigInteger x = null;
        BigInteger y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundIfNecessary(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideRoundToFixedDecimalPlaces method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideRoundToFixedDecimalPlaces_4args_4() {
        System.out.println("divideRoundToFixedDecimalPlaces");
        BigInteger x = null;
        BigInteger y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideRoundToFixedDecimalPlaces(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideNoRounding_BigInteger_BigInteger() {
        System.out.println("divideNoRounding");
        BigInteger x = null;
        BigInteger y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.divideNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerTestAbove method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestAbove() {
        System.out.println("powerTestAbove");
        BigDecimal compare = null;
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        int dp = 0;
        RoundingMode rm = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.powerTestAbove(compare, x, y, div, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerTestAboveNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestAboveNoRounding() {
        System.out.println("powerTestAboveNoRounding");
        BigDecimal compare = null;
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        boolean expResult = false;
        boolean result = Math_BigDecimal.powerTestAboveNoRounding(compare, x, y, div);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerTestBelow method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestBelow() {
        System.out.println("powerTestBelow");
        BigDecimal compare = null;
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        int dp = 0;
        RoundingMode rm = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.powerTestBelow(compare, x, y, div, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerTestBelowNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestBelowNoRounding() {
        System.out.println("powerTestBelowNoRounding");
        BigDecimal compare = null;
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        boolean expResult = false;
        boolean result = Math_BigDecimal.powerTestBelowNoRounding(compare, x, y, div);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_4args_1() {
        System.out.println("power");
        BigDecimal x = null;
        BigDecimal y = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.power(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_BigDecimal_BigDecimal() {
        System.out.println("powerNoRounding");
        BigDecimal x = null;
        BigDecimal y = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_4args_2() {
        System.out.println("power");
        BigDecimal x = null;
        long y = 0L;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.power(x, y, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_5args_1() {
        System.out.println("power");
        BigDecimal x = null;
        int y = 0;
        int div = 0;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.power(x, y, div, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_3args_1() {
        System.out.println("powerNoRounding");
        BigDecimal x = null;
        int y = 0;
        int div = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoRounding(x, y, div);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerUnscaled1Precision1 method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerUnscaled1Precision1() {
        System.out.println("powerUnscaled1Precision1");
        BigDecimal x = null;
        int y = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootUnscaled1Precision1 method, of class Math_BigDecimal.
     */
    @Test
    public void testRootUnscaled1Precision1() {
        System.out.println("rootUnscaled1Precision1");
        BigDecimal x = null;
        int root = 0;
        int dp = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.rootUnscaled1Precision1(x, root, dp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_5args_2() {
        System.out.println("power");
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.power(x, y, div, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_3args_2() {
        System.out.println("powerNoRounding");
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoRounding(x, y, div);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoSpecialCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheck() {
        System.out.println("powerNoSpecialCaseCheck");
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, div, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoSpecialCaseCheckNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheckNoRounding() {
        System.out.println("powerNoSpecialCaseCheckNoRounding");
        BigDecimal x = null;
        BigInteger y = null;
        int div = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoSpecialCaseCheckNoRounding(x, y, div);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_BigDecimal_int() {
        System.out.println("powerNoRounding");
        BigDecimal x = null;
        int y = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocal() {
        System.out.println("reciprocal");
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.reciprocal(x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciprocalWillBeIntegerReturnBigInteger method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocalWillBeIntegerReturnBigInteger() {
        System.out.println("reciprocalWillBeIntegerReturnBigInteger");
        BigDecimal x = null;
        BigInteger expResult = null;
        BigInteger result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigInteger(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciprocalWillBeIntegerReturnBigDecimal method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocalWillBeIntegerReturnBigDecimal() {
        System.out.println("reciprocalWillBeIntegerReturnBigDecimal");
        BigDecimal x = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigDecimal(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class Math_BigDecimal.
     */
    @Test
    public void testLog_4args_1() {
        System.out.println("log");
        int b = 0;
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.log(b, x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class Math_BigDecimal.
     */
    @Test
    public void testLog_4args_2() {
        System.out.println("log");
        BigDecimal base = null;
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.log(base, x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of round method, of class Math_BigDecimal.
     */
    @Test
    public void testRound_3args() {
        System.out.println("round");
        BigDecimal x = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.round(x, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of round method, of class Math_BigDecimal.
     */
    @Test
    public void testRound_BigDecimal_int() {
        System.out.println("round");
        BigDecimal x = null;
        int oom = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.round(x, oom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundToAndSetDecimalPlaces method, of class Math_BigDecimal.
     */
    @Test
    public void testRoundToAndSetDecimalPlaces() {
        System.out.println("roundToAndSetDecimalPlaces");
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.roundToAndSetDecimalPlaces(x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundStrippingTrailingZeros method, of class Math_BigDecimal.
     */
    @Test
    public void testRoundStrippingTrailingZeros() {
        System.out.println("roundStrippingTrailingZeros");
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.roundStrippingTrailingZeros(x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testRoundIfNecessary() {
        System.out.println("roundIfNecessary");
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.roundIfNecessary(x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecimalPlacePrecision method, of class Math_BigDecimal.
     */
    @Test
    public void testGetDecimalPlacePrecision() {
        System.out.println("getDecimalPlacePrecision");
        BigDecimal x = null;
        int sd = 0;
        int expResult = 0;
        int result = Math_BigDecimal.getDecimalPlacePrecision(x, sd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPi method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPi() {
        System.out.println("getPi");
        int dp = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPi(dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPiBy2 method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPiBy2() {
        System.out.println("getPiBy2");
        int dp = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPiBy2(dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPi2 method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPi2() {
        System.out.println("getPi2");
        int dp = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPi2(dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPiBy4 method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPiBy4() {
        System.out.println("getPiBy4");
        int dp = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPiBy4(dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEulerConstantToAMinimumDecimalPlacePrecision method, of class Math_BigDecimal.
     */
    @Test
    public void testGetEulerConstantToAMinimumDecimalPlacePrecision() {
        System.out.println("getEulerConstantToAMinimumDecimalPlacePrecision");
        int dp = 0;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getEulerConstantToAMinimumDecimalPlacePrecision(dp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEulerConstantToAFixedDecimalPlacePrecision method, of class Math_BigDecimal.
     */
    @Test
    public void testGetEulerConstantToAFixedDecimalPlacePrecision() {
        System.out.println("getEulerConstantToAFixedDecimalPlacePrecision");
        int dp = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getEulerConstantToAFixedDecimalPlacePrecision(dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    public void testExp() {
        System.out.println("exp");
        BigDecimal x = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.exp(x, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ln method, of class Math_BigDecimal.
     */
    @Test
    public void testLn() {
        System.out.println("ln");
        BigDecimal x = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.ln(x, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class Math_BigDecimal.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        Collection<BigDecimal> c = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.max(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of positionSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testPositionSignificantDigit() {
        System.out.println("positionSignificantDigit");
        BigDecimal x = null;
        int expResult = 0;
        int result = Math_BigDecimal.positionSignificantDigit(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of floorSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testFloorSignificantDigit() {
        System.out.println("floorSignificantDigit");
        BigDecimal x = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.floorSignificantDigit(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ceilingSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testCeilingSignificantDigit() {
        System.out.println("ceilingSignificantDigit");
        BigDecimal x = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testRootRoundIfNecessary_4args_1() {
        System.out.println("rootRoundIfNecessary");
        BigDecimal x = null;
        BigInteger root = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.rootRoundIfNecessary(x, root, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testRootNoRounding_BigDecimal_BigInteger() {
        System.out.println("rootNoRounding");
        BigDecimal x = null;
        BigInteger root = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.rootNoRounding(x, root);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testRootRoundIfNecessary_4args_2() {
        System.out.println("rootRoundIfNecessary");
        BigDecimal x = null;
        int root = 0;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.rootRoundIfNecessary(x, root, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testRootNoRounding_BigDecimal_int() {
        System.out.println("rootNoRounding");
        BigDecimal x = null;
        int root = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.rootNoRounding(x, root);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sqrt method, of class Math_BigDecimal.
     */
    @Test
    public void testSqrt() {
        System.out.println("sqrt");
        BigDecimal x = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.sqrt(x, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomUniformTest method, of class Math_BigDecimal.
     */
    @Test
    public void testRandomUniformTest_3args_1() {
        System.out.println("randomUniformTest");
        Random r = null;
        BigDecimal p = null;
        RoundingMode rm = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.randomUniformTest(r, p, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomUniformTest method, of class Math_BigDecimal.
     */
    @Test
    public void testRandomUniformTest_4args() {
        System.out.println("randomUniformTest");
        Random rand = null;
        BigDecimal p = null;
        int dp = 0;
        RoundingMode rm = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.randomUniformTest(rand, p, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomUniformTest method, of class Math_BigDecimal.
     */
    @Test
    public void testRandomUniformTest_3args_2() {
        System.out.println("randomUniformTest");
        Random rand = null;
        BigDecimal p = null;
        MathContext mc = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.randomUniformTest(rand, p, mc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandom method, of class Math_BigDecimal.
     */
    @Test
    public void testGetRandom_4args() {
        System.out.println("getRandom");
        Math_BigInteger bi = null;
        int dp = 0;
        BigDecimal l = null;
        BigDecimal u = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.getRandom(bi, dp, l, u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandom method, of class Math_BigDecimal.
     */
    @Test
    public void testGetRandom_Math_Number_int() {
        System.out.println("getRandom");
        Math_Number gn = null;
        int dp = 0;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.getRandom(gn, dp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEven method, of class Math_BigDecimal.
     */
    @Test
    public void testIsEven() {
        System.out.println("isEven");
        BigDecimal x = null;
        boolean expResult = false;
        boolean result = Math_BigDecimal.isEven(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cos method, of class Math_BigDecimal.
     */
    @Test
    public void testCos() {
        System.out.println("cos");
        BigDecimal x = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.cos(x, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sin method, of class Math_BigDecimal.
     */
    @Test
    public void testSin() {
        System.out.println("sin");
        BigDecimal x = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.sin(x, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sinNoCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    public void testSinNoCaseCheck() {
        System.out.println("sinNoCaseCheck");
        BigDecimal x = null;
        BigDecimal aPI = null;
        BigDecimal twoPI = null;
        BigDecimal aPIBy2 = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.sinNoCaseCheck(x, aPI, twoPI, aPIBy2, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sinAngleBetweenZeroAndPI method, of class Math_BigDecimal.
     */
    @Test
    public void testSinAngleBetweenZeroAndPI_6args_1() {
        System.out.println("sinAngleBetweenZeroAndPI");
        BigDecimal x = null;
        BigDecimal aPI = null;
        BigDecimal twoPI = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.sinAngleBetweenZeroAndPI(x, aPI, twoPI, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sinAngleBetweenZeroAndPI method, of class Math_BigDecimal.
     */
    @Test
    public void testSinAngleBetweenZeroAndPI_6args_2() {
        System.out.println("sinAngleBetweenZeroAndPI");
        BigDecimal x = null;
        BigDecimal aPI = null;
        BigDecimal twoPI = null;
        Math_BigInteger bi = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.sinAngleBetweenZeroAndPI(x, aPI, twoPI, bi, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tan method, of class Math_BigDecimal.
     */
    @Test
    public void testTan() {
        System.out.println("tan");
        BigDecimal x = null;
        Math_BigDecimal bd = null;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.tan(x, bd, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of atan method, of class Math_BigDecimal.
     */
    @Test
    public void testAtan() {
        System.out.println("atan");
        BigDecimal x = null;
        int scale = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.atan(x, scale, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acos method, of class Math_BigDecimal.
     */
    @Test
    public void testAcos() {
        System.out.println("acos");
        BigDecimal x = null;
        BigDecimal pi = null;
        int scale = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.acos(x, pi, scale, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asin method, of class Math_BigDecimal.
     */
    @Test
    public void testAsin() {
        System.out.println("asin");
        BigDecimal x = null;
        int scale = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.asin(x, scale, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBigDecimal method, of class Math_BigDecimal.
     */
    @Test
    public void testIsBigDecimal() {
        System.out.println("isBigDecimal");
        String s = "";
        boolean expResult = false;
        boolean result = Math_BigDecimal.isBigDecimal(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
