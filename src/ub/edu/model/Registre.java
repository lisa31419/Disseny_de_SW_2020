package ub.edu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registre {
    // Atributos
    private Map<String, ArrayList<Preferencia>> preferencias;
    private Map<String, ArrayList<ub.edu.model.Visualitzacio>> visualitzacions;
    private Map<String, ArrayList<CorValoracio>> corsValoracio;
    private Map<String, ArrayList<EstrellasValoracio>> estrellasValoracio;

    /**
     * Metodo constructor de Registre
     */
    public Registre(){
        this.preferencias = new HashMap<>();
        this.visualitzacions = new HashMap<>();
        this.corsValoracio = new HashMap<>();
        this.estrellasValoracio = new HashMap<>();
    }

    /**
     *
     * @param allPreferencias lista de todas las Preferencias
     * @param corValoracions lista de todas las Valoraciones con Corazones
     * @param estrellasValoracions lista de todas las Valoraciones con Estrellas
     * @param allVisualitzacions lista de todas las Visualizaciones
     */
    public void init( Map<String, ArrayList<Preferencia>> allPreferencias, Map<String, ArrayList<CorValoracio>> corValoracions, Map<String, ArrayList<EstrellasValoracio>> estrellasValoracions, Map<String, ArrayList<ub.edu.model.Visualitzacio>> allVisualitzacions) {
        this.preferencias = allPreferencias;
        this.visualitzacions = allVisualitzacions;
        this.corsValoracio = corValoracions;
        this.estrellasValoracio = estrellasValoracions;
    }



    //////////////////////////////////////
    /*     METODOS SOBRE PREFERENCIA    */
    //////////////////////////////////////

    /**
     * Metodo para Listar las Series preferidas de un Usuario
     * @param idUser ID del Usuario
     * @return lista de los titulos de las Series que prefiere un Usuario
     */
    public List<String> listPreferenciasById(String idUser) {
        List<String> titols = new ArrayList<>();
        if (!preferencias.containsKey(idUser)) return titols;
        for (Preferencia pref: preferencias.get(idUser)) titols.add(pref.getIdSerie());
        return titols;
    }

    /**
     * Metodo para encontrar la Preferencia de Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Preferencia encontrada o null si no existe
     */
    public Preferencia findPreferencia(String idUser, String idSerie) {
        if (preferencias.containsKey(idUser)){
            for (Preferencia pref: preferencias.get(idUser))
                if (pref.getIdSerie().equals(idSerie)) return pref;
        } return null;
    }

    /**
     * Metodo para añadir una Preferencia de Serie a un Usuario de un Cliente
     * @param id ID de la Preferencia
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return True si se ha añadido correctamente, False si no se ha podido realizar
     */
    public boolean addPreferencia(int id, String idClient, String idUser, String idSerie) {
        if (!preferencias.containsKey(idUser)) {
            preferencias.put(idUser, new ArrayList<>());
            return preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
        }
        if (findPreferencia(idUser, idSerie) == null) return preferencias.get(idUser).add(new Preferencia(id, idClient, idUser, idSerie));
        return false;
    }

    /**
     * Metodo para eliminar una Preferencia de Serie de un Usuario de un Cliente
     * @param id ID de la Preferencia
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return True si se ha elimando correctamente, False si no se ha podido realizar
     */
    public boolean removePreferencia(int id, String idClient, String idUser, String idSerie) {
        if (findPreferencia(idUser, idSerie) != null) {
            return preferencias.get(idUser).remove(findPreferencia(idUser, idSerie));
        } return false;
    }



    //////////////////////////////////////
    /*    METODOS SOBRE VISUALITZACIO   */
    //////////////////////////////////////

    /**
     * Metodo para listar todas las Series que ha visto un Usuario
     * @param idUser ID del Usuario
     * @return lista con los titulos de las Series
     */
    public List<String> listVisualitzacionsById(String idUser) {
        List<String> titols = new ArrayList<>();
        if (!visualitzacions.containsKey(idUser)) return titols;
        for (ub.edu.model.Visualitzacio repr: visualitzacions.get(idUser)) titols.add(repr.getIdSerie());
        return titols;
    }

    /**
     * Metodo para encontrar una Visualitzacio de una Serie de un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @return Visualitzacio si la encuentra o null si no existe
     */
    public ub.edu.model.Visualitzacio findVisualitzacio(String idUser, String idSerie) {
        if (visualitzacions.containsKey(idUser)) {
            for (ub.edu.model.Visualitzacio repr : visualitzacions.get(idUser)) {
                if (repr.getIdSerie().equals(idSerie)) return repr;
            }
        } return null;
    }

    /**
     * Metodo para añadir una Visualizacion de una Serie a un Usuario
     * @param id ID de la Visualizacion
     * @param idClient Id del Cliente
     * @param idUser ID del Usuario
     * @param idSerie Id de la Serie
     * @return True si se ha podido añadir correctamente, False si ha fallado la operacion
     */
    public boolean addVisualitzacio(int id, String idClient, String idUser, String idSerie){
        if (!visualitzacions.containsKey(idUser)) {
            visualitzacions.put(idUser, new ArrayList<>());
            return visualitzacions.get(idUser).add(new ub.edu.model.Visualitzacio(id, idClient, idUser, idSerie));
        }
        if (findVisualitzacio(idUser, idSerie) == null) return visualitzacions.get(idUser).add(new ub.edu.model.Visualitzacio(id, idClient, idUser, idSerie));
        return false;
    }

    /**
     * Metodo para eliminar una Visualizacion de una Serie de un Usuario
     * @param id ID de la Visualizacion
     * @param idClient Id del Cliente
     * @param idUser ID del Usuario
     * @param idSerie Id de la Serie
     * @return True si se ha podido eliminar correctamente, False si ha fallado la operacion
     */
    public boolean removeVisualitzacio(int id, String idClient, String idUser, String idSerie){
        if (findVisualitzacio(idUser, idSerie) != null) {
            return visualitzacions.get(idUser).remove(findVisualitzacio(idUser, idSerie));
        } return false;
    }



    //////////////////////////////////////
    /*    METODOS SOBRE COR VALORACIO   */
    //////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Corazon de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Corazon encontrada o null si no existe
     */
    public CorValoracio findCorValoracio(String idUser, String idSerie, int idTemp, int idEpisodi){
        if (corsValoracio.containsKey(idUser)) {
            for (CorValoracio corVal: corsValoracio.get(idUser)) {
                if (corVal.getIdSerie().equals(idSerie) && corVal.getIdTemporada() == idTemp
                        && corVal.getIdEpisodi() == idEpisodi) return corVal;
            }
        } return null;
    }

    /**
     * Metodo para valorar con un Corazon un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Corazon
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void addCorValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, String data){
        if (!corsValoracio.containsKey(idUser)){
            corsValoracio.put(idUser, new ArrayList<>());
            corsValoracio.get(idUser).add( new CorValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, data));
        }
        if (findCorValoracio(idUser, idSerie, idTemp, idEpisodi) == null) corsValoracio.get(idUser).add(new CorValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, data));
    }

    /**
     * Metodo para eliminar una valoracion con Corazon un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Corazon
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void removeCorValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, String data){
        if (findCorValoracio(idUser, idSerie, idTemp, idEpisodi) != null) corsValoracio.get(idUser).remove(findCorValoracio(idUser, idSerie, idTemp, idEpisodi));

    }



    ////////////////////////////////////////////
    /*    METODOS SOBRE ESTRELLAS VALORACIO   */
    ////////////////////////////////////////////

    /**
     * Metodo para encontrar una Valoracion con Estrellas de un Episosio hecha por un Usuario
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @return Valoracion con Estrellas encontrada o null si no existe
     */
    public EstrellasValoracio findEstrellasValoracio(String idUser, String idSerie, int idTemp, int idEpisodi){
        if (estrellasValoracio.containsKey(idUser)) {
            for (EstrellasValoracio starVal: estrellasValoracio.get(idUser)) {
                if (starVal.getIdSerie().equals(idSerie)
                    && starVal.getIdTemporada() == idTemp
                    && starVal.getIdEpisodi() == idEpisodi) return starVal;
            }
        } return null;
    }

    /**
     * Metodo para valorar con Estrellas un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void addEstrellaValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, int numEstrelles, String data){
        if (!estrellasValoracio.containsKey(idUser)) {
            estrellasValoracio.put(idUser, new ArrayList<>());
            estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, numEstrelles, data));
        }
        if (findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi) == null) estrellasValoracio.get(idUser).add(new EstrellasValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, numEstrelles, data));

    }

    /**
     * Metodo para eliminar una valoracion con Estrellas un Episodio por pàrte de un Usuario
     * @param id ID de la Valoracion con Estrellas
     * @param idClient ID del Cliente
     * @param idUser ID del Usuario
     * @param idSerie ID de la Serie
     * @param idTemp ID de la Temporada
     * @param idEpisodi Id del Episodio
     * @param data fecha de la valoracion
     */
    public void removeEstrellaValoracio(int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, int numEstrelles, String data){
        if (findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi) != null) estrellasValoracio.get(idUser).remove(findEstrellasValoracio(idUser, idSerie, idTemp, idEpisodi));
    }

}