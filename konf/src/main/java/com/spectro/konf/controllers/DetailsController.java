package com.spectro.konf.controllers;


import com.spectro.konf.dao.DetailsDAO;
import com.spectro.konf.models.Details;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/zapchasti")
public class DetailsController {

    private final DetailsDAO detailsDAO;

    @Autowired
    public DetailsController(DetailsDAO detailsDAO) {
        this.detailsDAO = detailsDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("zapchasti", detailsDAO.index());
    // Получим все запчасти из ДАО и передадим на отображение в представление
        return "zapchasti/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
//    Получим запчасть по id из дао и передадим на отображение в представление

        model.addAttribute("her", detailsDAO.show(id));
        return "zapchasti/show";
    }


    @GetMapping("/new")
    public String newDetail(Model model){
        model.addAttribute("detail", new Details());

        return "zapchasti/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("detail") @Valid Details detail,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "zapchasti/new";
        }
            detailsDAO.save(detail);
            return "redirect:/zapchasti";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("detail", detailsDAO.show(id));
        return "zapchasti/edit";
    }




    @PatchMapping("/{id}")
    public String update(@ModelAttribute("detail") @Valid Details details,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "zapchasti/edit";
        }

        detailsDAO.update(id, details);
        return "redirect:/zapchasti";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        detailsDAO.delete(id);
        return "redirect:/zapchasti";
    }





}
