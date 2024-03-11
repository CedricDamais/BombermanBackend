package fr.epita.assistants.jws.utils;

import java.util.ArrayList;
import java.util.List;

public class EncodeMap {
    public static String encodeLine(String line) {
        StringBuilder encodedLine = new StringBuilder();
        char prevChar = line.charAt(0);
        int count = 1;
        for (int i = 1; i < line.length(); i++) {
            char currChar = line.charAt(i);
            if (currChar == prevChar) {
                count++;
            } else {
                encodedLine.append(count).append(prevChar);
                prevChar = currChar;
                count = 1;
            }
        }
        // Append the last sequence
        encodedLine.append(count).append(prevChar);
        return encodedLine.toString();
    }


}
