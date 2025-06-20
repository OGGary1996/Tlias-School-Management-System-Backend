package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.entity.Position;

import java.util.List;

public interface PositionMapper {
    /**
     * select all positions from the database.
     *
     * @return the List of positions after insertion
     */
    List<Position> selectAllPositions();

    /*
    * delete a position by id.
    *
    * @param id the id of the position to be deleted
    *
    * @return the number of rows affected by the deletion
    * */
    Integer deletePositionById(Integer id);

    /*
    * Insert a new position into the database.
    *
    * @param position the position to be inserted
    *
    * @return the number of rows affected by the insertion
    * */
    Integer insertPosition(Position position);


    /*
    * Select a position by id.
    *
    * @param id the id of the position to be selected
    *
    * @return the Position entity containing position details
    *
    * */
    Position selectPositionById(Integer id);


    /*
    * Update a position by id.
    *
    * @param position the position entity containing updated details
    *
    * @return the number of rows affected by the update
    * */
    Integer updatePositionById(Position position);

    /*
    * Select a position id by its name.
    * @param positionName the name of the position to be selected
    * @return the id of the position if found, otherwise null
    * */
    Integer selectPositionIdByName(String name);
}
