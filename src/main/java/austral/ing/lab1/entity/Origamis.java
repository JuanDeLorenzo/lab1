package austral.ing.lab1.entity;

import austral.ing.lab1.model.Origami;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import static austral.ing.lab1.util.EntityManagers.currentEntityManager;
import static austral.ing.lab1.util.LangUtils.checkedList;
import static austral.ing.lab1.util.Transactions.tx;

public class Origamis {

    public static Optional<Origami> findById(Long id){
        return tx(() ->
                Optional.of(currentEntityManager().find(Origami.class, id))
        );
    }

    public static List<Origami> listAll() {
        return tx(() ->
                checkedList(currentEntityManager().createQuery("SELECT u FROM Origami u").getResultList())
        );
    }



    public static List<Origami> search(String title, String category, String difficulty){
        if (category.equals("All") && difficulty.equals("All")){
            return tx(() ->
                    checkedList(currentEntityManager().createQuery("SELECT u FROM Origami u WHERE u.title LIKE :title").setParameter("title", title).getResultList())
            );
        } else if(category.equals("All")){
            return tx(() ->
                    checkedList(currentEntityManager().createQuery("SELECT u FROM Origami u WHERE u.category =: category and u.title LIKE :title ").getResultList())
            );
        } else if(difficulty.equals("All")){
            return tx(() ->
                    checkedList(currentEntityManager().createQuery("SELECT u FROM Origami u WHERE u.difficulty =: difficulty and u.title LIKE :title ").getResultList())
            );
        } else{
            return tx(() ->
                    checkedList(currentEntityManager().createQuery("SELECT u FROM Origami u WHERE u.difficulty =:difficulty and u.category =: category and u.title LIKE :title ").getResultList())
            );
        }

    }


    public static Origami persist(Origami origami) {
        final EntityTransaction tx = currentEntityManager().getTransaction();

        try {
            tx.begin();

            currentEntityManager().persist(origami);

            tx.commit();
            return origami;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }


}
