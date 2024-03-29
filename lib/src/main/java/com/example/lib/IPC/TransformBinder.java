package com.example.lib.IPC;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TransformBinder extends Binder {

    private ServerInterface serverInterface;
    private Object server;

    public TransformBinder(ServerInterface serverInterface, Object server) {
        this.serverInterface = serverInterface;
        this.server = server;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        if (code == INTERFACE_TRANSACTION) {
            reply.writeString(serverInterface.getInterfaceName());
            return true;
        }
        IPCMethod method = serverInterface.getIPCMethod(code);
        if (method != null) {
            try {
                method.handleTransact(server, data, reply);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
