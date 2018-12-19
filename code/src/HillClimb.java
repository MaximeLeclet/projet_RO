import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HillClimb {

    private static final int _NBITER = 216;
    private static final int _NBETATS = 5;
    private static final int _SIZEMAX = 20;

    public Solution firstImprovement(int maxOpti, Automata automata) {

        Initialization initialisation = new Initialization();
        int[] rules = new int[_NBITER];
        initialisation.init(rules);

        for (int j = 0; j < _NBITER; j++) {
            System.out.println("rules" + j + rules[j]);
        }

        int fitness = automata.f(rules, _SIZEMAX);

        System.out.println("Fitness : " + fitness);

        Solution solution = new Solution(rules);

        boolean better = false;
        int i = 0;

        //Boucle de recherche de l'optimum local
        //Boucle recherche premier meilleur voisin
        while (better != true && i < maxOpti) {
            // --- Creation d'un nouveau voisin du current set
            //Copie du current
            int[] rulesTest = new int[_NBITER];
            for (int tmpi = 0; tmpi < _NBITER; tmpi++) {
                rulesTest[tmpi] = rules[tmpi];
            }
            //Altération d'1 rule
            Random r = new Random();
            int rank_rule_tochange = (int) (Math.random() * 88);
            int value_rule_tochange = (int) (Math.random() * 4);
            //get tableau 88 [rank_rule_tochange]
            int redirected_rank_rule_tochange = initialisation.tabIndice[rank_rule_tochange];
            initialisation.setRule(rulesTest, redirected_rank_rule_tochange / 36, (redirected_rank_rule_tochange / 6) % 6, redirected_rank_rule_tochange % 6, value_rule_tochange);

            int fitnessTest = automata.f(rulesTest, _SIZEMAX);

            if (fitnessTest >= fitness) {

                solution.setRegles(rulesTest);
                solution.setFitness(fitnessTest);

                //better = true;

            }
            i++;
        }

        return solution;

    }

    boolean[] randomBools(int len) {
        boolean[] arr = new boolean[len];
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = r.nextBoolean();
        }
        return arr;
    }

}