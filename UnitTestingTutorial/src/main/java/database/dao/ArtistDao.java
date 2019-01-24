package database.dao;

import database.model.Artist;

import java.sql.*;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

/**
 * An implementation of {@link BaseDao} that persists artists in database.
 * Created by cagaj on 23.7.2016.
 */
public class ArtistDao implements BaseDao<Artist> {

    private Connection connection;

    public ArtistDao(Connection connection) {
        this.connection = connection;
    }

    public Stream<Artist> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,name FROM Artist");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Artist>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Artist> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createArtist(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    public Optional<Artist> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("select id,name FROM Artist WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createArtist(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Artist> getByRecordingId(int recordingId) throws Exception{
        String sql = "select artist.id, artist.name from recording left join artist on (recording.artistId = artist.id) where recording.id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,recordingId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createArtist(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Artist> getByTrackId(int trackId) throws Exception{
        String sql = "select artist.id, artist.name from track left join artist on (track.artistId = artist.id) where track.id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,trackId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createArtist(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public int add(Artist item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Artist (name) VALUES ( ? )");
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

    public int update(Artist item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET name=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    public int delete(Artist item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Artist where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Artist createArtist(ResultSet resultSet) throws SQLException {
        return new Artist(resultSet.getInt("id"), resultSet.getString("name"));
    }

}
