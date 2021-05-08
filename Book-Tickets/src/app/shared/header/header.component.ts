import { Customer } from 'src/app/model/customer';
import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { CustomerServiceService } from 'src/app/services/customer/customer-service.service';

declare var $:any

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  title = "Đăng Nhập";
  signUp = false;
  logIn = false;

  logInSuccess = false;

  customer!:Customer;
  

  @Input() index = 0;
  @Input() pointAward = "";
  @Input() totalPoint = "";

  constructor(
    private router:Router, 
    private customerService: CustomerServiceService
  ) { }

  ngOnInit(): void {
    this.customer = {} as Customer;
    this.onResetPage(this.index);
    
    if(localStorage.getItem("LogInSuccess")!=null){
      this.logInSuccess = true;
      console.log(localStorage.getItem("LogInSuccess"));
    }
    else this.logInSuccess = false;
  }

  onClick(){
    var navList = document.getElementsByClassName('nav');
    navList[0].classList.toggle('collapse');
  }

  onResetPage(index:any){
    var navList = document.getElementsByClassName('nav-items');
    var itemList = navList[0].getElementsByClassName('item');
  
    for(let i = 0; i<index; i++){
      if(i!=index){
        itemList[i].classList.add('no-pick');
      }
    } 

    if(index!=-1) itemList[index].classList.add('pick');
  }

  onLogIn(){
    $('#myModal').show();
    this.signUp = false;
    this.logIn = true;
  }

  onTicketBookingHistory(){
    if(this.customer.email==null){
      this.customer = JSON.parse(localStorage.getItem("LogInSuccess")!);
    }
    this.router.navigate(["/ticket-booking-history/",this.customer.email]);
  }

  onInforAccount(){
    if(this.customer.email==null){
      this.customer = JSON.parse(localStorage.getItem("LogInSuccess")!);
    }
    this.router.navigate(["/infor-account/", this.customer.email]);
  }

  onInforIndividual(){
    if(this.customer.email==null){
      this.customer = JSON.parse(localStorage.getItem("LogInSuccess")!);
    }
    this.router.navigate(["/infor-individual/", this.customer.email]);
  }

  onAwardPoint(){
    if(this.customer.email==null){
      this.customer = JSON.parse(localStorage.getItem("LogInSuccess")!);
    }
    this.router.navigate(["/point-award/", this.customer.email]);
  }

  onLogOut(){
    this.customerService.getLogOut().subscribe(data =>{
      localStorage.removeItem("LogInSuccess");
      this.logInSuccess = false;
     
      $('#infor-user').toggle('show');
      this.router.navigate(['/']);
    })
  }

  onShowMenuUser(){
    $('#infor-user').toggle('show');
  }

  onClose(){
    $('#myModal').hide();
  }

  onLogInSucess(event:any){
    this.customer.ten_khach_hang = event.tenKh;
    this.logInSuccess = event.status;

    this.onResetPage(0);
    $('#myModal').hide();
    if(event == false){
      return alert("Sai tài khoản mật khẩu");
    }
    else{
      if(event.role[0]=="ROLE_ADMIN")
        this.router.navigate(['/dashboard']);
    }
  }

  onGoSignUp(event:any){
    this.signUp = event;
    this.logIn = false;
  }

  onBackToLogIn(event:any){
    this.signUp = false;
    this.logIn = event;
  }

}

$(document).mouseup(function(e:any) 
{
  var container = $("#myModal");

  if (container.is(e.target)) 
  {
    container.hide();
  }
});