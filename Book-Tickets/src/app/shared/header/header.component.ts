import { ToastrService } from 'ngx-toastr';
import { Customer } from 'src/app/model/customer';
import { Router } from '@angular/router';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CustomerServiceService } from 'src/app/services/customer/customer-service.service';

declare var $:any

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  email = "lekimdinh1412@gmail.com";
  isLoad = false;
  title = "Đăng Nhập";
  signUp = false;
  logIn = false;

  logInSuccess = false;
  isAdmin = false;

  customer: Customer = {} as Customer;
  

  @Input() index:number = 0;
  @Input() pointAward = "";
  @Input() totalPoint = "";

  constructor(
    private router:Router, 
    private customerService: CustomerServiceService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    if(localStorage.getItem("LogInSuccess")!=null){
      this.logInSuccess = true;
      this.customer = JSON.parse(localStorage.getItem("LogInSuccess")!);
      let role = new Array(this.customer.Roles)
      if(role[0]?.toString()=="ROLE_ADMIN"){
        this.isAdmin = true;
      }
    }
    else this.logInSuccess = false;

  }

  onClick(){
    var navList = document.getElementsByClassName('nav');
    navList[0].classList.toggle('collapse');
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

  onDashboard(){
    this.router.navigate(["/dashboard"]);
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
    this.isAdmin = false;
    localStorage.removeItem("LogInSuccess");
    location.reload();
    this.router.navigate(["/"]);
  }

  onShowMenuUser(){
    $('#infor-user').toggle('show');
  }

  onClose(){
    $('#myModal').hide();
  }

  onLogInSucess(event:any){
    $('#myModal').hide();
    if(!event) return;
    this.customer.ten_khach_hang = event.tenKh;
    this.logInSuccess = event.status;

    this.index = 0;
    
    if(event == false){
      this.toastr.warning("Sai tài khoản mật khẩu");
      return
    }
    else{
      if(event.role[0]=="ROLE_ADMIN"){
        this.router.navigate(['/dashboard']);
        this.isAdmin = true;
      }
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

  onLoading(event:any){
    this.isLoad = event;
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