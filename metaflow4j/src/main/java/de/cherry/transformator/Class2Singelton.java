package de.cherry.transformator;

import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;

public class Class2Singelton implements Transformator {

  private CtClass ctClass;
  private Factory factory;

  public Class2Singelton(CtClass ctClass) {
    this.ctClass = ctClass;
  }

  @Override
  public void init(Factory factory) {
    this.factory = factory;

    CtConstructor defaultConstructor = null;
    for (Object o : ctClass.getConstructors()) {
      CtConstructor constructor = (CtConstructor) o;
      constructor.addModifier(ModifierKind.PRIVATE);
      constructor.removeModifier(ModifierKind.PUBLIC);
      if (constructor.getParameters().size() == 0) {
        defaultConstructor = constructor;
      }
    }
    //create private defaultConstrurtor if not existe
    if (defaultConstructor.getBody().toString().length() <= 4) {
      defaultConstructor = factory.createDefault(ctClass);
      defaultConstructor.addModifier(ModifierKind.PRIVATE);
      defaultConstructor.removeModifier(ModifierKind.PUBLIC);
      defaultConstructor.setBody(factory.createBlock());
      ctClass.addConstructor(defaultConstructor);
    }


    CtField ourInstance = factory.createCtField("ourInstance"
        , ctClass.getReference()
        , "new " + ctClass.getSimpleName() + "();"
        , ModifierKind.PRIVATE, ModifierKind.STATIC);
    ctClass.addField(ourInstance);


    CtMethod<Object> getInstance = factory.createMethod();
    getInstance.setSimpleName("getInstance");
    getInstance.addModifier(ModifierKind.PUBLIC);
    getInstance.addModifier(ModifierKind.STATIC);
    getInstance.setType(ctClass.getReference());
    getInstance.setBody(factory.createCodeSnippetStatement("return ourInstance"));
    ctClass.addMethod(getInstance);
  }

  @Override
  public CtStatement getCall() {
    return factory.createCodeSnippetStatement("System.out.println(\"singelton\")");
  }
}
