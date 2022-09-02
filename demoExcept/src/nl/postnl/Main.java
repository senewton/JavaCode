package nl.postnl;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        System.out.println("SDF Test: " + DateUtil.getSimpleDateFormat().format(new Date()));
        System.out.println("DTF Test: " + DateUtil.getFormattedDateTimeNow());
    }

    private static void testDateFormats(){
        DateFormatTest dt = new DateFormatTest();
        dt.testSimpleDateFormat();
        dt.getFormattedDateTimeNow();

        String rawDate = "30/11/2022"; // "27/02/2011";
        dt.sdfFunction(rawDate);
        dt.dtfFunction(rawDate);
        dt.multiFunction("30/11/2022 15:34:59");


        dt.endOfMonth2();
        System.out.println( "End of current month: " + dt.endOfCurrentMonth() );
        Date d1 = dt.convertStringToDate("01-07-2021");
        Date d2 = dt.convertStringToDate("18-08-2022");
        Date d3 = dt.convertStringToDate("31-12-2022");
        dt.futureDate(null);
        dt.futureDate(d1);
        dt.futureDate(d2);
        dt.futureDate(d3);

        dt.futureDate2(new Date());
        dt.futureDate2(d1);
        dt.futureDate2(d2);
        dt.futureDate2(d3);
        dt.futureDate2(null);

        dt.incrementMonth(LocalDate.of(2021,6,15));
        dt.incrementMonth(LocalDate.of(2021,12,25));
        dt.incrementMonth(LocalDate.of(2021,12,31));
        dt.incrementMonth(LocalDate.of(2021,11,30));

        System.out.println("\nPrint: " + dt.getNextPricePeriodFullString());
        System.out.println("Print: " + dt.getNextPricePeriodDateString());

        System.out.println("\n## Program Completed");
    }


    private static void testExceptions(){
        ExceptionTest et = new ExceptionTest();

        System.out.println("\n## Demo Exception Handling");

        et.function1();
        et.function2("5678");
        et.function3("./testdata/myfile.txt");

        int linesRead = et.function4("./testdata/myfile.txt");
        System.out.println("## F4: Read " + linesRead + " lines from file.");

        linesRead = et.function5("./testdata/myfile.txt");
        System.out.println("## F5: Read " + linesRead + " lines from file.");
    }

}
