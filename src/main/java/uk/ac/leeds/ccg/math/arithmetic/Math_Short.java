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
package uk.ac.leeds.ccg.math.arithmetic;

import uk.ac.leeds.ccg.math.core.Math_Strings;
/**
 * A class for {@code short} numbers.
 *
 * @author Andy Turner
 * @version 2.0
 */
public class Math_Short {
    
    /**
     * Create a new instance;
     */
    public Math_Short(){}

    /**
     * In most instances this behaves like
     * {@link java.lang.Short#parseShort(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Short#MIN_VALUE} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link Math_Strings#S_NAN}), then a
     * {@link java.lang.Short#MIN_VALUE} is returned.</li>
     * </ul>
     *
     * @param s The String to be parsed as a {@code short}.
     * @return The short version of {@code s}.
     * @throws NumberFormatException if {@code s} cannot be parsed as a
     * {@code short}.
     */
    public static short parseShort(String s) throws NumberFormatException {
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Short.MIN_VALUE;
            }
            if (s.equalsIgnoreCase(Math_Strings.S_NAN)) {
                return Short.MIN_VALUE;
            }
            throw e;
        }
    }

    /**
     * For testing if {@code s} can be parsed as a {@code int}. If the result is
     * equal to {@code Short.MIN_VALUE} then this will return {@code false} as
     * {@code Short.MIN_VALUE} is reserved for representing no data values.
     *
     * @param s The String to indicate whether it can be represented as a
     * {@code short} (excluding {@code Short.MIN_VALUE} which is reserved for
     * representing no data values).
     * @return {@code true} iff {@code s} can be represented as a {@code short}
     * (excluding {@code Short.MIN_VALUE} which is reserved for representing no
     * data values).
     */
    public static boolean isShort(String s) {
        try {
            short x = Short.parseShort(s);
            return x != Short.MIN_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
