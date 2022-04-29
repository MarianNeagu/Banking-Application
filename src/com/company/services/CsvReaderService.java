package com.company.services;

import com.company.user.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderService {

    private static CsvReaderService instance;

    public static CsvReaderService getInstance(){
        if(instance == null){
            instance = new CsvReaderService();
        }
        return instance;
    }

    public List<String[]> readCustomersFromCsv()
    {
        List<String[]> stringList = new ArrayList<>();
        String line = "";
        String splitBy = ",";
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\resources\\customers.csv"));
            br.readLine(); // read header
            while ((line = br.readLine()) != null)
            //returns a Boolean value
            {
                String[] customer = line.split(splitBy);
                //use comma as separator
//                System.out.println("First Name: " + customer[1] + ", Last Name: " + customer[2] + ", Email: " + customer[3] + ", Password: " + customer[4] + ", CNP: " + customer[5]  + ", Phone Number: " + customer[6] + "]");
                stringList.add(customer);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
