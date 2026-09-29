
public class Main {
    public static void main(String[] args){
        CSVLoader loader1 = new CSVLoader("Ordini.csv");
        CSVLoader loader2 = new CSVLoader("Persone.csv");
        CSVLoader loader3 = new CSVLoader("Prodotti.csv");

        Relation ordini = loader1.loadCSVinRelation();
        Relation persone = loader2.loadCSVinRelation();
        Relation prodotti = loader3.loadCSVinRelation();

        System.out.println("Prodotto cartesiano");
        System.err.println(persone.prodotto(prodotti).toString());

        System.out.println("Giunzione");
        String[] s = {"id_prodotto", "id_prodotto"};
        System.err.println(ordini.join(prodotti, s).toString());

        System.out.println("\nQuery n1 (Visualizzare il totale per tutti gli ordin1): ");
        totaleOrdini(ordini, prodotti);

        System.out.println("\nQuery n2 (Visualizzare il totale per ogni singolo ordine): ");
        totaleSingolo(ordini, prodotti);
        
        System.out.println("\nQuery n3 (Gli utenti che hanno acquistato il prodotto più costoso): ");
        prodottoCostoso(prodotti, ordini, persone);
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