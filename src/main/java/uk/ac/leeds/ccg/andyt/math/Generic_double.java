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

public class Generic_double
        extends Generic_Number
        implements Serializable {

    /**
     * Creates a new instance of Generic_BigDecimal
     */
    public Generic_double() {
        //super();
    }

    /**
     * For testing is s can be represented as a double (excluding
     * -Double.MAX_VALUE which is reserved for representing noDataValues).
     *
     * @param s The String to be tested as to whether it can be represented as a
     * byte excluding Double.MIN_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a Byte greater than
     * Double.MIN_VALUE which is reserved for representing noDataValues).
     */
    public static boolean isDouble(String s) {
        try {
            double d = Double.parseDouble(s);
            if (d == -Double.MAX_VALUE) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * For testing if s can be represented as a double (excluding
     * -Double.MAX_VALUE which is reserved for representing noDataValues). This
     * strictly allows no rounding to the nearest double. So 0.1 cannot for
     * instance be represented as a double as the nearest double greater than or
     * equal to 0.1 is
     * 0.10000000000000001942890293094023945741355419158935546875 and the
     * nearest double less than or equal to 0.1 is
     * 0.09999999999999999167332731531132594682276248931884765625
     *
     *
     * @param s The String to be tested as to whether it can be represented as a
     * byte excluding Double.MIN_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a Byte greater than
     * Double.MIN_VALUE which is reserved for representing noDataValues).
     */
    public static boolean isDoubleStrict(String s) {
        try {
            double d = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(d);
            if (bds.compareTo(bdd) == 0) {
                if (d == -Double.MAX_VALUE) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * The count is written to System.out after each 100000000
     *
     * @param l The lower value in the range.
     * @param u The upper value in the range.
     * @return The total number of doubles represented in (l, u)
     */
    public static BigInteger getNumberOfDoublesInRange(double l, double u) {
        BigInteger r = BigInteger.ZERO;
        //BigInteger divisor = new BigInteger("100000000");
        double v = l;
        while (v < u) {
            v = Math.nextUp(v);
            r = r.add(BigInteger.ONE);
            //if (r % 100000000 == 0){
//            if (r.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
//                System.out.println("" + r + " values between " + l + " and " + u);
//                System.out.println(toPlainString(value));
//            }
        }
        return r;
    }

    /**
     *
     * @param v
     * @return
     */
    public static int roundUpToNearestInt(double v) {
        int r = Generic_BigDecimal.roundStrippingTrailingZeros(
                new BigDecimal(v), 0, RoundingMode.UP).intValue();
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
