package com.example.forum_backend.Post;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Post {
    private @Id Long id;
    private Date created_at;
    private Date updated_at;
    private String title;

}
