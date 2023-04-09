import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    ArrayList<Statistics> statisticsArrayList = new ArrayList<>();
    HashMap<Integer, Integer> yearReport = new HashMap<>();
    public String path;

    public YearlyReport(String path) {
        this.path = path;
        reportConversion();
    }

    public void reportConversion(){
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++){
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            Statistics statistics = new Statistics(month, amount, isExpense);
            statisticsArrayList.add(statistics);
        }
    }

    public int sumYearlyReport(){
        int sumYear = 0;
        for (Integer amount : yearReport.values()){
            sumYear += amount;
        }
        return sumYear;
    }

    public void getYearlyReport(){
        // Год
        // Месяц, Прибыль,

        for (Statistics statistic : statisticsArrayList) {
            if (statistic.isExpense) {
                yearReport.put(statistic.month, yearReport.getOrDefault(statistic.month, 0) - statistic.amount);
            } else {
                yearReport.put(statistic.month, yearReport.getOrDefault(statistic.month, 0) + statistic.amount);
            }

        }
        System.out.println(yearReport);
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
