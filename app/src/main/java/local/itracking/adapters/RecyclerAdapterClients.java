package local.itracking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import local.itracking.R;
import local.itracking.models.Client;
import local.itracking.models.User;


public class RecyclerAdapterClients
        extends RecyclerView.Adapter<RecyclerAdapterClients.ViewHolderLogs> {

    ArrayList<Client> clients ;

    public RecyclerAdapterClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public ViewHolderLogs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_clients,null,false);
        return new ViewHolderLogs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogs viewHolderLogs, int i) {
try{
    viewHolderLogs._User.setText(clients.get(i).getRS_Name());
    viewHolderLogs._Email.setText(clients.get(i).getRUC_DNI());
    //viewHolderLogs._photo.setImageResource(clients.get(i).getPhotoId());
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
        return clients.size();
    }

    public class ViewHolderLogs extends RecyclerView.ViewHolder {

        TextView _User,_Email;
       // ImageView _photo;

        public ViewHolderLogs(@NonNull View itemView) {

            super(itemView);
            _User = (TextView) itemView.findViewById(R.id.RecyclerUserName);
            _Email = (TextView) itemView.findViewById(R.id.RecyclerUserEmail);
        }

    }
}
