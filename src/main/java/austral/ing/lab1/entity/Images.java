package austral.ing.lab1.entity;

import austral.ing.lab1.model.Image;
import austral.ing.lab1.model.Origami;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

import static austral.ing.lab1.util.EntityManagers.currentEntityManager;
import static austral.ing.lab1.util.LangUtils.checkedList;
import static austral.ing.lab1.util.Transactions.tx;

public class Images {

    public static Optional<Image> findById(Long id){
        return tx(() ->
                Optional.of(currentEntityManager().find(Image.class, id))
        );
    }

    public static List<Image> listAll() {
        return tx(() ->
                checkedList(currentEntityManager().createQuery("SELECT u FROM Image u").getResultList())
        );
    }


    public static Image persist(Image image) {
        final EntityTransaction tx = currentEntityManager().getTransaction();

        try {
            tx.begin();

            currentEntityManager().persist(image);

            tx.commit();
            return image;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
