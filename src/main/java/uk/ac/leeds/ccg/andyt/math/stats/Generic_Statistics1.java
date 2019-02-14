/**
 * Copyright 2012 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.math.stats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.logging.Generic_Log;
import uk.ac.leeds.ccg.andyt.math.Generic_BigDecimal;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;

/**
 *
 * @author geoagdt
 */
public class Generic_Statistics {

    public Generic_Statistics() {
    }

    /**
     * Returns the sum of all values in data
     * @param data
     * @return 
     */
    public static BigDecimal getSum(List<BigDecimal> data) {
        BigDecimal result = BigDecimal.ZERO;
        Iterator<BigDecimal> ite = data.iterator();
        while (ite.hasNext()) {
            result = result.add(ite.next());
        }
        return result;
    }

    public static void printStatistics(BigDecimal[] stats) {
        System.out.println("Sum " + stats[0]);
        System.out.println("Mean " + stats[1]);
        System.out.println("Median " + stats[2]);
        System.out.println("q1 " + stats[3]);
        System.out.println("q3 " + stats[4]);
        System.out.println("Mode " + stats[5]);
        System.out.println("Min " + stats[6]);
        System.out.println("Max " + stats[7]);
        System.out.println("Number Of Different Values " + stats[8]);
        System.out.println("Number Of Different Values In Mode " + stats[9]);
        System.out.println("Number Of Same Values In Any Part Of Mode " + stats[10]);
    }

    /**
     * 
     * @param data
     * @param decimalPlaces
     * @param roundingMode
     * @return Object[] result:
     * result[0] = BigDecimal[] obtained from 
     * <code>getSummaryStatistics_1(ArrayList<BigDecimal>,int,int,RoundingMode)</code>
     * result[1] = BigDecimal[] secondOrderStatistics;
     * secondOrderStatistics[0] = moment1 = sum of the (differences from the mean)
     * secondOrderStatistics[1] = moment2 = sum of the (differences from the mean squared)
     * secondOrderStatistics[2] = moment3 = sum of the (differences from the mean cubed)
     * secondOrderStatistics[3] = moment4 = sum of the (differences from the mean squared squared)
     * secondOrderStatistics[4] = variance = (sum of the (differences from the mean))/n
     * @TODO secondOrderStatistics[5] = skewness 
     * @TODO secondOrderStatistics[6] = kurtosis
     */
    public static Object[] getSummaryStatistics_1(
            ArrayList<BigDecimal> data,
            int decimalPlaces,
            RoundingMode roundingMode) {
        Object[] result = new Object[2];
        BigDecimal[] summaryStatistics_0 = getSummaryStatistics_0(
                data, decimalPlaces, roundingMode);
        result[0] = summaryStatistics_0;
        BigDecimal[] secondOrderStatistics = new BigDecimal[6];

        BigDecimal moment1 = BigDecimal.ZERO;
        BigDecimal moment2 = BigDecimal.ZERO;
        BigDecimal moment3 = BigDecimal.ZERO;
        BigDecimal moment4 = BigDecimal.ZERO;

        // Deal with special cases
        int n = data.size();
        BigDecimal n_BigDecimal = BigDecimal.valueOf(n);
        if (n < 2) {
            secondOrderStatistics[0] = BigDecimal.ZERO;
            secondOrderStatistics[1] = BigDecimal.ZERO;
            secondOrderStatistics[2] = BigDecimal.ZERO;
            secondOrderStatistics[3] = BigDecimal.ZERO;
            secondOrderStatistics[4] = BigDecimal.ZERO;
        }
        BigDecimal value;
        BigDecimal meandiff;
        Iterator<BigDecimal> ite = data.iterator();
        while (ite.hasNext()) {
            value = ite.next();
            meandiff = value.subtract(summaryStatistics_0[1]);
            moment1 = moment1.add(meandiff);
            moment2 = moment2.add(meandiff.pow(2));
            moment3 = moment3.add(meandiff.pow(3));
            moment4 = moment4.add(meandiff.pow(4));
        }
        BigDecimal variance = Generic_BigDecimal.divideRoundIfNecessary(
                moment2,
                n_BigDecimal,
                decimalPlaces,
                roundingMode);
        secondOrderStatistics[0] = moment1;
        secondOrderStatistics[1] = moment2;
        secondOrderStatistics[2] = moment3;
        secondOrderStatistics[3] = moment4;
        secondOrderStatistics[4] = variance;
        try {
        secondOrderStatistics[5] = Generic_BigDecimal.power(
                variance,
                BigDecimal.valueOf(0.5d),
                decimalPlaces,
                roundingMode);
        } catch (UnsupportedOperationException e) {
            // A terrible hack!
            secondOrderStatistics[5]  = variance.divide(BigDecimal.valueOf(2));
        }
        result[1] = secondOrderStatistics;
//        System.out.println("Moment 1 " + moment1.toPlainString());
//        System.out.println("Moment 2 " + moment2.toPlainString());
//        System.out.println("Moment 3 " + moment3.toPlainString());
//        System.out.println("Moment 4 " + moment4.toPlainString());
//        System.out.println("Variance " + variance.toPlainString());
//        System.out.println("Standard Devation /n " + secondOrderStatistics[5].toPlainString());
        return result;
    }

