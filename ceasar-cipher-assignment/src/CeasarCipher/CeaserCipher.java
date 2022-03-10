package CeasarCipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class CeaserCipher {
    HashMap<Character, Integer> alphabet = new HashMap<>(26);
    HashSet<String> words = new HashSet<>();
    int key;

    public CeaserCipher(int key) {
        this.key = key;

        for (int i = 'a'; i <= 'z'; i++) {
            alphabet.put((char) i, i - 'a');
        }

    }

    public String encrypt(String message) {
        char[] messageChars = message.toCharArray();

        for (int i = 0; i < messageChars.length; i++) {
            messageChars[i] = encode(messageChars[i]);
        }

        return String.valueOf(messageChars);
    }

    public char encode(char _c) {
        char c = _c;

        if (Character.isLetter(c)) {
            if (Character.isUpperCase(_c)) {
                c = Character.toLowerCase(_c);
            }

            int newIndex = alphabet.get(c) + key;

            if (newIndex > 25) { newIndex = newIndex % 25; }
            if (newIndex < 0) { newIndex = 25 + newIndex; }

            // HashMap doesn't have a getValue()??!
            for (HashMap.Entry<Character, Integer> entry : alphabet.entrySet())
                if (entry.getValue() == newIndex)
                    c = entry.getKey();

            if (Character.isUpperCase(_c)) {
                c = Character.toUpperCase(c);
            }

        }

        else if (Character.isDigit(c)) {
            int digit = Integer.parseInt(("" + c)) + key;
            if (digit > 9) { digit %= 9; }
            if (digit < 0) { digit += 9; }

            c = ("" + digit).charAt(0);
        }

        return c;
    }

    public String decrypt(String message) {
        char[] messageChars = message.toCharArray();

        for (int i = 0; i < messageChars.length; i++) {
            messageChars[i] = decode(messageChars[i]);
        }

        return String.valueOf(messageChars);
    }

    public char decode(char _c) {
        char c = _c;

        if (Character.isLetter(c)) {
            if (Character.isUpperCase(_c)) {
                c = Character.toLowerCase(_c);
            }

            int newIndex = alphabet.get(c) - key;

            if (newIndex > 25) { newIndex = newIndex % 25; }
            if (newIndex < 0) { newIndex = 25 + newIndex; }

            // HashMap doesn't have a getByValue()??!
            for (HashMap.Entry<Character, Integer> entry : alphabet.entrySet())
                if (entry.getValue() == newIndex)
                    c = entry.getKey();

            if (Character.isUpperCase(_c)) {
                c = Character.toUpperCase(c);
            }

            return c;

        }

        else if (Character.isDigit(c)) {
            int digit = Integer.parseInt(("" + c)) - key;

            if (digit > 9) { digit %= 9; }
            if (digit < 0) { digit += 9; }

            c = ("" + digit).charAt(0);
        }

        return c;
    }

    public void loadWordList() {
        File wordFile = new File("src/resources/words.txt");
        HashSet<String> words = new HashSet<>();

        try {
            words.addAll(Files.readAllLines(wordFile.toPath(), StandardCharsets.UTF_16));
        } catch (Exception e) {
            System.out.println(e);
        }

        this.words = words;
    }

    public String bruteforce(String message) {
        int defaultKey = key;
        String decryptedMessage = "";

        loadWordList();

        for (int key = 0; key < 26; key++) {
            this.key = key;
            String potentialMessage = decrypt(message);

            for (String word : potentialMessage.split(" ")) {
                if (words.contains(word)) {
                    return decryptedMessage;
                }
            }

        }

        key = defaultKey;

        return "Unable to bruteforce message";
    }

}
