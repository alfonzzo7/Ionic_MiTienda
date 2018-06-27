import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { ProductoPage } from '../index.paginas';

import { ProductosProvider } from '../../providers/productos/productos';

@Component({
  selector: 'page-por-categorias',
  templateUrl: 'por-categorias.html',
})
export class PorCategoriasPage {

  categoria:any = {};
  productoPage = ProductoPage;
  masImagenes:boolean = true;

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private _productosPrv: ProductosProvider) {
                this.categoria = navParams.get("categoria");
                _productosPrv.cargarProductosPorLinea(this.categoria.id);
  }

  siguientePagina(infiniteScroll){
    this._productosPrv.cargarProductosPorLinea(this.categoria.id).then(
        (masImagenes:boolean) => {
          this.masImagenes = masImagenes;
          infiniteScroll.complete();
        });
  }

}
