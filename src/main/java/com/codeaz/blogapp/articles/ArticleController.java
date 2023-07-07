package com.codeaz.blogapp.articles;

import com.codeaz.blogapp.articles.Exception.ArticleNotFoundException;
import com.codeaz.blogapp.articles.dto.CreateArticleRequest;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService service) {
        this.articleService = service;
    }
    @GetMapping("")
    public ResponseEntity<List<ArticleEntity>> getAllArticles(){
        return ResponseEntity.ok(articleService.getAllArticles());
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ArticleEntity> getArticleBySlug(@PathVariable String slug)  throws ArticleNotFoundException{
        return ResponseEntity.ok(articleService.findArticleBySlug(slug));
    }
    @PostMapping("/{authorId}")
    public ResponseEntity<ArticleEntity> createArticle(@RequestBody CreateArticleRequest request, @PathVariable Long authorId)
            throws UserNotFoundException {
        var article = articleService.createArticle(request, authorId);
        var uri= URI.create("/api/articles" + article.getId());
        return ResponseEntity.created(uri).body(article);
    }
    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleEntity> updateArticle(@RequestBody CreateArticleRequest request, @PathVariable Long articleId)
            throws ArticleNotFoundException {
        return ResponseEntity.ok(articleService.updateArticle(request, articleId));
    }
}
