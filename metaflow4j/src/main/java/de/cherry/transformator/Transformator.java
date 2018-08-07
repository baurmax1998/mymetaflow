package de.cherry.transformator;

import spoon.reflect.code.CtStatement;
import spoon.reflect.factory.Factory;

public interface Transformator {
  public void init(Factory factory);
  public CtStatement getCall();
}
