package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.Iteration;
import com.github.nikita_volkov.java.iterations.executor.*;

/**
 * Turn any Iterable into Reducible.
 */
public final class IterableReducible<input> implements Reducible<input> {

  private final IterationExecutor<input> iterationExecutor;

  public IterableReducible(Iterable<input> iterable) {
    this.iterationExecutor = new IterableIterationExecutor<>(iterable);
  }

  @Override
  public <output> output execute(Iteration<input, output> iteration) {
    return iterationExecutor.execute(iteration);
  }

}
