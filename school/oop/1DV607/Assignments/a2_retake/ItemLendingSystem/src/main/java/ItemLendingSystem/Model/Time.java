package ItemLendingSystem.Model;

public class Time {
    
    private int dayCount;

    public Time() {
        this.dayCount = 0;
    }

    public void fastForward(int days) {
        for (int k=0 ; k<days ; k++) {
            this.dayCount++;
        }
    }

    public void rewind(int days) {
        for (int k=0 ; k<days ; k++) {
            this.dayCount--;
            if (this.dayCount == 0) {
                break;
            }
        }
    }

    public int getCurrentDay() {
        return this.dayCount;
    }

}
