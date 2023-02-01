package com.solvd.farm.dao;

import com.solvd.farm.animals.Hen;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IHenDAO extends IBaseDAO<Hen> {
    @Insert("INSERT INTO Hen (age, name, weight, Farm_idFarm) VALUES (#{age}, #{name}, #{weight}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "idHen")
    long createHen(Hen hen);

    @Select("SELECT MAX(idHen) FROM Hen")
    long getMaxId();

    @Results({
            @Result(property = "idHen", column = "hen_id"),
            @Result(property = "age", column = "hen_age"),
            @Result(property = "name", column = "hen_name"),
            @Result(property = "weight", column = "hen_weight"),
            @Result(property = "idBelongsFarm", column = "hen_idFarm")
    })
    @Select("SELECT h.idHen as hen_id, h.age as hen_age, h.name as hen_name, h.weight as hen_weight, h.Farm_idFarm as hen_idFarm FROM Hen h WHERE idHen = #{id}")
    Hen getHenById(@Param("id") long idHen);

    @Results({
            @Result(property = "idHen", column = "hen_id"),
            @Result(property = "age", column = "hen_age"),
            @Result(property = "name", column = "hen_name"),
            @Result(property = "weight", column = "hen_weight"),
            @Result(property = "idBelongsFarm", column = "hen_idFarm")
    })
    @Select("SELECT h.idHen as hen_id, h.age as hen_age, h.name as hen_name, h.weight as hen_weight, h.Farm_idFarm as hen_idFarm FROM Hen h WHERE name = #{name}")
    Hen getHenByName(@Param("name") String name);

    @Results({
            @Result(property = "idHen", column = "hen_id"),
            @Result(property = "age", column = "hen_age"),
            @Result(property = "name", column = "hen_name"),
            @Result(property = "weight", column = "hen_weight"),
            @Result(property = "idBelongsFarm", column = "hen_idFarm")
    })
    @Select("SELECT h.idHen as hen_id, h.age as hen_age, h.name as hen_name, h.weight as hen_weight, h.Farm_idFarm as hen_idFarm FROM Hen h WHERE Farm_idFarm = #{idFarm}")
    List<Hen> getAllFarmHens(@Param("idFarm") int idFarm);

    @Delete("DELETE FROM Hen WHERE idHen =#{id}")
    void removeHen(@Param("id") long id);

    @Delete("DELETE FROM Hen WHERE name =#{name}")
    boolean removeHenName(@Param("name") String name);

    @Update("UPDATE Hen SET name = #{name}, weight = #{weight}, age = #{age} WHERE idHen = #{idHen}")
    boolean updateHen(@Param("name") String name, @Param("weight") double weight, @Param("age") int age, @Param("idHen") int id);
}
