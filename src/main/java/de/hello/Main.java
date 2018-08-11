package de.hello;

import de.cherry.SpringApplication;
import de.cherry.generate.RemoveFromPrint;

import java.io.File;

@RemoveFromPrint
public class Main {

    public static void main(String[] args) {

        SpringApplication rest = new SpringApplication("rest"
                , Main.class.getPackage()
                , new File("/Users/mbaaxur/Documents/gits/mymetaflow/src/main/java/")
                , new File("/Users/mbaaxur/Documents/gits/mymetaflow/rest/src/main/java/"));
        rest.init();


        rest.print();
    }
}
