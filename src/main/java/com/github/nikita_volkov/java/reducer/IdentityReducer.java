package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.Iteration;

public final class IdentityReducer<input> implements Reducer<input, input> {
  @Override
  public Iteration<input, input> newIteration() {
    return new Iteration<input, input>() {
      private input input;

      @Override
      public boolean step(input input) {
        this.input = input;
        return false;
      }

      @Override
      public input output() {
        return input;
      }
    };
  }
}
