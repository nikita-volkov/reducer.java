package com.github.nikita_volkov.java.reducer;

import com.github.nikita_volkov.java.iterations.*;

import java.util.LinkedList;

public final class LinkedListReducer<input> implements Reducer<input, LinkedList<input>> {
  @Override
  public Iteration<input, LinkedList<input>> newIteration() {
    return new LinkedListIteration<>();
  }
}
