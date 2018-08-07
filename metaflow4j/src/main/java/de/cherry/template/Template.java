package de.cherry.template;

import spoon.reflect.code.CtBlock;
import spoon.reflect.factory.Factory;

public interface Template {
  CtBlock createFunction();

  void init(Factory factory);
}
