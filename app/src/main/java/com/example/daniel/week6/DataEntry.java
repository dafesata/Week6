package com.example.daniel.week6;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by daniel on 12/03/17.
 */

public class DataEntry implements Serializable {
    int id,field1,field2;

    public DataEntry() {
    }

    public DataEntry(int id, int field1, int field2) {
        this.id = id;
        this.field1 = field1;
        this.field2 = field2;
    }

    public DataEntry(int field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }
}
