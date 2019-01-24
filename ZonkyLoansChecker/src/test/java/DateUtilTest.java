import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Jiří Cága
 */
public class DateUtilTest {

    private static Date date;

    @BeforeClass
    public static void init(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,Calendar.JANUARY,2,7,5,0);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        date = calendar.getTime();
    }

    @Test
    public void createRightISO8601StringFromDate(){
        String formattedDate = DateUtil.getISO8601StringForDate(date);
        Assert.assertEquals("Date must be in ISO8601 format","2000-01-02T07:05:00Z",formattedDate);
    }

    @Test
    public void verifyTruncateTimeFromDate(){
        Date truncatedDate = DateUtil.truncateTime(date);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(truncatedDate);
        Assert.assertEquals("Hour attribute must be zero",0,calendar.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals("Minute attribute must be zero", 0,calendar.get(Calendar.MINUTE));
        Assert.assertEquals("Second atribute must be zero",0,calendar.get(Calendar.SECOND));
    }
}
