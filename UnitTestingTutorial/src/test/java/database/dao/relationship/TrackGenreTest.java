package database.dao.relationship;

import database.dao.BaseDaoTest;
import database.dao.GenreDao;
import database.dao.TrackDao;
import database.model.Genre;
import database.model.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by cagaj on 30.7.2016.
 */
public class TrackGenreTest extends BaseDaoTest {

    private GenreDao genreDao;
    private TrackDao trackDao;

    private int GENRE_ID;
    private int TRACK_ID;
    private final String GENRE_NAME = "jazz";
    private final String TRACK_TITLE = "title 1";
    private final Integer TRACK_DURATION = 2;

    @Override
    protected void setup() throws Exception {
        genreDao = new GenreDao(connection);
        trackDao = new TrackDao(connection);
        saveToDatabase();
    }

    private void saveToDatabase() throws Exception {
        Genre genre = new Genre(GENRE_NAME);
        GENRE_ID = genreDao.add(genre);
        genre.setId(GENRE_ID);

        Track track = new Track(TRACK_TITLE,TRACK_DURATION,genre,null,null);
        TRACK_ID = trackDao.add(track);
    }

    @Test
    public void relationship() throws Exception{
        Optional<Track> track = trackDao.getByID(TRACK_ID);
        Optional<Genre> genre = genreDao.getByID(GENRE_ID);

        Assert.assertTrue(track.isPresent());
        Assert.assertTrue(genre.isPresent());
        Assert.assertEquals(GENRE_NAME,genre.get().getName());
        Assert.assertEquals(TRACK_TITLE,track.get().getTitle());
        Assert.assertEquals(TRACK_DURATION,track.get().getDuration());
    }

}
