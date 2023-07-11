package com.deloitte.ads;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        Users users = new Users(new HashSet<>());
        Set<User> sentTo = new HashSet<>();
        User mainUser = new User();
        int numerate;
        boolean registrated = false;

        do {

            if(!registrated) {

                while (true) {
                    String email;
                    String firstName;
                    String lastName;

                    System.out.println("---REGISTRATION---");

                    System.out.println("First name:");
                    firstName = scanner.nextLine();

                    System.out.println("Last name:");
                    lastName = scanner.nextLine();

                    System.out.println("Email:");
                    email = scanner.nextLine();

                    try {
                        mainUser = new User(email, firstName, lastName);
                    } catch (Exception e) {
                        System.out.println(e);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        continue;
                    }
                    break;

                }
            }

            System.out.println("Press number to do one of the following actions:");
            System.out.println("1. Add user");
            System.out.println("2. Send marios");
            System.out.println("3. Display recieved mariosy");
            System.out.println("4. Display sent mariosy");
            System.out.println("5. Exit");
            input = scanner.next();

            switch (input){
                case "1":
                    while(true){
                        String email;
                        String firstName;
                        String lastName;

                        System.out.println("---NEW USER---");

                        System.out.println("First name:");
                        firstName = scanner.nextLine();

                        System.out.println("Last name:");
                        lastName = scanner.nextLine();

                        System.out.println("Email:");
                        email = scanner.nextLine();

                        try{
                            User newUser = new User(email, firstName, lastName);
                            users.addUser(newUser);

                        }
                        catch(Exception e){
                            System.out.println(e);
                            try{
                                TimeUnit.SECONDS.sleep(1);}
                            catch (Exception ex){
                                System.out.println(ex);
                            }
                            continue;
                        }

                        registrated = true;
                        break;

                    }
                    break;

                case "2":
                    System.out.println("Comment:");
                    String comment = scanner.nextLine();
                    Marios marios = new Marios(Marios.TypeEnum.HAPPY, comment, mainUser, sentTo);
                    mainUser.addSentMarios(marios);
                    sentTo.stream().forEach(user -> user.addReceivedMarios(marios));
                    break;

                case "3":
                    mainUser.displayReceivedMariosy();
                    break;

                case "4":
                    mainUser.displaySentMariosy();
                    break;

                case "5":
                    break;

                default:
                    System.out.println("Wrong input!");
            }

        } while(!input.equals("5"));


    }
}
