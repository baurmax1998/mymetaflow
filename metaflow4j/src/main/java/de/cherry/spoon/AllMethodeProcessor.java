package de.cherry.spoon;

import de.cherry.template.HelloWorldTemplate;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;

public class AllMethodeProcessor extends AbstractProcessor<CtMethod> {




    @Override
    public void process(CtMethod element) {
        System.out.println("hallo");
        HelloWorldTemplate helloWorldTemplate = new HelloWorldTemplate(getFactory());

        element.getBody().insertEnd(helloWorldTemplate.apply(null));

    }
}
