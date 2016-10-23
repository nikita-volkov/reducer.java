package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.function.Predicate;

public final class FilteringReducer<input, output> implements Reducer<input, output> {

  private final Reducer<input, output> initialReducer;
  private final Predicate<input> predicate;

  public FilteringReducer(Reducer<input, output> initialReducer, Predicate<input> predicate) {
    this.initialReducer = initialReducer;
    this.predicate = predicate;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new FilteringIteration<>(initialReducer.newIteration(), predicate);
  }

}
