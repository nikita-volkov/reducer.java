package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.composites.Product2;
import com.github.nikita_volkov.java.iterations.Iteration;

import java.util.Optional;

public final class ReducingWithLeftoversReducer<input, intermediateOutput, output> implements Reducer<input, output> {

  private final Reducer<input, Product2<Optional<input>, intermediateOutput>> intermediateReducer;
  private final Reducer<intermediateOutput, output> mainReducer;

  public ReducingWithLeftoversReducer(Reducer<input, Product2<Optional<input>, intermediateOutput>> intermediateReducer, Reducer<intermediateOutput, output> mainReducer) {
    this.intermediateReducer = intermediateReducer;
    this.mainReducer = mainReducer;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new Iteration<input, output>() {

      private Iteration<input, Product2<Optional<input>, intermediateOutput>> currentIntermediateIteration =
        intermediateReducer.newIteration();
      private final Iteration<intermediateOutput, output> mainIteration =
        mainReducer.newIteration();

      @Override
      public boolean step(input input) {
        boolean continueIntermediateIteration = currentIntermediateIteration.step(input);
        if (continueIntermediateIteration) {
          return true;
        } else {
          Product2<Optional<input>, intermediateOutput> intermediateOutput = currentIntermediateIteration.output();
          currentIntermediateIteration = intermediateReducer.newIteration();
          boolean continueMainIteration = mainIteration.step(intermediateOutput._2);
          if (continueMainIteration && intermediateOutput._1.isPresent()) {
            return step(intermediateOutput._1.get());
          } else {
            return continueMainIteration;
          }
        }
      }

      @Override
      public output output() {
        return mainIteration.output();
      }

    };
  }

}
