package ghub.fr.menu.shop.grades;

import ghub.fr.menu.api.persistentData;

public class gradesPrices {
    public static int prices(persistentData.customKey customKey) {
        switch (customKey) {
            case aventurier:
                return 25_000;
            default:
                return Integer.MAX_VALUE;
        }
    }
}
