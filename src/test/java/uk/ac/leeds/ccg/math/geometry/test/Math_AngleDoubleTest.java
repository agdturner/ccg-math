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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.geometry.Math_AngleDouble;

/**
 * Test class for V3D_Angle.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_AngleDoubleTest {

    public Math_AngleDoubleTest() {
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
     * Test of isIntersectedBy method, of class V3D_LineDouble.
     */
    @Test
    public void testNormalise() {
        System.out.println("normalise");
        // Positive angle
        double tolerance = 0.00000000000001D;
        //double tolerance = 0.000000000000001D; // This tolerance is too little.
        double theta = Math.PI * 21D;
        double result = Math_AngleDouble.normalise(theta);
        double expResult = Math.PI;
        assertTrue((result + tolerance > expResult) && (result - tolerance < expResult));
        // Test 2
        theta = Math.PI * 20D + Math.PI / 2.0D;
        result = Math_AngleDouble.normalise(theta);
        expResult = Math.PI / 2.0D;
        assertTrue((result + tolerance > expResult) && (result - tolerance < expResult));
        // Negative angles
        // Test 3
        tolerance = 0.0000000000001D;
        //tolerance = 0.00000000000001D; // This tolerance is too little.
        theta = -Math.PI * 21D;
        result = Math_AngleDouble.normalise(theta);
        expResult = Math.PI;
        assertTrue((result + tolerance > expResult) && (result - tolerance < expResult));
        // Test 4
        tolerance = 0.00000000000001D;
        //tolerance = 0.000000000000001D; // This tolerance is too little.
        theta = -(Math.PI * 20D +  Math.PI / 2.0D);
        result = Math_AngleDouble.normalise(theta);
        expResult = Math.PI * 3D / 2D;
        assertTrue((result + tolerance > expResult) && (result - tolerance < expResult));
    }

}
