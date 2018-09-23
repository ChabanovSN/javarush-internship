package ru.javarush.asaraykin.pccomponents.core.dao;

import ru.javarush.asaraykin.pccomponents.core.model.ComputerComponent;

import java.util.List;

public interface ComputerComponentDAO {


    void deleteComputerComponent(ComputerComponent computerComponent);

    void saveOrUpdateComputerComponent(ComputerComponent computerComponent);

    ComputerComponent findById(int id) throws Exception;

    List<ComputerComponent> findByName(String name);

    List<ComputerComponent> getAllComponents();

    List<ComputerComponent> filterByIsNecessary(List<ComputerComponent> computerComponents, int isNecessary);

    List<ComputerComponent> getComponentsByPage(int pageNumber, int stringsPerPage, String showFilter);
}
