/*
 * Copyright 2020 Centre for Computational Geography, University of Leeds.
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
package uk.ac.leeds.ccg.math.matrices;

import ch.obermuhlner.math.big.BigRational;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author Andy Turner
 */
public class Math_Matrix_BRTest {

    public Math_Matrix_BRTest() {
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
     * Test of equals method, of class Math_Matrix_BR.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        BigRational[][] m = new BigRational[1][1];
        m[0][0] = BigRational.ZERO;
        Object o = new Math_Matrix_BR(m);
        BigRational[][] m2 = new BigRational[1][1];
        m2[0][0] = BigRational.ZERO;
        Math_Matrix_BR instance = new Math_Matrix_BR(m);
        assertTrue(instance.equals(o));
    }

    /**
     * Test of hashCode method, of class Math_Matrix_BR.
     */
    @Test
    @Disabled
    public void testHashCode() {
        System.out.println("hashCode");
        // No test.
    }

    /**
     * Test of toString method, of class Math_Matrix_BR.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BigRational[][] m = new BigRational[1][1];
        m[0][0] = BigRational.ZERO;
        Math_Matrix_BR instance = new Math_Matrix_BR(m);
        String expResult = "Math_Matrix_BR(0 " + "\n" + ")";
        System.out.println(expResult);
        String result = instance.toString();
        System.out.println(result);
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of getIdentityMatrix method, of class Math_Matrix_BR.
     * Test not required.
     */
    @Test
    @Disabled
    public void testGetIdentityMatrix() {
    }

    /**
     * Test of getM method, of class Math_Matrix_BR.
     * Test not required.
     */
    @Test
    @Disabled
    public void testGetM() {
    }

    /**
     * Test of getNr method, of class Math_Matrix_BR.
     * Test not required.
     */
    @Test
    @Disabled
    public void testGetNr() {
    }

    /**
     * Test of getNc method, of class Math_Matrix_BR.
     * Test not required.
     */
    @Test
    @Disabled
    public void testGetNc() {
    }

