package dao;

import exception.EmptyStorageException;
import model.UserModel;
import exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private long nextId = 1L;

    private final List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model){
        model.setId(nextId++);
        models.add(model);
        return model;
    }
    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());
        if (model.getName() != null) toUpdate.setName(model.getName());
        if (model.getEmail() != null) toUpdate.setEmail(model.getEmail());
        if (model.getBirthday() != null) toUpdate.setBirthday(model.getBirthday());
        return toUpdate;

    }
    public void delete(final long id) throws UserNotFoundException{
        var toDelete = findById(id);
        models.remove(toDelete);

    }
    public UserModel findById(final long id){
        var message = String.format("Nao existe usuario com o id %s cadastrado",id);
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst().
                orElseThrow(() -> new UserNotFoundException(message));
    }
    public List<UserModel> findAll(){
       List <UserModel> result ;
        try{
            verifyStorage();
            result = models;
        } catch (EmptyStorageException ex) {
           ex.printStackTrace();
           result = new ArrayList<>();
        }
        return new ArrayList<>(result);
    }
    private void verifyStorage(){
        if(models.isEmpty())throw new EmptyStorageException("O armazenamento esta vazio!");
    }

}
