package com.github.nikita_volkov.java.reducers;

import com.github.nikita_volkov.java.composites.*;
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

    assertEquals("abc!", reducer.consume(Arrays.asList("a", "b", "c").iterator()));

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

    assertEquals("abc(3)", reducer.consume(Arrays.asList("a", "b", "c").iterator()));

  }

  public void testUniqueIteration() {

    Reducer<String, String> reducer =
      new UniquifyingReducer<>(new StringCatReducer());

    assertEquals("abc", reducer.consume(Arrays.asList("a", "b", "b", "c").iterator()));

  }

  public void testContraflatmapIteration() {

    Reducer<Integer, String> reducer =
      new ContraflatmappingReducer<>(new StringCatReducer(), i -> i % 2 == 0 ? Arrays.asList(i.toString()) : Arrays.asList());

    assertEquals("24", reducer.consume(Arrays.asList(1, 2, 3, 4, 5)));

  }

  public void testZipBranchesTermination() {

    Reducer<Integer, Product2<Long, Long>> reducer =
      new ZippingReducer<>(
        new TakingReducer<>(new LengthReducer<>(), 3),
        new LengthReducer<>()
      );

    assertEquals(new Product2<>(3L, 5L), reducer.consume(Arrays.asList(1, 2, 3, 4, 5)));

  }

}