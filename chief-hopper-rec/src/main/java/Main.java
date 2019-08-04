

public class Main {


    public static void main(String[] args) {

        int[] heights = new int[]{2, 3, 4, 3, 2};

        int minEnergy = chiefHopper(heights);

        System.out.println("Minimum starting energy is: " + minEnergy);

    }

    static int chiefHopper(int[] heights) {

        return chiefHopperRec(heights, 0);

    }

    static int chiefHopperRec(int[] heights, int pos) {


        if (pos == heights.length) {

            return 0;

        } else {

            int postJumpEnergyInitial = chiefHopperRec(heights, pos + 1);

            int preJumpEnergy = -1;

            while (preJumpEnergy < 0) {

                Pair result = calculateEnergy(heights[pos], postJumpEnergyInitial);

                if (!result.valid || result.energyBefore < 0) {
                    postJumpEnergyInitial ++;

                    continue;
                }
                else {

                    preJumpEnergy =  result.energyBefore;

                }

            }

            return preJumpEnergy;

        }

    }

    static  Pair calculateEnergy(int height, int postJumpEnergy) {

        int delta = height + postJumpEnergy;

        if (delta % 2 == 0) {

            return new Pair(delta / 2, true);

        } else {

            return new Pair(-1, false);

        }

    }

}
