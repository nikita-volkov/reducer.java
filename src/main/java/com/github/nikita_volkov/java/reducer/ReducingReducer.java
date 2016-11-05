package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.Iteration;

/**
 * The Semigroupoid operation for Reducer.
 */
public final class ReducingReducer<input, intermediateOutput, output> implements Reducer<input, output> {

  private final Reducer<input, intermediateOutput> intermediateReducer;
  private final Reducer<intermediateOutput, output> mainReducer;

  public ReducingReducer(Reducer<input, intermediateOutput> intermediateReducer, Reducer<intermediateOutput, output> mainReducer) {
    this.intermediateReducer = intermediateReducer;
    this.mainReducer = mainReducer;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new Iteration<input, output>() {

      private Iteration<input, intermediateOutput> currentIntermediateIteration =
        intermediateReducer.newIteration();
      private boolean currentIntermediateIterationIsUncommitted =
        true;
      private final Iteration<intermediateOutput, output> mainIteration =
        mainReducer.newIteration();

      @Override
      public boolean step(input input) {
        currentIntermediateIterationIsUncommitted = currentIntermediateIteration.step(input);
        if (currentIntermediateIterationIsUncommitted) {
          return true;
        } else {
          intermediateOutput output1 = currentIntermediateIteration.output();
          currentIntermediateIteration = intermediateReducer.newIteration();
          return mainIteration.step(output1);
        }
      }

      @Override
      public output output() {
        if (currentIntermediateIterationIsUncommitted) {
          mainIteration.step(currentIntermediateIteration.output());
        }
        return mainIteration.output();
      }

    };
  }

}
