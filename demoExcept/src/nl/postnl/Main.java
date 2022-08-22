package nl.postnl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        ExceptionTest et = new ExceptionTest();

        System.out.println("\n## Demo Exception Handling");

        et.function1();
        et.function2("2345");
        et.function3("./testdata/myfile.txt");

        int linesRead = et.function4("./testdata/myfile.txt");
        System.out.println("## F4: Read " + linesRead + " lines from file.");

        linesRead = et.function5("./testdata/myfile.txt");
        System.out.println("## F5: Read " + linesRead + " lines from file.");

        DateFormatTest dt = new DateFormatTest();
        String rawDate = "30/11/2022"; // "27/02/2011";
        dt.SdfFunction(rawDate);
        dt.DtfFunction(rawDate);
        //dt.MultiFunction("30/11/2022 15:34:59");
        //DateFormatTest.MultiFunction("30/11/2022 15:34:59");

        dt.EndOfMonth2();
        System.out.println( "End of current month: " + dt.EndOfCurrentMonth() );
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

        dt.IncrementMonth(LocalDate.of(2021,6,15));
        dt.IncrementMonth(LocalDate.of(2021,12,25));
        dt.IncrementMonth(LocalDate.of(2021,12,31));
        dt.IncrementMonth(LocalDate.of(2021,11,30));

        System.out.println("\nPrint: " + dt.getNextPricePeriodFullString());
        System.out.println("Print: " + dt.getNextPricePeriodDateString());

        System.out.println("\n## Program Completed");
    }

}
