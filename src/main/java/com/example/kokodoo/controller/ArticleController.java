package com.example.kokodoo.controller;

import com.example.kokodoo.dto.ArticleDto;
import com.example.kokodoo.dto.CommentDto;
import com.example.kokodoo.entity.Article;
import com.example.kokodoo.repository.ArticleRepository;
import com.example.kokodoo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 자동 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;  // 댓글 목록 조회를 위해

    // 게시글 목록 페이지
    @GetMapping("/articles")
    public String index(Model model){
        //1 모든 Article 을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        //2 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //뷰 페이지를 설계한다
        return "articles/index"; // articles/index.mustache
    }
    // 게시글 생성 페이지
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    // 게시글 생성 요청
    @PostMapping("/articles/create")
    public String createArticle(ArticleDto articleDtodto) {
        //System.out.println(form); > 로깅으로 대체

        // DTO 를 Entity 로 변환
        Article article = articleDtodto.toEntity();
        //System.out.println(article.toString());

        // Repository 에게 Entity 를 DB 에 저장하게
        Article saved = articleRepository.save(article);

        return "redirect:/articles/" + saved.getId();
        // redirect 를 이용해 articles/new 에서 데이터 입력시 해당 데이터 아이디 페이지로 이동
    }
    // 단독 게시글 페이지
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        //1 id로 데이터를 가져오고
        Article articleEntity = articleRepository.findById(id).orElse(null); // ENTITY에 id 값이 없으면 null 반환

        List<CommentDto> commentDtos = commentService.comments(id); // 댓글 목록 조회를 위해
        //2 가져온 데이터를 모델에 등록하고
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos); // 댓글 목록 조회를 위한 모델
        //3 보여줄 페이지를 설정한다.
        return "articles/show";
    }
    // 게시글 수정 페이지
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정
        return "articles/edit";
    }
    // 게시글 수정 요청 페이지
    @PostMapping("/articles/update")
    public String update(ArticleDto articleDto){
        //1. dto 를 enity 로 변환
        Article articleEntity = articleDto.toEntity();
        //2. 엔티티를 db 로 저장
        //2-1 DB 에 기존 데이터를 가져온다.repository 이용
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2 기존 데이터 값을 수정한다. null 이 아닐경우 갱신하도록
        if (target != null){
            articleRepository.save(articleEntity);
        }
        //3. 수정 결과 페이지로 redirect
        return "redirect:/articles/" + articleEntity.getId();
    }
    //게시글 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        // 모델 대신 Redirect... 변수명으로 선언_ 삭제 메시지
        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        //2. 대상을 삭제한다.
        if (target != null){
            articleRepository.delete(target);
            redirect.addFlashAttribute("msg","Delete complete");
            // addFlash... 휘발성 데이터로 msg 에 삭제 메시지 담기
            // header 파일에 msg 가 있을경우 {{#msg}} 를 조건으로 코드 추가
        }
        //3. 결과페이지로 리다이렉트
        return "redirect:/articles/";
    }





    // 여행 계획 미리 만들어논 게시판 들 _ 사이트 기본 제공 계획들
    @GetMapping("/articles/example/kim")
    public String kim(){return "/articles/example/kim";}
    @GetMapping("/articles/example/park")
    public String park(){return "/articles/example/park";}
    @GetMapping("/articles/example/jung")
    public String jung(){return "/articles/example/jung";}
    @GetMapping("/articles/example/hwan")
    public String hwan(){return "/articles/example/hwan";}

}
