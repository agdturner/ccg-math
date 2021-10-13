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
import java.math.RoundingMode;
import java.util.Objects;
import java.util.TreeMap;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;
import uk.ac.leeds.ccg.math.arithmetic.Math_Integer;

/**
 * This is a class to help with the storage and arithmetic of numbers that are
 * roots. Many roots are irrational and so it is best not to compute them to a
 * precision and store them as a rational unless necessary. Often calculations
 * can be simplified without needing to calculate component terms. For instance:
 * any root of a number multiplied by itself that root number of times is simply
 * that number.
 *
 * This class is being adapted from Math_BigRationalSqrt which just handles
 * square roots. The code development is incomplete... Use with extreme caution
 * and develop the unit tests!
 *
 * @author Andy Turner
 * @version 0.3
 */
public class Math_BigRationalRoot implements Serializable,
        Comparable<Math_BigRationalRoot> {

    private static final long serialVersionUID = 1L;

    /**
     * ZERO
     */
    public static final Math_BigRationalRoot ZERO = new Math_BigRationalRoot(
            Math_BigRational.ZERO, 1, Math_BigRational.ZERO);

    /**
     * ONE
     */
    public static final Math_BigRationalRoot ONE = new Math_BigRationalRoot(
            Math_BigRational.ONE, 1, Math_BigRational.ONE);

    /**
     * TWO
     */
    public static final Math_BigRationalRoot TWO = new Math_BigRationalRoot(
            Math_BigRational.TWO, 1, Math_BigRational.TWO);

    /**
     * The number for which {@code this} is the {@link #n}-th root
     * representation.
     */
    public final Math_BigRational x;

    /**
     * The order of the root of x. (i.e. n=2 is a square root, n=3 is a cube
     * root etc.)
     */
    public final int n;

    /**
     * Stores the {@link #n}-th root of {@link #x} if this can be stored exactly
     * as a BigRational, otherwise it is {@code null}.
     */
    public final Math_BigRational rootx;

    /**
     * Stores the approximate {@link #n}-th root of {@link #x} with a minimum
     * precision scale of {@link #oom}.
     */
    public BigDecimal rootxapprox;

    /**
     * Stores the minimum precision scale used for the approximate calculation
     * of {@link #rootxapprox}.
     */
    public int oom;

    /**
     * Stores the MathContext of the minimum precision scale used for the
     * approximate calculation of {@link #rootxapprox}.
     */
    public MathContext oommc;

    /**
     * Creates a new instance attempting to calculate the {@code n}th root of
     * {@code x}. If the root can be calculated exactly within oom precision,
     * then {@link #rootx} is set.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param oom The Order of Magnitude of the precision of the root
     * calculation.
     */
    public Math_BigRationalRoot(Math_BigRational x, int n, int oom) {
        this.x = x;
        this.n = n;
        rootx = getRoot(n, oom);
    }

    /**
     * Creates a new instance attempting to calculate the {@code n}th root of
     * {@code x} and store this as {@link #rootx}.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param oom The Order of Magnitude of the precision of the root
     * calculation.
     */
    public Math_BigRationalRoot(BigDecimal x, int n, int oom) {
        this(Math_BigRational.valueOf(x), n, oom);
    }

    /**
     * Creates a new instance attempting to calculate the {@code n}th root of
     * {@code x} and store this as {@link #rootx}.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param oom The Order of Magnitude of the precision of the root
     * calculation.
     */
    public Math_BigRationalRoot(BigInteger x, int n, int oom) {
        this(Math_BigRational.valueOf(x), n, oom);
    }

    /**
     * Creates a new instance attempting to calculate the {@code n}th root of
     * {@code x} and store this as {@link #rootx}.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param oom The Order of Magnitude of the precision of the root
     * calculation.
     */
    public Math_BigRationalRoot(long x, int n, int oom) {
        this(Math_BigRational.valueOf(x), n, oom);
    }

    /**
     * No check is performed to test that {@code rootx} is indeed what would be
     * returned from
     * {@link #getRoot(ch.obermuhlner.math.big.BigRational, int, int, java.math.RoundingMode)}
     * with {@code x} and {@code n} input. This is preferred for efficiency
     * reasons over
     * {@link #Math_BigRationalRoot(ch.obermuhlner.math.big.BigRational, int, int)}
     * if the {@code n}th root of {@code x} is known about.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param rootx The root of {@code x} or {@code null} if the {@code n}-th
     * root of {@code x} is irrational.
     */
    public Math_BigRationalRoot(Math_BigRational x, int n, Math_BigRational rootx) {
        this.x = x;
        this.n = n;
        this.rootx = rootx;
    }

    /**
     * No check is performed to test that {@code rootx} is indeed what would be
     * returned from
     * {@link #getRoot(ch.obermuhlner.math.big.BigRational, int, int, java.math.RoundingMode)}
     * with {@code x} as input. This is preferred for efficiency reasons over
     * {@link #Math_BigRationalRoot(ch.obermuhlner.math.big.BigRational, int, int)}
     * if it is known what the square root of {@code x} is.
     *
     * @param x What {@link #x} is set to.
     * @param n What {@link #n} is set to.
     * @param rootx What {@link #rootx} is set to.
     * @param rootxapprox What {@link #rootxapprox} is set to.
     * @param oom What {@link #oom} is set to.
     */
    public Math_BigRationalRoot(Math_BigRational x, int n, Math_BigRational rootx,
            BigDecimal rootxapprox, int oom) {
        this.x = x;
        this.n = n;
        this.rootx = rootx;
        this.rootxapprox = rootxapprox;
        this.oom = oom;
    }

    /**
     * Creates a new instance as a copy of {@code i}.
     *
     * @param i The instance to duplicate.
     */
    public Math_BigRationalRoot(Math_BigRationalRoot i) {
        this.x = i.x;
        this.n = i.n;
        this.rootx = i.rootx;
        this.oom = i.oom;
        this.rootxapprox = i.rootxapprox;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(x=" + x + ", n=" + n
                + ", rootx=" + rootx + ", rootxapprox=" + rootxapprox
                + ", oom=" + oom + ")";
    }

    /**
     * Convenience method for getting a new Math_BigRationalRoot from a
     * BigInteger.
     *
     * @param v The BigInteger to construct from.
     * @return {@code new Math_BigRationalRoot(v, 1)}
     */
    public static Math_BigRationalRoot valueOf(BigInteger v) {
        return new Math_BigRationalRoot(v, 1, -3);
    }

    /**
     * Convenience method for getting a new Math_BigRationalRoot from a
     * BigRational.
     *
     * @param v The BigDecimal to construct from.
     * @return {@code new Math_BigRationalRoot(v, 1)}
     */
    public static Math_BigRationalRoot valueOf(BigDecimal v) {
        return new Math_BigRationalRoot(v, 1,
                Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(v));
    }

    /**
     * Convenience method for getting a new Math_BigRationalRoot from a
     * BigRational.
     *
     * @param v The BigRational to construct from.
     * @param oom The Order of Magnitude of the precision for the root
     * calculation.
     * @return {@code new Math_BigRationalRoot(v, 1)}
     */
    public static Math_BigRationalRoot valueOf(Math_BigRational v, int oom) {
        return new Math_BigRationalRoot(v, 1, oom);
    }

    /**
     * This method will only return an exact root if it can be represented as a
     * BigRational and null otherwise.
     *
     * @param root The root i.e. 3 for a cube root etc.
     * @param oom The order of magnitude of precision for calculating the root.
     * If the root is rounded to this resolution then null is returned.
     * @return The root of {@code x} or {@code null} if a precise root is not
     * found at the oom.
     */
    public final Math_BigRational getRoot(int root, int oom) {
        Math_BigRational x0 = x.reduce();
        TreeMap<Integer, Integer> f = Math_Integer.getPrimeFactors(root);
        BigDecimal num = x0.getNumerator();
        BigDecimal den = x0.getDenominator();
        if (f.isEmpty()) {
            BigDecimal rn = Math_BigDecimal.root(num, root, oom);
            if (rn.pow(root).compareTo(num) != 0) {
                return null;
            }
            BigDecimal rd = Math_BigDecimal.root(den, root, oom);
            if (rn.pow(root).compareTo(num) != 0) {
                return null;
            }
            return Math_BigRational.valueOf(rn, rd);
        } else {
            BigDecimal rn = BigDecimal.ONE;
            BigDecimal rd = BigDecimal.ONE;
            int oomN6 = oom - 6;
            for (Integer p : f.keySet()) {
                for (int i = 0; i < f.get(p); i++) {
                    rn = rn.multiply(Math_BigDecimal.root(num, p, oomN6));
                    rd = rd.multiply(Math_BigDecimal.root(den, p, oomN6));
                }
            }
            return Math_BigRational.valueOf(rn).divide(Math_BigRational.valueOf(rd));
        }
    }

    /**
     * This method may return an exact or an approximate root. If the root is
     * approximate, then the result should be accurate to the given Order of
     * Magnitude {@code oom}.
     *
     * @param x The number to return the root of.
     * @param root The root i.e. 3 for a cube root etc.
     * @param oom The Order of Magnitude the result is to be accurate to.
     * @param rm The RoundingMode for any rounding.
     * @return The root of x rounded using {@code oom} and {@code rm}.
     */
    public static BigDecimal getRoot(Math_BigRational x, int root, int oom, RoundingMode rm) {
        Math_BigRational x0 = x.reduce();
        int oomN6 = oom - 6;
        TreeMap<Integer, Integer> f = Math_Integer.getPrimeFactors(root);
        BigDecimal num = x0.getNumerator();
        BigDecimal den = x0.getDenominator();
        if (f.isEmpty()) {
            BigDecimal rn = Math_BigDecimal.root(num, root, oom, rm);
            BigDecimal rd = Math_BigDecimal.root(den, root, oom, rm);
            return Math_BigDecimal.divide(rn, rd, oom, rm);
        } else {
            BigDecimal rn = BigDecimal.ONE;
            BigDecimal rd = BigDecimal.ONE;
            for (Integer p : f.keySet()) {
                for (int i = 0; i < f.get(p); i++) {
                    rn = rn.multiply(Math_BigDecimal.root(num, p, oomN6, rm));
                    rd = rd.multiply(Math_BigDecimal.root(den, p, oomN6, rm));
                }
            }
            return Math_BigDecimal.divide(rn, rd, oom, rm);
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
        if (oom < 0) {
            oommc = new MathContext(0);
        } else {
            oommc = new MathContext(oom);
        }
    }

    /**
     * A POJO class for code brevity.
     */
    private class MC {

        MathContext mc;
        MathContext mcp6;

        MC(int oom) {
            int precision = (int) Math.ceil(
                    x.integerPart().toBigDecimal().precision() / (double) 2)
                    - oom;
            mc = new MathContext(precision);
            mcp6 = new MathContext(precision + 6);
        }
    }

    /**
     * @param oom The minimum precision scale for approximating the result.
     * @return The square root of x approximated as a BigDecimal.
     */
    public BigDecimal toBigDecimal(int oom) {
        if (rootx == null) {
            if (rootxapprox == null) {
                init(oom);
                MC mcs = new MC(oom);
                x.toBigDecimal(mcs.mcp6).sqrt(mcs.mc);
                // Change the following
                rootxapprox = Math_BigDecimal.root(
                        x.toBigDecimal(mcs.mcp6), n, oom, RoundingMode.HALF_UP);
            } else {
                if (this.oom < oom) {
                    this.oom = oom;
                    rootxapprox = Math_BigDecimal.root(
                            x.toBigDecimal(new MathContext(oom + 6)), n, oom);
                }
            }
        } else {
            if (rootxapprox == null) {
                this.oom = oom;
                rootxapprox = rootx.toBigDecimal(new MathContext(oom));
            } else {
                int precision = (int) Math.ceil(x.integerPart().toBigDecimal().precision() / (double) 2) + oom;
                if (this.oom < oom) {
                    this.oom = oom;
                    rootxapprox = rootx.toBigDecimal(new MathContext(precision));
                }
            }
        }
        return rootxapprox;
    }

    /**
     * @param y The other number to multiply.
     * @return {@code y.x} multiplied by {@link #x} if this can be expressed
     * exactly as a Math_BigRationalRoot and {@code null} otherwise.
     */
    public Math_BigRationalRoot multiply(Math_BigRationalRoot y) {
        // Special cases
        if (y.compareTo(Math_BigRationalRoot.ZERO) == 0) {
            return Math_BigRationalRoot.ZERO;
        }
        if (this.compareTo(Math_BigRationalRoot.ZERO) == 0) {
            return Math_BigRationalRoot.ZERO;
        }
        if (y.compareTo(Math_BigRationalRoot.ONE) == 0) {
            return this;
        }
        if (this.compareTo(Math_BigRationalRoot.ONE) == 0) {
            return y;
        }
        if (this.rootx == null) {
            if (y.rootx == null) {
                return null;
            } else {
                Math_BigRational v = y.rootx.pow(y.n).multiply(x);
                return new Math_BigRationalRoot(v.multiply(x), y.n,
                        Math.min(this.oom, y.oom));
            }
        } else {
            Math_BigRational v;
            if (y.rootx == null) {
                v = rootx.pow(n).multiply(y.x);
                return new Math_BigRationalRoot(v, n,
                        Math.min(this.oom, y.oom));
            } else {
                v = rootx.multiply(y.rootx);
                return new Math_BigRationalRoot(v, 1,
                        Math.min(this.oom, y.oom));
            }
        }
    }

    /**
     * @param y The divisor.
     * @return {@code y.x} multiplied by {@link #x} if this can be expressed
     * exactly as a Math_BigRationalRoot and {@code null} otherwise.
     */
    public Math_BigRationalRoot divide(Math_BigRationalRoot y) {
        if (y.compareTo(Math_BigRationalRoot.ONE) == 0) {
            return this;
        }
        if (y.compareTo(Math_BigRationalRoot.ZERO) == 0) {
            return null;
        }
        if (this.compareTo(y) == 0) {
            return Math_BigRationalRoot.ONE;
        }
        if (this.rootx == null) {
            if (y.rootx == null) {
                return null;
            } else {
                if (x.compareTo(Math_BigRational.ZERO) == 0) {
                    return null;
                }
                return new Math_BigRationalRoot(y.rootx.pow(y.n).divide(x),
                        y.n, Math.min(this.oom, y.oom));
            }
        } else {
            if (y.rootx == null) {
                return new Math_BigRationalRoot(rootx.pow(n).divide(y.x), n,
                        Math.min(this.oom, y.oom));
            } else {
                return new Math_BigRationalRoot(rootx.divide(y.rootx), 1,
                        Math.min(this.oom, y.oom));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Math_BigRationalRoot) {
            return equals((Math_BigRationalRoot) o);
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
     * @param x The Math_BigRationalRoot to test for equality with this.
     * @return {@code true} iff this is equal to {@code x}
     */
    public boolean equals(Math_BigRationalRoot x) {
        return x.x.compareTo(this.x) == 0;
    }

    @Override
    public int compareTo(Math_BigRationalRoot o) {
        return x.compareTo(o.x);
    }
}
