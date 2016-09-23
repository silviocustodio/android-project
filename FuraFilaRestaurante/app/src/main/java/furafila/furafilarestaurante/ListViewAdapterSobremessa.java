package furafila.furafilarestaurante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 10/09/2015.
 */
public class ListViewAdapterSobremessa  extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    private List<Cardapio> list = null;
    private ArrayList<Cardapio> arraylist;

    public ListViewAdapterSobremessa(Context context, List<Cardapio> list) {
        ctx = context;
        this.list = list;
        inflater = LayoutInflater.from(ctx);
        this.arraylist = new ArrayList<Cardapio>();
        this.arraylist.addAll(list);
    }

    public class ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public List getItem(int position) {
        return (List) list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getID();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

        int imageResource = ctx.getResources().getIdentifier(list.get(position).getImage(), "drawable", ctx.getPackageName());
        holder.image.setImageResource(imageResource);

        return view;
    }
}

