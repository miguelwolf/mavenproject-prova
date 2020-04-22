package br.com.miguelwolf.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.miguelwolf.model.Cliente;

/**
 *
 * @author Miguel Wolf
 */
public class ClienteDAO {
    
    private static List<Cliente> mListCliente = new ArrayList<Cliente>();
    private static int cod = 1;
    
    public static void persistir(Cliente cli) {    
        cli.setCodigo(getCod());
        mListCliente.add(cli);
    }
    
    public static void deletar(Cliente cli){
        try {
            mListCliente.remove(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void atualizar(Cliente cli){
        mListCliente.set(mListCliente.indexOf(cli), cli);
    }
    
    public static Cliente getCliente(int cod) {
        for (int i = 0; i < mListCliente.size(); i++) {
            if (mListCliente.get(i).getCodigo() == cod) {
                return mListCliente.get(i);
            }
        }
        
        return null;
    }

    public static int getCod() {
        return cod++;
    }

    public static List<Cliente> getmListCliente() {
        return mListCliente;
    }            
    
}
