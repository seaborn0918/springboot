package com.bitc.security1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class Security1Application {

  public static void main(String[] args) {
    SpringApplication.run(Security1Application.class, args);

    String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"};
    List<String> nameList = Arrays.asList(nameArr);

    Stream<String> nameStream = nameList.stream();
    Stream<String> arrayStream = Arrays.stream(nameArr);


    // 원본의 데이터를 변경하지 않음

    // long cnt = nameStream.count();
    // stream은 일회용. 한번 사용이 끝나면 재사용 불가. IllegalStateException 발생

    // 내부 반복으로 작업을 처리한다.
    nameStream.sorted().forEach(System.out::println);
    arrayStream.sorted().forEach(System.out::println);

    // 스트림 연산 종류 : 생성, 가공, 결과
    // 1. stream 객체 생성 : 재사용 불가. 닫히면 재생성해주어야 한다.
    // 2. 가공하기 : 원본의 데이터를 별도의 데이터로 가공하기 위한 중간 연산. 연산결과를 stream으로 반환하기 때문에 연속해서 중간연산을 이어갈 수 있음
    // 3. 결과 : 최종 연산. Stream의 요소들을 소모하면서 연산이 수행되기 때문에 1번만 처리가능하다.

    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    myList.stream() // 생성
        .filter(s -> s.startsWith("c")) // 가공
        .map(String::toUpperCase)
        .sorted()
        .forEach(System.out::println);// 결과

    // stream 연산은 매개변수로 함수형 인터페이스 받고 람다식은 반환값으로 함수형 인터페이스 반환

    // 람다식 : 함수를 하나의 식으로 표현. 메소드의 이름 필요 없음. 익명함수의 한 종류. 익명함수는 모두 일급 객체이고 변수처럼 사용가능하며 매개변수로 전달 가능
    // 람다식 내에서 사용되는 지역변수는 final이 붙지 않아도 상수로 간주됨
    // 람다식으로 선언된 변수명은 다른 변수명과 중복될 수 없다

    // Collection의 stream 생성
    List<String> list = Arrays.asList("a", "b", "ca");
    Stream<String> listStream = list.stream();

    // 배열
    Stream<String> arrayStream1 = Stream.of("a", "b", "c");
    Stream<String> arrayStream2 = Arrays.stream(new String[]{"a", "b", "c"});

    // 원시 타입
    IntStream intStream = IntStream.range(4, 10);

    // 2. Stream 가공하기(중간연산)
    // 필터링 - filter : 조건에 맞는 데이터만을 정제하여 더작은 컬렉션을 만들어냄
    Stream<String> filterStream = list.stream()
        .filter(name -> name.contains("a"));

    // 데이터 변환 - map
    // 기존 스트림 요소들을 변환하여 새로운 스트림을 형성. 저장된 값을 특정한 형태로 변환하는데 주로 사용
    Stream<String> upper =
        list.stream()
            .map(s -> s.toUpperCase());

    Stream<File> fileStream = Stream.of(new File("test1.java"), new File("test2.java"), new File("test3.java"));

    Stream<String> fileNameStream = fileStream.map(File::getName);

    // 정렬 - sorted
    List<String> list2 = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

    Stream<String> ascStream = list2.stream().sorted(); // 오름차순
    Stream<String> descStream = list2.stream().sorted(Comparator.reverseOrder()); // 내림차순

    // 중복제거 - distinct
    List<String> list3 = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

    Stream<String> stream = list3.stream()
        .distinct();

    Employee e1 = new Employee("LCS");
    Employee e2 = new Employee("LCS");
    List<Employee> employees = new ArrayList<>();
    employees.add(e1);
    employees.add(e2);
    int size = employees.stream()
        .distinct().toList().size();
    System.out.println(size); // 중복 제거 안됨

    // 특정 연산 수행 - peek
    // 스트림 요소들을 대상으로 스트림에 영향을 주지 않고 특정 연산을 수행하기 위함. 결과에 영향을 주지 않는다
    int sum = IntStream.of(1, 3, 5, 7, 9)
        .peek(System.out::println)
        .sum();
    System.out.println(sum);

    // 원시 Stream <-> Stream
    // IntStream -> Stream<String>
    String toStream = IntStream.range(1, 4).mapToObj(i -> "a" + i).collect(Collectors.joining(","));
    System.out.println(toStream);

    // Stream<Double> -> IntStream -> Stream<String>
    String toStream2 = Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).collect(Collectors.joining(", "));
    System.out.println(toStream2);

    Stream<Double> doubleStream = Stream.of(1.0, 2.0, 3.0);
  }
}

class Employee {
  private String name;

  public Employee(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  // distinct 적용하기 위해서 equals와 hashCode 오버라이드
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
