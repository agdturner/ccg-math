/**
 * Copyright 2010 Andy Turner, The University of Leeds, UK
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

//import java.lang.Math;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * For BigDecimal arithmetic.
 *
 * For the time being it is expected that BigDecimal numbers will not have a
 * precision or scale greater than Integer.MAX_VALUE.
 *
 * Some of the functionality provided in this class may exist in third party
 * libraries with appropriate licenses, but at the time of writing, I have not
 * found them.
 *
 * The aim is for accuracy within a fixed number of decimal places. All methods
 * need to be fully tested to ensure compliance...
 */
//public class Generic_BigDecimal extends BigDecimal {
public class Generic_BigDecimal
        extends Generic_Number
        implements Serializable {

    /**
     * A Generic_BigInteger is often wanted (such as in Taylor Series
     * calculations).
     */
    public Generic_BigInteger _Generic_BigInteger;
    /**
     * For storing the Euler–Mascheroni constant correct to a fixed decimal
     * place precision
     */
    private BigDecimal _E;
    /**
     * For storing the PI constant correct to a fixed decimal place precision
     */
    private BigDecimal _PI;
    /**
     * RoundingMode used in calculations
     */
    private RoundingMode _RoundingMode;
    /**
     * The number 2 is often used so is made available as a constant.
     */
    public static final BigDecimal TWO = BigDecimal.valueOf(2);
    /**
     * The number 0.5 is often used so is made available as a constant.
     */
    public static final BigDecimal HALF = new BigDecimal("0.5");
    /**
     * The number 11 is often used so is made available as a constant.
     */
    public static final BigDecimal ELEVEN = BigDecimal.TEN.add(BigDecimal.ONE);

    /**
     * Creates a new instance of Generic_BigDecimal initialising
     * _Generic_BigInteger with 1000 entries and initialising _E to 1000 decimal
     * places.
     */
    public Generic_BigDecimal() {
        this(1000);
    }

    /**
     * Creates a new instance of Generic_BigDecimal based on _Generic_BigDecimal
     * @param _Generic_BigDecimal
     */
    public Generic_BigDecimal(Generic_BigDecimal _Generic_BigDecimal) {
        this._E = new BigDecimal(_Generic_BigDecimal._E.toString());
        this._Generic_BigInteger = new Generic_BigInteger(
                _Generic_BigDecimal._Generic_BigInteger);
        //this._InitialRandomSeed is best not be set
        //this._NextRandomSeed is best not be set
        this._PI = new BigDecimal(_Generic_BigDecimal._PI.toString());
        //this._RandomArray is best not be set
        this._RoundingMode = _Generic_BigDecimal._RoundingMode;
    }

    /**
     * Creates a new instance of Generic_BigDecimal initialising
     * _Generic_BigInteger with n entries and initialising _E to n decimal
     * places.
     * @param n
     */
    public Generic_BigDecimal(int n) {
        _RoundingMode = RoundingMode.HALF_UP;
        init_Factorial_Generic_BigInteger(n);
        init_E(n);
    }

    /**
     * @return the _RoundingMode
     */
    public RoundingMode get_RoundingMode() {
        if (_RoundingMode == null) {
            _RoundingMode = RoundingMode.HALF_UP;
        }
        return _RoundingMode;
    }

    /**
     * @param a_RoundingMode
     */
    public void set_RoundingMode(RoundingMode a_RoundingMode) {
        this._RoundingMode = a_RoundingMode;
    }

    /**
     * Initialises _Generic_BigInteger
     *
     * @param n
     */
    private void init_Factorial_Generic_BigInteger(int n) {
        _Generic_BigInteger = new Generic_BigInteger();
        _Generic_BigInteger.factorial(n);
    }

    /**
     * Initialises _E accurate to decimalPlaces decimal places
     *
     * @param decimalPlaces
     */
    private void init_E(int decimalPlaces) {
        _Generic_BigInteger.factorial(decimalPlaces);
        _E = getEulerConstantToAFixedDecimalPlacePrecision(
                decimalPlaces,
                get_RoundingMode());
//        _E = getEulerConstantToAMinimumDecimalPlacePrecision(
//                decimalPlaces);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Generic_BigDecimal a_Generic_BigDecimal = new Generic_BigDecimal();
//        a_Generic_BigDecimal.test();
    }

//    private void test() {
//        // For developing initial testing code which may form part of unit test.
//result = Generic_BigDecimal.getRandom(a_Random, a_Generic_BigDecimal, decimalPlaces);
//        System.out.println("result " + result);
//        System.out.println("getRandom");
//        Random a_Random = null;
//        int decimalPlaces = 0;
//        BigDecimal lowerLimit = null;
//        BigDecimal upperLimit = null;
//        BigDecimal expResult = null;
//        BigDecimal result = null;
//        long seed;
//        // Test 1
//        seed = 0L;
//        System.out.println("Test 1");
//        a_Random = new Random(seed);
//        decimalPlaces = 10;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 2
//        seed = 0L;
//        System.out.println("Test 2");
//        a_Random = new Random(seed);
//        decimalPlaces = 10000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 3
//        seed = 0L;
//        System.out.println("Test 3");
//        a_Random = new Random(seed);System.out.println("getRandom");
//        Random a_Random = null;
//        int decimalPlaces = 0;
//        BigDecimal lowerLimit = null;
//        BigDecimal upperLimit = null;
//        BigDecimal expResult = null;
//        BigDecimal result = null;
//        long seed;
//        // Test 1
//        seed = 0L;
//        System.out.println("Test 1");
//        a_Random = new Random(seed);
//        decimalPlaces = 10;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 2
//        seed = 0L;
//        System.out.println("Test 2");
//        a_Random = new Random(seed);
//        decimalPlaces = 10000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 3
//        seed = 0L;
//        System.out.println("Test 3");
//        a_Random = new Random(seed);
//        decimalPlaces = 10000000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 4
//        seed = 1111111L;
//        System.out.println("Test 4");
//        a_Random = new Random(seed);
//        decimalPlaces = 10000000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//        decimalPlaces = 10000000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//// Test 4
//        seed = 1111111L;
//        System.out.println("Test 4");
//        a_Random = new Random(seed);
//        decimalPlaces = 10000000;
//        lowerLimit = new BigDecimal("0");
//        upperLimit = new BigDecimal("1");
//        expResult = new BigDecimal("0");
//        result = Generic_BigDecimal.getRandom(a_Random, decimalPlaces, lowerLimit, upperLimit);
//        result = new BigDecimal(result.toPlainString());
//        System.out.println("result " + result);
//    }
//
//    /**
//     * @param x
//     * @param y1
//     * @return x^y1
//     */
//    @Deprecated
//    public BigDecimal power(
//            BigDecimal x,
//            BigDecimal y1) {
//        BigDecimal toCompare = null;
//        int yscale = y1.scale();
//        int tenToPoweryscale_int = (int) Math.pow(10, yscale);
//        BigInteger yunscaled = y1.unscaledValue();
//        //System.out.println("yunscaled " + yunscaled);
//        BigDecimal powerxyunscaled = power(x, yunscaled);
//        //System.out.println("powerxyunscaled " + powerxyunscaled);
//        int decimalPlaces = 10;
//        toCompare = getRoot(powerxyunscaled, tenToPoweryscale_int, decimalPlaces);
//        return toCompare;
//    }
    /**
     * Returns the number of digits to the left of the decimal point
     *
     * @param x
     * @return
     */
    public static int magnitude(BigDecimal x) {
        //int result = x.toPlainString().length() - x.scale() - 1;
        int result = x.toBigInteger().toString().length();
        return result;
    }

    /**
     * This is immutable.
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x multiplied by y rounded if necessary
     */
    public static BigDecimal multiplyRoundIfNecessary(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0
                || y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(y.toString());
            if (y.scale() > decimalPlaces) {
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(x.toString());
            if (x.scale() > decimalPlaces) {
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        return multiplyRoundIfNecessaryNoSpecialCaseCheck(
                x,
                y,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * This is immutable.
     *
     * @param x
     * @param y
     * @param a_MathContext
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x multiplied by y rounded if necessary
     */
    public static BigDecimal multiplyRoundIfNecessary(
            BigDecimal x,
            BigDecimal y,
            MathContext a_MathContext,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0
                || y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(y.toString());
            if (y.scale() > decimalPlaces) {
                return roundIfNecessary(
                        result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(x.toString());
            if (x.scale() > decimalPlaces) {
                return roundIfNecessary(
                        result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        return multiplyRoundIfNecessaryNoSpecialCaseCheck(
                x,
                y,
                a_MathContext,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * If a_MathContext has the right precision for rounding, it is used,
     * otherwise rounding is done if necessary using decimalPlaces and
     * a_RoundingMode. It is expected that the RoundingMode of the MathContext
     * is the same as a_RoundingMode.
     *
     * @param x
     * @param y
     * @param a_MathContext MathContext with some precision and a_RoundingMode
     * RoundingMode
     * @param decimalPlaces decimalPlaces &gt; -1
     * @param a_RoundingMode
     * @return
     */
    protected static BigDecimal multiplyRoundIfNecessaryNoSpecialCaseCheck(
            BigDecimal x,
            BigDecimal y,
            MathContext a_MathContext,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result = x.multiply(y);
        int resultScale = result.scale();
        if (result.scale() > decimalPlaces) {
            // RoundingNecessary
            if (a_MathContext.getPrecision() - (result.precision() - resultScale) == decimalPlaces) {
                return result.round(a_MathContext);
            } else {
                return roundIfNecessaryNoScaleCheck(
                        result, decimalPlaces, a_RoundingMode);
            }
        }
        return result;
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    protected static BigDecimal multiplyRoundIfNecessaryNoSpecialCaseCheck(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result = x.multiply(y);
        if (result.scale() > decimalPlaces) {
            return roundIfNecessaryNoScaleCheck(
                    result,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     *
     * The calculation could be divided into parts using the following algebra:
     * (a + b) * (c + d) = (a * c) + (a * d) + (b * c) + (b * d) Consider that a
     * is the integer part of x and b is the remainder and similarly that c is
     * the integer part of y and d is the remainder. Then: (a * c) is an
     * integer; (a * d) is a number with a scale no greater than the scale of d;
     * similarly, (b * c) is a number with a scale no greater than the scale of
     * b. These aforementioned parts give the main magnitude of the result. With
     * the exception of multiplications by zero, the remaining part, (b * d) is
     * the only part that is shrinking. It is also the only part that may
     * require more decimal places to store the result accurately. So, in
     * calculating all but (b * d) we can assess if the MathContext is
     * sufficient...
     *
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x multiplied by y then rounded;
     */
    public static BigDecimal multiplyRoundToFixedDecimalPlaces(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ONE) == 0) {
            if (y.scale() > decimalPlaces) {
                return roundToAndSetDecimalPlaces(
                        y, decimalPlaces, a_RoundingMode);
            } else {
                return y.setScale(decimalPlaces);
            }
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            if (x.scale() > decimalPlaces) {
                return roundToAndSetDecimalPlaces(
                        x, decimalPlaces, a_RoundingMode);
            } else {
                return x.setScale(decimalPlaces);
            }
        }
        if (x.compareTo(BigDecimal.ZERO) == 0
                || (y.compareTo(BigDecimal.ZERO) == 0)) {
            return BigDecimal.ZERO.setScale(decimalPlaces);
        }
        BigDecimal result = x.multiply(y);
        if (result.scale() > decimalPlaces) {
            // RoundingNecessary
            return roundToAndSetDecimalPlaces(
                    result, decimalPlaces, a_RoundingMode);
        } else {
            return result.setScale(decimalPlaces);
        }
    }

//    /**
//     * @param x
//     * @param y
//     * @param decimalPlaces
//     * @param a_RoundingMode
//     * @return x multiplied by y using a_MathContext the result returned is 
//     * rounded to and set to ;
//     */
//    public static BigDecimal multiplyRoundToAndSetDecimalPlaces(
//            BigDecimal x,
//            BigDecimal y,
//            MathContext a_MathContext) {
//        // Deal with special cases?
//        BigDecimal result = x.multiply(y, a_MathContext);
//        int decimalPlaces = 
//                a_MathContext.getPrecision() - 
//                result.toBigInteger().toString().length();
//        return roundToAndSetDecimalPlaces(
//                result, decimalPlaces, a_MathContext.getRoundingMode());
//    }
    /**
     * x*y If rounding is not wanted, use x.multiply(y1)
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x multiplied by y then rounded if necessary
     */
    public static BigDecimal multiplyRoundIfNecessary(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases (x = 0, x = 1, x = -1, y1 = 0, y1 = 1, y1 = -1)?
        // Deal with special cases
        if (x.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(y.toString());
            return result;
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            BigDecimal result = new BigDecimal(x.toString());
            if (x.scale() > decimalPlaces) {
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return multiplyRoundIfNecessaryNoSpecialCaseCheck(
                x,
                y_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * x*y If sure that a_MathContext is sufficgfhgshsdfgfdrounding is not
     * wanted, use x.multiply(y1)
     *
     * @param x
     * @param y
     * @param a_MathContext
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x multiplied by y then rounded if necessary
     */
    public static BigDecimal multiplyRoundIfNecessary(
            BigDecimal x,
            BigInteger y,
            MathContext a_MathContext,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases (x = 0, x = 1, x = -1, y1 = 0, y1 = 1, y1 = -1)?
        // Deal with special cases
        if (x.compareTo(BigDecimal.ONE) == 0) {
            BigDecimal result = new BigDecimal(y.toString());
            return result;
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            BigDecimal result = new BigDecimal(x.toString());
            if (x.scale() > decimalPlaces) {
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            } else {
                return result;
            }
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return multiplyRoundIfNecessaryNoSpecialCaseCheck(
                x,
                y_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * This is immutable.
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y then rounded if necessary
     */
    private static BigDecimal divideNoCaseCheckRoundIfNecessary(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        //int integerPartPrecision = x.divideToIntegralValue(y).toString().length();
        int integerPartPrecision = x.divideToIntegralValue(y).precision();
        //int precision = integerPartPrecision + decimalPlaces + 1;
        int precision = integerPartPrecision + decimalPlaces + 2;
        MathContext a_MathContext = new MathContext(precision, a_RoundingMode);
        BigDecimal xDivideY = x.divide(y, a_MathContext);
        return roundIfNecessary(xDivideY, decimalPlaces, a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y rounded to decimalPlaces decimal places if necessary using
     * a_RoundingMode RoundingMode
     */
    public static BigDecimal divideRoundIfNecessary(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(
                x,
                y,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @param a_MathContext
     * @return x/y rounded to decimalPlaces decimal places if necessary using
     * a_RoundingMode RoundingMode
     */
    public static BigDecimal divideRoundIfNecessary(
            BigDecimal x,
            BigDecimal y,
            MathContext a_MathContext) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return x.divide(y, a_MathContext);
    }

    /**
     * This is immutable.
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y then rounded if necessary
     */
    private static BigDecimal divideNoCaseCheckRoundToFixedDecimalPlaces(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        int integerPartLength = x.divideToIntegralValue(y).toString().length();
        int precision = integerPartLength + decimalPlaces + 1;
        MathContext a_MathContext = new MathContext(precision, a_RoundingMode);
        BigDecimal xDivideY = x.divide(y, a_MathContext);
        return roundToAndSetDecimalPlaces(
                xDivideY, decimalPlaces, a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y then rounded;
     */
    public static BigDecimal divideRoundToFixedDecimalPlaces(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundToAndSetDecimalPlaces(
                    x, decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(decimalPlaces);
        }
        BigDecimal result = divideNoCaseCheckRoundIfNecessary(
                x,
                y,
                decimalPlaces,
                a_RoundingMode);
        return roundToAndSetDecimalPlaces(
                result, decimalPlaces, a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @return x/y
     */
    public static BigDecimal divideNoRounding(
            BigDecimal x,
            BigDecimal y) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckNoRounding(
                x,
                y);
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y if it can be accurately represented as a BigDecimal. Throws a
     * ArithmeticException otherwise
     */
    private static BigDecimal divideNoCaseCheckNoRounding(
            BigDecimal x,
            BigDecimal y) {
        BigDecimal result = x.divide(y, RoundingMode.UNNECESSARY);
        return result;
    }

    /**
     * Creates and returns a new list containing all the numbers in list divided
     * by divisor using decimalPlaces and roundingMode and rounding if
     * necessary.
     * @param roundingMode
     * @param decimalPlaces
     * @return 
     */
    public static ArrayList<BigDecimal> divideRoundIfNecessary(
            ArrayList<BigDecimal> list,
            BigDecimal divisor,
            int decimalPlaces,
            RoundingMode roundingMode) {
        ArrayList<BigDecimal> result = new ArrayList<>();
        Iterator<BigDecimal> ite = list.iterator();
        while (ite.hasNext()) {
            BigDecimal value = ite.next();
            BigDecimal dividedValue = Generic_BigDecimal.divideRoundIfNecessary(
                    value,
                    divisor,
                    decimalPlaces,
                    roundingMode);
            result.add(dividedValue);
        }
        return result;
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param roundingMode
     * @return x/y then rounded;
     */
    public static BigDecimal divideRoundIfNecessary(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode roundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        if (x.compareTo(y_BigDecimal) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(
                x,
                y_BigDecimal,
                decimalPlaces,
                roundingMode);
    }

    /**
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x/y then rounded;
     */
    public static BigDecimal divideRoundToFixedDecimalPlaces(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return roundToAndSetDecimalPlaces(x, decimalPlaces, a_RoundingMode);
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        if (x.compareTo(y_BigDecimal) == 0) {
            return BigDecimal.ONE.setScale(decimalPlaces);
        }
        BigDecimal result = divideNoCaseCheckRoundIfNecessary(
                x,
                y_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
        return roundToAndSetDecimalPlaces(
                result, decimalPlaces, a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @return x/y
     */
    public static BigDecimal divideNoRounding(
            BigDecimal x,
            BigInteger y) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal y_BigDecimal = new BigDecimal(y);
        if (x.compareTo(y_BigDecimal) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckNoRounding(
                x,
                y_BigDecimal);
    }

    public static BigDecimal divideRoundIfNecessary(
            BigInteger x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal x_BigDecimal = new BigDecimal(x);
        if (x_BigDecimal.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(
                x_BigDecimal,
                y,
                decimalPlaces,
                a_RoundingMode);
    }

    public static BigDecimal divideRoundToFixedDecimalPlaces(
            BigInteger x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundToAndSetDecimalPlaces(
                    new BigDecimal(x), decimalPlaces, a_RoundingMode);
        }
        BigDecimal x_BigDecimal = new BigDecimal(x);
        if (x_BigDecimal.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(decimalPlaces);
        }
        BigDecimal result = divideNoCaseCheckRoundIfNecessary(
                x_BigDecimal,
                y,
                decimalPlaces,
                a_RoundingMode);
        return roundToAndSetDecimalPlaces(result, decimalPlaces, a_RoundingMode);
    }

    public static BigDecimal divideNoRounding(
            BigInteger x,
            BigDecimal y) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        BigDecimal x_BigDecimal = new BigDecimal(x);
        if (x_BigDecimal.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckNoRounding(
                x_BigDecimal,
                y);
    }

    public static BigDecimal divideRoundIfNecessary(
            BigInteger x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckRoundIfNecessary(
                new BigDecimal(x),
                new BigDecimal(y),
                decimalPlaces,
                a_RoundingMode);
    }

    public static BigDecimal divideRoundToFixedDecimalPlaces(
            BigInteger x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return roundToAndSetDecimalPlaces(
                    new BigDecimal(x), decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE.setScale(decimalPlaces);
        }
        BigDecimal result = divideNoCaseCheckRoundIfNecessary(
                new BigDecimal(x),
                new BigDecimal(y),
                decimalPlaces,
                a_RoundingMode);
        return roundToAndSetDecimalPlaces(
                result, decimalPlaces, a_RoundingMode);
    }

    public static BigDecimal divideNoRounding(
            BigInteger x,
            BigInteger y) {
        // Deal with special cases
        if (x.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ArithmeticException("Attempted division by zero...");
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(y) == 0) {
            return BigDecimal.ONE;
        }
        return divideNoCaseCheckNoRounding(
                new BigDecimal(x),
                new BigDecimal(y));
    }

    /**
     * x^y = e^(y * ln(x)) log(u/v)=log(u)-log(v) log(1) = 0; x^(a+b) = x^a*x^b
     * x^(a/b) = bthroot(x^a)
     *
     * @param x
     * @param y 0 < y < 1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y accurate to at least decimalPlaces
     */
    private static BigDecimal powerExponentLessThanOne(
            BigDecimal x,
            BigDecimal y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;
        //BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
        BigDecimal y0 = new BigDecimal(y.toString());
        BigDecimal element;
        BigInteger elementUnscaled;
        BigDecimal elementOne;
        BigInteger elementOneReciprocal;
        BigDecimal root;
        BigDecimal rootMultiple;
        int maxite = y0.precision();
        result = BigDecimal.ONE;
        for (int i = 0; i < maxite; i++) {
            element = floorSignificantDigit(y0);
            elementUnscaled = element.unscaledValue();
            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
                elementOne = divideRoundIfNecessary(
                        element,
                        elementUnscaled,
                        decimalPlaces,
                        a_RoundingMode);
                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
                root = rootRoundIfNecessary(
                        x,
                        elementOneReciprocal,
                        decimalPlaces + 3,
                        a_RoundingMode);
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = power(
                            root,
                            elementUnscaled,
                            64,
                            decimalPlaces,
                            a_RoundingMode);
                    result = multiplyRoundIfNecessary(
                            result,
                            rootMultiple,
                            decimalPlaces,
                            a_RoundingMode);
                }
            }
            y0 = y0.subtract(element);
        }
//        BigDecimal result;
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        BigDecimal x0 = new BigDecimal(x.toString());
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal rootRoundIfNecessary;
//        BigDecimal rootMultiple;
//        //int maxite = x0.precision();
//        result = BigDecimal.ONE;
//        //for (int i = 0; i < maxite; i++) {
//        while (true) {
//            element = floorSignificantDigit(x0);
//            elementUnscaled = element.unscaledValue();
//            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divide(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
//                    break;
//                }
//                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        x,
//                        elementOneReciprocal,
//                        //a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    result = multiply(
//                            result,
//                            rootMultiple,
//                            decimalPlaces,
//                            a_RoundingMode);
//            }
//            x0 = x0.subtract(element);
//        }

//        BigDecimal xPowerEleven = power(
//                x,
//                ELEVEN,
//                a_Generic_BigDecimal,
//                decimalPlaces * decimalPlaces, // guess at precision required
//                a_RoundingMode);
//        BigDecimal elevenSubtractY = ELEVEN.subtract(y);
//        BigDecimal xPowerElevenSubtractY = power(
//                x,
//                elevenSubtractY,
//                a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        result = divide(
//                xPowerEleven,
//                xPowerElevenSubtractY,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
//        // If x.unscaledValue() == 1, then the numerator becomes one.
//        // If x.unscaledValue() == 2, then the numerator tends to 1,
//        // but to calculate Euler's constant raised to the power of (the Euler
//        // based log of x.unscaledValue() multiplied by y) we are dealing with
//        // an exponentiation involving a power even smaller than y which is
//        // problematic.
//        // If x.unscaledValue() >= 3, then any further exponentiations will
//        // involve a power greater than y.
//        // For this reason, numerator and denominator are effectively mutlipled
//        // by 10.
//        BigDecimal numeratorx = new BigDecimal(x.unscaledValue().multiply(BigInteger.TEN));
//        BigDecimal denominatorx = new BigDecimal(BigInteger.TEN, -x.scale());
//        BigDecimal numeratorexponent = y.multiply(log(
//                10,
//                numeratorx,
//                decimalPlaces, // not sure about this or the multiplication
//                a_RoundingMode));
//        BigDecimal denominatorexponent = y.multiply(log(
//                10,
//                denominatorx,
//                decimalPlaces, // not sure about this
//                a_RoundingMode));
//        BigDecimal numerator = power(
//                BigDecimal.TEN,
//                numeratorexponent,
//                a_Generic_BigDecimal,
//                decimalPlaces, // not sure about this
//                a_RoundingMode);
//        BigDecimal denominator = power(
//                BigDecimal.TEN,
//                denominatorexponent,
//                a_Generic_BigDecimal,
//                decimalPlaces, // not sure about this
//                a_RoundingMode);
//        result = divideNoCaseCheckNoRounding(
//                numerator,
//                denominator,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
        //ln(x, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode)
        //power(numerator,y,a_Generic_BigDecimal, decimalPlaces, a_RoundingMode)
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        if (y1.compareTo(epsilon_BigDecimal) == -1) {
//            return new BigDecimal(BigInteger.ZERO, decimalPlaces);
//        }


//    if (x.compareTo (BigDecimal.ONE) 
//        == -1 && x.compareTo(BigDecimal.ONE.negate()) == 1) {
//
//            BigDecimal yunscaled = new BigDecimal(y.unscaledValue());
//        int yscale = y.scale();
//        int xscale = x.scale();
//        BigInteger rootRoundIfNecessary = new BigDecimal(BigInteger.ONE, (-1 * yscale)).toBigIntegerExact();
//        int rootLength = rootRoundIfNecessary.toString().length();
//
////            BigDecimal xrooted = rootRoundIfNecessary(
////                    x,
////                    rootRoundIfNecessary,
////                    a_Generic_BigDecimal,
////                    decimalPlaces,
////                    a_RoundingMode);
////            result = power(
////                    xrooted,
////                    yunscaled,
////                    a_Generic_BigDecimal,
////                    decimalPlaces + xscale,
////                    a_RoundingMode);
//        BigDecimal xpowyunscaled = power(
//                x,
//                yunscaled,
//                //a_Generic_BigDecimal,
//                //decimalPlaces + xscale, // Need much more precision....
//                decimalPlaces + yunscaled.intValueExact() + xscale,
//                a_RoundingMode);
//        //BigDecimal initialEstimate = BigDecimal.ONE.add(x).divide(TWO);
//        result = rootLessThanOne(
//                xpowyunscaled,
//                rootRoundIfNecessary,
//                //initialEstimate,
//                //a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
//    }
//    
//
//    
//        else {
//            decimalPlaces += 3;
//        BigDecimal y0 = new BigDecimal(y.toString());
//        int maxite = y0.precision();
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal rootRoundIfNecessary;
//        BigDecimal rootMultiple;
//        result = BigDecimal.ONE;
//        for (int i = 0; i < maxite; i++) {
//            element = floorSignificantDigit(y0);
//            //elementUnscaled = new BigDecimal(element.unscaledValue());
//            elementUnscaled = element.unscaledValue();
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divide(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        x,
//                        elementOneReciprocal,
//                        //a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    result = multiply(
//                            result,
//                            rootMultiple,
//                            decimalPlaces,
//                            a_RoundingMode);
//                }
//            }
//            y0 = y0.subtract(element);
//        }
//        return round(result, decimalPlaces - 3, a_RoundingMode);
//    }
//    //return round(result, decimalPlaces, a_RoundingMode);
//}
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    private static BigDecimal powerExponentLessThanOneNoRounding(
            BigDecimal x,
            BigDecimal y) {
        BigDecimal result = BigDecimal.ONE;
        //BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
        BigDecimal y0 = new BigDecimal(y.toString());
        BigDecimal element;
        BigInteger elementUnscaled;
        BigDecimal elementOne;
        BigInteger elementOneReciprocal;
        BigDecimal root;
        BigDecimal rootMultiple;
        BigDecimal previousResult = result;
        while (true) {
            element = floorSignificantDigit(y0);
            elementUnscaled = element.unscaledValue();
            //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
                elementOne = divideNoRounding(
                        element,
                        elementUnscaled);
                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                //System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
                root = rootNoRounding(
                        x,
                        elementOneReciprocal);
                if (root.compareTo(BigDecimal.ZERO) == 1) {
                    rootMultiple = powerNoRounding(
                            root,
                            elementUnscaled,
                            64);
                    result = result.multiply(rootMultiple);
                }
            }
            if (result.compareTo(previousResult) == 0) {
                break;
            }
            previousResult = result;
            y0 = y0.subtract(element);
        }
        return result;
    }

    /**
     * @param compare
     * @param x x > 1
     * @param y y > 1
     * @param div Optimisation parameter...
     * @param decimalPlaces The number of decimal places rounded to if rounding
     * is necessary.
     * @param a_RoundingMode The RoundingMode used in the case rounding is
     * necessary.
     * @return true iff x^y > compare
     */
    public static boolean powerTestAbove(
            BigDecimal compare,
            BigDecimal x,
            BigInteger y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        int compareScale = compare.scale();
        int powerDecimalPlaces;
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            powerDecimalPlaces = Math.min(compareScale + 1, decimalPlaces);
            toCompare0 = power(
                    x,
                    y,
                    div,
                    powerDecimalPlaces,
                    a_RoundingMode);
            if (toCompare0.compareTo(compare) == 1) {
                return true;
            } else {
                return false;
            }
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        int toCompareScale;
        int toCompare0Scale;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            toCompareScale = toCompare.scale();
            if (y1.compareTo(div_BigInteger) == -1) {
                powerDecimalPlaces = Math.min(
                        (div * compareScale) + 1,
                        decimalPlaces);
                toCompare0 = power(
                        x,
                        y1,
                        div,
                        powerDecimalPlaces,
                        a_RoundingMode);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                powerDecimalPlaces = Math.min(
                        compareScale + 1,
                        decimalPlaces);
                boolean powerTest0 = powerTestAbove(
                        compare,
                        x,
                        yDivideAndRemainder[0],
                        div,
                        powerDecimalPlaces,
                        a_RoundingMode);
                if (powerTest0) {
                    return true;
                } else {
                    powerDecimalPlaces = Math.min(
                            (div * compareScale) + 1,
                            decimalPlaces);
                    toCompare0 = power(
                            x,
                            yDivideAndRemainder[0],
                            div,
                            powerDecimalPlaces,
                            a_RoundingMode);
                }
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = power(
                            x,
                            yDivideAndRemainder[1],
                            div,
                            decimalPlaces, // @TODO Is this sufficient?
                            a_RoundingMode);
                    if (resultRemainder.compareTo(compare) == 1) {
                        return true;
                    }
                    toCompare0 = multiplyRoundIfNecessary(
                            toCompare0,
                            resultRemainder,
                            decimalPlaces,
                            a_RoundingMode); // @TODO Is this the correct form of multiply?
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            toCompare = multiplyRoundIfNecessary(
                    toCompare,
                    toCompare0,
                    decimalPlaces,
                    a_RoundingMode); // @TODO Is this the correct form of multiply?
            if (toCompare.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
        }
        return false;
    }

    /**
     * @param compare
     * @param x x > 1
     * @param y y > 1
     * @param div Optimisation parameter...
     * @return true iff x^y > compare
     */
    public static boolean powerTestAboveNoRounding(
            BigDecimal compare,
            BigDecimal x,
            BigInteger y,
            int div) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        int compareScale = compare.scale();
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            toCompare0 = powerNoRounding(
                    x,
                    y,
                    div);
            if (toCompare0.compareTo(compare) == 1) {
                return true;
            } else {
                return false;
            }
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(div_BigInteger) == -1) {
                toCompare0 = powerNoRounding(
                        x,
                        y1,
                        div);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                boolean powerTest0 = powerTestAboveNoRounding(
                        compare,
                        x,
                        yDivideAndRemainder[0],
                        div);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = powerNoRounding(
                            x,
                            yDivideAndRemainder[0],
                            div);
                }
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = powerNoRounding(
                            x,
                            yDivideAndRemainder[1],
                            div);
                    if (resultRemainder.compareTo(compare) == 1) {
                        return true;
                    }
                    toCompare0 = toCompare0.multiply(resultRemainder);
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            toCompare = toCompare.multiply(toCompare0);
            if (toCompare.compareTo(compare) == 1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
        }
        return false;
    }

    /**
     * @param compare
     * @param x x > 0
     * @param y y > 1
     * @param div Optimisation parameter...
     * @param decimalPlaces The number of decimal places rounded to if rounding
     * is necessary.
     * @param a_RoundingMode The RoundingMode used in the case rounding is
     * necessary.
     * @return true iff x^y < compare
     */
    public static boolean powerTestBelow(
            BigDecimal compare,
            BigDecimal x,
            BigInteger y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            toCompare0 = power(
                    x,
                    y,
                    div,
                    decimalPlaces, // @TODO Is this sufficient?
                    a_RoundingMode);
            if (toCompare0.compareTo(compare) == -1) {
                return true;
            } else {
                return false;
            }
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(div_BigInteger) == -1) {
                toCompare0 = power(
                        x,
                        y1,
                        div,
                        decimalPlaces, // @TODO Is this sufficient?
                        a_RoundingMode);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                boolean powerTest0 = powerTestAbove(
                        compare,
                        x,
                        yDivideAndRemainder[0],
                        div,
                        decimalPlaces, // @TODO Is this sufficient?
                        a_RoundingMode);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = power(
                            x,
                            yDivideAndRemainder[0],
                            div,
                            decimalPlaces, // @TODO Is this sufficient?
                            a_RoundingMode);
                }
                toCompare0 = power(
                        toCompare0,
                        div,
                        decimalPlaces, // @TODO Is this sufficient?
                        a_RoundingMode);
                if (toCompare.compareTo(compare) == -1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = power(
                            x,
                            yDivideAndRemainder[1],
                            div,
                            decimalPlaces, // @TODO Is this sufficient?
                            a_RoundingMode);
                    if (resultRemainder.compareTo(compare) == -1) {
                        return true;
                    }
                    toCompare0 = multiplyRoundIfNecessary(
                            toCompare0,
                            resultRemainder,
                            decimalPlaces,
                            a_RoundingMode); // @TODO Is this the correct form of multiply?
                    if (toCompare.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            toCompare = multiplyRoundIfNecessary(
                    toCompare,
                    toCompare0,
                    decimalPlaces,
                    a_RoundingMode); // @TODO Is this the correct form of multiply?
            if (toCompare.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
        }
        return false;
    }

    /**
     * @param compare
     * @param x x > 0
     * @param y y > 1
     * @param div Optimisation parameter...
     * @return true iff x^y < compare
     */
    public static boolean powerTestBelowNoRounding(
            BigDecimal compare,
            BigDecimal x,
            BigInteger y,
            int div) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        // Deal with special case
        if (y.compareTo(div_BigInteger) == -1) {
            toCompare0 = powerNoRounding(
                    x,
                    y,
                    div);
            if (toCompare0.compareTo(compare) == -1) {
                return true;
            } else {
                return false;
            }
        }
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        while (y1.compareTo(BigInteger.ONE) == 1) {
            if (y1.compareTo(div_BigInteger) == -1) {
                toCompare0 = powerNoRounding(
                        x,
                        y1,
                        div);
            } else {
                yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
                boolean powerTest0 = powerTestAboveNoRounding(
                        compare,
                        x,
                        yDivideAndRemainder[0],
                        div);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = powerNoRounding(
                            x,
                            yDivideAndRemainder[0],
                            div);
                }
                toCompare0 = powerNoRounding(
                        toCompare0,
                        div);
                if (toCompare.compareTo(compare) == -1) {
                    return true;
                }
                if (yDivideAndRemainder[1].compareTo(BigInteger.ONE) == 1) {
                    BigDecimal resultRemainder = powerNoRounding(
                            x,
                            yDivideAndRemainder[1],
                            div);
                    if (resultRemainder.compareTo(compare) == -1) {
                        return true;
                    }
                    toCompare0 = toCompare0.multiply(resultRemainder);
                    if (toCompare.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            toCompare = toCompare.multiply(toCompare0);
            if (toCompare.compareTo(compare) == -1) {
                return true;
            }
            y1 = y1.divide(div_BigInteger);
        }
        return false;
    }

    /**
     * @param compare
     * @param x x > 1
     * @param y
     * @param div If < 3 this is set to 2. If > 256 set to 256
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return true iff x^y1 > compare
     */
    @Deprecated
    public static boolean powerTestAbove(
            BigDecimal compare,
            BigDecimal x,
            int y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        // Deal with special case
        if (y < div) {
            toCompare0 = power(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
            if (toCompare0.compareTo(compare) == 1) {
                return true;
            } else {
                return false;
            }
        }
        while (y > 1) {
            if (y < div) {
                boolean powerTest0 = powerTestAbove(
                        compare,
                        x,
                        y,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = power(
                            x,
                            y,
                            div,
                            decimalPlaces,
                            a_RoundingMode);
                }
            } else {
                int party0 = y / div;
                toCompare0 = power(
                        x,
                        party0,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                toCompare0 = power(
                        toCompare0,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (toCompare.compareTo(compare) == 1) {
                    return true;
                }
                int party1 = y % div;
                if (party1 != 0) {
                    BigDecimal resultRemainder = power(
                            x,
                            party1,
                            div,
                            decimalPlaces,
                            a_RoundingMode);
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                    toCompare0 = multiplyRoundIfNecessary(
                            toCompare0,
                            resultRemainder,
                            decimalPlaces,
                            a_RoundingMode);
                    if (toCompare.compareTo(compare) == 1) {
                        return true;
                    }
                }
            }
            toCompare = multiplyRoundIfNecessary(
                    toCompare,
                    toCompare0,
                    decimalPlaces,
                    a_RoundingMode);
            if (toCompare.compareTo(compare) == 1) {
                return true;
            }
            y /= div;
        }
        return false;
    }

    /**
     * @param compare
     * @param x x > 0
     * @param y
     * @param div If < 3 this is set to 2. If > 256 set to 256
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return true iff x^y1 > compare
     */
    @Deprecated
    public static boolean powerTestBelow(
            BigDecimal compare,
            BigDecimal x,
            int y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (div < 3) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigDecimal toCompare = BigDecimal.ONE;
        BigDecimal toCompare0;
        // Deal with special case
        if (y < div) {
            toCompare0 = power(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
            if (toCompare0.compareTo(compare) == -1) {
                return true;
            } else {
                return false;
            }
        }
        while (y > 1) {
            if (y < div) {
                boolean powerTest0 = powerTestAbove(
                        compare,
                        x,
                        y,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (powerTest0) {
                    return true;
                } else {
                    toCompare0 = power(
                            x,
                            y,
                            div,
                            decimalPlaces,
                            a_RoundingMode);
                }
            } else {
                int party0 = y / div;
                toCompare0 = power(
                        x,
                        party0,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (toCompare.compareTo(compare) == -1) {
                    return true;
                }
                toCompare0 = power(
                        toCompare0,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (toCompare.compareTo(compare) == -1) {
                    return true;
                }
                int party1 = y % div;
                if (party1 != 0) {
                    BigDecimal resultRemainder = power(
                            x,
                            party1,
                            div,
                            decimalPlaces,
                            a_RoundingMode);
                    if (toCompare.compareTo(compare) == -1) {
                        return true;
                    }
                    toCompare0 = multiplyRoundIfNecessary(
                            toCompare0,
                            resultRemainder,
                            decimalPlaces,
                            a_RoundingMode);
                    if (toCompare.compareTo(compare) == -1) {
                        return true;
                    }
                }
            }
            toCompare = multiplyRoundIfNecessary(
                    toCompare,
                    toCompare0,
                    decimalPlaces,
                    a_RoundingMode);
            if (toCompare.compareTo(compare) == -1) {
                return true;
            }
            y /= div;
        }
        return false;
    }

    /**
     * x^y = e^(y * ln(x))
     *
     * x^(a+b) = (x^a)*(x^b)
     *
     * @param x
     * @param y
     * @param a_RoundingMode
     * @param decimalPlaces The number of decimal places the toCompare must be
     * accurate and rounded to if necessary.
     * @return x raised to the power of y accurate to decimalPlaces number of
     * decimal places
     */
    public static BigDecimal power(
            BigDecimal x,
            BigDecimal y,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return roundIfNecessary(x, decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result = power(
                    x.negate(),
                    y,
                    //a_Generic_BigDecimal,
                    decimalPlaces,
                    a_RoundingMode);
            if (isEven(y)) {
                return roundIfNecessary(result.negate(), decimalPlaces, a_RoundingMode);
            } else {
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = power(
                    x,
                    y.negate(),
                    //a_Generic_BigDecimal,
                    decimalPlaces + 2,
                    a_RoundingMode);
            BigDecimal result = reciprocal(
                    power,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        if (y.scale() <= 0) {
            return power(
                    x,
                    y.toBigIntegerExact(),
                    64,
                    decimalPlaces,
                    a_RoundingMode);
        }
        if (x.compareTo(TWO) == -1) {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                return powerExponentLessThanOne(
                        x,
                        y,
                        decimalPlaces,
                        a_RoundingMode);
                //throw new UnsupportedOperationException();
            } else {
                BigDecimal fractionalPart = powerExponentLessThanOne(
                        x,
                        y.subtract(BigDecimal.ONE),
                        decimalPlaces + 2,
                        a_RoundingMode);
                return roundIfNecessary(
                        x.multiply(fractionalPart),
                        decimalPlaces,
                        a_RoundingMode);
                //throw new UnsupportedOperationException();
            }
        } else {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                // x > 2 && y < 1
                BigDecimal result = powerExponentLessThanOne(
                        x,
                        y,
                        decimalPlaces,
                        a_RoundingMode);
//                int yScale = y.scale();
//                if (yScale < 9) {
//                    BigInteger yunscaled = y.unscaledValue();
//                    BigDecimal resultToBeRooted = power(
//                            x,
//                            yunscaled,
//                            (int) Math.pow(2, yScale),
//                            decimalPlaces + 2,
//                            a_RoundingMode);
//                    BigDecimal result = rootRoundIfNecessary(
//                            resultToBeRooted,
//                            );
//                    return result;
//                }

//                // Use x^a * y^a = (x * y)^a
//                BigInteger xIntegerPart = x.toBigInteger();
//                BigDecimal result0 = power(
//                        xIntegerPart,
//                        y,
//                        decimalPlaces + 2,
//                    a_RoundingMode);
//                BigDecimal
//               BigDecimal result = powerExponentLessThanOne(
//                    x,
//                    y,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
                //throw new UnsupportedOperationException();
                return result;
            } else {
                // x > 2 && y > 1
                BigInteger y_BigInteger = y.toBigInteger();
                BigDecimal integerPartResult = power(
                        x,
                        y_BigInteger,
                        256,
                        decimalPlaces,
                        a_RoundingMode);
                BigDecimal fractionalPartResult = power(
                        x,
                        y.subtract(new BigDecimal(y_BigInteger)),
                        decimalPlaces,
                        a_RoundingMode);
                BigDecimal result = multiplyRoundIfNecessary(
                        integerPartResult,
                        fractionalPartResult,
                        decimalPlaces,
                        a_RoundingMode);
                return result;
////                
////                if (x.compareTo(BigDecimal.TEN) == 1) {
////                    // A log base 10 x will return greater than one
////                    BigDecimal log10x = log(
////                            10,
////                            x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    BigDecimal ylog10x = multiplyRoundIfNecessary(
////                            y,
////                            log10x,
////                            decimalPlaces * 2, // Is this safe, or should it be a factor of y
////                            a_RoundingMode);
////                    BigDecimal result = power(
////                            BigDecimal.TEN,
////                            ylog10x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    return result;
////                } else {
////                    // A log base 2 x will return greater than one
////                    BigDecimal log2x = log(
////                            2,
////                            x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    BigDecimal ylog2x = multiplyRoundIfNecessary(
////                            y,
////                            log2x,
////                            decimalPlaces * 2, // Is this safe, or should it be a factor of y
////                            a_RoundingMode);
////                    BigDecimal result = power(
////                            TWO,
////                            ylog2x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    return result;
            }
        }
////        // Check a_Generic_BigDecimal
////        if (a_Generic_BigDecimal == null) {
////            a_Generic_BigDecimal = new Generic_BigDecimal();
////        }
//        BigDecimal result;
//        // Case where y is negative
//        // Case where y is less than one
//        if (y.compareTo(BigDecimal.ONE) == -1) {
//            result = powerExponentLessThanOne(
//                    //a_Generic_BigDecimal,
//                    x,
//                    y,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
//        }
//        if (y.scale() == 0) {
//            result = power(
//                    x,
//                    y.unscaledValue(),
//                    256,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
//        }
//
//
////        //Integer part
////        BigDecimal partresult = Generic_BigInteger.power(
////                Generic_BigInteger.Two,
////                ylog2x.intValue(),
////                decimalPlaces,
////                a_RoundingMode);
////
////        // Check tollerance of ylog2x.subtract(new BigDecimal(ylog2x.toBigInteger()))
////
//////        BigDecimal nextPartResult = power(
//////                TWO,
//////                ylog2x.subtract(new BigDecimal(ylog2x.toBigInteger())),
//////                a_Generic_BigDecimal,
//////                decimalPlaces,
//////                a_RoundingMode);
////        result = partresult.multiply(partresult);
//////        result = power(
//////                TWO,
//////                ylog2x,
//////                a_Generic_BigDecimal,
//////                decimalPlaces,
//////                a_RoundingMode);
////        return result;
//
//        //System.out.println("e " + e);
//        BigDecimal lnx;
//        if (x.compareTo(BigDecimal.ONE) == 1) {
//            // decimalPlaces decimal place precision is adequate
//            lnx = log(
//                    a_Generic_BigDecimal.get_E_MinDecimalPlaces(decimalPlaces),
//                    x,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
//            int logPrecision = x.precision() - x.scale() + decimalPlaces;
//            lnx = log(
//                    a_Generic_BigDecimal.get_E_MinDecimalPlaces(decimalPlaces),
//                    x,
//                    logPrecision,
//                    a_RoundingMode);
//        }
//        //System.out.println("lnx " + lnx.toString());
//        BigDecimal ylnx = multiply(
//                y,
//                lnx,
//                decimalPlaces * 2, // This is a safeish guess
//                a_RoundingMode);
//        //System.out.println("ylnx " + ylnx.toString());
//        BigDecimal expylnx = exp(
//                ylnx,
//                a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        //System.out.println("expylnx " + expylnx.toString());
//        return round(expylnx, decimalPlaces, a_RoundingMode);
    }

    /**
     * Immutable
     *
     * @param x
     * @param y
     * @return
     */
    public static BigDecimal powerNoRounding(
            BigDecimal x,
            BigDecimal y) {
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result = powerNoRounding(
                    x.negate(),
                    y);
            if (isEven(y)) {
                return result.negate();
            } else {
                return result;
            }
        }
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal power = powerNoRounding(
                    x,
                    y.negate());
            BigDecimal result = reciprocalWillBeIntegerReturnBigDecimal(
                    power);
            return result;
        }
        if (y.scale() <= 0) {
            return powerNoRounding(
                    x,
                    y.toBigIntegerExact(),
                    64);
        }
        if (x.compareTo(TWO) == -1) {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                throw new UnsupportedOperationException();
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            if (y.compareTo(BigDecimal.ONE) == -1) {
                // x > 2 && y < 1
                BigDecimal result = powerExponentLessThanOneNoRounding(
                        x,
                        y);
//                int yScale = y.scale();
//                if (yScale < 9) {
//                    BigInteger yunscaled = y.unscaledValue();
//                    BigDecimal resultToBeRooted = power(
//                            x,
//                            yunscaled,
//                            (int) Math.pow(2, yScale),
//                            decimalPlaces + 2,
//                            a_RoundingMode);
//                    BigDecimal result = rootRoundIfNecessary(
//                            resultToBeRooted,
//                            );
//                    return result;
//                }

//                // Use x^a * y^a = (x * y)^a
//                BigInteger xIntegerPart = x.toBigInteger();
//                BigDecimal result0 = power(
//                        xIntegerPart,
//                        y,
//                        decimalPlaces + 2,
//                    a_RoundingMode);
//                BigDecimal
//               BigDecimal result = powerExponentLessThanOne(
//                    x,
//                    y,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
                //throw new UnsupportedOperationException();
                return result;
            } else {
                // x > 2 && y > 1
                BigInteger y_BigInteger = y.toBigInteger();
                BigDecimal integerPartResult = powerNoRounding(
                        x,
                        y_BigInteger,
                        256);
                BigDecimal fractionalPartResult = powerNoRounding(
                        x,
                        y.subtract(new BigDecimal(y_BigInteger)));
                BigDecimal result = integerPartResult.multiply(
                        fractionalPartResult);
                return result;
////                
////                if (x.compareTo(BigDecimal.TEN) == 1) {
////                    // A log base 10 x will return greater than one
////                    BigDecimal log10x = log(
////                            10,
////                            x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    BigDecimal ylog10x = multiplyRoundIfNecessary(
////                            y,
////                            log10x,
////                            decimalPlaces * 2, // Is this safe, or should it be a factor of y
////                            a_RoundingMode);
////                    BigDecimal result = power(
////                            BigDecimal.TEN,
////                            ylog10x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    return result;
////                } else {
////                    // A log base 2 x will return greater than one
////                    BigDecimal log2x = log(
////                            2,
////                            x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    BigDecimal ylog2x = multiplyRoundIfNecessary(
////                            y,
////                            log2x,
////                            decimalPlaces * 2, // Is this safe, or should it be a factor of y
////                            a_RoundingMode);
////                    BigDecimal result = power(
////                            TWO,
////                            ylog2x,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    return result;
            }
        }
////        // Check a_Generic_BigDecimal
////        if (a_Generic_BigDecimal == null) {
////            a_Generic_BigDecimal = new Generic_BigDecimal();
////        }
//        BigDecimal result;
//        // Case where y is negative
//        // Case where y is less than one
//        if (y.compareTo(BigDecimal.ONE) == -1) {
//            result = powerExponentLessThanOne(
//                    //a_Generic_BigDecimal,
//                    x,
//                    y,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
//        }
//        if (y.scale() == 0) {
//            result = power(
//                    x,
//                    y.unscaledValue(),
//                    256,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return round(result, decimalPlaces, a_RoundingMode);
//        }
//
//
////        //Integer part
////        BigDecimal partresult = Generic_BigInteger.power(
////                Generic_BigInteger.Two,
////                ylog2x.intValue(),
////                decimalPlaces,
////                a_RoundingMode);
////
////        // Check tollerance of ylog2x.subtract(new BigDecimal(ylog2x.toBigInteger()))
////
//////        BigDecimal nextPartResult = power(
//////                TWO,
//////                ylog2x.subtract(new BigDecimal(ylog2x.toBigInteger())),
//////                a_Generic_BigDecimal,
//////                decimalPlaces,
//////                a_RoundingMode);
////        result = partresult.multiply(partresult);
//////        result = power(
//////                TWO,
//////                ylog2x,
//////                a_Generic_BigDecimal,
//////                decimalPlaces,
//////                a_RoundingMode);
////        return result;
//
//        //System.out.println("e " + e);
//        BigDecimal lnx;
//        if (x.compareTo(BigDecimal.ONE) == 1) {
//            // decimalPlaces decimal place precision is adequate
//            lnx = log(
//                    a_Generic_BigDecimal.get_E_MinDecimalPlaces(decimalPlaces),
//                    x,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
//            int logPrecision = x.precision() - x.scale() + decimalPlaces;
//            lnx = log(
//                    a_Generic_BigDecimal.get_E_MinDecimalPlaces(decimalPlaces),
//                    x,
//                    logPrecision,
//                    a_RoundingMode);
//        }
//        //System.out.println("lnx " + lnx.toString());
//        BigDecimal ylnx = multiply(
//                y,
//                lnx,
//                decimalPlaces * 2, // This is a safeish guess
//                a_RoundingMode);
//        //System.out.println("ylnx " + ylnx.toString());
//        BigDecimal expylnx = exp(
//                ylnx,
//                a_Generic_BigDecimal,
//                decimalPlaces,
//                a_RoundingMode);
//        //System.out.println("expylnx " + expylnx.toString());
//        return round(expylnx, decimalPlaces, a_RoundingMode);
    }

    /**
     *
     * @param x
     * @param y
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y1 accurate to decimalPlaces
     */
    public static BigDecimal power(
            BigDecimal x,
            long y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
//        if (Math.abs(y) < 999999999) {
//            return power(
//                    x,
//                    (int) y,
//                    256,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
        return power(
                x,
                BigInteger.valueOf(y),
                decimalPlaces,
                256,
                a_RoundingMode);
//        }
    }

    /**
     * @todo set precision
     *
     * @param x
     * @param y1
     * @return
     */
    @Deprecated
    private static BigDecimal power0(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigInteger.ONE) == 0) {
            return x;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal result;
            BigDecimal power = power4(
                    x,
                    y.negate(),
                    decimalPlaces,
                    a_RoundingMode);
            result = reciprocal(
                    power,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
        }
        return power4(
                x,
                y,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * Use
     * {@code power(BigDecimal,BigInteger,BigInteger,BigInteger,int,RoundingMode)}
     * instead.
     *
     * @param x
     * @param y1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     * @deprecated
     */
    @Deprecated
    private static BigDecimal power4(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigInteger Million_BigInteger = BigInteger.valueOf(1000000);
        if (y.compareTo(Million_BigInteger) == -1) {
            return power3(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
        }
        BigInteger[] party = y.divideAndRemainder(Generic_BigInteger.Hundred);
        BigDecimal result = power4(
                x,
                party[0],
                decimalPlaces,
                a_RoundingMode);
        result = power3(
                result,
                Million_BigInteger,
                decimalPlaces,
                a_RoundingMode);
        if (party[1].compareTo(BigInteger.ZERO) == 1) {
            BigDecimal resultRemainder = power3(
                    x,
                    party[1],
                    decimalPlaces,
                    a_RoundingMode);
            result = multiplyRoundIfNecessary(
                    result,
                    resultRemainder,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     * Use
     * {@code power(BigDecimal,BigInteger,BigInteger,BigInteger,int,RoundingMode)}
     * instead.
     *
     * @param x
     * @param y1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    @Deprecated
    private static BigDecimal power3(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigInteger TenThousand_BigInteger = BigInteger.valueOf(10000);
        if (y.compareTo(TenThousand_BigInteger) == -1) {
            return power2(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
        }
        BigInteger[] party = y.divideAndRemainder(Generic_BigInteger.Hundred);
        BigDecimal result = power3(
                x,
                party[0],
                decimalPlaces,
                a_RoundingMode);
        result = power2(
                result,
                TenThousand_BigInteger,
                decimalPlaces,
                a_RoundingMode);
        if (party[1].compareTo(BigInteger.ZERO) == 1) {
            BigDecimal resultRemainder = power2(
                    x,
                    party[1],
                    decimalPlaces,
                    a_RoundingMode);
            result = multiplyRoundIfNecessary(
                    result,
                    resultRemainder,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     * Use
     * {@code power(BigDecimal,BigInteger,BigInteger,BigInteger,int,RoundingMode)}
     * instead.
     *
     * @param x
     * @param y1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    @Deprecated
    private static BigDecimal power2(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigInteger Hundred_BigInteger = BigInteger.valueOf(100);
        if (y.compareTo(Hundred_BigInteger) != 1) {
            return power1(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
        }
        BigInteger[] party = y.divideAndRemainder(
                Generic_BigInteger.Hundred);
        BigDecimal result = power2(
                x,
                party[0],
                decimalPlaces,
                a_RoundingMode);
        result = power1(
                result,
                Hundred_BigInteger,
                decimalPlaces,
                a_RoundingMode);
        if (party[1].compareTo(BigInteger.ZERO) == 1) {
            BigDecimal resultRemainder = power1(
                    x,
                    party[1],
                    decimalPlaces,
                    a_RoundingMode);
            result = multiplyRoundIfNecessary(
                    result,
                    resultRemainder,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     * Use
     * {@code power(BigDecimal,BigInteger,BigInteger,BigInteger,int,RoundingMode)}
     * instead.
     *
     * @param x
     * @param y1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    @Deprecated
    private static BigDecimal power1(
            BigDecimal x,
            BigInteger y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigInteger Four_BigInteger = BigInteger.valueOf(4);
        if (y.compareTo(Four_BigInteger) != 1) {
            return power(
                    x,
                    y.intValue(),
                    decimalPlaces,
                    a_RoundingMode);
        }
        BigInteger[] party = y.divideAndRemainder(Four_BigInteger);
        BigDecimal result = power1(
                x,
                party[0],
                decimalPlaces,
                a_RoundingMode);
        result = power(
                result,
                4,
                decimalPlaces,
                a_RoundingMode);
        if (party[1].compareTo(BigInteger.ZERO) == 1) {
            BigDecimal resultRemainder = power(
                    x,
                    party[1].intValue(),
                    decimalPlaces,
                    a_RoundingMode);
            result = multiplyRoundIfNecessary(
                    result,
                    resultRemainder,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places.
     *
     * @param x
     * @param y
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256. div is used to divide the calculation up if y is less than div, it
     * is all done in one step. Otherwise it is broken into parts.
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y accurate to decimalPlaces
     */
    //private static BigDecimal power(
    public static BigDecimal power(
            BigDecimal x,
            int y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        return power(
                x,
                BigInteger.valueOf(y),
                div,
                decimalPlaces,
                a_RoundingMode);
//        
//        if (div < 2) {
//            div = 2;
//        } else {
//            if (div > 256) {
//                div = 256;
//            }
//        }
//        // Deal with special cases
//        if (y == 0) {
//            return BigDecimal.ONE;
//        }
//        if (y == 1) {
//            BigDecimal result = new BigDecimal(x.toString());
//            return result;
//            //return round(new BigDecimal(x.toString()), decimalPlaces, a_RoundingMode);
//        }
//        if (y < 0) {
//            BigDecimal powerminusy = power(
//                    x,
//                    -y,
//                    div,
//                    decimalPlaces,
//                    a_RoundingMode);
//            BigDecimal result = reciprocal(
//                    powerminusy,
//                    decimalPlaces,
//                    a_RoundingMode);
//            return result;
//            //return round(result, decimalPlaces, a_RoundingMode);
//        }
//        if (x.compareTo(BigDecimal.ZERO) == -1) {
//            BigDecimal result = power(
//                    x.negate(),
//                    y,
//                    div,
//                    decimalPlaces,
//                    a_RoundingMode);
//            if (Generic_int.isEven(y)) {
//                return result.negate();
//            } else {
//                return result;
//            }
//        }
//        BigDecimal result = BigDecimal.ONE;
//        BigDecimal result0;
//        if (y < div) {
//            result0 = power(
//                    x,
//                    y,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
//            int party0 = y / div;
//            result0 = power(
//                    x,
//                    party0,
//                    div,
//                    decimalPlaces,
//                    a_RoundingMode);
//            result0 = power(
//                    result0,
//                    div,
//                    decimalPlaces,
//                    a_RoundingMode);
//            int party1 = y % div;
//            if (party1 != 0) {
//                BigDecimal resultRemainder = power(
//                        x,
//                        party1,
//                        div / 2,
//                        decimalPlaces,
//                        a_RoundingMode);
//                result0 = multiply(
//                        result0,
//                        resultRemainder,
//                        decimalPlaces,
//                        a_RoundingMode);
//            }
//        }
//        result = multiply(
//                result,
//                result0,
//                decimalPlaces,
//                a_RoundingMode);
//        return result;
//        //return round(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places. If y is negative and a precise
     * answer cannot be given an exception will be thrown.
     *
     * @param x
     * @param y
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256. div is used to divide the calculation up if y is less than div, it
     * is all done in one step. Otherwise it is broken into parts.
     * @return x^y accurate to decimalPlaces
     */
    //private static BigDecimal power(
    public static BigDecimal powerNoRounding(
            BigDecimal x,
            int y,
            int div) {
        return powerNoRounding(
                x,
                BigInteger.valueOf(y),
                div);
    }

    /**
     * x.unscaledValue() == 1 x.precision == 1
     *
     * @param x
     * @param y y >=0
     * @return
     */
    public static BigDecimal powerUnscaled1Precision1(
            BigDecimal x,
            int y) {
        // Special cases
        if (y == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal result;
        int xscale = x.scale();
        int xprecision = x.precision();
        if (xscale == 0) {
            result = x.movePointRight((y - 1) * (xprecision - 1));
        } else {
            result = x.movePointLeft((y - 1) * xscale);
        }
        return result;
    }

    /**
     * x.unscaledValue() == 1 x.precision == 1
     *
     * @param x
     * @param rootRoundIfNecessary rootRoundIfNecessary > 0
     * @param decimalPrecision
     * @return
     */
    public static BigDecimal rootUnscaled1Precision1(
            BigDecimal x,
            int rootRoundIfNecessary,
            int decimalPrecision) {
        // Is there a need for precision?
        BigDecimal result;
        //int decimalPrecision = 10;
        int xscale = x.scale();
        int xprecision = x.precision();
        if (xscale == 0) {
            if (xprecision - rootRoundIfNecessary > 1) {
                result = x.movePointLeft(xprecision - rootRoundIfNecessary);
            } else {
                result = x.movePointLeft(xprecision - rootRoundIfNecessary);
                result = rootRoundIfNecessary(
                        x,
                        rootRoundIfNecessary,
                        decimalPrecision,
                        RoundingMode.UP);
            }
        } else {
            result = x.movePointRight((rootRoundIfNecessary - 1) * xscale);
        }
        return result;
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places. The calculation is divided into
     * parts
     *
     * @param x
     * @param y
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256.
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y accurate to decimalPlaces
     */
    public static BigDecimal power(
            BigDecimal x,
            BigInteger y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        // x = 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // y = 0
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y.compareTo(BigInteger.ONE) == 0) {
            return roundIfNecessary(x, decimalPlaces, a_RoundingMode);
        }
        // y = 2
        if (y.compareTo(Generic_BigInteger.Two) == 0) {
            BigDecimal result = multiplyRoundIfNecessary(
                    x,
                    x,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result = power(
                    x.negate(),
                    y,
                    div,
                    decimalPlaces,
                    a_RoundingMode);
            if (Generic_BigInteger.isEven(y)) {
                return result.negate();
                //return round(result.negate(), decimalPlaces, a_RoundingMode);
            } else {
                return result;
                //return round(result, decimalPlaces, a_RoundingMode);
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheck(
                    x,
                    y.negate(),
                    div,
                    decimalPlaces + 2,
                    a_RoundingMode);
            BigDecimal result = reciprocal(
                    power,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        BigDecimal result = powerNoSpecialCaseCheck(
                x,
                y,
                div,
                decimalPlaces,
                a_RoundingMode);
        return result;
    }

    /**
     * In the case of y being positive. For a negative y, decimalPlace and
     * a_RoundingMode are needed due to a division in the computation. In these
     * cases, the result
     *
     * @param x
     * @param y
     * @param div
     * @return
     */
    public static BigDecimal powerNoRounding(
            BigDecimal x,
            BigInteger y,
            int div) {
        // Deal with special cases
        // x = 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // y = 0
        if (y.compareTo(BigInteger.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y.compareTo(BigInteger.ONE) == 0) {
            return new BigDecimal(x.toString());
        }
        // y = 2
        if (y.compareTo(Generic_BigInteger.Two) == 0) {
            BigDecimal result = x.multiply(x);
            return result;
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result = powerNoRounding(
                    x.negate(),
                    y,
                    div);
            if (Generic_BigInteger.isEven(y)) {
                return result.negate();
                //return round(result.negate(), decimalPlaces, a_RoundingMode);
            } else {
                return result;
                //return round(result, decimalPlaces, a_RoundingMode);
            }
        }
        // y < 0
        if (y.compareTo(BigInteger.ZERO) == -1) {
            BigDecimal power = powerNoSpecialCaseCheckNoRounding(
                    x,
                    y.negate(),
                    div);
            BigDecimal result = new BigDecimal(
                    reciprocalWillBeIntegerReturnBigInteger(power));
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        BigDecimal result = powerNoSpecialCaseCheckNoRounding(
                x,
                y,
                div);
        return result;
    }

    /**
     * Calculates and returns x raised to the power of y accurate to
     * decimalPlaces number of decimal places. The calculation is divided into
     * parts
     *
     * @param x
     * @param y
     * @param div If div &lt; 2 it is set to 2. If div &gt; 256 it is set to
     * 256.
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y accurate to decimalPlaces
     */
    public static BigDecimal powerNoSpecialCaseCheck(
            BigDecimal x,
            BigInteger y,
            int div,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (div < 2) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal result;// = BigDecimal.ONE;
        BigDecimal result0;
        BigDecimal result1;
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        if (y1.compareTo(div_BigInteger) != 1) {
            while (y1.compareTo(div_BigInteger) != 1) {
                div_BigInteger = div_BigInteger.divide(Generic_BigInteger.Two);
                div = div / 2;
            }
        }
        yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
        if (yDivideAndRemainder[0].compareTo(BigInteger.ONE) == 0) {
            if (yDivideAndRemainder[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    result = power(
                            x,
                            y1.intValue(),
                            //a_MathContext,
                            decimalPlaces,
                            a_RoundingMode);
                    if (result.scale() > decimalPlaces) {
                        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
                    }
                    return result;
                } else {
                    div /= 2;
                    result = power(
                            x,
                            y,
                            div,
                            //a_MathContext,
                            decimalPlaces,
                            a_RoundingMode);
                    if (result.scale() > decimalPlaces) {
                        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
                    }
                    return result;
                }
            } else {
                if (div < 4) {
                    result0 = power(
                            x,
                            div,
                            //a_MathContext
                            decimalPlaces + div + 2,
                            //decimalPlaces,
                            a_RoundingMode);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = power(
                            x,
                            remainingY.intValue(),
                            //a_MathContext,
                            decimalPlaces + remainingY.intValue() + 2,
                            //decimalPlaces,
                            a_RoundingMode);
                    result = multiplyRoundIfNecessary(
                            result0,
                            result1,
                            //a_MathContext,
                            decimalPlaces,
                            a_RoundingMode);
                    return result;
                } else {
                    result0 = power(
                            x,
                            div_BigInteger,
                            div,
                            decimalPlaces + div + 2,
                            //decimalPlaces + 6,
                            a_RoundingMode);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = power(
                            x,
                            remainingY.intValue(),
                            div,
                            //a_MathContext,
                            decimalPlaces + remainingY.intValue() + 2,
                            //decimalPlaces,
                            a_RoundingMode);
                    result = multiplyRoundIfNecessary(
                            result0,
                            result1,
                            decimalPlaces,
                            a_RoundingMode);
                    return result;
                }
            }
        }
        result0 = power(
                x,
                div_BigInteger,
                div,
                decimalPlaces + div + 2,
                //decimalPlaces,
                a_RoundingMode);
        result0 = power(
                result0,
                yDivideAndRemainder[0],
                div,
                decimalPlaces + yDivideAndRemainder[0].intValue() + 2,
                //decimalPlaces + 4,
                a_RoundingMode);
        //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
        BigInteger remainingY = yDivideAndRemainder[1];
        result1 = power(
                x,
                remainingY,
                div,
                decimalPlaces + remainingY.intValue() + 2,
                //decimalPlaces + 2,
                a_RoundingMode);
        result = multiplyRoundIfNecessary(
                result0,
                result1,
                decimalPlaces,
                a_RoundingMode);
        return result;
    }

    public static BigDecimal powerNoSpecialCaseCheckNoRounding(
            BigDecimal x,
            BigInteger y,
            int div) {
        if (div < 2) {
            div = 2;
        } else {
            if (div > 256) {
                div = 256;
            }
        }
        BigInteger div_BigInteger = BigInteger.valueOf(div);
        BigDecimal result;// = BigDecimal.ONE;
        BigDecimal result0;
        BigDecimal result1;
        BigInteger y1 = new BigInteger(y.toString());
        BigInteger[] yDivideAndRemainder;
        if (y1.compareTo(div_BigInteger) != 1) {
            while (y1.compareTo(div_BigInteger) != 1) {
                div_BigInteger = div_BigInteger.divide(Generic_BigInteger.Two);
                div = div / 2;
            }
        }
        yDivideAndRemainder = y1.divideAndRemainder(div_BigInteger);
        if (yDivideAndRemainder[0].compareTo(BigInteger.ONE) == 0) {
            if (yDivideAndRemainder[1].compareTo(BigInteger.ZERO) == 0) {
                if (div < 6) {
                    result = powerNoRounding(
                            x,
                            y1.intValue());
                    return result;
                } else {
                    div /= 2;
                    result = powerNoRounding(
                            x,
                            y,
                            div);
                    return result;
                }
            } else {
                if (div < 4) {
                    result0 = powerNoRounding(
                            x,
                            div);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = powerNoRounding(
                            x,
                            remainingY.intValue());
                    result = result0.multiply(result1);
                    return result;
                } else {
                    result0 = powerNoRounding(
                            x,
                            div_BigInteger,
                            div);
                    //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
                    BigInteger remainingY = yDivideAndRemainder[1];
                    result1 = powerNoRounding(
                            x,
                            remainingY.intValue(),
                            div);
                    result = result0.multiply(result1);
                    return result;
                }
            }
        }
        result0 = powerNoRounding(
                x,
                div_BigInteger,
                div);
        result0 = powerNoRounding(
                result0,
                yDivideAndRemainder[0],
                div);
        //BigInteger remainingY = y1.subtract(yDivideAndRemainder[0].multiply(div_BigInteger));
        BigInteger remainingY = yDivideAndRemainder[1];
        result1 = powerNoRounding(
                x,
                remainingY,
                div);
        result = result0.multiply(result1);
        return result;
    }

    /**
     * Use {@code power(BigDecimal,int,int,int,int,RoundingMode)} instead.
     *
     * @param x
     * @param y1 > 1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    @Deprecated
    private static BigDecimal power1(
            BigDecimal x,
            int y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (y < 4) {
            return power(
                    x,
                    y,
                    decimalPlaces,
                    a_RoundingMode);
        }
        int party0 = y / 4;
        BigDecimal result = power1(
                x,
                party0,
                decimalPlaces,
                a_RoundingMode);
        result = power(
                result,
                4,
                //a_MathContext,
                decimalPlaces,
                a_RoundingMode);
        int party1 = y % 4;
        if (party1 != 0) {
            BigDecimal resultRemainder = power(
                    x,
                    party1,
                    //a_MathContext,
                    decimalPlaces,
                    a_RoundingMode);
            result = multiplyRoundIfNecessary(
                    result,
                    resultRemainder,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return result;
    }

    /**
     *
     * @param x
     * @param y 1 &lt; y &lt; 100
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    @Deprecated
    private static BigDecimal power0(
            BigDecimal x,
            int y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.
        BigDecimal result = new BigDecimal(x.toString());
//        for (int i = 1; i < y1; i++) {
//            result = multiply(
//                    result,
//                    x,
//                    decimalPlaces,
//                    a_RoundingMode);
//        }
//        return result;
        if (y < 10) {
            for (int i = 1; i < y; i++) {
                result = multiplyRoundIfNecessary(
                        result,
                        x,
                        decimalPlaces, // May not be sufficent for x < 1!
                        a_RoundingMode);
            }
            return result;
        }
        // Double up
        int i;
        for (i = 2; i < y; i *= 2) {
            result = multiplyRoundIfNecessary(
                    result,
                    result,
                    decimalPlaces, // May not be sufficent for x < 1!
                    a_RoundingMode);
        }
        // Calculate remainder
        i /= 2;
        BigDecimal remainderResult = power(
                x,
                y - i,
                //a_MathContext,
                decimalPlaces, // May not be sufficent for x < 1!
                a_RoundingMode);
        // Multiply together
        result = multiplyRoundIfNecessary(
                result,
                remainderResult,
                decimalPlaces, // May not be sufficent for x < 1!
                a_RoundingMode);
        return result;
    }

    /**
     * @param x
     * @param y 1 &lt; y &lt; 100
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x^y correct to decimalPlaces number of decimal places using
     * a_Rounding mode to round if necessary. For y > 8 it is better to use
     * power(BigDecimal,int,int,int,RoundingMode). For y &lt 2 this will return
     * a copy of x which is probably not what is wanted. This method should not
     * be used for such calculations. No check is made for efficiency reasons.
     */
    private static BigDecimal power(
            BigDecimal x,
            int y,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.
        BigDecimal result = new BigDecimal(x.toString());
        int decimalPlacesMultiply;
        for (int i = 1; i < y; i++) {
            decimalPlacesMultiply = (result.scale() * x.scale()) + 1;
            decimalPlacesMultiply = Math.min(
                    decimalPlacesMultiply,
                    decimalPlaces + 2 + y);
            result = multiplyRoundIfNecessary(
                    result,
                    x,
                    decimalPlacesMultiply,
                    a_RoundingMode);
        }
        if (result.scale() > decimalPlaces) {
            roundIfNecessary(result, decimalPlaces, a_RoundingMode);
        }
        return result;
    }

    /**
     * Calculates and returns an accurate representation of x^y. The method as
     * implemented duplicates x and multiplies this duplicate by x y times. For
     * large values of y this can be slow. A method which divides the
     * multiplication into more parts is provided by
     * <code>powerNoRounding(BigDecimal,int,int)</code>. Values of x that are
     * greater than one and have a scale greater than will have a both increased
     * scale and magnitude and the result gets effectively increments the scale
     * and magnitude with each multiplication. Consequently, this may not be the
     * most appropriate multiplication method to use. If the precision of the
     * result required in terms of significant digits or decimal places can be
     * specified then these parameters can be passed to other methods will
     * return the result faster albeit it rounded to the specified precision
     * using either a default or input RoundingMode. The scale of the result is
     * y times the scale of x.
     *
     * @param x
     * @param y
     * @return
     */
    public static BigDecimal powerNoRounding(
            BigDecimal x,
            int y) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.
        BigDecimal result = new BigDecimal(x.toString());
        for (int i = 1; i < y; i++) {
            result = result.multiply(x);
        }
        return result;
    }

    /**
     * x^(a+b) = x^a * x^b
     *
     * @param x
     * @param y
     * @param a_MathContext
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    private static BigDecimal power(
            BigDecimal x,
            int y,
            MathContext a_MathContext,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // x.pow(y) returned a mathematically incorrect result for
        // x = 1.0000000000000000000000000001, y1 = 100
        // Is x a "supernormal" number in this context?
        //BigDecimal result = x.pow(y1);// Don't attempt to support "supernormal" numbers.

        // Deal with special cases
        // x = 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // x = 1
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        // y = 0
        if (y == 0) {
            return BigDecimal.ONE;
        }
        // y = 1
        if (y == 1) {
            return roundIfNecessary(x, decimalPlaces, a_RoundingMode);
        }
        // y = 2
        if (y == 2) {
            BigDecimal result = multiplyRoundIfNecessary(
                    x,
                    x,
                    a_MathContext,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
        }
        // x < 0
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result = power(
                    x.negate(),
                    y,
                    //a_MathContext,
                    decimalPlaces,
                    a_RoundingMode);
            if ((double) y / 2.0d > y / 2) {
                return result;
            } else {
                return result.negate();
            }
        }
        // y < 0
        if (y < 0) {
            BigDecimal power = powerNoSpecialCaseCheck(
                    x,
                    -y,
                    a_MathContext,
                    decimalPlaces + 2,
                    a_RoundingMode);
            BigDecimal result = reciprocal(
                    power,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        BigDecimal result = powerNoSpecialCaseCheck(
                x,
                y,
                a_MathContext,
                decimalPlaces,
                a_RoundingMode);
        return result;
    }

    private static BigDecimal powerNoSpecialCaseCheck(
            BigDecimal x,
            int y,
            MathContext a_MathContext,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigInteger x_BigInteger = x.toBigInteger();
        if (x_BigInteger.compareTo(BigInteger.ZERO) != 0) {
            BigInteger integerPartResult = x_BigInteger.pow(y);
            BigDecimal xRemainder = x.subtract(new BigDecimal(integerPartResult));
            BigDecimal fractionalPartResult;
            if (xRemainder.compareTo(BigDecimal.ZERO) == 1) {
                fractionalPartResult = powerNoSpecialCaseCheck(
                        xRemainder,
                        y,
                        a_MathContext,
                        decimalPlaces,
                        a_RoundingMode);
            }
            BigDecimal result = multiplyRoundIfNecessary(
                    xRemainder,
                    x_BigInteger,
                    a_MathContext,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
        } else {
            BigDecimal result = new BigDecimal(x.toString());
            int decimalPlacesMultiply;
            int xScale = x.scale();
            for (int i = 1; i < y; i++) {
                decimalPlacesMultiply = (result.scale() + x.scale()) + 1;
                decimalPlacesMultiply = Math.min(
                        decimalPlacesMultiply,
                        decimalPlaces + 2 + y);
                if (decimalPlacesMultiply < a_MathContext.getPrecision()) {
                    result = multiplyRoundIfNecessary(
                            result,
                            x,
                            a_MathContext,
                            decimalPlacesMultiply,
                            a_RoundingMode);
                }
            }
            if (result.scale() > decimalPlaces) {
                roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            }
            return result;
        }
    }

    /**
     * @param x
     * @param decimalPlace
     * @param a_RoundingMode
     * @return 1/x Accurate to decimalPlace number of decimal places. Throws an
     * ArithmeticException - if divisor is zero,
     * roundingMode==RoundingMode.UNNECESSARY and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    public static BigDecimal reciprocal(
            BigDecimal x,
            int decimalPlace,
            RoundingMode a_RoundingMode) {
        BigDecimal result = BigDecimal.ONE.divide(
                x,
                decimalPlace,
                a_RoundingMode);
        return result;
//        return roundIfNecessary(result, decimalPlace, a_RoundingMode);
    }

    /**
     * @param x
     * @return 1/x if toCompare is an integer or throws a ArithmeticException.
     * If x = 0 or the result cannot be returned exactly, an ArithmeticException
     * is thrown
     */
    public static BigInteger reciprocalWillBeIntegerReturnBigInteger(
            BigDecimal x) {
        BigDecimal result_BigDecimal = reciprocalWillBeIntegerReturnBigDecimal(
                x);
        return result_BigDecimal.toBigIntegerExact();
    }

    /**
     * @param x
     * @return 1/x if toCompare is an integer or throws a ArithmeticException.
     * If x = 0 or the result cannot be returned exactly, an ArithmeticException
     * is thrown
     */
    public static BigDecimal reciprocalWillBeIntegerReturnBigDecimal(
            BigDecimal x) {
        return BigDecimal.ONE.divide(
                x,
                0,
                RoundingMode.UNNECESSARY);
    }

//    /**
//     * Adapted from http://everything2.com/index.pl?node_id=946812
//     * @param x
//     * @param decimalPlaces
//     * @return
//     */
//    public BigDecimal lg(
//            BigDecimal x,
//            int decimalPlaces) {
//        final int NUM_OF_DIGITS = decimalPlaces + 2;
//        // need to add one to get the right number of decimalPlaces
//        //  and then add one again to get the next number
//        //  so I can round it correctly.
//        MathContext mc = new MathContext(NUM_OF_DIGITS, RoundingMode.HALF_EVEN);
//        //special conditions:
//        // log(-toCompare) -> exception
//        // log(1) == 0 exactly;
//        // log of a number lessthan one = -log(1/toCompare)
//        if (x.signum() <= 0) {
//            throw new ArithmeticException("log of a negative number! (or zero)");
//        } else if (x.compareTo(BigDecimal.ONE) == 0) {
//            return BigDecimal.ZERO;
//        } else if (x.compareTo(BigDecimal.ONE) < 0) {
//            return (lg((BigDecimal.ONE).divide(x, mc), decimalPlaces)).negate();
//        }
//        StringBuffer sb = new StringBuffer();
//        //number of digits on the left of the decimal point
//        int leftDigits = x.precision() - x.scale();
//        //so, the first digits of the log10 are:
//        sb.append(leftDigits - 1).append(".");
//        //this is the algorithm outlined in the webpage
//        int n = 0;
//        while (n < NUM_OF_DIGITS) {
//            x = (x.movePointLeft(leftDigits - 1)).pow(10, mc);
//            leftDigits = x.precision() - x.scale();
//            sb.append(leftDigits - 1);
//            n++;
//        }
//        BigDecimal ans = new BigDecimal(sb.toString());
//        //Round the number to the correct number of decimal places.
//        ans = ans.round(new MathContext(ans.precision() - ans.scale() + decimalPlaces, RoundingMode.HALF_EVEN));
//        return ans;
//    }
    /**
     * Adapted from
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     * "Pseudocode algorithm for doing a logarithm. Assuming we want log_n of
     * toCompare
     *
     * toCompare = 0; base = x_BigDecimal; input = toCompare; while (input >
     * base) toCompare++; input /= base; fraction = 1/2; input *= input; while
     * (((toCompare + fraction) > toCompare) && (input > 1)) if (input > base)
     * input /= base; toCompare += fraction; input *= input; fraction /= 2.0;
     *
     * The big while loop may seem a bit confusing. On each pass, you can either
     * square your input or you can take the square rootRoundIfNecessary of your
     * base; either way, you must divide your fraction by 2. I find squaring the
     * input, and leaving the base alone, to be more accurate. If the input goes
     * to 1, we're through. The log of 1, for any base, is 0, which means we
     * don't need to add any more. if (toCompare + fraction) is not greater than
     * toCompare, then we've hit the limits of precision for our numbering
     * system. We can stop. Obviously, if you're working with a system which has
     * arbitrarily many digits of precision, you will want to put something else
     * in there to limit the loop."
     * @param a_RoundingMode
     * @param decimalPlaces
     * @return 
     */
    public static BigDecimal log(
            int base_int,
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("x <= 0");
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ZERO;
        }
        if (new BigDecimal("" + base_int).compareTo(x) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            BigDecimal oneOverX = Generic_BigDecimal.reciprocal(
                    x, 
                    decimalPlaces, 
                    a_RoundingMode);
            BigDecimal logOneOverX = Generic_BigDecimal.log(
                    base_int,
                    oneOverX,
                    decimalPlaces,
                    a_RoundingMode);
            return logOneOverX.negate();
        }
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal input = new BigDecimal(x.toString());
        int scale = input.precision() + decimalPlaces;
        int maxite = Math.max(base_int, 10000);
        int ite = 0;
        BigDecimal maxError_BigDecimal = new BigDecimal(
                BigInteger.ONE, decimalPlaces + 1);
        //System.out.println("epsilon_BigDecimal " + epsilon_BigDecimal);
        //System.out.println("scale " + scale);
        BigDecimal base_BigDecimal = new BigDecimal(base_int);
        while (input.compareTo(base_BigDecimal) == 1) {
            result = result.add(BigDecimal.ONE);
            input = input.divide(base_BigDecimal, scale, a_RoundingMode);
        }
        BigDecimal fraction = new BigDecimal("0.5");
        input = input.multiply(input);
        BigDecimal resultplusfraction = result.add(fraction);
        int decimalPlacesSafer = Math.max(decimalPlaces * 2, decimalPlaces + 10); // Not sure this is safe enough, maybe log(base_int,maxite,0) would be?

        boolean condition = true;
        do {
//        while (((resultplusfraction).compareTo(result) == 1)
//                && (input.compareTo(BigDecimal.ONE) == 1)) {
            if (input.compareTo(base_BigDecimal) == 1) {
                input = input.divide(base_BigDecimal, scale, a_RoundingMode);
                result = result.add(fraction);
            }
            input = multiplyRoundIfNecessary(
                    input,
                    input,
                    decimalPlacesSafer,
                    a_RoundingMode);
            fraction = fraction.divide(TWO, scale, a_RoundingMode);
            resultplusfraction = result.add(fraction);
            if (fraction.abs().compareTo(maxError_BigDecimal) == -1) {
                break;
            }
            if (maxite == ite) {
                System.out.println(
                        "Warning: maxite reached in "
                        + Generic_BigDecimal.class.getName()
                        + ".log(int,BigDecimal,int,RoundingMode) "
                        + " log("
                        + base_int + ", "
                        + x + ", "
                        + decimalPlaces + ", "
                        + a_RoundingMode.toString() + ") "
                        + result.toString());
                break;
            }
            ite++;

            condition =
                    ((resultplusfraction).compareTo(result) == 1)
                    && (input.compareTo(BigDecimal.ONE) == 1);

        } while (condition);
        
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * Only works for x > 0.
     *
     * @param base
     * @param x
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The log of x to the base base to decimalPlace precision
     */
    public static BigDecimal log(
            BigDecimal base,
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;// = BigDecimal.ZERO;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException("x <= 0");
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ZERO;
        }
        if (base.compareTo(x) == 0) {
            return BigDecimal.ONE;
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xprecision = x.precision();
            BigInteger unscaledx = x.unscaledValue();
            int unscaledxprecision = unscaledx.toString().length();
            int index = xprecision - unscaledxprecision;
            BigDecimal unscaledxmajor = new BigDecimal(unscaledx, index);
            //System.out.print(unscaledxmajor);
            BigDecimal logunscaledxmajor = log(
                    base,
                    unscaledxmajor,
                    decimalPlaces + index,
                    a_RoundingMode);
            result = logunscaledxmajor.subtract(BigDecimal.valueOf(index));
            return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
        }
        result = BigDecimal.ZERO;
        BigDecimal input = new BigDecimal(x.toString());
        int scale = input.precision() + decimalPlaces;
        BigInteger maxite = Generic_BigInteger.max(
                x.toBigInteger(),
                BigInteger.valueOf(10000));
        BigInteger ite = BigInteger.ZERO;
        BigDecimal maxError_BigDecimal = new BigDecimal(
                BigInteger.ONE,
                decimalPlaces + 1);
        //System.out.println("epsilon_BigDecimal " + epsilon_BigDecimal);
        //System.out.println("scale " + scale);
        while (input.compareTo(base) == 1) {
            result = result.add(BigDecimal.ONE);
            input = input.divide(base, scale, a_RoundingMode);
        }
        BigDecimal fraction = new BigDecimal("0.5");
        input = input.multiply(input);
        BigDecimal resultplusfraction = result.add(fraction);
        int decimalPlacesSafer = Math.max(decimalPlaces * 2, decimalPlaces + 10); // Safe in terms of precision? Maybe log(base_int,maxite,0) would be more appropriate?
        while (((resultplusfraction).compareTo(result) == 1)
                && (input.compareTo(BigDecimal.ONE) == 1)) {
            if (input.compareTo(base) == 1) {
                input = input.divide(base, scale, a_RoundingMode);
                result = result.add(fraction);
            }
            input = multiplyRoundIfNecessary(
                    input,
                    input,
                    decimalPlacesSafer,
                    a_RoundingMode);
            fraction = fraction.divide(TWO, scale, a_RoundingMode);
            resultplusfraction = result.add(fraction);
            if (fraction.abs().compareTo(maxError_BigDecimal) == -1) {
                break;
            }
            if (maxite.compareTo(ite) == 0) {
                System.out.println(
                        "Warning: maxite reached in "
                        + Generic_BigDecimal.class.getName()
                        + ".log(BigDecimal,BigDecimal,int,RoundingMode) "
                        + " log("
                        + base + ", "
                        + x + ", "
                        + decimalPlaces + ", "
                        + a_RoundingMode.toString() + ") "
                        + result.toString());
                break;
            }
            ite = ite.add(BigInteger.ONE);
        }
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * @param x
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x rounded if necessary and with a scale set to decimalPlaces
     */
    public static BigDecimal roundToAndSetDecimalPlaces(
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;
        int xScale = x.scale();
        if (xScale > decimalPlaces) {
            result = x.setScale(decimalPlaces, a_RoundingMode);
            return result;
        }
        result = x.setScale(decimalPlaces);
        return result;
    }

    /**
     * @param x
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x rounded to decimalPlaces with any trailing zeros stripped
     */
    public static BigDecimal roundStrippingTrailingZeros(
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;
        int xScale = x.scale();
        if (xScale > decimalPlaces) {
            result = x.setScale(decimalPlaces, a_RoundingMode);
            return result;
        }
        result = x.stripTrailingZeros();
        return result;
    }

    /**
     * This is not necessarily an immutable operation as x may be returned!
     *
     * @param x
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return x rounded to decimalPlaces if the scale of x is greater than
     * decimal places. Otherwise returns x.
     */
    public static BigDecimal roundIfNecessary(
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (x.scale() > decimalPlaces) {
            return roundIfNecessaryNoScaleCheck(
                    x,
                    decimalPlaces,
                    a_RoundingMode);
        }
        return x;
    }

    private static BigDecimal roundIfNecessaryNoScaleCheck(
            BigDecimal x,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result = x.setScale(decimalPlaces, a_RoundingMode);
        return result;
    }

    /**
     *
     * @param value
     * @param significantDigits
     * @return
     */
    public static int getDecimalPlacePrecision(
            BigDecimal value,
            int significantDigits) {
        int result = 0;
        if (value != null) {
            int scale = value.scale();
            int precision = value.precision();
            int integerScale = precision - scale;
            if (integerScale < 0) {
                // e.g. 0.00123467891011; scale = 15, precision = 12
                result = (integerScale * -1) + significantDigits;
            } else {
                // e.g. 192.1231213; scale = 7, precision = 10
                if (integerScale < significantDigits) {
                    result = significantDigits - integerScale;
                } else {
                    result = 1;
                }
            }
        }
        return result;
    }

    /**
     * Initialises _PI with first 10,000 digits from
     * http://www.eveandersson.com/pi/digits/ on 2011-01-14 From this site a
     * million digits are also available...
     */
    private void init_PI() {
        _PI = new BigDecimal(
                "3."
                + "14159265358979323846264338327950288419716939937510"
                + "58209749445923078164062862089986280348253421170679"
                + "82148086513282306647093844609550582231725359408128"
                + "48111745028410270193852110555964462294895493038196"
                + "44288109756659334461284756482337867831652712019091"
                + "45648566923460348610454326648213393607260249141273"
                + "72458700660631558817488152092096282925409171536436"
                + "78925903600113305305488204665213841469519415116094"
                + "33057270365759591953092186117381932611793105118548"
                + "07446237996274956735188575272489122793818301194912"
                + "98336733624406566430860213949463952247371907021798"
                + "60943702770539217176293176752384674818467669405132"
                + "00056812714526356082778577134275778960917363717872"
                + "14684409012249534301465495853710507922796892589235"
                + "42019956112129021960864034418159813629774771309960"
                + "51870721134999999837297804995105973173281609631859"
                + "50244594553469083026425223082533446850352619311881"
                + "71010003137838752886587533208381420617177669147303"
                + "59825349042875546873115956286388235378759375195778"
                + "18577805321712268066130019278766111959092164201989"
                + "38095257201065485863278865936153381827968230301952"
                + "03530185296899577362259941389124972177528347913151"
                + "55748572424541506959508295331168617278558890750983"
                + "81754637464939319255060400927701671139009848824012"
                + "85836160356370766010471018194295559619894676783744"
                + "94482553797747268471040475346462080466842590694912"
                + "93313677028989152104752162056966024058038150193511"
                + "25338243003558764024749647326391419927260426992279"
                + "67823547816360093417216412199245863150302861829745"
                + "55706749838505494588586926995690927210797509302955"
                + "32116534498720275596023648066549911988183479775356"
                + "63698074265425278625518184175746728909777727938000"
                + "81647060016145249192173217214772350141441973568548"
                + "16136115735255213347574184946843852332390739414333"
                + "45477624168625189835694855620992192221842725502542"
                + "56887671790494601653466804988627232791786085784383"
                + "82796797668145410095388378636095068006422512520511"
                + "73929848960841284886269456042419652850222106611863"
                + "06744278622039194945047123713786960956364371917287"
                + "46776465757396241389086583264599581339047802759009"
                + "94657640789512694683983525957098258226205224894077"
                + "26719478268482601476990902640136394437455305068203"
                + "49625245174939965143142980919065925093722169646151"
                + "57098583874105978859597729754989301617539284681382"
                + "68683868942774155991855925245953959431049972524680"
                + "84598727364469584865383673622262609912460805124388"
                + "43904512441365497627807977156914359977001296160894"
                + "41694868555848406353422072225828488648158456028506"
                + "01684273945226746767889525213852254995466672782398"
                + "64565961163548862305774564980355936345681743241125"
                + "15076069479451096596094025228879710893145669136867"
                + "22874894056010150330861792868092087476091782493858"
                + "90097149096759852613655497818931297848216829989487"
                + "22658804857564014270477555132379641451523746234364"
                + "54285844479526586782105114135473573952311342716610"
                + "21359695362314429524849371871101457654035902799344"
                + "03742007310578539062198387447808478489683321445713"
                + "86875194350643021845319104848100537061468067491927"
                + "81911979399520614196634287544406437451237181921799"
                + "98391015919561814675142691239748940907186494231961"
                + "56794520809514655022523160388193014209376213785595"
                + "66389377870830390697920773467221825625996615014215"
                + "03068038447734549202605414665925201497442850732518"
                + "66600213243408819071048633173464965145390579626856"
                + "10055081066587969981635747363840525714591028970641"
                + "40110971206280439039759515677157700420337869936007"
                + "23055876317635942187312514712053292819182618612586"
                + "73215791984148488291644706095752706957220917567116"
                + "72291098169091528017350671274858322287183520935396"
                + "57251210835791513698820914442100675103346711031412"
                + "67111369908658516398315019701651511685171437657618"
                + "35155650884909989859982387345528331635507647918535"
                + "89322618548963213293308985706420467525907091548141"
                + "65498594616371802709819943099244889575712828905923"
                + "23326097299712084433573265489382391193259746366730"
                + "58360414281388303203824903758985243744170291327656"
                + "18093773444030707469211201913020330380197621101100"
                + "44929321516084244485963766983895228684783123552658"
                + "21314495768572624334418930396864262434107732269780"
                + "28073189154411010446823252716201052652272111660396"
                + "66557309254711055785376346682065310989652691862056"
                + "47693125705863566201855810072936065987648611791045"
                + "33488503461136576867532494416680396265797877185560"
                + "84552965412665408530614344431858676975145661406800"
                + "70023787765913440171274947042056223053899456131407"
                + "11270004078547332699390814546646458807972708266830"
                + "63432858785698305235808933065757406795457163775254"
                + "20211495576158140025012622859413021647155097925923"
                + "09907965473761255176567513575178296664547791745011"
                + "29961489030463994713296210734043751895735961458901"
                + "93897131117904297828564750320319869151402870808599"
                + "04801094121472213179476477726224142548545403321571"
                + "85306142288137585043063321751829798662237172159160"
                + "77166925474873898665494945011465406284336639379003"
                + "97692656721463853067360965712091807638327166416274"
                + "88880078692560290228472104031721186082041900042296"
                + "61711963779213375751149595015660496318629472654736"
                + "42523081770367515906735023507283540567040386743513"
                + "62222477158915049530984448933309634087807693259939"
                + "78054193414473774418426312986080998886874132604721"
                + "56951623965864573021631598193195167353812974167729"
                + "47867242292465436680098067692823828068996400482435"
                + "40370141631496589794092432378969070697794223625082"
                + "21688957383798623001593776471651228935786015881617"
                + "55782973523344604281512627203734314653197777416031"
                + "99066554187639792933441952154134189948544473456738"
                + "31624993419131814809277771038638773431772075456545"
                + "32207770921201905166096280490926360197598828161332"
                + "31666365286193266863360627356763035447762803504507"
                + "77235547105859548702790814356240145171806246436267"
                + "94561275318134078330336254232783944975382437205835"
                + "31147711992606381334677687969597030983391307710987"
                + "04085913374641442822772634659470474587847787201927"
                + "71528073176790770715721344473060570073349243693113"
                + "83504931631284042512192565179806941135280131470130"
                + "47816437885185290928545201165839341965621349143415"
                + "95625865865570552690496520985803385072242648293972"
                + "85847831630577775606888764462482468579260395352773"
                + "48030480290058760758251047470916439613626760449256"
                + "27420420832085661190625454337213153595845068772460"
                + "29016187667952406163425225771954291629919306455377"
                + "99140373404328752628889639958794757291746426357455"
                + "25407909145135711136941091193932519107602082520261"
                + "87985318877058429725916778131496990090192116971737"
                + "27847684726860849003377024242916513005005168323364"
                + "35038951702989392233451722013812806965011784408745"
                + "19601212285993716231301711444846409038906449544400"
                + "61986907548516026327505298349187407866808818338510"
                + "22833450850486082503930213321971551843063545500766"
                + "82829493041377655279397517546139539846833936383047"
                + "46119966538581538420568533862186725233402830871123"
                + "28278921250771262946322956398989893582116745627010"
                + "21835646220134967151881909730381198004973407239610"
                + "36854066431939509790190699639552453005450580685501"
                + "95673022921913933918568034490398205955100226353536"
                + "19204199474553859381023439554495977837790237421617"
                + "27111723643435439478221818528624085140066604433258"
                + "88569867054315470696574745855033232334210730154594"
                + "05165537906866273337995851156257843229882737231989"
                + "87571415957811196358330059408730681216028764962867"
                + "44604774649159950549737425626901049037781986835938"
                + "14657412680492564879855614537234786733039046883834"
                + "36346553794986419270563872931748723320837601123029"
                + "91136793862708943879936201629515413371424892830722"
                + "01269014754668476535761647737946752004907571555278"
                + "19653621323926406160136358155907422020203187277605"
                + "27721900556148425551879253034351398442532234157623"
                + "36106425063904975008656271095359194658975141310348"
                + "22769306247435363256916078154781811528436679570611"
                + "08615331504452127473924544945423682886061340841486"
                + "37767009612071512491404302725386076482363414334623"
                + "51897576645216413767969031495019108575984423919862"
                + "91642193994907236234646844117394032659184044378051"
                + "33389452574239950829659122850855582157250310712570"
                + "12668302402929525220118726767562204154205161841634"
                + "84756516999811614101002996078386909291603028840026"
                + "91041407928862150784245167090870006992821206604183"
                + "71806535567252532567532861291042487761825829765157"
                + "95984703562226293486003415872298053498965022629174"
                + "87882027342092222453398562647669149055628425039127"
                + "57710284027998066365825488926488025456610172967026"
                + "64076559042909945681506526530537182941270336931378"
                + "51786090407086671149655834343476933857817113864558"
                + "73678123014587687126603489139095620099393610310291"
                + "61615288138437909904231747336394804575931493140529"
                + "76347574811935670911013775172100803155902485309066"
                + "92037671922033229094334676851422144773793937517034"
                + "43661991040337511173547191855046449026365512816228"
                + "82446257591633303910722538374218214088350865739177"
                + "15096828874782656995995744906617583441375223970968"
                + "34080053559849175417381883999446974867626551658276"
                + "58483588453142775687900290951702835297163445621296"
                + "40435231176006651012412006597558512761785838292041"
                + "97484423608007193045761893234922927965019875187212"
                + "72675079812554709589045563579212210333466974992356"
                + "30254947802490114195212382815309114079073860251522"
                + "74299581807247162591668545133312394804947079119153"
                + "26734302824418604142636395480004480026704962482017"
                + "92896476697583183271314251702969234889627668440323"
                + "26092752496035799646925650493681836090032380929345"
                + "95889706953653494060340216654437558900456328822505"
                + "45255640564482465151875471196218443965825337543885"
                + "69094113031509526179378002974120766514793942590298"
                + "96959469955657612186561967337862362561252163208628"
                + "69222103274889218654364802296780705765615144632046"
                + "92790682120738837781423356282360896320806822246801"
                + "22482611771858963814091839036736722208883215137556"
                + "00372798394004152970028783076670944474560134556417"
                + "25437090697939612257142989467154357846878861444581"
                + "23145935719849225284716050492212424701412147805734"
                + "55105008019086996033027634787081081754501193071412"
                + "23390866393833952942578690507643100638351983438934"
                + "15961318543475464955697810382930971646514384070070"
                + "73604112373599843452251610507027056235266012764848"
                + "30840761183013052793205427462865403603674532865105"
                + "70658748822569815793678976697422057505968344086973"
                + "50201410206723585020072452256326513410559240190274"
                + "21624843914035998953539459094407046912091409387001"
                + "26456001623742880210927645793106579229552498872758"
                + "46101264836999892256959688159205600101655256375678");
    }

    /**
     * @return _PI which is initialised with 10000 decimal places.
     */
    public BigDecimal get_PI() {
        if (_PI == null) {
            init_PI();
        }
        return _PI;
    }

//    /**
//     * Returns _PI rounded to decimalPlaces decimal places. Only supported for
//     * 0 < decimal places < 10001.
//     */
//    public BigDecimal get_PI(
//            int decimalPlaces) {
//        if (decimalPlaces < 10000) {
//            return round(get_PI(), decimalPlaces, get_RoundingMode());
//        } else {
//            throw new UnsupportedOperationException();
//        }
//    }
//    /**
//     * Returns _PI rounded to decimalPlaces decimal places.
//     */
//    @Deprecated
//    private BigDecimal getPI(
//            int decimalPlaces) {
//        if (decimalPlaces < 10000) {
//            return round(get_PI(), decimalPlaces, get_RoundingMode());
//        } else {
//            throw new UnsupportedOperationException();
////        _PI = BigDecimal.ZERO;
////        BigDecimal e = getE(decimalPlaces);
////        int maxite = 10;
////
////        // David Chudnovsky and Gregory Chudnovsky:
////        // Sum for all k > 0 1/(12((-1^k(6k)!(13591409+54514013k))/(3k)!((k!)^3)640320^(3k+3/2)))
////        // Sum for all k > 0 1/(12((-1^k(6k)!d)/(3k!)((k!)^3)640320^(3k+3/2)))
////        if (_Generic_BigInteger == null) {
////            init_Factorial_Generic_BigInteger(maxite);
////        } else {
////            _Generic_BigInteger.factorial(maxite);
////        }
////        BigDecimal Three = new BigDecimal("3");
////        BigDecimal Twelve = new BigDecimal("12");
////        BigInteger a = BigInteger.valueOf(13591409);
////        BigInteger b = BigInteger.valueOf(54514013);
////        BigDecimal c = BigDecimal.valueOf(640320);
////        BigDecimal cToh;
////        BigInteger d;
////
////        BigDecimal onePointFive = new BigDecimal("1.5");
////        BigDecimal h;
////        BigInteger k_BigInteger;
////        BigDecimal k_BigDecimal;
////        BigInteger kfactorial;
////        BigInteger kfactorialcubed;
////        BigInteger sixkfactorial;
////        BigInteger sixkfactoriald;
////        BigInteger threekfactorial;
////        BigDecimal numerator;
////        BigDecimal denominator;
////        BigDecimal divisionTimeTwelve;
////        // Sum for all k > 0 1/(12((-1^k(6k)!d)/(3k)!((k!)^3)640320^h))
////        // Sum for all k > 0 1/(12((-1^k(6k)!d)/(3k)!((k!)^3)c^h))
////        // Sum for all k > 0 1/(12((-1^k(6k)!d)/denominator))
////        boolean negate = false;
////        for (int k_int = 0; k_int < maxite; k_int++) {
////            k_BigInteger = BigInteger.valueOf(k_int);
////            k_BigDecimal = BigDecimal.valueOf(k_int);
////            kfactorial = _Generic_BigInteger.factorial(k_int);
////            sixkfactorial = _Generic_BigInteger.factorial(k_int * 6);
////            threekfactorial = _Generic_BigInteger.factorial(k_int * 3);
////            kfactorialcubed = kfactorial.pow(3);
////            d = a.add(b.multiply(k_BigInteger));
////            h = (Three.multiply(k_BigDecimal)).add(onePointFive);
////            cToh = power(
////                    c,
////                    h,
////                    e,
////                    this,
////                    decimalPlaces,
////                    _RoundingMode);
////            denominator = new BigDecimal(threekfactorial.multiply(kfactorialcubed)).multiply(cToh);
////            numerator = new BigDecimal(sixkfactorial.multiply(d));
////            if (negate) {
////                numerator = numerator.negate();
////                negate = false;
////            } else {
////                negate = true;
////            }
////            divisionTimeTwelve = (numerator.multiply(Twelve)).divide(
////                    denominator, decimalPlaces, _RoundingMode);
////            _PI = _PI.add(divisionTimeTwelve);
////            System.out.println("k_int " + k_int + ", _PI " + _PI);
////        }
////        _PI = new BigDecimal("4");
////        int safeDecimalPlaces = decimalPlaces + 3;
////        // This can be set higher, upto nearly Long.MAX_VALUE before BigInteger
////        // iteration needs to takeover...
////        maxite = Integer.MAX_VALUE;
////        //maxite = decimalPlaces * 2;
////        //maxite = safeDecimalPlaces;
////        BigDecimal tollerance = new BigDecimal(
////                BigInteger.ONE, safeDecimalPlaces);
////        BigDecimal Four = BigDecimal.valueOf(4);
////        BigDecimal fraction;
////        boolean add = false;
//////        for (long denominator_long = 3; denominator_long < maxite; denominator_long+=4) {
//////            fraction = Four.divide(
//////                    BigDecimal.valueOf(denominator_int),
//////                    safeDecimalPlaces,
//////                    _RoundingMode);
//////            if (fraction.compareTo(tollerance) == -1) {
//////                break;
//////            }
//////            _PI = _PI.subtract(fraction);
//////        }
//////        for (long denominator_long = 5; denominator_long < maxite; denominator_long+=4) {
//////            fraction = Four.divide(
//////                    BigDecimal.valueOf(denominator_int),
//////                    safeDecimalPlaces,
//////                    _RoundingMode);
//////            if (fraction.compareTo(tollerance) == -1) {
//////                break;
//////            }
//////                _PI = _PI.add(fraction);
//////        }
////        for (long denominator_long = 3;
////                denominator_long < maxite;
////                denominator_long += 2) {
////            fraction = Four.divide(
////                    BigDecimal.valueOf(denominator_long),
////                    safeDecimalPlaces,
////                    _RoundingMode);
////            if (fraction.compareTo(tollerance) == -1) {
////                break;
////            }
////            if (add) {
////                _PI = _PI.add(fraction);
////                add = false;
////            } else {
////                _PI = _PI.subtract(fraction);
////                add = true;
////            }
////            if (denominator_long % 10000000 == 3) {
////                System.out.println("denominator_long " + denominator_long + ", _PI " + _PI);
////            }
////        }
////        _PI = round(_PI, decimalPlaces, _RoundingMode);
////        return _PI;
//        }
//    }
    /**
     * If _E has at least decimalPlaces decimal place accuracy, then a copy
     * created using new BigDecimal(_E.toString()) is returned otherwise the
     * Euler constant is recalculated using get calculated to the required
     * precision, stored and a copy returned.
     *
     * @param decimalPlaces
     * @return
     */
    public BigDecimal getEulerConstantToAMinimumDecimalPlacePrecision(
            int decimalPlaces) {
        if (_E != null) {
            if (_E.scale() > decimalPlaces) {
                return new BigDecimal(_E.toString());
            }
        }
        return getEulerConstantToAFixedDecimalPlacePrecision(
                decimalPlaces,
                get_RoundingMode());
    }

    /**
     * If _E has enough precision, this is rounded and returned otherwise _E is
     * calculated to the required precision, stored and a copy returned.
     *
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return
     */
    public BigDecimal getEulerConstantToAFixedDecimalPlacePrecision(
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (_E != null) {
            if (_E.scale() > decimalPlaces) {
                return roundIfNecessary(
                        new BigDecimal(_E.toString()),
                        decimalPlaces,
                        a_RoundingMode);
            }
        }
        _E = new BigDecimal("2");
        int safeDecimalPlaces = decimalPlaces + 3;
        //int maxite = 10000;
        //int maxite = decimalPlaces * 2;
        int maxite = safeDecimalPlaces;
        if (_Generic_BigInteger == null) {
            init_Factorial_Generic_BigInteger(maxite);
        } else {
            _Generic_BigInteger.factorial(maxite);
        }
        BigDecimal factorial;
        BigDecimal oneOverFactorial;
        BigDecimal tollerance = new BigDecimal(
                BigInteger.ONE, safeDecimalPlaces);
        // Use Taylor Series
        Integer factorialKey;
        for (int i = 2; i
                < maxite; i++) {
            factorialKey = new Integer(i);
            factorial = new BigDecimal(
                    _Generic_BigInteger._Factorial_TreeMap.get(factorialKey));
            oneOverFactorial = BigDecimal.ONE.divide(
                    factorial, safeDecimalPlaces, get_RoundingMode());
            _E = _E.add(oneOverFactorial);
            if (oneOverFactorial.compareTo(tollerance) == -1) {
                break;
            }
        }
        _E = roundIfNecessary(_E, decimalPlaces, a_RoundingMode);
        return new BigDecimal(_E.toString());
    }

    /**
     * e^y = 1 + y/1! + y^2/2! + y^3/3! +...
     *
     * @param y
     * @param a_Generic_BigDecimal may be null. Passing a Generic_BigDecimal in
     * can save computation. The Euler constant is used in most invocations.
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return e^y where e is the Euler constant. The result is returned correct
     * to decimalPlaces decimal place precision.
     */
    public static BigDecimal exp(
            BigDecimal y,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;
        // Deal with special cases
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }
        // Check a_Generic_BigDecimal
        if (a_Generic_BigDecimal == null) {
            a_Generic_BigDecimal = new Generic_BigDecimal();
        }
        if (y.compareTo(BigDecimal.ONE) == 0) {
            return a_Generic_BigDecimal.getEulerConstantToAFixedDecimalPlacePrecision(
                    decimalPlaces,
                    a_RoundingMode);
        }
        int safeDecimalPlaces;
        if (y.compareTo(BigDecimal.ZERO) == -1) {
            safeDecimalPlaces = y.scale() + decimalPlaces;
            BigDecimal exp = exp(
                    y.negate(),
                    a_Generic_BigDecimal,
                    safeDecimalPlaces,
                    a_RoundingMode);
            result = reciprocal(
                    exp,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
        }
        int divisionDecimalPlaces = decimalPlaces + 3;
        result = BigDecimal.ONE.add(y);
//        if (a_Generic_BigDecimal._Generic_BigInteger == null) {
//            a_Generic_BigDecimal.init_Factorial_Generic_BigInteger(maxite);
//        } else {
//            a_Generic_BigDecimal._Generic_BigInteger.factorial(maxite);
//        }
        BigDecimal factorial;
        BigDecimal xpow;
        BigDecimal xpowOverFactorial;
        BigDecimal tollerance = new BigDecimal(
                BigInteger.ONE, decimalPlaces + 1);
        // Use Taylor Series
        BigInteger i_BigInteger = BigInteger.ONE;
        Integer factorialKey = 1;
        while (true) {
            i_BigInteger = i_BigInteger.add(BigInteger.ONE);
            factorialKey = factorialKey + 1;
            factorial = new BigDecimal(
                    a_Generic_BigDecimal._Generic_BigInteger.factorial(factorialKey));
            xpow = power(
                    y,
                    i_BigInteger,
                    64,
                    decimalPlaces, // Maybe there is need for more even though the bottom of the Taylor series grows fast
                    a_RoundingMode);
            xpowOverFactorial = divideRoundIfNecessary(
                    xpow,
                    factorial,
                    divisionDecimalPlaces,
                    a_RoundingMode);
            result = result.add(xpowOverFactorial);
            if (xpowOverFactorial.compareTo(tollerance) == -1) {
                break;
            }
        }
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

//    /**
//     * This method was not necessary. However something like it might be 
//     * useful for none Euler constant based exponentiation so for the time 
//     * being the code is just commented out rather than deleted. If the power
//     * methods are fully operational, this commented code should be deleted.
//     * @param y 0 < y < 1
//     * @param decimalPlaces
//     * @return e^y accurate to decimalPlaces
//     */
//    private static BigDecimal expLessThanOne(
//            BigDecimal y,
//            Generic_BigDecimal a_Generic_BigDecimal,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        BigDecimal epsilon_BigDecimal = new BigDecimal(BigInteger.ONE, decimalPlaces);
//        BigDecimal x0 = new BigDecimal(a_Generic_BigDecimal._E.toString());
//        BigDecimal element;
//        BigInteger elementUnscaled;
//        BigDecimal elementOne;
//        BigInteger elementOneReciprocal;
//        BigDecimal rootRoundIfNecessary;
//        BigDecimal rootMultiple;
//        int maxite = x0.precision();
//        result = BigDecimal.ONE;
//        while (true) {
//        for (int i = 0; i < maxite; i++) {
//            element = floorSignificantDigit(x0);
//            elementUnscaled = element.unscaledValue();
//            System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//            if (elementUnscaled.compareTo(BigInteger.ZERO) == 1) {
//                elementOne = divideRoundIfNecessary(
//                        element,
//                        elementUnscaled,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (elementOne.compareTo(BigDecimal.ZERO) == 0) {
//                    break;
//                }
//                System.out.println("element " + element + " elementUnscaled " + elementUnscaled);
//                elementOneReciprocal = reciprocalWillBeIntegerReturnBigInteger(elementOne);
//                rootRoundIfNecessary = rootRoundIfNecessary(
//                        y,
//                        elementOneReciprocal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (rootRoundIfNecessary.compareTo(BigDecimal.ZERO) == 1) {
//                    rootMultiple = power(
//                            rootRoundIfNecessary,
//                            elementUnscaled,
//                            64,
//                            decimalPlaces, // @TODO Is this sufficient?
//                            a_RoundingMode);
//                    result = multiplyRoundIfNecessary(
//                            result,
//                            rootMultiple,
//                            decimalPlaces + 3, // @TODO Is this sufficient or ott?
//                            a_RoundingMode);
//                }
//            }
//            x0 = x0.subtract(element);
//        }
//        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
//    }
    /**
     * Compute and return the natural logarithm of x accurate to decimalPlaces
     * decimal place precision using Newton's algorithm. Method adapted from
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     *
     * @param x
     * @param a_Generic_BigDecimal
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return the natural logarithm of x accurate to decimalPlaces number of
     * decimal places and with result rounded using a_RoundingMode if necessary.
     */
    public static BigDecimal ln(
            BigDecimal x,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Check that x > 0.
        if (x.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException(
                    "x <= 0 in " + BigDecimal.class.getName()
                    + "ln(BigDecimal,Generic_BigDecimal,int,RoundingMode)");
        }
        // The number of digits to the left of the decimal point.
        int magnitude = x.toString().length() - x.scale() - 1;
        if (magnitude < 3) {
            return lnNewton(
                    x, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode);
        } else {
            // Compute magnitude*ln(x^(1/magnitude)).
            // x^(1/magnitude)
            BigDecimal root = rootRoundIfNecessary(x, magnitude, decimalPlaces, a_RoundingMode);
            // ln(x^(1/magnitude))
            BigDecimal lnRoot = lnNewton(
                    root, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode);
            //BigDecimal lnRoot = ln(rootRoundIfNecessary, a_Generic_BigDecimal, decimalPlaces, a_RoundingMode);
            // magnitude*ln(x^(1/magnitude))
            BigDecimal result = BigDecimal.valueOf(magnitude).multiply(lnRoot);
            return result;
            //setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        }
    }

    /**
     * Compute and return the natural logarithm of x accurate to decimalPlaces
     * decimal place precision using Newton's algorithm.
     * http://stackoverflow.com/questions/739532/logarithm-of-a-bigdecimal
     */
    private static BigDecimal lnNewton(
            BigDecimal x,
            Generic_BigDecimal a_Generic_BigDecimal,
            int scale,
            RoundingMode a_RoundingMode) {
        BigDecimal result = new BigDecimal(x.toString());
        int sp1 = scale + 1;
        BigDecimal term;
        // Convergence tolerance = 5*(10^-(scale+1))
        BigDecimal tolerance = BigDecimal.valueOf(5).movePointLeft(sp1);
        // Loop until the approximation converges
        // (two successive approximations are within the tolerance).
        do {
            // e^toCompare
            BigDecimal exp = exp(
                    result,
                    a_Generic_BigDecimal,
                    sp1,
                    a_RoundingMode);
            // (e^toCompare - x)/e^toCompare
            term = exp.subtract(x).divide(exp, sp1, a_RoundingMode);
            // toCompare - (e^toCompare - x)/e^toCompare
            result = result.subtract(term);
            //Thread.yield();
        } while (term.compareTo(tolerance) > 0);
        return result.setScale(scale, a_RoundingMode);
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to positive infinity) of either x or y1
     */
    public static BigDecimal max(
            BigDecimal x,
            BigDecimal y) {
        if (x.compareTo(y) == -1) {
            return new BigDecimal(y.toString());
        } else {
            return new BigDecimal(x.toString());
        }
    }

//    /**
//     * @param x
//     * @param y1
//     * @return the larger (closer to positive infinity) of either x or y1
//     */
//    public static BigDecimal max(
//            BigInteger x,
//            BigDecimal y1) {
//        BigDecimal x_BigDecimal = new BigDecimal(x);
//        return max(x_BigDecimal,y1);
//    }
    /**
     * Returns a copy of the maximum BigDecimal in the list.
     * @param list
     * @return 
     */
    public static BigDecimal max(
            ArrayList<BigDecimal> list) {
        BigDecimal result = BigDecimal.valueOf(Long.MIN_VALUE);
        Iterator<BigDecimal> ite = list.iterator();
        while (ite.hasNext()) {
            result = result.max(ite.next());
        }
        return result;
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to positive infinity) of either x or y1
     */
    public static BigDecimal max(
            BigDecimal x,
            BigInteger y) {
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return max(x, y_BigDecimal);
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to negative infinity) of either x or y1
     */
    public static BigDecimal min(
            BigDecimal x,
            BigDecimal y) {
        if (x.compareTo(y) == -1) {
            return new BigDecimal(x.toString());
        } else {
            return new BigDecimal(y.toString());
        }
    }

    /**
     * @param x
     * @param y
     * @return the larger (closer to positive infinity) of either x or y1
     */
    public static BigDecimal min(
            BigDecimal x,
            BigInteger y) {
        BigDecimal y_BigDecimal = new BigDecimal(y);
        return min(x, y_BigDecimal);
    }

    /**
     * If most (biggest) significant digit is right of decimal point then this
     * is positive. If left of decimal point this is negative
     *
     * @param x
     * @return The exponent of 10 to which the unscaled value is multiplied to
     * return the most (biggest) significant digit
     */
    public static int positionSignificantDigit(
            BigDecimal x) {
        int result;
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            result = positionSignificantDigit(x.negate());
            return result;
        }
//        if (x.compareTo(BigDecimal.ONE) == -1) {
//            result = 0;
//            BigDecimal movedRight;
//            if (x.compareTo(BigDecimal.ZERO) == 1
//                    && x.compareTo(BigDecimal.ONE) == -1) {
//                result += 1;
//                movedRight = x.movePointRight(1);
//                while (movedRight.compareTo(BigDecimal.ONE) == -1) {
//                    result += 1;
//                    movedRight = movedRight.movePointRight(1);
//                }
//            }
//            return result;
//        }
        BigInteger xUnscaledValue = x.unscaledValue();
        String xUnscaledValueString = xUnscaledValue.toString();
        result = xUnscaledValueString.length() - x.scale();
        if (result < 1) {
            result--;
        }

        return result;
    }

    /**
     * @param x
     * @return The next closer to 0 BigDecimal with same decimal place precision
     * as x.
     */
    public static BigDecimal floorSignificantDigit(
            BigDecimal x) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result;
            //result = ceilingSignificantDigit(x.negate());
            result = floorSignificantDigit(x.negate());
            return result.negate();
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result;
        BigInteger xUnscaledValue = x.unscaledValue();
        String xUnscaledValueString = xUnscaledValue.toString();
        int scale = x.scale() - xUnscaledValueString.length() + 1;
        int biggest = new Integer(xUnscaledValueString.substring(0, 1));
        if (biggest == 0) {
            result = new BigDecimal(BigInteger.ONE, scale);
        } else {
            result = new BigDecimal(BigInteger.valueOf(biggest), scale);
        }
        return result.stripTrailingZeros();
    }

    /**
     * @param x
     * @return The next further away from 0 BigDecimal with same decimal place
     * precision as x.
     */
    public static BigDecimal ceilingSignificantDigit(
            BigDecimal x) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            BigDecimal result;
            result = ceilingSignificantDigit(x.negate());
            //result = floorSignificantDigit(x.negate());
            return result.negate();
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result;
        BigInteger xUnscaledValue = x.unscaledValue();
        String xUnscaledValueString = xUnscaledValue.toString();
        int scale = x.scale() - xUnscaledValueString.length() + 1;
        int biggest = new Integer(xUnscaledValueString.substring(0, 1));
        biggest++;
        result = new BigDecimal(BigInteger.valueOf(biggest), scale);
        return result.stripTrailingZeros();
    }

    /**
     * @param x
     * @param root
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary of x to decimalPlaces precision
     */
    public static BigDecimal rootRoundIfNecessary(
            BigDecimal x,
            BigInteger root,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root.compareTo(BigInteger.valueOf(999999999)) != 1) {
            BigDecimal result = rootRoundIfNecessary(
                    x,
                    root.intValue(),
                    //a_Generic_BigDecimal,
                    decimalPlaces,
                    a_RoundingMode);
            // return result;
            return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
        } else {
            // Deal with special cases
            // x <= 0
            if (x.compareTo(BigDecimal.ZERO) != 1) {
                // Complex roots are not handled!
                throw new IllegalArgumentException(
                        "x <= 0 in " + Generic_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
            }
            // x = 1
            if (x.compareTo(BigDecimal.ONE) == 0) {
                return BigDecimal.ONE;
            }
            // rootRoundIfNecessary = 1
            if (root.compareTo(BigInteger.ONE) == 0) {
                BigDecimal result = new BigDecimal(x.toString());
                if (result.scale() > decimalPlaces) {
                    return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
                }
                return result;
            }
            // x < 1
            if (x.compareTo(BigDecimal.ONE) == -1) {
                int xscale = x.scale();
                // Optimisation (untested if it is faster) for a large x.scale()
                int rootLength = root.toString().length();
                if (xscale < 10) {
                    BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                    BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                    // numerator will be greater than denominator and both will
                    // be close to a value of 1, a large number of decimal 
                    // places for both numerator and denominator are required!
                    BigDecimal denominator = rootRoundIfNecessary(
                            denominatorUnrooted,
                            root,
                            decimalPlaces + xscale + rootLength, // Can we cope with less or do we need more?
                            a_RoundingMode);
                    int denominatorScale = denominator.scale();
                    BigDecimal numerator = rootRoundIfNecessary(
                            numeratorUnrooted,
                            root,
                            decimalPlaces + xscale + rootLength, // Can we cope with less or do we need more?
                            a_RoundingMode);
                    BigDecimal result = Generic_BigDecimal.divideRoundIfNecessary(
                            numerator,
                            denominator,
                            decimalPlaces,
                            a_RoundingMode);
                    return result;
                } else {
                    BigDecimal result = rootLessThanOne(
                            x,
                            root,
                            //a_Generic_BigDecimal,
                            decimalPlaces,
                            a_RoundingMode);
                    return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
                }
            }
            // x > 1
            //int safeDecimalPlaces = decimalPlaces + 5;
            BigDecimal epsilon_BigDecimal =
                    new BigDecimal(BigInteger.ONE, decimalPlaces);
            // Check there is a rootRoundIfNecessary in the precision and return 0 if not
            BigDecimal comparator = BigDecimal.ONE.add(epsilon_BigDecimal);
            boolean powerTest = powerTestAbove(
                    x,
                    comparator,
                    root,
                    256,
                    decimalPlaces,
                    a_RoundingMode);
            if (powerTest) {
                System.out.println(
                        "No root in the precision returning BigDecimal.ONE"
                        + "in " + Generic_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");

                return BigDecimal.ONE;
            }
            BigDecimal result;// = new BigDecimal("1");
            int rootInitialisationMaxIte = 10;
            result = rootInitialisation(
                    x,
                    root,
                    epsilon_BigDecimal,
                    rootInitialisationMaxIte,
                    decimalPlaces,
                    a_RoundingMode);
            // Newton Raphson
            //int newtonRaphsonMaxite = 100;
            result = newtonRaphson(
                    x,
                    result,
                    root,
                    epsilon_BigDecimal,
                    //newtonRaphsonMaxite,
                    decimalPlaces + 1);
//                    a_RoundingMode);
            // return result;
            return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
        }
    }

    /**
     * @param x
     * @param root
     * @param rootRoundIfNecessary
     * @return The rootRoundIfNecessary of x to decimalPlaces precision
     */
    public static BigDecimal rootNoRounding(
            BigDecimal x,
            BigInteger root) {
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root.compareTo(BigInteger.valueOf(999999999)) != 1) {
            BigDecimal result = rootNoRounding(
                    x,
                    root.intValue());
            return result;
        } else {
            // Deal with special cases
            // x <= 0
            if (x.compareTo(BigDecimal.ZERO) != 1) {
                // Complex roots are not handled!
                throw new IllegalArgumentException(
                        "x <= 0 in " + Generic_BigDecimal.class
                        + ".root(BigDecimal,BigInteger,int,RoundingMode)");
            }
            // x = 1
            if (x.compareTo(BigDecimal.ONE) == 0) {
                return BigDecimal.ONE;
            }
            // rootRoundIfNecessary = 1
            if (root.compareTo(BigInteger.ONE) == 0) {
                BigDecimal result = new BigDecimal(x.toString());
                return result;
            }
            // x < 1
            if (x.compareTo(BigDecimal.ONE) == -1) {
                int xscale = x.scale();
                // Optimisation (untested if it is faster) for a large x.scale()
                int rootLength = root.toString().length();
                if (xscale < 10) {
                    BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                    BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                    // numerator will be greater than denominator and both will
                    // be close to a value of 1, a large number of decimal 
                    // places for both numerator and denominator are required!
                    BigDecimal denominator = rootNoRounding(
                            denominatorUnrooted,
                            root);
                    int denominatorScale = denominator.scale();
                    BigDecimal numerator = rootNoRounding(
                            numeratorUnrooted,
                            root);
                    BigDecimal result = Generic_BigDecimal.divideNoRounding(
                            numerator,
                            denominator);
                    return result;
                } else {
                    BigDecimal result = rootLessThanOneNoRounding(
                            x,
                            root);
                    return result;
                }
            }
            BigDecimal result;// = new BigDecimal("1");
            int rootInitialisationMaxIte = 10;
            result = rootInitialisationNoRounding(
                    x,
                    root,
                    rootInitialisationMaxIte);
            // Newton Raphson
            //int newtonRaphsonMaxite = 100;
            result = newtonRaphsonNoRounding(
                    x,
                    result,
                    root);
            return result;
        }
    }

    /**
     * Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphson(
            BigDecimal x,
            BigDecimal result0,
            BigInteger root,
            BigDecimal epsilon,
            //int maxite,
            int decimalPlaces) {
//            RoundingMode a_RoundingMode) {
        RoundingMode a_RoundingMode;
        if (x.compareTo(BigDecimal.ONE) == 1) {
            a_RoundingMode = RoundingMode.DOWN;
        } else {
            a_RoundingMode = RoundingMode.UP;
        }
        BigDecimal result = new BigDecimal(result0.toString());
        BigDecimal root_BigDecimal = new BigDecimal(root);
        int magnitudex = magnitude(x);
        //System.out.println("magnitudex " + magnitudex);
        int precision = decimalPlaces + 5;
        int divprecision = decimalPlaces + 10;

        MathContext a_MathContext = new MathContext(precision);
        BigDecimal previousResult_BigDecimal;
//        BigDecimal previousResult_BigDecimal0;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigInteger rootsubtract1 = root.subtract(BigInteger.ONE);

        boolean maxiteReached = true;
        //resultipowroot = toCompare.pow(rootRoundIfNecessary, a_MathContext);
        //resultipowrootsubtract1 = toCompare.pow(rootRoundIfNecessary - 1, a_MathContext);
        //divisor = root_BigDecimal.multiply(resultipowrootsubtract1, a_MathContext);

        // Initialise toCompare and previousResult_BigDecimal
        previousResult_BigDecimal = new BigDecimal("1");
//        previousResult_BigDecimal0 = new BigDecimal("1");

        // Newton Raphson
        while (true) {
            //for (int i = 0; i < maxite; i++) {
            resultipowroot = power(
                    result,
                    root,
                    64,
                    precision,
                    a_RoundingMode);
            resultipowrootsubtract1 = power(
                    result,
                    rootsubtract1,
                    64,
                    precision,
                    a_RoundingMode);
            divisor = resultipowrootsubtract1.multiply(root_BigDecimal);
            result = result.subtract(
                    Generic_BigDecimal.divideNoCaseCheckRoundIfNecessary(
                    resultipowroot.subtract(x),
                    divisor,
                    divprecision,
                    a_RoundingMode));
//            result = result.subtract(
//                    resultipowroot.subtract(x).divide(divisor, a_MathContext));
            // Test for convergence
            if ((previousResult_BigDecimal.subtract(result)).abs().compareTo(epsilon) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                maxiteReached = false;
                break;
            }
            //System.out.println(result.toPlainString());
//            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
            previousResult_BigDecimal = new BigDecimal(result.toString());
        }
//        if (maxiteReached) {
//            System.out.println(
//                    "maxite reached without finding rootRoundIfNecessary in "
//                    + Generic_BigDecimal.class.getName() + ".newtonRaphson(...)"
//                    + " previousResult_BigDecimal0.subtract(result).abs() "
//                    + previousResult_BigDecimal0.subtract(result).abs());
//        }
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonNoRounding(
            BigDecimal x,
            BigDecimal result0,
            BigInteger root) {
        BigDecimal result = new BigDecimal(result0.toString());
        BigDecimal root_BigDecimal = new BigDecimal(root);
        int magnitudex = magnitude(x);
        //System.out.println("magnitudex " + magnitudex);
        BigDecimal previousResult_BigDecimal;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigInteger rootsubtract1 = root.subtract(BigInteger.ONE);
        previousResult_BigDecimal = new BigDecimal("1");
        // Newton Raphson
        while (true) {
            resultipowroot = powerNoRounding(
                    result,
                    root,
                    64);
            resultipowrootsubtract1 = powerNoRounding(
                    result,
                    rootsubtract1,
                    64);
            divisor = resultipowrootsubtract1.multiply(root_BigDecimal);
            result = result.subtract(
                    Generic_BigDecimal.divideNoCaseCheckNoRounding(
                    resultipowroot.subtract(x),
                    divisor));
            // Test for convergence
            if (previousResult_BigDecimal.compareTo(result) == 0) {
                break;
            }
            //System.out.println(result.toPlainString());
            previousResult_BigDecimal = new BigDecimal(result.toString());
        }
        return result;
    }

    /**
     * Uses decimalPlace division which compared with MathContext based division
     * was tested to be slower and thought not to impact on precision.
     *
     * @see
     * newtonRaphson0(BigDecimal,BigDecimal,BigInteger,BigDecimal,int,int,RoundingMode)
     * @param x
     * @param result0
     * @param root_BigInteger
     * @param epsilon_BigDecimal
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphson0(
            BigDecimal x,
            BigDecimal result0,
            BigInteger root_BigInteger,
            BigDecimal epsilon_BigDecimal,
            int maxite,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result = new BigDecimal(result0.toString());
        int safeDecimalPlaces = decimalPlaces + 5;
        BigInteger rootsubtract1_BigInteger = root_BigInteger.subtract(
                BigInteger.ONE);
        BigDecimal previousResult_BigDecimal;
        BigDecimal previousResult_BigDecimal0;
        BigDecimal resultipowroot;
        BigDecimal resultipowrootsubtract1;
        BigDecimal divisor;
        BigDecimal resultipowrootsubtractx;
        BigDecimal resultipowrootsubtractxdividedivisor;
        boolean maxiteReached = true;
        // Initialise toCompare and previousResult_BigDecimal
        previousResult_BigDecimal = new BigDecimal(result0.toString());
        previousResult_BigDecimal0 = new BigDecimal(result0.toString());
        for (int i = 0; i < maxite; i++) {
            resultipowroot = power(
                    result,
                    root_BigInteger,
                    64,
                    safeDecimalPlaces,
                    a_RoundingMode);
            resultipowrootsubtract1 = power(
                    result,
                    rootsubtract1_BigInteger,
                    64,
                    safeDecimalPlaces,
                    a_RoundingMode);
            divisor = multiplyRoundIfNecessary(
                    resultipowrootsubtract1,
                    root_BigInteger,
                    safeDecimalPlaces,
                    a_RoundingMode);
            resultipowrootsubtractx = resultipowroot.subtract(x);
            resultipowrootsubtractxdividedivisor = divideRoundIfNecessary(
                    resultipowrootsubtractx,
                    divisor,
                    safeDecimalPlaces,
                    a_RoundingMode);
            result = result.subtract(resultipowrootsubtractxdividedivisor);
            // Test for convergence
            if (previousResult_BigDecimal.subtract(result).abs().compareTo(epsilon_BigDecimal) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                maxiteReached = false;
                break;
            }
            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
            previousResult_BigDecimal = new BigDecimal(result.toString());
        }
        if (maxiteReached) {
            System.out.println("previousResult_BigDecimal0.subtract(result).abs() " + previousResult_BigDecimal0.subtract(result).abs());
        }
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

//    public static BigDecimal newtonRaphson(
//            BigDecimal x,
//            BigDecimal result0,
//            BigInteger rootRoundIfNecessary,
//            BigDecimal epsilon_BigDecimal,
//            int maxite,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result = new BigDecimal(result0.toString());
//        int safeDecimalPlaces = decimalPlaces + 5;
//        BigInteger rootsubtract1_BigDecimal = rootRoundIfNecessary.subtract(BigInteger.ONE);
//        BigDecimal previousResult_BigDecimal;
//        BigDecimal previousResult_BigDecimal0;
//        BigDecimal resultipowroot;
//        BigDecimal resultipowrootsubtract1;
//        BigDecimal divisor;
//        BigDecimal resultipowrootsubtractx;
//        BigDecimal resultipowrootsubtractxdividedivisor;
//        boolean maxiteReached = true;
//        // Initialise toCompare and previousResult_BigDecimal
//        previousResult_BigDecimal = new BigDecimal("1");
//        previousResult_BigDecimal0 = new BigDecimal("1");
//        int i = 0;
//        while (i < maxite) {
//            resultipowroot = power(
//                    result,
//                    rootRoundIfNecessary,
//                    64,
//                    decimalPlaces,
//                    a_RoundingMode);
//            resultipowrootsubtract1 = power(
//                    result,
//                    rootsubtract1_BigDecimal,
//                    64,
//                    decimalPlaces,
//                    a_RoundingMode);
//            divisor = multiply(
//                    resultipowrootsubtract1,
//                    rootRoundIfNecessary,
//                    safeDecimalPlaces,
//                    a_RoundingMode);
//            resultipowrootsubtractx = resultipowroot.subtract(x);
//            resultipowrootsubtractxdividedivisor = divide(
//                    resultipowrootsubtractx,
//                    divisor,
//                    safeDecimalPlaces,
//                    a_RoundingMode);
//            result = result.subtract(resultipowrootsubtractxdividedivisor);
//            // Test for convergence
//            if (previousResult_BigDecimal.subtract(result).abs().compareTo(epsilon_BigDecimal) != 1) {
//                System.out.println("Root found in iteration " + i + " out of " + maxite);
//                maxiteReached = false;
//                break;
//            }
//            previousResult_BigDecimal0 = new BigDecimal(previousResult_BigDecimal.toString());
//            previousResult_BigDecimal = new BigDecimal(result.toString());
//            i++;
//        }
//        if (maxiteReached) {
//            System.out.println("previousResult_BigDecimal0.subtract(result).abs() " + previousResult_BigDecimal0.subtract(result).abs());
//        }
//        return result;
//    }
    private static BigDecimal rootInitialisation(
            BigDecimal x,
            BigInteger root,
            BigDecimal epsilon_BigDecimal,
            int maxite,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result;
        int div = 64;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOne(
                    x,
                    root,
                    epsilon_BigDecimal,
                    maxite,
                    decimalPlaces,
                    a_RoundingMode);
        } else {
            int i = 0;
            result = BigDecimal.ONE.add(epsilon_BigDecimal);
            boolean powerTestAbove = powerTestAbove(
                    x,
                    result,
                    root,
                    64,
                    decimalPlaces,
                    a_RoundingMode);
            if (powerTestAbove) {
                // Root cannot be found within current precision...
                int debug = 1;
                return BigDecimal.ONE;
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;

            BigInteger rootdiv = new BigInteger(root.toString());
            //boolean notInitialised = true;
            //BigDecimal difference_BigDecimal;
//            while (i < maxite && notInitialised) {
            while (i < maxite) {
//                difference_BigDecimal = a.subtract(b);
//                if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                    notInitialised = false;
//                }
//                if (notInitialised) {
                // Disect
                c = divideRoundIfNecessary(
                        a.subtract(b),
                        rootdiv,
                        decimalPlaces + 1,
                        a_RoundingMode);
                c = b.add(c);
                powerTestAbove = powerTestAbove(
                        x,
                        c,
                        root,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (powerTestAbove) {
                    a = c;
                    //a = new BigDecimal(c.toString());
                } else {
                    b = c;
                    //b = new BigDecimal(c.toString());
                    rootdiv = rootdiv.divide(Generic_BigInteger.Two);
                    result = b;
//                        difference_BigDecimal = (result.subtract(c)).abs();
//                        if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                            notInitialised = false;
//                        }
                }
//                if (notInitialised) {
                // Bisect
                c = divideRoundIfNecessary(
                        a.subtract(b),
                        TWO,
                        decimalPlaces + 1,
                        a_RoundingMode);
                c = c.add(b);
                powerTestAbove = powerTestAbove(
                        x,
                        c,
                        root,
                        div,
                        decimalPlaces,
                        a_RoundingMode);
                if (powerTestAbove) {
                    a = c;
                    //a = new BigDecimal(c.toString());
                } else {
                    b = c;
                    //b = new BigDecimal(c.toString());
                    result = b;
//                            difference_BigDecimal = (result.subtract(c)).abs();
//                            if (difference_BigDecimal.compareTo(epsilon_BigDecimal) == -1) {
//                                notInitialised = false;
//                            }
                }
//                }
//                }
                i++;
            }
        }
        return result;
    }

    private static BigDecimal rootInitialisationNoRounding(
            BigDecimal x,
            BigInteger root,
            int maxite) {
        BigDecimal result;
        int div = 64;
        // Initialise toCompare and previousResult_BigDecimal
        if (x.compareTo(BigDecimal.ONE) == -1) {
            return rootInitialisationLessThanOneNoRounding(
                    x,
                    root,
                    maxite);
        } else {
            int i = 0;
            result = BigDecimal.ONE;
            boolean powerTestAbove = powerTestAboveNoRounding(
                    x,
                    result,
                    root,
                    64);
            if (powerTestAbove) {
                // Root cannot be found within current precision...
                int debug = 1;
                return BigDecimal.ONE;
            }
            BigDecimal a = new BigDecimal(x.toString());
            BigDecimal b = BigDecimal.ONE;
            BigDecimal c;

            BigInteger rootdiv = new BigInteger(root.toString());
            while (i < maxite) {
                // Disect
                c = divideNoRounding(
                        a.subtract(b),
                        rootdiv);
                c = b.add(c);
                powerTestAbove = powerTestAboveNoRounding(
                        x,
                        c,
                        root,
                        div);
                if (powerTestAbove) {
                    a = c;
                } else {
                    b = c;
                    rootdiv = rootdiv.divide(Generic_BigInteger.Two);
                    result = b;
                }
                // Bisect
                c = divideNoRounding(
                        a.subtract(b),
                        TWO);
                c = c.add(b);
                powerTestAbove = powerTestAboveNoRounding(
                        x,
                        c,
                        root,
                        div);
                if (powerTestAbove) {
                    a = c;
                } else {
                    b = c;
                    result = b;
                }
                i++;
            }
        }
        return result;
    }

//    private static BigDecimal rootInitialisation0(
//            BigDecimal x,
//            BigDecimal root_BigDecimal,
//            BigDecimal maxError_BigDecimal,
//            BigDecimal a0,
//            BigDecimal e,
//            Generic_BigDecimal a_Generic_BigDecimal,
//            int decimalPlaces,
//            MathContext a_MathContext,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        if (x.compareTo(BigDecimal.ONE) == -1) {
//            result = BigDecimal.ONE.subtract(maxError_BigDecimal);
//            // Bisect
//            BigDecimal a = a0;
//            BigDecimal b = result;
//            BigDecimal c;
//            BigDecimal cPowerRoot;
//            boolean notInitialised = true;
//            while (notInitialised) {
//                c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                cPowerRoot = power(
//                        c,
//                        root_BigDecimal,
//                        e,
//                        a_Generic_BigDecimal,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (cPowerRoot.compareTo(x) == 1) {
//                    b = c;
//                    if (a.compareTo(c) == 0) {
//                        notInitialised = false;
//                    }
//                } else {
//                    result = c;
//                    notInitialised = false;
//                }
//            }
//        } else {
//            result = BigDecimal.ONE.add(maxError_BigDecimal);
//            BigDecimal a = x.divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//            if (a.compareTo(BigDecimal.ONE) == 1) {
//                result = BigDecimal.ONE.add(maxError_BigDecimal);
//                // Disect
//                BigDecimal b = a0;
//                BigDecimal c;
//                boolean notInitialised = true;
//                BigDecimal cPowerRoot;
//                while (notInitialised) {
//                    c = (a.add(b)).divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//                    cPowerRoot = power(
//                            c,
//                            root_BigDecimal,
//                            e,
//                            a_Generic_BigDecimal,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRoot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        // Bisect
//                        c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                        cPowerRoot = power(
//                                c,
//                                root_BigDecimal,
//                                e,
//                                a_Generic_BigDecimal,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRoot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            result = c;
//                            notInitialised = false;
//                        }
//                    }
//                }
//            } else {
//                // Bisect
//                a = x.divide(TWO, decimalPlaces, a_RoundingMode);
//                BigDecimal b = result;
//                BigDecimal c;
//                BigDecimal aAddb;
//                BigDecimal aAddbSubtractTwo;
//                BigDecimal cPowerroot;
//                boolean notInitialised = true;
//                BigDecimal cPowerRoot;
//                while (notInitialised) {
//                    // Disect
//                    aAddb = a.add(b);
//                    aAddbSubtractTwo = aAddb.subtract(TWO);
//                    if (aAddbSubtractTwo.compareTo(BigDecimal.ZERO) != 1) {
//                        notInitialised = false;
//                    } else {
//                        c = (aAddb).divide(aAddbSubtractTwo, decimalPlaces, a_RoundingMode);
//                        cPowerRoot = power(
//                                c,
//                                root_BigDecimal,
//                                e,
//                                a_Generic_BigDecimal,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRoot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            while (notInitialised) {
//                                // Bisect
//                                a = a.divide(TWO, decimalPlaces, a_RoundingMode);
//                                b = c;
//                                aAddb = a.add(b);
//                                aAddbSubtractTwo = aAddb.subtract(BigDecimal.ONE);
//                                c = (aAddb).divide(aAddbSubtractTwo, decimalPlaces, a_RoundingMode);
//                                cPowerRoot = power(
//                                        c,
//                                        root_BigDecimal,
//                                        e,
//                                        a_Generic_BigDecimal,
//                                        decimalPlaces,
//                                        a_RoundingMode);
//                                if (cPowerRoot.compareTo(x) == 1) {
//                                    a = c;
//                                } else {
//                                    result = c;
//                                    notInitialised = false;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
    /**
     * @param x x >= 0
     * @param root
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    public static BigDecimal rootRoundIfNecessary(
            BigDecimal x,
            int root,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException(
                    "x < 0 in " + Generic_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (root == 0) {
            throw new IllegalArgumentException(
                    "root = 0 in " + Generic_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (root == 1) {
            BigDecimal result = new BigDecimal(x.toString());
            //return result;
            return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
        }
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root >= 999999999) {
//            Generic_BigDecimal a_Generic_BigDecimal =
//                    new Generic_BigDecimal();
            BigDecimal result = rootRoundIfNecessary(
                    x,
                    BigInteger.valueOf(root),
                    //a_Generic_BigDecimal,
                    decimalPlaces,
                    a_RoundingMode);
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            // This is thought to be an optimisation for a large x.scale()
            // a faster way is needed! This may generally be generally 
            // faster anyway but some tests are needed to be sure.
            int rootLength = Integer.toString(root).length();
            if (xscale < 10) {
                BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                //int precisionMinusScale = xprecision - xscale;
                BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal numerator = rootRoundIfNecessary(
                        numeratorUnrooted,
                        root,
                        //a_Generic_BigDecimal,
                        decimalPlaces + (rootLength * 2), // Can we cope with less or do we need more?
                        a_RoundingMode);
                BigDecimal denominator = rootRoundIfNecessary(
                        denominatorUnrooted,
                        root,
                        //a_Generic_BigDecimal,
                        decimalPlaces + rootLength, // Can we cope with less or do we need more?
                        a_RoundingMode);
                BigDecimal result = Generic_BigDecimal.divideRoundIfNecessary(
                        numerator,
                        denominator,
                        decimalPlaces,
                        a_RoundingMode);
                return result;
            } else {
                BigDecimal result = rootLessThanOne(
                        x,
                        root,
                        //a_Generic_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
                return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
            }
        }
        BigDecimal epsilon_BigDecimal =
                new BigDecimal(BigInteger.ONE, decimalPlaces);
        BigDecimal comparator = BigDecimal.ONE.add(epsilon_BigDecimal);
        // Check there is a rootRoundIfNecessary in the precision and return 0 if not
        BigInteger root_BigInteger = BigInteger.valueOf(root);
        boolean powerTest = powerTestAbove(
                x,
                comparator,
                root_BigInteger,
                256,
                decimalPlaces,
                a_RoundingMode);
        if (powerTest) {
            System.out.println("No root in the precision... ");
            //return BigDecimal.ZERO;
            return BigDecimal.ONE;
        }
        BigDecimal result;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        result = rootInitialisation(
                x,
                root_BigInteger,
                epsilon_BigDecimal,
                rootInitialisationMaxite,
                decimalPlaces,
                a_RoundingMode);
        //int newtonRaphsonMaxite = 100;
        result = newtonRaphson(
                x,
                result,
                root_BigInteger,
                epsilon_BigDecimal,
                //newtonRaphsonMaxite,
                decimalPlaces + 1);
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    public static BigDecimal rootNoRounding(
            BigDecimal x,
            int root) {
        // Deal with special cases
        if (x.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException(
                    "x < 0 in " + Generic_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(BigDecimal.ONE) == 0) {
            return BigDecimal.ONE;
        }
        if (root == 0) {
            throw new IllegalArgumentException(
                    "root = 0 in " + Generic_BigDecimal.class
                    + ".root(BigDecimal,int,int,RoundingMode)");
        }
        if (root == 1) {
            BigDecimal result = new BigDecimal(x.toString());
            return result;
        }
        // The current (Java6) limit for n in x.pow(n)
        // for BigDecimal x and int n is
        // -999999999 < n < 999999999
        if (root >= 999999999) {
//            Generic_BigDecimal a_Generic_BigDecimal =
//                    new Generic_BigDecimal();
            BigDecimal result = rootNoRounding(
                    x,
                    BigInteger.valueOf(root));
            return result;
            //return round(result, decimalPlaces, a_RoundingMode);
        }
        if (x.compareTo(BigDecimal.ONE) == -1) {
            int xscale = x.scale();
            // This is thought to be an optimisation for a large x.scale()
            // a faster way is needed! This may generally be generally 
            // faster anyway but some tests are needed to be sure.
            int rootLength = Integer.toString(root).length();
            if (xscale < 10) {
                BigDecimal numeratorUnrooted = new BigDecimal(x.unscaledValue());
                //int precisionMinusScale = xprecision - xscale;
                BigDecimal denominatorUnrooted = new BigDecimal(BigInteger.ONE, (-1 * xscale));
                BigDecimal numerator = rootNoRounding(
                        numeratorUnrooted,
                        root);
                BigDecimal denominator = rootNoRounding(
                        denominatorUnrooted,
                        root);
                BigDecimal result = divideNoRounding(
                        numerator,
                        denominator);
                return result;
            } else {
                BigDecimal result = rootLessThanOneNoRounding(
                        x,
                        root);
                return result;
            }
        }
        BigInteger root_BigInteger = BigInteger.valueOf(root);
        BigDecimal result;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        result = rootInitialisationNoRounding(
                x,
                root_BigInteger,
                rootInitialisationMaxite);
        //int newtonRaphsonMaxite = 100;
        result = newtonRaphsonNoRounding(
                x,
                result,
                root_BigInteger);
        return result;
    }

    /**
     * @param x 0 < x < 1
     * @param rootRoundIfNecessary
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    private static BigDecimal rootLessThanOne(
            BigDecimal x,
            int root,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        return rootLessThanOne(
                x,
                BigInteger.valueOf(root),
                //a_Generic_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * @param x 0 < x < 1
     * @param rootRoundIfNecessary
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    private static BigDecimal rootLessThanOneNoRounding(
            BigDecimal x,
            int root) {
        return rootLessThanOneNoRounding(
                x,
                BigInteger.valueOf(root));
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     *
     * @param x 0 < x < 1
     * @param rootRoundIfNecessary
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    private static BigDecimal rootLessThanOne(
            BigDecimal x,
            BigInteger root,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal epsilon_BigDecimal =
                new BigDecimal(BigInteger.ONE, decimalPlaces);
        //BigDecimal comparator = BigDecimal.ONE.subtract(epsilon_BigDecimal);
//        // Check there is a rootRoundIfNecessary in the precision and return 1 if not
//        boolean powerTest = powerTestBelow(
//                epsilon_BigDecimal,//comparator,
//                x,
//                rootRoundIfNecessary,
//                256,
//                decimalPlaces,
//                a_RoundingMode);
//        if (powerTest) {
//            System.out.println("No rootRoundIfNecessary in the precision... ");
//            return BigDecimal.ONE;
//        }

        BigDecimal result;// = BigDecimal.ONE;
        if (x.scale() - x.precision() > decimalPlaces) {
            result = BigDecimal.ONE.subtract(epsilon_BigDecimal);
        } else {
            int rootInitialisationMaxite = 10;
            //int rootInitialisationMaxite = x.scale();
            result = rootInitialisationLessThanOne(
                    x,
                    root,
                    epsilon_BigDecimal,
                    rootInitialisationMaxite,
                    decimalPlaces,
                    a_RoundingMode);
        }
        //result = new BigDecimal(x.add(epsilon_BigDecimal).toString());
        int newtonRaphsonMaxite = decimalPlaces + x.scale();
        return newtonRaphsonLessThanOne(
                x,
                result,
                root,
                epsilon_BigDecimal,
                newtonRaphsonMaxite,
                //a_Generic_BigDecimal,
                decimalPlaces,
                a_RoundingMode);

// For rootRoundIfNecessary is large this takes too long 
//        BigDecimal tenPowerRoot = power(
//                BigDecimal.TEN,
//                rootRoundIfNecessary,
//                256,
//                0,
//                RoundingMode.UNNECESSARY);
//        BigDecimal xunscaled = multiply(
//                x,
//                tenPowerRoot,
//                0,
//                RoundingMode.UNNECESSARY);
//        BigDecimal result0 = rootRoundIfNecessary(
//                xunscaled,
//                rootRoundIfNecessary,
//                decimalPlaces + 1,
//                a_RoundingMode);
//        BigDecimal result = divide(
//                result0,
//                BigDecimal.TEN,
//                decimalPlaces + 1,
//                a_RoundingMode);
//        return result;
    }

    private static BigDecimal rootLessThanOneNoRounding(
            BigDecimal x,
            BigInteger root) {
        BigDecimal result;// = BigDecimal.ONE;
        int rootInitialisationMaxite = 10;
        result = rootInitialisationLessThanOneNoRounding(
                x,
                root,
                rootInitialisationMaxite);
        return newtonRaphsonLessThanOneNoRounding(
                x,
                result,
                root);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm
     *
     * @param x 0 < x < 1
     * @param rootRoundIfNecessary
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return The rootRoundIfNecessary rootRoundIfNecessary of x to
     * decimalPlaces precision.
     */
    private static BigDecimal rootLessThanOne(
            BigDecimal x,
            BigInteger root,
            BigDecimal initialEstimate,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal epsilon_BigDecimal =
                new BigDecimal(BigInteger.ONE, decimalPlaces);
        int newtonRaphsonMaxite = decimalPlaces + x.scale();
        return newtonRaphsonLessThanOne(
                x,
                initialEstimate,
                root,
                epsilon_BigDecimal,
                newtonRaphsonMaxite,
                //a_Generic_BigDecimal,
                decimalPlaces,
                a_RoundingMode);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm # Make an initial guess
     * x0 # Set x_{k+1} = \frac{1}{n} \left[{(n-1)x_k
     * +\frac{A}{x_k^{n-1}}}\right] Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonLessThanOne(
            BigDecimal x,
            BigDecimal result0,
            BigInteger root,
            BigDecimal epsilon,
            int maxite,
            //Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal result = new BigDecimal(result0.toString());
        int precision = decimalPlaces + 5;
        int divprecision = decimalPlaces + 10;
        //MathContext a_MathContext = new MathContext(precision);
        BigDecimal rootsubtract1 = new BigDecimal(root.subtract(BigInteger.ONE));
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal resultipowrootsubtract1;
        BigDecimal adivresultipowrootsubtract1;
        BigDecimal inside;
        MathContext add_MathContext;
        int generalAddPrecision = decimalPlaces + root.toString().length() + 2;
//        MathContext add_MathContext = new MathContext(
//                decimalPlaces + rootRoundIfNecessary.toString().length() + 1,
//                a_RoundingMode);

        boolean maxiteReached = true;

        // Newton Raphson
        for (int i = 0; i < maxite; i++) {
            result0 = new BigDecimal(result.toString());
            p = result.multiply(rootsubtract1);
            resultipowrootsubtract1 = Generic_BigDecimal.power(
                    result,
                    rootsubtract1,
                    //a_Generic_BigDecimal,
                    root.intValue() + precision,
                    a_RoundingMode);
            adivresultipowrootsubtract1 = Generic_BigDecimal.divideRoundIfNecessary(
                    x,
                    resultipowrootsubtract1,
                    precision,
                    a_RoundingMode);
            add_MathContext = new MathContext(
                    p.toBigInteger().toString().length() + generalAddPrecision,
                    a_RoundingMode);
            inside = p.add(adivresultipowrootsubtract1, add_MathContext);
            result = Generic_BigDecimal.divideRoundIfNecessary(
                    inside,
                    root,
                    precision,
                    a_RoundingMode);
//            result = Generic_BigDecimal.divide(
//                    inside,
//                    rootRoundIfNecessary, 
//                    divprecision, 
//                    a_RoundingMode);
            // Test for convergence
            if (result0.subtract(result).abs().compareTo(epsilon) != 1) {
                //System.out.println("Root found in iteration " + i + " out of " + maxite);
                maxiteReached = false;
                break;




            }
        }
        if (maxiteReached) {
            System.out.println(
                    "maxite reached without finding root in "
                    + Generic_BigDecimal.class.getName() + ".newtonRaphson(...)"
                    + " previousResult_BigDecimal0.subtract(result).abs() "
                    + result0.subtract(result).abs());
        }
        return roundIfNecessary(result, decimalPlaces, a_RoundingMode);
    }

    /**
     * http://en.wikipedia.org/wiki/Nth_root_algorithm # Make an initial guess
     * x0 # Set x_{k+1} = \frac{1}{n} \left[{(n-1)x_k
     * +\frac{A}{x_k^{n-1}}}\right] Uses MathContext division
     *
     * @param x
     * @param result0
     * @param rootRoundIfNecessary
     * @param epsilon
     * @param maxite
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return Re-approximation of result0 using Newton Raphson method
     */
    private static BigDecimal newtonRaphsonLessThanOneNoRounding(
            BigDecimal x,
            BigDecimal result0,
            BigInteger root) {
        BigDecimal result = new BigDecimal(result0.toString());
        BigDecimal rootsubtract1 = new BigDecimal(root.subtract(BigInteger.ONE));
        // p = (n-1)xk        
        BigDecimal p;
        BigDecimal resultipowrootsubtract1;
        BigDecimal adivresultipowrootsubtract1;
        BigDecimal inside;
        boolean maxiteReached = true;
        // Newton Raphson
        while (true) {
            result0 = new BigDecimal(result.toString());
            p = result.multiply(rootsubtract1);
            resultipowrootsubtract1 = Generic_BigDecimal.powerNoRounding(
                    result,
                    rootsubtract1);
            adivresultipowrootsubtract1 = divideNoRounding(
                    x,
                    resultipowrootsubtract1);
            inside = p.add(adivresultipowrootsubtract1);
            result = divideNoRounding(
                    inside,
                    root);
            // Test for convergence
            if (result0.compareTo(result) == 1) {
                break;
            }
        }
        return result;
    }

//    /**
//     * @return true if there is a rootRoundIfNecessary in the precision and false otherwise
//     */
//    private boolean isRootInPrecision(
//            BigDecimal x,
//            int rootRoundIfNecessary,
//            BigDecimal epsilon_BigDecimal,
//            int precision,
//            RoundingMode a_RoundingMode){
//        boolean toCompare = true;
//            if (x.compareTo(BigDecimal.ONE) == 1) {
//                BigDecimal check = power(
//                        epsilon_BigDecimal,
//                        rootRoundIfNecessary,
//                        rootRoundIfNecessary,
//                        4,
//                        precision,
//                        a_RoundingMode);
////                BigDecimal check = power( // Too slow...
////                        epsilon_BigDecimal,
////                        rootRoundIfNecessary,
////                        precision,
////                        a_RoundingMode);
//                if (check.compareTo(x) == 1) {
//                    System.out.println("No rootRoundIfNecessary in the precision...");
//                    return BigDecimal.ZERO;
//                }
//            }
//    }
//    private static BigDecimal rootInitialisation(
//            BigDecimal x,
//            int rootRoundIfNecessary,
//            //BigDecimal root_BigDecimal,
//            BigDecimal maxError_BigDecimal,
//            BigDecimal a0,
//            int decimalPlaces,
//            RoundingMode a_RoundingMode) {
//        BigDecimal result;
//        // Initialise toCompare and previousResult_BigDecimal
//        if (x.compareTo(BigDecimal.ONE) == -1) {
//            return rootInitialisationLessThanOne(
//                    x,
//                    rootRoundIfNecessary,
//                    //root_BigDecimal,
//                    maxError_BigDecimal,
//                    a0,
//                    decimalPlaces,
//                    a_RoundingMode);
//        } else {
//            result = BigDecimal.ONE.add(maxError_BigDecimal);
//            BigDecimal a = x.divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//            if (a.compareTo(BigDecimal.ONE) == 1) {
//                result = BigDecimal.ONE.add(maxError_BigDecimal);
//                // Disect
//                BigDecimal b = a0;
//                BigDecimal c;
//                //BigDecimal cPowerRoot;
//                boolean cPowerRootTest;
//                boolean notInitialised = true;
//                while (notInitialised) {
//                    c = (a.add(b)).divide(root_BigDecimal, decimalPlaces, a_RoundingMode);
//                    cPowerRootTest = powerTestAbove(
//                            x,
//                            c,
//                            rootRoundIfNecessary,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    if (cpowerroot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        // Bisect
//                        c = (a.add(b)).divide(TWO, decimalPlaces, a_RoundingMode);
//                        cPowerRootTest = powerTestAbove(
//                                x,
//                                c,
//                                rootRoundIfNecessary,
//                                64,
//                                decimalPlaces,
//                                a_RoundingMode);
//                        if (cPowerRootTest) {
////                        cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                        if (cpowerroot.compareTo(x) == 1) {
//                            a = c;
//                        } else {
//                            result = c;
//                            notInitialised = false;
//                        }
//                    }
//                }
//            } else {
//                boolean resultpowerroottest = powerTestAbove(
//                        x,
//                        result,
//                        rootRoundIfNecessary,
//                        64,
//                        decimalPlaces,
//                        a_RoundingMode);
//                if (resultpowerroottest) {
//                    // Root cannot be found within current precision...
//                    // return BigDecimal.ZERO or BigDecimal.ONE?
//                    int debug = 1;
//                    return BigDecimal.ONE;
//                }
////                BigDecimal resultpowroot = power(
////                        result,
////                        rootRoundIfNecessary,
////                        64,
////                        decimalPlaces,
////                        a_RoundingMode);
////                if (resultpowroot.compareTo(x) == 1) {
////                    // Root cannot be found within current precision...
////                    // return BigDecimal.ZERO or BigDecimal.ONE?
////                    int debug = 1;
////                }
//                a = x.divide(TWO, decimalPlaces, a_RoundingMode);
//                BigDecimal b = new BigDecimal(result.toString());
//                BigDecimal c;
//                //BigDecimal cPowerRoot;
//                boolean cPowerRootTest;
//                BigDecimal aAddb;
//                boolean notInitialised = true;
//                int maxite = 1000;
//                int i = 0;
//                while (notInitialised) {
//                    if (i == maxite) {
//                        notInitialised = false;
//                    }
//                    // Bisect
//                    aAddb = a.add(b);
//                    c = (aAddb).divide(TWO, decimalPlaces, a_RoundingMode);
//                    cPowerRootTest = powerTestAbove(
//                            x,
//                            c,
//                            rootRoundIfNecessary,
//                            64,
//                            decimalPlaces,
//                            a_RoundingMode);
//                    if (cPowerRootTest) {
////                    cpowerroot = power(
////                            c,
////                            rootRoundIfNecessary,
////                            64,
////                            decimalPlaces,
////                            a_RoundingMode);
////                    if (cpowerroot.compareTo(x) == 1) {
//                        a = c;
//                    } else {
//                        //result = c;
//                        notInitialised = false;
//                    }
//                    result = a;
//                    i++;
//                }
//            }
//        }
//        return result;
//    }
    /**
     *
     * @param x 0 < x < 1
     * @param root_BigDecimal
     * @param maxError_BigDecimal
     * @param a0
     * @param decimalPlaces
     * @param a_MathContext
     * @param a_RoundingMode
     * @return
     */
    private static BigDecimal rootInitialisationLessThanOne(
            BigDecimal x,
            BigInteger root,
            BigDecimal epsilon_BigDecimal,
            int maxite,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal result = BigDecimal.ONE.subtract(epsilon_BigDecimal);
//        BigDecimal result = BigDecimal.ONE.subtract(Generic_BigDecimal.reciprocal(new BigDecimal(rootRoundIfNecessary), decimalPlaces, a_RoundingMode));
//        return result;
        boolean powerTestBelow;
//        boolean powerTestBelow = powerTestBelow(
//                x,
//                result,
//                rootRoundIfNecessary,
//                64,
//                decimalPlaces,
//                a_RoundingMode);
//        if (powerTestBelow) {
//            // No rootRoundIfNecessary in precision
//            int debug = 1;
//            return b;
//        }
        BigDecimal a = new BigDecimal(x.toString());
        b = result;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger rootdiv = new BigInteger(root.toString(maxite));
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsubtracta = b.subtract(a);
            c = divideRoundIfNecessary(
                    b.subtract(a),
                    root,
                    decimalPlaces + 1,
                    a_RoundingMode);
            c = b.subtract(c);
            powerTestBelow = powerTestBelow(
                    x,
                    c,
                    root,
                    64,
                    decimalPlaces,
                    a_RoundingMode);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                rootdiv = rootdiv.divide(Generic_BigInteger.Two);
                result = b;
            }
            // Bisect;
            c = divideRoundIfNecessary(
                    b.subtract(a),
                    TWO,
                    decimalPlaces + 1,
                    a_RoundingMode);
            c = b.subtract(c);
            powerTestBelow = powerTestBelow(
                    x,
                    c,
                    root,
                    64,
                    decimalPlaces,
                    a_RoundingMode);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                result = b;
            }
            i++;
        }
        return result;
    }

    /**
     *
     * @param x 0 < x < 1
     * @param root_BigDecimal
     * @param maxError_BigDecimal
     * @param a0
     * @param decimalPlaces
     * @param a_MathContext
     * @param a_RoundingMode
     * @return
     */
    private static BigDecimal rootInitialisationLessThanOneNoRounding(
            BigDecimal x,
            BigInteger root,
            int maxite) {
        BigDecimal b;// = BigDecimal.ONE;
        BigDecimal result = BigDecimal.ONE;
        boolean powerTestBelow;
        BigDecimal a = new BigDecimal(x.toString());
        b = result;
        BigDecimal c;
//        boolean notInitialised = true;
        BigInteger rootdiv = new BigInteger(root.toString(maxite));
        int i = 0;
        while (i < maxite) {
            // Disect;
            BigDecimal bsubtracta = b.subtract(a);
            c = divideNoRounding(
                    b.subtract(a),
                    root);
            c = b.subtract(c);
            powerTestBelow = powerTestBelowNoRounding(
                    x,
                    c,
                    root,
                    64);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                rootdiv = rootdiv.divide(Generic_BigInteger.Two);
                result = b;
            }
            // Bisect;
            c = divideNoRounding(
                    b.subtract(a),
                    TWO);
            c = b.subtract(c);
            powerTestBelow = powerTestBelowNoRounding(
                    x,
                    c,
                    root,
                    64);
            if (powerTestBelow) {
                a = c;
            } else {
                b = c;
                result = b;
            }
            i++;
        }
        return result;
    }

    /**
     * Returns the square rootRoundIfNecessary of x as a BigDecimal rounded to
     * decimalPlaces decimal places.
     *
     * @param roundingMode
     *
     * @param x
     * @param decimalPlaces
     * @return Square rootRoundIfNecessary of input
     */
    public static BigDecimal sqrt(
            BigDecimal x,
            int decimalPlaces,
            RoundingMode roundingMode) {
// @TODO This used to be a hack, check tests are working for the new implementation.
//        if (x.compareTo(BigDecimal.ZERO) == 1 && x.compareTo(BigDecimal.ONE) == -1) {
//            return BigDecimal.valueOf(Math.sqrt(x.doubleValue()));
//        }
        return power(
                x,
                new BigDecimal("0.5"),
                decimalPlaces,
                roundingMode);
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param a_Random
     * @param probability 0 <= probability <=1
     * @param a_RoundingMode
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(
            Random a_Random,
            BigDecimal probability,
            RoundingMode a_RoundingMode) {
        return randomUniformTest(
                a_Random,
                probability,
                probability.scale(),
                a_RoundingMode);
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param a_Random
     * @param probability 0 <= probability <=1
     * @param decimalPlaces
     * @param a_RoundingMode
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(
            Random a_Random,
            BigDecimal probability,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        // Special case probability <= 0
        if (probability.compareTo(BigDecimal.ZERO) != 1) {
            if (probability.compareTo(BigDecimal.ZERO) == 0) {
                return false;
            } else {
                System.out.println(
                        "Warning probabilty negative in "
                        + Generic_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning false.");
                return false;
            }
        }
        // Special case probability >= 1
        if (probability.compareTo(BigDecimal.ONE) != -1) {
            if (probability.compareTo(BigDecimal.ONE) == 0) {
                return true;
            } else {
                System.out.println(
                        "Warning probabilty greater > 1 in "
                        + Generic_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning true.");
                return true;
            }
        }
        // Set decimalPlace precision to maximum of decimalPlaces and
        // probability.scale();
        int probabilityScale = probability.scale();
        if (decimalPlaces < probabilityScale) {
//            System.out.println(
//                    "Input decimalPlaces < probabilty.scale() in "
//                    + Generic_BigDecimal.class
//                    + ".randomUniformTest(Random,BigDecimal). "
//                    + "Set decimalPlaces = probabilty.scale().");
            decimalPlaces = probabilityScale;
        }
        BigDecimal midTestValue = new BigDecimal("0.5");
        BigDecimal half_BigDecimal = new BigDecimal("0.5");
        BigDecimal two_BigDecimal = new BigDecimal("2.0");
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        if (probability.compareTo(midTestValue) == -1) {
            return randomTest(
                    a_Random,
                    probability,
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    midTestValue,
                    half_BigDecimal,
                    two_BigDecimal,
                    decimalPlaces,
                    a_RoundingMode);
        } else {
            return !randomTest(
                    a_Random,
                    BigDecimal.ONE.subtract(probability),
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    midTestValue,
                    half_BigDecimal,
                    two_BigDecimal,
                    decimalPlaces,
                    a_RoundingMode);
        }
    }

    /**
     * Effectively this is the same as generating a random number between 0 and
     * 1 and comparing it with probability and if it were higher then return
     * false and otherwise return true
     *
     * @param a_Random
     * @param probability 0 <= probability <=1
     * @param a_MathContext
     * @return true or false based on a random uniform test of probability
     */
    public static boolean randomUniformTest(
            Random a_Random,
            BigDecimal probability,
            MathContext a_MathContext) {
        // Special case probability <= 0
        if (probability.compareTo(BigDecimal.ZERO) != 1) {
            if (probability.compareTo(BigDecimal.ZERO) == 0) {
                return false;
            } else {
                System.out.println(
                        "Warning probabilty negative in "
                        + Generic_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning false.");
                return false;
            }
        }
        // Special case probability >= 1
        if (probability.compareTo(BigDecimal.ONE) != -1) {
            if (probability.compareTo(BigDecimal.ONE) == 0) {
                return true;
            } else {
                System.out.println(
                        "Warning probabilty greater > 1 in "
                        + Generic_BigDecimal.class
                        + ".randomUniformTest(Random,BigDecimal). "
                        + "Returning true.");
                return true;
            }
        }
        BigDecimal midTestValue = new BigDecimal("0.5");
        BigDecimal half_BigDecimal = new BigDecimal("0.5");
        BigDecimal two_BigDecimal = new BigDecimal("2.0");
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        if (probability.compareTo(midTestValue) == -1) {
            return randomTest(
                    a_Random,
                    probability,
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    midTestValue,
                    half_BigDecimal,
                    two_BigDecimal,
                    a_MathContext);
        } else {
            return !randomTest(
                    a_Random,
                    BigDecimal.ONE.subtract(probability),
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    midTestValue,
                    half_BigDecimal,
                    two_BigDecimal,
                    a_MathContext);
        }
    }

    private static boolean randomTest(
            Random a_Random,
            BigDecimal probability,
            BigDecimal minTestValue,
            BigDecimal maxTestValue,
            BigDecimal midTestValue,
            BigDecimal half_BigDecimal,
            BigDecimal two_BigDecimal,
            int decimalPlaces,
            RoundingMode a_RoundingMode) {
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        boolean above = a_Random.nextBoolean();
        if (above) {
            if (probability.compareTo(midTestValue) == 1) {
                // Test
                BigDecimal newMinTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMinTestValue.add(maxTestValue),
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
                return randomTest(
                        a_Random,
                        probability,
                        newMinTestValue,
                        maxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
            } else {
                return false;
            }
        } else {
            if (probability.compareTo(midTestValue) == 1) {
                return true;
            } else {
                //Test
                BigDecimal newMaxTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMaxTestValue.add(minTestValue),
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
                return randomTest(
                        a_Random,
                        probability,
                        minTestValue,
                        newMaxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        decimalPlaces,
                        a_RoundingMode);
            }
        }
    }

    private static boolean randomTest(
            Random a_Random,
            BigDecimal probability,
            BigDecimal minTestValue,
            BigDecimal maxTestValue,
            BigDecimal midTestValue,
            BigDecimal half_BigDecimal,
            BigDecimal two_BigDecimal,
            MathContext a_MathContext) {
        if (probability.compareTo(midTestValue) == 0) {
            return a_Random.nextBoolean();
        }
        boolean above = a_Random.nextBoolean();
        if (above) {
            if (probability.compareTo(midTestValue) == 1) {
                // Test
                BigDecimal newMinTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMinTestValue.add(maxTestValue),
                        two_BigDecimal,
                        a_MathContext);
                return randomTest(
                        a_Random,
                        probability,
                        newMinTestValue,
                        maxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        a_MathContext);
            } else {
                return false;
            }
        } else {
            if (probability.compareTo(midTestValue) == 1) {
                return true;
            } else {
                //Test
                BigDecimal newMaxTestValue = midTestValue;
                BigDecimal newMidTestValue = divideRoundIfNecessary(
                        newMaxTestValue.add(minTestValue),
                        two_BigDecimal,
                        a_MathContext);
                return randomTest(
                        a_Random,
                        probability,
                        minTestValue,
                        newMaxTestValue,
                        newMidTestValue,
                        half_BigDecimal,
                        two_BigDecimal,
                        a_MathContext);
            }
        }
    }

//    /**
//     * Effectively this is the same as generating a random number between 0 and
//     * 1 and comparing it with probability and if it were higher then return
//     * false and otherwise return true
//     * @param a_Random
//     * @param probability
//     * @return true or false based on a random uniform test of probability
//     */
//    public static boolean randomUniformTest(
//            Random a_Random,
//            BigDecimal probability) {
//        BigDecimal testValue0 = new BigDecimal("0.5");
//        BigDecimal testValue1;
//        BigDecimal two_BigDecimal = new BigDecimal("2.0");
//        boolean test;
//        do {
//            test = a_Random.nextBoolean();
//            if (test) {
//                if (probability.compareTo(testValue0) != -1) {
//                    return false;
//                } else {
//                    testValue1 = testValue0.divide(two_BigDecimal);
//                    if (probability.compareTo(testValue1) == 1) {
//                        return true;
//                    }
//                    testValue0 = testValue1;
//                }
//            } else {
//                return false;
//            }
//        } while (true);
//    }
    /**
     *
     * @param a_Generic_BigInteger this contains the random and the powers of
     * two and is passed in for efficiency.
     * @param decimalPlaces
     * @param lowerLimit
     * @param upperLimit
     * @return a pseudo randomly constructed BigDecimal in the range from
     * lowerLimit to upperLimit inclusive and that has up to decimalPlaces
     * number of decimal places
     */
    public static BigDecimal getRandom(
            Generic_BigInteger a_Generic_BigInteger,
            int decimalPlaces,
            BigDecimal lowerLimit,
            BigDecimal upperLimit) {
        //BigDecimal resolution = new BigDecimal(BigInteger.ONE,decimalPlaces);
        BigDecimal range = upperLimit.subtract(lowerLimit);
        BigInteger rescaledRange = range.scaleByPowerOfTen(decimalPlaces).toBigInteger();
        BigInteger random_BigInteger = a_Generic_BigInteger.getRandom(
                rescaledRange);
        BigDecimal random_BigDecimal = new BigDecimal(random_BigInteger, decimalPlaces);
        BigDecimal result = random_BigDecimal.add(lowerLimit);
        return result;
    }

    public static BigDecimal getRandom(
            Generic_BigInteger a_Generic_BigInteger,
            Random random,
            int decimalPlaces,
            BigDecimal lowerLimit,
            BigDecimal upperLimit) {
        //BigDecimal resolution = new BigDecimal(BigInteger.ONE,decimalPlaces);
        BigDecimal range = upperLimit.subtract(lowerLimit);
        BigInteger rescaledRange = range.scaleByPowerOfTen(decimalPlaces).toBigInteger();
        BigInteger random_BigInteger = a_Generic_BigInteger.getRandom(
                random,
                rescaledRange);
        BigDecimal random_BigDecimal = new BigDecimal(random_BigInteger, decimalPlaces);
        BigDecimal result = random_BigDecimal.add(lowerLimit);
        return result;
    }

    /**
     * Provided for convenience.
     *
     * @param a_Generic_Number
     * @param decimalPlaces
     * @return a random BigDecimal between 0 and 1 inclusive which can have up
     * to decimalPlaces number of decimal places
     */
    public static BigDecimal getRandom(
            Generic_Number a_Generic_Number,
            int decimalPlaces) {
        //Generic_BigDecimal a_Generic_BigDecimal = new Generic_BigDecimal();
        Random[] random = a_Generic_Number.get_RandomArrayMinLength(
                decimalPlaces);
        //System.out.println("Got Random[] size " + random.length);
        String value = "0.";
        int digit;
        int ten_int = 10;
        for (int i = 0; i < decimalPlaces; i++) {
            digit = random[i].nextInt(ten_int);
            value += digit;
        }
        int length = value.length();
        // Tidy values ending with zero's
        while (value.endsWith("0")) {
            length--;
            value = value.substring(0, length);
        }
        if (value.endsWith(".")) {
            value = "0";
        }
        BigDecimal result = new BigDecimal(value);
        //result.stripTrailingZeros();
        return result;
    }

    /**
     * @param x
     * @return true iff last digit of x is even
     */
    public static boolean isEven(BigDecimal x) {
        String xString = x.toPlainString();
        String lastDigit_String = xString.substring(xString.length() - 1);
        int lastDigit_int = new Integer(lastDigit_String);
        return Generic_int.isEven(lastDigit_int);
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param a_Generic_BigDecimal
     * @param x
     * @param decimalPlacePrecision
     * @param aRoundingMode
     * @return
     */
    public static BigDecimal cos(
            BigDecimal x,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlacePrecision,
            RoundingMode aRoundingMode) {
        BigDecimal aPI = a_Generic_BigDecimal.get_PI();
        BigDecimal aPIBy2 = Generic_BigDecimal.divideRoundIfNecessary(
                aPI,
                BigInteger.valueOf(2),
                decimalPlacePrecision + 2,
                aRoundingMode);
        return sin(x.add(aPIBy2),
                a_Generic_BigDecimal,
                decimalPlacePrecision,
                aRoundingMode);
//        Generic_BigInteger aGeneric_BigInteger = a_Generic_BigDecimal._Generic_BigInteger;
//        BigDecimal aPI1000 = a_Generic_BigDecimal.get_PI();
//        // cosx = 1-(x^2)/(2!)+(x^4)/(4!)-(x^6)/(6!)+...
//        BigDecimal precision = new BigDecimal(BigInteger.ONE, decimalPlacePrecision + 2);
//        BigDecimal cosx = BigDecimal.ONE;
//        int factor = 2;
//        BigInteger factorial;
//        BigDecimal power;
//        boolean alternator = true;
//        while (true) {
//            factorial = aGeneric_BigInteger.factorial(factor);
//            power = Generic_BigDecimal.power(
//                    x,
//                    factor,
//                    decimalPlacePrecision + 2, aRoundingMode);
//            BigDecimal division = Generic_BigDecimal.divideRoundIfNecessary(
//                    power,
//                    factorial,
//                    decimalPlacePrecision + 2, aRoundingMode);
//            if (division.compareTo(precision) != -1) {
//                if (alternator) {
//                    alternator = false;
//                    cosx = cosx.subtract(division);
//                } else {
//                    alternator = true;
//                    cosx = cosx.add(division);
//                }
//            } else {
//                break;
//            }
//            factor += 2;
//        }
//        cosx = Generic_BigDecimal.roundIfNecessary(
//                cosx, decimalPlacePrecision, aRoundingMode);
//        return cosx;
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param a_Generic_BigDecimal
     * @param x
     * @param decimalPlacePrecision
     * @param aRoundingMode
     * @return
     */
    public static BigDecimal sin(
            BigDecimal x,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlacePrecision,
            RoundingMode aRoundingMode) {
        BigDecimal aPI = a_Generic_BigDecimal.get_PI();
        BigDecimal twoPI = BigDecimal.valueOf(2).multiply(aPI);
        while (x.compareTo(BigDecimal.ZERO) == -1) {
            x = x.add(twoPI);
        }
        while (x.compareTo(twoPI) == 1) {
            x = x.subtract(twoPI);
        }
        // SpecialCases
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(aPI) == 0) {
            return BigDecimal.ZERO;
        }
        if (x.compareTo(twoPI) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal aPIBy2 = Generic_BigDecimal.divideRoundIfNecessary(
                aPI,
                BigInteger.valueOf(2),
                decimalPlacePrecision + 2,
                aRoundingMode);
        return sinNoCaseCheck(
                x,
                aPI,
                twoPI,
                aPIBy2,
                a_Generic_BigDecimal,
                decimalPlacePrecision,
                aRoundingMode);
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param a_Generic_BigInteger
     * @param x
     * @param twoPI
     * @param decimalPlacePrecision
     * @param aPIBy2
     * @param aRoundingMode
     * @param a_Generic_BigDecimal
     * @return
     */
    protected static BigDecimal sinNoCaseCheck(
            BigDecimal x,
            BigDecimal aPI,
            BigDecimal twoPI,
            BigDecimal aPIBy2,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlacePrecision,
            RoundingMode aRoundingMode) {
        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
            return sinAngleBetweenZeroAndPI(
                    x,
                    aPI,
                    twoPI,
                    a_Generic_BigDecimal,
                    decimalPlacePrecision,
                    aRoundingMode);
        } else {
            if (x.compareTo(aPI) == -1) {
                return sinAngleBetweenZeroAndPI(
                        aPI.subtract(x),
                        aPI,
                        twoPI,
                        a_Generic_BigDecimal,
                        decimalPlacePrecision,
                        aRoundingMode);
            }
            BigDecimal uncorrectedResult = sinNoCaseCheck(
                    twoPI.subtract(x),
                    aPI,
                    twoPI,
                    aPIBy2,
                    a_Generic_BigDecimal,
                    decimalPlacePrecision,
                    aRoundingMode);
            return uncorrectedResult.negate();
        }
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     *
     * @param a_Generic_BigInteger
     * @param aPI
     * @param x
     * @param twoPI
     * @param decimalPlacePrecision
     * @param a_Generic_BigDecimal
     * @param aRoundingMode
     * @return
     */
    protected static BigDecimal sinAngleBetweenZeroAndPI(
            BigDecimal x,
            BigDecimal aPI,
            BigDecimal twoPI,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlacePrecision,
            RoundingMode aRoundingMode) {
        BigDecimal aPIBy2 = Generic_BigDecimal.divideRoundIfNecessary(
                aPI,
                BigInteger.valueOf(2),
                decimalPlacePrecision + 2,
                aRoundingMode);
        // sinx = 1-(x^3)/(3!)+(x^5)/(5!)-(x^7)/(7!)+... (1)
        Generic_BigInteger aGeneric_BigInteger = a_Generic_BigDecimal._Generic_BigInteger;
        if (x.compareTo(BigDecimal.ZERO) != -1 && x.compareTo(aPIBy2) != 1) {
            BigDecimal precision = new BigDecimal(BigInteger.ONE, decimalPlacePrecision + 4);
            BigDecimal sinx = new BigDecimal(x.toString());
            int factor = 3;
            BigInteger factorial;
            BigDecimal power;
            boolean alternator = true;
            while (true) {
                factorial = aGeneric_BigInteger.factorial(factor);
                power = Generic_BigDecimal.power(
                        x,
                        factor,
                        decimalPlacePrecision + 2, aRoundingMode);
                BigDecimal division = Generic_BigDecimal.divideRoundIfNecessary(
                        power,
                        factorial,
                        decimalPlacePrecision + 2, aRoundingMode);
                if (division.compareTo(precision) != -1) {
                    if (alternator) {
                        alternator = false;
                        sinx = sinx.subtract(division);
                    } else {
                        alternator = true;
                        sinx = sinx.add(division);
                    }
                } else {
                    break;
                }
                factor += 2;
            }
            sinx = Generic_BigDecimal.roundIfNecessary(
                    sinx, decimalPlacePrecision, aRoundingMode);
            return sinx;
        }
        return null;
    }

    /**
     * http://en.wikipedia.org/wiki/Cosine#Sine.2C_cosine.2C_and_tangent
     * Untested I am unsure if holding sinx to an additional 10 decimal places
     * and holding cosx to an additional 10 decimal places is sufficient.
     *
     * @param a_Generic_BigDecimal
     * @param x
     * @param decimalPlacePrecision
     * @param aRoundingMode
     * @return
     */
    public static BigDecimal tan(
            BigDecimal x,
            Generic_BigDecimal a_Generic_BigDecimal,
            int decimalPlacePrecision,
            RoundingMode aRoundingMode) {
        BigDecimal sinx = sin(
                x,
                a_Generic_BigDecimal,
                decimalPlacePrecision + 10,
                aRoundingMode);
        sinx = Generic_BigDecimal.roundIfNecessary(
                sinx, 
                decimalPlacePrecision + 8,
                RoundingMode.DOWN);
        BigDecimal cosx = cos(
                x,
                a_Generic_BigDecimal, 
                decimalPlacePrecision + 10,
                aRoundingMode);
        cosx = Generic_BigDecimal.roundIfNecessary(
                cosx,
                decimalPlacePrecision + 8,
                RoundingMode.DOWN);
        if (cosx.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        BigDecimal tanx = Generic_BigDecimal.divideRoundIfNecessary(
                sinx,
                cosx,
                decimalPlacePrecision, 
                aRoundingMode);
        return tanx;
    }
}
