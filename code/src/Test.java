import java.io.*;

public class Test {

  /**
  * @param args
  */
  public static void main(String[] args) {

    Automata automate = new Automata(35);

    // path to change
    String solFileName = "solution_19a";
    String path = "/var/www/html/Etudes/Master/Master1/RO/projet/code/";

    String bestName = path + solFileName + ".dat";
    int [] rules = initRulesFromFile(bestName);

    // Nombre maximale de fusiliers (taille maximale du réseau)
    int sizeMax = 35;

    int nFire;

    int fit = automate.f(rules, sizeMax);

    for(int i = 2; i <= sizeMax; i++) {
      // évolution de l'automate avec la règle rule sur un réseau de longueur i
      nFire = automate.evol(rules, i);

      // affiche la dynamique dans un fichier au format svg
      automate.exportSVG(i, 2 * i - 2, path + "svg/" + solFileName + "_" + i + ".svg");
    }

    String outName = path + "out.dat";

    Initialization init = new Initialization();

    PrintWriter ecrivain;
    try {
      ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(outName)));

      for(int i = 0; i < 1000; i++) {
        init.init(rules);

        fit = automate.f(rules, 19);

        printToFile(fit, rules, ecrivain);
      }

      ecrivain.close();
    }
    catch (Exception e){
      System.out.println(e.toString());
    }

    HillClimb hc = new HillClimb();
    Solution s = new Solution();

    s = hc.firstImprovement(10000000, automate);
    System.out.println(s.toString());

  }

  public static void printToFile(int fitness, int [] rules, PrintWriter ecrivain) {
    ecrivain.print(fitness);
    for(int i = 0; i < 216; i++) {
      ecrivain.print(" ");
      ecrivain.print(rules[i]);
    }
    ecrivain.println();
  }

  public static int [] initRulesFromFile(String fileName) {
    // 5 états + l'état "bord"
    int n = 5 + 1;

    int [] rules = new int[n * n * n];

    try {
      FileReader fichier = new FileReader(fileName);

      StreamTokenizer entree = new StreamTokenizer(fichier);

      int i = 0;
      while(entree.nextToken() == StreamTokenizer.TT_NUMBER)
      {
        rules[i] = (int) entree.nval;
        i++;
      }
      fichier.close();
    }
    catch (Exception e){
      System.out.println(e.toString());
    }

    return rules;
  }

}
