package de.cherry.dtf;

import de.cherry.spoon.AllClassProcessor;
import de.cherry.template.MainTemplate;
import de.cherry.template.Template;
import org.apache.commons.io.FileUtils;
import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.compiler.SpoonResourceHelper;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;

import java.io.File;
import java.util.List;

public class DtfMain {


    public static void main(String[] args) throws Exception {
        String pathname = "/Users/mbaaxur/Documents/JavaTest/spoontest/test/src/main/java";
        String packageName = "de.test";
        FileUtils.cleanDirectory(new File(pathname));
        Launcher launcher = new Launcher();
        List<SpoonResource> resources =
                SpoonResourceHelper.resources("/Users/mbaaxur/Documents/JavaTest/spoontest/src/main/java/"
                                + packageName.replace(".", "/")
                        ,"/Users/mbaaxur/Documents/JavaTest/spoontest/src/main/java/de/dtf/template" );
        for (SpoonResource resource : resources) {
            launcher.addInputResource(resource);
        }
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();
        launcher.setSourceOutputDirectory(pathname);

        CtModel model = launcher.getModel();
        Factory factory = launcher.getFactory();


        AllClassProcessor allClassProcessor = new AllClassProcessor();
        model.processWith(allClassProcessor);


        Template main = new MainTemplate();
        main.init(factory);
        CtBlock codeMain = main.createFunction();
        ProgrammBuilder programmBuilder = new ProgrammBuilder(codeMain, factory);

       /* programmBuilder
                .next(new Class2Singelton(find(allClassProcessor, Person.class)))
                .next(new Object2Object(find(allClassProcessor, Animal.class)))
                .next(new Object2Json());
        */

        launcher.prettyprint();

        FileUtils.deleteDirectory(new File(pathname + "/de/cherry/dtf"));
        System.out.println("ende");
    }




    private static CtClass find(AllClassProcessor allClassProcessor, Class find) {
        for (CtClass ctClass : allClassProcessor.getClasses()) {
            if (find.getSimpleName().equals(ctClass.getSimpleName())) {
                return ctClass;
            }
        }
        throw new RuntimeException("Oh Now");
    }
}
