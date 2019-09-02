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
import java.util.Iterator;
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
    public transient TreeMap<Integer, BigInteger> factorials;
    public transient TreeMap<Integer, BigInteger> powersOfTwo;

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
        factorials = new TreeMap<>();
        factorials.put(0, BigInteger.ONE);
        factorials.put(1, BigInteger.ONE);
    }

    /**
     * Initialises {@link #powersOfTwo}.
     */
    protected void initPowersOfTwo() {
        powersOfTwo = new TreeMap<>();
        powersOfTwo.put(0, BigInteger.ONE);
        powersOfTwo.put(1, TWO);
        powersOfTwo.put(2, TWO.multiply(TWO));
    }

    /**
     * Calculates and returns the next integer 
     * @param x 
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer further away from zero;
     */
    public static BigInteger ceiling(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigInteger ceiling = ceiling(x.negate());
            return ceiling.negate();
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
     * @param x
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer closer to zero;
     */
    public static BigInteger floor(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigInteger floor = floor(x.negate());
            return floor.negate();
        }
        return x.toBigInteger();
    }

    /**
     * Adds values to factorials if they do not already exist.
     *
     * @param x
     * @return x! as a BigInteger
     */
    public BigInteger factorial(Integer x) {
        if (factorials == null) {
            initFactorials();
        }
        if (factorials.containsKey(x)) {
            return factorials.get(x);
        }
        Integer lastKey = factorials.lastKey();
        BigInteger lastFactorial = factorials.get(lastKey);
        BigInteger factorial = null;
        BigInteger value;
        Integer y;
        int key = lastKey + 1;
        for (int keys = key; keys <= x; keys++) {
            y = keys;
            value = new BigInteger("" + keys);
            factorial = lastFactorial.multiply(value);
            factorials.put(y, factorial);
            lastFactorial = factorial;
        }
        return factorial;
    }

    /**
     * Adds values to powersOfTwo if they do not already exist.
     *
     * @param x
     * @return 2^^x as a BigInteger
     */
    public BigInteger powerOfTwo(Integer x) {
        if (powersOfTwo == null) {
            initPowersOfTwo();
        }
        if (powersOfTwo.containsKey(x)) {
            return powersOfTwo.get(x);
        }
        Integer lastKey = powersOfTwo.lastKey();
        BigInteger lastPowerOfTwo = factorials.get(lastKey);
        BigInteger powerOfTwo = null;
        int key = lastKey + 1;
        for (int keys = key; keys <= x; keys++) {
            powerOfTwo = TWO.multiply(lastPowerOfTwo);
            powersOfTwo.put(key, powerOfTwo);
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
        Integer lastKey = powersOfTwo.lastKey();
        BigInteger lastPowerOfTwo = powersOfTwo.get(lastKey);
        BigInteger powerOfTwo = TWO.multiply(lastPowerOfTwo);
        int key = lastKey + 1;
        powersOfTwo.put(key, powerOfTwo);
        return powerOfTwo;
    }

    /**
     * If {@link #powersOfTwo} is null then it is initialised.
     * @return {@link #powersOfTwo}.
     */
    public TreeMap<Integer, BigInteger> getPowersOfTwo() {
        if (this.powersOfTwo == null) {
            initPowersOfTwo();
        }
        return this.powersOfTwo;
    }

    /**
     * @param x
     * @return All the powers of two less than or equal to x
     */
    public TreeMap<Integer, BigInteger> getPowersOfTwo(BigInteger x) {
        // Special Cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return null;
        }
//        if (x.compareTo(BigInteger.ONE) == 0) {
//            return null;
//        }
//        if (x.compareTo(TWO) == 0) {
//            return null;
//        }
        while (getPowersOfTwo().lastEntry().getValue().compareTo(x) != 1) {
            addPowerOfTwo();
        }
        TreeMap<Integer, BigInteger> result = new TreeMap<>();
        for (Entry<Integer, BigInteger> entry : powersOfTwo.entrySet()) {
            Integer value = entry.getKey();
            BigInteger powerOfTwo = entry.getValue();
            if (powerOfTwo.compareTo(x) == 1) {
                break;
            }
            result.put(value, powerOfTwo);
        }
        return result;
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
        TreeMap<Integer, BigInteger> xPowersOfTwo = Math_BigInteger.this.getPowersOfTwo(x);
        Integer n;
        BigInteger powerofTwo;
        BigInteger remainder = new BigInteger(x.toString());
        Iterator<Integer> ite = xPowersOfTwo.descendingKeySet().iterator();
        while (ite.hasNext()) {
            if (remainder.compareTo(BigInteger.ZERO) == 1) {
                n = ite.next();
                powerofTwo = xPowersOfTwo.get(n);
                if (powerofTwo.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(powerofTwo);
                    Generic_Collections.addToTreeMapValueInteger(r, n, 1);
                }
            } else {
                break;
            }
        }
        //System.out.println("remainder " + remainder);
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
     * @param powerOf2 this must be 2 to the power of n for some n.
     * @return returns a random number in the range 0 to powerOf2
     */
    private BigInteger getRandomFromPowerOf2(BigInteger powerOf2) {
        Random[] random = getRandoms(1);
        BigInteger theRandom = BigInteger.ZERO;
        TreeMap<Integer, BigInteger> powersOfTwo = Math_BigInteger.this.getPowersOfTwo(powerOf2);
        BigInteger powerOfTwo;
        for (int i = powersOfTwo.lastKey() - 1; i >= 0; i--) {
            if (random[0].nextBoolean()) {
                powerOfTwo = powerOfTwo(i);
                theRandom = theRandom.add(powerOfTwo);
            }
        }
        if (random[0].nextBoolean()) {
            theRandom = theRandom.add(BigInteger.ONE);
        }
        return theRandom;
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
//     * @param a_Random
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
     *
     * @param n
     * @return
     */
    public BigInteger getFactorial(int n) {
        Integer nInteger = Integer.valueOf(n);
        if (factorials.containsKey(n)) {
            return factorials.get(nInteger);
        }
        Entry<Integer, BigInteger> lastEntry = factorials.lastEntry();
        int n0 = lastEntry.getKey();
        n0++;
        BigInteger nFactorial = lastEntry.getValue();
        for (int i = n0; i <= n; i++) {
            BigInteger iBigInteger = BigInteger.valueOf(i);
            nFactorial = nFactorial.multiply(iBigInteger);
            factorials.put(i, nFactorial);
        }
        return nFactorial;
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
