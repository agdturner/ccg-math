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

/**
 * A class for {@code long} numbers.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_Long extends Math_Number {

    private static final long serialVersionUID = 1L;

    /**
     * {@code INTEGER_MAX_VALUE = Integer.MAX_VALUE}
     */
    public static final long INTEGER_MAX_VALUE = Integer.MAX_VALUE;

    /**
     * In most instances this behaves like
     * {@link java.lang.Long#parseLong(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Long#MIN_VALUE} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #SNAN}), then a
     * {@link java.lang.Long#MIN_VALUE} is returned.</li>
     * </ul>
     *
     * @param s The String to attempt to parse as a {@code long}.
     * @return {@code s} parsed as a long.
     * @throws NumberFormatException if {@code s} cannot be parsed as a
     * {@code long}.
     */
    public static long parseLong(String s) throws NumberFormatException {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Long.MIN_VALUE;
            }
            if (s.equalsIgnoreCase(SNAN)) {
                return Long.MIN_VALUE;
            }
            throw e;
        }
    }

    /**
     * For testing if {@code s} can be parsed as a {@code long}. If the result
     * is equal to {@code Long.MIN_VALUE} then this will return {@code false} as
     * {@code Long.MIN_VALUE} is reserved for representing no data values.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * {@code long} (excluding {@code Long.MIN_VALUE} which is reserved for
     * representing no data values).
     * @return {@code true} iff {@code s} can be represented as a {@code long}
     * (excluding {@code Long.MIN_VALUE} which is reserved for representing no
     * data values).
     */
    public static boolean isLong(String s) {
        try {
            long x = Long.parseLong(s);
            return x != Long.MIN_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param x The number to indicate if it is even.
     * @return {@code true} iff {@code x} is even.
     */
    public static boolean isEven(long x) {
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
    public static long add(long x, long y) {
        return Math.addExact(x, y);
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
    public static long add(long x, int y) {
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
    public static long subtract(long x, long y) {
        return Math.subtractExact(x, y);
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
    public static long subtract(long x, int y) {
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
    public static long multiply(long x, long y) {
        return Math.multiplyExact(x, y);
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
    public static long multiply(long x, int y) {
        return Math.multiplyExact(x, y);
    }
}
