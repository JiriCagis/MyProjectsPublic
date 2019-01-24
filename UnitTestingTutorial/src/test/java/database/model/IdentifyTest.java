package database.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cagaj on 23.7.2016.
 */
public class IdentifyTest {

    @Test
    public void sameEntities(){
        Artist artist = new Artist(1,"Jarek Nohavica");
        Assert.assertTrue(artist.equals(artist));
    }

    @Test
    public void differentEntities(){
        Artist artist1 = new Artist(1,"Artist1");
        Artist artist2 = new Artist(2,"Artist2");
        Assert.assertFalse(artist1.equals(artist2));
    }

    @Test
    public void sameEntitiesByIdButDifferentValues(){
        Artist artist1 = new Artist(1,"Artist1");
        Artist artist2 = new Artist(1,"Artist2");
        Assert.assertTrue(artist1.equals(artist2));
    }

}
