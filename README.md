# [agdt-java-math](https://github.com/agdturner/agdt-java-math)

## Description
A Java library aiming for [arbitrary precision arithmetic](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic) using the latest [openJDK](https://openjdk.java.net/), the complimentary [BigMath](https://github.com/eobermuhlner/big-math) library, and [exp4J](https://github.com/fasseg/exp4j) for [symbolic computation](https://en.wikipedia.org/wiki/Symbolic_computation).

The library provides BigInteger and BigDecimal arithmetic that goes beyond what has been available in the latest openJDK. Users can expect to obtain results accurate to a specified scale (given a RoundingMode). It also provides:
- functionality for processing complex numbers where the real and imaginary parts are stored in part as BigDecimals or BigRationals;
- a package uk.ac.leeds.ccg.matrices with a class for processing matrices;
- a package uk.ac.leeds.ccg.primes with a class for prime numbers.

As the [openJDK](https://openjdk.java.net/) evolves and the BigInteger and BigDecimal classes are developed, some of the functionality provided by this library may become redundant.

The [BigMath](https://github.com/eobermuhlner/big-math) library has much similar functionality, but it is slightly different in general in that it focusses on users specifying a precision (rather than a scale), so whereas BigMath might provide an answer to a specified number of significant digits, this library aims to provide a result accurate to the nearsest x decimal places or x units (tens, hundreds, thousands etc). BigMath has an extremely useful BigRational class that allows for a wide range and very detailed rational number storage. I only discovered BigMath in the year 2020 as I began developing [agdt-java-vector3d](https://github.com/agdturner/agdt-java-vector3d) a library geared for 3 dimensional spatial geometry. There is much that can be done to compare implementations and probably rationalise and optimise and perhaps even combine the code bases.

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

## Contributions
- Please report issues.
- Contributions welcome.

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements
- The [University of Leeds](http://www.leeds.ac.uk) and succession of externally funded research grants have supported the development of this library.
- Thank you Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library.

## Version history
- Early versions of this code were bundled together with lots of other code. A first separation of code produced the generic library upon which this depends, and then this library was created. Over time documentation has improved, more unit tests have been added and some undocumented issues have been resolved. Upto version 1.7.3 there is no descitption of what has been changed between versions, but going forward an attempt will be made to provide a summary of additions, deprecations, deletions and other changes for each version release on Maven Central.
