package betrayal.player;


public class PlayerStatistic {

    /**
     * current index of Statistic.
     */
    private int currentIndex;

    /**
     * all possible values for stat.
     */
    private int[] possibleValues;

    private int startingIndex;

    /**
     * @param index
     * @param values
     */
    public PlayerStatistic(final int index, final int[] values) {
        this.currentIndex = index;
        this.startingIndex = index;
        int[] copiedVals = new int[8];
        for (int i = 0; i < copiedVals.length; i++) {
            copiedVals[i] = values[i];
        }
        this.possibleValues = copiedVals;
    }

    /**
     * @param index
     */
    public void setCurrentIndex(final int index) {
        this.currentIndex = index;
    }

    /**
     * @return the value at the current index
     */
    public int getCurrentValue() {
        return this.possibleValues[currentIndex];
    }

    /**
     * @return the current index
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * @return all possible values
     */
    public int[] getPossibleValues() {
        int[] toReturn = new int[8];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = this.possibleValues[i];
        }
        return toReturn;
    }

    /**
     * Resets the statistic to its initial value.
     */
    public void resetStatistic() {
        if (currentIndex < startingIndex) {
            this.currentIndex = this.startingIndex;
        }
    }
}
