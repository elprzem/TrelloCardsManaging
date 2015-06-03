package przefar.trellocardsmanaging.async_tasks;

import android.content.Context;
import android.os.AsyncTask;

import przefar.trellocardsmanaging.trello_classes.NullResponse;
import przefar.trellocardsmanaging.helpers.GsonHelper;
import przefar.trellocardsmanaging.helpers.HttpHelper;

/**
 * Created by Przemys?aw Farion on 6/3/2015.
 */
public class DeleteCardTask extends AsyncTask<String, Integer, Boolean>{
    private final Callback callback;
    private final Context context;

    public DeleteCardTask(DeleteCardTask.Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Boolean doInBackground(String... params) {
        String cardId = params[0];
        String response = HttpHelper.delete(
                "https://api.trello.com/1/cards/" + cardId + "?" +
                        "key=5a8e15eab66ef71d1abfc0a1c597ac7d&" +
                        "token=c12669b20bba2134c4c99f650e17cf82327043af3b04186e8ed77bea78cb5a6d");
        NullResponse nullResponse;
        try {
            nullResponse = GsonHelper.getNullResponseFromJson(response);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aBoolean The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        callback.doAfterDeleteCardTask((aBoolean));
    }

    public interface Callback {
        void doAfterDeleteCardTask(Boolean isSucceed);
    }
}
