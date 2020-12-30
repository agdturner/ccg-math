/*
 * Copyright 2020 Centre for Computational Geography, University of Leeds.
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Objects;

/**
 * This is a class to help with the storage and arithmetic of numbers that are
 * square roots. Many square roots are irrational and so it is best not to
 * compute them very precisely unless needed. Often calculations can be
 * simplified without needing to calculate terms. For instance: the square root
 * of 2 ({@code sqrt(2)}) multiplied by {@code sqrt(2)} is {@code 2}; and
 * {@code sqrt(2)} divided by {@code sqrt(2)} is {@code 1}. In the case of
 * multiplication, this has application for example in calculating the area of
 * surfaces, such as the area of a square that has the length of a side equal to
 * {@code sqrt(2)}.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_BigRationalSqrt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The square of a BigRational
     */
    public final BigRational x;

    /**
     * If the square root of x can be stored exactly as a BigRational then this
     * stores it, otherwise it is {@code null}.
     */
    public final BigRational sqrtx;

    /**
     * Stored the approximate square root of x with a minimum precision scale of
     * {@link #minimumPrecisionScale}.
     */
    public BigDecimal sqrtxapprox;

    /**
     * Stores the minimumPrecisionScale used for the approximately calculating
     * {@link #sqrtxapprox}.
     */
    public int minimumPrecisionScale;

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigRational x) {
        this.x = x;
        sqrtx = getSqrtRational(x);
    }

    /**
     * No check is performed to test that {@code sqrt} is indeed what would be
     * returned from
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input. This is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if it
     * is known what the square root of {@code x} is.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx The square root of {@code x} or {@code null} if the square
     * root of {@code x} is irrational.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx) {
        this.x = x;
        this.sqrtx = sqrtx;
    }

    /**
     * No check is performed to test that {@code sqrt} is indeed what would be
     * returned from
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input. This is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if it
     * is known what the square root of {@code x} is.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx The square root of {@code x} or {@code null} if the square
     * root of {@code x} is irrational.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx,
            BigDecimal sqrtxapprox, int minimumPrecisionScale) {
        this.x = x;
        this.sqrtx = sqrtx;
        this.sqrtxapprox = sqrtxapprox;
        this.minimumPrecisionScale = minimumPrecisionScale;
    }

    /**
     * Creates a copy of {@code i}.
     */
    public Math_BigRationalSqrt(Math_BigRationalSqrt i) {
        this.x = i.x;
        this.sqrtx = i.sqrtx;
        this.minimumPrecisionScale = i.minimumPrecisionScale;
        this.sqrtxapprox = i.sqrtxapprox;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x + ", sqrtx="
                + sqrtx + ", sqrtxapprox=" + sqrtxapprox 
                + ", minimumPrecisionScale=" + minimumPrecisionScale + ")";
    }

    /**
     * @param x The number to return the square root of (it the result is
     * rational).
     * @return The square root of x if that square root is rational and
     * {@code null} otherwise.
     */
    public static BigRational getSqrtRational(BigRational x) {
        BigInteger[] numden = getNumeratorAndDenominator(x);
        BigInteger nums = Math_BigInteger.getPerfectSquareRoot(numden[0]);
        if (nums != null) {
            BigInteger dens = Math_BigInteger.getPerfectSquareRoot(numden[1]);
            if (dens != null) {
                return BigRational.valueOf(nums).divide(BigRational.valueOf(dens));
            }
        }
        return null;
    }

    /**
     * @param mps minimum precision scale for approximating the result.
     * @return The square root of x approximated as a BigDecimal.
     */
    public BigDecimal toBigDecimal(int mps) {
        if (sqrtx == null) {
            if (sqrtxapprox == null) {
                minimumPrecisionScale = mps;
                // Not sure if new MathContext(mps + 4) is correct.
                sqrtxapprox = x.toBigDecimal(new MathContext(mps + 4))
                        .sqrt(new MathContext(mps));
            } else {
                if (minimumPrecisionScale < mps) {
                    minimumPrecisionScale = mps;
                    sqrtxapprox = x.toBigDecimal().sqrt(new MathContext(mps));
                }
            }
        } else {
            if (sqrtxapprox == null) {
                minimumPrecisionScale = mps;
                sqrtxapprox = sqrtx.toBigDecimal(new MathContext(mps));
            } else {
                if (minimumPrecisionScale < mps) {
                    minimumPrecisionScale = mps;
                    sqrtxapprox = sqrtx.toBigDecimal(new MathContext(mps));
                }
            }
        }
        return sqrtxapprox;
    }
    
    /**
     * @param x The value for which the numerator and denominator are returned.
     * @return The numerator and denominator of {@code x}
     */
    public static BigInteger[] getNumeratorAndDenominator(BigRational x) {
        BigInteger[] r = new BigInteger[2];
        r[0] = x.getNumeratorBigInteger();
        r[1] = x.getDenominatorBigInteger();
        if (Math_BigInteger.isDivisibleBy(r[0], r[1])) {
            r[0] = r[0].divide(r[1]);
            r[1] = BigInteger.ONE;
        }
        return r;
    }

    /**
     * @param y The other number to multiply.
     * @return {@code y.x} multiplied by {@link #x} if this can be expressed
     * exactly as a {@code BigRational} and {@code null} otherwise.
     */
    public Math_BigRationalSqrt multiply(Math_BigRationalSqrt y) {
        int c = y.x.compareTo(x);
        if (y.x.compareTo(x) == 0) {
            return new Math_BigRationalSqrt(x.multiply(y.x), x);
        } else {
            return new Math_BigRationalSqrt(x.multiply(y.x));
        }
    }

