package com.ukridk.fileinfo;

import com.facebook.react.bridge.*;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.net.URI;
import java.io.File;

public class Fileinfo extends ReactContextBaseJavaModule  {

    public Fileinfo(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ReactNativeFileinfo";
    }

    @ReactMethod
    public void getFileinfo(String uri, Promise promise) throws Exception {
        try {
            File file = new File(new URI(uri));

            WritableMap infoMap = Arguments.createMap();
            infoMap.putBoolean("canExecute", file.canExecute());
            infoMap.putBoolean("canRead", file.canRead());
            infoMap.putBoolean("canWrite", file.canWrite());
            infoMap.putBoolean("exists", file.exists());
            infoMap.putString("name", file.getName());
            infoMap.putString("parent", file.getParent());
            infoMap.putString("path", file.getPath());
            infoMap.putBoolean("isAbsolute", file.isAbsolute());
            infoMap.putBoolean("isDirectory", file.isDirectory());
            infomap.putBoolean("isHidden", file.isHidden());
            infoMap.putInt("lastModified", file.lastModified());
            infoMap.putInt("length", file.length());

            promise.resolve(infoMap);
        }
        catch (Exception e) {
            promise.reject(e.toString());
        }
    }

}
