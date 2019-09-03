/**
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
package uk.ac.leeds.ccg.andyt.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;

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
     * is returned.
     *
     * @param x The number for which the ceiling is returned.
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer closer to positive infinity.
     * @see {@link java.lang.Math#ceil(double)}
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
     * is returned.
     *
     * @param x The number for which the floor is returned.
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer closer to minus infinity.
     * @see {@link java.lang.Math#floor(double)}
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
     * Adds and returns a power of 2 to powersOfTwo
     *
     * @return
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
     * The result provides the binary encoding for x
     *
     * @param x
     * @return
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
                int count = 0;
                while (powerofTwo.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(powerofTwo);
                    count++;
                }
                if (count > 0) {
                    Generic_Collections.addToTreeMapValueInteger(r, index, count);
                }
            } else {
                break;
            }
        }
        if (remainder.compareTo(BigInteger.ZERO) == 1) {
            Generic_Collections.addToTreeMapIntegerInteger(r,
                    getPowersOfTwoDecomposition(remainder));
        }
        return r;
    }

    /**
     * @param x
     * @param y
     * @param dp decimalPlaces
     * @param rm RoundingMode
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
     * @param x
     * @param y
     * @param dp decimalPlaces
     * @param rm RoundingMode
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
     * @param x
     * @param dp decimalPlaces
     * @param rm RoundingMode
     * @return 1/x Accurate to decimalPlace number of decimal places. If x = 0
     * then an IllegalArgumentException is thrown
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
     * e^y = 1 + y/1! + y^2/2! + y^3/3! +...
     *
     * @param y
     * @param bd A Math_BigDecimal
     * @param dp decimalPlaces
     * @param rm RoundingMode
     * @return e^y where e is the Euler constant to a sufficient precision to
     * return the result accurate to the requested precision.
     */
    protected static BigDecimal exp(BigInteger y, Math_BigDecimal bd,
            int dp, RoundingMode rm) {
        // Deal with special cases
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return Math_BigDecimal.exp(y_BigDecimal, bd, dp, rm);
    }

    /**
     * There are methods to get large random numbers. This method would only
     * give un-bias results for upperLimit being a number made of only 9's such
     * as 99999. The problem with any other number is that there is an uneven
     * distribution of decimal digits skewed towards 1. There are many more 1's
     * in the numbers 0 to 1234567 than there are any other number, second most
     * common is 2 and so on. For any range of numbers this distribution is
     * different. There is a constructor method for BigDecimal that supports
     * this, but only for uniform distributions over a binary power range.
     *
     * @param upperLimit
     * @return a random integer as a BigInteger between 0 and upperLimit
     * inclusive
     */
    public BigInteger getRandom(BigInteger upperLimit) {
        return getRandom(getRandoms(1)[0], upperLimit);
    }

    public BigInteger getRandom(Random random, BigInteger upperLimit) {
        // Special cases
        if (upperLimit.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }
        if (upperLimit.compareTo(BigInteger.valueOf(Integer.MAX_VALUE - 1)) == -1) {
            int randomInt = random.nextInt(upperLimit.intValue() + 1);
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

//    /**
//     * There are methods to get large random numbers.
//     * This method would only give un-bias results for upperLimit being a number 
//     * made of only 9's such as 99999. The problem with any other number is that 
//     * there is an uneven distribution of decimal digits skewed towards 1.
//     * There are many more 1's in the numbers 0 to 1234567 than there are any 
//     * other number, second most common is 2 and so on. For any range of numbers
//     * this distribution is different.
//     * There is a constructor method for BigDecimal that supports this, but only
//     * for uniform distributions over a binary power range.
//     * @param a_Generic_Number
//     * @param upperLimit
//     * @return a random integer as a BigInteger between 0 and upperLimit
//     * inclusive
//     */
//    public static BigInteger getRandom(
//            Math_Number a_Generic_Number,
//            BigInteger upperLimit) {
//        // Special cases
//        if (upperLimit.compareTo(BigInteger.ZERO) == 0) {
//            return BigInteger.ZERO;
//        }
//        Random[] random;
//        // Special Case: upperLimit = 1
//        if (upperLimit.compareTo(BigInteger.ONE) == 0) {
//            random = a_Generic_Number.getRandomsMinLength(1);
//            if (random[0].nextBoolean()) {
//                return BigInteger.ONE;
//            } else {
//                return BigInteger.ZERO;
//            }
//        }
//        // General case 
//        BigInteger upperLimitSubtractOne = upperLimit.subtract(BigInteger.ONE);
//        String upperLimitSubtractOne_String = upperLimitSubtractOne.toString();
//        String upperLimit_String = upperLimit.toString();
//        int upperLimitSubtractOneStringLength = upperLimitSubtractOne_String.length();
//        int upperLimitStringLength = upperLimit_String.length();
//        random = a_Generic_Number.getRandomsMinLength(
//                upperLimitStringLength);
//        int startIndex = 0;
//        int endIndex = 1;
//        String result_String = "";
//        int digit;
//        int upperLimitDigit;
//        int i;
//        // Take care not to assign any digit that will result in a number larger
//        // upperLimit
//        for (i = 0; i < upperLimitStringLength; i++) {
//            upperLimitDigit = new Integer(
//                    upperLimit_String.substring(startIndex, endIndex));
//            startIndex++;
//            endIndex++;
//            digit = random[i].nextInt(upperLimitDigit + 1);
//            if (digit != upperLimitDigit) {
//                break;
//            }
//            result_String += digit;
//        }
//        // Once something smaller than upperLimit guaranteed, assign any digit
//        // between zero and nine inclusive
//        for (i = i + 1; i < upperLimitStringLength; i++) {
//            digit = random[i].nextInt(10);
//            result_String += digit;
//        }
//        // Tidy values starting with zero(s)
//        while (result_String.startsWith("0")) {
//            if (result_String.length() > 1) {
//                result_String = result_String.substring(1);
//            } else {
//                break;
//            }
//        }
//        BigInteger result = new BigInteger(result_String);
//        return result;
//    }
    /**
     * Implementation tests the remainder when divided by 2.
     *
     * @param x
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
