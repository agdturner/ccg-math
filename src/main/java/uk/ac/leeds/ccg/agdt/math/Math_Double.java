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
package uk.ac.leeds.ccg.agdt.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Math_Double extends Math_Number {

    public static final String POSITIVE_INFINITY = Double.toString(Double.POSITIVE_INFINITY);
    public static final String NEGATIVE_INFINITY = Double.toString(Double.NEGATIVE_INFINITY);

    /**
     * In most instances this behaves like
     * {@link java.lang.Double#parseDouble(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Double#NaN} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #NAN}), then a
     * {@link java.lang.Double#NaN} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #POSITIVE_INFINITY}), then a
     * {@link java.lang.Double#POSITIVE_INFINITY} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #NEGATIVE_INFINITY}), then a
     * {@link java.lang.Double#NEGATIVE_INFINITY} is returned.</li>
     * </ul>
     *
     * @param s The String to attempt to parse as a double. 
     * @return {@code s} parsed as a double. 
     * @throws NumberFormatException If s cannot be parse as a double.
     */
    public static double parseDouble(String s) throws NumberFormatException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Double.NaN;
            }
            if (s.equalsIgnoreCase(NAN)) {
                return Double.NaN;
            }
            if (s.equalsIgnoreCase(POSITIVE_INFINITY)) {
                return Double.POSITIVE_INFINITY;
            }
            if (s.equalsIgnoreCase(NEGATIVE_INFINITY)) {
                return Double.NEGATIVE_INFINITY;
            }
            throw e;
        }
    }

    /**
     * For testing if s can be parsed as a double.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double.
     * @return true iff s can be represented as a double.
     */
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return true;
            }
            if (s.equalsIgnoreCase(NAN)) {
                return true;
            }
            if (s.equalsIgnoreCase(POSITIVE_INFINITY)) {
                return true;
            }
            return s.equalsIgnoreCase(NEGATIVE_INFINITY);
        }
    }

    /**
     * For testing if s can be parsed as a double and is precise to {@code dp}
     * decimal places using {@code rm} RoundingMode if necessary to round the
     * parsed value.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double.
     * @param dp The number of decimal places the result must be accurate to.
     * @return true iff s can be represented as a Double greater than
     * -Double.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isDouble(String s, int dp) {
        try {
            double x = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            RoundingMode rm = RoundingMode.HALF_UP;
            BigDecimal bdsr = Math_BigDecimal.roundIfNecessary(bds, dp, rm);
            BigDecimal bddr = Math_BigDecimal.roundIfNecessary(bdd, dp, rm);
            return bdsr.compareTo(bddr) == 0;
        } catch (NumberFormatException e) {
            return isDouble(s);
        }
    }

    /**
     * For testing if s can be parsed as a double exactly. This allows no
     * rounding to the nearest double. e.g. 0.1 cannot for instance be
     * represented as a double as the nearest double greater than or equal to
     * 0.1 is 0.10000000000000001942890293094023945741355419158935546875 and the
     * nearest double less than or equal to 0.1 is
     * 0.09999999999999999167332731531132594682276248931884765625
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double.
     * @return true iff s can be represented as a double.
     */
    public static boolean isDoubleExact(String s) {
        try {
            double x = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            return bds.compareTo(bdd) == 0;
        } catch (NumberFormatException e) {
            return isDouble(s);
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
