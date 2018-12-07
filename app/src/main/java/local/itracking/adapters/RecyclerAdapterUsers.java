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
import local.itracking.helpers.HelperBase64;
import local.itracking.models.LogSecurity;
import local.itracking.models.User;


public class RecyclerAdapterUsers
        extends RecyclerView.Adapter<RecyclerAdapterUsers.ViewHolderLogs> {

    ArrayList<User> users ;

    public RecyclerAdapterUsers(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolderLogs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_users,null,false);
        return new ViewHolderLogs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogs viewHolderLogs, int i) {
try{
    viewHolderLogs._User.setText(users.get(i).getLastName() + " " + users.get(i).getFirstName() );
    viewHolderLogs._Email.setText(users.get(i).getEmail());

    HelperBase64 helperBase64 = new HelperBase64();
    if(users.get(i).getPhoto().isEmpty())
    {
        viewHolderLogs._photo.setImageResource(R.drawable.icon_no_photo);
    }
    else
    {
        viewHolderLogs._photo.setImageBitmap(helperBase64.Decode(users.get(i).getPhoto()));
    }

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
        return users.size();
    }

    public class ViewHolderLogs extends RecyclerView.ViewHolder {

        TextView _User,_Email;
        ImageView _photo;

        public ViewHolderLogs(@NonNull View itemView) {

            super(itemView);
            _User = (TextView) itemView.findViewById(R.id.RecyclerUserName);
            _Email = (TextView) itemView.findViewById(R.id.RecyclerUserEmail);
            _photo = (ImageView) itemView.findViewById(R.id.RecyclerUserPhotoId);
        }

    }
}
