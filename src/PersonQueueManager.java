import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this is to represent a person with first name, last name, and age
class Person {
    private String firstName;
    private String lastName;
    private int age;

    // constructor for initializing attributes of persons
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Overriding the toString in order to display details of person
    @Override
    public String toString() {
        return firstName + " " + lastName + ", Age: " + age;
    }
}

// queue class to store-manage person objects
class Queue {
    private List<Person> queue;

    // constructor for initializing empty queue
    public Queue() {
        this.queue = new ArrayList<>();
    }

    // adds a person to the queue
    public void enqueue(Person person) {
        queue.add(person);
    }

    // displaying elements in the queue
    public void displayQueue() {
        for (Person person : queue) {
            System.out.println(person);
        }
    }

    // sorts the queue by last name. Sorting in descending order via quickSort
    public void sortByLastNameDescending() {
        quickSortByLastName(0, queue.size() - 1);
    }

    // sorts the queue by age in descending order with quickSort
    public void sortByAgeDescending() {
        quickSortByAge(0, queue.size() - 1);
    }

    // entering quickSort for sorting by last name
    private void quickSortByLastName(int low, int high) {
        if (low < high) {
            int pivotIndex = partitionByLastName(low, high);
            quickSortByLastName(low, pivotIndex - 1);
            quickSortByLastName(pivotIndex + 1, high);
        }
    }

    // partition method for QuickSort--last name
    private int partitionByLastName(int low, int high) {
        String pivot = queue.get(high).getLastName();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // this is to compare the last names in descending order
            if (queue.get(j).getLastName().compareTo(pivot) > 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    // entering quickSort to sort by age
    private void quickSortByAge(int low, int high) {
        if (low < high) {
            int pivotIndex = partitionByAge(low, high);
            quickSortByAge(low, pivotIndex - 1);
            quickSortByAge(pivotIndex + 1, high);
        }
    }

    // partition method for QuickSort--age
    private int partitionByAge(int low, int high) {
        int pivot = queue.get(high).getAge();// choosing the last element as the pivot
        int i = low - 1; // index for smaller element

        for (int j = low; j < high; j++) {
            // comparing ages in descending order
            if (queue.get(j).getAge() > pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high); // placing pivot in its correct position
        return i + 1;
    }

    // this is a utility method to swap the two elements in the queue
    private void swap(int i, int j) {
        Person temp = queue.get(i);
        queue.set(i, queue.get(j));
        queue.set(j, temp);
    }
}

// Main class to show queue function
public class PersonQueueManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();

        System.out.println("Enter details for 5 people:");
        // prompting user to add 5 people to the queue
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            queue.enqueue(new Person(firstName, lastName, age));
        }

        System.out.println("\nOriginal Queue:");
        queue.displayQueue();

        // sort and display the queue by last name
        queue.sortByLastNameDescending();
        System.out.println("\nQueue sorted by last name (descending):");
        queue.displayQueue();

        // sort and display the queue by age
        queue.sortByAgeDescending();
        System.out.println("\nQueue sorted by age (descending):");
        queue.displayQueue();

        scanner.close();
    }
}
