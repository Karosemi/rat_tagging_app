package com.example.rattaggingstudio;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class OpenCSVWriter {

    private final File file;

    public OpenCSVWriter(File file) {
        this.file = file;

    }

    public void writeHeader(String data) {

        try {
            if (data != null) {

                PrintWriter csvWriter = new PrintWriter(new FileWriter(file, true));
                csvWriter.print(",");
                csvWriter.print(data);
                csvWriter.close();

            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }
}
//public class OpenCSVWriter {
//    String fileName = "src/main/resources/items.csv";
//    String title;
//    String date;
//    public OpenCSVWriter(String newTitle, String newDate){
//        title = newTitle;
//        date = newDate;
//    }
//    String[] info = {title, date};
//    public void main(String[] args)  {
//        File file = new File(fileName);
//        try {
//            FileWriter outputfile = new FileWriter(file);
//            CSVWriter writer = new CSVWriter(outputfile);
//            writer.writeNext(info);
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Something went wrong ");
//            e.printStackTrace();
//        }
//    }
//}
