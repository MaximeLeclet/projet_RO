/**
 * 
 */

import java.util.Random;

/**
 * @author verel
 *
 */
public class Initialization {

	int nbRules = 216;
	
	Random generator;
	public int [] tabIndice;
	public Initialization() {
		generator = new Random();

		tabIndice = new int[88];
		int i = 0;
		int k = 0;

		for (int a = 0; a < 4; a++){
			for (int b = 0; b < 4; b++){
				for (int c = 0; c < 4; c++){
					if((a != 0 && b != 0 && c != 0) || (a != 1 && b != 1 && c != 1)){
						//setRule(rules, a, b, c, generator.nextInt(4));
						tabIndice[k] = 36 * a + 6 * b + c; //generator.nextInt(4);
						k += 1;
					}
				}
			}
		}

		int bord = 5;

		for (int d = 0; d < 4; d++){
			for (int e = 0; e < 4; e++){
				if((d != 0 && e != 0) || (d != 1 && e != 1) || (d != 1 && e != 0)){
					//setRule(rules, bord, d, e, generator.nextInt(4));
					//tabIndice[36 * bord + 6 * d + e] = generator.nextInt(4);
					tabIndice[k] = 36 * bord + 6 * d + e; //generator.nextInt(4);
					k += 1;
				}
			}
		}

		for (int f = 0; f < 4; f++){
			for (int g = 0; g < 4; g++){
				if((f != 0 && g != 0) || (f != 1 && g != 1) || (f != 1 && g != 0)){
					//setRule(rules, f, g, bord, generator.nextInt(4));
					//tabIndice[36 * f + 6 * g + bord] = generator.nextInt(4);
					tabIndice[k] = 36 * f + 6 * g + bord; //generator.nextInt(4);
					k += 1;
				}
			}
		}

		
	}
	
	public void init(int [] rules) {
//		for(int i = 0; i < nbRules; i++)
//			rules[i] = generator.nextInt(4);

		for(int i = 0; i < tabIndice.length; i++){
			rules[tabIndice[i]] = generator.nextInt(4);
		}

		// les regles repos (obligatoires)
		setRule(rules, 0, 0, 0, 0);
		setRule(rules, 5, 0, 0, 0);
		setRule(rules, 0, 0, 5, 0);

		// les regles feu (trés conseillés)
		setRule(rules, 1, 1, 1, 4);
		setRule(rules, 5, 1, 1, 4);
		setRule(rules, 1, 1, 5, 4);

		// a priori bord droit (pour la taille 2)
		setRule(rules, 1, 0, 5, 1);

		// a priori bord gauche (pour la taille 2)
		setRule(rules, 5, 1, 0, 1);

	}

    /*
     * Ecrit la regle 
     *
     * g : etat de la cellule de gauche
     * c : etat de la cellule centrale
     * d : etat de la cellule de droite
     * r : etat de la cellule centale à t+1
     */
    public void setRule(int [] rules, int g, int c, int d, int r) {
	rules[g * 36 + c * 6 + d] = r;
    }
    /*
    public void isLegalRule(int index) {
    	for (int i = 0; i < tabIndice; ) {

		}
	}
	*/
}
