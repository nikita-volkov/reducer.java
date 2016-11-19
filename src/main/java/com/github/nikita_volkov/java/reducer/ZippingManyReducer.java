package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterables.ArrayIterable;
import com.github.nikita_volkov.java.iterations.*;

import java.util.LinkedList;

public final class ZippingManyReducer<input, output> implements Reducer<input, Iterable<output>> {

  private final Iterable<Reducer<input, output>> reducers;

  public ZippingManyReducer(Iterable<Reducer<input, output>> reducers) {
    this.reducers = reducers;
  }

  public ZippingManyReducer(Reducer<input, output>... reducers) {
    this(new ArrayIterable<>(reducers));
  }

  @Override
  public Iteration<input, Iterable<output>> newIteration() {
    LinkedList<Iteration<input, output>> iterations = new LinkedList<>();
    for (Reducer<input, output> reducer : reducers) {
      iterations.add(reducer.newIteration());
    }
    return new ZippingManyIteration<>(iterations);
  }

}
