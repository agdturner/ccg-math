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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * This is a class to help with the storage and multiplication of square roots.
 * Mathematically the square root of 2 multiplied by the square root of 2 is 2.
 * This helps ensure this is the case. It has application for example in
 * calculating the area of surfaces, such as the area of a square that has the
 * length of a side equal to the square root of 2.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_BigRationalSqrt {

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
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigRational x) {
        this.x = x;
        sqrtx = getSqrtRational(x);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x + ", sqrtx="
                + sqrtx + ")";
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
    public BigDecimal getSqrtApprox(int mps) {
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
                    sqrtxapprox = x.toBigDecimal().sqrt(new MathContext(mps));
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
     * @return {@code y} multiplied by {@link # x2} if this can be expressed
     * exactly as a BigRational and {@code null} otherwise.
     */
    public BigRational multiply(Math_BigRationalSqrt y) {
        int c = y.x.compareTo(x);
        switch (c) {
            case 0:
                return x;
            case 1: {
                if (sqrtx != null) {
                    if (y.sqrtx != null) {
                        return sqrtx.multiply(y.sqrtx);
                    }
                } else {
                    BigRational d = y.x.divide(x);
                    if (d.isInteger()) {
                        BigRational sqrtd = getSqrtRational(d);
                        if (sqrtd != null) {
                            return sqrtd.multiply(x);
                        }
                    }
                }
                break;
            }
            default: {
                if (sqrtx != null) {
                    if (y.sqrtx != null) {
                        return sqrtx.multiply(y.sqrtx);
                    }
                } else {
                    BigRational d = x.divide(y.x);
                    if (d.isInteger()) {
                        BigRational sqrtd = getSqrtRational(d);
                        if (sqrtd != null) {
                            return sqrtd.multiply(y.x);
                        }
                    }
                }
                break;
            }
        }
        return null;
    }
}
