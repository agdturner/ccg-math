/*
 * Copyright 2021 Centre for Computational Geography, University of Leeds.
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

import ch.obermuhlner.math.big.BigRational;
import java.math.BigInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for class Math_BigRational
 * 
 * @author Andy Turner
 * @verson 1.0
 */
public class Math_BigRationalTest {
    
    public Math_BigRationalTest() {
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
    public void testRound() {
        BigRational x;
        x = BigRational.valueOf("123.456789");
        BigRational result;
        BigRational expResult ;
        int oom;
        // Test 1
        oom = 0;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        oom = -1;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3
        oom = 1;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("120");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4
        oom = 2;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("100");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 5
        oom = -4;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4567");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 6
        oom = 20;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("0");
        assertTrue(result.compareTo(expResult) == 0);
        // Test 7
        oom = -7;
        result = Math_BigRational.round(x, oom);
        expResult = BigRational.valueOf("123.4567890");
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of getCommonFactor method, of class Math_BigRational.
     */
    @Test
    public void testGetCommonFactor() {
        System.out.println("getCommonFactor");
        BigRational x = BigRational.valueOf(BigInteger.valueOf(8),
                BigInteger.valueOf(3));
        BigRational y = BigRational.valueOf(BigInteger.valueOf(14),
                BigInteger.valueOf(3));
        BigRational expResult = BigRational.valueOf(BigInteger.valueOf(2),
                BigInteger.valueOf(3));
        BigRational result = Math_BigRational.getCommonFactor(x, y);
        assertTrue(result.compareTo(expResult) == 0);
    }
}
