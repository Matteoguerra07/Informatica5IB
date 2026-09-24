
import java.util.*;

public class Relation {
    List<String> header;
    ArrayList<Row> rows;

    public Relation() {
        
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }

    public void addRow(Row rowToAdd){
        this.rows.add(rowToAdd);
    }

    public void setHeader(List<String> header){
        this.header = header;
    }

    @Override 
    public String toString(){
        String out = "";
        for (int i = 0; i < header.size(); i++) {
            out += header.get(i) + " ";
        }
        out += "\n";
        for (int i = 0; i < rows.size(); i++) {
            out += rows.get(i) + "\n";
        }

        return out;
    }
    
    public void selection (Relation input, String key, String value){

    }

    public void projection (Relation input, ArrayList<String> keys  ){

    }

    public void union (){

    }

    public void difference (){

    }
}
