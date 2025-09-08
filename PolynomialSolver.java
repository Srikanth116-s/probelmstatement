import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class ShamirSecret {
    public static void main(String[] args) throws Exception {
        JSONArray testcases = (JSONArray) new JSONParser().parse(new FileReader("data.json"));

        int caseNo = 1;
        for (Object obj : testcases) {
            JSONObject j = (JSONObject) obj;
            JSONObject keys = (JSONObject) j.get("keys");
            int k = ((Number) keys.get("k")).intValue();

            JSONArray data = (JSONArray) j.get("data");

            double[] x = new double[k];
            double[] y = new double[k];
            for (int i = 0; i < k; i++) {
                JSONObject point = (JSONObject) data.get(i);
                x[i] = ((Number) point.get("base")).doubleValue();
                y[i] = ((Number) point.get("value")).doubleValue();
            }

            double secret = lagrangeInterpolation(0, x, y);
            System.out.println("Case " + caseNo + " → Reconstructed Secret = " + Math.round(secret));
            caseNo++;
        }
    }

    private static double lagrangeInterpolation(double at, double[] x, double[] y) {
        double result = 0.0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            double term = y[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (at - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }

        return result;
    }
}
