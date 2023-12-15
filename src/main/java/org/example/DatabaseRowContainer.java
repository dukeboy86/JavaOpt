package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseRowContainer implements RowContainer {

    private static final String RS_ERROR = "ResultSet ist leer :[";

    @Override
    public long size() {
        try (Statement statement = Persistence.getInstance().getConnection().createStatement()) {
            final ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM QUESTIONS");
            if (rs.next()) {
                return rs.getInt(1);
            }

            throw new RuntimeException(RS_ERROR);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Row row) {
        try (PreparedStatement statement = Persistence.getInstance().getConnection().prepareStatement("INSERT INTO QUESTIONS VALUES (?, ?, ?, ?, ?)")) {
            statement.setLong(1, row.id());
            statement.setLong(2, row.topic());
            statement.setString(3, row.questionTitle());
            statement.setString(4, row.questionContent());
            statement.setString(5, row.bestAnswer());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Row getById(long id) {
        try (PreparedStatement statement = Persistence.getInstance().getConnection().prepareStatement("SELECT * FROM QUESTIONS WHERE ID = ?")) {
            statement.setLong(1, id);

            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return parse(rs);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Row> getByTopic(long topic) {
        try (PreparedStatement statement = Persistence.getInstance().getConnection().prepareStatement("SELECT * FROM QUESTIONS WHERE TOPIC = ?")) {
            statement.setLong(1, topic);

            final List<Row> result = new ArrayList<>();
            final ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(parse(rs));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Row> getByTitle(String title) {
        try (PreparedStatement statement = Persistence.getInstance().getConnection().prepareStatement("SELECT * FROM QUESTIONS WHERE TITLE = ?")) {
            statement.setString(1, title);

            final List<Row> result = new ArrayList<>();
            final ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(parse(rs));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Long> findIdsByKeyword(String keyword) {
        throw new UnsupportedOperationException();
    }

    private Row parse(ResultSet rs) throws SQLException {
        return new Row(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5));
    }
}
