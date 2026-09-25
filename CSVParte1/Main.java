import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        CSVLoader loader1 = new CSVLoader("AlgebraRelazione1/src/Persone.csv");
        CSVLoader loader2 = new CSVLoader("AlgebraRelazione1/src/Persone2.csv");

        Relation r1 = loader1.loadCSVinRelation();
        Relation r2= loader2.loadCSVinRelation();

        System.out.println("Prima operazione: sleezione");
        System.out.println(r1.selection("nome","Piero"));

        System.out.println("Seconda operazione: Proiezione");

        ArrayList<String> k = new ArrayList<>();
        k.add("nome");
        k.add("cognome");

        System.out.println(r2.projection(k));

        System.out.println("Terza operazione: union");
        Relation ru = r1.union(r2);
        System.out.println(ru);

        System.out.println("Quarta operazione: differenza");
        System.out.println(ru.difference(r1));
    }
}