    /**
     * Test of multiply method, of class Math_Matrix_BR.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        BigRational[][] m = new BigRational[2][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        m[0][0] = BigRational.valueOf(11);
        m[0][1] = BigRational.valueOf(13);
        m[1][0] = BigRational.valueOf(17);
        m[1][1] = BigRational.valueOf(19);
        Math_Matrix_BR b = new Math_Matrix_BR(m);
        m[0][0] = BigRational.valueOf(2 * 11 + 3 * 17);
        m[0][1] = BigRational.valueOf(2 * 13 + 3 * 19);
        m[1][0] = BigRational.valueOf(5 * 11 + 7 * 17);
        m[1][1] = BigRational.valueOf(5 * 13 + 7 * 19);
        Math_Matrix_BR expResult = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.multiply(b);
        assertEquals(expResult, result);
        // Test 2
        m = new BigRational[5][3];
        m[0][0] = BigRational.TWO;
        m[1][0] = BigRational.valueOf(3);
        m[2][0] = BigRational.valueOf(5);
        m[3][0] = BigRational.valueOf(7);
        m[4][0] = BigRational.valueOf(11);
        m[0][1] = BigRational.valueOf(13);
        m[1][1] = BigRational.valueOf(17);
        m[2][1] = BigRational.valueOf(19);
        m[3][1] = BigRational.valueOf(21);
        m[4][1] = BigRational.valueOf(23);
        m[0][2] = BigRational.valueOf(29);
        m[1][2] = BigRational.valueOf(31);
        m[2][2] = BigRational.valueOf(37);
        m[3][2] = BigRational.valueOf(41);
        m[4][2] = BigRational.valueOf(43);
        a = new Math_Matrix_BR(m);
        m = new BigRational[3][4];
        m[0][0] = BigRational.valueOf(47);
        m[0][1] = BigRational.valueOf(53);
        m[0][2] = BigRational.valueOf(59);
        m[0][3] = BigRational.valueOf(61);
        m[1][0] = BigRational.valueOf(67);
        m[1][1] = BigRational.valueOf(71);
        m[1][2] = BigRational.valueOf(73);
        m[1][3] = BigRational.valueOf(79);
        m[2][0] = BigRational.valueOf(83);
        m[2][1] = BigRational.valueOf(87);
        m[2][2] = BigRational.valueOf(91);
        m[2][3] = BigRational.valueOf(97);
        b = new Math_Matrix_BR(m);
        m = new BigRational[5][4];
        m[0][0] = BigRational.valueOf(2 * 47 + 13 * 67 + 29 * 83); //3372 
        m[0][1] = BigRational.valueOf(2 * 53 + 13 * 71 + 29 * 87); //3552
        m[0][2] = BigRational.valueOf(2 * 59 + 13 * 73 + 29 * 91); //3706
        m[0][3] = BigRational.valueOf(2 * 61 + 13 * 79 + 29 * 97); //3962;
        m[1][0] = BigRational.valueOf(3 * 47 + 17 * 67 + 31 * 83); //3853
        m[1][1] = BigRational.valueOf(3 * 53 + 17 * 71 + 31 * 87); //4063
        m[1][2] = BigRational.valueOf(3 * 59 + 17 * 73 + 31 * 91); //4239
        m[1][3] = BigRational.valueOf(3 * 61 + 17 * 79 + 31 * 97); //4533
        m[2][0] = BigRational.valueOf(5 * 47 + 19 * 67 + 37 * 83); //4579
        m[2][1] = BigRational.valueOf(5 * 53 + 19 * 71 + 37 * 87); //4833
        m[2][2] = BigRational.valueOf(5 * 59 + 19 * 73 + 37 * 91); //5049
        m[2][3] = BigRational.valueOf(5 * 61 + 19 * 79 + 37 * 97); //5395
        m[3][0] = BigRational.valueOf(7 * 47 + 21 * 67 + 41 * 83); //5139
        m[3][1] = BigRational.valueOf(7 * 53 + 21 * 71 + 41 * 87); //5429
        m[3][2] = BigRational.valueOf(7 * 59 + 21 * 73 + 41 * 91); //5677
        m[3][3] = BigRational.valueOf(7 * 61 + 21 * 79 + 41 * 97); //6063
        m[4][0] = BigRational.valueOf(11 * 47 + 23 * 67 + 43 * 83); //5533
        m[4][1] = BigRational.valueOf(11 * 53 + 23 * 71 + 43 * 87); //5851
        m[4][2] = BigRational.valueOf(11 * 59 + 23 * 73 + 43 * 91); //6123
        m[4][3] = BigRational.valueOf(11 * 61 + 23 * 79 + 43 * 97); //6537
        expResult = new Math_Matrix_BR(m);
        result = a.multiply(b);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeterminant method, of class Math_Matrix_BR.
     */
    @Test
    public void testGetDeterminant() {
        System.out.println("getDeterminant");
        // Test 2x2 from https://www.mathsisfun.com/algebra/matrix-determinant.html
        BigRational[][] m = new BigRational[2][2];
        m[0][0] = BigRational.valueOf(3);
        m[0][1] = BigRational.valueOf(8);
        m[1][0] = BigRational.valueOf(4);
        m[1][1] = BigRational.valueOf(6);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        BigRational expResult = BigRational.valueOf(-14);
        BigRational result = a.getDeterminant();
        assertTrue(result.compareTo(expResult) == 0);
        // Test 3x3 from https://www.mathsisfun.com/algebra/matrix-determinant.html
        //   6   1   1
        //   4  -2   5
        //   2   8   7
        // -306
        m = new BigRational[3][3];
        m[0][0] = BigRational.valueOf(6);
        m[0][1] = BigRational.valueOf(1);
        m[0][2] = BigRational.valueOf(1);
        m[1][0] = BigRational.valueOf(4);
        m[1][1] = BigRational.valueOf(-2);
        m[1][2] = BigRational.valueOf(5);
        m[2][0] = BigRational.valueOf(2);
        m[2][1] = BigRational.valueOf(8);
        m[2][2] = BigRational.valueOf(7);
        a = new Math_Matrix_BR(m);
        expResult = BigRational.valueOf(-306);
        result = a.getDeterminant();
        assertTrue(result.compareTo(expResult) == 0);
        // Test 4x4 from https://youtu.be/fWzUwrt1Z0s
        //   1   0   4  -6
        //   2   5   0   3
        //  -1   2   3   5
        //   2   1  -2   3
        // 318
        m = new BigRational[4][4];
        m[0][0] = BigRational.valueOf(1);
        m[0][1] = BigRational.valueOf(0);
        m[0][2] = BigRational.valueOf(4);
        m[0][3] = BigRational.valueOf(-6);
        m[1][0] = BigRational.valueOf(2);
        m[1][1] = BigRational.valueOf(5);
        m[1][2] = BigRational.valueOf(0);
        m[1][3] = BigRational.valueOf(3);
        m[2][0] = BigRational.valueOf(-1);
        m[2][1] = BigRational.valueOf(2);
        m[2][2] = BigRational.valueOf(3);
        m[2][3] = BigRational.valueOf(5);
        m[3][0] = BigRational.valueOf(2);
        m[3][1] = BigRational.valueOf(1);
        m[3][2] = BigRational.valueOf(-2);
        m[3][3] = BigRational.valueOf(3);
        expResult = BigRational.valueOf(318);
        a = new Math_Matrix_BR(m);        
        result = a.getDeterminant();
        assertTrue(result.compareTo(expResult) == 0);
        
        // Test 5x5 from https://math.stackexchange.com/questions/1955784/how-to-find-the-determinant-of-a-5x5-matrix
        //   0   6  -2  -1   5
        //   0   0   0  -9  -7
        //   0  15  35   0   0
        //   0  −1 -11  -2   1
        //  -2  -2   3   0  -2
        // 2480
        m = new BigRational[5][5];
        m[0][0] = BigRational.valueOf(0);
        m[0][1] = BigRational.valueOf(6);
        m[0][2] = BigRational.valueOf(-2);
        m[0][3] = BigRational.valueOf(-1);
        m[0][4] = BigRational.valueOf(5);
        m[1][0] = BigRational.valueOf(0);
        m[1][1] = BigRational.valueOf(0);
        m[1][2] = BigRational.valueOf(0);
        m[1][3] = BigRational.valueOf(-9);
        m[1][4] = BigRational.valueOf(-7);
        m[2][0] = BigRational.valueOf(0);
        m[2][1] = BigRational.valueOf(15);
        m[2][2] = BigRational.valueOf(35);
        m[2][3] = BigRational.valueOf(0);
        m[2][4] = BigRational.valueOf(0);
        m[3][0] = BigRational.valueOf(0);
        m[3][1] = BigRational.valueOf(-1);
        m[3][2] = BigRational.valueOf(-11);
        m[3][3] = BigRational.valueOf(-2);
        m[3][4] = BigRational.valueOf(1);
        m[4][0] = BigRational.valueOf(-2);
        m[4][1] = BigRational.valueOf(-2);
        m[4][2] = BigRational.valueOf(3);
        m[4][3] = BigRational.valueOf(0);
        m[4][4] = BigRational.valueOf(-2);
        a = new Math_Matrix_BR(m);        
        expResult = BigRational.valueOf(2480);
        result = a.getDeterminant();
        assertTrue(result.compareTo(expResult) == 0);
    }

