/**
 * Copyright 2012 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.math.stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
public class Generic_StatisticsTest {

    public Generic_StatisticsTest() {
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
     * Test of getSum method, of class Generic_Statistics.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        List<BigDecimal> data = new ArrayList<>();
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.ONE);
        BigDecimal expResult = BigDecimal.valueOf(7);
        BigDecimal result = Generic_Statistics.getSum(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSummaryStatistics_0 method, of class Generic_Statistics.
     */
    @Test
    public void testGetBoxPlotStatistics() {
        System.out.println("getBoxPlotStatistics");
        ArrayList<BigDecimal> data = new ArrayList<>();
        data.add(BigDecimal.ZERO);
        data.add(BigDecimal.ONE);
        data.add(BigDecimal.valueOf(2));
        data.add(BigDecimal.valueOf(3));
        data.add(BigDecimal.valueOf(4));
        data.add(BigDecimal.valueOf(5));
        data.add(BigDecimal.valueOf(6));
        data.add(BigDecimal.valueOf(7));
        data.add(BigDecimal.valueOf(8));
        int decimalPlaces = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        BigDecimal median;
        BigDecimal q1;
        BigDecimal q3;

        // n = 9
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(4);
        q1 = BigDecimal.valueOf(2);
        q3 = BigDecimal.valueOf(6);
        BigDecimal[] result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 10
        data.add(BigDecimal.valueOf(9));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(4.5d);
        q1 = BigDecimal.valueOf(2.25d);
        q3 = BigDecimal.valueOf(6.75d);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 11
        data.add(BigDecimal.valueOf(10));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(2.5);
        q3 = BigDecimal.valueOf(7.5);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 13
        data.add(BigDecimal.valueOf(5));
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(3);
        q3 = BigDecimal.valueOf(7);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 14
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(3.25);
        q3 = BigDecimal.valueOf(6.75);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 15
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(3.5);
        q3 = BigDecimal.valueOf(6.5);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 16
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(4.25);
        q3 = BigDecimal.valueOf(6.75);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);

        // n = 17
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(4);
        q3 = BigDecimal.valueOf(6);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);
        
        // n = 18
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(4.25);
        q3 = BigDecimal.valueOf(5.75);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);
        
        // n = 19
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(4.5);
        q3 = BigDecimal.valueOf(5.5);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);
        
        // n = 20
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(5);
        q3 = BigDecimal.valueOf(5.75);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);
        
        // n = 21
        data.add(BigDecimal.valueOf(5));
        System.out.println("Stats for data.size() " + data.size());
        median = BigDecimal.valueOf(5);
        q1 = BigDecimal.valueOf(5);
        q3 = BigDecimal.valueOf(5);
        result = Generic_Statistics.getSummaryStatistics_0(
                data,
                decimalPlaces,
                roundingMode);
        assertEquals(median, result[2]);
        assertEquals(q1, result[3]);
        assertEquals(q3, result[4]);
    }
//    /**
//     * Test of getFirstOrderStatistics0 method, of class Generic_Statistics.
//     */
//    @Test
//    public void testGetFirstOrderStatistics0() {
//        System.out.println("getFirstOrderStatistics0");
//        TreeMap<Integer, BigDecimal> map0 = null;
//        TreeMap<Integer, BigDecimal> map1 = null;
//        String map0Name = "";
//        String map1Name = "";
//        String keyName = "";
//        Object[] expResult = null;
//        Object[] result = Generic_Statistics.getFirstOrderStatistics0(map0, map1, map0Name, map1Name, keyName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirstOrderStatistics1 method, of class Generic_Statistics.
//     */
//    @Test
//    public void testGetFirstOrderStatistics1() {
//        System.out.println("getFirstOrderStatistics1");
//        TreeMap<Integer, BigDecimal> map0 = null;
//        TreeMap<Integer, BigDecimal> map1 = null;
//        String map0Name = "";
//        String map1Name = "";
//        String keyName = "";
//        Object[] expResult = null;
//        Object[] result = Generic_Statistics.getFirstOrderStatistics1(map0, map1, map0Name, map1Name, keyName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
