package de.cherry.transformator;

import de.cherry.template.HelloWorldTemplate;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;

public class Object2Object implements Transformator {
  private CtClass ctClass;
  private Factory factory;

  public Object2Object(CtClass ctClass) {
    this.ctClass = ctClass;
  }


  @Override
  public void init(Factory factory) {
    this.factory = factory;

  }

  @Override
  public CtStatement getCall() {
      HelloWorldTemplate helloWorldTemplate = new HelloWorldTemplate(factory);
      return helloWorldTemplate.apply(null);
      //return factory.createCodeSnippetStatement("System.out.println(\"singelton\")");
  }
}
