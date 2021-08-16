/*
 * Copyright 2021 Centre for Computational Geography, University of Leeds.
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
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Andy Turner
 */
public class Math_BigRational {
    
    /**
     * Create a new instance.
     */
    public Math_BigRational() {
    }
    
    /**
     * Calculate and return the common factor of two rational numbers.
     * @param x One number.
     * @param y Another number.
     * @return The common factor of the two numbers x and y.
     */
    public static BigRational getCommonFactor(BigRational x, BigRational y) {
        BigRational xr = x.reduce();
        BigRational yr = y.reduce();
        BigInteger xrn = xr.getNumeratorBigInteger();
        BigInteger yrn = yr.getNumeratorBigInteger();
        BigInteger xrd = xr.getDenominatorBigInteger();
        BigInteger yrd = yr.getDenominatorBigInteger();
        BigInteger gcdn = xrn.gcd(yrn);
        BigInteger gcdd = xrd.gcd(yrd);
        return BigRational.valueOf(gcdn, gcdd);
    }
    
    /**
     * Always rounds down.
     * 
     * @param x The value to round.
     * @param oom The order of magnitude to round to.
     * @return A new value which is x rounded down to {@code oom}
     */
    public static BigRational round(BigRational x, int oom) {
        BigRational shift = BigRational.TEN.pow(oom);
        return x.divide(shift).integerPart().multiply(shift);
    }
    
    /**
     * Always rounds down.
     * 
     * @param x The value to round.
     * @param oom The order of magnitude to round to.
     * @return A new value which is x rounded down to {@code oom}
     */
    public static BigDecimal roundToBD(BigRational x, int oom) {
        BigRational shift = BigRational.TEN.pow(oom);
        return x.divide(shift).integerPart().multiply(shift).toBigDecimal();
    }
}
