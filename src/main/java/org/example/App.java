package org.example;

import java.nio.file.Path;

public class App {

    private final static Path resource = null; // TODO

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public RowContainer loadDataClassic() {
        final RowContainer rowContainer = new ClassicRowContainer();


        // TODO
        return null;
    }

    public RowContainer loadDataOptimized() {
        final RowContainer rowContainer = new OptimizedRowContainer();
        // TODO
        return null;
    }
}
