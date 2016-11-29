package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterables.ArrayIterable;
import com.github.nikita_volkov.java.iterations.Iteration;

import java.util.Iterator;

public interface Reducer<input, output> {

  /**
   * Create a new stateful disposable iteration.
   */
  Iteration<input, output> newIteration();

  default output reduce(Iterator<input> iterator) {
    Iteration<input, output> iteration = newIteration();
    while (iterator.hasNext()) {
      iteration.step(iterator.next());
    }
    return iteration.output();
  }

  default output reduce(Iterable<input> iterable) {
    return reduce(iterable.iterator());
  }

  default output reduce(input... array) {
    return reduce(new ArrayIterable<>(array));
  }

}
