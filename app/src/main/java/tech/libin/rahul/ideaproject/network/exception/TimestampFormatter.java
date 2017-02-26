package tech.libin.rahul.ideaproject.network.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 10945 on 10/27/2016.
 */

public class TimestampFormatter {
    private static TimestampFormatter timeFormatter;

    public static TimestampFormatter getInstance() {
        timeFormatter = new TimestampFormatter();
        return timeFormatter;
    }

    public String getTimestamp() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd.HH_mm_ss");
        return dateFormat.format(date);
    }
}
