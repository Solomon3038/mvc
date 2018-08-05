package com.solomon.mvc.repository;

import com.solomon.mvc.entity.ToDo;
import com.solomon.mvc.repository.exception.NoResultToDoPersistenseException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.UUID;


@NoRepositoryBean
public class ToDoRepositoryImp extends SimpleJpaRepository<ToDo, UUID> {

    public ToDoRepositoryImp(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public ToDoRepositoryImp(Class domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public void deleteById(UUID uuid) {
        try {
            super.deleteById(uuid);
        } catch(NoResultException ex){
            throw new NoResultToDoPersistenseException(ex);
        }
    }
}
