/*
 * Copyright 2010 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.math.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import uk.ac.leeds.ccg.math.util.Math_Collections;

/**
 * A class of methods for computation with {@code BigInteger} numbers.
 *
 * The aim is for accuracy to a given
 * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
 * Magnitude</a> and speed. By default if rounding is needed for results, then
 * {@link RoundingMode#HALF_UP} is used.
 *
 * @author Andy Turner
 * @version 2.0
 */
public class Math_BigInteger {

    /**
     * The number {@code Integer.MIN_VALUE} for convenience.
     */
    public static final BigInteger INTEGER_MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);

    /**
     * The number {@code Integer.MAX_VALUE} for convenience.
     */
    public static final BigInteger INTEGER_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);

    /**
     * The number {@code Long.MIN_VALUE} for convenience.
     */
    public static final BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);

    /**
     * The number {@code Long.MAX_VALUE} for convenience.
     */
    public static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);

    /**
     * For storing factorials for convenience.
     */
    protected transient List<BigInteger> factorials;

    /**
     * For storing powersOfTwo for convenience.
     */
    public transient List<BigInteger> powersOfTwo;

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.TWO;
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FIVE = BigInteger.valueOf(5);

    /**
     * Creates a new instance.
     */
    public Math_BigInteger() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param bi The Generic_BigInteger from which this is initialised.
     */
    public Math_BigInteger(Math_BigInteger bi) {
        this.factorials = bi.factorials;
        this.powersOfTwo = bi.powersOfTwo;
    }

    /**
     * Initialises {@link factorials}.
     */
    protected void initFactorials() {
        factorials = new ArrayList<>();
        factorials.add(ONE); // 0! = 1
        factorials.add(ONE); // 1! = 1
        factorials.add(TWO); // 2! = 2
    }

    /**
     * Initialises {@link #powersOfTwo}.
     */
    protected void initPowersOfTwo() {
        powersOfTwo = new ArrayList<>();
        powersOfTwo.add(ONE);
        powersOfTwo.add(TWO);
        powersOfTwo.add(TWO.multiply(TWO));
    }

    /**
     * @param v The value to return as a String.
     * @return A String representation of {@code v} in 10 characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public static String getStringValue(BigInteger v) {
        return getStringValue(v, 10);
    }
    
    /**
     * @param v The value to return as a String.
     * @param n The length of the String returned. This must be greater than or
     * equal to 10.
     * @return A String representation of {@code v} in n characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public static String getStringValue(BigInteger v, int n) {
        return Math_BigDecimal.getStringValue(new BigDecimal(v));
    }
    
    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the most significant digit of {@code x}. Examples:
     * <ul>
     * <li>x=0, result=0</li>
     * <li>x=1, result=0</li>
     * <li>x=11, result=1</li>
     * <li>x=111, result=2</li>
     * <li>x=100, result=2</li>
     * <li>x=1001, result=3</li>
     * </ul>
     *
     * @param x The number for which the largest OOM digit is returned.
     * @return The largest OOM digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfMostSignificantDigit(BigInteger x) {
        int xs = x.signum();
        return switch (xs) {
            case -1 -> log10(x.negate());
            case 0 -> 0;
            default -> log10(x);
        };
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the smallest non-zero digit of {@code x}. If the
     * OOM of the most significant digit of {@code x} is already known then use
     * {@link #getOrderOfMagnitudeOfSmallestNonZeroDigit(java.math.BigInteger, int)}
     * for computational efficiency. Examples:
     * <ul>
     * <li>x=0, result=0</li>
     * <li>x=1, result=0</li>
     * <li>x=11, result=0</li>
     * <li>x=110, result=1</li>
     * <li>x=100, result=2</li>
     * <li>x=1001, result=0</li>
     * <li>x=100100, result=2</li>
     * <li>x=1001000, result=3</li>
     * </ul>
     *
     * @param x The number for which the smallest non-zero OOM digit is
     * returned.
     * @return The smallest non-zero OOM digit of {@code x}.
     * @throws ArithmeticException if {@code x=0}.
     */
    public static int getOrderOfMagnitudeOfSmallestNonZeroDigit(BigInteger x)
            throws ArithmeticException {
        return getOrderOfMagnitudeOfSmallestNonZeroDigit(x,
                getOrderOfMagnitudeOfMostSignificantDigit(x));
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the smallest non-zero digit of {@code x}. This is
     * computationally more efficient than
     * {@link #getOrderOfMagnitudeOfSmallestNonZeroDigit(java.math.BigInteger)}.
     * No checking is done to ensure that {@code m} is correct. Examples:
     * <ul>
     * <li>x=0, result=0</li>
     * <li>x=1, result=0</li>
     * <li>x=11, result=0</li>
     * <li>x=110, result=1</li>
     * <li>x=100, result=2</li>
     * <li>x=1001, result=0</li>
     * <li>x=100100, result=2</li>
     * <li>x=1001000, result=3</li>
     * </ul>
     *
     * @param x The value for which the order of magnitude of the most
     * significant digit is returned.
     * @param m The OOM of the most significant digit of {@code x}. value for
     * which the order of magnitude of the most
     * @return The order of magnitude of the smallest non zero digit of
     * {@code x}. If {@code x=0} this returns {@code 0}.
     */
    public static int getOrderOfMagnitudeOfSmallestNonZeroDigit(BigInteger x, int m) {
        return m - new BigDecimal(x).divide(BigDecimal.TEN.pow(m)).scale();
    }

    /**
     * Calculate and return {@code x} add {@code y} rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
 rounding is necessary.
     * @return x add y rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigInteger add(BigInteger x, BigInteger y, int oom,
            RoundingMode rm) {
        return round(x.add(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} add {@code y} rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return x add y rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     */
    public static BigInteger add(BigInteger x, BigInteger y, int oom) {
        return add(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) {@code oom} using the
     * {@link RoundingMode} {@code rm}. This method is appropriate when
     * {@code x} and/or {@code y} are large and detailed, and the precision of
     * the result required is at an order of magnitude that is less detailed...
     * If the OOM of the least most significant digits of {@code x} and
     * {@code y} are close to {@code oom} then it may be computationally
     * advantageous to simply use
     * {@link #add(java.math.BigInteger, java.math.BigInteger, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
 rounding is necessary.
     * @return x multiplied by y rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigInteger addPriorRound(BigInteger x, BigInteger y,
            int oom, RoundingMode rm) {
        BigInteger xr = round(x, oom - 3, RoundingMode.DOWN);
        BigInteger yr = round(y, oom - 3, RoundingMode.DOWN);
        return add(xr, yr, oom, rm);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) {@code oom} using {@link RoundingMode#HALF_UP}. This
     * method is appropriate when {@code x} and/or {@code y} are large and
     * detailed, and the precision of the result required is at an order of
     * magnitude that is less detailed... If the OOM of the least most
     * significant digits of {@code x} and {@code y} are close to {@code oom}
     * then it may be computationally advantageous to simply use
     * {@link #add(java.math.BigInteger, java.math.BigInteger, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     * @return {@code x*y}
     */
    public static BigInteger addPriorRound(BigInteger x, BigInteger y, int oom) {
        return addPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
 rounding is necessary.
     * @return x multiplied by y rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigInteger multiply(BigInteger x, BigInteger y, int oom,
            RoundingMode rm) {
        return round(x.multiply(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return x multiplied by y rounding to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     */
    public static BigInteger multiply(BigInteger x, BigInteger y, int oom) {
        return multiply(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) {@code oom} using the
     * {@link RoundingMode} {@code rm}. This method is appropriate when
     * {@code x} and/or {@code y} are very large, and the precision of the
     * result required is at an order of magnitude the square root of which is
     * less than the magnitude of the larger of {@code x} and/or {@code y}.
     * Multiplication is only very time consuming for huge numbers, so to gain
     * some computational advantage of prior rounding the numbers being
     * multiplied may have to be very big. Some timing experiments should be
     * performed to test for any efficiencies... If the OOM of {@code x} and/or
     * {@code y} are small relative to {@code oom} then it may be
     * computationally advantageous to simply use
     * {@link #multiply(java.math.BigInteger, java.math.BigInteger, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
 rounding is necessary.
     * @return x multiplied by y rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigInteger multiplyPriorRound(BigInteger x, BigInteger y,
            int oom, RoundingMode rm) {
        int xm = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(x);
        BigInteger rp;
        int ym = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(y);
        if (xm >= ym) {
            //if (xm > m) {
            if (ym < oom) {
                int m = (int) Math.sqrt(oom);
                BigInteger d = BigInteger.TEN.pow(m);
                BigInteger xr = x.divide(d);
                rp = y.multiply(xr).multiply(d);
            } else {
                rp = y.multiply(x);
            }
        } else {
            //if (ym > m) {
            if (xm < oom) {
                int m = (int) Math.sqrt(oom);
                BigInteger d = BigInteger.TEN.pow(m);
                BigInteger yr = y.divide(d);
                rp = x.multiply(yr).multiply(d);
            } else {
                rp = x.multiply(y);
            }
        }
        return Math_BigInteger.round(rp, oom);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) {@code oom} using {@link RoundingMode#HALF_UP}. This
     * method is appropriate when {@code x} and/or {@code y} are very large, and
     * the precision of the result required is at an order of magnitude the
     * square root of which is less than the magnitude of the larger of
     * {@code x} and/or {@code y}. Multiplication is only very time consuming
     * for huge numbers, so to gain some computational advantage of prior
     * rounding the numbers being multiplied may have to be very big. Some
     * timing experiments should be performed to test for any efficiencies... If
     * the OOM of {@code x} and/or {@code y} are small relative to {@code oom}
     * then it may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigInteger, java.math.BigInteger, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> {@code oom} using {@link RoundingMode#HALF_UP}.
     * @return {@code x*y}
     */
    public static BigInteger multiplyPriorRound(BigInteger x, BigInteger y, int oom) {
        return multiplyPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * For rounding {@code x}.
     *
     * @param x The number to roundDown.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed. This should be greater
     * than 0 otherwise the result is simply x and this method need not be
     * called.
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return {@code x} rounded given {@code s} and {@code rm}
     */
    public static BigInteger round(BigInteger x, int oom, RoundingMode rm) {
        return new BigDecimal(x).movePointLeft(oom).setScale(0, rm)
                .movePointRight(oom).toBigInteger();
    }

    /**
     * For rounding {@code x}. This is the same as
     * {@link #round(java.math.BigInteger, int, java.math.RoundingMode)} with
     * {@code rm} set to {@link RoundingMode#HALF_UP}.
     *
     * @param x The number to roundDown.
     *
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed. This should be greater
     * than 0 otherwise the result is simply x and this method need not be
     * called.
     * @return {@code x} rounded given {@code s} and {@code rm}
     */
    public static BigInteger round(BigInteger x, int oom) {
        return round(x, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculates and returns the next integer closer to positive infinity
     * unless {@code x} is an integer - in which case a {@link BigInteger}
     * version of it is returned. See also {@link java.lang.Math#ceil(double)}.
     *
     * @param x The number for which the ceiling is returned.
     * @return {@code x.toBigInteger()} if {@code x} is an integer. Otherwise
     * return the next integer closer to positive infinity.
     */
    public static BigInteger ceiling(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            return floor(x.negate()).negate();
        }
        BigInteger r = x.toBigInteger();
        if (new BigDecimal(r).compareTo(x) == 0) {
            return r;
        } else {
            return r.add(ONE);
        }
    }

    /**
     * Calculates and returns the next integer closer to negative infinity
     * unless {@code x} is an integer - in which case a {@link BigInteger}
     * version of it is returned. See also {@link java.lang.Math#floor(double)}.
     *
     * @param x The number for which the floor is returned.
     * @return {@code x.toBigInteger()} if {@code x} is an integer. Otherwise
     * return the next integer closer to minus infinity.
     *
     */
    public static BigInteger floor(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigInteger r = ceiling(x.negate()).negate();
            return r;
        }
        return x.toBigInteger();
    }

    /**
     * Adds values to {@link #factorials} if they do not already exist and
     * returns {@code x} factorial ({@code x*(x-1)*(x-2)*(x-3)*...*(x-(x-1))}).
     *
     * @param x The number for which the factorial is returned.
     * @return {@code x} factorial as a BigInteger
     */
    public BigInteger factorial(int x) {
        if (x < 0) {
            throw new ArithmeticException("x < 0 in Math_BigInteger.factorial(x)");
        }
        if (factorials == null) {
            initFactorials();
        }
        int size = factorials.size();
        if (x < size) {
            return factorials.get(x);
        }
        BigInteger r = factorials.get(size - 1);
        for (int i = size; i <= x; i++) {
            r = r.multiply(BigInteger.valueOf(i));
            factorials.add(r);
        }
        return r;
    }

    /**
     * Adds values to {@link #powersOfTwo} if they do not already exist and
     * returns {@code 2} to the power of {@code x} (2^x).
     *
     * @param x The number for which 2^x is returned.
     * @return 2^x as a BigInteger
     */
    public BigInteger powerOfTwo(int x) {
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        int size = powersOfTwo.size();
        if (size > x) {
            return powersOfTwo.get(x);
        }
        BigInteger r = powersOfTwo.get(size - 1);
        for (int i = size; i <= x; i++) {
            r = TWO.multiply(r);
            powersOfTwo.add(r);
        }
        return r;
    }

    /**
     * Adds a power of {@code 2} to {@link #powersOfTwo} and returns it. If
     * {@link #powersOfTwo} is {@code null}, then it is initialised first.
     *
     * @return The next power of 2.
     */
    protected BigInteger addPowerOfTwo() {
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        int size = powersOfTwo.size();
        BigInteger r = TWO.multiply(powersOfTwo.get(size - 1));
        powersOfTwo.add(r);
        return r;
    }

    /**
     * If {@link #powersOfTwo} is null then it is initialised.
     *
     * @return {@link #powersOfTwo}.
     */
    protected List<BigInteger> getPowersOfTwo() {
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        return powersOfTwo;
    }

    /**
     * If {@link #powersOfTwo} is null then it is initialised. This returns a
     * subList of powersOfTwo where the first power of two in the subList is
     * {@code 1} and the last power of two in the subList is the last power of
     * two less than x. (All other powers of 2 are included in the subList
     * returned.)
     *
     * @param x A number for which a subList of {@link #powersOfTwo} is returned
     * such that the list returned has all powers of two less than or equal to
     * x.
     * @return {@link #powersOfTwo}.
     */
    protected List<BigInteger> getPowersOfTwo(BigInteger x) {
        getPowersOfTwo();
        int size = powersOfTwo.size();
        BigInteger p = powersOfTwo.get(size - 1);
        if (x.compareTo(p) != -1) {
            while (x.compareTo(p) == 1) {
                p = p.multiply(TWO);
                powersOfTwo.add(p);
            }
            return powersOfTwo.subList(0, powersOfTwo.size() - 1);
        } else {
            List<BigInteger> r = new ArrayList<>();
            for (BigInteger p2 : powersOfTwo) {
                if (x.compareTo(p2) != 1) {
                    return r;
                } else {
                    r.add(p2);
                }
            }
            return r;
        }
    }

    /**
     * Calculates and returns what basically amount to a binary encoding for
     * {@code x} based on the fact that all integers can be represented in the
     * form:
     * <ul>
     * <li>{@code x = m0*(2^y0) + m1*(2^y1) + m2*(2^y2) +... (where mi and yi are
     * integers, and the yi are decreasing from y0 being the smallest integer
     * such that 2^y0 >= x)}</li>
     * </ul>
     * The keys are what the number 2 is raised to in each part (yi) and the
     * values are the multiples (mi).
     *
     *
     * @param x The value to be decomposed.
     * @return A map of the powers of 2 decomposition of x. The keys are what
     * the number 2 is raised to in each part (yi) and the values are the
     * multiples (mi).
     */
    public TreeMap<Integer, Integer> getPowersOfTwoDecomposition(BigInteger x) {
        // Special Cases
        if (x.compareTo(ZERO) == 0) {
            return null;
        }
        TreeMap<Integer, Integer> r = new TreeMap<>();
        if (x.compareTo(ONE) == 0) {
            r.put(0, 1);
            return r;
        }
        List<BigInteger> xP = getPowersOfTwo(x);
        int i = xP.size() - 1;
        BigInteger p;
        BigInteger remainder = new BigInteger(x.toString());
        for (int index = i; index >= 0; index--) {
            p = xP.get(index);
            if (remainder.compareTo(ZERO) == 1) {
                int c = 0;
                while (p.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(p);
                    c++;
                }
                if (c > 0) {
                    //Generic_Collections.addToMapInteger(r, index, c);
                    Math_Collections.addToCount(r, index, c);
                }
            } else {
                break;
            }
        }
        if (remainder.compareTo(ZERO) == 1) {
            //Generic_Collections.addToMapInteger(r,
            //       getPowersOfTwoDecomposition(remainder));
            Math_Collections.addToCount(r,
                    getPowersOfTwoDecomposition(remainder));
        }
        return r;
    }

    /**
     * Adapted from https://rosettacode.org/wiki/Prime_decomposition#Java
     *
     * @param x The number to get the prime decomposition of.
     * @return 12 = 2 × 2 × 3 has a prime decomposition of {2, 2, 3}
     */
    public static ArrayList<BigInteger> getPrimeDecomposition(BigInteger x) {
        if (x.compareTo(TWO) < 0) {
            return null;
        }
        ArrayList<BigInteger> factors = new ArrayList<>();

        // Handle even values.
        while (x.and(ONE).equals(ZERO)) {
            x = x.shiftRight(1);
            factors.add(TWO);
        }

        // Handle values divisible by three.
        while (x.mod(THREE).equals(ZERO)) {
            factors.add(THREE);
            x = x.divide(THREE);
        }

        // Handle values divisible by five.
        while (x.mod(FIVE).equals(ZERO)) {
            factors.add(FIVE);
            x = x.divide(FIVE);
        }

        // Skip multiples of two, three and five.
        int[] pattern = {4, 2, 4, 2, 4, 6, 2, 6};
        int pi = 0;
        BigInteger t = BigInteger.valueOf(7);
        while (!x.equals(ONE)) {
            while (x.mod(t).equals(ZERO)) {
                factors.add(t);
                x = x.divide(t);
            }
            t = t.add(BigInteger.valueOf(pattern[pi]));
            pi = (pi + 1) & 7;
        }
        return factors;
    }

    /**
     * Optimised for huge numbers. See:
     * <a href="https://stackoverflow.com/a/18860385/1998054">https://stackoverflow.com/a/18860385/1998054</a>
     * <a href="http://en.wikipedia.org/wiki/Logarithm#Change_of_base">http://en.wikipedia.org/wiki/Logarithm#Change_of_base</a>
     *
     * States that log[b](x) = log[k](x)/log[k](b)
     *
     * We can get log[2](x) as the bitCount of the number so what we need is
     * essentially bitCount/log[2](10).Sadly that will lead to inaccuracies so
     * here I will attempt an iterative process that should achieve accuracy.
     *
     * log[2](10) = 3.32192809488736234787 so if I divide by 10^(bitCount/4) we
     * should not go too far. In fact repeating that process while adding
     * (bitCount/4) to the running count of the digits will end up with an
     * accurate figure given some twiddling at the end.
     *
     * So here's the scheme:
     *
     * While there are more than 4 bits in the number Divide by 10^(bits/4)
     * Increase digit count by (bits/4)
     *
     * Fiddle around to accommodate the remaining digit - if there is one.
     *
     * Essentially - each time around the loop we remove a number of decimal
     * digits (by dividing by 10^n) keeping a count of how many we've removed.
     *
     * The number of digits we remove is estimated from the number of bits in
     * the number (i.e. log[2](x) / 4). The perfect figure for the reduction
     * would be log[2](x) / 3.3219... so dividing by 4 is a good under-estimate.
     * We don't go too far but it does mean we have to repeat it just a few
     * times.
     *
     * @param x The number to log. This should be positive.
     * @return The number of digits in x.
     * @throws ArithmeticException if {@code x} is not greater than
     * {@code 0}.
     */
    public static int log10(BigInteger x) throws ArithmeticException {
        int xs = x.signum();
        if (xs == 1) {
            int digits = 0;
            int bits = x.bitLength();
            // Serious reductions.
            while (bits > 4) {
                // 4 > log[2](10) so we should not reduce it too far.
                int reduce = bits / 4;
                // Divide by 10^reduce
                x = x.divide(BigInteger.TEN.pow(reduce));
                // Removed that many decimal digits.
                digits += reduce;
                // Recalculate bitLength
                bits = x.bitLength();
            }
            // Now 4 bits or less - add 1 if necessary.
            if (x.intValue() > 9) {
                digits += 1;
            }
            return digits;
        }
        throw new ArithmeticException("!(x > 0)");
    }

    /**
     * Test if {@code} is an even number.
     *
     * @param x The number to test as to whether it is even.
     * @return {@code true} iff {@code x} is even (ends in 0,2,4,6,8)
     */
    public static boolean isEven(BigInteger x) {
        return x.remainder(TWO).compareTo(ZERO) == 0;
    }

    /**
     * Test if {@code} is square.
     *
     * @param x The number to test whether it is a square.
     * @return {@code true} iff {@code x} is a square number.
     */
    public static boolean isSquare(BigInteger x) {
        return x.sqrtAndRemainder()[1].compareTo(BigInteger.ZERO) == 0;
    }

    /**
     * For testing if {@code s} can be parsed as a {@link BigInteger}.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * BigInteger.
     * @return true iff {@code s} can be represented as a {@link BigInteger}.
     */
    public static boolean isBigInteger(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calculate and return the square root of {@code x}. This uses
     * {@link BigInteger#sqrt()} and returns a positive value iff {@code x} is a
     * <a href="https://en.wikipedia.org/wiki/Square_number">square number</a>).
     * (Maybe use {@link BigInteger#signum} to test the type of result if
     * necessary.)
     *
     * @param x The number to calculate and return the square root of.
     * @return
     * <ul>
     * <li>{@code null} if {@code x} is negative</li>
     * <li>the square root of {@code x} if {@code x} is a
     * <a href="https://en.wikipedia.org/wiki/Square_number">square number</a>
     * </li>
     * <li>the {@link BigInteger#negate()} of the {@link BigInteger#sqrt()} of
     * {@code x} if that is smaller than the actual square root of {@code x}
     * </li>
     * </ul>
     */
    public static BigInteger sqrt(BigInteger x) {
        if (x.compareTo(ZERO) != 1) {
            return null;
        }
        BigInteger xs = x.sqrt();
        if (x.compareTo(xs.multiply(xs)) == 0) {
            return xs;
        } else {
            return xs.negate();
        }
    }

    /**
     * For checking that {@code x} can be divided into {@code y} equal integer
     * parts.
     *
     * @param x The numerator.
     * @param y The denominator.
     * @return {@code true} iff {@code x} can be divided by {@code y} and the
     * exact answer stored as a {@link BigInteger}.
     */
    public static boolean isDivisibleBy(BigInteger x, BigInteger y) {
        BigInteger d = x.divide(y);
        return d.multiply(y).compareTo(x) == 0;
    }
    
    /**
     * @param x The values.
     * @return The minimum of all the values.
     */
    public static BigInteger min(BigInteger... x) {
        BigInteger r = x[0];
        for (BigInteger b : x) {
            r = r.min(b);
        }
        return r;
    }
    
    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static BigInteger min(Collection<BigInteger> c) {
        return c.parallelStream().min(Comparator.comparing(i -> i)).get();
    }
    
    /**
     * 
     * @param x The values.
     * @return The maximum of all the values.
     */
    public static BigInteger max(BigInteger... x) {
        BigInteger r = x[0];
        for (BigInteger b : x) {
            r = r.max(b);
        }
        return r;
    }
    
    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static BigInteger max(Collection<BigInteger> c) {
        return c.parallelStream().max(Comparator.comparing(i -> i)).get();
    }
}
