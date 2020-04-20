package model;

public class Elevator {
    private int minFloor;
    private int maxFloor;
    private int curFloor;
    private int maxChoosedFloors;
    private boolean upward;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.curFloor = 1;
        this.upward = true;
    }

    public boolean isUpward() {
        return upward;
    }

    public void setUpward(boolean upward) {
        this.upward = upward;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }

    public void move () {
        if (upward) {
            if (curFloor == maxFloor)
                upward = !upward;
            else
                curFloor++;
        }
        else {
            if (curFloor == minFloor)
                upward = !upward;
            else
                curFloor--;
        }
    }

}
