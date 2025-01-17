/*
 * Copyright (C) 2017 Buglife, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.buglife.sdk;

import android.os.Parcel;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

class LogFileAttachment extends FileAttachment {
    private static final String LOG_VERSION = "2.1";

    LogFileAttachment(@NonNull File file) {
        super(file, "application/json");
    }

    protected LogFileAttachment(Parcel in) {
        super(in);
    }

    public static final Creator<LogFileAttachment> CREATOR = new Creator<LogFileAttachment>() {
        @Override public LogFileAttachment createFromParcel(Parcel source) {
            return new LogFileAttachment(source);
        }

        @Override public LogFileAttachment[] newArray(int size) {
            return new LogFileAttachment[size];
        }
    };


    @Override public JSONObject toJSON() throws JSONException, IOException {
        JSONObject json = super.toJSON();
        json.put("log_version", LOG_VERSION);
        return json;
    }
}
