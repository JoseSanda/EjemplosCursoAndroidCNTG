package com.example.tarde.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SaludadorIntentService extends IntentService {

    public static final String BROADCAST_ACTION_SALUDAR = "com.example.tarde.intentservice.broadcast.action.SALUDAR";
    public static final String BROADCAST_ACTION_DESPEDIR = "com.example.tarde.intentservice.broadcast.action.DESPEDIR";
    private static final String ACTION_SALUDAR = "com.example.tarde.intentservice.action.SALUDAR";
    private static final String ACTION_DESPEDIR = "com.example.tarde.intentservice.action.DESPEDIR";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM_NOMBRE = "nombre";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSaludar(Context context, String param1) {
        Intent intent = new Intent(context, SaludadorIntentService.class);
        intent.setAction(ACTION_SALUDAR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, param1);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionDespedir(Context context, String param1) {
        Intent intent = new Intent(context, SaludadorIntentService.class);
        intent.setAction(ACTION_DESPEDIR);
        intent.putExtra(EXTRA_PARAM_NOMBRE, param1);
        context.startService(intent);
    }

    public SaludadorIntentService() {
        super("SaludadorIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SALUDAR.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM_NOMBRE);
                handleActionSaludar(param1);
            } else if (ACTION_DESPEDIR.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM_NOMBRE);
                handleActionDespedir(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSaludar(String param1) {
        Intent intent = new Intent(BROADCAST_ACTION_SALUDAR);
        intent.putExtra(EXTRA_PARAM_NOMBRE,param1);
        sendBroadcast(intent);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDespedir(String param1) {
        Intent intent = new Intent(BROADCAST_ACTION_DESPEDIR);
        intent.putExtra(EXTRA_PARAM_NOMBRE,param1);
        sendBroadcast(intent);
    }
}
