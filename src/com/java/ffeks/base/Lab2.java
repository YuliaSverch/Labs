package com.java.ffeks.base;

import com.java.ffeks.exception.EmptyRowException;
import com.java.ffeks.exception.InvalidIpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import static com.java.ffeks.util.UtilityClass.checkIpExistence;

/**
 * Скласти регулярне вираження, що визначає чи є заданий рядок IP адресою, записаним у десятковому виді.
 * Приклад правильних виражень: 127.0.0.1, 255.255.255.0.
 * Приклад неправильних виражень: 1300.6.7.8, abc.def.gha.bcd.
 */
public class Lab2 {

    /*
        ^		   matches the beginning of the line
       \d	       matches a digit (equal to [0-9])
       {1,3}      {1,3} Quantifier — Matches between 1 and 3 times, as many times as possible, giving back as needed (greedy)
       \.         matches the character . literally (case sensitive)
       $		\ matches the end of the line
   */
    private static final String IP_EXP = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";

    public static void main(String[] args) throws IOException {
        Validator validator = new Validator();
        validator.validate();
    }

    public static class Validator {

        public boolean validate() throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter IP:");
            String ip = input.readLine();

            try {
                checkIpExistence(ip);
            } catch (EmptyRowException exc) {
                System.err.println("IP can not be empty. Try again:");
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
                throw new InvalidIpException();
            }
        }
    }

    public static void multithreadingMain() throws IOException {
        Validator validator = new Validator();
        validator.validate();
    }
}
