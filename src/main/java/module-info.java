module uk.ac.leeds.ccg.math {
    requires transitive java.logging;
    requires java.desktop;
    requires ch.obermuhlner.math.big;
    //requires transitive uk.ac.leeds.ccg.generic;
    requires uk.ac.leeds.ccg.generic;
    exports uk.ac.leeds.ccg.math;
    exports uk.ac.leeds.ccg.math.core;
    exports uk.ac.leeds.ccg.math.io;
    exports uk.ac.leeds.ccg.math.matrices;
    exports uk.ac.leeds.ccg.math.primes;
}