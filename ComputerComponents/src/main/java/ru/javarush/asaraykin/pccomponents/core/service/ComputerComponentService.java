package ru.javarush.asaraykin.pccomponents.core.service;


import org.hibernate.exception.ConstraintViolationException;
import ru.javarush.asaraykin.pccomponents.core.dao.ComputerComponentDAOImpl;
import ru.javarush.asaraykin.pccomponents.core.model.ComputerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class ComputerComponentService {

    @Autowired
    public ComputerComponentDAOImpl dao;



       public ComputerComponentService() {
    }

    public ComputerComponent findComponent (int id) throws IllegalArgumentException{
           return dao.findById(id);
    }

    public List<ComputerComponent> getAllComponents(){
        return dao.getAllComponents();
    }

    public List<ComputerComponent> filterByIsNecessary(List<ComputerComponent> computerComponents, int isNecessary){
        return dao.filterByIsNecessary(computerComponents, isNecessary);
    }

    public List<ComputerComponent> getComponentsByPage(int pageNumber, int stringsPerPage, String showFilter){
           return dao.getComponentsByPage(pageNumber, stringsPerPage, showFilter);
    }

    public int countRows(){
           return dao.getNumberOfRows();
    }

    public void delete(int id){
           dao.deleteComputerComponent(findComponent(id));
    }
    public void saveOrUpdate (ComputerComponent computerComponent) throws PersistenceException {
           dao.saveOrUpdateComputerComponent(computerComponent);
    }

    public List<ComputerComponent> findByName(String name){
           return dao.findByName(name);
    }
}