    /**
     * There is no universal agreement on calculating quartiles:
     * http://en.wikipedia.org/wiki/Quartile
     * @param data
     * @param decimalPlaces
     * @param roundingMode
     * @return 
     * result[0] = sum;
     * result[1] = mean;
     * result[2] = median;
     * result[3] = q1;
     * result[4] = q3;
     * result[5] = mode;
     * result[6] = min;
     * result[7] = max;
     * result[8] = numberOfDifferentValues;
     * result[9] = numberOfDifferentValuesInMode;
     * result[10] = numberOfSameValuesInAnyPartOfMode;
     */
    public static BigDecimal[] getSummaryStatistics_0(
            ArrayList<BigDecimal> data,
            int decimalPlaces,
            RoundingMode roundingMode) {
        BigDecimal[] result = new BigDecimal[12];
        // Deal with special cases
        int n = data.size();
        BigDecimal value;
        if (n == 0) {
            // sum = mean = median = q1 = q3 = mode = min = max
            //value = data.get(0);
            result[0] = null;
            result[1] = null;
            result[2] = null;
            result[3] = null;
            result[4] = null;
            result[5] = null;
            result[6] = null;
            result[7] = null;
            result[8] = null;
            result[9] = null;
            result[10] = null;
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        if (n == 1) {
            // sum = mean = median = q1 = q3 = mode = min = max
            value = data.get(0);
            result[0] = new BigDecimal(value.toString());
            result[1] = new BigDecimal(value.toString());
            result[2] = new BigDecimal(value.toString());
            result[3] = new BigDecimal(value.toString());
            result[4] = new BigDecimal(value.toString());
            result[5] = new BigDecimal(value.toString());
            result[6] = new BigDecimal(value.toString());
            result[7] = new BigDecimal(value.toString());
            result[8] = BigDecimal.ONE;
            result[9] = BigDecimal.ONE;
            result[10] = BigDecimal.ONE;
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        if (n == 2) {
            // mean = median = q1 = q3 = mode
            value = data.get(0);
            BigDecimal value1 = data.get(1);
            result[0] = value.add(value1);
            if (value.compareTo(value1) == 0) {
                result[1] = new BigDecimal(value.toString());
                result[8] = BigDecimal.ONE;
                result[9] = BigDecimal.ONE;
                result[10] = BigDecimal.ONE;
            } else {
                result[1] = Generic_BigDecimal.divideRoundIfNecessary(
                        result[0],
                        Generic_BigDecimal.TWO,
                        decimalPlaces,
                        roundingMode);
                result[8] = Generic_BigDecimal.TWO;
                result[9] = Generic_BigDecimal.TWO;
                result[10] = Generic_BigDecimal.TWO;
            }
            result[2] = new BigDecimal(result[1].toString());
            result[3] = new BigDecimal(result[1].toString());
            result[4] = new BigDecimal(result[1].toString());
            result[5] = new BigDecimal(value.toString());
            result[6] = value.min(value1);
            result[7] = value.max(value1);
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        ArrayList<BigDecimal> sortedData = new ArrayList<>(data);
        Collections.sort(sortedData);
        if (n == 3) {
            // q1 defined as the average of data.get(0) and data.get(1)
            // q3 defined as the average of data.get(1) and data.get(2)
            value = sortedData.get(0);
            BigDecimal value1 = sortedData.get(1);
            BigDecimal value2 = sortedData.get(2);
            result[0] = value.add(value1).add(value2);
            result[1] = Generic_BigDecimal.divideRoundIfNecessary(
                    result[0],
                    BigInteger.valueOf(3),
                    decimalPlaces,
                    roundingMode);
            result[2] = new BigDecimal(value1.toString());
            result[3] = Generic_BigDecimal.divideRoundIfNecessary(
                    value.add(value1),
                    Generic_BigDecimal.TWO,
                    decimalPlaces,
                    roundingMode);
            result[4] = Generic_BigDecimal.divideRoundIfNecessary(
                    value1.add(value2),
                    Generic_BigDecimal.TWO,
                    decimalPlaces,
                    roundingMode);
            if (value.compareTo(value1) == 0) {
                result[5] = new BigDecimal(value.toString());
                if (value.compareTo(value2) == 0) {
                    result[8] = BigDecimal.ONE;
                    result[9] = BigDecimal.ONE;
                    result[10] = Generic_BigDecimal.TWO;
                } else {
                    result[8] = Generic_BigDecimal.TWO;
                    result[9] = BigDecimal.ONE;
                    result[10] = Generic_BigDecimal.TWO;
                }
            } else {
                if (value1.compareTo(value2) == 0) {
                    result[5] = new BigDecimal(value1.toString());
                    result[8] = Generic_BigDecimal.TWO;
                    result[9] = BigDecimal.ONE;
                    result[10] = Generic_BigDecimal.TWO;
                } else {
                    result[5] = new BigDecimal(result[1].toString());
                    result[8] = BigDecimal.valueOf(3);
                    result[9] = BigDecimal.valueOf(3);
                    result[10] = BigDecimal.ONE;
                }
            }
            result[6] = new BigDecimal(value.toString());
            result[7] = new BigDecimal(value2.toString());
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        TreeMap<BigDecimal, Integer> populationAge_TreeMap = new TreeMap<>();
        result[0] = BigDecimal.ZERO;
        Iterator<BigDecimal> ite = sortedData.iterator();
        int maxCount = 1;
        HashSet<BigDecimal> maxCountValues = new HashSet<>();
        while (ite.hasNext()) {
            value = ite.next();
            if (populationAge_TreeMap.containsKey(value)) {
                int count = populationAge_TreeMap.get(value) + 1;
                populationAge_TreeMap.put(value, count);
                if (count == maxCount) {
                    maxCountValues.add(value);
                } else {
                    if (count > maxCount) {
                        maxCountValues = new HashSet<>();
                        maxCountValues.add(value);
                        maxCount = count;
                    }
                }
            } else {
                populationAge_TreeMap.put(value, 1);
                if (maxCount == 1) {
                    maxCountValues.add(value);
                }
            }
            result[0] = result[0].add(value);
        }
        result[9] = BigDecimal.valueOf(maxCountValues.size());
        result[10] = BigDecimal.valueOf(maxCount);
        int nDifferentValues = populationAge_TreeMap.size();
        if (nDifferentValues == 1) {
            value = sortedData.get(0);
            result[1] = new BigDecimal(value.toString());
            result[2] = new BigDecimal(value.toString());
            result[3] = new BigDecimal(value.toString());
            result[4] = new BigDecimal(value.toString());
            result[5] = new BigDecimal(value.toString());
            result[6] = new BigDecimal(value.toString());
            result[7] = new BigDecimal(value.toString());
            //result[8] = BigDecimal.ONE;
            //result[9] = BigDecimal.ONE;
            //result[10] = BigDecimal.ONE;
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        result[1] = Generic_BigDecimal.divideRoundIfNecessary(
                result[0],
                BigDecimal.valueOf(n),
                decimalPlaces,
                roundingMode);
        result[8] = BigDecimal.valueOf(populationAge_TreeMap.size());
        if (n == 4) {
            // q1 = data.get(1)
            // q3 = data.get(2)
            value = sortedData.get(0);
            BigDecimal value1 = sortedData.get(1);
            BigDecimal value2 = sortedData.get(2);
            BigDecimal value3 = sortedData.get(3);
            result[2] = Generic_BigDecimal.divideRoundIfNecessary(
                    value1.add(value2),
                    Generic_BigDecimal.TWO,
                    decimalPlaces,
                    roundingMode);
            result[3] = new BigDecimal(value1.toString());
            result[4] = new BigDecimal(value2.toString());
            if (maxCount == 1) {
                result[5] = new BigDecimal(result[1].toString());
                //result[8] = BigDecimal.valueOf(n);
                //result[9] = BigDecimal.valueOf(n);
                //result[10] = BigDecimal.ONE;
            } else {
                if (maxCount == 4) {
                    result[5] = new BigDecimal(value.toString());
                    //result[8] = BigDecimal.ONE;
                    //result[9] = BigDecimal.ONE;
                    //result[10] = BigDecimal.valueOf(n);
                } else {
                    if (maxCountValues.size() == 1) {
                        result[5] = new BigDecimal(
                                maxCountValues.iterator().next().toString());
                        //result[8] = BigDecimal.ONE;
                        //result[9] = BigDecimal.ONE;
                        //result[10] = BigDecimal.valueOf(n);
                    } else {
                        BigDecimal modeMean = BigDecimal.ZERO;
                        Iterator<BigDecimal> modeIte = maxCountValues.iterator();
                        while (modeIte.hasNext()) {
                            modeMean = modeMean.add(modeIte.next());
                        }
                        result[5] = Generic_BigDecimal.divideRoundIfNecessary(
                                modeMean,
                                BigDecimal.valueOf(maxCountValues.size()),
                                decimalPlaces,
                                roundingMode);
                        //result[8] = BigDecimal.valueOf(populationAge_TreeMap.size());
                        //result[9] = BigDecimal.valueOf(maxCountValues.size());
                        //result[10] = BigDecimal.valueOf(n);
                    }
                }
            }
            result[6] = new BigDecimal(value.toString());
            result[7] = new BigDecimal(value3.toString());
            //printStatistics(result);
            return result;
            /* result[0] = sum;
             * result[1] = mean;
             * result[2] = median;
             * result[3] = q1;
             * result[4] = q3;
             * result[5] = mode;
             * result[6] = min;
             * result[7] = max;
             * result[8] = numberOfDifferentValues;
             * result[9] = numberOfDifferentValuesInMode;
             * result[10] = numberOfSameValuesInAnyPartOfMode;
             */
        }
        boolean interpolateMedian = false;
        if (n % 2 != 1) {
            interpolateMedian = true;
        }
        int ndiv2 = n / 2;
        boolean interpolateQuartile = false;
        if (interpolateMedian == true) {
            interpolateQuartile = true;
        } else {
            if (n % 4 != 1) {
                interpolateQuartile = true;
            }
        }
        int ndiv4 = n / 4;
        int n3div4 = 3 * n / 4;
        Iterator<Entry<BigDecimal, Integer>> ite2 = populationAge_TreeMap.entrySet().iterator();
        Entry<BigDecimal, Integer> entry;
        int count = 0;
        int valueCount;
        boolean medianInitialised = false;
        boolean medianFinalised = false;
        boolean q1Initialised = false;
        boolean q1Finalised = false;
        boolean q3Initialised = false;
        boolean q3Finalised = false;
        BigDecimal lastValue = null;
        while (ite2.hasNext()) {
            entry = ite2.next();
            value = entry.getKey();
            valueCount = entry.getValue();
            count += valueCount;
            if (!q1Finalised) {
                if (count > ndiv4) {
                    if (interpolateQuartile) {
                        if (q1Initialised) {
                            if (interpolateMedian) {
                                if (interpolateQuartile) {
                                    /*
                                     * The greater the remainder when n is 
                                     * divided by 4 the further q1 is from the 
                                     * median
                                     */
                                    if (n % 4 == 0) {
                                        result[3] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                result[3].multiply(BigDecimal.valueOf(3)).add(value),
                                                //result[3].add(value.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4),
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                    if (n % 4 == 2) {
                                        result[3] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                result[3].multiply(BigDecimal.valueOf(3)).add(value),
                                                //result[3].add(value.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4),
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                    if (n % 4 == 3) {
                                        result[3] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                result[3].add(value),
                                                Generic_BigDecimal.TWO,
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                }
                            } else {
                                result[3] =
                                        Generic_BigDecimal.divideRoundIfNecessary(
                                        result[3].add(value),
                                        Generic_BigDecimal.TWO,
                                        decimalPlaces,
                                        roundingMode);
                            }
                            q1Finalised = true;
                        } else {
                            result[3] = new BigDecimal(value.toString());
                            q1Initialised = true;
                            if (count > ndiv4 + 1) {
                                q1Finalised = true;
                            } else {
                                if (!interpolateQuartile) {
                                    q1Finalised = true;
                                }
                            }
                        }
                    } else {
                        result[3] = new BigDecimal(value.toString());
                        q1Finalised = true;
                    }
                }
            }
            if (!medianFinalised) {
                if (count >= ndiv2) {
                    if (interpolateMedian) {
                        if (medianInitialised) {
                            result[2] = Generic_BigDecimal.divideRoundIfNecessary(
                                    result[2].add(value),
                                    Generic_BigDecimal.TWO,
                                    decimalPlaces,
                                    roundingMode);
                            medianFinalised = true;
                        } else {
                            result[2] = new BigDecimal(value.toString());
                            medianInitialised = true;
                            if (count > ndiv2 + 1) {
                                medianFinalised = true;
                            }
                        }
                    } else {
                        if (count > ndiv2) {
                            result[2] = new BigDecimal(value.toString());
                            medianFinalised = true;
                        }
                    }
                }
            }
            if (!q3Finalised) {
                if (count >= n3div4) {
                    if (interpolateQuartile) {
                        if (q3Initialised) {
                            if (interpolateMedian) {
                                if (interpolateQuartile) {
                                    /*
                                     * The greater the remainder when n is 
                                     * divided by 4 the further q3 is from the 
                                     * median
                                     */
                                    if (n % 4 == 0) {
                                        result[4] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                //result[4].multiply(BigDecimal.valueOf(3)).add(value),
                                                result[4].add(value.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4),
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                    if (n % 4 == 2) {
                                        result[4] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                //result[4].multiply(BigDecimal.valueOf(3)).add(value),
                                                result[4].add(value.multiply(BigDecimal.valueOf(3))),
                                                BigDecimal.valueOf(4),
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                    if (n % 4 == 3) {
                                        result[4] =
                                                Generic_BigDecimal.divideRoundIfNecessary(
                                                result[4].add(value),
                                                Generic_BigDecimal.TWO,
                                                decimalPlaces,
                                                roundingMode);
                                    }
                                }
                            } else {
                                result[4] =
                                        Generic_BigDecimal.divideRoundIfNecessary(
                                        result[4].add(value),
                                        Generic_BigDecimal.TWO,
                                        decimalPlaces,
                                        roundingMode);
                            }
                            q3Finalised = true;
                        } else {
                            result[4] = new BigDecimal(value.toString());
                            q3Initialised = true;
                            if (count > n3div4 + 1) {
                                q3Finalised = true;
                            } else {
                                if (!interpolateQuartile) {
                                    q3Finalised = true;
                                }
                            }
                        }
                    } else {
                        if (count > n3div4) {
                            result[4] = new BigDecimal(value.toString());
                            q3Finalised = true;
                        }
                    }
                }
            }
            //lastCount = count;
            lastValue = value;
        }
        if (maxCountValues.size() == n) {
            // All the values are unique so set mode to be a copy of the mean
            result[5] = new BigDecimal(result[1].toString());
        } else {
            BigDecimal modeMean = BigDecimal.ZERO;
            Iterator<BigDecimal> modeIte = maxCountValues.iterator();
            while (modeIte.hasNext()) {
                modeMean = modeMean.add(modeIte.next());
            }
            result[5] = Generic_BigDecimal.divideRoundIfNecessary(
                    modeMean,
                    BigDecimal.valueOf(maxCountValues.size()),
                    decimalPlaces,
                    roundingMode);
        }
        result[6] = sortedData.get(0);
        result[7] = sortedData.get(sortedData.size() - 1);
        //printStatistics(result);
        return result;
        /* result[0] = sum;
         * result[1] = mean;
         * result[2] = median;
         * result[3] = q1;
         * result[4] = q3;
         * result[5] = mode;
         * result[6] = min;
         * result[7] = max;
         * result[8] = numberOfDifferentValues;
         * result[9] = numberOfDifferentValuesInMode;
         * result[10] = numberOfSameValuesInAnyPartOfMode;
         */
    }

    /**
     * Calculates and returns the sum of squared difference between the values 
     * in map0 and map1 
     * @param map0
     * @param map1
     * @param map0Name Used for logging and can be null
     * @param map1Name Used for logging and can be null
     * @param keyName Used for logging and can be null
     * @return 
     */
    public static Object[] getFirstOrderStatistics0(
            TreeMap<Integer, BigDecimal> map0,
            TreeMap<Integer, BigDecimal> map1,
            String map0Name,
            String map1Name,
            String keyName) {
        log("<getFirstOrderStatistics0>");
        Object[] result = new Object[3];
        BigDecimal map0Value;
        BigDecimal map1Value;
        BigDecimal difference;
        BigDecimal differenceSquared;
        BigDecimal sumDifference = BigDecimal.ZERO;
        BigDecimal sumDifferenceSquared = BigDecimal.ZERO;
        Integer key;
        HashSet<Integer> completeKeySet_HashSet = Generic_Collections.getCompleteKeySet_HashSet(
                map0.keySet(),
                map1.keySet());
        result[0] = completeKeySet_HashSet;
        Iterator<Integer> completeKeySetIterator = completeKeySet_HashSet.iterator();
        Object value;
        log(
                keyName + ", "
                + map0Name + ", "
                + map1Name + ", "
                + "difference, "
                + "difference squared, "
                + "sum difference"
                + "sum difference squared");
        while (completeKeySetIterator.hasNext()) {
            key = completeKeySetIterator.next();
            value = map0.get(key);
            if (value == null) {
                map0Value = BigDecimal.ZERO;
            } else {
                map0Value = (BigDecimal) value;
            }
            value = map1.get(key);
            if (value == null) {
                map1Value = BigDecimal.ZERO;
            } else {
                map1Value = (BigDecimal) value;
            }
            difference = map1Value.subtract(map0Value);
            sumDifference = sumDifference.add(difference);
            differenceSquared = difference.multiply(difference);
            sumDifferenceSquared = sumDifferenceSquared.add(differenceSquared);
            log(
                    key.toString() + ", "
                    + map0Value + ", "
                    + map1Value + ", "
                    + difference + ", "
                    + differenceSquared + ", "
                    + sumDifference + ", "
                    + sumDifferenceSquared);
        }
        result[1] = sumDifference;
        result[2] = sumDifferenceSquared;
        log("</getFirstOrderStatistics0>");
        return result;
    }

    /**
     * Calculates and returns the sum of squared difference between the values 
     * in map0 and map1 
     * @param map0
     * @param map1
     * @param map0Name Used for logging and can be null
     * @param map1Name Used for logging and can be null
     * @param keyName Used for logging and can be null
     * @return 
     */
    public static Object[] getFirstOrderStatistics1(
            TreeMap<Integer, BigDecimal> map0,
            TreeMap<Integer, BigDecimal> map1,
            String map0Name,
            String map1Name,
            String keyName) {
        log("<getFirstOrderStatistics1>");
        Object[] result = new Object[3];
        BigDecimal map0Value;
        BigDecimal map1Value;
        BigDecimal difference;
        BigDecimal differenceSquared;
        BigDecimal sumDifference = BigDecimal.ZERO;
        BigDecimal sumDifferenceSquared = BigDecimal.ZERO;
        Integer key;
        HashSet<Integer> completeKeySet_HashSet = Generic_Collections.getCompleteKeySet_HashSet(
                map0.keySet(),
                map1.keySet());
        result[0] = completeKeySet_HashSet;
        Iterator<Integer> completeKeySetIterator = completeKeySet_HashSet.iterator();
        Object value;
        log(keyName + ", "
                + map0Name + ", "
                + map1Name + ", "
                + "difference, "
                + "difference squared, "
                + "sum difference"
                + "sum difference squared");
        while (completeKeySetIterator.hasNext()) {
            key = completeKeySetIterator.next();
            value = map0.get(key);
            if (value == null) {
                map0Value = BigDecimal.ZERO;
            } else {
                //map0Value = new BigDecimal((BigInteger) value);
                map0Value = (BigDecimal) value;
            }
            value = map1.get(key);
            if (value == null) {
                map1Value = BigDecimal.ZERO;
            } else {
                map1Value = (BigDecimal) value;
            }
            difference = map1Value.subtract(map0Value);
            sumDifference = sumDifference.add(difference);
            differenceSquared = difference.multiply(difference);
            sumDifferenceSquared = sumDifferenceSquared.add(differenceSquared);
            log(key.toString() + ", "
                    + map0Value + ", "
                    + map1Value + ", "
                    + difference + ", "
                    + differenceSquared + ", "
                    + sumDifference + ", "
                    + sumDifferenceSquared);
        }
        result[1] = sumDifference;
        result[2] = sumDifferenceSquared;
        log("</getFirstOrderStatistics1>");
        return result;
    }
    
    private static void log(
            String message) {
        log(Generic_Log.Generic_DefaultLogLevel, message);
    }

    private static void log(
            Level a_Level,
            String message) {
        Logger.getLogger(Generic_Log.NAME).log(a_Level, message);
    }
}
