package com.codeaz.blogapp.articles.Exception;


public class ArticleNotFoundException extends  RuntimeException{
    public ArticleNotFoundException(String slug){
        super("Article with slug " + slug + " not found");
    }
    public ArticleNotFoundException(Long id){
        super("Article with id " + id + " not found");
    }
}
