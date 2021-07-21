import { Step2 } from './../../../model/book-ticket-steps';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';

declare var $:any

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {  
  isLoading = false;
  isSuccess = false;

  isViewMode = false;
  isEditMode = false;

  inforCustomer:any;
  inforRoute:any;
  inforSeat: any;

  logInSuccess: any;

  isSelectTypePay = false;
  isDiscount = false;
  type!:number;

  tickets:any[]=[];

  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.route
      .data
      .subscribe(data => {
        if (data && data.isPay) {
          this.isSuccess = false;
        }
        if (data && data.isSuccess) {
          this.isSuccess = true;
        }
      });
    this.inforCustomer = JSON.parse(sessionStorage.getItem("step3")!);
    this.inforSeat = JSON.parse(sessionStorage.getItem("step2")!);
    this.inforRoute = JSON.parse(sessionStorage.getItem("step1")!);
    this.logInSuccess = JSON.parse(localStorage.getItem("LogInSuccess")!);
    
    if(this.inforSeat.length == 2){
      this.tickets.push(this.inforSeat[0]);
      this.tickets.push(this.inforSeat[1]);
    }else{
      this.tickets.push(this.inforSeat);
    }
    if(Number(this.logInSuccess?.discount)>0){
      this.isDiscount = true;
      this.tickets.map(t=>{
        t.totalMoney = ((Number(t.totalMoney)*(100-Number(this.logInSuccess.discount)))/100).toFixed(2);
      })
    }
  }

  Loading(isLoading: boolean){
    this.isLoading = isLoading;
  }

  Success(isSuccess: boolean){
    this.isSuccess = isSuccess;
  }

  onSelectTypePay(type: number){
    this.type = type;
    this.isSelectTypePay = true;
    let pay = <HTMLCollection>document.getElementsByClassName("pay-item");
    let length = pay.length;
    for(let i = 0; i< length; i++){
      pay[i].classList.remove('pay-item-click');
    }
    pay[type].classList.add('pay-item-click');
  }

  onContinue(){
    if(!this.isSelectTypePay){
      this.toastr.warning("Xin hãy chọn hình thức thanh toán!");
      return;
    }

    $('#myModalPay').show();
  }
}

$(document).mouseup(function(e:any) 
{
  var container = $("#myModalPay");

  if (container.is(e.target)) 
  {
    $('#myModalPay').hide();
  }
});