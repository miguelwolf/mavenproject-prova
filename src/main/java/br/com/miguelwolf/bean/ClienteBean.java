package br.com.miguelwolf.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.core.type.TypeReference;
import br.com.miguelwolf.controller.ClienteDAO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import br.com.miguelwolf.model.Cidade;
import br.com.miguelwolf.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Miguel Wolf
 */
@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {

    private Cliente cliente;

    public List<Cliente> mListCliente;
    public List<Cidade> mListCidade;
    public List<String> mListNomes = new ArrayList<>(Arrays.asList("Miguel", "Arthur", "Heitor", "Bernardo", "Théo", "Davi",
            "Gabriel", "Pedro", "Samuel", "Lorenzo", "Benjamin", "Matheus", "Lucas", "Benício", "Gael", "Joaquim", "Nicolas", "Henrique", "Rafael",
            "Isaac", "Guilherme", "Murilo", "Lucca", "Gustavo", "João Miguel", "Noah", "Felipe", "Anthony", "Enzo", "João Pedro", "Pietro", "Bryan",
            "Daniel", "Pedro Henrique", "Enzo Gabriel", "Leonardo", "Vicente", "Valentim", "Eduardo", "Antônio", "Emanuel", "Davi Lucca", "Bento",
            "João", "João Lucas", "Caleb", "Levi", "Vitor", "Enrico", "Cauã", "Caio", "Vinícius", "Henry", "João Gabriel", "Augusto", "Ravi",
            "Francisco", "Otávio", "Davi Lucas", "João Guilherme", "Thomas", "Ícaro", "Theodoro", "João Vitor", "Luiz Miguel", "Yan", "Yuri Thiago",
            "Arthur Miguel", "Nathan", "Erick", "Breno", "Luiz Felipe", "Anthony Gabriel", "Martin", "Matteo", "Oliver", "Arthur Gabriel", "Ryan",
            "Raul", "Luan", "Tomás", "Mathias", "Davi Luiz", "Pedro Lucas", "Derick", "Vitor Hugo", "Kauê", "Lucas Gabriel", "Arthur Henrique",
            "Rodrigo", "Bruno", "Davi Miguel", "Yago", "José", "Pedro Miguel", "Luiz Henrique", "Hugo", "Otto", "Josué"));

    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
    }

    @PostConstruct
    public void init() {
        
        cliente = new Cliente();
        mListCliente = new ArrayList<Cliente>();
//        mListCidade = new ArrayList<Cidade>();

        try {
            URL url = new URL("http://maventest.herokuapp.com/mavenTest-1.0-SNAPSHOT/webresources/cidade");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String json = content.toString();

                ObjectMapper mapper = new ObjectMapper();
                mListCidade = mapper.readValue(json, new TypeReference<List<Cidade>>(){});

                con.disconnect();
            } else {
                mListCidade = new ArrayList<>();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            mListCidade = new ArrayList<>();
            
        } catch (ProtocolException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            mListCidade = new ArrayList<>();
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            mListCidade = new ArrayList<>();
        }

    }

    public void salvar() {
        
        ClienteDAO.persistir(cliente);
        cliente = new Cliente();
        mListCliente = ClienteDAO.getmListCliente();

    }

    public void salvarAdd5() {

        for (int i = 0; i < 5; i++) {
            cliente = gerarAutomaticamente();

            ClienteDAO.persistir(cliente);
            cliente = new Cliente();
            mListCliente = ClienteDAO.getmListCliente();
        }

    }

    public Cliente gerarAutomaticamente() {

        List<Cidade> mListCidadeTemp = mListCidade;
        Collections.shuffle(mListCidadeTemp);
        Collections.shuffle(mListNomes);

        return new Cliente(ClienteDAO.getCod(), mListNomes.get(0), mListCidadeTemp.get(0));
    }

//    public void pegarCidades() {
//        try {
//            URL url = new URL("http://maventest.herokuapp.com/mavenTest-1.0-SNAPSHOT/webresources/cidade");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setDoOutput(true);
//
//            int status = con.getResponseCode();
//
//            if (status == 200) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuffer content = new StringBuffer();
//                while ((inputLine = in.readLine()) != null) {
//                    content.append(inputLine);
//                }
//                in.close();
//                String json = content.toString();
//
//                ObjectMapper obMapper = new ObjectMapper();
//                mListCidade = obMapper.readValue(json, new TypeReference<List<Cidade>>() {
//                });
//
//                con.disconnect();
//            } else {
//                mListCidade = new ArrayList<>();
//            }
//        } catch (Exception e) {
//            mListCidade = new ArrayList<>();
//        }
//
//    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getmListCliente() {
        return mListCliente;
    }

    public void setmListCliente(List<Cliente> mListCliente) {
        this.mListCliente = mListCliente;
    }

    public List<Cidade> getmListCidade() {
        return mListCidade;
    }

    public void setmListCidade(List<Cidade> mListCidade) {
        this.mListCidade = mListCidade;
    }   
    
}
