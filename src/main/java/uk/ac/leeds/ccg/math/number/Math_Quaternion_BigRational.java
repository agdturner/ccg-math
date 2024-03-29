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
package uk.ac.leeds.ccg.math.number;

import ch.obermuhlner.math.big.BigRational;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * An implementation of a https://en.wikipedia.org/wiki/Quaternion number
 *
 * @author Andy Turner
 */
public class Math_Quaternion_BigRational implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ZERO
     */
    public static final Math_Quaternion_BigRational ZERO = new Math_Quaternion_BigRational(0, 0, 0, 0);

    /**
     * ONE
     */
    public static final Math_Quaternion_BigRational ONE = new Math_Quaternion_BigRational(1, 0, 0, 0);

    /**
     * I
     */
    public static final Math_Quaternion_BigRational I = new Math_Quaternion_BigRational(0, 1, 0, 0);

    /**
     * J
     */
    public static final Math_Quaternion_BigRational J = new Math_Quaternion_BigRational(0, 0, 1, 0);

    /**
     * K
     */
    public static final Math_Quaternion_BigRational K = new Math_Quaternion_BigRational(0, 0, 0, 1);

    /**
     * Scalar component.
     */
    public BigRational w;

    /**
     * First vector component.
     */
    public BigRational x;

    /**
     * Second vector component.
     */
    public BigRational y;

    /**
     * Third vector component.
     */
    public BigRational z;

    /**
     * Create a new instance.
     *
     * @param w Scalar component.
     * @param x First vector component.
     * @param y Second vector component.
     * @param z Third vector component.
     */
    public Math_Quaternion_BigRational(long w, long x, long y, long z) {
        this(BigRational.valueOf(w), BigRational.valueOf(x),
                BigRational.valueOf(y), BigRational.valueOf(z));
    }

    /**
     * Create a new instance.
     *
     * @param w Scalar component.
     * @param x First vector component.
     * @param y Second vector component.
     * @param z Third vector component.
     */
    public Math_Quaternion_BigRational(BigRational w, BigRational x,
            BigRational y, BigRational z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new instance.
     *
     * @param q Quaternion to duplicate.
     */
    public Math_Quaternion_BigRational(Math_Quaternion_BigRational q) {
        w = q.w;
        x = q.x;
        y = q.y;
        z = q.z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Math_Quaternion_BigRational q) {
            return w.equals(q.w) && x.equals(q.x) && y.equals(q.y)
                    && z.equals(q.z);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.w);
        hash = 29 * hash + Objects.hashCode(this.x);
        hash = 29 * hash + Objects.hashCode(this.y);
        hash = 29 * hash + Objects.hashCode(this.z);
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "("
                + "w=(" + w.toString() + "), "
                + "x=(" + x.toString() + "), "
                + "y=(" + y.toString() + "), "
                + "z=(" + z.toString() + "))";
    }

    /**
     * For addition.
     *
     * @param q The quaternion to add to this.
     * @return the sum of {@code this} and {@code q}.
     */
    public Math_Quaternion_BigRational add(Math_Quaternion_BigRational q) {
        return new Math_Quaternion_BigRational(
                this.w.add(q.w),
                this.x.add(q.x),
                this.y.add(q.y),
                this.z.add(q.z));
    }

    /**
     * For subtraction.
     *
     * @param q The quaternion to subtract from this.
     * @return the {@code q} subtracted from {@code this}.
     */
    public Math_Quaternion_BigRational subtract(Math_Quaternion_BigRational q) {
        return new Math_Quaternion_BigRational(
                this.w.subtract(q.w),
                this.x.subtract(q.x),
                this.y.subtract(q.y),
                this.z.subtract(q.z));
    }

    /**
     * Multiply by a scalar.
     *
     * @param s Scale factor.
     * @return A scaled quaternion.
     */
    public Math_Quaternion_BigRational multiply(BigRational s) {
        return new Math_Quaternion_BigRational(w.multiply(s), x.multiply(s),
                y.multiply(s), z.multiply(s));
    }

    /**
     * Divide by a scalar.
     *
     * @param s Scale factor.
     * @return A scaled quaternion.
     */
    public Math_Quaternion_BigRational divide(BigRational s) {
        return new Math_Quaternion_BigRational(w.divide(s), x.divide(s),
                y.divide(s), z.divide(s));
    }

    /**
     * Computes and return the Hamilton product. (N.B. multiplication is not
     * commutative for quaternions i.e. A multiplied by B does not necessarily
     * equal B multiplied by A.)
     *
     * @param q The quaternion to multiply this by.
     * @return the product of {@code this} multiplied by {@code q}.
     */
    public Math_Quaternion_BigRational multiply(Math_Quaternion_BigRational q) {
        return new Math_Quaternion_BigRational(
                w.multiply(q.w).subtract(x.multiply(q.x))
                        .subtract(y.multiply(q.y)).subtract(z.multiply(q.z)),
                w.multiply(q.x).add(x.multiply(q.w))
                        .add(y.multiply(q.z)).subtract(z.multiply(q.y)),
                w.multiply(q.y).subtract(x.multiply(q.z))
                        .add(y.multiply(q.w)).add(z.multiply(q.x)),
                w.multiply(q.z).add(x.multiply(q.y))
                        .subtract(y.multiply(q.x)).add(z.multiply(q.w)));
    }

    /**
     * @return the conjugate.
     */
    public Math_Quaternion_BigRational conjugate() {
        return new Math_Quaternion_BigRational(w, x.negate(), y.negate(), z.negate());
    }

    /**
     * Computes the Dot product.
     *
     * @param q Quaternion to get the projection onto.
     * @return {@code this} Dot {@code q}.
     */
    public BigRational getDotProduct(Math_Quaternion_BigRational q) {
        return this.w.multiply(q.w)
                .add(this.x.multiply(q.x))
                .add(this.y.multiply(q.y))
                .add(this.z.multiply(q.z));
    }

    /**
     * @return The negation.
     */
    public Math_Quaternion_BigRational negate() {
        return new Math_Quaternion_BigRational(w.negate(), x.negate(),
                y.negate(), z.negate());
    }

    /**
     * Compute and return the magnitude.
     *
     * @param oom The Order of Magnitude for the precision of the result.
     * @param rm The RoundingMode for any rounding.
     * @return The magnitude
     */
    public BigRational getMagnitude(int oom, RoundingMode rm) {
        return new Math_BigRationalSqrt(getMagnitude2(), oom, rm)
                .getSqrt(oom, rm);
    }

    /**
     * Compute and return the square of the magnitude.
     *
     * @return The square of the magnitude.
     */
    public BigRational getMagnitude2() {
        return w.pow(2).add(x.pow(2)).add(y.pow(2)).add(z.pow(2));

    }

    /**
     * Compute and return the normalized quaternion (a.k.a. the versor).
     *
     * @param oom The Order of Magnitude for the precision.
     * @param rm The RoundingMode for any rounding.
     * @return Normalized quaternion.
     */
    public Math_Quaternion_BigRational normalize(int oom, RoundingMode rm) {
        return this.divide(this.getMagnitude(oom, rm));
    }

    /**
     * @return {@link #w}.
     */
    public BigRational getW() {
        return w;
    }

    /**
     * @return {@link #x}.
     */
    public BigRational getX() {
        return x;
    }

    /**
     * @return {@link #y}.
     */
    public BigRational getY() {
        return y;
    }

    /**
     * @return {@link #z}.
     */
    public BigRational getZ() {
        return z;
    }
}
