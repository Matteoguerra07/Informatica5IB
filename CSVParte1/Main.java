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

        System.out.println("nome dei paesi con una popolazione compresa tra 100 milioni e 200 milioni:");
        ArrayList<String> quesito3 = new ArrayList<>();
        quesito3.add("Name");
        quesito3.add("Popolation");
        //System.out.println(country.projection(quesito3).selection(6).selection(null, null));
        System.out.println("\n");   

        //quesito 4: trova tutte le nazioni del sud America, il nome della capitale, la popolazione e nome dello stato

        System.out.println("Capitale, popolazione, stato delle nazioni del sud America");
        ArrayList<String> quesito4 = new ArrayList<>();
        Relation cityR = city.rename("Name", "CityName").rename("Population", "CityPopulation");
        Relation nazioni = country.selection("Continent", "South America");
        Relation uniti = nazioni.join(cityR, new String[]{"Capital", "ID"});

        quesito4.add("CityName");
        quesito4.add("Population");
        quesito4.add("Name");


        System.out.println(uniti.projection(quesito4));
        System.out.println("\n");


        //quesito 5: trova le nazioni asiatiche con numero di abitanti maggiore di quello del Giappone.
        System.out.println("Paesi asiatici con popolazione maggiore rispetto al giappone");
        int popolazione = country.getHeader().indexOf("Population");
        int popGiapp = Integer.parseInt(country.selection("Name", "Japan").getRows().get(0).getValue(popolazione));

        ArrayList<String> quesito5 = new ArrayList<>();
        quesito5.add("Name");
        quesito5.add("Population");
        System.out.println(country.selection("Continent", "Asia").selection("Population", ">", popGiapp).projection(quesito5));

        
    }

    /*public static void totaleOrdini(Relation relazione1, Relation relazione2){
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
    }*/
}