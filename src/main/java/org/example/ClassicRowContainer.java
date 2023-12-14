package org.example;

import java.util.ArrayList;
import java.util.Collection;

public class ClassicRowContainer implements RowContainer{

  public static final Row DEFAULT_ROW = new Row(-1, -1, "", "", "");

  private final Collection<Row> rowCollection = new ArrayList<>();


  @Override
  public long size() {
    return rowCollection.size();
  }

  @Override
  public void add(final Row row) {
    rowCollection.add(row);
  }

  @Override
  public Row getById(final long id) {
    return rowCollection.stream().filter(row -> row.id() == id).findFirst().orElse(DEFAULT_ROW);
  }

  @Override
  public Collection<Row> getByTopic(final long id) {
    return rowCollection.stream().filter(row -> row.topic() == id).toList();
  }

  @Override
  public Collection<Row> getByTitle(final String title) {
    return rowCollection.stream().filter(row -> row.questionTitle() == title).toList();
  }

  @Override
  public Collection<Long> findIdsByKeyword(final String keyword) {
    return rowCollection.stream().filter(row -> row.questionContent().contains(keyword)).map(Row::id).toList();
  }
}
