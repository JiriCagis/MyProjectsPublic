package database.dao;

import database.model.Track;

import java.sql.*;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

/**
 * An implementation of {@link BaseDao} that persists recordings in database.
 * Created by cagaj on 23.7.2016.
 */
public class TrackDao implements BaseDao<Track> {

    private Connection connection;

    public TrackDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stream<Track> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,title,duration FROM Track");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Track>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Track> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createTrack(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<Track> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,title,duration FROM Track WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createTrack(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Stream<Track> getByRecordingID(int recordingID) throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT id,title,duration FROM Track WHERE recordingId = ?");
        statement.setInt(1,recordingID);
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Track>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Track> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createTrack(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public int add(Track item) throws Exception {
        String sql = "INSERT INTO Track (title,duration,genreId,recordingId,artistId) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, item.getTitle());
        statement.setInt(2,item.getDuration());
        statement.setString(3,item.getGenre()!=null ? item.getGenre().getId().toString() : null);
        statement.setString(4,item.getRecording()!=null ? item.getRecording().getId().toString() : null);
        statement.setString(5,item.getArtist()!=null ? item.getArtist().getId().toString() : null);

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
    public int update(Track item) throws Exception {
        String sql = "UPDATE Track SET title=?, duration=?, artistId=?, recordingId=?, genreId=? WHERE id=? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,item.getTitle());
        statement.setInt(2,item.getDuration());
        statement.setString(3,item.getArtist()!=null ? item.getArtist().getId().toString() : null);
        statement.setString(4,item.getRecording()!=null ? item.getRecording().getId().toString() : null);
        statement.setString(5,item.getGenre()!=null ? item.getGenre().getId().toString() : null);
        statement.setInt(6,item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(Track item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Track where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Track createTrack(ResultSet resultSet) throws SQLException {
        return new Track(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("duration"),
                null,
                null,
                null);
    }
}
