package com.ukridk.fileinfo;

import com.facebook.react.bridge.*;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.net.URI;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Fileinfo extends ReactContextBaseJavaModule  {

    public Fileinfo(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ReactNativeFileinfo";
    }

    private String toIsoString(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        return df.format(date);
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
            infoMap.putBoolean("isHidden", file.isHidden());
            infoMap.putString("lastModified",
                              this.toIsoString(new Date(file.lastModified())));
            infoMap.putString("length", String.valueOf(file.length()));

            promise.resolve(infoMap);
        }
        catch (Exception e) {
            promise.reject(e.toString());
        }
    }

}
