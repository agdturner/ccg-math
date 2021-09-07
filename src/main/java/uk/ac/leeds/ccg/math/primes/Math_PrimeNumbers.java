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
import uk.ac.leeds.ccg.generic.io.Generic_IO;

/**
 * This class finds all prime numbers up to 2147483646 in about 20 seconds. It
 * is based on code posted on stackexchange:
 * https://codereview.stackexchange.com/questions/10823/yet-another-prime-number-generator/54942#54942
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_PrimeNumbers {

    /**
     * Create a new instance.
     */
    public Math_PrimeNumbers() {
    }

    /**
     * @param args Ignored.
     */
    public static void main(String[] args) {
        try {
            new Math_PrimeNumbers().run();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Creates a load of prime numbers and stores them in the data directory.
     * 
     * @throws IOException If encountered.
     * @throws ClassNotFoundException If encountered.
     */
    public void run() throws IOException, ClassNotFoundException {
        Path p = Paths.get(System.getProperty("user.dir"), "data");
        Files.createDirectories(p);

        int maxSize;
        //maxSize = 2147483646;
        //maxSize = 33554432;
        //maxSize = 67108864;
        maxSize = 268435456;
        Path fBitSet = Paths.get(p.toString(), "PrimesUpTo_" + maxSize + "_BitSet.dat");
        Path fPrimeList = Paths.get(p.toString(), "PrimesUpTo_" + maxSize + "_ArrayList_Integer.dat");
        Path fPrimeIndexMap = Paths.get(p.toString(), "PrimesIndexMapUpTo_" + maxSize + "_HashMap_Integer_Integer.dat");
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
                    System.out.println(n + " is the " 
                            + PrimeIndexMap.get(n).toString() 
                            + "th prime greater than 2.");
                } else {
                    if (n < maxPrime) {
                        System.out.println(n + " is not prime.");
                    } else {
                        System.out.println("Sorry, not calculated if " + n
                                + " is prime, maximum prime found so far is "
                                + maxPrime + "!");
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
