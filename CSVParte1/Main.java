public class Main {
    public static void main(String[] args) {
        CSVLoader loader = new CSVLoader("Persone.csv");
        loader.loadCSVinRelation();
    }
}
