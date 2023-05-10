package ex_002_select_where;




import ex_002_select_where.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {



    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();

        List<Author> authorList = ah.getAuthorList();

        for (Author author : authorList) {
            System.out.println(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

        //        Обновить поле name для всех записей,
//        у которых длина значения поля last_name больше 7 В поле name записать значение «1»

        AuthorHelper authorHelper = new AuthorHelper();
        authorHelper.updateAuthorsWithLastNameLengthGreaterThanSeven();

//        Из пакета ex_002_select_where написать отдельный метод для выборки по поиску выражения

        List<Author> authors = authorHelper.searchAuthors("claud");
        for (Author author : authors) {
            System.out.println(author.getName() + " " + author.getLastName());
        }
    }
}
