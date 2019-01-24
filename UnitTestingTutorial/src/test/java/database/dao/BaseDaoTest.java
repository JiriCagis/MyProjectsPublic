package database.dao;

import database.ConnectionHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by cagaj on 26.7.2016.
 */
public abstract class BaseDaoTest {

    public static final String DATABASE_CONFIG = "database.properties";
    protected static Connection connection;

    @BeforeClass
    public static void setUp() throws Exception {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(DATABASE_CONFIG);
        connection = ConnectionHelper.openConnection(inputStream);

    }

    @Before
    public void beforeTest() throws Exception {
        connection.setAutoCommit(false);
        setup();
    }

    protected abstract void setup() throws Exception;

    @After
    public void afterTest() throws SQLException {
        connection.rollback();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (connection != null){
            connection.close();
        }
    }
}
