package database.dao.relationship;

import database.dao.BaseDaoTest;
import database.dao.RecordingDao;
import database.dao.TrackDao;
import database.model.Recording;
import database.model.Track;
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
public class RecordingTracksTest extends BaseDaoTest {

    private RecordingDao recordingDao;
    private TrackDao trackDao;

    private int RECORDING_ID;
    private Set<Track> TRACKS;

    @Override
    protected void setup() throws Exception {
        recordingDao = new RecordingDao(connection);
        trackDao = new TrackDao(connection);
        saveToDatabase();
    }

    private void saveToDatabase() throws Exception {
        Recording recording = new Recording("My album", LocalDate.now(), null, null);
        RECORDING_ID = recordingDao.add(recording);
        recording.setId(RECORDING_ID);

        TRACKS = new HashSet<>(3);
        for (int i = 0; i < 3; i++) {
            Track track = new Track();
            track.setTitle("Song" + Integer.toString(i));
            track.setDuration(i * 2 + 10);
            track.setRecording(recording);
            int trackID = trackDao.add(track);
            track.setId(trackID);
            TRACKS.add(track);
        }
    }

    @Test
    public void relationship() throws Exception {
        Stream<Track> tracksStream = trackDao.getByRecordingID(RECORDING_ID);
        Set<Track> retrievedTracks = tracksStream.collect(Collectors.toSet());

        Assert.assertEquals(TRACKS.size(), retrievedTracks.size());
        Assert.assertEquals(TRACKS, retrievedTracks);
    }
}
