package com.solvd.farm.dao;

import com.solvd.farm.animals.Sheep;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISheepDAO extends IBaseDAO<Sheep> {
    List<Sheep> getAllSheep();

    Sheep getSheepById(long idSheep);

    Sheep getSheepByName(String name);

    List<Sheep> getAllSheepColor(String color);

    @Results({
            @Result(property = "idSheep", column = "sheep_id"),
            @Result(property = "age", column = "sheep_age"),
            @Result(property = "name", column = "sheep_name"),
            @Result(property = "weight", column = "sheep_weight"),
            @Result(property = "idBelongsFarm", column = "sheep_idFarm"),
            @Result(property = "color", column = "sheep_color")
    })
    @Select("SELECT s.idSheep as sheep_id, s.age as sheep_age, s.name as sheep_name, s.weight as sheep_weight, s.color as sheep_color, s.Farm_idFarm as sheep_idFarm FROM Sheep s WHERE Farm_idFarm = #{idFarm}")
    List<Sheep> getAllFarmSheep(@Param("idFarm") int idFarm);
}
