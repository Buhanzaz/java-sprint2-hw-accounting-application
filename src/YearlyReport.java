import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    MonthlyReport monthlyReport = new MonthlyReport();
    ArrayList<Statistics> yearlyStatistics = new ArrayList<>();


    //Loading cvs file and writing object in array list
    public void yearlyReportConversion(int year, String path){
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++){
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            Statistics statistics = new Statistics(year, month, amount, isExpense);
            yearlyStatistics.add(statistics);
        }
    }

    //Output year
    public int getYear() {
        return yearlyStatistics.get(1).year;
    }
    public HashMap<Integer, HashMap<Boolean, Integer>> expensesAndIncome() {
        HashMap<Integer, HashMap<Boolean, Integer>> expAndInc = new HashMap<>();
        for (Statistics statistics : yearlyStatistics) {
            if (!expAndInc.containsKey(statistics.month)){
                expAndInc.put(statistics.month, new HashMap<>());
            }
            HashMap<Boolean, Integer> statisticToCount = expAndInc.get(statistics.month);
            statisticToCount.put(statistics.isExpense,
                    statisticToCount.getOrDefault(statistics.isExpense, 0) + statistics.amount);
        }
        return expAndInc;
    }


    public HashMap<Integer, Integer> averageExpensesAndIncome(boolean value){
        HashMap<Integer, Integer> averageExpensesAndIncome = new HashMap<>();
        for (Integer month : expensesAndIncome().keySet()) {
            HashMap<Boolean, Integer> statisticToCountYear = expensesAndIncome().get(month);

            for (Boolean aBoolean : statisticToCountYear.keySet()) {
                if (aBoolean == value) {
                    averageExpensesAndIncome.put(month,
                            averageExpensesAndIncome.getOrDefault(month,0)
                                    + statisticToCountYear.get(aBoolean));
                }
            }
        }
        return averageExpensesAndIncome;
    }

    public void averageIncome(){
        int allIncome = 0;
        int monthInYear = averageExpensesAndIncome(false).size();
        for (Integer month : averageExpensesAndIncome(false).keySet()) {
            allIncome += averageExpensesAndIncome(false).get(month);
        }
        int averageIncome = allIncome / monthInYear;
        System.out.println("Средний доход за: " + monthInYear + " месяца " + averageIncome);
    }

    public void averageExpense(){
        int allExpense = 0;
        int monthInYear = averageExpensesAndIncome(true).size();
        for (Integer month : averageExpensesAndIncome(true).keySet()) {
            allExpense += averageExpensesAndIncome(true).get(month);
        }
        int averageExpense = allExpense / monthInYear;
        System.out.println("Средний доход за: " + monthInYear + " месяца " + averageExpense);
    }

    public void ProfitOfMouth(){
        System.out.println(getYear());
        for (Integer monthIncome : averageExpensesAndIncome(false).keySet()) {
            int profit = averageExpensesAndIncome(false).get(monthIncome)
                    - averageExpensesAndIncome(true).get(monthIncome);
            System.out.println(monthlyReport.nameMonth(monthIncome));
            System.out.println("Прибыль: " + profit + "\n");
        }
        System.out.println();
        averageIncome();
        averageExpense();
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. " +
                    "Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

}
