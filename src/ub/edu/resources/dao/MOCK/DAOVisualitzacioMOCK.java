package ub.edu.resources.dao.MOCK;

import ub.edu.model.Visualitzacio;
import ub.edu.resources.dao.DAOVisualitzacio;

import java.util.*;

public class DAOVisualitzacioMOCK implements DAOVisualitzacio {

    private final Map<String, ArrayList<Visualitzacio>> visualizedSeries = new HashMap<>();

    public DAOVisualitzacioMOCK(){

        // Visualizaciones del Usuario 'id1', del Cliente 'ajaleo'
        ArrayList<Visualitzacio> visualitzacio_1 = new ArrayList<>();
        visualitzacio_1.add(new Visualitzacio(1, "ajaleo", "id1","The Mandalorian", 1, 1, "22/03/2011", 0));
        visualitzacio_1.add(new Visualitzacio(1, "ajaleo", "id1","The Mandalorian", 1, 2, "22/03/2011", 10));

        visualizedSeries.put("id1", visualitzacio_1);

        // Visualizaciones del Usuario 'id2', del Cliente 'ajaleo'
        ArrayList<Visualitzacio> visualitzacio_2 = new ArrayList<>();
        visualitzacio_2.add(new Visualitzacio(2, "dtomacal", "id4","Mr Robot", 1, 1, "22/03/2011", 0));
        visualitzacio_2.add(new Visualitzacio(3, "dtomacal", "id4", "Breaking Bad", 1, 1, "22/03/2011", 0));
        visualitzacio_2.add(new Visualitzacio(4, "dtomacal", "id4", "The Mandalorian", 1, 1, "22/03/2011", 30));
        visualitzacio_2.add(new Visualitzacio(5, "dtomacal", "id4", "Stranger Things", 1, 1, "22/03/2011", 20));
        visualizedSeries.put("id4", visualitzacio_2);

        // Visualizaciones del Usuario 'id7', del Cliente 'dtomacal'
        ArrayList<Visualitzacio> visualitzacio_3 = new ArrayList<>();
        visualitzacio_3.add(new Visualitzacio(6, "chachipistachi", "id7", "Stranger Things", 1, 1, "22/03/2011", 0));
        visualitzacio_3.add(new Visualitzacio(7, "chachipistachi", "id7", "Stranger Things", 1, 2, "22/03/2011", 0));
        visualitzacio_3.add(new Visualitzacio(8, "chachipistachi", "id7", "Game of Thrones", 1, 1, "22/03/2011", 30));
        visualitzacio_3.add(new Visualitzacio(9, "chachipistachi", "id7", "Breaking Bad", 1, 1, "22/03/2011", 20));
        visualizedSeries.put("id7", visualitzacio_3);

        // Visualizaciones del Usuario 'id14', del Cliente ''
        ArrayList<Visualitzacio> visualitzacio_4 = new ArrayList<>();
        visualitzacio_4.add(new Visualitzacio(1, "", "id14","The Mandalorian", 1, 1, "22/03/2011", 0));
        visualitzacio_4.add(new Visualitzacio(1, "", "id14","The Mandalorian", 1, 2, "22/03/2011", 0));
        visualitzacio_4.add(new Visualitzacio(1, "", "id14","The Mandalorian", 1, 3, "22/03/2011", 2));

        visualizedSeries.put("id14", visualitzacio_4);
    }

    /**
     * M??todo para devolver todos las series en visualizacion de todos los Usuarios.
     * @return list de todos las visualizaciones (asociaciones Usuari - Serie).
     */
    @Override
    public List<Visualitzacio> getAll()  {
        List<Visualitzacio> llistaCompleta = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Visualitzacio>> entry: visualizedSeries.entrySet()) llistaCompleta.addAll(entry.getValue());
        return llistaCompleta;
    }

    /**
     * M??todo para a??adir una visualizaci??n a la base de datos de series preferidas.
     * @param s Visualitzacio, nueva asociaci??n entre Usuari y Serie
     * @return True si se ha a??adido correctamente, false sino.
     */
    @Override
    public boolean add(Visualitzacio s) {
        if (!visualizedSeries.containsKey(s.getIdUser())) visualizedSeries.put(s.getIdUser(), new ArrayList<>());
        return visualizedSeries.get(s.getIdUser()).add(s);
    }

    /**
     * M??todo para deletear cierta asociaci??n del tipo Visualitzacio.
     * @param s Id del Usuario
     * @return True si se ha encontrado y eliminado la visualitzacio.
     */
    @Override
    public boolean delete(Visualitzacio s) {
        if (!visualizedSeries.containsKey(s.getIdUser())) return false;
        return visualizedSeries.get(s.getIdUser()).remove(s);
    }

    /**
     * M??todo para obtener el listado de series visualizadas de un Usuario
     * @param userID Id del usuario
     * @return Listado de associaciones del tipo Visualitzacio.
     */
    @Override
    public List<Visualitzacio> getVisualizedSeriesByIdUser(String userID) {
        if (!visualizedSeries.containsKey(userID)) return null;
        return visualizedSeries.get(userID);
    }

    @Override
    public Map<String, ArrayList<Visualitzacio>> getMap() {
        return new HashMap<>(visualizedSeries);
    }

    @Override
    public Optional<Visualitzacio> getById(String id) { return Optional.empty(); }

    @Override
    public boolean update(Visualitzacio visualization, String[] params) { return false; }

}
