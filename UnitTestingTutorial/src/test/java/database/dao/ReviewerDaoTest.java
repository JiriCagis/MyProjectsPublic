package database.dao;

import database.model.Reviewer;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by cagaj on 27.7.2016.
 */
public class ReviewerDaoTest extends BaseDaoTest {

    private ReviewerDao dao;
    private int REVIEWER_ID;
    private final String NAME = "name1";

    @Override
    protected void setup() throws Exception {
        dao = new ReviewerDao(connection);
        REVIEWER_ID = dao.add(new Reviewer(NAME));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Reviewer> item = dao.getByID(REVIEWER_ID);

        assertTrue(item.isPresent());
        assertEquals(NAME,item.get().getName());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Reviewer> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Reviewer(REVIEWER_ID,null));
        Optional<Reviewer> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedName = "name2";

        Optional<Reviewer> itemForModify = dao.getByID(REVIEWER_ID);
        itemForModify.get().setName(modifiedName);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Reviewer> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedName,modifiedItem.get().getName());
    }
}
