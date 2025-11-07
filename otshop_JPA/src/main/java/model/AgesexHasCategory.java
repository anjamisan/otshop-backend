package model;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agesex_has_category")
public class AgesexHasCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agesex_id")
    private Agesex agesex;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    
    public AgesexHasCategory() {
    	
    }

    // getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Agesex getAgesex() { return agesex; }
    public void setAgesex(Agesex agesex) { this.agesex = agesex; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}

