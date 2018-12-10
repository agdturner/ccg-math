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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;

public class Generic_BigInteger
        extends Generic_Number
        implements Serializable {

    public static final BigInteger Two = BigInteger.valueOf(2);
    public static final BigInteger Three = BigInteger.valueOf(3);
    public static final BigInteger Hundred = BigInteger.valueOf(100);
    public static final BigInteger Integer_MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);
    public static final BigInteger Integer_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
    public static final BigInteger Long_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    public static final BigInteger Long_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    public transient TreeMap<Integer, BigInteger> _Factorial_TreeMap;
    public transient TreeMap<Integer, BigInteger> _PowersOfTwo_TreeMap;

    /** Creates a new instance of Generic_BigDecimal */
    public Generic_BigInteger() {
        //super();
    }

    /** Creates a new instance of Generic_BigDecimal
     * @param _Generic_BigInteger */
    public Generic_BigInteger(Generic_BigInteger _Generic_BigInteger) {
//        this._Factorial_TreeMap = new TreeMap<Integer, BigInteger>();
//        this._Factorial_TreeMap.putAll(_Generic_BigInteger._Factorial_TreeMap);
//        this._PowersOfTwo_TreeMap = new TreeMap<Integer, BigInteger>();
//        this._PowersOfTwo_TreeMap.putAll(_Generic_BigInteger._PowersOfTwo_TreeMap);
        this._Factorial_TreeMap = _Generic_BigInteger._Factorial_TreeMap;
        this._PowersOfTwo_TreeMap = _Generic_BigInteger._PowersOfTwo_TreeMap;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Generic_BigInteger().test();
    }

    private void test() {
        // Place for initial testing code
        System.out.println("getRandom");
        BigInteger upperLimit;
        BigInteger result;
        BigInteger expResult = null;
        Generic_BigInteger a_Generic_BigInteger = new Generic_BigInteger();
        int length;
        long seed;
        // Test 1
        upperLimit = new BigInteger("64");
        result = getRandom(
                upperLimit);
        System.out.println(result);
        // Test 2
        upperLimit = new BigInteger("321432412413264");
        result = a_Generic_BigInteger.getRandom(
                upperLimit);
        System.out.println(result);
        // Test 3
        upperLimit = new BigInteger("321432416342148743936189324781973817444121"
                + "421414124144444444444444444444444444444444444444444444444444"
                + "4444444444443243242222222222432222222222234222222322413264");
        result = a_Generic_BigInteger.getRandom(
                upperLimit);
        System.out.println(result);
    }

    /**
     * Initialises _Factorial_TreeMap
     */
    protected void init_Factorial_TreeMap() {
        _Factorial_TreeMap = new TreeMap<>();
        _Factorial_TreeMap.put(0, BigInteger.ONE);
        _Factorial_TreeMap.put(1, BigInteger.ONE);
    }

    /**
     * Initialises _Factorial_TreeMap
     */
    protected void init_PowersOfTwo_TreeMap() {
        _PowersOfTwo_TreeMap = new TreeMap<>();
        _PowersOfTwo_TreeMap.put(0, BigInteger.ONE);
        _PowersOfTwo_TreeMap.put(1, Two);
        _PowersOfTwo_TreeMap.put(2, Two.multiply(Two));
    }

    /**
     * @param x
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer further away from zero;
     */
    public static BigInteger ceiling(
            BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigInteger ceiling = ceiling(x.negate());
            return ceiling.negate();
        }
        BigInteger xBigInteger = x.toBigInteger();
        BigDecimal xBigIntegerBigDecimal = new BigDecimal(xBigInteger);
        if (xBigIntegerBigDecimal.compareTo(x) == 0) {
            return xBigInteger;
        } else {
            xBigInteger = xBigInteger.add(BigInteger.ONE);
        }
        return xBigInteger;
    }

    /**
     * @param x
     * @return If x is an integer then return a BigInteger value of x. Otherwise
     * return the next integer closer to zero;
     */
    public static BigInteger floor(
            BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigInteger floor = floor(x.negate());
            return floor.negate();
        }
        return x.toBigInteger();
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to positive infinity) of either x or y
     */
    public static BigInteger max(
            BigInteger x,
            BigInteger y) {
        if (x.compareTo(y) == -1) {
            return new BigInteger(y.toString());
        } else {
            return new BigInteger(x.toString());
        }
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to negative infinity) of either x or y
     */
    public static BigInteger min(
            BigInteger x,
            BigInteger y) {
        if (x.compareTo(y) == -1) {
            return new BigInteger(x.toString());
        } else {
            return new BigInteger(y.toString());
        }
    }

    /**
     * Adds values to _Factorial_TreeMap if they do not already exist.
     * @param x
     * @return x! as a BigInteger
     */
    public BigInteger factorial(Integer x) {
        if (_Factorial_TreeMap == null) {
            init_Factorial_TreeMap();
        }
        if (_Factorial_TreeMap.containsKey(x)) {
            return _Factorial_TreeMap.get(x);
        }
        Integer lastKey = _Factorial_TreeMap.lastKey();
        BigInteger lastFactorial = _Factorial_TreeMap.get(lastKey);
        BigInteger factorial = null;
        BigInteger value;
        Integer y;
        int key = lastKey + 1;
        for (int keys = key; keys <= x; keys++) {
            y = new Integer(keys);
            value = new BigInteger("" + keys);
            factorial = lastFactorial.multiply(value);
            _Factorial_TreeMap.put(y, factorial);
            lastFactorial = factorial;
        }
        return factorial;
    }

    /**
     * Adds values to _PowersOfTwo_TreeMap if they do not already exist.
     * @param x 
     * @return 2^^x as a BigInteger
     */
    public BigInteger powerOfTwo(Integer x) {
        if (_PowersOfTwo_TreeMap == null) {
            init_PowersOfTwo_TreeMap();
        }
        if (_PowersOfTwo_TreeMap.containsKey(x)) {
            return _PowersOfTwo_TreeMap.get(x);
        }
        Integer lastKey = _PowersOfTwo_TreeMap.lastKey();
        BigInteger lastPowerOfTwo = _Factorial_TreeMap.get(lastKey);
        BigInteger powerOfTwo = null;
        BigInteger value;
        int key = lastKey + 1;
        for (int keys = key; keys <= x; keys++) {
            powerOfTwo = Two.multiply(lastPowerOfTwo);
            _PowersOfTwo_TreeMap.put(key, powerOfTwo);
            lastPowerOfTwo = powerOfTwo;
        }
        return powerOfTwo;
    }

    /**
     *  Adds and returns a power of 2 to _PowersOfTwo_TreeMap
     * @return 
     */
    protected BigInteger addPowerOfTwo() {
        if (_PowersOfTwo_TreeMap == null) {
            init_PowersOfTwo_TreeMap();
        }
        Integer lastKey = _PowersOfTwo_TreeMap.lastKey();
        BigInteger lastPowerOfTwo = _PowersOfTwo_TreeMap.get(lastKey);
        BigInteger powerOfTwo = Two.multiply(lastPowerOfTwo);
        int key = lastKey + 1;
        _PowersOfTwo_TreeMap.put(key, powerOfTwo);
        //lastPowerOfTwo = powerOfTwo;
        return powerOfTwo;
    }

    public TreeMap<Integer, BigInteger> get_PowersOfTwo_TreeMap() {
        if (this._PowersOfTwo_TreeMap == null) {
            init_PowersOfTwo_TreeMap();
        }
        return this._PowersOfTwo_TreeMap;
    }

    /**
     * @param x
     * @return all the powers of two less than or equal to x 
     */
    public TreeMap<Integer, BigInteger> getPowersOfTwo(BigInteger x) {
        // Special Cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return null;
        }
