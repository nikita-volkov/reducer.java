package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.function.BiFunction;

public final class ZipmappingReducer<input, output1, output2, output3> implements Reducer<input, output3> {

  private final Reducer<input, output1> reducer1;
  private final Reducer<input, output2> reducer2;
  private final BiFunction<output1, output2, output3> fn;

  public ZipmappingReducer(Reducer<input, output1> reducer1, Reducer<input, output2> reducer2, BiFunction<output1, output2, output3> fn) {
    this.reducer1 = reducer1;
    this.reducer2 = reducer2;
    this.fn = fn;
  }

  @Override
  public Iteration<input, output3> newIteration() {
    return new ZipmappingIteration<>(reducer1.newIteration(), reducer2.newIteration(), fn);
  }

}
