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

import java.io.Serializable;
import java.util.Random;

/**
 * For dealing with numbers.
 * 
 * @author Andy Turner
 * @version 1.0
 */
public abstract class Math_Number implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * A String abbreviation for Not A Number.
     */
    public static final String SNAN = "NAN";

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
    protected void initRandoms(int length, long initialSeed, long seedIncrement) {
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
     * {@code null}, then it is initialised using
     * {@link #initRandoms(int, long, long)}. If {@link #randoms} is already
     * initialised, {@link #initialSeed} is the same as {@code initialSeed},
     * {@link #seedIncrement} is the same as {@code seedIncrement}, and
     * {@link #randoms}{@code .length} is less than or equal to {@code length},
     * then {@link #randoms} is returned. If {@link #randoms} is already
     * initialised, {@link #initialSeed} is the same as {@code initialSeed},
     * {@link #seedIncrement} is the same as {@code seedIncrement}, and
     * {@link #randoms}{@code .length} is greater than {@code length}, then
     * {@link #randoms} is effectively extended with the existing {@link Random}
     * instances copied into the start of the replacement array with length
     * {@code length} and the additional instances initialised using
     * {@link #nextSeed} incremented with {@code seedIncrement} for each new
     * instance added until the array is filled. In all other case
     * {{@link #randoms} is reinitialised using
     * {@link #initRandoms(int, long, long)}.
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
        if (randoms == null) {
            initRandoms(length, initialSeed, seedIncrement);
            return randoms;
        }
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
            initRandoms(length, initialSeed, seedIncrement);
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
}
