import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-beer-table',
  templateUrl: './beer-table.component.html',
  styleUrls: ['./beer-table.component.css']
})
export class BeerTableComponent implements OnInit {

  beers = [
    {sku: 'AA0001', name: 'Colorado 350ml', origin: 'Nacional', price: 13.70},
    {sku: 'AA0002', name: 'Skol 1000ml', origin: 'Nacional', price: 12.0},
    {sku: 'AA0003', name: 'Budweiser 269ml', origin: 'Interacional', price: 2.99},
    {sku: 'AA0001', name: 'Colorado 350ml', origin: 'Nacional', price: 13.70},
    {sku: 'AA0002', name: 'Skol 1000ml', origin: 'Nacional', price: 12.0},
    {sku: 'AA0003', name: 'Budweiser 269ml', origin: 'Interacional', price: 2.99},
    {sku: 'AA0001', name: 'Colorado 350ml', origin: 'Nacional', price: 13.70},
    {sku: 'AA0002', name: 'Skol 1000ml', origin: 'Nacional', price: 12.0},
    {sku: 'AA0003', name: 'Budweiser 269ml', origin: 'Interacional', price: 2.99},
    {sku: 'AA0001', name: 'Colorado 350ml', origin: 'Nacional', price: 13.70},
    {sku: 'AA0002', name: 'Skol 1000ml', origin: 'Nacional', price: 12.0},
    {sku: 'AA0003', name: 'Budweiser 269ml', origin: 'Interacional', price: 2.99},
    {sku: 'AA0001', name: 'Colorado 350ml', origin: 'Nacional', price: 13.70},
    {sku: 'AA0002', name: 'Skol 1000ml', origin: 'Nacional', price: 12.0},
    {sku: 'AA0003', name: 'Budweiser 269ml', origin: 'Interacional', price: 2.99}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
