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
        BigRational rp;
        BigRational r0;
        BigRational r = BigRational.ONE;
        BigRational x2 = x.pow(2);
        BigRational power = x.pow(2);
        int factor = 2;
        BigInteger factorial = BigInteger.valueOf(2);
        boolean alternator = true;
        while (true) {
//            factorial = bi.factorial(factor);
//            power = x.pow(factor);
//            BigRational division = power.divide(factorial);
//            if (division.abs().compareTo(precision) != -1) {
//                if (alternator) {
//                    alternator = false;
//                    r = r.subtract(division);
//                } else {
//                    alternator = true;
//                    r = r.add(division);
//                }
//            } else {
//                break;
//            }
//            factor += 2;
            rp = power.divide(factorial);
            if (alternator) {
                alternator = false;
                r0 = r.subtract(rp);
            } else {
                alternator = true;
                r0 = r.add(rp);
            }
            BigRational diff = r.subtract(r0);
            //if (i % 100000 == 0) {
            //    System.out.println(r0.toPlainString() + ", diff = " + diff.toPlainString());
            //}            
            if (diff.abs().compareTo(precision) == -1) {
                break;
            }
            //r = r0;
            r = Math_BigRational.round(r0, oom - 2, rm);
            factor += 2;
            factorial = factorial.multiply(BigInteger.valueOf(factor * (factor - 1)));
            //power = power.multiply(x2);
            power = Math_BigRational.round(power.multiply(x2), oom - 2, rm);
            
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
        BigRational rp;
        BigRational r0;
        BigRational r = x;
        BigRational x2 = x.pow(2);
        BigRational power = x.pow(3);
        int factor = 3;
        BigInteger factorial = BigInteger.valueOf(6);
        boolean alternator = true;
        while (true) {
//            BigRational division = power.divide(factorial);
//            if (division.abs().compareTo(precision) != -1) {
//                if (alternator) {
//                    alternator = false;
//                    r = r.subtract(division);
//                } else {
//                    alternator = true;
//                    r = r.add(division);
//                }
//            } else {
//                break;
//            }
//            factor += 2;
            rp = power.divide(factorial);
            if (alternator) {
                alternator = false;
                r0 = r.subtract(rp);
            } else {
                alternator = true;
                r0 = r.add(rp);
            }
            BigRational diff = r.subtract(r0);
            //if (i % 100000 == 0) {
            //    System.out.println(r0.toPlainString() + ", diff = " + diff.toPlainString());
            //}            
            if (diff.abs().compareTo(precision) == -1) {
                break;
            }
            //r = r0;
            r = Math_BigRational.round(r0, oom - 2, rm);
            factor += 2;
            factorial = factorial.multiply(BigInteger.valueOf(factor * (factor - 1)));
            //power = power.multiply(x2);
            power = Math_BigRational.round(power.multiply(x2), oom - 2, rm);
        }
        return round(r, oom, rm);
    }

    /**
     * For calculating the tangent of x.
     * https://en.wikipedia.org/wiki/Trigonometric_functions#tangent
     *
     * @param x the number to process.
     * @param bi A Math_BigInteger instance. efficiency.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The sine of x.
     */
    public static BigRational tan(BigRational x, Math_BigInteger bi, int oom,
            RoundingMode rm) {
        BigRational sin = sin(x, bi, oom - 2, rm);
        BigRational cos = cos(x, bi, oom - 2, rm);
        return round(sin.divide(cos), oom, rm);
    }

    /**
     * For calculating the arctangent of {@code x}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions (There is a
     * faster converging formula discovered by Euler that should be used here
     * instead of using the Taylor series!)
     *
     * @param x the number to process.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arctangent a.k.a the inverse tangent.
     */
    public static BigRational atan(BigRational x, int oom, RoundingMode rm) {
        // atan(x) = x − x^3/3 + x^5/5 − x^7/7 +...
        BigRational precision = BigRational.valueOf(BigInteger.ONE,
                BigInteger.TEN.pow(1 - oom));
        BigRational rp;
        BigRational r0;
        BigRational r = x;
        int factor = 3;
        BigRational x2 = x.pow(2);
        BigRational power = x.pow(3);
        boolean alternator = true;
        int i = 0;
        while (true) {
            i ++;
            rp = power.divide(factor);
            if (alternator) {
                alternator = false;
                r0 = r.subtract(rp);
            } else {
                alternator = true;
                r0 = r.add(rp);
            }
            BigRational diff = r.subtract(r0);
            //if (i % 100000 == 0) {
            //    System.out.println(r0.toPlainString() + ", diff = " + diff.toPlainString());
            //}            
            if (diff.abs().compareTo(precision) == -1) {
                break;
            }
            //r = r0;
            r = Math_BigRational.round(r0, oom - 2, rm);
            factor += 2;
            //power = power.multiply(x2);
            power = Math_BigRational.round(power.multiply(x2), oom - 2, rm);
        }
        return round(r, oom, rm);
    }

