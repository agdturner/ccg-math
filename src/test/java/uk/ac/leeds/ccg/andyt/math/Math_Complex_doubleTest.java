/*
 * Copyright (C) 2018 geoagdt.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package uk.ac.leeds.ccg.andyt.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author geoagdt
 */
public class Math_Complex_doubleTest extends Math_Test {

    public Math_Complex_doubleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void printTestAndC0(int test, Math_Complex_double c0) {
        printTest(test);
        System.out.println("c0 " + c0);
    }

    public void printFunctionTest(String funcName, int test,
            Math_Complex_double c0, double result) {
        printTestAndC0(test, c0);
        System.out.println(funcName + "(c0) " + result);
    }

    public void printFunctionTest(String funcName, int test,
            Math_Complex_double c0, Math_Complex_double result) {
        printTestAndC0(test, c0);
        System.out.println(funcName + "(c0,c1) " + result);
    }

    public void printFunctionTest(String funcName, int test,
            Math_Complex_double c0, Math_Complex_double c1,
            Math_Complex_double result) {
        printTestAndC0(test, c0);
        System.out.println("c1 " + c1);
        System.out.println(funcName + "(c0,c1) " + result);
    }

    @Test
    public void testAdd() {
        String funcName = "add";
        System.out.println("Test " + funcName);
        Math_Complex_double expResult;
        Math_Complex_double result;
        Math_Complex_double c0;
        Math_Complex_double c1;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.add(c1);
        expResult = new Math_Complex_double(-1, 5);
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubtract() {
        String funcName = "subtract";
        System.out.println("Test " + funcName);
        Math_Complex_double expResult;
        Math_Complex_double result;
        Math_Complex_double c0;
        Math_Complex_double c1;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.subtract(c1);
        expResult = new Math_Complex_double(5, 1);
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class Math_Complex_double.
     */
    @Test
    public void testMultiply() {
        String funcName = "multiply";
        System.out.println("Test " + funcName);
        Math_Complex_double expResult;
        Math_Complex_double result;
        Math_Complex_double c0;
        Math_Complex_double c1;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        int test = 1;
        delta = 0.0000001d;
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.multiply(c1);
        expResult = new Math_Complex_double(-12, -5);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of magnitude method, of class Math_Complex_double.
     */
    @Test
    public void testMagnitude() {
        String funcName = "magnitude";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        double expResult;
        double result;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(2, 5);
        expResult = Math.sqrt(29.0d);
        result = c0.magnitude();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of phase method, of class Math_Complex_double.
     */
    @Test
    public void testPhase() {
        String funcName = "phase";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        double expResult;
        double result;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(1, 1);
        expResult = Math.PI / 4.0d;
        result = c0.phase();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result, 0.0);
        // Test 2
        test++;
        c0 = new Math_Complex_double(1, -1);
        expResult = -Math.PI / 4.0d;
        result = c0.phase();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result, 0.0);
        // Test 3
        test++;
        c0 = new Math_Complex_double(1, 0);
        expResult = 0.0d;
        result = c0.phase();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result, 0.0);
        // Test 4
        test++;
        c0 = new Math_Complex_double(0, 1);
        expResult = Math.PI / 2.0d;
        result = c0.phase();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of divide method, of class Math_Complex_double.
     */
    @Test
    public void testDivide() {
        String funcName = "divide";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        Math_Complex_double c1;
        Math_Complex_double expResult;
        Math_Complex_double result;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(1, 1);
        c1 = new Math_Complex_double(1, 0);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        test++;
        c0 = new Math_Complex_double(3, 2);
        c1 = new Math_Complex_double(4, -3);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(6.0d / 25.0d, 17.0d / 25.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        test++;
        c0 = new Math_Complex_double(4, 5);
        c1 = new Math_Complex_double(2, 6);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(19.0d / 20.0d, -7.0d / 20.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        test++;
        c0 = new Math_Complex_double(2, -1);
        c1 = new Math_Complex_double(-3, 6);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(-4.0d / 15.0d, -1.0d / 5.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        printFunctionTest(funcName, test, c0, c1, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of conjugate method, of class Math_Complex_double.
     */
    @Test
    public void testConjugate() {
        String funcName = "conjugate";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        Math_Complex_double expResult;
        Math_Complex_double result;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(1, 1);
        result = c0.conjugate();
        printFunctionTest(funcName, test, c0, result);
        expResult = new Math_Complex_double(1, -1);
        assertEquals(expResult, result);
    }

    @Test
    public void testReciprocal() {
        String funcName = "reciprocal";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        Math_Complex_double expResult;
        Math_Complex_double result;
        double expReal;
        double expImaginary;
        double delta;
        delta = 0.000001d;
        // Test 1
        int test = 1;
        c0 = new Math_Complex_double(1d, 1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(0.5d, -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        test++;
        c0 = new Math_Complex_double(-1d, -1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        test++;
        c0 = new Math_Complex_double(1d, -1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        test++;
        c0 = new Math_Complex_double(-1d, 1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-0.5d, -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 5
        test++;
        c0 = new Math_Complex_double(0.5d, 0.5d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(1.0d, -1.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 6
        test++;
        c0 = new Math_Complex_double(-0.5, -0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 7
        test++;
        c0 = new Math_Complex_double(0.5, -0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 8
        test++;
        c0 = new Math_Complex_double(-0.5, 0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-1, -1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of exp method, of class Math_Complex_double.
     */
    @Test
    public void testExp() {
        String funcName = "exp";
        System.out.println("Test " + funcName);
        Math_Complex_double c0;
        Math_Complex_double result;
        Math_Complex_double expResult;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        int test = 1;
        delta = 0.0000001d;
        c0 = new Math_Complex_double(0.0d, Math.PI);
        result = c0.exp();
        expResult = new Math_Complex_double(-1.0d, 0.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of sin method, of class Math_Complex_double.
     */
    @Test
    public void testSin() {
        String funcName = "sin";
        System.out.println("Test " + funcName);
        Math_Complex_double c;
        Math_Complex_double result;
        Math_Complex_double expResult;
        // Test 1
        int test = 1;
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
        printFunctionTest(funcName, test, c, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of cos method, of class Math_Complex_double.
     */
    @Test
    public void testCos() {
        String funcName = "cos";
        System.out.println("Test " + funcName);
        Math_Complex_double c;
        Math_Complex_double result;
        Math_Complex_double expResult;
        // Test 1
        int test = 1;
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
        printFunctionTest(funcName, test, c, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of tan method, of class Math_Complex_double.
     */
    @Test
    public void testTan() {
        String funcName = "tan";
        System.out.println("Test " + funcName);
        Math_Complex_double c;
        Math_Complex_double result;
        Math_Complex_double expResult;
        // Test 1
        int test = 1;
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
        printFunctionTest(funcName, test, c, result);
        assertEquals(expResult, result);
    }

    /**
     * Test of rescale method, of class Math_Complex_double.
     */
    @Test
    public void testScale() {
        String funcName = "scale";
        System.out.println("Test " + funcName);
        double alpha;
        Math_Complex_double c0;
        Math_Complex_double expResult;
        Math_Complex_double result;
        // Test 1;
        int test = 1;
        alpha = 2.0;
        c0 = new Math_Complex_double(3, 5);
        result = c0.rescale(alpha);
        expResult = new Math_Complex_double(6, 10);
        printFunctionTest(funcName, test, c0, result);
        assertEquals(expResult, result);
    }
}
