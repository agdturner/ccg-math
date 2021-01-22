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
package uk.ac.leeds.ccg.math;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
//import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import uk.ac.leeds.ccg.math.Math_BigDecimal;

/**
 * A class of methods for computation with {@code BigDecimal} numbers. The
 * functionality herein was not available in the openJDK at the time of writing,
 * so it may become redundant.
 *
 * The aim is for accuracy to a given
 * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
 * Magnitude</a> and speed. By default if rounding is needed for results, then
 * {@link RoundingMode#HALF_UP} is used.
 *
 * @author Andy Turner
 * @version 2.0.0
 */
public class Math_BigDecimal extends Math_Number {

    private static final long serialVersionUID = 1L;

    /**
     * A {@link Math_BigInteger} is often wanted (such as in Taylor Series
     * calculations).
     */
    public Math_BigInteger bi;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Euler%E2%80%93Mascheroni_constant">Euler
     * constant</a> rounded to {@link #eOOM}
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> rounded using {@link RoundingMode#HALF_UP}. The first few
     * digits of the number are
     * {@code ~2.71828182845904523536028747135266249775724709369995}.
     */
    private BigDecimal e;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * that {@link #e} is rounded to.
     */
    private int eOOM;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Pi">Pi constant</a> rounded to
     * {@link #piOOM}
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> rounded using {@link RoundingMode#HALF_UP}. The first few
     * digits of the number are {@code 3.1415926535897932384626433...}.
     */
    private BigDecimal pi;

    /**
     * For storing the PI constant correct to a fixed decimal place precision.
     */
    private int piOOM;

    /**
     * For storing the PI constant divided by two correct to a fixed decimal
     * place precision.
     */
    private BigDecimal piBy2;

    /**
     * For storing the number of decimal places that {@link #piBy2} is accurate
     * to.
     */
    private int piBy2DP;

    /**
     * For storing the PI constant divided by four correct to a fixed decimal
     * place precision.
     */
    private BigDecimal piBy4;

    /**
     * For storing the number of decimal places that {@link #piBy4} is accurate
     * to.
     */
    private int piBy4DP;

    /**
     * For storing the PI constant multiplied by two correct to a fixed decimal
     * place precision.
     */
    private BigDecimal pi2;

    /**
     * For storing the number of decimal places that {@link #pi2} is accurate
     * to.
     */
    private int pi2DP;

    /**
     * The number 2 is often used so is made available as a constant.
     */
    public static final BigDecimal TWO = BigDecimal.valueOf(2);

    /**
     * The number 0.5 is often used so is made available as a constant.
     */
    public static final BigDecimal HALF = new BigDecimal("0.5");

    /**
     * The number 11 is often used so is made available as a constant.
     */
    public static final BigDecimal ELEVEN = BigDecimal.valueOf(11);

    /**
     * Creates a new instance initialising {@link #bi} with 1000 entries and
     * initialising {@link #pi} to 1000 decimal places.
     */
    public Math_BigDecimal() {
        this(1000);
    }

    /**
     * Creates a new instance of Generic_BigDecimal initialising {@link #bi}
     * with {@code n} entries and initialising {@link #e} to {@code n} decimal
     * places.
     *
     * @param n The number of decimal places {@link #e} is sure to be
     * initialised to.
     */
    public Math_BigDecimal(int n) {
        initBIF(n);
        initE(-n);
    }

    /**
     * Initialises {@link #bi}
     *
     * @param n If n factorial (n!) is not stored in {@link #bi} then this
     * ensures it and all other (n - a)! are stored (for all integer a in the
     * range [1, n]).
     */
    private void initBIF(int n) {
        bi = new Math_BigInteger();
        bi.factorial(n);
    }

    /**
     * Initialises {@link #e} accurate to {@code dp} decimal places.
     *
     * @param dp The number of decimal places {@link #e} is initialised accurate
     * to.
     */
    private void initE(int oom) {
        bi.factorial(-oom);
        e = getE(oom);
    }

    /**
     * Return the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the most significant digit of {@code x}. This
     * works for all values of {@code x}. If it is known that {@code x < 1} use
     * {@link #getOrderOfMagnitudeOfLeastSignificantDigit(java.math.BigDecimal, int)}
     * for more computational efficeincy. Examples:
     * <ul>
     * <li>x=0.0001, result=-4</li>
     * <li>x=0.0011, result=-3</li>
     * <li>x=0.0101, result=-2</li>
     * <li>x=0.1001, result=-1</li>
     * <li>x=1.1001, result=0</li>
     * <li>x=10.001, result=1</li>
     * <li>x=101.01, result=2</li>
     * <li>x=1001.1, result=3</li>
     * </ul>
     *
     * @param x The number for which the largest OOM digit is returned.
     * @return The largest OOM digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfMostSignificantDigit(BigDecimal x) {
        if (x.compareTo(BigDecimal.ONE) != -1) {
            return Math_BigInteger.getMagnitudeOfMostSignificantDigit(x.toBigInteger());
        }
        int scale = x.scale();
        return getOrderOfMagnitudeOfMostSignificantDigit(x, scale);
    }

    /**
     * Return the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the most significant digit. This only returns the
     * expected answer for {@code x < 1}. If {@code x > 1} use
     * {@link #getOrderOfMagnitudeOfLeastSignificantDigit(java.math.BigDecimal)}.
     * Examples:
     * <ul>
     * <li>x=0.0001, result=-4</li>
     * <li>x=0.0011, result=-3</li>
     * <li>x=0.0111, result=-2</li>
     * <li>x=0.1111, result=-1</li>
     * </ul>
     *
     * @param x The number for which the largest OOM digit is returned.
     * @param scale The scale of {@code x}.
     * @return The largest OOM digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfMostSignificantDigit(BigDecimal x, int scale) {
        int um = Math_BigInteger.getMagnitudeOfMostSignificantDigit(x.unscaledValue());
        return um - scale;
    }

    /**
     * Return the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the least significant digit of {@code x}. If the
     * scale of Examples:
     * <ul>
     * <li>x=10.0001, result=-4</li>
     * <li>x=1.0001, result=-4</li>
     * <li>x=0.1001, result=-4</li>
     * <li>x=0.0011, result=-4</li>
     * <li>x=0.0111, result=-4</li>
     * <li>x=0.1111, result=-4</li>
     * <li>x=1.1111, result=-4</li>
     * <li>x=11.111, result=-3</li>
     * <li>x=101.11, result=-2</li>
     * <li>x=1001.1, result=-1</li>
     * <li>x=1001.0, result=0</li>
     * <li>x=1001, result=0</li>
     * <li>x=1010, result=1</li>
     * <li>x=110, result=2</li>
     * <li>x=100, result=3</li>
     * <li>x=10100, result=3</li>
     * </ul>
     *
     * @param x The number for which the smallest OOM digit is returned.
     * @return The smallest OOM digit of {@code x}.
     */
    public static int getOrderOfMagnitudeOfLeastSignificantDigit(BigDecimal x) {
        BigDecimal xs = x.stripTrailingZeros();
        int scale = xs.scale();
        if (scale > 0) {
            return -scale;
        }
        return Math_BigInteger.getMagnitudeOfMostSignificantDigit(
                xs.toBigInteger()) + 1 - xs.precision();
    }

//    /**
//     * Calculates and returns x multiplied by y (x*y). The result is rounded if
//     * necessary using {@link RoundingMode} {@code rm} to give a result accurate
//     * to {@code dp} decimal places.
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used for rounding.
//     * @return x*y rounded if necessary.
//     */
//    public static BigDecimal multiplyRoundIfNecessary(BigDecimal x,
//            BigDecimal y, int dp, RoundingMode rm) {
//        // Deal with special cases
//        if (x.compareTo(BigDecimal.ZERO) == 0
//                || y.compareTo(BigDecimal.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        if (x.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(y.toString());
//            if (y.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        if (y.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(x.toString());
//            if (x.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        return multiplyRoundIfNecessaryNoSpecialCaseCheck(x, y, dp, rm);
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y). The result is rounding if
//     * necessary using {@link RoundingMode} {@code rm} to give a result accurate
//     * to {@code dp} decimal places. The {@link MathContext} {@code mc} may also
//     * be used to round the result.
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param mc The MathContext.
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y rounded if necessary.
//     */
//    public static BigDecimal multiplyRoundIfNecessary(BigDecimal x,
//            BigDecimal y, MathContext mc, int dp, RoundingMode rm) {
//        // Deal with special cases
//        if (x.compareTo(BigDecimal.ZERO) == 0
//                || y.compareTo(BigDecimal.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        if (x.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(y.toString());
//            if (y.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        if (y.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(x.toString());
//            if (x.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        return multiplyRoundIfNecessaryNoSpecialCaseCheck(x, y, mc, dp, rm);
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y). If mc has the right
//     * precision for rounding, it is used, otherwise rounding is done if
//     * necessary using dp and rm. It is expected that the RoundingMode of mc is
//     * the same as rm.
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param mc MathContext MathContext
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y rounded if necessary.
//     */
//    protected static BigDecimal multiplyRoundIfNecessaryNoSpecialCaseCheck(
//            BigDecimal x, BigDecimal y, MathContext mc, int dp,
//            RoundingMode rm) {
//        BigDecimal r = x.multiply(y);
//        int resultScale = r.scale();
//        if (r.scale() > dp) {
//            // RoundingNecessary
//            if (mc.getPrecision() - (r.precision() - resultScale) == dp) {
//                return r.round(mc);
//            } else {
//                return roundIfNecessaryNoScaleCheck(r, dp, rm);
//            }
//        }
//        return r;
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y).
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y rounded if necessary.
//     */
//    protected static BigDecimal multiplyRoundIfNecessaryNoSpecialCaseCheck(
//            BigDecimal x, BigDecimal y, int dp, RoundingMode rm) {
//        BigDecimal r = x.multiply(y);
//        if (r.scale() > dp) {
//            return roundIfNecessaryNoScaleCheck(r, dp, rm);
//        }
//        return r;
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y). The calculation could be
//     * divided into parts using the following algebra: (a + b) * (c + d) = (a *
//     * c) + (a * d) + (b * c) + (b * d) Consider that a is the integer part of x
//     * and b is the remainder and similarly that c is the integer part of y and
//     * d is the remainder. Then: (a * c) is an integer; (a * d) is a number with
//     * a scale no greater than the scale of d; similarly, (b * c) is a number
//     * with a scale no greater than the scale of b. These aforementioned parts
//     * give the main magnitude of the result. With the exception of
//     * multiplications by zero, the remaining part, (b * d) is the only part
//     * that is shrinking. It is also the only part that may require more decimal
//     * places to store the result accurately. So, in calculating all but (b * d)
//     * we can assess if the MathContext is sufficient...
//     *
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y then rounded;
//     */
//    public static BigDecimal multiply(
//            BigDecimal x, BigDecimal y, int dp, RoundingMode rm) {
//        // Deal with special cases
//        if (x.compareTo(BigDecimal.ONE) == 0) {
//            if (y.scale() > dp) {
//                return roundToAndSetDecimalPlaces(y, dp, rm);
//            } else {
//                return y.setScale(dp);
//            }
//        }
//        if (y.compareTo(BigDecimal.ONE) == 0) {
//            if (x.scale() > dp) {
//                return roundToAndSetDecimalPlaces(x, dp, rm);
//            } else {
//                return x.setScale(dp);
//            }
//        }
//        if (x.compareTo(BigDecimal.ZERO) == 0
//                || (y.compareTo(BigDecimal.ZERO) == 0)) {
//            return BigDecimal.ZERO.setScale(dp);
//        }
//        BigDecimal r = x.multiply(y);
//        if (r.scale() > dp) {
//            // RoundingNecessary
//            return roundToAndSetDecimalPlaces(r, dp, rm);
//        } else {
//            return r.setScale(dp);
//        }
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y). If rounding is not
//     * wanted, use {@link BigDecimal#multiply(java.math.BigDecimal)} - i.e.
//     * {@code x.multiply(y)}.
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y rounded if necessary.
//     */
//    public static BigDecimal multiplyRoundIfNecessary(BigDecimal x,
//            BigInteger y, int dp, RoundingMode rm) {
//        // Deal with special cases (x = 0, x = 1, x = -1, y1 = 0, y1 = 1, y1 = -1)?
//        // Deal with special cases
//        if (x.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(y.toString());
//            return r;
//        }
//        if (y.compareTo(BigInteger.ONE) == 0) {
//            BigDecimal r = new BigDecimal(x.toString());
//            if (x.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        if (x.compareTo(BigDecimal.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        if (y.compareTo(BigInteger.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        return multiplyRoundIfNecessaryNoSpecialCaseCheck(x, new BigDecimal(y),
//                dp, rm);
//    }
//
//    /**
//     * Calculates and returns x multiplied by y (x*y). If rounding is not
//     * wanted, use {@link BigDecimal#multiply(java.math.BigDecimal)} - i.e.
//     * {@code x.multiply(y)}.
//     *
//     * @param x One of the two numbers to multiply together.
//     * @param y One of the two numbers to multiply together.
//     * @param mc The {@link MathContext} is ignored!
//     * @param dp The number of decimal places the result has to be correct to.
//     * @param rm The {@link RoundingMode} used to round intermediate results and
//     * the final result.
//     * @return x*y rounded if necessary.
//     */
//    public static BigDecimal multiplyRoundIfNecessary(BigDecimal x,
//            BigInteger y, MathContext mc, int dp, RoundingMode rm) {
//        // Deal with special cases (x = 0, x = 1, x = -1, y1 = 0, y1 = 1, y1 = -1)?
//        // Deal with special cases
//        if (x.compareTo(BigDecimal.ONE) == 0) {
//            BigDecimal r = new BigDecimal(y.toString());
//            return r;
//        }
//        if (y.compareTo(BigInteger.ONE) == 0) {
//            BigDecimal r = new BigDecimal(x.toString());
//            if (x.scale() > dp) {
//                return roundIfNecessary(r, dp, rm);
//            } else {
//                return r;
//            }
//        }
//        if (x.compareTo(BigDecimal.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        if (y.compareTo(BigInteger.ZERO) == 0) {
//            return BigDecimal.ZERO;
//        }
//        return multiplyRoundIfNecessaryNoSpecialCaseCheck(x, new BigDecimal(y),
//                dp, rm);
//    }
    /**
     * Calculate and return {@code x} multiplied by {@code y} to the precision
     * scale given by {@code ps} using the RoundingMode {@code rm}. This method
     * is appropriate when {@code x} and or {@code y} are very large, and the
     * precision of the result required is at an order of magnitude the square
     * root of which is less than the magnitude of the larger of x and y.
     * Multiplication is only very time consuming for huge numbers, so to gain
     * some computational advantage of prior rounding the numbers have to be
     * perhaps over 100 digits in length. (TODO test timings, maybe efficiency
     * is only gained once numbers have an
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of over 1000 digits!)
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to round the result if rounding
     * is necessary.
     * @return x multiplied by y to the precision scale given by {@code ps} and
     * {@code rm}.
     */
    public static BigDecimal multiply(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        return Math_BigDecimal.round(x.multiply(new BigDecimal(y)), oom, rm);
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
     * Multiplication is only very time consuming for huge and/or very precise
     * numbers, so to gain some computational advantage of prior rounding the
     * numbers being multiplied may have to be very big and/or very precise.
     * Some timing experiments should be performed to test efficiencies... If
     * the OOM of {@code x} and/or {@code y} are small relative to {@code oom}
     * and {@code x} and/or {@code y} do not have a very large precision then it
     * may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal java.math.BigDecimal, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to round the result.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigInteger y,
            int oom, RoundingMode rm) {
        int xl = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
        if (xl < 0) {
            int ym = Math_BigInteger.getMagnitudeOfMostSignificantDigit(y);
            int xlo = oom - ym - 1;
            if (xlo > xl) {
                BigDecimal xr = Math_BigDecimal.round(x, xlo);
                return multiply(xr, y, oom, rm);
            }
            return multiply(x, y, oom, rm);
        }
        return new BigDecimal(Math_BigInteger.multiplyPriorRound(
                x.toBigIntegerExact(), y, oom, rm));
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
     * for huge and/or very precise numbers, so to gain some computational
     * advantage of prior rounding the numbers being multiplied may have to be
     * very big and/or very precise. Some timing experiments should be performed
     * to test efficiencies... If the OOM of {@code x} and/or {@code y} are
     * small relative to {@code oom} and {@code x} and/or {@code y} do not have
     * a very large precision then it may be computationally advantageous to
     * simply use
     * {@link #multiply(java.math.BigDecimal java.math.BigDecimal, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigInteger y,
            int oom) {
        return multiplyPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> given by {@code oom} using the
     * {@link RoundingMode} {@code rm}. If {@code x} and {@code y} are both very
     * large and or very detailed then prior rounding may be computationally
     * advantageous (@see also
     * {@link #multiplyPriorRound(java.math.BigDecimal, java.math.BigDecimal, int, java.math.RoundingMode)}).
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * </ul>
     * @param rm The {@link RoundingMode} used to round the final result if
     * rounding is necessary.
     * @return x multiplied by y to the precision scale given by {@code ps} and
     * {@code rm}.
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        return Math_BigDecimal.round(x.multiply(y), oom, rm);
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
     * Multiplication is only very time consuming for huge and/or very precise
     * numbers, so to gain some computational advantage of prior rounding the
     * numbers being multiplied may have to be very big and/or very precise.
     * Some timing experiments should be performed to test efficiencies... If
     * the OOM of {@code x} and/or {@code y} are small relative to {@code oom}
     * and {@code x} and/or {@code y} do not have a very large precision then it
     * may be computationally advantageous to simply use
     * {@link #multiply(java.math.BigDecimal java.math.BigDecimal, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to round the result.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigDecimal y,
            int oom, RoundingMode rm) {
        int s = x.scale();
        int xm = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, s);
        int ym = getOrderOfMagnitudeOfMostSignificantDigit(y);
        int m = (int) Math.sqrt(oom);
        BigDecimal d = BigDecimal.TEN.pow(m);
        if (ym > 0) {
            if (xm > 0) {
                // Case 4
                BigInteger yi = y.toBigInteger();
                BigDecimal xyi = Math_BigDecimal.multiplyPriorRound(x, yi, oom - 2, rm);
                BigDecimal yf = y.subtract(new BigDecimal(yi));
                BigDecimal xyf = Math_BigDecimal.multiplyPriorRoundXLT1YGT1(yf, x, oom - 2, rm);
                System.out.println(x.multiply(y));
                System.out.println(Math_BigDecimal.round(xyi.add(xyf), oom, rm));
                return Math_BigDecimal.round(xyi.add(xyf), oom, rm);
            } else {
                // Case 2
                return multiplyPriorRoundXLT1YGT1(x, y, oom, rm);
            }
        } else {
            if (xm > 0) {
                // Case 3
                return multiplyPriorRoundXLT1YGT1(y, x, oom, rm);
            } else {
                // Case 1
                return multiplyPriorRoundXLT1YLT1(x, y, oom, rm);
            }
        }
    }

    /**
     * @param x A number to multiply {@code x < 1}.
     * @param y A number to multiply {@code y < 1}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to round the result.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigDecimal multiplyPriorRoundXLT1YLT1(BigDecimal x,
            BigDecimal y, int oom, RoundingMode rm) {
        int s = x.scale();
        int xm = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, s);
        int ym = getOrderOfMagnitudeOfMostSignificantDigit(y);
        BigDecimal xr;
        if (xm + 1 >= oom) {
            xr = Math_BigDecimal.round(x, oom + ym, rm);
            xm = getOrderOfMagnitudeOfMostSignificantDigit(xr);
        } else {
            xr = x;
        }
        BigDecimal yr;
        if (ym + 1 >= oom) {
            yr = Math_BigDecimal.round(y, oom + xm, rm);
            ym = getOrderOfMagnitudeOfMostSignificantDigit(yr);
        } else {
            yr = y;
        }
        if ((ym + xm) < oom) {
            return BigDecimal.ZERO;
        } else {
            return Math_BigDecimal.multiply(xr,
                    Math_BigDecimal.round(yr, oom), oom, rm);
        }
    }

    /**
     * @param x A number to multiply {@code x < 1}.
     * @param y A number to multiply {@code y > 1}.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} used to round the result.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using the {@link RoundingMode} {@code rm}.
     */
    public static BigDecimal multiplyPriorRoundXLT1YGT1(BigDecimal x,
            BigDecimal y, int oom, RoundingMode rm) {
        int s = x.scale();
        int xm = Math_BigDecimal.getOrderOfMagnitudeOfMostSignificantDigit(x, s);
        int ym = getOrderOfMagnitudeOfMostSignificantDigit(y);
        BigDecimal xr;
        if (xm + 1 >= oom) {
            int xl = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(x);
            if ((oom - ym - 1) > xl) {
                xr = Math_BigDecimal.round(x, oom - ym - 1, rm);
                xm = getOrderOfMagnitudeOfMostSignificantDigit(xr);
            } else {
                xr = x;
            }
        } else {
            xr = x;
        }
        BigDecimal yr;
        if (ym + 1 >= oom) {
            int yl = Math_BigDecimal.getOrderOfMagnitudeOfLeastSignificantDigit(y);
            if ((oom + xm) > yl) {
                yr = Math_BigDecimal.round(y, oom + xm, rm);
                ym = getOrderOfMagnitudeOfMostSignificantDigit(yr);
            } else {
                yr = y;
            }
        } else {
            yr = y;
        }
        if ((ym + xm) < oom) {
            return BigDecimal.ZERO;
        } else {
            return Math_BigDecimal.multiply(xr,
                    Math_BigDecimal.round(yr, oom), oom, rm);
        }
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
     * for huge and/or very precise numbers, so to gain some computational
     * advantage of prior rounding the numbers being multiplied may have to be
     * very big and/or very precise. Some timing experiments should be performed
     * to test efficiencies... If the OOM of {@code x} and/or {@code y} are
     * small relative to {@code oom} and {@code x} and/or {@code y} do not have
     * a very large precision then it may be computationally advantageous to
     * simply use
     * {@link #multiply(java.math.BigDecimal java.math.BigDecimal, int)}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return ({@code x*y}) rounded to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">OOM</a>
     * {@code oom} using {@link RoundingMode#HALF_UP}.
     */
    public static BigDecimal multiplyPriorRound(BigDecimal x, BigDecimal y,
            int oom) {
        return multiplyPriorRound(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculates and returns x divided by y (x/y) correct to
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     *
     * @return x/y then rounded if necessary
     */
    private static BigDecimal divideNoCaseCheckRoundIfNecessary(
            BigDecimal x, BigDecimal y, int dp, RoundingMode rm) {
        //int integerPartPrecision = x.divideToIntegralValue(y).toString().length();
        int integerPartPrecision = x.divideToIntegralValue(y).precision();
        //int precision = integerPartPrecision + decimalPlaces + 1;
        int precision = integerPartPrecision + dp + 2;
        MathContext mc = new MathContext(precision, rm);
        BigDecimal xDivideY = x.divide(y, mc);
        return roundIfNecessary(xDivideY, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded if necessary given
     * the {@link RoundingMode} {@code rm} and {@code dp}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y rounded to decimalPlaces decimal places if necessary.
     */
    public static BigDecimal divideRoundIfNecessary(BigDecimal x, BigDecimal y,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(x, y, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded if necessary given
     * the {@link MathContext} {@code mc}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param mc The {@link MathContext} is used for rounding the result.
     * @return x/y rounded to decimalPlaces decimal places if necessary.
     */
    public static BigDecimal divideRoundIfNecessary(BigDecimal x, BigDecimal y,
            MathContext mc) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return x.divide(y, mc);
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded if necessary given
     * the {@link RoundingMode} {@code rm} and {@code dp}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y rounded if necessary
     */
    private static BigDecimal divideNoCaseCheckRoundToFixedDecimalPlaces(
            BigDecimal x, BigDecimal y, int dp, RoundingMode rm) {
        int integerPartLength = x.divideToIntegralValue(y).toString().length();
        int precision = integerPartLength + dp + 1;
        MathContext mc = new MathContext(precision, rm);
        BigDecimal xDivideY = x.divide(y, mc);
        return roundToAndSetDecimalPlaces(xDivideY, dp, rm);
    }

    /**
     * Calculates and returns {@code x} divided by {@code y} (x/y) rounded using
     * {@link RoundingMode#HALF_UP} to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) given by {@code oom}. If {@code y} is
     * {@link BigDecimal#ZERO} then an {@link ArithmeticException} is thrown.
     * Otherwise if {@code x} is {@link BigDecimal#ZERO} then
     * {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return x/y rounded to OOM = oom;
     * @throws ArithmeticException if y
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y, int oom) {
        return divide(x, y, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculates and returns {@code x} divided by {@code y} (x/y) rounded using
     * {@link RoundingMode} {@code rm} to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) given by {@code oom}. If {@code y} is
     * {@link BigDecimal#ZERO} then an {@link ArithmeticException} is thrown.
     * Otherwise if {@code x} is {@link BigDecimal#ZERO} then
     * {@link BigDecimal#ZERO} is returned.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @param rm The {@link RoundingMode} for rounding.
     * @return x/y then rounded;
     * @throws ArithmeticException if y
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y, int oom,
            RoundingMode rm) {
        // Deal with special cases
        if (y.signum() == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        if (x.signum() == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return round(x, oom, rm);
        }
        if (x.compareTo(y) == 0) {
            return round(BigDecimal.ONE, oom, rm);
        }
        return divideNoCaseCheck(x, y, oom, rm);
    }

    private static BigDecimal divideNoCaseCheck(
            BigDecimal x, BigDecimal y, int oom, RoundingMode rm) {
        int precision = x.divideToIntegralValue(y).precision() - oom;
        MathContext mc = new MathContext(precision, rm);
        return x.divide(y, mc);
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded to fixed decimal
     * places using the {@link RoundingMode} {@code rm} and {@code dp}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y then rounded;
     */
    @Deprecated
    public static BigDecimal divideRoundToFixedDecimalPlaces(BigDecimal x,
            BigDecimal y, int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundToAndSetDecimalPlaces(x, dp, rm);
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(dp);
        }
        BigDecimal result = divideNoCaseCheckRoundIfNecessary(x, y, dp, rm);
        return roundToAndSetDecimalPlaces(result, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y) without rounding.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return x/y
     */
    public static BigDecimal divideNoRounding(BigDecimal x, BigDecimal y) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
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
     * Creates and returns a new list containing all the numbers in list divided
     * by divisor using decimalPlaces and roundingMode and rounding if
     * necessary.
     *
     * @param list The numbers to be divided by {@code divisor}.
     * @param divisor The divisor.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return a new list containing all the numbers in list divided by divisor.
     */
    public static ArrayList<BigDecimal> divideRoundIfNecessary(
            ArrayList<BigDecimal> list, BigDecimal divisor, int dp,
            RoundingMode rm) {
        ArrayList<BigDecimal> r = new ArrayList<>();
        Iterator<BigDecimal> ite = list.iterator();
        while (ite.hasNext()) {
            BigDecimal v = ite.next();
            BigDecimal dividedValue = Math_BigDecimal.divideRoundIfNecessary(
                    v, divisor, dp, rm);
            r.add(dividedValue);
        }
        return r;
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded if necessary using
     * the {@link RoundingMode} {@code rm} and {@code dp}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y then rounded;
     */
    public static BigDecimal divideRoundIfNecessary(BigDecimal x, BigInteger y,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        if (x.compareTo(y_BigDecimal) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(x, y_BigDecimal, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y) rounded to fixed decimal
     * places using the {@link RoundingMode} {@code rm} and {@code dp}.
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y then rounded;
     */
    public static BigDecimal divideRoundToFixedDecimalPlaces(
            BigDecimal x, BigInteger y, int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return roundToAndSetDecimalPlaces(x, dp, rm);
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        if (x.compareTo(y_BigDecimal) == 0) {
            return BigDecimal.ONE.setScale(dp);
        }
        BigDecimal r = divideNoCaseCheckRoundIfNecessary(x, y_BigDecimal, dp, rm);
        return roundToAndSetDecimalPlaces(r, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return x/y
     */
    public static BigDecimal divideNoRounding(BigDecimal x, BigInteger y) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal yBD = new BigDecimal(y);
        if (x.compareTo(yBD) == 0) {
            return BigDecimal.ONE;
        }
        return x.divide(yBD);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y
     */
    public static BigDecimal divideRoundIfNecessary(BigInteger x, BigDecimal y,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal xBD = new BigDecimal(x);
        if (xBD.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(xBD, y, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y
     */
    public static BigDecimal divideRoundToFixedDecimalPlaces(BigInteger x,
            BigDecimal y, int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundToAndSetDecimalPlaces(new BigDecimal(x), dp, rm);
        }
        BigDecimal xBD = new BigDecimal(x);
        if (xBD.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(dp);
        }
        BigDecimal r;
        r = divideNoCaseCheckRoundIfNecessary(xBD, y, dp, rm);
        return roundToAndSetDecimalPlaces(r, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return x/y
     */
    public static BigDecimal divideNoRounding(BigInteger x, BigDecimal y) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal xBD = new BigDecimal(x);
        if (xBD.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return xBD.divide(y);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y
     */
    public static BigDecimal divideRoundIfNecessary(BigInteger x, BigInteger y,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(new BigDecimal(x),
                new BigDecimal(y), dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x/y
     */
    public static BigDecimal divideRoundToFixedDecimalPlaces(BigInteger x,
            BigInteger y, int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return roundToAndSetDecimalPlaces(new BigDecimal(x), dp, rm);
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(dp);
        }
        BigDecimal r = divideNoCaseCheckRoundIfNecessary(
                new BigDecimal(x), new BigDecimal(y), dp, rm);
        return roundToAndSetDecimalPlaces(r, dp, rm);
    }

    /**
     * Calculates and returns x divided by y (x/y).
     *
     * @param x The numerator of the division.
     * @param y The denominator of the division.
     * @return x/y
     */
    public static BigDecimal divideNoRounding(BigInteger x, BigInteger y) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return new BigDecimal(x).divide(new BigDecimal(y));
    }

    /**
     * Calculates and returns x raised to the power of y (x^y).
     * https://en.wikipedia.org/wiki/Exponentiation
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
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y accurate to at least decimalPlaces
     */
    private static BigDecimal powerExponentLessThanOne(BigDecimal x,
            BigDecimal y, int dp, RoundingMode rm) {
        BigDecimal r;
        //BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
        BigDecimal y0 = new BigDecimal(y.toString());
        BigDecimal element;
        BigInteger elementUnscaled;
        BigDecimal elementOne;
        BigInteger elementOneReciprocal;
        BigDecimal root;
        BigDecimal rootMultiple;
        //int maxite = y0.precision();
        int maxite = Math.max(y0.precision(), 100); // 100?
        r = BigDecimal.ONE;
        for (int i = 0; i < maxite; i++) {
            element = floorSignificantDigit(y0);
            elementUnscaled = element.unscaledValue();
            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
                elementOne = divideRoundIfNecessary(element, elementUnscaled, dp + 2, rm); // +2 sufficient?
                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
                root = rootRoundIfNecessary(x, elementOneReciprocal, dp + 3, rm);
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = power(root, elementUnscaled, 64, dp, rm);
                    //r = multiplyRoundIfNecessary(r, rootMultiple, dp, rm);
                    r = r.multiply(rootMultiple);
                }
            }
            y0 = y0.subtract(element);
        }
//        BigDecimal result;
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        BigDecimal x0 = new BigDecimal(x.toString());
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal rootRoundIfNecessary;
//        BigDecimal rootMultiple;
//        //int maxite = x0.precision();
//        result = BigDecimal.ONE;
//        //for (int i = 0; i < maxite; i++) {
//        while (true) {
//            element = floorSignificantDigit(x0);
//            elementUnscaled = element.unscaledValue();
//            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divide(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
//                    break;
//                }
//                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        x,
//                        elementOneReciprocal,
//                        //a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    result = multiply(
//                            result,
//                            rootMultiple,
//                            decimalPlaces,
//                            a_RoundingMode);
//            }
//            x0 = x0.subtract(element);
//        }

//        BigDecimal xPowerEleven = power(
//                x,
//                ELEVEN,
//                a_Generic_BigDecimal,
//                decimalPlaces * decimalPlaces, // guess at precision required
//                a_RoundingMode);
//        BigDecimal elevenSubtractY = ELEVEN.subtract(y);
//        BigDecimal xPowerElevenSubtractY = power(
//                x,
//                elevenSubtractY,
//                a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        result = divide(
//                xPowerEleven,
//                xPowerElevenSubtractY,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
//        // If x.unscaledValue() == 1, then the numerator becomes one.
//        // If x.unscaledValue() == 2, then the numerator tends to 1,
//        // but to calculate Euler's constant raised to the power of (the Euler
//        // based log of x.unscaledValue() multiplied by y) we are dealing with
//        // an exponentiation involving a power even smaller than y which is
//        // problematic.
//        // If x.unscaledValue() >= 3, then any further exponentiations will
//        // involve a power greater than y.
//        // For this reason, numerator and denominator are effectively mutlipled
//        // by 10.
//        BigDecimal numeratorx = new BigDecimal(x.unscaledValue().multiply(BigInteger.TEN));
//        BigDecimal denominatorx = new BigDecimal(BigInteger.TEN, -x.scale());
//        BigDecimal numeratorexponent = y.multiply(log(
//                10,
//                numeratorx,
//                decimalPlaces, // not sure about this or the multiplication
//                a_RoundingMode));
//        BigDecimal denominatorexponent = y.multiply(log(
//                10,
//                denominatorx,
//                decimalPlaces, // not sure about this
//                a_RoundingMode));
//        BigDecimal numerator = power(
//                BigDecimal.TEN,
//                numeratorexponent,
//                a_Generic_BigDecimal,
//                decimalPlaces, // not sure about this
//                a_RoundingMode);
//        BigDecimal denominator = power(
//                BigDecimal.TEN,
//                denominatorexponent,
//                a_Generic_BigDecimal,
//                decimalPlaces, // not sure about this
//                a_RoundingMode);
//        result = divideNoCaseCheckNoRounding(
//                numerator,
//                denominator,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
        //ln(x, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode)
        //power(numerator,y,a_Generic_BigDecimal, decimalPlaces, a_RoundingMode)
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        if (y1.compareTo(epsilon_BigDecimal) == -1) {
//            return new BigDecimal(BigInteger.ZERO, decimalPlaces);
//        }
//    if (x.compareTo (BigDecimal.ONE) 
//        == -1 && x.compareTo(BigDecimal.ONE.negate()) == 1) {
//
//            BigDecimal yunscaled = new BigDecimal(y.unscaledValue());
//        int yscale = y.scale();
//        int xscale = x.scale();
//        BigInteger rootRoundIfNecessary = new BigDecimal(BigInteger.ONE, (-1 * yscale)).toBigIntegerExact();
//        int rootLength = rootRoundIfNecessary.toString().length();
//
////            BigDecimal xrooted = rootRoundIfNecessary(
////                    x,
////                    rootRoundIfNecessary,
////                    a_Generic_BigDecimal,
////                    decimalPlaces,
////                    a_RoundingMode);
////            result = power(
////                    xrooted,
////                    yunscaled,
////                    a_Generic_BigDecimal,
////                    decimalPlaces + xscale,
////                    a_RoundingMode);
//        BigDecimal xpowyunscaled = power(
//                x,
//                yunscaled,
//                //a_Generic_BigDecimal,
//                //decimalPlaces + xscale, // Need much more precision....
//                decimalPlaces + yunscaled.intValueExact() + xscale,
//                a_RoundingMode);
//        //BigDecimal initialEstimate = BigDecimal.ONE.add(x).divide(TWO);
//        result = rootLessThanOne(
//                xpowyunscaled,
//                rootRoundIfNecessary,
//                //initialEstimate,
//                //a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
//    }
//    
//
//    
//        else {
//            decimalPlaces += 3;
//        BigDecimal y0 = new BigDecimal(y.toString());
//        int maxite = y0.precision();
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal rootRoundIfNecessary;
//        BigDecimal rootMultiple;
//        result = BigDecimal.ONE;
//        for (int i = 0; i < maxite; i++) {
//            element = floorSignificantDigit(y0);
//            //elementUnscaled = new BigDecimal(element.unscaledValue());
//            elementUnscaled = element.unscaledValue();
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divide(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        x,
//                        elementOneReciprocal,
//                        //a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    result = multiply(
//                            result,
//                            rootMultiple,
//                            decimalPlaces,
//                            a_RoundingMode);
//                }
//            }
//            y0 = y0.subtract(element);
//        }
//        return round(result, decimalPlaces - 3, a_RoundingMode);
//    }
//    //return round(result, decimalPlaces, a_RoundingMode);
//}
        return roundIfNecessary(r, dp, rm);
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
                root = rootNoRounding(x, elementOneReciprocal);
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = powerNoRounding(root, elementUnscaled, 64);
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

    private static int parseDiv(int div) {
        if (div < 2) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        return div;
    }

    /**
     * Test to see if x raised to the power of y (x^y) is greater than ({@code >})
     * {@code compare}.
     *
     * @param compare The value to compare.
     * @param x x {@code >} 1
     * @param y y {@code >} 1
     * @param div Optimisation parameter...
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The RoundingMode used in the case rounding is necessary.
     * @return true iff x^y {@code >} compare.
     */
    public static boolean powerTestAbove(BigDecimal compare, BigDecimal x,
            BigInteger y, int div, int dp, RoundingMode rm) {
        div = parseDiv(div);
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        int compareScale = compare.scale();
        int powerDecimalPlaces;
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            powerDecimalPlaces = Math.min(compareScale + 1, dp);
            toCompare0 = power(x, y, div, powerDecimalPlaces, rm);
            return toCompare0.compareTo(compare) == 1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        int toCompareScale;
        int toCompare0Scale;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            toCompareScale = toCompare.scale();
            if (y1.compareTo(div_BigInteger) == -1) {
                powerDecimalPlaces = Math.min((div * compareScale) + 1, dp);
                toCompare0 = power(x, y1, div, powerDecimalPlaces, rm);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                powerDecimalPlaces = Math.min(compareScale + 1, dp);
                boolean powerTest0 = powerTestAbove(compare, x,
                        yDivideAndRemainder[0], div, powerDecimalPlaces, rm);
                if (powerTest0) {
                    return true;
                } else {
                    powerDecimalPlaces = Math.min((div * compareScale) + 1, dp);
                    toCompare0 = power(x, yDivideAndRemainder[0], div,
                            powerDecimalPlaces, rm);
                }
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = power(x,
                            yDivideAndRemainder[1], div, dp, // @TODO Is this sufficient?
                            rm);
                    if (resultRemainder.compareTo(compare) == 1) {
                        return true;
                    }
//                    toCompare0 = multiplyRoundIfNecessary(toCompare0,
//                            resultRemainder, dp, rm); // @TODO Is this the correct form of multiply?
                    toCompare0 = toCompare0.multiply(resultRemainder); // @TODO Is this the correct form of multiply?
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            //toCompare = multiplyRoundIfNecessary(toCompare, toCompare0, dp, rm); // @TODO Is this the correct form of multiply?
            toCompare = toCompare.multiply(toCompare0);
            if (toCompare.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
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
     * @param div Optimisation parameter...
     * @return true iff x^y {@code >} compare.
     */
    public static boolean powerTestAboveNoRounding(BigDecimal compare,
            BigDecimal x, BigInteger y, int div) {
        div = parseDiv(div);
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        int compareScale = compare.scale();
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            toCompare0 = powerNoRounding(x, y, div);
            return toCompare0.compareTo(compare) == 1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(div_BigInteger) == -1) {
                toCompare0 = powerNoRounding(x, y1, div);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                boolean powerTest0 = powerTestAboveNoRounding(compare, x,
                        yDivideAndRemainder[0], div);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = powerNoRounding(x, yDivideAndRemainder[0], div);
                }
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = powerNoRounding(x,
                            yDivideAndRemainder[1], div);
                    if (resultRemainder.compareTo(compare) == 1) {
                        return true;
                    }
                    toCompare0 = toCompare0.multiply(resultRemainder);
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            toCompare = toCompare.multiply(toCompare0);
            if (toCompare.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
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
     * @param div Optimisation parameter...
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The RoundingMode used in the case rounding is necessary.
     * @return true iff x^y {@code <} compare
     */
    public static boolean powerTestBelow(BigDecimal compare, BigDecimal x,
            BigInteger y, int div, int dp, RoundingMode rm) {
        div = parseDiv(div);
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            toCompare0 = power(x, y, div,
                    dp, // @TODO Is this sufficient?
                    rm);
            return toCompare0.compareTo(compare) == -1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(div_BigInteger) == -1) {
                toCompare0 = power(x, y1, div,
                        dp, // @TODO Is this sufficient?
                        rm);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                boolean powerTest0 = powerTestAbove(compare, x,
                        yDivideAndRemainder[0], div,
                        dp, // @TODO Is this sufficient?
                        rm);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = power(x, yDivideAndRemainder[0], div,
                            dp, // @TODO Is this sufficient?
                            rm);
                }
                toCompare0 = power(toCompare0, div,
                        dp, // @TODO Is this sufficient?
                        rm);
                if (toCompare.compareTo(compare) == -1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = power(x,
                            yDivideAndRemainder[1], div,
                            dp, // @TODO Is this sufficient?
                            rm);
                    if (resultRemainder.compareTo(compare) == -1) {
                        return true;
                    }
//                    toCompare0 = multiplyRoundIfNecessary(toCompare0,
//                            resultRemainder, dp, rm); // @TODO Is this the correct form of multiply?
                    toCompare0 = toCompare0.multiply(resultRemainder); // @TODO Is this the correct form of multiply?
                    if (toCompare.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
//            toCompare = multiplyRoundIfNecessary(toCompare, toCompare0, dp,
//                    rm); // @TODO Is this the correct form of multiply?
            toCompare = toCompare.multiply(toCompare0);
            if (toCompare.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
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
     * @param div Optimisation parameter...
     * @return true iff x^y {@code <} compare
     */
    public static boolean powerTestBelowNoRounding(BigDecimal compare,
            BigDecimal x, BigInteger y, int div) {
        div = parseDiv(div);
        BigInteger divBI = BigInteger.valueOf(div);
        BigDecimal c = BigDecimal.ONE;
        BigDecimal c0;
        // Deal with special case
        if (y.compareTo(divBI) == -1) {
            c0 = powerNoRounding(x, y, div);
            return c0.compareTo(compare) == -1;
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDAR;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(divBI) == -1) {
                c0 = powerNoRounding(x, y1, div);
            } else {
                yDAR = y1.divideAndRemainder(divBI);
                boolean powerTest0 = powerTestAboveNoRounding(compare, x,
                        yDAR[0], div);
                if (powerTest0) {
                    return true;
                } else {
                    c0 = powerNoRounding(x, yDAR[0], div);
                }
                c0 = powerNoRounding(c0, div);
                if (c.compareTo(compare) == -1) {
                    return true;
                }
                if (yDAR[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = powerNoRounding(x,
                            yDAR[1], div);
                    if (resultRemainder.compareTo(compare) == -1) {
                        return true;
                    }
                    c0 = c0.multiply(resultRemainder);
                    if (c.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            c = c.multiply(c0);
            if (c.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(divBI);
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
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @param dp The number of decimal places the result has to be correct to.
     * @return x^y
     */
    public static BigDecimal power(BigDecimal x, BigDecimal y, int dp,
            RoundingMode rm) {
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundIfNecessary(x, dp, rm);
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = power(x.negate(), y, dp, rm);
            if (isEven(y)) {
                return roundIfNecessary(r.negate(), dp, rm);
            } else {
                return roundIfNecessary(r, dp, rm);
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = power(x, y.negate(), dp + 2, rm);
            BigDecimal r = reciprocal(power, dp, rm);
            return r;
            //return round(r, dp, rm);
        }
        if (y.scale() <= 0) {
            return power(x, y.toBigIntegerExact(), 64, dp, rm);
        }
        if (x.compareTo(TWO) == -1) {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                return powerExponentLessThanOne(x, y, dp, rm);
                //throw new UnsupportedOperationException();
            } else {
                BigDecimal fractionalPart = powerExponentLessThanOne(x,
                        y.subtract(BigDecimal.ONE), dp + 2, rm);
                return roundIfNecessary(x.multiply(fractionalPart), dp, rm);
                //throw new UnsupportedOperationException();
            }
        } else {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                // x > 2 && y < 1
                BigDecimal r = powerExponentLessThanOne(x, y, dp, rm);
//                // Use x^a * y^a = (x * y)^a
//                BigInteger xIntegerPart = x.toBigInteger();
//                ...
//                throw new UnsupportedOperationException();
                return r;
            } else {
                // x > 2 && y > 1
                BigInteger y_BigInteger = y.toBigInteger();
                BigDecimal integerPartResult = power(x, y_BigInteger, 256, dp, rm);
                BigDecimal fractionalPartResult = power(x,
                        y.subtract(new BigDecimal(y_BigInteger)), dp, rm);
//                BigDecimal r = multiplyRoundIfNecessary(
//                        integerPartResult, fractionalPartResult, dp, rm);
                BigDecimal r = integerPartResult.multiply(fractionalPartResult);
                return r;
////              if (x.compareTo(BigDecimal.TEN) == 1) {
////                  // A log base 10 x will return greater than one
////                  BigDecimal log10x = log(10, x, dp, rm);
////                  BigDecimal ylog10x = multiplyRoundIfNecessary(y, log10x,
////                          dp * 2, // Is this safe, or should it be a factor of y
////                          rm);
////                  return power(BigDecimal.TEN, ylog10x, dp, rm);
////              } else {
////                  // A log base 2 x will return greater than one
////                  BigDecimal log2x = log(2, x, dp, rm);
////                  BigDecimal ylog2x = multiplyRoundIfNecessary(y, log2x,
////                          dp * 2, // Is this safe, or should it be a factor of y
////                          rm);
////                  return power(TWO, ylog2x, dp, rm);
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
            BigDecimal result = powerNoRounding(x.negate(), y);
            if (isEven(y)) {
                return result.negate();
            } else {
                return result;
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = powerNoRounding(x, y.negate());
            BigDecimal r = reciprocalWillBeIntegerReturnBigDecimal(power);
            return r;
        }
        if (y.scale() <= 0) {
            return powerNoRounding(x, y.toBigIntegerExact(), 64);
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
                BigDecimal ipr = powerNoRounding(x, ybi, 256);
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
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y
     */
    public static BigDecimal power(BigDecimal x, long y, int dp, RoundingMode rm) {
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
//        if (Math.abs(y) < 999999999) {
//            return power(x, (int) y, 256, dp, rm);
//        } else {
        return power(x, BigInteger.valueOf(y), dp, 256, rm);
//        }
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
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256. div is used to divide the calculation up if y is less than div, it
     * is all done in one step. Otherwise it is broken into parts.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y accurate to decimalPlaces
     */
    //private static BigDecimal power(
    public static BigDecimal power(BigDecimal x, int y, int div, int dp,
            RoundingMode rm) {
        return power(x, BigInteger.valueOf(y), div, dp, rm);
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places. If y is negative and a precise
     * answer cannot be given an exception will be thrown.
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256. div is used to divide the calculation up if y is less than div, it
     * is all done in one step. Otherwise it is broken into parts.
     * @return x^y accurate to decimalPlaces
     */
    public static BigDecimal powerNoRounding(BigDecimal x, int y, int div) {
        return powerNoRounding(x, BigInteger.valueOf(y), div);
    }

    /**
     * x.unscaledValue() == 1 x.precision == 1
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
        BigDecimal result;
        int xscale = x.scale();
        int xprecision = x.precision();
        if (xscale == 0) {
            result = x.movePointRight((y - 1) * (xprecision - 1));
        } else {
            result = x.movePointLeft((y - 1) * xscale);
        }
        return result;
    }

    /**
     * Calculates and returns the root-th root of x.
     *
     * @param x The value to root. Expected that: x.unscaledValue() == 1;
     * x.precision == 1.
     * @param root The root. Expected to be greater than {@code 0}.
     * @param dp decimalPrecision
     * @return The root-th root of x.
     */
    public static BigDecimal rootUnscaled1Precision1(BigDecimal x,
            int root, int dp) {
        // Is there a need for precision?
        BigDecimal r;
        //int decimalPrecision = 10;
        int xscale = x.scale();
        int xprecision = x.precision();
        if (xscale == 0) {
            if (xprecision - root > 1) {
                r = x.movePointLeft(xprecision - root);
            } else {
                r = rootRoundIfNecessary(x, root, dp,
                        RoundingMode.UP);
            }
        } else {
            r = x.movePointRight((root - 1) * xscale);
        }
        return r;
    }

    /**
     * Calculates and returns x raised to the power of y (x^y) accurate to
     * {@code dp} number of decimal places. The calculation is divided into
     * parts...
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param div An optimisation parameter... If div &lt; 2 it is set to 2. If
     * div &gt; 256 it is set to 256.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y accurate to decimalPlaces
     */
    @Deprecated
    public static BigDecimal power(BigDecimal x, BigInteger y, int div,
            int dp, RoundingMode rm) {
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
            return roundIfNecessary(x, dp, rm);
        }
        // y = 2
        if (y.compareTo(BigInteger.TWO) == 0) {
            //return multiplyRoundIfNecessary(x, x, dp, rm);
            return x.multiply(x);
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = power(x.negate(), y, div, dp, rm);
            if (Math_BigInteger.isEven(y)) {
                return r.negate();
                //return round(result.negate(), decimalPlaces, a_RoundingMode);
            } else {
                return r;
                //return round(result, decimalPlaces, a_RoundingMode);
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheck(x, y.negate(), div,
                    dp + 2, rm);
            BigDecimal r = reciprocal(power, dp, rm);
            return r;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        BigDecimal r = powerNoSpecialCaseCheck(x, y, div, dp, rm);
        return r;
    }

    /**
     * Calculates and returns x raised to the power of y (x^y).
     *
     * @param x The base of the exponent.
     * @param y The exponent. Expected to be positive.
     * @param div An optimisation parameter...
     * @return x^y
     */
    public static BigDecimal powerNoRounding(BigDecimal x, BigInteger y,
            int div) {
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
            BigDecimal r = powerNoRounding(x.negate(), y, div);
            if (Math_BigInteger.isEven(y)) {
                return r.negate();
                //return round(result.negate(), decimalPlaces, a_RoundingMode);
            } else {
                return r;
                //return round(result, decimalPlaces, a_RoundingMode);
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheckNoRounding(x, y.negate(),
                    div);
            return new BigDecimal(reciprocalWillBeIntegerReturnBigInteger(power));
        }
        BigDecimal r = powerNoSpecialCaseCheckNoRounding(x, y, div);
        return r;
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places. The calculation is divided into
     * parts...
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param div An optimisation parameter... If div &lt; 2 it is set to 2. If
     * div &gt; 256 it is set to 256.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y
     */
    public static BigDecimal powerNoSpecialCaseCheck(BigDecimal x, BigInteger y,
            int div, int dp, RoundingMode rm) {
        div = parseDiv(div);
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal r;// = BigDecimal.ONE;
        BigDecimal result0;
        BigDecimal result1;
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        if (y1.compareTo(div_BigInteger) != 1) {
            while (y1.compareTo(div_BigInteger) != 1) {
                div_BigInteger = div_BigInteger.divide(BigInteger.TWO);
                div = div / 2;
            }
        }
        yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
        if (yDivideAndRemainder[0].compareTo(BigInteger.ONE) == 0) {
            if (yDivideAndRemainder[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    r = power(x, y1.intValue(), dp, rm);
                    if (r.scale() > dp) {
                        return roundIfNecessary(r, dp, rm);
                    }
                    return r;
                } else {
                    div /= 2;
                    r = power(x, y, div, dp, rm);
                    if (r.scale() > dp) {
                        return roundIfNecessary(r, dp, rm);
                    }
                    return r;
                }
            } else {
                if (div < 4) {
                    result0 = power(x, div, dp + div + 2, rm);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = power(x, remainingY.intValue(),
                            dp + remainingY.intValue() + 2, rm);
                    //r = multiplyRoundIfNecessary(result0, result1, dp, rm);
                    r = result0.multiply(result1);
                    return r;
                } else {
                    result0 = power(x, div_BigInteger, div, dp + div + 2,
                            //dp + 6,
                            rm);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = power(x, remainingY.intValue(), div,
                            dp + remainingY.intValue() + 2, rm);
                    //return multiplyRoundIfNecessary(result0, result1, dp, rm);
                    return result0.multiply(result1);
                }
            }
        }
        result0 = power(x, div_BigInteger, div, dp + div + 2, rm);
        result0 = power(result0, yDivideAndRemainder[0], div,
                dp + yDivideAndRemainder[0].intValue() + 2,
                //decimalPlaces + 4,
                rm);
        //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
        BigInteger remainingY = yDivideAndRemainder[1];
        result1 = power(x, remainingY, div, dp + remainingY.intValue() + 2, rm);
        //return multiplyRoundIfNecessary(result0, result1, dp, rm);
        return result0.multiply(result1);
    }

    /**
     * Calculates and returns x raised to the power of y.
     *
     * @param x The base of the exponent.
     * @param y The exponent.
     * @param div An optimisation parameter... Parsed using
     * {@link #parseDiv(int)}.
     * @return x^y
     */
    public static BigDecimal powerNoSpecialCaseCheckNoRounding(BigDecimal x,
            BigInteger y, int div) {
        div = parseDiv(div);
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal r;// = BigDecimal.ONE;
        BigDecimal r0;
        BigDecimal r1;
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        if (y1.compareTo(div_BigInteger) != 1) {
            while (y1.compareTo(div_BigInteger) != 1) {
                div_BigInteger = div_BigInteger.divide(BigInteger.TWO);
                div = div / 2;
            }
        }
        yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
        if (yDivideAndRemainder[0].compareTo(BigInteger.ONE) == 0) {
            if (yDivideAndRemainder[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    r = powerNoRounding(x, y1.intValue());
                    return r;
                } else {
                    div /= 2;
                    r = powerNoRounding(x, y, div);
                    return r;
                }
            } else {
                if (div < 4) {
                    r0 = powerNoRounding(x, div);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    r1 = powerNoRounding(x, remainingY.intValue());
                    r = r0.multiply(r1);
                    return r;
                } else {
                    r0 = powerNoRounding(x, div_BigInteger, div);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    r1 = powerNoRounding(x, remainingY.intValue(), div);
                    r = r0.multiply(r1);
                    return r;
                }
            }
        }
        r0 = powerNoRounding(x, div_BigInteger, div);
        r0 = powerNoRounding(r0, yDivideAndRemainder[0], div);
        //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
        BigInteger remainingY = yDivideAndRemainder[1];
        r1 = powerNoRounding(x, remainingY, div);
        r = r0.multiply(r1);
        return r;
    }

    /**
     * Calculates and returns x raised to the power of y.
     *
     * @param x The base of the exponent.
     * @param y The exponent. Expected to be between 1 and 100. For y greater
     * than 8 use {@link #power(java.math.BigDecimal, int, int, int, java.math.RoundingMode).
     * @param div An optimisation parameter... Parsed using {@link #parseDiv(int)}.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y correct to {@code dp} number of decimal places using
     *  {@link RoundingMode} {@code rm} to round the result. For y &lt 2 this will return
     * a copy of x which is probably not what is wanted, so this method should not
     * be used for such calculations. No check is made for efficiency reasons.
     */
    private static BigDecimal power(BigDecimal x, int y, int dp,
            RoundingMode rm) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal r = x.pow(y1);// Don't attempt to support "supernormal" numbers.
        BigDecimal r = new BigDecimal(x.toString());
        int dpMultiply;
        for (int i = 1; i < y; i++) {
            dpMultiply = (r.scale() * x.scale()) + 1;
            dpMultiply = Math.min(dpMultiply, dp + 2 + y);
            //r = multiplyRoundIfNecessary(r, x, dpMultiply, rm);
            r = r.multiply(x);
        }
        if (r.scale() > dp) {
            roundIfNecessary(r, dp, rm);
        }
        return r;
    }

    /**
     * Calculates and returns an accurate representation of x^y. The method as
     * implemented duplicates x and multiplies this duplicate by x y times. For
     * large values of y this can be slow. A method which divides the
     * multiplication into more parts is provided by
     * {@link #powerNoRounding(java.math.BigDecimal, int, int)}. Values of x
     * that are greater than one and have a scale greater than will have a both
     * increased scale and magnitude and the result gets effectively increments
     * the scale and magnitude with each multiplication. Consequently, this may
     * not be the most appropriate multiplication method to use. If the
     * precision of the result required in terms of significant digits or
     * decimal places can be specified then these parameters can be passed to
     * other methods will return the result faster albeit it rounded to the
     * specified precision using either a default or input RoundingMode. The
     * scale of the result is y times the scale of x.
     *
     * @param x The base.
     * @param y The exponent.
     * @return x^y
     */
    public static BigDecimal powerNoRounding(BigDecimal x, int y) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.
        BigDecimal r = new BigDecimal(x.toString());
        for (int i = 1; i < y; i++) {
            r = r.multiply(x);
        }
        return r;
    }

    /**
     * x^(a+b) = x^a * x^b
     *
     * @param x The base.
     * @param y The exponent.
     * @param mc MathContext
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x^y
     */
    private static BigDecimal power(BigDecimal x, int y, MathContext mc, int dp,
            RoundingMode rm) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.

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
        if (y == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y == 1) {
            return roundIfNecessary(x, dp, rm);
        }
        // y = 2
        if (y == 2) {
            //return multiplyRoundIfNecessary(x, x, mc, dp, rm);
            return x.multiply(x);
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal r = power(x.negate(), y, dp, rm);
            if ((double) y / 2.0d > y / 2) {
                return r;
            } else {
                return r.negate();
            }
        }
        // y < 0
        if (y < 0) {
            BigDecimal power = powerNoSpecialCaseCheck(x, -y, mc, dp + 2, rm);
            BigDecimal r = reciprocal(power, dp, rm);
            return r;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        BigDecimal r = powerNoSpecialCaseCheck(x, y, mc, dp, rm);
        return r;
    }

    private static BigDecimal powerNoSpecialCaseCheck(BigDecimal x,
            int y, MathContext mc, int dp, RoundingMode rm) {
        // Find the integer part of x.
        BigInteger xIP = x.toBigInteger();
        if (xIP.compareTo(BigInteger.ZERO) == 0) {
            BigDecimal r = new BigDecimal(x.toString());
            int decimalPlacesMultiply;
            int xScale = x.scale();
            for (int i = 1; i < y; i++) {
                decimalPlacesMultiply = (r.scale() + x.scale()) + 1;
                decimalPlacesMultiply = Math.min(
                        decimalPlacesMultiply,
                        dp + 2 + y);
                if (decimalPlacesMultiply < mc.getPrecision()) {
                    //r = multiplyRoundIfNecessary(r, x, mc, decimalPlacesMultiply, rm);
                    r = r.multiply(x);
                }
            }
            if (r.scale() > dp) {
                roundIfNecessary(r, dp, rm);
            }
            return r;
        } else {
            BigInteger integerPartResult = xIP.pow(y);
            BigDecimal xRemainder = x.subtract(new BigDecimal(integerPartResult));
            BigDecimal fractionalPartResult;
            if (xRemainder.compareTo(BigDecimal.ZERO) == 1) {
                fractionalPartResult = powerNoSpecialCaseCheck(xRemainder, y, mc, dp,
                        rm);
            }
            //return multiply(xRemainder, xIP, dp, rm);
            return null;
            //xRemainder.multiply(xIP);
        }
    }

    /**
     * Calculates and returns the reciprocal 1 divided by x (1/x).
     *
     * @param x The number to calculate the reciprocal of.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return 1/x Accurate to decimalPlace number of decimal places. Throws an
     * ArithmeticException - if divisor is zero,
     * roundingMode==RoundingMode.UNNECESSARY and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    @Deprecated
    public static BigDecimal reciprocal(BigDecimal x, int dp, RoundingMode rm) {
        BigDecimal r = BigDecimal.ONE.divide(x, dp, rm);
        return r;
//        return roundIfNecessary(result, decimalPlace, a_RoundingMode);
    }

    /**
     * Calculates and returns the reciprocal 1 divided by x (1/x).
     *
     * @param x The number to calculate the reciprocal of.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return 1/x Accurate to decimalPlace number of decimal places. Throws an
     * ArithmeticException - if divisor is zero,
     * roundingMode==RoundingMode.UNNECESSARY and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    public static BigDecimal reciprocal(BigDecimal x, int oom) {
        return Math_BigDecimal.divide(BigDecimal.ONE, x, oom);
    }

    /**
     * Calculates and returns the reciprocal of {@code x} ({@code 1/x}) rounded
     * to the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> given by {@code oom} using {@link RoundingMode#HALF_UP}.
     *
     * @param x The base.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return {@code 1/x}
     * @throws {@link ArithmeticException} iff {@code x = 0}
     */
    public static BigDecimal reciprocal(BigInteger x, int oom) {
        return Math_BigDecimal.divide(BigDecimal.ONE, new BigDecimal(x), oom);
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
    public static BigInteger reciprocalWillBeIntegerReturnBigInteger(
            BigDecimal x) {
        BigDecimal r = reciprocalWillBeIntegerReturnBigDecimal(x);
        return r.toBigIntegerExact();
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
//        //  so I can round it correctly.
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
//        ans = ans.round(new MathContext(ans.precision() - ans.scale() + decimalPlaces, RoundingMode.HALF_EVEN));
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
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @param x The number to calculate the logarithm of.
     * @param dp The number of decimal places the result has to be correct to.
     * @return logbx
     */
    public static BigDecimal log(int b, BigDecimal x, int dp,
            RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("x <= 0");
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ZERO;
        }
        if (BigDecimal.valueOf(b).compareTo(x) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            BigDecimal rX = Math_BigDecimal.reciprocal(x, dp, rm);
            BigDecimal logRX = Math_BigDecimal.log(b, rX, dp, rm);
            return logRX.negate();
        }
        BigDecimal r = BigDecimal.ZERO;
        BigDecimal input = new BigDecimal(x.toString());
        int scale = input.precision() + dp;
        int maxite = Math.max(b, 10000);
        int ite = 0;
        BigDecimal maxErrorBD = new BigDecimal(BigInteger.ONE, dp + 1);
        //System.out.println("epsilon_BigDecimal " + epsilon_BigDecimal);
        //System.out.println("scale " + scale);
        BigDecimal baseBD = new BigDecimal(b);
        while (input.compareTo(baseBD) == 1) {
            r = r.add(BigDecimal.ONE);
            input = input.divide(baseBD, scale, rm);
        }
        BigDecimal f = new BigDecimal("0.5");
        input = input.multiply(input);
        int dps = Math.max(dp * 2, dp + 10); // Not sure this is safe enough, maybe log(base,maxite,0) would be?

        boolean condition;
        do {
//        while (((resultplusfraction).compareTo(result) == 1)
//                && (input.compareTo(BigDecimal.ONE) == 1)) {
            if (input.compareTo(baseBD) == 1) {
                input = input.divide(baseBD, scale, rm);
                r = r.add(f);
            }
            input = multiply(input, input, dps, rm);
            f = f.divide(TWO, scale, rm);
            BigDecimal raf = r.add(f);
            if (f.abs().compareTo(maxErrorBD) == -1) {
                break;
            }
            if (maxite == ite) {
                System.out.println("Warning: maxite reached in "
                        + Math_BigDecimal.class.getName()
                        + ".log(int,BigDecimal,int,RoundingMode) "
                        + " log(" + b + ", " + x + ", " + dp + ", "
                        + rm.toString() + ") " + r.toString());
                break;
            }
            ite++;
            condition = ((raf).compareTo(r) == 1)
                    && (input.compareTo(BigDecimal.ONE) == 1);

        } while (condition);
        return roundIfNecessary(r, dp, rm);
    }

    /**
     * Calculates and returns the logarithm of x. If x is less than {@code 0} an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param base The base of the logarithm.
     * @param x The number to take the logarithm of.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The log of x to the base base to decimalPlace precision
     */
    public static BigDecimal log(BigDecimal base, BigDecimal x, int dp, RoundingMode rm) {
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
            int index = xp - xup;
            BigDecimal xum = new BigDecimal(xu, index);
            //System.out.print(unscaledxmajor);
            BigDecimal logxum = log(base, xum, dp + index, rm);
            r = logxum.subtract(BigDecimal.valueOf(index));
            return roundIfNecessary(r, dp, rm);
        }
        r = BigDecimal.ZERO;
        BigDecimal input = new BigDecimal(x.toString());
        int scale = input.precision() + dp;
        BigInteger maxite = x.toBigInteger().max(BigInteger.valueOf(10000));
        BigInteger ite = BigInteger.ZERO;
        BigDecimal epsilon = new BigDecimal(BigInteger.ONE, dp + 1);
        //System.out.println("epsilon " + epsilon);
        //System.out.println("scale " + scale);
        while (input.compareTo(base) == 1) {
            r = r.add(BigDecimal.ONE);
            input = input.divide(base, scale, rm);
        }
        BigDecimal f = new BigDecimal("0.5");
        input = input.multiply(input);
        BigDecimal raf = r.add(f);
        int dps = Math.max(dp * 2, dp + 10); // Safe in terms of precision? Maybe log(base_int,maxite,0) would be more appropriate?
        while (((raf).compareTo(r) == 1)
                && (input.compareTo(BigDecimal.ONE) == 1)) {
            if (input.compareTo(base) == 1) {
                input = input.divide(base, scale, rm);
                r = r.add(f);
            }
            input = multiply(input, input, dps, rm);
            f = f.divide(TWO, scale, rm);
            raf = r.add(f);
            if (f.abs().compareTo(epsilon) == -1) {
                break;
            }
            if (maxite.compareTo(ite) == 0) {
                System.out.println("Warning: maxite reached in "
                        + Math_BigDecimal.class.getName()
                        + ".log(BigDecimal,BigDecimal,int,RoundingMode) "
                        + " log(" + base + ", " + x + ", " + dp + ", "
                        + rm.toString() + ") " + r.toString());
                break;
            }
            ite = ite.add(BigInteger.ONE);
        }
        return roundIfNecessary(r, dp, rm);
    }

    /**
     * @param x The number to round.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
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
        return x.movePointLeft(oom).setScale(0, rm).movePointRight(oom).stripTrailingZeros();
    }

    /**
     * This is the same as
     * {@link #round(java.math.BigDecimal, int, java.math.RoundingMode)} with
     * {@code rm = RoundingMode.HALF_UP}
     *
     * @param x The number to round
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
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
     * Round x if necessary setting the scale to dp.
     *
     * @param x The number to be rounded.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x rounded if necessary and with a scale set to decimalPlaces
     */
    public static BigDecimal roundToAndSetDecimalPlaces(BigDecimal x, int dp,
            RoundingMode rm) {
        BigDecimal r;
        int xScale = x.scale();
        if (xScale > dp) {
            r = x.setScale(dp, rm);
            return r;
        }
        r = x.setScale(dp);
        return r;
    }

    /**
     * Round x if necessary setting the scale to dp.
     *
     * @param x The number to be rounded.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x rounded to decimalPlaces with any trailing zeros stripped
     */
    public static BigDecimal roundStrippingTrailingZeros(BigDecimal x, int dp,
            RoundingMode rm) {
        BigDecimal r;
        int xScale = x.scale();
        if (xScale > dp) {
            r = x.setScale(dp, rm);
            return r;
        }
        r = x.stripTrailingZeros();
        return r;
    }

    /**
     * @Deprecated Use
     * {@link #round(java.math.BigDecimal, int, java.math.RoundingMode} instead,
     * where {@code oom = -dp}.
     *
     * This may return {@code x}.
     *
     * @param x The number to be rounded.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return x rounded to decimalPlaces if the scale of x is greater than
     * decimal places. Otherwise returns x.
     */
    @Deprecated
    public static BigDecimal roundIfNecessary(BigDecimal x, int dp,
            RoundingMode rm) {
        if (x.scale() > dp) {
            return roundIfNecessaryNoScaleCheck(x, dp, rm);
        }
        return x;
    }

    @Deprecated
    private static BigDecimal roundIfNecessaryNoScaleCheck(BigDecimal x,
            int dp, RoundingMode rm) {
        return x.setScale(dp, rm);
    }

    /**
     * Initialises {@link #pi} with first 10,000 digits. The data were obtained
     * from
     * <a href="http://www.eveandersson.com/pi/digits/">http://www.eveandersson.com/pi/digits/</a>
     * on 2011-01-14, where the first million digits of PI was also available...
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
    }

    /**
     * @param dp If greater than 10000, then a
     * {@link java.lang.RuntimeException} is thrown.
     * @param rm The RoundingMode.
     * @return {@link #pi} unless it is {@code null} in which case it is
     * initialised with 10000 decimal places.
     */
    public BigDecimal getPi(int dp, RoundingMode rm) {
        if (dp > 10000) {
            throw new RuntimeException("dp > 10000");
        }
        if (pi == null) {
            initPi();
        }
        return Math_BigDecimal.roundToAndSetDecimalPlaces(pi, dp, rm);
    }

    /**
     * Initialises {@link #piBy2}.
     *
     * @param dp DecimalPlaces
     * @param rm RoundingMode
     */
    private void initPiBy2(int dp, RoundingMode rm) {
        piBy2 = Math_BigDecimal.divideRoundIfNecessary(getPi(dp + 1, rm),
                BigDecimal.valueOf(2), dp, rm);
        piBy2DP = dp;
    }

    /**
     * @param dp The decimal place precision.
     * @param rm The RoundingMode.
     * @return {@link #pi} divided by 2.
     */
    public BigDecimal getPiBy2(int dp, RoundingMode rm) {
        if (piBy2 == null) {
            initPiBy2(dp, rm);
        }
        if (piBy2DP < dp) {
            initPiBy2(dp, rm);
        }
        return Math_BigDecimal.roundToAndSetDecimalPlaces(piBy2, dp, rm);
    }

    /**
     * Initialises {@link #pi2}.
     *
     * @param dp DecimalPlaces
     * @param rm RoundingMode
     */
    private void initPi2(int dp, RoundingMode rm) {
        pi2 = Math_BigDecimal.divideRoundIfNecessary(getPi(dp + 1, rm),
                BigDecimal.valueOf(2), dp, rm);
        pi2DP = dp;
    }

    /**
     * @param dp The decimal place precision.
     * @param rm The RoundingMode.
     * @return {@link #pi} divided by 2.
     */
    public BigDecimal getPi2(int dp, RoundingMode rm) {
        if (pi2 == null) {
            initPi2(dp, rm);
        }
        if (pi2DP < dp) {
            initPi2(dp, rm);
        }
        return Math_BigDecimal.roundToAndSetDecimalPlaces(pi2, dp, rm);
    }

    /**
     * Initialises {@link #piBy4}.
     *
     * @param dp DecimalPlaces
     * @param rm RoundingMode
     */
    private void initPiBy4(int dp, RoundingMode rm) {
        piBy4 = Math_BigDecimal.divideRoundIfNecessary(getPi(dp + 1, rm),
                BigDecimal.valueOf(4), dp, rm);
        piBy4DP = dp;
    }

    /**
     * @param dp The decimal place precision.
     * @param rm The RoundingMode.
     * @return {@link #pi} divided by 4.
     */
    public BigDecimal getPiBy4(int dp, RoundingMode rm) {
        if (piBy4 == null) {
            initPiBy4(dp, rm);
        }
        if (piBy4DP < dp) {
            initPiBy4(dp, rm);
        }
        return Math_BigDecimal.roundToAndSetDecimalPlaces(piBy4, dp, rm);
    }

    /**
     * If {@link #e} has enough precision it is rounded and returned otherwise
     * {@link #e} is recalculated to the required precision, stored and a copy
     * returned.
     *
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * <ul>
     * <li>...</li>
     * <li>{@code oom=-1} rounds to the nearest {@code 0.1}</li>
     * <li>{@code oom=0} rounds to the nearest {@code unit}</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li>
     * <li>...</li>
     * </ul>
     * the result is rounded to if rounding is needed.
     * @return A value of the Euler constant rounded to {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    public BigDecimal getE(int oom) {
        if (e != null) {
            if (eOOM < oom) {
                return round(e, oom);
            }
        }
        e = new BigDecimal("2");
        int dps = 3 - oom;
        //int maxite = 10000;
        //int maxite = decimalPlaces * 2;
        int maxite = dps;
        if (bi == null) {
            initBIF(maxite);
        } else {
            bi.factorial(maxite);
        }
        BigDecimal tollerance = new BigDecimal(BigInteger.ONE, dps);
        // Use Taylor Series
        for (int i = 2; i < maxite; i++) {
            BigDecimal f = new BigDecimal(bi.factorials.get(i));
            BigDecimal rf = Math_BigDecimal.divide(BigDecimal.ONE, f, oom);
            e = e.add(rf);
            if (rf.compareTo(tollerance) == -1) {
                break;
            }
        }
        eOOM = oom;
        return round(e, oom);
    }

    /**
     * Calculates and returns {@link #e} to the power of x. Note that:
     * <ul>
     * <li>e^x = 1 + x/1! + x^2/2! + x^3/3! +...</li>
     * </ul>
     *
     * @param x The value for which e to the power of y (e^y) is returned.
     * @param bd may be null. Passing a Math_BigDecimal in can save computation.
     * The Euler constant is used in most invocations.
     * @param oom The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return e^y where e is the Euler constant. The result is returned correct
     * to decimalPlaces decimal place precision.
     */
    public BigDecimal exp(BigDecimal x, int oom) {
        BigDecimal r;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // Check bd
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return getE(oom);
        }
        BigInteger xBi = x.toBigInteger();
        BigDecimal xBiBd = new BigDecimal(xBi, 0);
        if (xBiBd.compareTo(x) == 0) {
            return exp(xBi, oom);
        }
        BigDecimal fract = x.subtract(xBiBd);
        if (fract.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal exp = exp(fract.negate(), oom - fract.scale());
            r = reciprocal(exp, oom);
            r = exp(xBi, oom + 2).multiply(r);
            return Math_BigDecimal.round(r, oom);
        }
        int oomm3 = oom - 3;
        r = BigDecimal.ONE.add(fract);
//        if (bd.bi == null) {
//            bd.init_Factorial_Generic_BigInteger(maxite);
//        } else {
//            bd.bi.factorial(maxite);
//        }
        BigDecimal dpxff;
        BigDecimal tollerance = new BigDecimal(BigInteger.ONE, 1 - oom);
        // Use Taylor Series
        BigInteger bi = BigInteger.ONE;
        Integer f = 1;
        while (true) {
            bi = bi.add(BigInteger.ONE);
            f = f + 1;
            BigDecimal ff = new BigDecimal(this.bi.factorial(f));
            /**
             * May need dpd to be larger (even though the bottom of the Taylor
             * series grows fast).
             */
            BigDecimal px = power(fract, bi, 64, oomm3, RoundingMode.HALF_UP);
            dpxff = divide(px, ff, oomm3);
            r = r.add(dpxff);
            if (dpxff.compareTo(tollerance) == -1) {
                break;
            }
        }
        r = exp(xBi, oom - 2).multiply(r);
        return round(r, oom);
    }

    /**
     * Calculates and returns the exponent of x:
     * <ul>
     * <li>e^x = 1 + x/1! + x^2/2! + x^3/3! +...</li>
     * </ul>
     *
     * @param x The exponent.
     * @param bd A Math_BigDecimal.
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
     * @return e^x where e is the Euler constant to a sufficient precision to
     * return the result accurate to the requested {@code oom} using
     * {@link RoundingMode#HALF_UP}.
     */
    protected BigDecimal exp(BigInteger x, int oom) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigInteger.ZERO) == -1) {
            return Math_BigDecimal.reciprocal(exp(x.negate(), oom), oom);
        }
        BigDecimal r = BigDecimal.ZERO;
        if (x.compareTo(BigInteger.valueOf(999999999)) != 1
                && x.compareTo(BigInteger.ZERO) != -1) {
            int xi = x.intValueExact();
            r = getE(oom - xi).pow(xi);
        } else {
            ArrayList<BigDecimal> rp = new ArrayList<>();
            BigDecimal rpp = getE(oom - Math_BigInteger.log10(x));
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
        return Math_BigDecimal.round(r, oom);
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
//        BigDecimal rootRoundIfNecessary;
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
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        y,
//                        elementOneReciprocal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
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
     * Calculate and return the natural logarithm of x accurate to dp decimal
     * places. This uses Newton's algorithm adapted from
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     *
     * @param x The number for which the natural logarithm is calculated.
     * @param bd may be null. Passing a Math_BigDecimal in can save computation.
     * The Euler constant is used in most invocations.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The natural logarithm of x accurate to decimalPlaces number of
     * decimal places and with result rounded using a_RoundingMode if necessary.
     */
    public BigDecimal ln(BigDecimal x, Math_BigDecimal bd, int dp,
            RoundingMode rm) {
        // Check that x > 0.
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException(
                    "x <= 0 in " + BigDecimal.class.getName()
                    + "ln(BigDecimal,Generic_BigDecimal,int,RoundingMode)");
        }
        // The number of digits to the left of the decimal point.
        int magnitude = x.toString().length() - x.scale() - 1;
        if (magnitude < 3) {
            return lnNewton(x, dp, rm);
        } else {
            // Compute magnitude*ln(x^(1/magnitude)).
            // x^(1/magnitude)
            BigDecimal root = rootRoundIfNecessary(x, magnitude, dp, rm);
            // ln(x^(1/magnitude))
            BigDecimal lnRoot = lnNewton(root, dp, rm);
            //BigDecimal lnRoot = ln(rootRoundIfNecessary, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode);
            // magnitude*ln(x^(1/magnitude))
            BigDecimal result = BigDecimal.valueOf(magnitude).multiply(lnRoot);
            return result;
            //setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        }
    }

    /**
     * Compute and return the natural logarithm of x accurate to decimalPlaces
     * decimal place precision using Newton's algorithm.
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     */
    private BigDecimal lnNewton(BigDecimal x, int scale, RoundingMode rm) {
        BigDecimal r = new BigDecimal(x.toString());
        int sp1 = scale + 1;
        BigDecimal term;
        // Convergence tolerance = 5*(10^-(scale+1))
        BigDecimal tolerance = BigDecimal.valueOf(5).movePointLeft(sp1);
        // Loop until the approximation converges
        // (two successive approximations are within the tolerance).
        do {
            // e^toCompare
            BigDecimal exp = exp(r, sp1);
            // (e^toCompare - x)/e^toCompare
            term = exp.subtract(x).divide(exp, sp1, rm);
            // toCompare - (e^toCompare - x)/e^toCompare
            r = r.subtract(term);
            //Thread.yield();
        } while (term.compareTo(tolerance) > 0);
        return r.setScale(scale, rm);
    }

    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static BigDecimal max(Collection<BigDecimal> c) {
        // Using lambda expression
        return c.parallelStream().max(Comparator.comparing(i -> i)).get();
//        Iterator<BigDecimal> ite = c.iterator();
//        BigDecimal r = null;
//        if (ite.hasNext()) {
//            r = ite.next();
//        }
//        while (ite.hasNext()) {
//            r = r.max(ite.next());
//        }
//        return r;
    }

    /**
     * Calculates and returns the position of the most significant digit. If the
     * most significant digit is right of decimal point then this is positive.
     * If left of decimal point this is negative.
     *
     * @param x The number for which the most significant digit is evaluated.
     * @return The position of the most significant digit.
     */
    @Deprecated
    public static int positionSignificantDigit(BigDecimal x) {
        int r;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            r = positionSignificantDigit(x.negate());
            return r;
        }
        BigInteger xu = x.unscaledValue();
        String xus = xu.toString();
        r = xus.length() - x.scale();
        if (r < 1) {
            r--;
        }
        return r;
    }

    /**
     * Calculate and return the closer to negative infinity value of {@code x}
     * where the non-zero digit with the largest
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) is lowered by {@code 1}. Trailing zeros are stripped
     * from the result. Examples
     * <table>
     * <th><td>x</td><td>floorSignificantDigit</td></th>
     * <tr><td>0.0001</td><td>0.0001</td></tr>
     * <tr><td>0.00012</td><td>0.0001</td></tr>
     * <tr><td>0.0009</td><td>0.0009</td></tr>
     * <tr><td>1.00099</td><td>1</td></tr>
     * <tr><td>10008798.00099</td><td>10000000</td></tr>
     * <tr><td>-1.00099</td><td>2</td></tr>
     * <tr><td>-10008798.00099</td><td>-20000000</td></tr>
     * <tr><td>-0.00099</td><td>-0.001</td></tr>
     * <tr><td>-0.99</td><td>-1</td></tr>
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
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) is increased by {@code 1}. Trailing zeros are
     * stripped from the result. Examples
     * <table>
     * <th><td>x</td><td>floorSignificantDigit</td></th>
     * <tr><td>0.0001</td><td>0.0002</td></tr>
     * <tr><td>0.00012</td><td>0.0002</td></tr>
     * <tr><td>0.0009</td><td>0.001</td></tr>
     * <tr><td>1.00099</td><td>2</td></tr>
     * <tr><td>10008798.00099</td><td>20000000</td></tr>
     * <tr><td>-1.00099</td><td>-1</td></tr>
     * <tr><td>-10008798.00099</td><td>-10000000</td></tr>
     * <tr><td>-0.00099</td><td>-0.0009</td></tr>
     * <tr><td>-0.99</td><td>-0.9</td></tr>
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
     * Calculates and returns the root-th root of x.
     *
     * @param x The value to be rooted.
     * @param root The root.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The root-th root of x.
     */
    public static BigDecimal rootRoundIfNecessary(BigDecimal x, BigInteger root,
            int dp, RoundingMode rm) {
        // The Java6 limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root.compareTo(BigInteger.valueOf(999999999)) != 1) {
            BigDecimal r = rootRoundIfNecessary(x, root.intValue(), dp, rm);
            // return result;
            return roundIfNecessary(r, dp, rm);
        } else {
            // Deal with special cases
            // x <= 0
            if (x.compareTo(BigDecimal.ZERO) != 1) {
                // Complex roots are not handled!
                throw new IllegalArgumentException(
                        "x <= 0 in " + Math_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
            }
            // x = 1
            if (x.compareTo(BigDecimal.ONE) == 0) {
                return BigDecimal.ONE;
            }
            // rootRoundIfNecessary = 1
            if (root.compareTo(BigInteger.ONE) == 0) {
                BigDecimal r = new BigDecimal(x.toString());
                if (r.scale() > dp) {
                    return roundIfNecessary(r, dp, rm);
                }
                return r;
            }
            // x < 1
            if (x.compareTo(BigDecimal.ONE) == -1) {
                int xscale = x.scale();
                // Optimisation (untested if it is faster) for a large x.scale()
                int rootLength = root.toString().length();
                if (xscale < 10) {
                    BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                    BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                    // numerator will be greater than denominator and both will
                    // be close to a value of 1, a large number of decimal 
                    // places for both numerator and denominator are required!
                    BigDecimal denominator = rootRoundIfNecessary(
                            denominatorUnrooted, root,
                            dp + xscale + rootLength, // Can we cope with less or do we need more?
                            rm);
                    int denominatorScale = denominator.scale();
                    BigDecimal numerator = rootRoundIfNecessary(
                            numeratorUnrooted, root,
                            dp + xscale + rootLength, // Can we cope with less or do we need more?
                            rm);
                    BigDecimal r = Math_BigDecimal.divideRoundIfNecessary(
                            numerator, denominator, dp, rm);
                    return r;
                } else {
                    BigDecimal r = rootLessThanOne(x, root, dp, rm);
                    return roundIfNecessary(r, dp, rm);
                }
            }
            // x > 1
            //int safeDecimalPlaces = decimalPlaces + 5;
            BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, dp);
            // Check there is a rootRoundIfNecessary in the precision and return 0 if not
            BigDecimal comparator = BigDecimal.ONE.add(epsilon_BigDecimal);
            boolean powerTest = powerTestAbove(x, comparator, root, 256, dp, rm);
            if (powerTest) {
                System.out.println("No root in the precision returning BigDecimal.ONE"
                        + "in " + Math_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
                return BigDecimal.ONE;
            }
            BigDecimal result;// = new BigDecimal("1");
            int rootInitialisationMaxIte = 10;
            result = rootInitialisation(x, root, epsilon_BigDecimal, rootInitialisationMaxIte, dp, rm);
            // Newton Raphson
            //int newtonRaphsonMaxite = 100;
            result = newtonRaphson(x, result, root, epsilon_BigDecimal,
                    //newtonRaphsonMaxite,
                    dp + 1);
//                    a_RoundingMode);
            // return result;
            return roundIfNecessary(result, dp, rm);
        }
    }

    /**
     * Calculates and returns the root-th root of x.
     *
     * @param x The value to be rooted.
     * @param root The root.
     * @return The root-th root of x.
     */
    public static BigDecimal rootNoRounding(BigDecimal x, BigInteger root) {
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root.compareTo(BigInteger.valueOf(999999999)) != 1) {
            BigDecimal r = rootNoRounding(x, root.intValue());
            return r;
        } else {
            // Deal with special cases
            // x <= 0
            if (x.compareTo(BigDecimal.ZERO) != 1) {
                // Complex roots are not handled!
                throw new IllegalArgumentException(
                        "x <= 0 in " + Math_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
            }
            // x = 1
            if (x.compareTo(BigDecimal.ONE) == 0) {
                return BigDecimal.ONE;
            }
            // rootRoundIfNecessary = 1
            if (root.compareTo(BigInteger.ONE) == 0) {
                BigDecimal result = new BigDecimal(x.toString());
                return result;
            }
            // x < 1
            if (x.compareTo(BigDecimal.ONE) == -1) {
                int xscale = x.scale();
                // Optimisation (untested if it is faster) for a large x.scale()
                int rootLength = root.toString().length();
                if (xscale < 10) {
                    BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                    BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                    // numerator will be greater than denominator and both will
                    // be close to a value of 1, a large number of decimal 
                    // places for both numerator and denominator are required!
                    BigDecimal denominator = rootNoRounding(denominatorUnrooted, root);
                    int denominatorScale = denominator.scale();
                    BigDecimal numerator = rootNoRounding(numeratorUnrooted, root);
                    BigDecimal r = Math_BigDecimal.divideNoRounding(
                            numerator, denominator);
                    return r;
                } else {
                    BigDecimal result = rootLessThanOneNoRounding(x, root);
                    return result;
                }
            }
            BigDecimal r;// = new BigDecimal("1");
            int rootInitialisationMaxIte = 10;
            r = rootInitialisationNoRounding(x, root, rootInitialisationMaxIte);
            // Newton Raphson
            //int newtonRaphsonMaxite = 100;
            r = newtonRaphsonNoRounding(x, r, root);
            return r;
        }
    }

    /**
     * Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphson(BigDecimal x, BigDecimal result0,
            BigInteger root, BigDecimal epsilon,
            //int maxite,
            int decimalPlaces) {
//            RoundingMode a_RoundingMode) {
        RoundingMode rm;
        if (x.compareTo(BigDecimal.ONE) == 1) {
            rm = RoundingMode.DOWN;
        } else {
            rm = RoundingMode.UP;
        }
        BigDecimal r = new BigDecimal(result0.toString());
        BigDecimal root_BigDecimal = new BigDecimal(root);
        //int magnitudex = magnitude(x);
        //System.out.println("magnitudex " + magnitudex);
        int precision = decimalPlaces + 5;
        int divprecision = decimalPlaces + 10;

        BigDecimal r0;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigInteger rootsubtract1 = root.subtract(BigInteger.ONE);

        // Initialise toCompare and previousResult_BigDecimal
        r0 = new BigDecimal("1");

        // Newton Raphson
        while (true) {
            //for (int i = 0; i < maxite; i++) {
            resultipowroot = power(r, root, 64, precision, rm);
            resultipowrootsubtract1 = power(r, rootsubtract1, 64, precision, rm);
            divisor = resultipowrootsubtract1.multiply(root_BigDecimal);
            r = r.subtract(Math_BigDecimal.divideNoCaseCheckRoundIfNecessary(
                    resultipowroot.subtract(x), divisor, divprecision, rm));
//            result = result.subtract(
//                    resultipowroot.subtract(x).divide(divisor, a_MathContext));
            // Test for convergence
            if ((r0.subtract(r)).abs().compareTo(epsilon) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                break;
            }
            //System.out.println(result.toPlainString());
//            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
            r0 = new BigDecimal(r.toString());
        }
//        if (maxiteReached) {
//            System.out.println(
//                    "maxite reached without finding rootRoundIfNecessary in "
//                    + Math_BigDecimal.class.getName() + ".newtonRaphson(...)"
//                    + " previousResult_BigDecimal0.subtract(result).abs() "
//                    + previousResult_BigDecimal0.subtract(result).abs());
//        }
        return roundIfNecessary(r, decimalPlaces, rm);
    }

    /**
     * Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonNoRounding(BigDecimal x,
            BigDecimal result0, BigInteger root) {
        BigDecimal r = new BigDecimal(result0.toString());
        BigDecimal root_BigDecimal = new BigDecimal(root);
        //System.out.println("magnitudex " + magnitudex);
        BigDecimal previousResult_BigDecimal;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigInteger rootsubtract1 = root.subtract(BigInteger.ONE);
        previousResult_BigDecimal = new BigDecimal("1");
        // Newton Raphson
        while (true) {
            resultipowroot = powerNoRounding(r, root, 64);
            resultipowrootsubtract1 = powerNoRounding(r, rootsubtract1, 64);
            divisor = resultipowrootsubtract1.multiply(root_BigDecimal);
            r = r.subtract(resultipowroot.subtract(x).divide(divisor));
            // Test for convergence
            if (previousResult_BigDecimal.compareTo(r) == 0) {
                break;
            }
            //System.out.println(result.toPlainString());
            previousResult_BigDecimal = new BigDecimal(r.toString());
        }
        return r;
    }

    /**
     * Uses decimalPlace division which compared with MathContext based division
     * was tested to be slower and thought not to impact on precision.
     *
     * @see
     * newtonRaphson0(BigDecimal,BigDecimal,BigInteger,BigDecimal,int,int,RoundingMode)
     * @param x
     * @param result0
     * @param root_BigInteger
     * @param epsilon_BigDecimal
     * @param maxite
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphson0(BigDecimal x, BigDecimal result0,
            BigInteger root_BigInteger, BigDecimal epsilon_BigDecimal,
            int maxite, int dp, RoundingMode rm) {
        BigDecimal r = new BigDecimal(result0.toString());
        int safeDecimalPlaces = dp + 5;
        BigInteger rootsubtract1_BigInteger = root_BigInteger.subtract(
                BigInteger.ONE);
        BigDecimal previousResult_BigDecimal;
        BigDecimal previousResult_BigDecimal0;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigDecimal resultipowrootsubtractx;
        BigDecimal resultipowrootsubtractxdividedivisor;
        boolean maxiteReached = true;
        // Initialise toCompare and previousResult_BigDecimal
        previousResult_BigDecimal = new BigDecimal(result0.toString());
        previousResult_BigDecimal0 = new BigDecimal(result0.toString());
        for (int i = 0; i < maxite; i++) {
            resultipowroot = power(r, root_BigInteger, 64, safeDecimalPlaces, rm);
            resultipowrootsubtract1 = power(r, rootsubtract1_BigInteger, 64,
                    safeDecimalPlaces, rm);
            divisor = multiply(resultipowrootsubtract1,
                    root_BigInteger, safeDecimalPlaces, rm);
            resultipowrootsubtractx = resultipowroot.subtract(x);
            resultipowrootsubtractxdividedivisor = divideRoundIfNecessary(
                    resultipowrootsubtractx, divisor, safeDecimalPlaces, rm);
            r = r.subtract(resultipowrootsubtractxdividedivisor);
            // Test for convergence
            if (previousResult_BigDecimal.subtract(r).abs().compareTo(epsilon_BigDecimal) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                maxiteReached = false;
                break;
            }
            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
            previousResult_BigDecimal = new BigDecimal(r.toString());
        }
        if (maxiteReached) {
            System.out.println("previousResult_BigDecimal0.subtract(result).abs() " + previousResult_BigDecimal0.subtract(r).abs());
        }
        return roundIfNecessary(r, dp, rm);
    }

//    public static BigDecimal newtonRaphson(
//            BigDecimal x,
//            BigDecimal result0,
//            BigInteger rootRoundIfNecessary,
//            BigDecimal epsilon_BigDecimal,
//            int maxite,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result = new BigDecimal(result0.toString());
//        int safeDecimalPlaces = decimalPlaces + 5;
//        BigInteger rootsubtract1_BigDecimal = rootRoundIfNecessary.subtract(BigInteger.ONE);
//        BigDecimal previousResult_BigDecimal;
//        BigDecimal previousResult_BigDecimal0;
//        BigDecimal resultipowroot;
//        BigDecimal resultipowrootsubtract1;
//        BigDecimal divisor;
//        BigDecimal resultipowrootsubtractx;
//        BigDecimal resultipowrootsubtractxdividedivisor;
//        boolean maxiteReached = true;
//        // Initialise toCompare and previousResult_BigDecimal
//        previousResult_BigDecimal = new BigDecimal("1");
//        previousResult_BigDecimal0 = new BigDecimal("1");
//        int i = 0;
//        while (i < maxite) {
//            resultipowroot = power(
//                    result,
//                    rootRoundIfNecessary,
//                    64,
//                    decimalPlaces,
//                    a_RoundingMode);
//            resultipowrootsubtract1 = power(
//                    result,
//                    rootsubtract1_BigDecimal,
//                    64,
//                    decimalPlaces,
//                    a_RoundingMode);
//            divisor = multiply(
//                    resultipowrootsubtract1,
//                    rootRoundIfNecessary,
//                    safeDecimalPlaces,
//                    a_RoundingMode);
//            resultipowrootsubtractx = resultipowroot.subtract(x);
//            resultipowrootsubtractxdividedivisor = divide(
//                    resultipowrootsubtractx,
//                    divisor,
//                    safeDecimalPlaces,
//                    a_RoundingMode);
//            result = result.subtract(resultipowrootsubtractxdividedivisor);
//            // Test for convergence
//            if (previousResult_BigDecimal.subtract(result).abs().compareTo(epsilon_BigDecimal) != 1) {
//                System.out.println("Root found in iteration " + i + " out of " + maxite);
//                maxiteReached = false;
//                break;
//            }
//            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
//            previousResult_BigDecimal = new BigDecimal(result.toString());
//            i++;
//        }
//        if (maxiteReached) {
//            System.out.println("previousResult_BigDecimal0.subtract(result).abs() " + previousResult_BigDecimal0.subtract(result).abs());
//        }
//        return result;
//    }
    private static BigDecimal rootInitialisation(BigDecimal x, BigInteger root,
            BigDecimal epsilon_BigDecimal, int maxite, int dp,
            RoundingMode rm) {
        BigDecimal result;
        int div = 64;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOne(x, root, epsilon_BigDecimal,
                    maxite, dp, rm);
        } else {
            int i = 0;
            result = BigDecimal.ONE.add(epsilon_BigDecimal);
            boolean powerTestAbove = powerTestAbove(x, result, root, 64, dp, rm);
            if (powerTestAbove) {
                // Root cannot be found within current precision...
                int debug = 1;
                return BigDecimal.ONE;
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;

            BigInteger rootdiv = new BigInteger(root.toString());
            //boolean notInitialised = true;
            //BigDecimal difference_BigDecimal;
//            while (i < maxite && notInitialised) {
            while (i < maxite) {
//                difference_BigDecimal = a.subtract(b);
//                if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                    notInitialised = false;
//                }
//                if (notInitialised) {
                // Disect
                c = divideRoundIfNecessary(a.subtract(b), rootdiv, dp + 1, rm);
                c = b.add(c);
                powerTestAbove = powerTestAbove(x, c, root, div, dp, rm);
                if (powerTestAbove) {
                    a = c;
                    //a = new BigDecimal(c.toString());
                } else {
                    b = c;
                    //b = new BigDecimal(c.toString());
                    rootdiv = rootdiv.divide(BigInteger.TWO);
                    result = b;
//                        difference_BigDecimal = (result.subtract(c)).abs();
//                        if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                            notInitialised = false;
//                        }
                }
//                if (notInitialised) {
                // Bisect
                c = divideRoundIfNecessary(a.subtract(b), TWO, dp + 1, rm);
                c = c.add(b);
                powerTestAbove = powerTestAbove(x, c, root, div, dp, rm);
                if (powerTestAbove) {
                    a = c;
                    //a = new BigDecimal(c.toString());
                } else {
                    b = c;
                    //b = new BigDecimal(c.toString());
                    result = b;
//                            difference_BigDecimal = (result.subtract(c)).abs();
//                            if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                                notInitialised = false;
//                            }
                }
//                }
//                }
                i++;
            }
        }
        return result;
    }

    private static BigDecimal rootInitialisationNoRounding(BigDecimal x,
            BigInteger root, int maxite) {
        BigDecimal r;
        int div = 64;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOneNoRounding(x, root, maxite);
        } else {
            int i = 0;
            r = BigDecimal.ONE;
            boolean powerTestAbove = powerTestAboveNoRounding(x, r, root, 64);
            if (powerTestAbove) {
                // Root cannot be found within current precision...
                int debug = 1;
                return BigDecimal.ONE;
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;

            BigInteger rootdiv = new BigInteger(root.toString());
            while (i < maxite) {
                // Disect
                c = divideNoRounding(a.subtract(b), rootdiv);
                c = b.add(c);
                powerTestAbove = powerTestAboveNoRounding(x, c, root, div);
                if (powerTestAbove) {
                    a = c;
                } else {
                    b = c;
                    rootdiv = rootdiv.divide(BigInteger.TWO);
                    r = b;
                }
                // Bisect
                c = divideNoRounding(a.subtract(b), TWO);
                c = c.add(b);
                powerTestAbove = powerTestAboveNoRounding(x, c, root, div);
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

//    private static BigDecimal rootInitialisation0(
//            BigDecimal x,
//            BigDecimal root_BigDecimal,
//            BigDecimal maxError_BigDecimal,
//            BigDecimal a0,
//            BigDecimal e,
//            Math_BigDecimal a_Generic_BigDecimal,
//            int decimalPlaces,
//            MathContext a_MathContext,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        if (x.compareTo(BigDecimal.ONE) == -1) {
//            result = BigDecimal.ONE.subtract(maxError_BigDecimal);
//            // Bisect
//            BigDecimal a = a0;
//            BigDecimal b = result;
//            BigDecimal c;
//            BigDecimal cPowerRoot;
//            boolean notInitialised = true;
//            while (notInitialised) {
//                c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                cPowerRoot = power(
//                        c,
//                        root_BigDecimal,
//                        e,
//                        a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (cPowerRoot.compareTo(x) == 1) {
//                    b = c;
//                    if (a.compareTo(c) == 0) {
//                        notInitialised = false;
//                    }
//                } else {
//                    result = c;
//                    notInitialised = false;
//                }
//            }
//        } else {
//            result = BigDecimal.ONE.add(maxError_BigDecimal);
//            BigDecimal a = x.divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//            if (a.compareTo(BigDecimal.ONE) == 1) {
//                result = BigDecimal.ONE.add(maxError_BigDecimal);
//                // Disect
//                BigDecimal b = a0;
//                BigDecimal c;
//                boolean notInitialised = true;
//                BigDecimal cPowerRoot;
//                while (notInitialised) {
//                    c = (a.add(b)).divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//                    cPowerRoot = power(
//                            c,
//                            root_BigDecimal,
//                            e,
//                            a_Generic_BigDecimal,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRoot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        // Bisect
//                        c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                        cPowerRoot = power(
//                                c,
//                                root_BigDecimal,
//                                e,
//                                a_Generic_BigDecimal,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRoot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            result = c;
//                            notInitialised = false;
//                        }
//                    }
//                }
//            } else {
//                // Bisect
//                a = x.divide(TWO, decimalPlaces, a_RoundingMode);
//                BigDecimal b = result;
//                BigDecimal c;
//                BigDecimal aAddb;
//                BigDecimal aAddbSubtractTwo;
//                BigDecimal cPowerroot;
//                boolean notInitialised = true;
//                BigDecimal cPowerRoot;
//                while (notInitialised) {
//                    // Disect
//                    aAddb = a.add(b);
//                    aAddbSubtractTwo = aAddb.subtract(TWO);
//                    if (aAddbSubtractTwo.compareTo(BigDecimal.ZERO) != 1) {
//                        notInitialised = false;
//                    } else {
//                        c = (aAddb).divide(aAddbSubtractTwo, decimalPlaces, a_RoundingMode);
//                        cPowerRoot = power(
//                                c,
//                                root_BigDecimal,
//                                e,
//                                a_Generic_BigDecimal,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRoot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            while (notInitialised) {
//                                // Bisect
//                                a = a.divide(TWO, decimalPlaces, a_RoundingMode);
//                                b = c;
//                                aAddb = a.add(b);
//                                aAddbSubtractTwo = aAddb.subtract(BigDecimal.ONE);
//                                c = (aAddb).divide(aAddbSubtractTwo, decimalPlaces, a_RoundingMode);
//                                cPowerRoot = power(
//                                        c,
//                                        root_BigDecimal,
//                                        e,
//                                        a_Generic_BigDecimal,
//                                        decimalPlaces,
//                                        a_RoundingMode);
//                                if (cPowerRoot.compareTo(x) == 1) {
//                                    a = c;
//                                } else {
//                                    result = c;
//                                    notInitialised = false;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
    /**
     * Calculates and returns the root-th root of x.
     *
     * @param x The number to root. Expected to be greater than or equal to
     * {@code 0}.
     * @param root The root to calculate.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    public static BigDecimal rootRoundIfNecessary(BigDecimal x, int root,
            int dp, RoundingMode rm) {
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
            BigDecimal result = new BigDecimal(x.toString());
            //return result;
            return roundIfNecessary(result, dp, rm);
        }
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root >= 999999999) {
//            Math_BigDecimal a_Generic_BigDecimal =
//                    new Math_BigDecimal();
            BigDecimal r = rootRoundIfNecessary(x, BigInteger.valueOf(root), dp, rm);
            return r;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            // This is thought to be an optimisation for a large x.scale()
            // a faster way is needed! This may generally be generally 
            // faster anyway but some tests are needed to be sure.
            int rootLength = Integer.toString(root).length();
            if (xscale < 10) {
                BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                //int precisionMinusScale = xprecision - xscale;
                BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal numerator = rootRoundIfNecessary(
                        numeratorUnrooted,
                        root,
                        //a_Generic_BigDecimal,
                        dp + (rootLength * 2), // Can we cope with less or do we need more?
                        rm);
                BigDecimal denominator = rootRoundIfNecessary(
                        denominatorUnrooted,
                        root,
                        //a_Generic_BigDecimal,
                        dp + rootLength, // Can we cope with less or do we need more?
                        rm);
                BigDecimal r = Math_BigDecimal.divideRoundIfNecessary(
                        numerator, denominator, dp, rm);
                return r;
            } else {
                BigDecimal r = rootLessThanOne(x, root, dp, rm);
                return roundIfNecessary(r, dp, rm);
            }
        }
        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, dp);
        BigDecimal comparator = BigDecimal.ONE.add(epsilon_BigDecimal);
        // Check there is a rootRoundIfNecessary in the precision and return 0 if not
        BigInteger root_BigInteger = BigInteger.valueOf(root);
        boolean powerTest = powerTestAbove(x, comparator, root_BigInteger, 256, dp, rm);
        if (powerTest) {
            System.out.println("No root in the precision... ");
            //return BigDecimal.ZERO;
            return BigDecimal.ONE;
        }
        BigDecimal r;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        r = rootInitialisation(x, root_BigInteger, epsilon_BigDecimal,
                rootInitialisationMaxite, dp, rm);
        //int newtonRaphsonMaxite = 100;
        r = newtonRaphson(x, r, root_BigInteger, epsilon_BigDecimal,
                //newtonRaphsonMaxite,
                dp + 1);
        return roundIfNecessary(r, dp, rm);
    }

    /**
     * For calculating the {@code root}th root of {@code x} without rounding.
     *
     * @param x The value to calculate and return the root for.
     * @param root The root.
     * @return The {@code root}th root of {@code x}
     */
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
        /**
         * The current (Java6) limit for n in x.pow(n) for BigDecimal x and int
         * n is {@code -999999999 < n < 999999999}
         */
        if (root >= 999999999) {
            return rootNoRounding(x, BigInteger.valueOf(root));
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            /* This is thought to be an optimisation for a large x.scale()
            * a faster way is needed! This may  be generally 
            * faster anyway but some tests are needed to be sure.
             */
            int rootLength = Integer.toString(root).length();
            if (xscale < 10) {
                BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                //int precisionMinusScale = xprecision - xscale;
                BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal numerator = rootNoRounding(numeratorUnrooted, root);
                BigDecimal denominator = rootNoRounding(denominatorUnrooted, root);
                BigDecimal result = divideNoRounding(numerator, denominator);
                return result;
            } else {
                BigDecimal r = rootLessThanOneNoRounding(x, root);
                return r;
            }
        }
        BigInteger root_BigInteger = BigInteger.valueOf(root);
        BigDecimal r;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        r = rootInitialisationNoRounding(x, root_BigInteger, rootInitialisationMaxite);
        //int newtonRaphsonMaxite = 100;
        r = newtonRaphsonNoRounding(x, r, root_BigInteger);
        return r;
    }

    /**
     * @param x {@code 0 < x < 1}
     * @param root
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    private static BigDecimal rootLessThanOne(BigDecimal x, int root, int dp,
            RoundingMode rm) {
        return rootLessThanOne(x, BigInteger.valueOf(root), dp, rm);
    }

    /**
     * Returns the root-th root of x.
     *
     * @param x The value to calculate the root of. This value must be between
     * {@code 0} and {@code 1}.
     * @param root The root to take.
     * @return The root-th root of x.
     */
    private static BigDecimal rootLessThanOneNoRounding(BigDecimal x, int root) {
        return rootLessThanOneNoRounding(x, BigInteger.valueOf(root));
    }

    /**
     * Returns the root-th root of x.
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     *
     * @param x The value to calculate the root of. This value must be between
     * {@code 0} and {@code 1}.
     * @param root The root to take.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The root-th root of x.
     */
    private static BigDecimal rootLessThanOne(BigDecimal x, BigInteger root,
            int dp, RoundingMode rm) {
        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, dp);
        //BigDecimal comparator = BigDecimal.ONE.subtract(epsilon_BigDecimal);
//        // Check there is a rootRoundIfNecessary in the precision and return 1 if not
//        boolean powerTest = powerTestBelow(
//                epsilon_BigDecimal,//comparator,
//                x,
//                rootRoundIfNecessary,
//                256,
//                decimalPlaces,
//                a_RoundingMode);
//        if (powerTest) {
//            System.out.println("No rootRoundIfNecessary in the precision... ");
//            return BigDecimal.ONE;
//        }

        BigDecimal r;// = BigDecimal.ONE;
        if (x.scale() - x.precision() > dp) {
            r = BigDecimal.ONE.subtract(epsilon_BigDecimal);
        } else {
            int rootInitialisationMaxite = 10;
            //int rootInitialisationMaxite = x.scale();
            r = rootInitialisationLessThanOne(x, root, epsilon_BigDecimal,
                    rootInitialisationMaxite, dp, rm);
        }
        //result = new BigDecimal(x.add(epsilon_BigDecimal).toString());
        int newtonRaphsonMaxite = dp + x.scale();
        return newtonRaphsonLessThanOne(x, r, root, epsilon_BigDecimal,
                newtonRaphsonMaxite, dp, rm);

// For rootRoundIfNecessary is large this takes too long 
//        BigDecimal tenPowerRoot = power(
//                BigDecimal.TEN,
//                rootRoundIfNecessary,
//                256,
//                0,
//                RoundingMode.UNNECESSARY);
//        BigDecimal xunscaled = multiply(
//                x,
//                tenPowerRoot,
//                0,
//                RoundingMode.UNNECESSARY);
//        BigDecimal result0 = rootRoundIfNecessary(
//                xunscaled,
//                rootRoundIfNecessary,
//                decimalPlaces + 1,
//                a_RoundingMode);
//        BigDecimal result = divide(
//                result0,
//                BigDecimal.TEN,
//                decimalPlaces + 1,
//                a_RoundingMode);
//        return result;
    }

    private static BigDecimal rootLessThanOneNoRounding(BigDecimal x,
            BigInteger root) {
        BigDecimal r;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        r = rootInitialisationLessThanOneNoRounding(x, root, rootInitialisationMaxite);
        return newtonRaphsonLessThanOneNoRounding(x, r, root);
    }

    /**
     * Returns the root-th root of x.
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     *
     * @param x The value to calculate the root of. This value must be between
     * {@code 0} and {@code 1}.
     * @param root The root to take.
     * @param estimate An estimate of the result.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The root-th root of x.
     */
    private static BigDecimal rootLessThanOne(BigDecimal x, BigInteger root,
            BigDecimal estimate, int dp, RoundingMode rm) {
        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, dp);
        int newtonRaphsonMaxite = dp + x.scale();
        return newtonRaphsonLessThanOne(x, estimate, root,
                epsilon_BigDecimal, newtonRaphsonMaxite, dp, rm);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     * https://en.wikipedia.org/wiki/Shifting_nth_root_algorithm
     * <ul>
     * <li>Make an initial guess x0</li>
     * <li>Do some calculations</li>
     * <li>Iterate...</li>
     * </ul>
     *
     * @param x The value to root.
     * @param result0 Estimate of result.
     * @param root The root to calculate.
     * @param epsilon Amount of difference deemed significant.
     * @param maxite The maximum number of iterations to iterate.
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonLessThanOne(BigDecimal x,
            BigDecimal result0, BigInteger root, BigDecimal epsilon,
            int maxite, int dp, RoundingMode rm) {
        BigDecimal r = new BigDecimal(result0.toString());
        int precision = dp + 5;
        int divprecision = dp + 10;
        //MathContext a_MathContext = new MathContext(precision);
        BigDecimal rootsubtract1 = new BigDecimal(root.subtract(BigInteger.ONE));
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal resultipowrootsubtract1;
        BigDecimal adivresultipowrootsubtract1;
        BigDecimal inside;
        MathContext add_MathContext;
        int generalAddPrecision = dp + root.toString().length() + 2;
//        MathContext add_MathContext = new MathContext(
//                decimalPlaces + rootRoundIfNecessary.toString().length() + 1,
//                a_RoundingMode);

        boolean maxiteReached = true;

        // Newton Raphson
        for (int i = 0; i < maxite; i++) {
            result0 = new BigDecimal(r.toString());
            p = r.multiply(rootsubtract1);
            resultipowrootsubtract1 = Math_BigDecimal.power(r, rootsubtract1,
                    root.intValue() + precision, rm);
            adivresultipowrootsubtract1 = Math_BigDecimal.divideRoundIfNecessary(
                    x, resultipowrootsubtract1, precision, rm);
            add_MathContext = new MathContext(
                    p.toBigInteger().toString().length() + generalAddPrecision,
                    rm);
            inside = p.add(adivresultipowrootsubtract1, add_MathContext);
            r = Math_BigDecimal.divideRoundIfNecessary(inside, root,
                    precision, rm);
//            result = Math_BigDecimal.divide(
//                    inside,
//                    rootRoundIfNecessary, 
//                    divprecision, 
//                    a_RoundingMode);
            // Test for convergence
            if (result0.subtract(r).abs().compareTo(epsilon) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                maxiteReached = false;
                break;

            }
        }
        if (maxiteReached) {
            System.out.println("maxite reached without finding root in "
                    + Math_BigDecimal.class.getName() + ".newtonRaphson(...)"
                    + " previousResult_BigDecimal0.subtract(result).abs() "
                    + result0.subtract(r).abs());
        }
        return roundIfNecessary(r, dp, rm);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm # Make an initial guess
     * x0 # Set x_{k+1} = \frac{1}{n} \left[{(n-1)x_k
     * +\frac{A}{x_k^{n-1}}}\right] Uses MathContext division
     *
     * @param x The value to root.
     * @param result0 Estimate of result.
     * @param root The root to calculate.
     * @param epsilon Amount of difference deemed significant.
     * @param maxite The maximum number of iterations to iterate.
     * @return Re-approximation of result0 using Newton Raphson method.
     */
    private static BigDecimal newtonRaphsonLessThanOneNoRounding(
            BigDecimal x, BigDecimal result0, BigInteger root) {
        BigDecimal r = new BigDecimal(result0.toString());
        BigDecimal rootsubtract1 = new BigDecimal(root.subtract(BigInteger.ONE));
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal resultipowrootsubtract1;
        BigDecimal adivresultipowrootsubtract1;
        BigDecimal inside;
        boolean maxiteReached = true;
        // Newton Raphson
        while (true) {
            result0 = new BigDecimal(r.toString());
            p = r.multiply(rootsubtract1);
            resultipowrootsubtract1 = Math_BigDecimal.powerNoRounding(
                    r, rootsubtract1);
            adivresultipowrootsubtract1 = divideNoRounding(
                    x, resultipowrootsubtract1);
            inside = p.add(adivresultipowrootsubtract1);
            r = divideNoRounding(inside, root);
            // Test for convergence
            if (result0.compareTo(r) == 1) {
                break;
            }
        }
        return r;
    }

//    /**
//     * @return true if there is a rootRoundIfNecessary in the precision and false otherwise
//     */
//    private boolean isRootInPrecision(
//            BigDecimal x,
//            int rootRoundIfNecessary,
//            BigDecimal epsilon_BigDecimal,
//            int precision,
//            RoundingMode a_RoundingMode){
//        boolean toCompare = true;
//            if (x.compareTo(BigDecimal.ONE) == 1) {
//                BigDecimal check = power(
//                        epsilon_BigDecimal,
//                        rootRoundIfNecessary,
//                        rootRoundIfNecessary,
//                        4,
//                        precision,
//                        a_RoundingMode);
////                BigDecimal check = power( // Too slow...
////                        epsilon_BigDecimal,
////                        rootRoundIfNecessary,
////                        precision,
////                        a_RoundingMode);
//                if (check.compareTo(x) == 1) {
//                    System.out.println("No rootRoundIfNecessary in the precision...");
//                    return BigDecimal.ZERO;
//                }
//            }
//    }
//    private static BigDecimal rootInitialisation(
//            BigDecimal x,
//            int rootRoundIfNecessary,
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
//                    rootRoundIfNecessary,
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
//                            rootRoundIfNecessary,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
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
//                                rootRoundIfNecessary,
//                                64,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRootTest) {
////                        cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
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
//                        rootRoundIfNecessary,
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
////                        rootRoundIfNecessary,
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
//                            rootRoundIfNecessary,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
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
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return Tthe root-th root of x.
     */
    private static BigDecimal rootInitialisationLessThanOne(BigDecimal x,
            BigInteger root, BigDecimal epsilon, int maxite,
            int dp, RoundingMode rm) {
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal r = BigDecimal.ONE.subtract(epsilon);
//        BigDecimal result = BigDecimal.ONE.subtract(Math_BigDecimal.reciprocal(new BigDecimal(rootRoundIfNecessary), decimalPlaces, a_RoundingMode));
//        return result;
        boolean powerTestBelow;
//        boolean powerTestBelow = powerTestBelow(
//                x,
//                result,
//                rootRoundIfNecessary,
//                64,
//                decimalPlaces,
//                a_RoundingMode);
//        if (powerTestBelow) {
//            // No rootRoundIfNecessary in precision
//            int debug = 1;
//            return b;
//        }
        BigDecimal a = new BigDecimal(x.toString());
        b = r;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger rootdiv = new BigInteger(root.toString(maxite));
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsa = b.subtract(a);
            c = divideRoundIfNecessary(bsa, root, dp + 1, rm);
            c = b.subtract(c);
            powerTestBelow = powerTestBelow(x, c, root, 64, dp, rm);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                rootdiv = rootdiv.divide(BigInteger.TWO);
                r = b;
            }
            // Bisect;
            c = divideRoundIfNecessary(b.subtract(a), TWO, dp + 1, rm);
            c = b.subtract(c);
            powerTestBelow = powerTestBelow(x, c, root, 64, dp, rm);
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
     * Produces an intiale Initialises
     *
     * @param x 0 < x < 1
     * @param root_BigDecimal
     * @param maxite
     * @return
     */
    private static BigDecimal rootInitialisationLessThanOneNoRounding(
            BigDecimal x, BigInteger root, int maxite) {
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal r = BigDecimal.ONE;
        boolean powerTestBelow;
        BigDecimal a = new BigDecimal(x.toString());
        b = r;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger rootdiv = new BigInteger(root.toString(maxite));
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsubtracta = b.subtract(a);
            c = divideNoRounding(bsubtracta, root);
            c = b.subtract(c);
            powerTestBelow = powerTestBelowNoRounding(x, c, root, 64);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                rootdiv = rootdiv.divide(BigInteger.TWO);
                r = b;
            }
            // Bisect;
            c = divideNoRounding(b.subtract(a), TWO);
            c = b.subtract(c);
            powerTestBelow = powerTestBelowNoRounding(x, c, root, 64);
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
     * Returns the square root of x as a BigDecimal. In some cases the square
     * root of a number is irrational and cannot be precisely stored as a
     * decimal number. This method therefore rounds the result as necessary
     * using the RoundingMode to try to ensure the result is correct to
     * {@code dp} decimal places.
     *
     * @param rm RoundingMode
     *
     * @param x The value for which the square root is returned.
     * @param dp The number of decimal places the result has to be correct to.
     * @return Square root of {@code x} rounded if necessary.
     */
    public static BigDecimal sqrt(BigDecimal x, int dp, RoundingMode rm) {
// @TODO This used to be a hack, check tests are working for the new implementation.
//        if (x.compareTo(BigDecimal.ZERO) == 1 && x.compareTo(BigDecimal.ONE) == -1) {
//            return BigDecimal.valueOf(Math.sqrt(x.doubleValue()));
//        }
//BigDecimal.sqrt()
// @TODO Compare with BigDecimal.sqrt(MathContext)
//int precision = 100;
//x.sqrt(new MathContext(precision, rm));
        return power(x, new BigDecimal("0.5"), dp, rm);
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param r The {@link Random} to use.
     * @param p The probability. Expected to be in the range [0,1].
     * @param rm The {@link RoundingMode} to use.
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(Random r, BigDecimal p, RoundingMode rm) {
        return randomUniformTest(r, p, p.scale(), rm);
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param rand The {@link Random} to use.
     * @param p The probability. Expected to be in the range [0,1].
     * @param dp The number of decimal places the result has to be correct to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(Random rand, BigDecimal p,
            int dp, RoundingMode rm) {
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
        if (dp < probabilityScale) {
//            System.out.println(
//                    "Input decimalPlaces < probabilty.scale() in "
//                    + Math_BigDecimal.class
//                    + ".randomUniformTest(Random,BigDecimal). "
//                    + "Set decimalPlaces = probabilty.scale().");
            dp = probabilityScale;
        }
        BigDecimal midTestValue = new BigDecimal("0.5");
        BigDecimal half_BigDecimal = new BigDecimal("0.5");
        if (p.compareTo(midTestValue) == 0) {
            return rand.nextBoolean();
        }
        if (p.compareTo(midTestValue) == -1) {
            return randomTest(rand, p, BigDecimal.ZERO,
                    BigDecimal.ONE, midTestValue, half_BigDecimal,
                    TWO, dp, rm);
        } else {
            return !randomTest(rand, BigDecimal.ONE.subtract(p),
                    BigDecimal.ZERO, BigDecimal.ONE, midTestValue,
                    half_BigDecimal, TWO, dp, rm);
        }
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param rand The {@link Random} to use.
     * @param p A probability expected to be a value in the range [0,1].
     * @param mc The {@link MathContext}.
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(Random rand, BigDecimal p,
            MathContext mc) {
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
        BigDecimal midTestValue = new BigDecimal("0.5");
        BigDecimal half_BigDecimal = new BigDecimal("0.5");
        BigDecimal two_BigDecimal = new BigDecimal("2.0");
        if (p.compareTo(midTestValue) == 0) {
            return rand.nextBoolean();
        }
        if (p.compareTo(midTestValue) == -1) {
            return randomTest(rand, p, BigDecimal.ZERO,
                    BigDecimal.ONE, midTestValue, half_BigDecimal,
                    two_BigDecimal, mc);
        } else {
            return !randomTest(rand, BigDecimal.ONE.subtract(p),
                    BigDecimal.ZERO, BigDecimal.ONE, midTestValue,
                    half_BigDecimal, two_BigDecimal, mc);
        }
    }

    private static boolean randomTest(
            Random a_Random,
            BigDecimal probability,
            BigDecimal minTestValue,
            BigDecimal maxTestValue,
            BigDecimal midTestValue,
            BigDecimal half_BigDecimal,
            BigDecimal two_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        boolean above = a_Random.nextBoolean();
        if (above) {
            if (probability.compareTo(midTestValue) == 1) {
                // Test
                BigDecimal newMinTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMinTestValue.add(maxTestValue),
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
                return randomTest(
                        a_Random,
                        probability,
                        newMinTestValue,
                        maxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
            } else {
                return false;
            }
        } else {
            if (probability.compareTo(midTestValue) == 1) {
                return true;
            } else {
                //Test
                BigDecimal newMaxTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMaxTestValue.add(minTestValue),
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
                return randomTest(
                        a_Random,
                        probability,
                        minTestValue,
                        newMaxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
            }
        }
    }

    private static boolean randomTest(
            Random a_Random,
            BigDecimal probability,
            BigDecimal minTestValue,
            BigDecimal maxTestValue,
            BigDecimal midTestValue,
            BigDecimal half_BigDecimal,
            BigDecimal two_BigDecimal,
            MathContext a_MathContext) {
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        boolean above = a_Random.nextBoolean();
        if (above) {
            if (probability.compareTo(midTestValue) == 1) {
                // Test
                BigDecimal newMinTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMinTestValue.add(maxTestValue),
                        two_BigDecimal,
                        a_MathContext);
                return randomTest(
                        a_Random,
                        probability,
                        newMinTestValue,
                        maxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        a_MathContext);
            } else {
                return false;
            }
        } else {
            if (probability.compareTo(midTestValue) == 1) {
                return true;
            } else {
                //Test
                BigDecimal newMaxTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMaxTestValue.add(minTestValue),
                        two_BigDecimal,
                        a_MathContext);
                return randomTest(
                        a_Random,
                        probability,
                        minTestValue,
                        newMaxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        a_MathContext);
            }
        }
    }

    /**
     * Returns a pseudorandom number in the range [l, u].
     *
     * @param bi this contains the random and the powers of two and is passed in
     * for efficiency.
     * @param dp The number of decimal places.
     * @param l The smallest value to return.
     * @param u The largest value to return.
     * @return A pseudorandom number in the range [l, u]
     */
    public static BigDecimal getRandom(Math_BigInteger bi, int dp, BigDecimal l,
            BigDecimal u) {
        //BigDecimal resolution = new BigDecimal(BigInteger.ONE,decimalPlaces);
        BigDecimal range = u.subtract(l);
        BigInteger rescaledRange = range.scaleByPowerOfTen(dp).toBigInteger();
        BigInteger rbi = bi.getRandom(rescaledRange);
        BigDecimal rbd = new BigDecimal(rbi, dp);
        BigDecimal result = rbd.add(l);
        return result;
    }

    /**
     * Provided for convenience.
     *
     * @param gn A Math_Number
     * @param dp The number of decimal places the result has to be correct to.
     * @return a random BigDecimal between 0 and 1 inclusive which can have up
     * to dp decimal places.
     */
    public static BigDecimal getRandom(Math_Number gn, int dp) {
        Random[] random = gn.getRandoms(dp);
        String value = "0.";
        int digit;
        int ten_int = 10;
        for (int i = 0; i < dp; i++) {
            digit = random[i].nextInt(ten_int);
            value += digit;
        }
        int length = value.length();
        // Tidy values ending with zero's
        while (value.endsWith("0")) {
            length--;
            value = value.substring(0, length);
        }
        if (value.endsWith(".")) {
            value = "0";
        }
        BigDecimal result = new BigDecimal(value);
        //result.stripTrailingZeros();
        return result;
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
     * @param bd An instance of {@link Math_BigDecimal}
     * @param dp The number of decimal places that the result should be correct
     * to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The cosine of x.
     */
    public static BigDecimal cos(BigDecimal x, Math_BigDecimal bd, int dp,
            RoundingMode rm) {
//        //BigDecimal aPIBy2 = bd.getPiBy2(dp + 2, rm);
//        //BigDecimal r = sin(x.add(aPIBy2), bd, dp + 1, rm);
//        return Math_BigDecimal.roundToAndSetDecimalPlaces(r, dp, rm);
        Math_BigInteger bi = bd.bi;
        //BigDecimal aPI1000 = bd.getPi(1000, rm);
        // cosx = 1-(x^2)/(2!)+(x^4)/(4!)-(x^6)/(6!)+...
        BigDecimal precision = new BigDecimal(BigInteger.ONE, dp + 2);
        BigDecimal cosx = BigDecimal.ONE;
        int factor = 2;
        BigInteger factorial;
        BigDecimal power;
        boolean alternator = true;
        while (true) {
            factorial = bi.factorial(factor);
            power = Math_BigDecimal.power(x, factor, dp + 2, rm);
            BigDecimal division = Math_BigDecimal.divideRoundIfNecessary(
                    power, factorial, dp + 2, rm);
            if (division.compareTo(precision) != -1) {
                if (alternator) {
                    alternator = false;
                    cosx = cosx.subtract(division);
                } else {
                    alternator = true;
                    cosx = cosx.add(division);
                }
            } else {
                break;
            }
            factor += 2;
        }
        cosx = Math_BigDecimal.roundIfNecessary(cosx, dp, rm);
        return cosx;
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the sine is returned.
     * @param bd An instance of {@link Math_BigDecimal}
     * @param dp The number of decimal places that the result should be correct
     * to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The sine of x.
     */
    public static BigDecimal sin(BigDecimal x, Math_BigDecimal bd, int dp, RoundingMode rm) {
        BigDecimal aPi = bd.getPi(dp + 1, rm);
        BigDecimal aPi2 = bd.getPi2(dp + 1, rm);
        while (x.compareTo(BigDecimal.ZERO) == -1) {
            x = x.add(aPi2);
        }
        while (x.compareTo(aPi2) == 1) {
            x = x.subtract(aPi2);
        }
        // SpecialCases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(aPi) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(aPi2) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal aPIBy2 = bd.getPiBy2(dp + 2, rm);
        return sinNoCaseCheck(x, aPi, aPi2, aPIBy2, bd, dp, rm);
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the sine is returned.
     * @param aPI The value of PI to be used.
     * @param twoPI The value of 2PI to be used.
     * @param aPIBy2 The value of PI/2 to be used.
     * @param bd An instance of {@link Math_BigDecimal}
     * @param dp The number of decimal places that the result should be correct
     * to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The sine of x without checking the case of x.
     */
    protected static BigDecimal sinNoCaseCheck(BigDecimal x, BigDecimal aPI,
            BigDecimal twoPI, BigDecimal aPIBy2, Math_BigDecimal bd, int dp,
            RoundingMode rm) {
        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
            return sinAngleBetweenZeroAndPI(x, aPI, twoPI, bd, dp, rm);
        } else {
            if (x.compareTo(aPI) == -1) {
                return sinAngleBetweenZeroAndPI(aPI.subtract(x), aPI, twoPI, bd, dp, rm);
            }
            BigDecimal uncorrectedResult = sinNoCaseCheck(twoPI.subtract(x),
                    aPI, twoPI, aPIBy2, bd, dp, rm);
            return uncorrectedResult.negate();
        }
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the sine is returned.
     * @param aPI The value of PI to be used.
     * @param twoPI The value of 2PI to be used.
     * @param bd An instance of {@link Math_BigDecimal}
     * @param dp The number of decimal places that the result should be correct
     * to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The sine of x in the range [0, PI].
     */
    protected static BigDecimal sinAngleBetweenZeroAndPI(BigDecimal x,
            BigDecimal aPI, BigDecimal twoPI, Math_BigDecimal bd,
            int dp, RoundingMode rm) {
        return sinAngleBetweenZeroAndPI(x, aPI, twoPI, bd.bi, dp, rm);
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param x The value for which the sine is returned.
     * @param aPI The value of PI to be used.
     * @param twoPI The value of 2PI to be used.
     * @param bi An instance of {@link Math_BigInteger}
     * @param dp The number of decimal places that the result should be correct
     * to.
     * @param rm The {@link RoundingMode} used to round intermediate results and
     * the final result.
     * @return The sine of x in the range [0, PI].
     */
    protected static BigDecimal sinAngleBetweenZeroAndPI(BigDecimal x,
            BigDecimal aPI, BigDecimal twoPI, Math_BigInteger bi,
            int dp, RoundingMode rm) {
        BigDecimal aPIBy2 = Math_BigDecimal.divideRoundIfNecessary(
                aPI, BigInteger.valueOf(2), dp + 2, rm);
        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
            BigDecimal precision = new BigDecimal(BigInteger.ONE, dp + 4);
            BigDecimal sinx = new BigDecimal(x.toString());
            int factor = 3;
            BigInteger factorial;
            BigDecimal power;
            boolean alternator = true;
            while (true) {
                factorial = bi.factorial(factor);
                power = Math_BigDecimal.power(x, factor, dp + 2, rm);
                BigDecimal division = Math_BigDecimal.divideRoundIfNecessary(
                        power, factorial, dp + 2, rm);
                if (division.compareTo(precision) != -1) {
                    if (alternator) {
                        alternator = false;
                        sinx = sinx.subtract(division);
                    } else {
                        alternator = true;
                        sinx = sinx.add(division);
                    }
                } else {
                    break;
                }
                factor += 2;
            }
            sinx = Math_BigDecimal.roundIfNecessary(sinx, dp, rm);
            return sinx;
        }
        return null;
    }

    /**
     * Calculate and return the tangent of x (tan(x)).
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     * Untested: I am unsure if holding sinx to an additional 10 decimal places
     * and holding cosx to an additional 10 decimal places is sufficient.
     *
     * @param bd An instance of Math_BigDecimal.
     * @param x The value to calculate the tangent of.
     * @param dp Decimal Place Precision
     * @param rm The {@link RoundingMode} used to round intermediate dp and the
     * final result.
     * @return tan(x)
     */
    public static BigDecimal tan(BigDecimal x, Math_BigDecimal bd, int dp,
            RoundingMode rm) {
        BigDecimal sinx = sin(x, bd, dp + 10, rm);
        RoundingMode rmd = RoundingMode.DOWN;
        sinx = roundIfNecessary(sinx, dp + 8, rmd);
        BigDecimal cosx = cos(x, bd, dp + 10, rm);
        cosx = roundIfNecessary(cosx, dp + 8, rmd);
        if (cosx.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return divideRoundIfNecessary(sinx, cosx, dp, rm);
    }

    /**
     * Calculates the atan of
     * {@code x}.https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
     *
     * @param x the value
     * @param scale scale
     * @param rm RoundingMode
     * @return atan(x)
     */
    public static BigDecimal atan(BigDecimal x, int scale, RoundingMode rm) {
        int scale2 = scale + 8; // Is 8 sufficient?
        BigDecimal xdivsqrt1px2 = divideRoundIfNecessary(x,
                sqrt(BigDecimal.ONE.add(x.multiply(x)), scale2, rm),
                scale, rm);
        return asin(xdivsqrt1px2, scale, rm);
    }

    /**
     * Calculates the acos of {@code x}.
     * <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">https://en.wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     *
     * @param x the value
     * @param pi A value of Pi
     * @param scale scale
     * @param rm RoundingMode
     * @return acos(x)
     */
    public static BigDecimal acos(BigDecimal x, BigDecimal pi, int scale, RoundingMode rm) {
        BigDecimal r = divideRoundIfNecessary(pi, TWO, scale + 1, rm)
                .subtract(asin(x, scale + 1, rm));
        return roundIfNecessary(r, scale, rm);
    }

    /**
     * Calculates the asin of {@code BigDecimal x}.
     * <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">https://en.wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     *
     * @param x the value
     * @param scale scale
     * @param rm RoundingMode
     * @return asin(x)
     */
    public static BigDecimal asin(BigDecimal x, int scale, RoundingMode rm) {
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
