import java.util.Scanner;
import java.util.Set;

public class MainApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen, Herr Keller, beim Lottotippreihengenerator-App!");

        Set<Integer> unluckyNumbers = UnluckyNumbersManager.readNumbersFromFile();

        System.out.println("Ihre aktuelle Unglückszahlen: " + unluckyNumbers);

        UnluckyNumbersManager.setNewUnluckyNumbers(unluckyNumbers, scanner);
    }

}
