import java.util.ArrayList;

public class Relation {
    ArrayList<String> header;
    ArrayList<Row> rows;

    public Relation(ArrayList<String> header, ArrayList<Row> rows) {
        this.header = header;
        this.rows = rows;
    }

    public Relation(){}

    public ArrayList<String> getHeader() {
        return header;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setHeader(ArrayList<String> header) {
        this.header = header;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Relation selection(String key, String value) {
        Relation ris = new Relation();

        int i = header.indexOf(key);
        ArrayList<Row> selectedRow = new ArrayList<>();

        for (int j = 0; j < rows.size(); j++) {
            Row r = rows.get(j);
            if (r.getValue(i).equals(value)) {
                selectedRow.add(r);
            }
        }

        ris.setHeader(header);
        ris.setRows(selectedRow);

        return ris;


    }



    public Relation projection(ArrayList<String> keys) {

        Relation ris = new Relation();
        ArrayList<Row> projectedRows = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            ArrayList<String> newVals = new ArrayList<>();

            for (int j = 0; j < keys.size(); j++) {

                int index = header.indexOf(keys.get(j));
                newVals.add(rows.get(i).getValue(index));
            }

            projectedRows.add(new Row(newVals));
        }

        ris.setHeader(keys);
        ris.setRows(projectedRows);

        return ris;
    }


    public Relation union(Relation other) {
        Relation ris = new Relation();

        if (!header.equals(other.getHeader())) {
            return null;
        }

        ArrayList<Row> unionRows = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            unionRows.add(rows.get(i));
        }

        for (int i = 0; i < other.getRows().size(); i++) {

            Row r = other.getRows().get(i);

            if (!unionRows.contains(r)) {
                unionRows.add(r);
            }
        }

        ris.setHeader(header);
        ris.setRows(unionRows);

        return ris;
    }


    public Relation difference(Relation other) {
        Relation ris = new Relation();

        if (!header.equals(other.getHeader())) {
            return null;
        }

        ArrayList<Row> differenceRows = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            Row r = rows.get(i);

            if (!other.getRows().contains(r)) {
                differenceRows.add(r);
            }
        }

        ris.setHeader(header);
        ris.setRows(differenceRows);

        return ris;
    }

    public Relation cartesianProduct(Relation two){

    }

    public Relation join(Relation two, String[] joinField){
        
    }

    @Override
    public String toString() {
        return "Relation{" +
                "header=" + header +
                ", rows=" + rows +
                '}';
    }
}