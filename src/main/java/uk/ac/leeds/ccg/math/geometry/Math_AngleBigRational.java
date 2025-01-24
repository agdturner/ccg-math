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
public class Math_AngleBigRational {

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
    
    public static final BigRational P180 = BigRational.valueOf(180);

    /**
     * Create a new instance
     */
    public Math_AngleBigRational() {
        bd = new Math_BigDecimal();
    }

    /**
     * Create a new instance.
     *
     * @param bd What bd is set to.
     */
    public Math_AngleBigRational(Math_BigDecimal bd) {
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
    public BigRational getPi(int oom, RoundingMode rm) {
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
    public static BigRational normalise(BigRational theta, Math_BigDecimal bd, int oom, RoundingMode rm) {
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
        return r;
    }
    
    /**
     * Calculate and return the x*180/Pi.
     *
     * @param x The angle in radians to convert to decimal degrees.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return The angle of x in decimal degrees.
     */
    public BigRational toDegrees(BigRational x, int oom, RoundingMode rm) {
        return x.multiply(P180).divide(getPi(oom, rm));
    }

    /**
     * Calculate and return the x*Math.PI/180.
     *
     * @param x The angle in decimal degrees to convert to radians.
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return The angle of x in radians.
     */
    public BigRational toRadians(BigRational x, int oom, RoundingMode rm) {
        return x.multiply(getPi(oom, rm)).divide(P180);
    }
}
