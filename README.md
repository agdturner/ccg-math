# [agdt-java-math](https://github.com/agdturner/agdt-java-math)

## Description
A [modularised](https://en.wikipedia.org/wiki/Java_Platform_Module_System) Java library dependent on and complimenting [BigMath](https://github.com/eobermuhlner/big-math) with functionality for:
- [Fixed point](https://en.wikipedia.org/wiki/Fixed-point_arithmetic) and [arbitrary precision](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic) arithmetic.
- Processing [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics)).
- Getting a specific [prime number](https://en.wikipedia.org/wiki/Prime_number) in the ordered list and identifying if a natural number is prime.
- Generating [pseudorandom](https://en.wikipedia.org/wiki/Pseudorandomness) [BigDecimal](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html) numbers.

[Fixed point arithmetic](https://en.wikipedia.org/wiki/Fixed-point_arithmetic) methods return [BigDecimal](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigDecimal.html) numbers. The user specifies a minimum precision scale where: a positive value gives the number of decimal places the result must be accurate to; and, a negative value gives a scale of unit to the left of the decimal point that the number must be accurate to. More precise and accurate results may be returned, but they must be at least accurate to the minimum precision scale specified. If a result cannot be precisely and accurately represented by a BigDecimal it is rounded. [BigMath](https://github.com/eobermuhlner/big-math) arithmetic is slightly different in that it focusses on users specifying a precision (rather than a scale), so BigMath is geared for computing calculations accurate to a specified number of [significant figures](https://en.wikipedia.org/wiki/Significant_figures), this library aims to provide a result accurate to a minimum scale precision (e.g to 2 decimal places).

[BigMath](https://github.com/eobermuhlner/big-math) has an extremely useful [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) class for representing a very wide range of [rational numbers](https://en.wikipedia.org/wiki/Rational_number). [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) is for representing the square roots of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers, some of which (including the square roots of prime numbers) are [irrational](https://en.wikipedia.org/wiki/Irrational_number). Math_BigRationalSqrt is useful as sometimes calculations can be simplified without a square root needing to be computed, consider for example calculating the area of a square that has a side with length equal to the square root of 2. 

## Latest versioned release
Developed and tested on [Java Development Kit, version 15](https://openjdk.java.net/projects/jdk/15/).
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-math</artifactId>
    <version>1.14</version>
</dependency>
```
[JAR](https://repo1.maven.org/maven2/io/github/agdturner/agdt-java-math/1.14/agdt-java-math-1.14.jar)

## Development plans/ideas
- Do some computational comparison, e.g. compare Math_BigDecimal.sqrt(BigDecimal, int, RoundingMode), BigDecimal.sqrt(MathContext) and BigDecimalMath.sqrt().
- Generalise [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for [nth roots](https://en.wikipedia.org/wiki/Nth_root).
- Create a [surreal number](https://en.wikipedia.org/wiki/Surreal_number) class (Math_Surreal) for storing [real numbers](https://en.wikipedia.org/wiki/Real_number).
- Create a pseudo random number generator for [BigInteger](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigInteger.html), [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) and real numbers. 
- Develop functionality for processing complex numbers where the real and imaginary parts are stored as [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) or [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java), or Math_Surreal. [Math_Complex_double](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_Complex_double.java) is a complex number class for numbers with real and imaginary components stored as [double](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html) values.
- For exponentiation and logarithms, review the implementation of [logarithmic numbers](https://en.wikipedia.org/wiki/Logarithmic_number_system).
- Generally improve the coverage of unit tests.
- Consider developing more functionality for [symbolic computation](https://en.wikipedia.org/wiki/Symbolic_computation).
- Develop in an [agile](https://en.wikipedia.org/wiki/Agile_software_development) way.
- [Contribute](https://openjdk.java.net/contribute/) to the development of the openJDK.

## Development history
### New in 1.9
- [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for representing the square roots of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers, some of which (including the square roots of prime numbers) are [irrational](https://en.wikipedia.org/wiki/Irrational_number).
### New in 1.8
- [Math_Matrix_BR](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/matrices/Math_Matrix_BR.java) for processing [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics)) of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers;
### Origins
- Early versions of this code were bundled together with lots of other code developed for academic research projects. A separation of code produced [agdt-java-generic](https://github.com/agdturner/agdt-java-generic) a generic higher level library upon which this depends. Since Version 1.7 an attempt is being made to provide a summary of changes for each version released on Maven Central.

## Contributions
- Welcome.

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements and thanks
- The [University of Leeds](http://www.leeds.ac.uk) and externally funded research grants have supported the development of this library.
- Thank you [openJDK](https://openjdk.java.net/) contributors and all involved in creating the platform.
- Thank you Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library.
- Thank you developers and maintainers of other useful Java libraries that provide inspiration.
- Thank you developers and maintainers of [Apache Maven](https://maven.apache.org/), [Apache NetBeans](https://netbeans.apache.org/), and [git](https://git-scm.com/) which I use for developing code.
- Thank you developers and maintainers of [GitHub](http://github.com) for supporting the development of this code and for providing a means of creating a community of users and  developers.
- Thank you developers, maintainers and contributors of relevent content on:
-- [Wikimedia](https://www.wikimedia.org/) projects, in particular the [English language Wikipedia](https://en.wikipedia.org/wiki/Main_Page)
-- [StackExchange](https://stackexchange.com), in particular [StackOverflow](https://stackoverflow.com/) and [Math.StackExchange](http://math.stackexchange.com/).
- Information that has helped me develop this library is cited in the source code.
- Thank you to those that supported me personally and all who have made a positive contribution to society. Let us try to look after each other, look after this world, make space for wildlife, and engineer knowledge :)
