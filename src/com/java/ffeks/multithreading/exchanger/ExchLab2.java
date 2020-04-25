package com.java.ffeks.multithreading.exchanger;

import com.java.ffeks.exception.EmptyRowException;
import com.java.ffeks.exception.InvalidIpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import static com.java.ffeks.util.UtilityClass.checkIpExistence;

public class ExchLab2 {

    /*
        ^		   matches the beginning of the line
       \d	       matches a digit (equal to [0-9])
       {1,3}      {1,3} Quantifier — Matches between 1 and 3 times, as many times as possible, giving back as needed (greedy)
       \.         matches the character . literally (case sensitive)
       $		\ matches the end of the line
   */
    private static final String IP_EXP = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";

    public static void multithreadingMain() throws IOException {
        ExchLab2.Validator validator = new ExchLab2.Validator();
        validator.validate();
    }

    static class Validator {

        boolean validate() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter IP:");
            String ip = input.readLine();

            try {
                checkIpExistence(ip);
            } catch (EmptyRowException exc) {
                System.err.println("Invalid IP. Try again:");
                return validate();
            }

            try {
                isIpMatches(ip);
            } catch (InvalidIpException exc) {
                System.err.printf("IP '%s' is not valid.", ip);
                System.out.println();
                return validate();
            }
            System.out.printf("IP '%s' is valid.", ip);
            System.out.println();

            return true;
        }

        public void isIpMatches(String ip) {
            boolean matches = Pattern.matches(IP_EXP, ip);
            if (!matches) {
                System.err.printf("IP '%s' is not valid.", ip);
            }
        }
    }
}
