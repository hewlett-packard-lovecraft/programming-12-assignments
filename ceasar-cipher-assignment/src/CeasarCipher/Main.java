package CeasarCipher;

public class Main {
    public static void main(String[] args) {
        CeaserCipher cipher = new CeaserCipher(5);
        String message = "caesar cipher 720";
        String encryptedMessage = cipher.encrypt(message);
        String decryptedMessage = cipher.decrypt(encryptedMessage);

        System.out.println(message);
        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
        System.out.println(cipher.bruteforce(decryptedMessage));

    }
}
