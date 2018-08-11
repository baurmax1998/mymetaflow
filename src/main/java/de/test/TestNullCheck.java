package de.test;

import com.sun.istack.internal.NotNull;

public class TestNullCheck {

  public void test(@NotNull Person p){
    final String name = p.getName();

  }
}
