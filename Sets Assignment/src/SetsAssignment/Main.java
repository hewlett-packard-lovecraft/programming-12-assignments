package SetsAssignment;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void solution1(File illiad) {
        try (Scanner scanner = new Scanner(illiad)){
            HashSet<String> uniqueWords = new HashSet<>(0);

            while (scanner.hasNext()) {
                String line = scanner.nextLine().toLowerCase(Locale.ROOT);
                line = line.replaceAll("[^a-zA-Z0-9\\n ]", " ");
                System.out.println(line);
                uniqueWords.addAll(List.of(line.strip().split(" ")));

            }

            System.out.println(uniqueWords);
            System.out.println(uniqueWords.size());

        } catch (IOException e) {
            System.err.println("File Not found!");
        }
    }

    public static void solution2(File illiad) {
        try (Scanner scanner = new Scanner(illiad)){
            HashSet<String> words = new HashSet<>();

            scanner.findAll("\\w+")
                   .forEach(word -> words.add(word.group(0).toLowerCase(Locale.ROOT)));

            System.out.println(words);
            System.out.println(words.size());

        } catch (IOException e) {
            System.err.println("File Not found!");
        }
    }
    public static void main(String[] args) {
        File illiad = new File("src/resources/illiad");

        solution1(illiad);
        solution2(illiad);

    }
}
