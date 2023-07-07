package com.codeaz.blogapp.articles;

import com.codeaz.blogapp.articles.Exception.ArticleNotFoundException;
import com.codeaz.blogapp.articles.dto.CreateArticleRequest;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.UserRepository;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticlesRepository articlesRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public ArticleService(ArticlesRepository articlesRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.articlesRepository = articlesRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public List<ArticleEntity> getAllArticles()
    {
        return articlesRepository.findAll();
    }
    public ArticleEntity createArticle(CreateArticleRequest articleRequest, Long authorId){
        var author = userRepository.findById(authorId).orElseThrow(()-> new UserNotFoundException(authorId));
        ArticleEntity article = modelMapper.map(articleRequest, ArticleEntity.class);
        article.setAuthor(author);
       //to do slug
        articlesRepository.save(article);
        return article;
    }
    public ArticleEntity updateArticle(CreateArticleRequest updateArticle, Long articleId){
        var article = articlesRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));
        if(!updateArticle.getTitle().isEmpty()){
            article.setTitle(updateArticle.getTitle());
            article.setSlug(updateArticle.getTitle());
        }
        if(! updateArticle.getSubtitle().isEmpty()){
            article.setSubtitle(updateArticle.getSubtitle());
        }
        if(! updateArticle.getBody().isEmpty()){
            article.setBody(updateArticle.getBody());
        }
        return  article;
    }
    public ArticleEntity findArticleBySlug(String slug){
        return articlesRepository.findArticleBySlug(slug).orElseThrow(()-> new ArticleNotFoundException(slug));
    }
}
