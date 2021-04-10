import java.io.*;
import java.util.*;

/*
Jean-Marc Prud'homme (20137035) p1209866
Vendredi, 20 Mars 2020

J'ai cree un objet dictionnaire car je crois que nous pourrions vouloir
acceder qu'aux dictionnaire et nous ne devrions pas avoir a passer par le
correcteur pour y avoir acces.
*/

public class Dictionnaire{

// Dictionnaire avec les mots de la langue francaise
// Plus les mots troncquer qui font reference au mots du dictionnaire
private Set<String> dictReference = new HashSet<>();
private Map<String, Set<String>> dictAssociation = new HashMap<>();


    //constructeur prends le dictionnaire en argument
    // et retourne un HashSet de ses mots et un dictionnaire de 
    //reference avec des mots troncquer qui peuvent etre le mots correct
    public Dictionnaire(String dict){



        //Lecture ligne par ligne du dictionnaire
        try {
            FileReader fileReader = new FileReader(dict);
            BufferedReader br = new BufferedReader(fileReader);

            String ligne;

            //rajoute la ligne au HashSet du dictionnaire
            while((ligne = br.readLine()) != null) {
                dictReference.add(ligne);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
                
        //Iteration a travers du dictoinnaire
        for ( String mot : dictReference) {

            //Pour chaque lettre du mots on cree
            // une variation avec une lettre en moins
            for (int i = 0; i < mot.length(); i++) {
                String mots = "";
                
                //si (j==i) on l'enleve du mots
                for (int j = 0; j < mot.length(); j++) {
                    if (j==i){
                        continue;
                    }
                    mots += mot.charAt(j);
                }

                // si mots complete n'existe pas et ne contient
                // pas le mot truncquer on cree le HashMap
                if (!dictAssociation.containsKey(mots)){

                    Set<String> listeAss = new HashSet<String>();
                    dictAssociation.put(mots, listeAss);
                }

                //On ajoute au set le mots complete.
                dictAssociation.get(mots).add(mot); 
            }
        }
    }

    //les Getters
    public Set<String> getDictReference(){
        return this.dictReference;
    }

    public Map<String, Set<String>> getDictAssociation(){
        return this.dictAssociation;
    }
}