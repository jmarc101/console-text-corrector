/*
Jean-Marc Prud'homme (20137035) p1209866
Vendredi, 20 Mars 2020

J'ai essayé de garder cette classe la plus petite possible pour respecter
les conventions d'orienter objets. Correcteur est la classe qui fait appel
a corrigé qui corrige le texte en utilisant le dictionnaire de référence.
En soi-même, Correcteur est simplement la classe qui crée les objets et
les mets en ligne de console.
*/

public class Correcteur {

    public static void main(String[] args) {
         
        if (args.length != 2) {
            
            System.out.println("Attention: 2 arguments sont attendus");
            System.out.println(" 1e: Texte a Corriger -- 2e: Dictionnaire");
            System.exit(-1);
        }

        Corriger corriger = new Corriger(args[0], args[1]);

        System.out.println(corriger.getTexteCorriger());

    }
}
