import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Jiří Cága 7/9/2018.
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan({"task","service"})
@PropertySource("classpath:zonkyLoansChecker.properties")
public class Application {

    private final static Logger LOGGER = Logger.getLogger(Application.class);



    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
