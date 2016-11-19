package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.Iteration;

public final class InitReducer<input, output> implements Reducer<input, output> {
  
  private final Reducer<input, output> reducer;

  public InitReducer(Reducer<input, output> reducer) {
    this.reducer = reducer;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new Iteration<input, output>() {
      private final Iteration<input, output> iteration = reducer.newIteration();
      private input lastInput;

      @Override
      public boolean step(input input) {
        if (lastInput == null) {
          lastInput = input;
          return true;
        } else {
          boolean proceed = iteration.step(lastInput);
          lastInput = input;
          return proceed;
        }
      }

      @Override
      public output output() {
        return iteration.output();
      }
    };
  }

}
