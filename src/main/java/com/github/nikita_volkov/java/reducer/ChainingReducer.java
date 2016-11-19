package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class ChainingReducer<input, output> implements Reducer<input, output> {

  private final OpenImplementation<input, ?, output> openImplementation;

  public <x> ChainingReducer(Reducer<input, Iterable<x>> reducer1, Reducer<x, output> reducer2) {
    openImplementation = new OpenImplementation<>(reducer1, reducer2);
  }

  @Override
  public Iteration<input, output> newIteration() {
    return openImplementation.newIteration();
  }

  /**
   * A work-around for the absence of the existential types.
   */
  private static final class OpenImplementation<a, b, c> implements Reducer<a, c> {

    private final Reducer<a, Iterable<b>> reducer1;
    private final Reducer<b, c> reducer2;

    public OpenImplementation(Reducer<a, Iterable<b>> reducer1, Reducer<b, c> reducer2) {
      this.reducer1 = reducer1;
      this.reducer2 = reducer2;
    }

    @Override
    public Iteration<a, c> newIteration() {
      return new ChainingIteration<>(reducer1.newIteration(), reducer2.newIteration());
    }

  }

}
