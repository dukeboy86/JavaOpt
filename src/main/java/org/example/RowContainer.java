package org.example;

import java.util.Collection;

public interface RowContainer {

    long size();

    void add(Row row);

    Row getById(long id);

    Collection<Row> getByTopic(long topic);

    Collection<Row> getByTitle(String title);

    Collection<Long> findIdsByKeyword(String keyword);
}
