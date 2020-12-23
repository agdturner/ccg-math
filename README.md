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

New to 1.7:
- uk.ac.leeds.ccg.matrices.Math_Matrix_BR
  For matrices storing BigRational numbers.

## Dependencies
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-generic -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-generic</artifactId>
    <version>1.7.2</version>
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

## Acknowledgements and feedback
- Thanks to the University of Leeds and numerous research grants for supporting the development of this code.
- If you find this code useful, please let me know and refer to the resources used in the usual ways.
- Thanks to Eric for the [BigMath](https://github.com/eobermuhlner/big-math) library :-)
