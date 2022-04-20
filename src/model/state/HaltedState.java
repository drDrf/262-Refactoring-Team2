package model.state;

import static java.lang.Thread.sleep;

public class HaltedState extends LaneState {
    public HaltedState(Lane lane) {
        super(lane);
    }

    @Override
    public void handleState() {
        try {
            sleep(1000);
        } catch (Exception e) {}
    }
}
