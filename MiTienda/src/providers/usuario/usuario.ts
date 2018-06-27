import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

import { AlertController,
         Platform} from 'ionic-angular';

import { Storage } from '@ionic/storage';

import { URL_SERVICIOS } from '../../config/url.servicios';

@Injectable()
export class UsuarioProvider {

  token:string;
  id_usuario:string;

  constructor(public http: Http,
              private alterCtrl: AlertController,
              private platform: Platform,
              private storage: Storage) {
    console.log('Hello UsuarioProvider Provider');
    this.cargarStorage();
  }

  activo(){
    if(this.token){
      return true;
    }else{
      return false;
    }
  }

  ingresar(correo:string, password:string){
    let url = `${URL_SERVICIOS}/login`;

    let headers = new Headers({
      'Content-Type': 'application/json'
    });

    let body = {
      'correo': correo,
      'contrasena': password
    }

    return this.http.post(url, body, {headers: headers})
                    .map(resp => {
                      if(resp.status == 200){
                        let data = resp.json();
                        this.token = data.token;
                        this.id_usuario = data.id;
                        this.guardarStorage();
                      }else{
                        this.alterCtrl.create({
                          title:'Error al iniciar',
                          subTitle: 'Correo y/o contraseña no son validos',
                          buttons: ["Aceptar"]
                        }).present();
                      }
                    });
  }

  cerrarSession(){
    this.token = null;
    this.id_usuario = null;
    this.guardarStorage();
  }

  private guardarStorage(){
    if(this.platform.is("cordova")){
      this.storage.set("token", this.token);
      this.storage.set("id_usuario", this.id_usuario);
    }else{
      if(this.token && this.id_usuario){
        localStorage.setItem("token", this.token);
        localStorage.setItem("id_usuario", this.id_usuario);
      }else{
        localStorage.removeItem("token");
        localStorage.removeItem("id_usuario");
      }
    }
  }

  cargarStorage(){
    let promesa = new Promise((resolve, reject) => {
      if(this.platform.is("cordova")){
        this.storage.ready().then(() => {
          this.storage.get("token").then(token => {
            if(token){
              this.token = token;
            }
          });
          this.storage.get("id_usuario").then(id_usuario => {
            if(id_usuario){
              this.id_usuario = id_usuario;
            }
          });
          resolve();
        });
      }else{
        if(localStorage.getItem("token") && localStorage.getItem("id_usuario")){
          this.token = localStorage.getItem("token");
          this.id_usuario = localStorage.getItem("id_usuario");
        }
      }
      resolve();
    });

    return promesa;
  }

}
