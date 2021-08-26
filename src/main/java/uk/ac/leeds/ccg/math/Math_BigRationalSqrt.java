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
import java.util.Collection;
import java.util.Comparator;
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
 * @version 1.1
 */
public class Math_BigRationalSqrt implements Serializable,
        Comparable<Math_BigRationalSqrt> {

    private static final long serialVersionUID = 1L;

    /**
     * ZERO
     */
    public static final Math_BigRationalSqrt ZERO = new Math_BigRationalSqrt(
            BigRational.ZERO, BigRational.ZERO, null, 0);

    /**
     * ONE
     */
    public static final Math_BigRationalSqrt ONE = new Math_BigRationalSqrt(
            BigRational.ONE, BigRational.ONE, null, 0);
    
    /**
     * The number for which {@code this} is the square root representation.
     */
    protected final BigRational x;

    /**
     * {@link #x} squared.
     */
    protected final BigRational xsquared;

    /**
     * Square root of {@link #x} if this can be stored exactly as a BigRational,
     * otherwise it is {@code null}.
     */
    protected final BigRational sqrtx;

    /**
     * For storing the approximate square root of {@link #x}.
     */
    protected BigDecimal sqrtxapprox;

    /**
     * Stores the Order Of Magnitude of the precision of {@link #sqrtxapprox}.
     */
    protected int oom;

    /**
     * Stores the MathContext for {@link #oom}.
     */
    protected MathContext oommc;

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigRational x) {
        this.x = x.reduce();
        xsquared = x.pow(2);
        sqrtx = initSqrt();
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigInteger x) {
        this(BigRational.valueOf(x));
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(long x) {
        this(BigRational.valueOf(x));
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed what would be
     * returned from {@link #getSqrt()} with {@code x} input. This is preferred
     * for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if the
     * square root of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx) {
        this.x = x.reduce();
        this.sqrtx = sqrtx;
        xsquared = x.pow(2);
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed what would be
     * returned from {@link #getSqrt()} with {@code x} as input. This is
     * preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational)} if the
     * square root of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     * @param sqrtxapprox What {@link #sqrtxapprox} is set to.
     * @param oom What {@link #oom} is set to.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx,
            BigDecimal sqrtxapprox, int oom) {
        this.x = x.reduce();
        this.sqrtx = sqrtx;
        this.sqrtxapprox = sqrtxapprox;
        xsquared = x.pow(2);
        init(oom);
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
        xsquared = x.pow(2);
        this.oom = i.oom;
        this.oommc = i.oommc;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x + ", sqrtx="
                + sqrtx + ", sqrtxapprox=" + sqrtxapprox + ", oom=" + oom + ")";
    }

    /**
     * @return {@link #x}.
     */
    public BigRational getX() {
        return x;
    }

    /**
     * @return The square root of x if that square root is rational and
     * {@code null} otherwise.
     */
    protected final BigRational initSqrt() {
        // Special cases
        if (x.compareTo(BigRational.ZERO) == 0) {
            return BigRational.ZERO;
        }
        if (x.compareTo(BigRational.ONE) == 0) {
            return BigRational.ONE;
        }
        BigInteger[] numden = getNumeratorAndDenominator();
        BigInteger nums = Math_BigInteger.sqrt(numden[0]);
        if (nums == null) {
            return null;
        }
        if (nums.signum() != -1) {
            BigInteger dens = Math_BigInteger.sqrt(numden[1]);
            if (dens.signum() != -1) {
                return BigRational.valueOf(nums).divide(BigRational.valueOf(dens));
            }
        }
        return null;
    }

    /**
     * @return The square root of x if that square root is rational and
     * {@code null} otherwise.
     */
    public final BigRational getSqrt() {
        return this.sqrtx;
    }

    /**
     * Initialises {@link #oom} and {@link #oommc}. {@link #oommc} is
     * initialised as {@code new MathContext(oom)}.
     *
     * @param oom What {@link #oom} is set to.
     */
    private void init(int oom) {
        this.oom = oom;
        if (oom > 0) {
            oommc = new MathContext(0);
        } else {
            oommc = new MathContext(-oom);
        }
    }

    /**
     * A POJO class for code brevity.
     */
    private class MC {

        /**
         * MathContext.
         */
        MathContext mc;

        /**
         * MathContext with an additional 6 places of precision as might be
         * needed for square root calculations.
         */
        MathContext mcp6;

        /**
         * Create a new instance.
         *
         * @param oom The Order of Magnitude for the precision.
         */
        MC(int oom) {
            int precision = (int) Math.ceil(
                    x.integerPart().toBigDecimal().precision() / (double) 2)
                    - oom;
            mc = new MathContext(precision);
            mcp6 = new MathContext(precision + 6);
        }
    }

    /**
     * @param oom The order of magnitude for approximating the result.
     * @return The square root of x approximated as a BigDecimal.
     */
    public BigDecimal toBigDecimal(int oom) {
        if (sqrtx == null) {
            if (sqrtxapprox == null) {
                init(oom);
                MC mcs = new MC(oom);
                sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
            } else {
                if (this.oom > oom) {
                    init(oom);
                    MC mcs = new MC(oom);
                    sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
                }
            }
        } else {
            if (sqrtxapprox == null) {
                init(oom);
                sqrtxapprox = sqrtx.toBigDecimal(oommc);
            } else {
                if (this.oom > oom) {
                    init(oom);
                    sqrtxapprox = sqrtx.toBigDecimal(oommc);
                }
            }
        }
        return Math_BigDecimal.round(sqrtxapprox, oom);
    }

    /**
     * @return The numerator and denominator of {@link #x}
     */
    public BigInteger[] getNumeratorAndDenominator() {
        BigInteger[] r = new BigInteger[2];
        r[0] = x.getNumeratorBigInteger();
        r[1] = x.getDenominatorBigInteger();
        if (Math_BigInteger.isDivisibleBy(r[0], r[1])) {
            // Given the use of x.reduce() on constructions, this is no longer necessary?
            r[0] = r[0].divide(r[1]);
            r[1] = BigInteger.ONE;
        }
        return r;
    }

    /**
     * Adding two square roots sometimes produces a number that can be stored
     * precisely as a square root or more simply as a rational number, but
     * sometimes it can only be stored with a more complicated Surd like
     * expression. This method returns a non null result only in the cases where
     * the result can be expressed exactly as a square root.
     *
     * @param y The number to add.
     * @return {@code new Math_BigRationalSqrt(x.add(y.x))}.
     */
    public Math_BigRationalSqrt add(Math_BigRationalSqrt y) {
        BigRational cf = Math_BigRational.getCommonFactor(x, y.x);
        BigRational r = x.divide(cf);
        BigRational ry = y.x.divide(cf);
        BigRational d;
        d = r.divide(ry);
        if (d.isInteger()) {
            Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r);
            Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry);
            if (ryr.sqrtx == null) {
                return null;
            } else {
                return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx.add(ryr.sqrtx).pow(2)));
            }
        }
        d = ry.divide(r);
        if (d.isInteger()) {
            Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r);
            Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry);
            if (ryr.sqrtx == null) {
                return null;
            } else {
                return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx.add(ryr.sqrtx).pow(2)));
            }
        }
        return null;
    }

    /**
     * This method returns a non null result only in the cases where
     * the result can be expressed exactly as a square root.
     * 
     * @param y The number to be added.
     * @return {@code this} add {@code y}.
     */
    public Math_BigRationalSqrt add(BigRational y) {
        return add(new Math_BigRationalSqrt(y.pow(2)));
    }
    
