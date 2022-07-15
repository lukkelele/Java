package CodeForLectures.lecture5.memento;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<State> list = new ArrayList<>();

    public void push(State state) {
        list.add(state);
    }

    public State pop() {
        int last = list.size() - 1;
        State lastState = list.get(last);
        list.remove(lastState);

        return lastState;
    }
}
