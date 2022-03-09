package CeasarCipher;

import java.util.HashMap;

public class CeaserCipher {
    HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>(26);
    int key = 0;

    public CeaserCipher(int key) {
        this.key = key;

        for (int i = 'a'; i <= 'z'; i++) {
            alphabet.put((char) i, (int) i - 'a');
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

            // HashMap doesn't have a getValue()??!
            for (HashMap.Entry<Character, Integer> entry : alphabet.entrySet())
                if (entry.getValue() == newIndex)
                    c = entry.getKey();

            if (Character.isUpperCase(_c)) {
                c = Character.toUpperCase(c);
            }

            return c;

        }

        else if (Character.isDigit(c)) {
            System.out.println(c);
            int digit = Integer.parseInt(("" + c)) - key;
            System.out.println(digit);

            if (digit > 9) { digit %= 9; }
            if (digit < 0) { digit = 9 + digit; }

            System.out.println(digit);
            System.out.println("\n");

            c = ("" + digit).charAt(0);
        }

        return c;
    }

    public String bruteforce(String message) {
      return "";
    }

}
