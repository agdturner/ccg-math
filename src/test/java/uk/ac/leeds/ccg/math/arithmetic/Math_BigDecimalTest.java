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
package uk.ac.leeds.ccg.math.arithmetic;

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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.random.Math_Random;

/**
 * @author Andy Turner
 */
public class Math_BigDecimalTest {

    public Math_BigDecimalTest() {
    }

//    public static void main(String[] args) {
//        new Math_BigDecimalTest().testPowerNoSpecialCaseCheckNoRounding();
//    }
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
     * Test of multiply method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiply_4args_1() {
        System.out.println("multiply");
        BigDecimal x;
        BigInteger y;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1.1");
        y = new BigInteger("1");
        rm = RoundingMode.HALF_UP;
        oom = -2;
        expResult = new BigDecimal("1.10");
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("1.123456789");
        y = new BigInteger("1234");
        oom = -9;
        expResult = new BigDecimal("1386.345677626");
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        //x.multiply(new BigDecimal(y));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("1.123456789");
        y = new BigInteger("1234");
        oom = -8;
        expResult = new BigDecimal("1386.34567763");
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        //x.multiply(new BigDecimal(y));
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiply method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiply_4args_2() {
        System.out.println("multiply");
        BigDecimal x;
        BigDecimal y;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1.1");
        y = new BigDecimal("1.1");
        rm = RoundingMode.HALF_UP;
        oom = -2;
        expResult = new BigDecimal("1.21");
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("1.123456789");
        y = new BigDecimal("1234.5678");
        oom = -13;
        expResult = new BigDecimal("1386.9835763907942");
        //System.out.println(x.multiply(y));
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("99999.99999");
        y = new BigDecimal("9999999.9999999");
        oom = -12;
        expResult = new BigDecimal("999999999899.990000000001");
        //System.out.println(x.multiply(y));
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivide_4args_1() {
        System.out.println("divide");
        BigDecimal x;
        BigDecimal y;
        int oom = -5;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1");
        y = new BigDecimal("2");
        expResult = new BigDecimal("0.5");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        y = new BigDecimal("3");
        expResult = new BigDecimal("0.33333");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        y = new BigDecimal("4");
        expResult = new BigDecimal("0.25");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = new BigDecimal("5");
        expResult = new BigDecimal("0.2");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        y = new BigDecimal("6");
        expResult = new BigDecimal("0.16667");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        y = new BigDecimal("7");
        expResult = new BigDecimal("0.14286");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("1");
        y = new BigDecimal("8");
        expResult = new BigDecimal("0.125");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        y = new BigDecimal("9");
        expResult = new BigDecimal("0.11111");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -10;
        x = new BigDecimal("987654321");
        y = new BigDecimal("123456789");
        // 8.000000072900000663390006036849....
        expResult = new BigDecimal("8.0000000729");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = -30;
        x = new BigDecimal("987654321");
        y = new BigDecimal("123456789");
        expResult = new BigDecimal("8.000000072900000663390006036849");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        oom = -10;
        x = new BigDecimal("987654321");
        y = new BigDecimal("12345");
        // 80004.400243013365735115431348724
        expResult = new BigDecimal("80004.4002430134");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        oom = 1;
        x = new BigDecimal("987654321");
        y = new BigDecimal("12345");
        // 80004.400243013365735115431348724
        expResult = new BigDecimal("80000");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        int test = 1;
        x = new BigDecimal("30.121");
        y = new BigDecimal("0.0121");
        rm = RoundingMode.HALF_UP;
        oom = -6;
        expResult = new BigDecimal("2489.338843");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 14
        test++;
        x = new BigDecimal("30.121");
        y = new BigDecimal("0.0121");
        rm = RoundingMode.HALF_UP;
        oom = -28;
        expResult = new BigDecimal("2489.3388429752066115702479338843");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 15
        test++;
        x = new BigDecimal("0.030121");
        y = new BigDecimal("0.0000121");
        rm = RoundingMode.HALF_UP;
        oom = -4;
        expResult = new BigDecimal("2489.3388");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 16
        test++;
        x = new BigDecimal("0.030121");
        y = new BigDecimal("0.0000121");
        rm = RoundingMode.HALF_UP;
        oom = -6;
        expResult = new BigDecimal("2489.338843");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 17
        test++;
        x = new BigDecimal("30.121");
        y = new BigDecimal("30.1215415431245365365754725456435315432513245135");
        rm = RoundingMode.HALF_UP;
        oom = -6;
        expResult = new BigDecimal("0.999982");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 18
        test++;
        x = new BigDecimal("30.121");
        y = new BigDecimal("30.1215415431245365365754725456435315432513245135");
        rm = RoundingMode.HALF_UP;
        oom = -30;
        expResult = new BigDecimal("0.999982021400738696662826767313");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 19
        test++;
        x = new BigDecimal("30.121");
        y = new BigDecimal("30.1215415431245365365754725456435315432513245135");
        rm = RoundingMode.HALF_UP;
        oom = -25;
        expResult = new BigDecimal("0.9999820214007386966628268");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testDivideNoRounding_BigDecimal_BigDecimal() {
        System.out.println("divideNoRounding");
        BigDecimal x;
        BigDecimal y;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1");
        y = new BigDecimal("2");
        expResult = new BigDecimal("0.5");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(ArithmeticException.class, () -> {
            Math_BigDecimal.divideNoRounding(new BigDecimal("1"), new BigDecimal("3"));
        });
        // Test 3
        x = new BigDecimal("1");
        y = new BigDecimal("4");
        expResult = new BigDecimal("0.25");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("1");
        y = new BigDecimal("5");
        expResult = new BigDecimal("0.2");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        assertThrows(ArithmeticException.class, () -> {
            Math_BigDecimal.divideNoRounding(new BigDecimal("1"), new BigDecimal("6"));
        });
        // Test 6
        assertThrows(ArithmeticException.class, () -> {
            Math_BigDecimal.divideNoRounding(new BigDecimal("1"), new BigDecimal("7"));
        });
        // Test 7
        x = new BigDecimal("1");
        y = new BigDecimal("8");
        expResult = new BigDecimal("0.125");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        assertThrows(ArithmeticException.class, () -> {
            Math_BigDecimal.divideNoRounding(new BigDecimal("1"), new BigDecimal("9"));
        });
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal. Test covered
     * by {@link #testDivideNoRounding_BigDecimal_BigDecimal()}.
     */
    @Test
    @Disabled
    public void testDivideNoRounding_BigDecimal_BigInteger() {
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivide_4args_3() {
        System.out.println("divide");
        BigInteger x;
        BigDecimal y;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigInteger("10030");
        y = new BigDecimal("0.0121");
        rm = RoundingMode.HALF_UP;
        oom = -100;
        expResult = new BigDecimal(
                "828925.6198347107438016528925619834710743801652892561983471074"
                + "380165289256198347107438016528925619834710744");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     *
     * Test covered by {@link #testDivideNoRounding_BigDecimal_BigDecimal()}.
     */
    @Test
    @Disabled
    public void testDivideNoRounding_BigInteger_BigDecimal() {
    }

    /**
     * Test of divideRoundIfNecessary method, of class Math_BigDecimal.
     */
    @Test
    public void testDivide_4args_4() {
        System.out.println("divide");
        BigInteger x;
        BigInteger y;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigInteger("10030");
        y = new BigInteger("23456789");
        rm = RoundingMode.HALF_UP;
        oom = -100;
        expResult = new BigDecimal(
                "0.000427594757321643640141879606795286430721613260877266705174"
                + "3527215084724511952595046150604841950021");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divideNoRounding method, of class Math_BigDecimal.
     *
     * Test covered by {@link #testDivideNoRounding_BigDecimal_BigDecimal()}.
     */
    @Test
    @Disabled
    public void testDivideNoRounding_BigInteger_BigInteger() {
    }

    /**
     * Test of powerTestAbove method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestAbove_5args_1() {
        System.out.println("powerTestAbove");
        BigDecimal compare;
        BigDecimal x;
        BigInteger y;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestAbove(compare, x, y, oom, rm));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestAbove(compare, x, y, oom, rm));
    }

    /**
     * Test of powerTestAbove method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestAbove_5args_2() {
        System.out.println("powerTestAbove");
        BigDecimal compare;
        BigDecimal x;
        int y;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = 2;
        assertFalse(Math_BigDecimal.powerTestAbove(compare, x, y, oom, rm));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = 2;
        assertTrue(Math_BigDecimal.powerTestAbove(compare, x, y, oom, rm));
    }

    /**
     * Test of powerTestAboveNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestAboveNoRounding() {
        System.out.println("powerTestAboveNoRounding");
        BigDecimal compare;
        BigDecimal x;
        BigInteger y;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestAboveNoRounding(compare, x, y));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestAboveNoRounding(compare, x, y));
    }

    /**
     * Test of powerTestBelow method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestBelow() {
        System.out.println("powerTestBelow");
        BigDecimal compare;
        BigDecimal x;
        BigInteger y;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestBelow(compare, x, y, oom, rm));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestBelow(compare, x, y, oom, rm));
    }

    /**
     * Test of powerTestBelowNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerTestBelowNoRounding() {
        System.out.println("powerTestBelowNoRounding");
        BigDecimal compare;
        BigDecimal x;
        BigInteger y;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestBelowNoRounding(compare, x, y));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestBelowNoRounding(compare, x, y));
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_4args_1() {
        System.out.println("power");
        BigDecimal x;
        BigDecimal y;
        int oom = -20;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("5");
        y = new BigDecimal("2");
        expResult = new BigDecimal("25");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("5.1");
        y = new BigDecimal("2");
        expResult = new BigDecimal("26.01");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("5");
        y = new BigDecimal("0.1");
        expResult = new BigDecimal("1.17461894308801900591");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("5");
        y = new BigDecimal("0.01");
        expResult = new BigDecimal("1.01622459126732563582");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("5");
        y = new BigDecimal("0.11");
        expResult = new BigDecimal("1.19367665533448014709");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("5");
        y = new BigDecimal("0.3");
        expResult = new BigDecimal("1.62065659669276243515");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("5");
        y = new BigDecimal("0.033");
        expResult = new BigDecimal("1.05454716902302751127");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("5");
        y = new BigDecimal("0.003");
        expResult = new BigDecimal("1.00483998882684405102");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("5");
        y = new BigDecimal("0.2");
        expResult = new BigDecimal("1.37972966146121483239");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("5");
        y = new BigDecimal("0.12");
        expResult = new BigDecimal("1.21304357117263042652");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("5");
        y = new BigDecimal("0.012");
        expResult = new BigDecimal("1.01950096232366887641");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 14 Why does it take a long time?
//        x = new BigDecimal("5");
//        y = new BigDecimal("0.0012");
//        expResult = new BigDecimal("1.00193319170523105054");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 15: Why does it take a long time?
//        x = new BigDecimal("5");
//        y = new BigDecimal("0.00012");
//        expResult = new BigDecimal("1.00019315120078363320");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 2
//        x = new BigDecimal("5.1");
//        y = new BigDecimal("0.000122");
//        expResult = new BigDecimal(
//                "1.000198787101384878642452720694744868971170929418775037222019"
//                + "2032254156701221277775377001688988217835");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 3
//        x = new BigDecimal("5.1");
//        y = new BigDecimal("-0.000122");
//        expResult = new BigDecimal(
//                "0.999801252407073026532411361647635459308346680589674812109435"
//                + "6355033399665871513258400090997521879138");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 4
//        x = new BigDecimal("5.1");
//        y = new BigDecimal("-0.000122");
//        expResult = new BigDecimal(
//                "0.999801252407073026532411361647635459308346680589674812109435"
//                + "6355033399665871513258400090997521879138");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 4
//        x = new BigDecimal("5.1");
//        y = new BigDecimal("-0.000000002");
//        expResult = new BigDecimal(
//                "0.999999996741518925848289291581390088493740454739378865003717"
//                + "6765269179691805288720372301300181098212");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 5
//        x = new BigDecimal("5.1");
//        y = new BigDecimal("-0.000000000002");
//        expResult = new BigDecimal(
//                "0.999999999996741518920544748674219018662252981309537110886643"
//                + "3325558136648752946348246771904715458001");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 6
//        x = new BigDecimal(
//                "2.718281828459045235360287471352662497757247093699959574966967"
//                + "627724076630353547594571382178525166427427466391932003059921"
//                + "817413596629043572900334295260595630738132328627943490763233"
//                + "829880753195251019011573834187930702154089149934884167509244"
//                + "761460668082264800168477411853742345442437107539077744992069"
//                + "551702761838606261331384583000752044933826560297606737113200"
//                + "709328709127443747047230696977209310141692836819025515108657"
//                + "463772111252389784425056953696770785449969967946864454905987"
//                + "931636889230098793127736178215424999229576351482208269895193"
//                + "668033182528869398496465105820939239829488793320362509443117"
//                + "301238197068416140397019837679320683282376464804295311802328"
//                + "782509819455815301756717361332069811250996181881593041690351"
//                + "598888519345807273866738589422879228499892086805825749279610"
//                + "484198444363463244968487560233624827041978623209002160990235"
//                + "304369941849146314093431738143640546253152096183690888707016"
//                + "768396424378140592714563549061303107208510383750510115747704"
//                + "1718986106873969655212671546889570350354");
//        y = x.negate();
//        expResult = new BigDecimal(
//                "0.065988035845312537076790187596846424938577048252796436402473"
//                + "5415723927466340880862459929685632483709");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 7
//        x = new BigDecimal("0.9");
//        y = new BigDecimal("200");
//        expResult = new BigDecimal(
//                "0.000000000705507910865533257124642715759347962165079496127873"
//                + "1576287122320926208555158293415657929853");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 8
//        x = new BigDecimal("1.1");
//        y = new BigDecimal("200");
////        expResult = new BigDecimal(
////                "189905276.4604618242121820463954116340585832240009877848127251"
////                + "456103762646167989140750662066593328455813588159");
//        expResult = new BigDecimal(
//                "189905276.4604618242121820463954116340585832240009877848127251"
//                + "456103762646167989140750662066593328455813588181");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 9
//        x = new BigDecimal("1.0000000000000000000000000000000001");
//        y = new BigDecimal("20000");
////        expResult = new BigDecimal(
////                "1.000000000000000000000000000002000000000000000000000000000001"
////                + "9999000000000000000000000000013331331204");
//        expResult = new BigDecimal(
//                "1.000000000000000000000000000002000000000000000000000000000001"
//                + "9999000000000000000000000000013331333400");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 10
//        x = new BigDecimal("1.000000000000000001234567");
//        y = new BigDecimal("2000078764654345654");
//        expResult = new BigDecimal(
//                "11.80925729618850935047900283877251871123117528236149368178447"
//                + "43606317427155216550611985572658924014225");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 11
//        x = new BigDecimal("0.9");
//        y = new BigDecimal("0.9");
//        expResult = new BigDecimal(
//                "0.909532576082962189535366090754262974443473210154553394006625"
//                + "8156584379857622915775444454069734604216");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 12
//        x = new BigDecimal("0.1");
//        y = new BigDecimal("0.9");
//        expResult = new BigDecimal(
//                "0.125892541179416721042395410639580060609361740946693106910792"
//                + "301952664761578250202412105096627594617");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 13
//        x = new BigDecimal("0.9");
//        y = new BigDecimal("0.1");
//        expResult = new BigDecimal(
//                "0.989519258206214392646230170419804832155538415337091539600605"
//                + "544414212962464564723600065458219541611");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 14
//        x = new BigDecimal("0.9");
//        y = new BigDecimal("0.12");
//        expResult = new BigDecimal(
//                "0.987436328376606708739063494229909542239622211966688276138278"
//                + "1495703008692225765762583023898416587461");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 15
//        x = new BigDecimal("0.09");
//        y = new BigDecimal("0.12");
//        expResult = new BigDecimal(
//                "0.749047055475647117310421468815370379819904146132649930111430"
//                + "8616900168333963941855154477042245424988");
////        expResult = new BigDecimal(
////                "0.749047055475647117310421468815370379819904146132649930111430"
////                + "8616900168333963941855154477042245371721");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 16
//        x = new BigDecimal("0.1");
//        y = new BigDecimal("0.999991");
//        expResult = new BigDecimal(
//                "0.100002072348056530317390970017713311383030160316867649898675"
//                + "1042536233958380984224300502095831919852");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 17
//        x = new BigDecimal("0.1");
//        y = new BigDecimal("0.9999999991");
//        expResult = new BigDecimal(
//                //0.10000000020723265858419098518432
//                "0.100000000207232449276900938780572607600670362630328624897040"
//                + "9053787443199517073876579178209190227768");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 18
//        x = new BigDecimal("0.92");
//        y = new BigDecimal(
//                "0.0040983606557377051313184601610828394768759608268737792968");
//        expResult = new BigDecimal(
//                "0.999658330476842250517859191452511308247518473356535240882710"
//                + "507544024872721215314306435063439340094");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 19
//        x = new BigDecimal("-32");
//        y = new BigDecimal("0.2");
//        expResult = new BigDecimal("-2");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_BigDecimal_BigDecimal() {
        System.out.println("powerNoRounding");
        BigDecimal x;
        int y;
        BigDecimal expResult;
        BigDecimal result;
        x = new BigDecimal("5.1");
        y = 2;
        // Test 1
        expResult = new BigDecimal("26.01");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("5.1");
        y = 20;
        expResult = new BigDecimal("141710986707530.43575626125424226001");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("5.10");
        y = 20;
        expResult = new BigDecimal(
                "141710986707530.4357562612542422600100000000000000000000");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = 20;
        expResult = new BigDecimal(
                "141710986707530.4357562612542422600100000000000055572935963737"
                + "425786769119310690200000000000001035182140500991264655503202"
                + "846190000000000000012178613417658720760652978857014000000000"
                + "000000101488445147156006338774823808450000000000000000636790"
                + "244060586706439371443504000000000000000003121520804218562286"
                + "467507076000000000000000000012241258055759067790068655200000"
                + "000000000000000039004008510997029723257970000000000000000000"
                + "000101971264081037986204596000000000000000000000000219938020"
                + "566944676127560000000000000000000000000392046382472272149960"
                + "000000000000000000000000000576538797753341397000000000000000"
                + "000000000000000695672757470095200000000000000000000000000000"
                + "000682032115166760000000000000000000000000000000000534927149"
                + "150400000000000000000000000000000000000327773988450000000000"
                + "000000000000000000000000000151222140000000000000000000000000"
                + "000000000000000049419000000000000000000000000000000000000000"
                + "000010200000000000000000000000000000000000000000000001");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("5.2");
        y = 2;
        expResult = new BigDecimal("27.04");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("5.3");
        y = 2;
        expResult = new BigDecimal("28.09");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("5.4");
        y = 2;
        expResult = new BigDecimal("29.16");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("5.5");
        y = 2;
        expResult = new BigDecimal("30.25");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("5.6");
        y = 2;
        expResult = new BigDecimal("31.36");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("5.7");
        y = 2;
        expResult = new BigDecimal("32.49");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("5.8");
        y = 2;
        expResult = new BigDecimal("33.64");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("5.9");
        y = 2;
        expResult = new BigDecimal("34.81");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_4args_3() {
        System.out.println("power");
        BigDecimal x = null;
        int y = 0;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -10;
        x = new BigDecimal("5.1");
        y = 13;
        expResult = new BigDecimal("1579109656.3156692196");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("5.1");
        y = 13;
        expResult = new BigDecimal("1579109656.3156692196");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 3
//        x = new BigDecimal("5.1");
//        y = 117;
//        expResult = new BigDecimal(
//                "61053505308866480538551405717028781301447815633741042337063285"
//                + "637565296816330773386.1309180477");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 4
//        x = new BigDecimal("3.14159265");
//        y = 10;
//        expResult = new BigDecimal("93648.0464059980");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 5
//        oom = -20;
//        x = new BigDecimal("3.14159265");
//        y = 10;
//        expResult = new BigDecimal("93648.04640599799415742896");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 6
//        oom = -30;
//        x = new BigDecimal("3.14159265");
//        y = 10;
//        expResult = new BigDecimal("93648.046405997994157428955669854799");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 7
//        x = new BigDecimal("3");
//        y = -10;
//        expResult = new BigDecimal("0.000016935087808430286711036597");
//        result = Math_BigDecimal.power(x, y, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 8
//        oom = -40;
//        x = new BigDecimal("3");
//        y = -10;
//        div = 3;
//        expResult = new BigDecimal(
//                "0.0000169350878084302867110365967247540178");
//        result = Math_BigDecimal.power(x, y, div, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 9
//        rm = RoundingMode.HALF_UP;
//        oom = -40;
//        x = new BigDecimal("3.00001");
//        y = -10;
//        div = 3;
//        expResult = new BigDecimal("0.0000169345233158524213740774680469856220");
//        result = Math_BigDecimal.power(x, y, div, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 10
//        oom = -130;
//        x = new BigDecimal("3.00001");
//        y = -178;
//        div = 3;
//        expResult = new BigDecimal("1.180752785902410385735038642706422321146970536E-85");
//        result = Math_BigDecimal.power(x, y, div, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 11
//        oom = -30;
//        x = new BigDecimal("0.1");
//        y = -10;
//        div = 3;
//        expResult = new BigDecimal("10000000000");
//        result = Math_BigDecimal.power(x, y, div, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 12
//        oom = -100;
//        x = new BigDecimal("0.99999999999999998");
//        y = -678;
//        div = 3;
//        expResult = new BigDecimal("1.00000000000001356000000000009207240000000"
//                + "04173948800000014212295664000038771142571392088268967920869");
//        result = Math_BigDecimal.power(x, y, div, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerUnscaled1Precision1 method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerUnscaled1Precision1() {
        System.out.println("powerUnscaled1Precision1");
        BigDecimal x;
        int y;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("10");
        y = 2;
        expResult = new BigDecimal("100");
        result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.1");
        y = 2;
        expResult = new BigDecimal("0.01");
        result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("100");
        y = 3;
        expResult = new BigDecimal("1000000");
        result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
        // Test 4
        x = new BigDecimal("0.01");
        y = 3;
        expResult = new BigDecimal("0.000001");
        result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
        // Test 5
        x = new BigDecimal("0.0001");
        y = 20;
        expResult = new BigDecimal("1e-80");
        result = Math_BigDecimal.powerUnscaled1Precision1(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    public void testPower_4args_2() {
        System.out.println("power");
        BigDecimal x;
        BigInteger y;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        rm = RoundingMode.HALF_UP;
        oom = -100;
        x = new BigDecimal("1");
        y = new BigInteger("2");
        expResult = new BigDecimal("1");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("2");
        y = new BigInteger("2");
        expResult = new BigDecimal("4");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("2");
        y = new BigInteger("2");
        expResult = new BigDecimal("4");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("2");
        y = new BigInteger("3");
        expResult = new BigDecimal("8");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("2");
        y = new BigInteger("3");
        expResult = new BigDecimal("8");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("2");
        y = new BigInteger("4");
        expResult = new BigDecimal("16");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("2");
        y = new BigInteger("4");
        expResult = new BigDecimal("16");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("2");
        y = new BigInteger("4");
        expResult = new BigDecimal("16");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("2");
        y = new BigInteger("8");
        expResult = new BigDecimal("256");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("2");
        y = new BigInteger("10");
        expResult = new BigDecimal("1024");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("2");
        y = new BigInteger("11");
        expResult = new BigDecimal("2048");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("2");
        y = new BigInteger("65");
        expResult = new BigDecimal("36893488147419103232");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("2");
        y = new BigInteger("65");
        expResult = new BigDecimal("36893488147419103232");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 14
        x = new BigDecimal("5.1");
        y = new BigInteger("117");
        expResult = new BigDecimal(
                "61053505308866480538551405717028781301447815633741042337063285"
                + "637565092974889024013.95170173462077405598021935614839617008"
                + "687986713833910778545970838802156925519570602363713165609193"
                + "54");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 15
        x = new BigDecimal("0.1122");
        y = new BigInteger("10");
        expResult = new BigDecimal("3.161757585765373988090713703424E-10");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 16
        x = new BigDecimal("0.001122");
        y = new BigInteger("1034");
        expResult = new BigDecimal("0");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 17
        oom = -10000;
        x = new BigDecimal("0.001122");
        y = new BigInteger("1034");
        expResult = new BigDecimal(
                "4.927357297629103147819324052152950275036601937850815403560445"
                + "236083700295574927045053216349848466569243304082385070586331"
                + "538839593433725186784660546994199784794405666417896880399734"
                + "392822152057717524084020821246913991425766042466809293424533"
                + "034972894114375629109690271014735839013650881281424404883404"
                + "952627178442044469256079442076023740811913748135717170634334"
                + "445973831766327749399479523802859361773136381923354508933461"
                + "618023938839877142689146213275481918263220429948169622162364"
                + "922337446992380378212665889994137343237617557677875626890254"
                + "295259706779905218435234455553753174796971982647768423787118"
                + "272807358331025975590389280878424837370222082758234244287706"
                + "456396898801155129679937344396324435783061607404812097234868"
                + "223924852041257089352428950170312134239367962109331109855790"
                + "350671174934941334586824970429002651992224894052283952295809"
                + "492316458272466319934117994496319511555952649951450177044090"
                + "043910161446057619100938879513439717388717664624689258094857"
                + "330731919006273181884439658027453268974869186067292641819566"
                + "514956984747329303816033104406738539019781020532599948507727"
                + "988529526687300872650069258742566120276271867340143033851056"
                + "789292747869626164371979694745519442059826501796596482340361"
                + "594017578274163660408966234284546668539961240874506432650150"
                + "962204807023954584202461954563649423166017673746765136181101"
                + "831836280153612589414848846170450328866059576479132229822554"
                + "368961449058866258909507183670738364373401695108104036319657"
                + "411787253318074758252059071601242090847802375207964848101217"
                + "240762394670426166686940320598217178293910712816082566723018"
                + "277853037528206089402413221557917916227355715767056870601127"
                + "171796774936494333940602256515033485043086215408933562045459"
                + "236127236363165656336833326716457246722275433533702177482434"
                + "176687040511148125279215965350737478875515854849066442403789"
                + "652291244024162928193197244590528924711755971667621792047252"
                + "734277545494085654143191267754076377548548279266561650484126"
                + "999279685602865864569540281127913486201906412912817807453099"
                + "410562658382724925596828822866110767698571645903112559112400"
                + "078560969490848054213197577561936787146926676157384475182508"
                + "100587514117344003219530478249469418533026074576366867297461"
                + "387290316061973897819596285809715456825028542616230425563570"
                + "433524229591761963338129315616129109658611061754492874730583"
                + "528968182186218155776892535845513654601365679547807926196958"
                + "603519737159661537672735062412090435893226036152222921236129"
                + "013231354189711006651658658781804723559652913654659746633325"
                + "405103399891184766762208994172861736113917300597267029682915"
                + "954208886059165634882109331499342718272753691056654173191966"
                + "435899634973815441157597773782979741183717596828280117000116"
                + "229752327279731633753836817650405027400452401990752146863213"
                + "788033245337415675675000281001230106541302735966668688378528"
                + "736203480118517708977302740545122325040451589429208511769945"
                + "859450470074880842465140671770861465213884520202745850889929"
                + "851209699075320366569240296373127293730488339784386301462443"
                + "393273966477304719599878835469922158098817241892186876600894"
                + "811711817735060315993183660630801180299388370977016001053753"
                + "213675590341472292056479804429326923743981250071473193821515"
                + "915066035479225636617768291794944E-3051");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 18
        x = new BigDecimal("0.0012345678991622");
        y = new BigInteger("1234");
        expResult = new BigDecimal(
                "8.501309043595503534858369295274746396278923131310551482437978"
                + "961099991282995536024130634771054723737567510248365858917231"
                + "808995514915177754278268538033054531901346973695941956769685"
                + "729610800471300015155325762523212459304928882008541290136448"
                + "164697202788344400871671423873916602607681361130208799214930"
                + "190634832273692766016402172989257259574619028114555353482935"
                + "885283201670178379414086061377809789063858635712731974532512"
                + "430310980023129185818120757432867331649583134291701959195137"
                + "729994754704454960784837427814154835399466742746222888199322"
                + "185691398841244989033783917831898297087694127334821959823479"
                + "560817125415700617393543446659425729143468979736023831630340"
                + "291883200802015650903509389558971413332344895685883054561707"
                + "854340007144964401666766582625306847399794811880488947288749"
                + "500445957984619975261662034931894815609905146393901404336951"
                + "726392362334745376069094901376259664138106029706873774338173"
                + "998329612594142761937455247432615013087149603712001682535027"
                + "487425695214888131492006015586387144812760140264885210350022"
                + "385571183468262416433409054829313787356217286799499124417308"
                + "643563055390840771184992546154685006868848737894861390391409"
                + "652736342833892345333471166044356661607374015039399319274718"
                + "629478937611112391808059758444081736104635530893031155401711"
                + "673096847613763965152316408173575789911244622621569819962160"
                + "974284289755137304410385414276774910129688662502430115684211"
                + "305043916746636944858613978083992656812345045926463710124242"
                + "872379317940073605025489112358266685006669123714884708927439"
                + "575426947912616191851513190852767791448069336538448488951966"
                + "436659843996475485313360233640553751372827806934551841424342"
                + "157725009543570890223152253563288261584639816015870633616805"
                + "683134344263615367388668849038519919346201987837309595807403"
                + "718115617674167506300541837716501813908555972354625254540683"
                + "928046281922405458446981053787881445368430905255377016518170"
                + "113140563107615310054843269354650004168801318589079765801259"
                + "842775168756959968188252335431745319932389096359454986676414"
                + "011610028757894591810956706626561300829131209434392416569502"
                + "141827755845524803576284528831533313691000225371337937565727"
                + "416258357361247370778641312576571435906709049598433637869426"
                + "245593194706827798900884879338116900931898068707334976831839"
                + "215750020927621680289776239758865004682751232836834561154530"
                + "197215226122620319389943412936769554657213178728950944501797"
                + "039022570088560027217747633236633920979496320588682775057916"
                + "009158350376537176872951520778371161324081739839202968670224"
                + "229972959153366128266511727756008999699629586301651148690711"
                + "371796038695569976090142721415725908235169696667844504772472"
                + "736976544942017666534880236850216672807647363830166005657534"
                + "171567373816577363431316616044039394078774589888717044488084"
                + "644522676289533172266925566791315322656094992410632065645396"
                + "380509305484786578772631063641740921733037857157400156071911"
                + "020031521457831211857239554432678681664642838766003136008130"
                + "129077927242892574138931736152628228463796956539174013954892"
                + "792553892387483738547963113646915043658437009663206139368176"
                + "159964357304162665535919759783652929030066644758752767833010"
                + "410369287010756428079116908776272929715107491462040237349428"
                + "684250204705736280788513376326532955500066049276222779281096"
                + "096756928936140563456490724817027743722271023583886738488933"
                + "061353838760233314417772662177147902966575423722023809984290"
                + "392061646793078078814005028975430990216115523464876904124422"
                + "223862890087913210798522753191413395243907996283739569642210"
                + "244602147214777891517294936823722328014924206255329285478843"
                + "242768703400460960231139714600064620770464936072766209246068"
                + "001436415856197371778286474381782580916353234797514144983276"
                + "211337188385353920068002449139542467912279400044121727926761"
                + "803852730768611548931120161525698845041370421892412093778104"
                + "745283421666724059788026496249189559093802977763691408091609"
                + "142134121969282634573919857399828585560634031473179571643083"
                + "783808705265238498624915950546029209543773191347021783369878"
                + "064938930413118498634885015874247944667984191553912172650072"
                + "707360380125285762521870414832881235235495810169441806505751"
                + "410420427162949183602660804248108261697912973545722496566734"
                + "031552691187532397080861867628983441089434386222107891651768"
                + "009689455386050198483378408658051348635073517417074986893516"
                + "809658468756417818261992978162100241689365042289910302048880"
                + "668879802020609547163584043402329200170942940017537437036586"
                + "839966336108968391846619896256151126487470606396269903867297"
                + "906077328929013196940913689665108348787185943866272099058941"
                + "901510264511251756607818028877192639267509937281747260532310"
                + "944163987018930018783955113759662369114939078486137333961539"
                + "526873631068991739648940494355858390232176614093384693622420"
                + "011139723392319121907335218016788125567617837405231286280386"
                + "694610492170510062764269879513345743770334391701759618750592"
                + "095118260919871813035646088502264831152265077198437063338482"
                + "130789544767815219698964668358168356688056592917908797647821"
                + "705412172370423395311733554057242343877758435748283702730078"
                + "242470967847669725871834821527137084885921073698664574920903"
                + "530527792194811676429277067815580464712394558156962539394897"
                + "059280295797315118741492881864032577951288182863069610930500"
                + "059224365654476691962029610532184783002186573781854736492904"
                + "565915933709278870064582939867368802415510056126011853717941"
                + "064181885081220156682835784634844622353783970271341998461216"
                + "475057645889401559331454188428301965249141374467473887220244"
                + "934834282333506871494750086904041314916341348107129943354661"
                + "625814231790475354074385012903967994878807059698070011985709"
                + "961271426096489639456300191830839942172279156535627221475915"
                + "573789110618901998835717141475677114720083752104740057253833"
                + "190765965547964444608147838708615265918002739090946164599149"
                + "408169445447456338009921249339171373172788937040615057462928"
                + "972482269975680079544132583259441223009437283042950763862314"
                + "391176503883320604664808288433482496178775762776512005388047"
                + "410260111049601002224431547061166136239769725780929612481677"
                + "155332655223655783365346890417816768355980483289894226077957"
                + "044439824627196514323213427796618252575012014386085013928263"
                + "834276287574437588709514610827522468792615834939104288133068"
                + "375259673875098070501886720795065319174689340681481756754150"
                + "297808463032191538533928197268662625849497992170965785935507"
                + "140926152173690023441115066205059469854367305763215156748232"
                + "703087457290093111633254634388438823165743886696298824349345"
                + "656496377276196412103923039892904397552591077832462093232455"
                + "91101586589950430363520701982464337600308855098521E-3590");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 19
        oom = -1000;
        x = new BigDecimal("-0.0012345678991622");
        y = new BigInteger("12");
        expResult = new BigDecimal(
                "-1.25365998693568959676678134576311434191784180749571395739232"
                + "878897951918031315109522890632250338730775085419737311675655"
                + "57247684654582741706554500513064226816E-35");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 20
        oom = -500;
        x = new BigDecimal("-0.0012345678991622");
        y = new BigInteger("43");
        expResult = new BigDecimal(
                "8.612795922579100431286467891265924977006154049807999513099100"
                + "860398926168811375495791586088327052448905995372673275625194"
                + "854714215437648883074238808406389133715622539770003076165360"
                + "359498335943518537092852659172457402813915377353569315634343"
                + "600751669994990651558870998167491502327268726540343541308219"
                + "777136364418488951763650222930343714459852346095478286203471"
                + "16113445581957E-126");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_BigDecimal_BigInteger() {
        System.out.println("powerNoRounding");
        BigDecimal x;
        BigInteger y;
        //int div;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        int test = 1;
        x = new BigDecimal("5.1");
        y = new BigInteger("20");
        //div = 2;
        expResult = new BigDecimal("141710986707530.43575626125424226001");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // Test 2
        test++;
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = new BigInteger("20");
        expResult = new BigDecimal(
                "141710986707530.4357562612542422600100000000000055572935963737"
                + "425786769119310690200000000000001035182140500991264655503202"
                + "846190000000000000012178613417658720760652978857014000000000"
                + "000000101488445147156006338774823808450000000000000000636790"
                + "244060586706439371443504000000000000000003121520804218562286"
                + "467507076000000000000000000012241258055759067790068655200000"
                + "000000000000000039004008510997029723257970000000000000000000"
                + "000101971264081037986204596000000000000000000000000219938020"
                + "566944676127560000000000000000000000000392046382472272149960"
                + "000000000000000000000000000576538797753341397000000000000000"
                + "000000000000000695672757470095200000000000000000000000000000"
                + "000682032115166760000000000000000000000000000000000534927149"
                + "150400000000000000000000000000000000000327773988450000000000"
                + "000000000000000000000000000151222140000000000000000000000000"
                + "000000000000000049419000000000000000000000000000000000000000"
                + "000010200000000000000000000000000000000000000000000001");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // Test 3
        test++;
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = new BigInteger("20");
        expResult = new BigDecimal(
                "141710986707530.4357562612542422600100000000000055572935963737"
                + "425786769119310690200000000000001035182140500991264655503202"
                + "846190000000000000012178613417658720760652978857014000000000"
                + "000000101488445147156006338774823808450000000000000000636790"
                + "244060586706439371443504000000000000000003121520804218562286"
                + "467507076000000000000000000012241258055759067790068655200000"
                + "000000000000000039004008510997029723257970000000000000000000"
                + "000101971264081037986204596000000000000000000000000219938020"
                + "566944676127560000000000000000000000000392046382472272149960"
                + "000000000000000000000000000576538797753341397000000000000000"
                + "000000000000000695672757470095200000000000000000000000000000"
                + "000682032115166760000000000000000000000000000000000534927149"
                + "150400000000000000000000000000000000000327773988450000000000"
                + "000000000000000000000000000151222140000000000000000000000000"
                + "000000000000000049419000000000000000000000000000000000000000"
                + "000010200000000000000000000000000000000000000000000001");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
        // Test 4
        test++;
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = new BigInteger("200");
        expResult = new BigDecimal(
                "32661431824467802229454073873432080851768274685145101404793774"
                + "576452998252512362581671667487048088656160662612395254996698"
                + "38852249217152696461.356901206565068735933481767168239913930"
                + "280323499656669613922968471032648138484549082976751149433925"
                + "750688556791475306905311908660060388833579486748894003125370"
                + "170045198537082600520758490362276327665108342115091975283768"
                + "184152685278624427232999470161745982272431522769026873464191"
                + "277044336165203154533720842603909488070802890388314902444582"
                + "682572923969512160999464798009520889416891681948951349176578"
                + "807265355963730373521315146367317991604961553810355716293381"
                + "253016526054040082578844980335745953798075748936975523133781"
                + "785381204061390192709205696583196145427000033625199001463461"
                + "552007509265668218628528184456849309333730137540867860942906"
                + "512918372446982316973041147062117618506875265082339285867786"
                + "377056803521592210075199563777335841908308241223251553712732"
                + "423504024673219809533123674093710308460601672325499755394141"
                + "765447564658590157637418775366272168987744817003658783192269"
                + "436740408933591810125181631527264100366551135208830844734340"
                + "052471548921979476253604807537617878708830823986376040966816"
                + "205531265081613902040442458022209228726532175757080502103671"
                + "260343175494974108896366363326169591146775486402549519978595"
                + "560695581430775704111970302440586374304684229293958752128140"
                + "388073651216766201744877883343369918698669366515144728430484"
                + "593109371560594787119646703761730963856783445030500561162899"
                + "400492768208872907438041247533887293898865408935284378132990"
                + "641890354621602288671087155904239746345001906108163179050849"
                + "493827588180978547881521397837520159835370682108572143363865"
                + "577486997125233899592941574463888892333696491052862914529406"
                + "391922118750167937062895729399852697624933511422839129707225"
                + "381832100528471756451279450235517844342084923735642465915508"
                + "417264011490757453457518620357012187822562887555854803164255"
                + "233137100810443172952775381229111764220063398378269526497245"
                + "024147852208621401392306266493534077142652212509301692553319"
                + "350887731639706841848517256392339244759841065616315227664478"
                + "427078579821858288017176588911219912297278459432183089082996"
                + "624150594612251357507088875170794168585596179905338817500027"
                + "206464472725839115152032431682180814927471032553381243895058"
                + "352147755624009443494300679928536505541369569146147045188769"
                + "100481674018094657840338226345020372404259774981139349656714"
                + "502625722161000694517021805853186592783832875096424968453470"
                + "591432063352425196021317637334818146165950047352700906519734"
                + "364802115757382590726495705549669730331936157290156579177121"
                + "785755513544129674673580151027552795172013761582766062013188"
                + "623602423815679997155329453730442059846871354053406643213109"
                + "224217162429414480745283016226322124267068021621768684384764"
                + "291990747940797903996533879729817358244044421620316390518869"
                + "835835873961304390292113878315868768474954371598413354008302"
                + "259607053824254635813713908025875259156585725724748661222646"
                + "849388307774842228001184848936165591491569573825465867285591"
                + "420686284704410722138747987721605575381347770874021242324164"
                + "948439640686460295820094367805094682159456941191636174328433"
                + "302523578806096575238297678957111155129567124045492652606204"
                + "205024696277747069423253051155720216484447535401501554946781"
                + "804419612070412558285140850278735772992058871002334886069754"
                + "528201647136730570100494509193225854076078795534073947021792"
                + "218566038443335348288941315444462946432980377260633670897733"
                + "416253184928441782357884920919685037093026261744086468127625"
                + "751490027032120428674049247743547767907916071245368256153102"
                + "369678672227515647583000186397385351466860489281314295436218"
                + "011242729929655782850105861245381149707044982802050292624863"
                + "077699493412206953007389208971168152930065107610637712942195"
                + "498262435777505861742785784369515634177701669119140703188938"
                + "476331039653456667288848403987031824207847643606764585858620"
                + "470928827009570960111166203211321016348347777636054916854198"
                + "001426811261510239579111778530402998542240650566877395959955"
                + "045064410420744465727808784837454400905986595099701222952496"
                + "070742946651678631843490169522868994938987826247425517826206"
                + "534898481790267633944274939440824052950395217386337266191181"
                + "836187769048716504484643945850974015308258094640705797336348"
                + "691293938420214497790186454279550272887856647454341610769460"
                + "302135900911310334307902401718328812302504014032528291240119"
                + "048667102323271926559210654533038201877449145303332506923572"
                + "465006904940685136619672459993012700413631915613397816712742"
                + "668290650788799570745419241231996871924380636198612941048429"
                + "121524146340643994095073130595831745687545421004479106516173"
                + "209296357367685099972119953855284958650351907223809713675361"
                + "124677852941085066273611068917208890592879305690237284534706"
                + "178061366841473098383640629483708224738119699652153994249182"
                + "747528259146223510785799284535412461197862235305354435361263"
                + "942478958586234920318350373813551750477416960893551652327937"
                + "514192583213736216202415742797126923936525590647802020776637"
                + "788487640242097299246192463491429908667851573393029261875310"
                + "770386756669766959675421407121608131793282076686286392061419"
                + "972601410093735268178631063328543664688385619490992305616578"
                + "450303094024585054518587680960986366781862669699145945465463"
                + "984120702579334357346446695435114770665690236421798600904697"
                + "382122466811455071513309341258456232392352376352774860423692"
                + "909938039781545795965777440720931029842931339578472178325517"
                + "108960945189288028016750780165782332129321283630491413295059"
                + "302586168568433724780844776076122333860000742844259902840419"
                + "992620364428698462118597649581931886119268720739388623554386"
                + "517407825772232837273665279508542647411066565258347874824908"
                + "412029504708087880627376609017052313156696308292318481235284"
                + "022891635466230881948210196179585746085885371676062798971605"
                + "416432728093246782649563823084535365411330294647266749336165"
                + "284407309275166723853517611067826466970731604290150241096640"
                + "848738456281423792224115541822758254256696444012256692183533"
                + "598616976376137756688506031511953166754544712875764446544943"
                + "612948268663859173442027683063022381685286222138220436146301"
                + "997731422313017774391364296980752385201909221696700771902326"
                + "982466009062101017006896673103555409600744877589153564890316"
                + "319521129849494087046862413902967995711862805056794729735181"
                + "359644271828434369316341582411926820237001732950684152032674"
                + "886605543141618436702698910261437010668800465247178793111285"
                + "645279615966330680160312220546680106833640059941564644428903"
                + "209229044088900258052013149311913225186653285083023566069845"
                + "947552923542201186029017803655248750644292456835466968977746"
                + "015968354788696887137679183531666630414463740895309686450177"
                + "130666328564271332474718447924727827403198528628759612981960"
                + "247367054935743541403921237911171312655444051299310110574731"
                + "627563518568431348297451458238711598610141671122568716565882"
                + "073808514325171824034990554406483170369561951445089019074620"
                + "357190814276091932117906913648991641277494332935193005419883"
                + "270812254984886595961663704435297090629480640738137326608446"
                + "379984384651068566515933134984565024991498903843520013628260"
                + "422555012349786764999104941200926343934274066019612373871143"
                + "084177885866765104145730062886593017615562560337577791843376"
                + "643075013375439557306696966989012220431865099337683157411375"
                + "683081004446286997751744072003561879787347606956826279112385"
                + "895014877702432649228717529922940939067693221734185754850242"
                + "656374966912768013311175060344407853723778182921557352115848"
                + "157992935343762083633575902739905121241873579910175193239072"
                + "640583033773927904390693906167715718005061646964661619850294"
                + "663664082259912123648048254962242492560992835640265424749462"
                + "208461508734214485351705659192753232921376476228595882928314"
                + "670243502333958093683891566916839892128145113727730563443641"
                + "375498015667365555417116514099355911781873411967124478319673"
                + "104483103403773607353476955848306305906755546364375089686184"
                + "466300950002917832235081497199718106452797781825924030508060"
                + "763834704572400339518774113122438916525582517669840449421085"
                + "741577750520283950181587394510045532584566939013798293836977"
                + "792897857352766577025157832181761362403698346675722271546955"
                + "292793908063958635335744277061544769113858044434465967275615"
                + "326711569591890100384675656041209576792656884858620322497983"
                + "239403095237516760951305784275847723022547719268083846271823"
                + "735318884131798881790995760811623503580152929866710020935567"
                + "600578531473971352480451465608038276147778920410264669145152"
                + "029610430823993358064511753897154522128649394358539586879851"
                + "751798500848058878282308964092107816990051604387533349829117"
                + "508584373557793461006198784188836749428605632366549829426681"
                + "649248274899393212958233580465857470011121150330780790501801"
                + "101347339521436870749573917776894223484888538680605048812509"
                + "889085873574610484080106437370444199510460704048003194585458"
                + "244228384338073908271548907618963564789426667849256684732821"
                + "595473571996296742764130064248078960739894526218598513228484"
                + "482203203832953567614071502390458287097109590835965709370324"
                + "810428915592663476532298387157534571732918360086878292131950"
                + "001225485074448272607876499488009501403904511693548580879992"
                + "797007053743912000001394774611126808322115259724223111028126"
                + "400000000189112118738511766661434881256783931640000000000023"
                + "543369902086743437464659975945712000000000000002672621970309"
                + "124642023852009653280000000000000000274368336958127978854722"
                + "514080000000000000000000025217678029239703938853172250000000"
                + "000000000000002049592850085518962824600000000000000000000000"
                + "000145008590565985776630000000000000000000000000000008748632"
                + "914991600400000000000000000000000000000000437606688424950000"
                + "000000000000000000000000000000017422382340000000000000000000"
                + "000000000000000000000517599000000000000000000000000000000000"
                + "000000000010200000000000000000000000000000000000000000000000"
                + "1");
        result = Math_BigDecimal.powerNoRounding(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of powerNoSpecialCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheck_4args() {
        System.out.println("powerNoSpecialCaseCheck");
        BigDecimal x;
        int y;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -2;
        x = new BigDecimal("1.1");
        y = 2;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("1.21");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -20;
        x = new BigDecimal("10.01");
        y = 10;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -5;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -25;
        x = new BigDecimal("0.01789");
        y = 10;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.0000000000000000033581756");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -5;
        x = new BigDecimal("0.01789");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -100;
        x = new BigDecimal("0.01789");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.00000000000000000335817563008269544848251"
                + "088786601");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoSpecialCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheck_4args_1() {
        System.out.println("powerNoSpecialCaseCheck");
        BigDecimal x;
        BigInteger y;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -2;
        x = new BigDecimal("1.1");
        y = new BigInteger("2");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("1.21");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -20;
        x = new BigDecimal("10.01");
        y = new BigInteger("10");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -5;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -25;
        x = new BigDecimal("0.01789");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.0000000000000000033581756");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -5;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -100;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.00000000000000000335817563008269544848251"
                + "088786601");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoSpecialCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheck_4args_2() {
        System.out.println("powerNoSpecialCaseCheck");
        BigDecimal x;
        int y;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -2;
        x = new BigDecimal("1.1");
        y = 2;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("1.21");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -20;
        x = new BigDecimal("10.01");
        y = 10;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -5;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("10100451202.10252");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -25;
        x = new BigDecimal("0.01789");
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.0000000000000000033581756");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -5;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -100;
        result = Math_BigDecimal.powerNoSpecialCaseCheck(x, y, oom, rm);
        expResult = new BigDecimal("0.00000000000000000335817563008269544848251"
                + "088786601");
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoSpecialCaseCheckNoRounding method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testPowerNoSpecialCaseCheckNoRounding() {
        System.out.println("powerNoSpecialCaseCheckNoRounding");
        BigDecimal x;
        BigInteger y;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1.1");
        y = new BigInteger("2");
        // div = 2;
        result = Math_BigDecimal.powerNoSpecialCaseCheckNoRounding(x, y);
        expResult = new BigDecimal("1.21");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("10.01");
        y = new BigInteger("10");
        result = Math_BigDecimal.powerNoSpecialCaseCheckNoRounding(x, y);
        expResult = new BigDecimal("10100451202.10252210120045010001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("10.01");
        y = new BigInteger("100");
        result = Math_BigDecimal.powerNoSpecialCaseCheckNoRounding(x, y);
        expResult = new BigDecimal("1105115697720767968379105237118840189434898"
                + "8003480476139953393312528176774650687493588626299347803138.0"
                + "723721663764349204459257355480400489666633588480442130848239"
                + "049466210974637537699943515395815911657098563163669683550470"
                + "162395224923677921255721401890902609742355547435230305292769"
                + "1441386704950100001");
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("10.01");
        y = new BigInteger("1000");
        result = Math_BigDecimal.powerNoSpecialCaseCheckNoRounding(x, y);
        expResult = new BigDecimal("2716923932235892457383088121947577188964315"
                + "018836572803722354774868894945523768158997885697298661429053"
                + "421034015406256924859461187617653889457753593083386399572063"
                + "538500432650176144488046171044844121805479607648086607018742"
                + "077798375087855857012278053105042704758822511824867218226931"
                + "719410407150364389665913091822576819072281835735365786202176"
                + "167228686198158460724641052407506305826211156964723064441295"
                + "969498221919251479211700941935114755531972677360157561485144"
                + "237786816579422141378066423317811515462669946309306263409027"
                + "388915931082226854264858661420878279983534424128672461206356"
                + "847463821364630504359665171573635397346037274752410368174877"
                + "433941234543153511100471651472869116068528478976916600585383"
                + "497180172395573924789047989563714318957536493108041591460911"
                + "612078698461739084741934442448701416575483263891529095158013"
                + "233115648534154086009312190489168546024398834243847135102411"
                + "661996020129557921444666343641039137906807591342742464200991"
                + "9337227915310632026776505819463604220277656459701824637802.7"
                + "316111300971758215548990267709505335420794477243927165644786"
                + "992182595904280132277572902249140201208460536778445609089298"
                + "768254781136048173179598063784755178825938424399734119075308"
                + "934338720175382136040543031032056448874114212008946036898659"
                + "013632473745937296366658653244357047417935265651763533374478"
                + "340169595196993629632325652503468552547042618522403684480348"
                + "744283163948315236283173535026962466870170242445094084088455"
                + "527132519087610266527785815469509276561363971857712743853864"
                + "941449267835876211023562177621878136088101065469627326470631"
                + "908845303585835505298880850777543956138523265230531628770565"
                + "343672764768140561832375720102294680111877014807242402138526"
                + "182959424836989017158399314793404423279251711874339321727641"
                + "617984209755449426901225132913478359603773397347830618825529"
                + "148435238469987142047271142307958631904183756367849847277942"
                + "228226102474439484455873837802710569969126008653263293094147"
                + "877968055464585077816870366142381900051589523290324373876348"
                + "157199908070209836931619960194224624788780838507382186151763"
                + "683992690745818460464894203635525668321921812991042282217733"
                + "678526862727448203747629434144456220719720950365951826621043"
                + "279107824832101545321801958660869620729529918311196315856416"
                + "241915274280743734624166767168846699824442472676583768215160"
                + "623063811165475659591701920645397802415709704254693734567333"
                + "717916524232539964812187717898772399950383919732818392534094"
                + "919182144369827547629524524946636181736720724808914471880857"
                + "215278103711220928594484402118653483215996429718197058445375"
                + "616320429711118582346774474346584023009826142478931331509395"
                + "176631445902794717670148921574688436342696157734838465188715"
                + "314060961636292733810768679449997490258157989707617271654150"
                + "429433430074144410674999471571341963068871945136265828881213"
                + "205685480733082705050506471444261824310101881215356379553902"
                + "437021996780151509997072192624062541851241794085476041556622"
                + "974624897375629756945230282156346757431325906601608952112277"
                + "920484487599886411493051606391032435933190384304006946732416"
                + "7490917499501000001");
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of reciprocalWillBeIntegerReturnBigInteger method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testReciprocalWillBeIntegerReturnBigInteger() {
        System.out.println("reciprocalWillBeIntegerReturnBigInteger");
        BigDecimal x;
        BigInteger expResult;
        BigInteger result;
        // Test 1
        x = BigDecimal.ONE;
        expResult = BigInteger.ONE;
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigInteger(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.1");
        expResult = new BigInteger("10");
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigInteger(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.00000000000000000000001");
        expResult = new BigInteger("100000000000000000000000");
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigInteger(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reciprocalWillBeIntegerReturnBigDecimal method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testReciprocalWillBeIntegerReturnBigDecimal() {
        System.out.println("reciprocalWillBeIntegerReturnBigDecimal");
        BigDecimal x;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = BigDecimal.ONE;
        expResult = BigDecimal.ONE;
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigDecimal(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.1");
        expResult = new BigDecimal("10");
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigDecimal(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.00000000000000000000001");
        expResult = new BigDecimal("100000000000000000000000");
        result = Math_BigDecimal.reciprocalWillBeIntegerReturnBigDecimal(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of log method, of class Math_BigDecimal.
     */
    @Test
    public void testLog_4args_1() {
        System.out.println("log");
        int base;
        BigDecimal x;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        base = 10;
        x = new BigDecimal("10");
        oom = -10;
        expResult = new BigDecimal("1");
        result = Math_BigDecimal.log(base, x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("100");
        expResult = new BigDecimal("2");
        result = Math_BigDecimal.log(base, x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        base = 10;
        x = new BigDecimal("100100.1");
        rm = RoundingMode.HALF_UP;
        expResult = new BigDecimal("5.0004345113");
        result = Math_BigDecimal.log(base, x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of log method, of class Math_BigDecimal.
     */
    @Test
    public void testLog_4args_2() {
        System.out.println("log");
        BigDecimal base;
        BigDecimal x;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -10;
        rm = RoundingMode.HALF_UP;
        base = new BigDecimal("10");
        x = new BigDecimal("10");
        result = Math_BigDecimal.log(base, x, oom, rm);
        expResult = BigDecimalMath.log10(x, new MathContext(result.scale() - oom));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        base = new BigDecimal("10");
        x = new BigDecimal("1");
        result = Math_BigDecimal.log(base, x, oom, rm);
        expResult = BigDecimalMath.log10(x, new MathContext(result.scale() - oom));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        base = new BigDecimal("11");
        x = new BigDecimal("10");
        result = Math_BigDecimal.log(base, x, oom, rm);
        expResult = new BigDecimal("0.9602525678");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        base = new BigDecimal("10.5");
        x = new BigDecimal("10");
        result = Math_BigDecimal.log(base, x, oom, rm);
        expResult = new BigDecimal("0.979250371");
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of round method, of class Math_BigDecimal.
     */
    @Test
    public void testRound_3args() {
        System.out.println("round");
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal x = new BigDecimal("98765432123456789.98765432123456789");
        int oom = -1;
        BigDecimal expResult = new BigDecimal("98765432123456790.0");
        BigDecimal result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -2;
        expResult = new BigDecimal("98765432123456789.99");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -3;
        expResult = new BigDecimal("98765432123456789.988");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -4;
        expResult = new BigDecimal("98765432123456789.9877");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -5;
        expResult = new BigDecimal("98765432123456789.98765");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -6;
        expResult = new BigDecimal("98765432123456789.987654");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -7;
        expResult = new BigDecimal("98765432123456789.9876543");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -8;
        expResult = new BigDecimal("98765432123456789.98765432");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -9;
        expResult = new BigDecimal("98765432123456789.987654321");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = 0;
        expResult = new BigDecimal("98765432123456790");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        oom = 1;
        expResult = new BigDecimal("98765432123456790");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        oom = 2;
        expResult = new BigDecimal("98765432123456800");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        oom = 3;
        expResult = new BigDecimal("98765432123457000");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 14
        oom = 4;
        expResult = new BigDecimal("98765432123460000");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 15
        oom = 5;
        expResult = new BigDecimal("98765432123500000");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 16
        x = new BigDecimal("99999.99999");
        oom = 4;
        expResult = new BigDecimal("100000");
        result = Math_BigDecimal.round(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of round method, of class Math_BigDecimal.
     *
     * Test covered by {@link #testRound_3args() }.
     */
    @Test
    @Disabled
    public void testRound_BigDecimal_int() {
    }

    /**
     * Test of getEulerConstantToAFixedDecimalPlacePrecision method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testGetE() {
        System.out.println("getEulerConstantToAFixedDecimalPlacePrecision");
        int oom = 0;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = BigDecimal.valueOf(3);
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal result = instance.getE(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = -100;
        expResult = new BigDecimal(
                "2.718281828459045235360287471352662497757247093699959574966967"
                + "6277240766303535475945713821785251664274");
        result = instance.getE(oom, rm);
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
        result = instance.getE(oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of max method, of class Math_BigDecimal.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        Collection<BigDecimal> c = new ArrayList<>();
        for (int i = -100; i < 101; i++) {
            c.add(BigDecimal.valueOf(123456789, i));
        }
        BigDecimal expResult = BigDecimal.valueOf(123456789, -100);
        BigDecimal result = Math_BigDecimal.max(c);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of floorSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testFloorSignificantDigit() {
        System.out.println("floorSignificantDigit");
        BigDecimal x;
        BigDecimal expResult;
        BigDecimal result;
        int test = 1;
        // Test 1
        x = new BigDecimal("0.0001");
        expResult = new BigDecimal("0.0001");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        test++;
        x = new BigDecimal("0.00012");
        expResult = new BigDecimal("0.0001");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        test++;
        x = new BigDecimal("0.0009");
        expResult = new BigDecimal("0.0009");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        test++;
        x = new BigDecimal("1.00099");
        expResult = new BigDecimal("1");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        test++;
        x = new BigDecimal("10008798.00099");
        expResult = new BigDecimal("10000000");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        test++;
        x = new BigDecimal("-1.00099");
        expResult = new BigDecimal("-2");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        test++;
        x = new BigDecimal("-10008798.00099");
        expResult = new BigDecimal("-20000000");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        test++;
        x = new BigDecimal("-0.00099");
        expResult = new BigDecimal("-0.001");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        test++;
        x = new BigDecimal("-0.99");
        expResult = new BigDecimal("-1");
        result = Math_BigDecimal.floorSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of ceilingSignificantDigit method, of class Math_BigDecimal. -0.99
     */
    @Test
    public void testCeilingSignificantDigit() {
        System.out.println("ceilingSignificantDigit");
        BigDecimal x;
        BigDecimal expResult;
        BigDecimal result;
        int test = 1;
        // Test 1
        x = new BigDecimal("0.0001");
        expResult = new BigDecimal("0.0002");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        test++;
        x = new BigDecimal("0.00012");
        expResult = new BigDecimal("0.0002");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        test++;
        x = new BigDecimal("0.0009");
        expResult = new BigDecimal("0.001");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        test++;
        x = new BigDecimal("1.00099");
        expResult = new BigDecimal("2");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        test++;
        x = new BigDecimal("10008798.00099");
        //expResult = new BigDecimal("20000000");
        expResult = new BigDecimal("2E+7");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        test++;
        x = new BigDecimal("-1.00099");
        expResult = new BigDecimal("-1");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        test++;
        x = new BigDecimal("-10008798.00099");
        expResult = new BigDecimal("-10000000");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        test++;
        x = new BigDecimal("-0.00099");
        expResult = new BigDecimal("-0.0009");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        test++;
        x = new BigDecimal("-0.99");
        expResult = new BigDecimal("-0.9");
        result = Math_BigDecimal.ceilingSignificantDigit(x);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of root method, of class Math_BigDecimal.
     */
    @Test
    public void testRoot() {
        System.out.println("root");
        BigDecimal x;
        int root;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        MathContext mc;
        // Test 1
        x = new BigDecimal("27");
        root = 3;
        oom = -3;
        expResult = new BigDecimal("3");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.1");
        root = 356;
        oom = -3;
        expResult = new BigDecimal("0.994");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.1");
        oom = -4;
        expResult = new BigDecimal("0.9936");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("0.1");
        oom = -100;
        expResult = new BigDecimal(
                "0.993552936417354226471692858641777111504937855829958649327917"
                + "9203022268144882986474329796822248724061");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 5
//        x = new BigDecimal("8904831940328.25");
//        root = 1000000000;
//        oom = -100;
//        expResult = new BigDecimal(
//                "1.000000029817615604530522955297464844042643450238720000794888"
//                + "829957679681379258243392341722104230672");
//        //1.000000029817615604530522955297...
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 6
//        x = new BigDecimal("0.25");
//        root = 1000000000;
//        expResult = new BigDecimal(
//                "0.999999998613705638454721047973532813135945294316465347162595"
//                + "0794816212223235738396849891311358363145");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 7
//        x = new BigDecimal("0.999");
//        root = 1000000000;
//        expResult = new BigDecimal(
//                "0.999999999998999499665416466665732735126112562031740141406732"
//                + "3221950570597106160577635181243915018588");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
// Test 1
        x = new BigDecimal("0.25");
        root = 42;
        oom = -10;
        result = Math_BigDecimal.root(x, root, oom, rm);
        //expResult = new BigDecimal("0.9675317785");
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("27");
        root = 3;
        expResult = new BigDecimal("3");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("8904831940328.25");
        root = 10;
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        //expResult = Math_BigDecimal.round(expResult, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("8904831940328.25");
        root = 100;
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("8904831940328.25");
        root = 500;
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("8904831940328.25");
        root = 1000;
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 7 BigDecimalMath not returning a result in a reasonable time frame!
//        x = new BigDecimal("8904831940328.25");
//        root = new BigInteger("5000");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
//        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 8 BigDecimalMath not returning a result in a reasonable time frame!
//        x = new BigDecimal("8904831940328.25");
//        root = new BigInteger("100023");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
//        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 9 BigDecimalMath not returning a result in a reasonable time frame!
//        x = new BigDecimal("0.25");
//        root = new BigInteger("100023");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
//        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 10
//        x = new BigDecimal("0.999");
//        root = 100023;
//        expResult = new BigDecimal(
//                "0.999999989997297335815964171495970213458904791728888003317236"
//                + "9473900240303824000659869633098129663322");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
//        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
//        assertTrue(expResult.compareTo(result) == 0);
//        // Test 11
//This test currently seems to get stuck. At least it does not return an answer 
//in a reasonable time frame.
//        x = new BigDecimal("89048.25");
//        root = new BigInteger("100023");
//        expResult = new BigDecimal(
//                "1.000113949621211600391921935699344997726967851095980198127275"
//                + "8749782805563101995300728186461240097898");
//        result = Math_BigDecimal.root(x, root, oom, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//This test currently seems to get stuck. At least it does not return an answer 
//in a reasonable time frame.
//        // Test 12
//        x = new BigDecimal("8904831940328.25");
//        root = new BigInteger("1000000000");
//        expResult = new BigDecimal(
//                "1.000000029817615604530522955297464844042643450238720000794888"
//                + "829957679681379258243392341722104230672");
//        result = Math_BigDecimal.root(x, root, dp, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//This test currently seems to get stuck. At least it does not return an answer 
//in a reasonable time frame.
//        // Test 13
//        x = new BigDecimal("0.25");
//        root = new BigInteger("1000000000");
//        expResult = new BigDecimal(
//                "0.999999998613705639841015408557905737253169342556579813124573"
//                + "7217463304587103469885523969834565259117");
//        result = Math_BigDecimal.root(x, root, dp, rm);
//        assertTrue(expResult.compareTo(result) == 0);
//This test currently seems to get stuck. At least it does not return an answer 
//in a reasonable time frame.
//        // Test 14
//        x = new BigDecimal("0.999");
//        root = new BigInteger("1000000000");
//        expResult = new BigDecimal(
//                "0.999999999998999499666416967000315767959920576358096366321598"
//                + "7363152842107633912159255642659280381426");
//        result = Math_BigDecimal.root(x, root, dp, rm);
//        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of rootNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testRootNoRounding() {
        System.out.println("rootNoRounding");
        BigDecimal x = BigDecimal.valueOf(27);
        int root = 3;
        BigDecimal expResult = BigDecimal.valueOf(3);
        BigDecimal result = Math_BigDecimal.rootNoRounding(x, root);
        assertEquals(expResult, result);
        // Test 2
        x = BigDecimal.valueOf(125);
        root = 3;
        expResult = BigDecimal.valueOf(5);
        result = Math_BigDecimal.rootNoRounding(x, root);
        assertEquals(expResult, result);
    }

    /**
     * Test of sqrt method, of class Math_BigDecimal.
     */
    @Test
    public void testSqrt() {
        System.out.println("sqrt");
        BigDecimal x;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = 0;
        x = new BigDecimal("25");
        expResult = BigDecimal.valueOf(5);
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("100");
        expResult = new BigDecimal("10");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("144");
        expResult = new BigDecimal("12");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("10000");
        expResult = new BigDecimal("100");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -1;
        x = new BigDecimal("0.01");
        expResult = new BigDecimal("0.1");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -7;
        x = new BigDecimal("0.00000001");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        MathContext mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        //expResult = new BigDecimal("0.0001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -7;
        x = new BigDecimal("0.000000001");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        //expResult = new BigDecimal("0.0000316");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -5;
        x = new BigDecimal("2");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -100;
        x = new BigDecimal("2");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -10;
        x = new BigDecimal("143");
        expResult = new BigDecimal("11.9582607431");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of randomUniformTest method, of class Math_BigDecimal.
     */
    @Test
    public void testRandomUniformTest() {
        System.out.println("randomUniformTest");
        Random rand;
        Math_BigDecimal bd = new Math_BigDecimal();
        int oom;
        RoundingMode rm;
        BigDecimal probability;
        boolean result;
        long seed;
        // Test 1
        seed = 0L;
        rand = new Random(seed);
//        // The following (commented) code reveals that the first 4096 consecutive
//        // tests for probability 0.5 return true!!!
//        probability = new BigDecimal("0.5");
//        boolean alternator = false;
//        for (long l = 0; l < 10000L; l++) {
//            rand = new Random(l);
//            result = Math_BigDecimal.randomUniformTest(rand, probability);
//            if (!alternator){
//              if (result == alternator) {
//                    System.out.println("result changed to " + result + " on iteration " + l);
//                    alternator = true;
//                }
//            } else {
//              if (result == alternator) {
//                    System.out.println("result changed to " + result + " on iteration " + l);
//                    alternator = false;
//                }
//            }
//        }
        oom = - 100;
        rm = RoundingMode.HALF_UP;
        probability = new BigDecimal("0.5");
        result = bd.randomUniformTest(rand, probability, oom, rm);
        assertTrue(result);
        // Test 2
        rand = new Random(seed);
        probability = new BigDecimal("0.025");
        result = bd.randomUniformTest(rand, probability, oom, rm);
        assertFalse(result);
        // Test 3
        rand = new Random(seed);
        probability = new BigDecimal("0.0125");
        result = bd.randomUniformTest(rand, probability, oom, rm);
        assertFalse(result);
    }

    /**
     * Test of isEven method, of class Math_BigDecimal.
     */
    @Test
    public void testIsEven() {
        System.out.println("isEven");
        BigDecimal x = Math_BigDecimal.TWO;
        assertTrue(Math_BigDecimal.isEven(x));
        // Test 2
        x = BigDecimal.valueOf(42, 2);
        assertTrue(Math_BigDecimal.isEven(x));
        // Test 3
        x = BigDecimal.valueOf(424, 2);
        assertTrue(Math_BigDecimal.isEven(x));
        // Test 4
        x = BigDecimal.valueOf(-424, 2);
        assertTrue(Math_BigDecimal.isEven(x));
        // Test 5
        x = BigDecimal.valueOf(4241, 2);
        assertFalse(Math_BigDecimal.isEven(x));
        // Test 6
        x = BigDecimal.valueOf(424, -2);
        assertTrue(Math_BigDecimal.isEven(x));
        // Test 7
        x = BigDecimal.valueOf(4241, -2);
        assertTrue(Math_BigDecimal.isEven(x));
    }

    /**
     * Test of isBigDecimal method, of class Math_BigDecimal.
     */
    @Test
    public void testIsBigDecimal() {
        System.out.println("isBigDecimal");
        String s = "1";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 2
        s = "1.0";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 3
        s = "1.000000000000000000000000000000000000000000000000000000000000001";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 4
        s = "100000000000000000000000000000000000000000000000000000000000000000"
                + ".0000000000000000000000000000000000000000000000000000000001";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 5
        s = "1.0.0";
        assertFalse(Math_BigDecimal.isBigDecimal(s));
        // Test 6
        s = "1,000.0";
        assertFalse(Math_BigDecimal.isBigDecimal(s));
        // Test 7
        s = "-123";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 8
        s = "1.23E3";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 9
        s = "1.23E+3";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 10
        s = "12.3E+7";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 11
        s = "-1.23E-12";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 12
        s = "1234.5E-4";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 13
        s = "0E+7";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
        // Test 14
        s = "-0";
        assertTrue(Math_BigDecimal.isBigDecimal(s));
    }

    /**
     * Test of getOrderOfMagnitudeOfMostSignificantDigit method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testGetOrderOfMagnitudeOfMostSignificantDigit_BigDecimal() {
        System.out.println("getOrderOfMagnitudeOfMostSignificantDigit");
        BigDecimal x = new BigDecimal("10001.1");
        int expResult = 4;
        int result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.1");
        expResult = -1;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("0.0001");
        expResult = -4;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 4
        x = new BigDecimal("1.0001");
        expResult = 0;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 5
        x = new BigDecimal("10.0001");
        expResult = 1;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderOfMagnitudeOfMostSignificantDigit method, of class
     * Math_BigDecimal. Most test cases are covered by
     * {@link #testGetOrderOfMagnitudeOfMostSignificantDigit_BigDecimal()}.
     */
    @Test
    public void testGetOrderOfMagnitudeOfMostSignificantDigit_BigDecimal_int() {
        System.out.println("getOrderOfMagnitudeOfMostSignificantDigit");
        BigDecimal x;
        int scale;
        int expResult;
        int result;
        // Test 1
        x = new BigDecimal("10.0001");;
        scale = x.scale();
        expResult = 1;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, scale);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.10001");;
        scale = x.scale();
        expResult = -1;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, scale);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("1.10001");;
        scale = x.scale();
        expResult = 0;
        result = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, scale);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getOrderOfMagnitudeOfLeastSignificantDigit method, of class
     * Math_BigDecimal.
     */
    @Test
    public void testGetOrderOfMagnitudeOfLeastSignificantDigit() {
        System.out.println("getOrderOfMagnitudeOfLeastSignificantDigit");
        BigDecimal x = new BigDecimal("10001.1");
        int expResult = -1;
        int result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("1.0001");
        expResult = -4;
        result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("0.0101");
        expResult = -4;
        result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 4
        x = new BigDecimal("101");
        expResult = 0;
        result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 5
        x = new BigDecimal("101.0");
        expResult = 0;
        result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 6
        x = new BigDecimal("10100.0");
        expResult = 2;
        result = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of ln method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testLn() {
        System.out.println("ln");
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal x;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        MathContext mc;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = BigDecimal.ONE;
        oom = -100;
        expResult = BigDecimal.ZERO;
        result = bd.ln(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigDecimal.valueOf(2);
        mc = new MathContext(-oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("0.69314718055994530941723212145817656807550"
        //        + "01343602552541206800094933936219696947156058633269964186875");
        result = bd.ln(x, oom, rm);
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = bd.getE(oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x) - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = BigDecimal.ONE;
        result = bd.ln(x, oom, rm);
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = BigDecimal.valueOf(3);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("1.09861228866810969139524523692252570464749"
        //        + "05578227494517346943336374942932186089668736157548137320888");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = BigDecimal.valueOf(4);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("1.09861228866810969139524523692252570464749"
        //        + "05578227494517346943336374942932186089668736157548137320888");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = BigDecimal.valueOf(5);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = BigDecimal.valueOf(15);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = BigDecimal.valueOf(40);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = BigDecimal.valueOf(100);
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("300");
        oom = -100;//-101;
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("3000");
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        //System.out.println(result);
        //System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("300000");
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        System.out.println(result);
        System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("300000000000");
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        System.out.println(result);
        System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("30000000000000000000");
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        System.out.println(result);
        System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -100;
        x = new BigDecimal("0.5");
        result = bd.ln(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom);
        expResult = BigDecimalMath.log(x, mc);
        //expResult = new BigDecimal("");
        System.out.println(result);
        System.out.println(expResult);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiplyPriorRound_4args_1() {
        System.out.println("multiplyPriorRound");
        BigDecimal x;
        BigInteger y;
        int oom = 0;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("9999999.999999999999");
        y = new BigInteger("999999");
        oom = -4;
        rm = RoundingMode.HALF_UP;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        //System.out.println(expResult.toPlainString());
        //System.out.println(result);
        //System.out.println(x.toBigIntegerExact().multiply(y));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("9999999.99999999999999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("9999999.99999999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("9999999.9999999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("9999999.999999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("9999999.99999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("9999999.9999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("9999999.9999");
        y = new BigInteger("999999");
        oom = 4;
        rm = RoundingMode.HALF_UP;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("99999999999");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("99999999990");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("99999999999.0");
        y = new BigInteger("999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        //System.out.println(expResult.toPlainString());
        //System.out.println(result);
        //System.out.println(x.toBigIntegerExact().multiply(y));
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigDecimal. Test case
     * covered by {@link #testMultiplyPriorRound_4args_1()}.
     */
    @Test
    @Disabled
    public void testMultiplyPriorRound_3args_1() {
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigDecimal.
     */
    @Test
    public void testMultiplyPriorRound_4args_2() {
        System.out.println("multiplyPriorRound");
        BigDecimal x;
        BigDecimal y;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Case 1: x < 1; y < 1
        // Test 1
        x = new BigDecimal("0.000999999999");
        y = new BigDecimal("0.000999999999");
        oom = -3;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
//        System.out.println(expResult.toPlainString());
//        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("0.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.0999999999");
        y = new BigDecimal("0.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("0.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("0.00999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("0.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("0.0999999999");
        y = new BigDecimal("0.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("0.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("0.999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Case 2: x < 1, y >= 1
        // Test 1
        x = new BigDecimal("0.000999999999");
        y = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
//        System.out.println(expResult.toPlainString());
//        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.0999999999");
        y = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        x.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("999999.00999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("0.00999999999");
        y = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("0.0999999999");
        y = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("0.999999999");
        y = new BigDecimal("999999.999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("0.989999899");
        y = new BigDecimal("1.998999999");
        oom = -2;
        x.multiply(y);
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("0.999989999");
        y = new BigDecimal("1.999999899");
        oom = -5;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("0.999999899");
        y = new BigDecimal("1.999999899");
        oom = -7;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("0.999999998");
        y = new BigDecimal("1.999999899");
        oom = -8;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Case 3: x >= 1, y < 1
        // Test 1
        y = new BigDecimal("0.000999999999");
        x = new BigDecimal("999999.000999999999");
        oom = -3;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
//        System.out.println(expResult.toPlainString());
//        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        y = new BigDecimal("0.00999999999");
        x = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        y = new BigDecimal("0.0999999999");
        x = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = new BigDecimal("0.999999999");
        x = new BigDecimal("999999.000999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        x.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        y = new BigDecimal("0.00999999999");
        x = new BigDecimal("999999.00999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        y = new BigDecimal("0.00999999999");
        x = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        y = new BigDecimal("0.0999999999");
        x = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        y = new BigDecimal("0.999999999");
        x = new BigDecimal("999999.0999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        y = new BigDecimal("0.999999999");
        x = new BigDecimal("999999.999999999");
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        y = new BigDecimal("0.989999899");
        x = new BigDecimal("1.998999999");
        oom = -2;
        x.multiply(y);
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        y = new BigDecimal("0.999989999");
        x = new BigDecimal("1.999999899");
        oom = -5;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        y = new BigDecimal("0.999999899");
        x = new BigDecimal("1.999999899");
        oom = -7;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        y = new BigDecimal("0.999999998");
        x = new BigDecimal("1.999999899");
        oom = -8;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Case 4: x >= 1, y >= 1
        y = new BigDecimal("9900990099.0009999999994999999");
        x = new BigDecimal("9900990099.0009999999994999999");
        // Test 1
        oom = 10;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        //System.out.println(expResult.toPlainString());
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 7;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = 5;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = 1;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        x.multiply(y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = 0;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -1;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -3;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        oom = -5;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -9;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = -13;
        x.multiply(y);
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        oom = -19;
        expResult = Math_BigDecimal.multiply(x, y, oom, rm);
        result = Math_BigDecimal.multiplyPriorRound(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of multiplyPriorRound method, of class Math_BigDecimal. Test case
     * covered by {@link #testMultiplyPriorRound_4args_2()}.
     */
    @Test
    @Disabled
    public void testMultiplyPriorRound_3args_2() {
    }

    /**
     * Test of multiplyPriorRoundXLT1YLT1 method, of class Math_BigDecimal. Test
     * case covered by {@link #testMultiplyPriorRound_4args_2()}
     */
    @Test
    @Disabled
    public void testMultiplyPriorRoundXLT1YLT1() {
    }

    /**
     * Test of multiplyPriorRoundXLT1YGT1 method, of class Math_BigDecimal. Test
     * case covered by {@link #testMultiplyPriorRound_4args_2()}
     */
    @Test
    @Disabled
    public void testMultiplyPriorRoundXLT1YGT1() {
    }

    /**
     * Test of add method, of class Math_BigDecimal.
     */
    @Test
    public void testAdd_4args_1() {
        System.out.println("add");
        BigDecimal x;
        BigInteger y;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("123456789.987654321");
        y = new BigInteger("123456789");
        result = Math_BigDecimal.add(x, y, oom, rm);
        // This is the same code so is not really a test!
        expResult = Math_BigDecimal.round(x.add(new BigDecimal(y)), oom, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Math_BigDecimal.
     */
    @Test
    public void testAdd_4args_2() {
        System.out.println("add");
        BigDecimal x;
        BigDecimal y;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("123456789.987654321");
        y = new BigDecimal("987654321.123456789");
        result = Math_BigDecimal.add(x, y, oom, rm);
        // This is the same code so is not really a test!
        expResult = Math_BigDecimal.round(x.add(y), oom, rm);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Math_BigDecimal. Test case covered by
     * {@link #testAdd_3args_2()}
     */
    @Test
    @Disabled
    public void testAdd_3args_1() {
    }

    /**
     * Test of add method, of class Math_BigDecimal.
     */
    @Test
    public void testAdd_3args_2() {
        System.out.println("add");
        BigDecimal x;
        BigDecimal y;
        int oom = 0;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("123456789000000000000000000000.123456789");
        y = new BigDecimal("12345678900000000.129345967679");
        //System.out.println(x.add(y)); // 123456789000012345678900000000.252802756679
        expResult = new BigDecimal("123456789000012345678900000000");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 10;
        expResult = new BigDecimal("123456789000012345680000000000");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -2;
        expResult = new BigDecimal("123456789000012345678900000000.25");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -5;
        expResult = new BigDecimal("123456789000012345678900000000.2528");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("123456789123456789123456789.123456789");
        y = new BigDecimal("123456789123456789.129345967679");
        //System.out.println(x.add(y)); // 123456789246913578246913578.252802756679
        expResult = new BigDecimal("123456789246913578246913578.2528");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = 0;
        expResult = new BigDecimal("123456789246913578246913578");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = 10;
        expResult = new BigDecimal("123456789246913580000000000");
        result = Math_BigDecimal.add(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of addPriorRound method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testAddPriorRound_4args_1() {
        System.out.println("addPriorRound");
        BigDecimal x = null;
        BigInteger y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.addPriorRound(x, y, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPriorRound method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testAddPriorRound_4args_2() {
        System.out.println("addPriorRound");
        BigDecimal x = null;
        BigDecimal y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.addPriorRound(x, y, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPriorRound method, of class Math_BigDecimal.
     */
    @Test
    public void testAddPriorRound_3args_1() {
        System.out.println("addPriorRound");
        BigDecimal x;
        BigInteger y;
        int oom = 0;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("123456789000000000000000000000.123456789");
        y = new BigInteger("12345678900000000");
        //System.out.println(x.add(y));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 10;
        //System.out.println(x.add(y));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -2;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -5;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("123456789123456789123456789.123456789");
        y = new BigInteger("123456789123456789");
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = 0;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = 10;
        //System.out.println(x.add(new BigDecimal(y)).toPlainString());
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult.toPlainString());
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of addPriorRound method, of class Math_BigDecimal.
     */
    @Test
    public void testAddPriorRound_3args_2() {
        System.out.println("addPriorRound");
        BigDecimal x;
        BigDecimal y;
        int oom = 0;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("123456789000000000000000000000.123456789");
        y = new BigDecimal("12345678900000000.129345967679");
        //System.out.println(x.add(y));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 10;
        //System.out.println(x.add(y));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -2;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -5;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("123456789123456789123456789.123456789");
        y = new BigDecimal("123456789123456789.129345967679");
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = 0;
        //System.out.println(x.add(new BigDecimal(y)));
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult);
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = 10;
        //System.out.println(x.add(y).toPlainString());
        expResult = Math_BigDecimal.add(x, y, oom);
        //System.out.println(expResult.toPlainString());
        result = Math_BigDecimal.addPriorRound(x, y, oom);
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of divide method, of class Math_BigDecimal.
     */
    @Test
    public void testDivide_4args_2() {
        System.out.println("divide");
        BigDecimal x;
        BigInteger y;
        int oom = -5;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("1");
        y = new BigInteger("2");
        expResult = new BigDecimal("0.5");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        y = new BigInteger("3");
        expResult = new BigDecimal("0.33333");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        y = new BigInteger("4");
        expResult = new BigDecimal("0.25");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        y = new BigInteger("5");
        expResult = new BigDecimal("0.2");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        y = new BigInteger("6");
        expResult = new BigDecimal("0.16667");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        y = new BigInteger("7");
        expResult = new BigDecimal("0.14286");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("1");
        y = new BigInteger("8");
        expResult = new BigDecimal("0.125");
        result = Math_BigDecimal.divideNoRounding(x, y);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        y = new BigInteger("9");
        expResult = new BigDecimal("0.11111");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        oom = -10;
        x = new BigDecimal("987654321");
        y = new BigInteger("123456789");
        // 8.000000072900000663390006036849....
        expResult = new BigDecimal("8.0000000729");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        oom = -30;
        x = new BigDecimal("987654321");
        y = new BigInteger("123456789");
        expResult = new BigDecimal("8.000000072900000663390006036849");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        oom = -10;
        x = new BigDecimal("987654321");
        y = new BigInteger("12345");
        // 80004.400243013365735115431348724
        expResult = new BigDecimal("80004.4002430134");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        oom = 1;
        x = new BigDecimal("987654321");
        y = new BigInteger("12345");
        // 80004.400243013365735115431348724
        expResult = new BigDecimal("80000");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("30.121");
        y = new BigInteger("1234567777777777543567543564353642432656543254626");
        oom = -136;
        expResult = new BigDecimal(
                "0.000000000000000000000000000000000000000000000024398012439801"
                + "248608678869240874632067769163126799290634383673965707848596"
                + "269730703248463");
        result = Math_BigDecimal.divide(x, y, oom, rm);
        //System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocal_3args_1() {
        System.out.println("reciprocal");
        BigDecimal x;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        rm = RoundingMode.HALF_UP;
        oom = -10;
        x = BigDecimal.ONE;
        expResult = BigDecimal.ONE;
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.1");
        expResult = new BigDecimal("10");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.00000000000000000000001");
        expResult = new BigDecimal("100000000000000000000000");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -100;
        x = new BigDecimal("1000000000000000000000000.00000000000000000000001");
        expResult = new BigDecimal("9.99999999999999999999999999999999999999999"
                + "9999900000000000000000000000000000E-25");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -150;
        x = new BigDecimal("1000000000000000000000000.00000000000000000000001");
        expResult = new BigDecimal("9.99999999999999999999999999999999999999999"
                + "999990000000000000000000000000000000000000000000000100000000"
                + "000000000000000000000000E-25");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -200;
        x = new BigDecimal("1000000000000000000000000.00000000000000000000001");
        expResult = new BigDecimal("9.99999999999999999999999999999999999999999"
                + "999990000000000000000000000000000000000000000000000099999999"
                + "999999999999999999999999999999999999999000000000000000000000"
                + "00000000000000E-25");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocal_3args_2() {
        System.out.println("reciprocal");
        BigInteger x = null;
        int oom;
        RoundingMode rm;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        rm = RoundingMode.HALF_UP;
        oom = -10;
        x = BigInteger.ONE;
        expResult = BigDecimal.ONE;
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = BigInteger.ONE.negate();
        expResult = BigDecimal.ONE.negate();
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = BigInteger.TWO;
        expResult = new BigDecimal("0.5");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -100;
        x = new BigInteger("1000000000000000000000000");
        expResult = new BigDecimal("1E-24");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -10;
        x = new BigInteger("3");
        expResult = new BigDecimal("0.3333333333");
        result = Math_BigDecimal.reciprocal(x, oom, rm);
        //System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getPi method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPi() {
        System.out.println("getPi");
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal result = instance.getPi(oom, rm);
        //System.out.println(result);
        BigDecimal expResult = new BigDecimal("3.141592653589793238462643383279"
                + "502884197169399375105820974944592307816406286208998628034825"
                + "342117068");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        assertThrows(RuntimeException.class, () -> {
            instance.getPi(-10001, rm);
        });
    }

    /**
     * Test of getPiBy2 method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPiBy2() {
        System.out.println("getPiBy2");
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal result = instance.getPiBy2(oom, rm);
        //System.out.println(result);
        BigDecimal expResult = new BigDecimal("1.570796326794896619231321691639"
                + "751442098584699687552910487472296153908203143104499314017412"
                + "671058534");
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getPi2 method, of class Math_BigDecimal.
     */
    @Test
    public void testGetPi2() {
        System.out.println("getPi2");
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal result = instance.getPi2(oom, rm);
        //System.out.println(result);
        BigDecimal expResult = new BigDecimal("6.283185307179586476925286766559"
                + "005768394338798750211641949889184615632812572417997256069650"
                + "684234136");
        assertTrue(expResult.compareTo(result) == 0);
    }

    public MathContext getMathContext(BigDecimal result, int oom, RoundingMode rm) {
        return new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) + 1 - oom, rm);
    }
    /**
     * Test of cos method, of class Math_BigDecimal.
     */
    @Test
    public void testCos() {
        System.out.println("cos");
        BigDecimal x;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal pi;
        oom = -100;
        BigDecimal expResult;
        BigDecimal result;
        MathContext mc;
        // Test 1
        x = new BigDecimal("0");
        expResult = new BigDecimal("1");
        result = bd.cos(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2: x = PI/4
        oom = -30;
        pi = bd.getPi(oom - 3, rm);
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(4),
                oom - 2, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3: 87*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(87)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4: 81*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(81)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5: 75*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(75)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6: 72*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(72)), BigInteger.valueOf(180),
                oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 7: 54*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(54)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = getMathContext(result, oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of sin method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testSin() {
        System.out.println("sin");
        BigDecimal x;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal expResult;
        BigDecimal result;
        BigDecimal pi;
        int oom = -30;
        pi = bd.getPi(oom - 3, rm);
        // Test 1: Test PI/4
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(4),
                oom - 2, rm);
        MathContext mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.sin(x, mc);
        //expResult = new BigDecimal("0.707106781186547524400844362105");
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2: PI/60
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(60),
                oom - 10, rm);
//        BigDecimal sqrt30 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(30), dp + 10, rm);
//        BigDecimal sqrt10 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(10), dp + 10, rm);
//        BigDecimal sqrt6 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(6), dp + 10, rm);
//        BigDecimal sqrt5 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(5), dp + 10, rm);
//        BigDecimal sqrt2 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(2), dp + 10, rm);
//        // (sqrt(30) + sqrt(10) + sqrt(20 + 4*sqrt(5)) - sqrt(6) - sqrt(2) - sqrt(60 + 12*sqrt(5)))/16
//        BigDecimal splurge1 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(60).add(BigDecimal.valueOf(12).multiply(sqrt5)), dp + 10, rm);
//        // (sqrt(30) + sqrt(10) + sqrt(20 + 4*sqrt(5)) - sqrt(6) - sqrt(2) - splurge1)/16
//        BigDecimal splurge2 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(20).add(BigDecimal.valueOf(4).multiply(sqrt5)), dp + 10, rm);
//        // (sqrt(30) + sqrt(10) + splurge2 - sqrt(6) - sqrt(2) - splurge1)/16
//        BigDecimal splurge = sqrt30.add(sqrt10).add(splurge2).subtract(sqrt6).subtract(sqrt2).subtract(splurge1);
//        expResult = Math_BigDecimal.divideRoundIfNecessary(splurge,
//                BigDecimal.valueOf(16), dp, rm);
        mc = new MathContext(oom, rm);
        expResult = BigDecimalMath.sin(x, mc);
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3: PI/20
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(20),
                oom + 10, rm);
//        BigDecimal sqrt90 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(90), dp + 10, rm);
//        BigDecimal sqrt18 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(18), dp + 10, rm);
//        // (sqrt(90) + sqrt(18) + sqrt(10) + sqrt(2) - sqrt(20 - 4*sqrt(5)) - sqrt(180 - 36*sqrt(5)))/5
//        splurge1 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(20).subtract(BigDecimal.valueOf(4).multiply(sqrt5)), dp + 10, rm);
//        // (sqrt(90) + sqrt(18) + sqrt(10) + sqrt(2) - splurge1 - sqrt(180 - 36*sqrt(5)))/5
//        splurge2 = Math_BigDecimal.sqrt(
//                BigDecimal.valueOf(180).subtract(BigDecimal.valueOf(36).multiply(sqrt5)), dp + 10, rm);
//        // (sqrt(90) + sqrt(18) + sqrt(10) + sqrt(2) - splurge1 - splurge2)/5
//        splurge = sqrt90.add(sqrt18).add(sqrt10).add(sqrt2).subtract(splurge1).subtract(splurge2);
//        expResult = Math_BigDecimal.divideRoundIfNecessary(splurge,
//                BigDecimal.valueOf(32), dp, rm);
        expResult = BigDecimalMath.sin(x, mc);
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4: PI/12
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(12),
                oom + 10, rm);
//        BigDecimal sqrt6subtractsqrt2 = sqrt6.subtract(sqrt2);
//        expResult = Math_BigDecimal.divideRoundIfNecessary(sqrt6subtractsqrt2,
//                BigDecimal.valueOf(4), dp, rm);
        expResult = BigDecimalMath.sin(x, mc);
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5: PI/10
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(10),
                oom + 10, rm);
//        BigDecimal sqrtsubtract1 = sqrt5.subtract(BigDecimal.ONE);
//        expResult = Math_BigDecimal.divideRoundIfNecessary(sqrtsubtract1,
//                BigDecimal.valueOf(4), dp, rm);
        expResult = BigDecimalMath.sin(x, mc);
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6: PI/5
        x = Math_BigDecimal.divide(pi, BigInteger.valueOf(5),
                oom + 10, rm);
//        BigDecimal tenSubtractTwoTimesSqrt5 = BigDecimal.valueOf(10).subtract(
//                sqrt5.multiply(BigDecimal.valueOf(2)));
//        expResult = Math_BigDecimal.sqrt(tenSubtractTwoTimesSqrt5, dp + 5, rm);
//        expResult = Math_BigDecimal.divideRoundIfNecessary(expResult,
//                BigDecimal.valueOf(4), dp, rm);
        expResult = BigDecimalMath.sin(x, mc);
        result = bd.sin(x, oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of sinNoCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testSinNoCaseCheck() {
        System.out.println("sinNoCaseCheck");
        BigDecimal x = null;
        BigDecimal aPI = null;
        BigDecimal twoPI = null;
        BigDecimal aPIBy2 = null;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.sinNoCaseCheck(x, aPI, twoPI, aPIBy2, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sinAngleBetweenZeroAndPI method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testSinAngleBetweenZeroAndPI() {
        System.out.println("sinAngleBetweenZeroAndPI");
        BigDecimal x = null;
        BigDecimal aPI = null;
        BigDecimal twoPI = null;
        int oom = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.sinAngleBetweenZeroAndPI(x, aPI, twoPI, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tan method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testTan() {
        System.out.println("tan");
        BigDecimal x;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal expResult;
        BigDecimal pi;
        BigDecimal result;
        pi = bd.getPi(oom, rm);
        // Test 1: x = PI/4
        x = Math_BigDecimal.divide(pi,
                BigInteger.valueOf(4), oom - 2, rm);
        result = bd.tan(x, oom, rm);
        MathContext mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.tan(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of getScaleOfMostSignificantDigit method, of class Math_BigDecimal.
     */
    @Test
    public void testGetScaleOfMostSignificantDigit() {
        System.out.println("getScaleOfMostSignificantDigit");
        BigDecimal x = new BigDecimal("10001.1");
        int expResult = 5;
        int result = Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.1");
        expResult = -1;
        result = Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("0.0001");
        expResult = -4;
        result = Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 4
        x = new BigDecimal("1.0001");
        expResult = 1;
        result = Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        assertEquals(expResult, result);
        // Test 5
        x = new BigDecimal("10.0001");
        expResult = 2;
        result = Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testExp_3args_1() {
        System.out.println("exp");
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        MathContext mc;
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal x;
        // Test 1
        x = BigDecimal.valueOf(2);
        oom = -10;
        result = bd.exp(x, oom, rm);
        //expResult = new BigDecimal("7.3890560989");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("0.02");
        result = bd.exp(x, oom, rm);
        //expResult = new BigDecimal("1.0202013400");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("0.000000012");
        result = bd.exp(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("0.000000012");
        oom = -100;
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "1.000000012000000072000000288000000864000002073600004147200007"
//                + "1094857249499428713618285884913371614711");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("1234");
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "83059759373617942182585212913113567351910010438992024991210999"
//                + "5454923315008203249228622332287168900783807203236941"
//                + "4902057190611503380339135282346122079463872568744812"
//                + "9160282114608484850219955438665976430056110355929008"
//                + "1568915299754172470903436740058467313053059744618822"
//                + "7760213564550738008482852338833888837967092607345470"
//                + "7870887762743415777162495569954964980646026647274941"
//                + "9574313243340244466395229965260298922134183326397331"
//                + "1296815702676888489308238510796136610049551587722092"
//                + "6062479018036337690481712828521819397655551425938830"
//                + "264837.973417052783657749759051806294126581414124375"
//                + "6552569443184292961499614756472883606953172641990622"
//                + "460");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("0.5678");
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "1.764381139990485997370555270712146080977921321938820437756994"
//                7484665772482841560184819887244807497714");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("1234.5678");
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "14654907293095947998348213850397980421762219967522365396627015"
//                + "7446954995433819741094410764947112047906012815540251"
//                + "0099496044260696725324177360570330992742045983853145"
//                + "9484650997562904686479876588810478907498492770961626"
//                + "1452461385220475510438783429614855364551372899974754"
//                + "9385827529882582456544414514903991140262837264067071"
//                + "5856383062081491926713908229300604276719403032558655"
//                + "8598102530989548975007326686244681359829698224824777"
//                + "2230516585207831661198442009726028544382751383530565"
//                + "3512991383291690403456444424125771608013650132825780"
//                + "7115515.06895420242229125976133533005718191453230212"
//                + "0267300236914758300188509653349524745461710911967049"
//                + "3253");
        // 1.4654907293095947998348213850398e+536
        // 1.4654907293095947998348213850397980421762219967522365396627015
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        System.out.println("res=" + result.toPlainString());
        System.out.println("exp=" + expResult.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("1");
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "2.718281828459045235360287471352662497757247093699959574966967"
//                + "6277240766303535475945713821785251664274");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("1");
        oom = -1000;
        result = bd.exp(x, oom, rm);
//        expResult = new BigDecimal(
//                "2.718281828459045235360287471352662497757247093699959574966967"
//                + "627724076630353547594571382178525166427427466391932003059921"
//                + "817413596629043572900334295260595630738132328627943490763233"
//                + "829880753195251019011573834187930702154089149934884167509244"
//                + "761460668082264800168477411853742345442437107539077744992069"
//                + "551702761838606261331384583000752044933826560297606737113200"
//                + "709328709127443747047230696977209310141692836819025515108657"
//                + "463772111252389784425056953696770785449969967946864454905987"
//                + "931636889230098793127736178215424999229576351482208269895193"
//                + "668033182528869398496465105820939239829488793320362509443117"
//                + "301238197068416140397019837679320683282376464804295311802328"
//                + "782509819455815301756717361332069811250996181881593041690351"
//                + "598888519345807273866738589422879228499892086805825749279610"
//                + "484198444363463244968487560233624827041978623209002160990235"
//                + "304369941849146314093431738143640546253152096183690888707016"
//                + "768396424378140592714563549061303107208510383750510115747704"
//                + "1718986106873969655212671546889570350354");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("1");
        oom = -1001;
//        expResult = new BigDecimal(
//                "2.718281828459045235360287471352662497757247093699959574966967"
//                + "627724076630353547594571382178525166427427466391932003059921"
//                + "817413596629043572900334295260595630738132328627943490763233"
//                + "829880753195251019011573834187930702154089149934884167509244"
//                + "761460668082264800168477411853742345442437107539077744992069"
//                + "551702761838606261331384583000752044933826560297606737113200"
//                + "709328709127443747047230696977209310141692836819025515108657"
//                + "463772111252389784425056953696770785449969967946864454905987"
//                + "931636889230098793127736178215424999229576351482208269895193"
//                + "668033182528869398496465105820939239829488793320362509443117"
//                + "301238197068416140397019837679320683282376464804295311802328"
//                + "782509819455815301756717361332069811250996181881593041690351"
//                + "598888519345807273866738589422879228499892086805825749279610"
//                + "484198444363463244968487560233624827041978623209002160990235"
//                + "304369941849146314093431738143640546253152096183690888707016"
//                + "768396424378140592714563549061303107208510383750510115747704"
//                + "17189861068739696552126715468895703503540");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("1");
        oom = -2000;
//        expResult = new BigDecimal(
//                "2.718281828459045235360287471352662497757247093699959574966967"
//                + "627724076630353547594571382178525166427427466391932003059921"
//                + "817413596629043572900334295260595630738132328627943490763233"
//                + "829880753195251019011573834187930702154089149934884167509244"
//                + "761460668082264800168477411853742345442437107539077744992069"
//                + "551702761838606261331384583000752044933826560297606737113200"
//                + "709328709127443747047230696977209310141692836819025515108657"
//                + "463772111252389784425056953696770785449969967946864454905987"
//                + "931636889230098793127736178215424999229576351482208269895193"
//                + "668033182528869398496465105820939239829488793320362509443117"
//                + "301238197068416140397019837679320683282376464804295311802328"
//                + "782509819455815301756717361332069811250996181881593041690351"
//                + "598888519345807273866738589422879228499892086805825749279610"
//                + "484198444363463244968487560233624827041978623209002160990235"
//                + "304369941849146314093431738143640546253152096183690888707016"
//                + "768396424378140592714563549061303107208510383750510115747704"
//                + "171898610687396965521267154688957035035402123407849819334321"
//                + "068170121005627880235193033224745015853904730419957777093503"
//                + "660416997329725088687696640355570716226844716256079882651787"
//                + "134195124665201030592123667719432527867539855894489697096409"
//                + "754591856956380236370162112047742722836489613422516445078182"
//                + "442352948636372141740238893441247963574370263755294448337998"
//                + "016125492278509257782562092622648326277933386566481627725164"
//                + "019105900491644998289315056604725802778631864155195653244258"
//                + "698294695930801915298721172556347546396447910145904090586298"
//                + "496791287406870504895858671747985466775757320568128845920541"
//                + "334053922000113786300945560688166740016984205580403363795376"
//                + "452030402432256613527836951177883863874439662532249850654995"
//                + "886234281899707733276171783928034946501434558897071942586398"
//                + "772754710962953741521115136835062752602326484728703920764310"
//                + "059584116612054529703023647254929666938115137322753645098889"
//                + "031360205724817658511806303644281231496550704751025446501172"
//                + "721155519486685080036853228183152196003735625279449515828418"
//                + "82947876108526398140");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("2");
        oom = -5;
        //expResult = new BigDecimal("7.38095");
//        expResult = new BigDecimal("7.38906");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("2");
        oom = -1001;
//        expResult = new BigDecimal(
//                "7.389056098930650227230427460575007813180315570551847324087127"
//                + "822522573796079057763384312485079121794773753161265478866123"
//                + "884603692781273374478392213398077774900122895607410753702391"
//                + "330947550682086581820269647868208404220982255234875742462541"
//                + "414679928129331888070763301019337899740729986960095303307515"
//                + "320818823684694793029913558771445683123923272764602588339996"
//                + "461212849285209678905138824663987122813726861064735626379295"
//                + "182227842948434586135287693866985752001549960148075071971293"
//                + "369418851997228882636255971941095866191479871504328397693264"
//                + "610235116312389990010513783406764498663892685615821864215577"
//                + "248492011193531621171951731747269796829345199850541848631971"
//                + "356859470229125573983561105149793681450277644807642985104182"
//                + "117055944191787683471285276497809713462504140235242158740938"
//                + "668254271570392645296404550628778001311092650138483345302646"
//                + "363141560471888117657942786348599076704527119372958723995987"
//                + "073310814961253109770593530099050329681075421090877626308572"
//                + "48500382787227614486674505649873858771575");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 14
        x = new BigDecimal("2.1");
        oom = -10;
//        expResult = new BigDecimal("8.1661699126");
        result = bd.exp(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 15
        x = new BigDecimal("2.10111010101010101010101111");
        oom = -20;
//        expResult = new BigDecimal("8.17524021958327462764");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 16
        x = new BigDecimal("0.5");
        oom = -20;
//        expResult = new BigDecimal("1.64872127070012814685");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 17
        x = new BigDecimal("0.0000001");
        oom = -100;
//        expResult = new BigDecimal(
//                "1.000000100000005000000166666670833333416666668055555575396825"
//                + "6448412725970017912257498096039783583187");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 18
        x = new BigDecimal("-0.0000001");
//        expResult = new BigDecimal(
//                "0.999999900000004999999833333337499999916666668055555535714285"
//                + "9623015845458554067460314955106642650045");
        mc = new MathContext(Math_BigDecimal.getScaleOfMostSignificantDigit(result) - oom);
        expResult = BigDecimalMath.exp(x, mc);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testExp_3args_2() {
        System.out.println("exp");
        BigInteger x = new BigInteger("10");
        Math_BigDecimal bd = new Math_BigDecimal();
        int oom = -1;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult = new BigDecimal("22026.5");
        BigDecimal result = bd.exp(x, oom, rm);
        assertEquals(expResult, result);
        // Test 2
        oom = -10;
        expResult = new BigDecimal("22026.4657948067");
        result = bd.exp(x, oom, rm);
        assertEquals(expResult, result);
        // Test 3
        x = BigInteger.TWO;
        oom = -20;
        expResult = new BigDecimal("7.38905609893065022723");
        result = bd.exp(x, oom, rm);
        assertEquals(expResult, result);
        // Test 4
        x = BigInteger.valueOf(100);
        oom = -20;
        expResult = new BigDecimal("2688117141816135448412625551580013587361111"
                + "8.77374192241519160862");
        result = bd.exp(x, oom, rm);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 5
        x = BigInteger.valueOf(1000);
        oom = -20;
        expResult = new BigDecimal("1970071114017046993888879352243323125316937"
                + "985323845789952802991385063850782441193474978076563026889930"
                + "963817987520226935982981730544612899232627836601528252323205"
                + "351695845667561922715676027880714224668263140068551685086534"
                + "979416603160453678179380929052997285801328699458564702865343"
                + "759004565643555891562204223202605188261122886383583722487247"
                + "252145061504188819374941008712642322484363157605603774399306"
                + "23959705844189509050047074217568.22675780833081020707");
        result = bd.exp(x, oom, rm);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 6
        x = BigInteger.TWO;
        oom = -200;
        expResult = new BigDecimal("7.38905609893065022723042746057500781318031"
                + "557055184732408712782252257379607905776338431248507912179477"
                + "375316126547886612388460369278127337447839221339807777490012"
                + "289560741075370239133094755068208658182");
        result = bd.exp(x, oom, rm);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 7
        x = BigInteger.valueOf(100);
        oom = -200;
        expResult = new BigDecimal("2688117141816135448412625551580013587361111"
                + "8.7737419224151916086152802870349095649141588710972198457108"
                + "116708791905760686975977097618682335484596389298719660896291"
                + "336261200293809572765340329622698656680169177435144518460651"
                + "6280444223775676229696");
        result = bd.exp(x, oom, rm);
        //System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of asin method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of atan method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testAtan() {
        System.out.println("atan");
        BigDecimal x = null;
        int oom = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.atan(x, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acos method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testAcos() {
        System.out.println("acos");
        BigDecimal x = null;
        int oom = 0;
        RoundingMode rm = null;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.acos(x, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
