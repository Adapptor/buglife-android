package com.buglife.sdk;

import static com.buglife.sdk.ImagePickerInputField.PICK_IMAGE_REQUEST_CODE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImagePickerInputFieldView extends InputFieldView {
    private TextView mTitleTextView;

    ImagePickerInputFieldView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.image_picker_input_field_view, this);
        mTitleTextView = findViewById(R.id.title_text_view);
    }

    @Override
    void configureWithInputField(@NonNull InputField inputField, @NonNull final ValueCoordinator valueCoordinator) {
        final ImagePickerInputField imagePickerInputField = (ImagePickerInputField) inputField;
        mTitleTextView.setText(imagePickerInputField.getTitle());

        mTitleTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePicker();
            }
        });
    }

    @Override
    void setValue(@Nullable String value) {
        // Nothing to do: this input field is really just a button!
    }

    private void showImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        ((Activity) getContext()).startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }
}
