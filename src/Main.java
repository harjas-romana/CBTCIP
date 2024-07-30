import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define classes for Book and Member
class Book {
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, Category: %s, Issued: %s", title, author, category, isIssued ? "Yes" : "No");
    }
}

class Member {
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Email: %s", name, email);
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Admin methods
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(String email) {
        Member member = findMemberByEmail(email);
        if (member != null) {
            members.remove(member);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // User methods
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchBook(String searchQuery) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchQuery) ||
                    book.getAuthor().equalsIgnoreCase(searchQuery) ||
                    book.getCategory().equalsIgnoreCase(searchQuery)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public void issueBook(String title, String memberEmail) {
        Book book = findBookByTitle(title);
        Member member = findMemberByEmail(memberEmail);
        if (book != null && member != null) {
            if (!book.isIssued()) {
                book.setIssued(true);
                System.out.println("Book issued to " + member.getName());
            } else {
                System.out.println("Book is already issued.");
            }
        } else {
            System.out.println("Book or Member not found.");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            if (book.isIssued()) {
                book.setIssued(false);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book was not issued.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                return member;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Add some books and members
        library.addBook(new Book("Book1", "Author1", "Category1"));
        library.addBook(new Book("Book2", "Author2", "Category2"));
        library.addMember(new Member("Member1", "member1@example.com"));
        library.addMember(new Member("Member2", "member2@example.com"));

        // Menu-driven interface
        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Add Book");
            System.out.println("6. Remove Book");
            System.out.println("7. Add Member");
            System.out.println("8. Remove Member");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter book title/author/category to search: ");
                    String searchQuery = scanner.nextLine();
                    library.searchBook(searchQuery);
                    break;
                case 3:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String memberEmail = scanner.nextLine();
                    library.issueBook(issueTitle, memberEmail);
                    break;
                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book category: ");
                    String category = scanner.nextLine();
                    library.addBook(new Book(title, author, category));
                    break;
                case 6:
                    System.out.print("Enter book title to remove: ");
                    String removeTitle = scanner.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 7:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String email = scanner.nextLine();
                    library.addMember(new Member(name, email));
                    break;
                case 8:
                    System.out.print("Enter member email to remove: ");
                    String removeEmail = scanner.nextLine();
                    library.removeMember(removeEmail);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
