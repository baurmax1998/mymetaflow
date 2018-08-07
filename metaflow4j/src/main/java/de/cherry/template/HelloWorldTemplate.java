package de.cherry.template;


import spoon.processing.FactoryAccessor;
import spoon.reflect.factory.Factory;
import spoon.template.Parameter;
import spoon.template.StatementTemplate;

public class HelloWorldTemplate extends StatementTemplate {

    @Parameter
    public FactoryAccessor factoryAccess;

    public HelloWorldTemplate(final Factory factory) {
        this.factoryAccess = new FactoryAccessor() {
            public Factory getFactory() {
                return factory;
            }

            public void setFactory(Factory factory) {

            }
        };
    }

    @Override
    public void statement() throws Throwable {
        System.out.println("Hello World!1!");
    }
}
