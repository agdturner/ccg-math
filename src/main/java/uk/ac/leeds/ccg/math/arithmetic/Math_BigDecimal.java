/*
 * Copyright 2010 CCG, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
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

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

/**
 * A class of methods for processing {@code BigDecimal} numbers. The
 * functionality herein was not available in BigDecimal at the time of writing.
 * Later versions of BigDecimal may make some of this redundant.
 *
 * The aim is for accuracy to a given
 * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
 * Magnitude</a> (OOM) and for the processing to be fast and efficient in terms
 * of memory usage. By default if rounding is needed for results, then
 * {@link RoundingMode#HALF_UP} is used.
 *
 * @author Andy Turner
 * @version 2.0
 */
public class Math_BigDecimal {

    /**
     * A {@link Math_BigInteger} is often wanted (such as for calculations
     * involving the Taylor Series).
     */
    public Math_BigInteger bi;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Euler%E2%80%93Mascheroni_constant">Euler
     * Mascheroni constant</a> rounded to the OOM {@link #eOOM}. The first few
     * digits are: {@code 2.7182818284590452353602874...}.
     */
    private BigDecimal e;

    /**
     * For storing the OOM that {@link #e} is rounded to.
     */
    private int eOOM;

    /**
     * For storing the first 10,000 digits of the constant
     * <a href="https://en.wikipedia.org/wiki/Pi">Pi</a>. This is initialised
     * when any of {@link #getPi(int, java.math.RoundingMode)},
     * {@link #getPiBy2(int, java.math.RoundingMode)} or
     * {@link #getPi2(int, java.math.RoundingMode)} are called. For more details
     * see {@link #initPi()}. The first few digits are:
     * {@code 3.1415926535897932384626433...}.
     */
    private BigDecimal pi;

    /**
     * For storing the constant
     * <a href="https://en.wikipedia.org/wiki/Pi">Pi</a> divided by {@code 2}.
     * This is initialised when any of {@link #getPi(int, java.math.RoundingMode)},
     * {@link #getPiBy2(int, java.math.RoundingMode)} or
     * {@link #getPi2(int, java.math.RoundingMode)} are called.
     */
    private BigDecimal piBy2;

    /**
     * For storing the constant
     * <a href="https://en.wikipedia.org/wiki/Pi">Pi</a> squared. This is
     * initialised when any of {@link #getPi(int, java.math.RoundingMode)},
     * {@link #getPiBy2(int, java.math.RoundingMode)} or
     * {@link #getPi2(int, java.math.RoundingMode)} are called.
     */
    private BigDecimal pi2;

    /**
     * The number 2.
     */
    public static final BigDecimal TWO = BigDecimal.valueOf(2);

    /**
     * The number 0.5.
     */
    public static final BigDecimal HALF = new BigDecimal("0.5");

    /**
     * The number 11.
     */
    public static final BigDecimal ELEVEN = BigDecimal.valueOf(11);

    /**
     * {@code BigDecimal.valueOf(Float.MIN_VALUE)}
     */
    public static final BigDecimal FLOAT_MIN_VALUE = BigDecimal.valueOf(Float.MIN_VALUE);

    /**
     * {@code Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(FLOAT_MIN_VALUE)}
     */
    public static final int FLOAT_MIN_VALUE_LSD = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(FLOAT_MIN_VALUE);

    /**
     * {@code BigDecimal.valueOf(Double.MIN_VALUE)}
     */
    public static final BigDecimal DOUBLE_MIN_VALUE = BigDecimal.valueOf(Double.MIN_VALUE);

    /**
     * {@code Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(DOUBLE_MIN_VALUE)}
     */
    public static final int DOUBLE_MIN_VALUE_LSD = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(DOUBLE_MIN_VALUE);

    /**
     * Creates a new instance.
     */
    public Math_BigDecimal() {
    }

    /**
     * Creates a new instance initialising {@link #bi} with {@code n} entries
     * and {@link #e} to {@code -n} OOM.
     *
     * @param n The larger n is, the more time this takes and the more
     * heavyweight the instance is. Sometimes the user might know what is needed
     * for a given usage.
     */
    public Math_BigDecimal(int n) {
        initBIF(n);
        initE(-n);
    }

    /**
     * For initialising {@link #bi}.
     *
     * @param n If n factorial (n!) is not stored in {@link #bi} then this
     * ensures it and all other (n - a)! are stored (for all integers in the
     * range [1, n]).
     */
    private void initBIF(int n) {
        getBi().factorial(n);
    }

    /**
     * For initialising {@link #e}.
     *
     * @param oom The OOM {@link #e} is initialised to.
     */
    private void initE(int oom) {
        bi.factorial(-oom);
        e = getE(oom, RoundingMode.DOWN);
        eOOM = oom;
    }

    /**
     * For initialising and returning {@link #bi}.
     *
     * @return {@link #bi} initialised if {@code null}
     */
    public Math_BigInteger getBi() {
        if (bi == null) {
            bi = new Math_BigInteger();
        }
        return bi;
    }

    /**
     * @param v The value to return as a String.
     * @return A String representation of {@code v} in 10 characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public static String getStringValue(BigDecimal v) {
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
    public static String getStringValue(BigDecimal v, int n) {
        String r = v.toString();
        if (r.length() > n) {
            if (v.abs().compareTo(BigDecimal.ONE) == -1) {
                int oomm = getOrderOfMagnitudeOfMostSignificantDigit(v);
                int ooml = getOrderOfMagnitudeOfLeastSignificantDigit(v);
                String oomms = Integer.toString(oomm);
                int oommsl = oomms.length();
                if (oommsl > n - 2) {
                    return "  Epsilon ";
                } else {
                    int digits = n - 4 - oommsl;
                    int d = 0;
                    r = "~";
                    if (v.compareTo(BigDecimal.ZERO) == -1) {
                        d++;
                        digits--;
                        r += "-";
                    }
                    int oomd = ooml - oomm;
                    BigDecimal rv;
                    if (oomd < digits) {
                        rv = round(v, oomm - digits);
                        //rv = round(v, oomm - 1 - digits);
                    } else {
                        rv = v;
                    }
                    String bis = rv.unscaledValue().toString();
                    r += bis.substring(d, d + 1);
                    d++;
                    r += ".";
                    for (int i = d; i < d + digits; i++) {
                        if (i < bis.length()) {
                            r += bis.substring(i, i + 1);
                        }
                    }
                    r += "E" + oomm;
                }
            } else {
                int ooml = getOrderOfMagnitudeOfLeastSignificantDigit(v);
                int oomm = getOrderOfMagnitudeOfMostSignificantDigit(v);
                String oomms = Integer.toString(oomm);
                int oommsl = oomms.length();
                int digits = n - 2 - oommsl;
                int d = 0;
                r = "~";
                int oommsc = n - 1;
                if (v.compareTo(BigDecimal.ZERO) == -1) {
                    r += "-";
                    if (oommsl > n - 2) {
                        oommsc--;
                    }
                    d++;
                }
                if (oommsl > oommsc) {
                    r += "Large";
                } else {
                    int oomd = ooml - oomm;
                    BigDecimal rv;
                    if (oomd < digits) {
                        rv = round(v, oomm - digits + 2 + d);
                        //rv = round(v, oomm - digits + 1 + d);
                    } else {
                        rv = v;
                    }
                    String rvs = rv.toString();
                    r += rvs.substring(d, d + 1);
                    d++;
                    r += ".";
                    for (int i = d + 1; i < digits; i++) {
                        r += rvs.substring(i, i + 1);
                    }
                    r += "E" + oomm;
                }
            }
        }
        while (r.length() < n - 1) {
            r = " " + r + " ";
        }
        if (r.length() < n) {
            r = " " + r;
        }
        //System.out.println(r.length());
        return r;
    }

    /**
     * For getting the OOM of the most significant digit of {@code x}. This
     * works for all values of {@code x}. If it is known that
     * {@code (-1 < x.abs() < 1)} then
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal, int)}
     * is computationally more efficient.
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>OOM of the most significant digit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>0.0001</td><td>-4</td></tr>
     * <tr><td>0.0011</td><td>-3</td></tr>
     * <tr><td>0.0101</td><td>-2</td></tr>
     * <tr><td>0.1001</td><td>-1</td></tr>
     * <tr><td>1.1001</td><td>0</td></tr>
     * <tr><td>10.001</td><td>1</td></tr>
     * <tr><td>101.011</td><td>2</td></tr>
     * <tr><td>1001.1</td><td>3</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The number for which the OOM of the most significant digit is
     * returned.
     * @return The largest OOM digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfMostSignificantDigit(BigDecimal x) {
        if (x.abs().compareTo(BigDecimal.ONE) == 1) {
            return Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                    x.toBigInteger());
        }
        int scale = x.scale();
        return getOrderOfMagnitudeOfMostSignificantDigit(x, scale);
    }

    /**
     * For getting the OOM of the most significant digit of {@code x}. This
     * works for all values of {@code x}. If it is known that
     * {@code (-1 > x.abs() > 1)} then
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal)}
     * is computationally more efficient.
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>OOM of the most significant digit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>0.0001</td><td>-4</td></tr>
     * <tr><td>0.0011</td><td>-3</td></tr>
     * <tr><td>0.0101</td><td>-2</td></tr>
     * <tr><td>0.1001</td><td>-1</td></tr>
     * <tr><td>1.1001</td><td>0</td></tr>
     * <tr><td>10.001</td><td>1</td></tr>
     * <tr><td>101.011</td><td>2</td></tr>
     * <tr><td>1001.1</td><td>3</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The number for which the largest OOM digit is returned.
     * @param scale The scale of {@code x}.
     * @return The largest OOM digit of {@code x}.
     */
    protected static int getOrderOfMagnitudeOfMostSignificantDigit(
            BigDecimal x, int scale) {
        int um = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                x.unscaledValue());
        return um - scale;
    }

