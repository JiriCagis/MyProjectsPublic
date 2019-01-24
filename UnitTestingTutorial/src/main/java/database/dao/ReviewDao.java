package database.dao;


import database.model.Review;

import java.sql.*;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

/**
 * An implementation of {@link BaseDao} that persists reviews in database.
 * Created by cagaj on 23.7.2016.
 */
public class ReviewDao implements BaseDao<Review> {

    private Connection connection;

    public ReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stream<Review> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,rating,review FROM Review");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Review>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Review> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createReview(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<Review> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,review,rating FROM Review WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createReview(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Stream<Review> getByRecordingID(int recordingId) throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT id,rating,review FROM Review WHERE recordingId=?");
        statement.setInt(1,recordingId);
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Review>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Review> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createReview(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public int add(Review item) throws Exception {
        String sql = "INSERT INTO Review (reviewerId,recordingId,rating,review) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,(item.getReviewer() != null) ? item.getReviewer().getId().toString() : null);
        statement.setString(2,(item.getRecording() != null) ? item.getRecording().getId().toString() : null);
        statement.setString(3,item.getRating());
        statement.setString(4,item.getReview());

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
    public int update(Review item) throws Exception {
        String sql = "UPDATE Review SET reviewerId=?, recordingId=?, rating=?, review=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,item.getReviewer()!=null ? item.getReviewer().getId().toString() : null);
        statement.setString(2,item.getRecording()!=null ? item.getRecording().getId().toString() : null);
        statement.setString(3,item.getRating());
        statement.setString(4,item.getReview());
        statement.setInt(5,item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(Review item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Review where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Review createReview(ResultSet resultSet) throws SQLException {
        return new Review(resultSet.getInt("id"),
                null,
                null,
                resultSet.getString("review"),
                resultSet.getString("rating"));
    }
}
