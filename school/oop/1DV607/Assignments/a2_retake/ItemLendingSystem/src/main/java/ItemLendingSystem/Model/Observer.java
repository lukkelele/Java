package ItemLendingSystem.Model;

public interface Observer {
    int update(int days);
    void reset();
}
