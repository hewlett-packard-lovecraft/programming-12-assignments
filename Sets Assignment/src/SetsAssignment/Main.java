package SetsAssignment;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Main {
    public static String rstrip(String _line) {
        String line = _line;

        line.toLowerCase(Locale.ROOT);
        line = line.replaceAll("[^a-z0-9 ]", ""); // catch all space/nonalphanumeric characters
        return line;
    }

    public static void main(String[] args) {
        File illiad = new File("src/resources/illiad");

        try {
            BufferedReader br = new BufferedReader(new FileReader(illiad.getAbsolutePath()));
            HashSet<String> uniqueWords = new HashSet<>();
            String line = "";

            while ((line = br.readLine()) != null) {
                line = rstrip(line);
                uniqueWords.addAll(List.of(line.split(" ")));
            }

            System.out.println(uniqueWords.size());

        } catch (IOException e) {
            System.err.println("File Not found!");
        }

    }
}
