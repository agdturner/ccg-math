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
import static uk.ac.leeds.ccg.math.Math_BigDecimal.exp;

public class Math_BigInteger extends Math_Number {

    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger THREE = BigInteger.valueOf(3);
    public static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100);
    public static final BigInteger INTEGER_MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);
    public static final BigInteger INTEGER_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
    public static final BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    public static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    protected transient List<BigInteger> factorials;
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
        powersOfTwo.add(TWO);
        powersOfTwo.add(TWO.multiply(TWO));
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
            //BigInteger ceiling = ceiling(x.negate());
            BigInteger r = floor(x.negate()).negate();
            return r;
        }
        BigInteger xBI = x.toBigInteger();
        BigDecimal xBD = new BigDecimal(xBI);
        if (xBD.compareTo(x) == 0) {
            return xBI;
        } else {
            xBI = xBI.add(BigInteger.ONE);
        }
        return xBI;
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
        BigInteger lastFactorial = factorials.get(size - 1);
        BigInteger factorial = null;
        for (int keys = size; keys <= x; keys++) {
            BigInteger value = new BigInteger("" + keys);
            factorial = lastFactorial.multiply(value);
            factorials.add(factorial);
            lastFactorial = factorial;
        }
        return factorial;
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
        BigInteger lastPowerOfTwo = powersOfTwo.get(size - 1);
        BigInteger powerOfTwo = null;
        for (int keys = size; keys <= x; keys++) {
            powerOfTwo = TWO.multiply(lastPowerOfTwo);
            powersOfTwo.add(powerOfTwo);
            lastPowerOfTwo = powerOfTwo;
        }
        return powerOfTwo;
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
        BigInteger lastPowerOfTwo = powersOfTwo.get(size - 1);
        BigInteger powerOfTwo = TWO.multiply(lastPowerOfTwo);
        powersOfTwo.add(powerOfTwo);
        return powerOfTwo;
    }

    /**
     * If {@link #powersOfTwo} is null then it is initialised.
     *
     * @return {@link #powersOfTwo}.
     */
    protected List<BigInteger> getPowersOfTwo() {
        if (this.powersOfTwo == null) {
            initPowersOfTwo();
        }
        return this.powersOfTwo;
    }

    /**
     * If {@link #powersOfTwo} is null then it is initialised. This returns a
     * subList of powersOfTwo where the fist power of two in the subList is
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
        BigInteger lastPowerOfTwo = powersOfTwo.get(size - 1);
        if (x.compareTo(lastPowerOfTwo) != -1) {
            while (x.compareTo(lastPowerOfTwo) == 1) {
                lastPowerOfTwo = lastPowerOfTwo.multiply(TWO);
                powersOfTwo.add(lastPowerOfTwo);
            }
            return powersOfTwo.subList(0, powersOfTwo.size() - 1);
        } else {
            List<BigInteger> r = new ArrayList<>();
            Iterator<BigInteger> ite = powersOfTwo.iterator();
            while (ite.hasNext()) {
                BigInteger powerOfTwo = ite.next();
                if (x.compareTo(powerOfTwo) != 1) {
                    return r;
                } else {
                    r.add(powerOfTwo);
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
        List<BigInteger> xPowersOfTwo = getPowersOfTwo(x);
        int i = xPowersOfTwo.size() - 1;
        BigInteger powerofTwo;
        BigInteger remainder = new BigInteger(x.toString());
        for (int index = i; index >= 0; index--) {
            powerofTwo = xPowersOfTwo.get(index);
            if (remainder.compareTo(BigInteger.ZERO) == 1) {
                int c = 0;
                while (powerofTwo.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(powerofTwo);
                    c++;
                }
                if (c > 0) {
                    Generic_Collections.addToMapInteger(r, index, c);
                }
            } else {
                break;
            }
        }
        if (remainder.compareTo(BigInteger.ZERO) == 1) {
            Generic_Collections.addToMapInteger(r,
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
        r = Math_BigDecimal.multiplyRoundIfNecessary(y0Power, y1Power, dp,
                rm);
        return r;
    }

    /**
     * Calculates and returns the reciprocal of x (1/x) to {@code dp} decimal
     * place precision using the {@link RoundingMode} {@code rm}. If
     * {@code x == 0} then an {@link IllegalArgumentException} is thrown.
     *
     * @param x The base.
     * @param dp The number of decimal places the result is to be accurate to.
     * @param rm The {@link RoundingMode} used for any rounding.
     * @return 1/x
     */
    public static BigDecimal reciprocal(BigInteger x, int dp, RoundingMode rm) {
        if (x.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException(
                    "x = 0 in " + Math_BigInteger.class
                    + ".reciprocal(BigInteger,int,RoundingMode)");
        }
        BigDecimal result = BigDecimal.ONE.divide(new BigDecimal(x), dp, rm);
        return Math_BigDecimal.roundIfNecessary(result, dp, rm);
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
    protected static BigDecimal exp(BigInteger x, Math_BigDecimal bd,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigInteger.ZERO) == -1) {
            return Math_BigDecimal.reciprocal(exp(x.negate(), bd, dp, rm), dp, rm);
        }
        BigDecimal r = BigDecimal.ZERO;
        if (x.compareTo(BigInteger.valueOf(999999999)) != 1
                && x.compareTo(BigInteger.ZERO) != -1) {
            r = bd.getEulerConstantToAMinimumDecimalPlacePrecision(
                    dp + Math_BigInteger.log10(x)).pow(x.intValueExact());
        } else {
            ArrayList<BigDecimal> rp = new ArrayList<>();
            BigDecimal rpp = bd.getEulerConstantToAMinimumDecimalPlacePrecision(
                    dp + Math_BigInteger.log10(x));
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
        return Math_BigDecimal.roundToAndSetDecimalPlaces(r, dp, rm);
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
     * @param x The number to log.
     * @return The number of digits in x.
     */
    public static int log10(BigInteger x) {
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
            BigInteger power = lastPower.multiply(TWO);
            powersOfTwo.add(power);
            while (power.compareTo(powerOf2) == -1) {
                power = lastPower.multiply(TWO);
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
}
