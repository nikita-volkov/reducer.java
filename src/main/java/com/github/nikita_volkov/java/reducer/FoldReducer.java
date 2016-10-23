package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.function.*;

public final class FoldReducer<input, accumulator> implements Reducer<input, accumulator> {

  private final BiFunction<accumulator, input, accumulator> step;

  private final accumulator init;

  public FoldReducer(accumulator init, BiFunction<accumulator, input, accumulator> step) {
    this.init = init;
    this.step = step;
  }

  @Override
  public Iteration<input, accumulator> newIteration() {
    return new FoldIteration<>(init, step);
  }

}
