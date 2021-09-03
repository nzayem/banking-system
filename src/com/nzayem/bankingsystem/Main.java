package com.nzayem.bankingsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Connecting to the database

        Scanner scanner = new Scanner(System.in);

        Card myCard = new Card();

        DbConnection dbConnection = new DbConnection();

        boolean loggedIn;

        while (true) {

            generalMenu();

            int userSelection = scanner.nextInt();

            switch (userSelection) {

                case 1:

                    System.out.println("Your card has been created\\r\n" +
                            "Your card number:");

                    myCard.setAccountNumber();

                    String account = myCard.getAccountNumber();

                    System.out.println(account);

                    System.out.println("Your card PIN:");

                    myCard.setPassword();

                    String pin = myCard.getPassword();

                    System.out.println(pin + "\n");

                    int balance = myCard.getBalance();

                    dbConnection.insert(account, pin, balance);

                    break;

                case 2:

                    System.out.println("\nEnter your card number:");

                    String userAccount = scanner.next();

                    System.out.println("Enter your PIN:");

                    String userPin = scanner.next();

                    if (dbConnection.checkLogin(userAccount, userPin)) {

                        System.out.println("\nYou have successfully logged in!\n");

                        loggedIn = true;

                        balance = dbConnection.returnBalance(userAccount, userPin);

                        while (loggedIn) {

                            accountMenu();

                            int accountOption = scanner.nextInt();

                            switch (accountOption) {

                                case 1:

                                    System.out.println("\nBalance: " + balance);
                                    System.out.println();

                                    break;

                                case 2:

                                    // Add Income
                                    System.out.println("Enter income:");

                                    balance += scanner.nextInt();

                                    dbConnection.update(userAccount, userPin, balance);

                                    System.out.println("Income was added!\n");

                                    break;

                                case 3:

                                    while (true) {

                                        System.out.println("Transfer\n" +
                                                "Enter card number:");

                                        String userCard = scanner.next();

                                        if (!Card.verifyLuhn(userCard)) {

                                            System.out.println("Probably you made a mistake in the card number." +
                                                    " Please try again!\n");
                                            break;
                                        }

                                        if (!dbConnection.checkCard(userCard) && Card.verifyLuhn(userCard)) {

                                            System.out.println("Such a card does not exist.\n");

                                            break;

                                        }

                                        if (userCard.equals(userAccount)) {

                                            System.out.println("You can't transfer money to the same account!\n");

                                            break;

                                        }

                                        if (Card.verifyLuhn(userCard) &&
                                                !(!dbConnection.checkCard(userCard) && Card.verifyLuhn(userCard))) {

                                            System.out.println("Enter how much money you want to transfer:");

                                            int amountTransfer = scanner.nextInt();

                                            if (balance < amountTransfer) {

                                                System.out.println("Not enough money!\n");

                                                break;

                                            }

                                            // Money to be transferred

                                            int targetBalance = dbConnection.returnBalance(userCard);

                                            dbConnection.update(userCard, amountTransfer + targetBalance);

                                            // Subtract amount from the actual account

                                            dbConnection.update(userAccount, balance - amountTransfer);

                                            System.out.println("Success!\n");

                                            break;

                                        }
                                    }
                                    break;

                                case 4:

                                    // Close account

                                    dbConnection.deleteAccount(userAccount);

                                    System.out.println("The account has been closed!\n");

                                    break;

                                case 5:

                                    System.out.println("\nYou have successfully logged out!\n");

                                    loggedIn = false;

                                    break;

                                case 0:

                                    System.out.println("\nBye!");

                                    scanner.close();

                                    System.exit(0);

                            }

                        }

                    } else {

                        System.out.println("Wrong card number or PIN!\n");
                    }

                    break;

                case 0:

                    System.out.println("\nBye!");

                    scanner.close();

                    System.exit(0);

            }
        }

    }

    public static void generalMenu() {

        System.out.println("""
                1. Create an account
                2. Log into account
                0. Exit
                """);
    }

    public static void accountMenu() {

        System.out.println("""
                1. Balance
                2. Add income
                3. Do transfer
                4. Close account
                5. Log out
                0. Exit
                """);
    }

}

