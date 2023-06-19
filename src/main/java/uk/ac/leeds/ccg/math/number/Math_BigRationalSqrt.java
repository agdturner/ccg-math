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
package uk.ac.leeds.ccg.math.number;

import uk.ac.leeds.ccg.math.arithmetic.Math_BigRational;
import ch.obermuhlner.math.big.BigRational;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;

/**
 * This is a class to help with the storage and arithmetic of numbers that are
 * square roots of positive numbers. Many such square roots are irrational and
 * so it is best not to compute them until necessary and then to do so knowing
 * what precision is wanted. Sometimes calculations can be simplified by
 * realising that component terms cancel out. For instance: the square root of 2
 * ({@code sqrt(2)}) multiplied by {@code sqrt(2)} is {@code 2}; and
 * {@code sqrt(2)} divided by {@code sqrt(2)} is {@code 1}. This has application
 * in geometry for calculating distances, areas and volumes.
 *
 * Throughout <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order
 * of Magnitude</a> (OOM) is abbreviated as such.
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
            BigRational.ZERO, BigRational.ZERO);

    /**
     * ONE
     */
    public static final Math_BigRationalSqrt ONE = new Math_BigRationalSqrt(
            BigRational.ONE, BigRational.ONE);

    /**
     * The number for which {@code this} is the square root representation.
     */
    protected final BigRational x;

    /**
     * Square root of {@link #x} if this can be stored exactly as a
     * Math_BigRational using {@link #oom} for the precision of the calculation,
     * otherwise it is {@code null}.
     */
    protected BigRational sqrtx;

    /**
     * For storing the approximate square root of {@link #x}.
     */
    protected BigDecimal sqrtxapprox;

    /**
     * Stores the Order Of Magnitude of the precision of {@link #sqrtxapprox}.
     */
    protected int oom;

    /**
     * Stores the RoundingMode used in the calculation of {@link #sqrtxapprox}.
     */
    protected RoundingMode rm;

    /**
     * Stores the MathContext for {@link #oom}.
     */
    protected MathContext oommc;

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input. By default the positive root
     * is calculated.
     *
     * @param x What {@link #x} is set to.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     */
    public Math_BigRationalSqrt(BigRational x, int oom, RoundingMode rm) {
        this(x, oom, rm, false);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     * @param negative Determines the sign of the root calculated.
     */
    public Math_BigRationalSqrt(BigRational x, int oom, RoundingMode rm,
            boolean negative) {
        this.x = x;
        sqrtxapprox = Math_BigDecimal.round(Math_BigRational.toBigDecimal(
                getSqrt(oom - 2, rm), oom - 2), oom, rm);
        if (negative) {
            sqrtxapprox = sqrtxapprox.negate();
        }
        if (BigRational.valueOf(sqrtxapprox.pow(2)).compareTo(x) == 0) {
            sqrtx = BigRational.valueOf(sqrtxapprox);
        }
        setOom(oom);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input. By default the positive root
     * is calculated.
     *
     * @param x What {@link #x} is set to.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     */
    public Math_BigRationalSqrt(BigInteger x, int oom, RoundingMode rm) {
        this(BigRational.valueOf(x), oom, rm, false);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param negative Determines the sign of the root calculated.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     */
    public Math_BigRationalSqrt(BigInteger x, int oom, RoundingMode rm,
            boolean negative) {
        this(BigRational.valueOf(x), oom, rm, negative);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input. By default this is the
     * positive square root. By default the positive root is calculated.
     *
     * @param x What {@link #x} is set to.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     */
    public Math_BigRationalSqrt(long x, int oom, RoundingMode rm) {
        this(BigRational.valueOf(x), oom, rm, false);
    }

    /**
     * Creates a new instance.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     */
    public Math_BigRationalSqrt(long x, long sqrtx) {
        this(BigRational.valueOf(x), BigRational.valueOf(sqrtx));
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param oom What {@link #oom} is set to.
     * @param rm What {@link #rm} is set to.
     * @param negative Indicates if negative or positive.
     */
    public Math_BigRationalSqrt(long x, int oom, RoundingMode rm,
            boolean negative) {
        this(BigRational.valueOf(x), oom, rm, negative);
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed the square
     * root of {@code x}. This constructor is preferred for efficiency reasons
     * over
     * {@link #Math_BigRationalSqrt(ch.obermuhlner.math.big.BigRational, int, java.math.RoundingMode)}
     * if the square root of {@code x} is known. By default the positive root is
     * calculated.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to. Cannot be {@code null}. This
     * should be the exact square root of x.
     */
    public Math_BigRationalSqrt(BigRational x, BigRational sqrtx) {
        this.x = x.reduce();
        this.sqrtx = sqrtx;
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
        this.oom = i.oom;
        this.oommc = i.oommc;
    }

    @Override
    public String toString() {
        String r = this.getClass().getSimpleName() + "(x=" + x;
        if (sqrtx == null) {
            r += ", sqrtxapprox=" + sqrtxapprox;
        } else {
            r += ", sqrtx=" + sqrtx;
        }
        r += ", oom=" + oom + ")";
        return r;
    }

    /**
     * @return A simple String representation of this. 
     */
    public String toStringSimple() {
        String r = "";
        if (sqrtx == null) {
            r += sqrtxapprox;
        } else {
            r += sqrtx;
        }
        return r;
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
    public final BigRational getSqrt() {
        return this.sqrtx;
    }

    /**
     * Return the square root rounded to a precision given by oom and rm.
     *
     * @param oom The Order of Magnitude precision to calculate square root to.
     * @param rm The RoundingMode for any rounding.
     * @return The square root rounded to a precision given by oom and rm.
     */
    public final BigRational getSqrt(int oom, RoundingMode rm) {
        if (sqrtx != null) {
            return BigRational.valueOf(Math_BigRational.toBigDecimal(sqrtx, oom, rm));
        }
        if (this.oom < oom) {
            return BigRational.valueOf(Math_BigDecimal.round(sqrtxapprox, oom, rm));
        } else if (this.oom == oom && getRoundingMode().equals(rm)) {
            return BigRational.valueOf(sqrtxapprox);
        }
        setOom(oom);
        this.rm = rm;
        BigDecimal num = x.getNumerator();
        BigDecimal den = x.getDenominator();
        BigDecimal rn = Math_BigDecimal.sqrt(num, oom - 2, rm);
        if (rn == null) {
            return null;
        } else {
            BigDecimal rd = Math_BigDecimal.sqrt(den, oom - 2, rm);
            if (rd == null) {
                return null;
            } else {
                BigRational r = BigRational.valueOf(rn, rd);
//                if (negative) {
//                    r = r.negate();
//                }
                return BigRational.valueOf(Math_BigRational.toBigDecimal(r, oom, rm));
            }
        }
    }

    /**
     * @return A copy of {@link #oom}.
     */
    public int getOom() {
        return oom;
    }

    /**
     * @return {@link #rm}.
     */
    public RoundingMode getRoundingMode() {
        return rm;
    }

    /**
     * Initialises {@link #oom} and {@link #oommc}. {@link #oommc} is
     * initialised as {@code new MathContext(oom)}.
     *
     * @param oom What {@link #oom} is set to.
     */
    private void setOom(int oom) {
        this.oom = oom;
        if (oom > 0) {
            oommc = new MathContext(oom);
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
            int precision = (int) Math.ceil(Math_BigRational.toBigDecimal(
                    x.integerPart(), oom).precision() / (double) 2) - oom;
            mc = new MathContext(precision);
            mcp6 = new MathContext(precision + 6);
        }
    }

    /**
     * @param oom The order of magnitude for approximating the result.
     * @param rm The RoundingMode for any rounding.
     * @return The square root of x approximated as a BigDecimal.
     */
    public BigDecimal toBigDecimal(int oom, RoundingMode rm) {
        if (sqrtx == null) {
            if (sqrtxapprox == null) {
                setOom(oom);
                MC mcs = new MC(oom);
                sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
            } else {
                if (this.oom > oom) {
                    setOom(oom);
                    MC mcs = new MC(oom);
                    sqrtxapprox = x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
                }
            }
        } else {
            if (sqrtxapprox == null) {
                setOom(oom);
                sqrtxapprox = sqrtx.toBigDecimal(oommc);
            } else {
                if (this.oom > oom) {
                    setOom(oom);
                    sqrtxapprox = sqrtx.toBigDecimal(oommc);
                }
            }
        }
        return Math_BigDecimal.round(sqrtxapprox, oom, rm);
    }

    /**
     * @return The numerator and denominator of {@link #x}
     */
    public final BigInteger[] getNumeratorAndDenominator() {
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
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code new Math_BigRationalSqrt(x.add(y.x))}.
     */
    public Math_BigRationalSqrt add(Math_BigRationalSqrt y, int oom,
            RoundingMode rm) {
        // Special cases
        if (this.equals(ZERO)) {
            return y;
        }
        if (y.equals(ZERO)) {
            return this;
        }
        BigRational ts = getSqrt();
        if (ts != null) {
            BigRational ys = y.getSqrt();
            if (ys != null) {
                BigRational tsays = ts.add(ys);
                return new Math_BigRationalSqrt(tsays.pow(2), tsays);
            }
        }
        // General case
        BigRational cf = Math_BigRational.getCommonFactor(x, y.x);
        if (cf.compareTo(BigRational.ONE) == 0) {
            return null;
        } else {
            BigRational r = x.divide(cf);
            BigRational ry = y.x.divide(cf);
            BigRational d = r.divide(ry);
            if (d.isInteger()) {
                Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r, oom, rm);
                Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry, oom, rm);
                if (ryr.sqrtx == null) {
                    return null;
                } else {
                    if (rr.sqrtx == null) {
                        return null;
                    } else {
                        return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx
                                .add(ryr.sqrtx).pow(2)), oom, rm);
                    }
                }
            }
            d = ry.divide(r);
            if (d.isInteger()) {
                Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r, oom, rm);
                Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry, oom, rm);
                if (ryr.sqrtx == null) {
                    return null;
                } else {
                    if (rr.sqrtx == null) {
                        return null;
                    } else {
                        return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx
                                .add(ryr.sqrtx).pow(2)), oom, rm);
                    }
                }
            }
            return null;
        }
    }

    /**
     * This method returns a non null result only in the cases where the result
     * can be expressed exactly as a square root.
     *
     * @param y The number to be added.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code this} add {@code y}.
     */
    public Math_BigRationalSqrt add(BigRational y, int oom,
            RoundingMode rm) {
        return add(new Math_BigRationalSqrt(y.pow(2), y), oom, rm);
    }

    /**
     * @return The negation of {@code this}.
     */
    public Math_BigRationalSqrt negate() {
        if (sqrtx == null) {
            Math_BigRationalSqrt r = new Math_BigRationalSqrt(this);
            r.sqrtxapprox.negate();
            return r;
        } else {
            return new Math_BigRationalSqrt(x, sqrtx.negate());
        }
    }

    /**
     * @return {@code true} if {@code this} represents a negative square root.
     */
    public boolean isNegative() {
        if (sqrtx == null) {
            return sqrtxapprox.compareTo(BigDecimal.ZERO) == -1;
        }
        return sqrtx.compareTo(BigRational.ZERO) == -1;
    }

    /**
     * @return {@code true} if {@code this} is Zero.
     */
    public boolean isZero() {
        return equals(ZERO);
    }

    /**
     * @return {@code true} if {@code this} is Zero.
     */
    public boolean isZero(int oom) {
        return equals(ZERO, oom);
    }
    
    /**
     * @return The absolute value of this. (If negative then return the negate,
     * else return a copy of this.
     */
    public Math_BigRationalSqrt abs() {
        if (isNegative()) {
            return negate();
        } else {
            return new Math_BigRationalSqrt(this);
        }
    }
    
    
    /**
     * @param y The number to multiply by.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code this} multiplied by {@code y}.
     */
    public Math_BigRationalSqrt multiply(Math_BigRationalSqrt y, int oom,
            RoundingMode rm) {
        BigRational m = x.multiply(y.x);
        if (isNegative()) {
            if (y.isNegative()) {
                return new Math_BigRationalSqrt(m, oom, rm, false);
            } else {
                return new Math_BigRationalSqrt(m, oom, rm, true);
            }
        } else {
            if (y.isNegative()) {
                return new Math_BigRationalSqrt(m, oom, rm, true);
            } else {
                return new Math_BigRationalSqrt(m, oom, rm, false);
            }
        }
    }

    /**
     * @param y The number to multiply by.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code this} multiplied by {@code y}.
     */
    public Math_BigRationalSqrt multiply(BigRational y, int oom,
            RoundingMode rm) {
        return multiply(new Math_BigRationalSqrt(y.pow(2), y), oom, rm);
    }

    /**
     * @param y The number to divide by.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code this} divided by {@code y}.
     */
    public Math_BigRationalSqrt divide(Math_BigRationalSqrt y, int oom,
            RoundingMode rm) {
        BigRational d = x.divide(y.x);
        if (isNegative()) {
            if (y.isNegative()) {
                return new Math_BigRationalSqrt(d, oom, rm, false);
            } else {
                return new Math_BigRationalSqrt(d, oom, rm, true);
            }
        } else {
            if (y.isNegative()) {
                return new Math_BigRationalSqrt(d, oom, rm, true);
            } else {
                return new Math_BigRationalSqrt(d, oom, rm, false);
            }
        }
    }

    /**
     * @param y The number to divide by.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return {@code this} divided by {@code y}.
     */
    public Math_BigRationalSqrt divide(BigRational y, int oom,
            RoundingMode rm) {
        return divide(new Math_BigRationalSqrt(y.pow(2), y), oom, rm);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Math_BigRationalSqrt m) {
            return equals(m);
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
        if (isNegative()) {
            if (x.isNegative()) {
                return x.x.compareTo(this.x) == 0;
            } else {
                return false;
            }
        } else {
            if (x.isNegative()) {
                return false;
            } else {
                return x.x.compareTo(this.x) == 0;
            }
        }
    }

    /**
     * @param x The Math_BigRationalSqrt to test for equality with this.
     * @return {@code true} iff this is equal to {@code x}
     */
    public boolean equals(Math_BigRationalSqrt x, int oom) {
        if (isNegative()) {
            if (x.isNegative()) {
                return Math_BigRational.equals(x.x, this.x, oom);
            } else {
                return false;
            }
        } else {
            if (x.isNegative()) {
                return false;
            } else {
                return Math_BigRational.equals(x.x, this.x, oom);
            }
        }
    }
    
    @Override
    public int compareTo(Math_BigRationalSqrt o) {
        if (isNegative()) {
            if (o.isNegative()) {
                return -x.compareTo(o.x);
            } else {
                return -1;
            }
        } else {
            if (o.isNegative()) {
                return 1;
            } else {
                return x.compareTo(o.x);
            }
        }
    }

    /**
     * For getting the Order of Magnitude needed for a square root calculation
     * so that the result can be returned with the precision given by
     * {@code oom}.
     *
     * @param v The number for which the square root is wanted.
     * @param oom The Order of Magnitude for the precision desired.
     * @return The Order of Magnitude of the most significant digit of the
     * resulting square root.
     */
    public static int getOOM(BigRational v, int oom) {
        int oomn = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                v.getNumeratorBigInteger());
        int oomd = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                v.getDenominatorBigInteger());
        int oom2 = oomn - oomd;
        int oom3 = 2 * (oom + 1);
        if (oom2 < oom3) {
            return Math.max(oom2, oom3);
        } else {
            return oom3;
        }
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
