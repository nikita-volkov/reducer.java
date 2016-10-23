package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.Optional;

public final class MaxReducer<input extends Comparable<input>> implements Reducer<input, Optional<input>> {
  @Override
  public Iteration<input, Optional<input>> newIteration() {
    return new MaxIteration<>();
  }
}
