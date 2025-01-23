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
package uk.ac.leeds.ccg.math.arithmetic.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ac.leeds.ccg.math.arithmetic.Math_Integer;
import uk.ac.leeds.ccg.math.arithmetic.Math_Long;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_IntegerTest {

    public Math_IntegerTest() {
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
    public void testIsInt() {
        String funcName = "isInt";
        System.out.println("Test " + funcName);
        // Test 1
        long x = Math_Long.INTEGER_MAX_VALUE + 1;
        String s = Long.toString(x);
        boolean result;
        result = Math_Integer.isInt(s);
        assertFalse(result);
        // Test 2
        x--;
        s = Long.toString(x);
        result = Math_Integer.isInt(s);
        assertTrue(result);
    }

    @Test
    public void testIsEven() {
        String funcName = "isEven";
        System.out.println("Test " + funcName);
        int x;
        boolean expResult;
        boolean result;
        // Test 1
        x = 1;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 2
        x = 2;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 3
        x = 3;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 4
        x = 4;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 5
        x = 5;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 6
        x = 6;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 7
        x = 7;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 8
        x = 8;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 9
        x = 9;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 10
        x = 0;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 11
        x = -1;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 12
        x = -2;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 13
        x = -3;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 14
        x = -4;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 15
        x = -5;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 16
        x = -6;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 17
        x = -7;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 18
        x = -8;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 19
        x = -9;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 20
        x = 123456789;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 21
        x = 12345678;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 22
        x = -123456789;
        expResult = false;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
        // Test 23
        x = -12345678;
        expResult = true;
        result = Math_Integer.isEven(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseInt method, of class Math_Integer.
     */
    @Test
    public void testParseInt() {
        System.out.println("parseInt");
        String s;
        int expResult;
        int result;
        // Test 1
        s = "0";
        expResult = 0;
        result = Math_Integer.parseInt(s);
        assertEquals(expResult, result);
        // Test 2
        assertThrows(NumberFormatException.class, () -> {
            Math_Integer.parseInt("1E+10");
        });
        // Test 3
        assertThrows(NumberFormatException.class, () -> {
            Math_Integer.parseInt("1E10");
        });
    }

    /**
     * Test of parseInt method, of class Math_Integer.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        int x;
        int y;
        int expResult;
        int result;
        // Test 1
        x = 0;
        y = 0;
        result = Math_Integer.add(x, y);
        expResult = 0;
        assertEquals(expResult, result);
        // Test 2
        x = Integer.MAX_VALUE;
        y = 0;
        result = Math_Integer.add(x, y);
        expResult = Integer.MAX_VALUE;
        assertEquals(expResult, result);
        // Test 3
        assertThrows(ArithmeticException.class, () -> {
            Math_Integer.add(Integer.MAX_VALUE, 1);
        });
    }

    /**
     * Test of subtract method, of class Math_Integer.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        // Test
        assertThrows(ArithmeticException.class, () -> {
            Math_Integer.subtract(-Integer.MAX_VALUE, 2);
        });
    }

    /**
     * Test of multiply method, of class Math_Integer.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        // Test
        assertThrows(ArithmeticException.class, () -> {
            Math_Integer.multiply(Integer.MAX_VALUE, 2);
        });
    }
    
    @Test
    public void testMin() {
        System.out.println("Test min");
        int a = Integer.MAX_VALUE;
        int b = -Integer.MAX_VALUE;
        assertTrue(Math_Integer.min(a, b, 0) == b);
        assertTrue(Math_Integer.min(0, 10, -10) == -10);
    }
    
    @Test
    public void testMax() {
        System.out.println("Test max");
        int a = Integer.MAX_VALUE;
        int b = -Integer.MAX_VALUE;
        assertTrue(Math_Integer.max(a, b, 0) == a);
        assertTrue(Math_Integer.max(0, 10, -10) == 10);
    }
}
