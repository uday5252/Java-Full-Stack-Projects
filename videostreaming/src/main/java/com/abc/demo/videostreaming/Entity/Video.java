package com.abc.demo.videostreaming.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String description;
    String url;

    @ManyToOne
    @JoinColumn(name = "genreid", nullable = true)
    Genre genre;
    
    @ManyToOne
    @JoinColumn(name = "userid",nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    User user;

    @CreationTimestamp
    Date uploaded_at;

    @Override
    public String toString() {
        return "Video [title=" + title + ", description=" + description + ", url=" + url + ", genre=" + genre
                + ", user=" + user + ", uploaded_at=" + uploaded_at + "]";
    } 
}
