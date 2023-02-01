package com.solvd.farm.dao;

import com.solvd.farm.animals.Cow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICowDAO extends IBaseDAO<Cow> {
    @Insert("INSERT INTO Cow (age, name, weight, Farm_idFarm) VALUES (#{age}, #{name}, #{weight}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "idCow")
    long createCow(Cow cow);

    @Select("SELECT MAX(idCow) FROM Cow")
    long getMaxId();

    @Results({
            @Result(property = "idCow", column = "cow_id"),
            @Result(property = "age", column = "cow_age"),
            @Result(property = "name", column = "cow_name"),
            @Result(property = "weight", column = "cow_weight"),
            @Result(property = "idBelongsFarm", column = "cow_idFarm")
    })
    @Select("SELECT c.idCow as cow_id, c.age as cow_age, c.name as cow_name, c.weight as cow_weight, c.Farm_idFarm as cow_idFarm FROM Cow c WHERE idCow = #{id}")
    Cow getCowById(long idCow);

    @Results({
            @Result(property = "idCow", column = "cow_id"),
            @Result(property = "age", column = "cow_age"),
            @Result(property = "name", column = "cow_name"),
            @Result(property = "weight", column = "cow_weight"),
            @Result(property = "idBelongsFarm", column = "cow_idFarm")
    })
    @Select("SELECT c.idCow as cow_id, c.age as cow_age, c.name as cow_name, c.weight as cow_weight, c.Farm_idFarm as cow_idFarm FROM Cow c WHERE name = #{name}")
    Cow getCowByName(String name);

    @Select("SELECT idCow as cow_id, age as cow_age, name as cow_name, weight as cow_weight, Farm_idFarm as cow_idFarm FROM Cow WHERE Farm_idFarm = #{idFarm}")
    @Results(value = {
            @Result(property = "idCow", column = "cow_id"),
            @Result(property = "age", column = "cow_age"),
            @Result(property = "name", column = "cow_name"),
            @Result(property = "weight", column = "cow_weight"),
            @Result(property = "idBelongsFarm", column = "cow_idFarm")
    })
    List<Cow> getAllFarmCows(int idFarm);

    @Delete("DELETE FROM Cow WHERE idCow =#{id}")
    void removeCow(long id);

    @Delete("DELETE FROM Cow WHERE name =#{name}")
    boolean removeCowName(@Param("name") String name);

    @Update("UPDATE Cow SET name = #{name}, weight = #{weight}, age = #{age} WHERE idCow = #{idCow}")
    boolean updateCow(@Param("name") String name, @Param("weight") double weight, @Param("age") int age, @Param("idCow") int id);
}
