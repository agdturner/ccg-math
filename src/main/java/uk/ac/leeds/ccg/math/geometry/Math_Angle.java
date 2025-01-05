/*
 * Copyright 2025 Centre for Computational Geography.
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

import ch.obermuhlner.math.big.BigRational;
import java.math.RoundingMode;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigRational;

/**
 *
 * @author Andy Turner
 */
public class Math_Angle {

    /**
     * For calculating/loading pi.
     */
    private final Math_BigDecimal bd;

    /**
     * For storing pi at the given order of magnitude and for the given
     * RoundingMode.
     */
    private BigRational pi;

    /**
     * For storing the order of magnitude of precision of {@link pi}.
     */
    private int oompi;

    /**
     * The RoundingMode used for rounding {@link pi}.
     */
    private RoundingMode rmpi;

    /**
     * Create a new instance
     */
    public Math_Angle() {
        bd = new Math_BigDecimal();
    }

    /**
     * Create a new instance.
     *
     * @param bd What bd is set to.
     */
    public Math_Angle(Math_BigDecimal bd) {
        this.bd = bd;
    }

    /**
     * Get pi to the given order of magnitude for the precision using the given 
     * RoundingMode.
     *
     * @param oom The order of magnitude for the precision.
     * @param rm The RoundingMode for rounding.
     * @return pi to the given order of magnitude using the given RoundingMode.
     */
    public BigRational getPI(int oom, RoundingMode rm) {
        if (pi != null) {
            if (oompi <= oom) {
                if (oompi < oom) {
                    return Math_BigRational.round(pi, oom, rm);
                }
                if (rm.equals(rmpi)) {
                    return Math_BigRational.round(pi, oom, rm);
                }
            }
        }
        oompi = oom;
        rmpi = rm;
        pi = Math_BigRational.getPi(bd, oom, rm);
        return pi;
    }

    /**
     * Returns a normal angle in the range 0 to 2PI.
     *
     * @param theta The angle to be normalised.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return A normalised angle.
     */
    public BigRational normalise(BigRational theta, int oom, RoundingMode rm) {
        BigRational twoPi = BigRational.valueOf(bd.getPi(oom, rm)).multiply(2);
        BigRational r = theta;
        // Change a negative angle into a positive one.
        if (theta.compareTo(BigRational.ZERO) == -1) {
            int n = theta.divide(twoPi).subtract(1).intValue();
            r = theta.subtract(twoPi.multiply(n));
        } else {
            // Only rotate less than 2Pi radians.
            if (theta.compareTo(BigRational.ZERO) == 1) {
                int n = theta.divide(twoPi).intValue();
                r = theta.subtract(twoPi.multiply(n));
            }
        }
        //        BigRational r = theta;
        //        // Change a negative angle into a positive one.
        //        while (r.compareTo(BigRational.ZERO) == -1) {
        //            r = r.add(twoPi);
        //        }
        //        // Only rotate less than 2Pi radians.
        //        while (r.compareTo(twoPi) == 1) {
        //            r = r.subtract(twoPi);
        //        }
        return r;
    }
}
