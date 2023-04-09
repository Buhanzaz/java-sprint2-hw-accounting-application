import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    ArrayList<Statistics> statisticsArrayList = new ArrayList<>();
    HashMap<Integer, Integer> monthlyReport = new HashMap<>();
    HashMap<Integer, Integer> onlyMonthlyExpenses = new HashMap<>();
    HashMap<Integer, Integer> onlyMonthlyIncome = new HashMap<>();
    public int numberMonth = 12;

    public void constructorOfPath(){
        for (int i = 1; i <= numberMonth; i++) {
            if (i < 10) {
                List<String> content = readFileContents("resources/m.20210" + i + ".csv");
                reportConversion(content, i);
            } else {
                List<String> content = readFileContents("resources/m.2021" + i + ".csv");
                reportConversion(content, i);
            }

        }
    }

    public void reportConversion(List<String> content, int month){
        for (int j = 1; j < content.size(); j++) {
            String line = content.get(j);
            String[] parts = line.split(",");
            String item_name = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            Statistics statistics = new Statistics(month, item_name, isExpense, quantity, sumOfOne);
            statisticsArrayList.add(statistics);
        }
    }

    public void getMonthlyReport(){

        for (Statistics statistics : statisticsArrayList) {
            int sum = statistics.quantity * statistics.sumOfOne;
            if (statistics.isExpense) {
                monthlyReport.put(statistics.month, monthlyReport.getOrDefault(statistics.month, 0) - sum);
            } else {
                monthlyReport.put(statistics.month, monthlyReport.getOrDefault(statistics.month, 0) + sum);
            }
        }
        System.out.println(monthlyReport);
    }

    public void onlyMonthlyExpenses(){
        for (Statistics statistics : statisticsArrayList) {
            int sum = statistics.quantity * statistics.sumOfOne;
            if (statistics.isExpense) {
                onlyMonthlyExpenses.put(statistics.month, onlyMonthlyExpenses.getOrDefault(statistics.month, 0) + sum);
            }
        }
        System.out.println(onlyMonthlyExpenses);
    }
    public void onlyMonthlyIncome(){
        for (Statistics statistics : statisticsArrayList) {
            int sum = statistics.quantity * statistics.sumOfOne;
            if (!statistics.isExpense) {
                onlyMonthlyIncome.put(statistics.month, onlyMonthlyIncome.getOrDefault(statistics.month, 0) + sum);
            }
        }
        System.out.println(onlyMonthlyIncome);
    }





    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
