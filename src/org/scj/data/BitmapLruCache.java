package org.scj.data;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache{

	public BitmapLruCache() {
		this(getDefaultLruCacheSize());
	}
	
	public BitmapLruCache(int maxSize) {
		super(maxSize);
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value) {
		// TODO Auto-generated method stub
		return value.getRowBytes() * value.getHeight() / 1024;
	} 
	
	public static int getDefaultLruCacheSize() {
		final int maxMemory = 
				(int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		
		return cacheSize;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}

}
