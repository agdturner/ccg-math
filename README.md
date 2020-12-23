# [agdt-java-math](https://github.com/agdturner/agdt-java-math)

## Description
A Java library for numerical data processing providing BigDecimal and BigInteger arithmetic functionality that allows the user to specify the accuracy and precision of results. It also provides:
- functionality for processing complex numbers where the real and imaginary parts are stored in part as BigDecimals or BigRationals;
- a package uk.ac.leeds.ccg.matrices with a class for processing matrices;
- a package uk.ac.leeds.ccg.primes with a class for prime numbers.

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
Compare and contrast more with Eric's [BigMath](https://github.com/eobermuhlner/big-math) library.

## Contributions
- Please raise issues in the usual way.
- Please email Andy about contributing to development (email address in the POM).

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements
- The [University of Leeds](http://www.leeds.ac.uk) and succession of externally funded research grants have supported the development of this library.
- Thank you Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library :-)

## Version history
- Early versions of this code were bundled together with lots of other code. A first separation of code produced the generic library upon which this depends, and then this library was created. Over time documentation has improved, more unit tests have been added and some undocumented issues have been resolved. Upto version 1.7.3 there is no descitption of what has been changed between versions, but going forward an attempt will be made to provide a summary of additions, deprecations, deletions and other changes for each version release on Maven Central.
