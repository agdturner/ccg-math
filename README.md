# [agdt-java-math](https://github.com/agdturner/agdt-java-math)

## Description
A Java library aiming for [arbitrary precision arithmetic](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic) using the latest [openJDK](https://openjdk.java.net/), the complimentary [BigMath](https://github.com/eobermuhlner/big-math) library, and some [symbolic computation](https://en.wikipedia.org/wiki/Symbolic_computation).

The library provides additional [BigInteger](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigInteger.html), [BigDecimal](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html) and [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) arithmetic. For many functions returning BigDecimal numbers, the user specifies a minimum precision scale for the precision of the result where: a positive value gives the number of decimal places the result must be accurate to; and, a negative value gives a scale of unit to the left of the decimal point that the number must be accurate too. More accurate result can be provided and the user may use rounding, but the result is accurate at least to the minimum precision scale specified. Sometimes the result is rounded using [RoundingMode.HALF_UP](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/RoundingMode.html#HALF_UP). The library provides:
- [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for [surds](https://en.wikipedia.org/wiki/Nth_root) of square roots of BigRational numbers;
- [Math_Matrix_BR](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/matrices/Math_Matrix_BR.java) for processing matrices holding BigRational numbers;
- Math_PrimeNumbers(https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/primes/Math_PrimeNumbers.java) for processing and identifying prime numbers.

As the [openJDK](https://openjdk.java.net/) evolves, some of the functionality provided by this library may become folded in or redundant. To make this more likely the code is modularised and an effort is being made to better documented, test it and go through the process to [contribute it](https://openjdk.java.net/contribute/). 

The [BigMath](https://github.com/eobermuhlner/big-math) library has much similar functionality, but it is slightly different in general in that it focusses on users specifying a precision (rather than a scale), so whereas BigMath might provide an answer to a specified number of significant digits, this library aims to provide a result accurate to a specific scale (e.g 2 decimal places, e.g. the nearest thousand). BigMath has an extremely useful BigRational class that allows for a wide range and very detailed rational number storage. I only discovered BigMath in the year 2020 as I began developing [agdt-java-vector3d](https://github.com/agdturner/agdt-java-vector3d) a library geared for 3 dimensional spatial geometry. There is much that can be done to compare implementations and probably rationalise and optimise and perhaps even combine the code bases.

Sometimes it is best to use [symbolic computation] to avoid imprecision and error propagation. For instance consider a calculation where two terms which are both equal to [sqrt(2)](https://en.wikipedia.org/wiki/Square_root_of_2) are multiplied together - this can be done completely accurately by realising the answer is 2. The basic idea is to store numbers and operations as symbols and do calculations by first simplifying expressions. Many mathematical constants that are irrational numbers become important in many geometric, physical and engineering calculations (see [Stan's Extra Byte Mathematical Constants and Sequences](http://dx.doi.org/10.3247/SL2Math08.001)), and with logs and roots amongst them, it will be good to figure out how to utilise [exp4J](https://github.com/fasseg/exp4j) or similar...

## Latest Versions
Developed and tested on Java 15.
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-math</artifactId>
    <version>1.7</version>
</dependency>
```
A JAR is available:
https://repo1.maven.org/maven2/io/github/agdturner/agdt-java-math/1.7/agdt-java-math-1.7.jar

[//]: # (Move to version history section if/when a new version and summary are added)
New to 1.7:
- uk.ac.leeds.ccg.matrices.Math_Matrix_BR
  For matrices storing BigRational numbers.

## Dependencies
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-generic -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-generic</artifactId>
    <version>1.7.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/ch.obermuhlner/big-math -->
<dependency>
    <groupId>ch.obermuhlner</groupId>
    <artifactId>big-math</artifactId>
    <version>2.3.0</version>
</dependency>
```

## Development Roadmap
### Version 1.8
- Improve unit tests and review code.

## Plans
- Compare and contrast more with Eric's [BigMath](https://github.com/eobermuhlner/big-math) library.
- Compare Math_BigDecimal.sqrt(BigDecimal, int, RoundingMode) with BigDecimal.sqrt(MathContext) and BigDecimalMath.sqrt()
- Begin some experiments with [exp4J](https://github.com/fasseg/exp4j) 
- Develop some functionality for processing complex numbers where the real and imaginary parts are stored as BigRational or [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) based on [Math_Complex_double](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_Complex_double.java);

## Version history
- Early versions of this code were bundled together with lots of other code. A first separation of code produced [the generic library upon which this depends](https://github.com/agdturner/agdt-java-generic), and this library both of which were improved significantly via a process of self review refactoring, development of more compreensive unit tests, documention improvements and better use of version control systems. Upto version 1.7.3 there is not any detailed description of what has been changed between versions, but going forward an attempt is being made to provide a summary of additions, deprecations, deletions and other changes for each version released on Maven Central.

## Contributions
- Welcome.

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements
- The [University of Leeds](http://www.leeds.ac.uk) and succession of externally funded research grants have supported the development of this library.
- Thank you to all Java developers that contribute to the [openJDK](https://openjdk.java.net/) and develop other useful libraries out in the wild that have inspired me. In particular, thank you Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library.
- Thank you to all those that help maintain and contribute usefully to:
-- [Wikimedia](https://www.wikimedia.org/) projects, in particular [en.wikipedia](https://en.wikipedia.org/wiki/Main_Page)
-- [StackExchange](https://stackexchange.com), in particular [StackOverflow](https://stackoverflow.com/) and (http://math.stackexchange.com/).
-- The information on the Web that has helped me develop this library. I have made an effort to keep links to the content that I have used in the documentation.
- If you feel you are owed an acknowledgement, please let me know.
