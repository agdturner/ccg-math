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
public class Generic_Complex_doubleTest {

    public Generic_Complex_doubleTest() {
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

    /**
     * Test of add method, of class Generic_Complex_double.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        Generic_Complex_double c0;
        Generic_Complex_double c1;
        // Test 1
        c0 = new Generic_Complex_double(2.0, 3.0);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(-3.0, 2.0);
        System.out.println("c1 " + c1);
        result = c0.add(c1);
        System.out.println("c1 add c2 = " + result);
        expResult = new Generic_Complex_double(-1, 5);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class Generic_Complex_double.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        Generic_Complex_double c0;
        Generic_Complex_double c1;
        // Test 1
        c0 = new Generic_Complex_double(2.0, 3.0);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(-3.0, 2.0);
        System.out.println("c1 " + c1);
        result = c0.subtract(c1);
        System.out.println("c1 subtract c2 = " + result);
        expResult = new Generic_Complex_double(5, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class Generic_Complex_double.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        Generic_Complex_double c0;
        Generic_Complex_double c1;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        delta = 0.0000001d;
        c0 = new Generic_Complex_double(2.0, 3.0);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(-3.0, 2.0);
        System.out.println("c1 " + c1);
        result = c0.multiply(c1);
        System.out.println("c0 multiply c1 = " + result);
        expResult = new Generic_Complex_double(-12, -5);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
//        assertEquals(expResult, result);
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of magnitude method, of class Generic_Complex_double.
     */
    @Test
    public void testMagnitude() {
        System.out.println("magnitude");
        Generic_Complex_double c0;
        double expResult;
        double result;
        // Test 1
        c0 = new Generic_Complex_double(2, 5);
        System.out.println("c0 " + c0);
        expResult = Math.sqrt(29.0d);
        result = c0.magnitude();
        System.out.println("magnitude " + result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of phase method, of class Generic_Complex_double.
     */
    @Test
    public void testPhase() {
        System.out.println("phase");
        Generic_Complex_double c0;
        double expResult;
        double result;
        // Test 1
        c0 = new Generic_Complex_double(1, 1);
        System.out.println("c0 " + c0);
        expResult = Math.PI / 4.0d;
        result = c0.phase();
        System.out.println("phase " + result);
        assertEquals(expResult, result, 0.0);
        // Test 2
        c0 = new Generic_Complex_double(1, -1);
        System.out.println("c0 " + c0);
        expResult = -Math.PI / 4.0d;
        result = c0.phase();
        System.out.println("phase " + result);
        assertEquals(expResult, result, 0.0);
        // Test 3
        c0 = new Generic_Complex_double(1, 0);
        System.out.println("c0 " + c0);
        expResult = 0.0d;
        result = c0.phase();
        System.out.println("phase " + result);
        assertEquals(expResult, result, 0.0);
        // Test 3
        c0 = new Generic_Complex_double(0, 1);
        System.out.println("c0 " + c0);
        expResult = Math.PI / 2.0d;
        result = c0.phase();
        System.out.println("phase " + result);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of divide method, of class Generic_Complex_double.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Generic_Complex_double c0;
        Generic_Complex_double c1;
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        c0 = new Generic_Complex_double(1, 1);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(1, 0);
        System.out.println("c1 " + c1);
        result = c0.divide(c1);
        System.out.println("c0.divide(c1) = " + result);
        expResult = new Generic_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        c0 = new Generic_Complex_double(3, 2);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(4, -3);
        System.out.println("c1 " + c1);
        result = c0.divide(c1);
        System.out.println("c0.divide(c1) = " + result);
        expResult = new Generic_Complex_double(6.0d/25.0d, 17.0d/25.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        c0 = new Generic_Complex_double(4, 5);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(2, 6);
        System.out.println("c1 " + c1);
        result = c0.divide(c1);
        System.out.println("c0.divide(c1) = " + result);
        expResult = new Generic_Complex_double(19.0d/20.0d, -7.0d/20.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        c0 = new Generic_Complex_double(2, -1);
        System.out.println("c0 " + c0);
        c1 = new Generic_Complex_double(-3, 6);
        System.out.println("c1 " + c1);
        result = c0.divide(c1);
        System.out.println("c0.divide(c1) = " + result);
        expResult = new Generic_Complex_double(-4.0d/15.0d, -1.0d/5.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of conjugate method, of class Generic_Complex_double.
     */
    @Test
    public void testConjugate() {
        System.out.println("conjugate");
        Generic_Complex_double c0;
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        // Test 1
        c0 = new Generic_Complex_double(1, 1);
        System.out.println("c0 " + c0);
        result = c0.conjugate();
        System.out.println("c0.conjugate() " + result);
        expResult = new Generic_Complex_double(1, -1);
        assertEquals(expResult, result);
    }

    /**
     * Test of reciprocal method, of class Generic_Complex_double.
     */
    @Test
    public void testReciprocal() {
        System.out.println("reciprocal");
        Generic_Complex_double c0;
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        double expReal;
        double expImaginary;
        double delta;
        delta = 0.000001d;
        // Test 1
        c0 = new Generic_Complex_double(1d, 1d);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(0.5d , -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        c0 = new Generic_Complex_double(-1d, -1d);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(-0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        c0 = new Generic_Complex_double(1d, -1d);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        c0 = new Generic_Complex_double(-1d, 1d);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(-0.5d, -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 5
        c0 = new Generic_Complex_double(0.5d, 0.5d);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(1.0d, -1.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 6
        c0 = new Generic_Complex_double(-0.5, -0.5);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(-1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 7
        c0 = new Generic_Complex_double(0.5, -0.5);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 8
        c0 = new Generic_Complex_double(-0.5, 0.5);
        System.out.println("c0 " + c0);
        result = c0.reciprocal();
        System.out.println("c0.reciprocal() " + result);
        expResult = new Generic_Complex_double(-1, -1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of exp method, of class Generic_Complex_double.
     */
    @Test
    public void testExp() {
        System.out.println("exp");
        Generic_Complex_double c0;
        Generic_Complex_double result;
        Generic_Complex_double expResult;
        double expReal;
        double expImaginary;
        double delta;
        // Test 1
        delta = 0.0000001d;
        c0 = new Generic_Complex_double(0.0d, Math.PI);
        System.out.println("c0 " + c0);
        result = c0.exp();
        System.out.println("c0.exp() " + result);
        expResult = new Generic_Complex_double(-1.0d, 0.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
    }

    /**
     * Test of sin method, of class Generic_Complex_double.
     */
    @Test
    public void testSin() {
        System.out.println("sin");
        Generic_Complex_double c;
        Generic_Complex_double result;
        Generic_Complex_double expResult;
        // Test 1
        c = new Generic_Complex_double(1, 1);
        System.out.println("c " + c);
        result = c.sin();
        System.out.println("sine c = " + result);
        expResult = new Generic_Complex_double(1.2984575814159773, 0.6349639147847361);
        assertEquals(expResult, result);
    }

    /**
     * Test of cos method, of class Generic_Complex_double.
     */
    @Test
    public void testCos() {
        System.out.println("cos");
        Generic_Complex_double c;
        Generic_Complex_double result;
        Generic_Complex_double expResult;
        // Test 1
        c = new Generic_Complex_double(1, 1);
        System.out.println("c " + c);
        result = c.sin();
        System.out.println("cosine c = " + result);
        expResult = new Generic_Complex_double(1.2984575814159773, 0.6349639147847361);
        assertEquals(expResult, result);
    }

    /**
     * Test of tan method, of class Generic_Complex_double.
     */
    @Test
    public void testTan() {
        System.out.println("tan");
        Generic_Complex_double c;
        Generic_Complex_double result;
        Generic_Complex_double expResult;
        // Test 1
        c = new Generic_Complex_double(1, 1);
        System.out.println("c " + c);
        result = c.sin();
        System.out.println("tangent c = " + result);
        expResult = new Generic_Complex_double(1.2984575814159773, 0.6349639147847361);
        assertEquals(expResult, result);
    }

    /**
     * Test of rescale method, of class Generic_Complex_double.
     */
    @Test
    public void testScale() {
        System.out.println("scale");
        double alpha;
        Generic_Complex_double c0;
        Generic_Complex_double expResult;
        Generic_Complex_double result;
        // Test 1;
        alpha = 2.0;
        c0 = new Generic_Complex_double(3, 5);
        System.out.println("c0 " + c0);
        result = c0.rescale(alpha);
        System.out.println("c0.scale(" + alpha + ") " + result);
        expResult = new Generic_Complex_double(6, 10);
        assertEquals(expResult, result);
    }
}
