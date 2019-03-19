package git.challenge;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    private static Scanner op = new Scanner(System.in);
    private static File bookColl = new File("endava_library.txt");

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        mainMenu();

    }

    public static void mainMenu() {
        boolean flag = true;
        while(flag){
            System.out.println("********Endava Library********");
            System.out.println("1. Add a new book.");
            System.out.println("2. Search a book. ");
            System.out.println("3. Update a book.");
            System.out.println("4. Delete a book.");
            System.out.println("5. Exit.");
            System.out.println();
            System.out.print("Select an option: ");
            int option = op.nextInt();
            System.out.println("");

            switch(option) {
                case 1: //Create feature
                    addBook();
                    break;
                case 2: //Read feature
                    readBookInfo();
                    break;
                case 3: //Update feature
                    updateBookInfo();
                    break;
                case 4: //Delete feature
                    deleteBook();
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }

    public static void addBook(){
        Scanner input = new Scanner(System.in);
        Book newBook = new Book();
        ArrayList<Book> books = new ArrayList<>();

        if (bookColl.length() == 0) { //If the file is empty...
            //Asks the user for the new book's info
            System.out.print("Type the name of the new book you want to add: ");
            newBook.setName(input.nextLine());
            System.out.print("Type the book's author: ");
            newBook.setAuthor(input.nextLine());
            System.out.print("Type the book's ISBN number: ");
            newBook.setIsbn(input.nextLine());
            System.out.print("Type the book's published date: ");
            newBook.setPublishedDate(input.nextLine());
            System.out.print("Type the book's language: ");
            newBook.setLanguage(input.nextLine());
            System.out.print("Type the book's publisher: ");
            newBook.setPublisher(input.nextLine());
            System.out.println("");

            books.add(newBook); //Adds the object to the ArrayList and then stores it in the file
            writeFile(books, bookColl);
        } else {
            books = readFile(bookColl); //When the file is not empty, reads it and then stores the objects in the ArrayList

            System.out.print("Type the name of the new book you want to add: ");
            newBook.setName(input.nextLine());
            System.out.print("Type the book's author: ");
            newBook.setAuthor(input.nextLine());
            System.out.print("Type the book's ISBN number: ");
            newBook.setIsbn(input.nextLine());
            System.out.print("Type the book's published date: ");
            newBook.setPublishedDate(input.nextLine());
            System.out.print("Type the book's language: ");
            newBook.setLanguage(input.nextLine());
            System.out.print("Type the book's publisher: ");
            newBook.setPublisher(input.nextLine());
            System.out.println("");

            books.add(newBook); //Adds the new book to the ArrayList and stores it in the file
            writeFile(books, bookColl);
        }
        System.out.println("Book successfully added!");
        System.out.println();
    }

    public static void readBookInfo() {
        Scanner input = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<Book>();

        if (bookColl.length() == 0){
            System.out.println("There are no books registered!");
        } else {
            books = readFile(bookColl);

            System.out.print("Please type the name of the book: ");
            String bName = input.nextLine();
            System.out.println("");
            Iterator<Book> itr = books.iterator();
            while(itr.hasNext()) {
                Book tmpBook = itr.next();
                if (tmpBook.getName().equals(bName)) {
                    System.out.println("||||" + tmpBook.getName() + "||||");
                    System.out.println("");
                    System.out.println("Author: " + tmpBook.getAuthor());
                    System.out.println("ISBN: " + tmpBook.getIsbn());
                    System.out.println("Published date: " + tmpBook.getPublishedDate());
                    System.out.println("Language: " + tmpBook.getLanguage());
                    System.out.println("Publisher: " + tmpBook.getPublisher());
                    System.out.println("");
                    break;
                } if (!itr.hasNext()) {
                    System.out.println("The specified book does not exists!");
                    System.out.println("");
                }
            }
        }
    }

    public static void updateBookInfo() {
        Scanner input = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        if (bookColl.length() == 0) {
            System.out.println("There are no books registered!");
        } else {
            books = readFile(bookColl);

            System.out.print("Please type the name of the book you want to update: ");
            String bName = input.nextLine();
            System.out.println("");
            Iterator<Book> itr = books.iterator();
            while(itr.hasNext()) {
                Book tmpBook = itr.next();
                if (tmpBook.getName().equals(bName)) {
                    System.out.println("Which field you would like to update?");
                    System.out.println("");
                    System.out.println("1. Author.");
                    System.out.println("2. ISBN.");
                    System.out.println("3. Published date.");
                    System.out.println("4. Language.");
                    System.out.println("5. Publisher.");
                    System.out.println("");
                    System.out.print("Select a field: ");
                    String field = input.nextLine();
                    //String updateField = "";
                    System.out.println("");

                    switch (field) {
                        case "1":
                            System.out.print("Type the updated author : ");
                            tmpBook.setAuthor(input.nextLine());
                            System.out.println("");
                            System.out.println("Book successfully updated!");
                            System.out.println("");
                            break;
                        case "2":
                            System.out.print("Type the updated ISBN number: ");
                            tmpBook.setIsbn(input.nextLine());
                            System.out.println("");
                            System.out.print("Book successfully updated!");
                            System.out.println("");
                            break;
                        case "3":
                            System.out.print("Type the updated published date: ");
                            tmpBook.setPublishedDate(input.nextLine());
                            System.out.println("");
                            System.out.println("Book successfully updated!");
                            System.out.println("");
                            break;
                        case "4":
                            System.out.print("Type the updated language: ");
                            tmpBook.setLanguage(input.nextLine());
                            System.out.println("");
                            System.out.println("Book successfully updated!");
                            System.out.println("");
                            break;
                        case "5":
                            System.out.print("Type the updated publisher: ");
                            tmpBook.setPublisher(input.nextLine());
                            System.out.println("");
                            System.out.println("Book successfully updated!");
                            System.out.println("");
                            break;
                    }
                    break;
                } if (!itr.hasNext()) {
                    System.out.println("The specified book does not exists!");
                    System.out.println("");
                }
            }
            writeFile(books, bookColl);
        }
    }

    public static void deleteBook() {
        Scanner input = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        if (bookColl.length() == 0) {
            System.out.println("There are no books registered!");
        } else {
            books = readFile(bookColl);

            System.out.print("Please type the name of the book you want to delete: ");
            String bName = input.nextLine();
            System.out.println("");
            Iterator<Book> itr = books.iterator();
            while (itr.hasNext()) {
                Book tmpBook = itr.next();
                if (tmpBook.getName().equals(bName)) {
                    System.out.println("Are you sure you want to delete the specified book?");
                    System.out.println("");
                    System.out.println("Yes (Y)");
                    System.out.println("No (N)");
                    System.out.println("");
                    System.out.print("Select an option: ");
                    String op = input.nextLine();
                    System.out.println("");
                    if (op.equals("Y")) {
                        //books.remove(tmpBook);
                        itr.remove();
                        writeFile(books, bookColl);
                        System.out.println(bName + " successfully removed!");
                        System.out.println("");
                    } else {
                        System.out.println(tmpBook.getName() + " not deleted!");
                        System.out.println("");
                    }
                    break;
                } if (!itr.hasNext()) {
                    System.out.println("The specified book does not exists!");
                    System.out.println("");
                }
            }
        }
    }

    public static ArrayList<Book> readFile(File file) {
        ArrayList<Book> currBooks = new ArrayList<>();
        Boolean flag = true;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Book tmp = null;
            while (flag) {
                if (fis.available() != 0) {
                    tmp = (Book) ois.readObject();
                    currBooks.add(tmp);
                } else {
                    flag = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currBooks;
    }

    public static void writeFile(ArrayList<Book> b, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < b.size(); i++) {
                oos.writeObject(b.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

