package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class DroppingReducer<input, output> implements Reducer<input, output> {

  private final Reducer<input, output> initialReducer;
  private final long amount;

  public DroppingReducer(Reducer<input, output> initialReducer, long amount) {
    this.initialReducer = initialReducer;
    this.amount = amount;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new DroppingIteration<>(initialReducer.newIteration(), amount);
  }

}
