package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public RowContainer loadDataClassic() {
        final RowContainer container = new ClassicRowContainer();
        load(container);

        return container;
    }

    public RowContainer loadDataOptimized() {
        final RowContainer container = new OptimizedRowContainer();
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
