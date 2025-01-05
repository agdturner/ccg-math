/*
 * Copyright 2022 Centre for Computational Geography.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.math.geometry;

/**
 *
 * @author Andy Turner
 */
public class Math_AngleDouble {

    /**
     * Create a new instance.
     */
    public Math_AngleDouble(){}

    public static double PIBY2 = Math.PI/2.0d;

    public static double PIBY4 = Math.PI/4.0d;

    /**
     * Returns a normal angle in the range 0 to 2PI.
     *
     * @param theta The angle to be normalised.
     * @return A normalised angle.
     */
    public static double normalise(double theta) {
        double twoPi = Math.PI * 2d;
        // Change a negative angle into a positive one.
        if (theta < 0d) {
            int n = (int) (theta / twoPi) - 1;
            theta = theta - (n * twoPi);
        } else {
            // Only rotate less than 2Pi radians.
            if (theta > twoPi) {
                int n = (int) (theta / twoPi);
                theta = theta - (n * twoPi);
            }
        }
        return theta;
    }
}
