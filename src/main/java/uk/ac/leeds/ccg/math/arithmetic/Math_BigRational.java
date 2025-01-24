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
package uk.ac.leeds.ccg.math.arithmetic;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import uk.ac.leeds.ccg.math.number.Math_BigRationalSqrt;

/**
 * A class with additional utility methods for processing BigRational numbers.
 *
 * @author Andy Turner
 * @version 3.0
 */
public class Math_BigRational {

    /**
     * Create a new instance.
     */
    public Math_BigRational() {
    }

    /**
     * @param x A BigRational to check if it is within an oom of y;
     * @param y A BigRational to check if it is within an oom of x;
     * @param oom the order of magnitude for the precision.
     * @return {@code true} iff x and y are less than or equal to epsilon 
     * different.
     */
    public static boolean equals(BigRational x, BigRational y, int oom) {
        BigRational oomb = BigRational.TEN.pow(oom);
        return x.compareTo(y.add(oomb)) != 1 
                && x.compareTo(y.subtract(oomb)) != -1;
    }
    
    /**
     * Returns a rational number with approximately <code>this</code> value and
     * the specified precision.
     *
     * @param x the number to process.
     * @param oom the order of magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return the calculated rational number with the specified precision
     */
    public static BigRational round(BigRational x, int oom, RoundingMode rm) {
        return BigRational.valueOf(toBigDecimal(x, oom, rm));
    }
    
    /**
     * @return A String representation of {@code x} in 10 characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public static String getStringValue(BigRational x) {
        return getStringValue(x, 10);
    }

    /**
     * @param n The length of the String returned. This must be greater than or
     * equal to 10.
     * @return A String representation of {@code x} in n characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public static String getStringValue(BigRational x, int n) {
        return Math_BigDecimal.getStringValue(x.toBigDecimal());
    }

    /**
     * Returns this rational number as a float value.
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision of the conversion.
     * @return the float value
     */
    public static float toFloat(BigRational x, int oom) {
        // May return NaN!
        float r = x.getNumerator().floatValue() / x.getDenominator().floatValue();
        if (Float.isNaN(r)) {
            return Float.parseFloat(toBigDecimal(x, oom).toString());
            //return Float.valueOf(x.toBigDecimal().toString());
        }
        if (Float.isInfinite(r)) {
            return Float.parseFloat(toBigDecimal(x, oom).toString());
        }
        return r;
    }

    /**
     * Returns this rational number as a {@link BigDecimal} rounded if necessary
     * to {@code oom}.
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision of the result.
     * @param rm The RoundingMode used for rounding.
     * @return the {@link BigDecimal} value rounded to {@code oom}.
     */
    public static BigDecimal toBigDecimal(BigRational x, int oom,
            RoundingMode rm) {
        if (x.isZero()) {
            return BigDecimal.ZERO;
        }
        return Math_BigDecimal.round(Math_BigDecimal.divide(x.getNumerator(),
                x.getDenominator(), oom - 3, rm), oom, rm);
    }

