package in.blogspot.iamdhariot.sqlliteapp;

/**
 * Created by Dhariot on 09-Feb-18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.mName.setText(contact.getmName());
        holder.mMobileNumber.setText(contact.getmMobileNo());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mMobileNumber,mName;
        public ViewHolder(View View) {
            super(View);
            mName  =(TextView) itemView.findViewById(R.id.nameTView);
            mMobileNumber  =(TextView) itemView.findViewById(R.id.moblileNum);

        }
    }
}
