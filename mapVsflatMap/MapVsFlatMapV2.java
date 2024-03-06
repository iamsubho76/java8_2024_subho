import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Address {
    private String street;
    private String city;
    private String state;

    private Employee employee;

    public Address(String street, String city, String state, Employee employee) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.employee = employee;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

class Employee {
    private String name;
    private List<Address> addresses;

    public Employee(String name, Address... addresses) {
        this.name = name;
        this.addresses = Arrays.asList(addresses);
    }

    public String getName() {
        return name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}

public class MapVsFlatMapV2 {
    public static void main(String[] args) {
        Employee employee1 = new Employee("John",
                new Address("123 Main St.", "Anytown", "Anycity", null),
                new Address("456 Elm St.", "Anothertown", "Anothercity", null));

        Employee employee2 = new Employee("Jane",
                new Address("789 Oak St.", "Bangalore", "Somewherecity", null),
                new Address("321 Pine St.", "Anytown", "Anycity", null));

        Employee employee3 = new Employee("James",
                new Address("10th Cross", "Bangalore", "Somewherecity", null),
                new Address("XYZ Road", "Anothertown", "Anothercity", null));

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3);

        employees.forEach(emp -> emp.getAddresses().forEach(addr -> addr.setEmployee(emp)));

        List<String> bangaloreEmployees = employees.stream()
                .flatMap(emp -> emp.getAddresses().stream())
                .filter(addr -> addr.getCity().equalsIgnoreCase("Bangalore"))
                .map(addr -> addr.getEmployee().getName())
                .distinct()
                .collect(Collectors.toList());

        // below one is also important
        // List<String> bangaloreEmployees = employees.stream()
        //         .filter(emp -> emp.getAddresses().stream().anyMatch(addr -> addr.getCity().equalsIgnoreCase("Bangalore")))
        //         .map(Employee::getName)
        //         .collect(Collectors.toList());

        System.out.println(bangaloreEmployees);
    }
}