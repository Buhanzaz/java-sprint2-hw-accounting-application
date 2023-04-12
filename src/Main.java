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
            switch (command) {
                case 0:
                    return;
                case 1:
                    monthlyReport.reportCount();
                    System.out.println("Месячные отчеты подсчитаны!\n");
                    break;
                case 2:
                    yearlyReport.yearlyReportConversion(2021,"resources/y.2021.csv" );
                    System.out.println("Годовой отчет подсчитан!\n");
                    continue;
                case 3:
                    checker.check();
                    break;
                case 4:
                    monthlyReport.getStatisticMonth();
                    break;
                case 5:
                    System.out.println(yearlyReport.averageExpensesAndIncome(false)); // income
                    System.out.println(yearlyReport.averageExpensesAndIncome(true)); // expense
                    break;
                default:
                    System.out.println("Вы ввели неверное значение!");
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

