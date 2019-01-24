package database.dao.relationship;

import database.dao.BaseDaoTest;
import database.dao.RecordingDao;
import database.dao.ReviewDao;
import database.model.Recording;
import database.model.Review;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cagaj on 31.7.2016.
 */
public class RecordingReviewsTest extends BaseDaoTest {

    private RecordingDao recordingDao;
    private ReviewDao reviewDao;

    private int RECORDING_ID;
    private Set<Review> REVIEWS;

    @Override
    protected void setup() throws Exception {
        recordingDao = new RecordingDao(connection);
        reviewDao = new ReviewDao(connection);
        saveToDatabase();
    }

    private void saveToDatabase() throws Exception {
        Recording recording = new Recording("Album 1", LocalDate.now(),null,null);
        RECORDING_ID = recordingDao.add(recording);
        recording.setId(RECORDING_ID);

        REVIEWS = new HashSet<>(3);
        for(int i=0;i<3;i++){
            Review review = new Review();
            review.setReview("text,text,text");
            review.setRating(Integer.toString(i));
            review.setRecording(recording);
            int reviewId = reviewDao.add(review);
            review.setId(reviewId);
            REVIEWS.add(review);
        }
    }

    @Test
    public void relationship() throws Exception {
        Stream<Review> reviewsStream = reviewDao.getByRecordingID(RECORDING_ID);
        Set<Review> retrievedReviews = reviewsStream.collect(Collectors.toSet());

        Assert.assertEquals(REVIEWS.size(),retrievedReviews.size());
        Assert.assertEquals(REVIEWS,retrievedReviews);
    }


}
