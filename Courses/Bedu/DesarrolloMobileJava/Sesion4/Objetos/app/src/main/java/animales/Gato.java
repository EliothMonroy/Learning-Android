package animales;

import android.util.Log;

public class Gato extends Animal{

    @Override
    public void hacerSonido() {
        super.hacerSonido();
        Log.d("Gato","Miau");
    }
}
