package database.dao;

import database.model.Review;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by cagaj on 26.7.2016.
 */
public class ReviewDaoTest extends BaseDaoTest {
    private ReviewDao dao;

    private int REVIEW_ID;
    private final String RATING = "rating";
    private final String REVIEW = "text";


    @Override
    protected void setup() throws Exception {
        dao = new ReviewDao(connection);
        REVIEW_ID = dao.add(new Review(null,null,REVIEW,RATING));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Review> item = dao.getByID(REVIEW_ID);

        assertTrue(item.isPresent());
        assertEquals(RATING,item.get().getRating());
        assertEquals(REVIEW,item.get().getReview());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Review> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Review(REVIEW_ID,null,null,REVIEW,RATING));
        Optional<Review> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedRating = "rating2";
        String modifiedReview = "review2";

        Optional<Review> itemForModify = dao.getByID(REVIEW_ID);
        itemForModify.get().setRating(modifiedRating);
        itemForModify.get().setReview(modifiedReview);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Review> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedReview,modifiedItem.get().getReview());
        assertEquals(modifiedRating,modifiedItem.get().getRating());
    }
}
