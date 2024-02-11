import java.util.*;

public class MonthlyReport {
    public int numberMonth = 3;
    public List<Statistics> monthStatistic = new ArrayList<>();
    protected boolean checkUseMonthly = false;
    String[] nameMonths = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    // This is the path constructor
    public void reportCount(){
        for (int month = 1; month <= numberMonth; month++) {
            monthlyReportConversion(month, "resources/m.20210" + month + ".csv");
        }
    }
    public void clear(){
        monthStatistic.clear();
    }
    //Loading cvs file and writing object in array list
    public void monthlyReportConversion(int month, String path) {
        ReadFile readFile = new ReadFile();
        List<String> content = readFile.readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            Statistics statistics = new Statistics(month, itemName, isExpense, quantity, sumOfOne);
            monthStatistic.add(statistics);
        }
        checkUseMonthly = true;
    }
    //Search and writing maximum incomes and minimum expense
    // Сделал как вы просили) но так же сделал из двух методов один)
    public HashMap<String, Integer> maxOrMinIncomeAndExpenseItem(int month, boolean value){
        HashMap<String, Integer> maxOrMinIncomeAndExpenseItem = new HashMap<>();
        for (Statistics statistics : monthStatistic) {
            if (statistics.month == month){
                if (statistics.isExpense == value) {
                    int sum = sum(statistics.sumOfOne, statistics.quantity);
                    maxOrMinIncomeAndExpenseItem.put(statistics.itemName, maxOrMinIncomeAndExpenseItem.getOrDefault(statistics.itemName, 0) + sum);
                }
            }
        }
        return maxOrMin(maxOrMinIncomeAndExpenseItem);
    }

    public int sum (int sumOfOne, int quantity) {
        return sumOfOne * quantity;
    }
    // Return name and in expense or income
    public HashMap<String, Integer> maxOrMin(HashMap<String, Integer> map) {
        HashMap<String, Integer> maxOrMin = new HashMap<>();
        String maxOrMinNameItem = null;
        for (String nameItem : map.keySet()) {
            if (maxOrMinNameItem == null){
                maxOrMinNameItem = nameItem;
                continue;
            }
            if (map.get(maxOrMinNameItem) < map.get(nameItem)){
                maxOrMinNameItem = nameItem;
            }
        }
        maxOrMin.put(maxOrMinNameItem, map.get(maxOrMinNameItem));
        return maxOrMin;
    }

    //Monthly statistic output
    public void getStatisticMonth() {
        System.out.println("Информация о всех месячных отчётах\n");
        for (int month = 1; month <= numberMonth; month++) {
            HashMap<String,Integer> maxIncomeItem = maxOrMinIncomeAndExpenseItem(month, false);
            HashMap<String,Integer> maxExpenseItem = maxOrMinIncomeAndExpenseItem(month, true);
            System.out.println(nameMonth(month));
            for (String nameItem : maxIncomeItem.keySet()) {
                System.out.println("Самая большая прибыль: " + nameItem + " - "+ maxIncomeItem.get(nameItem));
            }
            for (String nameItem : maxExpenseItem.keySet()) {
                System.out.println("Самая большая трата: " + nameItem + " - "+ maxExpenseItem.get(nameItem) + "\n");
            }
        }
    }

    //Convert number to month
    public String nameMonth(int numberMonth) {
        String nameMonth = null;
        for (int i = 0; i <= nameMonths.length; i++) {
            if (numberMonth == (i + 1)) {
                nameMonth = nameMonths[i];
            }
        }
        return nameMonth;
    }

}
