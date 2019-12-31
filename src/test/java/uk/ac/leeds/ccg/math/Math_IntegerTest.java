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

import uk.ac.leeds.ccg.math.Math_Long;
import uk.ac.leeds.ccg.math.Math_Integer;
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
public class Math_IntegerTest extends Math_Test {

    public Math_IntegerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsInt() {
        String funcName = "isInt";
        System.out.println("Test " + funcName);
        // Test 1
        int test = 1;
        long x = Math_Long.INTEGER_MAX_VALUE + 1;
        String s = Long.toString(x);
        boolean result;
        result = Math_Integer.isInt(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
        // Test 2
        test ++;
        x--;
        s = Long.toString(x);
        result = Math_Integer.isInt(s);
        printFunctionTest(funcName, test, x, result);
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
        int test = 1;
        x = 1;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 2
        test++;
        x = 2;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 3
        test++;
        x = 3;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 4
        test++;
        x = 4;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 5
        test++;
        x = 5;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 6
        test++;
        x = 6;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 7
        test++;
        x = 7;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 8
        test++;
        x = 8;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 9
        test++;
        x = 9;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 10
        test++;
        x = 0;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 11
        test++;
        x = -1;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 12
        test++;
        x = -2;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 13
        test++;
        x = -3;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 14
        test++;
        x = -4;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 15
        test++;
        x = -5;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 16
        test++;
        x = -6;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 17
        test++;
        x = -7;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 18
        test++;
        x = -8;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 19
        test++;
        x = -9;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 20
        test++;
        x = 123456789;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 21
        test++;
        x = 12345678;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 22
        test++;
        x = -123456789;
        expResult = false;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 23
        test++;
        x = -12345678;
        expResult = true;
        result = Math_Integer.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
    }
}
