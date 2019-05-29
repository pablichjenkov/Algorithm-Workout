import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        new Main().generate(10);

    }

    public List<Integer> generate(int numRows) {

        ArrayList<Integer> row = new ArrayList<>();

        if (numRows == 1) {
            row.add(1);
            return row;
        }


        row.add(1);

        System.out.println(getPrintedArray(row));


        List<Integer> previousRow = row;

        for (int i = 2; i < numRows; i++) {

            previousRow = createPascalRow(previousRow);
            System.out.println(getPrintedArray(previousRow));

        }

        return row;
    }


    private List<Integer> createPascalRow(List<Integer> aboveRow) {

        ArrayList<Integer> nextRow = new ArrayList<>();
        nextRow.add(1);


        for (int i = 1; i < aboveRow.size(); i++) {

            int aboveLeft = aboveRow.get(i - 1);
            int aboveRigth = aboveRow.get(i);

            nextRow.add(aboveLeft + aboveRigth);

        }

        nextRow.add(1);

        return nextRow;
    }

    private String getPrintedArray(List<Integer> acc) {

        StringBuilder text = new StringBuilder();

        for (Integer value : acc) {
            text.append(value.toString()).append("_");
        }

        return text.toString();
    }

}
