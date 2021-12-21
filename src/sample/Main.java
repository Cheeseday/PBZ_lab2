package sample;

public class Main{

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.addRank(1, 1.25);
        controller.addRank(2, 1.5);
        controller.addRank(3, 1.75);
        controller.addRank(4, 2);
        controller.addRank(5, 2.25);

        controller.addEmployee("Матросская Алла", "Менеджер по продажам", 1, true);
        controller.addEmployee("Антонов Александр", "Учитель", 4, true);
        controller.addEmployee("Жариков Андрей", "Дворник", 2, false);
        controller.addEmployee("Иванова Анастасия", "Бармен", 3, false);
        controller.addEmployee("Камлач Антон", "Официант", 4, true);

        //controller.deleteEmployee("Камлач Антон");

        //controller.deleteRank(2);

        //controller.editEmployee("Антонов Александр", "Учитель", 4, true);

        //controller.editRank(3, 1.25);

        //controller.printRanks();

        //controller.printEmployees();

        //controller.printSalaryDetails("Филиппова Ева");
    }
}
