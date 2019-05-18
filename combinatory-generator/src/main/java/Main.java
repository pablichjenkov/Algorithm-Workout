import java.util.ArrayList;

class Main {

    public static void main(String[] args) {

        ArrayList<Integer> newAcc = new ArrayList<>();

        //[6,8,18,3,1]
        //[60,30,30,30,30,
        // 30,30,30,30,30,
        // 30,30,30,30,30,
        // 30,30,30,30,30,
        // 30,30,30,30,30,
        // 30,30,30,30,30]
        ArrayList<Integer> data = new ArrayList<>();
        data.add(60);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);
        data.add(30);

        Main app = new Main();

        app.nextLevel(data, newAcc, 0);

    }


    public boolean nextLevel(ArrayList<Integer> data, ArrayList<Integer> acc, int level) {

        String comb = getPrintedArray(acc);
        System.out.println(comb);

        if (validateCond(data, acc)) {
            System.out.println("Above is true");
            return true;
        }

        while (level < data.size()) {

            ArrayList<Integer> newAcc = new ArrayList<>(acc);
            newAcc.add(level);

            boolean found = nextLevel(data, newAcc, level + 1);

            if (found) {
                return true;
            }

            level ++;
        }

        return false;

    }


    public String getPrintedArray(ArrayList<Integer> acc) {

        StringBuilder text = new StringBuilder();

        for (Integer value : acc) {
            text.append(value.toString()).append("_");
        }

        return text.toString();
    }

    public boolean validateCond(ArrayList<Integer> data, ArrayList<Integer> comb) {

        int sumA = 0;
        int lenA = comb.size();
        int sumB = 0;
        int lenB = data.size() - lenA;

        for (int i=0; i<data.size(); i++) {

            int nextValue = data.get(i);

            if (comb.contains(i)) {
                sumA += nextValue;
            }
            else {
                sumB += nextValue;
            }

        }

        float meanA = sumA / (float)lenA;
        float meanB = sumB / (float)lenB;

        return meanA == meanB;

    }

}