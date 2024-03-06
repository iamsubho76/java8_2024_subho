package com.javatechie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapVsFlatMap {

        public static void main(String[] args) {

                //EkartDataBase ekartDataBase = new EkartDataBase();
                List<Customer> customers = EkartDataBase.getAll();

                // List<Customer> convert List<String> -> Data Transformation
                // mapping : customer -> customer.getEmail()
                // customer -> customer.getEmail() one to one mapping
                List<String> emails = customers.stream()
                                .map(customer -> customer.getEmail())
                                .collect(Collectors.toList());
                System.out.println("\n" + "Emails using map():: " + emails + "\n");

                // customer -> customer.getPhoneNumbers() ->> one to many mapping
                List<List<String>> phoneNumbers = customers.stream().map(customer -> customer.getPhoneNumbers())
                                .collect(Collectors.toList());
                System.out.println("phoneNumbers using map():: " + phoneNumbers + "\n");

                // List<Customer> convert List<String> -> Data Transformation
                // mapping : customer -> phone Numbers
                // customer -> customer.getPhoneNumbers() ->> one to many mapping
                List<String> phones = customers.stream()
                                .flatMap(customer -> customer.getPhoneNumbers().stream())
                                .collect(Collectors.toList());
                System.out.println("phoneNumbers using flatMap():: " + phones + "\n");
        }
}


class Customer {

        private int id;
        private String name;
        private String email;
        private List<String> phoneNumbers;

        public Customer() {
        }

        public Customer(int id, String name, String email, List<String> phoneNumbers) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.phoneNumbers = phoneNumbers;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public List<String> getPhoneNumbers() {
                return phoneNumbers;
        }

        public void setPhoneNumbers(List<String> phoneNumbers) {
                this.phoneNumbers = phoneNumbers;
        }
}

class EkartDataBase {

        public static List<Customer> getAll() {
                return Stream.of(
                        new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")),
                        new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947")),
                        new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236")),
                        new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467"))
                ).collect(Collectors.toList());
        }
}

