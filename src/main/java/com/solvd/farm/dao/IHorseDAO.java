package com.solvd.farm.dao;

import com.solvd.farm.animals.Horse;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IHorseDAO extends IBaseDAO<Horse> {
    @Insert("INSERT INTO Horse (age, name, weight, Farm_idFarm) VALUES (#{age}, #{name}, #{weight}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "idHorse")
    long createHorse(Horse horse);

    @Select("SELECT MAX(idHorse) FROM Horse")
    long getMaxId();

    @Results({
            @Result(property = "idHorse", column = "horse_id"),
            @Result(property = "age", column = "horse_age"),
            @Result(property = "name", column = "horse_name"),
            @Result(property = "weight", column = "horse_weight"),
            @Result(property = "idBelongsFarm", column = "horse_idFarm")
    })
    @Select("SELECT h.idHorse as horse_id, h.age as horse_age, h.name as horse_name, h.weight as horse_weight, h.Farm_idFarm as horse_idFarm FROM Horse h WHERE idHorse = #{id}")
    Horse getHorseById(@Param("id") long idHorse);

    @Results({
            @Result(property = "idHorse", column = "horse_id"),
            @Result(property = "age", column = "horse_age"),
            @Result(property = "name", column = "horse_name"),
            @Result(property = "weight", column = "horse_weight"),
            @Result(property = "idBelongsFarm", column = "horse_idFarm")
    })
    @Select("SELECT h.idHorse as horse_id, h.age as horse_age, h.name as horse_name, h.weight as horse_weight, h.Farm_idFarm as horse_idFarm FROM Horse h WHERE name = #{name}")
    Horse getHorseByName(@Param("name") String name);

    @Results({
            @Result(property = "idHorse", column = "horse_id"),
            @Result(property = "age", column = "horse_age"),
            @Result(property = "name", column = "horse_name"),
            @Result(property = "weight", column = "horse_weight"),
            @Result(property = "idBelongsFarm", column = "horse_idFarm")
    })
    @Select("SELECT h.idHorse as horse_id, h.age as horse_age, h.name as horse_name, h.weight as horse_weight, h.Farm_idFarm as horse_idFarm FROM Horse h WHERE Farm_idFarm = #{idFarm}")
    List<Horse> getAllFarmHorses(@Param("idFarm") int idFarm);

    @Delete("DELETE FROM Horse WHERE idHorse =#{id}")
    void removeHorse(@Param("id") long id);

    @Delete("DELETE FROM Horse WHERE name =#{name}")
    boolean removeHorseName(@Param("name") String name);

    @Update("UPDATE Horse SET name = #{name}, weight = #{weight}, age = #{age} WHERE idHorse = #{idHorse}")
    boolean updateHorse(@Param("name") String name, @Param("weight") double weight, @Param("age") int age, @Param("idHorse") int id);
}