//    /**
//     * @param y The other number to multiply.
//     * @return {@code y.x} multiplied by {@link #x} if this can be expressed
//     * exactly as a {@code BigRational} and {@code null} otherwise.
//     */
//    public BigRational multiply(Math_BigRationalSqrt y) {
//        int c = y.x.compareTo(x);
//        switch (c) {
//            case 0:
//                return x;
//            case 1: {
//                if (sqrtx != null) {
//                    if (y.sqrtx != null) {
//                        return sqrtx.multiply(y.sqrtx);
//                    }
//                } else {
//                    BigRational d = y.x.divide(x);
//                    if (d.isInteger()) {
//                        BigRational sqrtd = getSqrtRational(d);
//                        if (sqrtd != null) {
//                            return sqrtd.multiply(x);
//                        }
//                    }
//                }
//                break;
//            }
//            default: {
//                if (sqrtx != null) {
//                    if (y.sqrtx != null) {
//                        return sqrtx.multiply(y.sqrtx);
//                    }
//                } else {
//                    BigRational d = x.divide(y.x);
//                    if (d.isInteger()) {
//                        BigRational sqrtd = getSqrtRational(d);
//                        if (sqrtd != null) {
//                            return sqrtd.multiply(y.x);
//                        }
//                    }
//                }
//                break;
//            }
//        }
//        return null;
//    }
    /**
     * @param y The divisor.
     * @return {@code y.x} multiplied by {@link #x} if this can be expressed
     * exactly as a {@code BigRational} and {@code null} otherwise.
     */
    public Math_BigRationalSqrt divide(Math_BigRationalSqrt y) {
        int c = y.x.compareTo(x);
        if (y.x.compareTo(x) == 0) {
            return new Math_BigRationalSqrt(BigRational.ONE, BigRational.ONE);
        } else {
            return new Math_BigRationalSqrt(x.divide(y.x));
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Math_BigRationalSqrt) {
            return equals((Math_BigRationalSqrt) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.x);
        return hash;
    }

    public boolean equals(Math_BigRationalSqrt x) {
        return x.x.compareTo(this.x) == 0;
    }
}
