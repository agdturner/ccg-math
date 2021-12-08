/*
 * Copyright 2019 Andy Turner, University of Leeds.
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
package uk.ac.leeds.ccg.math.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import uk.ac.leeds.ccg.math.arithmetic.Math_Math;
import uk.ac.leeds.ccg.math.number.Math_BigRational;

/**
 * For processing and manipulating collections including Lists, Arrays, Sets and
 * Maps.
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_Collections {

    /**
     * Create a new instance.
     */
    public Math_Collections() {}
    
    /**
     * @param min Min
     * @param w Interval width
     * @param map Map
     * @return CountsLabelsMins
     */
    public static CountsLabelsMins getIntervalCountsLabelsMins(
            Math_BigRational min, Math_BigRational w, 
            TreeMap<?, Math_BigRational> map) {
        CountsLabelsMins r = new CountsLabelsMins();
        for (Math_BigRational v : map.values()) {
            Integer interval;
            if (w.compareTo(Math_BigRational.ZERO) == 0) {
                interval = 0;
            } else {
                interval = getInterval(min, w, v);
            }
            //addToTreeMapIntegerInteger(counts, interval, 1);
            //addToMapInteger(r.counts, interval, 1);
            addToCount(r.counts, interval, 1);
            if (!r.labels.containsKey(interval)) {
                Math_BigRational imin = getIntervalMin(min, w, interval);
                Math_BigRational intervalMax = getIntervalMax(imin, w);
                r.labels.put(interval, "" + imin + " - " + intervalMax);
                r.mins.put(interval, imin);
            }
        }
        return r;
    }

    /**
     * A POJO
     */
    public static class CountsLabelsMins {

        /**
         * Counts.
         */
        public TreeMap<Integer, Integer> counts;

        /**
         * Labels.
         */
        public TreeMap<Integer, String> labels;

        /**
         * Mins.
         */
        public TreeMap<Integer, Number> mins;

        /**
         * Create a new instance.
         */
        public CountsLabelsMins() {
            counts = new TreeMap<>();
            labels = new TreeMap<>();
            mins = new TreeMap<>();
        }
    }

    /**
     * @param min Minimum
     * @param w Interval width
     * @param interval Interval
     * @return {@code min.add(new BigDecimal(interval).multiply(w))}
     */
    public static Math_BigRational getIntervalMin(Math_BigRational min, 
            Math_BigRational w, int interval) {
        return min.add(Math_BigRational.valueOf(interval).multiply(w));
    }

    /**
     * @param min Minimum
     * @param w Interval width
     * @return {@code min.add(w)}
     */
    public static Math_BigRational getIntervalMax(Math_BigRational min,
            Math_BigRational w) {
        return min.add(w);
    }

    /**
     * @param min Minimum
     * @param w Interval width
     * @param v Value
     * @param mc MathContext
     * @return {@code (v.subtract(min)).divide(w, mc).intValue()}
     */
    public static int getInterval(Math_BigRational min, Math_BigRational w, 
            Math_BigRational v) {
        return (v.subtract(min)).divide(w).intValue();
    }

    /**
     * @param <K> A generic key.
     * @param <V> A generic value that extends Comparable<V>.
     * @param m Map
     * @return A list with the first element being the minimum and the second and last being the maximum.
     */
    public static <K, V extends Comparable<V>> ArrayList<V> getMinMax(Map<K, V> m) {
        ArrayList<V> r = new ArrayList<>();
        Iterator<V> ite = m.values().iterator();
        if (ite.hasNext()) {
            V v = ite.next();
            r.add(v);
            r.add(v);
            while (ite.hasNext()) {
                v = ite.next();
                if (v.compareTo(r.get(0)) == -1) {
                    r.set(0, v);
                }
                if (v.compareTo(r.get(1)) == 1) {
                    r.set(1, v);
                }
            }
        }
        return r;
    }
    
    /**
     * Adds to a mapped number. This would be better called addToValue. If m
     * does not already contain the key k then i is mapped to k. Otherwise the
     * value for k is obtained from m and i is added to it using
     * {@link Math_Math#add(java.lang.Number, java.lang.Number)}. This may
     * result in infinite values being added to m or ArithmeticExceptions being
     * thrown all depending on the result of any additions as calculated via
     * {@link Math_Math#add(java.lang.Number, java.lang.Number)}.
     *
     * @param <K> Key
     * @param <V> Number
     * @param m The map that is to be added to.
     * @param k The key which value is added to or initialised.
     * @param v The amount to be added to the map.
     */
    public static <K, V extends Number> void addToCount(Map<K, V> m, K k, V v) {
        if (!m.containsKey(k)) {
            m.put(k, v);
        } else {
            m.put(k, Math_Math.add(m.get(k), v));
        }
    }

    /**
     * Add {@code v} to the value of {@code l} at position {@code p}.
     *
     * @param <N> The Number to add.
     * @param l The list to add to.
     * @param pos The position in the list to add to.
     * @param v The value to add to the existing value in {@code l} at position
     * {@code p}.
     */
    public static <N extends Number> void addToList(List<N> l, int pos, N v) {
        N v0 = l.get(pos);
        l.remove(pos);
        l.add(pos, Math_Math.add(v, v0));
    }

    /**
     * This will add the values in uf to the values in u for any existing keys.
     * Where keys in u do not exist, the the numerical value of these in uf are
     * put in u. This may result in infinite numbers being values in u (where
     * the resulting addition is beyond the bounds of the type of V1). Existing
     * keys in u that are mapped to null values are removed. NaN values may also
     * be added to u if infinite values added are the opposite infinities.
     * ArithmeticExceptions might also be throws if NaN type values are
     * attempted to be added to types that cannot represent NaN.
     *
     * @param <K> The types of key in u and f.
     * @param <V1> The type of Number values in u.
     * @param <V2> The type of Number values in uf.
     * @param u The map to updated by adding to the values from uf.
     * @param uf The map to update u from by adding values.
     */
    public static <K, V1 extends Number, V2 extends Number> void addToCount2(
            Map<K, V1> u, Map<K, V2> uf) {
        if (uf != null) {
            uf.entrySet().forEach((entry) -> {
                K key = entry.getKey();
                V2 v2 = entry.getValue();
                V1 v1 = u.get(key);
                if (v1 != null) {
                    V1 v = Math_Math.add2(v1, v2);
                    if (v != null) {
                        u.put(key, v);
                    }
                } else {
                    u.remove(key);
                }
            });
        }
    }

    /**
     * Sets the value in map to the max of map.get(key) and value.
     *
     * @param <K> A generic key.
     * @param m Map
     * @param k key
     * @param v value
     */
    public static <K> void setMaxValueInteger(Map<K, Integer> m, K k,
            Integer v) {
        Integer v0 = m.get(k);
        if (v0 != null) {
            int v1 = Math.max(v0, v);
            if (!(v1 == v0)) {
                m.put(k, v1);
            }
        } else {
            m.put(k, v);
        }
    }

    /**
     * Sets the value in map to the min of map.get(key) and value.
     *
     * @param <K> Generic key.
     * @param m Map
     * @param k key
     * @param v value
     */
    public static <K> void setMinValueInteger(Map<K, Integer> m, K k,
            Integer v) {
        Integer v0 = m.get(k);
        if (v0 != null) {
            Integer v1 = Math.min(v0, v);
            if (!(v1 == v0.intValue())) {
                m.put(k, v1);
            }
        } else {
            m.put(k, v);
        }
    }

    /**
     * @param <K> Key.
     * @param m Map
     * @return A copy of m with all keys the same (not duplicated), but with all
     * values duplicated.
     */
    public static <K> TreeMap<K, BigInteger> deepCopyTreeMapBigInteger(
            TreeMap<K, BigInteger> m) {
        TreeMap<K, BigInteger> r = new TreeMap<>();
        Iterator<K> ite = m.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            BigInteger vToCopy = m.get(k);
            BigInteger vCopy = new BigInteger(vToCopy.toString());
            r.put(k, vCopy);
        }
        return r;
    }

    /**
     * @param <K> Key.
     * @param m Map
     * @return A copy of m with all keys the same (not duplicated), but with all
     * values duplicated.
     */
    public static <K> HashMap<K, Integer> deepCopyHashMapInteger(
            HashMap<K, Integer> m) {
        HashMap<K, Integer> r = new HashMap<>();
        Iterator<K> ite = m.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            r.put(k, m.get(k));
        }
        return r;
    }

    /**
     * @param <K> Key.
     * @param m Map
     * @return A copy of m with all keys the same (not duplicated), but with all
     * values duplicated.
     */
    public static <K> TreeMap<K, Integer> deepCopyTreeMapInteger(
            TreeMap<K, Integer> m) {
        TreeMap<K, Integer> r = new TreeMap<>();
        Iterator<K> ite = m.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            r.put(k, m.get(k));
        }
        return r;
    }

    /**
     * @param <K> Key.
     * @param m Map
     * @return A copy of m with all keys the same (not duplicated), but with all
     * values duplicated.
     */
    public static <K> TreeMap<K, BigDecimal> deepCopyTreeMapBigDecimal(
            TreeMap<K, BigDecimal> m) {
        TreeMap<K, BigDecimal> r = new TreeMap<>();
        Iterator<K> ite = m.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            BigDecimal v0 = m.get(k);
            BigDecimal v1 = new BigDecimal(v0.toString());
            r.put(k, v1);
        }
        return r;
    }

    /**
     * @param <K> Key.
     * @param m Map
     * @return A copy of m with all keys the same (not duplicated), but with all
     * values duplicated.
     */
    public static <K> TreeMap<K, Long> deepCopyTreeMapLong(
            TreeMap<K, Long> m) {
        TreeMap<K, Long> r = new TreeMap<>();
        Iterator<K> ite = m.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            Long v0 = m.get(k);
            Long v1 = v0;
            r.put(k, v1);
        }
        return r;
    }

    /**
     * @param <K> Key.
     * @param <V> Type of value.
     * @param mapToAddTo Map that's values may change.
     * @param mapToAdd Map with values to add to mapToAddTo.
     */
    public static <K, V extends Number> void addToCount(Map<K, V> mapToAddTo,
            Map<K, V> mapToAdd) {
        Iterator<K> ite = mapToAdd.keySet().iterator();
        while (ite.hasNext()) {
            K k = ite.next();
            V v = mapToAdd.get(k);
            if (mapToAddTo.containsKey(k)) {
                mapToAddTo.put(k, Math_Math.add(v, mapToAddTo.get(k)));
            } else {
                mapToAddTo.put(k, v);
            }
        }
    }

    /**
     * @param <K0> Key0.
     * @param <K1> Key1.
     * @param <V> Type of value.
     * @param mapToAddTo Map with values that are maps for which the values may
     * change.
     * @param mapToAdd Map with values that are maps that has values that are
     * added to mapToAddTo.
     */
    public static <K0, K1, V extends Number> void addToCount1(
            Map<K0, Map<K1, V>> mapToAddTo,
            Map<K0, Map<K1, V>> mapToAdd) {
        Iterator<K0> ite = mapToAdd.keySet().iterator();
        while (ite.hasNext()) {
            K0 ko = ite.next();
            if (mapToAddTo.containsKey(ko)) {
                addToCount(mapToAddTo.get(ko), mapToAdd.get(ko));
            }
        }
    }

    /**
     * @param <K> A generic key for m.
     * @param m The map to find the maximum of the values in.
     * @param x Equal to the minimum value that would be returned.
     * @return The maximum of the BigDecimal values in m and x, or a copy of x
     * if m is empty.
     */
    public static <K> BigDecimal getMaxValueBigDecimal(Map<K, BigDecimal> m,
            BigDecimal x) {
        BigDecimal r = new BigDecimal(x.toString());
        Iterator<BigDecimal> ite = m.values().iterator();
        while (ite.hasNext()) {
            r = r.max(ite.next());
        }
        return r;
    }

    /**
     * @param <K> A generic key for m.
     * @param m The map to find the minimum of the values in.
     * @param x Equal to the maximum value that would be returned.
     * @return The minimum of the BigDecimal values in m and x, or a copy of x
     * if m is empty.
     */
    public static <K> BigDecimal getMinValueBigDecimal(Map<K, BigDecimal> m,
            BigDecimal x) {
        BigDecimal r = new BigDecimal(x.toString());
        Iterator<BigDecimal> ite = m.values().iterator();
        while (ite.hasNext()) {
            r = r.min(ite.next());
        }
        return r;
    }

    /**
     * @param <K> A generic key for m.
     * @param m The map to find the maximum of the values in.
     * @param x Equal to the minimum value that would be returned.
     * @return The maximum of the BigDecimal values in m and x, or a copy of x
     * if m is empty.
     */
    public static <K> BigInteger getMaxValueBigInteger(Map<K, BigInteger> m,
            BigInteger x) {
        BigInteger r = new BigInteger(x.toString());
        Iterator<BigInteger> ite = m.values().iterator();
        while (ite.hasNext()) {
            r = r.max(ite.next());
        }
        return r;
    }

    /**
     * @param <K> A generic key for m.
     * @param m The map to find the minimum of the values in.
     * @param x Equal to the maximum value that would be returned.
     * @return The minimum of the BigInteger values in m and x, or a copy of x
     * if m is empty.
     */
    public static <K> BigInteger getMinValueBigInteger(TreeMap<K, BigInteger> m,
            BigInteger x) {
        BigInteger r = new BigInteger(x.toString());
        Iterator<BigInteger> ite = m.values().iterator();
        while (ite.hasNext()) {
            r = r.max(ite.next());
        }
        return r;
    }

    /**
     * For getting the maximum in a collection.
     *
     * @param <V> Value type.
     * @param c The collection.
     * @return The maximum in {@code c}
     */
    public static <V extends Comparable<V>> V getMax(Collection<V> c) {
        return c.stream().parallel().max(V::compareTo).get();
    }

    /**
     * For getting the minimum in a collection.
     *
     * @param <V> Value type.
     * @param c The collection.
     * @return The minimum in {@code c}
     */
    public static <V extends Comparable<V>> V getMin(Collection<V> c) {
        return c.stream().parallel().min(V::compareTo).get();
    }

    /**
     * A test if b is contained in c.
     *
     * @param c The collection tested.
     * @param b The value sought.
     * @return True iff b is in c.
     */
    public static boolean containsValue(Collection<BigDecimal> c, BigDecimal b) {
        return c.stream().parallel().anyMatch(v -> v.equals(b));
    }

    /**
     * Calculates and returns the sum of the sizes of all the sets in {@code m}
     * as an int.
     *
     * @param <K> Keys
     * @param <T> Types
     * @param m Map
     * @return The sum of the sizes of all the sets in {@code m}.
     */
    public static <K, T> int getCountInt(Map<K, Set<T>> m) {
        int r = 0;
        Iterator<Set<T>> ite = m.values().iterator();
        while (ite.hasNext()) {
            r += ite.next().size();
        }
        return r;
    }
}
