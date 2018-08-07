package de.cherry.template;

import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtTypeReference;

public class MainTemplate implements Template {

  private CtMethod<Object> main;

  public CtBlock createFunction() {
    return main.getBody();
  }

  public void init(Factory factory) {
    CtClass mainClass = factory.createClass("Main");
    main = factory.createMethod();
    main.setSimpleName("main");
    TypeFactory typeFactory = new TypeFactory();
    CtTypeReference aVoid = typeFactory.VOID_PRIMITIVE;
    main.setType(aVoid);
    main.addModifier(ModifierKind.PUBLIC);
    main.addModifier(ModifierKind.STATIC);
    CtParameter<Object> parameter = factory.createParameter();
    CtArrayTypeReference stringArr = factory.createArrayReference(factory.createCtTypeReference(String.class));
    parameter.setType(stringArr);
    parameter.setSimpleName("args");
    main.addParameter(parameter);
    CtBlock<Object> block = factory.createBlock();
    main.setBody(block);
    mainClass.addMethod(this.main);
  }
}
