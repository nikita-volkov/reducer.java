package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.HashMap;
import java.util.function.*;

public final class GroupingReducer<input, key, groupingOutput, output> implements Reducer<input, output> {

  private final Function<input, key> keyProjection;

  private final Reducer<input, groupingOutput> groupReducer;

  private final Reducer<groupingOutput, output> finalReducer;

  public GroupingReducer(Function<input, key> keyProjection, Reducer<input, groupingOutput> groupReducer, Reducer<groupingOutput, output> finalReducer) {
    this.keyProjection = keyProjection;
    this.groupReducer = groupReducer;
    this.finalReducer = finalReducer;
  }

  @Override
  public Iteration<input, output> newIteration() {
    return new Iteration<input, output>() {

      private final HashMap<key, Iteration<input, groupingOutput>> iterations = new HashMap<>();

      @Override
      public boolean step(input input) {
        key key = keyProjection.apply(input);
        Iteration<input, groupingOutput> existingIteration = iterations.computeIfAbsent(key, __ -> groupReducer.newIteration());
        existingIteration.step(input);
        return true;
      }

      @Override
      public output output() {
        Iteration<groupingOutput, output> finalIteration = finalReducer.newIteration();
        for (Iteration<input, groupingOutput> iteration : iterations.values()) {
          finalIteration.step(iteration.output());
        }
        return finalIteration.output();
      }

    };
  }

}
