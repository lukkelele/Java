package CodeForLectures.lecture5.memento;

public class MementoMain {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.setContent("Grogu");
        history.push(editor.save());

        editor.setContent("is");
        history.push(editor.save());

        editor.setContent("cute!");
        history.push(editor.save());

        System.out.println(editor.getContent()); // cute! as it is on top
        editor.restore(history.pop()); // sets to top value cute!
        System.out.println(editor.getContent()); // still prints cute! as it was on top

        editor.restore(history.pop()); // removes cute!
        System.out.println(editor.getContent()); // printes "is"
    }
}
