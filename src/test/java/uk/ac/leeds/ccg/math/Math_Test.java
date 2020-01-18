/*
 * Copyright 2019 Centre for Computational Geography, University of Leeds.
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

import java.math.RoundingMode;

/**
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_Test {
    public void printTest(int test) {
        System.out.println("Test " + test);
    }

    public void printX(Number x) {
        System.out.println("x " + x);
    }

    public void printY(Number y) {
        System.out.println("y " + y);
    }

    public void printTestAndX(int test, Number x) {
        printTest(test);
        printX(x);
    }

    public void printTestAndXAndY(int test, Number x, Number y) {
        printTestAndX(test, x);
        printY(y);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            Number result) {
        printTestAndX(test, x);
        System.out.println(funcName + "(x) " + result);
    }
    
    public void printFunctionTest(String funcName, int test, Number x,
            String result) {
        printTestAndX(test, x);
        System.out.println(funcName + "(x) " + result);
    }
    
    public void printFunctionTest(String funcName, int test, Number x,
            boolean result) {
        printTestAndX(test, x);
        System.out.println(funcName + "(x) " + result);
    }

    public void printFunctionTest(String funcName, int test, String s,
            Number result) {
        printTest(test);
        System.out.println("s " + s);
        System.out.println(funcName + "(s) " + result);
    }

    public void printFunctionTest(String funcName, int test, String s,
            boolean result) {
        printTest(test);
        System.out.println("s " + s);
        System.out.println(funcName + "(s) " + result);
    }

    public void printFunctionTest(String funcName, int test, String s,
            int dp, boolean result) {
        printTest(test);
        System.out.println("s " + s);
        printDecimalPlaces(dp);
        System.out.println(funcName + "(s,dp) " + result);
    }
    
    public void printFunctionTest(String funcName, int test, Number x,
            Number y, Number result) {
        printTestAndXAndY(test, x, y);
        System.out.println(funcName + "(x,y) " + result);
    }

    public void printRoundingMode(RoundingMode rm) {
        System.out.println("RoundingMode (rm) " + rm);
    }

    public void printDecimalPlaces(int dp) {
        System.out.println("decimal places (dp) " + dp);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            int dp, RoundingMode rm, Number result) {
        printTestAndX(test, x);
        printDecimalPlaces(dp);
        printRoundingMode(rm);
        System.out.println(funcName + "(x,dp,rm) " + result);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            Number y, int dp, Number result) {
        printFunctionTest(funcName, test, x, y, dp, null, result);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            Number y, int dp, RoundingMode rm, Number result) {
        printTestAndXAndY(test, x, y);
        printDecimalPlaces(dp);
        if (rm != null) {
            printRoundingMode(rm);
            System.out.println(funcName + "(x,y,dp) " + result);
        } else {
            System.out.println(funcName + "(x,y,dp,rm) " + result);
        }
    }

    public void printDiv(int div) {
        System.out.println("div " + div);
    }

    public void printCompare(Number c) {
        System.out.println("compare (c)" + c);
    }

    public void printFunctionTest(String funcName, int test, Number c,
            Number x, Number y, int div, int dp, RoundingMode rm,
            boolean result) {
        printTest(test);
        printCompare(c);
        printX(x);
        printY(y);
        printDiv(div);
        printDecimalPlaces(dp);
        printRoundingMode(rm);
        System.out.println(funcName + "(c,x,y,div,dp,rm) " + result);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            Number y, int div, int dp, RoundingMode rm, Number result) {
        printTestAndXAndY(test, x, y);
        printDiv(div);
        printDecimalPlaces(dp);
        printRoundingMode(rm);
        System.out.println(funcName + "(x,y,div,dp,rm) " + result);
    }

    public void printFunctionTest(String funcName, int test, int dp,
            RoundingMode rm, Number result) {
        printTest(test);
        printDecimalPlaces(dp);
        printRoundingMode(rm);
        System.out.println(funcName + "(x,dp,rm,y) " + result);
    }

    public void printFunctionTest(String funcName, int test, Number x,
            long seed, int dp, RoundingMode rm, boolean result) {
        printTestAndX(test, x);
        System.out.println("seed " + seed);
        printDecimalPlaces(dp);
        printRoundingMode(rm);
        System.out.println(funcName + "(x,seed,dp,rm) " + result);
    }

    public void printFunctionTest(String funcName, int test,
            Math_BigInteger bi, Number x, Number y, Number z, Number result) {
        printTest(test);
        System.out.println("bi " + bi);
        printX(x);
        printY(y);
        System.out.println("z " + z);
        System.out.println(funcName + "(bi,x,y,z) " + result);
    }
}
