package ru.javarush.asaraykin.pccomponents.web.controller;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.*;
import ru.javarush.asaraykin.pccomponents.core.model.ComputerComponent;
import ru.javarush.asaraykin.pccomponents.core.service.ComputerComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.persistence.PersistenceException;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    public ComputerComponentService computerComponentService;


    private int stringsPerPage = 10;


    @RequestMapping(value = {"", "/{pageNumber}"})

    public String showMainPage(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "filter", required = false) String filterAndSorting,
            @RequestParam(value = "crudOperation", required = false) String crudOperation,
            @RequestParam(value = "id", required = false) String id,
            ModelMap model) {

        if (pageNumber == null) {
            pageNumber = 1;
        }

        if (filterAndSorting == null) {
            filterAndSorting = "showAllComponents";
        }

        if (crudOperation != null && crudOperation.equals("delete")) {
            int idInt = Integer.parseInt(id);
            try {
                computerComponentService.delete(idInt);
            } catch (IllegalArgumentException r) {
            }
        }

        List<ComputerComponent> list = computerComponentService.getAllComponents();
        List<ComputerComponent> sortedListForSetNumber = computerComponentService.filterByIsNecessary(list, 1);
        int numberOfSets;
        try {
            numberOfSets = sortedListForSetNumber.get(0).getAmount();
        }
        catch (IndexOutOfBoundsException t){
            numberOfSets = 0;
        }

        list = computerComponentService.getComponentsByPage(pageNumber, stringsPerPage, filterAndSorting);

        int numberOfPages = numberOfPagesCount(computerComponentService.countRows(), stringsPerPage);

        model.addAttribute("numberOfComputerSets", numberOfSets);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("componentsByPage", list);
        model.addAttribute("filterAndSorting", filterAndSorting);
        model.addAttribute("pageNumber", pageNumber);

        return "index";
    }

    @RequestMapping(value = "/edit")
    public String showEditPage(
            @RequestParam(value = "id", required = false) int id,
            ModelMap model) {
        try {
            ComputerComponent computerComponent = computerComponentService.findComponent(id);
            model.addAttribute("computerComponent", computerComponent);
        } catch (IllegalArgumentException e) {
        }
        return "edit";
    }

    @RequestMapping(value = "/add")
    public String showAddPage() {
        return "edit";
    }

    @RequestMapping(value = "/save")
    public String savePage(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "isNecessary", required = true) int isNecessary,
            @RequestParam(value = "amount", required = true) int amount,
            ModelMap model) {

            ComputerComponent computerComponent = new ComputerComponent();
            if (id != null) {
                computerComponent.setId(id);
            }

            computerComponent.setAmount(amount);
            computerComponent.setIsNecessary(isNecessary);
            computerComponent.setName(name);
            boolean ff = computerComponentService.findByName(name).isEmpty();
            try {
                if (!computerComponentService.findByName(name).isEmpty() &&
                        (computerComponentService.findByName(name).get(0).getName().equals(name) &&
                        id == null || (id != null &&
                        computerComponentService.findByName(name).get(0).getId() != id))) {
                    String warning = "Запись с таким именем уже существует";
                    model.addAttribute("warning", warning);
                } else {
                    computerComponentService.saveOrUpdate(computerComponent);
                    model.addAttribute("computerComponent", computerComponent);
                }
            }
            catch (ConstraintViolationException r){

            }

        return "edit";
    }


    @RequestMapping(value = "/search")
    public String showSearchPage(
            @RequestParam(value = "name", required = false) String name,
            ModelMap model) {
        List<ComputerComponent> list = computerComponentService.findByName(name);
        model.addAttribute("componentsByPage", list);
        return "index";
    }

    public int numberOfPagesCount(int numberOfRowsInTable, int stringsPerPage) {
        int number = (int) Math.ceil(numberOfRowsInTable * 1.0 / stringsPerPage);
        if (number == 0) {
            return 1;
        } else {
            return number;
        }
    }
}