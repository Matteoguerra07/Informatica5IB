import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVLoader {
    String file = "";

    public CSVLoader(String file) {
        this.file = file;
    }

    public Relation loadCSVinRelation() {
        ArrayList<String> header = new ArrayList<>();
        ArrayList<Row> rows = new ArrayList<>();
        int rowNum = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] campi = line.split(",");

                if (rowNum == 0) {
                    for (int i = 0; i < campi.length; i++) {
                        header.add(campi[i]);
                    }
                } else {
                    ArrayList<String> values = new ArrayList<>();

                    for (int i = 0; i < campi.length; i++) {
                        values.add(campi[i]);
                    }

                    rows.add(new Row(values));
                }
                rowNum++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("can't load " + this.file);
        }

        return new Relation(header, rows);
    }
}