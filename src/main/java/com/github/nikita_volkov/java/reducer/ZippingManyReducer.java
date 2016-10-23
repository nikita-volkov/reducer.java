package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterables.*;
import com.github.nikita_volkov.java.iterations.*;

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
    return new ZippingManyIteration<>(new MappingIterable<>(reducers, Reducer::newIteration));
  }

}
