
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    String file = "Persone.csv";

    public CSVLoader(String file){
        this.file = file;
    }

    public Relation loadCSVinRelation(){
        Relation loaded = new Relation(file);
        List<List<String>> records = new ArrayList<>();
        int rowNumber = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
                String line;
                while ((line = br.readLine()) != null) {
                    
                    String [] campi= line.split(",");
                    rowNumber++;
                }
        }catch(Exception e){
            System.out.println("can't load "+this.file);

        }

        return loaded;
    }

}