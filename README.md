# Banking System
This is my implementation of the [Simple Banking System project](https://hyperskill.org/projects/93?track=17) from the JetBrains Academy as part of the Java developer track.

This project was a good introduction to interaction with SQlite Database and applying basic OOP principles.

Generated card numbers and verification were made based on the [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm).

The program has 2 menus:

* Main Menu:

```java
    public static void generalMenu() {

        System.out.println("""
                1. Create an account
                2. Log into account
                0. Exit
                """);
    }
```


  This menu allows the user to generate a card Number (following the Luhn Algorithm) and a pin that will be stored in the database by entering 1.
  
  2 will prompt the user for the Card number and pin. The program will check if the submitted data much the stored number/pin in the database to allow the user in. 
  And the Account menu will appear. Otherwise, an error will be thrown.
  
  0 will exit and stop the program.
  
  * Account Menu:
  
  ```java
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
   ```
  
  This menu will allow the following operations:
  
  1 will show the actual balance
  
  2 Allow the user to add money to his account.
  
  3 This will allow the user to transfer money to any other account stored in the database. if the account does not exist or the amount to be transferred is higher
  than the actual balance, an error will be thrown. If the operation is successful, the money will be added to the target account and subtracted from the sender's account.
  
  4 This will delete the row of the open account from the database
  
  5 Will return to the main menu
  
  0 will exit and stop the program.


## Demo and ScreenShot: 


```diff
- Click the Gif for larger view -
```

<img src="https://github.com/nzayem/banking-system/blob/master/bank.gif" width="950" height="650">
