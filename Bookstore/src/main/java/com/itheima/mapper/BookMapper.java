package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.domain.Book;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface BookMapper {
    @Select("select * from book where book_status!=3 order by book_uploadtime desc")
    @Results(id = "bookMap", value = {
            @Result(id = true, property = "id", column = "book_id"),
            @Result(property = "name", column = "book_name"),
            @Result(property = "isbn", column = "book_isbn"),
            @Result(property = "press", column = "book_press"),
            @Result(property = "author", column = "book_author"),
            @Result(property = "pagination", column = "book_pagination"),
            @Result(property = "price", column = "book_price"),
            @Result(property = "uploadTime", column = "book_uploadTime"),
            @Result(property = "status", column = "book_status"),
            @Result(property = "borrower", column = "book_borrower"),
            @Result(property = "borrowTime", column = "book_borrowTime"),
            @Result(property = "returnTime", column = "book_returnTime"),
    })
    public Page<Book> selectNewBooks();

    @Select("select * from book where book_id=#{id}")
    @ResultMap("bookMap")
    public Book findById(String id);


    public Integer editBook(Book book);

    @Select("<script>\n" +
            "        select * from book\n" +
            "        <where>\n" +
            "            <if test=\"name!=null and name.trim()!=''\">\n" +
            "                and book_name like concat('%',#{name},'%')\n" +
            "            </if>\n" +
            "            <if test=\"author!=null and author.trim()!=''\">\n" +
            "                and book_author like concat('%',#{author},'%')\n" +
            "            </if>\n" +
            "            <if test=\"press!=null and press.trim()!=''\">\n" +
            "                and book_press like concat('%',#{press},'%')\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        order by book_status\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> searchBooks(Book book);

    public Integer addBook(Book book);

    /**
     * 查询当前用户借阅的和所有用户归还中的（管理员）
     *
     * @return
     */
    @Select("<script>\n" +
            "        select * from book where book_borrower=#{borrower} and book_status =1\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "            and book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        or book_status = 2\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "            and book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> selectBorrowed(Book book);

    //查询当前用户借阅和当前用户归还中的（普通用户）
    @Select("<script>\n" +
            "        select * from book where book_borrower=#{borrower} and book_status in(1,2)\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "            and book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> selectMyBorrow(Book book);
}
