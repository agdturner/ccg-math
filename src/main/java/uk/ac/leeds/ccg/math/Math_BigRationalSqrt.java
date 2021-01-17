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
 * compute them to a precision unless necessary. Sometimes calculations can be
 * simplified without needing to calculate component terms. For instance: the
 * square root of 2 ({@code sqrt(2)}) multiplied by {@code sqrt(2)} is
 * {@code 2}; and {@code sqrt(2)} divided by {@code sqrt(2)} is {@code 1}. This
 * has application in geometry for calculating distances, areas and volumes.
 *
 * @author Andy Turner
 * @version 1.1.0
 */
public class Math_BigRationalSqrt implements Serializable, Comparable<Math_BigRationalSqrt> {

    private static final long serialVersionUID = 1L;

    /**
     * ZERO
     */
    public static final Math_BigRationalSqrt ZERO = new Math_BigRationalSqrt(0);

    /**
     * ONE
     */
    public static final Math_BigRationalSqrt ONE = new Math_BigRationalSqrt(1);

    /**
     * The number for which {@code this} is the square root representation.
     */
    public final BigRational x;

    /**
     * Stores the square root of {@link #x} if this can be stored exactly as a
     * BigRational, otherwise it is {@code null}.
     */
    public final BigRational sqrtx;

    /**
     * Stores the approximate square root of {@link #x} with a minimum precision
     * scale of {@link #mps}.
     */
    public BigDecimal sqrtxapprox;

    /**
     * Stores the minimum precision scale used for the approximate calculation
     * of {@link #sqrtxapprox}.
     */
    public int mps;

    /**
     * Stores the MathContext of the minimum precision scale used for the
     * approximate calculation of {@link #sqrtxapprox}.
     */
    public MathContext mpsmc;

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
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigInteger x) {
        this.x = BigRational.valueOf(x);
        sqrtx = getSqrtRational(this.x);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(long x) {
        this.x = BigRational.valueOf(x);
        sqrtx = getSqrtRational(this.x);
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed what would be
     * returned from
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} input. This is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if the
     * square root of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx) {
        this.x = x;
        this.sqrtx = sqrtx;
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed what would be
     * returned from
     * {@link #getSqrtRational(ch.obermuhlner.math.big.BigRational)} with
     * {@code x} as input. This is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if the
     * square root of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     * @param sqrtxapprox What {@link #sqrtxapprox} is set to.
     * @param mps What {@link #mps} is set to. This should be the minimum
     * precision scale for the calculation of {@code sqrtxapprox}.
     * @param mpsmc What {@link #mpsmc} is set to. This should be the
     * MathContext for {@link #mps}.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx,
            BigDecimal sqrtxapprox, int mps, MathContext mpsmc) {
        this.x = x;
        this.sqrtx = sqrtx;
        this.sqrtxapprox = sqrtxapprox;
        this.mps = mps;
        this.mpsmc = mpsmc;
    }

    /**
     * Creates a copy of {@code i}.
     *
     * @param i The instance to create a copy of.
     */
    public Math_BigRationalSqrt(Math_BigRationalSqrt i) {
        this.x = i.x;
        this.sqrtx = i.sqrtx;
        this.sqrtxapprox = i.sqrtxapprox;
        this.mps = i.mps;
        this.mpsmc = i.mpsmc;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x + ", sqrtx="
                + sqrtx + ", sqrtxapprox=" + sqrtxapprox + ", mps=" + mps + ")";
    }

    /**
     * @param x The number to return the square root of (if the result is
     * rational).
     * @return The square root of x if that square root is rational and
     * {@code null} otherwise.
     */
    public static BigRational getSqrtRational(BigRational x) {
        BigInteger[] numden = getNumeratorAndDenominator(x);
        BigInteger nums = Math_BigInteger.sqrt(numden[0]);
        if (nums.signum() != -1) {
            BigInteger dens = Math_BigInteger.sqrt(numden[1]);
            if (dens.signum() != -1) {
                return BigRational.valueOf(nums).divide(BigRational.valueOf(dens));
            }
        }
        return null;
    }

    /**
     * Initialises {@link #mps} and {@link #mpsmc}. {@link #mpsmc} is
     * initialised as {@code new MathContext(mps)}.
     *
     * @param mps What {@link #mps} is set to.
     */
    private void init(int mps) {
        this.mps = mps;
        if (mps < 0) {
            mpsmc = new MathContext(0);
        } else {
            mpsmc = new MathContext(mps);
        }
    }
    
    /**
     * A POJO class for code brevity.
     */
    private class MC {
        MathContext mc;
        MathContext mcp6;
        MC(int mps){
            int precision = (int) Math.ceil(
                    x.integerPart().toBigDecimal().precision() / (double) 2)
                    + mps;
            mc = new MathContext(precision);
            mcp6 = new MathContext(precision + 6);
        }
    }

    /**
     * @param mps minimum precision scale for approximating the result.
     * @return The square root of x approximated as a BigDecimal.
     */
    public BigDecimal toBigDecimal(int mps) {
        if (sqrtx == null) {
            if (sqrtxapprox == null) {
                init(mps);
                MC mcs = new MC(mps);
                sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
            } else {
                if (this.mps < mps) {
                    init(mps);
                    MC mcs = new MC(mps);
                    sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
                }
            }
        } else {
            if (sqrtxapprox == null) {
                init(mps);
                sqrtxapprox = sqrtx.toBigDecimal(mpsmc);
            } else {
                if (this.mps < mps) {
                    init(mps);
                    sqrtxapprox = sqrtx.toBigDecimal(mpsmc);
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

    @Override
    public int compareTo(Math_BigRationalSqrt o) {
        return x.compareTo(o.x);
    }
}
