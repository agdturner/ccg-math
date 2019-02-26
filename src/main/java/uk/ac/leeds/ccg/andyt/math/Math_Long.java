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

public class Math_Long extends Math_Number  {

    public static final long INTEGER_MAX_VALUE = Integer.MAX_VALUE;

    /**
     * In most instances this behaves like
     * {@link java.lang.Long#parseLong(java.lang.String)}, but if a
     * {@link java.lang.NumberFormatException} is thrown then this method deals
     * with the following special cases:
     * <ul>
     * <li>If {@code s} contains only white space, then a
     * {@link java.lang.Long#MIN_VALUE} is returned.</li>
     * <li>If {@code s}.equalsIgnoreCase({@link #NAN}), then a
     * {@link java.lang.Long#MIN_VALUE} is returned.</li>
     * </ul>
     *
     * @param s The String to be parsed as a long.
     * @return The long version of s.
     * @throws NumberFormatException if s cannot be parsed as a long.
     */
    public static long parseLong(String s) throws NumberFormatException {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            // Deal with special cases
            if (s.isBlank()) {
                return Long.MIN_VALUE;
            }
            if (s.equalsIgnoreCase(NAN)) {
                return Long.MIN_VALUE;
            }
            throw e;
        }
    }
    
    /**
     * For testing if s can be parsed as a int. If the result is equal to
     * Long.MIN_VALUE then this will return false as Long.MIN_VALUE is
     * reserved for representing noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * byte (excluding Long.MIN_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a long (excluding
     * Long.MIN_VALUE which is reserved for representing noDataValues).
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
     * 
     * @param x The number to test as to whether it is even.
     * @return True iff x is even. 
     */
    public static boolean isEven(long x) {
        return x % 2 == 0;
    }
}
