import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TestUtilities {
    public static String timeLeftUntilNextBirthday(String dateOfBirth, String unit) {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        LocalDate birthday = LocalDate.parse(dateOfBirth);
        LocalDate nextBirthday = birthday.withYear(today.getYear());

        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        long timeLeft;

        switch (unit.toLowerCase()) {
            case "hour":
                timeLeft = ChronoUnit.HOURS.between(today.atTime(hour, minute), nextBirthday.atStartOfDay());
                return timeLeft + " hours left";
            case "day":
                timeLeft = ChronoUnit.DAYS.between(today, nextBirthday);
                return timeLeft + " days left";
            case "week":
                timeLeft = ChronoUnit.WEEKS.between(today, nextBirthday);
                return timeLeft + " weeks left";
            case "month":
                timeLeft = ChronoUnit.MONTHS.between(today, nextBirthday);
                return timeLeft + " months left";
            default:
                return "Invalid unit provided";
        }
    }

    
    @DataProvider(name = "BirthdayPositiveTestData")
    public Object[][] getData(){
        return new Object[][] {
                { getBirthdayWithDaysDiff(-1), "hour", "Yesterday" },
                { getBirthdayWithDaysDiff(-1), "day", "Yesterday" },
                { getBirthdayWithDaysDiff(-1), "week", "Yesterday" },
                { getBirthdayWithDaysDiff(-1), "month", "Yesterday" },
                { getBirthdayWithDaysDiff(1), "hour", "Tomorrow" },
                { getBirthdayWithDaysDiff(1), "day", "Tomorrow" },
                { getBirthdayWithDaysDiff(20), "hour", "After 20 days" },
                { getBirthdayWithDaysDiff(20), "day", "After 20 days" },
                { getBirthdayWithDaysDiff(20), "week", "After 20 days" },
                { getBirthdayWithDaysDiff(31), "month", "At least a month" },
                { getBirthdayWithDaysDiff(0), "hour", "Today" },
                { getBirthdayWithDaysDiff(0), "day", "Today" },
                { getBirthdayWithDaysDiff(0), "week", "Today" },
                { getBirthdayWithDaysDiff(0), "month", "Today" },
                { "2000-02-29", "month", "Feb-29 leap 4th century"},
                { "1996-02-29", "month", "Feb-29 leap year"},
        };
    }

    public static String getBirthdayWithDaysDiff(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        Date newDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(newDate);
    }

}
