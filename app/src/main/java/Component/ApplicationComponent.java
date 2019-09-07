package Component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import Annotations.ApplicationContext;
import Module.ApplicationModule;
import dagger.Component;
import data.DataManager;
import root.YamiboApp;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(YamiboApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
