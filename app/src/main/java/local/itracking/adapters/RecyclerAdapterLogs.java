package local.itracking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import local.itracking.R;
import local.itracking.models.LogSecurity;


public class RecyclerAdapterLogs
        extends RecyclerView.Adapter<RecyclerAdapterLogs.ViewHolderLogs> {

    ArrayList<LogSecurity> logs ;

    public RecyclerAdapterLogs(ArrayList<LogSecurity> logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public ViewHolderLogs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_logs,null,false);
        return new ViewHolderLogs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogs viewHolderLogs, int i) {
try{
    viewHolderLogs._DateTime.setText(
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(logs.get(i).getDateTime())));
    viewHolderLogs._Type.setText(logs.get(i).getType());
    viewHolderLogs._Value.setText(logs.get(i).getValue());
}
catch (Exception ex)
{
    Log.d("=iTracking=>",ex.toString());
}
finally {

}

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class ViewHolderLogs extends RecyclerView.ViewHolder {

        TextView _DateTime,_Type,_Value;

        public ViewHolderLogs(@NonNull View itemView) {
            super(itemView);
            _DateTime = (TextView) itemView.findViewById(R.id.RecyclerLogDateTime);
            _Type = (TextView) itemView.findViewById(R.id.RecyclerLogType);
            _Value = (TextView) itemView.findViewById(R.id.RecyclerLogValue);
        }

    }
}
