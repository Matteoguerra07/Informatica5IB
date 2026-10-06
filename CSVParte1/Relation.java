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

    public Relation selection(String key, String operator, int num){
        Relation ris = new Relation();
        int i = header.indexOf(key);
        ArrayList<Row> selectedRow = new ArrayList<>();

        for (int j = 0; j < rows.size(); j++) {
            Row r = rows.get(j);
            double v;
            try {
                v = Double.parseDouble(r.getValue(i));
            } catch (NumberFormatException e) {
                continue; // salta NULL
            }

            boolean ok = false;
            if (operator.equals(">")) ok = v > num;
            else if (operator.equals(">=")) ok = v >= num;
            else if (operator.equals("<")) ok = v < num;
            else if (operator.equals("<=")) ok = v <= num;
            else if (operator.equals("=")) ok = v == num;

            if (ok) selectedRow.add(r);
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

    public Relation prodotto(Relation other) {
        Relation ris = new Relation();
        ArrayList<String> newHeader = new ArrayList<>();

        for (int i = 0; i < header.size(); i++) {
            newHeader.add(header.get(i));
        }

        for (int i = 0; i < other.getHeader().size(); i++) {
            newHeader.add(other.getHeader().get(i));
        }

        ArrayList<Row> newRows = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < other.getRows().size(); j++) {
                ArrayList<String> values = new ArrayList<>();
                for (int k = 0; k < rows.get(i).getValues().size(); k++) {
                    values.add(rows.get(i).getValues().get(k));
                }
                for (int k = 0; k < other.getRows().get(j).getValues().size(); k++) {
                    values.add(other.getRows().get(j).getValues().get(k));
                }
                newRows.add(new Row(values));
            }
        }
        ris.setHeader(newHeader);
        ris.setRows(newRows);

        return ris;
    }

    public Relation join(Relation other, String[] joinField) {
        Relation prodotto = this.prodotto(other);
        int pos1 = prodotto.getHeader().indexOf(joinField[0]);
        int pos2 = prodotto.getHeader().lastIndexOf(joinField[1]);
        ArrayList<Row> righe = new ArrayList<>();

        for (int i = 0; i < prodotto.getRows().size(); i++) {
            Row r = prodotto.getRows().get(i);
            if (r.getValue(pos1).equals(r.getValue(pos2))) {
                righe.add(r);
            }
        }
        return new Relation(prodotto.getHeader(), righe);
    }

    public Relation rename(String oldName, String newName) {
        ArrayList<String> newHeader = new ArrayList<>(header);
        newHeader.set(newHeader.indexOf(oldName), newName);
        return new Relation(newHeader, rows);
    }

    @Override 
    public String toString() {
        String out = "";
        for (int i = 0; i < header.size(); i++) {
            out += header.get(i) + " ";
        }
        out += "\n";
        for (int i = 0; i < rows.size(); i++) {
            out += rows.get(i) + " \n";
        }
        return out;
    }
}