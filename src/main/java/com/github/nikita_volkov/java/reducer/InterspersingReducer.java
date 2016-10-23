package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class InterspersingReducer<input, output> implements Reducer<input, output> {

  private final Reducer<input, output> initialReducer;
  private final input separator;

  public InterspersingReducer(Reducer<input, output> initialReducer, input separator) {
    this.initialReducer = initialReducer;
    this.separator = separator;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new InterspersingIteration<>(initialReducer.newIteration(), separator);
  }

}
