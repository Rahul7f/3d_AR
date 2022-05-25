package com.rsin.js_api_ar;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.devicesupport.Feature;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_ACTIVITY;
import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_AR_FEATURES;
import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_CAMERA_2_ENABLED;
import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_CAMERA_FOCUS_MODE;
import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_CAMERA_POSITION;
import static com.rsin.js_api_ar.DefaultConfigs.DEFAULT_CAMERA_RESOLUTION;


public class SampleData {
    private final String name;
    private final String path;
    private final Class<?> activityClass;
    private final List<String> extensions;

    private final EnumSet<Feature> arFeatures;
    private final CameraSettings.CameraPosition cameraPosition;
    private final CameraSettings.CameraResolution cameraResolution;
    private final CameraSettings.CameraFocusMode cameraFocusMode;
    private final boolean camera2Enabled;

    private boolean isDeviceSupporting;
    private String isDeviceSupportingError;

    private SampleData(@NonNull Builder builder) {
        name = builder.name;
        path = builder.path;
        activityClass = builder.activityClass;
        extensions = builder.extensions;
        arFeatures = builder.arFeatures;
        cameraPosition = builder.cameraPosition;
        cameraResolution = builder.cameraResolution;
        cameraFocusMode = builder.cameraFocusMode;
        camera2Enabled = builder.camera2Enabled;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public EnumSet<Feature> getArFeatures() {
        return arFeatures;
    }

    public CameraSettings.CameraPosition getCameraPosition() {
        return cameraPosition;
    }

    public CameraSettings.CameraResolution getCameraResolution() {
        return cameraResolution;
    }

    public CameraSettings.CameraFocusMode getCameraFocusMode() {
        return cameraFocusMode;
    }

    public boolean isCamera2Enabled() {
        return camera2Enabled;
    }

    public void isDeviceSupporting(boolean isDeviceSupporting, String isDeviceSupportingError) {
        this.isDeviceSupporting = isDeviceSupporting;
        this.isDeviceSupportingError = isDeviceSupportingError;
    }

    public boolean getIsDeviceSupporting() {
        return isDeviceSupporting;
    }

    public String getIsDeviceSupportingError() {
        return isDeviceSupportingError;
    }

    public static class Builder {

        private final @NonNull
        String name;
        private final @NonNull String path;
        private Class<?> activityClass = DEFAULT_ACTIVITY;
        private @NonNull List<String> extensions = new ArrayList<>();
        private EnumSet<Feature> arFeatures = DEFAULT_AR_FEATURES;
        private @NonNull
        CameraSettings.CameraPosition cameraPosition = DEFAULT_CAMERA_POSITION;
        private @NonNull
        CameraSettings.CameraResolution cameraResolution = DEFAULT_CAMERA_RESOLUTION;
        private @NonNull
        CameraSettings.CameraFocusMode cameraFocusMode = DEFAULT_CAMERA_FOCUS_MODE;
        private boolean camera2Enabled = DEFAULT_CAMERA_2_ENABLED;

        public Builder(@NonNull String name, @NonNull String path) {
            this.name = name;
            this.path = path;
        }

        public Builder extensions(@Nullable List<String> extensions) {
            if (extensions != null) {
                this.extensions = extensions;
            }
            return this;
        }

        public Builder arFeatures(EnumSet<Feature> arFeatures) {
            this.arFeatures = arFeatures;
            return this;
        }

        public Builder activityClass(@Nullable Class<?> activityClass) {
            if (activityClass != null) {
                this.activityClass = activityClass;
            }
            return this;
        }

        public Builder cameraPosition(@Nullable CameraSettings.CameraPosition cameraPosition) {
            if (cameraPosition != null) {
                this.cameraPosition = cameraPosition;
            }
            return this;
        }

        public Builder cameraResolution(@Nullable CameraSettings.CameraResolution cameraResolution) {
            if (cameraResolution != null) {
                this.cameraResolution = cameraResolution;
            }
            return this;
        }

        public Builder cameraFocusMode(@Nullable CameraSettings.CameraFocusMode cameraFocusMode) {
            if (cameraFocusMode != null) {
                this.cameraFocusMode = cameraFocusMode;
            }
            return this;
        }

        public Builder camera2Enabled(boolean camera2Enabled) {
            this.camera2Enabled = camera2Enabled;
            return this;
        }

        public SampleData build() {
            return new SampleData(this);
        }
    }
}
