/*
 * Copyright 2019 Centre for Computational Geography, University of Leeds.
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
package uk.ac.leeds.ccg.math.primes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import uk.ac.leeds.ccg.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.generic.core.Generic_Strings;
import uk.ac.leeds.ccg.generic.io.Generic_Defaults;
import uk.ac.leeds.ccg.generic.io.Generic_Files;
import uk.ac.leeds.ccg.generic.io.Generic_IO;
import uk.ac.leeds.ccg.generic.io.Generic_Path;
import uk.ac.leeds.ccg.math.core.Math_Environment;
import uk.ac.leeds.ccg.math.core.Math_Object;
import uk.ac.leeds.ccg.math.io.Math_Files;

/**
 * This class finds all prime numbers up to 2147483646 in about 20 seconds. It
 * is based on code posted on stackexchange:
 * https://codereview.stackexchange.com/questions/10823/yet-another-prime-number-generator/54942#54942
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_PrimeNumbers extends Math_Object {

    Generic_Defaults defaults;
    
    public Math_PrimeNumbers(Math_Environment e, Generic_Defaults defaults) {
        super(e);
        this.defaults = defaults;
    }
    
    /**
     * @param args Ignored.
     */
    public static void main(String[] args) {
        try {
            Generic_Defaults defaults = new Generic_Defaults(
                    Paths.get(System.getProperty("user.home"), 
                            Generic_Strings.s_data,
                            "agdt-java=generic-math"));
            Math_Environment env = new Math_Environment(new Generic_Environment(
                    new Generic_Files(defaults)));
            Math_PrimeNumbers p = new Math_PrimeNumbers(env, defaults);
            p.run();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public void run() throws IOException, ClassNotFoundException {
        
        /**
         * Prime numbers are incredibly useful for IDs when you want to be able
         * to trace something when IDs are combined. So for example for
         * inheritance each new individual can be assigned their own unique
         * prime number as an ID. They can then also have an inherited ID which
         * is the multiplication of their parents inherited IDs and their
         * parents unique prime number ID. After a number of generating it may
         * then be possible to quickly see how related any two children are
         * based on the factorisation of these IDs. There may be a completely
         * different and better way to do this!
         *
         * Consider the application of Godel numbering to keeping track of
         * research publications and who working in what organisations has
         * played a part in the evolution of science:
         * https://en.wikipedia.org/wiki/G%C3%B6del_numbering
         *
         * Let's reserve the first 1000 prime numbers in case we later think of
         * a special use for them. The 1st prime is 2, the 1000th prime is 7933.
         * Let's reserve the next 999000 prime numbers for roles. The 1001st
         * prime is 7937, the 100000th prime is 1299743. What are roles? Well,
         * I'm thinking things like "author", "data contributor", "reviewer".
         * That might seem like a lot of roles, but we might want to get fine
         * grained with them, there might be lots of different types of author
         * contributions and indeed lots of different types of reviewer. Let's
         * reserve the next 9900000 prime numbers for organisation IDs. The
         * 100001st prime is 1299763, the 10000000th prime is 179424697. Let us
         * assume that all individual people are either working independently or
         * for an organisation. Let us say that a number of these work together
         * to produce a research output which is given an ID. That research
         * output can also be given another number which is the product of all
         * the inputs that have gone into it. That number is kind of like the
         * Godel number of the work. For any two works, it should be possible to
         * find out if they have something in common by factorising the Godel
         * numbers. The more factors they share, the more common they are. How
         * common different factor are can be summarised and analysed.
         *
         */
        Math_Files files = new Math_Files(defaults);
        Generic_Path dir = files.getGeneratedDir();
        Files.createDirectories(dir.getPath());

        int maxSize;
        //maxSize = 2147483646;
        //maxSize = 33554432;
        //maxSize = 67108864;
        maxSize = 268435456;
        Path fBitSet = Paths.get(dir.toString(), "PrimesUpTo_" + maxSize + "_BitSet.dat");
        Path fPrimeList = Paths.get(dir.toString(), "PrimesUpTo_" + maxSize + "_ArrayList_Integer.dat");
        Path fPrimeIndexMap = Paths.get(dir.toString(), "PrimesIndexMapUpTo_" + maxSize + "_HashMap_Integer_Integer.dat");
        BitSet numbList;
        ArrayList<Integer> PrimeList;
        HashMap<Integer, Integer> PrimeIndexMap;
        int maxPrime;
        String name;
        int million = 1000000;

        if (!Files.exists(fBitSet)) {
            PrimeList = new ArrayList<>();
            PrimeIndexMap = new HashMap<>();

            int maxNumber;
            int maxSearch;
            int primeCount;
            long startTime = System.currentTimeMillis();
            maxNumber = maxSize + 1;
            maxSearch = (int) java.lang.Math.sqrt(maxNumber);
            primeCount = 1;  //Start the count at 1 because 2 is prime and we'll start at 3
            maxPrime = -1;
            //use a BitSet array to maximize how many primes can be found
            numbList = new BitSet(maxNumber);

            numbList.set(0, maxNumber - 1, true);  //set all bits to true

            numbList.clear(0);
            numbList.clear(1);

            //clear the even numbers (except 2, it's prime)
            for (long k = 4; k <= maxSize; k += 2) {
                numbList.clear((int) k);
            }

            // sieve out the non-primes
            for (int k = 3; k < maxSearch; k += 2) {
                if (numbList.get(k)) {
                    sieveTheRest(k, numbList, maxSize);
                }
            }

            int i = 0;

            //Count the primes
            for (int k = 3; k <= maxSize; k += 2) {
                if (numbList.get(k)) {
                    maxPrime = k;
                    PrimeList.add(k);
                    PrimeIndexMap.put(k, i);
                    i++;
                    primeCount += 1;
                    if (primeCount % million == 0) {
                        System.out.format("the " + ((primeCount / million < 100) ? " " : "")
                                + ((primeCount / million < 10) ? " " : "")
                                + primeCount / million + " millionth prime is: %,11d%n", maxPrime);
                    }
                }
            }
            Generic_IO.writeObject(PrimeList, fPrimeList);
            Generic_IO.writeObject(PrimeIndexMap, fPrimeIndexMap);
            Generic_IO.writeObject(numbList, fBitSet);

            System.out.format("array size         : %,11d%n", maxNumber);
            //      System.out.format("prime count        : %,11d%n", primeCount);
            System.out.format("prime count        : %,11d%n", numbList.cardinality());
            System.out.format("largest prime found: %,11d%n", maxPrime);
            System.out.format("max factor         : %,11d%n \n", maxSearch);

            long stopTime = System.currentTimeMillis();
            System.out.println("That took " + (stopTime - startTime)
                    / 1000.0 + " seconds");
        } else {
            PrimeList = (ArrayList<Integer>) Generic_IO.readObject(fPrimeList);
            PrimeIndexMap = (HashMap<Integer, Integer>) Generic_IO.readObject(
                    fPrimeIndexMap);
            numbList = (BitSet) Generic_IO.readObject(fBitSet);
            maxPrime = PrimeList.get(PrimeList.size() - 1);
        }
        while (getQuit().compareTo("-1") != 0) {
            name = getTheNthPrime();
            while (name.compareTo("-1") != 0) {
                int n = Integer.parseInt(name);
                if (n < numbList.cardinality()) {
                    System.out.println("The " + n + "th prime is "
                            + PrimeList.get(n));
                } else {
                    System.out.println("Sorry, not calculated the " + n + "th "
                            + "prime yet, only calculated the first "
                            + (numbList.cardinality() - 1) + " primes!");
                }
                name = getTheNthPrime();
            }

            name = getTestPrime();
            while (name.compareTo("-1") != 0) {
                int n = Integer.parseInt(name);
                if (PrimeIndexMap.containsKey(n)) {
                    System.out.println(n + " is the " + PrimeIndexMap.get(n).toString() + "th prime greater than 2.");
                } else {
                    if (n < maxPrime) {
                        System.out.println(n + " is not prime.");
                    } else {
                        System.out.println("Sorry, not calculated if " + n + " is prime yet, maximum prime found so far is " + maxPrime + "!");
                    }
                }
                name = getTestPrime();
            }
        }

    }

    static String getTheNthPrime() {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String bigNumber = "";
        System.out.print("What is the index of the prime number you want to get? (-1 to continue): ");
        try {
            bigNumber = dataIn.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return bigNumber;
    }

    static String getTestPrime() {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String bigNumber = "";
        System.out.print("What number do you want to test to see if it is prime? (-1 to continue): ");
        try {
            bigNumber = dataIn.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return bigNumber;
    }

    static String getQuit() {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String bigNumber = "";
        System.out.print("Quit? (Enter -1 to quit, anything else to continue): ");
        try {
            bigNumber = dataIn.readLine();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return bigNumber;
    }

    /**
     * @param myPrime The latest prime to be found.
     * @param theBitSet The BitSet holding the prime flags
     * @param maxSize The largest index in the BitSet
     */
    static void sieveTheRest(int myPrime, BitSet theBitSet, int maxSize) {
        for (long k = myPrime * myPrime; k <= maxSize; k += 2 * myPrime) {
            theBitSet.clear((int) k);
        }
    }
}
