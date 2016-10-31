package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.executor.IterationExecutor;
import com.github.nikita_volkov.java.reducer.Reducer;

public interface Reducible<input> extends IterationExecutor<input> {
  default <output> output reduce(Reducer<input, output> reducer) {
    return execute(reducer.newIteration());
  }
}
