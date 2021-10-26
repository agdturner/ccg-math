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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import uk.ac.leeds.ccg.math.arithmetic.Math_BigDecimal;

/**
 * A rational number represented as a quotient of two values.
 *
 * <p>
 * Basic calculations with rational numbers (+ - * /) have no loss of precision.
 * This allows to use {@link Math_BigRational} as a replacement for
 * {@link BigDecimal} if absolute accuracy is desired.</p>
 *
 * <p>
 * <a href="http://en.wikipedia.org/wiki/Rational_number">Wikipedia: Rational
 * number</a>
 * </p>
 *
 * <p>
 * The values are internally stored as {@link BigDecimal} (for performance
 * optimizations) but represented as {@link BigInteger} (for mathematical
 * correctness) when accessed with {@link #getNumeratorBigInteger()} and
 * {@link #getDenominatorBigInteger()}.
 * </p>
 *
 * <p>
 * The following basic calculations have no loss of precision:</p>
 * <ul>
 * <li>{@link #add(BigRational)}</li>
 * <li>{@link #subtract(BigRational)}</li>
 * <li>{@link #multiply(BigRational)}</li>
 * <li>{@link #divide(BigRational)}</li>
 * <li>{@link #pow(int)}</li>
 * </ul>
 *
 * <p>
 * The following calculations are special cases of the ones listed above and
 * have no loss of precision:</p>
 * <ul>
 * <li>{@link #negate()}</li>
 * <li>{@link #reciprocal()}</li>
 * <li>{@link #increment()}</li>
 * <li>{@link #decrement()}</li>
 * </ul>
 *
 * <p>
 * This was originally a wrapper around Version 2.3.0 of the BigRational class
 * in:
 * <a href="https://mvnrepository.com/artifact/ch.obermuhlner/big-math">https://mvnrepository.com/artifact/ch.obermuhlner/big-math</a>
 * <a href="https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java">https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java</a>
 * The wrapper made the class both Serializable and it extended Math_Number.
 * </p>
 * <p>
 * The wrapper was not a great solution. Despite encouraging a new version of
 * BigMath to be released, this didn't seem likely to happen. The BigRational
 * class was standalone so was effectively duplicated, although some parts of
 * the class to do with Bernoulli numbers were intentionally left out.
 * </p>
 *
 * @author Eric Obermühlner, Andy Turner
 * @version 2.0
 */
public class Math_BigRational extends Number implements Comparable<Math_BigRational> {

    private static final long serialVersionUID = 1L;

    /**
     * The value 0 as {@link Math_BigRational}.
     */
    public static final Math_BigRational ZERO = new Math_BigRational(0);

    /**
     * The value 1 as {@link Math_BigRational}.
     */
    public static final Math_BigRational ONE = new Math_BigRational(1);

    /**
     * The value 2 as {@link Math_BigRational}.
     */
    public static final Math_BigRational TWO = new Math_BigRational(2);

    /**
     * The value 10 as {@link Math_BigRational}.
     */
    public static final Math_BigRational TEN = new Math_BigRational(10);

    /**
     * The numerator.
     */
    private final BigDecimal numerator;

    /**
     * The denominator.
     */
    private final BigDecimal denominator;

    /**
     * Create a new instance.
     *
     * @param value The
     */
    private Math_BigRational(int value) {
        this(BigDecimal.valueOf(value), BigDecimal.ONE);
    }

