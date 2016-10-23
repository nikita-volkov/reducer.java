package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class LengthReducer<input> implements Reducer<input, Long> {
  @Override
  public Iteration<input, Long> newIteration() {
    return new LengthIteration<>();
  }
}
