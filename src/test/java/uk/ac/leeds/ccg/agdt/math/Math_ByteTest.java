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
package uk.ac.leeds.ccg.agdt.math;

import uk.ac.leeds.ccg.agdt.math.Math_Byte;
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
public class Math_ByteTest extends Math_Test {
    
    public Math_ByteTest() {
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

    @Test
    public void testIsByte() {
        String funcName = "isByte";
        System.out.println("Test " + funcName);
        boolean result;
        // Test 1
        int test = 1;
        String s;
        byte x = Byte.MIN_VALUE;
        s = Byte.toString(x);
        result = Math_Byte.isByte(s);
        printFunctionTest(funcName, test, x, result);
        assertFalse(result);
        // Test 2
        test ++;
        x += 1;
        s = Byte.toString(x);
        result = Math_Byte.isByte(s);
        printFunctionTest(funcName, test, x, result);
        assertTrue(result);
    }
    
}
