package animales;

import android.util.Log;

public class Perro extends Animal {

    @Override
    public void hacerSonido() {
        super.hacerSonido();
        Log.d("Gato","Wouf");

    }
}
