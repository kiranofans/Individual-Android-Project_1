package root;

import android.app.Application;

import Module.ApplicationModule;

public class YamiboApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        /*mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);*/
    }



   /* // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }*/
}