    /**
     * For getting the OOM of the least significant digit of {@code x}.
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>OOM of the least significant digit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>10.0001</td><td>-4</td></tr>
     * <tr><td>1.0001</td><td>-4</td></tr>
     * <tr><td>0.1001</td><td>-4</td></tr>
     * <tr><td>0.0011</td><td>-4</td></tr>
     * <tr><td>0.0111</td><td>-4</td></tr>
     * <tr><td>0.1111</td><td>-4</td></tr>
     * <tr><td>1.1111</td><td>-4</td></tr>
     * <tr><td>11.111</td><td>-3</td></tr>
     * <tr><td>101.11</td><td>-2</td></tr>
     * <tr><td>1001.1</td><td>-1</td></tr>
     * <tr><td>1001.0</td><td>0</td></tr>
     * <tr><td>1001</td><td>0</td></tr>
     * <tr><td>1010</td><td>1</td></tr>
     * <tr><td>110</td><td>1</td></tr>
     * <tr><td>100</td><td>2</td></tr>
     * <tr><td>10100</td><td>2</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The number for which the OOM of the least significant digit is
     * returned.
     * @return The OOM of the least significant digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfLeastSignificantDigit(BigDecimal x) {
        BigDecimal xs = x.stripTrailingZeros();
        int scale = xs.scale();
        if (scale > 0) {
            return -scale;
        }
        return Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                xs.toBigInteger()) + 1 - xs.precision();
    }

    /**
     * For getting the scale of the most significant digit of {@code x}. This is
     * the same as the OOM for {@code (1 < x < -1)} and {@code 1} greater than
     * the OOM otherwise.
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>OOM of the least significant digit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>0.0001</td><td>-4</td></tr>
     * <tr><td>0.0011</td><td>-3</td></tr>
     * <tr><td>0.0101</td><td>-2</td></tr>
     * <tr><td>0.1001</td><td>-1</td></tr>
     * <tr><td>1.1001</td><td>1</td></tr>
     * <tr><td>10.001</td><td>2</td></tr>
     * <tr><td>101.01</td><td>3</td></tr>
     * <tr><td>1001.1</td><td>4</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The number for which the scale of the most significant digit is
     * returned.
     * @return The scale of the most significant digit of {@code x}.
     */
    public static int getScaleOfMostSignificantDigit(BigDecimal x) {
        int oommx = getOrderOfMagnitudeOfMostSignificantDigit(x);
        if (oommx < 0) {
            return oommx;
        } else {
            return oommx + 1;
        }
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounding to
     * {@code oom} using {@code rm} if necessary.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
     * rounding is necessary.
     * @return {@code x+y} rounded to {@code oom} using {@code rm} if necessary.
     */
    public static BigDecimal add(BigDecimal x, BigInteger y, int oom,
            RoundingMode rm) {
        return round(x.add(new BigDecimal(y)), oom, rm);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounding to
     * {@code oom} using {@code rm} if necessary.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
     * rounding is necessary.
     * @return {@code x+y} rounded to {@code oom} using {@code rm} if necessary.
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y, int oom,
            RoundingMode rm) {
        return round(x.add(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounding to
     * {@code oom} using {@link RoundingMode#HALF_UP} if necessary.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is needed.
     * @return {@code x+y} rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP} if necessary.
     */
    public static BigDecimal add(BigDecimal x, BigInteger y, int oom) {
        return add(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounding to
     * the {@code oom} using {@link RoundingMode#HALF_UP} if necessary.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is needed.
     * @return {@code x+y} rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP} if necessary.
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y, int oom) {
        return add(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounded to
     * {@code oom} using {@code rm}. This method is appropriate when {@code x}
     * and/or {@code y} are large and detailed, and the OOM precision of the
     * result required is much less... If the OOM of the least most significant
     * digits of {@code x} and {@code y} are close to {@code oom} then it may be
     * computationally advantageous to simply use
     * {@link #add(java.math.BigDecimal, java.math.BigDecimal, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used for rounding.
     * @return {@code x+y} rounded to {@code oom} using {@code rm} if necessary.
     */
    public static BigDecimal addPriorRound(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        BigDecimal xr = roundDown(x, oom - 3);
        if (oom < 0) {
            return add(xr, Math_BigInteger.round(y, oom - 3, rm), oom, rm);
        } else {
            return add(xr, y, oom, rm);
        }
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounded to
     * {@code oom} using {@code rm}. This method is appropriate when {@code x}
     * and/or {@code y} are large and detailed, and the OOM precision of the
     * result required is much less... If the OOM of the least most significant
     * digits of {@code x} and {@code y} are close to {@code oom} then it may be
     * computationally advantageous to simply use
     * {@link #add(java.math.BigDecimal, java.math.BigDecimal, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
     * rounding is necessary.
     * @return {@code x+y} rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal addPriorRound(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        BigDecimal xr = roundDown(x, oom - 3);
        BigDecimal yr = roundDown(y, oom - 3);
        return add(xr, yr, oom, rm);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounded to
     * {@code oom} using {@link RoundingMode#HALF_UP}. This method is
     * appropriate when {@code x} and/or {@code y} are large and detailed, and
     * the OOM precision of the result required is much less... If the OOM of
     * the least most significant digits of {@code x} and {@code y} are close to
     * {@code oom} then it may be computationally advantageous to simply use
     * {@link #add(java.math.BigDecimal, java.math.BigInteger, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @return {@code x+y} rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal addPriorRound(BigDecimal x, BigInteger y, int oom) {
        return addPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} add {@code y} ({@code x+y}) rounded to
     * {@code oom} using {@link RoundingMode#HALF_UP}. This method is
     * appropriate when {@code x} and/or {@code y} are large and detailed, and
     * the OOM precision of the result required is much less... If the OOM of
     * the least most significant digits of {@code x} and {@code y} are close to
     * {@code oom} then it may be computationally advantageous to simply use
     * {@link #add(java.math.BigDecimal, java.math.BigDecimal, int)}.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @return {@code x+y} rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal addPriorRound(BigDecimal x, BigDecimal y, int oom) {
        return addPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@link RoundingMode#HALF_UP}. This method is
     * appropriate when {@code x} and or {@code y} are very large, and the
     * precision of the result required is at an order of magnitude the square
     * root of which is less than the magnitude of the larger of x and y.
     * Multiplication is only very time consuming for huge numbers, so to gain
     * some computational advantage of prior rounding the numbers have to be
     * perhaps over 100 digits in length. (TODO test timings, maybe efficiency
     * is only gained once numbers have over 1000 digits!)
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the result if
     * rounding is necessary.
     * @return {@code x*y} to the precision scale given by {@code ps} and
     * {@code rm}.
     */
    public static BigDecimal multiply(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        return Math_BigDecimal.round(x.multiply(new BigDecimal(y)), oom, rm);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@code rm}. This method is appropriate when
     * {@code x} and/or {@code y} are very large, and the precision of the
     * result required is at an order of magnitude the square root of which is
     * less than the magnitude of the larger of {@code x} and/or {@code y}.
     * Multiplication is only very time consuming for huge and/or very precise
     * numbers, so to gain some computational advantage of prior rounding the
     * numbers being multiplied may have to be very big and/or very precise.
     * Some timing experiments should be performed to test efficiencies... If
     * the OOM of {@code x} and/or {@code y} are small relative to {@code oom}
     * and {@code x} and/or {@code y} do not have a very large precision then it
     * may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the result.
     * @return {@code x*y} rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        int xl = getOrderOfMagnitudeOfLeastSignificantDigit(x);
        if (xl < 0) {
            int ym = Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(y);
            int xlo = oom - ym - 1;
            if (xlo > xl) {
                BigDecimal xr = Math_BigDecimal.round(x, xlo, rm);
                return multiply(xr, y, oom, rm);
            }
            return multiply(x, y, oom, rm);
        }
        return new BigDecimal(Math_BigInteger.multiplyPriorRound(
                x.toBigIntegerExact(), y, oom, rm));
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@link RoundingMode#HALF_UP}. This method is
     * appropriate when {@code x} and/or {@code y} are very large, and the
     * precision of the result required is at an order of magnitude the square
     * root of which is less than the magnitude of the larger of {@code x}
     * and/or {@code y}. Multiplication is only very time consuming for huge
     * and/or very precise numbers, so to gain some computational advantage of
     * prior rounding the numbers being multiplied may have to be very big
     * and/or very precise. Some timing experiments should be performed to test
     * efficiencies... If the OOM of {@code x} and/or {@code y} are small
     * relative to {@code oom} and {@code x} and/or {@code y} do not have a very
     * large precision then it may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @return {@code x*y} rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigInteger y,
            int oom) {
        return multiplyPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@code rm}. If {@code x} and {@code y} are
     * both very large and or very detailed then prior rounding may be
     * computationally advantageous (@see also
     * {@link #multiplyPriorRound(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}).
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the final result if
     * rounding is necessary.
     * @return {@code x*y} rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        return round(x.multiply(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@code rm}. This method is appropriate when
     * {@code x} and/or {@code y} are very large, and the precision of the
     * result required is at an order of magnitude the square root of which is
     * less than the magnitude of the larger of {@code x} and/or {@code y}.
     * Multiplication is only very time consuming for huge and/or very precise
     * numbers, so to gain some computational advantage of prior rounding the
     * numbers being multiplied may have to be very big and/or very precise.
     * Some timing experiments should be performed to test efficiencies... If
     * the OOM of {@code x} and/or {@code y} are small relative to {@code oom}
     * and {@code x} and/or {@code y} do not have a very large precision then it
     * may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the result.
     * @return ({@code x*y}) rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        int xs = x.scale();
        int xm = getOrderOfMagnitudeOfMostSignificantDigit(x, xs);
        int ys = x.scale();
        int ym = getOrderOfMagnitudeOfMostSignificantDigit(y, ys);
        if (ym < 0) {
            if (xm < 0) {
                // Case 1
                return multiplyPriorRoundXLT1YLT1(x, xm, y, ym, oom, rm);
                //return multiply(x, y, oom, rm);
            } else {
                // Case 2
                //return multiplyPriorRoundXLT1YGT1(y, xm, x, ym, oom, rm);
                BigInteger xi = x.toBigInteger();
                BigDecimal yxi = multiplyPriorRound(y, xi, oom - 2, rm);
                BigDecimal xf = x.subtract(new BigDecimal(xi));
                BigDecimal yxf = multiplyPriorRoundXLT1YGT1(y, xf, xm, oom - 2, rm);
                return Math_BigDecimal.round(yxi.add(yxf), oom, rm);
            }
        } else {
            if (xm < 0) {
                // Case 3
                //return multiplyPriorRoundXLT1YGT1(x, xm, y, ym, oom, rm);
                BigInteger yi = y.toBigInteger();
                BigDecimal xyi = multiplyPriorRound(x, yi, oom - 2, rm);
                BigDecimal yf = y.subtract(new BigDecimal(yi));
                BigDecimal xyf = multiplyPriorRoundXLT1YGT1(yf, x, xm, oom - 2, rm);
                return Math_BigDecimal.round(xyi.add(xyf), oom, rm);
            }  else {
                // Case 4
                //if (oom >= 0) {
                    return multiply(x, y, oom, rm);
                //}
                //return multiplyPriorRoundXLT1YGT1(y, ym, x, xm, oom, rm);
            }
        }
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> (OOM) {@code oom} using the
     * {@link RoundingMode} {@code rm}.
     *
     * @param x A number to multiply {@code x < 1}.
     * @param xm The
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal)}
     * of {@code x}.
     * @param y A number to multiply {@code y < 1}.
     * @param oom The OOM the result is rounded to if rounding is needed.
     * @param ym The
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal)}
     * of {@code y}.
     * @param rm The {@link RoundingMode} used to roundDown the result.
     * @return ({@code x*y}) rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal multiplyPriorRoundXLT1YLT1(BigDecimal x, int xm,
            BigDecimal y, int ym, int oom, RoundingMode rm) {
        //int xs = x.scale();
        BigDecimal xr;
        if (xm + 1 >= oom) {
            xr = Math_BigDecimal.round(x, oom + ym, rm);
            //xr = Math_BigDecimal.roundDown(x, oom + ym - 3, RoundingMode.DOWN);
            xm = getOrderOfMagnitudeOfMostSignificantDigit(xr);
        } else {
            //xr = x;
            xr = Math_BigDecimal.round(x, oom - 2, rm);
            //xr = Math_BigDecimal.roundDown(x, oom - 3, RoundingMode.DOWN);
        }
        BigDecimal yr;
        if (ym + 1 >= oom) {
            yr = Math_BigDecimal.round(y, oom + xm, rm);
            //yr = Math_BigDecimal.roundDown(y, oom + xm - 3, RoundingMode.DOWN);
            ym = getOrderOfMagnitudeOfMostSignificantDigit(yr);
        } else {
            //yr = y;
            yr = Math_BigDecimal.round(y, oom - 2, rm);
            //yr = Math_BigDecimal.roundDown(y, oom - 3, RoundingMode.DOWN);
        }
        if ((ym + xm) < oom) {
            return BigDecimal.ZERO;
        } else {
            return Math_BigDecimal.multiply(xr, yr, oom, rm);
        }
    }

    private static BigDecimal multiplyPriorRoundXLT1YGT1(BigDecimal x,
            BigDecimal y, int ym, int oom, RoundingMode rm) {
        return multiplyPriorRoundXLT1YGT1(x,
                getOrderOfMagnitudeOfMostSignificantDigit(x), y, ym, oom, rm);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@code rm}.
     *
     * @param x A number to multiply {@code x < 1}.
     * @param xm
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal)}
     * of {@code x}.
     * @param y A number to multiply {@code y > 1}.
     * @param ym
     * {@link #getOrderOfMagnitudeOfMostSignificantDigit(java.math.BigDecimal)}
     * of {@code y}.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} used to roundDown the result.
     * @return ({@code x*y}) rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal multiplyPriorRoundXLT1YGT1(BigDecimal x,
            int xm, BigDecimal y, int ym, int oom, RoundingMode rm) {
        BigDecimal xr;
        if (xm + 1 >= oom) {
            int xl = getOrderOfMagnitudeOfLeastSignificantDigit(x);
            if ((oom - ym - 1) > xl) {
                xr = Math_BigDecimal.round(x, oom - ym - 1, rm);
                //xr = Math_BigDecimal.roundDown(x, oom - ym - 1, RoundingMode.DOWN);
                xm = getOrderOfMagnitudeOfMostSignificantDigit(xr);
            } else {
                xr = x;
            }
        } else {
            xr = x;
        }
        BigDecimal yr;
        if (ym + 1 >= oom) {
            int yl = getOrderOfMagnitudeOfLeastSignificantDigit(y);
            int oomy = oom + xm - 3;
            if (oomy > yl) {
                yr = Math_BigDecimal.round(y, oomy, rm);
                //yr = Math_BigDecimal.roundDown(y, oomy, RoundingMode.DOWN);
                ym = getOrderOfMagnitudeOfMostSignificantDigit(yr);
            } else {
                //yr = y;
                yr = Math_BigDecimal.round(y, oom - 2, rm);
                //yr = Math_BigDecimal.roundDown(y, oom - 2, RoundingMode.DOWN);
            }
        } else {
            //yr = y;
            yr = Math_BigDecimal.round(y, oom - 2, rm);
            //yr = Math_BigDecimal.roundDown(y, oom - 3, RoundingMode.DOWN);
        }
        if ((ym + xm) < oom) {
            return BigDecimal.ZERO;
        } else {
            return Math_BigDecimal.multiply(xr, yr, oom, rm);
        }
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} ({@code x*y})
     * rounded to {@code oom} using {@link RoundingMode#HALF_UP}. This method is
     * appropriate when {@code x} and/or {@code y} are very large, and the
     * precision of the result required is at an order of magnitude the square
     * root of which is less than the magnitude of the larger of {@code x}
     * and/or {@code y}. Multiplication is only very time consuming for huge
     * and/or very precise numbers, so to gain some computational advantage of
     * prior rounding the numbers being multiplied may have to be very big
     * and/or very precise. Some timing experiments should be performed to test
     * efficiencies... If the OOM of {@code x} and/or {@code y} are small
     * relative to {@code oom} and {@code x} and/or {@code y} do not have a very
     * large precision then it may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @return ({@code x*y}) rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigDecimal y,
            int oom) {
        return multiplyPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) rounded
     * to {@code oom} using {@code rm}. If {@code y} is {@link BigDecimal#ZERO}
     * then an {@link ArithmeticException} is thrown. Otherwise if {@code x} is
     * {@link BigDecimal#ZERO} then {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} for rounding.
     * @return ({@code x/y}) rounded to {@code oom} using {@code rm}.
     * @throws ArithmeticException if {@code y} is zero.
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y, int oom,
            RoundingMode rm) {
        // Deal with special cases
        int ys = y.signum();
        if (x.signum() == 0) {
            if (ys == 0) {
                throw new ArithmeticException("Division undefined 0/0.");
            }
            return BigDecimal.ZERO;
        }
        if (ys == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundDown(x, oom);
        }
        if (x.compareTo(y) == 0) {
            return roundDown(BigDecimal.ONE, oom);
        }
        //return divideNoCaseCheck(x, y, oom, rm);
        return divideNoCaseCheck(x, y, oom, rm);
    }

    private static BigDecimal divideNoCaseCheck(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        BigDecimal[] xDARy = x.divideAndRemainder(y);
        int p;
        if (xDARy[0].compareTo(BigDecimal.ZERO) == 0) {
            int oommx = getOrderOfMagnitudeOfMostSignificantDigit(x);
            p = -oom;
            if (oommx >= 0) {
                p += oommx - 1;
            } else {
                p += oommx;
            }
            int oommy = getOrderOfMagnitudeOfMostSignificantDigit(y);
            if (oommy >= 0) {
                p -= oommy - 1;
            } else {
                p -= oommy;
            }
            if (p < 1) {
                p = 1;
            }
        } else {
            p = xDARy[0].precision() - oom;
        }
        if (p < 0) {
            return BigDecimal.ZERO;
        }
        MathContext mc = new MathContext(p, rm);
        //return x.divide(y, mc);
        return roundDown(x.divide(y, mc), oom);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) rounded
     * to {@code oom} using {@code rm}. If {@code y} is {@link BigDecimal#ZERO}
     * then an {@link ArithmeticException} is thrown. Otherwise if {@code x} is
     * {@link BigDecimal#ZERO} then {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} for rounding.
     * @return ({@code x/y}) rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal divide(BigDecimal x, BigInteger y, int oom,
            RoundingMode rm) {
        return divide(x, new BigDecimal(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) rounded
     * to {@code oom} using {@code rm}. If {@code y} is {@link BigDecimal#ZERO}
     * then an {@link ArithmeticException} is thrown. Otherwise if {@code x} is
     * {@link BigDecimal#ZERO} then {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} for rounding.
     * @return ({@code x/y}) rounded to OOM {@code oom} using {@code rm}.
     */
    public static BigDecimal divide(BigInteger x, BigDecimal y, int oom,
            RoundingMode rm) {
        return divide(new BigDecimal(x), y, oom, rm);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) rounded
     * to {@code oom} using {@code rm}. If {@code y} is {@link BigDecimal#ZERO}
     * then an {@link ArithmeticException} is thrown. Otherwise if {@code x} is
     * {@link BigDecimal#ZERO} then {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The OOM the result is rounded to if rounding is necessary.
     * @param rm The {@link RoundingMode} for rounding.
     * @return ({@code x/y}) rounded to {@code oom} using {@code rm}.
     */
    public static BigDecimal divide(BigInteger x, BigInteger y, int oom,
            RoundingMode rm) {
        return divide(new BigDecimal(x), new BigDecimal(y), oom, rm);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) without
     * rounding.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return {@code x/y}
     * @throws ArithmeticException if {@code y} is zero or the result cannot be
     * stored exactly as a BigDecimal.
     */
    public static BigDecimal divideNoRounding(BigDecimal x, BigDecimal y) {
        // Deal with special cases
        int ys = y.signum();
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            if (ys == 0) {
                throw new ArithmeticException("Division undefined 0/0.");
            }
            return BigDecimal.ZERO;
        }
        if (ys == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return x.divide(y);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) without
     * rounding.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return {@code x/y}
     * @throws ArithmeticException if {@code y} is zero or the result cannot be
     * stored exactly as a BigDecimal.
     */
    public static BigDecimal divideNoRounding(BigDecimal x, BigInteger y) {
        return divideNoRounding(x, new BigDecimal(y));
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) without
     * rounding.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return {@code x/y}
     * @throws ArithmeticException if {@code y} is zero or the result cannot be
     * stored exactly as a BigDecimal.
     */
    public static BigDecimal divideNoRounding(BigInteger x, BigDecimal y) {
        return divideNoRounding(new BigDecimal(x), y);
    }

    /**
     * Calculate and return {@code x} divided by {@code y} ({@code x/y}) without
     * rounding.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return {@code x/y}
     * @throws ArithmeticException if {@code y} is zero or the result cannot be
     * stored exactly as a BigDecimal.
     */
    public static BigDecimal divideNoRounding(BigInteger x, BigInteger y) {
        return divideNoRounding(new BigDecimal(x), new BigDecimal(y));
    }

    /**
     * Calculate and return x raised to the power of y (x^y) for
     * {@code (0 < y < 1)}.
     *
     * The following formulae are used:
     * <ul>
     * <li>x^y = e^(y * ln(x))</li>
     * <li>log(u/v)=log(u)-log(v)</li>
     * <li>log(1) = 0</li>
     * <li>x^(a+b) = x^a*x^b</li>
     * <li>x^(a/b) = bthroot(x^a)</li>
     * </ul>
     *
     * @param x The base.
     * @param y The exponent. Expected to be in the range (0,1).
     * @param oom The OOM for the precision of the result.
     * @param rm The {@link RoundingMode} used to roundDown the final result.
     * @return x^y accurate to {@code oom}.
     */
    private static BigDecimal powerExponentLessThanOne(BigDecimal x,
            BigDecimal y, int oom, RoundingMode rm) {
        BigDecimal r;
        BigDecimal y0 = new BigDecimal(y.toString());
        BigDecimal element;
        BigInteger elementUnscaled;
        BigDecimal elementOne;
        BigInteger elementOneReciprocal;
        BigDecimal root;
        BigDecimal rootMultiple;
        r = BigDecimal.ONE;
        do {
            element = floorSignificantDigit(y0);
            elementUnscaled = element.unscaledValue();
            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
                elementOne = divideNoRounding(element, elementUnscaled);
                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
                root = Math_BigDecimal.root(x, elementOneReciprocal.intValueExact(), oom - 3, RoundingMode.DOWN);
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = power(root, elementUnscaled, oom - 3, RoundingMode.DOWN);
                    //r = multiplyRoundIfNecessary(r, rootMultiple, oom, rm);
                    //r = r.multiply(rootMultiple);
                    r = multiply(r, rootMultiple, oom - 3, RoundingMode.DOWN);
                }
            }
            y0 = y0.subtract(element);
        } while (y0.compareTo(BigDecimal.ZERO) != 0);
        return round(r, oom, rm);
    }

    private static BigDecimal powerExponentLessThanOneNoRounding(BigDecimal x,
            BigDecimal y) {
        BigDecimal r = BigDecimal.ONE;
        //BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
        BigDecimal y0 = new BigDecimal(y.toString());
        BigDecimal element;
        BigInteger elementUnscaled;
        BigDecimal elementOne;
        BigInteger elementOneReciprocal;
        BigDecimal root;
        BigDecimal rootMultiple;
        BigDecimal previousResult = r;
        while (true) {
            element = floorSignificantDigit(y0);
            elementUnscaled = element.unscaledValue();
            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
                elementOne = divideNoRounding(element, elementUnscaled);
                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
                root = rootNoRounding(x, elementOneReciprocal.intValueExact());
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = powerNoRounding(root, elementUnscaled);
                    r = r.multiply(rootMultiple);
                }
            }
            if (r.compareTo(previousResult) == 0) {
                break;
            }
            previousResult = r;
            y0 = y0.subtract(element);
        }
        return r;
    }

    private static int getDiv(BigDecimal x, BigInteger y) {
        BigDecimal ylog2x = Math_BigDecimal.multiply(
                log(2, x, 0, RoundingMode.UP), y, 0, RoundingMode.UP);
        if (ylog2x.compareTo(BigDecimal.ZERO) == 0) {
            return 2;
        } else {
            BigDecimal log2ylog2x = log(2, ylog2x, 0, RoundingMode.UP);
            int r = TWO.pow(log2ylog2x.intValueExact()).intValueExact();
            //int r = TWO.pow(log2ylog2x.intValue()).intValueExact();
            if (r < 2) {
                r = 2;
            }
            return r;
        }
    }

    private static int getDiv(BigDecimal x, long y) {
        return getDiv(x, BigInteger.valueOf(y));
    }

    /**
     * Test to see if x raised to the power of y (x^y) is greater than
     * {@code compare}.
     *
     * @param compare The value to compare.
     * @param x x {@code >} 1
     * @param y y {@code >} 1
     * @param oom The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used in the case rounding is
     * necessary.
     * @return true iff x^y {@code compare}.
     */
    public static boolean powerTestAbove(BigDecimal compare, BigDecimal x,
            BigInteger y, int oom, RoundingMode rm) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y.compareTo(divbi) == -1) {
            return power(x, y, oom - 1, rm).compareTo(compare) == 1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(divbi) == -1) {
                c0 = power(x, y1, oom - 1, rm);
            } else {
                BigInteger[] yDAR = y1.divideAndRemainder(divbi);
                if (powerTestAbove(compare, x, yDAR[0], oom - 1, rm)) {
                    return true;
                } else {
                    c0 = power(x, yDAR[0], oom - 1, rm);
                }
                if (c.compareTo(compare) == 1) {
                    return true;
                }
                if (yDAR[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal rr = power(x, yDAR[1], oom, rm);
                    if (rr.compareTo(compare) == 1) {
                        return true;
                    }
                    c0 = c0.multiply(rr); // @TODO Is this the correct form of multiply?
                    if (c.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0); // @TODO Is this the correct form of multiply?
            if (c.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(divbi);
        }
        return false;
    }

    /**
     * Test to see if x raised to the power of y (x^y) is greater than
     * {@code compare}.
     *
     * @param compare The value to compare.
     * @param x x {@code >} 1
     * @param y y {@code >} 1
     * @param oom The number of decimal places the result has to be correct to.
     * @param rm The RoundingMode used in the case rounding is necessary.
     * @return true iff x^y {@code compare}.
     */
    public static boolean powerTestAbove(BigDecimal compare, BigDecimal x,
            int y, int oom, RoundingMode rm) {
        int div = getDiv(x, y);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y < div) {
            return power(x, y, oom - 1, rm).compareTo(compare) == 1;
        }
        while (y > 1) {
            if (y < div) {
                c0 = power(x, y, oom - 1, rm);
            } else {
                int yDAR0 = y / div;
                int yDAR1 = y % div;
                if (powerTestAbove(compare, x, yDAR0, oom - 1, rm)) {
                    return true;
                } else {
                    c0 = power(x, yDAR0, oom - 1, rm);
                }
                if (c.compareTo(compare) == 1) {
                    return true;
                }
                if (yDAR1 > 1) {
                    BigDecimal rr = power(x, yDAR1, oom, rm);
                    if (rr.compareTo(compare) == 1) {
                        return true;
                    }
                    c0 = c0.multiply(rr); // @TODO Is this the correct form of multiply?
                    if (c.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0); // @TODO Is this the correct form of multiply?
            if (c.compareTo(compare) == 1) {
                return true;
            }
            y /= div;
        }
        return false;
    }

    /**
     * Test to see if x raised to the power of y (x^y) is greater than ({@code >})
     * {@code compare}.
     *
     * @param compare The value to compare.
     * @param x x {@code >} 1
     * @param y y {@code >} 1
     * @return true iff x^y {@code >} compare.
     */
    public static boolean powerTestAboveNoRounding(BigDecimal compare,
            BigDecimal x, BigInteger y) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y.compareTo(divbi) == -1) {
            return powerNoRounding(x, y).compareTo(compare) == 1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(divbi) == -1) {
                c0 = powerNoRounding(x, y1);
            } else {
                BigInteger[] yDAR = y1.divideAndRemainder(divbi);
                if (powerTestAboveNoRounding(compare, x, yDAR[0])) {
                    return true;
                } else {
                    c0 = powerNoRounding(x, yDAR[0]);
                }
                if (c.compareTo(compare) == 1) {
                    return true;
                }
                if (yDAR[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal rr = powerNoRounding(x, yDAR[1]);
                    if (rr.compareTo(compare) == 1) {
                        return true;
                    }
                    c0 = c0.multiply(rr);
                    if (c.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0);
            if (c.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(divbi);
        }
        return false;
    }

    /**
     * Test to see if {@code x} raised to the power {@code y} (x^y) is less than ({@code <})
     * {@code compare}.
     *
     * @param compare The number to compare with x^y.
     * @param x The base. Expected to be greater than {@code 0}.
     * @param y The exponent. Expected to be greater than {@code 1}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The RoundingMode used in the case rounding is necessary.
     * @return true iff x^y {@code <} compare
     */
    public static boolean powerTestBelow(BigDecimal compare, BigDecimal x,
            BigInteger y, int oom, RoundingMode rm) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y.compareTo(divbi) == -1) {
            return power(x, y, oom, rm).compareTo(compare) == -1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(divbi) == -1) {
                c0 = power(x, y1, oom, rm);
            } else {
                BigInteger[] yDAR = y1.divideAndRemainder(divbi);
                if (powerTestAbove(compare, x, yDAR[0], oom, rm)) {
                    return true;
                } else {
                    c0 = power(x, yDAR[0], oom, rm);
                }
                c0 = power(c0, div, oom, rm);
                if (c.compareTo(compare) == -1) {
                    return true;
                }
                if (yDAR[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal rr = power(x, yDAR[1], oom, rm);
                    if (rr.compareTo(compare) == -1) {
                        return true;
                    }
                    c0 = c0.multiply(rr); // @TODO Is this the correct form of multiply?
                    if (c.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0); // @TODO Is this the correct form of multiply?
            if (c.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(divbi);
        }
        return false;
    }

    /**
     * Test to see if x raised to the power of y (x^y) is less than ({@code <})
     * {@code compare}.
     *
     * @param compare The number to compare with x^y.
     * @param x The base. Expected to be greater than {@code 0}.
     * @param y The exponent. Expected to be greater than {@code 1}.
     * @return true iff x^y {@code <} compare
     */
    public static boolean powerTestBelowNoRounding(BigDecimal compare,
            BigDecimal x, BigInteger y) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y.compareTo(divbi) == -1) {
            return powerNoRounding(x, y).compareTo(compare) == -1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDAR;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(divbi) == -1) {
                c0 = powerNoRounding(x, y1);
            } else {
                yDAR = y1.divideAndRemainder(divbi);
                if (powerTestAboveNoRounding(compare, x, yDAR[0])) {
                    return true;
                } else {
                    c0 = powerNoRounding(x, yDAR[0]);
                }
                c0 = powerNoRounding(c0, div);
                if (c.compareTo(compare) == -1) {
                    return true;
                }
                if (yDAR[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal rr = powerNoRounding(x, yDAR[1]);
                    if (rr.compareTo(compare) == -1) {
                        return true;
                    }
                    c0 = c0.multiply(rr);
                    if (c.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0);
            if (c.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(divbi);
        }
        return false;
    }

    /**
     * Calculates and returns x raised to the power of y (x^y). This may employ
     * the following:
     * <ul>
     * <li>{@code x^y = e^(y * ln(x))}</li>
     * <li>{@code x^(a+b) = (x^a)*(x^b)}</li>
     * </ul>
     * For some x and y this is not well behaved! To understand why please see:
     * https://en.wikipedia.org/wiki/Exponentiation
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @return x^y
     */
    public static BigDecimal power(BigDecimal x, BigDecimal y, int oom, RoundingMode rm) {
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundDown(x, oom);
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = power(x.negate(), y, oom - 3, RoundingMode.DOWN);
            if (isEven(y)) {
                return round(r.negate(), oom, rm);
            } else {
                return round(r, oom, rm);
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = power(x, y.negate(), oom - 3, RoundingMode.DOWN);
            return reciprocal(power, oom, rm);
        }
        if (y.scale() <= 0) {
            return power(x, y.toBigIntegerExact(), oom, rm);
        }
        if (x.compareTo(TWO) == -1) {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                return powerExponentLessThanOne(x, y, oom, rm);
            } else {
                BigDecimal fractionalPart = powerExponentLessThanOne(x,
                        y.subtract(BigDecimal.ONE), oom - 3, RoundingMode.DOWN);
                return round(x.multiply(fractionalPart), oom, rm);
            }
        } else {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                // x > 2 && y < 1
                return powerExponentLessThanOne(x, y, oom, rm);
            } else {
                // x > 2 && y > 1
                BigInteger ybi = y.toBigInteger();
                BigDecimal ri = power(x, ybi, oom, RoundingMode.DOWN);
                BigDecimal rf = power(x,
                        y.subtract(new BigDecimal(ybi)), oom - 3, RoundingMode.DOWN);
                return multiply(ri, rf, oom, rm);
            }
        }
    }

    /**
     * Calculates and returns x raised to the power of y (x^y). This may employ
     * the following:
     * <ul>
     * <li>{@code x^y = e^(y * ln(x))}</li>
     * <li>{@code x^(a+b) = (x^a)*(x^b)}</li>
     * </ul>
     * For some x and y this is not well behaved! To understand why please see:
     * https://en.wikipedia.org/wiki/Exponentiation
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @return x^y
     */
    public static BigDecimal powerNoRounding(BigDecimal x, BigDecimal y) {
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = powerNoRounding(x.negate(), y);
            if (isEven(y)) {
                return r.negate();
            } else {
                return r;
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = powerNoRounding(x, y.negate());
            BigDecimal r = reciprocalWillBeIntegerReturnBigDecimal(power);
            return r;
        }
        if (y.scale() <= 0) {
            return powerNoRounding(x, y.toBigIntegerExact());
        }
        if (x.compareTo(TWO) == -1) {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                throw new UnsupportedOperationException();
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                // x > 2 && y < 1
                BigDecimal r = powerExponentLessThanOneNoRounding(x, y);
//                // Use x^a * y^a = (x * y)^a
//                throw new UnsupportedOperationException();
                return r;
            } else {
                // x > 2 && y > 1
                BigInteger ybi = y.toBigInteger();
                // Integer part result ipr
                BigDecimal ipr = powerNoRounding(x, ybi);
                // Fractional part result fpr
                BigDecimal fpr = powerNoRounding(x, y.subtract(new BigDecimal(ybi)));
                BigDecimal r = ipr.multiply(fpr);
                return r;
            }
        }
    }

    /**
     * Calculates and returns x raised to the power of y (x^y). This may employ
     * the following:
     * <ul>
     * <li>{@code x^y = e^(y * ln(x))}</li>
     * <li>{@code x^(a+b) = (x^a)*(x^b)}</li>
     * </ul>
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return x^y
     */
    public static BigDecimal power(BigDecimal x, long y, int oom, RoundingMode rm) {
        return power(x, BigInteger.valueOf(y), oom, rm);
    }

    /**
     * Calculates and returns x raised to the power of y (x^y). This may employ
     * the following:
     * <ul>
     * <li>{@code x^y = e^(y * ln(x))}</li>
     * <li>{@code x^(a+b) = (x^a)*(x^b)}</li>
     * </ul>
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return x^y accurate to decimalPlaces
     */
    public static BigDecimal power(BigDecimal x, int y, int oom, RoundingMode rm) {
        return power(x, BigInteger.valueOf(y), oom, rm);
    }

    /**
     * Calculates and returns {@code x} raised to the power of {@code y}
     * ({@code x^y}). If y is negative and a precise answer cannot be given an
     * ArtithmeticException will be thrown.
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @return x^y
     */
    public static BigDecimal powerNoRounding(BigDecimal x, int y) {
        return powerNoRounding(x, BigInteger.valueOf(y));
    }

    /**
     * {@code x.unscaledValue() == 1} and {@code x.precision() == 1}
     *
     * @param x The base of the exponent.
     * @param y The exponent. Expected to be greater than or equal to {@code 0}.
     * @return {@code 1} iff y == 0.
     */
    public static BigDecimal powerUnscaled1Precision1(BigDecimal x, int y) {
        // Special cases
        if (y == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal r;
        int xs = x.scale();
        if (xs == 0) {
            r = x.movePointRight((y - 1) * (x.precision() - 1));
        } else {
            r = x.movePointLeft((y - 1) * xs);
        }
        return r;
    }

    /**
     * Calculates and returns x raised to the power of y (x^y) accurate to
     * {@code oom} number of decimal places. The calculation is divided into
     * parts... In this implementation {@code 0^0 = 1}
     * <a href="https://en.wikipedia.org/wiki/Zero_to_the_power_of_zero">https://en.wikipedia.org/wiki/Zero_to_the_power_of_zero"</a>.
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm RoundingMode for any rounding.
     * @return x^y accurate to decimalPlaces
     */
    public static BigDecimal power(BigDecimal x, BigInteger y, int oom,
            RoundingMode rm) {
        // Deal with special cases
        // x = 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // y = 0
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y.compareTo(BigInteger.ONE) == 0) {
            return round(x, oom, rm);
        }
        // y = 2
        if (y.compareTo(BigInteger.TWO) == 0) {
            return round(x.multiply(x), oom, rm);
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = power(x.negate(), y, oom, rm);
            if (Math_BigInteger.isEven(y)) {
                return r.negate();
            } else {
                return r;
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheck(x, y.negate(),
                    oom - 2, rm);
            return reciprocal(power, oom, rm);
        }
        return powerNoSpecialCaseCheck(x, y, oom, rm);
    }

    /**
     * Calculates and returns x raised to the power of y (x^y).
     *
     * @param x The base of the exponent.
     * @param y The exponent. Expected to be positive.
     * @return x^y
     */
    public static BigDecimal powerNoRounding(BigDecimal x, BigInteger y) {
        // Deal with special cases
        // x = 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // y = 0
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        // y = 2
        if (y.compareTo(BigInteger.TWO) == 0) {
            BigDecimal r = x.multiply(x);
            return r;
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = powerNoRounding(x.negate(), y);
            if (Math_BigInteger.isEven(y)) {
                return r.negate();
            } else {
                return r;
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheckNoRounding(x, y.negate());
            return new BigDecimal(reciprocalWillBeIntegerReturnBigInteger(power));
        }
        return powerNoSpecialCaseCheckNoRounding(x, y);
    }

    /**
     * Calculates and returns {@code x} raised to the power of {@code y}
     * ({@code x^y}) accurate to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> (OOM) {@code oom} and RoundingMode {@code rm}.
     *
     * @param x The base of the exponent {@code x > 0 && x != 1}.
     * @param y The exponent {@code y >= 3}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to.
     * @param rm The {@link RoundingMode} used to roundDown the final result.
     * @return {@code x^y}
     */
    public static BigDecimal powerNoSpecialCaseCheck(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal r0;
        BigDecimal r1;
        BigInteger y1 = new BigInteger(y.toString());
        if (y1.compareTo(divbi) != 1) {
            while (y1.compareTo(divbi) != 1) {
                divbi = divbi.divide(BigInteger.TWO);
                div = div / 2;
            }
        }
        BigInteger[] yDAR = y1.divideAndRemainder(divbi);
        if (yDAR[0].compareTo(BigInteger.ONE) == 0) {
            if (yDAR[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    return power(x, y1.intValue(), oom, rm);
                } else {
                    //div /= 2;
                    return power(x, y, oom, rm);
                }
            } else {
                int remainingY = yDAR[1].intValue();
                if (div < 4) {
                    r0 = power(x, div, oom - div - 2, rm);
                    r1 = power(x, remainingY, oom - remainingY - 2, rm);
                    return multiply(r0, r1, oom, rm);
                } else {
                    r0 = power(x, divbi, oom - div - 2, rm);
                    r1 = power(x, remainingY, oom - remainingY - 2, rm);
                    return multiply(r0, r1, oom, rm);
                }
            }
        }
        r0 = power(x, divbi, oom - div - 2, rm);
        r0 = power(r0, yDAR[0], oom - yDAR[0].intValue() - 2, rm);
        BigInteger remainingY = yDAR[1];
        r1 = power(x, remainingY, oom - remainingY.intValue() - 2, rm);
        return multiply(r0, r1, oom, rm);
    }

    /**
     * Calculates and returns {@code x} raised to the power of {@code y}
     * ({@code x^y}) accurate to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> (OOM) {@code oom} and RoundingMode {@code rm}.
     *
     * @param x The base of the exponent {@code x > 0 && x != 1}.
     * @param y The exponent {@code y >= 3}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to.
     * @param rm The {@link RoundingMode} used to roundDown the final result.
     * @return {@code x^y}
     */
    public static BigDecimal powerNoSpecialCaseCheck(BigDecimal x, int y,
            int oom, RoundingMode rm) {
        int div = getDiv(x, y);
        int divbi = div;
        BigDecimal r0;
        BigDecimal r1;
        int y1 = y;
        if (y1 <= divbi) {
            while (y1 <= divbi) {
                divbi = divbi / 2;
                div = div / 2;
            }
        }
        int quotient = y1 / divbi;
        int remainder = y1 % divbi;
        if (quotient == 1) {
            if (remainder == 0) {
                if (div < 6) {
                    return power(x, y1, oom, rm);
                } else {
                    return power(x, y, oom, rm);
                }
            } else {
                if (div < 4) {
                    r0 = power(x, div, oom - div - 2, rm);
                    r1 = power(x, remainder, oom - remainder - 2, rm);
                    return multiply(r0, r1, oom, rm);
                } else {
                    r0 = power(x, divbi, oom - div - 2, rm);
                    r1 = power(x, remainder, oom - remainder - 2, rm);
                    return multiply(r0, r1, oom, rm);
                }
            }
        }
        r0 = power(x, divbi, oom - div - 2, rm);
        r0 = power(r0, quotient, oom - quotient - 2, rm);
        r1 = power(x, remainder, oom - remainder - 2, rm);
        return multiply(r0, r1, oom, rm);
    }

    /**
     * Calculates and returns {@code x} raised to the power of {@code y}
     * ({@code x^y}).
     *
     * @param x The base of the exponent {@code x > 0 && x != 1}.
     * @param y The exponent {@code y >= 3}.
     * @return {@code x^y}
     */
    public static BigDecimal powerNoSpecialCaseCheckNoRounding(BigDecimal x,
            BigInteger y) {
        int div = getDiv(x, y);
        BigInteger divbi = BigInteger.valueOf(div);
        BigDecimal r0;
        BigDecimal r1;
        BigInteger y1 = new BigInteger(y.toString());
        if (y1.compareTo(divbi) != 1) {
            while (y1.compareTo(divbi) != 1) {
                divbi = divbi.divide(BigInteger.TWO);
                div = div / 2;
            }
        }
        BigInteger[] yDAR = y1.divideAndRemainder(divbi);
        if (yDAR[0].compareTo(BigInteger.ONE) == 0) {
            if (yDAR[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    return powerNoRounding(x, y1.intValue());
                } else {
                    //div /= 2;
                    return powerNoRounding(x, y);
                }
            } else {
                if (div < 4) {
                    r0 = powerNoRounding(x, div);
                    r1 = powerNoRounding(x, yDAR[1].intValue());
                    return r0.multiply(r1);
                } else {
                    r0 = powerNoRounding(x, divbi);
                    r1 = powerNoRounding(x, yDAR[1].intValue());
                    return r0.multiply(r1);
                }
            }
        }
        r0 = powerNoRounding(x, divbi);
        r0 = powerNoRounding(r0, yDAR[0]);
        BigInteger remainingY = yDAR[1];
        r1 = powerNoRounding(x, remainingY);
        return r0.multiply(r1);
    }

    /**
     * Calculates and returns the reciprocal 1 divided by x (1/x).
     *
     * @param x The number to calculate the reciprocal of.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return 1/x Accurate to decimalPlace number of decimal places. Throws an
     * ArithmeticException - if divisor is zero,
     * roundingMode==RoundingMode.UNNECESSARY and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    public static BigDecimal reciprocal(BigDecimal x, int oom, RoundingMode rm) {
        return Math_BigDecimal.divide(BigDecimal.ONE, x, oom, rm);
    }

    /**
     * Calculates and returns the reciprocal of {@code x} ({@code 1/x}) rounded
     * to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> given by {@code oom} using {@link RoundingMode#HALF_UP}.
     *
     * @param x The base.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> the result is rounded to if rounding is needed.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return {@code 1/x}
     * @throws ArithmeticException iff {@code x = 0}
     */
    public static BigDecimal reciprocal(BigInteger x, int oom, RoundingMode rm)
            throws ArithmeticException {
        return Math_BigDecimal.divide(BigDecimal.ONE, new BigDecimal(x), oom, rm);
    }

    /**
     * Calculates and returns the reciprocal {@code 1} divided by {@code x}
     * ({@code 1/x}) iff the result can be stored as an integer, otherwise an
     * {@link ArithmeticException} is thrown.
     *
     * @param x The number to calculate the reciprocal of.
     * @return {@code 1/x} if the result can be stored as an integer, otherwise
     * an {@link ArithmeticException} is thrown.
     */
    public static BigInteger reciprocalWillBeIntegerReturnBigInteger(
            BigDecimal x) {
        return reciprocalWillBeIntegerReturnBigDecimal(x).toBigIntegerExact();
    }

    /**
     * Calculates and returns the reciprocal 1 divided by x (1/x) iff the result
     * can be stored as an integer, otherwise an {@link ArithmeticException} is
     * thrown.
     *
     * @param x The number to calculate the reciprocal of.
     * @return 1/x if the result can be stored as an integer, otherwise an
     * {@link ArithmeticException} is thrown.
     */
    public static BigDecimal reciprocalWillBeIntegerReturnBigDecimal(
            BigDecimal x) {
        return BigDecimal.ONE.divide(x, 0, RoundingMode.UNNECESSARY);
    }

//    /**
//     * Adapted from http://everything2.com/index.pl?node_id=946812
//     * @param x
//     * @param decimalPlaces
//     * @return
//     */
//    public BigDecimal lg(BigDecimal x, int decimalPlaces) {
//        final int NUM_OF_DIGITS = decimalPlaces + 2;
//        // need to add one to get the right number of decimalPlaces
//        //  and then add one again to get the next number
//        //  so I can roundDown it correctly.
//        MathContext mc = new MathContext(NUM_OF_DIGITS, RoundingMode.HALF_EVEN);
//        //special conditions:
//        // log(-toCompare) -> exception
//        // log(1) == 0 exactly;
//        // log of a number lessthan one = -log(1/toCompare)
//        if (x.signum() <= 0) {
//            throw new ArithmeticException("log of a negative number! (or zero)");
//        } else if (x.compareTo(BigDecimal.ONE) == 0) {
//            return BigDecimal.ZERO;
//        } else if (x.compareTo(BigDecimal.ONE) < 0) {
//            return (lg((BigDecimal.ONE).divide(x, mc), decimalPlaces)).negate();
//        }
//        StringBuffer sb = new StringBuffer();
//        //number of digits on the left of the decimal point
//        int leftDigits = x.precision() - x.scale();
//        //so, the first digits of the log10 are:
//        sb.append(leftDigits - 1).append(".");
//        //this is the algorithm outlined in the webpage
//        int n = 0;
//        while (n < NUM_OF_DIGITS) {
//            x = (x.movePointLeft(leftDigits - 1)).pow(10, mc);
//            leftDigits = x.precision() - x.scale();
//            sb.append(leftDigits - 1);
//            n++;
//        }
//        BigDecimal ans = new BigDecimal(sb.toString());
//        //Round the number to the correct number of decimal places.
//        ans = ans.roundDown(new MathContext(ans.precision() - ans.scale() + decimalPlaces, RoundingMode.HALF_EVEN));
//        return ans;
//    }
    /**
     * Calculates and returns the logarithm base b of x (logbx) correct to dp
     * decimal places using {@link RoundingMode} rm. The algorithms was adapted
     * from http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     *
     * The calculation involves a main loop where iteratively, either the input
     * is squared, or base is square rooted. Squaring the input, and leaving the
     * base alone, is generally thought to be more accurate. If the input
     * becomes 1, then halt. The log of 1, for any base, is 0.
     *
     * @param b The base of the logarithm.
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @param x The number to calculate the logarithm of.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @return logbx
     */
    public static BigDecimal log(int b, BigDecimal x, int oom, RoundingMode rm) {
        return log(BigDecimal.valueOf(b), x, oom, rm);
    }

    /**
     * Calculates and returns the logarithm of x. If x is less than {@code 0} an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param base The base of the logarithm.
     * @param x The number to take the logarithm of.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return The log of x to the base base to decimalPlace precision
     */
    public static BigDecimal log(BigDecimal base, BigDecimal x, int oom,
            RoundingMode rm) {
        BigDecimal r;// = BigDecimal.ZERO;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("x <= 0");
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ZERO;
        }
        if (base.compareTo(x) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xp = x.precision();
            BigInteger xu = x.unscaledValue();
            int xup = xu.toString().length();
            int i = xp - xup;
            BigDecimal xum = new BigDecimal(xu, i);
            //System.out.print(unscaledxmajor);
            BigDecimal logxum = log(base, xum, oom - i, rm);
            r = logxum.subtract(BigDecimal.valueOf(i));
            return round(r, oom);
        }
        r = BigDecimal.ZERO;
        BigDecimal x0 = new BigDecimal(x.toString());
        //int scale = x0.precision() - oom;
        //BigInteger maxite = x.toBigInteger().max(BigInteger.valueOf(10000));
        BigInteger ite = BigInteger.ZERO;
        BigDecimal epsilon = new BigDecimal(BigInteger.ONE, 1 - oom);
        //System.out.println("epsilon " + epsilon);
        //System.out.println("scale " + scale);
        while (x0.compareTo(base) == 1) {
            r = r.add(BigDecimal.ONE);
            //x0 = x0.divide(base, scale, rm);
            x0 = Math_BigDecimal.divide(x0, base, oom - 3, RoundingMode.DOWN);
        }
        BigDecimal f = HALF;
        x0 = x0.multiply(x0);
        int ooms = Math.min(oom * 2, oom - 10); // Not sure this is safe enough, maybe log(base,maxite,0) would be?
        boolean condition;
        do {
            if (x0.compareTo(base) == 1) {
                //x0 = x0.divide(base, scale, rm);
                x0 = Math_BigDecimal.divide(x0, base, oom - 3, RoundingMode.DOWN);
                r = r.add(f);
            }
            x0 = multiply(x0, x0, ooms, rm);
            //f = f.divide(TWO, scale, rm);
            f = Math_BigDecimal.divide(f, TWO, oom - 3, RoundingMode.DOWN);
            BigDecimal raf = r.add(f);
            if (f.abs().compareTo(epsilon) == -1) {
                break;
            }
//            if (maxite.compareTo(ite) == 0) {
//                System.out.println("Warning: maxite reached in "
//                        + Math_BigDecimal.class.getName()
//                        + ".log(BigDecimal,BigDecimal,int,RoundingMode)");
//                break;
//            }
            ite = ite.add(BigInteger.ONE);
            condition = ((raf).compareTo(r) == 1)
                    && (x0.compareTo(BigDecimal.ONE) == 1);
        } while (condition);
        return round(r, oom, rm);
    }

    /**
     * @param x The number to roundDown.
     * @param oom The Order of Magnitude for any rounding. Trailing zeros are
     * stripped from any fractional part.
     * @return {@code x} rounded down to {@code oom} precision.
     */
    public static BigDecimal roundDown(BigDecimal x, int oom) {
        return x.movePointLeft(oom).setScale(0, RoundingMode.DOWN).movePointRight(oom).stripTrailingZeros();
    }

    /**
     * @param x The number to roundDown.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * Trailing zeros are stripped from any fractional part.
     * @param rm The rounding mode for any rounding.
     * @return {@code x} rounded given {@code oom} and {@code rm}
     */
    public static BigDecimal round(BigDecimal x, int oom, RoundingMode rm) {
//        int oomsd = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x);
//        int precision = oomsd - oom;
//        if (oomsd >= 0) {
//            precision += 1;
//        }
////        if (precision == 0) {
////            precision = 1;
////        } else if (precision < 0) {
////            return roundDown(x, oom);
////        }
////        return x.round(new MathContext(precision + 1, rm));
//        if (precision == 0) {
//            precision = 1;
//        } else if (precision < 0) {
//            x.toPlainString().charAt(precision);
//            return roundDown(x, oom);
//        }
//        return x.round(new MathContext(precision, rm));
//        //return x.movePointLeft(oom).setScale(0, rm).movePointRight(oom).stripTrailingZeros(); // This always rounds down!
//        //return x.movePointLeft(oom - 3).setScale(0, rm).movePointRight(oom - 3).stripTrailingZeros(); // This always rounds down!
        return x.movePointLeft(oom).setScale(0, rm).movePointRight(oom).stripTrailingZeros();
    }

    /**
     * The is the same as
     * {@link #round(java.math.BigDecimal, int, java.math.RoundingMode)}
     * with {@code rm = RoundingMode.HALF_UP}
     *
     * @param x The number to round.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @return {@code x} rounded given {@code s} and {@code rm}
     */
    public static BigDecimal round(BigDecimal x, int oom) {
        return round(x, oom, RoundingMode.HALF_UP);
    }

    /**
     * Initialises {@link #pi} with first 10,000 digits. The data were obtained
     * from
     * <a href="http://www.eveandersson.com/pi/digits/">http://www.eveandersson.com/pi/digits/</a>
     * on 2011-01-14, where the first million digits of Pi were also
     * available...
     */
    private void initPi() {
        pi = new BigDecimal(
                "3."
                + "14159265358979323846264338327950288419716939937510"
                + "58209749445923078164062862089986280348253421170679"
                + "82148086513282306647093844609550582231725359408128"
                + "48111745028410270193852110555964462294895493038196"
                + "44288109756659334461284756482337867831652712019091"
                + "45648566923460348610454326648213393607260249141273"
                + "72458700660631558817488152092096282925409171536436"
                + "78925903600113305305488204665213841469519415116094"
                + "33057270365759591953092186117381932611793105118548"
                + "07446237996274956735188575272489122793818301194912"
                + "98336733624406566430860213949463952247371907021798"
                + "60943702770539217176293176752384674818467669405132"
                + "00056812714526356082778577134275778960917363717872"
                + "14684409012249534301465495853710507922796892589235"
                + "42019956112129021960864034418159813629774771309960"
                + "51870721134999999837297804995105973173281609631859"
                + "50244594553469083026425223082533446850352619311881"
                + "71010003137838752886587533208381420617177669147303"
                + "59825349042875546873115956286388235378759375195778"
                + "18577805321712268066130019278766111959092164201989"
                + "38095257201065485863278865936153381827968230301952"
                + "03530185296899577362259941389124972177528347913151"
                + "55748572424541506959508295331168617278558890750983"
                + "81754637464939319255060400927701671139009848824012"
                + "85836160356370766010471018194295559619894676783744"
                + "94482553797747268471040475346462080466842590694912"
                + "93313677028989152104752162056966024058038150193511"
                + "25338243003558764024749647326391419927260426992279"
                + "67823547816360093417216412199245863150302861829745"
                + "55706749838505494588586926995690927210797509302955"
                + "32116534498720275596023648066549911988183479775356"
                + "63698074265425278625518184175746728909777727938000"
                + "81647060016145249192173217214772350141441973568548"
                + "16136115735255213347574184946843852332390739414333"
                + "45477624168625189835694855620992192221842725502542"
                + "56887671790494601653466804988627232791786085784383"
                + "82796797668145410095388378636095068006422512520511"
                + "73929848960841284886269456042419652850222106611863"
                + "06744278622039194945047123713786960956364371917287"
                + "46776465757396241389086583264599581339047802759009"
                + "94657640789512694683983525957098258226205224894077"
                + "26719478268482601476990902640136394437455305068203"
                + "49625245174939965143142980919065925093722169646151"
                + "57098583874105978859597729754989301617539284681382"
                + "68683868942774155991855925245953959431049972524680"
                + "84598727364469584865383673622262609912460805124388"
                + "43904512441365497627807977156914359977001296160894"
                + "41694868555848406353422072225828488648158456028506"
                + "01684273945226746767889525213852254995466672782398"
                + "64565961163548862305774564980355936345681743241125"
                + "15076069479451096596094025228879710893145669136867"
                + "22874894056010150330861792868092087476091782493858"
                + "90097149096759852613655497818931297848216829989487"
                + "22658804857564014270477555132379641451523746234364"
                + "54285844479526586782105114135473573952311342716610"
                + "21359695362314429524849371871101457654035902799344"
                + "03742007310578539062198387447808478489683321445713"
                + "86875194350643021845319104848100537061468067491927"
                + "81911979399520614196634287544406437451237181921799"
                + "98391015919561814675142691239748940907186494231961"
                + "56794520809514655022523160388193014209376213785595"
                + "66389377870830390697920773467221825625996615014215"
                + "03068038447734549202605414665925201497442850732518"
                + "66600213243408819071048633173464965145390579626856"
                + "10055081066587969981635747363840525714591028970641"
                + "40110971206280439039759515677157700420337869936007"
                + "23055876317635942187312514712053292819182618612586"
                + "73215791984148488291644706095752706957220917567116"
                + "72291098169091528017350671274858322287183520935396"
                + "57251210835791513698820914442100675103346711031412"
                + "67111369908658516398315019701651511685171437657618"
                + "35155650884909989859982387345528331635507647918535"
                + "89322618548963213293308985706420467525907091548141"
                + "65498594616371802709819943099244889575712828905923"
                + "23326097299712084433573265489382391193259746366730"
                + "58360414281388303203824903758985243744170291327656"
                + "18093773444030707469211201913020330380197621101100"
                + "44929321516084244485963766983895228684783123552658"
                + "21314495768572624334418930396864262434107732269780"
                + "28073189154411010446823252716201052652272111660396"
                + "66557309254711055785376346682065310989652691862056"
                + "47693125705863566201855810072936065987648611791045"
                + "33488503461136576867532494416680396265797877185560"
                + "84552965412665408530614344431858676975145661406800"
                + "70023787765913440171274947042056223053899456131407"
                + "11270004078547332699390814546646458807972708266830"
                + "63432858785698305235808933065757406795457163775254"
                + "20211495576158140025012622859413021647155097925923"
                + "09907965473761255176567513575178296664547791745011"
                + "29961489030463994713296210734043751895735961458901"
                + "93897131117904297828564750320319869151402870808599"
                + "04801094121472213179476477726224142548545403321571"
                + "85306142288137585043063321751829798662237172159160"
                + "77166925474873898665494945011465406284336639379003"
                + "97692656721463853067360965712091807638327166416274"
                + "88880078692560290228472104031721186082041900042296"
                + "61711963779213375751149595015660496318629472654736"
                + "42523081770367515906735023507283540567040386743513"
                + "62222477158915049530984448933309634087807693259939"
                + "78054193414473774418426312986080998886874132604721"
                + "56951623965864573021631598193195167353812974167729"
                + "47867242292465436680098067692823828068996400482435"
                + "40370141631496589794092432378969070697794223625082"
                + "21688957383798623001593776471651228935786015881617"
                + "55782973523344604281512627203734314653197777416031"
                + "99066554187639792933441952154134189948544473456738"
                + "31624993419131814809277771038638773431772075456545"
                + "32207770921201905166096280490926360197598828161332"
                + "31666365286193266863360627356763035447762803504507"
                + "77235547105859548702790814356240145171806246436267"
                + "94561275318134078330336254232783944975382437205835"
                + "31147711992606381334677687969597030983391307710987"
                + "04085913374641442822772634659470474587847787201927"
                + "71528073176790770715721344473060570073349243693113"
                + "83504931631284042512192565179806941135280131470130"
                + "47816437885185290928545201165839341965621349143415"
                + "95625865865570552690496520985803385072242648293972"
                + "85847831630577775606888764462482468579260395352773"
                + "48030480290058760758251047470916439613626760449256"
                + "27420420832085661190625454337213153595845068772460"
                + "29016187667952406163425225771954291629919306455377"
                + "99140373404328752628889639958794757291746426357455"
                + "25407909145135711136941091193932519107602082520261"
                + "87985318877058429725916778131496990090192116971737"
                + "27847684726860849003377024242916513005005168323364"
                + "35038951702989392233451722013812806965011784408745"
                + "19601212285993716231301711444846409038906449544400"
                + "61986907548516026327505298349187407866808818338510"
                + "22833450850486082503930213321971551843063545500766"
                + "82829493041377655279397517546139539846833936383047"
                + "46119966538581538420568533862186725233402830871123"
                + "28278921250771262946322956398989893582116745627010"
                + "21835646220134967151881909730381198004973407239610"
                + "36854066431939509790190699639552453005450580685501"
                + "95673022921913933918568034490398205955100226353536"
                + "19204199474553859381023439554495977837790237421617"
                + "27111723643435439478221818528624085140066604433258"
                + "88569867054315470696574745855033232334210730154594"
                + "05165537906866273337995851156257843229882737231989"
                + "87571415957811196358330059408730681216028764962867"
                + "44604774649159950549737425626901049037781986835938"
                + "14657412680492564879855614537234786733039046883834"
                + "36346553794986419270563872931748723320837601123029"
                + "91136793862708943879936201629515413371424892830722"
                + "01269014754668476535761647737946752004907571555278"
                + "19653621323926406160136358155907422020203187277605"
                + "27721900556148425551879253034351398442532234157623"
                + "36106425063904975008656271095359194658975141310348"
                + "22769306247435363256916078154781811528436679570611"
                + "08615331504452127473924544945423682886061340841486"
                + "37767009612071512491404302725386076482363414334623"
                + "51897576645216413767969031495019108575984423919862"
                + "91642193994907236234646844117394032659184044378051"
                + "33389452574239950829659122850855582157250310712570"
                + "12668302402929525220118726767562204154205161841634"
                + "84756516999811614101002996078386909291603028840026"
                + "91041407928862150784245167090870006992821206604183"
                + "71806535567252532567532861291042487761825829765157"
                + "95984703562226293486003415872298053498965022629174"
                + "87882027342092222453398562647669149055628425039127"
                + "57710284027998066365825488926488025456610172967026"
                + "64076559042909945681506526530537182941270336931378"
                + "51786090407086671149655834343476933857817113864558"
                + "73678123014587687126603489139095620099393610310291"
                + "61615288138437909904231747336394804575931493140529"
                + "76347574811935670911013775172100803155902485309066"
                + "92037671922033229094334676851422144773793937517034"
                + "43661991040337511173547191855046449026365512816228"
                + "82446257591633303910722538374218214088350865739177"
                + "15096828874782656995995744906617583441375223970968"
                + "34080053559849175417381883999446974867626551658276"
                + "58483588453142775687900290951702835297163445621296"
                + "40435231176006651012412006597558512761785838292041"
                + "97484423608007193045761893234922927965019875187212"
                + "72675079812554709589045563579212210333466974992356"
                + "30254947802490114195212382815309114079073860251522"
                + "74299581807247162591668545133312394804947079119153"
                + "26734302824418604142636395480004480026704962482017"
                + "92896476697583183271314251702969234889627668440323"
                + "26092752496035799646925650493681836090032380929345"
                + "95889706953653494060340216654437558900456328822505"
                + "45255640564482465151875471196218443965825337543885"
                + "69094113031509526179378002974120766514793942590298"
                + "96959469955657612186561967337862362561252163208628"
                + "69222103274889218654364802296780705765615144632046"
                + "92790682120738837781423356282360896320806822246801"
                + "22482611771858963814091839036736722208883215137556"
                + "00372798394004152970028783076670944474560134556417"
                + "25437090697939612257142989467154357846878861444581"
                + "23145935719849225284716050492212424701412147805734"
                + "55105008019086996033027634787081081754501193071412"
                + "23390866393833952942578690507643100638351983438934"
                + "15961318543475464955697810382930971646514384070070"
                + "73604112373599843452251610507027056235266012764848"
                + "30840761183013052793205427462865403603674532865105"
                + "70658748822569815793678976697422057505968344086973"
                + "50201410206723585020072452256326513410559240190274"
                + "21624843914035998953539459094407046912091409387001"
                + "26456001623742880210927645793106579229552498872758"
                + "46101264836999892256959688159205600101655256375678");
        piBy2 = pi.divide(TWO);
        pi2 = pi.multiply(TWO);
    }

    /**
     * @param oom If greater than 10000, then a
     * {@link java.lang.RuntimeException} is thrown.
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return {@link #pi} unless it is {@code null} in which case it is
     * initialised with 10000 decimal places.
     */
    public BigDecimal getPi(int oom, RoundingMode rm) {
        if (oom < -10000) {
            throw new RuntimeException("oom < -10000");
        }
        if (pi == null) {
            initPi();
        }
        return Math_BigDecimal.round(pi, oom, rm);
    }

    /**
     * @return {@link #pi} divided by {@code 2}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     */
    public BigDecimal getPiBy2(int oom, RoundingMode rm) {
        if (pi == null) {
            initPi();
        }
        return Math_BigDecimal.round(piBy2, oom, rm);
    }

    /**
     * @return {@link #pi} multiplied by {@code 2}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     */
    public BigDecimal getPi2(int oom, RoundingMode rm) {
        if (pi == null) {
            initPi();
        }
        return Math_BigDecimal.round(pi2, oom, rm);
    }

    /**
     * If {@link #e} has enough precision it is rounded and returned otherwise
     * {@link #e} is recalculated to the required precision, stored and a copy
     * returned.
     *
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return A value of the Euler constant rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public BigDecimal getE(int oom, RoundingMode rm) {
        if (e != null) {
            if (eOOM < oom) {
                return round(e, oom, rm);
            }
        }
        e = new BigDecimal("2");
        int dps = 3 - oom;
        //int maxite = 10000;
        //int maxite = decimalPlaces * 2;
        int maxite = dps;
        getBi().factorial(maxite);
        BigDecimal tollerance = new BigDecimal(BigInteger.ONE, dps);
        // Use Taylor Series
        for (int i = 2; i < maxite; i++) {
            BigDecimal f = new BigDecimal(bi.factorials.get(i));
            BigDecimal rf = divide(BigDecimal.ONE, f, oom - 3, RoundingMode.DOWN);
            e = e.add(rf);
            if (rf.compareTo(tollerance) != 1) {
                break;
            }
        }
        eOOM = oom;
        return round(e, oom, rm);
    }

    /**
     * Calculates and returns {@link #e} to the power of x. Note that:
     * <ul>
     * <li>e^x = 1 + x/1! + x^2/2! + x^3/3! +...</li>
     * </ul>
     *
     * @param x The value for which e to the power of y (e^y) is returned.
     * @param oom The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return e^y where e is the Euler constant. The result is returned correct
     * to decimalPlaces decimal place precision.
     */
    public BigDecimal exp(BigDecimal x, int oom, RoundingMode rm) {
        BigDecimal r;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // Check bd
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return getE(oom, rm);
        }
        BigInteger xi = x.toBigInteger();
        BigDecimal xid = new BigDecimal(xi);
        if (xid.compareTo(x) == 0) {
            return exp(xi, oom, rm);
        }
        BigDecimal fract = x.subtract(xid);
        if (fract.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal exp = exp(fract.negate(), oom - fract.scale(), RoundingMode.DOWN);
            r = reciprocal(exp, oom - 3, RoundingMode.DOWN);
            return Math_BigDecimal.multiply(exp(xi, oom - 3, RoundingMode.DOWN), r, oom, rm);
        }
        r = BigDecimal.ONE.add(fract);
        BigDecimal dpxff;
        BigDecimal ex = exp(xi, oom - 3, RoundingMode.DOWN);
        //int oomm3 = oom - 3 - Math_BigDecimal.getScaleOfMostSignificantDigit(x);
        int oomm3 = oom - 3 - Math_BigDecimal.getScaleOfMostSignificantDigit(ex);
        //int oomm3 = oom - 3 - (int) Math.sqrt(Math_BigDecimal.getScaleOfMostSignificantDigit(ex));
        BigDecimal tollerance = new BigDecimal(BigInteger.ONE, 1 - oomm3);
        // Use Taylor Series
        BigInteger exponent = BigInteger.ONE;
        int f = 1;
        while (true) {
            exponent = exponent.add(BigInteger.ONE);
            f++;
            BigDecimal ff = new BigDecimal(getBi().factorial(f));
            /**
             * May need oomm3 to be larger (even though the bottom of the Taylor
             * series grows fast)!
             */
            BigDecimal px = power(fract, exponent, oomm3, rm);
            dpxff = Math_BigDecimal.divide(px, ff, oomm3, rm);
            r = r.add(dpxff);
            if (dpxff.compareTo(tollerance) == -1) {
                break;
            }
        }
        return Math_BigDecimal.multiplyPriorRound(ex, r, oom, rm);
    }

    /**
     * Calculates and returns the exponent of x:
     * <ul>
     * <li>e^x = 1 + x/1! + x^2/2! + x^3/3! +...</li>
     * </ul>
     *
     * @param x The exponent.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return e^x where e is the Euler constant to a sufficient precision to
     * return the result accurate to the requested {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    protected BigDecimal exp(BigInteger x, int oom, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigInteger.ZERO) == -1) {
            return Math_BigDecimal.reciprocal(exp(x.negate(), oom - 3, RoundingMode.DOWN), oom, rm);
        }
        BigDecimal r = BigDecimal.ZERO;
        if (x.compareTo(BigInteger.valueOf(999999999)) != 1
                && x.compareTo(BigInteger.ZERO) != -1) {
            int xi = x.intValueExact();
            return power(getE(oom - xi - 1, rm), xi, oom, rm);
        } else {
            ArrayList<BigDecimal> rp = new ArrayList<>();
            BigDecimal rpp = getE(oom - Math_BigInteger.log10(x), RoundingMode.DOWN);
            BigInteger c = BigInteger.valueOf(2);
            BigInteger cl = BigInteger.ZERO;
            while (c.compareTo(x) != 1) {
                BigDecimal m = rpp.multiply(rpp);
                rp.add(m);
                r = m;
                cl = c;
                c = c.multiply(c);
            }
            if (cl.compareTo(x) != 0) {
                for (int i = rp.size() - 1; i == 0; i--) {
                    BigInteger pow = BigInteger.valueOf(2).pow(i);
                    if (cl.compareTo(pow) == 1) {
                        cl = cl.subtract(pow);
                        r = r.multiply(rp.get(i));
                    }
                    if (cl.compareTo(BigInteger.ZERO) != 0) {
                        break;
                    }
                }

            }
        }
        return Math_BigDecimal.round(r, oom, rm);
    }

//    /**
//     * This method was not necessary. However something like it might be 
//     * useful for none Euler constant based exponentiation so for the time 
//     * being the code is just commented out rather than deleted. If the power
//     * methods are fully operational, this commented code will be obsolete.
//     * @param y 0 < y < 1
//     * @param decimalPlaces
//     * @return e^y accurate to decimalPlaces
//     */
//    private static BigDecimal expLessThanOne(
//            BigDecimal y,
//            Math_BigDecimal a_Generic_BigDecimal,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        BigDecimal x0 = new BigDecimal(a_Generic_BigDecimal._E.toString());
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal root;
//        BigDecimal rootMultiple;
//        int maxite = x0.precision();
//        result = BigDecimal.ONE;
//        while (true) {
//        for (int i = 0; i < maxite; i++) {
//            element = floorSignificantDigit(x0);
//            elementUnscaled = element.unscaledValue();
//            System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divideRoundIfNecessary(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
//                    break;
//                }
//                System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                root = root(
//                        y,
//                        elementOneReciprocal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (root.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            root,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces, // @TODO Is this sufficient?
//                            a_RoundingMode);
//                    result = multiplyRoundIfNecessary(
//                            result,
//                            rootMultiple,
//                            decimalPlaces + 3, // @TODO Is this sufficient or ott?
//                            a_RoundingMode);
//                }
//            }
//            x0 = x0.subtract(element);
//        }
//        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
//    }
    /**
     * Calculate and return the natural logarithm of x. This uses Newton's 
     * algorithm adapted from
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     *
     * @param x The number for which the natural logarithm is calculated.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The {@link RoundingMode} used to round intermediate results
     * and the final result.
     * @return The natural logarithm of x accurate to decimalPlaces number of
     * decimal places and with result rounded using a_RoundingMode if necessary.
     */
    public BigDecimal ln(BigDecimal x, int oom, RoundingMode rm) {
        // Check that x > 0.
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException(
                    "x <= 0 in " + BigDecimal.class.getName()
                    + "ln(BigDecimal,Generic_BigDecimal,int,RoundingMode)");
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ZERO;
        }
        // Number of digits left of decimal point
        int oommx1 = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x) + 1;
        if (oommx1 < 2) {
            return lnNewton(x, oom, rm);
        } else {
            // Compute magnitude*ln(x^(1/magnitude)).
            // x^(1/magnitude)
            //int oomr = oom + (int) (oom / Math.log10(Math.abs(oom)));
            //BigDecimal root = root(x, oommx1, oomr, rm);
            //BigDecimal root = root(x, oommx1, oom - 1, RoundingMode.DOWN);
            BigDecimal root = root(x, oommx1, oom - 1, rm);
            // ln(x^(1/magnitude))
            //int ooml = oom - 6 - oommx1;
            int ooml = oom - 3 - oommx1;
            BigDecimal lnRoot = lnNewton(root, ooml, RoundingMode.DOWN);
            return Math_BigDecimal.multiplyPriorRound(lnRoot,
                    BigDecimal.valueOf(oommx1), oom, rm);
//            return Math_BigDecimal.multiply(lnRoot,
//                    BigDecimal.valueOf(oommx1), oom, rm);
        }
    }

    /**
     * Compute and return the natural logarithm of x rounded down to oom
     * precision.
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     */
    private BigDecimal lnNewton(BigDecimal x, int oom, RoundingMode rm) {
        BigDecimal r = new BigDecimal(x.toString());
        int sp1 = oom - 3;
        //int sp1 = oom + (int) (oom / Math.log10(Math.abs(oom)));
        BigDecimal term;
        // Convergence tolerance = 1*(10^-(scale+1))
        BigDecimal tolerance = BigDecimal.valueOf(1).movePointRight(sp1);
        sp1--;
        do {
            // e^r
            BigDecimal exp = exp(r, sp1, RoundingMode.DOWN);
            // (e^r - x)/e^r
            term = divide(exp.subtract(x), exp, sp1, RoundingMode.DOWN);
            r = Math_BigDecimal.addPriorRound(r, term.negate(), sp1, RoundingMode.DOWN);
            //System.out.println(r);
        } while (term.abs().compareTo(tolerance) > 0);
        return round(r, oom, rm);
    }

    /**
     * @param x The values.
     * @return The minimum of all the values.
     */
    public static BigDecimal min(BigDecimal... x) {
        BigDecimal r = x[0];
        for (BigDecimal b : x) {
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
    public static BigDecimal min(Collection<BigDecimal> c) {
        return c.parallelStream().min(Comparator.comparing(i -> i)).get();
    }

    /**
     *
     * @param x The values.
     * @return The maximum of all the values.
     */
    public static BigDecimal max(BigDecimal... x) {
        BigDecimal r = x[0];
        for (BigDecimal b : x) {
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
    public static BigDecimal max(Collection<BigDecimal> c) {
        return c.parallelStream().max(Comparator.comparing(i -> i)).get();
    }

    /**
     * Calculate and return the closer to negative infinity value of {@code x}
     * where the non-zero digit with the largest
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> (OOM) is lowered by {@code 1}. Trailing zeros are stripped
     * from the result. Examples
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>floorSignificantDigit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>0.0001</td><td>0.0001</td></tr>
     * <tr><td>0.00012</td><td>0.0001</td></tr>
     * <tr><td>0.0009</td><td>0.0009</td></tr>
     * <tr><td>1.00099</td><td>1</td></tr>
     * <tr><td>10008798.00099</td><td>10000000</td></tr>
     * <tr><td>-1.00099</td><td>2</td></tr>
     * <tr><td>-10008798.00099</td><td>-20000000</td></tr>
     * <tr><td>-0.00099</td><td>-0.001</td></tr>
     * <tr><td>-0.99</td><td>-1</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The value to calculate the floor of.
     * @return The next closer to negative infinity value at the OOM of the most
     * significant digit.
     */
    public static BigDecimal floorSignificantDigit(BigDecimal x) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r;
            //r = floorSignificantDigit(x.negate());
            r = ceilingSignificantDigit(x.negate());
            return r.negate();
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal r;
        BigInteger xu = x.unscaledValue();
        String xus = xu.toString();
        int scale = x.scale() - xus.length() + 1;
        int biggest = Integer.valueOf(xus.substring(0, 1));
        if (biggest == 0) {
            r = new BigDecimal(BigInteger.ONE, scale);
        } else {
            r = new BigDecimal(BigInteger.valueOf(biggest), scale);
        }
        return r.stripTrailingZeros();
    }

    /**
     * Calculate and return the closer to positive infinity value of {@code x}
     * where the non-zero digit with the largest
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> (OOM) is increased by {@code 1}. Trailing zeros are
     * stripped from the result. Examples
     * <table>
     * <caption>Examples</caption>
     * <thead>
     * <tr><td>x</td><td>floorSignificantDigit</td></tr>
     * </thead>
     * <tbody>
     * <tr><td>0.0001</td><td>0.0002</td></tr>
     * <tr><td>0.00012</td><td>0.0002</td></tr>
     * <tr><td>0.0009</td><td>0.001</td></tr>
     * <tr><td>1.00099</td><td>2</td></tr>
     * <tr><td>10008798.00099</td><td>20000000</td></tr>
     * <tr><td>-1.00099</td><td>-1</td></tr>
     * <tr><td>-10008798.00099</td><td>-10000000</td></tr>
     * <tr><td>-0.00099</td><td>-0.0009</td></tr>
     * <tr><td>-0.99</td><td>-0.9</td></tr>
     * </tbody>
     * </table>
     *
     * @param x The value to calculate the floor of.
     * @return The next further away from negative infinity value at the OOM of
     * the most significant digit.
     */
    public static BigDecimal ceilingSignificantDigit(BigDecimal x) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r;
            //r = ceilingSignificantDigit(x.negate());
            r = floorSignificantDigit(x.negate());
            return r.negate();
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal r;
        BigInteger xu = x.unscaledValue();
        String xus = xu.toString();
        int scale = x.scale() - xus.length() + 1;
        int biggest = Integer.valueOf(xus.substring(0, 1));
        biggest++;
        r = new BigDecimal(BigInteger.valueOf(biggest), scale);
        return r.stripTrailingZeros();
    }

    /**
     * Calculates and returns the n-th root of x.
     *
     * @param x The value to be rooted.
     * @param n The root (2 is for a square root, 3 is for a cube root etc.).
     * @param oom The order of magnitude that the result is calculated to.
     * @return The nth root of x calculated to the oom level of precision.
     */
    public static BigDecimal root(BigDecimal x, int n,
            int oom) {
        return root(x, n, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculates and returns the root-th root of x.
     *
     * @param x The value to be rooted.
     * @param n The root (2 is for a square root, 3 is for a cube root etc.).
     * @param oom The order of magnitude that the result is calculated to.
     * @param rm The {@link RoundingMode} used to roundDown the final result.
     * @return The nth root of x which is either exact or rounded to the oom
     * level of precision.
     */
    public static BigDecimal root(BigDecimal x, int n,
            int oom, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            // Complex roots are not handled!
            throw new IllegalArgumentException(
                    "x <= 0 in " + Math_BigDecimal.class
                    + ".root(BigDecimal,BigInteger,int,RoundingMode)");
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // root = 1
        if (n == 1) {
            BigDecimal r = new BigDecimal(x.toString());
            if (r.scale() > -oom) {
                return roundDown(r, oom);
            }
            return r;
        }
        // x < 1
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            // Optimisation (untested if it is faster) for a large x.scale()
            int rootLength = Integer.toString(n).length();
            if (xscale < 10) {
                BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                // numerator will be greater than denominator and both will
                // be close to a value of 1, a large number of decimal 
                // places for both numerator and denominator are required!
                BigDecimal denominator = Math_BigDecimal.root(
                        denominatorUnrooted, n,
                        oom - xscale - rootLength, // Can we cope with less or do we need more?
                        RoundingMode.DOWN);
                //int denominatorScale = denominator.scale();
                BigDecimal numerator = Math_BigDecimal.root(
                        numeratorUnrooted, n,
                        oom - xscale - rootLength, // Can we cope with less or do we need more?
                        RoundingMode.DOWN);
                return Math_BigDecimal.divide(numerator, denominator, oom, rm);
            } else {
                return rootLessThanOne(x, n, oom, rm);
            }
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            // This is thought to be an optimisation for a large x.scale()
            // a faster way is needed! This may generally be generally 
            // faster anyway but some tests are needed to be sure.
            int rootLength = Integer.toString(n).length();
            if (xscale < 10) {
                BigDecimal xu = new BigDecimal(x.unscaledValue());
                BigDecimal du = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal num = root(xu, n,
                        oom - xscale - rootLength, // Can we cope with less or do we need more?
                        RoundingMode.DOWN);
                BigDecimal denominator = root(du, n,
                        oom - xscale - rootLength, // Can we cope with less or do we need more?
                        RoundingMode.DOWN);
                return Math_BigDecimal.divide(num, denominator, oom, rm);
            } else {
                return rootLessThanOne(x, n, oom, rm);
            }
        }
        // x > 1
        BigDecimal epsilon = new BigDecimal(BigInteger.ONE, 1 - oom);
        BigDecimal comparator = BigDecimal.ONE.subtract(epsilon);
        // Check for root in precision and return 1 if not.
        BigInteger rootbi = BigInteger.valueOf(n);
        boolean powerTest = powerTestAbove(x, comparator, rootbi, oom, rm);
        if (powerTest) {
            System.out.println("No root in the precision... ");
            //return BigDecimal.ZERO;
            return BigDecimal.ONE;
        }
        BigDecimal r = rootInitialisation(x, n, epsilon, 10, oom, RoundingMode.DOWN);
        return newtonRaphson(x, r, n, epsilon, oom, rm);
    }

//    /**
//     * Calculates and returns the root-th root of x.
//     *
//     * @param x The value to be rooted.
//     * @param root The root.
//     * @return The root-th root of x.
//     */
//    public static BigDecimal rootNoRounding(BigDecimal x, int root) {
//        // The current (Java6) limit for n in x.pow(n)
//        // for BigDecimal x and int n is
//        // -999999999 < n < 999999999
//        if (root.compareTo(BigInteger.valueOf(999999999)) != 1) {
//            BigDecimal r = rootNoRounding(x, root.intValue());
//            return r;
//        } else {
//            // Deal with special cases
//            // x <= 0
//            if (x.compareTo(BigDecimal.ZERO) != 1) {
//                // Complex roots are not handled!
//                throw new IllegalArgumentException(
//                        "x <= 0 in " + Math_BigDecimal.class
//                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
//            }
//            // x = 1
//            if (x.compareTo(BigDecimal.ONE) == 0) {
//                return BigDecimal.ONE;
//            }
//            // root = 1
//            if (root.compareTo(BigInteger.ONE) == 0) {
//                BigDecimal result = new BigDecimal(x.toString());
//                return result;
//            }
//            // x < 1
//            if (x.compareTo(BigDecimal.ONE) == -1) {
//                int xscale = x.scale();
//                // Optimisation (untested if it is faster) for a large x.scale()
//                if (xscale < 10) {
//                    BigDecimal nu = new BigDecimal(x.unscaledValue());
//                    BigDecimal du = new BigDecimal(BigInteger.ONE, (-1 * xscale));
//                    // numerator will be greater than denominator and both will
//                    // be close to a value of 1, a large number of decimal 
//                    // places for both numerator and denominator are required!
//                    BigDecimal d = rootNoRounding(du, root);
//                    BigDecimal n = rootNoRounding(nu, root);
//                    return Math_BigDecimal.divideNoRounding(n, d);
//                } else {
//                    return rootLessThanOneNoRounding(x, root);
//                }
//            }
//            BigDecimal r = rootInitialisationNoRounding(x, root, 10);
//            return newtonRaphsonNoRounding(x, r, root);
//        }
//    }
    /**
     * Uses MathContext division
     *
     * @param x
     * @param r0 The result to modify.
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param oom
     * @param rm RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphson(BigDecimal x, BigDecimal r0,
            int root, BigDecimal epsilon, int oom, RoundingMode rm) {
        RoundingMode rmdou;
        if (x.compareTo(BigDecimal.ONE) == 1) {
            rmdou = RoundingMode.DOWN;
        } else {
            rmdou = RoundingMode.UP;
        }
        BigDecimal rootbd = new BigDecimal(root);
        //int oommsdx = getOrderOfMagnitudeOfMostSignificantDigit(x);
        //int oomp = oom - oommsdx - 5;
        //int oomd = oomp - oommsdx - 10;
        int oomp = oom - 5;
        int oomd = oomp - 10;
        int ooma = oom - Integer.toString(root).length() - 2;

        BigDecimal r = new BigDecimal(r0.toString());
        BigDecimal rpowroot;
        BigDecimal rpowrootsub1;
        BigDecimal rpowrootsubx;
        BigDecimal divisor;
        int rootsub1 = root - 1;

        int maxdiv = 2;
        while (maxdiv < root) {
            maxdiv *= 2;
        }

        //int div = 64;
        for (int div = 64; div >= 1; div /= 2) {
            //System.out.println("div=" + div);
            BigDecimal divbd = BigDecimal.valueOf(div);
            // Newton Raphson
            while (true) {
                rpowroot = power(r, root, oomp, rmdou);
                rpowrootsub1 = power(r, rootsub1, oomp, rmdou);
                divisor = rpowrootsub1.multiply(rootbd);
                if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //rpowrootsubx = rpowroot.subtract(x);
                rpowrootsubx = rpowroot.subtract(x.multiply(divbd));
                if (rpowrootsubx.abs().compareTo(epsilon) == -1) {
                    break;
                }
                BigDecimal rpowrootsubxd = Math_BigDecimal.divideNoCaseCheck(
                        rpowrootsubx, divisor, oomd, rmdou);
                if (rpowrootsubxd.abs().compareTo(epsilon) == -1) {
                    break;
                }
                r = Math_BigDecimal.addPriorRound(r, rpowrootsubxd.negate(), ooma);
                //System.out.println(r.toPlainString());
                //r = r.subtract(rpowrootsubxd);
            }
        }
        return round(r, oom, rm);
    }

    /**
     * Calculates and returns the {@code n}th root of {@code x} without
     * rounding. The problem here is that
     *
     * @param x The number to calculate and return the {@code n}th root of.
     * @param r0 A potential answer to use to begin an iterative approximation.
     * @param n The root.
     * @return The {@code n}th root of {@code x}.
     */
    private static BigDecimal newtonRaphsonNoRounding(BigDecimal x,
            BigDecimal r0, BigInteger n) {
        BigDecimal rootbd = new BigDecimal(n);
        BigDecimal r = new BigDecimal(r0.toString());
        BigDecimal rpowroot;
        BigDecimal rpowrootsub1;
        BigDecimal divisor;
        BigDecimal rpowrootsubx;
        BigInteger rootsub1 = n.subtract(BigInteger.ONE);
        int oomx = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        int oomxN2 = oomx - 2;
        int oomxN3 = oomx - 3;
        // Newton Raphson
        while (true) {
            rpowroot = powerNoRounding(r, n);
            rpowrootsub1 = powerNoRounding(r, rootsub1);
            divisor = rpowrootsub1.multiply(rootbd);
            if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            rpowrootsubx = rpowroot.subtract(x);
            if (rpowrootsubx.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            r = roundDown(r.subtract(divide(rpowrootsubx, divisor, oomxN3,
                    RoundingMode.DOWN)), oomxN2);
            //r = r.subtract(divide(rpowrootsubx, divisor, -1, RoundingMode.UP));
            //r = r.subtract(rpowrootsubx.divide(divisor));
            if (r0.compareTo(r) == 0) {
                if (rpowroot.compareTo(x) != 0) {
                    return null;
                } else {
                    return r;
                }
            }
            r0 = r;
        }
        return r;
    }

    private static BigDecimal rootInitialisation(BigDecimal x, int root,
            BigDecimal epsilon, int maxite, int oom, RoundingMode rm) {
        BigDecimal r;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOne(x, root, epsilon,
                    maxite, oom, rm);
        } else {
            int i = 0;
            r = BigDecimal.ONE.add(epsilon);
            boolean powerTestAbove = powerTestAbove(x, r, root, oom, RoundingMode.DOWN);
            if (powerTestAbove) {
                // Root cannot be found within current precision...
                return BigDecimal.ONE; // Debug...
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;
            while (i < maxite) {
                if (root == 0) {
                    return r;
                }
                // Disect
                c = divide(a.subtract(b), BigInteger.valueOf(root), oom - 1, RoundingMode.DOWN);
                c = b.add(c);
                powerTestAbove = powerTestAbove(x, c, root, oom, RoundingMode.DOWN);
                if (powerTestAbove) {
                    a = c;
                } else {
                    b = c;
                    root /= 2;
                }
                // Bisect
                c = divide(a.subtract(b), TWO, oom - 1, RoundingMode.DOWN);
                c = c.add(b);
                powerTestAbove = powerTestAbove(x, c, root, oom, RoundingMode.DOWN);
                if (powerTestAbove) {
                    a = c;
                } else {
                    b = c;
                    r = b;
                }
                i++;
            }
        }
        return r;
    }

    /**
     * This has been deprecated as for many inputs, the result is irrational or
     * cannot be expressed as a BigDecimal exactly and so this enters an
     * infinite loop.
     *
     * @param x The number to root.
     * @param root The root.
     * @param maxite The maximum number of iterations.
     * @return Either an exact or approximate root of x.
     */
    @Deprecated
    private static BigDecimal rootInitialisationNoRounding(BigDecimal x,
            int root, int maxite) {
        BigDecimal r;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOneNoRounding(x, root, maxite);
        } else {
            int i = 0;
            r = BigDecimal.ONE;
            BigInteger rootbi = BigInteger.valueOf(root);
            if (powerTestAboveNoRounding(x, r, rootbi)) {
                // Root cannot be found within current precision...
                return BigDecimal.ONE; // Debug...
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;
            while (i < maxite) {
                // Disect
                //c = divideNoRounding(a.subtract(b), rootbi);
                if (rootbi.compareTo(BigInteger.ZERO) == 0) {
                    return r;
                }
                c = divide(a.subtract(b), rootbi, 0, RoundingMode.DOWN);
                //c = divide(a.subtract(b), rootbi, 0, RoundingMode.UP);
                c = b.add(c);
                if (powerTestAboveNoRounding(x, c, rootbi)) {
                    a = c;
                } else {
                    b = c;
                    rootbi = rootbi.divide(BigInteger.TWO);
                    r = b;
                }
                // Bisect
                c = divideNoRounding(a.subtract(b), TWO);
                c = c.add(b);
                if (powerTestAboveNoRounding(x, c, rootbi)) {
                    a = c;
                } else {
                    b = c;
                    r = b;
                }
                i++;
            }
        }
        return r;
    }

    /**
     * This has been deprecated as for many inputs, the result is irrational or
     * cannot be expressed as a BigDecimal exactly and so this enters an
     * infinite loop.
     *
     * For calculating the {@code root}th root of {@code x} without rounding.
     *
     * @param x The value to calculate and return the root for.
     * @param root The root.
     * @return The {@code root}th root of {@code x}
     */
    @Deprecated
    public static BigDecimal rootNoRounding(BigDecimal x, int root) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException(
                    "x < 0 in " + Math_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (root == 0) {
            throw new IllegalArgumentException(
                    "root = 0 in " + Math_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (root == 1) {
            return new BigDecimal(x.toString());
        }
//        /**
//         * The current (Java6) limit for n in x.pow(n) for BigDecimal x and int
//         * n is {@code -999999999 < n < 999999999}
//         */
//        if (root >= 999999999) {
//            return rootNoRounding(x, root);
//        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            /* This is thought to be an optimisation for a large x.scale()
            * a faster way is needed! This may  be generally 
            * faster anyway but some tests are needed to be sure.
             */
            if (xscale < 10) {
                BigDecimal xu = new BigDecimal(x.unscaledValue());
                //int precisionMinusScale = xprecision - xscale;
                BigDecimal du = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal num = rootNoRounding(xu, root);
                BigDecimal den = rootNoRounding(du, root);
                return divideNoRounding(num, den);
            } else {
                BigDecimal r = rootLessThanOneNoRounding(x, root);
                return r;
            }
        }
        BigInteger rootbi = BigInteger.valueOf(root);
        BigDecimal r = rootInitialisationNoRounding(x, root, 10);
        return newtonRaphsonNoRounding(x, r, rootbi);
    }

    /**
     * This has been deprecated as for many inputs, the result is irrational or
     * cannot be expressed as a BigDecimal exactly and so this enters an
     * infinite loop.
     *
     * @param x {@code 0 < x < 1}
     * @param root
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return The root root of x to decimalPlaces precision.
     */
    @Deprecated
    private static BigDecimal rootLessThanOneNoRounding(BigDecimal x, int root) {
        BigDecimal r = rootInitialisationLessThanOneNoRounding(x, root, 10);
        return newtonRaphsonLessThanOneNoRounding(x, r, root);
    }

    /**
     * Returns the root-th root of x.
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     *
     * @param x The value to calculate the root of. This value must be between
     * {@code 0} and {@code 1}.
     * @param root The root to take.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return The root-th root of x.
     */
    private static BigDecimal rootLessThanOne(BigDecimal x, int root,
            int oom, RoundingMode rm) {
        BigDecimal epsilon = new BigDecimal(BigInteger.ONE, -oom);
//        // Check there is a root in the precision and return 1 if not
//        boolean powerTest = powerTestBelow(epsilon, x, root, 256, oom, rm);
//        if (powerTest) {
//            System.out.println("No root in the precision... ");
//            return BigDecimal.ONE;
//        }
        BigDecimal r;// = BigDecimal.ONE;
        if (x.scale() - x.precision() > -oom) {
            r = BigDecimal.ONE.subtract(epsilon);
        } else {
            int rootInitialisationMaxite = 10;
            //int rootInitialisationMaxite = x.scale();
            r = rootInitialisationLessThanOne(x, root, epsilon,
                    rootInitialisationMaxite, oom, rm);
        }
        return newtonRaphsonLessThanOne(x, r, root, epsilon, oom, rm);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     * https://en.wikipedia.org/wiki/Shifting_nth_root_algorithm
     * <ul>
     * <li>Make an initial guess</li>
     * <li>Do some calculations</li>
     * <li>Iterate...</li>
     * </ul>
     *
     * @param x The value to root.
     * @param r0 Estimate of result.
     * @param root The root to calculate.
     * @param epsilon Amount of difference deemed significant.
     * @param maxite The maximum number of iterations to iterate.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonLessThanOne(BigDecimal x,
            BigDecimal r0, int root, BigDecimal epsilon, int oom, RoundingMode rm) {
        BigDecimal r = new BigDecimal(r0.toString());
        BigInteger rootbi = BigInteger.valueOf(root);
        //int oommsdx  = getOrderOfMagnitudeOfMostSignificantDigit(x);
        //int oomp = oom - oommsdx - 5;
        //int oomd = oomp - oommsdx - 10;
        int oomp = oom - 5;
        int oomd = oomp - 10;
        int ooma = oom - Integer.toString(root).length() - 2;

        BigDecimal rootsub1 = BigDecimal.valueOf(root - 1);
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal rpowrootsub1;
        BigDecimal xdivrpowrootsub1;
        BigDecimal inside;

        // Newton Raphson
        while (true) {
            p = r.multiply(rootsub1);
            rpowrootsub1 = Math_BigDecimal.power(r, rootsub1,
                    oomp - root, RoundingMode.DOWN);
            if (rpowrootsub1.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            xdivrpowrootsub1 = Math_BigDecimal.divide(
                    x, rpowrootsub1, oomd, RoundingMode.DOWN);
            inside = Math_BigDecimal.add(p, xdivrpowrootsub1, ooma, RoundingMode.DOWN);
            if (inside.abs().compareTo(epsilon) == -1) {
                break;
            }
            r = Math_BigDecimal.divide(inside, rootbi, oomd, RoundingMode.DOWN);
        }
        return roundDown(r, oom);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm # Make an initial guess
     * x0 # Set x_{k+1} = \frac{1}{n} \left[{(n-1)x_k
     * +\frac{A}{x_k^{n-1}}}\right] Uses MathContext division
     *
     * @param x The value to root.
     * @param r0 Estimate of result.
     * @param root The root to calculate.
     * @param epsilon Amount of difference deemed significant.
     * @param maxite The maximum number of iterations to iterate.
     * @return Re-approximation of result0 using Newton Raphson method.
     */
    private static BigDecimal newtonRaphsonLessThanOneNoRounding(
            BigDecimal x, BigDecimal r0, int root) {
        BigDecimal r = new BigDecimal(r0.toString());
        BigInteger rootbi = BigInteger.valueOf(root);
        BigDecimal rootsub1 = BigDecimal.valueOf(root - 1);
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal rpowrootsub1;
        BigDecimal xdivrpowrootsub1;
        BigDecimal inside;
        // Newton Raphson
        while (true) {
            p = r.multiply(rootsub1);
            rpowrootsub1 = Math_BigDecimal.powerNoRounding(r, rootsub1);
            if (rpowrootsub1.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            xdivrpowrootsub1 = Math_BigDecimal.divideNoRounding(x, rpowrootsub1);
            inside = p.add(xdivrpowrootsub1);
            if (inside.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            r = divideNoRounding(inside, rootbi);
        }
        return r;
    }

//    private static BigDecimal rootInitialisation(
//            BigDecimal x,
//            int root,
//            //BigDecimal root_BigDecimal,
//            BigDecimal maxError_BigDecimal,
//            BigDecimal a0,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        // Initialise toCompare and previousResult_BigDecimal
//        if (x.compareTo(BigDecimal.ONE) == -1) {
//            return rootInitialisationLessThanOne(
//                    x,
//                    root,
//                    //root_BigDecimal,
//                    maxError_BigDecimal,
//                    a0,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
//            result = BigDecimal.ONE.add(maxError_BigDecimal);
//            BigDecimal a = x.divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//            if (a.compareTo(BigDecimal.ONE) == 1) {
//                result = BigDecimal.ONE.add(maxError_BigDecimal);
//                // Disect
//                BigDecimal b = a0;
//                BigDecimal c;
//                //BigDecimal cPowerRoot;
//                boolean cPowerRootTest;
//                boolean notInitialised = true;
//                while (notInitialised) {
//                    c = (a.add(b)).divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//                    cPowerRootTest = powerTestAbove(
//                            x,
//                            c,
//                            root,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            root,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    if (cpowerroot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        // Bisect
//                        c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                        cPowerRootTest = powerTestAbove(
//                                x,
//                                c,
//                                root,
//                                64,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRootTest) {
////                        cpowerroot = power(
////                            c,
////                            root,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                        if (cpowerroot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            result = c;
//                            notInitialised = false;
//                        }
//                    }
//                }
//            } else {
//                boolean resultpowerroottest = powerTestAbove(
//                        x,
//                        result,
//                        root,
//                        64,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (resultpowerroottest) {
//                    // Root cannot be found within current precision...
//                    // return BigDecimal.ZERO or BigDecimal.ONE?
//                    int debug = 1;
//                    return BigDecimal.ONE;
//                }
////                BigDecimal resultpowroot = power(
////                        result,
////                        root,
////                        64,
////                        decimalPlaces,
////                        a_RoundingMode);
////                if (resultpowroot.compareTo(x) == 1) {
////                    // Root cannot be found within current precision...
////                    // return BigDecimal.ZERO or BigDecimal.ONE?
////                    int debug = 1;
////                }
//                a = x.divide(TWO, decimalPlaces, a_RoundingMode);
//                BigDecimal b = new BigDecimal(result.toString());
//                BigDecimal c;
//                //BigDecimal cPowerRoot;
//                boolean cPowerRootTest;
//                BigDecimal aAddb;
//                boolean notInitialised = true;
//                int maxite = 1000;
//                int i = 0;
//                while (notInitialised) {
//                    if (i == maxite) {
//                        notInitialised = false;
//                    }
//                    // Bisect
//                    aAddb = a.add(b);
//                    c = (aAddb).divide(TWO, decimalPlaces, a_RoundingMode);
//                    cPowerRootTest = powerTestAbove(
//                            x,
//                            c,
//                            root,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            root,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    if (cpowerroot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        //result = c;
//                        notInitialised = false;
//                    }
//                    result = a;
//                    i++;
//                }
//            }
//        }
//        return result;
//    }
    /**
     * Calculate and return the root-th root of x.
     *
     * @param x The value to root. Which is expected to be between 0 and 1.
     * @param root The root to take.
     * @param epsilon Amount of difference deemed significant.
     * @param maxite The maximum number of iterations to iterate.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return Tthe root-th root of x.
     */
    private static BigDecimal rootInitialisationLessThanOne(BigDecimal x,
            int root, BigDecimal epsilon, int maxite,
            int oom, RoundingMode rm) {
        BigInteger rootbi = BigInteger.valueOf(root);
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal r = BigDecimal.ONE.subtract(epsilon);
        BigDecimal a = new BigDecimal(x.toString());
        b = r;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger rootdiv = new BigInteger(rootbi.toString(maxite));
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsa = b.subtract(a);
            c = divide(bsa, rootbi, oom - 1, rm);
            c = b.subtract(c);
            boolean powerTestBelow = powerTestBelow(x, c, rootbi, oom, rm);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                rootdiv = rootdiv.divide(BigInteger.TWO);
                r = b;
            }
            // Bisect;
            c = divide(b.subtract(a), TWO, oom - 1, rm);
            c = b.subtract(c);
            powerTestBelow = powerTestBelow(x, c, rootbi, oom, rm);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                r = b;
            }
            i++;
        }
        return r;
    }

    /**
     * This has been deprecated as for many inputs, the result is irrational or
     * cannot be expressed as a BigDecimal exactly and so this enters an
     * infinite loop.
     *
     * @param x 0 < x < 1
     * @param root_BigDecimal
     * @param maxite
     * @return
     */
    @Deprecated
    private static BigDecimal rootInitialisationLessThanOneNoRounding(
            BigDecimal x, int root, int maxite) {
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal r = BigDecimal.ONE;
        BigDecimal a = new BigDecimal(x.toString());
        b = r;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger root0 = BigInteger.valueOf(root);
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsubtracta = b.subtract(a);
            c = divideNoRounding(bsubtracta, root0);
            c = b.subtract(c);
            if (powerTestBelowNoRounding(x, c, root0)) {
                a = c;
            } else {
                b = c;
                root0 = root0.divide(BigInteger.TWO);
                r = b;
            }
            // Bisect;
            c = divideNoRounding(b.subtract(a), TWO);
            c = b.subtract(c);
            if (powerTestBelowNoRounding(x, c, root0)) {
                a = c;
            } else {
                b = c;
                r = b;
            }
            i++;
        }
        return r;
    }

    /**
     * @param x The value for which the square root is wanted.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode}.
     * @return a MathContext appropriate for calculating a square root to the
     * precision given by {@code oom} and {@code rm}.
     */
    private static MathContext getSqrtMathContext(BigDecimal x, int oom,
            RoundingMode rm) {
        return new MathContext((int) Math.ceil(
                Math.sqrt((double) x.precision())) - oom, rm);
    }

    /**
     * @param x The value for which the square root is returned.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode}.
     * @return Square root of {@code x} as a BigDecimal if this can be returned
     * exactly at the {@code oom} precision and {@code null} otherwise.
     */
    public static BigDecimal sqrt(BigDecimal x, int oom, RoundingMode rm) {
        //return root(x, 2, oom, rm);
        int c = x.compareTo(BigDecimal.ONE);
        switch (c) {
            case 0:
                return BigDecimal.ONE;
            case -1:
                if (oom >= 0) {
                    return BigDecimal.ZERO;
                } else {
                    int p = Math.max(x.scale(), -oom);
                    return round(x.sqrt(new MathContext(p, rm)), oom, rm);
//                    BigDecimal r = x.sqrt(new MathContext(p, rm));
//                    if (r.pow(2).compareTo(x) == 0) {
//                        return r;
//                    } else {
//                        return null;
//                    }
                }
            default:
                //BigDecimal r = x.sqrt(new MathContext(x.scale() + 2 - oom, rm));
                return round(x.sqrt(getSqrtMathContext(x, oom, rm)), oom, rm);
//                BigDecimal r = x.sqrt(getSqrtMathContext(x, oom, rm));
//                if (r.pow(2).compareTo(x) == 0) {
//                    return r;
//                } else {
//                    return null;
//                }
        }
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param rand The {@link Random} to use.
     * @param p The probability. Expected to be in the range [0,1].
     * @param oom The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return true or false based on a random uniform test of probability
     */
    public boolean randomUniformTest(Random rand, BigDecimal p, int oom,
            RoundingMode rm) {
        // Special case probability <= 0
        if (p.compareTo(BigDecimal.ZERO) != 1) {
            if (p.compareTo(BigDecimal.ZERO) == 0) {
                return false;
            } else {
                System.out.println("Warning probabilty negative in "
                        + Math_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning false.");
                return false;
            }
        }
        // Special case probability >= 1
        if (p.compareTo(BigDecimal.ONE) != -1) {
            if (p.compareTo(BigDecimal.ONE) == 0) {
                return true;
            } else {
                System.out.println("Warning probabilty greater > 1 in "
                        + Math_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning true.");
                return true;
            }
        }
        // Set decimalPlace precision to maximum of decimalPlaces and
        // probability.scale();
        int probabilityScale = p.scale();
        if (-oom < probabilityScale) {
//            System.out.println(
//                    "Input decimalPlaces < probabilty.scale() in "
//                    + Math_BigDecimal.class
//                    + ".randomUniformTest(Random,BigDecimal). "
//                    + "Set decimalPlaces = probabilty.scale().");
            oom = -probabilityScale;
        }
        BigDecimal midTestValue = new BigDecimal("0.5");
        if (p.compareTo(midTestValue) == 0) {
            return rand.nextBoolean();
        }
        if (p.compareTo(midTestValue) == -1) {
            return randomTest(rand, p, BigDecimal.ZERO,
                    BigDecimal.ONE, midTestValue,
                    oom, rm);
        } else {
            return !randomTest(rand, BigDecimal.ONE.subtract(p),
                    BigDecimal.ZERO, BigDecimal.ONE, midTestValue, oom, rm);
        }
    }

    private boolean randomTest(Random rand, BigDecimal probability,
            BigDecimal mintv, BigDecimal maxtv, BigDecimal midtv,
            int oom, RoundingMode rm) {
        if (probability.compareTo(midtv) == 0) {
            return rand.nextBoolean();
        }
        boolean above = rand.nextBoolean();
        if (above) {
            if (probability.compareTo(midtv) == 1) {
                // Test
                BigDecimal maxtv0 = midtv;
                BigDecimal midtv0 = divide(maxtv0.add(maxtv),
                        Math_BigDecimal.TWO, oom, rm);
                return randomTest(rand, probability, maxtv0, maxtv, midtv0,
                        oom, rm);
            } else {
                return false;
            }
        } else {
            if (probability.compareTo(midtv) == 1) {
                return true;
            } else {
                //Test
                BigDecimal maxtv0 = midtv;
                BigDecimal midtv0 = divide(maxtv0.add(mintv),
                        Math_BigDecimal.TWO, oom, rm);
                return randomTest(rand, probability, mintv, maxtv0, midtv0,
                        oom, rm);
            }
        }
    }

    /**
     * For assessing if x is even or odd.
     *
     * @param x The number to test whether or not it is even.
     * @return true iff last digit of x is even
     */
    public static boolean isEven(BigDecimal x) {
        String xS = x.toPlainString();
        String xSLastDigit = xS.substring(xS.length() - 1);
        return Math_Integer.isEven(Integer.valueOf(xSLastDigit));
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the cosine is returned.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return The cosine of x.
     */
    public BigDecimal cos(BigDecimal x, int oom, RoundingMode rm) {
        // cosx = 1-(x^2)/(2!)+(x^4)/(4!)-(x^6)/(6!)+...
        BigDecimal precision = new BigDecimal(BigInteger.ONE, 2 - oom);
        BigDecimal r = BigDecimal.ONE;
        int factor = 2;
        BigInteger factorial;
        BigDecimal power;
        boolean alternator = true;
        while (true) {
            factorial = getBi().factorial(factor);
            power = x.pow(factor);
            //power = Math_BigDecimal.power(x, factor, oom - 2, rm);
            BigDecimal division = divide(power, factorial, oom - 2, rm);
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
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the sine is returned.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> the result is rounded to if rounding is needed.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return The sine of x.
     */
    public BigDecimal sin(BigDecimal x, int oom, RoundingMode rm) {
        // sin x = x − x^3/3! + x^5/5! − x^7/7! +...
        BigDecimal precision = new BigDecimal(BigInteger.ONE, 2 - oom);
        BigDecimal r = x;
        int factor = 3;
        BigInteger factorial;
        BigDecimal power;
        boolean alternator = true;
        while (true) {
            factorial = getBi().factorial(factor);
            power = x.pow(factor);
            BigDecimal division = divide(power, factorial, oom - 2, rm);
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

//    /**
//     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
//     *
//     * @param x The value for which the sine is returned.
//     * @param aPI The value of PI to be used.
//     * @param twoPI The value of 2PI to be used.
//     * @param aPIBy2 The value of PI/2 to be used.
//     * @param oom The
//     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
//     * Magnitude</a> the result is rounded to if rounding is needed.
//     * <ul>
//     * <li>...</li>
//     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
//     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
//     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
//     * <li>...</li>
//     * </ul>
//     * @param rm The {@link RoundingMode} used to roundDown.
//     * @return The sine of x without checking the case of x.
//     */
//    protected BigDecimal sinNoCaseCheck(BigDecimal x, BigDecimal aPI,
//            BigDecimal twoPI, BigDecimal aPIBy2, int oom, RoundingMode rm) {
//        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
//        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
//            return sinAngleBetweenZeroAndPI(x, aPI, twoPI, oom, rm);
//        } else {
//            if (x.compareTo(aPI) == -1) {
//                return sinAngleBetweenZeroAndPI(aPI.subtract(x), aPI, twoPI, oom, rm);
//            }
//            return sinNoCaseCheck(twoPI.subtract(x), aPI, twoPI, aPIBy2, oom, rm).negate();
//        }
//    }
//
//    /**
//     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
//     *
//     * @param x The value for which the sine is returned.
//     * @param aPI The value of PI to be used.
//     * @param twoPI The value of 2PI to be used.
//     * @param oom The
//     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
//     * Magnitude</a> the result is rounded to if rounding is needed.
//     * <ul>
//     * <li>...</li>
//     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
//     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
//     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
//     * <li>...</li>
//     * </ul>
//     * @param rm The {@link RoundingMode} used to roundDown.
//     * @return The sine of x in the range [0, PI].
//     */
//    protected BigDecimal sinAngleBetweenZeroAndPI(BigDecimal x, BigDecimal aPI,
//            BigDecimal twoPI, int oom, RoundingMode rm) {
//        BigDecimal aPIBy2 = Math_BigDecimal.roundDown(piBy2, oom - 2);
//        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
//        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
//            BigDecimal precision = new BigDecimal(BigInteger.ONE, oom - 4);
//            BigDecimal sinx = new BigDecimal(x.toString());
//            int factor = 3;
//            BigInteger factorial;
//            BigDecimal power;
//            boolean alternator = true;
//            while (true) {
//                factorial = getBi().factorial(factor);
//                power = Math_BigDecimal.power(x, factor, oom - 2, rm);
//                BigDecimal division = Math_BigDecimal.divide(
//                        power, factorial, oom - 2, rm);
//                if (division.compareTo(precision) != -1) {
//                    if (alternator) {
//                        alternator = false;
//                        sinx = sinx.subtract(division);
//                    } else {
//                        alternator = true;
//                        sinx = sinx.add(division);
//                    }
//                } else {
//                    break;
//                }
//                factor += 2;
//            }
//            return Math_BigDecimal.roundDown(sinx, oom, rm);
//        }
//        return null;
//    }
    /**
     * Calculate and return the tangent of x (tan(x)).
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     * Caution: This is not well tested! Are the additional precisions used
     * sufficient?
     *
     * @param x The value to calculate the tangent of.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown intermediate results
     * and the final result.
     * @return tan(x) or null if the cosine is zero.
     */
    public BigDecimal tan(BigDecimal x, int oom, RoundingMode rm) {
        BigDecimal sinx = sin(x, oom - 10, rm);
        sinx = round(sinx, oom - 8, rm);
        BigDecimal cosx = cos(x, oom - 10, rm);
        cosx = round(cosx, oom - 8, rm);
        if (cosx.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return divide(sinx, cosx, oom, rm);
    }

    /**
     * Calculates the atan of
     * {@code x}.https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the value
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> the result is rounded to if rounding is needed.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return atan(x)
     */
    public BigDecimal atan(BigDecimal x, int oom, RoundingMode rm) {
        int oomn8 = oom - 8; // Is 8 sufficient?
        BigDecimal xdivsqrt1px2 = divide(x,
                sqrt(BigDecimal.ONE.add(x.pow(2)), oomn8, RoundingMode.DOWN),
                oom, rm);
        return asin(xdivsqrt1px2, oom, rm);
    }

    /**
     * Calculates the acos of {@code x}.
     * <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">https://en.wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     *
     * @param x the value
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude#Uses">Order of
     * Magnitude</a> the result is rounded to if rounding is needed.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to roundDown.
     * @return acos(x)
     */
    public BigDecimal acos(BigDecimal x, int oom, RoundingMode rm) {
        BigDecimal r = getPiBy2(oom - 2, rm).subtract(asin(x, oom - 2, rm));
        return round(r, oom, rm);
    }

    /**
     * Calculates the arcsine of {@code BigDecimal x}.
     * https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     * http://en.wikipedia.org/wiki/Arcsine
     * 
     * @param x the value
     * @param scale scale
     * @param rm RoundingMode
     * @return asin(x)
     */
    public static BigDecimal asin(BigDecimal x, int scale, RoundingMode rm) {
        // x + (1/2)(x^3/3) + (1.3/2.4)(x^5/5) + (1.3.5/2.4.6)(x^7/7) +...
        //if (x.compareTo(BigDecimal.ZERO) == -1) {
        //    return asin(x.negate(), scale, rm).negate();
        //}
        // As values should be between -1 and 1 the precision is scale + 1;
        MathContext mc = new MathContext(scale + 1, rm);
        return BigDecimalMath.asin(x, mc);
    }

    /**
     * For testing if s can be parsed as a BigDecimal.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * BigDecimal.
     * @return {@code true} iff {@code s} can be represented as a BigDecimal.
     */
    public static boolean isBigDecimal(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
