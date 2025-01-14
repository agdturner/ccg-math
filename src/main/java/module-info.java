/*
 * Copyright 2020 Andy Turner, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Provides some general mathematics functionality.
 */
module uk.ac.leeds.ccg.math {
    requires transitive java.logging;
    requires transitive java.desktop;
    
    /**
     * The big-math library is mostly used for representing and computing with
     * rational numbers as {@link ch.obermuhlner.math.big.BigRational}. It is
     * also for some functions that work on {@link java.math.BigDecimal}.
     */
    requires transitive ch.obermuhlner.math.big;

    /**
     * The ccg-io library is used for input and output.
     */
    requires transitive uk.ac.leeds.ccg.io;
    
    /**
     * Exports.
     */
    exports uk.ac.leeds.ccg.math.arithmetic;
    exports uk.ac.leeds.ccg.math.core;
    exports uk.ac.leeds.ccg.math.geometry;
    exports uk.ac.leeds.ccg.math.matrices;
    exports uk.ac.leeds.ccg.math.number;
    exports uk.ac.leeds.ccg.math.primes;
    exports uk.ac.leeds.ccg.math.random;
    exports uk.ac.leeds.ccg.math.util;
    
    /**
     * Opens
     */
    opens uk.ac.leeds.ccg.math.arithmetic;
    opens uk.ac.leeds.ccg.math.core;
    opens uk.ac.leeds.ccg.math.geometry;
    opens uk.ac.leeds.ccg.math.matrices;
    opens uk.ac.leeds.ccg.math.number;
    opens uk.ac.leeds.ccg.math.primes;
    opens uk.ac.leeds.ccg.math.random;
    opens uk.ac.leeds.ccg.math.util;
}