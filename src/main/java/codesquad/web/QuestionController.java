package codesquad.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.security.LoginUser;
import codesquad.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "questionService")
	private QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService){
		this.questionService = questionService;
	}

	@GetMapping("/form")
	public String form() {
		return "/qna/form";
	}

	@PostMapping("")
	public String create(@LoginUser User loginUser, Question question){
		questionService.create(loginUser, question);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	String show(@PathVariable Long id, Model model){
		Question question = questionService.findQuestionById(id);
		model.addAttribute("question", question);
		return "/qna/show";
	}
}
