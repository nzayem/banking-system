package com.nzayem.bankingsystem;

import java.util.Random;

public class Card {

    private String accountNumber;
    private String password;
    public int balance;

    private static final Random rand = new Random();

    // No argument constructor, just for fun

    public Card() {

    }

    public static String generateLuhn() {

        String BIN = "400000";
        int controlNumber = 0;
        int checkSum = 0;

        String card = BIN.concat(String.format("%09d", rand.nextInt(100000000)));

        String[] cardArr = card.split("");

        // Converting the String to an array of Integers

        int[] cardNumber = new int[15];

        for (int i = 0; i < 15; i++) {

            cardNumber[i] = Integer.parseInt(cardArr[i]);

        }

        //Multiply even digits by 2 and subtract 9

        for (int i = 0; i < cardNumber.length; i++) {

            if (i % 2 == 0) {

                cardNumber[i] *= 2;

                if (cardNumber[i] > 9) {

                    cardNumber[i] -= 9;

                }
            }

        }

        // Calculating the Control number

        for (int j : cardNumber) {

            controlNumber += j;

        }

        // Calculating the checkSum

        while ((controlNumber + checkSum) % 10 != 0) {

            checkSum++;

        }

        // Adding the checkSum and return the complete card Number

        return card.concat(String.valueOf(checkSum));

    }

    public static boolean verifyLuhn(String cardNumber) {

        int controlNumber = 0;
        int checkSum = 0;

        String[] cardArray = cardNumber.split("");

        // Converting the String to an array of Integers and dropping the last digit

        int[] cardInt = new int[cardArray.length - 1];

        for (int i = 0; i < cardInt.length; i++) {

            cardInt[i] = Integer.parseInt(cardArray[i]);

        }

        //Multiply even digits by 2 and subtract 9

        for (int i = 0; i < cardInt.length; i++) {

            if (i % 2 == 0) {

                cardInt[i] *= 2;

                if (cardInt[i] > 9) {

                    cardInt[i] -= 9;

                }
            }

        }

        // Calculating the Control number

        for (int element : cardInt) {

            controlNumber += element;

        }

        // Calculating the checkSum

        while ((controlNumber + checkSum) % 10 != 0) {

            checkSum++;

        }

        // verifying the checkSum with the last digit of the card

        return checkSum == Integer.parseInt(cardArray[cardArray.length - 1]);
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber() {

        this.accountNumber = generateLuhn();
    }

    public String getPassword() {

        return password;
    }

    public void setPassword() {

        this.password = String.format("%04d", rand.nextInt(8999) + 1000);
    }

    public int getBalance() {

        return balance;
    }

}