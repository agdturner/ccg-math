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
package uk.ac.leeds.ccg.math.number.test;

import uk.ac.leeds.ccg.math.number.Math_Complex_double;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.leeds.ccg.math.number.Math_Complex_double;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_Complex_doubleTest {

    public Math_Complex_doubleTest() {
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
    public void testAdd() {
        String funcName = "add";
        System.out.println("Test " + funcName);
        Math_Complex_double expResult;
        Math_Complex_double result;
        Math_Complex_double c0;
        Math_Complex_double c1;
        // Test 1
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.add(c1);
        expResult = new Math_Complex_double(-1, 5);
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
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.subtract(c1);
        expResult = new Math_Complex_double(5, 1);
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
        delta = 0.0000001d;
        c0 = new Math_Complex_double(2.0, 3.0);
        c1 = new Math_Complex_double(-3.0, 2.0);
        result = c0.multiply(c1);
        expResult = new Math_Complex_double(-12, -5);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
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
        c0 = new Math_Complex_double(2, 5);
        expResult = Math.sqrt(29.0d);
        result = c0.magnitude();
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
        c0 = new Math_Complex_double(1, 1);
        expResult = Math.PI / 4.0d;
        result = c0.phase();
        assertEquals(expResult, result, 0.0);
        // Test 2
        c0 = new Math_Complex_double(1, -1);
        expResult = -Math.PI / 4.0d;
        result = c0.phase();
        assertEquals(expResult, result, 0.0);
        // Test 3
        c0 = new Math_Complex_double(1, 0);
        expResult = 0.0d;
        result = c0.phase();
        assertEquals(expResult, result, 0.0);
        // Test 4
        c0 = new Math_Complex_double(0, 1);
        expResult = Math.PI / 2.0d;
        result = c0.phase();
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
        c0 = new Math_Complex_double(1, 1);
        c1 = new Math_Complex_double(1, 0);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        c0 = new Math_Complex_double(3, 2);
        c1 = new Math_Complex_double(4, -3);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(6.0d / 25.0d, 17.0d / 25.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        c0 = new Math_Complex_double(4, 5);
        c1 = new Math_Complex_double(2, 6);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(19.0d / 20.0d, -7.0d / 20.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        c0 = new Math_Complex_double(2, -1);
        c1 = new Math_Complex_double(-3, 6);
        result = c0.divide(c1);
        expResult = new Math_Complex_double(-4.0d / 15.0d, -1.0d / 5.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        delta = 0.0000001d;
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
        c0 = new Math_Complex_double(1, 1);
        result = c0.conjugate();
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
        c0 = new Math_Complex_double(1d, 1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(0.5d, -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 2
        c0 = new Math_Complex_double(-1d, -1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 3
        c0 = new Math_Complex_double(1d, -1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(0.5d, 0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 4
        c0 = new Math_Complex_double(-1d, 1d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-0.5d, -0.5d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 5
        c0 = new Math_Complex_double(0.5d, 0.5d);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(1.0d, -1.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 6
        c0 = new Math_Complex_double(-0.5, -0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 7
        c0 = new Math_Complex_double(0.5, -0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(1, 1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
        assertEquals(expReal, result.getReal(), delta);
        assertEquals(expImaginary, result.getImaginary(), delta);
        // Test 8
        c0 = new Math_Complex_double(-0.5, 0.5);
        result = c0.reciprocal();
        expResult = new Math_Complex_double(-1, -1);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
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
        delta = 0.0000001d;
        c0 = new Math_Complex_double(0.0d, Math.PI);
        result = c0.exp();
        expResult = new Math_Complex_double(-1.0d, 0.0d);
        expReal = expResult.getReal();
        expImaginary = expResult.getImaginary();
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
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
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
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
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
        c = new Math_Complex_double(1, 1);
        result = c.sin();
        expResult = new Math_Complex_double(1.2984575814159773, 0.6349639147847361);
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
        alpha = 2.0;
        c0 = new Math_Complex_double(3, 5);
        result = c0.rescale(alpha);
        expResult = new Math_Complex_double(6, 10);
        assertEquals(expResult, result);
    }
}
