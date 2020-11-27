import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-beer-search',
  templateUrl: './beer-search.component.html',
  styleUrls: ['./beer-search.component.css']
})
export class BeerSearchComponent implements OnInit {

  styles: any[];
  flavors: any[];

  constructor() {
    this.styles = [
      {name: 'Amber Lager'},
      {name: 'Dark Lager'},
      {name: 'Pale Lager'},
      {name: 'Pilsner'},
    ];

    this.flavors = [
      {name: 'Adocicada'},
      {name: 'Amarga'},
      {name: 'Forte'},
      {name: 'Doce'},
      {name: 'Frutada'},
    ];
  }

  ngOnInit(): void {
  }

}
