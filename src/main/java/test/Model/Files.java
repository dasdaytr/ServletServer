package test.Model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_unique")
    private String nameUnique;

    @Column(name = "extension")
    private String extension;

    public Files(String name, String nameUnique,String extension) {
        this.name = name;
        this.nameUnique = nameUnique;
        this.extension = extension;
    }

    public Files() {

    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUnique() {
        return nameUnique;
    }

    public void setNameUnique(String nameUnique) {
        this.nameUnique = nameUnique;
    }
}
