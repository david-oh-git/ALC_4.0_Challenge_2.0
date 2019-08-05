package io.audioshinigami.travelmantics.viewmodels;

import androidx.lifecycle.ViewModel;

public class DealViewModel extends ViewModel {

    private static DealViewModel instance;

    public void getAlldeals(){

    }

    public static DealViewModel getInstance(){
        if( instance == null ){
            synchronized ( DealViewModel.class ){
                if( instance == null ){
                    instance = new DealViewModel();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;

    } /*end getInstance*/
}
