package ch.obermuhlner.math.big.example.internal;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.obermuhlner.math.big.example.StopWatch;

import java.math.MathContext;
import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static ch.obermuhlner.math.big.BigDecimalMath.*;

public class RootExperiments {
    public static BigDecimal rootUsingLogPow(BigDecimal x, BigDecimal n, MathContext mathContext) {
        switch (x.signum()) {
            case 0:
                return ZERO;
            case -1:
                throw new ArithmeticException("Illegal root(x) for x < 0: x = " + x);
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        BigDecimal result = exp(BigDecimalMath.log(x, mc).divide(n, mc), mc);

        return round(result, mathContext);
    }

    public static BigDecimal rootUsingPowReciprocal(BigDecimal x, BigDecimal n, MathContext mathContext) {
        switch (x.signum()) {
            case 0:
                return ZERO;
            case -1:
                throw new ArithmeticException("Illegal root(x) for x < 0: x = " + x);
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        return pow(x, BigDecimal.ONE.divide(n, mc), mathContext);
    }

    public static void main(String[] args) {
        //MathContext mathContext = new MathContext(10);
        MathContext mathContext = new MathContext(100);

//        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("6"), mathContext);
//        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("9"), mathContext);
//        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("7"), mathContext);
        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("3"), mathContext);
        
        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("1000"), mathContext);
        measureRoot(toBigDecimal("8904831940328.25"), toBigDecimal("0.0001"), mathContext);
        measureRoot(toBigDecimal("12345.6"), toBigDecimal("1000"), mathContext);
        measureRoot(toBigDecimal("12345.6"), toBigDecimal("0.01"), mathContext);
        measureRoot(toBigDecimal("0.0000123"), toBigDecimal("1000"), mathContext);
        measureRoot(toBigDecimal("0.0000123"), toBigDecimal("0.01"), mathContext);
    }

    public static void measureRoot(BigDecimal x, BigDecimal n, MathContext mathContext) {
        
        System.out.println("x=" + x.toString());
        System.out.println("n=" + n.toString());
        
        StopWatch stopWatch = new StopWatch();

        System.out.println(rootUsingLogPow(x, n, mathContext));
        System.out.println("rootUsingLogPow in " + stopWatch);
        stopWatch.start();

        System.out.println(rootUsingPowReciprocal(x, n, mathContext));
        System.out.println("rootUsingPowReciprocal in " + stopWatch);
        stopWatch.start();

        System.out.println(BigDecimalMath.root(x, n, mathContext));
        System.out.println("rootUsingNewtonRaphson in " + stopWatch);
        System.out.println();
    }

}
