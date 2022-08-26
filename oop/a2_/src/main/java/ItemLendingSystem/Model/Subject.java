package ItemLendingSystem.Model;

public interface Subject {
    public void update(int days);
    public void signContract(Observer subscriber);
    public void ceaseContract(Observer subscriber);
}
