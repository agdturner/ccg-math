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

import java.io.Serializable;
import java.util.Objects;

/**
 * An implementation of a https://en.wikipedia.org/wiki/Quaternion number
 *
 * @author Andy Turner
 */
public class Math_Quaternion_Double implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ZERO
     */
    public static final Math_Quaternion_Double ZERO = new Math_Quaternion_Double(0, 0, 0, 0);

    /**
     * ONE
     */
    public static final Math_Quaternion_Double ONE = new Math_Quaternion_Double(1, 0, 0, 0);

    /**
     * I
     */
    public static final Math_Quaternion_Double I = new Math_Quaternion_Double(0, 1, 0, 0);

    /**
     * J
     */
    public static final Math_Quaternion_Double J = new Math_Quaternion_Double(0, 0, 1, 0);

    /**
     * K
     */
    public static final Math_Quaternion_Double K = new Math_Quaternion_Double(0, 0, 0, 1);

    /**
     * Scalar component.
     */
    public double w;

    /**
     * First vector component.
     */
    public double x;

    /**
     * Second vector component.
     */
    public double y;

    /**
     * Third vector component.
     */
    public double z;

    /**
     * Create a new instance.
     *
     * @param w Scalar component.
     * @param x First vector component.
     * @param y Second vector component.
     * @param z Third vector component.
     */
    public Math_Quaternion_Double(double w, double x, double y, double z) {
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
    public Math_Quaternion_Double(Math_Quaternion_Double q) {
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
        if (o instanceof Math_Quaternion_Double q) {
            return w == q.w && x == q.x && y == q.y && z == q.z;
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
                + "w=(" + w + "), "
                + "x=(" + x + "), "
                + "y=(" + y + "), "
                + "z=(" + z + "))";
    }

    /**
     * For addition.
     *
     * @param q The quaternion to add to this.
     * @return the sum of {@code this} and {@code q}.
     */
    public Math_Quaternion_Double add(Math_Quaternion_Double q) {
        return new Math_Quaternion_Double(
                this.w+q.w,
                this.x+q.x,
                this.y+q.y,
                this.z+q.z);
    }

    /**
     * For subtraction.
     *
     * @param q The quaternion to subtract from this.
     * @return the {@code q} subtracted from {@code this}.
     */
    public Math_Quaternion_Double subtract(Math_Quaternion_Double q) {
        return new Math_Quaternion_Double(
                this.w-q.w,
                this.x-q.x,
                this.y-q.y,
                this.z-q.z);
    }

    /**
     * Multiply by a scalar.
     *
     * @param s Scale factor.
     * @return A scaled quaternion.
     */
    public Math_Quaternion_Double multiply(double s) {
        return new Math_Quaternion_Double(w*s, x*s, y*s,z*s);
    }

    /**
     * Divide by a scalar.
     *
     * @param s Scale factor.
     * @return A scaled quaternion.
     */
    public Math_Quaternion_Double divide(double s) {
        return new Math_Quaternion_Double(w/s, x/s, y/s,z/s);
    }

    /**
     * Computes and return the Hamilton product. (N.B. multiplication is not
     * commutative for quaternions i.e. A multiplied by B does not necessarily
     * equal B multiplied by A.)
     *
     * @param q The quaternion to multiply this by.
     * @return the product of {@code this} multiplied by {@code q}.
     */
    public Math_Quaternion_Double multiply(Math_Quaternion_Double q) {
        return new Math_Quaternion_Double(
                w*q.w-x*q.x -y*q.y-z*q.z,
                w*q.x+x*q.w +y*q.z-z*q.y,
                w*q.y-x*q.z +y*q.w+z*q.x,
                w*q.z+x*q.y -y*q.x+z*q.w);
    }

    /**
     * @return the conjugate.
     */
    public Math_Quaternion_Double conjugate() {
        return new Math_Quaternion_Double(w, -x, -y, -z);
    }

    /**
     * Computes the Dot product.
     *
     * @param q Quaternion to get the projection onto.
     * @return {@code this} Dot {@code q}.
     */
    public double getDotProduct(Math_Quaternion_Double q) {
        return w*q.w +x*q.x +y*q.y +z*q.z;
    }

    /**
     * @return The negation.
     */
    public Math_Quaternion_Double negate() {
        return new Math_Quaternion_Double(-w, -x,-y, -z);
    }

    /**
     * Compute and return the magnitude.
     *
     * @return The magnitude
     */
    public double getMagnitude() {
        return Math.sqrt(getMagnitude2());
    }

    /**
     * Compute and return the square of the magnitude.
     *
     * @return The square of the magnitude.
     */
    public double getMagnitude2() {
        return w*w+x*x+y*y+z*z;
    }

    /**
     * Compute and return the normalized quaternion (a.k.a. the versor).
     *
     * @return Normalized quaternion.
     */
    public Math_Quaternion_Double normalize() {
        return this.divide(this.getMagnitude());
    }
}
