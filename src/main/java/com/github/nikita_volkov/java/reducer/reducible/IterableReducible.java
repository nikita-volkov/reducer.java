package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.Iteration;

/**
 * Turn any Iterable into Reducible.
 */
public final class IterableReducible<input> implements Reducible<input> {

  private final Iterable<input> iterable;

  public IterableReducible(Iterable<input> iterable) {
    this.iterable = iterable;
  }

  @Override
  public <output> output reduce(Iteration<input, output> iteration) {
    for (input input : iterable) {
      if (!iteration.step(input)) break;
    }
    return iteration.output();
  }

}
