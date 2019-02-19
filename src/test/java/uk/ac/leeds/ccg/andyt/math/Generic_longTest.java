/**
 * Copyright 2010 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
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
public class Generic_longTest {

    public Generic_longTest() {
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

    /**
     * Test of isEven method, of class Generic_long.
     */
    @Test
    public void testIsEven() {
        System.out.println("isEven");
        System.out.println("isEven(x)");
        long x;
        boolean expResult;
        boolean result;
        // Test 1
        System.out.println("Test 1");
        x = 1L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 2
        System.out.println("Test 2");
        x = 2L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 3
        System.out.println("Test 3");
        x = 3L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 4
        System.out.println("Test 4");
        x = 4L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 5
        System.out.println("Test 5");
        x = 5L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 6
        System.out.println("Test 6");
        x = 6L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 7
        System.out.println("Test 7");
        x = 7L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 8
        System.out.println("Test 8");
        x = 8L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 9
        System.out.println("Test 9");
        x = 9L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 10
        System.out.println("Test 10");
        x = 0L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 11
        System.out.println("Test 11");
        x = -1L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 12
        System.out.println("Test 12");
        x = -2L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 13
        System.out.println("Test 13");
        x = -3L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 14
        System.out.println("Test 14");
        x = -4L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 15
        System.out.println("Test 15");
        x = -5L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 16
        System.out.println("Test 16");
        x = -6L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 17
        System.out.println("Test 17");
        x = -7L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 18
        System.out.println("Test 18");
        x = -8L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 19
        System.out.println("Test 19");
        x = -9L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 20
        System.out.println("Test 20");
        x = 123456789L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 21
        System.out.println("Test 21");
        x = 12345678L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 22
        System.out.println("Test 22");
        x = -123456789L;
        expResult = false;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
        // Test 23
        System.out.println("Test 23");
        x = -12345678L;
        expResult = true;
        result = Generic_long.isEven(x);
        printTestIsEven(x, result);
        assertEquals(expResult, result);
    }

    /**
     * For printing details of testIsEven() tests
     *
     * @param x
     * @param result
     */
    private void printTestIsEven(long x, boolean result) {
        System.out.println("x " + x);
        System.out.println("result " + result);
    }
}
