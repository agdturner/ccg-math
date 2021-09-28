/*
 * Copyright 2021 Centre for Computational Geography, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.math;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This is mostly a wrapper around BigRational essentially so it is
 * serializable. A few extra utility methods are included.
 *
 * @author Andy Turner
 */
public class Math_BigRational extends Math_Number implements Comparable<Math_BigRational> {

    private static final long serialVersionUID = 1L;

    /**
     * Zero
     */
    public static final Math_BigRational ZERO = new Math_BigRational(BigRational.ZERO);

    /**
     * One
     */
    public static final Math_BigRational ONE = new Math_BigRational(BigRational.ONE);

    /**
     * Serializable numerator of the rational number.
     */
    public final BigDecimal numerator;

    /**
     * Serializable denominator of the rational number.
     */
    public final BigDecimal denominator;

    /**
     * Create a new {@code null} instance.
     */
    public Math_BigRational() {
        numerator = null;
        denominator = null;
    }

    /**
     * Create a new instance.
     *
     * @param x The number to wrap.
     */
    public Math_BigRational(BigRational x) {
        this.numerator = x.getNumerator();
        this.denominator = x.getDenominator();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + "(" + getX().toIntegerRationalString() + ")";
    }

    /**
     * @return A Float representation of this.
     */
    public float toFloat() {
        return toFloat(getX());
    }

    /**
     * @return A Float representation of this.
     */
    public static float toFloat(BigRational x) {
        // Sometimes this return NaN!
        float r = x.toFloat();
        if (Float.isNaN(r)) {
            return Float.parseFloat(x.toBigDecimal().toString());
            //return Float.valueOf(x.toBigDecimal().toString());
        }
        return r;
    }

    /**
     * Calculate and return the common factor of two rational numbers.
     *
     * @param x One number.
     * @param y Another number.
     * @return The common factor of the two numbers x and y.
     */
    public static BigRational getCommonFactor(BigRational x, BigRational y) {
        BigRational xr = x.reduce();
        BigRational yr = y.reduce();
        BigInteger xrn = xr.getNumeratorBigInteger();
        BigInteger yrn = yr.getNumeratorBigInteger();
        BigInteger xrd = xr.getDenominatorBigInteger();
        BigInteger yrd = yr.getDenominatorBigInteger();
        BigInteger gcdn = xrn.gcd(yrn);
        BigInteger gcdd = xrd.gcd(yrd);
        return BigRational.valueOf(gcdn, gcdd);
    }

    /**
     * Always rounds down.
     *
     * @param x The value to round.
     * @param oom The order of magnitude to round to.
     * @return A new value which is x rounded down to {@code oom}
     */
    public static BigRational round(BigRational x, int oom) {
        BigRational shift = BigRational.TEN.pow(oom);
        return x.divide(shift).integerPart().multiply(shift);
    }

    /**
     * Always rounds down.
     *
     * @param x The value to round.
     * @param oom The order of magnitude to round to.
     * @return A new value which is x rounded down to {@code oom}
     */
    public static BigDecimal roundToBD(BigRational x, int oom) {
        BigRational shift = BigRational.TEN.pow(oom);
        return x.divide(shift).integerPart().multiply(shift).toBigDecimal();
    }

    /**
     * A convenience method for finding the minimum of this and x.
     *
     * @param x A number to compare with this to find out which is the minimum.
     * @return The minimum of this and x.
     */
    public Math_BigRational min(Math_BigRational x) {
        if (this.compareTo(x) == -1) {
            return this;
        } else {
            return x;
        }
    }

    /**
     * A convenience method for finding the minimum of this.getX() and x.
     *
     * @param x A number to compare with this to find out which is the minimum.
     * @return The minimum of this and x.
     */
    public Math_BigRational min(BigRational x) {
        if (this.getX().compareTo(x) == -1) {
            return this;
        } else {
            return new Math_BigRational(x);
        }
    }

    /**
     * A convenience method for finding the minimum of two BigRational numbers.
     *
     * @param x A number to compare with y to find out which is the minimum.
     * @param y A number to compare with x to find out which is the minimum.
     * @return The minimum of x and y.
     */
    public static BigRational min(BigRational x, BigRational y) {
        if (x.compareTo(y) == -1) {
            return x;
        } else {
            return y;
        }
    }

    /**
     * A convenience method for finding the maximum of this and x.
     *
     * @param x A number to compare with this to find out which is the maximum.
     * @return The maximum of this and x.
     */
    public Math_BigRational max(Math_BigRational x) {
        if (this.compareTo(x) == 1) {
            return this;
        } else {
            return x;
        }
    }

    /**
     * A convenience method for finding the maximum of this.getX() and x.
     *
     * @param x A number to compare with this to find out which is the maximum.
     * @return The maximum of this and x.
     */
    public Math_BigRational max(BigRational x) {
        if (this.getX().compareTo(x) == 1) {
            return this;
        } else {
            return new Math_BigRational(x);
        }
    }

    /**
     * A convenience method for finding the maximum of two BigRational numbers.
     *
     * @param x A number to compare with y to find out which is the maximum.
     * @param y A number to compare with x to find out which is the maximum.
     * @return The maximum of x and y.
     */
    public static BigRational max(BigRational x, BigRational y) {
        if (x.compareTo(y) == 1) {
            return x;
        } else {
            return y;
        }
    }
//    /**
//     * A convenience method to construct from.
//     * @param x The number to wrap.
//     * @return x represented as Math_BigRational.
//     */
//    public static Math_BigRational valueOf(BigDecimal x) {
//        return new Math_BigRational(BigRational.valueOf(x));
//    }
//
//    public static Math_BigRational valueOf(BigInteger x) {
//        return new Math_BigRational(BigRational.valueOf(x));
//    }
//
//    public static Math_BigRational valueOf(String x) {
//        return new Math_BigRational(BigRational.valueOf(x));
//    }
//
//    public static Math_BigRational valueOf(double x) {
//        return new Math_BigRational(BigRational.valueOf(x));
//    }
//
//    public static Math_BigRational valueOf(int x) {
//        return new Math_BigRational(BigRational.valueOf(x));
//    }
//
//    public static Math_BigRational valueOf(long x) {
//        return new Math_BigRational(BigRational.valueOf(BigInteger.valueOf(x)));
//    }
//
//    public static Math_BigRational valueOf(BigDecimal numerator, BigDecimal denominator) {
//        return new Math_BigRational(BigRational.valueOf(numerator, denominator));
//    }
//
//    public static Math_BigRational valueOf(BigInteger numerator, BigInteger denominator) {
//        return new Math_BigRational(BigRational.valueOf(numerator, denominator));
//    }
//
//    public static Math_BigRational valueOf(int numerator, int denominator) {
//        return new Math_BigRational(BigRational.valueOf(numerator, denominator));
//    }

    /**
     * @return The value of the BigRational this is wrapping.
     */
    public BigRational getX() {
        return BigRational.valueOf(numerator, denominator);
    }

    @Override
    public int compareTo(Math_BigRational o) {
        return this.getX().compareTo(o.getX());
    }

}
