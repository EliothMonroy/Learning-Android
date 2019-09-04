package xyz.eliothmonroy.calculadoramvp.presenter;

public interface MainActivityPresenter {
    void sumar(String number1,String number2);//interactor
    void showError(String error);//vista
    void showResult(String result);//vista
}
