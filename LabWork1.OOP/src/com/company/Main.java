package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void switchB()  {
        Scanner input = new Scanner(System.in);
        int size = userInput("Введите размерность массива: ");
        while (size <= 0) {
            size = userInput("Неверный ввод! Попробуйте ещё раз\n");
        }

        int array[] = new int[size];
        System.out.println("Введите значения элементов массива");
        for (int i = 0; i < size; i++) {
            array[i] = userInput("Введите " + (i) + " элемент массива: ");
        }

        int num = userInput("Введите число,с которым необходимо выполнить арифметическое действие: ");

        while (num == 0) {
            num = userInput("Неверный ввод! Попробуйте ещё раз\n");
        }

        System.out.println("\nВведите арифметическое действие,что необходимо выполнить : " + "\n" +
                "+ - сложение" + "\n" +
                "- - вычитание" + "\n" +
                "* - умножение" + "\n" +
                "/ -  деление" + "\n");
        String sign;

        sign = input.nextLine();

        while (!sign.equals("+") && !sign.equals("-") && !sign.equals("*") && !sign.equals("/")) {
            System.out.println("Неверный ввод арифметического знака!");
            sign = input.nextLine();
        }

        String answer = Logic.completeTaskB(sign, array, num);

        System.out.print("Вывод результата арифметического действия ");

        System.out.println("{" + " " + answer + "}");
    }

    public static void switchA(int []array)
    {
        int i = 0;

        System.out.print("Числовой ряд Фибоначи: \n");
        while (i < 10) {
            if (array[i] % 2 == 0) {
                System.out.print("*" + array[i] + " ");
            } else {
                System.out.print(array[i] + " ");
            }
            i++;
        }
    }

    public static int userInput(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int i;
        while (true) {
            try {
                i = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод!");
            }
        }
        return i;
    }

    public static void main(String[] args)  {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите букву задания, что нужно выполнить (a,b или c)");
        String TaskChoice;

        TaskChoice = input.nextLine();

        while (!TaskChoice.equals("a") && !TaskChoice.equals("b") && !TaskChoice.equals("c")) {
            System.out.println("Введите корректные данные");
            TaskChoice = input.nextLine();
        }

        switch (TaskChoice) {
            case "a": {
                int[] array=Logic.completeTaskA();

                Main.switchA(array);
                break;
            }
            case "b": {
               Main.switchB();
                break;
            }
            case "c": {
                System.out.print(Logic.completeTaskC());
                break;
            }
        }

    }

    public static class Logic {
        public static int[] completeTaskA() {
            int[] array = new int[50];

            array[0] = array[1] = 1;

            int i = 0;

            while (i < 50 - 2) {
                array[i + 2] = array[i + 1] + array[i];

                i++;
            }

            return array;
        }

        public static String completeTaskB(String sign, int[] array, int num) {

            String answer = "";
            switch (sign) {
                case "+":
                    for (int i = 0; i < array.length; i++) {
                        array[i] = array[i] + num;
                    }
                    break;
                case "-":
                    for (int i = 0; i < array.length; i++) {
                        array[i] = array[i] - num;
                    }
                    break;
                case "*":
                    for (int i = 0; i < array.length; i++) {
                        array[i] = array[i] * num;
                    }
                    break;
                case "/":
                    for (int i = 0; i < array.length; i++) {
                        array[i] = array[i] / num;
                    }
                    break;
            }
            for (int i = 0; i < array.length; i++) {

                answer += array[i] + ",";
            }
            return answer;

        }

        private static int binaryCodeToDecimal(String str) {
            char[] chars = str.toCharArray();
            int result = 0;
            int multiply = 1;
            for (int i = str.length() - 1; i >= 0; i--) {
                if (chars[i] == '1') {
                    result += multiply;
                }
                multiply *= 2;
            }
            return result;
        }

        public static String completeTaskC() {

            String answer3 ="";
            String str = "";

            for (int i = 0; i < 5; i++) {
                if (i != 4) {
                    str += Integer.toBinaryString(1 + (int) (Math.random() * 1000)) + ",";
                } else {
                    str += Integer.toBinaryString(1 + (int) (Math.random() * 1000));
                }
            }
            answer3+="\n" + "Сгенерированная строка двоичных чисел: " + str;


            String[] StrToArr = str.split(",");

            int[] OrderedArr = new int[StrToArr.length];

            for (int i = 0; i < OrderedArr.length; i++) {
                OrderedArr[i] = binaryCodeToDecimal(StrToArr[i]);
            }

            for (int i = 0; i < StrToArr.length - 1; i++) {
                for (int j = i + 1; j < OrderedArr.length; j++) {
                    if (OrderedArr[i] > OrderedArr[j]) {
                        int temp = OrderedArr[i];
                        OrderedArr[i] = OrderedArr[j];
                        OrderedArr[j] = temp;
                    }
                }
            }
            String BinaryString = "";
            String Decimals = "";
            for (int i = 0; i < OrderedArr.length; i++) {
                if (i != OrderedArr.length - 1) {
                    Decimals += OrderedArr[i] + ",";
                    BinaryString += Integer.toBinaryString(OrderedArr[i]) + ",";
                } else {
                    Decimals += OrderedArr[i];
                    BinaryString += Integer.toBinaryString(OrderedArr[i]);
                }
            }
            answer3+="\n" + "Упорядоченная строка двоичных чисел в десятичном представлении : " + Decimals;
            answer3 +="\n" + "Упорядоченная строка двоичных чисел: " + BinaryString;
            return answer3;
        }
    }
}
