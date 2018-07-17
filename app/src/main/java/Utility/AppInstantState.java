package Utility;

import android.app.Application;
/**聽說這個類建好了的話可以用Global Variables*/
public class AppInstantState extends Application {
    private StateManager appState;
    public StateManager getAppState(){
        return appState;
    }
    public void setAppState(StateManager appState){
        this.appState=appState;
    }
}
class StateManager {
    private String state;
    StateManager(){
        /**Make the operation faster*/
    }
    public String getState(){
        /**if necessary, perform blocking calls here */
        /**make sure to deal with any multithreading/synchronicity issues */
        return state;
    }
}