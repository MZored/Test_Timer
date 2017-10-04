package com.example.mzored.timer;

public class NumbToStr {

    private StringBuffer summa = new StringBuffer();
    private StringBuffer part = new StringBuffer();
    private Boolean isNil = false;

    private String[] tisyachi = {"тысяча ", "тысячи ", "тысяч "};
    private String[] millioni = {"миллион ", "миллиона ", "миллионов "};
    private String[] milliardi = {"миллиард ", "миллиарда ", "миллиардов "};
    private String[] trillioni = {"триллион ", "триллиона ", "триллионов "};
    private String[][] currency = {
            {" ", " ", " "},
            {" ", " ", " "}
    };

    private StringBuffer getPart() {
        return this.part;
    }

    private void clearSumma() {
        this.summa = new StringBuffer();
    }

    private StringBuffer getSumma() {
        return this.summa;
    }

    private void parseEdinici(char edinici, int triad) {
        if (edinici != '0') isNil = false;
        switch (edinici) {
            case '1':
                getPart().append((triad == 1) ? "одна " : "один ");
                if (triad != 0) getPart().append(getNames(triad)[0]);
                break;
            case '2':
                getPart().append("два ");
                if (triad != 0) getPart().append(getNames(triad)[1]);
                break;
            case '3':
                getPart().append("три ");
                if (triad != 0) getPart().append(getNames(triad)[1]);
                break;
            case '4':
                getPart().append("четыре ");
                if (triad != 0) getPart().append(getNames(triad)[1]);
                break;
            case '5':
                getPart().append("пять ");
                if (triad != 0) getPart().append(getNames(triad)[2]);
                break;
            case '6':
                getPart().append("шесть ");
                if (triad != 0) getPart().append(getNames(triad)[2]);
                break;
            case '7':
                getPart().append("семь ");
                if (triad != 0) getPart().append(getNames(triad)[2]);
                break;
            case '8':
                getPart().append("восемь ");
                if (triad != 0) getPart().append(getNames(triad)[2]);
                break;
            case '9':
                getPart().append("девять ");
                if (triad != 0) getPart().append(getNames(triad)[2]);
                break;
            case '0':
                if (triad != 0 && !isNil) getPart().append(getNames(triad)[2]);
        }
        insert();
    }

    private void parseDecyatki(char edinici, int triad) {
        switch (edinici) {
            case '1':
                getPart().append("одиннадцать ");
                break;
            case '2':
                getPart().append("двенадцать ");
                break;
            case '3':
                getPart().append("тринадцать ");
                break;
            case '4':
                getPart().append("четырнадцать ");
                break;
            case '5':
                getPart().append("пятнадцать ");
                break;
            case '6':
                getPart().append("шестнадцать ");
                break;
            case '7':
                getPart().append("семнадцать ");
                break;
            case '8':
                getPart().append("восемнадцать ");
                break;
            case '9':
                getPart().append("девятнадцать ");
                break;
            case '0':
                getPart().append("десять ");
        }
        if (triad == 0) {
            insert();
        } else {
            getPart().append(getNames(triad)[2]);
            insert();
        }
    }

    private void parseDesyatki(char desyatki, char edinici, int triad) {
        switch (desyatki) {
            case '1':
                parseDecyatki(edinici, triad);
                break;
            case '2':
                getPart().append("двадцать ");
                parseEdinici(edinici, triad);
                break;
            case '3':
                getPart().append("тридцать ");
                parseEdinici(edinici, triad);
                break;
            case '4':
                getPart().append("сорок ");
                parseEdinici(edinici, triad);
                break;
            case '5':
                getPart().append("пятьдесят ");
                parseEdinici(edinici, triad);
                break;
            case '6':
                getPart().append("шестьдесят ");
                parseEdinici(edinici, triad);
                break;
            case '7':
                getPart().append("семьдесят ");
                parseEdinici(edinici, triad);
                break;
            case '8':
                getPart().append("восемьдесят ");
                parseEdinici(edinici, triad);
                break;
            case '9':
                getPart().append("девяносто ");
                parseEdinici(edinici, triad);
                break;
            case '0':
                isNil = true;
                parseEdinici(edinici, triad);
        }
        if (desyatki != '0') isNil = false;
    }

    private void parseSotni(char sotni) {
        switch (sotni) {
            case '1':
                getPart().append("сто ");
                break;
            case '2':
                getPart().append("двести ");
                break;
            case '3':
                getPart().append("триста ");
                break;
            case '4':
                getPart().append("четыреста ");
                break;
            case '5':
                getPart().append("пятьсот ");
                break;
            case '6':
                getPart().append("шестьсот ");
                break;
            case '7':
                getPart().append("семьсот ");
                break;
            case '8':
                getPart().append("восемьсот ");
                break;
            case '9':
                getPart().append("девятьсот ");
                break;
            case '0':
                isNil = true;
        }
        if (sotni != '0') isNil = false;
    }

    private int getCountOfDigitsInFirstTriad(int length) {
        int i = length % 3;
        return i == 0 ? 3 : i;
    }

    private String[] getNames(int triad) {
        switch (triad) {
            case 2:
                return millioni;
            case 3:
                return milliardi;
            case 4:
                return trillioni;
        }
        return tisyachi;
    }

    private void insert() {
        summa.insert(0, part);
        part = new StringBuffer();
    }

    private String getCurrency(int currency, Long money) {
        char[] charArray = money.toString().toCharArray();
        String rezult = "";
        switch (charArray[charArray.length - 1]) {
            default:
                rezult = this.currency[currency][2];
                break;
            case '1':
                rezult = this.currency[currency][0];
                break;
            case '2':
            case '3':
            case '4':
                rezult = this.currency[currency][1];
        }
        return rezult;
    }

    private StringBuffer long2string(Long money) {
        char[] charArray = money.toString().toCharArray();
        int length = charArray.length;
        // количество триад
        int triadCount = length % 3 > 0 ? length / 3 + 1 : length / 3;
        int charArrayIndex = length - 1;

        if (money == 0) {
            summa.append("");
        } else

            for (int i = 0; i < triadCount; i++) {
                if (i == triadCount - 1) {
                    switch (getCountOfDigitsInFirstTriad(length)) {
                        case 1:
                            parseEdinici(charArray[charArrayIndex], i);
                            break;
                        case 2:
                            parseDesyatki(
                                    charArray[charArrayIndex - 1],
                                    charArray[charArrayIndex],
                                    i);
                            break;
                        case 3:
                            parseSotni(charArray[charArrayIndex - 2]);
                            parseDesyatki(
                                    charArray[charArrayIndex - 1],
                                    charArray[charArrayIndex],
                                    i);
                    }
                } else {
                    parseSotni(charArray[charArrayIndex - 2]);
                    parseDesyatki(
                            charArray[charArrayIndex - 1],
                            charArray[charArrayIndex],
                            i);
                }
                charArrayIndex = charArrayIndex - 3;
            }
        return getSumma();
    }

    public String convert(Long money) {
        // если число отрицательное - делаем из него положительное
        if (money < 0) money *= -1;
        StringBuffer rezult = new StringBuffer();
        Long longPart = money.longValue();
        rezult.append(long2string(longPart));
        rezult.append(getCurrency(0, longPart));
        clearSumma();
        Long fractPart =
                (Long) Math.round((money.doubleValue() - longPart) * 100);
        rezult.append(long2string(fractPart));
        rezult.append(getCurrency(1, fractPart));
        return rezult.toString().trim();
    }
}
