import java.util.*;

public class GeneticAlgorithm {
    private ProblemLoader problem;
    private int populationSize = 100;
    private int generations = 200;
    private double mutationRate = 0.1;

    public GeneticAlgorithm(ProblemLoader problem) {
        this.problem = problem;
    }

    public Solution run() {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Individual(problem.customerCount, problem.depotCount, problem));
        }

        for (int gen = 0; gen < generations; gen++) {
            population.sort(Comparator.comparingDouble(ind -> ind.fitness));

            List<Individual> newPop = new ArrayList<>();
            for (int i = 0; i < populationSize / 2; i++) {
                Individual parent1 = population.get(i);
                Individual parent2 = population.get(i + 1);
                Individual child = parent1.crossover(parent2, problem);
                child.mutate(mutationRate, problem);
                newPop.add(child);
            }

            population.addAll(newPop);
            population.sort(Comparator.comparingDouble(ind -> ind.fitness));
            population = population.subList(0, populationSize);
        }

        Individual best = population.get(0);
        return new Solution(best, problem);
    }
}
