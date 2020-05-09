package org.ituns.toolset.html.drawable;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrawableTaskManager implements DrawableTask.Callback {
    private static DrawableTaskManager mInstance;

    private TaskHandler taskHandler;
    private ExecutorService executorService;
    private ConcurrentHashMap<String, Callback> concurrentHashMap;

    public static DrawableTaskManager getInstance() {
        if(mInstance == null) {
            synchronized (DrawableTaskManager.class) {
                if(mInstance == null) {
                    mInstance = new DrawableTaskManager();
                }
            }
        }
        return mInstance;
    }

    private DrawableTaskManager() {
        concurrentHashMap = new ConcurrentHashMap<>();
        executorService = Executors.newCachedThreadPool();
        taskHandler = new TaskHandler(Looper.getMainLooper());
    }

    public void execute(String source, Callback callback) {
        if(source == null || callback == null) {
            return;
        }

        if(concurrentHashMap.containsKey(source)) {
            return;
        }

        concurrentHashMap.put(source, callback);
        executorService.execute(new DrawableTask(source, this));
    }

    @Override
    public synchronized void onDrawable(String source, Drawable drawable) {
        TaskEntity taskEntity = new TaskEntity(source, drawable);
        Message message = Message.obtain(taskHandler, 0, taskEntity);
        message.sendToTarget();
    }

    private void callbackWithDrawable(String source, Drawable drawable) {
        if(source == null) {
            return;
        }

        Callback callback = concurrentHashMap.get(source);
        if(callback != null) {
            callback.onDrawable(drawable);
            concurrentHashMap.remove(source);
        }
    }

    private class TaskHandler extends Handler {

        public TaskHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                TaskEntity entity = (TaskEntity) msg.obj;
                callbackWithDrawable(entity.source, entity.drawable);
            }
        }
    }

    private static class TaskEntity {
        public String source;
        public Drawable drawable;

        public TaskEntity(String source, Drawable drawable) {
            this.source = source;
            this.drawable = drawable;
        }
    }

    public interface Callback {
        void onDrawable(Drawable drawable);
    }
}
