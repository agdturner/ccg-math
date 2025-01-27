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
package uk.ac.leeds.ccg.math.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import uk.ac.leeds.ccg.math.core.Math_Strings;

/**
 * A class for {@code double} numbers.
 *
 * @author Andy Turner
 * @version 2.0
 */
public class Math_Double {

    /**
     * The number {@code Double.POSITIVE_INFINITY} for convenience.
     */
    public static final String POSITIVE_INFINITY = Double.toString(Double.POSITIVE_INFINITY);

    /**
     * The number {@code Double.NEGATIVE_INFINITY} for convenience.
     */
    public static final String NEGATIVE_INFINITY = Double.toString(Double.NEGATIVE_INFINITY);

    /**
     * Create a new instance.
     */
    public Math_Double() {
    }

    /**
     * In most instances this behaves like
     * {@link java.lang.Double#parseDouble(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Double#NaN} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link Math_Strings#S_NAN}), then a
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
            if (s.equalsIgnoreCase(Math_Strings.S_NAN)) {
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
            if (s.equalsIgnoreCase(Math_Strings.S_NAN)) {
                return true;
            }
            if (s.equalsIgnoreCase(POSITIVE_INFINITY)) {
                return true;
            }
            return s.equalsIgnoreCase(NEGATIVE_INFINITY);
        }
    }

    /**
     * For testing if s can be parsed as a {@code double} accurate to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) {@code oom} using RoundingMode {@code rm}.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * double.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @return true iff s can be represented as a Double greater than
     * -Double.MAX_VALUE which is reserved for representing noDataValues.
     */
    public static boolean isDouble(String s, int oom) {
        try {
            double x = Double.parseDouble(s);
            BigDecimal bds = new BigDecimal(s);
            BigDecimal bdd = new BigDecimal(x);
            RoundingMode rm = RoundingMode.HALF_UP;
            BigDecimal bdsr = Math_BigDecimal.round(bds, oom, rm);
            BigDecimal bddr = Math_BigDecimal.round(bdd, oom, rm);
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
        int r = Math_BigDecimal.round(
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

    /**
     * @param d1 A double to check if it is within epsilon of d2;
     * @param d2 A double to check if it is within epsilon of d1;
     * @param epsilon Usually a small amount.
     * @return {@code true} iff d1 and d2 are less than or equal to epsilon
     * different.
     */
    public static boolean equals(double d1, double d2, double epsilon) {
        return (d1 <= d2 + epsilon && d1 >= d2 - epsilon);
        //return Math.abs(d1 - d2) < epsilon;
    }

    /**
     * Take the square root preserving the sign.
     *
     * @param d The number to be square rooted.
     * @return The square root which is negative of the positive square root for
     * a negative d.
     */
    public static double sqrt(double d) {
        double r;
        if (d < 0) {
            r = -Math.sqrt(-d);
        } else {
            r = Math.sqrt(d);
        }
        return r;
    }

    /**
     * For getting a tight tolerance value for d.
     *
     * @param d The number for which a tolerance is wanted.
     * @return The difference between the next bigger than d and next smaller
     * than d number.
     */
    public static double getTolerance(double d) {
        double rp = Math.nextAfter(d, Double.POSITIVE_INFINITY);
        double rn = Math.nextAfter(d, Double.NEGATIVE_INFINITY);
        return rp - rn;
    }
    
    /**
     * For getting a tight tolerance value for ds.
     *
     * @param ds The numbers for which a tolerance is wanted.
     * @return The difference between the next bigger than ds and next smaller
     * than ds numbers.
     */
    public static double getTolerance(double... ds) {
        double r = getTolerance(ds[0]);
        for (var d : ds) {
            r = Math.max(r, getTolerance(d));
        }
        return r;
    }
    
    
    
    /**
     * A convenience method to get the minimum of a set of double numbers 
     * ignoring any that are NaN. If all values are NaN then NaN is returned.
     * @param values
     * @return The minimum of all the values or NaN.
     */
    public static double min(double... values) {
        double r = values[0];
        boolean isNaN;
        if (Double.isNaN(r)) {
            isNaN = true;
        } else {
            isNaN = false;
        }
        for (int i = 1; i < values.length; i ++) {
            if (Double.isNaN(r)) {
                r = values[i];
            } else {
                if (!Double.isNaN(values[i])) {
                    r = Math.min(r, values[i]);
                }
            }
        }
        return r;
    }
    
    /**
     * A convenience method to get the maximum of a set of double numbers 
     * ignoring any that are NaN. If all values are NaN then NaN is returned.
     * @param values
     * @return The maximum of all the values or NaN.
     */
    public static double max(double... values) {
        double r = values[0];
        boolean isNaN;
        if (Double.isNaN(r)) {
            isNaN = true;
        } else {
            isNaN = false;
        }
        for (int i = 1; i < values.length; i ++) {
            if (Double.isNaN(r)) {
                r = values[i];
            } else {
                if (!Double.isNaN(values[i])) {
                    r = Math.max(r, values[i]);
                }
            }
        }
        return r;
    }
}
