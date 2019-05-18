import java.util.*;


public class Main {

    public static void main(String[] args) {

        new Main().generate(10);

    }

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> pascalTri = new ArrayList<>();

        ArrayList<Integer> row0 = new ArrayList<>();
        row0.add(1);

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(1);

        pascalTri.add(row0);
        pascalTri.add(row1);

        System.out.println(getPrintedArray(row0));
        System.out.println(getPrintedArray(row1));


        List<Integer> previousRow = row1;

        for (int i = 2; i < numRows; i++) {


            previousRow = createPascalRow(previousRow);
            System.out.println(getPrintedArray(previousRow));
            pascalTri.add(previousRow);

        }

        return pascalTri;
    }


    public List<Integer> createPascalRow(List<Integer> aboveRow) {

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

    public String getPrintedArray(List<Integer> acc) {

        StringBuilder text = new StringBuilder();

        for (Integer value : acc) {
            text.append(value.toString()).append("_");
        }

        return text.toString();
    }

    public void printLevelInfo(List<Map.Entry<Integer, Integer>> entryList) {

        for (Map.Entry<Integer, Integer> entry : entryList) {

            System.out.print("Key: " + entry.getKey());
            System.out.println(" -> Value = " + entry.getValue());

        }


    }

}
