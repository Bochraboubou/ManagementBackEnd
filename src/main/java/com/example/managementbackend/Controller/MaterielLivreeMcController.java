package com.example.managementbackend.Controller;

import com.example.managementbackend.Service.MaterielLivreeMcService;
import com.example.managementbackend.Service.MaterielLivreeProService;
import com.example.managementbackend.dto.ArticleDTO;
import com.example.managementbackend.model.MaterielLivreePro;
import com.example.managementbackend.model.MaterielleLivreeMC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class MaterielLivreeMcController {

    @Autowired
    MaterielLivreeMcService materielLivreeMcService;



    @PostMapping("/bondLivraisonMC/{blMCid}/article/{articleid}/MaterielLivreeMC")
    public MaterielleLivreeMC createArticleUtilisee(@PathVariable(value = "blMCid") Long blMCid, @PathVariable (value = "articleid") Long articleid,
                                                   @Valid @RequestBody MaterielleLivreeMC materielLivreeMC) {
        return materielLivreeMcService.create(blMCid, articleid, materielLivreeMC);
    }


    @GetMapping("/materielLivreeJoinMCbyBL/bl/{blId}")
    public List<ArticleDTO> getArticlesRealiseesByBLdeMC(@PathVariable(value = "blId") long blId){
        return materielLivreeMcService.getMaterielLivreeByBLdeMC(blId);

    }
}
