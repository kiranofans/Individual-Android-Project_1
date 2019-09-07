package Module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import Annotations.ApplicationContext;
import Annotations.PreferenceInfo;
import Managers.SessionManager;
import Utils.AppConstants;
import dagger.Module;
import dagger.Provides;
import data.BaseDataManager;
import data.DataManager;
import data.prefs.PrefsHelper;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_FILE_GLOBAL;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(BaseDataManager mDataManager) {
        return mDataManager;
    }


    @Provides
    @Singleton
    PrefsHelper providePreferencesHelper(SessionManager preferencesManager) {
        return preferencesManager;
    }
}
