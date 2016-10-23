package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class ChainingReducer<a, b, c> implements Reducer<a, c> {

  private final Reducer<a, Iterable<b>> reducer1;
  private final Reducer<b, c> reducer2;

  public ChainingReducer(Reducer<a, Iterable<b>> reducer1, Reducer<b, c> reducer2) {
    this.reducer1 = reducer1;
    this.reducer2 = reducer2;
  }

  @Override
  public Iteration<a, c> newIteration() {
    return new ChainingIteration<>(reducer1.newIteration(), reducer2.newIteration());
  }

}
