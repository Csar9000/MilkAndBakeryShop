package com.company;
import java.io.*;

public class CompleteTasks implements Serializable {
    private String taskA;
    private int[] taskB;
    private String[] taskC;


    public CompleteTasks(){ //Конструктор по умолчанию

        this.taskA = "Недостаточно данных для решения задач!";
        this.taskB = new int[]{0,0,0};
        this.taskC = new String[]{"Недостаточно данных для решения задач!"};
    }

    public CompleteTasks(int[] Arr, int operand, String sign){ //Конструктор с параметрами
        this.taskA = completeTaskA();
        this.taskB = completeTaskB(Arr,operand,sign);
        this.taskC = completeTaskC();
    }


    public CompleteTasks (CompleteTasks str) throws IOException, ClassNotFoundException// Конструктор копирования
    {
        // Сохранение объекта в файл
        FileOutputStream file = new FileOutputStream("file.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        // Метод сериализации объекта
        out.writeObject(str);
        out.close();
        file.close();

        CompleteTasks copy = null;
        // Чтение объекта из файла
        FileInputStream file1 = new FileInputStream("file.ser");
        ObjectInputStream in = new ObjectInputStream(file1);
        // Способ десериализации объекта
        copy = (CompleteTasks) in.readObject();
        in.close();
        file1.close();
        setTasks(copy.taskA, copy.taskB,copy.taskC);
    }

    public String getTaskA(){
        return this.taskA;
    }

    public int [] getTaskB() {
        return this.taskB;
    }
    public String[] getTaskC() {
        return this.taskC;
    }

    public void setTasks(String taskA,int[]taskB,String[] taskC) {
        this.taskA = taskA;
        this.taskB = taskB;
        this.taskC = taskC;
    }

    public String completeTaskA() {
        int[] array = new int[50];

        array[0] = array[1] = 1;

        int i = 0;

        while (i < 50 - 2) {
            array[i + 2] = array[i + 1] + array[i];

            i++;
        }
        i = 0;

        String answerA = "";
        while (i < 10) {
            if (array[i] % 2 == 0) {
                answerA+= "*" + array[i] + " ";
            } else {
                answerA+=array[i] + " ";
            }
            i++;
        }
        answerA = " числовой ряд Фибоначи: "+ answerA;
        return answerA;
    }

    public int[] completeTaskB(int[] Arr,int operand,String sign) {

        switch (sign) {
            case "+":
                for (int i = 0; i < Arr.length; i++) {
                    Arr[i] = Arr[i] + operand;
                }
                break;
            case "-":
                for (int i = 0; i < Arr.length; i++) {
                    Arr[i] = Arr[i] - operand;
                }
                break;
            case "*":
                for (int i = 0; i < Arr.length; i++) {
                    Arr[i] = Arr[i] * operand;
                }
                break;
            case "/":
                for (int i = 0; i < Arr.length; i++) {
                    Arr[i] = Arr[i] / operand;
                }
                break;
        }

        return Arr;
    }



    public static int binaryCodeToDecimal(String str) {
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

    public String[] completeTaskC() {

        String answerC[] =new String[3];
        String str = "";

        for (int i = 0; i < 5; i++) {
            if (i != 4) {
                str += Integer.toBinaryString(1 + (int) (Math.random() * 1000)) + ",";
            } else {
                str += Integer.toBinaryString(1 + (int) (Math.random() * 1000));
            }
        }
        answerC[0]="\nСгенерированная строка двоичных чисел: " + str;


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
        answerC[1]="Упорядоченная строка двоичных чисел в десятичном представлении : " + Decimals;
        answerC[2]="Упорядоченная строка двоичных чисел: " + BinaryString;
        return answerC;
    }

}
