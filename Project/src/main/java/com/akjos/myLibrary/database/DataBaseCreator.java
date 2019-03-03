package com.akjos.myLibrary.database;

import com.akjos.myLibrary.database.dao.AuthorDao;
import com.akjos.myLibrary.database.dao.BookDao;
import com.akjos.myLibrary.database.dao.CategoryDao;
import com.akjos.myLibrary.database.models.Author;
import com.akjos.myLibrary.database.models.Book;
import com.akjos.myLibrary.database.models.BooksToRead;
import com.akjos.myLibrary.database.models.Category;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class DataBaseCreator {

    public static String databaseUrl = "jdbc:sqlite:src\\main\\resources\\database\\database.db";
    private static ConnectionSource connection;

    public static void init() {
        connect();
//        dropTable();
//        createTable();
        closeConnect();
//        createData();
    }

    public static void connect() {
        try {
            connection = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSource getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    public static void closeConnect() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connection, Author.class);
            TableUtils.createTableIfNotExists(connection, Category.class);
            TableUtils.createTableIfNotExists(connection, Book.class);
            TableUtils.createTableIfNotExists(connection, BooksToRead.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try {
            TableUtils.dropTable(connection, Author.class, true);
            TableUtils.dropTable(connection, Category.class, true);
            TableUtils.dropTable(connection, Book.class, true);
            TableUtils.dropTable(connection, BooksToRead.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createData() {
//        createAuthor();
//        createCategory();
        createAll();

    }

    private static void createCategory() {
        ArrayList<Category> categoryList = new ArrayList<>();

        Category category = new Category();
        category.setName("Horror");
        categoryList.add(category);

        Category category1 = new Category();
        category1.setName("Kryminał");
        categoryList.add(category1);

        Category category2 = new Category();
        category2.setName("Fantastyka");
        categoryList.add(category2);

        Category category3 = new Category();
        category3.setName("Przygodowa");
        categoryList.add(category3);

        Category category4 = new Category();
        category4.setName("Science Fiction");
        categoryList.add(category4);

        CategoryDao dao = new CategoryDao();
        dao.createOrUpdate(categoryList);


    }

    private static void createAuthor() {

        ArrayList<Author> authorList = new ArrayList<>();

        Author author = new Author();
        author.setName("Andrzej");
        author.setSurname("Sapkowski");
        author.setNickname("");
        author.setNationality("Polska");
        authorList.add(author);

        Author author2 = new Author();
        author2.setName("Jo");
        author2.setSurname("Nesbo");
        author2.setNickname("");
        author2.setNationality("Norwegia");
        authorList.add(author2);

        Author author3 = new Author();
        author3.setName("John");
        author3.setSurname("Tolkien");
        author3.setNickname("J.R.R. Tolkien");
        author3.setNationality("Anglia");
        authorList.add(author3);

        Author author4 = new Author();
        author4.setName("Joanne");
        author4.setSurname("Murray");
        author4.setNickname("J.K. Rowling");
        author4.setNationality("Anglia");
        authorList.add(author4);

        AuthorDao dao = new AuthorDao();
        dao.createOrUpdate(authorList);
    }

    private static void createAll() {

        ArrayList<Category> categoryList = new ArrayList<>();

        Category category = new Category();
        category.setName("Horror");
        categoryList.add(category);

        Category category1 = new Category();
        category1.setName("Kryminał");
        categoryList.add(category1);

        Category category2 = new Category();
        category2.setName("Fantasy");
        categoryList.add(category2);

        Category category3 = new Category();
        category3.setName("Przygodowa");
        categoryList.add(category3);

        Category category4 = new Category();
        category4.setName("Science Fiction");
        categoryList.add(category4);

        CategoryDao categoryDao = new CategoryDao();
        categoryDao.createOrUpdate(categoryList);

        ArrayList<Author> authorList = new ArrayList<>();

        Author author = new Author();
        author.setName("Andrzej");
        author.setSurname("Sapkowski");
        author.setNickname("");
        author.setNationality("Polska");
        authorList.add(author);

        Author author2 = new Author();
        author2.setName("Jo");
        author2.setSurname("Nesbo");
        author2.setNickname("");
        author2.setNationality("Norwegia");
        authorList.add(author2);

        Author author3 = new Author();
        author3.setName("John");
        author3.setSurname("Tolkien");
        author3.setNickname("J.R.R. Tolkien");
        author3.setNationality("Anglia");
        authorList.add(author3);

        Author author4 = new Author();
        author4.setName("Joanne");
        author4.setSurname("Murray");
        author4.setNickname("J.K. Rowling");
        author4.setNationality("Anglia");
        authorList.add(author4);

        AuthorDao authorDao = new AuthorDao();
        authorDao.createOrUpdate(authorList);

        ArrayList<Book> listBook = new ArrayList<>();

        Book book1 = new Book();
        book1.setTitle("Drużyna Pierścienia");
        book1.setOriginalTitle("The Fellowship of the Ring");
        book1.setSeries("Władca Pierścieni");
        book1.setRating(9);
        book1.setFavorite(true);
        book1.setOnTheShelf(true);
        book1.setDescription("");
        book1.setAddDate(new Date(2000,5,25));
        book1.setPublicationDate(1994);
        book1.setISBN("78548965427");
        book1.setAuthor(author3);
        book1.setCategory(category2);
        listBook.add(book1);

        Book book2 = new Book();
        book2.setTitle("Dwie wieże");
        book2.setOriginalTitle("The Two Towers");
        book2.setSeries("Władca Pierścieni");
        book2.setRating(8);
        book2.setFavorite(false);
        book2.setOnTheShelf(true);
        book2.setDescription("");
        book2.setAddDate(new Date(2001,9,12));
        book2.setPublicationDate(1994);
        book2.setISBN("45967458214");
        book2.setAuthor(author3);
        book2.setCategory(category2);
        listBook.add(book2);

        Book book3 = new Book();
        book3.setTitle("Pierwszy Śnieg");
        book3.setOriginalTitle("Snomannen");
        book3.setSeries("Harry Hole");
        book3.setRating(8);
        book3.setFavorite(true);
        book3.setOnTheShelf(false);
        book3.setDescription("");
        book3.setAddDate(new Date(2015,7,20));
        book3.setPublicationDate(2007);
        book3.setISBN("98745478962");
        book3.setAuthor(author2);
        book3.setCategory(category1);
        listBook.add(book3);

        Book book4 = new Book();
        book4.setTitle("Harry Potter i Kamień Filozoficzny");
        book4.setOriginalTitle("Harry Potter and the Philosopher’s Stone");
        book4.setSeries("Harry Potter");
        book4.setRating(6);
        book4.setFavorite(false);
        book4.setOnTheShelf(true);
        book4.setDescription("");
        book4.setAddDate(new Date(1999,9,1));
        book4.setPublicationDate(1997);
        book4.setISBN("98653217472");
        book4.setAuthor(author4);
        book4.setCategory(category2);
        listBook.add(book4);

        Book book5 = new Book();
        book5.setTitle("Harry Potter i więzień z Azkabanu");
        book5.setOriginalTitle("Harry Potter and the Prisoner of Azkaban");
        book5.setSeries("Harry Potter");
        book5.setRating(9);
        book5.setFavorite(true);
        book5.setOnTheShelf(true);
        book5.setDescription("");
        book5.setAddDate(new Date(2001,6,10));
        book5.setPublicationDate(1999);
        book5.setISBN("98969592321");
        book5.setAuthor(author4);
        book5.setCategory(category2);
        listBook.add(book5);

        Book book6 = new Book();
        book6.setTitle("Ostatnie Życzenie");
        book6.setOriginalTitle("");
        book6.setSeries("Wiedźmin");
        book6.setRating(9);
        book6.setFavorite(true);
        book6.setOnTheShelf(true);
        book6.setDescription("");
        book6.setAddDate(new Date(2005,1,18));
        book6.setPublicationDate(1993);
        book6.setISBN("96633221412");
        book6.setAuthor(author);
        book6.setCategory(category2);
        listBook.add(book6);

        Book book7 = new Book();
        book7.setTitle("Miecz przeznaczenia");
        book7.setOriginalTitle("");
        book7.setSeries("Wiedźmin");
        book7.setRating(8);
        book7.setFavorite(true);
        book7.setOnTheShelf(true);
        book7.setDescription("");
        book7.setAddDate(new Date(05, 03, 05));
        book7.setPublicationDate(1993);
        book7.setISBN("98959192939");
        book7.setAuthor(author);
        book7.setCategory(category2);
        listBook.add(book7);

        BookDao bookDao = new BookDao();
        bookDao.createOrUpdate(listBook);
    }
}
