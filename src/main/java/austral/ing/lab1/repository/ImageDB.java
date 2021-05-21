package austral.ing.lab1.repository;

import austral.ing.lab1.model.Image;
import austral.ing.lab1.model.Origami;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ImageDB {

    private final EntityManager entityManager;

    public ImageDB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Image> findById(Long id){
        return tx(() ->
                Optional.of(entityManager.find(Image.class, id))
        );
    }

    public <R> R tx(Supplier<R> s) {
        final EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();

            R r = s.get();

            tx.commit();
            return r;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public void tx(Runnable r){
        final EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();

            r.run();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public List<Image> listAll() {
        return Collections.emptyList();
    }

    public Image persist(Image image) {
        final EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();

            entityManager.persist(image);

            tx.commit();
            return image;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
