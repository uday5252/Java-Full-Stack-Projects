package com.sivasai.endGame.SpringFinalProject.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Column(unique = true)
    private String title;

    private String description;

    private String url;

    @ManyToMany // lazy it is the default fetch type
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Set<Genre> genre_id = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User uploadedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp uploaded_at;

    public Video(String title, String description, String url, User uploaded_by) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.uploadedBy = uploaded_by;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Genre> getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Set<Genre> genre_id) {
        this.genre_id = genre_id;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Timestamp getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(Timestamp uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public void addGenres(Set<Genre> genres){
        for(Genre g: genres){
            genre_id.add(g);
        }
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", genre='" + forGenres() + '\''+
                ", uploaded_by=" + uploadedBy.toStringForVideo() +
                ", uploaded_at=" + uploaded_at +
                '}';
    }

    public String forGenres(){
        String str = "";
        for(Genre genre : genre_id){
            str = str.concat(genre.toStringForVideo());
        }
        return str;
    }
}
