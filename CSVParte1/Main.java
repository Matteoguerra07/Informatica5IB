public class Main {
    public static void main(String[] args) {
        CSVLoader loader = new CSVLoader("Persone.csv");
        loader.loadCSVinRelation();
        Relation r1 = loader.loadCSVinRelation();
        System.out.println(r1.toString());
    }
}
