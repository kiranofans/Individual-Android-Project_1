package Component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import com.yamibo.bbs.Annotations.ApplicationContext;
import Module.ApplicationModule;
import dagger.Component;
import com.yamibo.bbs.data.DataManager;
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
