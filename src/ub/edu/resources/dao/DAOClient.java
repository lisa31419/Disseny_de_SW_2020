package ub.edu.resources.dao;

import ub.edu.model.Client;

public interface DAOClient extends DAO<Client> {

    Client  findClientByUserNameAndPassword(String usuari, String pwd) throws Exception;

}
