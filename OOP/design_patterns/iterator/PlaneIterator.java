package iterator;

public class PlaneIterator implements Iterator {

    private String[] planes;
    private int position;

    @Override
    public boolean hasNext() {
        if (position >= planes.length){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void first() {
        // reset to first index
        position = 0;
    }

    @Override
    public String next() {
        return planes[position+1];
        
    }

    @Override
    public String currentItem() {
        return planes[position];
    }

    
}
