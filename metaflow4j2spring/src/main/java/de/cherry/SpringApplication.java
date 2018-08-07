package de.cherry;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import de.cherry.generate.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spoon.reflect.factory.Factory;

import javax.lang.model.element.Modifier;
import java.io.File;

public class SpringApplication extends Application {

    private Factory factory;

    public SpringApplication(String name, Package mainPackage, File src, File out) {
        super(name, mainPackage, src, out);
    }

    @Override
    public void init() {
        super.init();
        this.factory = getFactory();
        this.createSpringMain();
    }

    private void createSpringMain() {

        String application = "Application";
        String args = "args";
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, args)
                .addStatement("$T.run(" + application + ".class, $L)"
                        , org.springframework.boot.SpringApplication.class, args)
                .build();


        TypeSpec helloWorld = TypeSpec.classBuilder(application)
                .addAnnotation(SpringBootApplication.class)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder(this.getMainPackage().getName(), helloWorld)
                .build();

        this.addClass(javaFile);

    }
}