    private Math_BigRational(BigDecimal num, BigDecimal denom) {
        BigDecimal n = num;
        BigDecimal d = denom;
        if (d.signum() == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        if (d.signum() < 0) {
            n = n.negate();
            d = d.negate();
        }
        numerator = n;
        denominator = d;
    }

    /**
     * Returns the numerator of this rational number as BigInteger.
     *
     * @return the numerator as BigInteger
     */
    public BigInteger getNumeratorBigInteger() {
        return numerator.toBigInteger();
    }

    /**
     * Returns the numerator of this rational number as BigDecimal.
     *
     * @return the numerator as BigDecimal
     */
    public BigDecimal getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator of this rational number as BigInteger.
     *
     * <p>
     * Guaranteed to not be 0.</p>
     * <p>
     * Guaranteed to be positive.</p>
     *
     * @return the denominator as BigInteger
     */
    public BigInteger getDenominatorBigInteger() {
        return denominator.toBigInteger();
    }

    /**
     * Returns the denominator of this rational number as BigDecimal.
     *
     * <p>
     * Guaranteed to not be 0.</p>
     * <p>
     * Guaranteed to be positive.</p>
     *
     * @return the denominator as BigDecimal
     */
    public BigDecimal getDenominator() {
        return denominator;
    }

    /**
     * Reduces this rational number to the smallest numerator/denominator with
     * the same value.
     *
     * @return the reduced rational number
     */
    public Math_BigRational reduce() {
        BigInteger n = numerator.toBigInteger();
        BigInteger d = denominator.toBigInteger();

        BigInteger gcd = n.gcd(d);
        n = n.divide(gcd);
        d = d.divide(gcd);

        return valueOf(n, d);
    }

    /**
     * Returns the integer part of this rational number.
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(3.5).integerPart()</code> returns
     * <code>BigRational.valueOf(3)</code></li>
     * </ul>
     *
     * @return the integer part of this rational number
     */
    public Math_BigRational integerPart() {
        return of(numerator.subtract(numerator.remainder(denominator)), denominator);
    }

    /**
     * Returns the fraction part of this rational number.
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(3.5).integerPart()</code> returns
     * <code>BigRational.valueOf(0.5)</code></li>
     * </ul>
     *
     * @return the fraction part of this rational number
     */
    public Math_BigRational fractionPart() {
        return of(numerator.remainder(denominator), denominator);
    }

    /**
     * Negates this rational number (inverting the sign).
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(3.5).negate()</code> returns
     * <code>BigRational.valueOf(-3.5)</code></li>
     * </ul>
     *
     * @return the negated rational number
     */
    public Math_BigRational negate() {
        if (isZero()) {
            return this;
        }
        return of(numerator.negate(), denominator);
    }

    /**
     * Calculates the reciprocal of this rational number (1/x).
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(0.5).reciprocal()</code> returns
     * <code>BigRational.valueOf(2)</code></li>
     * <li><code>BigRational.valueOf(-2).reciprocal()</code> returns
     * <code>BigRational.valueOf(-0.5)</code></li>
     * </ul>
     *
     * @return the reciprocal rational number
     * @throws ArithmeticException if this number is 0 (division by zero)
     */
    public Math_BigRational reciprocal() {
        return of(denominator, numerator);
    }

    /**
     * Returns the absolute value of this rational number.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(-2).abs()</code> returns
     * <code>BigRational.valueOf(2)</code></li>
     * <li><code>BigRational.valueOf(2).abs()</code> returns
     * <code>BigRational.valueOf(2)</code></li>
     * </ul>
     *
     * @return the absolute rational number (positive, or 0 if this rational is
     * 0)
     */
    public Math_BigRational abs() {
        return isPositive() ? this : negate();
    }

    /**
     * Returns the signum function of this rational number.
     *
     * @return -1, 0 or 1 as the value of this rational number is negative, zero
     * or positive.
     */
    public int signum() {
        return numerator.signum();
    }

    /**
     * Calculates the increment of this rational number (+ 1).
     *
     * <p>
     * This is functionally identical to <code>this.add(BigRational.ONE)</code>
     * but slightly faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @return the incremented rational number
     */
    public Math_BigRational increment() {
        return of(numerator.add(denominator), denominator);
    }

    /**
     * Calculates the decrement of this rational number (- 1).
     *
     * <p>
     * This is functionally identical to
     * <code>this.subtract(BigRational.ONE)</code> but slightly faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @return the decremented rational number
     */
    public Math_BigRational decrement() {
        return of(numerator.subtract(denominator), denominator);
    }

    /**
     * Calculates the addition (+) of this rational number and the specified
     * argument.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the rational number to add
     * @return the resulting rational number
     */
    public Math_BigRational add(Math_BigRational value) {
        if (denominator.equals(value.denominator)) {
            return of(numerator.add(value.numerator), denominator);
        }

        BigDecimal n = numerator.multiply(value.denominator).add(value.numerator.multiply(denominator));
        BigDecimal d = denominator.multiply(value.denominator);
        return of(n, d);
    }

    private Math_BigRational add(BigDecimal value) {
        return of(numerator.add(value.multiply(denominator)), denominator);
    }

    /**
     * Calculates the addition (+) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.add(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the {@link BigInteger} to add
     * @return the resulting rational number
     */
    public Math_BigRational add(BigInteger value) {
        if (value.equals(BigInteger.ZERO)) {
            return this;
        }
        return add(new BigDecimal(value));
    }

    /**
     * Calculates the addition (+) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.add(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the int value to add
     * @return the resulting rational number
     */
    public Math_BigRational add(int value) {
        if (value == 0) {
            return this;
        }
        return add(BigInteger.valueOf(value));
    }

    /**
     * Calculates the subtraction (-) of this rational number and the specified
     * argument.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the rational number to subtract
     * @return the resulting rational number
     */
    public Math_BigRational subtract(Math_BigRational value) {
        if (denominator.equals(value.denominator)) {
            return of(numerator.subtract(value.numerator), denominator);
        }

        BigDecimal n = numerator.multiply(value.denominator).subtract(value.numerator.multiply(denominator));
        BigDecimal d = denominator.multiply(value.denominator);
        return of(n, d);
    }

    private Math_BigRational subtract(BigDecimal value) {
        return of(numerator.subtract(value.multiply(denominator)), denominator);
    }

    /**
     * Calculates the subtraction (-) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.subtract(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the {@link BigInteger} to subtract
     * @return the resulting rational number
     */
    public Math_BigRational subtract(BigInteger value) {
        if (value.equals(BigInteger.ZERO)) {
            return this;
        }
        return subtract(new BigDecimal(value));
    }

    /**
     * Calculates the subtraction (-) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.subtract(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the int value to subtract
     * @return the resulting rational number
     */
    public Math_BigRational subtract(int value) {
        if (value == 0) {
            return this;
        }
        return subtract(BigInteger.valueOf(value));
    }

    /**
     * Calculates the multiplication (*) of this rational number and the
     * specified argument.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the rational number to multiply
     * @return the resulting rational number
     */
    public Math_BigRational multiply(Math_BigRational value) {
        if (isZero() || value.isZero()) {
            return ZERO;
        }
        if (equals(ONE)) {
            return value;
        }
        if (value.equals(ONE)) {
            return this;
        }

        BigDecimal n = numerator.multiply(value.numerator);
        BigDecimal d = denominator.multiply(value.denominator);
        return of(n, d);
    }

    // private, because we want to hide that we use BigDecimal internally
    private Math_BigRational multiply(BigDecimal value) {
        BigDecimal n = numerator.multiply(value);
        BigDecimal d = denominator;
        return of(n, d);
    }

    /**
     * Calculates the multiplication (*) of this rational number and the
     * specified argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.multiply(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the {@link BigInteger} to multiply
     * @return the resulting rational number
     */
    public Math_BigRational multiply(BigInteger value) {
        if (isZero() || value.signum() == 0) {
            return ZERO;
        }
        if (equals(ONE)) {
            return valueOf(value);
        }
        if (value.equals(BigInteger.ONE)) {
            return this;
        }

        return multiply(new BigDecimal(value));
    }

    /**
     * Calculates the multiplication (*) of this rational number and the
     * specified argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.multiply(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the int value to multiply
     * @return the resulting rational number
     */
    public Math_BigRational multiply(int value) {
        return multiply(BigInteger.valueOf(value));
    }

    /**
     * Calculates the division (/) of this rational number and the specified
     * argument.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the rational number to divide (0 is not allowed)
     * @return the resulting rational number
     * @throws ArithmeticException if the argument is 0 (division by zero)
     */
    public Math_BigRational divide(Math_BigRational value) {
        if (value.equals(ONE)) {
            return this;
        }

        BigDecimal n = numerator.multiply(value.denominator);
        BigDecimal d = denominator.multiply(value.numerator);
        return of(n, d);
    }

    private Math_BigRational divide(BigDecimal value) {
        BigDecimal n = numerator;
        BigDecimal d = denominator.multiply(value);
        return of(n, d);
    }

    /**
     * Calculates the division (/) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.divide(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the {@link BigInteger} to divide (0 is not allowed)
     * @return the resulting rational number
     * @throws ArithmeticException if the argument is 0 (division by zero)
     */
    public Math_BigRational divide(BigInteger value) {
        if (value.equals(BigInteger.ONE)) {
            return this;
        }

        return divide(new BigDecimal(value));
    }

    /**
     * Calculates the division (/) of this rational number and the specified
     * argument.
     *
     * <p>
     * This is functionally identical to
     * <code>this.divide(BigRational.valueOf(value))</code> but slightly
     * faster.</p>
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param value the int value to divide (0 is not allowed)
     * @return the resulting rational number
     * @throws ArithmeticException if the argument is 0 (division by zero)
     */
    public Math_BigRational divide(long value) {
        return divide(BigInteger.valueOf(value));
    }

    /**
     * Returns whether this rational number is zero.
     *
     * @return <code>true</code> if this rational number is zero (0),
     * <code>false</code> if it is not zero
     */
    public boolean isZero() {
        return numerator.signum() == 0;
    }

    private boolean isPositive() {
        return numerator.signum() > 0;
    }

    /**
     * Returns whether this rational number is an integer number without
     * fraction part.
     *
     * @return <code>true</code> if this rational number is an integer number,
     * <code>false</code> if it has a fraction part
     */
    public boolean isInteger() {
        return isIntegerInternal() || reduce().isIntegerInternal();
    }

    /**
     * Returns whether this rational number is an integer number without
     * fraction part.
     *
     * <p>
     * Will return <code>false</code> if this number is not reduced to the
     * integer representation yet (e.g. 4/4 or 4/2)</p>
     *
     * @return <code>true</code> if this rational number is an integer number,
     * <code>false</code> if it has a fraction part
     * @see #isInteger()
     */
    private boolean isIntegerInternal() {
        return denominator.compareTo(BigDecimal.ONE) == 0;
    }

    /**
     * Calculates this rational number to the power (x<sup>y</sup>) of the
     * specified argument.
     *
     * <p>
     * The result has no loss of precision.</p>
     *
     * @param exponent exponent to which this rational number is to be raised
     * @return the resulting rational number
     */
    public Math_BigRational pow(int exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if (exponent == 1) {
            return this;
        }

        final BigInteger n;
        final BigInteger d;
        if (exponent > 0) {
            n = numerator.toBigInteger().pow(exponent);
            d = denominator.toBigInteger().pow(exponent);
        } else {
            n = denominator.toBigInteger().pow(-exponent);
            d = numerator.toBigInteger().pow(-exponent);
        }
        return valueOf(n, d);
    }

    /**
     * Finds the minimum (smaller) of two rational numbers.
     *
     * @param value the rational number to compare with
     * @return the minimum rational number, either <code>this</code> or the
     * argument <code>value</code>
     */
    public Math_BigRational min(Math_BigRational value) {
        return compareTo(value) <= 0 ? this : value;
    }

    /**
     * Finds the maximum (larger) of two rational numbers.
     *
     * @param value the rational number to compare with
     * @return the minimum rational number, either <code>this</code> or the
     * argument <code>value</code>
     */
    public Math_BigRational max(Math_BigRational value) {
        return compareTo(value) >= 0 ? this : value;
    }

    /**
     * Returns a rational number with approximatively <code>this</code> value
     * and the specified precision.
     *
     * @param precision the precision (number of significant digits) of the
     * calculated result, or 0 for unlimited precision
     * @return the calculated rational number with the specified precision
     */
    public Math_BigRational withPrecision(int precision) {
        return valueOf(toBigDecimal(new MathContext(precision)));
    }

    /**
     * Returns this rational number as a double value.
     *
     * @return the double value
     */
    public double toDouble() {
        // TODO best accuracy or maybe bigDecimalValue().doubleValue() is better?
        return numerator.doubleValue() / denominator.doubleValue();
    }

    /**
     * Returns this rational number as a float value.
     *
     * @param oom The Order of Magnitude for the precision of the conversion.
     * @return the float value
     */
    public float toFloat(int oom) {
        // Sometimes this return NaN!
        float r = numerator.floatValue() / denominator.floatValue();
        if (Float.isNaN(r)) {
            return Float.parseFloat(toBigDecimal(oom).toString());
            //return Float.valueOf(x.toBigDecimal().toString());
        }
        if (Float.isInfinite(r)) {
            return Float.parseFloat(toBigDecimal(oom).toString());
        }
        return r;
    }

    /**
     * Returns this rational number as a {@link BigDecimal} rounded if 
     * necessary to {@code oom}.
     * 
     * @param oom The Order of Magnitude for the precision of the result.
     * @return the {@link BigDecimal} value rounded to {@code oom}.
     */
    public BigDecimal toBigDecimal(int oom) {
        return Math_BigDecimal.divide(numerator, denominator, oom, 
                    RoundingMode.HALF_UP);
    }

    /**
     * Returns this rational number as a {@link BigDecimal} with the precision
     * specified by the {@link MathContext}.
     *
     * @param mc the {@link MathContext} specifying the precision of the
     * calculated result
     * @return the {@link BigDecimal}
     */
    public BigDecimal toBigDecimal(MathContext mc) {
        return numerator.divide(denominator, mc);
    }

    @Override
    public int compareTo(Math_BigRational other) {
        if (this == other) {
            return 0;
        }
        return numerator.multiply(other.denominator).compareTo(denominator.multiply(other.numerator));
    }

    @Override
    public int hashCode() {
        if (isZero()) {
            return 0;
        }
        return numerator.hashCode() + denominator.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Math_BigRational)) {
            return false;
        }

