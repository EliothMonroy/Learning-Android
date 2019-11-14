package xyz.eliothmonroy.toppeliculas;

import android.app.Application;
import android.content.Context;

import xyz.eliothmonroy.toppeliculas.model.repository.RestClient;
import xyz.eliothmonroy.toppeliculas.utils.RetrofitUtils;

/**
 * CLase que nos permite tener una instancia de Retrofit sin necesidad de crearla varias veces
 * De igual forma aquí podemos tener varias configuraciones de la aplicación
 */
public class App extends Application {
    
    private static RestClient restClient;
    private static Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        restClient = RetrofitUtils.getInstance().create(RestClient.class);
        context = this;
    }
    
    /**
     * @return Una instancia de Retrofit para ser usada
     */
    public static RestClient getRestClientInstance() {
        return restClient;
    }
    
    /**
     * @return Una instancia del contexto de la aplicación, esto puede ser sustituido
     * con Dagger o alguna otra librería de inyección de dependencias
     */
    public static Context getContextInstance() {
        return context;
    }
    
}
