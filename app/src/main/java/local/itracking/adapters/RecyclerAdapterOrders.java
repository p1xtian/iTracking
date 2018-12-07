package local.itracking.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import local.itracking.R;
import local.itracking.models.Client;
import local.itracking.models.Order;


public class RecyclerAdapterOrders
        extends RecyclerView.Adapter<RecyclerAdapterOrders.ViewHolderLogs>
        implements View.OnClickListener {

    ArrayList<Order> orders ;
    private View.OnClickListener listerner;

    public RecyclerAdapterOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolderLogs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_orders,null,false);
        view.setOnClickListener(this);

        return new ViewHolderLogs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLogs viewHolderLogs, int i) {
try{
    viewHolderLogs._Id.setText(

            Integer.toString(orders.get(i).getId()) +
                    " : " +
            orders.get(i).getClientName()

            );
    viewHolderLogs._ShipmentOrigin.setText(
            "SOURCE : \n" +
            orders.get(i).getSource()
    );
    viewHolderLogs._ShipmentDestination.setText(
            "DESTINATION : \n" +
            orders.get(i).getDestination()
    );
}
catch (Exception ex)
{
    Log.d("=iTracking:Error:=>",ex.toString());
}
finally {

}

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public void setOnClickListener(View.OnClickListener listener)
    {
this.listerner = listerner;
    }

    @Override
    public void onClick(View view) {
if(listerner != null)
{
    Log.d("=iTracking=>", "Recycler Listener");
    listerner.onClick(view);
}
    }

    public class ViewHolderLogs extends RecyclerView.ViewHolder {

        TextView _Id,_ShipmentOrigin,_ShipmentDestination,_NumberOfBoxs;
       // ImageView _photo;

        public ViewHolderLogs(@NonNull View itemView) {

            super(itemView);
            _Id = (TextView) itemView.findViewById(R.id.RecyclerOrderId);
            _ShipmentOrigin = (TextView) itemView.findViewById(R.id.RecyclerOrderOrigin);
            _ShipmentDestination = (TextView) itemView.findViewById(R.id.RecyclerOrderDestination);
        }

    }
}
