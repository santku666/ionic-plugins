package com.mycompany.plugins.example;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.File;

@CapacitorPlugin(name = "Security")
public class SecurityPlugin extends Plugin {

    private Security implementation = new Security();

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
