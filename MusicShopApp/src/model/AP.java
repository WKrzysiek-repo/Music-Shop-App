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
public class AP {
    
    private Integer plyta_id;
    
    private String nazwa;
    
    private Float cena;
    
    private Integer ilosc;
    
    private Integer autor_id;
    
    private String name;
    
    private Integer year;
    
    /**
     *
     * @param nazwa_ name identifier of requested record
     * @param cena_ price identifier of requested record
     * @param ilosc_ quantity identifier of requested record
     * @param name_ name identifier of requested author
     * @param year_ year identifier of requested author
     */
    public AP(String nazwa_, Float cena_, Integer ilosc_, String name_, Integer year_){
        this.nazwa = nazwa_;
        this.cena = cena_;
        this.ilosc = ilosc_;
        this.name = name_;
        this.year = year_;
    }

    /**
     *
     * @return Integer identifier of requested record
     */
    public Integer getPlyta_id() {
        return plyta_id;
    }

    /**
     *
     * @param plyta_id identifier of requested record
     */
    public void setPlyta_id(Integer plyta_id) {
        this.plyta_id = plyta_id;
    }

    /**
     *
     * @return String nazwa of requested record
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     *
     * @param nazwa name of requested record
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     *
     * @return  Float price of requested record
     */
    public Float getCena() {
        return cena;
    }

    /**
     *
     * @param cena price of requested record
     */
    public void setCena(Float cena) {
        this.cena = cena;
    }

    /**
     *
     * @return  Integer quantity of requested record
     */
    public Integer getIlosc() {
        return ilosc;
    }

    /**
     *
     * @param ilosc quantity of requested record
     */
    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    /**
     *
     * @return Integer identifier of requested author
     */
    public Integer getAutor_id() {
        return autor_id;
    }

    /**
     *
     * @param autor_id  identifier of requested author
     */
    public void setAutor_id(Integer autor_id) {
        this.autor_id = autor_id;
    }

    /**
     *
     * @return String name of requested author
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name of requested author
     */
    public void setImie(String name) {
        this.name = name;
    }

    /**
     *
     * @return Integer year of requested author
     */
    public Integer getYear() {
        return year;
    }

    /**
     *
     * @param year year of requested author
     */
    public void setYear(Integer year) {
        this.year = year;
    }



}
