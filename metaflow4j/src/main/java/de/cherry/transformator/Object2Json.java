package de.cherry.transformator;

import spoon.reflect.code.CtStatement;
import spoon.reflect.factory.Factory;

public class Object2Json implements Transformator {

  private Factory factory;

  @Override
  public void init(Factory factory) {
    this.factory = factory;

  }

  @Override
  public CtStatement getCall() {
    return factory.createCodeSnippetStatement("System.out.println(\"singelton\")");
  }
}
