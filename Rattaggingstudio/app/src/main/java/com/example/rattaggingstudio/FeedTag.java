package com.example.rattaggingstudio;

import android.provider.BaseColumns;

public final class FeedTag {
    private FeedTag(){}
    public static class FeedEntryTag implements BaseColumns {
        public static final String TABLE_NAME = "tag";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

    }
}
