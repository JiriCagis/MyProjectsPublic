package database.dao;

import database.model.Label;
import database.model.Recording;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by cagaj on 26.7.2016.
 */
public class RecordingDaoTest extends BaseDaoTest {

    private RecordingDao dao;

    private int RECORDING_ID;
    private final String TITLE = "title";
    private final LocalDate RELEASE_DATE = LocalDate.now();

    @Override
    protected void setup() throws Exception {
        dao = new RecordingDao(connection);
        RECORDING_ID = dao.add(new Recording(TITLE,RELEASE_DATE,null,null));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Recording> item = dao.getByID(RECORDING_ID);

        assertTrue(item.isPresent());
        assertEquals(TITLE,item.get().getTitle());
        assertEquals(RELEASE_DATE,item.get().getReleaseDate());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Recording> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Recording(RECORDING_ID,TITLE,RELEASE_DATE,null,null));
        Optional<Recording> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedTitle = "title2";
        LocalDate modifiedDate = LocalDate.of(1999,1,1);

        Optional<Recording> itemForModify = dao.getByID(RECORDING_ID);
        itemForModify.get().setTitle(modifiedTitle);
        itemForModify.get().setReleaseDate(modifiedDate);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Recording> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedTitle,modifiedItem.get().getTitle());
        assertEquals(modifiedDate,modifiedItem.get().getReleaseDate());
    }
}
