package com.langinteger.demo.application.rest;

import com.langinteger.demo.domain.model.Poetry;
import com.langinteger.demo.domain.repository.PoetRepository;
import com.langinteger.demo.domain.repository.PoetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Random;

@Controller
public class PoetryController {

  @Autowired
  PoetryRepository poetryRepo;

  @Autowired
  PoetRepository poetRepo;

  @RequestMapping("/")
  public String randomPoetry(ModelMap map) {

    Poetry poetry = getRandomPoetry();
    map.addAttribute("poetry", poetry);

    return "index";
  }

  private Poetry getRandomPoetry() {
    Random random = new Random();
    int randomNum = random.nextInt(43030);
    Optional<Poetry> poetry = poetryRepo.findById(randomNum);

    while (!poetry.isPresent()) {
      randomNum = random.nextInt(43030);
      poetry = poetryRepo.findById(randomNum);
    }
    return poetry.get();
  }

}
