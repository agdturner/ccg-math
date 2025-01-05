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
package uk.ac.leeds.ccg.math.number.test;

import ch.obermuhlner.math.big.BigRational;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.number.Math_Quaternion_BigRational;

/**
 * Tests for Math_Quaternion_BigRational.
 * 
 * @author Andy Turner
 */
public class Math_Quaternion_BigRationalTest {
    
    public Math_Quaternion_BigRationalTest() {
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
     * Test of equals method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Math_Quaternion_BigRational instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testHashCode() {
        System.out.println("hashCode");
        Math_Quaternion_BigRational instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of add method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testAdd() {
        System.out.println("add");
        Math_Quaternion_BigRational q = null;
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.add(q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testSubtract() {
        System.out.println("subtract");
        Math_Quaternion_BigRational q = null;
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.subtract(q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testMultiply_BigRational() {
        System.out.println("multiply");
        BigRational s = null;
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.multiply(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testDivide() {
        System.out.println("divide");
        BigRational s = null;
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.divide(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testMultiply_Math_Quaternion_BigRational() {
        System.out.println("multiply");
        Math_Quaternion_BigRational q = null;
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.multiply(q);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conjugate method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testConjugate() {
        System.out.println("conjugate");
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.conjugate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDotProduct method, of class Math_Quaternion_BigRational.
     * Test copied from https://github.com/apache/commons-numbers/blob/master/commons-numbers-quaternion/src/test/java/org/apache/commons/numbers/quaternion/QuaternionTest.java
     */
    @Test
    public void testGetDotProduct() {
        System.out.println("getDotProduct");
        Math_Quaternion_BigRational q;
        Math_Quaternion_BigRational instance;
        BigRational expResult = BigRational.valueOf(-6);
        BigRational result;
        // Test 1
        q = new Math_Quaternion_BigRational(1, 2, 2, 1);
        instance = new Math_Quaternion_BigRational(3, -2, -1, -3);
        result = instance.getDotProduct(q);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of negate method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testNegate() {
        System.out.println("negate");
        Math_Quaternion_BigRational instance = null;
        Math_Quaternion_BigRational expResult = null;
        Math_Quaternion_BigRational result = instance.negate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitude method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetMagnitude() {
        System.out.println("getMagnitude");
        int oom = 0;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getMagnitude(oom, rm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMagnitude2 method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetMagnitude2() {
        System.out.println("getMagnitude2");
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getMagnitude2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalize method, of class Math_Quaternion_BigRational.
     * Test copied from https://github.com/apache/commons-numbers/blob/master/commons-numbers-quaternion/src/test/java/org/apache/commons/numbers/quaternion/QuaternionTest.java
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        int oom;
        RoundingMode rm = RoundingMode.HALF_UP;
        Math_Quaternion_BigRational instance;
        Math_Quaternion_BigRational result;
        // Test 1
        oom = -3;
        instance = new Math_Quaternion_BigRational(2, 1, -4, -2);
        result = instance.normalize(oom, rm);
        assertTrue(BigRational.valueOf(2,5).compareTo(result.getW()) == 0);
        assertTrue(BigRational.valueOf(1,5).compareTo(result.getX()) == 0);
        assertTrue(BigRational.valueOf(-4,5).compareTo(result.getY()) == 0);
        assertTrue(BigRational.valueOf(-2,5).compareTo(result.getZ()) == 0);
    }

    /**
     * Test of getW method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetW() {
        System.out.println("getW");
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getW();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetX() {
        System.out.println("getX");
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetY() {
        System.out.println("getY");
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZ method, of class Math_Quaternion_BigRational.
     */
    @Test
    @Disabled
    public void testGetZ() {
        System.out.println("getZ");
        Math_Quaternion_BigRational instance = null;
        BigRational expResult = null;
        BigRational result = instance.getZ();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
