import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport("resources/y.2021.csv");
        MonthlyReport monthlyReport = new MonthlyReport();


        printMenu();

        while (true){
            int command = scanner.nextInt();
            switch (command) {
                case 0:
                    return;
                case 1:
                    monthlyReport.constructorOfPath();
                    monthlyReport.getMonthlyReport();
                    monthlyReport.onlyMonthlyExpenses();
                    monthlyReport.onlyMonthlyIncome();
                    break;
                case 2:
                    yearlyReport.getYearlyReport();
                    System.out.println(yearlyReport.sumYearlyReport());
                    break;
                case 3:
                    break;
                case 4:
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

