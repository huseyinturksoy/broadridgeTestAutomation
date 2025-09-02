package com.broadridge.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class GenerateUtils {

    static Faker faker = new Faker();
    static Random random = new Random();

    public static String generateCustomerCode() {
        String randomString = faker.regexify("[a-zA-Z0-9]{5}");
        return randomString.toUpperCase();
    }

    public static String generateShortName() {
        String randomFirstName;

        do {
            randomFirstName = faker.name().firstName();
        } while (randomFirstName.length() != 5);

        return randomFirstName;
    }

    public static String generateLastName() {
        String lastName = faker.name().lastName();
        return lastName;
    }

    public static String generateEmail() {
        String email = faker.internet().emailAddress();
        return email;
    }

    public static String generatePhoneNumber() {
        String phoneNumber = faker.phoneNumber().cellPhone();
        return phoneNumber;
    }

    public static String generateJobTitle() {
        String jobTitle = faker.job().title();
        return jobTitle;
    }

    public static String generateCompanyName() {
        String companyName = faker.company().name();
        return companyName;
    }

    public static String generateMessage() {
        String message = faker.lorem().sentence();
        return message;
    }

    public static String generateRandom5char() {
        String randomString = faker.regexify("[a-zA-Z0-9]{5}");
        return randomString;
    }
    public static String generateRandom10char() {
        String randomString = faker.regexify("[a-zA-Z0-9]{10}");
        return randomString;
    }

    public static int generateNumberOneToHundred(){
        return random.nextInt(100)+1;
    }
    public static int generateNumberZeroTo5Hundred(){
        return random.nextInt(501);
    }

    public static String generateEORInumber(){
        return faker.numerify("#############");
    }

    public static String getTomorrowsDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(calendar.getTime());
    }

 /*   public static String generateRandomDate(){

        int day = random.nextInt(30);
        int month = random.nextInt(12);
        int


        return faker.date().future()
    }*/




}
