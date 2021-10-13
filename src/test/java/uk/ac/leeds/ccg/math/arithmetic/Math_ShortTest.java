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
package uk.ac.leeds.ccg.math.arithmetic;

import uk.ac.leeds.ccg.math.arithmetic.Math_Short;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_ShortTest {
    
    public Math_ShortTest() {
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
    public void testIsShort() {
        String funcName = "isShort";
        System.out.println("Test " + funcName);
        short x;
        String s;
        boolean result;
        // Test 1
        x = Short.MIN_VALUE;
        s = Short.toString(x);
        result = Math_Short.isShort(s);
        assertFalse(result);
        // Test 2
        x += 1;
        s = Short.toString(x);
        result = Math_Short.isShort(s);
        assertTrue(Math_Short.isShort(s));
    }
    
}
