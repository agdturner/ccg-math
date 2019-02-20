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

public class Math_short extends Math_Number {

    /**
     * For testing if s can be parsed as a int. If the result is equal to
     * Short.MIN_VALUE then this will return false as Short.MIN_VALUE is
     * reserved for representing noDataValues.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * byte (excluding Short.MIN_VALUE which is reserved for representing
     * noDataValues).
     * @return true iff s can be represented as a double (excluding
     * Short.MIN_VALUE which is reserved for representing noDataValues).
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
