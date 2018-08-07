package de.cherry.generate;

import com.squareup.javapoet.JavaFile;
import de.cherry.spoon.AllClassProcessor;
import org.apache.commons.io.FileUtils;
import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.compiler.SpoonResourceHelper;
import spoon.processing.AbstractAnnotationProcessor;
import spoon.processing.Processor;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Application {

    private String name;

    private Package mainPackage;

    private File src;

    private File out;

    private Launcher launcher;
    private Factory factory;

    private AllClassProcessor allSpoonClasses;
    private CtModel model;

    public Factory getFactory() {
        return factory;
    }

    public String getName() {
        return name;
    }

    public Package getMainPackage() {
        return mainPackage;
    }

    public File getSrc() {
        return src;
    }

    public File getOut() {
        return out;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public AllClassProcessor getAllSpoonClasses() {
        return allSpoonClasses;
    }

    public CtModel getModel() {
        return model;
    }

    public Application(String name, Package mainPackage, File src, File out) {
        this.mainPackage = mainPackage;
        this.name = name;
        this.out = out;
        this.src = src;
    }

    public void init() {
        try {
            FileUtils.cleanDirectory(out);
            this.launcher = new Launcher();
            String exactSrc = src.getAbsolutePath() + "/" + mainPackage.getName().replace(".", "/");

            List<SpoonResource> resources =
                    SpoonResourceHelper.resources(exactSrc);
            for (SpoonResource resource : resources) {
                launcher.addInputResource(resource);
            }

            launcher.getEnvironment().setAutoImports(true);
            launcher.getEnvironment().setNoClasspath(true);
            launcher.buildModel();

            this.model = launcher.getModel();
            this.factory = launcher.getFactory();


            this.allSpoonClasses = new AllClassProcessor();
            model.processWith(allSpoonClasses);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addClass(JavaFile jClass) {
        try {
            File file = new File(out.getAbsolutePath());
            jClass.writeTo(file);
            launcher.addInputResource(SpoonResourceHelper.createResource(file));
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void processWith(Processor<?> processor) {
        model.processWith(processor);
    }

    public void print() {
        launcher.setSourceOutputDirectory(out);
        launcher.prettyprint();
        deleteTemps();

    }

    private void deleteTemps() {
        try {
            File tempDir = new File(out.getAbsolutePath() + "/de/cherry/dtf");
            FileUtils.deleteDirectory(tempDir);
            model.processWith(new AbstractAnnotationProcessor<RemoveFromPrint, CtClass>() {
                public void process(RemoveFromPrint annotation, CtClass element) {
                    new File(
                            out.getAbsolutePath()
                                    + "/"
                                    + element.getQualifiedName().replace('.', '/')
                                    + ".java")
                            .delete();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException("Can´t delete temp-files", e);
        }
    }

    public CtClass findClass(Class find) {
        for (CtClass ctClass : allSpoonClasses.getClasses()) {
            if (find.getSimpleName().equals(ctClass.getSimpleName())) {
                return ctClass;
            }
        }
        return null;
    }
}
