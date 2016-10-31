package com.github.nikita_volkov.java.reducer.reducible;

import com.github.nikita_volkov.java.reducer.Reducer;

public interface Reducible<input> {
  <output> output reduce(Reducer<input, output> reducer);
}
