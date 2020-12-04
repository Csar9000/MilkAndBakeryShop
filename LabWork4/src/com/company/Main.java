package com.company;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.Start();
    }
    public static int userInput(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int i;
        while (true) {
            try {
                i = Integer.parseInt(sc.next());
                if (i<0)
                {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите целое положительное число!");
            }
        }
        return i;
    }

}

