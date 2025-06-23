package com.kezhang.tliasbackend.service;

import com.kezhang.tliasbackend.dto.SubjectInsertDTO;
import com.kezhang.tliasbackend.dto.SubjectResponseDTO;
import com.kezhang.tliasbackend.dto.SubjectUpdateDTO;

import java.util.List;

public interface SubjectService {
    /**
     * Get all subjects.
     *
     * @return the List of subjects DTO
     */
    List<SubjectResponseDTO> getAllSubjects();

    /*
    * Delete a subject by id.
    *
    * @param id the id of the subject to be deleted
    * @return the number of rows affected by the deletion
    * */
    void deleteSubjectById(Integer id);

    /*
    * Insert a new subject.
    *
    * @param subjectInsertDTO the DTO containing subject creation details
    * @return the number of rows affected by the insertion
    * */
    void insertSubject(SubjectInsertDTO subjectInsertDTO);

    /*
    * Select a subject by id.
    *
    * @param id the id of the subject to be selected
    *
    * @return the SubjectResponseDTO containing subject details
    *
    * */
    SubjectResponseDTO getSubjectById(Integer id);


    /*
    * Update a subject by id.
    *
    * @param subjectUpdateDTO the DTO containing updated subject details
    *
    * @return the number of rows affected by the update
    * */
    void updateSubjectById(SubjectUpdateDTO subjectUpdateDTO);
} 