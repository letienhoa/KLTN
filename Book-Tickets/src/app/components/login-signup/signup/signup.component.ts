import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  @Output() backToLogIn = new EventEmitter<Boolean>(false);
  isShow = false;
  typeInput = "password"

  constructor() { }

  ngOnInit(): void {
  }

  onBackToLogIn(){
    this.backToLogIn.emit(true);
  }

  onSignUpSuccess(){

  } 

  onShowPassword(show:boolean){
    this.isShow = show;
    this.typeInput = this.typeInput=="text"?"password":"text";
  }

}
