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
//import org.hamcrest.Matchers;
//import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

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
        BigDecimal result = null;
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
        rm = RoundingMode.HALF_UP;
        oom = -9;
        expResult = new BigDecimal("1386.345677626");
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        //x.multiply(new BigDecimal(y));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("1.123456789");
        y = new BigInteger("1234");
        rm = RoundingMode.HALF_UP;
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
        BigDecimal x = null;
        BigDecimal y = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = null;
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
        rm = RoundingMode.HALF_UP;
        oom = -13;
        expResult = new BigDecimal("1386.9835763907942");
        System.out.println(x.multiply(y));
        result = Math_BigDecimal.multiply(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("99999.99999");
        y = new BigDecimal("9999999.9999999");
        rm = RoundingMode.HALF_UP;
        oom = -12;
        expResult = new BigDecimal("999999999899.990000000001");
        System.out.println(x.multiply(y));
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
    public void testPowerTestAbove() {
        System.out.println("powerTestAbove");
        BigDecimal compare;
        BigDecimal x;
        BigInteger y;
        int div = 64;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestAbove(compare, x, y, div, oom, rm));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestAbove(compare, x, y, div, oom, rm));
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
        int div = 64;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestAboveNoRounding(compare, x, y, div));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestAboveNoRounding(compare, x, y, div));
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
        int div = 64;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestBelow(compare, x, y, div, oom, rm));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestBelow(compare, x, y, div, oom, rm));
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
        int div = 64;
        // Test 1
        compare = new BigDecimal("100");
        x = new BigDecimal("9.99999999999999999999999999999999999999999999999");
        y = new BigInteger("2");
        assertTrue(Math_BigDecimal.powerTestBelowNoRounding(compare, x, y, div));
        // Test 2
        compare = new BigDecimal("100");
        x = new BigDecimal("10.0000000000000000000000000000000000000000000001");
        y = new BigInteger("2");
        assertFalse(Math_BigDecimal.powerTestBelowNoRounding(compare, x, y, div));
    }

    /**
     * Test of power method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testPower_4args_1() {
        System.out.println("power");
        BigDecimal x;
        BigDecimal y;
        int oom = -100;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("5.1");
        y = new BigDecimal("0.0122");
        expResult = new BigDecimal(
                "1.020075592235233496296126665026067483207748617172583488021310"
                + "8734138226078100585587805435645098947068");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
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
        // Test 4
        x = new BigDecimal("5.1");
        y = new BigDecimal("-0.000000002");
        expResult = new BigDecimal(
                "0.999999996741518925848289291581390088493740454739378865003717"
                + "6765269179691805288720372301300181098212");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("5.1");
        y = new BigDecimal("-0.000000000002");
        expResult = new BigDecimal(
                "0.999999999996741518920544748674219018662252981309537110886643"
                + "3325558136648752946348246771904715458001");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal(
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
        y = x.negate();
        expResult = new BigDecimal(
                "0.065988035845312537076790187596846424938577048252796436402473"
                + "5415723927466340880862459929685632483709");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("0.9");
        y = new BigDecimal("200");
        expResult = new BigDecimal(
                "0.000000000705507910865533257124642715759347962165079496127873"
                + "1576287122320926208555158293415657929853");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 8
        x = new BigDecimal("1.1");
        y = new BigDecimal("200");
//        expResult = new BigDecimal(
//                "189905276.4604618242121820463954116340585832240009877848127251"
//                + "456103762646167989140750662066593328455813588159");
        expResult = new BigDecimal(
                "189905276.4604618242121820463954116340585832240009877848127251"
                + "456103762646167989140750662066593328455813588181");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 9
        x = new BigDecimal("1.0000000000000000000000000000000001");
        y = new BigDecimal("20000");
//        expResult = new BigDecimal(
//                "1.000000000000000000000000000002000000000000000000000000000001"
//                + "9999000000000000000000000000013331331204");
        expResult = new BigDecimal(
                "1.000000000000000000000000000002000000000000000000000000000001"
                + "9999000000000000000000000000013331333400");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 10
        x = new BigDecimal("1.000000000000000001234567");
        y = new BigDecimal("2000078764654345654");
        expResult = new BigDecimal(
                "11.80925729618850935047900283877251871123117528236149368178447"
                + "43606317427155216550611985572658924014225");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 11
        x = new BigDecimal("0.9");
        y = new BigDecimal("0.9");
        expResult = new BigDecimal(
                "0.909532576082962189535366090754262974443473210154553394006625"
                + "8156584379857622915775444454069734604216");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 12
        x = new BigDecimal("0.1");
        y = new BigDecimal("0.9");
        expResult = new BigDecimal(
                "0.125892541179416721042395410639580060609361740946693106910792"
                + "301952664761578250202412105096627594617");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 13
        x = new BigDecimal("0.9");
        y = new BigDecimal("0.1");
        expResult = new BigDecimal(
                "0.989519258206214392646230170419804832155538415337091539600605"
                + "544414212962464564723600065458219541611");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 14
        x = new BigDecimal("0.9");
        y = new BigDecimal("0.12");
        expResult = new BigDecimal(
                "0.987436328376606708739063494229909542239622211966688276138278"
                + "1495703008692225765762583023898416587461");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 15
        x = new BigDecimal("0.09");
        y = new BigDecimal("0.12");
        expResult = new BigDecimal(
                "0.749047055475647117310421468815370379819904146132649930111430"
                + "8616900168333963941855154477042245424988");
//        expResult = new BigDecimal(
//                "0.749047055475647117310421468815370379819904146132649930111430"
//                + "8616900168333963941855154477042245371721");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 16
        x = new BigDecimal("0.1");
        y = new BigDecimal("0.999991");
        expResult = new BigDecimal(
                "0.100002072348056530317390970017713311383030160316867649898675"
                + "1042536233958380984224300502095831919852");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 17
        x = new BigDecimal("0.1");
        y = new BigDecimal("0.9999999991");
        expResult = new BigDecimal(
                //0.10000000020723265858419098518432
                "0.100000000207232449276900938780572607600670362630328624897040"
                + "9053787443199517073876579178209190227768");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 18
        x = new BigDecimal("0.92");
        y = new BigDecimal(
                "0.0040983606557377051313184601610828394768759608268737792968");
        expResult = new BigDecimal(
                "0.999658330476842250517859191452511308247518473356535240882710"
                + "507544024872721215314306435063439340094");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 19
        x = new BigDecimal("-32");
        y = new BigDecimal("0.2");
        expResult = new BigDecimal("-2");
        result = Math_BigDecimal.power(x, y, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
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
    @Disabled
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
    @Disabled
    public void testPower_5args_1() {
        System.out.println("power");
        BigDecimal x = null;
        int y = 0;
        int div = 0;
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        oom = -10;
        x = new BigDecimal("5.1");
        y = 13;
        div = 8;
        expResult = new BigDecimal("1579109656.3156692196");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("5.1");
        y = 13;
        div = 12;
        expResult = new BigDecimal("1579109656.3156692196");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("5.1");
        y = 117;
        div = 8;
        expResult = new BigDecimal(
                "61053505308866480538551405717028781301447815633741042337063285"
                + "637565296816330773386.1309180477");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("3.14159265");
        y = 10;
        div = 3;
        expResult = new BigDecimal("93648.0464059980");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -20;
        x = new BigDecimal("3.14159265");
        y = 10;
        div = 3;
        expResult = new BigDecimal("93648.04640599799415742896");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -30;
        x = new BigDecimal("3.14159265");
        y = 10;
        div = 3;
        expResult = new BigDecimal("93648.046405997994157428955669854799");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        x = new BigDecimal("3");
        y = -10;
        div = 3;
        expResult = new BigDecimal("0.000016935087808430286711036597");
        result = Math_BigDecimal.power(x, y, div, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
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
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of rootUnscaled1Precision1 method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testRootUnscaled1Precision1() {
        System.out.println("rootUnscaled1Precision1");
        BigDecimal x;
        int y;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        int test = 1;
        x = new BigDecimal("100");
        y = 2;
        int oom;
        oom = -10;
        expResult = new BigDecimal(BigInteger.TEN);
        expResult = expResult.setScale(oom);
        result = Math_BigDecimal.rootUnscaled1Precision1(x, y, oom);
        assertEquals(expResult, result);
        // Test 2
        test++;
        x = new BigDecimal("125");
        y = 3;
        oom = -1;
        expResult = new BigDecimal(BigInteger.valueOf(5));
        expResult = expResult.setScale(oom);
        result = Math_BigDecimal.rootUnscaled1Precision1(x, y, oom);
        assertEquals(expResult, result);
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

    }

    /**
     * Test of powerNoRounding method, of class Math_BigDecimal.
     */
    @Test
    public void testPowerNoRounding_3args_2() {
        System.out.println("powerNoRounding");
        BigDecimal x;
        BigInteger y;
        int div;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        int test = 1;
        x = new BigDecimal("5.1");
        y = new BigInteger("20");
        div = 2;
        expResult = new BigDecimal("141710986707530.43575626125424226001");
        result = Math_BigDecimal.powerNoRounding(x, y, div);
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
        result = Math_BigDecimal.powerNoRounding(x, y, div);
        assertEquals(expResult, result);
        // Test 3
        test++;
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = new BigInteger("20");
        div = 4;
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
        result = Math_BigDecimal.powerNoRounding(x, y, div);
        assertEquals(expResult, result);
        // Test 4
        test++;
        x = new BigDecimal("5.10000000000000000000000000000000000000000000001");
        y = new BigInteger("200");
        div = 4;
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
        result = Math_BigDecimal.powerNoRounding(x, y, div);
        assertEquals(expResult, result);
    }

    /**
     * Test of powerNoSpecialCaseCheck method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of powerNoSpecialCaseCheckNoRounding method, of class
     * Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    public void testReciprocal() {
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
    @Disabled
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
        oom = 10;
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
        oom = 10;
        rm = RoundingMode.HALF_UP;
        expResult = new BigDecimal("5.0004345113");
        result = Math_BigDecimal.log(base, x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of log method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
    @Disabled
    public void testRootRoundIfNecessary_4args_1() {
        System.out.println("rootRoundIfNecessary");
        BigDecimal x;
        BigInteger root;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        MathContext mc;
        BigDecimal expResult;
        BigDecimal result;
        // Test 1
        x = new BigDecimal("0.25");
        root = new BigInteger("42");
        oom = -10;
        result = Math_BigDecimal.root(x, root, oom, rm);
        //expResult = new BigDecimal("0.9675317785");
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        x = new BigDecimal("27");
        root = new BigInteger("3");
        expResult = new BigDecimal("3");
        result = Math_BigDecimal.root(x, root, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        x = new BigDecimal("8904831940328.25");
        root = new BigInteger("10");
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        //expResult = Math_BigDecimal.round(expResult, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        x = new BigDecimal("8904831940328.25");
        root = new BigInteger("100");
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("8904831940328.25");
        root = new BigInteger("500");
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        x = new BigDecimal("8904831940328.25");
        root = new BigInteger("1000");
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
        // Test 10
        x = new BigDecimal("0.999");
        root = new BigInteger("100023");
        expResult = new BigDecimal(
                "0.999999989997297335815964171495970213458904791728888003317236"
                + "9473900240303824000659869633098129663322");
        result = Math_BigDecimal.root(x, root, oom, rm);
        mc = new MathContext(-oom + Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1, rm);
        expResult = BigDecimalMath.root(x, new BigDecimal(root), mc);
        assertTrue(expResult.compareTo(result) == 0);
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
    @Disabled
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
     * Test of root method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testRootRoundIfNecessary_4args_2() {
        System.out.println("rootRoundIfNecessary");
        BigDecimal x = null;
        int root = 0;
        int dp = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.root(x, root, dp, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rootNoRounding method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
    @Disabled
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
        x = new BigDecimal("10000");
        expResult = new BigDecimal("100");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -1;
        x = new BigDecimal("0.01");
        expResult = new BigDecimal("0.1");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        oom = -7;
        x = new BigDecimal("0.00000001");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        MathContext mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        //expResult = new BigDecimal("0.0001");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = -7;
        x = new BigDecimal("0.000000001");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        //expResult = new BigDecimal("0.0000316");
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -5;
        x = new BigDecimal("2");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = -100;
        x = new BigDecimal("2");
        result = Math_BigDecimal.sqrt(x, oom, rm);
        mc = new MathContext(Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(result) + 1 - oom, rm);
        //expResult = BigDecimalMath.sqrt(x, mc);
        expResult = x.sqrt(mc);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of randomUniformTest method, of class Math_BigDecimal.
     */
    @Test
    public void testRandomUniformTest_4args() {
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
     * Test of getRandom method, of class Math_BigDecimal.
     */
    @Test
    public void testGetRandom_4args() {
        System.out.println("getRandom");
        int oom;
        BigDecimal expResult;
        BigDecimal result;
        Math_BigDecimal bd = new Math_BigDecimal();
        // Test 1
        oom = -10;
        System.out.println("Test 2");
        BigDecimal lowerBound = BigDecimal.ZERO;
        BigDecimal upperBound = BigDecimal.ONE;
        //expResult = new BigDecimal("0.4932604312");
        expResult = new BigDecimal("0.4106274901");
        result = Math_BigDecimal.getRandom(bd.bi, oom, lowerBound, upperBound);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRandom method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testExp_BigDecimal_int() {
        System.out.println("exp");
        Math_BigDecimal bd = new Math_BigDecimal();
        BigDecimal x;
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigDecimal expResult;
        BigDecimal result;
        MathContext mc;
        // Test 1
        int test = 1;
        x = BigDecimal.valueOf(2);
        oom = -10;
        result = bd.exp(x, oom, rm);
        //expResult = new BigDecimal("7.3890560989");
        mc = new MathContext(-oom + result.precision() - result.scale());
        expResult = BigDecimalMath.exp(x, mc);
        expResult = Math_BigDecimal.round(expResult, oom);
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.comparesEqualTo(result));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        test++;
        x = new BigDecimal("0.02");
        oom = -10;
        result = bd.exp(x, oom, rm);
        //expResult = new BigDecimal("1.0202013400");
        mc = new MathContext(-oom + result.precision() - result.scale());
        expResult = BigDecimalMath.exp(x, mc);
        expResult = Math_BigDecimal.round(expResult, oom);
        //printFunctionTest(funcName, test, x, dp, rm, result);
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.comparesEqualTo(result));
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        test++;
        x = new BigDecimal("0.000000012");
        oom = -10;
        result = bd.exp(x, oom, rm);
        int mr = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        if (mr > 0) {
            mr++;
        }
        mc = new MathContext(-oom - mr);
        expResult = BigDecimalMath.exp(x, mc);
        //expResult = Math_BigDecimal.round(expResult, oom);
        //printFunctionTest(funcName, test, x, dp, rm, result);
        //assertEquals(expResult, result);
        //assertThat(expResult, Matchers.comparesEqualTo(result));
        assertTrue(expResult.compareTo(result) == 0);
//        // Test 4
//        test++;
//        x = new BigDecimal("0.000000012");
//        dp = 100;
//        rm = RoundingMode.HALF_UP;
//        result = bd.exp(x, bd, dp, rm);
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
     * Test of exp method, of class Math_BigDecimal.
     */
    @Test
    public void testExp_BigInteger_int() {
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
        System.out.println(result);
        assertEquals(expResult, result);
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
        System.out.println("multiplyPriorRound");
        // No test.
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
        System.out.println(expResult.toPlainString());
        System.out.println(result.toPlainString());
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
        System.out.println("multiplyPriorRound");
        // No test.
    }

    /**
     * Test of multiplyPriorRoundXLT1YLT1 method, of class Math_BigDecimal. Test
     * case covered by {@link #testMultiplyPriorRound_4args_2()}
     */
    @Test
    @Disabled
    public void testMultiplyPriorRoundXLT1YLT1() {
        System.out.println("multiplyPriorRoundXLT1YLT1");
        // No test.
    }

    /**
     * Test of multiplyPriorRoundXLT1YGT1 method, of class Math_BigDecimal. Test
     * case covered by {@link #testMultiplyPriorRound_4args_2()}
     */
    @Test
    @Disabled
    public void testMultiplyPriorRoundXLT1YGT1() {
        System.out.println("multiplyPriorRoundXLT1YGT1");
        // No test.
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
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 2
        oom = 10;
        expResult = new BigDecimal("123456789000012345680000000000");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 3
        oom = -2;
        expResult = new BigDecimal("123456789000012345678900000000.25");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 4
        oom = -5;
        expResult = new BigDecimal("123456789000012345678900000000.2528");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 5
        x = new BigDecimal("123456789123456789123456789.123456789");
        y = new BigDecimal("123456789123456789.129345967679");
        //System.out.println(x.add(y)); // 123456789246913578246913578.252802756679
        expResult = new BigDecimal("123456789246913578246913578.2528");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 6
        oom = 0;
        expResult = new BigDecimal("123456789246913578246913578");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
        assertTrue(expResult.compareTo(result) == 0);
        // Test 7
        oom = 10;
        expResult = new BigDecimal("123456789246913580000000000");
        result = Math_BigDecimal.add(x, y, oom);
        System.out.println(result);
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
        System.out.println(result.toPlainString());
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testReciprocal_3args_1() {
        System.out.println("reciprocal");
        BigDecimal x = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.reciprocal(x, oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reciprocal method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testReciprocal_3args_2() {
        System.out.println("reciprocal");
        BigInteger x = null;
        int oom = 0;
        RoundingMode rm = null;
        BigDecimal expResult = null;
        BigDecimal result = Math_BigDecimal.reciprocal(x, oom, rm);
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
        System.out.println(result);
        BigDecimal expResult = new BigDecimal("1.570796326794896619231321691639"
                + "751442098584699687552910487472296153908203143104499314017412"
                + "671058534");
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getPi2 method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
    public void testGetPi2() {
        System.out.println("getPi2");
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_BigDecimal instance = new Math_BigDecimal();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPi2(oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cos method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
        mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3: 87*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(87)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4: 81*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(81)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5: 75*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(75)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6: 72*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(72)), BigInteger.valueOf(180),
                oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = new MathContext(-oom, rm);
        expResult = BigDecimalMath.cos(x, mc);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 7: 54*PI/180
        x = Math_BigDecimal.divide(
                pi.multiply(BigDecimal.valueOf(54)),
                BigInteger.valueOf(180), oom - 10, rm);
        result = bd.cos(x, oom, rm);
        mc = new MathContext(-oom, rm);
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
     * Test of atan method, of class Math_BigDecimal.
     */
    @Test
    @Disabled
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
    @Disabled
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

}
