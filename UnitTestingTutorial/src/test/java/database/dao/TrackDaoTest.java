package database.dao;

import database.model.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by cagaj on 27.7.2016.
 */
public class TrackDaoTest extends BaseDaoTest{

    private TrackDao dao;
    private int TRACK_ID;
    private final String TITLE = "Pisen1";
    private final Integer DURATION = 2;

    @Override
    protected void setup() throws Exception {
        dao = new TrackDao(connection);
        TRACK_ID = dao.add(new Track(TITLE,DURATION,null,null,null));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Track> item = dao.getByID(TRACK_ID);

        assertTrue(item.isPresent());
        assertEquals(TITLE,item.get().getTitle());
        assertEquals(DURATION,item.get().getDuration());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Track> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Track(TRACK_ID,null,null,null,null,null));
        Optional<Track> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedTitle = "title2";
        Integer modifiedDuration = 5;

        Optional<Track> itemForModify = dao.getByID(TRACK_ID);
        itemForModify.get().setTitle(modifiedTitle);
        itemForModify.get().setDuration(modifiedDuration);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Track> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedTitle,modifiedItem.get().getTitle());
        assertEquals(modifiedDuration,modifiedItem.get().getDuration());
    }
    
}
