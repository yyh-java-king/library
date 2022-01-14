package com.atguigu.domain;

import java.util.Date;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 19:13
 * 借还记录类
 */
public class Lend {
    /*
    `id` BIGINT  PRIMARY KEY AUTO_INCREMENT,	#主键
	`user_id` INT  NOT NULL,			        #用户id
	`book_id` INT  NOT NULL,			        #图书id
	`lend_date` DATETIME,				        #借阅时间
	`back_date` DATETIME				        #归还时间
     */
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private Date lend_date;
    private Date back_date;

    public Lend() {
    }

    public Lend(Integer id, Integer user_id, Integer book_id, Date lend_date, Date back_date) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.lend_date = lend_date;
        this.back_date = back_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Date getLend_date() {
        return lend_date;
    }

    public void setLend_date(Date lend_date) {
        this.lend_date = lend_date;
    }

    public Date getBack_date() {
        return back_date;
    }

    public void setBack_date(Date back_date) {
        this.back_date = back_date;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", lend_date=" + lend_date +
                ", back_date=" + back_date +
                '}';
    }
}
