package Module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import Annotations.ApplicationContext;
import Annotations.PreferenceInfo;
import Managers.SessionManager;
import Utils.AppConstants;
import Utils.PrefsHelper;
import dagger.Provides;

public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_FILE_GLOBAL;
    }

    @Provides
    @Singleton
    PrefsHelper providePreferencesHelper(SessionManager preferencesManager) {
        return preferencesManager;
    }

}
