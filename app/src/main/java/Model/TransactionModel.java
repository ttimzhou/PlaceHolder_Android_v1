package Model;

import java.util.Date;

public class TransactionModel {
    String type;
    String amount;
    String time;

    public TransactionModel(String type, String amount, String time) {
        this.type = type;
        this.amount = amount;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }

}
