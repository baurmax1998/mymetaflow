package de.cherry.dtf;

import de.cherry.transformator.Transformator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtStatement;
import spoon.reflect.factory.Factory;

public class ProgrammBuilder {
  private CtBlock code;
  private Factory factory;

  public ProgrammBuilder(CtBlock code, Factory factory) {
    this.code = code;
    this.factory = factory;
  }

  public ProgrammBuilder next(Transformator transformator) {
    transformator.init(factory);
    CtStatement call = transformator.getCall();
    code.insertEnd(call);
    return this;
  }
}
