package database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import database.ConnectionHelper.ConnectionSetting;

/**
 * Created by cagaj on 17.7.2016.
 */
public class SqlConnectionTest {


    public static final String DATABASE_CONFIG = "database.properties";

    private InputStream inputStream;

    @Before
    public void init() throws IOException {
        inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(DATABASE_CONFIG);
    }

    @Test
    public void canRetrieveConnectionSetting() throws Exception {
        ConnectionSetting connectionSetting = ConnectionHelper.parseConnectionSetting(inputStream);
        Assert.assertNotNull(connectionSetting);
        Assert.assertNotNull("Driver is null",connectionSetting.driver);
        Assert.assertNotNull("URL is null",connectionSetting.url);
        Assert.assertNotNull("Username is null",connectionSetting.userName);
        Assert.assertNotNull("Password is null",connectionSetting.passwd);
    }

    @Test
    public void connectionIsOpen() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionHelper.openConnection(inputStream);
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT 1 FROM Dual");
        Assert.assertTrue(resultSet.next());
    }
}
