

    package services;
import java.util.List;

/**
 *
 * @author Amirov
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T p);
    
     public List<T> afficher();
     public void modifier(T p);
    public boolean supprimer(T p);
    

}
