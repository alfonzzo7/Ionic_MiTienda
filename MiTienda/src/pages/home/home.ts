import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { ProductoPage } from '../index.paginas';

import { ProductosProvider } from '../../providers/productos/productos';
import { CarritoProvider } from '../../providers/carrito/carrito';
import { UsuarioProvider } from '../../providers/usuario/usuario';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  masImagenes:boolean = true;

  constructor(public navCtrl: NavController,
              private _productosPrv: ProductosProvider,
              private _carritoProvider: CarritoProvider,
              private _usuarioProvider: UsuarioProvider) {}

  siguientePagina(infiniteScroll){
    this._productosPrv.cargarProductos().then(
        (masImagenes:boolean) => {
          this.masImagenes = masImagenes;
          infiniteScroll.complete();
        });
  }

  verProducto(producto){
    this.navCtrl.push(ProductoPage, {"producto":producto});
  }

}
