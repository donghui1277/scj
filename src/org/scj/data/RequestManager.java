
package org.scj.data;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import java.io.File;

import org.scj.AppData;
import org.scj.util.CacheUtils;

public class RequestManager {
    public static RequestQueue mRequestQueue = newRequestQueue();


    private static ImageLoader mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());

    private static DiskBasedCache mDiskCache = (DiskBasedCache) mRequestQueue.getCache();

    private RequestManager(){

    }

    private static Cache openCache() {
        return new DiskBasedCache(CacheUtils.getExternalCacheDir(AppData.getContext()),
                10 * 1024 * 1024);
    }

    private static RequestQueue newRequestQueue() {
        RequestQueue requestQueue = new RequestQueue(openCache(), new BasicNetwork(new HurlStack()));
        requestQueue.start();
        return requestQueue;
    }

    public static <T> void addRequest(Request<T> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public static File getCachedImageFile(String url) {
        return mDiskCache.getFileForKey(url);
    }

    public static ImageLoader.ImageContainer loadImage(String requestUrl,
            ImageLoader.ImageListener imageListener) {
        return loadImage(requestUrl, imageListener, 0, 0);
    }

    public static ImageLoader.ImageContainer loadImage(String requestUrl,
            ImageLoader.ImageListener imageListener, int maxWidth, int maxHeight) {
        return mImageLoader.get(requestUrl, imageListener, maxWidth, maxHeight);
    }

    public static ImageLoader.ImageListener getImageListener(final ImageView view,
            final Drawable defaultImageDrawable, final Drawable errorImageDrawable) {
        return new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (errorImageDrawable != null) {
                    view.setImageDrawable(errorImageDrawable);
                }
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    if (!isImmediate && defaultImageDrawable != null) {
                        TransitionDrawable transitionDrawable = new TransitionDrawable(
                                new Drawable[] {
                                        defaultImageDrawable,
                                        new BitmapDrawable(AppData.getContext().getResources(),
                                                response.getBitmap())
                                });
                        transitionDrawable.setCrossFadeEnabled(true);
                        if (response.getBitmap().getHeight() > 500) {
                        	view.setMaxHeight(500);
						}
                        view.setImageDrawable(transitionDrawable);
                        transitionDrawable.startTransition(100);
                    } else {
                    	if (response.getBitmap().getHeight() > 500) {
                    		view.setMaxHeight(500);
						}
                    	view.setImageBitmap(response.getBitmap());
                    }
                } else if (defaultImageDrawable != null) {
                    view.setImageDrawable(defaultImageDrawable);
                }
            }
        };
    }
}
