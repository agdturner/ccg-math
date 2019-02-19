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

public class Generic_float extends Generic_Number implements Serializable {

    /**
     * Creates a new instance of Generic_BigDecimal
     */
    public Generic_float() {
        //super();
    }

    /**
     * For testing is s can be parsed as a float. If the result is equal to
     * -Float.MAX_VALUE then this will return false as -Float.MAX_VALUE is
     * reserved for representing noDataValues).
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float excluding -Float.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a Float greater than
     * -Float.MAX_VALUE which is reserved for representing noDataValues).
     */
    public static boolean isFloat(String s) {
        try {
            float f = Float.parseFloat(s);
            return f != -Float.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * For testing if s can be parsed as a float exactly. If the result is equal
     * to -Float.MAX_VALUE then this will return false as -Float.MAX_VALUE is
     * reserved for representing noDataValues). This allows no rounding to the
     * nearest float. e.g. 0.1 cannot for instance be represented as a float
     * as the nearest float greater than or equal to 0.1 is
     * 0.10000000000000001942890293094023945741355419158935546875 and the
     * nearest float less than or equal to 0.1 is
     * 0.09999999999999999167332731531132594682276248931884765625
     *
     * @param s The String to be tested as to whether it can be represented as a
     * float excluding -Float.MAX_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a float greater than
     * -Float.MAX_VALUE which is reserved for representing noDataValues).
     */
    public static boolean isFloatExact(String s) {
        try {
            float d = Float.parseFloat(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(d);
            if (bds.compareTo(bdd) == 0) {
                return d != -Float.MAX_VALUE;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     *
     * @param f Number to be rounded up to the nearest int.
     * @return f rounded up to the nearest int
     */
    public static int roundUpToNearestInt(float f) {
        int r = Generic_BigDecimal.roundStrippingTrailingZeros(
                new BigDecimal(f), 0, RoundingMode.UP).intValue();
        return r;
    }
    
    /**
     * @param l The lower value in the range.
     * @param u The upper value in the range.
     * @return The total number of floats represented in the range (l, u)
     */
    public static BigInteger getNumberOfFloatsInRange(float l, float u) {
        BigInteger r = BigInteger.ZERO;
        //BigInteger divisor = new BigInteger("100000000");
        float f = l;
        while (f < u) {
            f = Math.nextUp(f);
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
     * @param f The float value to return as a plain number String.
     * @return A plain number String representation of d. A plain number String
     * is not written in scientific notation, but as a plain decimal.
     */
    public static String toPlainString(float f) {
        return new BigDecimal(Float.toString(f)).toPlainString();
    }
}
