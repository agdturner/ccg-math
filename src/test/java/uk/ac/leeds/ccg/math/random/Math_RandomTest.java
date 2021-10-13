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
package uk.ac.leeds.ccg.math.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;
import uk.ac.leeds.ccg.math.random.Math_Random;

/**
 *
 * @author Andy Turner
 */
public class Math_RandomTest {
    
    public Math_RandomTest() {
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
     * Test of getRandom method, of class Math_BigDecimal.
     */
    @Test
    public void testGetRandom_int() {
        System.out.println("getRandom");
        int oom;
        BigDecimal expResult;
        BigDecimal result;
        long seed;
        int numberOfRandomInstances;
        Math_BigDecimal bd = new Math_BigDecimal();
        // Test 1
        seed = 0L;
        numberOfRandomInstances = 1000;
        Math_Random rand = new Math_Random(numberOfRandomInstances, seed, 1);
        oom = -1000;
        expResult = new BigDecimal(
                "0.058427164938625116053827514073627362958427064938847316953827"
                + "504950497261958417066150847306953817281750397261948349286150"
                + "837206959584170649286150069538175049726172619483170639288372"
                + "069528175039403972519483160561408372059428172716403962519473"
                + "392851408362059494831605392851400594281740397251625194731605"
                + "382783620594271640394938625184731695514073620584271627064938"
                + "615084733827504973629584847316953827504905842716493862516150"
                + "847306953817736295842706493849286150837206955049726195841706"
                + "170639286140837228175039726194831706392851408372281740397261"
                + "948394831605392851400594281740396251625184731605382773620594"
                + "271640394938625184730695514073629584271616053827514073622716"
                + "403962519473847306953827504905842716493862516150847306952817"
                + "736295841706493839286150837206955049726195841706069538175049"
                + "726127064938615084738372069528175039958417064928615061408372"
                + "059428177261948317063928392851408362059440397251948316050594"
                + "281740397251170639286140837283620594271640399483160539285140"
                + "5140736205842716625194731605382738275049");
//        BigDecimal smallProbability = new BigDecimal("0.0001");
//        for (int i = 0; i < 10000; i ++) {
//            result = Math_BigDecimal.getRandom(bd, dp);
//            if (result.compareTo(smallProbability) == -1){
//                System.out.println("result " + result.toPlainString());
//            }
//        }
        result = rand.getRandomBigDecimal(oom);
        assertTrue(expResult.compareTo(result) == 0);
    }
   
    /**
     * Test of getRandom method, of class Math_Random.
     */
    @Test
    public void testGetRandom_4args() {
        System.out.println("getRandom");
        int oom = 0;
        Math_BigInteger mbi = new Math_BigInteger();
        Math_Random instance = null;
        BigDecimal expResult = null;
        Math_Random rand = new Math_Random();
        // Test 1
        oom = -10;
        BigDecimal l = BigDecimal.ZERO;
        BigDecimal u = BigDecimal.ONE;
        //expResult = new BigDecimal("0.4932604312");
        expResult = new BigDecimal("0.4106274901");
        BigDecimal result = rand.getRandom(oom, l, u, mbi);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRandom method, of class Math_Random.
     */
    @Test
    public void testGetRandom_Math_BigInteger_BigInteger() {
        System.out.println("getRandom");
        BigInteger upperLimit;
        BigInteger result;
        BigInteger expResult;
        Math_Random rand;
        int length;
        long seed;
        int seedIncrement;
        // Test 1
        int test = 1;
        length = 100;
        seed = 0L;
        seedIncrement = 1;
        rand = new Math_Random(length, seed, seedIncrement);
        upperLimit = new BigInteger("10000");
        expResult = new BigInteger("4402");
        result = rand.getRandom(new Math_BigInteger(), upperLimit);
        assertEquals(expResult, result);
        // Test 2
        test++;
        length = 100;
        seed = 0L;
        rand = new Math_Random(length, seed, seedIncrement);
        upperLimit = new BigInteger("1000000000000000000000000000000000000000");
        expResult = new BigInteger("628570378078456855601488631590048551226");
        result = rand.getRandom(new Math_BigInteger(), upperLimit);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 3
        test++;
        length = 100;
        seed = 1234567L;
        rand = new Math_Random(length, seed, seedIncrement);
        upperLimit = new BigInteger("10000");
        expResult = new BigInteger("8804");
        result = rand.getRandom(new Math_BigInteger(), upperLimit);
        //System.out.println(result);
        assertEquals(expResult, result);
        // Test 4
        test++;
        length = 100;
        seed = 1234567L;
        rand = new Math_Random(length, seed, seedIncrement);
        upperLimit = new BigInteger("1000000000000000000000000000000000000000");
        expResult = new BigInteger("772480814235536969920545354438528182674");
        //System.out.println(result);
        result = rand.getRandom(new Math_BigInteger(), upperLimit);
        assertEquals(expResult, result);
    }
    
}
