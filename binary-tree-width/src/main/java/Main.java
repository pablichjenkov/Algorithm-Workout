import java.util.*;


public class Main {

    public static void main(String[] args) {

        TreeNode rootNode = new TreeNode(3);

        TreeNode node_1_1 = new TreeNode(4);
        TreeNode node_1_2 = new TreeNode(5);

        TreeNode node_2_1 = new TreeNode(6);
        TreeNode node_2_2 = new TreeNode(7);
        TreeNode node_2_3 = new TreeNode(8);
        TreeNode node_2_4 = new TreeNode(9);

        TreeNode node_3_1 = new TreeNode(10);
        TreeNode node_3_2 = new TreeNode(11);
        TreeNode node_3_3 = new TreeNode(12);
        TreeNode node_3_4 = new TreeNode(13);
        TreeNode node_3_5 = new TreeNode(14);
        TreeNode node_3_6 = new TreeNode(15);
        TreeNode node_3_7 = new TreeNode(16);
        TreeNode node_3_8 = new TreeNode(17);

        rootNode.left = node_1_1;
        rootNode.right = node_1_2;

        node_1_1.left = node_2_1;
        node_1_1.right = node_2_2;

        //node_1_2.left = node_2_3;
        node_1_2.right = node_2_4;

        node_2_1.left = node_3_1;
        node_2_1.right = node_3_2;

        node_2_2.left = node_3_3;
        node_2_2.right = node_3_4;

        node_2_3.left = node_3_5;
        node_2_3.right = node_3_6;

        node_2_4.left = node_3_7;
        node_2_4.right = node_3_8;


        new Main().widthOfBinaryTree(rootNode);

    }

    public int widthOfBinaryTree(TreeNode root) {

        //countChildLevel(root, 0);
        //List<Map.Entry<Integer, Integer>> sortPairs = sortMapByValue(collector);
        //return sortPairs.get(sortPairs.size() - 1).getValue();

        countChildLevel_V2(root, 0, new ArrayList<>());

        int max = processLevels();

        System.out.print("Maximum is: " + max);

        return max;

    }

    private Map<Integer, Integer> collector = new HashMap<>();
    private Map<Integer, List<PositionInfo>> collector_v2 = new HashMap<>();
    private Map<Integer, Integer> nullInfo = new HashMap<>();

    public void countChildLevel(TreeNode node, Integer level) {

        Integer levelCounter = collector.get(level);

        if (levelCounter == null) {
            collector.put(level, new Integer(1));
        }
        else {

            int counter = levelCounter + 1;

            Integer nullCounter = nullInfo.get(level);

            if (nullCounter != null) {
                counter = counter + nullCounter;
                nullInfo.put(level, new Integer(0));
            }

            collector.put(level, counter);
        }

        if (node.left != null) {
            countChildLevel(node.left, level + 1);
        } else {
            saveNullInfo(level + 1);
        }

        if (node.right != null) {
            countChildLevel(node.right, level + 1);
        } else {
            saveNullInfo(level + 1);
        }

    }

    private void saveNullInfo(Integer level) {

        Integer levelCounter = collector.get(level);

        if (levelCounter == null) {
            return;
        }

        Integer nullCounter = nullInfo.get(level);

        if (nullCounter == null) {
            nullInfo.put(level, new Integer(1));
        }
        else {
            int counter = nullCounter + 1;
            nullInfo.put(level, counter);
        }
    }

    //****************** V2 ****************//

    public void countChildLevel_V2(TreeNode node, Integer level, List<Boolean> path) {

        List<PositionInfo> levelPositions = collector_v2.get(level);

        if (levelPositions == null) {
            levelPositions = new ArrayList<>();
            levelPositions.add(new PositionInfo(path));
            collector_v2.put(level, levelPositions);
        }
        else {
            levelPositions.add(new PositionInfo(path));
            collector_v2.put(level, levelPositions);
        }

        if (node.left != null) {

            ArrayList newPath = new ArrayList(path);
            newPath.add(true);
            countChildLevel_V2(node.left, level + 1, newPath);
        }

        if (node.right != null) {
            ArrayList newPath = new ArrayList(path);
            newPath.add(false);
            countChildLevel_V2(node.right, level + 1, newPath);
        }

    }


    public List<Map.Entry<Integer, Integer>> sortMapByValue(Map<Integer, Integer> unsortMap) {

        List<Map.Entry<Integer, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        printLevelInfo(list);

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list,
            (Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)  -> {
                return (o1.getValue()).compareTo(o2.getValue());

        });
        System.out.println("----------------- After Sorting ------------------");

        printLevelInfo(list);

        return list;

    }

    private int processLevels() {

        int curMax = 0;

        for (Map.Entry<Integer, List<PositionInfo>> levelInfo : collector_v2.entrySet()) {

            int levelCounter = processLevelInfo(levelInfo.getKey(), levelInfo.getValue());

            if (levelCounter > curMax) {
                curMax = levelCounter;
            }
        }


        return curMax;
    }

    private int processLevelInfo(Integer level, List<PositionInfo> levelInfo) {

        if (levelInfo.size() < 2) {
            return 1;
        }

        PositionInfo firstNode = levelInfo.get(0);
        PositionInfo lastNode = levelInfo.get(levelInfo.size() - 1);

        int levelMin = 1;
        int levelMax = (int)Math.pow(2, level);

        LevelRange levelRange = new LevelRange(levelMin, levelMax);

        int firstNodePositionInRow = processNodeNumberInRow(levelRange, firstNode.path, 0);
        int lastNodePositionInRow = processNodeNumberInRow(levelRange, lastNode.path, 0);

        return lastNodePositionInRow - firstNodePositionInRow + 1;

    }


    private int processNodeNumberInRow(LevelRange range, List<Boolean> path, int pathIdx) {

        if (pathIdx < path.size() - 1) {

            boolean isLeft = path.get(pathIdx);
            int rangeMin = range.min;
            int rangeMax = range.max;

            int rangeMiddle = 1 + ((rangeMax - rangeMin) / 2);

            LevelRange newRange = null;

            if (isLeft) {
                newRange = new LevelRange(rangeMin, rangeMiddle);
            }
            else {
                newRange = new LevelRange(rangeMiddle, rangeMax);
            }

            return processNodeNumberInRow(newRange, path, pathIdx + 1);

        }
        else {

            boolean isLeft = path.get(pathIdx);

            if (isLeft) {
                return range.min;
            }
            else {
                return range.max;
            }

        }

    }

    private void printLevelInfo(List<Map.Entry<Integer, Integer>> entryList) {

        for (Map.Entry<Integer, Integer> entry : entryList) {

            System.out.print("Key: " + entry.getKey());
            System.out.println(" -> Value = " + entry.getValue());

        }


    }

}
