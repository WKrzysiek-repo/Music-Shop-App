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
 * @see Plyta
 */
public class Plyta {
 
    private Integer id;
    
    private String name;
    
    private Float price;
    
    private Integer quantity;
    
    /**
     *
     * @param name_ name identifier of requested record
     * @param price_  price of requested record   
     * @param quantity_ quantity of requested record 
     */
    public Plyta(String name_, Float price_, Integer quantity_){
        this.name = name_;
        this.price = price_;
        this.quantity = quantity_;
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
     * @param name_ name of requested record
     */
    public void setName(String name_) {
        this.name = name_;
    }

    /**
     *
     * @param price_  price of requested record
     */
    public void setPrice(Float price_) {
        this.price = price_;
    }

    /**
     *
     * @param quantity_ quantity  of requested record 
     */
    public void setQuanitity(Integer quantity_) {
        this.quantity = quantity_;
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
     * @return Float price of requested record
     */
    public Float getPrice() {
        return price;
    }

    /**
     *
     * @return  Integer quantity of requested record
     */
    public Integer getQuantity() {
        return quantity;
    }

    
}