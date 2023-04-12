import java.util.HashMap;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    private boolean check = true;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean check(){

        HashMap<Integer, HashMap<Boolean, Integer>> monthStatistic = new HashMap<>();
        for (Statistics statistics : monthlyReport.monthStatistic) {
            if (!monthStatistic.containsKey(statistics.month)){
                monthStatistic.put(statistics.month, new HashMap<>());
            }
            HashMap<Boolean, Integer> statisticToCount = monthStatistic.get(statistics.month);
            int sum = statistics.quantity * statistics.sumOfOne;
            statisticToCount.put(statistics.isExpense, statisticToCount.getOrDefault(statistics.isExpense, 0) + sum);
        }

        HashMap<Integer, HashMap<Boolean, Integer>> yearStatistic = new HashMap<>();
        for (Statistics statistics : yearlyReport.yearlyStatistics) {
            if (!yearStatistic.containsKey(statistics.month)){
                yearStatistic.put(statistics.month, new HashMap<>());
            }
            HashMap<Boolean, Integer> statisticToCount = yearStatistic.get(statistics.month);
            statisticToCount.put(statistics.isExpense,
                    statisticToCount.getOrDefault(statistics.isExpense, 0) + statistics.amount);
        }

        for (Integer month : yearStatistic.keySet()) {
            HashMap<Boolean, Integer> statisticToCountMonth = monthStatistic.get(month);
            HashMap<Boolean, Integer> statisticToCountYear = yearStatistic.get(month);


            for (Boolean aBoolean : statisticToCountYear.keySet()) {
                int countByYear = statisticToCountYear.get(aBoolean);
                int countByMonth = statisticToCountMonth.getOrDefault(aBoolean, 0);

                if (countByYear != countByMonth) {
                    System.out.println("Ошибка!");
                    System.out.println("В месяце " + month);
                    if (aBoolean) {
                        System.out.println("Не совпадают траты");
                    } else {
                        System.out.println("Не совпадают доходы");
                    }
                    System.out.println("Данные годового отчета: " + countByYear);
                    System.out.println("Данные месячного отчета: " + countByMonth);
                    check = false;
                } else {
                    check = true;
                }
            }
        }
        if (check) System.out.println("Отчеты прошли проверку!\n");
        return check;
    }
}
