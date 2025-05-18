import java.io.*;

public class Solution {
    private Individual best;
    private ProblemLoader problem;

    public Solution(Individual best, ProblemLoader problem) {
        this.best = best;
        this.problem = problem;
    }

    public double getFitness() {
        return best.fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : best.assignment) {
            sb.append(i).append(" ");
        }
        return sb.toString().trim();
    }

    public void printSolution() {
        System.out.printf("Optimal Cost: %.3f\n", best.fitness);
        for (int i : best.assignment) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void saveToFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(String.format("%.3f\n", best.fitness));
            for (int i = 0; i < best.assignment.length; i++) {
                writer.write(best.assignment[i] + (i < best.assignment.length - 1 ? " " : ""));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }
}
