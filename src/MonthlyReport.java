import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MonthlyReport {
    public int numberMonth = 3;
    public ArrayList<Statistics> monthStatistic = new ArrayList<>();
    String[] monthly = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    public void reportCount(){
        for (int month = 1; month <= numberMonth; month++) {
            monthlyReportConversion(month, "resources/m.20210" + month + ".csv");
        }
    }
    public void monthlyReportConversion(int month, String path) {
        List<String> content = readFileContents(path);
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
    }

    public HashMap<String, Integer> maxIncomeItem(int month){
        HashMap<String, Integer> incomeItems = new HashMap<>();
        for (Statistics statistics : monthStatistic) {
            if (statistics.month == month){
                if (!statistics.isExpense) {
                    int income = statistics.sumOfOne * statistics.quantity;
                    incomeItems.put(statistics.itemName, incomeItems.getOrDefault(statistics.itemName, 0) + income);
                }
            }
        }
        return maxOrMin(incomeItems);
    }

    public HashMap<String, Integer> minIncomeItem(int month){
        HashMap<String, Integer> extendItems = new HashMap<>();
        for (Statistics statistics : monthStatistic) {
            if (statistics.month == month){
                if (statistics.isExpense) {
                    int income = statistics.sumOfOne * statistics.quantity;
                    extendItems.put(statistics.itemName, extendItems.getOrDefault(statistics.itemName, 0) + income);
                }
            }
        }
        return maxOrMin(extendItems);
    }

    public HashMap<String, Integer> maxOrMin(HashMap<String, Integer> map) {
        HashMap<String, Integer> maxOrMin = new HashMap<>();
        String NameItem = null;
        for (String nameItem : map.keySet()) {
            if (NameItem == null){
                NameItem = nameItem;
                continue;
            }
            if (map.get(NameItem) < map.get(nameItem)){
                NameItem = nameItem;
            }
        }
        maxOrMin.put(NameItem, map.get(NameItem));
        return maxOrMin;
    }


    public void getStatisticMonth() {
        System.out.println("Информация о всех месячных отчётах\n");
        for (int month = 1; month <= numberMonth; month++) {
            System.out.println(nameMonth(month));
            for (String nameItem : maxIncomeItem(month).keySet()) {
                System.out.println("Самая большая прибыль: " + nameItem + " - "+ maxIncomeItem(month).get(nameItem));
            }
            for (String nameItem : minIncomeItem(month).keySet()) {
                System.out.println("Самая большая прибыль: " + nameItem + " - "+ minIncomeItem(month).get(nameItem) + "\n");
            }
        }
    }


    public String nameMonth(int numberMonth) {
        String nameMonth = null;
        for (int i = 0; i <= monthly.length; i++) {
            if (numberMonth == (i + 1)) {
                nameMonth = monthly[i];
            }
        }
        return nameMonth;
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
