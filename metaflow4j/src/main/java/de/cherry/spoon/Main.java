package de.cherry.spoon;

import de.cherry.spoon.getter.GetterProcessor;
import de.cherry.spoon.setter.SetterProcessor;
import org.apache.commons.io.FileUtils;
import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.compiler.SpoonResource;
import spoon.compiler.SpoonResourceHelper;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        FileUtils.cleanDirectory(new File("C:\\Users\\BA22036\\Desktop\\spoontest\\test\\src\\main\\java"));
        Launcher launcher = getLauncher();
        launcher.buildModel();
        launcher.setSourceOutputDirectory("C:\\Users\\BA22036\\Desktop\\spoontest\\test\\src\\main\\java");
        CtModel model = launcher.getModel();

// list all packages of the model
        for (CtPackage p : model.getAllPackages()) {
            System.out.println("package: " + p.getQualifiedName());
        }
// list all classes of the model
        for (CtType<?> s : model.getAllTypes()) {
            System.out.println("class: " + s.getQualifiedName());
        }

        model.processWith(new AllClassProcessor());
        model.processWith(new GetterProcessor());
        model.processWith(new SetterProcessor());
        // model.processWith(new RestifyProcessor());
        model.processWith(new NotNullProcessor());
        model.processWith(new NotNullCheckAdderProcessor());
        launcher.prettyprint();

        System.out.println("ende");
    }

    public static Launcher getLauncher() throws FileNotFoundException {
        Launcher launcher = new Launcher();

        List<SpoonResource> resources = SpoonResourceHelper.resources("/Users/mbaaxur/Desktop/spoontest/src/main/java/de/test");


        // path can be a folder or a file
        // addInputResource can be called several times
        launcher.addInputResource(resources.get(0));

        // if true, the pretty-printed code is readable without fully-qualified names
        launcher.getEnvironment().setAutoImports(true); // optional

        // if true, the model can be built even if the dependencies of the analyzed source code are not known or incomplete
        // the classes that are in the current classpath are taken into account
        launcher.getEnvironment().setNoClasspath(true); // optional
        return launcher;
    }


    private static Launcher getMavenLauncher() {
        return new MavenLauncher("C:\\Users\\BA22036\\Desktop\\spoontest\\test\\pom.xml", MavenLauncher.SOURCE_TYPE.APP_SOURCE);
    }

}
