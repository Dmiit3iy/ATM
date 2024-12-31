package model;

public interface Sdk {
    /**
     * Посчитать количество купюр определенного номинала в банкомате.
     * Эта операция занимает около 10 секунд, и шумная, её стоит вызывать как можно реже.
     *
     * @param banknote номинал купюры
     * @return количество купюр в банкомате
     */
    int countBanknotes(int banknote);

    /**
     * Переместить некоторое количество купюр одного номинала в лоток выдачи.
     *
     * @param banknote номинал купюры
     * @param count    количество купюр
     */
    void moveBanknoteToDispenser(int banknote, int count);

    /**
     * Открыть лоток выдачи.
     */
    void openDispenser();
}