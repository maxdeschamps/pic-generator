package com.picgenerator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "image")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="src")
    private String src;

    @ManyToMany
    @JoinTable(name = "images_tags",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name="author", nullable=false)
    @JsonIgnore
    private User author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
