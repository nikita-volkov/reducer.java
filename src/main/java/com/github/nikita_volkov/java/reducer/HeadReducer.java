package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.Iteration;

import java.util.Optional;

public final class HeadReducer<a> implements Reducer<a, Optional<a>> {
  @Override
  public Iteration<a, Optional<a>> newIteration() {
    return new Iteration<a, Optional<a>>() {
      private Optional<a> result = Optional.empty();

      @Override
      public boolean step(a a) {
        if (result.isPresent()) return false;
        else {
          result = Optional.of(a);
          return false;
        }
      }

      @Override
      public Optional<a> output() {
        return result;
      }
    };
  }
}
