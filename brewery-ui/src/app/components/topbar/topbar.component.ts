import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  openMenu = false;

  constructor() { }

  ngOnInit(): void {
  }

  showMenu() {
    this.openMenu = !this.openMenu;
    console.log(this.openMenu);
  }

}