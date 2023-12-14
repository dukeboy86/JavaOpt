package org.example;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ClassicRowContainer implements RowContainer {

    private final Map<Long, Row> map;

    public ClassicRowContainer() {
        this.map = new TreeMap<>();
    }

    @Override
    public long size() {
        return map.size();
    }

    @Override
    public void add(Row row) {
        map.put(row.id(), row);
    }

    @Override
    public Row getById(long id) {
        return map.get(id);
    }

    @Override
    public Collection<Row> getByTopic(long topic) {
        return map.values().parallelStream()
                .filter(row -> row.topic() == topic)
                .toList();
    }

    @Override
    public Collection<Row> getByTitle(String title) {
        return map.values().parallelStream()
                .filter(row -> row.questionTitle().equals(title))
                .toList();
    }

    @Override
    public Collection<Long> findIdsByKeyword(String keyword) {
        return map.entrySet().parallelStream()
                .filter(entry -> entry.getValue().questionContent().contains(keyword))
                .map(Map.Entry::getKey)
                .toList();
    }
}
