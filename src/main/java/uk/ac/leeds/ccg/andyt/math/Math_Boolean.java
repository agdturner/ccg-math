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

import uk.ac.leeds.ccg.andyt.generic.core.Generic_Strings;

public class Math_Boolean extends Math_Number {

    /**
     * If {@link #isTrue(java.lang.String)} == {@code true} then return
     * {@code true} else return {@code false}.
     *
     * @param s The String to be tested as to whether it is equivalent to
     * {@code true}.
     * @return true iff s is equivalent to {@code true}.
     */
    public static boolean parseBoolean(String s) {
        return isTrue(s);
    }

    /**
     * For testing if s is the equivalent of {@code true}. Values of s
     * equivalent to true include: "TRUE", "True", "true", "T", "t", "1".
     *
     * @param s The String to be tested as to whether it is equivalent to
     * {@code true}.
     * @return true iff s is equivalent to {@code true}.
     */
    public static boolean isTrue(String s) {
        if (s.equalsIgnoreCase(Generic_Strings.s_True)) {
            return true;
        }
        if (s.equalsIgnoreCase(Generic_Strings.s_T)) {
            return true;
        }
        return s.equalsIgnoreCase(Generic_Strings.symbol_1);
    }

    /**
     * For testing if s is the equivalent of {@code false}. Values of s
     * equivalent to false include: "FALSE", "False", "false", "F", "f", "0".
     *
     * @param s The String to be tested as to whether it is equivalent to
     * {@code fasle}.
     * @return true iff s is equivalent to {@code false}.
     */
    public static boolean isFalse(String s) {
        if (s.equalsIgnoreCase(Generic_Strings.s_False)) {
            return true;
        }
        if (s.equalsIgnoreCase(Generic_Strings.s_F)) {
            return true;
        }
        return s.equalsIgnoreCase(Generic_Strings.symbol_0);
    }

    /**
     * For testing if s either equivalent to {@code true} or {@code false}.
     * {@code return isTrue(s) || isFalse(s);}.
     *
     * @param s The String to be tested as to whether it is equivalent to
     * {@code true} or {@code false}.
     * @return true iff s is equivalent to {@code true} or {@code false}.
     */
    public static boolean isBoolean(String s) {
        return isTrue(s) || isFalse(s);
    }
}
