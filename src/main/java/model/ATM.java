package model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ATM {
    private Sdk sdk;
    private List<Integer> denominationList = new ArrayList<>(List.of(5000, 1000, 500, 100, 50));

    /**
     * Метод для выдачи запрашиваемого количества купюр
     * @param amount
     * @return
     */
    public boolean giveMyMoneyB(int amount) {

        if (amount <= 0 || amount % 50 != 0) {
            return false;
        }

        //Количество доступных банкнот в банкомате
        Map<Integer, Integer> mapCountOfDenominations = countOfDenominations();

        Map<Integer, Integer> toDispense = getIntegerIntegerMap(amount, mapCountOfDenominations);


        giveOutMoney(toDispense);
        return true;
    }

    /**
     * Метод для расчета необходимого количества купюр к выдаче
     *
     * @param amount
     * @param mapCountOfDenominations
     * @return Map<Integer, Integer>, где <Номинал, Количество>
     */
    private static Map<Integer, Integer> getIntegerIntegerMap(int amount, Map<Integer, Integer> mapCountOfDenominations) {

        Map<Integer, Integer> toDispense = new HashMap<>();
        for (var i : mapCountOfDenominations.entrySet()) {
            int countNeeded = amount / i.getKey();
            int countAvailable = i.getValue();
            int countToUse = Math.min(countNeeded, countAvailable);
            if (countToUse > 0) {
                toDispense.put(i.getKey(), countToUse);
                amount -= countToUse * i.getKey();
            }
        }
        return toDispense;
    }

    /**
     * Метод для выдачи в лоток выдачи необходимого количества всех купюр
     *
     * @param toDispense Map<Integer, Integer>, где <Номинал, Количество>
     */
    private void giveOutMoney(Map<Integer, Integer> toDispense) {
        for (var entry : toDispense.entrySet()) {
            sdk.moveBanknoteToDispenser(entry.getKey(), entry.getValue());
        }
        sdk.openDispenser();
    }

    /**
     * Метод для подсчета количества доступных банкнот каждого номинала
     *
     * @return Map<Integer, Integer>, где <Номинал, Количество>
     */
    private Map<Integer, Integer> countOfDenominations() {
        return denominationList.stream().collect(Collectors.toMap(x -> x, x -> sdk.countBanknotes(x)));
    }

}
