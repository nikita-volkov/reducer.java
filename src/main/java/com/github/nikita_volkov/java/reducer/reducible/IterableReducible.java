package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.Iteration;
import com.github.nikita_volkov.java.reducer.Reducer;

import java.util.Iterator;

/**
 * Turn any Iterable into Reducible.
 */
public final class IterableReducible<input> implements Reducible<input> {

  private final Iterable<input> iterable;

  public IterableReducible(Iterable<input> iterable) {
    this.iterable = iterable;
  }

  @Override
  public <output> output reduce(Reducer<input, output> reducer) {
    Iterator<input> iterator = iterable.iterator();
    Iteration<input, output> iteration = reducer.newIteration();
    while (iterator.hasNext()) {
      if (!iteration.step(iterator.next())) break;
    }
    return iteration.output();
  }

}
