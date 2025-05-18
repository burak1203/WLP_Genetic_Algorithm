import java.io.*;
import java.util.*;

public class ProblemLoader {
    public int depotCount;
    public int customerCount;
    public int[] depotCapacities;
    public double[] depotCosts;
    public int[] customerDemands;
    public double[][] travelCosts;

    public ProblemLoader(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // İlk satırı oku ve sayıları ayır
            String[] firstLine = br.readLine().trim().split(" ");
            depotCount = Integer.parseInt(firstLine[0]);
            customerCount = Integer.parseInt(firstLine[1]);

            // Depo kapasiteleri ve maliyetleri
            depotCapacities = new int[depotCount];
            depotCosts = new double[depotCount];

            for (int i = 0; i < depotCount; i++) {
                String[] line = br.readLine().trim().split(" ");
                depotCapacities[i] = Integer.parseInt(line[0]);
                depotCosts[i] = Double.parseDouble(line[1]);
            }

            // Müşteri talepleri ve seyahat maliyetleri
            customerDemands = new int[customerCount];
            travelCosts = new double[customerCount][depotCount];

            for (int i = 0; i < customerCount; i++) {
                customerDemands[i] = Integer.parseInt(br.readLine().trim());
                String[] costs = br.readLine().trim().split(" ");
                for (int j = 0; j < depotCount; j++) {
                    travelCosts[i][j] = Double.parseDouble(costs[j]);
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
