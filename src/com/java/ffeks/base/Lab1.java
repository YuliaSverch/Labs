package com.java.ffeks.base;

import com.java.ffeks.exception.InvalidIndexException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


public class Lab1 {

    public static void main(String[] args) throws IOException {
        String replacedText = new Runner().getReplacedText();
        System.out.println(replacedText);
    }

    public static class Runner {

        public static final String TEXT_FILE = "text.txt";

        public String getReplacedText() throws IOException {
            String textFromFile = readFileAsString(TEXT_FILE);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the index to be replaced with:");
            int replacedCharacterIndex;

            try {
                replacedCharacterIndex = Integer.parseInt(reader.readLine());
                checkIndex(replacedCharacterIndex, textFromFile);
            } catch (NumberFormatException | InvalidIndexException exc) {
                System.err.println("Invalid index. Try again:");
                return getReplacedText();
            }

            System.out.println("Enter new character:");
            char newCharacter = reader.readLine().charAt(0);


            return replaceSymbolInText(textFromFile, replacedCharacterIndex, newCharacter);
        }

        private void checkIndex(int replacedCharacterIndex, String textFromFile) {
            char[] chars = textFromFile.toCharArray();

            if (replacedCharacterIndex > chars.length || replacedCharacterIndex <= 0) {
                throw new InvalidIndexException();
            }
        }

        private String replaceSymbolInText(String text,
                                           int replacedCharacterIndex,
                                           char newCharacter) {
            StringBuilder stringBuilder = new StringBuilder();
            if (Objects.nonNull(text)
                    && text.length() > 0
                    && replacedCharacterIndex > 0) {

                // if word length is is greater or equal than the replaced character index
                // then replace character by given new character
                // otherwise leave it without modifying
                for (String word : text.split(" ")) {
                    if (word.length() >= replacedCharacterIndex) {
                        stringBuilder
                                .append(word, 0, replacedCharacterIndex - 1)
                                .append(newCharacter)
                                .append(word.substring(replacedCharacterIndex));
                    } else {
                        stringBuilder.append(word);
                    }
                    stringBuilder.append(" ");
                }

            }
            return stringBuilder.toString();
        }

        public static String readFileAsString(String fileName) throws IOException {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        }

    }

    public static void multithreadingMain() throws IOException {
        String replacedText = new Runner().getReplacedText();
        System.out.println(replacedText);
    }

}