package database.dao.relationship;

import database.dao.BaseDaoTest;
import database.dao.ReviewDao;
import database.dao.ReviewerDao;
import database.model.Review;
import database.model.Reviewer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by cagaj on 27.7.2016.
 */
public class ReviewReviewerTest extends BaseDaoTest {

    private ReviewDao reviewDao;
    private ReviewerDao reviewerDao;

    private int REVIEWER_ID;
    private int REVIEW_ID;
    private final String REVIEWER_NAME = "James";
    private final String REVIEW = "text, text, text";
    private final String RATING = "rating 1";

    @Override
    protected void setup() throws Exception {
        reviewDao = new ReviewDao(connection);
        reviewerDao = new ReviewerDao(connection);
        saveToDatabase();
    }

    private void saveToDatabase() throws Exception {
        Reviewer reviewer = new Reviewer(REVIEWER_NAME);
        REVIEWER_ID = reviewerDao.add(reviewer);
        reviewer.setId(REVIEWER_ID);

        Review review = new Review(reviewer, null, REVIEW, RATING);
        REVIEW_ID = reviewDao.add(review);
    }


    @Test
    public void relationship() throws Exception {
        Optional<Review> review = reviewDao.getByID(REVIEW_ID);
        Optional<Reviewer> reviewer = reviewerDao.getByID(REVIEWER_ID);

        Assert.assertTrue(review.isPresent());
        Assert.assertTrue(reviewer.isPresent());
        Assert.assertEquals(REVIEW, review.get().getReview());
        Assert.assertEquals(RATING, review.get().getRating());
        Assert.assertEquals(REVIEWER_NAME, reviewer.get().getName());
    }
}
