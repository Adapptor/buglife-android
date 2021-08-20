package com.buglife.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class AttachmentUtils {
    /**
     * Creates a copy of the file identified by `uri`.
     * The file is placed in the cache directory and deleted on exit.
     * @param context the current context
     * @param uri a URI referencing the file to be copied
     * @return a copy of the file
     */
    static File copyFile(Context context, Uri uri) {
        try {
            final InputStream inputStream = context.getContentResolver().openInputStream(uri);

            if (inputStream == null) {
                Log.e("Buglife", "Error creating attachment file (input stream null)");
                return null;
            }

            final String mimeType = AttachmentUtils.getMimeType(context, uri);
            final String extension = AttachmentUtils.getExtension(mimeType);

            final File outputDir = context.getCacheDir();
            final File outputFile = File.createTempFile("Buglife", "." + extension, outputDir);
            outputFile.deleteOnExit();

            final FileOutputStream outputStream = new FileOutputStream(outputFile);

            if (AttachmentUtils.copyStream(inputStream, outputStream)) {
                return outputFile;
            } else {
                Log.e("Buglife", "Error creating attachment file (copy error)");
                return null;
            }
        } catch (Exception e) {
            Log.e("Buglife", "Error creating attachment file", e);
            return null;
        }
    }

    /**
     * Returns the MIME type of the file identified by `uri`.
     * @param context the current context
     * @param uri a URI referencing the file to be inspected
     * @return the MIME type of the file
     */
    static String getMimeType(Context context, Uri uri) {
        ContentResolver resolver = context.getContentResolver();
        return resolver.getType(uri);
    }

    /**
     * Returns the extension corresponding to the specified MIME type.
     * @param mimeType the MIME type being checked
     * @return the extension
     */
    static String getExtension(String mimeType) {
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(mimeType);
    }

    /**
     * Copies the contents of `inputStream` to `outputStream`.
     * Both streams will be closed.
     * @param inputStream the source stream
     * @param outputStream the destination stream
     * @return success flag
     */
    static boolean copyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            final int bufferSize = 64 * 1024;
            final byte[] buffer = new byte[bufferSize];
            int totalRead = 0, read;

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, totalRead, read);
            }

            return true;
        } catch (IOException e) {
            Log.e("AttachmentUtils", "Error creating copy of attachment file", e);
            return false;
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                Log.e("AttachmentUtils", "Error closing streams", e);
            }
        }
    }
}
