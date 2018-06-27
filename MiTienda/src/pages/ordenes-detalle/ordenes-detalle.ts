import { Component } from '@angular/core';
import { NavController, NavParams, AlertController } from 'ionic-angular';

import { CarritoProvider } from '../../providers/carrito/carrito';

@Component({
  selector: 'page-ordenes-detalle',
  templateUrl: 'ordenes-detalle.html',
})
export class OrdenesDetallePage {

  orden:any = {};

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private alertCtrl: AlertController,
              private _carritoProvider: CarritoProvider) {
                this.orden = navParams.get("orden");
                console.log(this.orden);
  }

  borrarOrden(idOrden:number) {
    const confirm = this.alertCtrl.create({
      title: 'Eliminar Orden',
      message: '¿Esta seguro que desea eliminar la orden?',
      buttons: [
        {
          text: 'Cancelar'
        },
        {
          text: 'Continuar',
          handler: () => {
            this._carritoProvider.borrarOrdenes(idOrden)
                      .subscribe(data => {
                        if(data.status == 200){
                          this.navCtrl.pop();
                        }else if(data.status == 204){
                          this._carritoProvider.mensaje('El usuario no tiene pedidos', null);
                        }else if(data.status == 401){
                          this._carritoProvider.mensaje('Error', 'Id de usuario y/o token no son validos');
                        }
                      });
          }
        }
      ]
    });
    confirm.present();
  }

}
