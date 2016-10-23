package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class StringCatReducer implements Reducer<String, String> {
  @Override
  public Iteration<String, String> newIteration() {
    return new StringCatIteration();
  }
}
