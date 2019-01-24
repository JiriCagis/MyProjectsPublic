package database.dao;

import database.model.Genre;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by cagaj on 27.7.2016.
 */
public class GenreDaoTest extends BaseDaoTest {
    
    GenreDao dao;

    private int GENRE_ID;
    private final String NAME = "name1";

    @Override
    protected void setup() throws Exception {
        dao = new GenreDao(connection);
        GENRE_ID = dao.add(new Genre(NAME));
    }

    @Test
    public void retrieveItemFromDatabase() throws Exception{
        Optional<Genre> item = dao.getByID(GENRE_ID);

        assertTrue(item.isPresent());
        assertEquals(NAME,item.get().getName());
    }

    @Test
    public void retrieveAllItemsFromDatabase() throws Exception{
        Stream<Genre> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteItemFromDatabase() throws Exception{
        int deleteItemId = dao.delete(new Genre(GENRE_ID,null));
        Optional<Genre> deletedItem = dao.getByID(deleteItemId);
        assertFalse(deletedItem.isPresent());
    }

    @Test
    public void updateItemInDatabase() throws Exception{
        String modifiedName = "name2";

        Optional<Genre> itemForModify = dao.getByID(GENRE_ID);
        itemForModify.get().setName(modifiedName);
        int modifiedItemId = dao.update(itemForModify.get());
        Optional<Genre> modifiedItem = dao.getByID(modifiedItemId);

        assertTrue(modifiedItem.isPresent());
        assertEquals(modifiedName,modifiedItem.get().getName());
    }
}
