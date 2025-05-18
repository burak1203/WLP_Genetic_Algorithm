import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        String[] dataFiles = { "wl_25", "wl_50", "wl_200", "wl_300", "wl_500" };

        try (PrintWriter writer = new PrintWriter(new FileWriter("result.txt"))) {
            for (String file : dataFiles) {
                writer.println("=== " + file + " için sonuçlar ===");
                ProblemLoader loader = new ProblemLoader("data/" + file);
                GeneticAlgorithm ga = new GeneticAlgorithm(loader);
                Solution best = ga.run();

                // Sonuçları dosyaya yaz
                writer.println("En iyi çözüm:");
                writer.println("Fitness: " + best.getFitness());
                writer.println("Çözüm: " + best.toString());
                writer.println("\n");

                // Konsola da yazdır
                System.out.println("=== " + file + " için sonuçlar ===");
                best.printSolution();
            }
        } catch (IOException e) {
            System.err.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
}
