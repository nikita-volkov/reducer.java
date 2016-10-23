package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

/**
 * Modifies an existing iteration to operate only on unique input values.
 */
public final class UniquifyingReducer<input, output> implements Reducer<input, output> {

  private final Reducer<input, output> initialReducer;

  public UniquifyingReducer(Reducer<input, output> initialReducer) {
    this.initialReducer = initialReducer;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new UniquifyingIteration<>(initialReducer.newIteration());
  }

}
