package org.example;

import it.unimi.dsi.fastutil.longs.Long2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ReferenceAVLTreeMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OptimizedRowContainer implements RowContainer {

    private final Long2ReferenceAVLTreeMap<Row> map;

    private final Long2ReferenceAVLTreeMap<List<Row>> idxTopic;

    public OptimizedRowContainer() {
        map = new Long2ReferenceAVLTreeMap<>();
        idxTopic = new Long2ReferenceAVLTreeMap<>();
    }

    @Override
    public long size() {
        return map.size();
    }

    @Override
    public void add(Row row) {
        map.put(row.id(), row);
        idxTopic.computeIfAbsent(row.topic(), r -> new ArrayList<>()).add(row);
    }

    @Override
    public Row getById(long id) {
        return map.get(id);
    }

    @Override
    public Collection<Row> getByTopic(long topic) {
        return idxTopic.get(topic);

//        return map.values().parallelStream()
//                .filter(row -> row.topic() == topic)
//                .toList();
    }

    @Override
    public Collection<Row> getByTitle(String title) {
        return map.values().parallelStream()
                .filter(row -> row.questionTitle().equals(title))
                .toList();
    }

    @Override
    public Collection<Long> findIdsByKeyword(String keyword) {
        return map.values().parallelStream()
                .filter(row -> row.questionContent().contains(keyword))
                .map(Row::id)
                .toList();
    }
}
