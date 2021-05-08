import { Component, OnInit } from '@angular/core';
import { CustomerInfor } from '@model/customer';
import { CustomerServiceService } from 'src/app/services/customer/customer-service.service';

@Component({
  selector: 'app-award-point',
  templateUrl: './award-point.component.html',
  styleUrls: ['./award-point.component.css']
})
export class AwardPointComponent implements OnInit {

  customerInfor!: CustomerInfor;
  customerId!:Int16Array;

  constructor(private customerService: CustomerServiceService) { }

  ngOnInit(): void {
    this.customerId = JSON.parse(localStorage.getItem("LogInSuccess")!).id;
    this.getCustomerInfor(this.customerId);
  }

  getCustomerInfor(customerId:Int16Array){
    this.customerService.getCustomerInfor(customerId).subscribe(
      data => {
        this.customerInfor = data.data;
        console.log(this.customerInfor);
      }
    )
  }  

}
