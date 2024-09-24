package org.example.task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
    static List<Employee> employes = List.of( new Employee(25,"Вася", "Сторож")
            ,new Employee(26,"Петя", "Инженер")
            ,new Employee(35,"Маша", "Инженер")
            ,new Employee(29,"Леон", "Инженер")
            ,new Employee(48,"Платон", "Инженер")
            ,new Employee(28,"Света", "Инженер")
            ,new Employee(28,"Света", "Инженер")
            ,new Employee(45,"Савелий", "Директор")
            ,new Employee(79,"Сережа", "Просто живет тут")
            ,new Employee(50,"Галя", "Бухгалтер")
            ,new Employee(5,"Матроскин", "Кот")
            ,new Employee(51,"Тимофей ", "Повар")
            ,new Employee(50,"Василиса", "Кладовщик"));

    static List<String> words = List.of("симулянт", "гопкомпания", "кружева", "компрометировать", "кружева", "оттачивать","вершина", "новозеландцы","вершина", "уборка", "окурить", "хлопоты", "вершина");
    static String wordsInLine = String.join(" ", words);

    static String[] wordsArray = {"Покормиться Бандит Пробуждать Фронт Обстригать"
    ,"Подмесить Перевоплотить Обсчитаться Раскалить Злоречивый"
    ,"Прохаживаться Ополченец Окроплять Конспектировать Обмануться"
    ,"Воображение Коломенок Новоявленный Поручик Злить"
    ,"Ампер Сочиться Влиять Подстрекательство Навербовать"};

    public static void main(String[] args) {
        Integer[] intArr = {5, 2, 25, 10,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9, 9, 4, 3, 10, 1, 13};

        System.out.println("\n========================== 01");
        System.out.println(Stream.of(intArr)
                .distinct()
                .collect(Collectors.toList()));

        intArr = new Integer[]{5, 2, 10, 9, 4, 3, 10, 1, 13}; //Чтобы с условиями совападло
        System.out.println("\n========================== 02");
        System.out.println(Stream.of(intArr)
                .sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow()//.limit(1).toList()
        );

        System.out.println("\n========================== 03");
        System.out.println(Stream.of(intArr)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow() //.limit(1).toList()
        );


        System.out.println("\n========================== 04");
        employes.stream()
                .filter(i -> i.function.equals("Инженер"))
                .sorted((a,b) -> b.age.compareTo(a.age))
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n========================== 05");
        System.out.println(employes.stream()
                .filter(i -> i.function.equals("Инженер"))
                .collect(Collectors.averagingDouble(Employee::getAge)));

        System.out.println("\n========================== 06");
        System.out.println(words.stream()
                .max(Comparator.comparingInt(String::length)).orElse(""));

        System.out.println("\n========================== 07");
        System.out.println(
                Arrays.stream(wordsInLine.split("\s"))
                .collect(Collectors.groupingBy(it->it,Collectors.counting()))
        );

        System.out.println("\n========================== 08");
        words.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .forEach(System.out::println);


        System.out.println("\n========================== 09");
        System.out.println(Arrays.stream(wordsArray)
                .flatMap(line -> Arrays.stream(line.split("\s")))
                .max(Comparator.comparing(String::length))
                .orElse(""));
    }
}