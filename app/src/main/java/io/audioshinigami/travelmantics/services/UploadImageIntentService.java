package io.audioshinigami.travelmantics.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import io.audioshinigami.travelmantics.repository.DealRepository;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
public class UploadImageIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_UPLOAD = "io.audioshinigami.travelmantics.services.action.IMAGE_UPLOAD";

    private static final String ABS_PATH = "io.audioshinigami.travelmantics.services.extra.PARAM1";

    public UploadImageIntentService() {
        super("UploadImageIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    
    public static void startActionUpload(Context context, String absPath) {
        Intent intent = new Intent(context, UploadImageIntentService.class);
        intent.setAction(ACTION_UPLOAD);
        intent.putExtra(ABS_PATH, absPath);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPLOAD.equals(action)) {
                final String absPath = intent.getStringExtra(ABS_PATH);
                handleActionUpload(absPath);
            }
        }
    }

    /**
     * Handle actionUpload in the provided background thread with the provided
     * parameters.
     */
    private void handleActionUpload(String absPath) {
        DealRepository.getInstance().uploadImage(absPath);
    }

}
