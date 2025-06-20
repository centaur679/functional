/*
 * Copyright (C) 2021 iofairy, <https://github.com/io-fairy/functional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iofairy.range;

import com.iofairy.annos.Beta;
import com.iofairy.top.G;

import java.io.Serializable;
import java.util.*;

/**
 * Range<br>
 * See: <a href="https://en.wikipedia.org/wiki/Interval_(mathematics)">Interval_(mathematics)</a>
 * <br>
 *
 * <blockquote>
 *
 * <table>
 * <caption>Range Types</caption>
 * <tr><th>Notation        <th>Definition
 * <tr><td>{@code (a, b)}  <td>{@code {x | a < x < b}}
 * <tr><td>{@code [a, b]}  <td>{@code {x | a ≤ x ≤ b}}
 * <tr><td>{@code (a, b]}  <td>{@code {x | a < x ≤ b}}
 * <tr><td>{@code [a, b)}  <td>{@code {x | a ≤ x < b}}
 * <tr><td>{@code (a, +∞)} <td>{@code {x | x > a}}
 * <tr><td>{@code [a, +∞)} <td>{@code {x | x ≥ a}}
 * <tr><td>{@code (-∞, b)} <td>{@code {x | x < b}}
 * <tr><td>{@code (-∞, b]} <td>{@code {x | x ≤ b}}
 * <tr><td>{@code (-∞, +∞)}<td><b>R</b>
 * <tr><td>{@code [a, a]}  <td>{@code {a}}
 * <tr><td>{@code [a, a)}  <td>{@code ∅}
 * <tr><td>{@code (a, a]}  <td>{@code ∅}
 * <tr><td>{@code (a, a)}  <td>{@code ∅}
 * </table>
 *
 * </blockquote>
 *
 * @param <T> Range type
 * @since 0.5.0
 */
@Beta
@SuppressWarnings("rawtypes")
public class Range<T extends Comparable> implements Serializable {
    private static final long serialVersionUID = 9999875683659872L;

    public static final String EMPTY_SET = "∅";
    public static final String INFINITY = "∞";
    public static final String SET_OF_REAL_NUMBERS = "R";

    /**
     * lowerBound and upperBound. It represents <b>-∞</b> if value is null
     */
    public final T lowerBound, upperBound;
    /**
     * start and end
     */
    public final T start, end;
    /**
     * min and max
     */
    public final T min, max;
    /**
     * from and to
     */
    public final T from, to;
    /**
     * earliest and latest
     */
    public final T earliest, latest;
    /**
     * left and right
     */
    public final T left, right;
    /**
     * infimum and supremum
     */
    public final T infimum, supremum;
    /**
     * head and tail
     */
    public final T head, tail;
    /**
     * begin and finish
     */
    public final T begin, finish;
    /**
     * first and last
     */
    public final T first, last;
    /**
     * origin and destination
     */
    public final T origin, destination;
    /**
     * source and target
     */
    public final T source, target;
    /**
     * bottom and top
     */
    public final T bottom, top;
    /**
     * floor and ceiling
     */
    public final T floor, ceiling;


    /**
     * IntervalType
     */
    public final IntervalType intervalType;
    /**
     * <b>EMPTY SET</b> or not
     */
    public final boolean isEmpty;
    /**
     * Contains  <b>INFINITY</b> flag or not <b>({@code lowerBound} is null or {@code upperBound} is null )</b>
     */
    public final boolean hasInfinity;

