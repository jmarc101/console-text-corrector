import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


/*
Jean-Marc Prud'homme (20137035) p1209866
Vendredi, 20 Mars 2020

J'ai divisé en un objet le corriger comme cela si nous le voulions nous
pourrions corriger plusieurs textes et avoir leurs textes, original, et
leurs corrections a notre disposition
*/

public class Corriger {
    private Dictionnaire dictionnaire;
    private Set<String> dictReference;
    private Map<String, Set<String>> dictAssociation;
    private String texteCorriger = "";
    private String texteOriginal;

    //Constructeur qui prends la dictionnaire utiliser et le mets en variables
    public Corriger(String texte, String dict){

        dictionnaire = new Dictionnaire(dict);
        dictReference = dictionnaire.getDictReference();
        dictAssociation = dictionnaire.getDictAssociation();
        this.texteOriginal = texte;



        Pattern patternMot = Pattern.compile("[a-zA-Z0-9\\u00C0-\\u017F]+");
        Pattern patternSeparateur = Pattern.compile("[^a-zA-Z\\u00C0-\\u017F]+");

        try {

            // Passe a travers le txt et corrige chaque mots 1 par 1 et cree
            // le resultat final en meme temps.
            FileReader fileReader = new FileReader(texte);
            Scanner s = new Scanner(fileReader);
            s.useDelimiter("\\b");

            while (s.hasNext(patternMot)){
                // Lire un mot
                String mot = s.next(patternMot);

                // si le mots est sans faute on le retranscrit immediatement
                if (dictReference.contains(mot.toLowerCase().toString()) || dictReference.contains(mot)){
                    texteCorriger += mot;
                }

                //sinon on montre les options possible et retranscrit
                else{
                    texteCorriger += corrigerMots(mot);
                }

                // Protege si le fichier.txt ne fini pas avec separateur
                if (s.hasNext(patternSeparateur)){
                 //ajoute les separateur directement
                 String separateur = s.next(patternSeparateur);

                 texteCorriger += separateur;
                }

                 

             
            }

            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public  String corrigerMots(String mot){
        String correction = "[" + mot +" => "; 
        Set<String> corPos = new HashSet<>();

        //si le mots erronne appartient dela a la liste associative
        // on mets sa correction dans la liste
        if (dictAssociation.get(mot) != null){
            corPos.addAll(dictAssociation.get(mot));
        }


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

            // si le mots fait parti dict Association
            if (dictAssociation.get(mots) != null){
                corPos.addAll(dictAssociation.get(mots));
            }

            //si le mot appartient au dictionnaire
            if (dictReference.contains(mots)){
                corPos.add(mots);
            }
            
        }

        // si aucune correction c'est un mot weird
        if (corPos.size() == 0){
            correction += "?]";
            return correction;
        }

        // on ajoute toutes les possibilite
        for (String e : corPos){
            correction += e + ",";
        }

        //on slice le string pour enlever le dernier , et mets ] au lieu
        return correction.substring(0, correction.length() - 1) + "]";
        
    }

    // les getters
    public String getTexteCorriger(){
        return this.texteCorriger;
    }
    
    public String getTextOriginal(){
        return this.texteOriginal;
    }

}