package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class L2V9 {


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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        int size = userInput("Введите размерность массива: ");

        while (size <= 0) {
            size = userInput("Неверный ввод! Размерность массива не может быть меньше или равной 0\n");
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

        CompleteTasks cls = new CompleteTasks(array,num,sign);

        System.out.println("Пример с введёнными данными");
        outputAnswers(cls.getTaskA(),cls.getTaskB(),cls.getTaskC());




        int[] test = new int[]{ 10,100,1000,10000,100000,1000000,10000000,100000000,1000000000,1000000005 };
        CompleteTasks str1 = new CompleteTasks();
        CompleteTasks str2 = new CompleteTasks(test,5,"/");
        CompleteTasks str3 = new CompleteTasks(str2);
        
        String A;
        int[] B;
        String[] C;

        System.out.println("\n");
        System.out.println("Работа конструкторов:");
        System.out.println("\n");
        System.out.println("Конструктор по умолчанию");
        A = str1.getTaskA();
        B = str1.getTaskB();
        C = str1.getTaskC();
        outputAnswers(A,B,C);
        System.out.println("\n");
        System.out.println("Конструктор с параметрами");
        A = str2.getTaskA();
        B = str2.getTaskB();
        C = str2.getTaskC();
        outputAnswers(A,B,C);

        System.out.println("\n");
        System.out.println("Конструктор копирования");
        A = str3.getTaskA();
        B = str3.getTaskB();
        C = str3.getTaskC();
        outputAnswers(A, B, C);

    }
    public static void outputAnswers(String taskA,int[]Arr,String[] taskC){
        System.out.print("Задание А: ");
        System.out.println(taskA);
        System.out.print("Задание B: ");
        for(int i = 0;i< Arr.length;i++){
            System.out.print(Arr[i]+" ");
        }
        System.out.print("\nЗадание С: ");
        for(int i = 0;i< taskC.length;i++){
            System.out.println(taskC[i]+" ");
        }
    }
}