    /**
     * Test of transpose method, of class Math_Matrix_BR.
     */
    @Test
    public void testgetTranspose_0args() {
        System.out.println("getTranspose");
        BigRational[][] m = new BigRational[2][1];
        Math_Matrix_BR a;
        m[0][0] = BigRational.TWO;
        m[1][0] = BigRational.valueOf(3);
        a = new Math_Matrix_BR(m);
        m = new BigRational[1][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        Math_Matrix_BR expResult = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.getTranspose();
        assertEquals(expResult, result);
        // Test 2
        m = new BigRational[3][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        m[2][0] = BigRational.valueOf(11);
        m[2][1] = BigRational.valueOf(13);
        a = new Math_Matrix_BR(m);
        m = new BigRational[2][3];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(5);
        m[0][2] = BigRational.valueOf(11);
        m[1][0] = BigRational.valueOf(3);
        m[1][1] = BigRational.valueOf(7);
        m[1][2] = BigRational.valueOf(13);
        expResult = new Math_Matrix_BR(m);
        result = a.getTranspose();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Math_Matrix_BR.
     */
    @Test
    public void testEquals_Math_Matrix_BR() {
        System.out.println("equals");
        BigRational[][] m = new BigRational[1][1];
        m[0][0] = BigRational.ZERO;
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        Math_Matrix_BR instance = new Math_Matrix_BR(m);
        assertTrue(instance.equals(a));
    }

    /**
     * Test of divide method, of class Math_Matrix_BR.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        BigRational[][] m = new BigRational[2][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR b = new Math_Matrix_BR(m);
        m[0][0] = BigRational.valueOf(4);
        m[0][1] = BigRational.valueOf(6);
        m[1][0] = BigRational.valueOf(10);
        m[1][1] = BigRational.valueOf(14);
        Math_Matrix_BR c = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.add(b);
        assertEquals(c, result);
    }

    /**
     * Test of getTranspose method, of class Math_Matrix_BR.
     */
    @Test
    public void testGetTranspose() {
        System.out.println("getTranspose");
        BigRational[][] m = new BigRational[2][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(5);
        m[1][0] = BigRational.valueOf(3);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR b = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.getTranspose();
        assertEquals(b, result);
    }

    /**
     * Test of isSymmetric method, of class Math_Matrix_BR.
     */
    @Test
    public void testIsSymmetric() {
        System.out.println("isSymmetric");
        BigRational[][] m = new BigRational[2][2];
        m[0][0] = BigRational.TWO;
        m[0][1] = BigRational.valueOf(3);
        m[1][0] = BigRational.valueOf(5);
        m[1][1] = BigRational.valueOf(7);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        assertFalse(a.isSymmetric());
        // Test 2
        m[0][0] = BigRational.valueOf(3);
        m[0][1] = BigRational.TWO;
        m[1][0] = BigRational.TWO;
        m[1][1] = BigRational.valueOf(7);
        a = new Math_Matrix_BR(m);
        assertTrue(a.isSymmetric());
    }

    /**
     * Test of getRank method, of class Math_Matrix_BR.
     * https://www.superprof.co.uk/resources/academic/maths/linear-algebra/determinants/matrix-rank.html#chapter_introduction-to-matrix-rank
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        BigRational[][] m = new BigRational[3][3];
        m[0][0] = BigRational.valueOf(1);
        m[0][1] = BigRational.valueOf(2);
        m[0][2] = BigRational.valueOf(1);
        m[1][0] = BigRational.valueOf(-2);
        m[1][1] = BigRational.valueOf(-3);
        m[1][2] = BigRational.valueOf(1);
        m[2][0] = BigRational.valueOf(3);
        m[2][1] = BigRational.valueOf(5);
        m[2][2] = BigRational.valueOf(0);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        int expResult = 2;
        int result = a.getRank();
        assertTrue(expResult == result);
    }

    /**
     * Test of getGauss method, of class Math_Matrix_BR.
     */
    @Test
    public void testGetReducedRowEchelonForm() {
        System.out.println("getReducedRowEchelonForm");
        BigRational[][] m = new BigRational[4][3];
        m[0][0] = BigRational.valueOf(0);
        m[0][1] = BigRational.valueOf(1);
        m[0][2] = BigRational.valueOf(1);
        m[1][0] = BigRational.valueOf(1);
        m[1][1] = BigRational.valueOf(0);
        m[1][2] = BigRational.valueOf(0);
        m[2][0] = BigRational.valueOf(2);
        m[2][1] = BigRational.valueOf(-1);
        m[2][2] = BigRational.valueOf(0);
        m[3][0] = BigRational.valueOf(1);
        m[3][1] = BigRational.valueOf(1);
        m[3][2] = BigRational.valueOf(1);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.getReducedRowEchelonForm();
        m = new BigRational[4][3];
        m[0][0] = BigRational.valueOf(1);
        m[0][1] = BigRational.valueOf(-1, 2);
        m[0][2] = BigRational.valueOf(0);
        m[1][0] = BigRational.valueOf(0);
        m[1][1] = BigRational.valueOf(1);
        m[1][2] = BigRational.valueOf(2, 3);
        m[2][0] = BigRational.valueOf(0);
        m[2][1] = BigRational.valueOf(0);
        m[2][2] = BigRational.valueOf(1);
        m[3][0] = BigRational.valueOf(0);
        m[3][1] = BigRational.valueOf(0);
        m[3][2] = BigRational.valueOf(0);
        Math_Matrix_BR expResult = new Math_Matrix_BR(m);
        assertTrue(expResult.equals(result));
        // Test 2
        m = new BigRational[3][4];
        m[0][0] = BigRational.valueOf(2);
        m[0][1] = BigRational.valueOf(1);
        m[0][2] = BigRational.valueOf(-1);
        m[0][3] = BigRational.valueOf(8);
        m[1][0] = BigRational.valueOf(-3);
        m[1][1] = BigRational.valueOf(-1);
        m[1][2] = BigRational.valueOf(2);
        m[1][3] = BigRational.valueOf(-11);
        m[2][0] = BigRational.valueOf(-2);
        m[2][1] = BigRational.valueOf(1);
        m[2][2] = BigRational.valueOf(2);
        m[2][3] = BigRational.valueOf(-3);
        a = new Math_Matrix_BR(m);
        result = a.getReducedRowEchelonForm();
        m[0][0] = BigRational.valueOf(1);
        m[0][1] = BigRational.valueOf(0);
        m[0][2] = BigRational.valueOf(0);
        m[0][3] = BigRational.valueOf(2);
        m[1][0] = BigRational.valueOf(0);
        m[1][1] = BigRational.valueOf(1);
        m[1][2] = BigRational.valueOf(0);
        m[1][3] = BigRational.valueOf(3);
        m[2][0] = BigRational.valueOf(0);
        m[2][1] = BigRational.valueOf(0);
        m[2][2] = BigRational.valueOf(1);
        m[2][3] = BigRational.valueOf(-1);
        expResult = new Math_Matrix_BR(m);
        assertTrue(expResult.equals(result));
    }
    
    /**
     * Test of getGauss method, of class Math_Matrix_BR.
     */
    @Test
    public void testGetRowEchelonForm() {
        System.out.println("getRowEchelonForm");
        BigRational[][] m = new BigRational[3][4];
        m[0][0] = BigRational.valueOf(2);
        m[0][1] = BigRational.valueOf(1);
        m[0][2] = BigRational.valueOf(-1);
        m[0][3] = BigRational.valueOf(8);
        m[1][0] = BigRational.valueOf(-3);
        m[1][1] = BigRational.valueOf(-1);
        m[1][2] = BigRational.valueOf(2);
        m[1][3] = BigRational.valueOf(-11);
        m[2][0] = BigRational.valueOf(-2);
        m[2][1] = BigRational.valueOf(1);
        m[2][2] = BigRational.valueOf(2);
        m[2][3] = BigRational.valueOf(-3);
        Math_Matrix_BR a = new Math_Matrix_BR(m);
        Math_Matrix_BR result = a.getRowEchelonForm();
        m = new BigRational[4][3];
        m[0][0] = BigRational.valueOf(1);
        m[0][1] = BigRational.valueOf(1, 2);
        m[0][2] = BigRational.valueOf(-1, 2);
        m[0][3] = BigRational.valueOf(4);
        m[1][0] = BigRational.valueOf(0);
        m[1][1] = BigRational.valueOf(1);
        m[1][2] = BigRational.valueOf(1, 2);
        m[1][3] = BigRational.valueOf(5, 2);
        m[2][0] = BigRational.valueOf(0);
        m[2][1] = BigRational.valueOf(0);
        m[2][2] = BigRational.valueOf(1);
        m[2][3] = BigRational.valueOf(-1);
        Math_Matrix_BR expResult = new Math_Matrix_BR(m);
        assertTrue(expResult.equals(result));
    }
    
}
