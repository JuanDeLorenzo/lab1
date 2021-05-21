package austral.ing.lab1.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ENTRYS")
    private Integer entrys;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToMany
    List<User> users = new ArrayList<>();

    @OneToMany
    List<Origami> origamis = new ArrayList<>();


 }
