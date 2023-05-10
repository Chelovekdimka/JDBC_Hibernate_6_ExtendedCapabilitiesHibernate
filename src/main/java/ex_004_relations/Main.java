package ex_004_relations;

//В класс BookHelper пакета ex_004_relations дописать методы удаления книге по id и по автору.

import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {


    public static void main(String[] args) {

       Author author = new AuthorHelper().getAuthorById(1);

       List<Book> authorBooks = author.getBooks();

       for (Book book : authorBooks) {
           System.out.println(book.getName() + " " + book.getAuthor().getName()
                   + " " + book.getAuthor().getLastName());
       }
        BookHelper bookHelper = new BookHelper();
        // Видалення книги по id
        long bookId = 4;
        bookHelper.deleteBookById(bookId);
        // Видалення книги по автору
        Author author1 = new Author();
        author1.setId(13);
        author1.setName("Americo");
        author1.setLastName("McCullough");

        bookHelper.deleteBooksByAuthor(author);
        List<Book> authorBooks1 = author.getBooks();

        for (Book book : authorBooks1) {
            System.out.println(book.getName() + " " + book.getAuthor().getName()
                    + " " + book.getAuthor().getLastName());
        }

    }

}
