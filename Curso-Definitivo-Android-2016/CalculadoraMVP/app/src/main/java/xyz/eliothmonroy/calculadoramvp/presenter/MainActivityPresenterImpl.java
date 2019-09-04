package xyz.eliothmonroy.calculadoramvp.presenter;

import xyz.eliothmonroy.calculadoramvp.interactor.MainActivityInteractor;
import xyz.eliothmonroy.calculadoramvp.interactor.MainActivityInteractorImpl;
import xyz.eliothmonroy.calculadoramvp.view.MainActivityView;

public class MainActivityPresenterImpl implements MainActivityPresenter{

    private MainActivityView mainActivityView;
    private MainActivityInteractor mainActivityInteractor;

   public MainActivityPresenterImpl(MainActivityView mainActivityView){
       this.mainActivityView=mainActivityView;
       mainActivityInteractor=new MainActivityInteractorImpl(this);
   }

    @Override
    public void sumar(String number1, String number2) {
        mainActivityInteractor.sumar(number1,number2);
    }

    @Override
    public void showError(String error) {
        mainActivityView.showError(error);
    }

    @Override
    public void showResult(String result) {
        mainActivityView.showResult(result);
    }
}
