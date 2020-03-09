# agdt-java-math

## Description
A Java library for numerical data processing.

The library depends only on an identically licensed generic library available via (https://github.com/agdturner/agdt-java-generic). It provides BigDecimal and BigInteger arithmetic functionality that allow the user to specify the accuracy and precision of results. It also provides some functionality for processing complex numbers.

## Requirements
Java 11 or higher.

## Dependencies
agdt-java-generic available via https://github.com/agdturner/agdt-java-generic.
To run the Unit Tests there is a dependency on JUnit 4.

## Status, Current Version and platform requirements
Version 1.2 is developed and tested on Java 11 using Maven. It is available from Maven Central via: https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math/1.2

To use with Maven add the following dependencies to pom.xml:
```
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-math -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-math</artifactId>
    <version>1.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.github.agdturner/agdt-java-generic -->
<dependency>
    <groupId>io.github.agdturner</groupId>
    <artifactId>agdt-java-generic</artifactId>
    <version>1.1.0</version>
</dependency>
```
Here is a link to the jar:
https://repo1.maven.org/maven2/io/github/agdturner/agdt-java-math/1.1.0/agdt-java-math-1.1.0.jar

## Development Roadmap
### Version 1.3
Compare and contract with https://github.com/eobermuhlner/big-math and https://github.com/kiprobinson/BigFraction
A SNAPSOT version of this will be made available in due course...

## Dependencies
- Please see the pom.xml or maven central for details.
- Currently there are no third party dependencies except for testing.

## Contributions
- Please raise issues in the usual way.
- Please email Andy about contributing to development (email address in the POM).

## LICENCE
- APACHE LICENSE, VERSION 2.0: https://www.apache.org/licenses/LICENSE-2.0

## Acknowledgements and feedback
- Thanks to the University of Leeds and numerous research grants for supporting the development of this code and the developer over many years.
- If you find this code useful, please let the developer know and refer to the resources used in the usual ways. 
