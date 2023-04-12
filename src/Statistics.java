public class Statistics {
    public Integer month, amount, quantity, sumOfOne;
    public String itemName;
    public Boolean isExpense;


    //public String[] monthly = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Statistics(Integer month, Integer amount, Boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
        //convertMonth();
    }

    public Statistics(Integer month,String itemName, Boolean isExpense, Integer quantity, Integer sumOfOne) {
        this.month =month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

/*
    public void convertMonth() {
        for (int i = 0; i < monthly.length; i++)
            if (month == (i + 1)){
                monthName = monthly[i];
            }

    }
*/
}

