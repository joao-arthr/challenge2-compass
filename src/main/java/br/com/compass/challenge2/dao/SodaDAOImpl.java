package br.com.compass.challenge2.dao;

import br.com.compass.challenge2.domain.entity.Soda;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SodaDAOImpl implements SodaDAO {
    private EntityManager entityManager;

    @Autowired
    public SodaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Soda theSoda) {
        entityManager.persist(theSoda);
    }
}
