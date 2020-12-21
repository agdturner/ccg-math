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

/**
 *
 * @author Andy Turner
 */
public class Matrix_BRTest {
    
    public Matrix_BRTest() {
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
     * Test of multiply method, of class Matrix_BR.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        Matrix_BR a = new Matrix_BR(2, 2);
        a.m[0][0] = BigRational.TWO;
        a.m[0][1] = BigRational.valueOf(3);
        a.m[1][0] = BigRational.valueOf(5);
        a.m[1][1] = BigRational.valueOf(7);
        Matrix_BR b = new Matrix_BR(2, 2);
        b.m[0][0] = BigRational.valueOf(11);
        b.m[0][1] = BigRational.valueOf(13);
        b.m[1][0] = BigRational.valueOf(17);
        b.m[1][1] = BigRational.valueOf(19);
        Matrix_BR expResult = new Matrix_BR(2, 2);
        expResult.m[0][0] = BigRational.valueOf(2*11 + 3*17);
        expResult.m[0][1] = BigRational.valueOf(2*13 + 3*19);
        expResult.m[1][0] = BigRational.valueOf(5*11 + 7*17);
        expResult.m[1][1] = BigRational.valueOf(5*13 + 7*19);
        Matrix_BR result = Matrix_BR.multiply(a, b);
        assertEquals(expResult, result);
        // Test 2
        a = new Matrix_BR(5, 3);
        a.m[0][0] = BigRational.TWO;
        a.m[1][0] = BigRational.valueOf(3);
        a.m[2][0] = BigRational.valueOf(5);
        a.m[3][0] = BigRational.valueOf(7);
        a.m[4][0] = BigRational.valueOf(11);
        a.m[0][1] = BigRational.valueOf(13);
        a.m[1][1] = BigRational.valueOf(17);
        a.m[2][1] = BigRational.valueOf(19);
        a.m[3][1] = BigRational.valueOf(21);
        a.m[4][1] = BigRational.valueOf(23);
        a.m[0][2] = BigRational.valueOf(29);
        a.m[1][2] = BigRational.valueOf(31);
        a.m[2][2] = BigRational.valueOf(37);
        a.m[3][2] = BigRational.valueOf(41);
        a.m[4][2] = BigRational.valueOf(43);
        b = new Matrix_BR(3, 4);
        b.m[0][0] = BigRational.valueOf(47);
        b.m[0][1] = BigRational.valueOf(53);
        b.m[0][2] = BigRational.valueOf(59);
        b.m[0][3] = BigRational.valueOf(61);
        b.m[1][0] = BigRational.valueOf(67);
        b.m[1][1] = BigRational.valueOf(71);
        b.m[1][2] = BigRational.valueOf(73);
        b.m[1][3] = BigRational.valueOf(79);
        b.m[2][0] = BigRational.valueOf(83);
        b.m[2][1] = BigRational.valueOf(87);
        b.m[2][2] = BigRational.valueOf(91);
        b.m[2][3] = BigRational.valueOf(97);
        expResult = new Matrix_BR(5, 4);
        expResult.m[0][0] = BigRational.valueOf(2*47 + 13*67 + 29*83); //3372 
        expResult.m[0][1] = BigRational.valueOf(2*53 + 13*71 + 29*87); //3552
        expResult.m[0][2] = BigRational.valueOf(2*59 + 13*73 + 29*91); //3706
        expResult.m[0][3] = BigRational.valueOf(2*61 + 13*79 + 29*97); //3962;
        expResult.m[1][0] = BigRational.valueOf(3*47 + 17*67 + 31*83); //3853
        expResult.m[1][1] = BigRational.valueOf(3*53 + 17*71 + 31*87); //4063
        expResult.m[1][2] = BigRational.valueOf(3*59 + 17*73 + 31*91); //4239
        expResult.m[1][3] = BigRational.valueOf(3*61 + 17*79 + 31*97); //4533
        expResult.m[2][0] = BigRational.valueOf(5*47 + 19*67 + 37*83); //4579
        expResult.m[2][1] = BigRational.valueOf(5*53 + 19*71 + 37*87); //4833
        expResult.m[2][2] = BigRational.valueOf(5*59 + 19*73 + 37*91); //5049
        expResult.m[2][3] = BigRational.valueOf(5*61 + 19*79 + 37*97); //5395
        expResult.m[3][0] = BigRational.valueOf(7*47 + 21*67 + 41*83); //5139
        expResult.m[3][1] = BigRational.valueOf(7*53 + 21*71 + 41*87); //5429
        expResult.m[3][2] = BigRational.valueOf(7*59 + 21*73 + 41*91); //5677
        expResult.m[3][3] = BigRational.valueOf(7*61 + 21*79 + 41*97); //6063
        expResult.m[4][0] = BigRational.valueOf(11*47 + 23*67 + 43*83); //5533
        expResult.m[4][1] = BigRational.valueOf(11*53 + 23*71 + 43*87); //5851
        expResult.m[4][2] = BigRational.valueOf(11*59 + 23*73 + 43*91); //6123
        expResult.m[4][3] = BigRational.valueOf(11*61 + 23*79 + 43*97); //6537
        result = Matrix_BR.multiply(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Matrix_BR.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object o = new Matrix_BR(1, 1);
        ((Matrix_BR) o).m[0][0] = BigRational.ZERO;
        Matrix_BR instance = new Matrix_BR(1, 1);
        instance.m[0][0] = BigRational.ZERO;
        assertTrue(instance.equals(o));
    }

    /**
     * Test of hashCode method, of class Matrix_BR.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        // No test.
    }

    /**
     * Test of equals method, of class Matrix_BR.
     */
    @Test
    public void testEquals_Matrix_BR() {
        System.out.println("equals");
        Matrix_BR a = new Matrix_BR(1, 1);
        a.m[0][0] = BigRational.ZERO;
        Matrix_BR instance = new Matrix_BR(1, 1);
        instance.m[0][0] = BigRational.ZERO;
        assertTrue(instance.equals(a));
    }

    /**
     * Test of toString method, of class Matrix_BR.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Matrix_BR instance = new Matrix_BR(1, 1);
        instance.m[0][0] = BigRational.ZERO;
        String expResult = "Matrix_BR(nr=1, nc=1" + "\n" + "0 " + "\n" + ")";
        System.out.println(expResult);
        String result = instance.toString();
        System.out.println(result);
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of transpose method, of class Matrix_BR.
     */
    @Test
    public void testTranspose() {
        System.out.println("transpose");
        Matrix_BR a = new Matrix_BR(2, 1);
        a.m[0][0] = BigRational.TWO;
        a.m[1][0] = BigRational.valueOf(3);
        Matrix_BR expResult = new Matrix_BR(1, 2);
        expResult.m[0][0] = BigRational.TWO;
        expResult.m[0][1] = BigRational.valueOf(3);
        Matrix_BR result = Matrix_BR.transpose(a);
        assertEquals(expResult, result);
        // Test 2
        a = new Matrix_BR(3, 2);
        a.m[0][0] = BigRational.TWO;
        a.m[0][1] = BigRational.valueOf(3);
        a.m[1][0] = BigRational.valueOf(5);
        a.m[1][1] = BigRational.valueOf(7);
        a.m[2][0] = BigRational.valueOf(11);
        a.m[2][1] = BigRational.valueOf(13);
        expResult = new Matrix_BR(2, 3);
        expResult.m[0][0] = BigRational.TWO;
        expResult.m[0][1] = BigRational.valueOf(5);
        expResult.m[0][2] = BigRational.valueOf(11);
        expResult.m[1][0] = BigRational.valueOf(3);
        expResult.m[1][1] = BigRational.valueOf(7);
        expResult.m[1][2] = BigRational.valueOf(13);
        result = Matrix_BR.transpose(a);
        assertEquals(expResult, result);
    }
    
}
