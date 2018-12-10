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
import java.util.Random;

public class Generic_float
        extends Generic_Number
        implements Serializable {

    static final long serialVersionUID = 1L;

    /** Creates a new instance of Generic_BigDecimal */
    public Generic_float() {
        //super();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Generic_float().test();
    }

    private void test() {
        print();
        float min = 0.5f;
        float next = next(min);
        System.out.println(
                "next(" + min + ") " + next);
        next = Math.nextUp(min);



        System.out.println(
                "next(" + min + ") " + next);

        float max = 0.6f;
        BigInteger numberOfFloatsInRange = getNumberOfFloatsInRange(min, max);
        System.out.println(
                "numberOfFloatsInRange(" + min + "," + max + ") "
                + numberOfFloatsInRange);

        Random a_Random = new Random(0L);
        int n = 10;
        //print(a_Random, n);
        int m0 = 10;
        for (int m = m0; m < 30; m++) {
            System.out.println(m);
            print(a_Random, n, m);
        }

//        BigDecimal min = new BigDecimal(Float.toString(Float.MIN_VALUE));
//        BigDecimal value_BigDecimal = new BigDecimal(min.toString());
//        System.out.println(value_BigDecimal.toPlainString());
//        for (int i = 0; i < 100; i++) {
//            value_BigDecimal = value_BigDecimal.add(min);
//            System.out.println(value_BigDecimal.toPlainString());
//        }
//        System.out.println(min.toPlainString());
//
//        BigInteger count = BigInteger.ZERO;
//        BigInteger divisor = new BigInteger("100000000");
//        float value = 0;
//        while (value < 1.0d) {
//            value = next(value);
//            //if (count % 100000000 == 0){
//            if (count.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
//                System.out.println("" + count + " values between 0 and ");
//                System.out.println(toPlainString(value));
//            }
//            count = count.add(BigInteger.ONE);
//        }

//        float zero_float = 0.0d;
//        float bigger_float = zero_float;
//        for (int i = 0; i < 100; i++) {
//            bigger_float = next(bigger_float);
//            //System.out.println(bigger_float);
//            System.out.println(toPlainString(bigger_float));
//        }
//
//        float smaller_float = zero_float;
//        for (int i = 0; i < 100; i++) {
//            smaller_float = previous(smaller_float);
//            //System.out.println(smaller_float);
//            System.out.println(toPlainString(smaller_float));
//        }
    }

    public static void print() {
        System.out.println("Float.MIN_VALUE");
        System.out.println(toPlainString(Float.MIN_VALUE));

        System.out.println("Float.MIN_NORMAL");
        System.out.println(toPlainString(Float.MIN_NORMAL));

        System.out.println("Float.MAX_VALUE");
        System.out.println(Float.MAX_VALUE);

        //System.out.println("Float.MAX_VALUE");
        //System.out.println(Math.nextUp());
    }

    /**
     * Print out the next n a_Random.nextFloat() values that are represented by
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
            float value = a_Random.nextFloat();
            String value_String = toPlainString(value);
            if (value_String.length() > m) {
                System.out.println(toPlainString(value));
                counter++;
            }
            loopCounter = loopCounter.add(BigInteger.ONE);
            if (loopCounter.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                System.out.println(
                        "a_Random.nextFloat() tested " + loopCounter + " times.");
            }
        }
        System.out.println(
                "a_Random.nextFloat() tested " + loopCounter + " times.");
    }

    public static void print(
            Random a_Random,
            int n) {
        for (long i = 0; i < n; i++) {
            float value = a_Random.nextFloat();
            System.out.println(toPlainString(value));
        }
    }

    public static String toPlainString(float f) {
        return new BigDecimal(Float.toString(f)).toPlainString();
    }

    public static BigInteger getNumberOfFloatsInRange(float lower, float upper) {
        BigInteger count = BigInteger.ZERO;
        BigInteger divisor = new BigInteger("100000");
        float value = lower;
        while (value <= upper) {
            value = next(value);
            //if (count % 100000000 == 0){
            if (count.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                System.out.println("" + count + " values between " + lower + " and " + upper);
                System.out.println(toPlainString(value));
            }
            count = count.add(BigInteger.ONE);
        }
        return count;
    }

//    /**
//     * @returns the next (closer to positive infinity) float to value
//     */
//    public static float next(
//            float value) {
//        if (value == Float.MAX_VALUE) {
//            System.out.println(
//                    "Warning: Returning Float.POSITIVE_INFINITY in "
//                    + Generic_float.class + ".next(" + value + ")");
//            return Float.POSITIVE_INFINITY;
//        } else {
//            float MIN_VALUE = Float.MIN_VALUE;
//            float MIN_NORMAL = Float.MIN_NORMAL;
//            float difference = MIN_NORMAL;
//            //float difference = MIN_VALUE;
//            float previousDifference = difference;
//            boolean calculated = false;
//            while (!calculated) {
//                if ((value + difference) == value) {
//                    previousDifference = difference;
//                    System.out.println(difference);
//                    difference += MIN_NORMAL;
//                    //difference += MIN_VALUE;
//                } else {
//                    calculated = true;
//                }
//            }
//            return value + previousDifference;
//        }
//    }

    /**
     * @param a
     * @param b
     * @return a + b > a;
     */
    public static boolean isAddSignificant(float a, float b) {
        return a + b > a;
    }

    /**
     * @param a
     * @param b
     * @return a - b < a;
     */
    public static boolean isSubtractSignificant(float a, float b) {
        return a - b < a;
    }

    public static float next(
            float value,
            float lowerbound,
            float upperbound) {
        float halfway = (upperbound - lowerbound) / 2f;
        if (isAddSignificant(value, halfway)) {
            return next(value, lowerbound, lowerbound + halfway);
        } else {
            if (halfway > 0f) {
                return next(value, lowerbound + halfway, upperbound);
            } else {
                return value + lowerbound;
            }
        }
    }

    public static float next(
            float value) {
        if (value == Float.MAX_VALUE) {
            System.out.println(
                    "Warning: Returning Float.POSITIVE_INFINITY in "
                    + Generic_float.class + ".next(" + value + ")");
            return Float.POSITIVE_INFINITY;
        } else {
            float MAX_VALUE = Float.MAX_VALUE;
            float diff_max = value - MAX_VALUE;
            float MIN_VALUE = Float.MIN_VALUE;
            if (diff_max == MIN_VALUE) {
                // Would this ever happen?
                return MAX_VALUE;
            }
            float one = 1f;
            float two = 2f;
            float diff2 = one;
            while (isAddSignificant(value, diff2)) {
                diff2 /= two;
            }
            // diff2 < next <= (diff2 * two)
            return next(value, diff2, diff2 * two);
        }
    }

    public static float previous(
            float value) {
        if (value == Float.MIN_VALUE) {
            System.out.println(
                    "Warning: Returning Float.POSITIVE_INFINITY in "
                    + Generic_float.class + ".next(" + value + ")");
            return Float.POSITIVE_INFINITY;
        } else {
            float MAX_VALUE = Float.MAX_VALUE;
            float diff_max = value - MAX_VALUE;
            float MIN_VALUE = Float.MIN_VALUE;
            if (diff_max == MIN_VALUE) {
                // Would this ever happen?
                return MAX_VALUE;
            }
            float one = 1f;
            float two = 2f;
            float diff2 = one;
            while (isAddSignificant(value, diff2)) {
                diff2 /= two;
            }
            // diff2 < next <= (diff2 * two)
            return next(value, diff2, diff2 * two);
        }
    }

//    /**
//     * @returns the next (closer to positive infinity) float to value
//     */
//    public static float previous(
//            float value) {
//        if (value == Float.MIN_VALUE) {
//            System.out.println(
//                    "Warning: Returning Float.NEGATIVE_INFINITY in "
//                    + Generic_float.class + ".next(" + value + ")"
//                    + " returning Float.POSITIVE_INFINITY");
//            return Float.NEGATIVE_INFINITY;
//        } else {
//            float MIN_VALUE = Float.MIN_VALUE;
//            float difference = MIN_VALUE;
//            float previousDifference = difference;
//            boolean calculated = false;
//            while (!calculated) {
//                if ((value - difference) == value) {
//                    previousDifference = difference;
//                    difference += MIN_VALUE;
//                } else {
//                    calculated = true;
//                }
//            }
//            return value - previousDifference;
//        }
//    }
}
