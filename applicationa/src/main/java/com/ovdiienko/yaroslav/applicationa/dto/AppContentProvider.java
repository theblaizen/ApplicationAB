package com.ovdiienko.yaroslav.applicationa.dto;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AppContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.ovdiienko.yaroslav.applicationa.dto";
    public static final Uri URI_IMAGE_ENTITY = Uri.parse(
            "content://" + AUTHORITY + ".db.model/" + "image");

    private static final UriMatcher sMATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_DIR = 1;
    private static final int CODE_ITEM = 2;

    static {
        sMATCHER.addURI(AUTHORITY, "image", CODE_DIR);
        sMATCHER.addURI(AUTHORITY, "image" + "/*", CODE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
