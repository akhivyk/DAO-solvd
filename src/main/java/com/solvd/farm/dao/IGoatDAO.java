package com.solvd.farm.dao;

import com.solvd.farm.animals.Goat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IGoatDAO extends IBaseDAO<Goat> {
    List<Goat> getAllGoats();

    Goat getGoatById(long idGoat);

    Goat getGoatByName(String name);

    @Results({
            @Result(property = "idGoat", column = "goat_id"),
            @Result(property = "age", column = "goat_age"),
            @Result(property = "name", column = "goat_name"),
            @Result(property = "weight", column = "goat_weight"),
            @Result(property = "idBelongsFarm", column = "goat_idFarm")
    })
    @Select("SELECT g.idGoat as goat_id, g.age as goat_age, g.name as goat_name, g.weight as goat_weight, g.Farm_idFarm as goat_idFarm FROM Goat g WHERE Farm_idFarm = #{idFarm}")
    List<Goat> getAllFarmGoats(@Param("idFarm") int idFarm);
}
