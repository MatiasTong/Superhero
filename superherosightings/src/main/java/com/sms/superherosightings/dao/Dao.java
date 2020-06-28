/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import java.util.List;

/**
 *
 * @author matiastong
 */
public interface Dao<T> {

    public T create(T model);

    public List<T> readAll();

    public T readById(int id);

    public void update(T model);

    public void delete(int id);

}
