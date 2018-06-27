import { Component } from '@angular/core';
import { NavController, NavParams, ViewController, LoadingController } from 'ionic-angular';

import { UsuarioProvider } from '../../providers/usuario/usuario';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  correo:string = "";
  password:string = "";

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private viewCtrl: ViewController,
              private loadingCtrl: LoadingController,
              private _usuarioProvider: UsuarioProvider) {
  }

  ingresar(){
    const loader = this.loadingCtrl.create({
      content: "Espere por favor..."
    });
    loader.present();
    this._usuarioProvider.ingresar(this.correo, this.password)
            .subscribe(() => {
              if(this._usuarioProvider.activo()){
                this.viewCtrl.dismiss(true);
              }
              loader.dismiss();
            });
  }

}
