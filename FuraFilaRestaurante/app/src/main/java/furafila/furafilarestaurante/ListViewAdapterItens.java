package furafila.furafilarestaurante;

/**
 * Created by Silvio on 09/09/2015.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ListViewAdapterItens extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    private List<Cardapio> list = null;
    private List<Cardapio> listPedidos = null;
    private ArrayList<Cardapio> arraylist;
    int numero_itens = 0;
    private RequestQueue requestQueue;
    private static final String URL = "http://192.168.0.106:80/login/user_control.php"; //172.16.5.71//10.0.0.106
    private int tipoPedido = 0;

    public ListViewAdapterItens(Context context, List<Cardapio> list, int tipoPedido) {
        ctx = context;
        this.list = list;
        inflater = LayoutInflater.from(ctx);
        this.arraylist = new ArrayList<Cardapio>();
        this.listPedidos = new ArrayList<Cardapio>();
        this.arraylist.addAll(list);
        this.tipoPedido = tipoPedido;
        requestQueue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
        Button btdecrementar, btincrementar;
        EditText edcontador;
        ImageButton bt_adicionar;
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
            view = inflater.inflate(R.layout.listview, null);//view = inflater.inflate(R.layout.listview, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.bt_adicionar = (ImageButton) view.findViewById(R.id.bt_adicionar);
            holder.btdecrementar = (Button) view.findViewById(R.id.botao_menos);
            holder.btincrementar = (Button) view.findViewById(R.id.botao_mais);
            holder.edcontador = (EditText) view.findViewById(R.id.ed_contador);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //Cardapio prato = getPrato(position);

        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

        // if(numero_itens >= 0) {
        holder.btincrementar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (numero_itens >= 0) {
                    // numero_itens = numero_itens - numero_itens;
                    numero_itens++;
                    holder.edcontador.setText("" + numero_itens);

                }

            }
        });

        // }
        holder.btdecrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numero_itens >= 1) {
                    numero_itens--;
                    holder.edcontador.setText("" + numero_itens);
                }

            }
        });

        holder.bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 0;
                Random random = new Random();
                int x = random.nextInt(1000);
                holder.edcontador.setText("" + numero_itens);
                Cardapio prato = getPrato(position);
                listPedidos.add(prato);
                Toast.makeText(ctx, "total " + numero_itens + " PRATO->> " + prato._name, Toast.LENGTH_SHORT).show();
                switch (tipoPedido) {//sobre,bebi,refe
                    case 1://FOOD
                        enviaPedido(x, 1, 5, 5, prato.getID(), numero_itens, 0, 0, time);
                        break;
                    case 2://DRINK
                        enviaPedido(x, 1, 5, prato.getID(), 5, numero_itens, 0, 0, time);
                        break;
                    case 3://DESSERT
                        enviaPedido(x, 1, prato.getID(), 5, 5, 0, numero_itens, 0, time);
                        break;

                }

numero_itens=0;
                // /

            }

        });

        int imageResource = ctx.getResources().getIdentifier(list.get(position).getImage(), "drawable", ctx.getPackageName());
        holder.image.setImageResource(imageResource);

        return view;
    }


    public Cardapio getPrato(int position) {
        Cardapio prato = list.get(position);
        return prato;
    }

    private void enviaPedido(final int idorder, final int idCliente, final int tbdessert_idtbdessert,
                             final int tbdrink_idtbdrink, final int tbfood_idtbfood, final int qnt_drink,
                             final int qnt_food, final int qnt_dessert, final int order_time) {


        //String url = "http://correiosapi.apphb.com/cep/76873274";

        HashMap<String, String> param = new HashMap<>();
        //  param.put("idtbdessert", String.valueOf(codigoPrato));
        //param.put("idcliente","1231");


        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //  JSONObject jsonObject = new JSONObject(response);
                    Log.i("pedido", response.toString());
                   /* if (jsonObject.names().get(0).equals("success")) {

                    } else {

                    }*/

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idorder", String.valueOf(idorder));
                param.put("idCliente", String.valueOf(idCliente));
                param.put("tbdessert_idtbdessert", String.valueOf(tbdessert_idtbdessert));
                param.put("tbdrink_idtbdrink", String.valueOf(tbdrink_idtbdrink));
                param.put("tbfood_idtbfood", String.valueOf(tbfood_idtbfood));
                param.put("qnt_drink", String.valueOf(qnt_drink));
                param.put("qnt_food", String.valueOf(qnt_food));
                param.put("qnt_dessert", String.valueOf(qnt_dessert));
                param.put("order_time", String.valueOf(order_time));

                return param;
            }
        };

        requestQueue.add(request);
    }
}




