package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class App {

    private final PersistanceOption option;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public App(PersistanceOption option) {
        this.option = option;
        Persistence.init(option).clear();
    }

    public RowContainer loadData() {
        switch (option) {
            case CLASSIC_COLLECTION -> {
                return loadDataClassic();
            }
            case OPTIMIZED_COLLECTION -> {
                return loadDataOptimized();
            }
            case DATABASE_DISK -> {
                return loadDataDatabaseDisk();
            }
            case DATABASE_MEMORY -> {
                return loadDataDatabaseMemory();
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private RowContainer loadDataClassic() {
        final RowContainer container = new ClassicRowContainer();
        load(container);

        return container;
    }

    private RowContainer loadDataOptimized() {
        final RowContainer container = new OptimizedRowContainer();
        load(container);

        return container;
    }

    private RowContainer loadDataDatabaseDisk() {
        final RowContainer container = new DatabaseRowContainer();
        load(container);

        return container;
    }

    private RowContainer loadDataDatabaseMemory() {
        final RowContainer container = new DatabaseRowContainer();
        load(container);

        return container;
    }

    private void load(RowContainer container) {
        try (CSVReader reader = new CSVReader(new FileReader(App.class.getClassLoader().getResource("test.csv").getFile()))) {
            reader.skip(1);

            String[] values = null;
            while ((values = reader.readNext()) != null) {
                if (values.length < 5) {
                    throw new RuntimeException("Erwarte 5 Spalten, aber es gibt nur " + values.length);
                }

                container.add(
                        new Row(
                                Long.parseLong(values[0]),
                                Long.parseLong(values[1]),
                                values[2],
                                values[3],
                                values[4]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
