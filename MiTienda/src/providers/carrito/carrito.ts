import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

import { Storage } from '@ionic/storage';

import { AlertController,
        ToastController,
        Platform,
        ModalController } from 'ionic-angular';

import { LoginPage, CarritoPage } from '../../pages/index.paginas';

import { UsuarioProvider } from '../usuario/usuario';

import { URL_SERVICIOS } from '../../config/url.servicios';

@Injectable()
export class CarritoProvider {

  items:any[] = [];
  ordenes:any[] = [];
  total:number = 0;

  constructor(public http: Http,
              private alertCtrl: AlertController,
              private toastCtrl: ToastController,
              private modalCtrl: ModalController,
              private platform: Platform,
              private storage: Storage,
              private _usuarioProvider: UsuarioProvider) {
    console.log('Hello CarritoProvider Provider');

    this.cargarStorage();
    this.actualizarTotal();
  }

  verCarrito(){
    let modal:any;

    if(this._usuarioProvider.token){
      modal = this.modalCtrl.create(CarritoPage);
    }else{
      modal = this.modalCtrl.create(LoginPage);
    }

    modal.present();

    modal.onDidDismiss((abrirCarrito:boolean) => {
      if(abrirCarrito){
        this.modalCtrl.create(CarritoPage).present();
      }
    });
  }

  agregarItem(itemParam:any){
    for(let item of this.items){
      if(item.codigo == itemParam.codigo){
        this.mensaje(itemParam.producto, 'El articulo ya se encuentra en el carrito de compra');
        return;
      }
    }

    this.items.push(itemParam);
    this.actualizarTotal();
    this.guardarStorage();
    this.toastCtrl.create({
      message: 'Articulo añadido al carrito de compra',
      position: 'bottom',
      duration: 2000
    }).present();
  }

  eliminar(index:number){
    this.items.splice(index, 1);
    this.actualizarTotal();
    this.guardarStorage();
  }

  actualizarTotal(){
    this.total = 0;
    for(let item of this.items){
      this.total += Number(item.precioCompra);
    }
  }

  confirmarFinalizarPedido() {
    const confirm = this.alertCtrl.create({
      title: 'Finalizar Pedido',
      message: '¿Esta seguro que desea finalizar su pedido?',
      buttons: [
        {
          text: 'Cancelar'
        },
        {
          text: 'Continuar',
          handler: () => {
            this.finalizarPedido();
          }
        }
      ]
    });
    confirm.present();
  }

  finalizarPedido(){
    let url = `${URL_SERVICIOS}/pedido/nuevo`;

    let headers = new Headers({
      'Content-Type': 'application/json'
    });

    let productos:any[] = [];
    for(let item of this.items){
      productos.push({'codigo':item.codigo});
    }

    let body = {
      'token': this._usuarioProvider.token,
      'idUsuario': this._usuarioProvider.id_usuario,
      'productos': productos
    }

    this.http.post(url, body, {headers: headers})
             .subscribe(resp => {
               if(resp.status == 200){
                 this.items = [];
                 this.actualizarTotal();
                 this.guardarStorage();
                 this.mensaje('Orden realizada', 'Contactaremos en breve con usted para formalizar la orden');
               }else if(resp.status == 204){
                 this.mensaje('Error', 'Alguno de los productos del pedido no existen');
               }else if(resp.status == 400){
                 this.mensaje('Error', 'Ocurrio un error al intentar crear la orden');
               }else if(resp.status == 401){
                 this.mensaje('Error', 'Id de usuario y/o token no son validos');
               }
             });
  }

  cargarOrdenes(){
    if(this._usuarioProvider.token){
      let url = `${URL_SERVICIOS}/pedido/obtener`;

      let headers = new Headers({
        'Content-Type': 'application/json'
      });

      let body = {
        'token': this._usuarioProvider.token,
        'idUsuario': this._usuarioProvider.id_usuario
      }

      this.http.post(url, body, {headers: headers})
               .subscribe(resp => {
                 if(resp.status == 200){
                   this.ordenes = resp.json();
                 }else if(resp.status == 204){
                   this.mensaje('El usuario no tiene pedidos', null);
                   this.ordenes = [];
                 }else if(resp.status == 401){
                   this.mensaje('Error', 'Id de usuario y/o token no son validos');
                 }
               });
    }
  }

  borrarOrdenes(idOrden:number){
    let url = `${URL_SERVICIOS}/pedido/eliminar`;

    let headers = new Headers({
      'Content-Type': 'application/json'
    });

    let body = {
      'token': this._usuarioProvider.token,
      'idUsuario': this._usuarioProvider.id_usuario,
	    'idOrden': idOrden
    }

    return this.http.delete(url, {headers: headers, body: body})
             .map(resp => {
               return resp;
             });
  }

  mensaje(title:string, subTitle:string){
    this.alertCtrl.create({
      title:title,
      subTitle:subTitle,
      buttons:["Aceptar"]
    }).present();
  }

  private guardarStorage(){
    if(this.platform.is("cordova")){
      this.storage.set("carrito", this.items);
    }else{
      localStorage.setItem("carrito", JSON.stringify(this.items));
    }
  }

  private cargarStorage(){
    let promesa = new Promise((resolve, reject) => {
      if(this.platform.is("cordova")){
        this.storage.ready().then(() => {
          this.storage.get("carrito").then(carrito => {
            if(carrito){
              this.items = carrito;
            }
            resolve();
          });
        });
      }else{
        if(localStorage.getItem("carrito")){
          this.items = JSON.parse(localStorage.getItem("carrito"));
        }
      }
      resolve();
    });

    return promesa;
  }

}
