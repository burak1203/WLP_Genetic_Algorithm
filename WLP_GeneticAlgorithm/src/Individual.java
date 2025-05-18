import java.util.*;

public class Individual {
    public int[] assignment; // customer -> depot
    public double fitness;

    public Individual(int customerCount, int depotCount, ProblemLoader problem) {
        assignment = new int[customerCount];
        Random rand = new Random();

        // Random valid assignment
        for (int i = 0; i < customerCount; i++) {
            assignment[i] = rand.nextInt(depotCount);
        }

        evaluateFitness(problem);
    }

    public Individual(int[] assignment) {
        this.assignment = assignment.clone();
    }

    public void evaluateFitness(ProblemLoader problem) {
        double totalCost = 0.0;
        int[] usedCapacities = new int[problem.depotCount];
        boolean[] opened = new boolean[problem.depotCount];

        for (int i = 0; i < assignment.length; i++) {
            int depot = assignment[i];
            usedCapacities[depot] += problem.customerDemands[i];
            totalCost += problem.travelCosts[i][depot];
            opened[depot] = true;
        }

        for (int i = 0; i < problem.depotCount; i++) {
            if (usedCapacities[i] > problem.depotCapacities[i]) {
                totalCost += 100000; // Penalty
            }
            if (opened[i])
                totalCost += problem.depotCosts[i];
        }

        this.fitness = totalCost;
    }

    public Individual crossover(Individual other, ProblemLoader problem) {
        Random rand = new Random();
        int[] childAssignment = new int[assignment.length];
        for (int i = 0; i < assignment.length; i++) {
            childAssignment[i] = rand.nextBoolean() ? this.assignment[i] : other.assignment[i];
        }
        Individual child = new Individual(childAssignment);
        child.evaluateFitness(problem);
        return child;
    }

    public void mutate(double mutationRate, ProblemLoader problem) {
        Random rand = new Random();
        for (int i = 0; i < assignment.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                assignment[i] = rand.nextInt(problem.depotCount);
            }
        }
        evaluateFitness(problem);
    }
}
