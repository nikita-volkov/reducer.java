package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.composites.Product2;
import com.github.nikita_volkov.java.iterations.*;

public final class ZippingReducer<input, output1, output2> implements Reducer<input, Product2<output1, output2>> {

  private final Reducer<input, output1> reducer1;
  private final Reducer<input, output2> reducer2;

  public ZippingReducer(Reducer<input, output1> reducer1, Reducer<input, output2> reducer2) {
    this.reducer1 = reducer1;
    this.reducer2 = reducer2;
  }

  @Override
  public Iteration<input, Product2<output1, output2>> newIteration() {
    return new ZippingIteration<>(reducer1.newIteration(), reducer2.newIteration());
  }

}
