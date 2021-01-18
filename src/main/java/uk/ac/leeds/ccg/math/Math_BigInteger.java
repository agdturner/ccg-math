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
package uk.ac.leeds.ccg.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.generic.util.Generic_Collections;
//import static uk.ac.leeds.ccg.math.Math_BigDecimal.exp;

/**
 * A class for {@code BigInteger} numbers.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_BigInteger extends Math_Number {

    private static final long serialVersionUID = 1L;

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
    protected transient List<BigInteger> powersOfTwo;

    /**
     * Creates a new instance.
     */
    public Math_BigInteger() {
        //super();
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
        factorials.add(BigInteger.ONE); // 0! = 1
        factorials.add(BigInteger.ONE); // 1! = 1
        factorials.add(BigInteger.TWO); // 2! = 2
    }

    /**
     * Initialises {@link #powersOfTwo}.
     */
    protected void initPowersOfTwo() {
        powersOfTwo = new ArrayList<>();
        powersOfTwo.add(BigInteger.ONE);
        powersOfTwo.add(BigInteger.TWO);
        powersOfTwo.add(BigInteger.TWO.multiply(BigInteger.TWO));
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> of the most significant digit of {@code x}.
     *
     * @param x The value for which the order of magnitude of the most
     * significant digit is returned.
     * @return The order of magnitude of the most significant digit of
     * {@code x}.
     */
    public static int getMagnitudeOfMostSignificantDigit(BigInteger x) {
        //return x.abs().toString().length();
        int xs = x.signum();
        switch (xs) {
            case -1:
                return log10(x.negate()) + 1;
            case 0:
                return 1;
            default:
                return log10(x) + 1;
        }
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> of the least significant digit of {@code x}.
     *
     * @param x The value for which the order of magnitude of the most
     * significant digit is returned.
     * @return The order of magnitude of the most significant digit of
     * {@code x}.
     */
    public static int getMagnitudeOfLeastSignificantDigit(BigInteger x) {
        return 1;
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> of the smallest non-zero digit of {@code x}.
     *
     * @param x The value for which the order of magnitude of the most
     * significant digit is returned.
     * @return The order of magnitude of the smallest non zero digit of
     * {@code x}.
     */
    public static int getMagnitudeOfSmallestNonZeroDigit(BigInteger x) {
        return getMagnitudeOfSmallestNonZeroDigit(x,
                getMagnitudeOfMostSignificantDigit(x));
    }

    /**
     * Returns the
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a> (OOM) of the smallest non-zero digit of {@code x}. This is
     * computationally more efficient that
     * {@link #getMagnitudeOfSmallestNonZeroDigit(java.math.BigInteger)}. No
     * checking is done to ensure that {@code m} is correct. That is the
     * responsibility of the user.
     *
     * @param x The value for which the order of magnitude of the most
     * significant digit is returned.
     * @param m The OOM of the most significant digit of {@code x}. value for
     * which the order of magnitude of the most
     * @return The order of magnitude of the smallest non zero digit of
     * {@code x}.
     */
    public static int getMagnitudeOfSmallestNonZeroDigit(BigInteger x, int m) {
        return m - new BigDecimal(x).divide(BigDecimal.TEN.pow(m - 1)).scale();
    }

    /**
     * Calculate and return {@code x} multiplied by {@code y} to the precision
     * scale given by {@code ps} using the RoundingMode {@code rm}.
     *
     * @param x A number to multiply.
     * @param y A number to multiply.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * to round to.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li=>
     * <li>{@code oom=2} rounds to the nearest {@code 100}</li>
     * <li>{@code oom=3} rounds to the nearest {@code 1000}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to round the final result if
     * rounding is necessary.
     * @return x multiplied by y to the precision scale given by {@code ps} and
     * {@code rm}.
     */
    public static BigInteger multiply(BigInteger x, BigInteger y, int oom,
            RoundingMode rm) {
        return round(x.multiply(y), oom, rm);
    }

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
     * to round to.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li=>
     * <li>{@code oom=2} rounds to the nearest {@code 100}</li>
     * <li>{@code oom=3} rounds to the nearest {@code 1000}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used to round the final result if
     * rounding is necessary.
     * @return x multiplied by y to the precision scale given by {@code ps} and
     * {@code rm}.
     */
    public static BigInteger multiplyPriorRound(BigInteger x, BigInteger y, int oom,
            RoundingMode rm) {
        int xm = Math_BigInteger.getMagnitudeOfMostSignificantDigit(x);
        int m = (int) Math.sqrt(oom);
        BigInteger d = BigInteger.TEN.pow(m);
        BigInteger rp;
        int ym = Math_BigInteger.getMagnitudeOfMostSignificantDigit(y);
        if (xm >= ym) {
            if (xm > m) {
                BigInteger xr = x.divide(d);
                rp = y.multiply(xr).multiply(d);
                System.out.println(rp);
            } else {
                rp = y.multiply(x).multiply(d);
            }
        } else {
            if (ym > m) {
                BigInteger yr = y.divide(d);
                rp = x.multiply(yr).multiply(d);
            } else {
                rp = x.multiply(y).multiply(d);
            }
        }
        return Math_BigInteger.round(rp, oom);
    }

    /**
     * @param x The number to round
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * to round to. This should be greater than 0 otherwise the result is simply
     * x and this method need not be called.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li=>
     * <li>{@code oom=2} rounds to the nearest {@code 100}</li>
     * <li>{@code oom=3} rounds to the nearest {@code 1000}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return {@code x} rounded given {@code s} and {@code rm}
     */
    public static BigInteger round(BigInteger x, int oom, RoundingMode rm) {
        return new BigDecimal(x).movePointLeft(oom).setScale(0, rm)
                .movePointRight(oom).toBigInteger();
    }

    /**
     * This is the same as
     * {@link #round(java.math.BigInteger, int, java.math.RoundingMode)} with
     * {@code rm} set to {@link RoundingMode#HALF_UP}.
     *
     * @param x The number to round
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * to round to. This should be greater than 0 otherwise the result is simply
     * x and this method need not be called.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li=>
     * <li>{@code oom=2} rounds to the nearest {@code 100}</li>
     * <li>{@code oom=3} rounds to the nearest {@code 1000}</li>
     * <li>...</li>
     * </ul>
     * @return {@code x} rounded given {@code s} and {@code rm}
     */
    public static BigInteger round(BigInteger x, int oom) {
        return round(x, oom, RoundingMode.HALF_UP);
    }

    /**
     * Calculates and returns the next integer closer to positive infinity
     * unless x is an integer - in which case a {@link BigInteger} version of it
     * is returned. See also {@link java.lang.Math#ceil(double)}.
     *
     * @param x The number for which the ceiling is returned.
     * @return If x is an integer then return a BigInteger value of x. Otherwise
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
            return r.add(BigInteger.ONE);
        }
    }

    /**
     * Calculates and returns the next integer closer to negative infinity
     * unless x is an integer - in which case a {@link BigInteger} version of it
     * is returned. See also {@link java.lang.Math#floor(double)}.
     *
     * @param x The number for which the floor is returned.
     * @return If x is an integer then return a BigInteger value of x. Otherwise
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
     * returns x factorial (x!) = x*(x-1)*(x-2)*(x-3)*...*(x-(x-1)).
     *
     * @param x The number for which the factorial is returned.
     * @return x! as a BigInteger
     */
    public BigInteger factorial(int x) {
        if (x < 0) {
            throw new ArithmeticException("x < 0 in Math_BigInteger.factorial(x)");
        }
        if (factorials == null) {
            initFactorials();
        }
        int size = factorials.size();
        if (x <= size) {
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
     * returns 2 to the power of x (2^x).
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
            r = BigInteger.TWO.multiply(r);
            powersOfTwo.add(r);
        }
        return r;
    }

    /**
     * Adds and power of 2 to {@link #powersOfTwo} and returns it. If
     * {@link #powersOfTwo} is {@code null}, then it is initialised first.
     *
     * @return The next power of 2.
     */
    protected BigInteger addPowerOfTwo() {
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        int size = powersOfTwo.size();
        BigInteger r = BigInteger.TWO.multiply(powersOfTwo.get(size - 1));
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
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        int size = powersOfTwo.size();
        BigInteger p = powersOfTwo.get(size - 1);
        if (x.compareTo(p) != -1) {
            while (x.compareTo(p) == 1) {
                p = p.multiply(BigInteger.TWO);
                powersOfTwo.add(p);
            }
            return powersOfTwo.subList(0, powersOfTwo.size() - 1);
        } else {
            List<BigInteger> r = new ArrayList<>();
            Iterator<BigInteger> ite = powersOfTwo.iterator();
            while (ite.hasNext()) {
                BigInteger p2 = ite.next();
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
     * Calculates and returns what basically amount to a binary encoding for x
     * based on the fact that all integers can be represented in the form:
     * <ul>
     * <li>{@code x = m0*(2^y0) + m1*(2^y1) + m2*(2^y2) +... (where mi and yi are
     * integers, and the yi are decreasing from y0 being the smallest integer
     * such that 2^y0 >= x)}</li>
     * </ul>
     * The keys are the number 2 is raised to in each part (yi) and the values
     * are the multiples (mi).
     *
     *
     * @param x The value to be decomposed.
     * @return A map of the powers of 2 decomposition of x.
     */
    public TreeMap<Integer, Integer> getPowersOfTwoDecomposition(BigInteger x) {
        // Special Cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return null;
        }
        TreeMap<Integer, Integer> r = new TreeMap<>();
        if (x.compareTo(BigInteger.ONE) == 0) {
            r.put(0, 1);
            return r;
        }
        List<BigInteger> xP = getPowersOfTwo(x);
        int i = xP.size() - 1;
        BigInteger p;
        BigInteger remainder = new BigInteger(x.toString());
        for (int index = i; index >= 0; index--) {
            p = xP.get(index);
            if (remainder.compareTo(BigInteger.ZERO) == 1) {
                int c = 0;
                while (p.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(p);
                    c++;
                }
                if (c > 0) {
                    //Generic_Collections.addToMapInteger(r, index, c);
                    Generic_Collections.addToCount(r, index, c);
                }
            } else {
                break;
            }
        }
        if (remainder.compareTo(BigInteger.ZERO) == 1) {
            //Generic_Collections.addToMapInteger(r,
            //       getPowersOfTwoDecomposition(remainder));
            Generic_Collections.addToCount(r,
                    getPowersOfTwoDecomposition(remainder));
        }
        return r;
    }

    /**
     * Calculates and returns x to the power of y (x^y) to {@code dp} decimal
     * place precision using the {@link RoundingMode} {@code rm}.
     *
     * @param x The base.
     * @param y The power.
     * @param dp The number of decimal places the result is to be accurate to.
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return x^y
     */
    public static BigDecimal power(BigInteger x, int y, int dp, RoundingMode rm) {
        if (y < 0) {
            return reciprocal(x.pow(-y), dp, rm);
        } else {
            return new BigDecimal(x.pow(y));
        }
    }

    /**
     * Calculates and returns x to the power of y (x^y) to {@code dp} decimal
     * place precision using the {@link RoundingMode} {@code rm}.
     *
     * @param x The base.
     * @param y The power.
     * @param dp The number of decimal places the result is to be accurate to.
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return x^y
     */
    public static BigDecimal power(BigInteger x, long y, int dp,
            RoundingMode rm) {
        if (y <= Math_Long.INTEGER_MAX_VALUE) {
            return power(x, (int) y, dp, rm);
        }
        BigDecimal r;
        long y0 = y / Math_Long.INTEGER_MAX_VALUE;
        BigDecimal y0Power = power(x, y0, dp, rm);
        long y1 = y - y0;
        BigDecimal y1Power = power(x, y1, dp, rm);
        //return Math_BigDecimal.multiplyRoundIfNecessary(y0Power, y1Power, dp, rm);
        return y0Power.multiply(y1Power);
    }

    /**
     * Calculates and returns the reciprocal of x (1/x) to {@code oom} decimal
     * place precision using the {@link RoundingMode} {@code rm}. If
     * {@code x == 0} then an {@link IllegalArgumentException} is thrown.
     *
     * @param x The base.
     * @param oom The
     * <a href="https://en.wikipedia.org/wiki/Order_of_magnitude">Order of
     * Magnitude</a>
     * to round to.
     * <ul>
     * <li>...</li>
     * <li>{@code oom=1} rounds to the nearest {@code 10}</li=>
     * <li>{@code oom=2} rounds to the nearest {@code 100}</li>
     * <li>{@code oom=3} rounds to the nearest {@code 1000}</li>
     * <li>...</li>
     * </ul>
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return 1/x
     */
    public static BigDecimal reciprocal(BigInteger x, int oom, RoundingMode rm) {
        if (x.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException(
                    "x = 0 in " + Math_BigInteger.class
                    + ".reciprocal(BigInteger,int,RoundingMode)");
        }
        return Math_BigDecimal.divideRoundToFixedDecimalPlaces(
                BigDecimal.ONE, new BigDecimal(x), oom, rm);
    }

    /**
     * Calculates and returns the exponent of x:
     * <ul>
     * <li>e^x = 1 + x/1! + x^2/2! + x^3/3! +...</li>
     * </ul>
     *
     * @param x The exponent.
     * @param bd A Math_BigDecimal.
     * @param dp The number of decimal places the result is to be accurate to.
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return e^x where e is the Euler constant to a sufficient precision to
     * return the result accurate to the requested dp decimal place precision.
     */
    protected static BigDecimal exp(BigInteger x, Math_BigDecimal bd, int oom) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigInteger.ZERO) == -1) {
            return Math_BigDecimal.reciprocal(exp(x.negate(), bd, oom), oom);
        }
        BigDecimal r = BigDecimal.ZERO;
        if (x.compareTo(BigInteger.valueOf(999999999)) != 1
                && x.compareTo(BigInteger.ZERO) != -1) {
            int xi = x.intValueExact();
            r = bd.getE(oom - xi).pow(xi);
        } else {
            ArrayList<BigDecimal> rp = new ArrayList<>();
            BigDecimal rpp = bd.getE(oom - Math_BigInteger.log10(x));
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
     * @throws {@link ArithmeticException} if {@code x} is not greater than {@code 0}.
     */
    public static int log10(BigInteger x) {
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
     * For getting a random number between {@code 0} and {@code upperLimit}.
     *
     * @param upperLimit The largest number that can be returned.
     * @return A random integer as a BigInteger between 0 and upperLimit
     * inclusive.
     */
    public BigInteger getRandom(BigInteger upperLimit) {
        /**
         * The original implementation of this method only gave unbiased results
         * for upperLimit being a number made of only 9's such as 99999. For
         * other numbers there was an uneven distribution of decimal digits
         * (skewed towards 1), e.g. There are many more 1's in the numbers 0 to
         * 1234567 than there are any other number, the second most common is 2
         * and so on. For any range of numbers this distribution is different.
         */
        // Special cases
        if (upperLimit.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }
        if (upperLimit.compareTo(BigInteger.valueOf(Integer.MAX_VALUE - 1)) == -1) {
            int randomInt = getRandoms()[0].nextInt(upperLimit.intValue() + 1);
            return BigInteger.valueOf(randomInt);
        }
        TreeMap<Integer, Integer> upperLimit_PowersOfTwoDecomposition = getPowersOfTwoDecomposition(upperLimit);
        //Random[] random = this.getRandomsMinLength(1);
        BigInteger theRandom = BigInteger.ZERO;
        Integer key;
        BigInteger powerOfTwo;
        Integer multiples;
        for (Entry<Integer, Integer> entry : upperLimit_PowersOfTwoDecomposition.entrySet()) {
            key = entry.getKey();
            powerOfTwo = powerOfTwo(key);
            multiples = entry.getValue();
            for (int i = 0; i < multiples; i++) {
                theRandom = theRandom.add(getRandomFromPowerOf2(powerOfTwo));
//                if (random[0].nextBoolean()){
//                    theRandom = theRandom.add(getRandomFromPowerOf2(powerOfTwo));
//                }
            }
//            if (random[0].nextBoolean()){
//                theRandom = theRandom.add(BigInteger.ONE);
//            }
        }
        return theRandom;
    }

    /**
     * Calculates and returns a pseudorandom number in the range 0 to 2^n. If
     * necessary, this expands {@link #powerOfTwo(int)} until it contains
     * {@code powerOf2}. The it uses {@link #randoms} to determine whether to
     * add each power of two from {@code 1} (inclusive) up to {@code powersof2}
     * (exclusive). It then randomly adds {@code 1} or {@code 0}.
     *
     * @param powerOf2 This must be 2 to the power of n (2^n) for some n.
     * @return returns a pseudorandom number in the range 0 to 2^n.
     */
    private BigInteger getRandomFromPowerOf2(BigInteger powerOf2) {
        Random[] random = getRandoms(1);
        if (!powersOfTwo.contains(powerOf2)) {
            int size = powersOfTwo.size();
            BigInteger lastPower = powersOfTwo.get(size - 1);
            BigInteger power = lastPower.multiply(BigInteger.TWO);
            powersOfTwo.add(power);
            while (power.compareTo(powerOf2) == -1) {
                power = lastPower.multiply(BigInteger.TWO);
                powersOfTwo.add(power);
            }
        }
        int i0 = powersOfTwo.indexOf(powerOf2);
        BigInteger r = BigInteger.ZERO;
        int randomLength = random.length;
        int i;
        for (i = 0; i < i0; i++) {
            int ri = i;
            while (ri >= randomLength) {
                ri -= randomLength;
            }
            if (random[ri].nextBoolean()) {
                BigInteger powerOfTwo = powerOfTwo(i);
                r = r.add(powerOfTwo);
            }
        }
        int ri = i;
        while (ri >= randomLength) {
            ri -= randomLength;
        }
        if (random[ri].nextBoolean()) {
            r = r.add(BigInteger.ONE);
        }
        return r;
    }

    /**
     * Implementation tests the remainder when divided by 2.
     *
     * @param x The number to test as to whether it is even.
     * @return true iff x is even (ends in 0,2,4,6,8)
     */
    public static boolean isEven(BigInteger x) {
        return x.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0;
    }

    /**
     * For testing if s can be parsed as a BigInteger.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * BigInteger.
     * @return true iff s can be represented as a BigInteger.
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
     * @return:
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
        if (x.compareTo(BigInteger.ZERO) != 1) {
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
     * @param x The numerator.
     * @param y The denominator.
     * @return {@code true} iff x can be divided by y with the answer being able
     * to be stored precisely as a BigInteger.
     */
    public static boolean isDivisibleBy(BigInteger x, BigInteger y) {
        BigInteger d = x.divide(y);
        if (d.multiply(y).compareTo(x) == 0) {
            return true;
        }
        return false;
    }
}
