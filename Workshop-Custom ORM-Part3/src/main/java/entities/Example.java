package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

@Entity(name="examples")
public class Example {
    @Id
    @Column(name="Id")
    private Integer id;

    @Column(name="full_name")
    private String fullName;

    public Example(String fullName) {
        this.fullName = fullName;
    }
}
