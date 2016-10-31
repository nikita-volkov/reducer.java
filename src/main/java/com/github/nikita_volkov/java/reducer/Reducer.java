package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.Iteration;

import java.util.Iterator;

public interface Reducer<input, output> {

  /**
   * Create a new stateful disposable iteration.
   */
  Iteration<input, output> newIteration();

}
