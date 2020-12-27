/*
 * Copyright 2020 Centre for Computational Geography, University of Leeds.
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
package uk.ac.leeds.ccg.math;

import ch.obermuhlner.math.big.BigRational;
import java.math.BigInteger;

/**
 *
 * @author Andy Turner
 * @version 0.1
 */
public class Math_BigRationalSqrt {

    /**
     * The square of a BigRational
     */
    public final BigRational x;

    /**
     * If the square root of x can be stored exactly as a BigRational then this 
     * stores it, otherwise it is {@code null}.
     */
    public final BigRational sqrtx;

    /**
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSqrt(BigRational x) {
        this.x = x;
        sqrtx = getSqrtRational(x);
    }

    /**
     * @param x The number to indicate if the square root of it is rational.
     * @return The square root of x if it is rational and {@code null}
     * otherwise.
     */
    public static BigRational getSqrtRational(BigRational x) {
        BigInteger num = x.getNumeratorBigInteger();
        BigInteger nums = Math_BigInteger.getPerfectSquareRoot(num);
        if (nums != null) {
            BigInteger den = x.getDenominatorBigInteger();
            BigInteger dens = Math_BigInteger.getPerfectSquareRoot(den);
            if (dens != null) {
                return BigRational.valueOf(nums).divide(BigRational.valueOf(dens));
            }
        }
        return null;
    }

    /**
     * @param y The other number to multiply.
     * @return {@code y} multiplied by {@link # x2} if this can be expressed
     * exactly as a BigRational and {@code null} otherwise.
     */
    public BigRational multiply(Math_BigRationalSqrt y) {
        int c = y.x.compareTo(x);
        switch (c) {
            case 0:
                return x;
            case -1: {
                BigRational d = y.x.divide(x);
                if (d.isInteger()) {
                    return d.multiply(x);
                }
                break;
            }
            default: {
                BigRational d = x.divide(y.x);
                if (d.isInteger()) {
                    return d.multiply(y.x);
                }
                break;
            }
        }
        return null;
    }
}
