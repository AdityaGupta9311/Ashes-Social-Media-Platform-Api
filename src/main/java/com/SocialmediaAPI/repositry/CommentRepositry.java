package com.SocialmediaAPI.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SocialmediaAPI.models.Comment;

@Repository
public interface CommentRepositry extends JpaRepository<Comment, Integer> {

}
