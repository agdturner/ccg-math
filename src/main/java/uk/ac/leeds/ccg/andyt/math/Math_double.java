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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Math_double extends Math_Number {

    /**
     * For testing if s can be parsed as a double. If the result is equal to
     * -Double.MAX_VALUE then this will return false as -Double.MAX_VALUE is
     * reserved for representing noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * byte excluding Double.MIN_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a double greater than
     * -Double.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isDouble(String s) {
        try {
            double x = Double.parseDouble(s);
            return x != -Double.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * For testing if s can be parsed as a double and is precise to {@code dp}
     * decimal places using {@code rm} RoundingMode if necessary to round the
     * parsed value.If the result is equal to -Double.MAX_VALUE then this will
     * return false as -Double.MAX_VALUE is reserved for representing
     * noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double excluding -Double.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @param dp The number of decimal places the result must be accurate to.
     * @param rm The RoundingMode used to round.
     * @return true iff s can be represented as a Double greater than
     * -Double.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isDouble(String s, int dp, RoundingMode rm) {
        try {
            double x = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            BigDecimal bdsr = Math_BigDecimal.roundIfNecessary(bds,dp,rm);
            BigDecimal bddr = Math_BigDecimal.roundIfNecessary(bdd,dp,rm);
//            System.out.println(bdsr.toPlainString());
//            System.out.println(bddr.toPlainString());
            if (bdsr.compareTo(bddr) == 0) {
                return x != -Double.MAX_VALUE;
            } else {
//                System.out.println(bdd.toPlainString());
//                System.out.println(new BigDecimal(Math.nextDown(f)));
//                System.out.println(new BigDecimal(Math.nextUp(f)));
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * For testing if s can be parsed as a double exactly. If the result is equal
     * to -Double.MAX_VALUE then this will return false as -Double.MAX_VALUE is
     * reserved for representing noDataValues). This allows no rounding to the
     * nearest double. e.g. 0.1 cannot for instance be represented as a double
     * as the nearest double greater than or equal to 0.1 is
     * 0.10000000000000001942890293094023945741355419158935546875 and the
     * nearest double less than or equal to 0.1 is
     * 0.09999999999999999167332731531132594682276248931884765625
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double excluding -Double.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a double greater than
     * -Double.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isDoubleExact(String s) {
        try {
            double x = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            if (bds.compareTo(bdd) == 0) {
                return x != -Double.MAX_VALUE;
            } else {
//                System.out.println(bdd.toPlainString());
//                System.out.println(new BigDecimal(Math.nextDown(d)));
//                System.out.println(new BigDecimal(Math.nextUp(d)));
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param l The lower value in the range.
     * @param u The upper value in the range.
     * @return The total number of doubles represented in the range (l, u)
     */
    public static BigInteger getNumberOfDoublesInRange(double l, double u) {
        BigInteger r = BigInteger.ZERO;
//        int i = 100000000;
//        BigInteger divisor = new BigInteger(Integer.toString(i));
        double x = l;
        while (x < u) {
            x = Math.nextUp(x);
            r = r.add(BigInteger.ONE);
            //if (r % i == 0){
//            if (r.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
//                System.out.println("" + r + " values between " + l + " and " + u);
//                System.out.println(toPlainString(value));
//            }
        }
        return r;
    }

    /**
     * 
     * @param x Number to be rounded up to the nearest int.
     * @return d rounded up to the nearest int
     */
    public static int roundUpToNearestInt(double x) {
        int r = Math_BigDecimal.roundStrippingTrailingZeros(
                new BigDecimal(x), 0, RoundingMode.UP).intValue();
        return r;
    }

    /**
     *
     * @param d The double value to return as a plain number String.
     * @return A plain number String representation of d. A plain number String
     * is not written in scientific notation, but as a plain decimal.
     */
    public static String toPlainString(double d) {
        return new BigDecimal(d).toPlainString();
    }
}
