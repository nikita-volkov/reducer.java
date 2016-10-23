package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.composites.Product2;
import com.github.nikita_volkov.java.iterations.*;

public final class ParReducer<input1, input2, output1, output2> implements Reducer<Product2<input1, input2>, Product2<output1, output2>> {

  private final Reducer<input1, output1> reducer1;
  private final Reducer<input2, output2> reducer2;

  public ParReducer(Reducer<input1, output1> reducer1, Reducer<input2, output2> reducer2) {
    this.reducer1 = reducer1;
    this.reducer2 = reducer2;
  }

  @Override
  public Iteration<Product2<input1, input2>, Product2<output1, output2>> newIteration() {
    return new ParIteration<>(reducer1.newIteration(), reducer2.newIteration());
  }

}
