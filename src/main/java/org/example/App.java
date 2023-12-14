package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {

    private static final Path RESOURCE = Paths.get("src/main/resources/test.csv");

    public static void main(String[] args) {
        final App app = new App();
        final RowContainer rowContainerClassic = app.loadDataClassic();
        final RowContainer rowContainerOptimized = app.loadDataOptimized();
    }

    public RowContainer loadDataClassic() {
        final RowContainer rowContainer = new ClassicRowContainer();
        getData(rowContainer, RESOURCE);
        return rowContainer;
    }

    public RowContainer loadDataOptimized() {
        final RowContainer rowContainer = new OptimizedRowContainer();
        getData(rowContainer, RESOURCE);
        return rowContainer;
    }

    private static void getData(RowContainer rowContainer, final Path resource) {
        try {
            final List<String[]> dataStringArrays = CSVUtils.readAllLines(resource);
            for (final String[] dataStringArray : dataStringArrays) {
                final Long id = Long.valueOf(dataStringArray[0]);
                final Long topic = Long.valueOf(dataStringArray[1]);
                final String title = dataStringArray[2];
                final String content = dataStringArray[3];
                final String answer = dataStringArray[4];
                final Row row = new Row(id, topic, title, content, answer);
                rowContainer.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
