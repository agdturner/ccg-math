# [ccg-math](https://github.com/agdturner/ccg-math)

## Description
A modular Java library dependent on and complimenting [BigMath](https://github.com/eobermuhlner/big-math) with functionality for:
- [arbitrary precision arithmetic](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic);
- [matrix](https://en.wikipedia.org/wiki/Matrix_(mathematics)) calculations;
- handling [prime number](https://en.wikipedia.org/wiki/Prime_number)s
- Generating [pseudorandom](https://en.wikipedia.org/wiki/Pseudorandomness) numbers numbers.

- For many arithmetic calculations, the user specifies an [Order of Magnitude](https://en.wikipedia.org/wiki/Order_of_magnitude) for the precision of results. If a result cannot be precisely and accurately represented, then it is rounded to the given Order of Magnitude. [BigMath](https://github.com/eobermuhlner/big-math) arithmetic is slightly different in that it focusses on users specifying a precision (rather than a scale), so BigMath is geared for computing calculations accurate to a specified number of [significant figures](https://en.wikipedia.org/wiki/Significant_figures).

## Latest versioned release
Developed and tested on [Java Development Kit, version 15](https://openjdk.java.net/projects/jdk/15/).
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/ccg-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>ccg-math</artifactId>
    <version>2.0</version>
</dependency>
```
[JAR](https://repo1.maven.org/maven2/io/github/agdturner/ccg-math/2.0/ccg-math-2.0.jar)

## Example
See [ccg-math-example](https://github.com/agdturner/ccg-math-example) for an example of how to set up and run the library.

## Notes
[Math_BigRational](https://github.com/agdturner/ccg-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/number/Math_BigRational.java) is based on [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java), but has been slightly modified. Mostly this was to make it serializable.
The library is dependent on both [ccg-io](https://github.com/agdturner/ccg-io) and [big-math](https://github.com/eobermuhlner/big-math)

## Development plans/ideas
- Conduct some computational comparisons, e.g. compare Math_BigDecimal.sqrt(BigDecimal, int, RoundingMode), BigDecimal.sqrt(MathContext) and BigDecimalMath.sqrt().
- Generalise [Math_BigRationalSqrt](https://github.com/agdturner/agdt-java-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for [nth roots](https://en.wikipedia.org/wiki/Nth_root).
- Create a pseudo random number generator for [BigInteger](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/math/BigInteger.html). 
- Develop functionality for processing complex numbers where the real and imaginary parts are stored as [Math_BigRational](https://github.com/agdturner/ccg-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/number/Math_BigRational.java).
- Generally improve the coverage of unit tests.
- Consider developing more functionality for [symbolic computation](https://en.wikipedia.org/wiki/Symbolic_computation).

## Development history
### New in 2.0
- The library was repackaged and [Math_BigRational](https://github.com/agdturner/ccg-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/number/Math_BigRational.java) based on [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) was included and used in it's place.
### New in 1.9
- [Math_BigRationalSqrt](https://github.com/agdturner/ccg-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/Math_BigRationalSqrt.java) for representing the square roots of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers, some of which (including the square roots of prime numbers) are [irrational](https://en.wikipedia.org/wiki/Irrational_number).
### New in 1.8
- [Math_Matrix_BR](https://github.com/agdturner/ccg-math/blob/master/src/main/java/uk/ac/leeds/ccg/math/matrices/Math_Matrix_BR.java) for processing [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics)) of [BigRational](https://github.com/eobermuhlner/big-math/blob/master/ch.obermuhlner.math.big/src/main/java/ch/obermuhlner/math/big/BigRational.java) numbers.
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
