package ub.edu.resources.dao;

import ub.edu.model.Client;
import ub.edu.model.Usuari;

import java.util.List;

public interface DAOUsuari extends DAO<Usuari> {

    List<Usuari> getUsuarisForClient(Client c);

}
