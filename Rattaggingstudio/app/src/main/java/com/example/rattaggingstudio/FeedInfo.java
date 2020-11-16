package com.example.rattaggingstudio;

import android.provider.BaseColumns;


public final class FeedInfo {
    private FeedInfo(){}
    public static class FeedEntryInfo implements BaseColumns {
        public static final String TABLE_NAME = "info";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";

    }
}
