package model.lane;

public abstract class LaneState {
    private Lane lane;

    public LaneState(Lane lane) {
        this.lane = lane;
    }

    protected Lane getLane() {
        return lane;
    }

    public abstract void handleState();
}
