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

import ch.obermuhlner.math.big.BigDecimalMath;
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
     * Test of getEulerConstantToAFixedDecimalPlacePrecision method, of class Math_BigDecimal.
     */
    @Test
    public void testGetE() {
        System.out.println("getEulerConstantToAFixedDecimalPlacePrecision");
        int oom = 1;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = BigDecimal.valueOf(3);
        BigDecimal result = instance.getE(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -100;
        expResult = new BigDecimal(
                "2.718281828459045235360287471352662497757247093699959574966967"
                + "6277240766303535475945713821785251664274");
        result = instance.getE(oom);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -1000;
        expResult = new BigDecimal(
                "2.718281828459045235360287471352662497757247093699959574966967"
                + "627724076630353547594571382178525166427427466391932003059921"
                + "817413596629043572900334295260595630738132328627943490763233"
                + "829880753195251019011573834187930702154089149934884167509244"
                + "761460668082264800168477411853742345442437107539077744992069"
                + "551702761838606261331384583000752044933826560297606737113200"
                + "709328709127443747047230696977209310141692836819025515108657"
                + "463772111252389784425056953696770785449969967946864454905987"
                + "931636889230098793127736178215424999229576351482208269895193"
                + "668033182528869398496465105820939239829488793320362509443117"
                + "301238197068416140397019837679320683282376464804295311802328"
                + "782509819455815301756717361332069811250996181881593041690351"
                + "598888519345807273866738589422879228499892086805825749279610"
                + "484198444363463244968487560233624827041978623209002160990235"
                + "304369941849146314093431738143640546253152096183690888707016"
                + "768396424378140592714563549061303107208510383750510115747704"
                + "1718986106873969655212671546889570350354");
        result = instance.getE(oom);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    public void testExp() {
        System.out.println("exp");
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal x;
        int oom;
        BigDecimal expResult;
        BigDecimal result;
        MathContext mc;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        int test = 1;
        x = BigDecimal.valueOf(2);
        oom = -10;
        result = Math_BigDecimal.exp(x, bd, oom);
        //expResult = new BigDecimal("7.3890560989");
        mc = new MathContext(-oom + result.precision() - result.scale(), rm);
        expResult = BigDecimalMath.exp(x, mc);
        expResult = Math_BigDecimal.round(expResult, oom);
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.comparesEqualTo(result));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        test++;
        x = new BigDecimal("0.02");
        oom = -10;
        result = Math_BigDecimal.exp(x, bd, oom);
        //expResult = new BigDecimal("1.0202013400");
        mc = new MathContext(-oom + result.precision() - result.scale(), rm);
        expResult = BigDecimalMath.exp(x, mc);
        expResult = Math_BigDecimal.round(expResult, oom);
        //printFunctionTest(funcName, test, x, dp, rm, result);
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.comparesEqualTo(result));
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 3
//        test++;
//        x = new BigDecimal("0.000000012");
//        dp = 10;
//        rm = RoundingMode.HALF_UP;
//        result = Math_BigDecimal.exp(x, bd, dp, rm);
//        //expResult = new BigDecimal("1.000000012");
//        mc = new MathContext(dp + result.precision() - result.scale(), rm);
//        expResult = BigDecimalMath.exp(x, mc);
//        expResult = Math_BigDecimal.roundStrippingTrailingZeros(expResult, dp, rm);
//        //printFunctionTest(funcName, test, x, dp, rm, result);
//        //assertEquals(expResult, result);
//        assertThat(expResult, Matchers.comparesEqualTo(result));
//        // Test 4
//        test++;
//        x = new BigDecimal("0.000000012");
//        dp = 100;
//        rm = RoundingMode.HALF_UP;
//        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        expResult = new BigDecimal(
////                "1.000000012000000072000000288000000864000002073600004147200007"
////                + "1094857249499428713618285884913371614711");
//        mc = new MathContext(dp + result.precision() - result.scale(), rm);
//        expResult = BigDecimalMath.exp(x, mc);
//        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
//        //printFunctionTest(funcName, test, x, dp, rm, result);
//        //assertEquals(expResult, result);
//        assertThat(expResult, Matchers.comparesEqualTo(result));
//        // Test 5
//        test++;
//        x = new BigDecimal("1234");
//        dp = 100;
//        rm = RoundingMode.HALF_UP;
//        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        expResult = new BigDecimal(
////                "83059759373617942182585212913113567351910010438992024991210999"
////                + "5454923315008203249228622332287168900783807203236941"
////                + "4902057190611503380339135282346122079463872568744812"
////                + "9160282114608484850219955438665976430056110355929008"
////                + "1568915299754172470903436740058467313053059744618822"
////                + "7760213564550738008482852338833888837967092607345470"
////                + "7870887762743415777162495569954964980646026647274941"
////                + "9574313243340244466395229965260298922134183326397331"
////                + "1296815702676888489308238510796136610049551587722092"
////                + "6062479018036337690481712828521819397655551425938830"
////                + "264837.973417052783657749759051806294126581414124375"
////                + "6552569443184292961499614756472883606953172641990622"
////                + "460");
//        mc = new MathContext(dp + result.precision() - result.scale(), rm);
//        expResult = BigDecimalMath.exp(x, mc);
//        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
//        printFunctionTest(funcName, test, x, dp, rm, result);
//        //assertEquals(expResult, result);
//        assertThat(expResult, Matchers.comparesEqualTo(result));
//        // Test 6
//        test++;
//        x = new BigDecimal("0.5678");
//        dp = 100;
//        rm = RoundingMode.HALF_UP;
//        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        expResult = new BigDecimal(
////                "1.764381139990485997370555270712146080977921321938820437756994"
////                7484665772482841560184819887244807497714");
//        mc = new MathContext(dp + result.precision() - result.scale(), rm);
//        expResult = BigDecimalMath.exp(x, mc);
//        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
//        printFunctionTest(funcName, test, x, dp, rm, result);
//        assertEquals(expResult, result);
////        // Test 7
////        test++;
////        x = new BigDecimal("1234.5678");
////        dp = 100;
////        rm = RoundingMode.HALF_UP;
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
//////        expResult = new BigDecimal(
//////                "14654907293095947998348213850397980421762219967522365396627015"
//////                + "7446954995433819741094410764947112047906012815540251"
//////                + "0099496044260696725324177360570330992742045983853145"
//////                + "9484650997562904686479876588810478907498492770961626"
//////                + "1452461385220475510438783429614855364551372899974754"
//////                + "9385827529882582456544414514903991140262837264067071"
//////                + "5856383062081491926713908229300604276719403032558655"
//////                + "8598102530989548975007326686244681359829698224824777"
//////                + "2230516585207831661198442009726028544382751383530565"
//////                + "3512991383291690403456444424125771608013650132825780"
//////                + "7115515.06895420242229125976133533005718191453230212"
//////                + "0267300236914758300188509653349524745461710911967049"
//////                + "3253");
////        mc = new MathContext(dp + result.precision() - result.scale(), rm);
////        expResult = BigDecimalMath.exp(x, mc);
////        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result); // Test failing
////        // Test 8
////        test++;
////        x = new BigDecimal("1");
////        dp = 100;
////        rm = RoundingMode.HALF_UP;
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
//////        expResult = new BigDecimal(
//////                "2.718281828459045235360287471352662497757247093699959574966967"
//////                + "6277240766303535475945713821785251664274");
////        mc = new MathContext(dp + result.precision() - result.scale(), rm);
////        expResult = BigDecimalMath.exp(x, mc);
////        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 9
////        test++;
////        x = new BigDecimal("1");
////        dp = 1000;
////        rm = RoundingMode.HALF_UP;
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
//////        expResult = new BigDecimal(
//////                "2.718281828459045235360287471352662497757247093699959574966967"
//////                + "627724076630353547594571382178525166427427466391932003059921"
//////                + "817413596629043572900334295260595630738132328627943490763233"
//////                + "829880753195251019011573834187930702154089149934884167509244"
//////                + "761460668082264800168477411853742345442437107539077744992069"
//////                + "551702761838606261331384583000752044933826560297606737113200"
//////                + "709328709127443747047230696977209310141692836819025515108657"
//////                + "463772111252389784425056953696770785449969967946864454905987"
//////                + "931636889230098793127736178215424999229576351482208269895193"
//////                + "668033182528869398496465105820939239829488793320362509443117"
//////                + "301238197068416140397019837679320683282376464804295311802328"
//////                + "782509819455815301756717361332069811250996181881593041690351"
//////                + "598888519345807273866738589422879228499892086805825749279610"
//////                + "484198444363463244968487560233624827041978623209002160990235"
//////                + "304369941849146314093431738143640546253152096183690888707016"
//////                + "768396424378140592714563549061303107208510383750510115747704"
//////                + "1718986106873969655212671546889570350354");
////        mc = new MathContext(dp + result.precision() - result.scale(), rm);
////        expResult = BigDecimalMath.exp(x, mc);
////        expResult = Math_BigDecimal.roundIfNecessary(expResult, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
//////        // Test 10
//////        test++;
//////        x = new BigDecimal("1");
//////        dp = 1001;
//////        rm = RoundingMode.HALF_UP;
//////        expResult = new BigDecimal(
//////                "2.718281828459045235360287471352662497757247093699959574966967"
//////                + "627724076630353547594571382178525166427427466391932003059921"
//////                + "817413596629043572900334295260595630738132328627943490763233"
//////                + "829880753195251019011573834187930702154089149934884167509244"
//////                + "761460668082264800168477411853742345442437107539077744992069"
//////                + "551702761838606261331384583000752044933826560297606737113200"
//////                + "709328709127443747047230696977209310141692836819025515108657"
//////                + "463772111252389784425056953696770785449969967946864454905987"
//////                + "931636889230098793127736178215424999229576351482208269895193"
//////                + "668033182528869398496465105820939239829488793320362509443117"
//////                + "301238197068416140397019837679320683282376464804295311802328"
//////                + "782509819455815301756717361332069811250996181881593041690351"
//////                + "598888519345807273866738589422879228499892086805825749279610"
//////                + "484198444363463244968487560233624827041978623209002160990235"
//////                + "304369941849146314093431738143640546253152096183690888707016"
//////                + "768396424378140592714563549061303107208510383750510115747704"
//////                + "17189861068739696552126715468895703503540");
//////        result = Math_BigDecimal.exp(x, bd, dp, rm);
//////        printFunctionTest(funcName, test, x, dp, rm, result);
//////        assertEquals(expResult, result);
////        // Test 11
////        test++;
////        x = new BigDecimal("1");
////        dp = 2000;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal(
////                "2.718281828459045235360287471352662497757247093699959574966967"
////                + "627724076630353547594571382178525166427427466391932003059921"
////                + "817413596629043572900334295260595630738132328627943490763233"
////                + "829880753195251019011573834187930702154089149934884167509244"
////                + "761460668082264800168477411853742345442437107539077744992069"
////                + "551702761838606261331384583000752044933826560297606737113200"
////                + "709328709127443747047230696977209310141692836819025515108657"
////                + "463772111252389784425056953696770785449969967946864454905987"
////                + "931636889230098793127736178215424999229576351482208269895193"
////                + "668033182528869398496465105820939239829488793320362509443117"
////                + "301238197068416140397019837679320683282376464804295311802328"
////                + "782509819455815301756717361332069811250996181881593041690351"
////                + "598888519345807273866738589422879228499892086805825749279610"
////                + "484198444363463244968487560233624827041978623209002160990235"
////                + "304369941849146314093431738143640546253152096183690888707016"
////                + "768396424378140592714563549061303107208510383750510115747704"
////                + "171898610687396965521267154688957035035402123407849819334321"
////                + "068170121005627880235193033224745015853904730419957777093503"
////                + "660416997329725088687696640355570716226844716256079882651787"
////                + "134195124665201030592123667719432527867539855894489697096409"
////                + "754591856956380236370162112047742722836489613422516445078182"
////                + "442352948636372141740238893441247963574370263755294448337998"
////                + "016125492278509257782562092622648326277933386566481627725164"
////                + "019105900491644998289315056604725802778631864155195653244258"
////                + "698294695930801915298721172556347546396447910145904090586298"
////                + "496791287406870504895858671747985466775757320568128845920541"
////                + "334053922000113786300945560688166740016984205580403363795376"
////                + "452030402432256613527836951177883863874439662532249850654995"
////                + "886234281899707733276171783928034946501434558897071942586398"
////                + "772754710962953741521115136835062752602326484728703920764310"
////                + "059584116612054529703023647254929666938115137322753645098889"
////                + "031360205724817658511806303644281231496550704751025446501172"
////                + "721155519486685080036853228183152196003735625279449515828418"
////                + "82947876108526398140");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 12
////        test++;
////        x = new BigDecimal("2");
////        dp = 5;
////        rm = RoundingMode.HALF_UP;
////        //expResult = new BigDecimal("7.38095");
////        expResult = new BigDecimal("7.38906");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 13
////        test++;
////        x = new BigDecimal("2");
////        dp = 1001;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal(
////                "7.389056098930650227230427460575007813180315570551847324087127"
////                + "822522573796079057763384312485079121794773753161265478866123"
////                + "884603692781273374478392213398077774900122895607410753702391"
////                + "330947550682086581820269647868208404220982255234875742462541"
////                + "414679928129331888070763301019337899740729986960095303307515"
////                + "320818823684694793029913558771445683123923272764602588339996"
////                + "461212849285209678905138824663987122813726861064735626379295"
////                + "182227842948434586135287693866985752001549960148075071971293"
////                + "369418851997228882636255971941095866191479871504328397693264"
////                + "610235116312389990010513783406764498663892685615821864215577"
////                + "248492011193531621171951731747269796829345199850541848631971"
////                + "356859470229125573983561105149793681450277644807642985104182"
////                + "117055944191787683471285276497809713462504140235242158740938"
////                + "668254271570392645296404550628778001311092650138483345302646"
////                + "363141560471888117657942786348599076704527119372958723995987"
////                + "073310814961253109770593530099050329681075421090877626308572"
////                + "48500382787227614486674505649873858771575");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 14
////        test++;
////        x = new BigDecimal("2.1");
////        dp = 10;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal("8.1661699126");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 15
////        test++;
////        x = new BigDecimal("2.10111010101010101010101111");
////        dp = 20;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal("8.17524021958327462764");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 16
////        test++;
////        x = new BigDecimal("0.5");
////        dp = 20;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal("1.64872127070012814685");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 17
////        test++;
////        x = new BigDecimal("0.0000001");
////        dp = 100;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal(
////                "1.000000100000005000000166666670833333416666668055555575396825"
////                + "6448412725970017912257498096039783583187");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
////        // Test 18
////        test++;
////        x = new BigDecimal("-0.0000001");
////        dp = 100;
////        rm = RoundingMode.HALF_UP;
////        expResult = new BigDecimal(
////                "0.999999900000004999999833333337499999916666668055555535714285"
////                + "9623015845458554067460314955106642650045");
////        result = Math_BigDecimal.exp(x, bd, dp, rm);
////        printFunctionTest(funcName, test, x, dp, rm, result);
////        assertEquals(expResult, result);
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
