package com.xmpp.Chat.util;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatTools {

    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyy-MM-dd";
    private static final int ww = 480;
    private static final int hh = 800;

    // ��byte[]ת����InputStream
    public static InputStream Byte2InputStream(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        return bais;
    }

    // ��InputStreamת����byte[]
    public static byte[] InputStream2Bytes(InputStream is) {
        String str = "";
        byte[] readByte = new byte[1024];
        int readCount = -1;
        try {
            while ((readCount = is.read(readByte, 0, 1024)) != -1) {
                str += new String(readByte).trim();
            }
            return str.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ��Bitmapת����InputStream
    public static InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    // ��Bitmapת����InputStream
    public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    // ��InputStreamת����Bitmap
    public static Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    // Drawableת����InputStream
    public static InputStream Drawable2InputStream(Drawable d) {
        Bitmap bitmap = drawable2Bitmap(d);
        return Bitmap2InputStream(bitmap);
    }

    // InputStreamת����Drawable
    public static Drawable InputStream2Drawable(InputStream is) {
        Bitmap bitmap = InputStream2Bitmap(is);
        return bitmap2Drawable(bitmap);
    }

    // Drawableת����byte[]
    public static byte[] Drawable2Bytes(Drawable d) {
        Bitmap bitmap = drawable2Bitmap(d);
        return Bitmap2Bytes(bitmap);
    }

    // byte[]ת����Drawable
    public static Drawable Bytes2Drawable(byte[] b) {
        Bitmap bitmap = Bytes2Bitmap(b);
        return bitmap2Drawable(bitmap);
    }

    // Bitmapת����byte[]
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    // byte[]ת����Bitmap
    public static Bitmap Bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    // Drawableת����Bitmap
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    // Bitmapת����Drawable
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        Drawable d = (Drawable) bd;
        return d;
    }

    /*
    * DateToString(Date date),ʱ��ת�����ַ���
    */
    public static String DateToString(Date date, String type) {
        SimpleDateFormat formatDate = new SimpleDateFormat(type);
        String s = formatDate.format(date);
        try {
            date = formatDate.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate.format(date);
    }

    /*
    * Date StringToDate(String s),�ַ���ת����ʱ��
    */
    public static java.util.Date StringToUtilDate(String s, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type, Locale.CHINA);
        try {
            Date date = sdf.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date StringToSqlDate(String s) {
        try {
            return java.sql.Date.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int dip2px(Context context, int value) {
        float scaleing = context.getResources().getDisplayMetrics().density;
        return (int) (value * scaleing + 0.5f);
    }

    public static int px2dip(Context context, int value) {
        float scaling = context.getResources().getDisplayMetrics().density;
        return (int) (value / scaling + 0.5f);
    }

    /**
     * ͼƬ��Բ��
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * save
     *
     * @param path
     * @param buffer
     * @return
     */
    public static int saveBitmap(String path, byte[] buffer) {
        int result = -1;
        try {
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(buffer);
            out.flush();
            out.close();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * @param filePath
     * @return
     */
    public static Bitmap getdecodeBitmap(String filePath) {
        if (filePath == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        int width = options.outWidth;
        int height = options.outHeight;
        float scale = 1f;
        if (width > ww && width > height) {
            scale = width / ww;
        } else if (height > hh && height > width) {
            scale = height / hh;
        } else {
            scale = 1;
        }

        options.inSampleSize = (int) scale;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    public static int saveBitmap(String path, Bitmap bitmap) {
        int result = -1;
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
