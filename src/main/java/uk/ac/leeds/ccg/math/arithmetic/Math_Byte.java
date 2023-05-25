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

/**
 * A class for {@code byte} numbers. 
 *
 * @author Andy Turner
 * @version 2.0
 */
public class Math_Byte {
    
    /**
     * Create a new instance;
     */
    public Math_Byte(){}

    /**
     * For testing if {@code s} can be parsed as a {@code byte}. If the result is equal
     * to {@code Byte.MIN_VALUE} then this will return {@code false} as {@code Byte.MIN_VALUE} is
     * reserved for representing {@code noDataValues}.
     *
     * @param s The String to be tested as to whether it can be represented as a
     * {@code byte} (excluding {@code Byte.MIN_VALUE} which is reserved for representing
     *  no data values).
     * @return {@code true} iff {@code s} can be represented as a {@code byte} (excluding {@code Byte.MIN_VALUE}
     * which is reserved for representing no data values).
     */
    public static boolean isByte(String s) {
        try {
            byte x = Byte.parseByte(s);
            return x != Byte.MIN_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
