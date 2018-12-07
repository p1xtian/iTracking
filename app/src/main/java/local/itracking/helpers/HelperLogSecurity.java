package local.itracking.helpers;

import android.util.Log;

import java.util.Date;

import local.itracking.models.LogSecurity;
import local.itracking.repositories.RepositoryLogsSecurity;

public class HelperLogSecurity {
    HelperSQLite helper;
    public HelperLogSecurity(HelperSQLite helper) {
        this.helper = helper;
        }

        public void InsertLog(String Type, String Value)
        {


            String _type;

            if(Type.equalsIgnoreCase("0"))
            {
                    _type = "Info";
            }
                else if (Type.equalsIgnoreCase("1"))
            {
                _type = "Error";
            }
                        else
            {
                _type = "Warning";
            }

            Log.d("=iTracking=>","Type: " + _type + " Value: " + Value);

            LogSecurity log =
                    new LogSecurity(
                            new Date().getTime(),
                            _type ,
                            Value);
            RepositoryLogsSecurity repository = new RepositoryLogsSecurity();
            repository.Insert(helper,log);
        }

    public void DeleteLog()
    {

        RepositoryLogsSecurity repository = new RepositoryLogsSecurity();
        repository.Delete(helper);
    }

}
