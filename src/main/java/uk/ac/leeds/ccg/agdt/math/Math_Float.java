/*
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

public class Math_Float extends Math_Number {

    public static final String NAN = Float.toString(Float.NaN);
    public static final String POSITIVE_INFINITY = Float.toString(Float.POSITIVE_INFINITY);
    public static final String NEGATIVE_INFINITY = Float.toString(Float.NEGATIVE_INFINITY);

    /**
     * In most instances this behaves like
     * {@link java.lang.Float#parseFloat(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Float#NaN} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #NAN}), then a
     * {@link java.lang.Float#NaN} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #POSITIVE_INFINITY}), then a
     * {@link java.lang.Float#POSITIVE_INFINITY} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #NEGATIVE_INFINITY}), then a
     * {@link java.lang.Float#NEGATIVE_INFINITY} is returned.</li>
     * </ul>
     *
     * @param s The String to attempt to parse as a float. 
     * @return {@code s} parsed as a float. 
     * @throws NumberFormatException If s cannot be parse as a float.
     */
    public static float parseFloat(String s) throws NumberFormatException {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Float.NaN;
            }
            if (s.equalsIgnoreCase(NAN)) {
                return Float.NaN;
            }
            if (s.equalsIgnoreCase(POSITIVE_INFINITY)) {
                return Float.POSITIVE_INFINITY;
            }
            if (s.equalsIgnoreCase(NEGATIVE_INFINITY)) {
                return Float.NEGATIVE_INFINITY;
            }
            throw e;
        }
    }

    /**
     * For testing if s can be parsed as a float.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float.
     * @return true iff s can be represented as a float.
     */
    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
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
     * For testing if s can be parsed as a float and is precise to {@code dp}
     * decimal places.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float.
     * @param dp The number of decimal places the result must be accurate to.
     * @return true iff s can be represented as a float.
     */
    public static boolean isFloat(String s, int dp) {
        try {
            float x = Float.parseFloat(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            RoundingMode rm = RoundingMode.HALF_UP;
            BigDecimal bdsr = Math_BigDecimal.roundIfNecessary(bds,dp,rm);
            BigDecimal bddr = Math_BigDecimal.roundIfNecessary(bdd,dp,rm);
            return bdsr.compareTo(bddr) == 0;
        } catch (NumberFormatException e) {
            return isFloat(s);
        }
    }

    /**
     * For testing if s can be parsed as a float exactly. This allows no rounding to the
     * nearest float. e.g. 0.1 cannot for instance be represented as a float as
     * the nearest float greater than or equal to 0.1 is
     * 0.100000001490116119384765625 and the nearest float less than or equal to
     * 0.1 is 0.0999999940395355224609375
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float.
     * @return true iff s can be represented as a float.
     */
    public static boolean isFloatExact(String s) {
        try {
            float x = Float.parseFloat(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            return bds.compareTo(bdd) == 0;
        } catch (NumberFormatException e) {
            return isFloat(s);
        }
    }

    /**
     *
     * @param x Number to be rounded up to the nearest int.
     * @return f rounded up to the nearest int
     */
    public static int roundUpToNearestInt(float x) {
        int r = Math_BigDecimal.roundStrippingTrailingZeros(
                new BigDecimal(x), 0, RoundingMode.UP).intValue();
        return r;
    }

    /**
     * @param l The lower value in the range.
     * @param u The upper value in the range.
     * @return The total number of floats represented in the range (l, u)
     */
    public static BigInteger getNumberOfFloatsInRange(float l, float u) {
        BigInteger r = BigInteger.ZERO;
//        int i = 10000;
//        BigInteger divisor = new BigInteger(Integer.toString(i));
        float x = l;
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
     * @param x The float value to return as a plain number String.
     * @return A plain number String representation of x. A plain number String
     * is not written in scientific notation, but as a plain decimal.
     */
    public static String toPlainString(float x) {
        return new BigDecimal(Float.toString(x)).toPlainString();
    }
}
