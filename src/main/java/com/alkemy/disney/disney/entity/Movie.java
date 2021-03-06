package com.alkemy.disney.disney.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String title;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createDate;

    private Byte calification;
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "movies_charcters",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_charcter"))
    private List<Charcter> movieCharcters = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "movie_gender",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_gender"))
    private List<Gender> movieGender = new ArrayList<>();

    public void addCharcterOnMovie(Charcter charcterAdded){
        this.movieCharcters.add(charcterAdded);
    }

    public void removeCharcterFromMovie(Charcter charcterRemoved){
        this.movieCharcters.remove(charcterRemoved);
    }

    public void addGenderOnMovie(Gender genderAdded){
        this.movieGender.add(genderAdded);
    }

    public void removeGenderFromMovie(Gender genderRemoved){
        this.movieGender.remove(genderRemoved);
    }

}