    /**
     * Constructs a {@code Range} <br>
     *
     * <b>NOTE:</b> <br>
     * {@code [a,a] = {a} (a≤x≤a)}<br>
     * {@code [a,a) = ϕ  (a≤x<a)}<br>
     * {@code (a,a] = ϕ  (a<x≤a)}<br>
     * {@code (a,a) = ϕ  (a<x<a)}
     *
     * @param lowerBound   lower bound
     * @param upperBound   upper bound
     * @param intervalType interval type
     */
    public Range(T lowerBound, T upperBound, final IntervalType intervalType) {
        Objects.requireNonNull(intervalType, "Param `intervalType` must be non-null! ");

        this.intervalType = getIntervalType(lowerBound, upperBound, intervalType);

        boolean isEmpty = false;
        if (lowerBound != null && upperBound != null) {
            @SuppressWarnings("unchecked")
            int i = lowerBound.compareTo(upperBound);

            isEmpty = this.intervalType != IntervalType.CLOSED && i == 0;

            /*
             lowerBound 如果比 upperBound 大，则交换位置
             */
            if (i > 0) {
                T tmpLowerBound = lowerBound;
                lowerBound = upperBound;
                upperBound = tmpLowerBound;
            }
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.start = lowerBound;
        this.end = upperBound;
        this.infimum = lowerBound;
        this.supremum = upperBound;
        this.min = lowerBound;
        this.max = upperBound;
        this.left = lowerBound;
        this.right = upperBound;
        this.from = lowerBound;
        this.to = upperBound;
        this.head = lowerBound;
        this.tail = upperBound;
        this.begin = lowerBound;
        this.finish = upperBound;
        this.first = lowerBound;
        this.last = upperBound;
        this.origin = lowerBound;
        this.destination = upperBound;
        this.source = lowerBound;
        this.target = upperBound;
        this.earliest = lowerBound;
        this.latest = upperBound;
        this.bottom = lowerBound;
        this.top = upperBound;
        this.floor = lowerBound;
        this.ceiling = upperBound;

        this.isEmpty = isEmpty;
        this.hasInfinity = lowerBound == null || upperBound == null;
    }

    public static <T extends Comparable<?>> Range<T> of(T lowerBound, T upperBound, IntervalType intervalType) {
        return new Range<>(lowerBound, upperBound, intervalType);
    }

    public static <T extends Comparable<?>> Range<T> of(Range<T> range, IntervalType intervalType) {
        return new Range<>(range.lowerBound, range.upperBound, intervalType);
    }

    public static <T extends Comparable<?>> Range<T> open(T lowerBound, T upperBound) {
        return of(lowerBound, upperBound, IntervalType.OPEN);
    }

    public static <T extends Comparable<?>> Range<T> closed(T lowerBound, T upperBound) {
        return of(lowerBound, upperBound, IntervalType.CLOSED);
    }

    public static <T extends Comparable<?>> Range<T> openClosed(T lowerBound, T upperBound) {
        return of(lowerBound, upperBound, IntervalType.OPEN_CLOSED);
    }

    public static <T extends Comparable<?>> Range<T> closedOpen(T lowerBound, T upperBound) {
        return of(lowerBound, upperBound, IntervalType.CLOSED_OPEN);
    }

    @SuppressWarnings("unchecked")
    public boolean contains(T value) {
        if (value == null) return false;

        return (lowerBound == null || (intervalType.isLeftClose() ? lowerBound.compareTo(value) <= 0 : lowerBound.compareTo(value) < 0)) &&
                (upperBound == null || (intervalType.isRightClose() ? upperBound.compareTo(value) >= 0 : upperBound.compareTo(value) > 0));
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean hasInfinity() {
        return hasInfinity;
    }

    /**
     * Convert list to Range list
     *
     * @param ts           a list will be converted to Range list
     * @param sortOrNot    Whether to sort. The sorting is in ascending order
     * @param intervalType interval Type
     * @param <T>          Range type
     * @return a list of Ranges
     */
    public static <T extends Comparable<? super T>> List<Range<T>> toRanges(List<T> ts, boolean sortOrNot, IntervalType intervalType) {
        if (G.isEmpty(ts)) return new ArrayList<>();
        if (intervalType == null) intervalType = IntervalType.CLOSED_OPEN;

        if (sortOrNot) {
            ts = new ArrayList<>(ts);
            Collections.sort(ts);
        }

        List<Range<T>> ranges = new ArrayList<>();
        for (int i = 0; i < ts.size() - 1; i++) {
            ranges.add(new Range<>(ts.get(i), ts.get(i + 1), intervalType));
        }
        return ranges;
    }

    public static <T extends Comparable<? super T>> List<Range<T>> toRanges(List<T> ts, boolean sortOrNot) {
        return toRanges(ts, sortOrNot, IntervalType.CLOSED_OPEN);
    }

    public static <T extends Comparable<? super T>> List<Range<T>> toRanges(List<T> ts) {
        return toRanges(ts, true, IntervalType.CLOSED_OPEN);
    }

    private static <T extends Comparable<?>> IntervalType getIntervalType(T lowerBound, T upperBound, IntervalType intervalType) {
        if (lowerBound == null && upperBound == null) intervalType = IntervalType.OPEN;
        else if (lowerBound == null) intervalType = intervalType.isRightClose() ? IntervalType.OPEN_CLOSED : IntervalType.OPEN;
        else if (upperBound == null) intervalType = intervalType.isLeftClose() ? IntervalType.CLOSED_OPEN : IntervalType.OPEN;
        return intervalType;
    }

    /**
     * A hash code value for this Range
     *
     * @return a hash code value for this Range.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lowerBound, upperBound, intervalType == null ? 0 : intervalType.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String lower = lowerBound == null ? "-" + INFINITY : G.toString(lowerBound);
        String upper = upperBound == null ? "+" + INFINITY : G.toString(upperBound);

        sb.append(intervalType.leftSign).append(lower).append(", ").append(upper).append(intervalType.rightSign);

        return sb.toString();
    }

    public String toSimpleString() {
        return isEmpty ? EMPTY_SET : ((lowerBound == null && upperBound == null) ? SET_OF_REAL_NUMBERS : toString());
    }

}
