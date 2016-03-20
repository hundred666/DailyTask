package txiner.top.dailytask.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wzhuo on 2016/3/20.
 */
public class DateUtil {
    public long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();
        return date.getTime();
    }
}
