import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { ProductosProvider } from '../../providers/productos/productos';

import { ProductoPage } from '../index.paginas';

@Component({
  selector: 'page-busqueda',
  templateUrl: 'busqueda.html',
})
export class BusquedaPage {

  productoPage = ProductoPage;

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private _productosProvider: ProductosProvider) {
  }

  buscarProducto(ev: any){
    const val = ev.target.value;
    
    if (val && val.trim() != '') {
      this._productosProvider.buscarProducto(val);
    }else{
      this._productosProvider.productosResultado = [];
    }
  }

}
