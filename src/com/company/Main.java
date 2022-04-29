package com.company;
import com.company.services.MainService;

public class Main {

    public static void main(String[] args)
    {
        MainService mainService = MainService.getInstance();
        mainService.loginMenu();
    }
}