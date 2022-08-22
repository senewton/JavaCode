package nl.postnl;

import java.io.*;

public class ExceptionTest {


    ExceptionTest(){
        System.out.println("## Constructed Exception Test Class");
    }


    public void function1(){
        try {
            int i = Integer.parseInt("324");
            System.out.println("Function 1: i = " + i );
        } catch (NumberFormatException nfe){
            System.out.println("F1 Caught Exception: " + nfe);
        }
    }


    public void function2(String s) throws NumberFormatException {
        try{
            int j = Integer.parseInt(s);
            System.out.println("Function 2: i = " + j );
        } catch(NumberFormatException nfe) {
            // throw new NumberFormatException("function2 exception");
            System.out.println("F2 Caught Exception: " + nfe);
        }
    }


    public boolean function3(String fn)  {
        try{
            FileInputStream infile = new FileInputStream(fn);
            System.out.println("F3 File opened: " + fn);
            return true;
        } catch(FileNotFoundException fnfe) {
            System.out.println("F3 caught exception: " + fnfe );
            return false;
        }
    }


    public int function4(String fn) {
        int linesRead = 0;
        BufferedReader buff = null ;
        try{
            buff = new BufferedReader(new FileReader(fn));
            System.out.println("F4 Opened file: " + fn );
            String row;

            while ((row = buff.readLine()) != null ){
                System.out.println( "\tLine: " + row);
                linesRead++;
             }
            return linesRead;
        } catch(IOException ioe){
            System.out.println("F4 Failed to open or read file: " + fn + ioe);
            return linesRead;
        } finally {
            if(buff != null ) {
                try {
                    buff.close();
                    System.out.println("F4 Closed File: " + fn);
                } catch(IOException ioe) {
                    System.out.println("F4 Failed to close file:" + ioe );
                }
            } else {
                System.out.println("F4 No need to close file if never opened");
            }
            // return linesRead;
        }
        //return linesRead;
    }

    public int function5(String fn) {
        int linesRead = 0;
        BufferedReader buff = null ;
        try{
            buff = new BufferedReader(new FileReader(fn));
            System.out.println("F5 Opened file: " + fn );
            String row;

            while ((row = buff.readLine()) != null ){
                int rowId = Integer.parseInt(row.substring(0,row.indexOf(';')));
                String colourDesc = row.substring(row.indexOf(';')+1);
                linesRead++;
                System.out.println("\tF5 Line: " + linesRead + " ID:" + rowId + " Colour:" + colourDesc );
            }
            return linesRead;
        } catch(IOException ioe){
            System.out.println("F5 Failed to open or read file: " + fn + ioe);
            return linesRead;
        } catch(NumberFormatException nfe){
            System.out.println("F5 Failed to parse ID: " + nfe );
            return linesRead;
        }
        finally {
            if(buff != null ) {
                try {
                    buff.close();
                    System.out.println("F5 Closed File: " + fn);
                } catch(IOException ioe) {
                    System.out.println("F5 Failed to close file:" + ioe );
                }
            } else {
                System.out.println("F5 No need to close file if never opened");
            }
        }
    }

}
