package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.composites.Product2;
import com.github.nikita_volkov.java.iterations.*;

import java.util.HashMap;

public final class HashMapReducer<key, value> implements Reducer<Product2<key, value>, HashMap<key, value>> {
  @Override
  public Iteration<Product2<key, value>, HashMap<key, value>> newIteration() {
    return new HashMapIteration<>();
  }
}
