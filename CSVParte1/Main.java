
import java.util.ArrayList;




public class Main {
    public static void main(String[] args){
        CSVLoader loader1 = new CSVLoader("city.csv");
        CSVLoader loader2 = new CSVLoader("country.csv");
        CSVLoader loader3 = new CSVLoader("countrylanguage.csv");

        Relation city = loader1.loadCSVinRelation();
        Relation country = loader2.loadCSVinRelation();
        Relation countrylanguage = loader3.loadCSVinRelation();

        //quesito 1: trova tutte le nazioni Europee
        System.out.println("Tutti i paesi in europa:");
        System.out.println(country.selection("Continent", "Europe"));
        System.out.println("\n");

        //quesito 2: trova tutte le città in francia
        System.out.println("Tutti le città in francia:");
        ArrayList<String> quesito2 =  new ArrayList<>();
        quesito2.add("Name");
        quesito2.add("CountryCode");
        System.out.println(city.projection(quesito2).selection("CountryCode", "FRA"));
        System.out.println("\n");

        //quesito 3: trova il nome delle nazioni che hanno una popolazione compresa tra 100 milioni e 200 milioni di abitanti

        System.out.println("nome dei paesi con una popolazione compresta tra 100 milioni e 200 milioni:");
        
        System.out.println("\n");
    }

    public static void totaleOrdini(Relation relazione1, Relation relazione2){
        int tot = 0;
        for (int i = 0; i<relazione1.getRows().size(); i++){
            Row r = relazione1.getRows().get(i);
            int idprodotto = Integer.parseInt(r.getValue(2));
            int quantita = Integer.parseInt(r.getValue(3));
            for (int j = 0; j< relazione2.getRows().size(); j++){
                Row r2= relazione2.getRows().get(j);
                if ((Integer.parseInt(r2.getValue(0)) == idprodotto)) {
                    int prezzo = Integer.parseInt(r2.getValue(2));
                    tot += prezzo + quantita;
                }
            }
        }
        System.out.println("Totale: " + tot);
    }

    public static void totaleSingolo(Relation relazione1, Relation relazione2){
        for (int i = 0; i<relazione1.getRows().size(); i++){
            Row r = relazione1.getRows().get(i);
            int id = Integer.parseInt(r.getValue(0));
            int idprodotto = Integer.parseInt(r.getValue(2));
            int quantità = Integer.parseInt(r.getValue(3));
            for (int j = 0; j< relazione2.getRows().size(); j++){
                Row r2= relazione2.getRows().get(j);
                if ((Integer.parseInt(r2.getValue(0)) == idprodotto)) {
                    int prezzo = Integer.parseInt(r2.getValue(2));
                    int totOrdine = prezzo * quantità;
                    System.out.println("ordine " + id + ": " + totOrdine + " Euro");
                }
            }
        }
    }

    public static void prodottoCostoso(Relation relazione1, Relation relazione2, Relation relazione3){
        int prezzomax = 0;
        String idProdottoCostoso = "";
        for (int i = 0; i<relazione1.getRows().size(); i++){
            Row r = relazione1.getRows().get(i);
            int prezzo = Integer.parseInt(r.getValue(2));
            if(prezzo > prezzomax){
                prezzomax = prezzo;
                idProdottoCostoso = r.getValue(0);
            }
        }
        System.out.println("Gli utenti che hanno preso il prodotto più costoso: ");
        for (int j = 0; j< relazione2.getRows().size(); j++){
            Row r2= relazione2.getRows().get(j);
            if (r2.getValue(2).equals(idProdottoCostoso)) {
                String idUtente = r2.getValue(1);
                for(int k = 0; k <relazione3.getRows().size(); k++){
                    Row persone = relazione3.getRows().get(k);
                    if (persone.getValue(0).equals(idUtente)) {
                        System.out.println(persone.getValue(1) + " " + persone.getValue(2));
                    }
                }
            }
        }
    }
}