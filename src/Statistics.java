public class Statistics {
    public Integer month, amount, quantity, sumOfOne, year;
    public String itemName;
    public Boolean isExpense;

    public Statistics(Integer year, Integer month, Integer amount, Boolean isExpense) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public Statistics(Integer month, String itemName, Boolean isExpense, Integer quantity, Integer sumOfOne) {
        this.month = month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}

