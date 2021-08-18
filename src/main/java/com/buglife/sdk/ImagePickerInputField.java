package com.buglife.sdk;

import androidx.annotation.NonNull;

/**
 * Allows the user to select an image to be attached to the Buglife report.
 */
public final class ImagePickerInputField extends InputField {
    static final String IMAGE_PICKER_ATTRIBUTE_NAME = "IMAGE_PICKER_ATTRIBUTE";
    static final int PICK_IMAGE_REQUEST_CODE = 101;

    /**
     * Default constructor
     * @param attributeName The attribute name. If the given attributeName is equal to
     *                      a custom attribute value set via `Buglife.putAttribute()`, then
     *                      that value will be the default value for this field in the bug
     *                      reporter UI.
     */
    public ImagePickerInputField(@NonNull String attributeName) {
        super(attributeName);
    }
}
