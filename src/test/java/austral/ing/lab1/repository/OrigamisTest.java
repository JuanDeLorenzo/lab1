package austral.ing.lab1.repository;

import austral.ing.lab1.entity.Origamis;
import austral.ing.lab1.model.Image;
import austral.ing.lab1.model.Origami;
import austral.ing.lab1.util.EntityManagers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class OrigamisTest {
    private EntityManagerFactory managerFactory;

    @After
    public void tearDown() throws Exception {
        managerFactory.close();
    }

    @Before
    public void setUp() {
        managerFactory = Persistence.createEntityManagerFactory("test");
        EntityManagers.setFactory(managerFactory);
    }

    @Test
    public void createOrigami() {
        final Origami origami = new Origami();
        final Image image = new Image();
        final List<Image> images = new ArrayList<>();
        images.add(image);

        origami.setTitle("Oso");
        origami.setDifficulty("Facil");
        origami.setCategory("Animales");
        origami.setImages(images);

        assertThat(Origamis.persist(origami).getId(), greaterThan(0L));

        final Optional<Origami> persistedOrigami = Origamis.findById(origami.getId());

        assertThat(persistedOrigami.isPresent(), is(true));
        assertThat(persistedOrigami.get().getTitle(), is("Oso"));
        assertThat(persistedOrigami.get().getDifficulty(), is("Facil"));
        assertThat(persistedOrigami.get().getCategory(), is("Animales"));
        assertThat(persistedOrigami.get().getImages(), is(images));


    }
}
