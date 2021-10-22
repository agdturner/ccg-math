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
package uk.ac.leeds.ccg.math.random;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigInteger;

/**
 * For dealing with numbers.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_Random implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Random instances.
     */
    protected Random[] randoms;

    /**
     * The seed for the first element in {@link #randoms}.
     */
    long initialSeed;

    /**
     * The seed for the next element in {@link #randoms} that might be created.
     */
    long nextSeed;

    /**
     * The seed increment - the difference between all the seeds used to
     * initialise randoms.
     */
    long seedIncrement;
    
    /**
     * Initialises or reinitialise {@link #randoms}.
     */
    public Math_Random() {
        this(1, 0, 1);
    }

    /**
     * Initialises or reinitialise {@link #randoms}.
     *
     * @param length The size to initialise {@link #randoms}. This should be
     * greater than or equal to 1 and less than or equal to
     * {@link java.lang.Integer#MAX_VALUE}. Although for practical purposes, the
     * larger it is, the more memory is required, so it is best to keep it as
     * small as necessary.
     * @param initialSeed The seed for the first random.
     * @param seedIncrement The difference between the last seed and the next
     * (the last seed being the initialSeed to begin with).
     */
    public Math_Random(int length, long initialSeed, long seedIncrement) {
        //Integer max = Integer.MAX_VALUE;
        this.initialSeed = initialSeed;
        nextSeed = initialSeed;
        this.seedIncrement = seedIncrement;
        randoms = new Random[length];
        for (int i = 0; i < length; i++) {
            randoms[i] = new Random(nextSeed);
            nextSeed += seedIncrement;
        }
    }

    /**
     *
     * Initialises or reinitialise {@link #randoms}. If {{@link #randoms} is
     * {@code null}, then it is initialised. If {@link #randoms} is already
     * initialised, {@link #initialSeed} is the same as {@code initialSeed},
     * {@link #seedIncrement} is the same as {@code seedIncrement}, and
     * {@link #randoms}{@code .length} is less than or equal to {@code length},
     * then {@link #randoms} is returned. If {@link #randoms} is already
     * initialised, {@link #initialSeed} is the same as {@code initialSeed},
     * {@link #seedIncrement} is the same as {@code seedIncrement}, and
     * {@link #randoms} {@code length} is greater than {@code length}, then
     * {@link #randoms} is effectively extended with the existing {@link Random}
     * instances copied into the start of the replacement array with length
     * {@code length} and the additional instances initialised using
     * {@link #nextSeed} incremented with {@code seedIncrement} for each new
     * instance added until the array is filled. In all other case
     * {{@link #randoms} is reinitialised.
     *
     * @param length The size to initialise {@link #randoms}. This should be
     * greater than or equal to 1 and less than or equal to
     * {@link java.lang.Integer#MAX_VALUE}. Although for practical purposes, the
     * larger it is, the more memory is required, so it is best to keep it as
     * small as necessary.
     * @param initialSeed The seed for the first random.
     * @param seedIncrement The difference between the last seed and the next
     * (the last seed being the initialSeed to begin with).
     * @return {@link randoms} possibly re-initialising it first.
     */
    protected Random[] getRandomsMinLength(int length, long initialSeed,
            long seedIncrement) {
        if (this.initialSeed == initialSeed && this.seedIncrement
                == seedIncrement) {
            if (randoms.length < length) {
                Random[] r = new Random[length];
                System.arraycopy(randoms, 0, r, 0, randoms.length);
                for (int i = randoms.length; i < length; i++) {
                    r[i] = new Random(nextSeed);
                    nextSeed += seedIncrement;
                }
                randoms = r;
            }
        } else {
            this.initialSeed = initialSeed;
            nextSeed = initialSeed;
            this.seedIncrement = seedIncrement;
            randoms = new Random[length];
            for (int i = 0; i < length; i++) {
                randoms[i] = new Random(nextSeed);
                nextSeed += seedIncrement;
            }
        }
        return randoms;
    }

    /**
     * @return {@link #randoms}.
     */
    protected Random[] getRandoms() {
        return randoms;
    }

    /**
     * {@link #getRandomsMinLength(int, long, long)} where first parameter is
     * {@code i}, the second element is 0L, and the third parameter is 1L.
     *
     * @param i The length of the array to be returned.
     * @return An array of {@link java.util.Random}.
     */
    protected Random[] getRandoms(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(
                    "i < 0 in " + this.getClass().getName()
                    + ".getRandoms(int)");
        }
        return getRandomsMinLength(i, 0L, 1L);
    }

    /**
     * Provided for convenience.
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
     * @return a random BigDecimal between 0 and 1 inclusive which can have up
     * to dp decimal places.
     */
    public BigDecimal getRandomBigDecimal(int oom) {
        Random[] random = getRandoms(-oom);
        String value = "0.";
        int digit;
        for (int i = 0; i < -oom; i++) {
            digit = random[i].nextInt(10);
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
        return new BigDecimal(value);
    }
    
    /**
     * Returns a pseudorandom number in the range [l, u].
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
     * @param l The smallest value to return.
     * @param u The largest value to return.
     * @param mbi The Math_BigInteger.
     * @return A pseudorandom number in the range [l, u]
     */
    public BigDecimal getRandom(int oom, BigDecimal l, BigDecimal u, Math_BigInteger mbi) {
        //BigDecimal resolution = new BigDecimal(BigInteger.ONE,decimalPlaces);
        BigDecimal range = u.subtract(l);
        BigInteger rescaledRange = range.scaleByPowerOfTen(-oom).toBigInteger();
        BigInteger rbi = getRandom(mbi, rescaledRange);
        BigDecimal rbd = new BigDecimal(rbi, -oom);
        return rbd.add(l);
    }
    
    /**
     * For getting a random number between {@code 0} and {@code upperLimit}.
     *
     * @param mbi The Math_BigInteger.
     * @param upperLimit The largest number that can be returned.
     * @return A random integer as a BigInteger between 0 and upperLimit
     * inclusive.
     */
    public BigInteger getRandom(Math_BigInteger mbi, BigInteger upperLimit) {
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
        TreeMap<Integer, Integer> upperLimit_PowersOfTwoDecomposition = mbi.getPowersOfTwoDecomposition(upperLimit);
        //Random[] random = this.getRandomsMinLength(1);
        BigInteger r = BigInteger.ZERO;
        Integer key;
        BigInteger powerOfTwo;
        Integer multiples;
        for (Map.Entry<Integer, Integer> entry : upperLimit_PowersOfTwoDecomposition.entrySet()) {
            key = entry.getKey();
            powerOfTwo = mbi.powerOfTwo(key);
            multiples = entry.getValue();
            for (int i = 0; i < multiples; i++) {
                r = r.add(getRandomFromPowerOf2(mbi, powerOfTwo));
//                if (random[0].nextBoolean()){
//                    r = r.add(getRandomFromPowerOf2(powerOfTwo));
//                }
            }
//            if (random[0].nextBoolean()){
//                r = r.add(ONE);
//            }
        }
        return r;
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
    private BigInteger getRandomFromPowerOf2(Math_BigInteger mbi, BigInteger powerOf2) {
        Random[] random = getRandoms(1);
        if (!mbi.powersOfTwo.contains(powerOf2)) {
            int size = mbi.powersOfTwo.size();
            BigInteger lastPower = mbi.powersOfTwo.get(size - 1);
            BigInteger power = lastPower.multiply(BigInteger.TWO);
            mbi.powersOfTwo.add(power);
            while (power.compareTo(powerOf2) == -1) {
                power = lastPower.multiply(BigInteger.TWO);
                mbi.powersOfTwo.add(power);
            }
        }
        int i0 = mbi.powersOfTwo.indexOf(powerOf2);
        BigInteger r = BigInteger.ZERO;
        int randomLength = random.length;
        int i;
        for (i = 0; i < i0; i++) {
            int ri = i;
            while (ri >= randomLength) {
                ri -= randomLength;
            }
            if (random[ri].nextBoolean()) {
                BigInteger powerOfTwo = mbi.powerOfTwo(i);
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
}
