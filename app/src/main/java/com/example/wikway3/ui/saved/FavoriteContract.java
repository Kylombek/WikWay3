package com.example.wikway3.ui.saved;

import android.provider.BaseColumns;

/**
 * Created by delaroy on 5/27/17.
 */
public class FavoriteContract {

    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_JOBNAME = "jobName";
        public static final String COLUMN_IMAGEURL = "imageUrl";
        public static final String COLUMN_BUNDESLAND = "bundesland";


    }
}