//    /**
//     * This has issues!
//     * For calculating the arctangent of {@code x}.
//     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions (This uses
//     * a formula discovered by Euler that should converge faster than that using
//     * a Taylor series.)
//     *
//     * @param x the number to process.
//     * @param oom The Order of Magnitude for the precision.
//     * @param rm The RoundingMode if rounding is necessary.
//     * @return The arctangent a.k.a the inverse tangent.
//     */
//    public static BigRational atan(BigRational x, Math_BigInteger bi, int oom, RoundingMode rm) {
//        if (x.compareTo(BigRational.ZERO) == -1) {
//            return atan(x.negate(), bi, oom, rm).negate();
//        }
//        BigRational precision = BigRational.valueOf(BigInteger.ONE,
//                BigInteger.TEN.pow(2 - oom));
//        int n = 0;
//        BigRational x2 = x.pow(2);
//        BigRational r0 = getAtanPart(x, x2, bi, n);
//        n = 1;
//        BigRational r1 = getAtanPart(x, x2, bi, n);
//        BigRational r = r0;
//        while (true) {
//            r = r.add(r1);
//            if (n % 100 == 0) {
//                System.out.println(r.toPlainString());
//            }
//            BigRational diff = r0.subtract(r1);
//            if (diff.abs().compareTo(precision) != -1) {
//                r0 = r1;
//                n++;
//                r1 = getAtanPart(x, x2, bi, n);
//            } else {
//                break;
//            }
//        }
//        return round(r, oom, rm);
//    }
//
//    private static BigRational getAtanPart(BigRational x, BigRational x2, Math_BigInteger bi, int n) {
//        int n2p1 = (2 * n) + 1;
//        BigInteger f_n = bi.factorial(n);
//        BigInteger f_2np1 = bi.factorial(n2p1);
//        return (((BigRational.TWO.pow(2 * n)).multiply(f_n.pow(2)))
//                .divide(f_2np1)).multiply((x.pow(n2p1)).divide(
//                (BigRational.ONE.add(x2))).pow(n + 1));
//    }

    /**
     * For calculating the angle between the positive x-axis and the ray from
     * the origin to point (x, y) in the Cartesian plane.
     * https://en.wikipedia.org/wiki/Atan2
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param bd The Math_BigDecimal used to approximate Pi.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arctangent a.k.a the inverse tangent.
     */
    public static BigRational atan2(BigRational x, BigRational y,
            Math_BigDecimal bd, int oom, RoundingMode rm) {
        // atan(x) = x − x^3/3 + x^5/5 − x^7/7 +...
        BigRational atan = atan(x.divide(y), oom - 1, rm);
        //BigRational atan = atan(x.divide(y), bd.bi, oom - 1, rm);
        int xc0 = x.compareTo(BigRational.ZERO);
        if (xc0 == 1) {
            return Math_BigRational.round(atan, oom, rm);
        }
        int yc0 = y.compareTo(BigRational.ZERO);
        BigRational pi = BigRational.valueOf(bd.getPi(oom - 1, rm));
        switch (xc0) {
            case -1 -> {
                if (yc0 != -1) {
                    return Math_BigRational.round(atan.add(pi), oom, rm);
                } else {
                    return Math_BigRational.round(atan.subtract(pi), oom, rm);
                }
            }
            default -> {
                BigRational hpi = pi.divide(2);
                switch (yc0) {
                    case 1 -> {
                        return Math_BigRational.round(atan.add(hpi), oom, rm);
                    }
                    case -1 -> {
                        return Math_BigRational.round(atan.subtract(hpi), oom, rm);
                    }
                    default ->
                        throw new RuntimeException(
                                "atan2 is undefined as x=y=0");
                }
            }
        }
    }

    /**
     * For calculating the arcsine of {@code x}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the number to process.
     * @param bd The Math_BigDecimal used to approximate Pi.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arcsine a.k.a the inverse sine.
     */
    public static BigRational asin(BigRational x, Math_BigDecimal bd, int oom,
            RoundingMode rm) {
        // Special cases
        if (x.abs().compareTo(BigRational.ONE) == 0) {
            BigRational r = BigRational.valueOf(bd.getPi(oom,
                    RoundingMode.HALF_UP), Math_BigDecimal.TWO);
            if (isPositive(x)) {
                return r;
            }
            return r.negate();
        }
        if (x.compareTo(BigRational.ONE) == 0) {
            return BigRational.valueOf(bd.getPi(oom, RoundingMode.HALF_UP),
                    Math_BigDecimal.TWO);
        }
        // General case
        BigRational divisor = new Math_BigRationalSqrt(
                BigRational.ONE.subtract(x.pow(2)), oom, rm)
                .getSqrt(oom, rm);
        BigRational r = x.divide(divisor);
        return atan(r, oom, rm);
        //return atan(r, bd.bi, oom, rm);
    }

    private static boolean isPositive(BigRational x) {
        return x.compareTo(BigRational.ZERO) != -1;
    }

    /**
     * For calculating the arccosine of {@code this}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the number to process.
     * @param bd The Math_BigDecimal used to approximate Pi.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode if rounding is necessary.
     * @return The arccosine a.k.a the inverse cosine.
     */
    public static BigRational acos(BigRational x, Math_BigDecimal bd, int oom,
            RoundingMode rm) {
        //Pi/2 - asin(oom)
        return BigRational.valueOf(bd.getPiBy2(oom, rm)).subtract(
                asin(x, bd, oom, rm));
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
