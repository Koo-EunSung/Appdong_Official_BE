package com.appdong.website.repository.form;

import com.appdong.website.entity.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByActiveIsTrue();
    @Query("select distinct f from Form f LEFT join fetch f.questions where f.id = :id")
    Optional<Form> findByIdFetchJoinQuestion(Long id);
}
