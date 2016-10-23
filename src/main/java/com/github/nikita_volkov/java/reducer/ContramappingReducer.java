package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.function.Function;

public final class ContramappingReducer<input1, input2, output> implements Reducer<input2, output> {

  private final Reducer<input1, output> initialReducer;
  private final Function<input2, input1> fn;

  public ContramappingReducer(Reducer<input1, output> initialReducer, Function<input2, input1> fn) {
    this.initialReducer = initialReducer;
    this.fn = fn;
  }

  @Override
  public Iteration<input2, output> newIteration() {
    return new ContramappingIteration<>(initialReducer.newIteration(), fn);
  }

}
