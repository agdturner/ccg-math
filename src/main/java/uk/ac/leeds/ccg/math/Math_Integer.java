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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;
import uk.ac.leeds.ccg.generic.util.Generic_Collections;

/**
 * A class for {@code int} numbers.
 *
 * @author Andy Turner
 * @version 1.1
 */
public class Math_Integer extends Math_Number {

    private static final long serialVersionUID = 1L;

    /**
     * In most instances this behaves like
     * {@link java.lang.Integer#parseInt(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Integer#MIN_VALUE} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #SNAN}), then a
     * {@link java.lang.Integer#MIN_VALUE} is returned.</li>
     * </ul>
     *
     * @param s The String to attempt to parse as a {@code int}.
     * @return {@code s} parsed as a {@code int}.
     * @throws NumberFormatException If {@code s} cannot be parse as a
     * {@code int}.
     */
    public static int parseInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Integer.MIN_VALUE;
            }
            if (s.equalsIgnoreCase(SNAN)) {
                return Integer.MIN_VALUE;
            }
            throw e;
        }
    }

    /**
     * For testing if {@code s} can be parsed as an {@code int}. If the result
     * is equal to {@code Integer.MIN_VALUE} then this will return {@code false}
     * as {@code Integer.MIN_VALUE} is reserved for representing no data values.
     *
     * @param s The String to indicate whether it can be represented as an
     * {@code int} (excluding {@code Integer.MIN_VALUE} which is reserved for
     * representing {@code noDataValues}).
     * @return {@code true} iff {@code s} can be represented as an {@code int}
     * (excluding {@code Integer.MIN_VALUE} which is reserved for representing
     * no data values).
     */
    public static boolean isInt(String s) {
        try {
            int x = Integer.parseInt(s);
            return x != Integer.MIN_VALUE;
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return true;
            }
            return s.equalsIgnoreCase(SNAN);
        }
    }

    /**
     * @param x The number to indicate it is even.
     * @return {@code true} iff {@code x} is even.
     */
    public static boolean isEven(int x) {
        return x % 2 == 0;
    }

    /**
     * Returns the x + y checking for overflows.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @return x + y
     * @throws ArithmeticException if the result overflows.
     */
    @Deprecated
    public static int add(int x, int y) {
        return Math.addExact(x, y);
    }
    
    /**
     * Returns the x - y checking for overflows.
     *
     * @param x The number to subtract from.
     * @param y The number to subtract.
     * @return x - y
     * @throws ArithmeticException if the result overflows.
     */
    @Deprecated
    public static int subtract(int x, int y) {
        return Math.subtractExact(x, y);
    }
    
    /**
     * Returns the x * y checking for overflows.
     *
     * @param x A number to add.
     * @param y A number to add.
     * @return x * y
     * @throws ArithmeticException if the result overflows.
     */
    @Deprecated
    public static int multiply(int x, int y) {
        return Math.multiplyExact(x, y);
    }
    
    /**
     * Multiplying by all the prime factors returns the original number. 1 and 
     * the number itself are not included.
     * @param n The number for which the prime factors are returned.
     * @return The prime factors of {@code n} in ascending order. 
     */
    public static TreeMap<Integer, Integer> getPrimeFactors(int n) {
        TreeMap<Integer, Integer> r = new TreeMap<>();
        int n2 = n;
        for (int i = 2; i <= n2/2; i++) {
            if (n2 % i == 0) {
                Generic_Collections.addToCount(r, i, 1);
                n2 = n2 / i;
                i = 2;
            }
        }
        if (!r.isEmpty()) {
            Generic_Collections.addToCount(r, n2, 1);
        }
        return r;
    }
}
