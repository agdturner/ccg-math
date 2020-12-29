# [agdt-java-math](https://github.com/agdturner/agdt-java-math)

## Description
A [modularised](https://en.wikipedia.org/wiki/Java_Platform_Module_System) Java library dependent on and complimenting [BigMath](https://github.com/eobermuhlner/big-math) with functionality for:
- [Fixed point](https://en.wikipedia.org/wiki/Fixed-point_arithmetic) and [arbitrary precision](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic) arithmetic.
- Processing [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics)).
- Getting a specific [prime number](https://en.wikipedia.org/wiki/Prime_number) in the ordered list and identifying if a natural number is prime.
- Generating [pseudorandom](https://en.wikipedia.org/wiki/Pseudorandomness) [BigDecimal](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html) numbers.

[Fixed point arithmetic](https://en.wikipedia.org/wiki/Fixed-point_arithmetic) methods return [BigDecimal](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html) numbers. The user specifies a minimum precision scale where: a positive value gives the number of decimal places the result must be accurate to; and, a negative value gives a scale of unit to the left of the decimal point that the number must be accurate to. More precise and accurate results may be returned, but they must be at least accurate to the minimum precision scale specified. If a result cannot be precisely and accurately represented by a BigDecimal it is rounded. [BigMath](https://github.com/eobermuhlner/big-math) arithmetic is slightly different in that it focusses on users specifying a precision (rather than a scale), so BigMath is geared for computing calculations accurate to a specified number of [significant figures](https://en.wikipedia.org/wiki/Significant_figures), this library aims to provide a result accurate to a minimum scale precision (e.g to 2 decimal places).

[BigMath](https://github.com/eobermuhlner/big-math) has an extremely useful [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) class for representing a very wide range of [rational numbers](https://en.wikipedia.org/wiki/Rational_number). [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) is for representing the square roots of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers, some of which (including the square roots of prime numbers) are [irrational](https://en.wikipedia.org/wiki/Irrational_number). Math_BigRationalSqrt is useful as sometimes calculations can be simplified without a square root needing to be computed, consider for example calculating the area of a square that has a side with length equal to the square root of 2. 

## Latest versions
Developed and tested on [Java Development Kit, version 15](https://openjdk.java.net/projects/jdk/15/).
### Stable
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-math</artifactId>
    <version>1.7</version>
</dependency>
```
[JAR](https://repo1.maven.org/maven2/io/github/agdturner/agdt-java-math/1.7/agdt-java-math-1.7.jar)

### Unstable development version
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-math</artifactId>
    <version>1.8-SNAPSHOT</version>
</dependency>
```
[//]: # (Move to version history section if/when a new version and summary are added)
New to 1.8-SNAPSHOT:
- [Math_Matrix_BR](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/matrices/Math_Matrix_BR.java) for processing [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics)) of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers;
- [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for representing the square roots of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers, some of which (including the square roots of prime numbers) are [irrational](https://en.wikipedia.org/wiki/Irrational_number).

## Development plans/ideas
- Do some computational comparison, e.g. compare Math_BigDecimal.sqrt(BigDecimal, int, RoundingMode), BigDecimal.sqrt(MathContext) and BigDecimalMath.sqrt().
- Generalise [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for [nth roots](https://en.wikipedia.org/wiki/Nth_root).
- Develop functionality for processing complex numbers where the real and imaginary parts are stored as [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) or [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java). [Math_Complex_double](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_Complex_double.java) is a complex number class for numbers with real and imaginary components stored as [double](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html) values.
- For exponentiation and logarithms, review the implementation of [logarithmic numbers](https://en.wikipedia.org/wiki/Logarithmic_number_system).
- Generally improve the coverage of unit tests.
- Consider developing more functionality for [symbolic computation](https://en.wikipedia.org/wiki/Symbolic_computation).
- Develop the library in an [agile](https://en.wikipedia.org/wiki/Agile_software_development) way by engaging in more code review activity.
- [Contribute](https://openjdk.java.net/contribute/) to the development of the openJDK.

## Development history
- Early versions of this code were bundled together with lots of other code developed for academic research projects. A separation of code produced [agdt-java-generic](https://github.com/agdturner/agdt-java-generic) a generic higher level library upon which this depends. From version 1.7 an attempt is being made to provide a summary of changes for each version released on Maven Central.

## Contributions
- Welcome.

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements and thanks
- The [University of Leeds](http://www.leeds.ac.uk) and externally funded research grants have supported the development of this library.
- Thanks you [openJDK](https://openjdk.java.net/) contributors and all involved in creating the platform.
- Thank you Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library.
- Thank you developers of other useful Java libraries that have helped inspire me.
- Thank you to the creators, maintainers and contributors for the utility of:
-- [Wikimedia](https://www.wikimedia.org/) projects, in particular the [English language Wikipedia](https://en.wikipedia.org/wiki/Main_Page)
-- [StackExchange](https://stackexchange.com), in particular [StackOverflow](https://stackoverflow.com/) and (http://math.stackexchange.com/).
- Information that has helped me develop this library is cited in the source code.
- Of course, I would not have been able to produce this library without my education. Thanks to all my teachers and the learning resources I have had access to and learned from. 
