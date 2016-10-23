package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.function.Function;

public final class MappingReducer<input, output1, output2> implements Reducer<input, output2> {

  private final Reducer<input, output1> initialReducer;
  private final Function<output1, output2> fn;

  public MappingReducer(Reducer<input, output1> initialReducer, Function<output1, output2> fn) {
    this.initialReducer = initialReducer;
    this.fn = fn;
  }

  @Override
  public Iteration<input, output2> newIteration() {
    return new MappingIteration<>(initialReducer.newIteration(), fn);
  }

}
