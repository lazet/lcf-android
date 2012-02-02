package org.lcf.android.event.eventListener;

import roboguice.util.SafeAsyncTask;

import android.os.Handler;

/**
 * Adaptor between the SafeAsyncTask class and provided Runnable.
 *
* @author John Ericksen
* @author Lazet
*/
public class RunnableAsyncTaskAdaptor extends SafeAsyncTask<Void> {

    protected Runnable runnable;

    public RunnableAsyncTaskAdaptor(Runnable runnable) {
        this.runnable = runnable;
    }

    public RunnableAsyncTaskAdaptor(Handler handler, Runnable runnable) {
        super(handler);
        this.runnable = runnable;
    }

    public Void call() throws Exception {
        runnable.run();
        return null;
    }
}
