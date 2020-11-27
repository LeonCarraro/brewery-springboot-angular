import { Component, Input, OnInit } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  animations: [
    trigger('openCloseMenu', [
      state('false', style({
        transform: 'translateX(-240px)'
      })),
      state('true', style({
        transform: 'translateX(0)'
      })),
      transition('false <=> true', animate(300))
    ])
  ]
})
export class SidebarComponent implements OnInit {

  @Input()
  openMenu: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
