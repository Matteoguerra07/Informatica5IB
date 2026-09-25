import java.util.ArrayList;

public class Row {
    ArrayList<String> values;

    public Row(ArrayList<String> values) {
        this.values = values;
    }

    public String getValue(int index) {
        return values.get(index);
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}