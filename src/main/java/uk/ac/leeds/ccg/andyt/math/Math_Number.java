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
import java.util.Random;

public abstract class Math_Number implements Serializable {

    //static final long serialVersionUID = 1L;
    
    
    /**
     * A String abbreviation for Not A Number.
     */
    public static final String NAN = "NAN";
    
    /**
     * Random instance.
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
     * {@link java.lang.Integer.MAX_VALUE}.
     * @param initialSeed The seed for the first random.
     * @param seedIncrement The difference between the last seed and the next
     * (the last seed being the initialSeed to begin with).
     */
    protected void initRandoms(int length, long initialSeed, long seedIncrement) {
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
     * Initialises or reinitialise {@link #randoms}.If {@link #randoms} is
     * already initialised using the same initialSeed and seedIncrement with at
     * least the size of length, then it is returned, otherwise it is
     * reinitialised.
     *
     * @param length The size to initialise {@link #randoms}. This should be
     * greater than or equal to 1 and less than or equal to
     * {@link java.lang.Integer.MAX_VALUE}.
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
                Random[] newRandomArray = new Random[length];
                System.arraycopy(randoms, 0, newRandomArray, 0, randoms.length);
                for (int i = randoms.length; i < length; i++) {
                    newRandomArray[i] = new Random(nextSeed);
                    nextSeed += seedIncrement;
                }
                randoms = newRandomArray;
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
     *
     * @param i
     * @return
     */
    protected Random[] getRandoms(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(
                    "i < 0 in " + this.getClass().getName()
                    + ".get_RandomArray(int)");
        }
        return getRandomsMinLength(i, 0L, 1L);
    }
}
