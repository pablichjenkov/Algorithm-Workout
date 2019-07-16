import java.util.ArrayList;

class Main {

    public static void main(String[] args) {

        ArrayList<Integer> emptyPath = new ArrayList<>();

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

        app.nextLevel(data, emptyPath, 0);

    }


    public boolean nextLevel(ArrayList<Integer> data, ArrayList<Integer> path, int level) {

        String comb = getPrintedArray(path);
        System.out.println(comb);

        if (validateCond(data, path)) {
            System.out.println("Above is true");
            return true;
        }

        while (level < data.size()) {

            ArrayList<Integer> newPath = new ArrayList<>(path);
            newPath.add(level);

            boolean found = nextLevel(data, newPath, level + 1);

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

    public boolean validateCond(ArrayList<Integer> data, ArrayList<Integer> combination) {

        int sumA = 0;
        int lenA = combination.size();
        int sumB = 0;
        int lenB = data.size() - lenA;

        for (int i=0; i<data.size(); i++) {

            int nextValue = data.get(i);

            if (combination.contains(i)) {
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