package com.kezhang.tliasbackend.mapper;

import com.kezhang.tliasbackend.entity.Subject;

import java.util.List;

public interface SubjectMapper {
    /**
     * select all subjects from the database.
     *
     * @return the List of subjects after insertion
     */
    List<Subject> selectAllSubjects();

    /*
    * delete a subject by id.
    *
    * @param id the id of the subject to be deleted
    *
    * @return the number of rows affected by the deletion
    * */
    Integer deleteSubjectById(Integer id);

    /*
    * Insert a new subject into the database.
    *
    * @param subject the subject to be inserted
    *
    * @return the number of rows affected by the insertion
    * */
    Integer insertSubject(Subject subject);


    /*
    * Select a subject by id.
    *
    * @param id the id of the subject to be selected
    *
    * @return the Subject entity containing subject details
    *
    * */
    Subject selectSubjectById(Integer id);


    /*
    * Update a subject by id.
    *
    * @param subject the subject entity containing updated details
    *
    * @return the number of rows affected by the update
    * */
    Integer updateSubjectById(Subject subject);

    /*
    * Select a subject id by its name.
    * @param subjectName the name of the subject to be selected
    * @return the id of the subject if found, otherwise null
    * */
    Integer selectSubjectIdByName(String name);
} 