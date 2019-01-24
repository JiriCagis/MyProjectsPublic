package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class for create connection by input stream with database params.
 * Important: Database params should be save in properties file, place obvious in resource folder.
 * Created by cagaj on 17.7.2016.
 */
public class ConnectionHelper {

    public static class ConnectionSetting{
        public String driver;
        public String url;
        public String userName;
        public String passwd;
    }


    public static Connection openConnection(InputStream inputStream) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            ConnectionSetting setting = parseConnectionSetting(inputStream);
            Class.forName(setting.driver);
            connection = DriverManager.getConnection(setting.url,setting.userName,setting.passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ConnectionSetting parseConnectionSetting(InputStream inputStream) throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(inputStream);

        ConnectionSetting setting = new ConnectionSetting();
        setting.driver = databaseProperties.getProperty("driver");
        setting.url = databaseProperties.getProperty("url");
        setting.userName = databaseProperties.getProperty("username");
        setting.passwd = databaseProperties.getProperty("passwd");

        return setting;
    }
}
