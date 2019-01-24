package database.dao;

import database.model.Genre;

import java.sql.*;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;


/**
 * An implementation of {@link BaseDao} that persists genres in database.
 * Created by cagaj on 23.7.2016.
 */
public class GenreDao implements BaseDao<Genre> {

    private Connection connection;

    public GenreDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public Stream<database.model.Genre> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,name FROM genre");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Genre>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Genre> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createGenre(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<database.model.Genre> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("select id,name FROM Genre WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createGenre(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Genre> getByTrackId(int trackId) throws Exception{
        String sql = "select genre.id, genre.name from track left join genre on (track.genreId = genre.id) where track.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,trackId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createGenre(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int add(database.model.Genre item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Genre (name) VALUES ( ? )");
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
    public int update(database.model.Genre item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE Genre SET name=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(database.model.Genre item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Genre where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Genre createGenre(ResultSet resultSet) throws SQLException {
        return new Genre(resultSet.getInt("id"),resultSet.getString("name"));
    }
}
