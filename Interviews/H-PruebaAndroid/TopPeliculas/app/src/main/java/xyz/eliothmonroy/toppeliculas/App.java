package xyz.eliothmonroy.toppeliculas;

import android.app.Application;
import xyz.eliothmonroy.toppeliculas.model.repository.RestClient;
import xyz.eliothmonroy.toppeliculas.utils.RetrofitUtils;

/**
 * CLase que nos permite tener una instancia de Retrofit sin necesidad de crearla varias veces
 * De igual forma aquí podemos tener varias configuraciones de la aplicación
 */
public class App extends Application {
    
    private static RestClient restClient;
    
    @Override
    public void onCreate() {
        super.onCreate();
        restClient = RetrofitUtils.getInstance().create(RestClient.class);
    }
    
    /**
     * @return Una instancia de Retrofit para ser usada
     */
    public static RestClient getRestClientInstance() {
        return restClient;
    }
    
}
