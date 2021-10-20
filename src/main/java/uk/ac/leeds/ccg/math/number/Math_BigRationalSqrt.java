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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
            Math_BigRational.ZERO, Math_BigRational.ZERO);

    /**
     * ONE
     */
    public static final Math_BigRationalSqrt ONE = new Math_BigRationalSqrt(
            Math_BigRational.ONE, Math_BigRational.ONE);

    /**
     * TWO
     */
    public static final Math_BigRationalSqrt TWO = new Math_BigRationalSqrt(
            Math_BigRational.TWO, 0, false);

    /**
     * The number for which {@code this} is the square root representation.
     */
    protected final Math_BigRational x;

    /**
     * The Order of Magnitude used to calculate {@link #sqrtx}.
     */
    protected int oomi;

    /**
     * Square root of {@link #x} if this can be stored exactly as a
     * Math_BigRational using {@link #oomi} for the precision of the
     * calculation, otherwise it is {@code null}. This is always stored as a
     * positive number. If it is negative, then {@link #negative} indicates
     * this.
     */
    protected Math_BigRational sqrtx;

    /**
     * If {@code true}, then the root is negative.
     */
    public final boolean negative;

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
     * {@link #getSqrt()} with {@code x} as input. {@link #negative} is set to
     * {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(Math_BigRational x, int oomi) {
        this(x, null, oomi, false);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param negative What {@link #negative} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(Math_BigRational x, int oomi, boolean negative) {
        this(x, null, oomi, negative);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input. {@link #negative} is set to
     * {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(BigInteger x, int oomi) {
        this(Math_BigRational.valueOf(x), oomi, false);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param negative What {@link #negative} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(BigInteger x, int oomi, boolean negative) {
        this(Math_BigRational.valueOf(x), oomi, negative);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input. {@link #negative} is set to
     * {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(long x, int oomi) {
        this(Math_BigRational.valueOf(x), oomi, false);
    }

    /**
     * Creates a new instance. {@link #negative} is set to {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(long x, long sqrtx, int oomi) {
        this(Math_BigRational.valueOf(x), Math_BigRational.valueOf(sqrtx),
                oomi, false);
    }

    /**
     * Creates a new instance attempting to calculate {@link #sqrtx} using
     * {@link #getSqrt()} with {@code x} as input.
     *
     * @param x What {@link #x} is set to.
     * @param negative What {@link #negative} is set to.
     * @param oomi The Order of Magnitude of the precision for the initial root
     * calculation.
     */
    public Math_BigRationalSqrt(long x, int oomi, boolean negative) {
        this(Math_BigRational.valueOf(x), oomi, negative);
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed the square
     * root of {@code x}. This constructor is preferred for efficiency reasons
     * over {@link #Math_BigRationalSqrt(Math_BigRational, int)} if the square
     * root of {@code x} is known. If {@code sqrtx} is negative then
     * {@link #negative} is set to {@code true} otherwise it is set to
     * {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to. Cannot be {@code null}.
     */
    public Math_BigRationalSqrt(Math_BigRational x, Math_BigRational sqrtx) {
        this(x, sqrtx,
                Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(
                        sqrtx.toBigDecimal()));
    }

    /**
     * No check is performed to test that {@code sqrtx} the square root of
     * {@code x}. This constructor is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(Math_BigRational, int)} if the square root
     * of {@code x} is known. If {@code sqrtx} is negative then
     * {@link #negative} is set to {@code true} otherwise it is set to
     * {@code false}.
     *
     * @param x What {@link #x} is set to.
     * @param sqrtx What {@link #sqrtx} is set to. Cannot be {@code null}.
     * @param oomi The OOM used to initialise the square root and what
     * {@link #oomi} is set to.
     */
    public Math_BigRationalSqrt(Math_BigRational x, Math_BigRational sqrtx,
            int oomi) {
        this(x, sqrtx, oomi, sqrtx.compareTo(Math_BigRational.ZERO) == -1);
    }

    /**
     * No check is performed to test that {@code sqrtx} the square root of
     * {@code x}. This constructor is preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(Math_BigRational, int)} if the square root
     * of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param negative What {@link #negative} is set to.
     * @param sqrtx {@link #sqrtx} is set to {@code sqrtx.abs()} unless it is
     * {@code null} in which case an effort is made to calculate it using {@code oomi}.
     * @param oomi The Order of Magnitude used to initialise the square root and
     * what {@link #oomi} is set to if {@code sqrtx} is {@code null}.
     */
    public Math_BigRationalSqrt(Math_BigRational x, Math_BigRational sqrtx,
            int oomi, boolean negative) {
        this.x = x.reduce().abs();
        this.negative = negative;
        this.oomi = oomi;
        if (sqrtx == null) {
            if (x.compareTo(Math_BigRational.ZERO) == 0) {
                this.sqrtx = Math_BigRational.ZERO;
            } else {
                if (x.compareTo(Math_BigRational.ONE) == 0) {
                    this.sqrtx = Math_BigRational.ONE;
                } else {
                    this.sqrtxapprox = getSqrt(oomi).toBigDecimal();
                    this.sqrtx = initSqrtx();
                }
            }
        } else {
            this.sqrtx = sqrtx.abs();
        }
        init(oomi);
    }

    private Math_BigRational initSqrtx() {
        if (Math_BigRational.valueOf(this.sqrtxapprox.pow(2)).compareTo(x) == 0) {
            return Math_BigRational.valueOf(sqrtxapprox);
        } else {
            return null;
        }
    }

    /**
     * No check is performed to test that {@code sqrtx} is indeed what would be
     * returned from {@link #getSqrt()} with {@code x} as input. This is
     * preferred for efficiency reasons over
     * {@link #Math_BigRationalSqrt(Math_BigRational, int)} if the square root
     * of {@code x} is known about.
     *
     * @param x What {@link #x} is set to. This should be positive.
     * @param negative What {@link #negative} is set to.
     * @param sqrtx {@link #sqrtx} is set to. This should be positive if not
     * null.
     * @param oomi What {@link #oomi} is set to.
     * @param sqrtxapprox What {@link #sqrtxapprox} is set to. This should be
     * positive if not null.
     * @param oom What {@link #oom} is set to.
     */
    public Math_BigRationalSqrt(Math_BigRational x, boolean negative, Math_BigRational sqrtx, int oomi,
            BigDecimal sqrtxapprox, int oom) {
        this.x = x.reduce().abs();
        this.negative = negative;
        this.sqrtx = sqrtx;
        this.oomi = oomi;
        this.sqrtxapprox = sqrtxapprox;
        init(oom);
    }

    /**
     * Creates a copy of {@code i}.
     *
     * @param i The instance to create a copy of.
     */
    public Math_BigRationalSqrt(Math_BigRationalSqrt i) {
        this.x = i.x;
        this.negative = i.negative;
        this.sqrtx = i.sqrtx;
        this.oomi = i.oomi;
        this.sqrtxapprox = i.sqrtxapprox;
        this.oom = i.oom;
        this.oommc = i.oommc;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + "(x=" + x
                + ", negative=" + negative
                + ", sqrtx=" + sqrtx
                + ", oomi=" + oomi
                + ", sqrtxapprox=" + sqrtxapprox
                + ", oom=" + oom + ")";
    }

    /**
     * @return {@link #x}.
     */
    public Math_BigRational getX() {
        if (negative) {
            return x.abs().negate();
        } else {
            return x;
        }
    }

    /**
     * @return The square root of x if that square root is rational and
     * {@code null} otherwise.
     */
    public final Math_BigRational getSqrt() {
        if (negative) {
            if (sqrtx != null) {
                return sqrtx.negate();
            }
        }
        return this.sqrtx;
    }

    /**
     * @param oom The Order of Magnitude precision to calculate square root to.
     * @return The square root accurate to oom precision.
     */
    public final Math_BigRational getSqrt(int oom) {
        BigDecimal num = x.getNumerator();
        BigDecimal den = x.getDenominator();
        BigDecimal rn = Math_BigDecimal.sqrt(num, oom);
        if (rn == null) {
            return null;
        } else {
            BigDecimal rd = Math_BigDecimal.sqrt(den, oom);
            if (rd == null) {
                return null;
            } else {
                Math_BigRational r = Math_BigRational.valueOf(rn, rd);
                if (negative) {
                    r = r.negate();
                }
                return r;
            }
        }
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
                    initSqrtx();
                    oomi = oom;
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
     * @return {@code new Math_BigRationalSqrt(x.add(y.x))}.
     */
    public Math_BigRationalSqrt add(Math_BigRationalSqrt y) {
        // Special cases
        if (this.equals(ZERO)) {
            return y;
        }
        if (y.equals(ZERO)) {
            return this;
        }
        Math_BigRational ts = getSqrt();
        if (ts != null) {
            Math_BigRational ys = y.getSqrt();
            if (ys != null) {
                Math_BigRational tsays = ts.add(ys);
                return new Math_BigRationalSqrt(tsays.pow(2), tsays, y.oomi);
            }
        }
        // General case
        Math_BigRational cf = Math_BigRational.getCommonFactor(x, y.x);
        if (cf.compareTo(Math_BigRational.ONE) == 0) {
            return null;
        } else {
            Math_BigRational r = x.divide(cf);
            Math_BigRational ry = y.x.divide(cf);
            Math_BigRational d;
            d = r.divide(ry);
            if (d.isInteger()) {
                Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r, y.oomi);
                Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry, y.oomi);
                if (ryr.sqrtx == null) {
                    return null;
                } else {
                    return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx.add(ryr.sqrtx).pow(2)), y.oomi);
                }
            }
            d = ry.divide(r);
            if (d.isInteger()) {
                Math_BigRationalSqrt rr = new Math_BigRationalSqrt(r, y.oomi);
                Math_BigRationalSqrt ryr = new Math_BigRationalSqrt(ry, y.oomi);
                if (ryr.sqrtx == null) {
                    return null;
                } else {
                    return new Math_BigRationalSqrt(cf.multiply(rr.sqrtx.add(ryr.sqrtx).pow(2)), y.oomi);
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
     * @return {@code this} add {@code y}.
     */
    public Math_BigRationalSqrt add(Math_BigRational y) {
        return add(new Math_BigRationalSqrt(y.pow(2), y));
    }

    /**
     * @return The negation of {@code this}.
     */
    public Math_BigRationalSqrt negate() {
        if (negative) {
            if (sqrtx == null) {
                return new Math_BigRationalSqrt(x.abs(), false, sqrtx, oomi,
                        sqrtxapprox.abs(), oom);
            } else {
                return new Math_BigRationalSqrt(x.abs(), false, sqrtx.abs(),
                        oomi, sqrtxapprox, oom);
            }
        } else {
            if (sqrtx == null) {
                return new Math_BigRationalSqrt(x.abs().negate(), true,
                        sqrtx, oomi, sqrtxapprox.abs().negate(), oom);
            } else {
                return new Math_BigRationalSqrt(x.abs().negate(), true,
                        sqrtx.abs().negate(), oomi, sqrtxapprox, oom);
            }
        }
    }

    /**
     * @param y The number to multiply by.
     * @return {@code this} multiplied by {@code y}.
     */
    public Math_BigRationalSqrt multiply(Math_BigRationalSqrt y) {
        Math_BigRational m = x.multiply(y.x);
        return getBigRationalSqrt(m, y);
    }

    /**
     * @param y The number to multiply by.
     * @return {@code this} multiplied by {@code y}.
     */
    public Math_BigRationalSqrt multiply(Math_BigRational y) {
        return multiply(new Math_BigRationalSqrt(y.pow(2), y));
    }

    /**
     * @param y The number to divide by.
     * @return {@code this} divided by {@code y}.
     */
    public Math_BigRationalSqrt divide(Math_BigRationalSqrt y) {
        Math_BigRational d = x.divide(y.x);
        return getBigRationalSqrt(d, y);
    }

    private Math_BigRationalSqrt getBigRationalSqrt(Math_BigRational x,
            Math_BigRationalSqrt y) {
        if (negative) {
            if (y.negative) {
                return new Math_BigRationalSqrt(x, y.oomi, false);
            } else {
                return new Math_BigRationalSqrt(x, y.oomi, true);
            }
        } else {
            if (y.negative) {
                return new Math_BigRationalSqrt(x, y.oomi, true);
            } else {
                return new Math_BigRationalSqrt(x, y.oomi, false);
            }
        }
    }

    /**
     * @param y The number to divide by.
     * @return {@code this} divided by {@code y}.
     */
    public Math_BigRationalSqrt divide(Math_BigRational y) {
        return divide(new Math_BigRationalSqrt(y.pow(2), y));
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

    /**
     * @param x The Math_BigRationalSqrt to test for equality with this.
     * @return {@code true} iff this is equal to {@code x}
     */
    public boolean equals(Math_BigRationalSqrt x) {
        if (negative) {
            if (x.negative) {
                return x.x.compareTo(this.x) == 0;
            } else {
                return false;
            }
        } else {
            if (x.negative) {
                return false;
            } else {
                return x.x.compareTo(this.x) == 0;
            }
        }
    }

    @Override
    public int compareTo(Math_BigRationalSqrt o) {
        if (negative) {
            if (o.negative) {
                return -x.compareTo(o.x);
            } else {
                return -1;
            }
        } else {
            if (o.negative) {
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
    public static int getOOM(Math_BigRational v, int oom) {
        int oomn = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(v.getNumeratorBigInteger());
        int oomd = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(v.getDenominatorBigInteger());
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
