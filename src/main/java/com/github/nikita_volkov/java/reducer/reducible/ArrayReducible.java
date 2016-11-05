package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.Iteration;
import com.github.nikita_volkov.java.iterations.executor.*;

/**
 * Turn any Iterable into Reducible.
 */
public final class ArrayReducible<input> implements Reducible<input> {

  private final IterationExecutor<input> iterationExecutor;

  public ArrayReducible(input... inputs) {
    iterationExecutor = new ArrayIterationExecutor<>(inputs);
  }

  @Override
  public <output> output execute(Iteration<input, output> iteration) {
    return iterationExecutor.execute(iteration);
  }

}
