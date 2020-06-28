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

    public T Create(T model);

    public List<T> ReadAll();

    public T ReadById(int id);

    public void Update(T model);

    public void Delete(int id);

}
