package CodeForLectures.lecture5.composite;

import java.util.ArrayList;
import java.util.List;

public class Box implements Component {
    private List<Component>  children = new ArrayList<>();

    public void add(Component c) {
        children.add(c);
    }

    @Override
    public int calculatePrice() {
        int sum = 0;
        
        for(Component c: children) {
            sum += c.calculatePrice();
        }
        return sum;
    }
    
}
