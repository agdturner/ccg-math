module uk.ac.leeds.ccg.math {
    requires transitive java.logging;
    requires java.desktop;
    requires ch.obermuhlner.math.big;
    requires transitive uk.ac.leeds.ccg.generic;
    //requires uk.ac.leeds.ccg.generic.core;
    //requires uk.ac.leeds.ccg.generic.util;
    //requires uk.ac.leeds.ccg.generic.io;
    exports uk.ac.leeds.ccg.math;
    exports uk.ac.leeds.ccg.math.core;
    exports uk.ac.leeds.ccg.math.io;
    exports uk.ac.leeds.ccg.math.primes;
}