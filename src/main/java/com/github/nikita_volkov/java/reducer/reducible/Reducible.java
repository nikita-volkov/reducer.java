package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.iterations.Iteration;
import com.github.nikita_volkov.java.reducer.Reducer;

public interface Reducible<input> {
  <output> output reduce(Iteration<input, output> iteration);
  default <output> output reduce(Reducer<input, output> reducer) {
    return reduce(reducer.newIteration());
  }
}
