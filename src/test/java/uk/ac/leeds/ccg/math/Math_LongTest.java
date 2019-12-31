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

import uk.ac.leeds.ccg.math.Math_BigInteger;
import uk.ac.leeds.ccg.math.Math_Long;
import java.math.BigInteger;
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
public class Math_LongTest  extends Math_Test {

    public Math_LongTest() {
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
    public void testIsLong() {
       String funcName = "isLong";
        System.out.println("Test " + funcName);
       long x;
        String s;
        boolean result;
        // Test 1
        int test = 1;
        x = Long.MIN_VALUE;
        s = Long.toString(x);
        result = Math_Long.isLong(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
        // Test 2
        test ++;
        x += 1;
        s = Long.toString(x);
        result = Math_Long.isLong(s);
        printFunctionTest(funcName, test, x, result);
        assertTrue(result);
        // Test 3
        test ++;
        BigInteger bi = Math_BigInteger.LONG_MAX_VALUE.add(BigInteger.ONE);
        s = bi.toString();
        result = Math_Long.isLong(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
    }
    
    @Test
    public void testIsEven() {
        String funcName = "isEven";
        System.out.println("Test " + funcName);
        long x;
        boolean expResult;
        boolean result;
        // Test 1
        int test = 1;
        x = 1L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 2
        test ++;
        x = 2L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 3
        test ++;
        x = 3L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 4
        test ++;
        x = 4L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 5
        test ++;
        x = 5L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 6
        test ++;
        x = 6L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 7
        test ++;
        x = 7L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 8
        test ++;
         x = 8L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 9
        test ++;
        x = 9L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 10
        test ++;
        x = 0L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 11
        test ++;
        x = -1L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 12
        test ++;
        x = -2L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 13
        test ++;
        x = -3L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 14
        test ++;
        x = -4L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 15
        test ++;
        x = -5L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 16
        test ++;
        x = -6L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 17
        test ++;
        x = -7L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 18
        test ++;
        x = -8L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 19
        test ++;
        x = -9L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 20
        test ++;
        x = 123456789L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 21
        test ++;
        x = 12345678L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 22
        test ++;
        x = -123456789L;
        expResult = false;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
        // Test 23
        test ++;
        x = -12345678L;
        expResult = true;
        result = Math_Long.isEven(x);
        printFunctionTest(funcName, test, x, result);
        assertEquals(expResult, result);
    }
}
