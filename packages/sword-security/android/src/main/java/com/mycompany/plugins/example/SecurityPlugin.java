package com.mycompany.plugins.example;

import android.content.Intent;
import android.provider.Settings;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.File;

@CapacitorPlugin(name = "SecurityPlugin")
public class SecurityPlugin extends Plugin {

    @PluginMethod
    public void isDeveloperModeEnabled(PluginCall call) {

        boolean enabled =
            Settings.Global.getInt(
                getContext().getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
                0
            ) != 0;

        JSObject ret = new JSObject();
        ret.put("enabled", enabled);

        call.resolve(ret);
    }

    @PluginMethod
    public void isRooted(PluginCall call) {

        boolean rooted = checkRootMethod1() || checkRootMethod2();

        JSObject ret = new JSObject();
        ret.put("rooted", rooted);

        call.resolve(ret);
    }

    private boolean isUsbDebuggingEnabled() {

        return Settings.Global.getInt(
                getContext().getContentResolver(),
                Settings.Global.ADB_ENABLED,
                0
        ) == 1;
    }

    @PluginMethod
    public void openDeveloperSettings(PluginCall call) {

        Intent intent =
            new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getContext().startActivity(intent);

        call.resolve();
    }        

private boolean checkRootMethod1() {
    String buildTags = android.os.Build.TAGS;
    return buildTags != null && buildTags.contains("test-keys");
}

private boolean checkRootMethod2() {

    String[] paths = {
        "/system/app/Superuser.apk",
        "/sbin/su",
        "/system/bin/su",
        "/system/xbin/su",
        "/data/local/xbin/su",
        "/data/local/bin/su",
        "/system/sd/xbin/su",
        "/system/bin/failsafe/su",
        "/data/local/su"
    };

    for (String path : paths) {
        if (new File(path).exists()) {
            return true;
        }
    }

    return false;
}    
}
