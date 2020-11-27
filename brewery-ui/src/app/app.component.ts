import { Component } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('canApplyLeftMargin', [
      state('false', style({
      })),
      state('true', style({
        'margin-left': '260px'
      })),
      transition('false <=> true', animate(300))
    ])
  ]
})
export class AppComponent {

  resizeScreen = false;
  minimumScreenWidth: boolean;

  constructor(
  ) { }

}
