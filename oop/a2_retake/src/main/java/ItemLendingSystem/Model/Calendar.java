package ItemLendingSystem.Model;

import java.util.ArrayList;

public class Calendar implements Subject {
    
    private ArrayList<Observer> contracts;
    private int day; 

    public Calendar() {
        this.day = 0;
        this.contracts = new ArrayList<>();
    }

    @Override
    public void signContract(Observer contract) {
        if (!this.contracts.contains(contract)) {
            this.contracts.add(contract);
        } else {
            System.out.println("Contract already subscribed!");
        }
    }

    @Override
    public void ceaseContract(Observer contract) {
        if (this.contracts.contains(contract)) {
            this.contracts.remove(contract);
        } else {
            System.out.println("Contract already unsubscribed!");
        }
    }

    @Override 
    public void update(int days) {
        for (Observer contract : this.contracts) {
            int terminationDate = contract.update(days);
            if (this.day == terminationDate) {
                // NOTIFY BANK FOR FEE
                // CEASE CONTRACT
                ceaseContract(contract);
            } else {
                // NOTIFY BANK FOR FEE
            }
        }
    }

    public void fastForward(int days) {
        for (int k=0 ; k<days ; k++) {
            this.day++;
        }
    }

    public void rewind(int days) {
        for (int k=0 ; k<days ; k++) {
            this.day--;
            if (this.day == 0) {
                break;
            }
        }
    }

    public int getCurrentDay() {
        return this.day;
    }
    

}
