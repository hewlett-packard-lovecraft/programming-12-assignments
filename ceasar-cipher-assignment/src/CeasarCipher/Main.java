package CeasarCipher;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int key = ThreadLocalRandom.current().nextInt(0, 25 + 1);
        String message = "Caesar cipher 720";

        CeaserCipher cipher = new CeaserCipher(key);
        String encryptedMessage = cipher.encrypt(message);
        String decryptedMessage = cipher.decrypt(encryptedMessage);

        System.out.println("key: " + key);
        System.out.println("message: " + message);
        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
        System.out.println("Potential bruteforce messages: " + Arrays.toString(cipher.bruteforce(encryptedMessage)));

    }
}
