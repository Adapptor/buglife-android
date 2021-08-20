package com.buglife.sdk;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

abstract class InputFieldView extends LinearLayout {

    InputFieldView(Context context) {
        super(context);
    }

    public static InputFieldView newInstance(Context context, InputField inputField) {
        if (inputField instanceof TextInputField) {
            return new TextInputFieldView(context);
        } else if (inputField instanceof PickerInputField) {
            return new PickerInputFieldView(context);
        } else if (inputField instanceof ImagePickerInputField) {
            return new ImagePickerInputFieldView(context);
        } else {
            throw new Buglife.BuglifeException("Unexpected input field type: " + inputField);
        }
    }

    abstract void configureWithInputField(@NonNull InputField inputField, @NonNull ValueCoordinator valueCoordinator);

    abstract void setValue(@Nullable String value);

    interface ValueCoordinator {
        void onValueChanged(@NonNull InputField inputField, @Nullable String newValue);
    }
}
