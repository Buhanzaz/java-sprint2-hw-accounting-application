import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport("resources/y.2021.csv");
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(monthlyReport,yearlyReport);

        while (true){
            printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 0:
                    return;
                case 1:

                    System.out.println("Месячные отчеты подсчитаны!");
                    break;
                case 2:
                    continue;
                case 3:
                    //monthlyReport.ss();
                    checker.check();
                    break;
                case 4:
                    monthlyReport.getStatisticMonth();
                    break;
                case 5:
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

