package com.rsin.js_api_ar;


import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.devicesupport.Feature;

import java.util.EnumSet;

public class DefaultConfigs {

    private DefaultConfigs() {}

    // Defaults configuration for samples
    public static final Class<?> DEFAULT_ACTIVITY = SimpleArActivity.class;
    public static final CameraSettings.CameraPosition DEFAULT_CAMERA_POSITION = CameraSettings.CameraPosition.DEFAULT;
    public static final CameraSettings.CameraResolution DEFAULT_CAMERA_RESOLUTION = CameraSettings.CameraResolution.SD_640x480;
    public static final CameraSettings.CameraFocusMode DEFAULT_CAMERA_FOCUS_MODE = CameraSettings.CameraFocusMode.CONTINUOUS;
    public static final boolean DEFAULT_CAMERA_2_ENABLED = true;
    public static final EnumSet<Feature> DEFAULT_AR_FEATURES = EnumSet.allOf(Feature.class);

}
