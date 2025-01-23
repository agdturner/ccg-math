/*
 * Copyright 2019 Andy Turner, University of Leeds.
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
package uk.ac.leeds.ccg.math.arithmetic;

//import ch.obermuhlner.math.big.BigRational;
import ch.obermuhlner.math.big.BigRational;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Generic Math
 *
 * Methods for adding two Numbers and testing if Numbers are in the range of
 * other Numbers.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_Math {

    /**
     * Create a new instance;
     */
    public Math_Math(){}
    
    /**
     * Stores the second largest finite double. The largest is:
     *
     * new BigDecimal(Double.MAX_VALUE)
     *
     * Values greater than this are best not stored as doubles and nearby values
     * may not necessarily be stored any differently to this - if stored as a
     * double - due to the way floating point numbers operate with narrowing
     * conversions.
     */
    public static final BigDecimal DOUBLE_MAXVALUE = new BigDecimal(
            String.valueOf(Double.MAX_VALUE));
    //public static final BigDecimal DOUBLE_MAXVALUE = new BigDecimal(
    //        Double.toString(Double.MAX_VALUE));

    /**
     * Stores the third largest finite double.
     */
    public static final BigDecimal DOUBLE_MAXVALUE_PEN = new BigDecimal(
            String.valueOf(Math.nextDown(Double.MAX_VALUE)));

    /**
     * Stores a negative finite double with the second largest magnitude. The
     * largest is:
     *
     * new BigDecimal(-Double.MAX_VALUE)
     *
     * Values less than this are best not stored as doubles and nearby values
     * may not necessarily be stored any differently to this - if stored as a
     * double - due to the way floating point numbers operate with narrowing
     * conversions.
     */
    public static final BigDecimal DOUBLE_MAXVALUE_NEG = DOUBLE_MAXVALUE.negate();

    /**
     * Stores a negative finite double with the third largest magnitude.
     */
    public static final BigDecimal DOUBLE_MINVALUE_PEN = new BigDecimal(
            String.valueOf(Math.nextUp(-Double.MAX_VALUE)));

    /**
     * Used for testing. If values are greater than this then storing them as
     * Float values is dubious. The toString() conversion is necessary otherwise
     * the number is too large.
     */
    public static final BigDecimal FLOAT_MAXVALUE = new BigDecimal(
            String.valueOf(Float.MAX_VALUE));
    //public static final BigDecimal FLOAT_MAXVALUE = new BigDecimal(
    //        Float.toString(Float.MAX_VALUE));

    /**
     * Used for testing. If values are greater than this then they cannot be
     * stored as Shorts.
     */
    public static final BigInteger SHORT_MAXVALUE = new BigInteger(
            Short.toString(Short.MAX_VALUE));
    /**
     * Used for testing. If values are less than this then storing them as Float
     * values is dubious.
     */
    public static final BigDecimal FLOAT_MAXVALUE_NEG = FLOAT_MAXVALUE.negate();

    /**
     * For adding two generic Numbers x and y of the same type. The numbers x
     * and y are converted as appropriate into BigInteger or BigDecimal (for
     * integer type or floating point numbers respectively - if they are not
     * already of these types). In the general case, the converted numbers are
     * then added and the result cast into the type T and returned. If the
     * result of adding the two numbers cannot be stored exactly in the case of
     * integer type numerical addition then an ArithmeticException is thrown. If
     * the result of adding the two numbers is beyond the range in the type of
     * numbers being added then an ArithmeticExcpetion is thrown unless
     * infinities are involved (except when the two numbers being added are
     * opposite infinities - in which case an ArithmeticException is thrown). If
     * one number is positive or negative infinity then that infinity is
     * returned. In cases where the values being added are represented in
     * floating point precision the results are a consequence of a narrowing
     * digital conversion. (N.B. If desirable then if numbers do not increase or
     * decrease as expected then there could be an implementation that throws an
     * ArithmeticException in cases where a narrowing digital conversion does
     * not result in a larger or smaller number as is typically mathematically
     * expected in a sum.
     *
     * @param <T> The type T of the Numbers x and y.
     * @param x A Number to add.
     * @param y A Number to add.
     * @return The result of adding x and y expressed in the same type as x and
     * y.
     */
    public static <T extends Number> T add(T x, T y) {
        if (x == null || y == null) {
            return null;
        }
        if (x instanceof BigRational) {
            @SuppressWarnings("unchecked")
            T r = (T) ((BigRational) x).add((BigRational) y);
            return r;
        } else {
            if (x instanceof BigDecimal xbd) {
                @SuppressWarnings("unchecked")
                T r = (T) xbd.add((BigDecimal) y);
                return r;
            } else if (x instanceof BigInteger xbi) {
                @SuppressWarnings("unchecked")
                T r = (T) xbi.add((BigInteger) y);
                return r;
            } else {
                if (x instanceof Double xd) {
                    // Deal with special cases.
                    if (xd.isNaN()) {
                        @SuppressWarnings("unchecked")
                        T r = (T) xd;
                        return r;
                    }
                    Double y0 = (Double) y;
                    if (y0.isNaN()) {
                        @SuppressWarnings("unchecked")
                        T r = (T) y0;
                        return r;
                    }
                    if (xd == Double.POSITIVE_INFINITY) {
                        if (y0 == Double.NEGATIVE_INFINITY) {
                            throw new ArithmeticException("Attempting to add "
                                    + "positive and negative infinity.");
                            //return (T) (Double) Double.NaN;
                        } else {
                            @SuppressWarnings("unchecked")
                            T r = (T) xd;
                            return r;
                        }
                    } else if (xd == Double.NEGATIVE_INFINITY) {
                        if (y0 == Double.POSITIVE_INFINITY) {
                            throw new ArithmeticException("Attempting to add "
                                    + "positive and negative infinity.");
                            //return (T) (Double) Double.NaN;
                        } else {
                            @SuppressWarnings("unchecked")
                            T r = (T) xd;
                            return r;
                        }
                    } else {
                        BigDecimal x1 = BigDecimal.valueOf(xd);
                        BigDecimal y1 = BigDecimal.valueOf(y0);
                        BigDecimal sum = x1.add(y1);
                        testDouble(sum);
                        @SuppressWarnings("unchecked")
                        T r = (T) (Double) sum.doubleValue();
                        return r;
                    }
                } else if (x instanceof Float xf) {
                    // Deal with special cases.
                    if (xf.isNaN()) {
                        @SuppressWarnings("unchecked")
                        T r = (T) xf;
                        return r;
                    }
                    Float y0 = (Float) y;
                    if (y0.isNaN()) {
                        @SuppressWarnings("unchecked")
                        T r = (T) y0;
                        return r;
                    }
                    if (xf == Float.POSITIVE_INFINITY) {
                        if (y0 == Float.NEGATIVE_INFINITY) {
                            throw new ArithmeticException("Attempting to add "
                                    + "positive and negative infinity.");
                            //return (T) (Float) Float.NaN;
                        } else {
                            @SuppressWarnings("unchecked")
                            T r = (T) xf;
                            return r;
                        }
                    } else if (xf == Float.NEGATIVE_INFINITY) {
                        if (y0 == Float.POSITIVE_INFINITY) {
                            throw new ArithmeticException("Attempting to add "
                                    + "positive and negative infinity.");
                            //return (T) (Float) Float.NaN;
                        } else {
                            @SuppressWarnings("unchecked")
                            T r = (T) xf;
                            return r;
                        }
                    } else {
                        if (y0.isInfinite()) {
                            throw new ArithmeticException("Attempting to add "
                                    + "to negative infinity.");
                        }
                        BigDecimal x1 = BigDecimal.valueOf(xf);
                        BigDecimal y1 = BigDecimal.valueOf(y0);
                        BigDecimal sum = x1.add(y1);
                        testFloat(sum);
                        @SuppressWarnings("unchecked")
                        T r = (T) (Float) sum.floatValue();
                        return r;
                    }
                } else if (x instanceof Long xl) {
                    BigInteger x0 = BigInteger.valueOf(xl);
                    BigInteger y0 = BigInteger.valueOf((Long) y);
                    @SuppressWarnings("unchecked")
                    T r = (T) (Long) x0.add(y0).longValueExact();
                    return r;
                } else if (x instanceof Integer xi) {
                    BigInteger x0 = BigInteger.valueOf(xi);
                    BigInteger y0 = BigInteger.valueOf((Integer) y);
                    @SuppressWarnings("unchecked")
                    T r = (T) (Integer) x0.add(y0).intValueExact();
                    return r;
                } else if (x instanceof Short xs) {
                    BigInteger x0 = BigInteger.valueOf(xs);
                    BigInteger y0 = BigInteger.valueOf((Short) y);
                    @SuppressWarnings("unchecked")
                    T r = (T) (Short) x0.add(y0).shortValueExact();
                    return r;
                } else if (x instanceof Byte xb) {
                    BigInteger x0 = BigInteger.valueOf(xb);
                    BigInteger y0 = BigInteger.valueOf((Byte) y);
                    @SuppressWarnings("unchecked")
                    T r = (T) (Byte) x0.add(y0).byteValueExact();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T=" + x.getClass()
                            + " is not supported in Generic_Collections.add(T, T)");
                }
            }
        }
    }

    /**
     * For adding two generic Numbers of the same type. The types of number are
     * converted as appropriate into BigDecimals or BigIntegers (for integer
     * type numbers and floating point number respectively - if they are not
     * already of these types) which are then added and the result cast into the
     * type T. If the result of adding the two numbers cannot be stored exactly
     * in the case of integer type numerical addition then an
     * ArithmeticException is thrown.
     *
     * If the result of adding the two numbers is beyond the range in T1 then an
     * ArithmeticExcpetion is thrown unless infinities are involved (except when
     * the two numbers being added are opposite infinities - in which case an
     * ArithmeticException is thrown). If one number is positive or negative
     * infinity then that infinity is returned. In cases where the values being
     * added are represented in floating point precision the results are a
     * consequence of a narrowing digital conversion. (N.B. If desirable then if
     * numbers do not increase or decrease as expected then there could be an
     * implementation that throws an ArithmeticException in cases where a
     * narrowing digital conversion does not result in a larger or smaller
     * number as is typically mathematically expected in a sum.
     *
     * @param <T1> The type of the Number x and the result returned.
     * @param <T2> The type of the Number y.
     * @param x A Number to add.
     * @param y A Number to add.
     * @return The result of adding x and y expressed in the same type as x.
     */
    public static <T1 extends Number, T2 extends Number> T1 add2(T1 x, T2 y) {
        if (x == null || y == null) {
            return null;
        }
        if (x instanceof BigDecimal) {
            BigDecimal x0 = (BigDecimal) x;
            if (y instanceof BigDecimal) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add((BigDecimal) y);
                    return r;
            } else if (y instanceof BigInteger) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((BigInteger) y));
                    return r;
            } else if (y instanceof Double) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Double) y));
                    return r;
            } else if (y instanceof Float) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Float) y));
                    return r;
            } else if (y instanceof Long) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Long) y));
                    return r;
            } else if (y instanceof Integer) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Integer) y));
                    return r;
            } else if (y instanceof Short) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Short) y));
                    return r;
            } else if (y instanceof Byte) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(new BigDecimal((Byte) y));
                    return r;
            } else {
                throw new IllegalArgumentException("Type T2=" + y.getClass()
                        + " is not supported in Generic_Math.add(T1, T2)");
            }
        } else if (x instanceof BigInteger) {
            BigInteger x0 = (BigInteger) x;
            if (y instanceof BigDecimal) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(((BigDecimal) y).toBigInteger());
                    return r;
            } else if (y instanceof BigInteger) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add((BigInteger) y);
                    return r;
            } else if (y instanceof Double) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf(((Double) y).longValue()));
                    return r;
            } else if (y instanceof Float) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf(((Float) y).longValue()));
                    return r;
            } else if (y instanceof Long) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf((Long) y));
                    return r;
            } else if (y instanceof Integer) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf((Integer) y));
                    return r;
            } else if (y instanceof Short) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf((Short) y));
                    return r;
            } else if (y instanceof Byte) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0.add(BigInteger.valueOf((Byte) y));
                    return r;
            } else {
                throw new IllegalArgumentException("Type T2=" + y.getClass()
                        + " is not supported in Generic_Math.add(T1, T2)");
            }
        } else if (x instanceof Double) {
            Double x0 = (Double) x;
            // Deal with special cases.
            if (x0.isNaN()) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
            }
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) Double.NaN;
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) Double.NaN;
                    return r;
                }
            }
            if (x0 == Double.POSITIVE_INFINITY) {
                if (y instanceof Double) {
                    Double y0 = (Double) y;
                    if (y0 == Double.NEGATIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Double) Double.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else if (y instanceof Float) {
                    Float y0 = (Float) y;
                    if (y0 == Float.NEGATIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Double) Double.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                }
            } else if (x0 == Double.NEGATIVE_INFINITY) {
                if (y instanceof Double) {
                    Double y0 = (Double) y;
                    if (y0 == Double.POSITIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Double) Double.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else if (y instanceof Float) {
                    Float y0 = (Float) y;
                    if (y0 == Float.POSITIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Double) Double.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                }
            } else {
                BigDecimal x1 = BigDecimal.valueOf(x0);
                if (y instanceof BigDecimal) {
                    BigDecimal sum = x1.add((BigDecimal) y);
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof BigInteger) {
                    BigDecimal sum = x1.add(new BigDecimal((BigInteger) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Double) {
                    BigDecimal sum = x1.add(new BigDecimal((Double) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Float) {
                    BigDecimal sum = x1.add(new BigDecimal((Float) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Long) {
                    BigDecimal sum = x1.add(new BigDecimal((Long) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Integer) {
                    BigDecimal sum = x1.add(new BigDecimal((Integer) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Short) {
                    BigDecimal sum = x1.add(new BigDecimal((Short) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else if (y instanceof Byte) {
                    BigDecimal sum = x1.add(new BigDecimal((Byte) y));
                    testDouble(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Double) sum.doubleValue();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else if (x instanceof Float) {
            Float x0 = (Float) x;
            // Deal with special cases.
            if (x0.isNaN()) {
                @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
            }
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) Float.NaN;
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) Float.NaN;
                    return r;
                }
            }
            if (x0 == Float.POSITIVE_INFINITY) {
                if (y instanceof Double) {
                    Double y0 = (Double) y;
                    if (y0 == Double.NEGATIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Float) Float.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else if (y instanceof Float) {
                    Float y0 = (Float) y;
                    if (y0 == Float.NEGATIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Float) Float.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                }
            } else if (x0 == Float.NEGATIVE_INFINITY) {
                if (y instanceof Double) {
                    Double y0 = (Double) y;
                    if (y0 == Double.POSITIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Float) Float.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else if (y instanceof Float) {
                    Float y0 = (Float) y;
                    if (y0 == Float.POSITIVE_INFINITY) {
                        throw new ArithmeticException("Attempting to add "
                                + "positive and negative infinity.");
                        //return (T1) (Float) Float.NaN;
                    } else {
                        @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                    }
                } else {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) x0;
                    return r;
                }
            } else {
                BigDecimal x1 = BigDecimal.valueOf(x0);
                if (y instanceof BigDecimal) {
                    BigDecimal sum = x1.add((BigDecimal) y);
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof BigInteger) {
                    BigDecimal sum = x1.add(new BigDecimal((BigInteger) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Double) {
                    BigDecimal sum = x1.add(new BigDecimal((Double) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Float) {
                    BigDecimal sum = x1.add(new BigDecimal((Float) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Long) {
                    BigDecimal sum = x1.add(new BigDecimal((Long) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Integer) {
                    BigDecimal sum = x1.add(new BigDecimal((Integer) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Short) {
                    BigDecimal sum = x1.add(new BigDecimal((Short) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else if (y instanceof Byte) {
                    BigDecimal sum = x1.add(new BigDecimal((Byte) y));
                    testFloat(sum);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Float) sum.floatValue();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else if (x instanceof Long) {
            Long x0 = (Long) x;
            // Deal with special cases.
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Long and T2 is "
                            + "Double and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add((new BigDecimal((Double) y))
                            .toBigInteger()).longValueExact();
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Long and T2 is "
                            + "Float and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add((new BigDecimal((Float) y))
                            .toBigInteger()).longValueExact();
                    return r;
                }
            } else {
                BigInteger x1 = BigInteger.valueOf(x0);
                if (y instanceof BigDecimal) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add(((BigDecimal) y).toBigInteger())
                            .longValueExact();
                    return r;
                } else if (y instanceof BigInteger) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add((BigInteger) y).longValueExact();
                    return r;
                } else if (y instanceof Long) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add(BigInteger.valueOf((Long) y))
                            .longValueExact();
                    return r;
                } else if (y instanceof Integer) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add(BigInteger.valueOf((Integer) y))
                            .longValueExact();
                    return r;
                } else if (y instanceof Short) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add(BigInteger.valueOf((Short) y))
                            .longValueExact();
                    return r;
                } else if (y instanceof Byte) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Long) x1.add(BigInteger.valueOf((Byte) y))
                            .longValueExact();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else if (x instanceof Integer) {
            Integer x0 = (Integer) x;
            // Deal with special cases.
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Integer and T2 is"
                            + " Double and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add((new BigDecimal((Double) y))
                            .toBigInteger()).intValueExact();
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Integer and T2 is"
                            + " Float and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add((new BigDecimal((Float) y))
                            .toBigInteger()).intValueExact();
                    return r;
                }
            } else {
                BigInteger x1 = BigInteger.valueOf(x0);
                if (y instanceof BigDecimal) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add(((BigDecimal) y).
                            toBigInteger()).intValueExact();
                    return r;
                } else if (y instanceof BigInteger) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add((BigInteger) y).intValueExact();
                    return r;
                } else if (y instanceof Long) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add(BigInteger.valueOf((Long) y))
                            .intValueExact();
                    return r;
                } else if (y instanceof Integer) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add(BigInteger.valueOf((Integer) y))
                            .intValueExact();
                    return r;
                } else if (y instanceof Short) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add(BigInteger.valueOf((Short) y))
                            .intValueExact();
                    return r;
                } else if (y instanceof Byte) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Integer) x1.add(BigInteger.valueOf((Byte) y))
                            .intValueExact();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else if (x instanceof Short) {
            Short x0 = (Short) x;
            // Deal with special cases.
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Short and T2 is"
                            + " Double and NaN in Generic_Math.add(T1, T2)");
                } else if (y instanceof Double) {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add((new BigDecimal((Double) y))
                            .toBigInteger()).shortValueExact();
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Short and T2 is"
                            + " Float and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add((new BigDecimal((Float) y))
                            .toBigInteger()).shortValueExact();
                    return r;
                }
            } else {
                BigInteger x1 = BigInteger.valueOf(x0);
                if (y instanceof BigDecimal) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add(((BigDecimal) y).toBigInteger())
                            .shortValueExact();
                    return r;
                } else if (y instanceof BigInteger) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add((BigInteger) y).shortValueExact();
                    return r;
                } else if (y instanceof Long) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add(BigInteger.valueOf((Long) y))
                            .shortValueExact();
                    return r;
                } else if (y instanceof Integer) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add(BigInteger.valueOf((Integer) y))
                            .shortValueExact();
                    return r;
                } else if (y instanceof Short) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add(BigInteger.valueOf((Short) y))
                            .shortValueExact();
                    return r;
                } else if (y instanceof Byte) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Short) x1.add(BigInteger.valueOf((Byte) y))
                            .shortValueExact();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else if (x instanceof Byte) {
            Byte x0 = (Byte) x;
            // Deal with special cases.
            if (y instanceof Double) {
                Double y0 = (Double) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Byte and T2 is"
                            + " Double and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add((new BigDecimal((Double) y))
                            .toBigInteger()).byteValueExact();
                    return r;
                }
            } else if (y instanceof Float) {
                Float y0 = (Float) y;
                if (y0.isNaN()) {
                    throw new ArithmeticException("T1 is type Byte and T2 is"
                            + " Float and NaN in Generic_Math.add(T1, T2)");
                } else {
                    BigInteger x1 = BigInteger.valueOf(x0);
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add((new BigDecimal((Float) y))
                            .toBigInteger()).byteValueExact();
                    return r;
                }
            } else {
                BigInteger x1 = BigInteger.valueOf(x0);
                if (y instanceof BigDecimal) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add(((BigDecimal) y).toBigInteger())
                            .byteValueExact();
                    return r;
                } else if (y instanceof BigInteger) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add((BigInteger) y).byteValueExact();
                    return r;
                } else if (y instanceof Long) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add(BigInteger.valueOf((Long) y))
                            .byteValueExact();
                    return r;
                } else if (y instanceof Integer) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add(BigInteger.valueOf((Integer) y))
                            .byteValueExact();
                    return r;
                } else if (y instanceof Short) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add(BigInteger.valueOf((Short) y))
                            .byteValueExact();
                    return r;
                } else if (y instanceof Byte) {
                    @SuppressWarnings("unchecked")
                    T1 r = (T1) (Byte) x1.add(BigInteger.valueOf((Byte) y))
                            .byteValueExact();
                    return r;
                } else {
                    throw new IllegalArgumentException("Type T2=" + y.getClass()
                            + " is not supported in Generic_Math.add(T1, T2)");
                }
            }
        } else {
            throw new IllegalArgumentException("Type T=" + x.getClass()
                    + " is not supported in Generic_Collections.add(T, T)");
        }
        // This line should never be reached!
        return null;
    }

    /**
     * @param x Value to test.
     * @throws ArithmeticException if x is greater than {@link #DOUBLE_MAXVALUE}
     * or less than {@link #DOUBLE_MAXVALUE_NEG}.
     */
    public static void testDouble(BigDecimal x) {
        if (x.compareTo(DOUBLE_MAXVALUE) == 1) {
            throw new ArithmeticException("x " + x.toString() + " is greater "
                    + "than Double.Max_Value.");
        }
        if (x.compareTo(DOUBLE_MAXVALUE_NEG) == -1) {
            throw new ArithmeticException("x " + x.toString() + " is less "
                    + "than -Double.Max_Value.");
        }
    }

    /**
     * @param x Value to test.
     * @throws ArithmeticException if x is greater than {@link #FLOAT_MAXVALUE}
     * or less than {@link #FLOAT_MAXVALUE_NEG}.
     */
    public static void testFloat(BigDecimal x) {
        if (x.compareTo(FLOAT_MAXVALUE) == 1) {
            throw new ArithmeticException("x " + x.toString() + " is greater "
                    + "than Float.Max_Value.");
        }
        if (x.compareTo(FLOAT_MAXVALUE_NEG) == -1) {
            throw new ArithmeticException("x " + x.toString() + " is less "
                    + "than -Float.Max_Value.");
        }
    }

    /**
     * Tests if x can be represented within epsilon as a double. Choose epsilon
     * equal to BigDecimal.ZERO to test 100% accuracy.
     *
     * @param x Number to test.
     * @param epsilon The allowed range either side of x for the double
     * representation.
     * @return -1 if x cannot be represented as a double within epsilon and the
     * nearest representation is less than x; 1 if x cannot be represented as a
     * double within epsilon and the nearest representation is greater than x; 0
     * otherwise.
     */
    public static int testDouble(BigDecimal x, BigDecimal epsilon) {
        double xd = x.doubleValue();
        //double xd = Double.valueOf(x.toString());
        //System.out.println(xd);
        //System.out.println(Double.toString(xd));
        /**
         * Simply using:
         *
         * BigDecimal xToCompare = new BigDecimal(xd);
         *
         * Fails!
         *
         * Precision needs to be handled explicitly.
         */
        BigDecimal xToCompare = new BigDecimal(Double.toString(xd),
                getMathContextForComparison(x));
        return compare(x, xToCompare, epsilon);
    }

    /**
     * Compares x and xd. If the difference is greater than epsilon then -1 is
     * returned if x is less than xd.subtract(epsilon), 1 is returned if x is
     * greater than xd.add(epsilon); 0 otherwise (i.e. x and xd are within
     * epsilon).
     *
     * @param x A number to compare.
     * @param xd A number to compare.
     * @param epsilon The allowed error epsilon.
     * @return -1 if x is less than xd.subtract(epsilon), 1 if x is greater than
     * xd.add(epsilon) and 0 otherwise.
     */
    public static int compare(BigDecimal x, BigDecimal xd, BigDecimal epsilon) {
        if (x.compareTo(xd.subtract(epsilon)) == -1) {
            return -1;
        }
        if (x.compareTo(xd.add(epsilon)) == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * Tests if x can be represented within epsilon as a double. Choose epsilon
     * equal to BigDecimal.ZERO to test 100% accuracy.
     *
     * @param x Number to test.
     * @param epsilon The allowed range either side of x for the double
     * representation.
     * @return false if x cannot be represented as a double within epsilon; true
     * otherwise.
     */
    public static boolean testDouble2(BigDecimal x, BigDecimal epsilon) {
        double xd = x.doubleValue();
        //double xd = Double.valueOf(x.toString());
        //System.out.println(xd);
        //System.out.println(Double.toString(xd));
        /**
         * Simply using:
         *
         * BigDecimal xToCompare = new BigDecimal(xd);
         *
         * Fails!
         *
         * Precision needs to be handled explicitly.
         */
        BigDecimal xToCompare = new BigDecimal(Double.toString(xd),
                getMathContextForComparison(x));
        return compare2(x, xToCompare, epsilon);
    }

    /**
     * For getting a MathContext for {@code x} for comparison purposes.
     *
     * @param x A BigDecimal.
     * @return A MathContext.
     */
    public static MathContext getMathContextForComparison(BigDecimal x) {
        int ul = x.unscaledValue().toString().length();
        // 2 is added to precision in the MathContext to cope with any rounding.
        MathContext mc = new MathContext(
                ul + (int) Math.pow(10, ul) + x.scale() + 2, // Precision.
                RoundingMode.FLOOR);
        return mc;
    }

    /**
     * Use x.precision() instead.
     *
     * @param x A BigDecimal.
     * @return An integer representing the precision of {@code x}.
     * @deprecated As of Version 1.1 - this is likely to removed in a future
     * release.
     */
    @Deprecated
    public static int getPrecision(BigDecimal x) {
        int ul = x.unscaledValue().toString().length();
        return ul + (int) Math.pow(10, ul) + x.scale();
    }

    /**
     * {@code x.precision() + 2}
     *
     * @param x A BigDecimal.
     * @return An integer representing the precision of {@code x}.
     */
    public static int getPrecisionSafe(BigDecimal x) {
        return x.precision() + 2;
        //return getPrecision(x) + 2;
    }

    /**
     * Compares x and xd. If the difference is greater than epsilon then false
     * is returned. Otherwise true is returned.
     *
     * @param x A number to compare.
     * @param xd A number to compare.
     * @param epsilon The allowed error epsilon.
     * @return true if x is less than xd.add(epsilon) and greater than
     * xd.subtract(epsilon) and false otherwise.
     */
    public static boolean compare2(BigDecimal x, BigDecimal xd, BigDecimal epsilon) {
        if (x.compareTo(xd.subtract(epsilon)) == -1) {
            return false;
        }
        return x.compareTo(xd.add(epsilon)) != 1;
    }

    /**
     * Tests if x can be represented within epsilon as a float. Choose epsilon
     * equal to BigDecimal.ZERO to test 100% accuracy.
     *
     * @param x Number to test.
     * @param epsilon The allowed range either side of x for the float
     * representation.
     * @return -1 if x cannot be represented as a float within epsilon and the
     * nearest representation is less than x; 1 if x cannot be represented as a
     * float within epsilon and the nearest representation is greater than x; 0
     * otherwise.
     */
    public static int testFloat(BigDecimal x, BigDecimal epsilon) {
        float xd = x.floatValue();
        return compare(x, new BigDecimal(xd), epsilon);
    }

    /**
     * Tests if x can be represented within epsilon as a float. Choose epsilon
     * equal to BigDecimal.ZERO to test 100% accuracy.
     *
     * @param x Number to test.
     * @param epsilon The allowed range either side of x for the float
     * representation.
     * @return false if x cannot be represented as a float within epsilon; true
     * otherwise.
     */
    public static boolean testFloat2(BigDecimal x, BigDecimal epsilon) {
        float xd = x.floatValue();
        //float xd = Float.valueOf(x.toString());
        //System.out.println(xd);
        //System.out.println(Float.toString(xd));
        /**
         * Simply using:
         *
         * BigDecimal xToCompare = new BigDecimal(xd);
         *
         * Fails!
         *
         * Precision needs to be handled explicitly.
         */
        BigDecimal xToCompare = new BigDecimal(Float.toString(xd),
                getMathContextForComparison(x));
        return compare2(x, xToCompare, epsilon);
    }
    
    /**
     * A convenience method to get the minimum of a set of double numbers 
     * ignoring any that are NaN. If all values are NaN then NaN is returned.
     * @param values
     * @return The minimum of all the values or NaN.
     */
    public static double min(double... values) {
        double r = values[0];
        boolean isNaN;
        if (Double.isNaN(r)) {
            isNaN = true;
        } else {
            isNaN = false;
        }
        for (int i = 1; i < values.length; i ++) {
            if (Double.isNaN(r)) {
                r = values[i];
            } else {
                if (!Double.isNaN(values[i])) {
                    r = Math.min(r, values[i]);
                }
            }
        }
        return r;
    }
    
    /**
     * A convenience method to get the maximum of a set of double numbers 
     * ignoring any that are NaN. If all values are NaN then NaN is returned.
     * @param values
     * @return The maximum of all the values or NaN.
     */
    public static double max(double... values) {
        double r = values[0];
        boolean isNaN;
        if (Double.isNaN(r)) {
            isNaN = true;
        } else {
            isNaN = false;
        }
        for (int i = 1; i < values.length; i ++) {
            if (Double.isNaN(r)) {
                r = values[i];
            } else {
                if (!Double.isNaN(values[i])) {
                    r = Math.max(r, values[i]);
                }
            }
        }
        return r;
    }
}
