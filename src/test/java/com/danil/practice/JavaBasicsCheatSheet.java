package com.danil.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaBasicsCheatSheet {

    public static final int MAX_ATTEMPTS = 3;
    public static final double MIN_PASS_RATE = 90.0;
    public static int sharedCounter = 0;

    public static void main(String[] args) {
        // Шпаргалка: методы специально не вызываются автоматически.
        // Вызывай по одному нужному методу, например:
        // dataTypes();
        // operators();
        // conditions();
        // loops();
    }

    // =========================================================
    // 01. DATA TYPES
    // =========================================================
    public static void dataTypes() {
        byte smallNumber = 100;
        short shortNumber = 30000;
        int totalTests = 150;
        long largeNumber = 5_000_000_000L;
        float floatValue = 10.5F;
        double passRate = 95.75;
        char grade = 'A';
        boolean active = true;
        String username = "Danil";

        System.out.println(smallNumber);
        System.out.println(shortNumber);
        System.out.println(totalTests);
        System.out.println(largeNumber);
        System.out.println(floatValue);
        System.out.println(passRate);
        System.out.println(grade);
        System.out.println(active);
        System.out.println(username);
    }

    // =========================================================
    // 02. TYPE CONVERSION / CASTING
    // =========================================================
    public static void typeConversions() {
        int intValue = 10;
        long longValue = intValue;
        double doubleValue = intValue;

        double price = 10.8;
        int wholePrice = (int) price;

        String intText = "123";
        String doubleText = "95.5";
        int parsedInt = Integer.parseInt(intText);
        double parsedDouble = Double.parseDouble(doubleText);

        String numberAsString = String.valueOf(parsedInt);

        System.out.println(longValue);
        System.out.println(doubleValue);
        System.out.println(wholePrice);
        System.out.println(parsedInt);
        System.out.println(parsedDouble);
        System.out.println(numberAsString);
    }

    // =========================================================
    // 03. OPERATORS
    // =========================================================
    public static void operators() {
        int totalTests = 10;
        int passedTests = 8;

        int sum = totalTests + passedTests;
        int difference = totalTests - passedTests;
        int product = totalTests * passedTests;
        int integerDivision = 5 / 2;
        double decimalDivision = (double) 5 / 2;
        int remainder = 5 % 2;

        passedTests++;
        totalTests += 5;

        boolean equal = passedTests == totalTests;
        boolean notEqual = passedTests != totalTests;
        boolean greater = totalTests > passedTests;
        boolean releaseReady = passedTests >= 8 && totalTests >= 10;
        boolean blocked = !releaseReady;

        System.out.println(sum);
        System.out.println(difference);
        System.out.println(product);
        System.out.println(integerDivision);
        System.out.println(decimalDivision);
        System.out.println(remainder);
        System.out.println(equal);
        System.out.println(notEqual);
        System.out.println(greater);
        System.out.println(releaseReady);
        System.out.println(blocked);
    }

    // =========================================================
    // 04. IF / ELSE IF / ELSE + TERNARY
    // =========================================================
    public static void conditions() {
        String expectedUsername = "Danil";
        String actualUsername = "danil";
        int enteredPin = 1234;
        int correctPin = 1234;

        boolean usernameMatches = expectedUsername.equalsIgnoreCase(actualUsername);
        boolean pinMatches = enteredPin == correctPin;

        if (usernameMatches && pinMatches) {
            System.out.println("Login successful");
        } else if (!usernameMatches) {
            System.out.println("Wrong username");
        } else {
            System.out.println("Wrong PIN");
        }

        String result = usernameMatches && pinMatches ? "PASSED" : "FAILED";
        System.out.println(result);
    }

    // =========================================================
    // 05. SWITCH
    // =========================================================
    public static void switchStatements() {
        String environment = "QA";

        switch (environment) {
            case "DEV" -> System.out.println("Development environment");
            case "QA" -> System.out.println("Testing environment");
            case "UAT" -> System.out.println("UAT environment");
            case "PROD" -> System.out.println("Production environment");
            default -> System.out.println("Unknown environment");
        }

        int statusCode = 201;

        switch (statusCode) {
            case 200, 201, 204 -> System.out.println("SUCCESS");
            case 400, 404 -> System.out.println("CLIENT ERROR");
            case 500, 503 -> System.out.println("SERVER ERROR");
            default -> System.out.println("UNKNOWN");
        }
    }

    // =========================================================
    // 06. FOR / FOR-EACH / BREAK / CONTINUE
    // =========================================================
    public static void loops() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        String[] names = {"Danil", "Alex", "Maria"};

        for (String name : names) {
            System.out.println(name);
        }

        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                continue;
            }

            if (i == 7) {
                break;
            }

            System.out.println(i);
        }
    }

    // =========================================================
    // 07. WHILE / DO-WHILE
    // =========================================================
    public static void whileAndDoWhile() {
        int attempt = 1;
        boolean serviceAvailable = false;

        while (attempt <= 3 && !serviceAvailable) {
            System.out.println("Attempt: " + attempt);

            if (attempt == 3) {
                serviceAvailable = true;
            }

            attempt++;
        }

        int number = 10;

        do {
            System.out.println("Runs at least once: " + number);
        } while (number < 5);
    }

    // =========================================================
    // 08. ARRAYS
    // =========================================================
    public static void arrays() {
        String[] browsers = {"Chrome", "Firefox", "Edge"};

        System.out.println(browsers[0]);
        System.out.println(browsers.length);

        browsers[1] = "Safari";

        for (int i = 0; i < browsers.length; i++) {
            System.out.println(i + ": " + browsers[i]);
        }

        for (String browser : browsers) {
            System.out.println(browser);
        }
    }

    // =========================================================
    // 09. LIST / ARRAYLIST
    // =========================================================
    public static void arrayLists() {
        List<String> users = new ArrayList<>();

        users.add("Danil");
        users.add("Alex");
        users.add("Maria");

        System.out.println(users.get(0));
        System.out.println(users.size());
        System.out.println(users.contains("Alex"));

        users.set(1, "Sasha");
        users.remove("Maria");

        for (String user : users) {
            System.out.println(user);
        }
    }

    // =========================================================
    // 10. LINKEDLIST
    // =========================================================
    public static void linkedLists() {
        LinkedList<String> queue = new LinkedList<>();

        queue.add("LoginTest");
        queue.add("SearchTest");
        queue.addFirst("SmokeTest");
        queue.addLast("LogoutTest");

        System.out.println(queue.get(1));
        System.out.println(queue);

        queue.removeFirst();
        queue.removeLast();

        System.out.println(queue);
    }

    // =========================================================
    // 11. SET / HASHSET / LINKEDHASHSET
    // =========================================================
    public static void sets() {
        Set<String> hashSet = new HashSet<>();

        hashSet.add("Chrome");
        hashSet.add("Firefox");
        hashSet.add("Chrome");

        System.out.println(hashSet.size());
        System.out.println(hashSet.contains("Chrome"));

        Set<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("Danil");
        linkedHashSet.add("Alex");
        linkedHashSet.add("Danil");
        linkedHashSet.add("Maria");

        for (String name : linkedHashSet) {
            System.out.println(name);
        }
    }

    // =========================================================
    // 12. MAP / HASHMAP / LINKEDHASHMAP
    // =========================================================
    public static void maps() {
        Map<Integer, String> users = new HashMap<>();

        users.put(101, "Danil");
        users.put(102, "Alex");
        users.put(103, "Danil");

        System.out.println(users.get(101));
        System.out.println(users.containsKey(102));
        System.out.println(users.containsValue("Alex"));

        users.put(102, "Sasha");
        users.remove(103);

        for (Map.Entry<Integer, String> entry : users.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<Integer, String> orderedUsers = new LinkedHashMap<>();

        orderedUsers.put(1, "Danil");
        orderedUsers.put(2, "Alex");
        orderedUsers.put(3, "Maria");

        System.out.println(orderedUsers);
    }

    // =========================================================
    // 13. STRING METHODS
    // =========================================================
    public static void stringMethods() {
        String text = "  Danil Fedosov  ";

        System.out.println(text.length());
        System.out.println(text.toUpperCase());
        System.out.println(text.toLowerCase());
        System.out.println(text.contains("Danil"));
        System.out.println(text.startsWith("  Danil"));
        System.out.println(text.endsWith("  "));
        System.out.println(text.trim());
        System.out.println(text.strip());
        System.out.println(text.replace("Danil", "Alex"));
        System.out.println(text.charAt(2));
        System.out.println(text.substring(2, 7));
        System.out.println(text.isEmpty());
        System.out.println(text.isBlank());
    }

    // =========================================================
    // 14. SPLIT / INDEXOF / CHAR ARRAY
    // =========================================================
    public static void splitIndexOfAndChars() {
        String users = "Danil,Alex,Maria";
        String[] userArray = users.split(",");

        for (String user : userArray) {
            System.out.println(user);
        }

        String name = "Danil";

        System.out.println(name.indexOf("n"));
        System.out.println(name.indexOf("il"));
        System.out.println(name.indexOf("x"));

        for (char letter : name.toLowerCase().toCharArray()) {
            System.out.println(letter);
        }
    }

    // =========================================================
    // 15. STRING IMMUTABILITY / STRING POOL
    // =========================================================
    public static void stringImmutabilityAndPool() {
        String name = "Danil";
        name.toUpperCase();

        System.out.println(name);

        String upperName = name.toUpperCase();
        System.out.println(upperName);

        String first = "Danil";
        String second = "Danil";
        String third = new String("Danil");

        System.out.println(first == second);
        System.out.println(first == third);
        System.out.println(first.equals(third));
    }

    // =========================================================
    // 16. STRINGBUILDER / STRINGBUFFER
    // =========================================================
    public static void stringBuilderAndBuffer() {
        StringBuilder builder = new StringBuilder();

        builder.append("Danil");
        builder.append(" ");
        builder.append("Fedosov");
        builder.insert(6, "QA ");
        builder.delete(6, 9);

        System.out.println(builder);
        System.out.println(builder.reverse());

        String normalString = builder.toString();
        System.out.println(normalString);

        StringBuffer buffer = new StringBuffer("Danil");
        buffer.append(" Fedosov");

        System.out.println(buffer);
    }

    // =========================================================
    // 17. WRAPPER CLASSES / AUTOBOXING / UNBOXING
    // =========================================================
    public static void wrappers() {
        int primitive = 10;
        Integer wrapper = primitive;
        int unboxed = wrapper;

        String numberText = "123";
        int parsed = Integer.parseInt(numberText);
        Integer wrapperFromText = Integer.valueOf(numberText);

        Integer nullableNumber = null;

        System.out.println(wrapper);
        System.out.println(unboxed);
        System.out.println(parsed);
        System.out.println(wrapperFromText);
        System.out.println(nullableNumber);
    }

    // =========================================================
    // 18. METHODS / PARAMETERS / ARGUMENTS / RETURN / VOID
    // =========================================================
    public static void methodsExample() {
        printUser("Danil");

        int result = addNumbers(10, 5);
        System.out.println(result);

        double passRate = calculatePassRate(90, 100);
        System.out.println(passRate);
    }

    public static void printUser(String username) {
        System.out.println(username);
    }

    public static int addNumbers(int first, int second) {
        return first + second;
    }

    public static double calculatePassRate(int passedTests, int totalTests) {
        return (double) passedTests / totalTests * 100;
    }

    // =========================================================
    // 19. METHOD OVERLOADING
    // =========================================================
    public static void methodOverloading() {
        System.out.println(add(10, 5));
        System.out.println(add(10, 5, 3));
        System.out.println(add(10.5, 5.5));
    }

    public static int add(int first, int second) {
        return first + second;
    }

    public static int add(int first, int second, int third) {
        return first + second + third;
    }

    public static double add(double first, double second) {
        return first + second;
    }

    // =========================================================
    // 20. STATIC / FINAL
    // =========================================================
    public static void staticAndFinal() {
        sharedCounter++;
        System.out.println(sharedCounter);
        System.out.println(MAX_ATTEMPTS);
        System.out.println(MIN_PASS_RATE);
    }

    // =========================================================
    // 21. CLASS / OBJECT / CONSTRUCTOR / THIS
    // =========================================================
    public static void classesObjectsConstructorsThis() {
        BasicUser user1 = new BasicUser("Danil", 26);
        BasicUser user2 = new BasicUser("Alex");

        user1.printInfo();
        user2.printInfo();

        user1.changeName("John");
        user1.printInfo();
    }

    public static class BasicUser {
        String name;
        int age;

        public BasicUser() {
        }

        public BasicUser(String name) {
            this.name = name;
        }

        public BasicUser(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void changeName(String name) {
            this.name = name;
        }

        public void printInfo() {
            System.out.println(name + ", " + age);
        }
    }

    // =========================================================
    // 22. ACCESS MODIFIERS
    // =========================================================
    public static class AccessExample {
        public String publicField = "public";
        protected String protectedField = "protected";
        String packagePrivateField = "package-private";
        private String privateField = "private";

        public String getPrivateField() {
            return privateField;
        }
    }

    // =========================================================
    // 23. ENCAPSULATION / GETTERS / SETTERS
    // =========================================================
    public static void encapsulation() {
        Account account = new Account("Danil", 26);

        System.out.println(account.getName());
        System.out.println(account.getAge());

        account.setAge(30);
        account.setAge(-10);

        System.out.println(account.getAge());
    }

    public static class Account {
        private String name;
        private int age;

        public Account(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            if (age >= 0) {
                this.age = age;
            }
        }
    }

    // =========================================================
    // 24. INHERITANCE / SUPER
    // =========================================================
    public static void inheritanceAndSuper() {
        Admin admin = new Admin("Danil", true);

        admin.login();
        admin.deleteUser();
    }

    public static class ParentUser {
        protected String name;

        public ParentUser(String name) {
            this.name = name;
        }

        public void login() {
            System.out.println(name + " logged in as User");
        }
    }

    public static class Admin extends ParentUser {
        private boolean canDeleteUsers;

        public Admin(String name, boolean canDeleteUsers) {
            super(name);
            this.canDeleteUsers = canDeleteUsers;
        }

        public void deleteUser() {
            if (canDeleteUsers) {
                System.out.println(name + " deleted a user");
            }
        }
    }

    // =========================================================
    // 25. OVERRIDING / @OVERRIDE / SUPER.METHOD()
    // =========================================================
    public static void overriding() {
        AdminOverride admin = new AdminOverride("Danil");
        admin.login();
    }

    public static class UserOverrideParent {
        protected String name;

        public UserOverrideParent(String name) {
            this.name = name;
        }

        public void login() {
            System.out.println("Parent login");
        }
    }

    public static class AdminOverride extends UserOverrideParent {
        public AdminOverride(String name) {
            super(name);
        }

        @Override
        public void login() {
            super.login();
            System.out.println("Admin login for " + name);
        }
    }

    // =========================================================
    // 26. POLYMORPHISM
    // =========================================================
    public static void polymorphism() {
        PolyUser firstUser = new PolyAdmin();
        PolyUser secondUser = new PolyCustomer();

        firstUser.login();
        secondUser.login();

        List<PolyUser> users = new ArrayList<>();

        users.add(new PolyAdmin());
        users.add(new PolyCustomer());
        users.add(new PolyAdmin());

        for (PolyUser user : users) {
            user.login();
        }
    }

    public static class PolyUser {
        public void login() {
            System.out.println("User login");
        }
    }

    public static class PolyAdmin extends PolyUser {
        @Override
        public void login() {
            System.out.println("Admin login");
        }

        public void deleteUser() {
            System.out.println("User deleted");
        }
    }

    public static class PolyCustomer extends PolyUser {
        @Override
        public void login() {
            System.out.println("Customer login");
        }

        public void placeOrder() {
            System.out.println("Order placed");
        }
    }

    // =========================================================
    // 27. ABSTRACT CLASS / ABSTRACT METHOD
    // =========================================================
    public static void abstraction() {
        AbstractTestCase apiTest = new ConcreteApiTest("GetUsers");

        apiTest.printName();
        apiTest.runTest();
    }

    public abstract static class AbstractTestCase {
        protected String name;

        public AbstractTestCase(String name) {
            this.name = name;
        }

        public abstract void runTest();

        public void printName() {
            System.out.println(name);
        }
    }

    public static class ConcreteApiTest extends AbstractTestCase {
        public ConcreteApiTest(String name) {
            super(name);
        }

        @Override
        public void runTest() {
            System.out.println("Running API test: " + name);
        }
    }

    // =========================================================
    // 28. INTERFACE / IMPLEMENTS
    // =========================================================
    public static void interfaces() {
        Executable executable = new InterfaceApiTest();

        executable.execute();

        InterfaceApiTest apiTest = new InterfaceApiTest();

        apiTest.execute();
        apiTest.generateReport();
    }

    public interface Executable {
        void execute();
    }

    public interface Reportable {
        void generateReport();
    }

    public static class InterfaceApiTest implements Executable, Reportable {
        @Override
        public void execute() {
            System.out.println("Executing API test");
        }

        @Override
        public void generateReport() {
            System.out.println("Generating API report");
        }
    }

    // =========================================================
    // 29. ANONYMOUS CLASS
    // =========================================================
    public static void anonymousClass() {
        Executable executable = new Executable() {
            @Override
            public void execute() {
                System.out.println("Anonymous class execution");
            }
        };

        executable.execute();
    }

    // =========================================================
    // 30. EQUALS / HASHCODE / OBJECT
    // =========================================================
    public static void equalsHashCodeAndObject() {
        EqualityUser user1 = new EqualityUser("Danil", 26);
        EqualityUser user2 = new EqualityUser("Danil", 26);

        System.out.println(user1 == user2);
        System.out.println(user1.equals(user2));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.toString());
        System.out.println(user1.getClass());

        Set<EqualityUser> users = new HashSet<>();

        users.add(user1);
        users.add(user2);

        System.out.println(users.size());
    }

    public static class EqualityUser {
        private String name;
        private int age;

        public EqualityUser(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof EqualityUser otherUser)) {
                return false;
            }

            return age == otherUser.age && Objects.equals(name, otherUser.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + ", " + age;
        }
    }

    // =========================================================
    // 31. EXCEPTIONS / TRY / CATCH / FINALLY / THROW
    // =========================================================
    public static void exceptions() {
        try {
            int number = Integer.parseInt("ABC");
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + e.getMessage());
        } finally {
            System.out.println("Parsing finished");
        }

        try {
            validateAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        System.out.println(age);
    }

    // =========================================================
    // 32. THROWS / CHECKED VS UNCHECKED
    // =========================================================
    public static void checkedAndUncheckedExceptions() {
        try {
            readFileWithThrows();
        } catch (IOException e) {
            System.out.println("Checked exception handled: " + e.getMessage());
        }

        try {
            Integer.parseInt("ABC");
        } catch (NumberFormatException e) {
            System.out.println("Unchecked exception handled: " + e.getMessage());
        }
    }

    public static void readFileWithThrows() throws IOException {
        Path path = Path.of("missing-file.txt");
        Files.readString(path);
    }

    // =========================================================
    // 33. GENERICS
    // =========================================================
    public static void generics() {
        DataHolder<String> name = new DataHolder<>("Danil");
        DataHolder<Integer> code = new DataHolder<>(200);
        DataHolder<Boolean> passed = new DataHolder<>(true);

        System.out.println(name.getData());
        System.out.println(code.getData());
        System.out.println(passed.getData());

        Pair<Integer, String> user = new Pair<>(101, "Danil");

        System.out.println(user.getKey());
        System.out.println(user.getValue());
    }

    public static class DataHolder<T> {
        private T data;

        public DataHolder(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // =========================================================
    // 34. COMPARABLE / COMPARATOR / SORTING
    // =========================================================
    public static void sorting() {
        List<SortableUser> users = new ArrayList<>();

        users.add(new SortableUser("Danil", 26));
        users.add(new SortableUser("Alex", 35));
        users.add(new SortableUser("Maria", 22));

        Collections.sort(users);

        System.out.println("Natural order by age:");
        users.forEach(user -> System.out.println(user));

        users.sort(new NameComparator());

        System.out.println("Comparator by name:");
        users.forEach(user -> System.out.println(user));

        users.sort((first, second) -> Integer.compare(second.getAge(), first.getAge()));

        System.out.println("Lambda comparator by age descending:");
        users.forEach(user -> System.out.println(user));
    }

    public static class SortableUser implements Comparable<SortableUser> {
        private String name;
        private int age;

        public SortableUser(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(SortableUser otherUser) {
            return Integer.compare(this.age, otherUser.age);
        }

        @Override
        public String toString() {
            return name + ", " + age;
        }
    }

    public static class NameComparator implements Comparator<SortableUser> {
        @Override
        public int compare(SortableUser firstUser, SortableUser secondUser) {
            return firstUser.getName().compareTo(secondUser.getName());
        }
    }

    // =========================================================
    // 35. ENUM
    // =========================================================
    public static void enums() {
        TestStatus status = TestStatus.PASSED;

        System.out.println(status);

        switch (status) {
            case NOT_STARTED -> System.out.println("Not started");
            case PASSED -> System.out.println("Passed");
            case FAILED -> System.out.println("Failed");
            case SKIPPED -> System.out.println("Skipped");
        }

        for (TestStatus value : TestStatus.values()) {
            System.out.println(value);
        }
    }

    public enum TestStatus {
        NOT_STARTED,
        PASSED,
        FAILED,
        SKIPPED
    }

    // =========================================================
    // 36. FUNCTIONAL INTERFACE / LAMBDA
    // =========================================================
    public static void lambdas() {
        NumberOperation addition = (first, second) -> first + second;
        NumberOperation subtraction = (first, second) -> first - second;
        NumberOperation multiplication = (first, second) -> first * second;

        System.out.println(addition.execute(10, 5));
        System.out.println(subtraction.execute(10, 5));
        System.out.println(multiplication.execute(10, 5));

        List<String> browsers = List.of("Chrome", "Firefox", "Edge");
        browsers.forEach(browser -> System.out.println(browser));
    }

    @FunctionalInterface
    public interface NumberOperation {
        int execute(int first, int second);
    }

    // =========================================================
    // 37. STREAMS
    // =========================================================
    public static void streams() {
        List<Integer> numbers = List.of(10, 25, 30, 45, 50);

        List<Integer> filtered = numbers.stream().filter(number -> number > 20).toList();
        List<Integer> doubled = numbers.stream().map(number -> number * 2).toList();
        List<Integer> filteredAndDoubled = numbers.stream().filter(number -> number > 20).map(number -> number * 2).toList();
        List<Integer> sorted = numbers.stream().sorted().toList();

        long count = numbers.stream().filter(number -> number > 20).count();
        boolean any = numbers.stream().anyMatch(number -> number == 50);
        boolean all = numbers.stream().allMatch(number -> number >= 10);
        boolean none = numbers.stream().noneMatch(number -> number > 100);

        System.out.println(filtered);
        System.out.println(doubled);
        System.out.println(filteredAndDoubled);
        System.out.println(sorted);
        System.out.println(count);
        System.out.println(any);
        System.out.println(all);
        System.out.println(none);

        numbers.stream().filter(number -> number >= 25).forEach(number -> System.out.println(number));
    }

    // =========================================================
    // 38. OPTIONAL
    // =========================================================
    public static void optional() {
        Optional<String> firstUser = findUserById(101);
        Optional<String> secondUser = findUserById(999);

        System.out.println(firstUser.orElse("Not found"));
        System.out.println(secondUser.orElse("Not found"));

        firstUser.ifPresent(user -> System.out.println("Found: " + user));

        Optional<String> upperUser = firstUser.map(user -> user.toUpperCase());
        System.out.println(upperUser.orElse("No user"));
    }

    public static Optional<String> findUserById(int id) {
        if (id == 101) {
            return Optional.of("Danil");
        }

        return Optional.empty();
    }

    // =========================================================
    // 39. REGEX
    // =========================================================
    public static void regex() {
        String pin = "1234";
        String username = "Danil123";
        String response = "Codes: 200, 404, 500";

        System.out.println(pin.matches("\\d{4}"));
        System.out.println(username.matches("[A-Za-z0-9]+"));

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(response);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // =========================================================
    // 40. FILES / PATH
    // =========================================================
    public static void files() {
        Path relativePath = Path.of("test-results.txt");
        Path absolutePath = Path.of("C:/Users/danil/Documents/QAInterviewPractice/java_basics/test-results.txt");

        System.out.println(relativePath.toAbsolutePath());
        System.out.println(absolutePath);

        try {
            List<String> results = List.of("LoginTest: PASSED", "SearchTest: FAILED", "CheckoutTest: PASSED");

            Files.write(relativePath, results);
            System.out.println(Files.exists(relativePath));

            List<String> savedResults = Files.readAllLines(relativePath);

            for (String result : savedResults) {
                System.out.println(result);
            }

            Files.writeString(relativePath, "\nProfileTest: PASSED", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }
    }

    // =========================================================
    // 41. DATE / TIME / FORMATTER
    // =========================================================
    public static void dateTime() {
        LocalDate date = LocalDate.of(2026, 8, 30);
        LocalTime time = LocalTime.of(14, 30);
        LocalDateTime dateTime = LocalDateTime.of(2026, 8, 30, 14, 30);

        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        LocalDate nextWeek = date.plusDays(7);
        System.out.println(nextWeek);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        System.out.println(formattedDate);

        LocalDate parsedDate = LocalDate.parse("15/09/2026", formatter);

        System.out.println(parsedDate.isAfter(date));
        System.out.println(parsedDate.isBefore(date));
        System.out.println(parsedDate.isEqual(date));
    }

    // =========================================================
    // 42. NULL / NULLPOINTEREXCEPTION
    // =========================================================
    public static void nullAndNpe() {
        String name = null;

        if (name != null) {
            System.out.println(name.toUpperCase());
        } else {
            System.out.println("Name is null");
        }

        String status = null;

        if ("PASSED".equals(status)) {
            System.out.println("Passed");
        } else {
            System.out.println("Not passed or null");
        }
    }

    // =========================================================
    // 43. UPCASTING / DOWNCASTING / INSTANCEOF
    // =========================================================
    public static void casting() {
        CastUser user = new CastAdmin();

        user.login();

        if (user instanceof CastAdmin admin) {
            admin.deleteUser();
        }

        CastAdmin directAdmin = new CastAdmin();
        CastUser upcasted = directAdmin;
        CastAdmin downcasted = (CastAdmin) upcasted;

        downcasted.deleteUser();
    }

    public static class CastUser {
        public void login() {
            System.out.println("User login");
        }
    }

    public static class CastAdmin extends CastUser {
        @Override
        public void login() {
            System.out.println("Admin login");
        }

        public void deleteUser() {
            System.out.println("User deleted");
        }
    }

    // =========================================================
    // 44. CHARACTER FREQUENCY WITH HASHMAP
    // =========================================================
    public static void characterFrequency() {
        String text = "Google";
        Map<Character, Integer> letterCount = new HashMap<>();

        for (char letter : text.toLowerCase().toCharArray()) {
            int currentCount = letterCount.getOrDefault(letter, 0);
            int newCount = currentCount + 1;
            letterCount.put(letter, newCount);
        }

        System.out.println(letterCount);
    }

    // =========================================================
    // 45. SMALL INTERVIEW CODING EXAMPLES
    // =========================================================
    public static void reverseString() {
        String text = "Automation";
        String reversed = new StringBuilder(text).reverse().toString();

        System.out.println(reversed);
    }

    public static void palindrome() {
        String text = "level";
        String reversed = new StringBuilder(text).reverse().toString();
        boolean palindrome = text.equalsIgnoreCase(reversed);

        System.out.println(palindrome);
    }

    public static void removeDuplicates() {
        List<String> names = List.of("Danil", "Alex", "Danil", "Maria", "Alex");
        Set<String> uniqueNames = new LinkedHashSet<>(names);

        System.out.println(uniqueNames);
    }

    public static void maxAndMin() {
        List<Integer> numbers = List.of(10, 50, 20, 5, 100);

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}
