package database.dao;

import database.model.Reviewer;

import java.sql.*;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

/**
 * An implementation of {@link BaseDao} that persists reviewers in database.
 * Created by cagaj on 23.7.2016.
 */
public class ReviewerDao implements BaseDao<Reviewer> {

    private Connection connection;

    public ReviewerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stream<Reviewer> getAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT id,name FROM Reviewer");
        final ResultSet resultSet = statement.executeQuery();

        //new interface added to java.util is the Spliterator, which as the name implies, is a new special kind of
        //Iterator that can traverse a Collection
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Reviewer>(Long.MAX_VALUE,
                ORDERED) {
            public boolean tryAdvance(Consumer<? super Reviewer> action) {
                try {
                    if (!resultSet.next()) {
                        return false;
                    }
                    action.accept(createReviewer(resultSet));
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false);
    }

    @Override
    public Optional<Reviewer> getByID(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("select id,name FROM Reviewer WHERE id= ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createReviewer(resultSet));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Reviewer> getByReviewID(int reviewID) throws Exception{
        String sql = "select reviewer.id, reviewer.name from review left join reviewer on (review.reviewerId = reviewer.id) where review.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,reviewID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(createReviewer(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int add(Reviewer item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Reviewer (name) VALUES ( ? )");
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
    public int update(Reviewer item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE Reviewer SET name=? WHERE id=?");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    @Override
    public int delete(Reviewer item) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Reviewer where id = ? ");
        statement.setInt(1,item.getId());
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating item failed, no rows affected.");
        }

        return item.getId();
    }

    private Reviewer createReviewer(ResultSet resultSet) throws SQLException {
        return new Reviewer(resultSet.getInt("id"),resultSet.getString("name"));
    }
}
