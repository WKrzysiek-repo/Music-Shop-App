/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Wojciech
 * @version 1.0
 * @see Author
 */
public class Author {
    
    private Integer id;
    
    private String name;
    
    private Integer year;
    
    /**
     *
     * @param name_ name of requested record
     * @param year_ year of requested record
     */
    public Author(String name_, Integer year_){
        this.name = name_;
        this.year = year_;
    }

    /**
     *
     * @param id identifier of requested record
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param name name of requested record
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param year year of requested record
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return Integer identifier of requested record
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return String name of requested record
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return integer year of requested record
     */
    public Integer getYear() {
        return year;
    }

}
