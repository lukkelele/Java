package CodeForLectures.lecture5.memento;

public class State {
    // Should be final as the state is immutable, only set via constructor
    private String content;

    public State(String content) {
        this.content = content;
    }

    public String getState() {
        return content;
    }

}
