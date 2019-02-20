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

public class Math_float extends Math_Number {

    /**
     * For testing if s can be parsed as a float. If the result is equal to
     * -Float.MAX_VALUE then this will return false as -Float.MAX_VALUE is
     * reserved for representing noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float excluding -Float.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a Float greater than
     * -Float.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isFloat(String s) {
        try {
            float x = Float.parseFloat(s);
            return x != -Float.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * For testing if s can be parsed as a float and is precise to {@code dp}
     * decimal places using {@code rm} RoundingMode if necessary to round the
     * parsed value.If the result is equal to -Float.MAX_VALUE then this will
     * return false as -Float.MAX_VALUE is reserved for representing
     * noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float excluding -Float.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @param dp The number of decimal places the result must be accurate to.
     * @param rm The RoundingMode used to round.
     * @return true iff s can be represented as a Float greater than
     * -Float.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isFloat(String s, int dp, RoundingMode rm) {
        try {
            float x = Float.parseFloat(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            BigDecimal bdsr = Math_BigDecimal.roundIfNecessary(bds,dp,rm);
            BigDecimal bddr = Math_BigDecimal.roundIfNecessary(bdd,dp,rm);
//            System.out.println(bdsr.toPlainString());
//            System.out.println(bddr.toPlainString());
            if (bdsr.compareTo(bddr) == 0) {
                return x != -Float.MAX_VALUE;
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
     * For testing if s can be parsed as a float exactly. If the result is equal
     * to -Float.MAX_VALUE then this will return false as -Float.MAX_VALUE is
     * reserved for representing noDataValues. This allows no rounding to the
     * nearest float. e.g. 0.1 cannot for instance be represented as a float as
     * the nearest float greater than or equal to 0.1 is
     * 0.100000001490116119384765625 and the nearest float less than or equal to
     * 0.1 is 0.0999999940395355224609375
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float excluding -Float.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a float greater than
     * -Float.MAX_VALUE which is reserved for representing noDataValue).
     */
    public static boolean isFloatExact(String s) {
        try {
            float x = Float.parseFloat(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            if (bds.compareTo(bdd) == 0) {
                return x != -Float.MAX_VALUE;
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
