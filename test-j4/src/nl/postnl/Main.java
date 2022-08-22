package nl.postnl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Main {

    public static void main(String[] args) {
        System.out.println("Java Tests");

        String scode = "1";  // String type
        int icode = 1; // Integer Type

        String sresult = String.format("%3s", scode).replace(" ", "0");
        String iresult = String.format("%03d", icode);
        System.out.println(iresult);
        System.out.println(sresult);

        String s1 = "Something";
        String s2 = " ";
        String s3 = "";
        String s4 = null;

        System.out.println("\nTest 1:");
        testMyString(s1);

        System.out.println("\nTest 2:");
        testMyString(s2);

        System.out.println("\nTest 3:");
        testMyString(s3);

        System.out.println("\nTest 4:");
        testMyString(s4);

        System.out.println("\nTest 5:");
        testMyString(null);

        System.out.println("\nTest 5:");
        testMyString("a");
    }

    /*
    public static void testMyString(String s){

        if( s == null ){
            System.out.println("Null argument: []");
            return;
        }

        if( s.isEmpty() ){
            System.out.println("Empty String: [" + s + "]");
        }

        if( !s.isEmpty() ){
            System.out.println("Non empty string: [" + s + "]");
        }

    }
    */

    public static void testMyString(String s){
        /*if(!s.trim().isEmpty()){
            System.out.println("1; Non empty string: [" + s + "]");
        }
        if(!s.isEmpty() && !s.trim().isEmpty()){
            System.out.println("2; Non empty string: [" + s + "]");
        }*/
        if(s != null && !s.isEmpty() && !s.trim().isEmpty()){
            System.out.println("3; Non empty string: [" + s + "]");
        }
    }

}
