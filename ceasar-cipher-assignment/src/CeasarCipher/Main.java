package CeasarCipher;

public class Main {
    public static void main(String[] args) {
        CeaserCipher cipher = new CeaserCipher(4);
        String message = "ceasarean sipher 720";
        String encryptedMessage = cipher.encrypt(message);
        String decryptedMessage = cipher.decrypt(encryptedMessage);

        System.out.println(message);
        System.out.println(encryptedMessage);
        System.out.println(decryptedMessage);
    }
}
