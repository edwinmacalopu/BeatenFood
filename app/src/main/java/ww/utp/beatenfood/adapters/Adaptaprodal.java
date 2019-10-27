package ww.utp.beatenfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ww.utp.beatenfood.R;
import ww.utp.beatenfood.models.Producto;

public class Adaptaprodal extends RecyclerView.Adapter<Adaptaprodal.ViewHolderDatos> {
        List<Producto> lis;

public Adaptaprodal(List<Producto> lista ){

        lis=lista;
        }
@Override
public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View ver= LayoutInflater.from(parent.getContext()).inflate(R.layout.vistaprodall,null,false);

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
    TextView idpuser, ipnombrep,iptprod,ipcant,ipmuni,ipfechai;
    //,ipfechaf,ipconsu,ipfoto;
    //ImageView imagevista;
    public ViewHolderDatos(View itemView) {
        super(itemView);
        idpuser=itemView.findViewById(R.id.txtproiduser);
        ipnombrep=itemView.findViewById(R.id.txtpronomprod);
        iptprod=itemView.findViewById(R.id.txtprotipoprod);
        ipcant=itemView.findViewById(R.id.txtprocant);
        ipmuni=itemView.findViewById(R.id.txtpromediuni);
      //  imagevista=itemView.findViewById(R.id.imagenv);

    }

    public void asigna(Producto p) {
        idpuser.setText(""+p.getIduser());
        ipnombrep.setText(p.getNombreproducto());
        iptprod.setText(p.getTipoproducto());
        ipcant.setText(""+p.getCantidad());
        ipmuni.setText(p.getMedidaunidad());
        //imagevista.setImageBitmap(a.getFoto());
    }
}
}
