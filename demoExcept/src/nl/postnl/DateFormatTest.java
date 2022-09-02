package nl.postnl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.*;

public class DateFormatTest {

    private final String PMR_ZONE = "Europe/Amsterdam"; // "US/Hawaii"
    private static final SimpleDateFormat mySdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    DateFormatTest(){
        System.out.println("Constructed DateFormatTest");
    }

    public void testSimpleDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        final String sysdate = sdf.format(new Date());
        System.out.println("SDF Date String: " + sysdate);
    }

    public String getFormattedDateTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm").withZone(ZoneId.of("Europe/Amsterdam"));
        final String sysdate = dtf.format(LocalDateTime.now());
        System.out.println("SDF Date String: " + sysdate);
        return sysdate;
    }

    public void sdfFunction(String dateString){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date d = sdf.parse(dateString);
            System.out.println("SDF: Parsed date:" + d.toString());
        }catch(ParseException pe){
            System.out.println("SDF Function: " + pe);
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        sdf1.setTimeZone(TimeZone.getTimeZone("CET"));
        try {
            Date d = sdf1.parse("2020-06-14T03:02:01Z");
            System.out.println("SDF1: Parsed date:" + d.toString());
        }catch(ParseException pe){
            System.out.println("SDF1 Function: " + pe);
        }
    }

    public void dtfFunction(String dateString){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(dateString, dtf);
            System.out.println("DTF: Parsed date:" + ld.toString());
        } catch(DateTimeParseException dtpe){
            System.out.println("DTF Function: " + dtpe);
        }
    }

    public static void multiFunction(String dateString){

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Date d = mySdf.parse(dateString);
                    System.out.println("Multi SDF: Parsed date:" + d.toString());
                }catch(ParseException pe){
                    System.out.println("Multi SDF Function: " + pe);
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }

    public void endOfMonth(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endOfMonth = c.getTime();
        System.out.println("Date: End of Month:" + endOfMonth );
    }

    public void endOfMonth2(){
        LocalDate ld = LocalDate.now();
        LocalDate ld_start = ld.withDayOfMonth(1);
        LocalDate ld_end = ld.plusMonths(1).withDayOfMonth(1).minusDays(1);
        System.out.println("End: Now: " + ld);
        System.out.println("End: First: " + ld_start);
        System.out.println("End: Last: " + ld_end);
    }

    public Date endOfCurrentMonth(){
        ZoneId zoneId = ZoneId.systemDefault(); // Europe/Amsterdam
        System.out.println("ZoneId: " + zoneId);
        try {
            LocalDate ld = LocalDate.now();
            LocalDate ld_end = ld.plusMonths(1).withDayOfMonth(1).minusDays(1);
            return Date.from(ld_end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch(DateTimeException dte ){
            return null;
        }
    }

    public Date convertStringToDate (String dateString ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate locDate = null;

        try {
            locDate = LocalDate.parse(dateString, formatter);
            System.out.println("Converted date:" + Date.from(locDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) );
            return Date.from(locDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch(DateTimeParseException dtpe){
            System.out.println("Problem formatting date: " + dtpe);
            return null;
        }

    }

    public boolean futureDate(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (date == null ) {
            System.out.println("Null Date");
            return true;
        } else {
            System.out.println("Cal:" + cal.getTime());
            System.out.println("Date:" + date);
            if (date.compareTo(cal.getTime()) >= 0) {
                System.out.println("Date in future or present" + date);
                return true;
            }
        }
        System.out.println("Date in past" + date);
        return false;
    }

    public boolean futureDate2(Date date) {
       LocalDate ld_now = LocalDate.now();

        if (date == null ) {
            System.out.println("Null Date");
            return true;
        } else {
            Instant i = date.toInstant();
            LocalDateTime ldt_inp = LocalDateTime.ofInstant(i, ZoneId.of("Europe/Amsterdam"));
            LocalDate ld_inp = ldt_inp.toLocalDate();

            System.out.println("Input Date:" + date);
            System.out.println("LocalDate Now:" + ld_now);
            System.out.println("LocalDate Inp:" + ld_inp);

            if(ld_inp.isAfter(ld_now)){
                System.out.println("Date in future:" + date);
                return true;
            }
            if(ld_inp.isEqual(ld_now)){
                System.out.println("Date equal:" + date);
                return true;
            }
            if(ld_inp.isBefore(ld_now)){
                System.out.println("Date in past:" + date);
                return false;
            }
        }
        System.out.println("Date in past" + date);
        return false;
    }

    public void incrementMonth1(LocalDate ld_now){
        // LocalDate ld_now  = LocalDate.now();
        LocalDate ld_next = ld_now.plusMonths(1).withDayOfMonth(1);
        System.out.println("Increment Month: " + ld_now + " Result: " + ld_next);
    }

    public LocalDate incrementMonth(LocalDate ld_input){
        try {
            LocalDate ld_next = ld_input.plusMonths(1).withDayOfMonth(1);
            System.out.println("Increment Month: " + ld_input + " Result: " + ld_next);
            return ld_next;
        } catch (DateTimeException dte){
            return null;
        }
    }

    /**
     *
     * @return Returns a date string (01-MM-yyyy) with the first day of next month
     */
    public String getNextPricePeriodDateString() {
        try {
            LocalDate localDate = this.incrementMonth(LocalDate.now(ZoneId.of(PMR_ZONE)));
            String result = "01" + "-" + String.format("%02d", localDate.getMonthValue()) + "-" + localDate.getYear();
            return result;
        } catch (DateTimeException dte ) {
            return null;
        }
    }


    /**
     *
     * @return Returns a string containing "prijzen_" and the first day of the next pricing period: yyyy-MM-01
     */
    public String getNextPricePeriodFullString() {
        try {
            LocalDate localDate = this.incrementMonth(LocalDate.now(ZoneId.of(PMR_ZONE)));
            String result = "prijzen_" + localDate.getYear() + "-" + String.format("%02d", localDate.getMonthValue()) + "-01";
            return result;
        } catch (DateTimeException dte ){
            return null;
        }
    }

    public String formatDateAsString(DateTimeFormatter dtf, final Date d) {
        String dateTimeStr = null;

        Instant i = d.toInstant();

        try {
            LocalDateTime ld = LocalDateTime.ofInstant(i, dtf.getZone());
            dateTimeStr = dtf.format(ld);
        } catch(DateTimeException dte) {
            System.out.println("Format Date As String: Error converting date: " + d + " : " + dte );
        }
        return dateTimeStr;
    }


    public String setExportDateTime(final Date d) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm").withZone(ZoneId.of("Europe/Amsterdam"));

        return formatDateAsString(dtf, d);
    }

}
