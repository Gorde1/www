import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первый символ");
        String choice = scanner.next();

        if (Character.isDigit(choice.charAt(0))) {

            double number1 = Double.parseDouble(choice);

            System.out.println("Введите второе число:");
            double number2 = scanner.nextDouble();

            if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
                System.out.println("Ошибка: числа должны быть в диапазоне от 1 до 10.");
                return;
            }

            System.out.println("Введите операцию (+, -, *, /):");
            char operator = scanner.next().charAt(0);

            double result = calculate(number1, number2, operator);
            System.out.println("Результат: " + result);
        } else if (Character.isLetter(choice.charAt(0))) {
            String roman1 = choice;

            System.out.println("Введите второе римское число:");
            String roman2 = scanner.next();

            if (!roman1.matches("^[IVXLCDM]+$") || !roman2.matches("^[IVXLCDM]+$")) {
                System.out.println("Ошибка: недопустимые римские числа.");
                return;
            }

            System.out.println("Введите операцию (+, -, *, /):");
            char operator = scanner.next().charAt(0);

            int arabic1 = romanToArabic(roman1);
            int arabic2 = romanToArabic(roman2);

            double result = calculate(arabic1, arabic2, operator);
            String romanResult = arabicToRoman((int) result);

            System.out.println("Результат: " + romanResult);
        } else {
            System.out.println("Ошибка: некорректный выбор системы!");
        }

    }

    public static double calculate(double num1, double num2, char operator) {
        double result = 0;

        switch (operator) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println("Ошибка: деление на ноль!");
                    System.exit(1);
                }
                result = num1 / num2;
            }
            default -> {
                System.out.println("Ошибка: некорректный оператор!");
                System.exit(1);
            }
        }

        return result;
    }

    public static int romanToArabic(String roman) {
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int curValue = romanMap.get(roman.charAt(i));

            if (curValue < prevValue) {
                result -= curValue;
            } else {
                result += curValue;
            }

            prevValue = curValue;
        }

        return result;
    }

    public static String arabicToRoman(int arabic) {
        String[] romanNumerals = {
                "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        int[] values = {
                1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (arabic >= values[i]) {
                arabic -= values[i];
                roman.append(romanNumerals[i]);
            }
        }

        return roman.toString();
    }
}
