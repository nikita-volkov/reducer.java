package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

public final class CharacterCatReducer implements Reducer<Character, String> {
  @Override
  public Iteration<Character, String> newIteration() {
    return new CharacterCatIteration();
  }
}
