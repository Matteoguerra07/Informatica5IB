import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVLoader {
    String file = "";
    public CSVLoader(String file) {
        this.file = file;
    }

    public Relation loadCSVinRelation() {
        Relation loaded = new Relation();
        List<List<String>> records = new ArrayList<>();
        int rowNumber = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] campi = line.split(",");
                List<String> campiList = Arrays.asList(campi);
                System.out.println("Lista: ");
                System.out.println(campiList);
                records.add(campiList);
                rowNumber++;
                loaded.setHeader(records.get(0));
                ArrayList<Row> temp = new ArrayList<>();
                for (int i = 1; i < records.size(); i++) {
                    Row r = new Row(records.get(i));
                    temp.add(r);
                }
                loaded.setRows(temp);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("can't load " + this.file + " - error: " + e);
        }

        return loaded;
    }
}