package com.atguigu.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 8:47
 */
public class Book {
    /*
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,	#图书id 主键
	`name` VARCHAR (32) NOT NULL,		#图书名
	`author` VARCHAR (32) NOT NULL,		#作者
	`publish` VARCHAR (20) NOT NULL,	#出版社
	`ISBN` VARCHAR (15) NOT NULL,		#ISBN
	`intro` TEXT NOT NULL,			    #简介
	`language` VARCHAR (12) NOT NULL,	#语言
	`price` DECIMAL (11,2) NOT NULL,	#价格
	`pub_date` DATE NOT NULL,		    #出版日期
	`type`  VARCHAR(20) NOT NULL,		#图书类型
	`number` INT				        #剩余数量
     */

    private Integer id;
    private String name;
    private String author;
    private String publish;
    private String ISBN;
    private String intro;
    private String language;
    private BigDecimal price;
    private Date pub_date;
    private String type;
    private Integer number;

    public Book() {
    }

    public Book(Integer id, String name, String author, String publish, String ISBN, String intro, String language, BigDecimal price, Date pub_date, String type, Integer number) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.ISBN = ISBN;
        this.intro = intro;
        this.language = language;
        this.price = price;
        this.pub_date = pub_date;
        this.type = type;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", intro='" + intro + '\'' +
                ", language='" + language + '\'' +
                ", price=" + price +
                ", pub_date=" + pub_date +
                ", type='" + type + '\'' +
                ", number=" + number +
                '}';
    }
}
