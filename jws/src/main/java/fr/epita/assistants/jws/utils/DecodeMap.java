package fr.epita.assistants.jws.utils;

import java.util.ArrayList;
import java.util.List;

public class DecodeMap {
    public String map;

    public static String decodeLine(String encodedLine) {
        StringBuilder decodedLine = new StringBuilder();
        for (int i = 0; i < encodedLine.length(); i += 2) {
            // Assuming the count is a single digit
            int count = Character.getNumericValue(encodedLine.charAt(i));
            char blockType = encodedLine.charAt(i + 1);
            for (int j = 0; j < count; j++) {
                decodedLine.append(blockType);
            }
        }
        return decodedLine.toString();
    }
}
