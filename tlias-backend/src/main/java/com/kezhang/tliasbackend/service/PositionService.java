package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.PositionInsertDTO;
import com.kezhang.tliasbackend.dto.PositionResponseDTO;
import com.kezhang.tliasbackend.dto.PositionUpdateDTO;

import java.util.List;

public interface PositionService {
    /**
     * Get all positions.
     *
     * @return the List of positions DTO
     */
    List<PositionResponseDTO> getAllPositions();

    /*
    * Delete a position by id.
    *
    * @param id the id of the position to be deleted
    * @return the number of rows affected by the deletion
    * */
    void deletePositionById(Integer id);

    /*
    * Insert a new position.
    *
    * @param positionInsertDTO the DTO containing position creation details
    * @return the number of rows affected by the insertion
    * */
    void insertPosition(PositionInsertDTO positionInsertDTO);

    /*
    * Select a position by id.
    *
    * @param id the id of the position to be selected
    *
    * @return the PositionResponseDTO containing position details
    *
    * */
    PositionResponseDTO getPositionById(Integer id);


    /*
    * Update a position by id.
    *
    * @param positionUpdateDTO the DTO containing updated position details
    *
    * @return the number of rows affected by the update
    * */
    void updatePositionById(PositionUpdateDTO positionUpdateDTO);
}