//    /**
//     * Return null if there is no common multiple of x and y.x. Otherwise the 
//     * roots either cancel completely, or the result can be expressed as a Math_BigRationalSqrt.
//     * @param y The number to multiply by.
//     * @return {@code this} multiplied by {@code y}.
//     */
//    public Math_BigRationalSqrt multiply(Math_BigRationalSqrt y) {
//        return new Math_BigRationalSqrt(x.multiply(y.x));
//    }

//    /**
//     * @param y The number to multiply by.
//     * @return {@code this} multiplied by {@code y}.
//     */
//    public Math_BigRationalSqrt multiply(BigRational y) {
//        return multiply(new Math_BigRationalSqrt(y.pow(2)));
//    }
//
//    /**
//     * @param y The number to divide by.
//     * @return {@code this} divided by {@code y}.
//     */
//    public Math_BigRationalSqrt divide(Math_BigRationalSqrt y) {
//        return new Math_BigRationalSqrt(x.divide(y.x));
//    }
//
//    /**
//     * @param y The number to divide by.
//     * @return {@code this} divided by {@code y}.
//     */
//    public Math_BigRationalSqrt divide(BigRational y) {
//        return divide(new Math_BigRationalSqrt(y.pow(2)));
//    }

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

    /**
     * @param x The Math_BigRationalSqrt to test for equality with this.
     * @return {@code true} iff this is equal to {@code x}
     */
    public boolean equals(Math_BigRationalSqrt x) {
        return x.x.compareTo(this.x) == 0;
    }

    @Override
    public int compareTo(Math_BigRationalSqrt o) {
        return x.compareTo(o.x);
    }
    
    /**
     * For getting the Order of Magnitude needed for a square root calculation 
     * so that the result can be returned with the precision given by {@code oom}. 
     * @param v The number for which the square root is wanted.
     * @param oom The Order of Magnitude for the precision desired.
     * @return The Order of Magnitude of the most significant digit of the resulting square root.
     */
    public static int getOOM(BigRational v, int oom) {
        return Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                v.toBigDecimal(new MathContext(2 - oom)).toBigInteger().sqrt()) + 1;
    }

    /**
     * @param x The values.
     * @return The minimum of all the values.
     */
    public static Math_BigRationalSqrt min(Math_BigRationalSqrt... x) {
        Math_BigRationalSqrt r = x[0];
        for (Math_BigRationalSqrt b : x) {
            if (b.compareTo(r) == -1) {
                r = b;
            }
        }
        return r;
    }
    
    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static Math_BigRationalSqrt min(Collection<Math_BigRationalSqrt> c) {
        return c.parallelStream().min(Comparator.comparing(i -> i)).get();
    }
    
    /**
     * 
     * @param x The values.
     * @return The maximum of all the values.
     */
    public static Math_BigRationalSqrt max(Math_BigRationalSqrt... x) {
        Math_BigRationalSqrt r = x[0];
        for (Math_BigRationalSqrt b : x) {
            if (b.compareTo(r) == 1) {
                r = b;
            }
        }
        return r;
    }
    
    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static Math_BigRationalSqrt max(Collection<Math_BigRationalSqrt> c) {
        return c.parallelStream().max(Comparator.comparing(i -> i)).get();
    }
}
