/*
 * Copyright 2020 Andy Turner, University of Leeds.
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
package uk.ac.leeds.ccg.math.geometry.test;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigRational;
import uk.ac.leeds.ccg.math.geometry.Math_AngleBigRational;
import uk.ac.leeds.ccg.math.geometry.Math_AngleBigRational;

/**
 * Test class for V3D_Angle.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_AngleBigRationalTest {

    public Math_AngleBigRationalTest() {
        super();
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
     * Test of normalise.
     */
    @Test
    public void testNormalise() {
        System.out.println("normalise");
        Math_AngleBigRational ma = new Math_AngleBigRational();
        int oom = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational pi = Math_BigRational.getPi(new Math_BigDecimal(), oom, rm);
        BigRational dpi = pi.multiply(2);
        BigRational hpi = pi.divide(2);
        // Positive angle
        BigRational theta = dpi.multiply(BigInteger.TEN).add(pi);
        BigRational result = ma.normalise(theta, oom, rm);
        BigRational expResult = pi;
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        theta = dpi.multiply(BigInteger.TEN).add(hpi);
        result = ma.normalise(theta, oom, rm);
        expResult = hpi;
        assertTrue(result.compareTo(expResult) == 0);
        // Negative angle
        theta = dpi.multiply(BigInteger.TEN).add(pi).negate();
        result = ma.normalise(theta, oom, rm);
        expResult = pi;
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4
        theta = dpi.multiply(BigInteger.TEN).add(hpi).negate();
        result = ma.normalise(theta, oom, rm);
        expResult = hpi.multiply(3);
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of toDegrees.
     */
    @Test
    public void testToDegrees() {
        System.out.println("toDegrees");
        Math_AngleBigRational ma = new Math_AngleBigRational();
        int oom = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational theta = ma.getPi(oom, rm);
        BigRational result = ma.toDegrees(theta, oom, rm);
        BigRational expResult = Math_AngleBigRational.P180;
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        oom = -100;
        theta = ma.getPi(oom, rm);
        result = ma.toDegrees(theta, oom, rm);
        expResult = Math_AngleBigRational.P180;
        assertTrue(result.compareTo(expResult) == 0);
    }
    
    /**
     * Test of toDegrees.
     */
    @Test
    public void testToRadians() {
        System.out.println("toRadians");
        Math_AngleBigRational ma = new Math_AngleBigRational();
        int oom = -3;
        RoundingMode rm = RoundingMode.HALF_UP;
        BigRational theta = Math_AngleBigRational.P180;
        BigRational result = ma.toRadians(theta, oom, rm);
        BigRational expResult = ma.getPi(oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
        // Test 2
        oom = -100;
        theta = Math_AngleBigRational.P180;
        result = ma.toRadians(theta, oom, rm);
        expResult = ma.getPi(oom, rm);
        assertTrue(result.compareTo(expResult) == 0);
    }
}
