package CodeForLectures.lecture5.memento;

public class Editor {
    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public State save() {
        return new State(content);
    }

    public void restore(State state) {
        content = state.getState();
    }
}
