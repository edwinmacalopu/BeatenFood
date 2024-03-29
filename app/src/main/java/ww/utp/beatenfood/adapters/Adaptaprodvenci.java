package ww.utp.beatenfood.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.models.Producto;

public class Adaptaprodvenci extends RecyclerView.Adapter<Adaptaprodvenci.ViewHolderDatos> {
        List<Producto> lis;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

public Adaptaprodvenci(List<Producto> lista ){

        lis=lista;
        }
@Override
public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View ver= LayoutInflater.from(parent.getContext()).inflate(R.layout.vistaprodvencidos,null,false);

        return new ViewHolderDatos(ver);
        }

@Override
public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.asigna(lis.get(position));
        }

@Override
public int getItemCount() {
        return lis.size();
        }

public class ViewHolderDatos extends RecyclerView.ViewHolder {
    TextView idpuser, ipnombrep,iptprod,ipcant,ipmuni,ipfechaf,ipconsu,consum,idproddd;
    //,ipfechaf,ipconsu,ipfoto;
    ImageView imagevista;
    public ViewHolderDatos(View itemView) {
        super(itemView);
       // idpuser=itemView.findViewById(R.id.txtproiduser);
        ipnombrep=itemView.findViewById(R.id.txtpronomprodvenci);
       // iptprod=itemView.findViewById(R.id.txtprotipoprod);
        ipcant=itemView.findViewById(R.id.txtprocantvenci);
        ipmuni=itemView.findViewById(R.id.txtpromediunivenci);
        idproddd=itemView.findViewById(R.id.txtprodvenci);
        imagevista=itemView.findViewById(R.id.imagendeprodallvenci);
        ipfechaf=itemView.findViewById(R.id.txtprofechainivenci);
        ipconsu=itemView.findViewById(R.id.txtprofechafinvenci);
        consum=itemView.findViewById(R.id.consumidovenci);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            }
        });
    }

    public void asigna(Producto p) {
        //idpuser.setText(""+p.getIduser());
        ipnombrep.setText(p.getNombreproducto());
       // iptprod.setText(p.getTipoproducto());
        ipcant.setText(""+p.getCantidad());
        idproddd.setText(""+p.getIdprod());
        ipmuni.setText(p.getMedidaunidad());
        ipfechaf.setText(p.getFechainicio());
        ipconsu.setText(p.getFechacaducidad());
        if(p.getConsumido().equals("0")){
            consum.setText("No Consumido");
            consum.setTextColor(Color.parseColor("#FF5252"));

        }else{
            consum.setText("Consumido");
            consum.setTextColor(Color.parseColor("#4285f4"));
        }
        String imageurl=p.getFotoproducto();
        Picasso.get().load(imageurl).fit().centerInside().into(imagevista);
    }
}
}
