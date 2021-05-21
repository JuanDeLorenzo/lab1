package austral.ing.lab1.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Lob
    @Column(name = "DATA")
    private byte[] data;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
