package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class TakingReducer<input, output> implements Reducer<input, output> {

  private final Reducer<input, output> initialReducer;

  private final long amount;

  public TakingReducer(Reducer<input, output> initialReducer, long amount) {
    this.initialReducer = initialReducer;
    this.amount = amount;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new TakingIteration<>(initialReducer.newIteration(), amount);
  }

}
