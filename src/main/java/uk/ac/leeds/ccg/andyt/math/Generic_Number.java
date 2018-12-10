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

public abstract class Generic_Number
        implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * For storing Random instance for getRandom methods
     */
    protected Random[] _RandomArray;
    Long _InitialRandomSeed;
    Long _NextRandomSeed;

    /**
     * Reports the difference between the system clock and time in milliseconds.
     * System.out.println("Time taken " + (System.currentTimeMillis() - time));
     * @param time a time in milliseconds
     * @return System.currentTimeMillis();
     */
    protected long timeReport(
            long time) {
        // Report time taken
        long result = System.currentTimeMillis();
        System.out.println("Time taken " + (result - time));
        return result;
    }

    protected void init_RandomArrayMinLength(
            int length,
            long seed) {
        _InitialRandomSeed = seed;
        _RandomArray = new Random[length];
        for (int i = 0; i < length; i++) {
            _RandomArray[i] = new Random(seed);
            seed++;
        }
        _NextRandomSeed = seed;
    }

    protected Random[] get_RandomArrayMinLength(
            int length) {
        if (_RandomArray == null){
            init_RandomArrayMinLength(
            length,
            0L);
        }
        if (_RandomArray.length < length) {
            Random[] newRandomArray = new Random[length];
            System.arraycopy(_RandomArray, 0, newRandomArray, 0, _RandomArray.length);
            long seed = _NextRandomSeed;
            for (int i = _RandomArray.length; i < length; i++) {
                newRandomArray[i] = new Random(seed);
                seed++;
            }
            _NextRandomSeed = seed;
            _RandomArray = newRandomArray;
        }
        return _RandomArray;
    }
    
    protected Random[] get_RandomArray() {
        return _RandomArray;
    }

    protected Random[] get_RandomArray(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(
                    "i < 0 in " + this.getClass().getName() +
                    ".get_RandomArray(int)");
        }
        return get_RandomArrayMinLength(i);
    }
}
