package ControladorRepartidor;

import Estructuras.Colas.ColasList;
import Estructuras.DinamicQueue.Queue;
import IServices.IRepartidor;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ControladorRepartidor implements Serializable {
    private IRepartidor service;
    private String ip;
    private String port;
    private String serviceName;
    private String url;

    public ControladorRepartidor(String ip, String port, String serviceName) {
        this.service = null;
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
    }


    public boolean validarUsuario(String nombre, String contraseña) throws RemoteException {
        try{
            service = (IRepartidor) Naming.lookup(url);
            return service.validarUsuario(nombre,contraseña);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Queue imprimirRuta() throws RemoteException {
        try{
            service = (IRepartidor) Naming.lookup(url);
            return service.imprimirRuta();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean estadoPedido(Boolean estado) throws RemoteException {
        try{
            service = (IRepartidor) Naming.lookup(url);
            return service.estadoPedido(estado);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean disponibilidadRepartidor() throws RemoteException {
        try{
            service = (IRepartidor) Naming.lookup(url);
            return service.disponibilidadRepartidor();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
