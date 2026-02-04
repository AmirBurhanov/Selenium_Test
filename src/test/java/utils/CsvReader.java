package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static String[] readCredentials(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length >= 2) {
                    return new String[] {
                            values[0].trim(),
                            values[1].trim()
                    };
                }
            }
        }

        throw new IOException("No credentials found in " + filePath);
    }
    public static String getUsername() {
        try {
            return readCredentials("/Users/macbook/sc21/myProject/Test_UI_Selenium/src/test/resources/test-data/users.csv")[0];
        } catch (IOException e) {
            throw new RuntimeException("Cannot read username", e);
        }
    }

    public static String getPassword() {
        try {
            return readCredentials("/Users/macbook/sc21/myProject/Test_UI_Selenium/src/test/resources/test-data/users.csv")[1];
        } catch (IOException e) {
            throw new RuntimeException("Cannot read password", e);
        }
    }
}