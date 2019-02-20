/**
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
package uk.ac.leeds.ccg.andyt.math;

/**
 * https://en.wikipedia.org/wiki/Complex_number
 *
 * It is almost certainly better to use org.apache.commons.math.complex.Complex
 * instead of this. This however is perhaps worthwhile background if using
 * BigDecimal instead of double to represent Real and Imaginary parts of a
 * complex number.
 *
 * @author geoagdt
 */
public class Math_Complex_double {

    /**
     * The _real part of the complex number.
     */
    private final double _real;
    
    /**
     * The _imaginary part of the complex number.
     */
    private final double _imaginary;

    /**
     * Create a new instance.
     * 
     * @param real The _real part of the complex number
     * @param imaginary The _imaginary part of the complex number.
     */
    public Math_Complex_double(double real, double imaginary) {
        this._real = real;
        this._imaginary = imaginary;
    }

    /**
     * Create a new instance.
     * 
     * @param magnitude The magnitude used to calculate the _real and _imaginary 
     * @param phase
     * @param irrelevantFlag 
     */
    public Math_Complex_double(double magnitude, double phase, 
            boolean irrelevantFlag) {
        _real = magnitude * Math.cos(phase);
        _imaginary = magnitude * Math.sin(phase);
    }

    public double getReal() {
        return _real;
    }

    public double getImaginary() {
        return _imaginary;
    }

    @Override
    public String toString() {
        return _real + " " + _imaginary + "i";
    }

    /**
     * @param c
     * @return this + c.
     */
    public Math_Complex_double add(Math_Complex_double c) {
        Math_Complex_double result;
        double real;
        real = this._real + c._real;
        double imaginary;
        imaginary = this._imaginary + c._imaginary;
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @param c
     * @return this - c.
     */
    public Math_Complex_double subtract(Math_Complex_double c) {
        Math_Complex_double result;
        double real;
        real = this._real - c._real;
        double imaginary;
        imaginary = this._imaginary - c._imaginary;
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @param c
     * @return this / c.
     */
    public Math_Complex_double multiply(Math_Complex_double c) {
        Math_Complex_double result;
        double magnitude;
        magnitude = this.magnitude() * c.magnitude();
        double phase;
        phase = this.phase() + c.phase();
        result = new Math_Complex_double(magnitude, phase, true);
        return result;
    }

    /**
     * @param c
     * @return this * c.
     */
    public Math_Complex_double multiply2(Math_Complex_double c) {
        Math_Complex_double r;
        double real;
        real = (this._real * c._real) - (this._imaginary * c._imaginary);
        double imaginary;
        imaginary = (this._real * c._imaginary) + (this._imaginary * c._real);
        r = new Math_Complex_double(real, imaginary);
        return r;
    }

    /**
     * N.B. This is called abs() in Apache Commons Math
     *
     * @return The magnitude of this.
     */
    public double magnitude() {
        return Math.hypot(_real, _imaginary);
    }

    /**
     * @return AKA the argument, this is the angle from the Origin to the _real
 axis.
     */
    public double phase() {
        return Math.atan2(_imaginary, _real);
    }

    /**
     * @return Conjugate (new GenericComplex(_real, -_imaginary)).
     */
    public Math_Complex_double conjugate() {
        return new Math_Complex_double(_real, -_imaginary);
    }

    /**
     * @return 1 / this.
     */
    public Math_Complex_double reciprocal() {
        Math_Complex_double result;
        double scale;
        scale = _real * _real + _imaginary * _imaginary;
        double real;
        real = this._real / scale;
        double imaginary = -this._imaginary / scale;
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @param c
     * @return this / c.
     */
    public Math_Complex_double divide(Math_Complex_double c) {
        Math_Complex_double result;
        double magnitude;
        magnitude = this.magnitude() / c.magnitude();
        double phase;
        phase = this.phase() - c.phase();
        result = new Math_Complex_double(magnitude, phase, true);
        return result;
    }

    /**
     * @param c
     * @return this / c.
     */
    public Math_Complex_double divide2(Math_Complex_double c) {
        Math_Complex_double a = this;
        return a.multiply(c.reciprocal());
    }

    /**
     * @return exponent of this.
     */
    public Math_Complex_double exp() {
        Math_Complex_double result;
        double real;
        real = Math.exp(this._real) * Math.cos(_imaginary);
        double imaginary;
        imaginary = Math.exp(this._real) * Math.sin(this._imaginary);
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @return The complex sine of this.
     */
    public Math_Complex_double sin() {
        Math_Complex_double result;
        double real;
        real = Math.sin(this._real) * Math.cosh(_imaginary);
        double imaginary;
        imaginary = Math.cos(this._real) * Math.sinh(this._imaginary);
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @return The complex cosine of this.
     */
    public Math_Complex_double cos() {
        Math_Complex_double result;
        double real;
        real = Math.cos(this._real) * Math.cosh(_imaginary);
        double imaginary;
        imaginary = -Math.sin(this._real) * Math.sinh(this._imaginary);
        result = new Math_Complex_double(real, imaginary);
        return result;
    }

    /**
     * @return The complex tangent of this.
     */
    public Math_Complex_double tan() {
        return sin().divide(cos());
    }

    /**
     * @param alpha
     * @return The complex rescaling of this.
     */
    public Math_Complex_double rescale(double alpha) {
        return new Math_Complex_double(alpha * _real, alpha * _imaginary);
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
        return (this._real == that._real) && (this._imaginary == that._imaginary);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this._real) ^ (Double.doubleToLongBits(this._real) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this._imaginary) ^ (Double.doubleToLongBits(this._imaginary) >>> 32));
        return hash;
    }
}
