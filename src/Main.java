import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(monthlyReport,yearlyReport);

        while (true){
            printMenu();
            System.out.println();
            int command = scanner.nextInt();
            System.out.println();
            switch (command) {
                case 0:
                    return;
                case 1:
                    monthlyReport.clear();
                    monthlyReport.reportCount();
                    System.out.println("Месячные отчеты подсчитаны!\n");
                    break;
                case 2:
                    yearlyReport.clear();
                    yearlyReport.yearlyReportConversion(2021,"resources/y.2021.csv" );
                    System.out.println("Годовой отчет подсчитан!\n");
                    continue;
                case 3:
                    if (monthlyReport.checkUseMonthly & yearlyReport.checkUseYearly) {
                        checker.check();
                    } else if (monthlyReport.checkUseMonthly | yearlyReport.checkUseYearly) {
                        if (!monthlyReport.checkUseMonthly){
                            System.out.println("Ошибка!");
                            System.out.println("Вы не сосчитали все месячные отчеты!\n");
                        }
                        if (!yearlyReport.checkUseYearly){
                            System.out.println("Ошибка!");
                            System.out.println("Вы не сосчитали годовой отчет!\n");
                        }
                    } else {
                        System.out.println("Ошибка!");
                        System.out.println("Вы не сосчитали месячные и годовой отчет!\n");
                    }
                    break;
                case 4:
                    if (monthlyReport.checkUseMonthly) {
                        monthlyReport.getStatisticMonth();
                        System.out.println();
                    } else {
                        System.out.println("Ошибка!");
                        System.out.println("Вы не сосчитали все месячные отчеты!\n");
                    }
                    break;
                case 5:
                    if (yearlyReport.checkUseYearly) {
                        yearlyReport.ProfitOfMouth();
                        System.out.println();
                    } else {
                        System.out.println("Ошибка!");
                        System.out.println("Вы не сосчитали годовой отчет!\n");
                    }
                    break;
                default:
                    System.out.println("Вы ввели неверное значение!\n");
            }
        }
    }


    public static void printMenu() {
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выход");
    }
}

