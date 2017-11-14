package label;

import shampoo.BasicShampoo;

import javax.persistence.*;

@Entity
@Table (name="labels")
public class BasicLabel implements Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Basic
    private String title;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    private BasicShampoo shampoo;

    @Basic
    private String subtitle;

    public BasicLabel(String title, String subtitle) {
        this.title=title;
        this.subtitle=subtitle;
    }

    public BasicLabel() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BasicShampoo getShampoo() {
        return this.shampoo;
    }

    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
