package ItemLendingSystem;

import java.util.Date;

public class Contract {

    private Date startDate;
    private Date terminationDate;
    private Member owner;

    protected Contract(Date start, Date termination, Member owner) {
        this.startDate = start;
        this.terminationDate = termination;
        this.owner = owner;
    }


    protected void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    protected void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
    
}
