package database.dao;

import database.model.Label;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by cagaj on 26.7.2016.
 */
public class LabelDaoTest extends BaseDaoTest {

    private LabelDao dao;
    private final String labelName = "Sony s.r.o.";
    private int labelId;

    @Override
    protected void setup() throws Exception {
        dao = new LabelDao(connection);
        Label label = new Label(labelName);
        labelId = dao.add(label);
    }

    @Test
    public void retrieveLabelFromDatabase() throws Exception {
        Optional<Label> label = dao.getByID(labelId);

        assertTrue("Item with id=" + labelId + "should be in database.",label.isPresent());
        assertEquals(labelName,label.get().getName());
    }

    @Test
    public void retrieveAllLabelsFromDatabase() throws Exception{
        Stream<Label> items = dao.getAll();
        assertTrue(items.count()>0);
    }

    @Test
    public void deleteLabelFromDatabase() throws Exception{
        int deleteLabelId = dao.delete(new Label(labelId,labelName));
        Optional<Label> deletedLabel  = dao.getByID(deleteLabelId);
        Assert.assertTrue(!deletedLabel.isPresent());
    }

    @Test
    public void updateLabelInDatabase() throws Exception{
        String modifiedName = "NAME 1";
        Optional<Label> labelForModify = dao.getByID(labelId);
        labelForModify.get().setName(modifiedName);
        int modifiedLabelId = dao.update(labelForModify.get());
        Optional<Label> modifiedLabel = dao.getByID(modifiedLabelId);

        assertTrue(modifiedLabel.isPresent());
        assertEquals(modifiedName,modifiedLabel.get().getName());
    }
}
