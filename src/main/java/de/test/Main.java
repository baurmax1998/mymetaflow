package de.test;

import de.cherry.generate.Application;
import de.cherry.generate.RemoveFromPrint;

import java.io.File;

@RemoveFromPrint
public class Main {

    public static void main(String[] args) {
        Application master = new Application("Master"
                , Main.class.getPackage()
                , new File("/Users/mbaaxur/Documents/gits/mymetaflow/src/main/java/")
                , new File("/Users/mbaaxur/Documents/gits/mymetaflow/test/src/main/java"));
        master.init();

        master.print();
    }
}
