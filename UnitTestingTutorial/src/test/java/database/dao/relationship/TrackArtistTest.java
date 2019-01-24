package database.dao.relationship;

import database.dao.ArtistDao;
import database.dao.BaseDaoTest;
import database.dao.TrackDao;
import database.model.Artist;
import database.model.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by cagaj on 31.7.2016.
 */
public class TrackArtistTest extends BaseDaoTest {

    private TrackDao trackDao;
    private ArtistDao artistDao;

    private int ARTIST_ID;
    private int TRACK_ID;
    private final String ARTIST_NAME = "artist 1";
    private final String TRACK_TITLE = "title 1";
    private final Integer TRACK_DURATION = 2;

    @Override
    protected void setup() throws Exception {
        trackDao = new TrackDao(connection);
        artistDao = new ArtistDao(connection);
        saveToDatabase();
    }

    private void saveToDatabase() throws Exception {
        Artist artist = new Artist(ARTIST_NAME);
        ARTIST_ID = artistDao.add(artist);
        artist.setId(ARTIST_ID);

        Track track = new Track(TRACK_TITLE, TRACK_DURATION, null, artist, null);
        TRACK_ID = trackDao.add(track);
    }

    @Test
    public void relationship() throws Exception {
        Optional<Track> track = trackDao.getByID(TRACK_ID);
        Optional<Artist> artist = artistDao.getByID(ARTIST_ID);

        Assert.assertTrue(track.isPresent());
        Assert.assertTrue(artist.isPresent());
        Assert.assertEquals(ARTIST_NAME,artist.get().getName());
        Assert.assertEquals(TRACK_TITLE,track.get().getTitle());
        Assert.assertEquals(TRACK_DURATION,track.get().getDuration());
    }
}