    /**
     * Returns this rational number as a {@link BigDecimal} rounded if necessary
     * to {@code oom} using RoundingMode.HALF_UP.
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision of the result.
     * @return the {@link BigDecimal} value rounded to {@code oom}.
     */
    public static BigDecimal toBigDecimal(BigRational x, int oom) {
        return toBigDecimal(x, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return the common factor of two rational numbers.
     *
     * @param x One number.
     * @param y Another number.
     * @return The common factor of the two numbers x and y.
     */
    public static BigRational getCommonFactor(BigRational x, BigRational y) {
        if (x.isZero() || y.isZero()) {
            return BigRational.ZERO;
        }
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
     * @param x the number to process.
     * @return If this has a fractional part, then it returns just the whole
     * integer number part.
     */
    public static BigInteger floor(BigRational x) {
        if (x.isZero()) {
            return BigInteger.ZERO;
        }
        BigDecimal num = x.getNumerator();
        BigDecimal den = x.getDenominator();
        BigDecimal fractionNumerator = num.remainder(den);
        BigDecimal integerNumerator = num.subtract(fractionNumerator);
        BigInteger r = integerNumerator.divide(den).toBigInteger();
        if (fractionNumerator.compareTo(BigDecimal.ZERO) == -1) {
            return r.subtract(BigInteger.ONE);
        }
        return r;
    }

    /**
     * @param x the number to process.
     * @return If this has a fractional part, then it returns the whole integer
     * number greater than it.
     */
    public static BigInteger ceil(BigRational x) {
        if (x.isZero()) {
            return BigInteger.ZERO;
        }
        BigDecimal num = x.getNumerator();
        BigDecimal den = x.getDenominator();
        BigDecimal fractionNumerator = num.remainder(den);
        BigDecimal integerNumerator = num.subtract(fractionNumerator);
        BigInteger r = integerNumerator.divide(den).toBigInteger();
        if (fractionNumerator.compareTo(BigDecimal.ZERO) == 1) {
            return r.add(BigInteger.ONE);
        }
        return r;
    }

    /**
     * For calculating the cosine of x.
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x the number to process.
     * @param bi Stores Taylor series expansion terms for computational
     * efficiency.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The cosine of x.
     */
    public static BigRational cos(BigRational x, Math_BigInteger bi, int oom,
            RoundingMode rm) {
        // cosx = 1-(x^2)/(2!)+(x^4)/(4!)-(x^6)/(6!)+...
        BigRational precision = BigRational.valueOf(BigInteger.ONE, BigInteger.TEN.pow(2 - oom));
        BigRational r = BigRational.ONE;
        int factor = 2;
        BigInteger factorial;
        BigRational power;
        boolean alternator = true;
        while (true) {
            factorial = bi.factorial(factor);
            power = x.pow(factor);
            BigRational division = power.divide(factorial);
            if (division.abs().compareTo(precision) != -1) {
                if (alternator) {
                    alternator = false;
                    r = r.subtract(division);
                } else {
                    alternator = true;
                    r = r.add(division);
                }
            } else {
                break;
            }
            factor += 2;
        }
        return round(r, oom, rm);
    }

    /**
     * For calculating the sine of x.
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x the number to process.
     * @param bi Stores Taylor series expansion terms for computational
     * efficiency.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The sine of x.
     */
    public static BigRational sin(BigRational x, Math_BigInteger bi, int oom,
            RoundingMode rm) {
        // sin(x) = x − x^3/3! + x^5/5! − x^7/7! +...
        BigRational precision = BigRational.valueOf(BigInteger.ONE,
                BigInteger.TEN.pow(2 - oom));
        BigRational r = x;
        int factor = 3;
        BigInteger factorial;
        BigRational power;
        boolean alternator = true;
        while (true) {
            factorial = bi.factorial(factor);
            power = x.pow(factor);
            BigRational division = power.divide(factorial);
            if (division.abs().compareTo(precision) != -1) {
                if (alternator) {
                    alternator = false;
                    r = r.subtract(division);
                } else {
                    alternator = true;
                    r = r.add(division);
                }
            } else {
                break;
            }
            factor += 2;
        }
        return round(r, oom, rm);
    }

    /**
     * For calculating the arctangent of {@code x}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arctangent a.k.a the inverse tangent.
     */
    public static BigRational atan(BigRational x, int oom, RoundingMode rm) {
        // atan(x) = x − x^3/3 + x^5/5 − x^7/7 +...
        BigRational precision = BigRational.valueOf(BigInteger.ONE,
                BigInteger.TEN.pow(2 - oom));
        BigRational r = x;
        int factor = 3;
        BigRational power;
        boolean alternator = true;
        while (true) {
            power = x.pow(factor);
            BigRational division = power.divide(factor);
            if (division.abs().compareTo(precision) != -1) {
                if (alternator) {
                    alternator = false;
                    r = r.subtract(division);
                } else {
                    alternator = true;
                    r = r.add(division);
                }
            } else {
                break;
            }
            factor += 2;
        }
        return round(r, oom, rm);
    }

    /**
     * For calculating the arcsine of {@code x}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arcsine a.k.a the inverse sine.
     */
    public static BigRational asin(BigRational x, int oom, RoundingMode rm) {
        // Special cases
        if (x.abs().compareTo(BigRational.ONE) == 0) {
            BigRational r = BigRational.valueOf(
                    new Math_BigDecimal().getPi(oom, RoundingMode.HALF_UP),
                    Math_BigDecimal.TWO);
            if (isPositive(x)) {
                return r;
            }
            return r.negate();
        }
        if (x.compareTo(BigRational.ONE) == 0) {
            return BigRational.valueOf(
                    new Math_BigDecimal().getPi(oom, RoundingMode.HALF_UP),
                    Math_BigDecimal.TWO);
        }
        // General case
        BigRational divisor = new Math_BigRationalSqrt(
                BigRational.ONE.subtract(x.pow(2)), oom, rm)
                .getSqrt(oom, rm);
        BigRational r = x.divide(divisor);
        return atan(r, oom, rm);
    }

    private static boolean isPositive(BigRational x) {
        return x.compareTo(BigRational.ZERO) != -1;
    }

    /**
     * For calculating the arccosine of {@code this}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arccosine a.k.a the inverse cosine.
     */
    public static BigRational arccos(BigRational x, int oom, RoundingMode rm) {
        //Pi/2 - asin(oom)
        Math_BigDecimal bd = new Math_BigDecimal();
        return BigRational.valueOf(bd.getPiBy2(oom, rm)).subtract(
                asin(x, oom, rm));
    }

    /**
     * @param bD An instance for getting Pi
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode.
     * @return An approximation of Pi rounded to {@code oom} precision.
     */
    public static BigRational getPi(Math_BigDecimal bD, int oom, RoundingMode rm) {
        return BigRational.valueOf(bD.getPi(oom, rm));
    }

    /**
     * For converting degrees to radians.
     *
     * @param x the number to process.
     * @param bD An instance for getting Pi
     * @param oom The Order of Magnitude for the precision.
     * @param rm The Rounding mode.
     * @return degrees converted to radians using the given precision.
     */
    public static BigRational toRadians(BigRational x, Math_BigDecimal bD, int oom,
            RoundingMode rm) {
        BigRational pi = getPi(bD, oom, rm);
        return x.multiply(pi).divide(180);
    }

    /**
     * For converting radians to degrees.
     *
     * @param x the number to process.
     * @param bD An instance for getting Pi
     * @param oom The Order of Magnitude for the precision.
     * @param rm The Rounding mode.
     * @return radians converted to degrees using the given precision.
     */
    public static BigRational toDegrees(BigRational x, Math_BigDecimal bD, int oom,
            RoundingMode rm) {
        BigRational pi = getPi(bD, oom, rm);
        return x.multiply(180).divide(pi);
    }
}
