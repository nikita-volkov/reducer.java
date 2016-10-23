package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class LongSumReducer implements Reducer<Long, Long> {
  @Override
  public Iteration<Long, Long> newIteration() {
    return new LongSumIteration();
  }
}
