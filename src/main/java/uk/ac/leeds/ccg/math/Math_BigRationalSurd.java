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
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * This is a class to help with the storage and arithmetic of numbers that are
 * comprised of multiplications and division of square roots e.g.
 * {@code sqrt(2) * sqrt(3)}, or {@code sqrt(8) / sqrt(2)}. Sometimes
 * calculations can be simplified without needing to calculate component terms.
 * For instance: {@code sqrt(2) * sqrt(8)} is {@code 4}; and
 * {@code sqrt(3/2) * sqrt(2) * sqrt(6)} is {@code 12}. This has application in
 * geometry for storing distances, areas and volumes precisely and allowing the 
 * user to later determine the Order of Magnitude of precision wanted if another 
 * form of number is wanted. This builds on {@link #Math_BigRationalSqrt} and 
 * may be a component for a more general algebraic number. 
 *
 * @author Andy Turner
 * @version 0.1
 */
public class Math_BigRationalSurd implements Serializable,
        Comparable<Math_BigRationalSurd> {

    private static final long serialVersionUID = 1L;

    /**
     * The numbers.
     */
    protected final ArrayList<Math_BigRationalSqrt> x;

    /**
     * For storing the approximate value {@link #x}.
     */
    protected BigDecimal approx;

    /**
     * Stores the Order Of Magnitude of the precision of {@link #approx}.
     */
    protected int oom;

    /**
     * Stores the MathContext for {@link #oom}.
     */
    protected MathContext oommc;

    /**
     * Creates a new instance.
     *
     * @param x What {@link #x} is set to.
     */
    public Math_BigRationalSurd(Math_BigRationalSqrt... x) {
        this.x = new ArrayList<>();
        this.x.addAll(Arrays.asList(x));
    }

//    /**
//     * @param y The number to multiply by.
//     * @return {@code this} multiplied by {@code y}.
//     */
//    public Math_BigRationalSqrt multiply(Math_BigRationalSqrt y) {
//        return new Math_BigRationalSqrt(x.multiply(y.x));
//    }
//
//    /**
//     * @param y The number to multiply by.
//     * @return {@code this} multiplied by {@code y}.
//     */
//    public Math_BigRationalSqrt multiply(BigRational y) {
//        return multiply(new Math_BigRationalSqrt(y.pow(2)));
//    }
//
//    /**
//     * @param y The number to divide by.
//     * @return {@code this} divided by {@code y}.
//     */
//    public Math_BigRationalSqrt divide(Math_BigRationalSqrt y) {
//        return new Math_BigRationalSqrt(x.divide(y.x));
//    }
//
//    /**
//     * @param y The number to divide by.
//     * @return {@code this} divided by {@code y}.
//     */
//    public Math_BigRationalSqrt divide(BigRational y) {
//        return divide(new Math_BigRationalSqrt(y.pow(2)));
//    }
//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof Math_BigRationalSurd) {
//            return equals((Math_BigRationalSurd) o);
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 29 * hash + Objects.hashCode(this.x);
//        return hash;
//    }
//
//    /**
//     * @param x The Math_BigRationalSqrt to test for equality with this.
//     * @return {@code true} iff this is equal to {@code x}
//     */
//    public boolean equals(Math_BigRationalSurd x) {
//        return x.approx.compareTo(this.approx) == 0;
//    }
    @Override
    public int compareTo(Math_BigRationalSurd o) {
        return approx.compareTo(o.approx);
    }

    /**
     * For getting the Order of Magnitude needed for a square root calculation
     * so that the result can be returned with the precision given by
     * {@code oom}.
     *
     * @param v The number for which the square root is wanted.
     * @param oom The Order of Magnitude for the precision desired.
     * @return The Order of Magnitude of the most significant digit of the
     * resulting square root.
     */
    public static int getOOM(BigRational v, int oom) {
        return Math_BigInteger.getOrderOfMagnitudeOfMostSignificantDigit(
                v.toBigDecimal(new MathContext(2 - oom)).toBigInteger().sqrt()) + 1;
    }

    /**
     * @param x The values.
     * @return The minimum of all the values.
     */
    public static Math_BigRationalSurd min(Math_BigRationalSurd... x) {
        Math_BigRationalSurd r = x[0];
        for (Math_BigRationalSurd b : x) {
            if (b.compareTo(r) == -1) {
                r = b;
            }
        }
        return r;
    }

    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static Math_BigRationalSurd min(Collection<Math_BigRationalSurd> c) {
        return c.parallelStream().min(Comparator.comparing(i -> i)).get();
    }

    /**
     *
     * @param x The values.
     * @return The maximum of all the values.
     */
    public static Math_BigRationalSurd max(Math_BigRationalSurd... x) {
        Math_BigRationalSurd r = x[0];
        for (Math_BigRationalSurd b : x) {
            if (b.compareTo(r) == 1) {
                r = b;
            }
        }
        return r;
    }

    /**
     * Find the maximum in {@code c}.
     *
     * @param c A collection the maximum in which is returned.
     * @return The maximum in {@code c}.
     */
    public static Math_BigRationalSurd max(Collection<Math_BigRationalSurd> c) {
        return c.parallelStream().max(Comparator.comparing(i -> i)).get();
    }
}
