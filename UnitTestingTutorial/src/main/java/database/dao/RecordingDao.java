package database.dao;

import database.model.Recording;

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
public class RecordingDao implements BaseDao<Recording> {

    private Connection connection;

    public RecordingDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public Stream<Recording> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,title,releaseDate FROM Recording");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Recording>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Recording> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createRecording(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<Recording> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,title,releaseDate FROM Recording WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createRecording(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Recording> getByTrackID(int trackId) throws Exception{
        String sql = "select recording.id, recording.title, recording.releaseDate FROM track left join recording on (recording.id = track.recordingId) where track.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, trackId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createRecording(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Recording> getByReviewID(int reviewId) throws Exception{
        String sql = "select recording.id, recording.title, recording.releaseDate FROM review left join recording on (recording.id = review.recordingId) where review.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, reviewId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createRecording(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int add(Recording item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Recording (title,releaseDate,artistId,labelId) " +
                "VALUES (?,?,?,?)");

        statement.setString(1, item.getTitle());
        statement.setDate(2,java.sql.Date.valueOf(item.getReleaseDate()));
        statement.setString(3,(item.getArtist() != null) ? item.getArtist().getId().toString() : null);
        statement.setString(4,(item.getLabel() != null) ? item.getLabel().getId().toString() : null);

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
    public int update(Recording item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE Recording SET title=?, releaseDate=?, " +
                "artistId=?, labelId=? WHERE id=?");
        statement.setString(1, item.getTitle());
        statement.setDate(2,(item.getReleaseDate()!=null) ? java.sql.Date.valueOf(item.getReleaseDate()):null);
        statement.setString(3, (item.getArtist() != null) ? item.getArtist().getId().toString() : null);
        statement.setString(4, (item.getLabel() != null) ? item.getLabel().getId().toString() : null);
        statement.setInt(5,item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(Recording item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Recording where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Recording createRecording(ResultSet resultSet) throws SQLException{
        Date releaseDate = resultSet.getDate("releaseDate");

        return new Recording(resultSet.getInt("id"),
                resultSet.getString("title"),
                (releaseDate !=null) ? releaseDate.toLocalDate() : null,
                null,
                null);
    }
}
