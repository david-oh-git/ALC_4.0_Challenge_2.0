package io.audioshinigami.travelmantics.repository;

public class DealRepository {

    private static DealRepository instance;

    private DealRepository(){

    }

    public void addDeal(){

    }

    public static DealRepository getInstance(){
        if( instance == null ){
            synchronized ( DealRepository.class ){
                if( instance == null ){
                    instance = new DealRepository();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;
    } /*end getInstance*/
}

