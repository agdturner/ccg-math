/**
 * Copyright 2010 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.math;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Generic_double
        extends Generic_Number
        implements Serializable {

    /** Creates a new instance of Generic_BigDecimal */
    public Generic_double() {
        //super();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Generic_double().test();
    }

    private void test() {
        double value;
        double next;

        value = 0.0;
        next = Math.nextUp(value);
        System.out.println(
                "next(" + value + ") " + next);

        value = 0.1;
        next = Math.nextUp(value);
        System.out.println(
                "next(" + value + ") " + next);

        BigDecimal third_15dp_BigDecimal = new BigDecimal("0.33333333333333333333333333333333");
        System.out.println(third_15dp_BigDecimal.doubleValue());

        testDoubleRepresentations0();

//        // Test double representations
//        BigDecimal test_BigDecimal = new BigDecimal("0.87777777777777777");
//        BigDecimal testResult_BigDecimal = testRepresentation(test_BigDecimal);
//        if (test_BigDecimal != null){
//            System.out.println(
//                    test_BigDecimal.toPlainString() + " != "
//                    + testResult_BigDecimal.toPlainString());
//        }
//        testDoubleRepresentations();

        double upper = 0.01;
        double lower = 0.001;
        double upperbit;
        BigInteger numberOfDoublesInRange = null;
        for (int i = 0; i < 1000; i++) {
            // 7205759404 double values between 0.0999999 and 0.1
            // 3602879702 double values between 0.1999999 and 0.2
            // 1801439851 double values between 0.2999999 and 0.3
            // 900719925 double values between 0.5999999 and 0.6
            // 900719926 double values between 0.9999998 and 0.9999999
            // 900719925 double values between 0.9999999 and 1.0
            // 9 double values between 0.999999999999999 and 1.0
            // 858993460 double values between 1000000.2 and 1000000.3

            numberOfDoublesInRange = getNumberOfDoublesInRange(
                    lower,
                    upper);

            System.out.println(
                    numberOfDoublesInRange + " double values between " + upper + " and " + lower);
            lower /= 10.0d;
            upper /= 10.0d;
        }
//        value = 0.5;
//        next = Math.nextUp(value);
//        System.out.println(
//                "next(" + value + ") " + next);
//
//        value = 0.9;
//        next = Math.nextUp(value);
//        System.out.println(
//                "next(" + value + ") " + next);
//
//        print();
//        Random a_Random = new Random(0L);
//        int n = 10;
//        //print(a_Random, n);
//        int m0 = 20;
//        for (int m = m0; m < 30; m++) {
//            System.out.println(m);
//            print(a_Random, n, m);
//        }
    }

    public void testDoubleRepresentations() {
        // Test double representations
        String[] decimalDigits = new String[10];
        decimalDigits[0] = "0";
        decimalDigits[1] = "1";
        decimalDigits[2] = "2";
        decimalDigits[3] = "3";
        decimalDigits[4] = "4";
        decimalDigits[5] = "5";
        decimalDigits[6] = "6";
        decimalDigits[7] = "7";
        decimalDigits[8] = "8";
        decimalDigits[9] = "9";
        double value_double;
        BigDecimal value_BigDecimal;
        String value_String = null;
        double increment_double = 1.0d;
        BigDecimal increment_BigDecimal = new BigDecimal("1.0");
        double max_double = 1.0d;
        BigDecimal max_BigDecimal = BigDecimal.ONE;
        BigInteger count_BigInteger = null;
        BigDecimal maxAbsDifference = null;
        BigDecimal positiveDifferenceSum = null;
        BigDecimal negativeDifferenceSum = null;
        BigInteger hundred_BigInteger = new BigInteger("100");
        BigDecimal percentageCorrectValues_BigDecimal;
        BigDecimal nValues_BigDecimal = null;
//        for (int decimalPlaces = 1; decimalPlaces < 100; decimalPlaces++) {
//            value_String = "0.";
//            for (int decimalPlace = 1; decimalPlace < decimalPlaces; decimalPlace ++) {
//                for (int digit = 0; digit < 10; digit ++) {
//                        value_String += decimalDigits[digit];
//                    }
//                        System.out.println(value_String);
//            }
//        }
        for (int i = 0; i < 100; i++) {
            value_BigDecimal = BigDecimal.ZERO;
            increment_BigDecimal = increment_BigDecimal.divide(
                    BigDecimal.TEN, i + 1, RoundingMode.UNNECESSARY);
            System.out.println(
                    "Test double representation between "
                    + value_BigDecimal.toPlainString()
                    + " and " + max_double + " with "
                    + increment_BigDecimal.toPlainString()
                    + " increment.");
            BigDecimal comparison_BigDecimal = null;
            count_BigInteger = BigInteger.ZERO;
            negativeDifferenceSum = BigDecimal.ZERO;
            positiveDifferenceSum = BigDecimal.ZERO;
            maxAbsDifference = BigDecimal.ZERO;
            //while (value_double < max_double) {
            while (value_BigDecimal.compareTo(max_BigDecimal) == -1) {
                //value_double += increment_double;
                value_BigDecimal = value_BigDecimal.add(increment_BigDecimal);
                value_double = value_BigDecimal.doubleValue();
                comparison_BigDecimal = new BigDecimal(Double.toString(value_double));
                if (value_BigDecimal.compareTo(comparison_BigDecimal) != 0) {
//                    System.out.println(
//                            value_BigDecimal + " != " + comparison_BigDecimal);
                    count_BigInteger = count_BigInteger.add(BigInteger.ONE);
                    BigDecimal difference = value_BigDecimal.subtract(comparison_BigDecimal);
                    BigDecimal absDifference = difference.abs();
                    maxAbsDifference = Generic_BigDecimal.max(maxAbsDifference, absDifference);
                    if (difference.compareTo(BigDecimal.ZERO) == -1) {
                        negativeDifferenceSum = negativeDifferenceSum.add(difference);
                    } else {
                        positiveDifferenceSum = positiveDifferenceSum.add(difference);
                    }
                }
            }
            nValues_BigDecimal = Generic_BigDecimal.reciprocal(
                    increment_BigDecimal, 0, RoundingMode.UNNECESSARY);
            percentageCorrectValues_BigDecimal = Generic_BigDecimal.divideRoundIfNecessary(
                    count_BigInteger.multiply(hundred_BigInteger),
                    nValues_BigDecimal,
                    5,
                    RoundingMode.UP);
            System.out.println(
                    percentageCorrectValues_BigDecimal.toPlainString() + "% ("
                    + count_BigInteger.toString() + " out of "
                    + nValues_BigDecimal.toPlainString()
                    + ") double values not precisely represented.");
            System.out.println(
                    "positiveDifferenceSum " + positiveDifferenceSum.toPlainString());
            System.out.println(
                    "negativeDifferenceSum " + negativeDifferenceSum.toPlainString());
            System.out.println(
                    "difference "
                    + positiveDifferenceSum.add(negativeDifferenceSum).toPlainString());
            System.out.println(
                    "maxAbsDifference "
                    + maxAbsDifference.toPlainString());
        }
    }

    public BigDecimal testRepresentation(BigDecimal value_BigDecimal) {
        double value_double = value_BigDecimal.doubleValue();
        BigDecimal comparison_BigDecimal = new BigDecimal(Double.toString(value_double));
        if (value_BigDecimal.compareTo(comparison_BigDecimal) != 0) {
            return comparison_BigDecimal;
        }
        return null;
    }

    public void testDoubleRepresentations0() {
        // Test double representations
        double value_double;
        BigDecimal value_BigDecimal;
        String value_String = null;
        double increment_double = 1.0d;
        BigDecimal increment_BigDecimal = null;
        BigInteger incrementDivisor_BigInteger = null;
        double max_double = 1.0d;

        //BigDecimal min_BigDecimal = BigDecimal.ZERO;
        BigDecimal min_BigDecimal = new BigDecimal("1.0");
        BigDecimal minIncrement_BigDecimal = new BigDecimal("0.01");
        BigInteger count_BigInteger = null;
        BigDecimal maxAbsDifference = null;
        BigDecimal positiveDifferenceSum = null;
        BigDecimal negativeDifferenceSum = null;
        BigInteger hundred_BigInteger = new BigInteger("100");
        BigDecimal percentageCorrectValues_BigDecimal;
        BigDecimal nValues_BigDecimal = null;
        for (int i = 14; i < 20; i++) {
            value_double = 0.0d;
            value_BigDecimal = BigDecimal.ONE;
            increment_double /= 10.0d;
            incrementDivisor_BigInteger =
                    BigInteger.TEN.pow(i + 1);
            increment_BigDecimal = Generic_BigDecimal.divideRoundIfNecessary(
                    BigDecimal.ONE,
                    incrementDivisor_BigInteger,
                    i + 2,
                    RoundingMode.UNNECESSARY);
            for (int j = 0; j < 100; j ++){
                min_BigDecimal = min_BigDecimal.subtract(minIncrement_BigDecimal);
            System.out.println(
                    "Test double representation between "
                    + value_BigDecimal.toPlainString()
                    + " and " + min_BigDecimal + " with "
                    + increment_BigDecimal.toPlainString()
                    + " increment.");
            BigDecimal comparison_BigDecimal = null;
            count_BigInteger = BigInteger.ZERO;
            negativeDifferenceSum = BigDecimal.ZERO;
            positiveDifferenceSum = BigDecimal.ZERO;
            maxAbsDifference = BigDecimal.ZERO;
            //while (value_double < max_double) {
            while (value_BigDecimal.compareTo(min_BigDecimal) == 1) {
                //value_double += increment_double;
                value_BigDecimal = value_BigDecimal.subtract(increment_BigDecimal);
                value_double = value_BigDecimal.doubleValue();
                comparison_BigDecimal = new BigDecimal(Double.toString(value_double));
                if (value_BigDecimal.compareTo(comparison_BigDecimal) != 0) {
                    System.out.println(
                            value_BigDecimal + " != " + comparison_BigDecimal);
                    count_BigInteger = count_BigInteger.add(BigInteger.ONE);
                    BigDecimal difference = value_BigDecimal.subtract(comparison_BigDecimal);
                    BigDecimal absDifference = difference.abs();
                    maxAbsDifference = Generic_BigDecimal.max(maxAbsDifference, absDifference);
                    if (difference.compareTo(BigDecimal.ZERO) == -1) {
                        negativeDifferenceSum = negativeDifferenceSum.add(difference);
                    } else {
                        positiveDifferenceSum = positiveDifferenceSum.add(difference);
                    }
                }
            }
            nValues_BigDecimal = Generic_BigDecimal.reciprocal(
                    increment_BigDecimal, 0, RoundingMode.UNNECESSARY);
            percentageCorrectValues_BigDecimal = Generic_BigDecimal.divideRoundIfNecessary(
                    count_BigInteger.multiply(hundred_BigInteger),
                    nValues_BigDecimal,
                    5,
                    RoundingMode.UP);
            System.out.println(
                    percentageCorrectValues_BigDecimal.toPlainString() + "% ("
                    + count_BigInteger.toString() + " out of "
                    + nValues_BigDecimal.toPlainString()
                    + ") double values not precisely represented.");
            System.out.println(
                    "positiveDifferenceSum " + positiveDifferenceSum.toPlainString());
            System.out.println(
                    "negativeDifferenceSum " + negativeDifferenceSum.toPlainString());
            System.out.println(
                    "difference "
                    + positiveDifferenceSum.add(negativeDifferenceSum).toPlainString());
            System.out.println(
                    "maxAbsDifference "
                    + maxAbsDifference.toPlainString());
            }
        }
    }

    public static void print() {
        System.out.println("Double.MIN_VALUE");
        System.out.println(toPlainString(Double.MIN_VALUE));

        System.out.println("Double.MIN_NORMAL");
        System.out.println(toPlainString(Double.MIN_NORMAL));

        System.out.println("Long.MAX_VALUE");
        System.out.println(Long.MAX_VALUE);
    }

    /**
     * Print out the next n a_Random.nextDouble() values that are represented by
     * a string longer than m
     * @param a_Random
     * @param n
     * @param m
     */
    public static void print(
            Random a_Random,
            int n,
            int m) {
        int counter = 0;
        BigInteger divisor = new BigInteger("100000000");
        BigInteger loopCounter = BigInteger.ZERO;
        while (counter < n) {
            double value = a_Random.nextDouble();
            String value_String = toPlainString(value);
            if (value_String.length() > m) {
                System.out.println(toPlainString(value));
                counter++;
            }
            loopCounter = loopCounter.add(BigInteger.ONE);
            if (loopCounter.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                System.out.println(
                        "a_Random.nextDouble() tested " + loopCounter + " times.");
            }
        }
        System.out.println(
                "a_Random.nextDouble() tested " + loopCounter + " times.");

    }

    public static void print(
            Random a_Random,
            int n) {
        for (long i = 0; i < n; i++) {
            double value = a_Random.nextDouble();
            System.out.println(toPlainString(value));
        }
    }

    public static String toPlainString(double d) {
        return new BigDecimal(Double.toString(d)).toPlainString();
    }

    /**
     * The count is written to System.out after each 100000000
     * @param lower
     * @param upper
     * @return The total number of doubles represented in (lower, upper)
     */
    public static BigInteger getNumberOfDoublesInRange(
            double lower,
            double upper) {
        BigInteger count = BigInteger.ZERO;
        BigInteger divisor = new BigInteger("100000000");
        double value = lower;
        while (value < upper) {
            value = Math.nextUp(value);
            count = count.add(BigInteger.ONE);
            //if (count % 100000000 == 0){
//            if (count.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
//                System.out.println("" + count + " values between " + lower + " and " + upper);
//                System.out.println(toPlainString(value));
//            }
        }
        return count;
    }
    
    
    public static int roundUpToNearestInt(double v) {
        int result;
        result = Generic_BigDecimal.roundStrippingTrailingZeros(
                new BigDecimal(v),
                0,
                RoundingMode.UP).intValue();
        return result;
}
}