//        if (x.compareTo(BigInteger.ONE) == 0) {
//            return null;
//        }
//        if (x.compareTo(Two) == 0) {
//            return null;
//        }
        while (get_PowersOfTwo_TreeMap().lastEntry().getValue().compareTo(x) != 1) {
            addPowerOfTwo();
        }
        TreeMap<Integer, BigInteger> result = new TreeMap<>();
        for (Entry<Integer, BigInteger> entry : _PowersOfTwo_TreeMap.entrySet()) {
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
     * @param x
     * @return 
     */
    public TreeMap<Integer, Integer> getPowersOfTwoDecomposition(BigInteger x) {
        // Special Cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return null;
        }
        TreeMap<Integer, Integer> result = new TreeMap<>();
        if (x.compareTo(BigInteger.ONE) == 0) {
            result.put(0, 1);
            return result;
        }
        TreeMap<Integer, BigInteger> powersOfTwo = getPowersOfTwo(x);
        Integer n;
        BigInteger powerofTwo;
        BigInteger remainder = new BigInteger(x.toString());
        Iterator<Integer> powersOfTwoDescendingKeySet_Iterator = powersOfTwo.descendingKeySet().iterator();
        while (powersOfTwoDescendingKeySet_Iterator.hasNext()) {
            if (remainder.compareTo(BigInteger.ZERO) == 1) {
                n = powersOfTwoDescendingKeySet_Iterator.next();
                powerofTwo = powersOfTwo.get(n);
                if (powerofTwo.compareTo(remainder) != 1) {
                    remainder = remainder.subtract(powerofTwo);
                    Generic_Collections.addToTreeMapIntegerInteger(result, n, 1);
                }
            } else {
                break;
            }
        }
        //System.out.println("remainder " + remainder);
        if (remainder.compareTo(BigInteger.ZERO) == 1) {
            Generic_Collections.addToTreeMapIntegerInteger(
                    result,
                    getPowersOfTwoDecomposition(remainder));
        }
        return result;
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y
     */
    public static BigDecimal power(
            BigInteger x,
            int y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (y < 0) {
            return reciprocal(
                    x.pow(-y),
                    decimalPlaces,
                    a_RoundingMode);
        } else {
            return new BigDecimal(x.pow(y));
        }
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y
     */
    public static BigDecimal power(
            BigInteger x,
            long y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (y <= Generic_long.Integer_MAX_VALUE) {
            return power(
                    x,
                    (int) y,
                    decimalPlaces,
                    a_RoundingMode);
        }
        BigDecimal result;
        long y0 = y / Generic_long.Integer_MAX_VALUE;
        BigDecimal y0Power = power(
                x,
                y0,
                decimalPlaces,
                a_RoundingMode);
        long y1 = y - y0;
        BigDecimal y1Power = power(
                x,
                y1,
                decimalPlaces,
                a_RoundingMode);
        result = Generic_BigDecimal.multiplyRoundIfNecessary(
                y0Power,
                y1Power,
                decimalPlaces,
                a_RoundingMode);
        return result;
    }

    /**
     * @param x
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return 1/x Accurate to decimalPlace number of decimal places. If x = 0
     * then an IllegalArgumentException is thrown
     */
    public static BigDecimal reciprocal(
            BigInteger x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (x.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException(
                    "x = 0 in " + Generic_BigInteger.class
                    + ".reciprocal(BigInteger,int,RoundingMode)");
        }
        BigDecimal result = BigDecimal.ONE.divide(
                new BigDecimal(x),
                decimalPlaces,
                a_RoundingMode);
        return Generic_BigDecimal.roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * e^y = 1 + y/1! + y^2/2! + y^3/3! +...
     * @param y
     * @param a_Generic_BigDecimal
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return e^y where e is the Euler constant to a sufficient precision to
     * return the result accurate to the requested precision.
     */
    protected static BigDecimal exp(
            BigInteger y,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return Generic_BigDecimal.exp(
                y_BigDecimal,
                a_Generic_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * There are methods to get large random numbers.
     * This method would only give un-bias results for upperLimit being a number 
     * made of only 9's such as 99999. The problem with any other number is that 
     * there is an uneven distribution of decimal digits skewed towards 1.
     * There are many more 1's in the numbers 0 to 1234567 than there are any 
     * other number, second most common is 2 and so on. For any range of numbers
     * this distribution is different.
     * There is a constructor method for BigDecimal that supports this, but only
     * for uniform distributions over a binary power range.
     * @param upperLimit
     * @return a random integer as a BigInteger between 0 and upperLimit
     * inclusive
     */
    public BigInteger getRandom(
            BigInteger upperLimit) {
        return getRandom(get_RandomArrayMinLength(1)[0],upperLimit);
    }

    public BigInteger getRandom(
            Random a_Random, 
            BigInteger upperLimit) {
        // Special cases
        if (upperLimit.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ZERO;
        }
        if (upperLimit.compareTo(BigInteger.valueOf(Integer.MAX_VALUE - 1)) == -1) {
        int randomInt = a_Random.nextInt(upperLimit.intValue() + 1);
            return BigInteger.valueOf(randomInt);
        }
        TreeMap<Integer, Integer> upperLimit_PowersOfTwoDecomposition = getPowersOfTwoDecomposition(upperLimit);
        //Random[] random = this.get_RandomArrayMinLength(1);
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
    private BigInteger getRandomFromPowerOf2(
            BigInteger powerOf2) {
        Random[] random = this.get_RandomArrayMinLength(1);
        BigInteger theRandom = BigInteger.ZERO;
        TreeMap<Integer, BigInteger> powersOfTwo = getPowersOfTwo(powerOf2);
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
//            Generic_Number a_Generic_Number,
//            BigInteger upperLimit) {
//        // Special cases
//        if (upperLimit.compareTo(BigInteger.ZERO) == 0) {
//            return BigInteger.ZERO;
//        }
//        Random[] random;
//        // Special Case: upperLimit = 1
//        if (upperLimit.compareTo(BigInteger.ONE) == 0) {
//            random = a_Generic_Number.get_RandomArrayMinLength(1);
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
//        random = a_Generic_Number.get_RandomArrayMinLength(
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
        if (_Factorial_TreeMap.containsKey(n)) {
            return _Factorial_TreeMap.get(nInteger);
        }
        Entry<Integer, BigInteger> lastEntry = _Factorial_TreeMap.lastEntry();
        int n0 = lastEntry.getKey();
        n0 ++;
        BigInteger nFactorial = lastEntry.getValue();
        for (int i = n0; i <= n; i ++) {
            BigInteger iBigInteger = BigInteger.valueOf(i);
            nFactorial = nFactorial.multiply(iBigInteger);
            _Factorial_TreeMap.put(i, nFactorial);
        }
        return nFactorial;
    }
}
