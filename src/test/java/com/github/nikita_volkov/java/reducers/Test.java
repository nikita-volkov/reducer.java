package com.github.nikita_volkov.java.reducers;

import com.github.nikita_volkov.java.composites.Product2;
import com.github.nikita_volkov.java.reducer.*;
import junit.framework.TestCase;

import java.util.Arrays;

public class Test extends TestCase {

  public void testMapIteration() {

    Reducer<String, String> reducer =
      new MappingReducer<>(
        new StringCatReducer(),
        string -> string + "!"
      );

    assertEquals("abc!", reducer.reduce(Arrays.asList("a", "b", "c")));

  }

  public void testZipIteration() {

    Reducer<String, String> reducer =
      new MappingReducer<>(
        new ZippingReducer<>(
          new LengthReducer<>(),
          new StringCatReducer()
        ),
        r -> r._2 + "(" + r._1.toString() + ")"
      );

    assertEquals("abc(3)", reducer.reduce(Arrays.asList("a", "b", "c")));

  }

  public void testUniqueIteration() {

    Reducer<String, String> reducer =
      new UniquifyingReducer<>(new StringCatReducer());

    assertEquals("abc", reducer.reduce(Arrays.asList("a", "b", "b", "c")));

  }

  public void testContraflatmapIteration() {

    Reducer<Integer, String> reducer =
      new ContraflatmappingReducer<>(new StringCatReducer(), i -> i % 2 == 0 ? Arrays.asList(i.toString()) : Arrays.asList());

    assertEquals("24", reducer.reduce(Arrays.asList(1, 2, 3, 4, 5)));

  }

  public void testZipBranchesTermination() {

    Reducer<Integer, Product2<Long, Long>> reducer =
      new ZippingReducer<>(
        new TakingReducer<>(new LengthReducer<>(), 3),
        new LengthReducer<>()
      );

    assertEquals(new Product2<>(3L, 5L), reducer.reduce(Arrays.asList(1, 2, 3, 4, 5)));

  }

  public void testPrereducingReducer2() {

    Reducer<Character, String> reducer =
      new ReducingReducer<>(new FilteringReducer<>(new TakingReducer<>(new CharacterCatReducer(), 2), Character::isUpperCase), new StringCatReducer());

    assertEquals("ABC", reducer.reduce(Arrays.asList('A', 'a', 'B', 'b', 'C', 'c')));

  }

  public void testPrereducingReducer3() {

    Reducer<Character, String> reducer =
      new ReducingReducer<>(new TakingReducer<>(new CharacterCatReducer(), 2), new StringCatReducer());

    assertEquals("AaBbCcD", reducer.reduce(Arrays.asList('A', 'a', 'B', 'b', 'C', 'c', 'D')));

  }

  public void testZipManyAndChainingReducer() {

    Reducer<Long, Long> reducer =
      new ChainingReducer<>(new ZippingManyReducer<>(new LongSumReducer(), new LengthReducer<>()), new LongSumReducer());

    assertEquals(6L, reducer.reduce(1L, 1L, 1L).longValue());

  }

  public void testZipMany() {

    Reducer<Long, Iterable<Long>> reducer =
      new ZippingManyReducer<>(new LongSumReducer(), new LengthReducer<>());

    assertEquals(6L, new LongSumReducer().reduce(reducer.reduce(1L, 1L, 1L)).longValue());

  }

}