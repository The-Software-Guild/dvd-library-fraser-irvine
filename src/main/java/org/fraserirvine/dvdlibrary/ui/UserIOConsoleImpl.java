package org.fraserirvine.dvdlibrary.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        //display the prompt
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public String readStringAllowEmpty(String prompt) {
        //display the prompt
        System.out.println(prompt);
        //replace empty string with "N/A"
        String inputString = scanner.nextLine();
        if (inputString.equals("")) {
            inputString = "N/A";
        }
        return inputString;
    }

    public String readStringAllowEmpty(String prompt, String substitute) {
        //display the prompt
        System.out.println(prompt);
        String inputString = scanner.nextLine();
        if (inputString.equals("")) {
            inputString = substitute;
        }
        return inputString;
    }

    @Override
    public int readInt(String prompt) {
        //display the prompt
        System.out.println(prompt);
        //get user input int with validation
        while (true) {
            if (scanner.hasNextInt()) {
                return Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Error, value entered is not an Integer");
                scanner.next();
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        //display the prompt
        System.out.println(prompt + " " + min + " - " + max);
        //get user input int with validation
        int outputInt = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                outputInt = Integer.parseInt(scanner.nextLine());
                if (outputInt < min || outputInt > max) {
                    System.out.println("Error, value entered is out of bounds: " + min + " - " + max);
                } else {
                    return outputInt;
                }
            } else {
                System.out.println("Error, value entered is not an Integer");
                scanner.next();
            }
        }
    }

    @Override
    public double readDouble(String prompt) {
        //display the prompt
        System.out.println(prompt);
        //get user input int with validation
        while (true) {
            if (scanner.hasNextDouble()) {
                return Double.parseDouble(scanner.nextLine());
            } else {
                System.out.println("Error, value entered is not a Double");
                scanner.next();
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        //display the prompt
        System.out.println(prompt + " " + min + " - " + max);
        //get user input int with validation
        double outputDouble = 0;
        while (true) {
            if (scanner.hasNextDouble()) {
                outputDouble = Double.parseDouble(scanner.nextLine());
                if (outputDouble < min || outputDouble > max) {
                    System.out.println("Error, value entered is out of bounds: " + min + " - " + max);
                } else {
                    return outputDouble;
                }
            } else {
                System.out.println("Error, value entered is not a Double");
                scanner.next();
            }
        }
    }

    @Override
    public float readFloat(String prompt) {
        //display the prompt
        System.out.println(prompt);
        //get user input int with validation
        while (true) {
            if (scanner.hasNextFloat()) {
                return Float.parseFloat(scanner.nextLine());
            } else {
                System.out.println("Error, value entered is not a Float");
                scanner.next();
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        //display the prompt
        System.out.println(prompt + " " + min + " - " + max);
        //get user input int with validation
        float outputFloat = 0;
        while (true) {
            if (scanner.hasNextFloat()) {
                outputFloat = Float.parseFloat(scanner.nextLine());
                if (outputFloat < min || outputFloat > max) {
                    System.out.println("Error, value entered is out of bounds: " + min + " - " + max);
                } else {
                    return outputFloat;
                }
            } else {
                System.out.println("Error, value entered is not a Float");
                scanner.next();
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        //display the prompt
        System.out.println(prompt);
        //get user input int with validation
        while (true) {
            if (scanner.hasNextLong()) {
                return Long.parseLong(scanner.nextLine());
            } else {
                System.out.println("Error, value entered is not a Long");
                scanner.next();
            }
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        //display the prompt
        System.out.println(prompt + " " + min + " - " + max);
        //get user input int with validation
        long outputLong = 0;
        while (true) {
            if (scanner.hasNextLong()) {
                outputLong = Long.parseLong(scanner.nextLine());
                if (outputLong < min || outputLong > max) {
                    System.out.println("Error, value entered is out of bounds: " + min + " - " + max);
                } else {
                    return outputLong;
                }
            } else {
                System.out.println("Error, value entered is not a Long");
                scanner.next();
            }
        }
    }

    @Override
    public LocalDate readDate(String prompt) {
        //display the prompt
        System.out.println(prompt);
        LocalDate outputLd;
        //input validation for insesrting date in ISO format
        while (true) {
            String dateString = scanner.nextLine();
            try {
                outputLd = LocalDate.parse(dateString);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("The inserted date must be in ISO Format: YYYY-MM-DD, try again.");
            }
        }
        return outputLd;
    }

}
