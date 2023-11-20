package calculator;

import java.util.Scanner;

public class CalculatorMain {

    public static void main(String[] args) {
        System.out.println("Введите выражение для вычисления. Доступные арифметические действия: + - * / (a+b)(III*V) \n" +
                "Числа могут быть либо арабскими, либо римскими, но не больше 10(X)");
        Scanner in = new Scanner(System.in);
        System.out.println(calc(in.nextLine()));
    }

    public static String calc(String input){
        input = input.replaceAll("\\s","").toUpperCase();
        RomanNumberConverter romanNumberConverter = new RomanNumberConverter();
        boolean arabic = false;
        boolean roman = false;
        int operationCharAt = 0;
        int operationCount = 0;
        String leftFromOperation;
        String rightFromOperation;
        String answer = "";

        for (int i = 0; i < input.length(); i++) {

            if(Character.isDigit(input.charAt(i))){
                arabic = true;
            }

            if(Character.isLetter(input.charAt(i))){
                if(isRomanNumber(input.charAt(i))) {
                    roman = true;
                }else {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Разрешено вводить только арабские, либо римские числа");
                        break;
                    }
//                    return "Разрешено вводить только арабские, либо римские числа";
                }
            }

            if(input.charAt(i) == '+' || input.charAt(i) == '-' ||
               input.charAt(i) == '*' || input.charAt(i) == '/'){
                operationCount++;
                operationCharAt = i;
                if(operationCount > 1) try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Разрешено только одно арифметическое действие");
                }
//                if(operationCount > 1) return "Разрешено только одно арифметическое действие";
            }
        }

        if(arabic == true & roman == true)
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Одновременно можно использовать только арабские, либо римские числа");
            }
//            return "Одновременно можно использовать только арабские, либо римские числа";

        if(operationCharAt != 0 & !input.substring(operationCharAt + 1).equals("")) {

            leftFromOperation = input.substring(0, operationCharAt);
            rightFromOperation = input.substring(operationCharAt + 1);

            if (arabic == true) {
// -----------------Запрет на использование чисел больше 10------------------
                if(Integer.valueOf(leftFromOperation) > 10 || Integer.valueOf(rightFromOperation) > 10)

                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Разрешено использовать числа не больше 10(X)");
                    }
//                    return "Разрешено использовать числа не больше 10(X)";
//------------------Убрать эту часть !!!В ДВУХ МЕСТАХ!!!, чтобы использовать все числа---------------------------
                switch (input.charAt(operationCharAt)){
                    case ('+'): answer =
                            String.valueOf(Integer.valueOf(leftFromOperation) + Integer.valueOf(rightFromOperation));
                            break;
                    case ('-'): answer =
                            String.valueOf(Integer.valueOf(leftFromOperation) - Integer.valueOf(rightFromOperation));
                            break;
                    case ('*'): answer =
                            String.valueOf(Integer.valueOf(leftFromOperation) * Integer.valueOf(rightFromOperation));
                            break;
                    case ('/'): answer =
                            String.valueOf(Integer.valueOf(leftFromOperation) / Integer.valueOf(rightFromOperation));
                            break;
                }
            }

            if(roman == true){
                int romanLeftFromOperation = romanNumberConverter.romanToInt(leftFromOperation);
                int romanRightFromOperation = romanNumberConverter.romanToInt(rightFromOperation);
// -----------------Запрет на использование чисел больше 10------------------
                if(romanLeftFromOperation > 10 || romanRightFromOperation > 10)
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Разрешено использовать числа не больше 10(X)");
                    }
//                    return "Разрешено использовать числа не больше 10(X)";
//------------------Убрать эту часть !!!В ДВУХ МЕСТАХ!!!, чтобы использовать все числа---------------------------
                switch (input.charAt(operationCharAt)){
                    case ('+'): answer =
                            romanNumberConverter.toRoman(romanLeftFromOperation + romanRightFromOperation);
                        break;

                    case ('-'): try {
                            throw new Exception();
                        } catch (Exception e) {
                            System.out.println("В римской системе нет отрицательных чисел");
                        }
//                    case ('-'): answer =
//                            "В римской системе нет отрицательных чисел";
                    case ('*'): answer =
                            romanNumberConverter.toRoman(romanLeftFromOperation * romanRightFromOperation);
                        break;
                    case ('/'): answer =
                            romanNumberConverter.toRoman(romanLeftFromOperation / romanRightFromOperation);
                        break;
                }
            }
            return answer;
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите выражение корректно. Пример:(A+B)/(V*III)");
                throw new ArithmeticException();
            }
//            return "Введите выражение корректно. Пример:(A+B)/(V*III)";
        }
    }

    public static boolean isRomanNumber(char a){
        boolean answer = false;
        if(a == 'I') answer = true;
        if(a == 'V') answer = true;
        if(a == 'X') answer = true;
        if(a == 'L') answer = true;
        if(a == 'C') answer = true;
        if(a == 'D') answer = true;
        if(a == 'M') answer = true;
        return answer;
    }
}

