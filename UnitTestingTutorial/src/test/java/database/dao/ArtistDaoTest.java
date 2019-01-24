package database.dao;

import database.model.Artist;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by cagaj on 27.7.2016.
 */
public class ArtistDaoTest extends BaseDaoTest {

    private ArtistDao dao;

    private int ARTIST_ID;
    private final String NAME = "name1";

    @Override
    protected void setup() throws Exception {
        dao = new ArtistDao(connection);
        ARTIST_ID = dao.add(new Artist(NAME));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Artist> item = dao.getByID(ARTIST_ID);

        assertTrue(item.isPresent());
        assertEquals(NAME,item.get().getName());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Artist> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Artist(ARTIST_ID,null));
        Optional<Artist> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedName = "name2";

        Optional<Artist> itemForModify = dao.getByID(ARTIST_ID);
        itemForModify.get().setName(modifiedName);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Artist> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedName,modifiedItem.get().getName());
    }
}
