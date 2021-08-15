/*
 * Copyright 2017 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.math;

/**
 * https://en.wikipedia.org/wiki/Complex_number
 *
 * It is almost certainly better to use org.apache.commons.math.complex.Complex
 * instead of this. This however, could help when implementing a version using
 * BigDecimal or BigRational instead of double to represent Real and Imaginary
 * parts of a complex number.
 *
 * @author Andy Turner
 */
public class Math_Complex_double {

    /**
     * The real part of the complex number.
     */
    private final double real;

    /**
     * The imaginary part of the complex number.
     */
    private final double imaginary;

    /**
     * Create a new instance.
     *
     * @param real The real part of the complex number
     * @param imaginary The imaginary part of the complex number.
     */
    public Math_Complex_double(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Create a new instance.
     *
     * @param magnitude The magnitude used to calculate the real and imaginary
     * parts of the number.
     * @param phase The phase used to calculate the real and imaginary parts of
     * the number.
     * @param irrelevantFlag Simply used to distinguish between this method and
     * {@link Math_Complex_double(double,double)}
     */
    public Math_Complex_double(double magnitude, double phase,
            boolean irrelevantFlag) {
        real = magnitude * Math.cos(phase);
        imaginary = magnitude * Math.sin(phase);
    }

    /**
     * @return a copy of {@link #real}.
     */
    public double getReal() {
        return real;
    }

    /**
     * @return a copy of {@link #imaginary}.
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * @return A string representation of this.
     */
    @Override
    public String toString() {
        return real + " " + imaginary + "i";
    }

    /**
     * @param c The number to add to this.
     * @return A new complex number which is this + c.
     */
    public Math_Complex_double add(Math_Complex_double c) {
        double r = this.real + c.real;
        double i = this.imaginary + c.imaginary;
        return new Math_Complex_double(r, i);
    }

    /**
     * @param c The number to subtract from this.
     * @return A new complex number which is this - c.
     */
    public Math_Complex_double subtract(Math_Complex_double c) {
        double r = this.real - c.real;
        double i = this.imaginary - c.imaginary;
        return new Math_Complex_double(r, i);
    }

    /**
     * Multiply by multiplying magnitude and adding phases.
     *
     * @param c The number to multiply with this.
     * @return A new complex number which is this * c.
     */
    public Math_Complex_double multiply(Math_Complex_double c) {
        double magnitude = this.magnitude() * c.magnitude();
        double phase = this.phase() + c.phase();
        return new Math_Complex_double(magnitude, phase, true);
    }

    /**
     * Multiply by:
     * <ol>
     * <li>multiplying real parts and subtracting multiplied imaginary parts to
     * get the real part.</li>
     * <li>multiplying real part of this with the imaginary part of c and adding
     * this to the imaginary part of this multiplied by the real part of c.</li>
     * </ol>
     *
     * @param c The number to add to this.
     * @return A new complex number which is * c.
     */
    public Math_Complex_double multiply2(Math_Complex_double c) {
        double r = (this.real * c.real) - (this.imaginary * c.imaginary);
        double i = (this.real * c.imaginary) + (this.imaginary * c.real);
        return new Math_Complex_double(r, i);
    }

    /**
     * N.B. This is called abs() in Apache Commons Math.
     *
     * @return The magnitude of this.
     */
    public double magnitude() {
        return Math.hypot(real, imaginary);
    }

    /**
     * @return The argument: the angle from the Origin to the real axis.
     */
    public double phase() {
        return Math.atan2(imaginary, real);
    }

    /**
     * @return Conjugate: {@code new GenericComplex(real, -imaginary)}.
     */
    public Math_Complex_double conjugate() {
        return new Math_Complex_double(real, -imaginary);
    }

    /**
     * @return 1 / this.
     */
    public Math_Complex_double reciprocal() {
        double scale = real * real + imaginary * imaginary;
        double r = this.real / scale;
        double i = -this.imaginary / scale;
        return new Math_Complex_double(r, i);
    }

    /**
     * Divide by calculating resulting magnitude and phase.
     *
     * @param c The number to multiply with this.
     * @return A new complex number which is this / c.
     */
    public Math_Complex_double divide(Math_Complex_double c) {
        double magnitude = this.magnitude() / c.magnitude();
        double phase = this.phase() - c.phase();
        return new Math_Complex_double(magnitude, phase, true);
    }

    /**
     * Divide by taking the reciprocal of c and multiplying with this.
     *
     * @param c The number to multiply with this.
     * @return A new complex number which is this / c.
     */
    public Math_Complex_double divide2(Math_Complex_double c) {
        return this.multiply(c.reciprocal());
    }

    /**
     * @return exponent of this.
     */
    public Math_Complex_double exp() {
        double r = Math.exp(this.real) * Math.cos(imaginary);
        double i = Math.exp(this.real) * Math.sin(this.imaginary);
        return new Math_Complex_double(r, i);
    }

    /**
     * @return The complex sine of this.
     */
    public Math_Complex_double sin() {
        double r = Math.sin(this.real) * Math.cosh(imaginary);
        double i = Math.cos(this.real) * Math.sinh(this.imaginary);
        return new Math_Complex_double(r, i);
    }

    /**
     * @return The complex cosine of this.
     */
    public Math_Complex_double cos() {
        double r = Math.cos(this.real) * Math.cosh(imaginary);
        double i = -Math.sin(this.real) * Math.sinh(this.imaginary);
        return new Math_Complex_double(r, i);
    }

    /**
     * @return The complex tangent of this.
     */
    public Math_Complex_double tan() {
        return sin().divide(cos());
    }

    /**
     * @param alpha The rescaling factor.
     * @return The complex rescaling of this.
     */
    public Math_Complex_double rescale(double alpha) {
        return new Math_Complex_double(alpha * real, alpha * imaginary);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Math_Complex_double that = (Math_Complex_double) o;
        return (this.real == that.real) && (this.imaginary == that.imaginary);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.real) ^ (Double.doubleToLongBits(this.real) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.imaginary) ^ (Double.doubleToLongBits(this.imaginary) >>> 32));
        return hash;
    }
}
