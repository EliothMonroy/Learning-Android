package xyz.eliothmonroy.calculadoramvp.interactor;

import xyz.eliothmonroy.calculadoramvp.presenter.MainActivityPresenter;

public class MainActivityInteractorImpl implements MainActivityInteractor{

    private MainActivityPresenter mainActivityPresenter;

    public MainActivityInteractorImpl(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    @Override
    public void sumar(String number1, String number2) {
        double result=Double.valueOf(number1)+Double.valueOf(number2);
        mainActivityPresenter.showResult(Double.toString(result));
    }
}
