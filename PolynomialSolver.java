import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PolynomialSolver {
    public static void main(String[] args) throws Exception {
        JSONObject j = (JSONObject) new JSONParser().parse(new FileReader("data.json"));
        int A = decode(j.get("A"));
        int B = decode(j.get("B"));
        System.out.println("Decoded A=" + A + ", B=" + B);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first root: ");
        int r1 = sc.nextInt();
        System.out.print("Enter second root: ");
        int r2 = sc.nextInt();
        int C = A * (r1 * r2);
        System.out.println("Polynomial: " + A + "x^2 + " + B + "x + " + C + " = 0");
        System.out.println("COnstant C = " + C);
        sc.close();
    }

    private static int decode(Object val) {
        if (val instanceof Number)
            return ((Number) val).intValue();
        String s = val.toString().trim().toLowerCase();
        Map<String, Integer> words = new HashMap<>();
        words.put("zero", 0);
        words.put("one", 1);
        words.put("two", 2);
        words.put("three", 3);
        words.put("four", 4);
        words.put("five", 5);
        words.put("six", 6);
        words.put("seven", 7);
        words.put("eight", 8);
        words.put("nine", 9);
        if (words.containsKey(s))
            return words.get(s);
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
        }
        try {
            return Integer.decode(s);
        } catch (Exception e) {
        }
        throw new IllegalArgumentException("Unsupported format: " + val);
    }
}