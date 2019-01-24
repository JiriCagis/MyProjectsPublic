package database.dao;

import database.model.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static java.util.Spliterator.ORDERED;
/**
 * An implementation of {@link BaseDao} that persists labels in database.
 * Created by cagaj on 23.7.2016.
 */
public class LabelDao implements BaseDao<Label> {

    private Connection connection;

    public LabelDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public Stream<Label> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,name FROM Label");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Label>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Label> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createLabel(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<Label> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("select id,name FROM Label WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createLabel(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Label> getByRecordingID(int recordingId) throws Exception {
        String sql = "select label.id, label.name from recording left join label on (label.id = recording.labelId) where recording.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, recordingId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createLabel(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int add(Label item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Label (name) VALUES ( ? )");
        statement.setString(1, item.getName());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else
            return -1;
    }

    @Override
    public int update(Label item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE Label SET name=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(Label item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Label where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Label createLabel(ResultSet resultSet)throws SQLException {
        return new Label(resultSet.getInt("id"),resultSet.getString("name"));
    }
}
