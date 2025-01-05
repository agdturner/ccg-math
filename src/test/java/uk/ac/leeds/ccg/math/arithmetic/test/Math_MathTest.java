/*
 * Copyright 2019 Andy Turner, University of Leeds.
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
package uk.ac.leeds.ccg.math.arithmetic.test;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.arithmetic.Math_Math;

/**
 * Tests for the {@link Math_Math} class.
 * 
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_MathTest {

    public Math_MathTest() {
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
     * Test of add2 method, of class Math_Math.
     */
    @Test
    public void testAdd2() {
        System.out.println("add2");
        Number x;
        Number y;
        Number expResult;
        Number result;
        // Test 1
        x = BigDecimal.ONE;
        y = BigDecimal.ONE;
        expResult = BigDecimal.valueOf(2);
        result = Math_Math.add2(x, y);
        assertEquals(expResult, result);
        // Test 2
        x = BigDecimal.ONE;
        y = 100L;
        expResult = BigDecimal.valueOf(101L);
        result = Math_Math.add2(x, y);
        assertEquals(expResult, result);
        // Test 3
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.add2(Integer.MAX_VALUE, BigDecimal.ONE);
        });
        // Test 4
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.add2(Long.MAX_VALUE, BigDecimal.ONE);
        });
        // Test 5
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.add2(Double.MAX_VALUE, BigDecimal.ONE);
        });
    }

    /**
     * Test of add method, of class Math_Math.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Number x;
        Number y;
        Number expResult;
        Number result;
        // Test 1
        x = BigDecimal.ONE;
        y = BigDecimal.ONE;
        expResult = BigDecimal.valueOf(2);
        result = Math_Math.add(x, y);
        assertEquals(expResult, result);
        // Test 2
        Assertions.assertThrows(ClassCastException.class, () -> {
            Math_Math.add(BigDecimal.ONE, 100L);
        });
        // Test 3
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.add2(Integer.MAX_VALUE, 1);
        });
        // Test 4
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.add2(Long.MAX_VALUE, 1L);
        });

    }

    /**
     * Test of testDouble method, of class Math_Math.
     */
    @Test
    public void testTestDouble_BigDecimal() {
        System.out.println("testDouble");
        // Test 1
        Math_Math.testDouble(Math_Math.DOUBLE_MAXVALUE);
        // Test 2
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.testDouble(Math_Math.DOUBLE_MAXVALUE
                    .add(BigDecimal.ONE));
        });
        // Test 3
        Math_Math.testDouble(Math_Math.DOUBLE_MAXVALUE_NEG);
        // Test 4
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.testDouble(Math_Math.DOUBLE_MAXVALUE_NEG.
                    subtract(BigDecimal.ONE));
        });
    }

    /**
     * Test of testFloat method, of class Math_Math.
     */
    @Test
    public void testTestFloat_BigDecimal() {
        System.out.println("testFloat");
        // Test 1
        Math_Math.testFloat(Math_Math.FLOAT_MAXVALUE);
        // Test 2
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.testFloat(Math_Math.FLOAT_MAXVALUE
                    .add(BigDecimal.ONE));
        });
        // Test 3
        Math_Math.testFloat(Math_Math.FLOAT_MAXVALUE_NEG);
        // Test 4
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math_Math.testFloat(Math_Math.FLOAT_MAXVALUE_NEG
                    .subtract(BigDecimal.ONE));
        });
    }

    /**
     * Test of testDouble method, of class Math_Math.
     */
    @Test
    public void testTestDouble_BigDecimal_BigDecimal() {
        System.out.println("testDouble");
        BigDecimal x;
        BigDecimal epsilon;
        int expResult;
        int result;
        // Test 1
        epsilon = BigDecimal.ZERO;
        expResult = 0;
        double d = 10d;
        x = new BigDecimal(String.valueOf(d));
        result = Math_Math.testDouble(x, epsilon);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.1");
        epsilon = BigDecimal.ZERO;
        expResult = 0;
        result = Math_Math.testDouble(x, epsilon);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("0.1");
        x = x.add(x).add(x);
        epsilon = BigDecimal.ZERO;
        expResult = 0;
        result = Math_Math.testDouble(x, epsilon);
        assertEquals(expResult, result);
    }

    /**
     * Test of testDouble2 method, of class Math_Math.
     */
    @Test
    public void testTestDouble2() {
        System.out.println("testDouble2");
        BigDecimal x = Math_Math.DOUBLE_MAXVALUE;
        System.out.println(x.toString());
        BigDecimal epsilon = BigDecimal.ZERO;
        boolean expResult = true;
        boolean result = Math_Math.testDouble2(x, epsilon);
        assertEquals(expResult, result);
    }

    /**
     * Test of testFloat method, of class Math_Math.
     */
    @Test
    public void testTestFloat_BigDecimal_BigDecimal() {
        System.out.println("testFloat");
        BigDecimal x;
        BigDecimal epsilon;
        int expResult;
        int result;
        // Test 1
        epsilon = BigDecimal.ZERO;
        expResult = 0;
        double d = 10d;
        x = new BigDecimal(String.valueOf(d));
        result = Math_Math.testFloat(x, epsilon);
        assertEquals(expResult, result);
        // Test 2
        x = new BigDecimal("0.1");
        epsilon = BigDecimal.ZERO;
        expResult = -1;
        result = Math_Math.testFloat(x, epsilon);
        assertEquals(expResult, result);
        // Test 3
        x = new BigDecimal("0.1");
        x = x.add(x).add(x);
        epsilon = BigDecimal.ZERO;
        expResult = -1;
        result = Math_Math.testFloat(x, epsilon);
        assertEquals(expResult, result);
    }

    /**
     * Test of testFloat2 method, of class Math_Math.
     */
    @Test
    public void testTestFloat2() {
        System.out.println("testFloat2");
        BigDecimal x = Math_Math.FLOAT_MAXVALUE;
        System.out.println(x.toString());
        BigDecimal epsilon = BigDecimal.ZERO;
        boolean expResult = true;
        boolean result = Math_Math.testFloat2(x, epsilon);
        assertEquals(expResult, result);
    }

    /**
     * Test of compare method, of class Math_Math.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        BigDecimal x = new BigDecimal(Double.toString(0.1D + 0.1D + 0.1D));
        BigDecimal xd = new BigDecimal("0.3");
        BigDecimal epsilon = BigDecimal.ZERO;
        int expResult = 1;
        int result = Math_Math.compare(x, xd, epsilon);
        assertEquals(expResult, result);
    }

    /**
     * Test of compare2 method, of class Math_Math.
     */
    @Test
    public void testCompare2() {
        System.out.println("compare2");
        BigDecimal x = new BigDecimal(Double.toString(0.1D + 0.1D + 0.1D));
        BigDecimal xd = new BigDecimal("0.3");
        BigDecimal epsilon = BigDecimal.ZERO;
        assertFalse(Math_Math.compare2(x, xd, epsilon));
    }

}
