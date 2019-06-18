package com.cloud.eurekaclient.user.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "city")
public class City {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.id
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     */
    @Id
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.provinceId
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     */
    @Column(name = "provinceid")
    private Long provinceid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.cityName
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     */
    @Column(name = "cityname")
    private String cityname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.description
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     */
    private String description;

   /* *//**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.id
     *
     * @return the value of city.id
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public Long getId() {
        return id;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.id
     *
     * @param id the value for city.id
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public void setId(Long id) {
        this.id = id;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.provinceId
     *
     * @return the value of city.provinceId
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public Long getProvinceid() {
        return provinceid;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.provinceId
     *
     * @param provinceid the value for city.provinceId
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.cityName
     *
     * @return the value of city.cityName
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public String getCityname() {
        return cityname;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.cityName
     *
     * @param cityname the value for city.cityName
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.description
     *
     * @return the value of city.description
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public String getDescription() {
        return description;
    }

    *//**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.description
     *
     * @param description the value for city.description
     *
     * @mbg.generated Mon Jun 17 22:47:22 CST 2019
     *//*
    public void setDescription(String description) {
        this.description = description;
    }*/
}