        Math_BigRational other = (Math_BigRational) obj;
        if (!numerator.equals(other.numerator)) {
            return false;
        }
        return denominator.equals(other.denominator);
    }

    @Override
    public String toString() {
        if (isZero()) {
            return "0";
        }
        if (isIntegerInternal()) {
            return numerator.toString();
        }
        return toBigDecimal(Math_BigDecimal.FLOAT_MIN_VALUE_LSD).toString();
    }

    /**
     * Returns a plain string representation of this rational number without any
     * exponent.
     *
     * @return the plain string representation
     * @see BigDecimal#toPlainString()
     */
    public String toPlainString() {
        if (isZero()) {
            return "0";
        }
        if (isIntegerInternal()) {
            return numerator.toPlainString();
        }
        return toBigDecimal(Math_BigDecimal.FLOAT_MIN_VALUE_LSD).toPlainString();
    }

    /**
     * Returns the string representation of this rational number in the form
     * "numerator/denominator".
     *
     * <p>
     * The resulting string is a valid input of the {@link #valueOf(String)}
     * method.</p>
     *
     * <p>
     * Examples:</p>
     * <ul>
     * <li><code>BigRational.valueOf(0.5).toRationalString()</code> returns
     * <code>"1/2"</code></li>
     * <li><code>BigRational.valueOf(2).toRationalString()</code> returns
     * <code>"2"</code></li>
     * <li><code>BigRational.valueOf(4, 4).toRationalString()</code> returns
     * <code>"4/4"</code> (not reduced)</li>
     * </ul>
     *
     * @return the rational number string representation in the form
     * "numerator/denominator", or "0" if the rational number is 0.
     * @see #valueOf(String)
     * @see #valueOf(int, int)
     */
    public String toRationalString() {
        if (isZero()) {
            return "0";
        }
        if (isIntegerInternal()) {
            return numerator.toString();
        }
        return numerator + "/" + denominator;
    }

    /**
     * Returns the string representation of this rational number as integer and
     * fraction parts in the form "integerPart
     * fractionNominator/fractionDenominator".
     *
     * <p>
     * The integer part is omitted if it is 0 (when this absolute rational
     * number is smaller than 1).</p>
     * <p>
     * The fraction part is omitted it it is 0 (when this rational number is an
     * integer).</p>
     * <p>
     * If this rational number is 0, then "0" is returned.</p>
     *
     * <p>
     * Example: <code>BigRational.valueOf(3.5).toIntegerRationalString()</code>
     * returns <code>"3 1/2"</code>.</p>
     *
     * @return the integer and fraction rational string representation
     * @see #valueOf(int, int, int)
     */
    public String toIntegerRationalString() {
        BigDecimal fractionNumerator = numerator.remainder(denominator);
        BigDecimal integerNumerator = numerator.subtract(fractionNumerator);
        BigDecimal integerPart = integerNumerator.divide(denominator);

        StringBuilder result = new StringBuilder();
        if (integerPart.signum() != 0) {
            result.append(integerPart);
        }
        if (fractionNumerator.signum() != 0) {
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(fractionNumerator.abs());
            result.append('/');
            result.append(denominator);
        }
        if (result.length() == 0) {
            result.append('0');
        }

        return result.toString();
    }
    
    /**
     * @return A String representation of {@code v} in 10 characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public String getStringValue() {
        return getStringValue(10);
    }
    
    /**
     * @param n The length of the String returned. This must be greater than or
     * equal to 10.
     * @return A String representation of {@code v} in n characters. This may
     * involve rounding in which case {@link RoundingMode#HALF_UP} is used. If
     * the default number has fewer than 10 characters it is padded with spaces.
     * The returned String is always of length 10.
     */
    public String getStringValue(int n) {
        return Math_BigDecimal.getStringValue(toBigDecimal(Math_BigDecimal.FLOAT_MIN_VALUE_LSD));
    }

    /**
     * Creates a rational number of the specified int value.
     *
     * @param value the int value
     * @return the rational number
     */
    public static Math_BigRational valueOf(int value) {
        if (value == 0) {
            return ZERO;
        }
        if (value == 1) {
            return ONE;
        }
        return new Math_BigRational(value);
    }

    /**
     * Creates a rational number of the specified numerator/denominator int
     * values.
     *
     * @param numerator the numerator int value
     * @param denominator the denominator int value (0 not allowed)
     * @return the rational number
     * @throws ArithmeticException if the denominator is 0 (division by zero)
     */
    public static Math_BigRational valueOf(int numerator, int denominator) {
        return of(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator));
    }

    /**
     * Creates a rational number of the specified integer and fraction parts.
     *
     * <p>
     * Useful to create numbers like 3 1/2 (= three and a half = 3.5) by calling
     * <code>BigRational.valueOf(3, 1, 2)</code>.</p>
     * <p>
     * To create a negative rational only the integer part argument is allowed
     * to be negative: to create -3 1/2 (= minus three and a half = -3.5) call
     * <code>BigRational.valueOf(-3, 1, 2)</code>.</p>
     *
     * @param integer the integer part int value
     * @param fractionNumerator the fraction part numerator int value (negative
     * not allowed)
     * @param fractionDenominator the fraction part denominator int value (0 or
     * negative not allowed)
     * @return the rational number
     * @throws ArithmeticException if the fraction part denominator is 0
     * (division by zero), or if the fraction part numerator or denominator is
     * negative
     */
    public static Math_BigRational valueOf(int integer, int fractionNumerator, int fractionDenominator) {
        if (fractionNumerator < 0 || fractionDenominator < 0) {
            throw new ArithmeticException("Negative value");
        }

        Math_BigRational integerPart = valueOf(integer);
        Math_BigRational fractionPart = valueOf(fractionNumerator, fractionDenominator);
        return integerPart.isPositive() ? integerPart.add(fractionPart) : integerPart.subtract(fractionPart);
    }

    /**
     * Creates a rational number of the specified numerator/denominator
     * BigInteger values.
     *
     * @param numerator the numerator {@link BigInteger} value
     * @param denominator the denominator {@link BigInteger} value (0 not
     * allowed)
     * @return the rational number
     * @throws ArithmeticException if the denominator is 0 (division by zero)
     */
    public static Math_BigRational valueOf(BigInteger numerator, BigInteger denominator) {
        return of(new BigDecimal(numerator), new BigDecimal(denominator));
    }

    /**
     * Creates a rational number of the specified {@link BigInteger} value.
     *
     * @param value the {@link BigInteger} value
     * @return the rational number
     */
    public static Math_BigRational valueOf(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) == 0) {
            return ZERO;
        }
        if (value.compareTo(BigInteger.ONE) == 0) {
            return ONE;
        }
        return valueOf(value, BigInteger.ONE);
    }

    /**
     * Creates a rational number of the specified double value.
     *
     * @param value the double value
     * @return the rational number
     * @throws NumberFormatException if the double value is Infinite or NaN.
     */
    public static Math_BigRational valueOf(double value) {
        if (value == 0.0) {
            return ZERO;
        }
        if (value == 1.0) {
            return ONE;
        }
        if (Double.isInfinite(value)) {
            throw new NumberFormatException("Infinite");
        }
        if (Double.isNaN(value)) {
            throw new NumberFormatException("NaN");
        }
        return valueOf(new BigDecimal(String.valueOf(value)));
    }

    /**
     * Creates a rational number of the specified {@link BigDecimal} value.
     *
     * @param value the double value
     * @return the rational number
     */
    public static Math_BigRational valueOf(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return ZERO;
        }
        if (value.compareTo(BigDecimal.ONE) == 0) {
            return ONE;
        }

        int scale = value.scale();
        if (scale == 0) {
            return new Math_BigRational(value, BigDecimal.ONE);
        } else if (scale < 0) {
            BigDecimal n = new BigDecimal(value.unscaledValue()).multiply(BigDecimal.ONE.movePointLeft(value.scale()));
            return new Math_BigRational(n, BigDecimal.ONE);
        } else {
            BigDecimal n = new BigDecimal(value.unscaledValue());
            BigDecimal d = BigDecimal.ONE.movePointRight(value.scale());
            return new Math_BigRational(n, d);
        }
    }

    /**
     * Creates a rational number of the specified string representation.
     *
     * <p>
     * The accepted string representations are:</p>
     * <ul>
     * <li>Output of {@link Math_BigRational#toString()} :
     * "integerPart.fractionPart"</li>
     * <li>Output of {@link Math_BigRational#toRationalString()} :
     * "numerator/denominator"</li>
     * <li>Output of <code>toString()</code> of
     * {@link BigDecimal}, {@link BigInteger}, {@link Integer}, ...</li>
     * <li>Output of <code>toString()</code> of {@link Double}, {@link Float} -
     * except "Infinity", "-Infinity" and "NaN"</li>
     * </ul>
     *
     * @param string the string representation to convert
     * @return the rational number
     * @throws ArithmeticException if the denominator is 0 (division by zero)
     */
    public static Math_BigRational valueOf(String string) {
        String[] strings = string.split("/");
        Math_BigRational result = valueOfSimple(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            result = result.divide(valueOfSimple(strings[i]));
        }
        return result;
    }

    private static Math_BigRational valueOfSimple(String string) {
        return valueOf(new BigDecimal(string));
    }

    /**
     *
     * @param positive If true then {@link Math_BigRational} created is
     * positive.
     * @param integerPart The whole integer part of the {@link Math_BigRational}
     * created.
     * @param fractionPart The fractional part of the {@link Math_BigRational}
     * created.
     * @param fractionRepeatPart The fractionRepeatPart.
     * @param exponentPart The exponentPart.
     * @return {@link Math_BigRational} created.
     */
    public static Math_BigRational valueOf(boolean positive, String integerPart,
            String fractionPart, String fractionRepeatPart,
            String exponentPart) {
        Math_BigRational result = ZERO;
        if (fractionRepeatPart != null && fractionRepeatPart.length() > 0) {
            BigInteger lotsOfNines = BigInteger.TEN.pow(fractionRepeatPart.length()).subtract(BigInteger.ONE);
            result = valueOf(new BigInteger(fractionRepeatPart), lotsOfNines);
        }
        if (fractionPart != null && fractionPart.length() > 0) {
            result = result.add(valueOf(new BigInteger(fractionPart)));
            result = result.divide(BigInteger.TEN.pow(fractionPart.length()));
        }
        if (integerPart != null && integerPart.length() > 0) {
            result = result.add(new BigInteger(integerPart));
        }
        if (exponentPart != null && exponentPart.length() > 0) {
            int exponent = Integer.parseInt(exponentPart);
            BigInteger powerOfTen = BigInteger.TEN.pow(Math.abs(exponent));
            result = exponent >= 0 ? result.multiply(powerOfTen) : result.divide(powerOfTen);
        }
        if (!positive) {
            result = result.negate();
        }
        return result;
    }

    /**
     * Creates a rational number of the specified numerator/denominator
     * BigDecimal values.
     *
     * @param numerator the numerator {@link BigDecimal} value
     * @param denominator the denominator {@link BigDecimal} value (0 not
     * allowed)
     * @return the rational number
     * @throws ArithmeticException if the denominator is 0 (division by zero)
     */
    public static Math_BigRational valueOf(BigDecimal numerator, BigDecimal denominator) {
        return valueOf(numerator).divide(valueOf(denominator));
    }

    private static Math_BigRational of(BigDecimal numerator, BigDecimal denominator) {
        if (numerator.signum() == 0 && denominator.signum() != 0) {
            return ZERO;
        }
        if (numerator.compareTo(BigDecimal.ONE) == 0 && denominator.compareTo(BigDecimal.ONE) == 0) {
            return ONE;
        }
        return new Math_BigRational(numerator, denominator);
    }

    /**
     * Returns the smallest of the specified rational numbers.
     *
     * @param values the rational numbers to compare
     * @return the smallest rational number, 0 if no numbers are specified
     */
    public static Math_BigRational min(Math_BigRational... values) {
        if (values.length == 0) {
            return Math_BigRational.ZERO;
        }
        Math_BigRational result = values[0];
        for (int i = 1; i < values.length; i++) {
            result = result.min(values[i]);
        }
        return result;
    }

    /**
     * Returns the largest of the specified rational numbers.
     *
     * @param values the rational numbers to compare
     * @return the largest rational number, 0 if no numbers are specified
     * @see #max(Math_BigRational)
     */
    public static Math_BigRational max(Math_BigRational... values) {
        if (values.length == 0) {
            return Math_BigRational.ZERO;
        }
        Math_BigRational r = values[0];
        for (int i = 1; i < values.length; i++) {
            r = r.max(values[i]);
        }
        return r;
    }

    @Override
    public int intValue() {
        return toBigDecimal(0).intValue();
    }

    @Override
    public long longValue() {
        return toBigDecimal(0).longValue();
    }

    @Override
    public float floatValue() {
        return toBigDecimal(Math_BigDecimal.FLOAT_MIN_VALUE_LSD).floatValue();
    }

    @Override
    public double doubleValue() {
        return toBigDecimal(Math_BigDecimal.DOUBLE_MIN_VALUE_LSD).doubleValue();
    }

    /**
     * Calculate and return the common factor of two rational numbers.
     *
     * @param x One number.
     * @param y Another number.
     * @return The common factor of the two numbers x and y.
     */
    public static Math_BigRational getCommonFactor(Math_BigRational x, Math_BigRational y) {
        Math_BigRational xr = x.reduce();
        Math_BigRational yr = y.reduce();
        BigInteger xrn = xr.getNumeratorBigInteger();
        BigInteger yrn = yr.getNumeratorBigInteger();
        BigInteger xrd = xr.getDenominatorBigInteger();
        BigInteger yrd = yr.getDenominatorBigInteger();
        BigInteger gcdn = xrn.gcd(yrn);
        BigInteger gcdd = xrd.gcd(yrd);
        return Math_BigRational.valueOf(gcdn, gcdd);
    }
    
    /**
     * @return If this has a fractional part, then it returns just the whole 
     * integer number part.
     */
    public BigInteger floor() {
        BigDecimal fractionNumerator = numerator.remainder(denominator);
        BigDecimal integerNumerator = numerator.subtract(fractionNumerator);
        BigInteger r = integerNumerator.divide(denominator).toBigInteger();
        if (fractionNumerator.compareTo(BigDecimal.ZERO) == -1) {
            return r.subtract(BigInteger.ONE);
        }
        return r;
    }
    
    /**
     * @return If this has a fractional part, then it returns the whole integer 
     * number greater than it.
     */
    public BigInteger ceil() {
        BigDecimal fractionNumerator = numerator.remainder(denominator);
        BigDecimal integerNumerator = numerator.subtract(fractionNumerator);
        BigInteger r = integerNumerator.divide(denominator).toBigInteger();
        if (fractionNumerator.compareTo(BigDecimal.ZERO) == 1) {
            return r.add(BigInteger.ONE);
        }
        return r;
    }
}
