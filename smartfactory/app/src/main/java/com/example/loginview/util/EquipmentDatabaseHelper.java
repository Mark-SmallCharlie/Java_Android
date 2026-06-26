package com.example.loginview.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.loginview.R;
import com.example.loginview.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "equipment.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_EQUIPMENT = "equipment";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_CODE = "code";
    private static final String COL_CATEGORY = "category";
    private static final String COL_DAMAGE = "damage_level";
    private static final String COL_USAGE_HOURS = "usage_hours";
    private static final String COL_IMAGE_RES = "image_res_id";
    private static final String COL_PRICE = "market_price";

    public static final String CATEGORY_PRODUCTION = "生产设备";
    public static final String CATEGORY_TESTING = "检测设备";
    public static final String CATEGORY_PACKAGING = "包装设备";
    public static final String CATEGORY_STORAGE = "仓储设备";

    private final Context context;

    public EquipmentDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_EQUIPMENT + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT NOT NULL, "
                + COL_CODE + " TEXT NOT NULL, "
                + COL_CATEGORY + " TEXT NOT NULL, "
                + COL_DAMAGE + " TEXT NOT NULL, "
                + COL_USAGE_HOURS + " INTEGER NOT NULL, "
                + COL_IMAGE_RES + " INTEGER NOT NULL, "
                + COL_PRICE + " REAL NOT NULL)";
        db.execSQL(sql);
        insertDefaultData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPMENT);
        onCreate(db);
    }

    private void insertDefaultData(SQLiteDatabase db) {
        insertItem(db, "CNC数控车床", "PRD-001", CATEGORY_PRODUCTION, "良好", 1200, R.drawable.ic_equipment, 85000.00);
        insertItem(db, "激光切割机", "PRD-002", CATEGORY_PRODUCTION, "轻微", 3400, R.drawable.ic_equipment, 120000.00);
        insertItem(db, "液压成型机", "PRD-003", CATEGORY_PRODUCTION, "中等", 5600, R.drawable.ic_equipment, 68000.00);
        insertItem(db, "自动焊接机器人", "PRD-004", CATEGORY_PRODUCTION, "良好", 800, R.drawable.ic_equipment, 250000.00);

        insertItem(db, "三坐标测量仪", "TST-001", CATEGORY_TESTING, "良好", 600, R.drawable.ic_equipment, 150000.00);
        insertItem(db, "X射线探伤机", "TST-002", CATEGORY_TESTING, "轻微", 2100, R.drawable.ic_equipment, 320000.00);
        insertItem(db, "硬度计", "TST-003", CATEGORY_TESTING, "良好", 900, R.drawable.ic_equipment, 25000.00);
        insertItem(db, "光谱分析仪", "TST-004", CATEGORY_TESTING, "严重", 7800, R.drawable.ic_equipment, 480000.00);

        insertItem(db, "自动封箱机", "PKG-001", CATEGORY_PACKAGING, "良好", 1500, R.drawable.ic_equipment, 35000.00);
        insertItem(db, "热缩包装机", "PKG-002", CATEGORY_PACKAGING, "轻微", 2800, R.drawable.ic_equipment, 28000.00);
        insertItem(db, "贴标机", "PKG-003", CATEGORY_PACKAGING, "中等", 4200, R.drawable.ic_equipment, 18000.00);
        insertItem(db, "真空包装机", "PKG-004", CATEGORY_PACKAGING, "良好", 700, R.drawable.ic_equipment, 42000.00);

        insertItem(db, "电动叉车", "STR-001", CATEGORY_STORAGE, "良好", 3000, R.drawable.ic_equipment, 95000.00);
        insertItem(db, "自动化立体仓库", "STR-002", CATEGORY_STORAGE, "良好", 1800, R.drawable.ic_equipment, 580000.00);
        insertItem(db, "输送带系统", "STR-003", CATEGORY_STORAGE, "中等", 6500, R.drawable.ic_equipment, 120000.00);
        insertItem(db, "堆垛机", "STR-004", CATEGORY_STORAGE, "严重", 9200, R.drawable.ic_equipment, 210000.00);
    }

    private void insertItem(SQLiteDatabase db, String name, String code, String category,
                            String damage, int hours, int imageRes, double price) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_CODE, code);
        cv.put(COL_CATEGORY, category);
        cv.put(COL_DAMAGE, damage);
        cv.put(COL_USAGE_HOURS, hours);
        cv.put(COL_IMAGE_RES, imageRes);
        cv.put(COL_PRICE, price);
        db.insert(TABLE_EQUIPMENT, null, cv);
    }

    public long insertEquipment(Equipment equipment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, equipment.getName());
        cv.put(COL_CODE, equipment.getCode());
        cv.put(COL_CATEGORY, equipment.getCategory());
        cv.put(COL_DAMAGE, equipment.getDamageLevel());
        cv.put(COL_USAGE_HOURS, equipment.getUsageHours());
        cv.put(COL_IMAGE_RES, equipment.getImageResId());
        cv.put(COL_PRICE, equipment.getMarketPrice());
        long id = db.insert(TABLE_EQUIPMENT, null, cv);
        db.close();
        return id;
    }

    public List<Equipment> getEquipmentByCategory(String category) {
        List<Equipment> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EQUIPMENT, null, COL_CATEGORY + "=?",
                new String[]{category}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(cursorToEquipment(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<Equipment> getAllEquipment() {
        List<Equipment> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EQUIPMENT, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(cursorToEquipment(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public int updateEquipment(Equipment equipment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, equipment.getName());
        cv.put(COL_CODE, equipment.getCode());
        cv.put(COL_CATEGORY, equipment.getCategory());
        cv.put(COL_DAMAGE, equipment.getDamageLevel());
        cv.put(COL_USAGE_HOURS, equipment.getUsageHours());
        cv.put(COL_IMAGE_RES, equipment.getImageResId());
        cv.put(COL_PRICE, equipment.getMarketPrice());
        int rows = db.update(TABLE_EQUIPMENT, cv, COL_ID + "=?",
                new String[]{String.valueOf(equipment.getId())});
        db.close();
        return rows;
    }

    public int deleteEquipment(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(TABLE_EQUIPMENT, COL_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    private Equipment cursorToEquipment(Cursor cursor) {
        Equipment e = new Equipment();
        e.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID)));
        e.setName(cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)));
        e.setCode(cursor.getString(cursor.getColumnIndexOrThrow(COL_CODE)));
        e.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COL_CATEGORY)));
        e.setDamageLevel(cursor.getString(cursor.getColumnIndexOrThrow(COL_DAMAGE)));
        e.setUsageHours(cursor.getInt(cursor.getColumnIndexOrThrow(COL_USAGE_HOURS)));
        e.setImageResId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_IMAGE_RES)));
        e.setMarketPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PRICE)));
        return e;
    }
}
