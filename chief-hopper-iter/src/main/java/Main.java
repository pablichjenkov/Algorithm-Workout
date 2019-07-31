

public class Main {


    public static void main(String[] args) {

        int[] heights = new int[]{4,4,4};

        int minEnergy = chiefHopper(heights);

        System.out.println("Minimum starting energy is: " + minEnergy);

    }

    static int chiefHopper(int[] heights) {

        int postJumpEnergy = 0;

        for (int i = heights.length - 1; i >= 0; i--) {

            Pair result = calculateEnergy(heights[i], postJumpEnergy);

            while (!result.valid || result.energyBeforeHop < 0) {

                result = calculateEnergy(heights[i], postJumpEnergy ++);

            }

            postJumpEnergy = result.energyBeforeHop;

        }

        return postJumpEnergy;

    }

    static int chiefHopperRec(int[] heights, int pos) {


        if (pos == heights.length) {

            return 0;

        } else {

            int postJumpEnergyInitial = chiefHopperRec(heights, pos + 1);

            int preJumpEnergy = -1;

            while (preJumpEnergy < 0) {

                Pair result = calculateEnergy(heights[pos], postJumpEnergyInitial);

                if (!result.valid || result.energyBeforeHop < 0) {
                    postJumpEnergyInitial ++;

                    continue;
                }
                else {

                    preJumpEnergy =  result.energyBeforeHop;

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

    static class Pair {

        int energyBeforeHop;

        boolean valid;

        Pair(int energyBeforeHop, boolean valid) {
            this.energyBeforeHop = energyBeforeHop;
            this.valid = valid;
        }

    }

}
