package model;

public class StubSdk implements Sdk {
    @Override
    public int countBanknotes(int banknote) {
        return 0;
    }

    @Override
    public void moveBanknoteToDispenser(int banknote, int count) {
        System.out.printf("Перемещаю купюру %s в лоток выдачи, %s штук%n", banknote, count);
    }

    @Override
    public void openDispenser() {
        System.out.printf("Лоток выдачи открыт пользователю%n");
    }
}